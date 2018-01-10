package util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.File;
import java.util.regex.Pattern;

public class FileUtils implements ServletContextListener {

    private static String userHomePath = System.getProperty("user.home");
    public static File pictureDirectory = new File(userHomePath + "/MTMS/upload/pic");

    public static String convertBackslash2Slash(String pathWithBackslash) {
        return pathWithBackslash.replaceAll("\\\\", "/");
    }

    private static void initUserHomePath() {
        userHomePath = convertBackslash2Slash(userHomePath);
    }

    private static boolean initDirectory() {
        if (!pictureDirectory.exists()) {
            return pictureDirectory.mkdir();
        }
        return false;
    }

    public static String getRealName(String path) {
        int index = path.lastIndexOf("\\");

        if (index == -1) {
            index = path.lastIndexOf("/");
        }

        return path.substring(index + 1);
    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        initUserHomePath();
        initDirectory();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
