package com.mandm.astar.util;

import java.util.Date;

/**
 * Created on 18.10.2016.
 *
 * @author Alex Lardschneider
 */
public class Log {

    public static void i(String text) {
        Date now = new Date();
        System.out.println("[INFO] " + now + ": " + text);
    }

    public static void e(String text) {
        Date now = new Date();
        System.err.println("[ERROR] " + now + ": " + text);
    }

    public static void d(String text) {
        Date now = new Date();
        System.out.println("[DEBUG] " + now + ": " + text);
    }

    public static void w(String text) {
        Date now = new Date();
        System.out.println("[WARNING] " + now + ": " + text);
    }


}
