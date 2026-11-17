#!/usr/bin/env bash
# =============================================================================
# Serveur Paper local pour tester le plugin, sans toucher à la production.
#
# Le script compile le plugin, prépare un serveur Paper hors-ligne dans
# ~/mc-test (modifiable) et le lance dans Docker.
#
# Le type de serveur (hub, skyblock, survie, creatif, anarchie, rpg, opprison,
# test) se choisit avec --server : le plugin le lit dans la variable d'env
# SERVER_NAME (il ne regarde pas le port).
#
# Dépendances : docker, mvn (JDK 21), curl, python3, unzip
#
# Exemples :
#   ./scripts/test-local.sh # skyblock (défaut)
#   ./scripts/test-local.sh --server hub # hub
#   ./scripts/test-local.sh --server survie --port 25566
#   ./scripts/test-local.sh --no-run # prépare seulement
#   ./scripts/test-local.sh --fresh # repart de zéro
# =============================================================================
set -euo pipefail

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
REPO_DIR="$(dirname "$SCRIPT_DIR")"

# --- réglages (surchargables par variables d'environnement) ------------------
TEST_DIR="${TEST_DIR:-$HOME/mc-test}" # dossier de travail
DOCKER_SERVER_DIR="${DOCKER_SERVER_DIR:-$HOME/repo/docker-server}" # config Paper + cartes
DATA_DIR="${DATA_DIR:-$HOME/repo/server/Serveurs/DataFolder}" # anciennes données (structures, joueurs)
# Valeurs Paper par défaut : celles du .env du dépôt docker-server
env_value() {
    [ -f "$DOCKER_SERVER_DIR/.env" ] || return 0
    python3 - "$DOCKER_SERVER_DIR/.env" "$1" <<'PY'
import sys

path, key = sys.argv[1], sys.argv[2]
for line in open(path, encoding="utf-8", errors="replace"):
    line = line.strip()
    if not line or line.startswith("#") or "=" not in line:
        continue
    name, value = line.split("=", 1)
    if name.strip() != key:
        continue
    value = value.strip()
    if len(value) >= 2 and value[0] == value[-1] and value[0] in "'\"":
        value = value[1:-1]
    print(value)
    break
PY
}
MC_VERSION="${MC_VERSION:-$(env_value PAPER_VERSION)}" # version Paper (comme la prod)
MC_VERSION="${MC_VERSION:-1.21.11}"
PAPER_BUILD="${PAPER_BUILD:-$(env_value PAPER_BUILD)}" # build Paper (comme la prod)
PAPER_BUILD="${PAPER_BUILD:-132}"
MEMORY="${MEMORY:-1G}" # tas Java du serveur de test
SERVER_NAME="${SERVER_NAME:-skyblock}" # hub, skyblock, survie...
DOMAIN_NAME="${DOMAIN_NAME:-localhost}" # affiché par le plugin (tab, boss bar, MOTD)
TLD="${TLD:-}" # vide en local, ".fr" en prod
MC_PORT="${MC_PORT:-25565}"
RCON_PORT="${RCON_PORT:-}" # défaut : MC_PORT + 10
CONTAINER="${CONTAINER:-}"
IMAGE="${IMAGE:-eclipse-temurin:25-jre-alpine}"

# --- options ----------------------------------------------------------------
BUILD=1
RUN=1
FRESH=0
WITH_DATA=0
CPUS=""

usage() {
    cat <<'EOF'
Usage : scripts/test-local.sh [options]

  --server TYPE      type de serveur : hub, skyblock, survie, creatif,
                     anarchie, rpg, opprison, test (défaut : skyblock)
  --fresh            supprime le dossier de test avant de recommencer
  --no-build         ne recompile pas le plugin (utilise target/plugin-1.0-fat.jar)
  --no-run           prépare le dossier mais ne lance pas Docker
  --port N           port du serveur (défaut : 25565)
  --rcon-port N      port RCON (défaut : port + 10)
  --memory TAILLE    tas Java (défaut : 1G)
  --cpus N           limite de CPU du conteneur (ex. : 2)
  --dir CHEMIN       dossier de test (défaut : ~/mc-test)
  --data CHEMIN      anciennes données du plugin (structures, joueurs)
  --with-data        copie aussi les anciennes données (joueurs, îles, argent)
  -h, --help         affiche cette aide

Le type de serveur passe par la variable SERVER_NAME lue par le plugin :
le port n'influence pas le type, il sert seulement à se connecter.

Variables d'environnement : TEST_DIR, DOCKER_SERVER_DIR, DATA_DIR, MC_VERSION, PAPER_BUILD, MEMORY, SERVER_NAME, DOMAIN_NAME, TLD, MC_PORT, RCON_PORT, CONTAINER, IMAGE
EOF
}

