package util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.File;
import java.nio.file.Path;
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
            return pictureDirectory.mkdirs();
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

    public static String getPictureSavingPath() {
        return FileUtils.convertBackslash2Slash(FileUtils.pictureDirectory.getPath());
    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        initUserHomePath();
        boolean status = initDirectory();
        System.out.println("Initialize work directory: " + status + ": " + pictureDirectory.getPath());
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
