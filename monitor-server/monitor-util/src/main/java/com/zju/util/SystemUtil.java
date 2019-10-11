package com.zju.util;

/**
 * @author GongTengPangYi
 */
public class SystemUtil {

    public static boolean isWindows() {
        return System.getProperties().getProperty("os.name").toUpperCase().indexOf("WINDOWS") != -1;
    }
}