info() { printf '\033[1;34m→\033[0m %s\n' "$*"; }
die() {
    printf '\033[1;31mErreur :\033[0m %s\n' "$*" >&2
    exit 1
}
need() {
    command -v "$1" >/dev/null 2>&1 || die "« $1 » est requis mais introuvable. $2"
}

while [ $# -gt 0 ]; do
    case "$1" in
        --server) SERVER_NAME="$2"; shift ;;
        --fresh) FRESH=1 ;;
        --no-build) BUILD=0 ;;
        --no-run) RUN=0 ;;
        --port) MC_PORT="$2"; shift ;;
        --rcon-port) RCON_PORT="$2"; shift ;;
        --memory) MEMORY="$2"; shift ;;
        --cpus) CPUS="$2"; shift ;;
        --dir) TEST_DIR="$2"; shift ;;
        --data) DATA_DIR="$2"; shift ;;
        --with-data) WITH_DATA=1 ;;
        -h | --help) usage; exit 0 ;;
        *) die "option inconnue : $1 (voir --help)" ;;
    esac
    shift
done

# --- type de serveur --------------------------------------------------------
case "$SERVER_NAME" in
    hub) MAP_NAME=Hub ;;
    survie) MAP_NAME=Survie ;;
    anarchie | test) MAP_NAME=Survie ;;
    skyblock) MAP_NAME=SkyBlock ;;
    opprison) MAP_NAME=OPPrison ;;
    creatif | rpg) MAP_NAME="" ;;
    *) die "type de serveur inconnu : $SERVER_NAME (hub, skyblock, survie, creatif, anarchie, rpg, opprison, test)" ;;
esac

# Structure de spawn posée par le plugin (voir structure.SpawnName)
case "$SERVER_NAME" in
    hub | creatif | rpg) SPAWN_STRUCTURE=spawn ;;
    skyblock) SPAWN_STRUCTURE=spawn_SB ;;
    survie | anarchie) SPAWN_STRUCTURE=spawn_Survie ;;
    opprison) SPAWN_STRUCTURE=spawn_OPPrison ;;
    test) SPAWN_STRUCTURE=spawn_Survie ;;
esac

if [ -z "$RCON_PORT" ]; then
    RCON_PORT=$((MC_PORT + 10))
fi
if [ -z "$CONTAINER" ]; then
    CONTAINER="mc-plugin-test-$SERVER_NAME"
fi

# --- dépendances ------------------------------------------------------------
need curl "Installer curl (paquet curl)."
need python3 "Installer Python 3 (paquet python3)."
need unzip "Installer unzip (paquet unzip)."
if [ "$RUN" = 1 ]; then
    need docker "Installer Docker et vérifier l'accès (groupe docker)."
    docker info >/dev/null 2>&1 || die "Docker ne répond pas (démon arrêté ou droits insuffisants)."
fi
if [ "$BUILD" = 1 ]; then
    need mvn "Installer Maven (paquet maven)."
    need java "Installer un JDK 21 (paquet openjdk-21-jdk)."
fi

# --- compilation ------------------------------------------------------------
JAR="$REPO_DIR/target/plugin-1.0-fat.jar"
if [ "$BUILD" = 1 ]; then
    info "Compilation du plugin (mvn clean package)"
    (cd "$REPO_DIR" && mvn -B clean package -DskipTests)
fi
[ -f "$JAR" ] || die "jar introuvable : $JAR (lancer sans --no-build)"

# --- dossier de test --------------------------------------------------------
if [ "$FRESH" = 1 ]; then
    info "Suppression de $TEST_DIR"
    rm -rf "$TEST_DIR"
fi
mkdir -p "$TEST_DIR/plugins" "$TEST_DIR/config" "$TEST_DIR/DataFolder/structures"

# Un dossier = un type de serveur (les mondes et la carte en dépendent)
if [ -f "$TEST_DIR/.server-type" ]; then
    PREVIOUS_TYPE="$(cat "$TEST_DIR/.server-type")"
    if [ "$PREVIOUS_TYPE" != "$SERVER_NAME" ]; then
        die "$TEST_DIR a été préparé pour « $PREVIOUS_TYPE ».
       Relancer avec --fresh ou choisir un autre dossier :
       ./scripts/test-local.sh --server $SERVER_NAME --dir $HOME/mc-test-$SERVER_NAME"
    fi
