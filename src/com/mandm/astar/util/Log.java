/*
 * Copyright 2016 Martin Fink & Moriz Martiner
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mandm.astar.util;

import java.util.Date;

/**
 * Created on 18.10.2016.
 *
 * @author Martin
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
