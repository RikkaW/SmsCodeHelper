/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 */
package cn.com.xy.sms.sdk.util;

import cn.com.xy.sms.sdk.constant.Constant;
import cn.com.xy.sms.sdk.db.entity.SysParamEntityManager;
import cn.com.xy.sms.sdk.net.i;
import cn.com.xy.sms.sdk.util.StringUtils;

public class KeyManager {
    public static String channel = null;

    public static String getAppKey() {
        return i.e;
    }

    /*
     * Enabled aggressive block sorting
     */
    public static void initAppKey() {
        if (channel == null) {
            channel = SysParamEntityManager.getStringParam(Constant.getContext(), "CHANNEL");
        }
        if (StringUtils.isNull(i.d)) {
            if ("PKWBZlRIbwLENOVO".equals((Object)channel)) {
                i.e = "LENOVO";
            } else if ("hdtKldgsdkgo".equals((Object)channel)) {
                i.e = "GOSMS";
            } else if ("J8KeTyOROASamsungReminder".equals((Object)channel)) {
                i.e = "SReminder";
            } else if ("TGsTZewAYUN".equals((Object)channel)) {
                i.e = "OSYUN";
            } else if ("KQIDAQABLEV".equals((Object)channel)) {
                i.e = "LENOVO2";
            } else if ("BwIDAQABFROG".equals((Object)channel)) {
                i.e = "LEFROG";
            } else if ("XwIDAQABYUN".equals((Object)channel)) {
                i.e = "BJYUNOS";
            } else if ("NQIDAQABCOOL".equals((Object)channel)) {
                i.e = "COOLPAD";
            } else if ("SAMOPERATORYQIDAQAB".equals((Object)channel)) {
                i.e = "SAMOPERATOR";
            } else if ("SAMBANKVwIDAQAB".equals((Object)channel)) {
                i.e = "SAMBANK";
            } else if ("SAMCLASSFIYVwIDAQAB".equals((Object)channel)) {
                i.e = "SAMCLASSFIY";
            } else if ("6QIDAQABSTARRYSKY".equals((Object)channel)) {
                i.e = "STARRYSKY";
            } else if ("vwIDAQABLIANLUOOS".equals((Object)channel)) {
                i.e = "LIANLUOOS";
            } else if ("5xKI47wSAMALL".equals((Object)channel)) {
                i.e = "SAMALL";
            } else if ("FEhNrwHTXL".equals((Object)channel)) {
                i.e = "HTXL";
            } else if ("SAMALLxKI47w".equals((Object)channel)) {
                i.e = "SAMALL";
            } else if ("VMhlWdEwVNEW_LENOVO".equals((Object)channel)) {
                i.e = "VNEW_LENOVO";
            } else if ("jE5vSv5QPIAO".equals((Object)channel)) {
                i.e = "XYPIAO";
            } else if ("GwIDAQABZTE".equals((Object)channel)) {
                i.e = "ZTE";
            } else if ("1i1BDH2wONE+".equals((Object)channel)) {
                i.e = "ONE+";
            } else if ("1w36SBLwVNEW_ZTE".equals((Object)channel)) {
                i.e = "VNEW_ZTE";
            } else if ("Oq3iD6UlMAGIC".equals((Object)channel)) {
                i.e = "MAGIC";
            } else if ("7kRgxjdwVNEW_STARRYSKY".equals((Object)channel)) {
                i.e = "VNEW_STARRYSKY";
            } else if ("D6mKXM8MEIZU".equals((Object)channel)) {
                i.e = "MEIZU";
            } else if ("rq7Fyxl5DUOQU".equals((Object)channel)) {
                i.e = "DUOQU";
            } else if ("3GdfMSKwHUAWEI".equals((Object)channel)) {
                i.e = "HUAWEI";
            } else if ("j3FIT5mwLETV".equals((Object)channel)) {
                i.e = "LETV";
            } else if ("1i1BDH2wONE+CARD".equals((Object)channel)) {
                i.e = "ONE+CARD";
            } else if ("0GCSqGSITOS".equals((Object)channel)) {
                i.e = "TOS";
            } else if ("UM0srSjQ365".equals((Object)channel)) {
                i.e = "365";
            } else if ("YHMesqOQCOOL".equals((Object)channel)) {
                i.e = "COOL";
            } else if ("5Mj22a4wHUAWEICARD".equals((Object)channel)) {
                i.e = "HUAWEICARD";
            } else if ("wupzCqnwGUAIWU".equals((Object)channel)) {
                i.e = "GUAIWU";
            } else if ("XRyvMvZwSMARTISAN".equals((Object)channel)) {
                i.e = "SMARTISAN";
            } else if ("MEIZUPAYGJw".equals((Object)channel)) {
                i.e = "MEIZUPAY";
            } else if ("dToXA5JQDAKELE".equals((Object)channel)) {
                i.e = "DAKELE";
            } else if ("p5O4wKmwGIONEE".equals((Object)channel)) {
                i.e = "GIONEE";
            } else if ("z5N7W51wKINGSUN".equals((Object)channel)) {
                i.e = "KINGSUN";
            } else if ("Cko59T6wSUGAR".equals((Object)channel)) {
                i.e = "SUGAR";
            } else if ("oWIH+3ZQLEIDIANOS".equals((Object)channel)) {
                i.e = "LEIDIANOS";
            } else if ("XYTEST".equals((Object)channel)) {
                i.e = "XYTEST";
            } else if ("al30zFgQTEST_T".equals((Object)channel)) {
                i.e = "TEST_T";
            } else if ("gsjHPHwIKOOBEE".equals((Object)channel)) {
                i.e = "KOOBEE";
            } else if ("AjAFrJSQWENTAI".equals((Object)channel)) {
                i.e = "WENTAI";
            } else if ("JqyMtaHQNUBIA".equals((Object)channel)) {
                i.e = "NUBIA";
            } else if ("15Du354QGIONEECARD".equals((Object)channel)) {
                i.e = "GIONEECARD";
            } else if ("rahtBH7wTCL".equals((Object)channel)) {
                i.e = "TCL";
            } else if ("xU6UT6pwTOS2".equals((Object)channel)) {
                i.e = "TOS2";
            } else if ("5Gx84kmwYULONG_COOLPAD".equals((Object)channel)) {
                i.e = "YULONG_COOLPAD";
            } else if ("Uj2pznXQHCT".equals((Object)channel)) {
                i.e = "HCT";
            } else if ("tnjdWFeQKTOUCH".equals((Object)channel)) {
                i.e = "KTOUCH";
            } else if ("XkXZJmwIPPTV".equals((Object)channel)) {
                i.e = "PPTV";
            } else if ("dGxSiEbwTOSCARD".equals((Object)channel)) {
                i.e = "TOSCARD";
            } else if ("PzqP0ONQTOSWATCH".equals((Object)channel)) {
                i.e = "TOSWATCH";
            } else if ("VCTyBOSwSmartisan".equals((Object)channel)) {
                i.e = "Smartisan";
            } else if ("5rLWVKgQMEITU_PHONE".equals((Object)channel)) {
                i.e = "MEITU_PHONE";
            }
            i.d = SysParamEntityManager.getStringParam(Constant.getContext(), "SECRETKEY");
        }
        new StringBuilder("channel=").append(channel).append(" XyHttpRunnable.appKey=").append(i.e);
        if (StringUtils.isNull(i.e)) {
            throw new Exception("\u65e0\u6548\u7684\u6e20\u9053");
        }
    }
}