fi
printf '%s\n' "$SERVER_NAME" >"$TEST_DIR/.server-type"

# --- Paper ------------------------------------------------------------------
PAPER_JAR="$TEST_DIR/paper.jar"
PAPER_STAMP="$TEST_DIR/.paper-${MC_VERSION}-${PAPER_BUILD}"
if [ ! -f "$PAPER_JAR" ] || [ ! -f "$PAPER_STAMP" ]; then
    info "Téléchargement de Paper ${MC_VERSION} build ${PAPER_BUILD}"
    PAPER_URL="$(curl -fsSL "https://fill.papermc.io/v3/projects/paper/versions/${MC_VERSION}/builds/${PAPER_BUILD}" |
        python3 -c 'import json, sys; print(json.load(sys.stdin)["downloads"]["server:default"]["url"])')"
    [ -n "$PAPER_URL" ] || die "URL Paper introuvable pour ${MC_VERSION} build ${PAPER_BUILD}"
    curl -fsSL -o "$PAPER_JAR" "$PAPER_URL"
    printf '%s\n' "$PAPER_URL" >"$PAPER_STAMP"
else
    info "Paper déjà téléchargé"
fi

# --- plugin -----------------------------------------------------------------
cp "$JAR" "$TEST_DIR/plugins/plugin.jar"
info "Plugin copié dans $TEST_DIR/plugins/plugin.jar"
info "Type de serveur : $SERVER_NAME (variable SERVER_NAME du plugin)"

# --- configuration Paper (dépôt docker-server) ------------------------------
PAPER_CONFIG_SRC="$DOCKER_SERVER_DIR/stacks/minecraft/paper/config/paper-global.yml"
if [ -f "$PAPER_CONFIG_SRC" ]; then
    cp "$PAPER_CONFIG_SRC" "$TEST_DIR/config/paper-global.yml"
    # Velocity est désactivé : le serveur de test est joignable directement
    python3 - "$TEST_DIR/config/paper-global.yml" <<'PY'
import sys
path = sys.argv[1]
text = open(path).read()
text = text.replace("  velocity:\n    enabled: true", "  velocity:\n    enabled: false")
open(path, "w").write(text)
PY
    info "paper-global.yml copié depuis $DOCKER_SERVER_DIR"
else
    info "paper-global.yml introuvable dans $DOCKER_SERVER_DIR : configuration Paper par défaut (Velocity désactivé)"
fi

# --- données du plugin -------------------------------------------------------
if [ "$WITH_DATA" = 1 ]; then
    if [ -d "$DATA_DIR" ]; then
        info "Copie des anciennes données depuis $DATA_DIR"
        cp -a "$DATA_DIR/." "$TEST_DIR/DataFolder/"
    else
        info "Anciennes données introuvables dans $DATA_DIR : ignorées"
    fi
fi

# --- monde de spawn (carte de la prod) --------------------------------------
# Les cartes du dépôt sont soit des zip (« Spawn/... »), soit des tar
# (« Serveurs/<Carte>/Spawn/... ») : on gère les deux comme l'entrypoint Paper.
install_map() {
    archive="$1"
    map="$2"
    tmp="$(mktemp -d)"
    if tar -tf "$archive" >/dev/null 2>&1; then
        tar -xf "$archive" -C "$tmp"
    else
        unzip -q -o "$archive" -d "$tmp"
    fi
    source_dir="$tmp/Serveurs/$map/Spawn"
    [ -d "$source_dir" ] || source_dir="$tmp/Spawn"
    if [ ! -d "$source_dir" ]; then
        rm -rf "$tmp"
        die "monde Spawn introuvable dans $archive"
    fi
    rm -rf "$TEST_DIR/Spawn"
    mv "$source_dir" "$TEST_DIR/Spawn"
    rm -rf "$tmp"
}

LEVEL_TYPE='minecraft\:normal'
if [ -n "$MAP_NAME" ]; then
    SPAWN_ZIP="$DOCKER_SERVER_DIR/stacks/minecraft/paper/maps/${MAP_NAME}/Spawn.zip"
    if [ -f "$SPAWN_ZIP" ] && [ ! -f "$TEST_DIR/Spawn/level.dat" ]; then
        info "Installation de la carte $MAP_NAME (Spawn)"
        install_map "$SPAWN_ZIP" "$MAP_NAME"
    fi
fi
if [ ! -f "$TEST_DIR/Spawn/level.dat" ]; then
    if [ -n "$MAP_NAME" ]; then
        LEVEL_TYPE='minecraft\:flat'
        info "Carte $MAP_NAME introuvable : monde superflat (le vrai spawn n'est pas testé)"
    fi
