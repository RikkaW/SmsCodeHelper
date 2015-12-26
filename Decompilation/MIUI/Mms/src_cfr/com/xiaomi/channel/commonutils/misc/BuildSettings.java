/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 */
package com.xiaomi.channel.commonutils.misc;

public class BuildSettings {
    public static final boolean IsBetaBuild;
    public static final boolean IsDebugBuild;
    public static final boolean IsDefaultChannel;
    public static final boolean IsForYYBuild;
    public static final boolean IsLogableBuild;
    public static final boolean IsRCBuild;
    public static boolean IsTestBuild;
    private static int envType;

    /*
     * Enabled aggressive block sorting
     */
    static {
        boolean bl = false;
        IsDefaultChannel = "@SHIP.TO.2A2FE0D7@".contains((CharSequence)"2A2FE0D7");
        boolean bl2 = IsDefaultChannel || "DEBUG".equalsIgnoreCase("@SHIP.TO.2A2FE0D7@");
        IsDebugBuild = bl2;
        IsLogableBuild = "LOGABLE".equalsIgnoreCase("@SHIP.TO.2A2FE0D7@");
        IsForYYBuild = "@SHIP.TO.2A2FE0D7@".contains((CharSequence)"YY");
        IsTestBuild = "@SHIP.TO.2A2FE0D7@".equalsIgnoreCase("TEST");
        IsBetaBuild = "BETA".equalsIgnoreCase("@SHIP.TO.2A2FE0D7@");
        bl2 = bl;
        if ("@SHIP.TO.2A2FE0D7@" != null) {
            bl2 = bl;
            if ("@SHIP.TO.2A2FE0D7@".startsWith("RC")) {
                bl2 = true;
            }
        }
        IsRCBuild = bl2;
        envType = 1;
        if ("@SHIP.TO.2A2FE0D7@".equalsIgnoreCase("SANDBOX")) {
            envType = 2;
            return;
        }
        if ("@SHIP.TO.2A2FE0D7@".equalsIgnoreCase("ONEBOX")) {
            envType = 3;
            return;
        }
        envType = 1;
    }

    public static boolean IsOneBoxBuild() {
        if (envType == 3) {
            return true;
        }
        return false;
    }

    public static boolean IsSandBoxBuild() {
        if (envType == 2) {
            return true;
        }
        return false;
    }

    public static int getEnvType() {
        return envType;
    }

    public static void setEnvType(int n) {
        envType = n;
    }
}

