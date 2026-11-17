# Badges
![Doc site](https://img.shields.io/website?down_color=red&down_message=offline&style=badge&up_color=green&up_message=online&url=https%3A%2F%2Fdoc.Altherneum.fr)
![MC site](https://img.shields.io/website?down_color=red&down_message=offline&style=badge&up_color=green&up_message=online&url=https%3A%2F%2Fmc.Altherneum.fr)  
![View](https://komarev.com/ghpvc/?username=Altherneum&color=red&label=%F0%9F%91%80)
![Discord](https://discordapp.com/api/guilds/1081921426333909072/widget.png)

![Commit week](https://img.shields.io/github/commit-activity/w/Altherneum/plugin?color=red)
![Commit month](https://img.shields.io/github/commit-activity/m/Altherneum/plugin?color=red)
![Commit year](https://img.shields.io/github/commit-activity/y/Altherneum/plugin?color=red)

![Creation date](https://img.shields.io/github/created-at/Altherneum/plugin?color=red)
![Commits](https://img.shields.io/github/commit-activity/t/Altherneum/plugin?color=red)

![Code size](https://img.shields.io/github/languages/code-size/Altherneum/plugin?color=red)
![Repo size](https://img.shields.io/github/repo-size/Altherneum/plugin?color=red)
![Files](https://img.shields.io/github/directory-file-count/Altherneum/plugin?color=red)

![Last commit](https://img.shields.io/github/last-commit/Altherneum/plugin?color=red)

![Release](https://img.shields.io/github/v/release/Altherneum/plugin?color=red)
![Release date](https://img.shields.io/github/release-date/Altherneum/plugin)
![Action workflow](https://img.shields.io/github/actions/workflow/status/Altherneum/plugin/maven.yml?color=red)
![Download](https://img.shields.io/github/downloads/Altherneum/plugin/total?color=red)

![Repo stars](https://img.shields.io/github/stars/Altherneum/plugin?color=red&style=badge)
![Contributors](https://img.shields.io/github/contributors/Altherneum/plugin?color=red)
![Forks](https://img.shields.io/github/forks/Altherneum/plugin?color=red&style=badge)
![Watchers](https://img.shields.io/github/watchers/Altherneum/plugin?color=red&style=badge)

![Languages counter](https://img.shields.io/github/languages/count/Altherneum/plugin?color=red)
![Top language](https://img.shields.io/github/languages/top/Altherneum/plugin?color=red)

![Issues](https://img.shields.io/github/issues/Altherneum/plugin)
![Pull request](https://img.shields.io/github/issues-pr/Altherneum/plugin)

## Organisation
![.github discussions](https://img.shields.io/github/discussions/Altherneum/.github?color=red)
![Org followers](https://img.shields.io/github/followers/Altherneum?color=red&style=badge)
![Org sponsors](https://img.shields.io/github/sponsors/Altherneum?color=red)

# plugin
- Server side plugin with Spigot / PaperMC & Javacord

# Tester en local
- Script : [`scripts/test-local.sh`](scripts/test-local.sh)
- Compile le plugin et lance un serveur Paper hors-ligne dans `~/mc-test`, sans toucher à la production
- N'importe quel type de serveur : hub, skyblock, survie, creatif, anarchie, rpg, opprison, test

## Prérequis
| Outil | Utilité |
|---|---|
| Docker | exécution du serveur Paper |
| Maven + JDK 21 | compilation du plugin |
| Python 3 | récupération de l'URL Paper et adaptation de `paper-global.yml` |
| curl, unzip | téléchargement de Paper, extraction des cartes |

## Lancement
```sh
./scripts/test-local.sh # skyblock par défaut
./scripts/test-local.sh --server hub # hub
./scripts/test-local.sh --server survie --port 25566
./scripts/test-local.sh --no-run # prépare seulement
./scripts/test-local.sh --fresh # repart de zéro (mondes supprimés)
./scripts/test-local.sh --no-build # relance sans recompiler
./scripts/test-local.sh --help
```

Le premier lancement télécharge Paper (~55 Mo) et le serveur démarre en une
minute environ. La version et le build de Paper sont lus dans le `.env` du
dépôt `docker-server` (surchargables par `MC_VERSION` et `PAPER_BUILD`).

Le type de serveur passe par la variable `SERVER_NAME` lue par le plugin
(`ServerBootFile`) : le port ne sert qu'à se connecter, il n'influence pas le
type. Un dossier de test = un type : pour changer de type, relancer avec
`--fresh` ou choisir un autre dossier (`--dir ~/mc-test-hub`).

## Cartes et structures
| Type | Carte `Spawn` | Structure de spawn | Autres structures |
|---|---|---|---|
| `hub`, `creatif`, `rpg` | Hub (seulement `hub`) / monde généré sinon | `spawn` | — |
| `skyblock` | SkyBlock | `spawn_SB` | `Island` (généré si absent) |
| `survie` | Survie | `spawn_Survie` | — |
| `anarchie`, `test` | Survie | `spawn_Survie` | — |
| `opprison` | OPPrison | `spawn_OPPrison` | `mine` |

Les cartes viennent de `docker-server` (`stacks/minecraft/paper/maps`), les
structures de `~/repo/server/Serveurs/DataFolder/structures` (toutes copiées :
elles ne sont chargées que si le type de serveur en a besoin).

## En jeu
- Connexion directe à `localhost:25565` (serveur hors-ligne : n'importe quel pseudo)
- `skyblock` : `/island` crée une île, sans droit administrateur
- Commandes admin : `op <pseudo>` dans la console, puis `tag <pseudo> ADD Admin`
- RCON : port + 10 (`25575` par défaut), mot de passe `test`
- Le domaine affiché (tab, boss bar, MOTD) vient de `DOMAIN_NAME` + `TLD` : par défaut `localhost` sans TLD, donc `SkyBlock.localhost` ; pour simuler un domaine :
  ```sh
  DOMAIN_NAME=example TLD=.test ./scripts/test-local.sh
  ```

## Options
| Option | Effet |
|---|---|
| `--server TYPE` | type de serveur (défaut : `skyblock`) |
| `--fresh` | supprime le dossier de test avant de recommencer |
| `--no-build` | ne recompile pas (utilise `target/plugin-1.0-fat.jar`) |
| `--no-run` | prépare seulement, sans lancer Docker |
| `--port N` | port du serveur (défaut : `25565`), RCON = port + 10 |
| `--rcon-port N` | port RCON explicite |
| `--memory TAILLE` | tas Java (défaut : `1G`) |
| `--cpus N` | limite de CPU du conteneur |
| `--dir CHEMIN` | dossier de test (défaut : `~/mc-test`) |
| `--data CHEMIN` | anciennes données du plugin (défaut : `~/repo/server/Serveurs/DataFolder`) |
| `--with-data` | copie aussi les anciennes données (joueurs, îles, argent) |

Variables d'environnement équivalentes : `TEST_DIR`, `DOCKER_SERVER_DIR`,
`DATA_DIR`, `MC_VERSION`, `PAPER_BUILD`, `MEMORY`, `SERVER_NAME`, `DOMAIN_NAME`,
`TLD`, `MC_PORT`, `RCON_PORT`, `CONTAINER`, `IMAGE`.

Aucun push, aucune image publiée : le jar compilé est simplement copié dans le
conteneur local, et les fichiers créés appartiennent à l'utilisateur courant
(`--user` Docker).

# Github
- [Repository](https://github.com/Altherneum/plugin)
- [Tableau](https://github.com/orgs/Altherneum/projects/3/)
- [Issues](https://github.com/Altherneum/plugin/issues)
- [Commits](https://github.com/Altherneum/plugin/commits/main)
- [Latest release](https://github.com/Altherneum/plugin/releases/latest)
- [Releases](https://github.com/Altherneum/plugin/releases)

- Git origin : [git.altherneum.fr/altherneum/](git.altherneum.fr/altherneum/)