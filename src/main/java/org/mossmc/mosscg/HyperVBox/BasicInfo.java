package org.mossmc.mosscg.HyperVBox;

import org.mossmc.mosscg.MossLib.Object.ObjectConfig;
import org.mossmc.mosscg.MossLib.Object.ObjectLogger;

public class BasicInfo {
    public static String version = "V1.0.0.0.0000";
    public static String author = "MossCG";

    public static ObjectLogger logger;
    public static ObjectConfig config;

    public static Runtime runtime;

    public static boolean debug = false;
    public static void sendDebug(String message) {
        if (debug) logger.sendAPI(message);
    }
}
