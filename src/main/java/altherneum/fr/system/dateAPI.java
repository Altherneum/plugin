package altherneum.fr.system;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class dateAPI {
    public static DateFormat dateFormat() {
        return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    }

    public static String DateFormated(Date date) {
        return dateFormat().format(date);
    }

    public static Date now() {
        return new Date();
    }

    public static Date ReturnDate(File file, String path) throws ParseException {
        if (file.exists()) {
            FileConfiguration fileConfiguration = YamlConfiguration.loadConfiguration(file);
            if (fileConfiguration.getString(path) != null) {
                return dateFormat().parse((fileConfiguration.getString(path)));
            }
        }
        return null;
    }

    public static Date ReturnDateWithXDay(Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, days);
        return calendar.getTime();
    }

    public static Date ReturnDateLessXSeconds(Date date, int sec) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.SECOND, sec);
        return calendar.getTime();
    }
}