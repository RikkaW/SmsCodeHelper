package cn.com.xy.sms.sdk.util;

import cn.com.xy.sms.sdk.constant.Constant;
import cn.com.xy.sms.sdk.db.entity.SysParamEntityManager;
import cn.com.xy.sms.sdk.net.i;

public class KeyManager
{
  public static String channel = null;
  
  public static String getAppKey()
  {
    return i.e;
  }
  
  public static void initAppKey()
  {
    if (channel == null) {
      channel = SysParamEntityManager.getStringParam(Constant.getContext(), "CHANNEL");
    }
    if (StringUtils.isNull(i.d))
    {
      if (!"PKWBZlRIbwLENOVO".equals(channel)) {
        break label99;
      }
      i.e = "LENOVO";
    }
    for (;;)
    {
      i.d = SysParamEntityManager.getStringParam(Constant.getContext(), "SECRETKEY");
      new StringBuilder("channel=").append(channel).append(" XyHttpRunnable.appKey=").append(i.e);
      if (!StringUtils.isNull(i.e)) {
        break;
      }
      throw new Exception("无效的渠道");
      label99:
      if ("hdtKldgsdkgo".equals(channel)) {
        i.e = "GOSMS";
      } else if ("J8KeTyOROASamsungReminder".equals(channel)) {
        i.e = "SReminder";
      } else if ("TGsTZewAYUN".equals(channel)) {
        i.e = "OSYUN";
      } else if ("KQIDAQABLEV".equals(channel)) {
        i.e = "LENOVO2";
      } else if ("BwIDAQABFROG".equals(channel)) {
        i.e = "LEFROG";
      } else if ("XwIDAQABYUN".equals(channel)) {
        i.e = "BJYUNOS";
      } else if ("NQIDAQABCOOL".equals(channel)) {
        i.e = "COOLPAD";
      } else if ("SAMOPERATORYQIDAQAB".equals(channel)) {
        i.e = "SAMOPERATOR";
      } else if ("SAMBANKVwIDAQAB".equals(channel)) {
        i.e = "SAMBANK";
      } else if ("SAMCLASSFIYVwIDAQAB".equals(channel)) {
        i.e = "SAMCLASSFIY";
      } else if ("6QIDAQABSTARRYSKY".equals(channel)) {
        i.e = "STARRYSKY";
      } else if ("vwIDAQABLIANLUOOS".equals(channel)) {
        i.e = "LIANLUOOS";
      } else if ("5xKI47wSAMALL".equals(channel)) {
        i.e = "SAMALL";
      } else if ("FEhNrwHTXL".equals(channel)) {
        i.e = "HTXL";
      } else if ("SAMALLxKI47w".equals(channel)) {
        i.e = "SAMALL";
      } else if ("VMhlWdEwVNEW_LENOVO".equals(channel)) {
        i.e = "VNEW_LENOVO";
      } else if ("jE5vSv5QPIAO".equals(channel)) {
        i.e = "XYPIAO";
      } else if ("GwIDAQABZTE".equals(channel)) {
        i.e = "ZTE";
      } else if ("1i1BDH2wONE+".equals(channel)) {
        i.e = "ONE+";
      } else if ("1w36SBLwVNEW_ZTE".equals(channel)) {
        i.e = "VNEW_ZTE";
      } else if ("Oq3iD6UlMAGIC".equals(channel)) {
        i.e = "MAGIC";
      } else if ("7kRgxjdwVNEW_STARRYSKY".equals(channel)) {
        i.e = "VNEW_STARRYSKY";
      } else if ("D6mKXM8MEIZU".equals(channel)) {
        i.e = "MEIZU";
      } else if ("rq7Fyxl5DUOQU".equals(channel)) {
        i.e = "DUOQU";
      } else if ("3GdfMSKwHUAWEI".equals(channel)) {
        i.e = "HUAWEI";
      } else if ("j3FIT5mwLETV".equals(channel)) {
        i.e = "LETV";
      } else if ("1i1BDH2wONE+CARD".equals(channel)) {
        i.e = "ONE+CARD";
      } else if ("0GCSqGSITOS".equals(channel)) {
        i.e = "TOS";
      } else if ("UM0srSjQ365".equals(channel)) {
        i.e = "365";
      } else if ("YHMesqOQCOOL".equals(channel)) {
        i.e = "COOL";
      } else if ("5Mj22a4wHUAWEICARD".equals(channel)) {
        i.e = "HUAWEICARD";
      } else if ("wupzCqnwGUAIWU".equals(channel)) {
        i.e = "GUAIWU";
      } else if ("XRyvMvZwSMARTISAN".equals(channel)) {
        i.e = "SMARTISAN";
      } else if ("MEIZUPAYGJw".equals(channel)) {
        i.e = "MEIZUPAY";
      } else if ("dToXA5JQDAKELE".equals(channel)) {
        i.e = "DAKELE";
      } else if ("p5O4wKmwGIONEE".equals(channel)) {
        i.e = "GIONEE";
      } else if ("z5N7W51wKINGSUN".equals(channel)) {
        i.e = "KINGSUN";
      } else if ("Cko59T6wSUGAR".equals(channel)) {
        i.e = "SUGAR";
      } else if ("oWIH+3ZQLEIDIANOS".equals(channel)) {
        i.e = "LEIDIANOS";
      } else if ("XYTEST".equals(channel)) {
        i.e = "XYTEST";
      } else if ("al30zFgQTEST_T".equals(channel)) {
        i.e = "TEST_T";
      } else if ("gsjHPHwIKOOBEE".equals(channel)) {
        i.e = "KOOBEE";
      } else if ("AjAFrJSQWENTAI".equals(channel)) {
        i.e = "WENTAI";
      } else if ("JqyMtaHQNUBIA".equals(channel)) {
        i.e = "NUBIA";
      } else if ("15Du354QGIONEECARD".equals(channel)) {
        i.e = "GIONEECARD";
      } else if ("rahtBH7wTCL".equals(channel)) {
        i.e = "TCL";
      } else if ("xU6UT6pwTOS2".equals(channel)) {
        i.e = "TOS2";
      } else if ("5Gx84kmwYULONG_COOLPAD".equals(channel)) {
        i.e = "YULONG_COOLPAD";
      } else if ("Uj2pznXQHCT".equals(channel)) {
        i.e = "HCT";
      } else if ("tnjdWFeQKTOUCH".equals(channel)) {
        i.e = "KTOUCH";
      } else if ("XkXZJmwIPPTV".equals(channel)) {
        i.e = "PPTV";
      } else if ("dGxSiEbwTOSCARD".equals(channel)) {
        i.e = "TOSCARD";
      } else if ("PzqP0ONQTOSWATCH".equals(channel)) {
        i.e = "TOSWATCH";
      } else if ("VCTyBOSwSmartisan".equals(channel)) {
        i.e = "Smartisan";
      } else if ("5rLWVKgQMEITU_PHONE".equals(channel)) {
        i.e = "MEITU_PHONE";
      }
    }
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.util.KeyManager
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */