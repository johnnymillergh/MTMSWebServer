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

    /**
     * From: https://www.cnblogs.com/gaopeng527/p/5787535.html
     * 递归删除目录下的所有文件及子目录下所有文件
     *
     * @param dir 将要删除的文件目录
     * @return boolean Returns "true" if all deletions were successful.
     * If a deletion fails, the method stops attempting to
     * delete and returns "false".
     */
    public static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            //递归删除目录中的子目录下
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        // 目录此时为空，可以删除
        return dir.delete();
    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        initUserHomePath();
        boolean status = initDirectory();
        System.out.println("Initial work directory: " + getClass() + ", " + status + ": " + pictureDirectory.getPath());
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        boolean status = false;
        for (int i = 0; i < 3; i++) {
            status = deleteDir(pictureDirectory);
            pictureDirectory = pictureDirectory.getParentFile();
        }
        System.out.println("Destroy work directory: " + getClass() + ", " + status + ": " + pictureDirectory.getPath());
    }
}