fi

# --- structures du plugin ----------------------------------------------------
# Structure de spawn du type de serveur + structure d'île / de mine
NEEDED=" $SPAWN_STRUCTURE "
case "$SERVER_NAME" in
    skyblock) NEEDED="$NEEDED Island " ;;
    opprison) NEEDED="$NEEDED mine " ;;
esac

STRUCT_SRC="$DATA_DIR/structures"
if [ -d "$STRUCT_SRC" ]; then
    COPIED=0
    for structure in "$STRUCT_SRC"/*.yml; do
        [ -f "$structure" ] || continue
        name="$(basename "$structure" .yml)"
        if [ ! -f "$TEST_DIR/DataFolder/structures/$(basename "$structure")" ]; then
            # Toutes les structures sont copiées : les types de serveur partagent
            # les mêmes fichiers, et cela évite de relancer le script plus tard.
            cp "$structure" "$TEST_DIR/DataFolder/structures/"
            COPIED=$((COPIED + 1))
        fi
    done
    info "Structures copiées depuis $STRUCT_SRC ($COPIED nouveau(x) fichier(s))"
else
    info "Structures introuvables dans $STRUCT_SRC : seules les structures générées seront disponibles"
fi

# Vérifie que la structure attendue par le type de serveur est là
for name in $NEEDED; do
    if [ ! -f "$TEST_DIR/DataFolder/structures/$name.yml" ]; then
        if [ "$name" = "Island" ]; then
            info "Island.yml absent : génération d'une plateforme de test 5x5"
            python3 - "$TEST_DIR/DataFolder/structures/Island.yml" <<'PY'
import sys
path = sys.argv[1]
blocks = [(x, 63, z) for x in range(-2, 3) for z in range(-2, 3)]
lines = []
for index, (x, y, z) in enumerate(blocks, 1):
    lines += [f"'{index}':", "  type: STONE", "  location:",
              f"    x: {x}", f"    y: {y}", f"    z: {z}", "  data: minecraft:stone"]
lines.append(f"blockCount: {len(blocks)}")
open(path, "w").write("\n".join(lines) + "\n")
PY
        else
            info "Structure $name.yml absente : le spawn restera vide pour ce test"
        fi
    fi
done

# --- eula + server.properties ----------------------------------------------
printf 'eula=true\n' >"$TEST_DIR/eula.txt"
if [ ! -f "$TEST_DIR/server.properties" ]; then
    info "Écriture de server.properties (hors-ligne, sans proxy)"
    {
        printf '%s\n' \
            'online-mode=false' \
            'level-name=Spawn' \
            "level-type=${LEVEL_TYPE}" \
            'server-port=25565' \
            'enable-rcon=true' \
            'rcon.port=25575' \
            'rcon.password=test' \
            'view-distance=8' \
            'simulation-distance=6' \
            'spawn-protection=0' \
            'max-players=20' \
            "motd=test local - $SERVER_NAME"
    } >"$TEST_DIR/server.properties"
fi

# --- lancement --------------------------------------------------------------
info "Serveur : $SERVER_NAME | localhost:${MC_PORT} | RCON : localhost:${RCON_PORT} (mot de passe : test)"

if [ "$RUN" = 0 ]; then
    info "Prêt dans $TEST_DIR (lancement ignoré : --no-run)"
    info "Pour lancer : ./scripts/test-local.sh --server $SERVER_NAME --no-build"
    exit 0
fi

info "En jeu : connexion directe à localhost:${MC_PORT} (hors-ligne)"
info "Arrêt  : Ctrl+C"

docker rm -f "$CONTAINER" >/dev/null 2>&1 || true

extra_args=()
if [ -n "$CPUS" ]; then extra_args+=(--cpus "$CPUS"); fi
if [ -t 1 ]; then extra_args+=(-t); fi

exec docker run --rm -i "${extra_args[@]}" \
    --name "$CONTAINER" \
    --user "$(id -u):$(id -g)" \
    -p "${MC_PORT}:25565" \
    -p "${RCON_PORT}:25575" \
    -v "${TEST_DIR}:/data" \
    -v "${TEST_DIR}/DataFolder:/DataFolder" \
    -e SERVER_NAME="$SERVER_NAME" \
    -e DOMAIN_NAME="$DOMAIN_NAME" \
    -e TLD="$TLD" \
    -e DISCORD_BOT_TOKEN= \
    "$IMAGE" \
    sh -c "cd /data && exec java -Xms${MEMORY} -Xmx${MEMORY} -jar paper.jar --nogui"
