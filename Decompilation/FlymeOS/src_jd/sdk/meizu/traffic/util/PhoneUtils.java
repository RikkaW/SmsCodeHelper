package sdk.meizu.traffic.util;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

public class PhoneUtils
{
  private static final String CLASS_NAME_BUILD_EXT = "android.os.BuildExt";
  private static final String CLASS_NAME_CONTEXT_EXT = "android.content.ContextExt";
  public static final String TAG = "PhoneUtils";
  private static Boolean sIsFlymeRom;
  private static Boolean sIsGuestMode;
  private static Boolean sIsPhoneRooted;
  private static Boolean sIsProductInternational;
  private static String sPhoneIMEI;
  private static String sPhoneModel;
  private static String sPhoneSn;
  
  private PhoneUtils()
  {
    throw new AssertionError();
  }
  
  public static String checkAndNormalizePhoneNum(Context paramContext, String paramString)
  {
    String str = paramString;
    if (paramString != null)
    {
      str = paramString;
      if (paramString.contains("+86")) {
        str = paramString.replace("+86", "");
      }
    }
    if ((TextUtils.isEmpty(str)) || (TextUtils.isDigitsOnly(str))) {
      return str;
    }
    Toast.makeText(paramContext, "手机号码不符合规范", 0).show();
    return "";
  }
  
  /* Error */
  public static String getPhoneImei(Context paramContext)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: getstatic 71	sdk/meizu/traffic/util/PhoneUtils:sPhoneIMEI	Ljava/lang/String;
    //   6: invokestatic 51	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   9: istore_1
    //   10: iload_1
    //   11: ifeq +42 -> 53
    //   14: ldc 73
    //   16: ldc 75
    //   18: aconst_null
    //   19: aconst_null
    //   20: invokestatic 81	sdk/meizu/traffic/util/ReflectHelper:invokeStatic	(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Class;[Ljava/lang/Object;)Ljava/lang/Object;
    //   23: checkcast 36	java/lang/String
    //   26: putstatic 71	sdk/meizu/traffic/util/PhoneUtils:sPhoneIMEI	Ljava/lang/String;
    //   29: getstatic 71	sdk/meizu/traffic/util/PhoneUtils:sPhoneIMEI	Ljava/lang/String;
    //   32: invokestatic 51	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   35: ifeq +18 -> 53
    //   38: aload_0
    //   39: ldc 83
    //   41: invokevirtual 89	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   44: checkcast 91	android/telephony/TelephonyManager
    //   47: invokevirtual 94	android/telephony/TelephonyManager:getDeviceId	()Ljava/lang/String;
    //   50: putstatic 71	sdk/meizu/traffic/util/PhoneUtils:sPhoneIMEI	Ljava/lang/String;
    //   53: ldc 14
    //   55: new 96	java/lang/StringBuilder
    //   58: dup
    //   59: invokespecial 97	java/lang/StringBuilder:<init>	()V
    //   62: ldc 99
    //   64: invokevirtual 103	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   67: getstatic 71	sdk/meizu/traffic/util/PhoneUtils:sPhoneIMEI	Ljava/lang/String;
    //   70: invokevirtual 103	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   73: invokevirtual 106	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   76: invokestatic 112	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   79: pop
    //   80: getstatic 71	sdk/meizu/traffic/util/PhoneUtils:sPhoneIMEI	Ljava/lang/String;
    //   83: ifnonnull +8 -> 91
    //   86: ldc 42
    //   88: putstatic 71	sdk/meizu/traffic/util/PhoneUtils:sPhoneIMEI	Ljava/lang/String;
    //   91: getstatic 71	sdk/meizu/traffic/util/PhoneUtils:sPhoneIMEI	Ljava/lang/String;
    //   94: astore_0
    //   95: ldc 2
    //   97: monitorexit
    //   98: aload_0
    //   99: areturn
    //   100: astore_2
    //   101: aload_2
    //   102: invokevirtual 115	java/lang/Exception:printStackTrace	()V
    //   105: goto -76 -> 29
    //   108: astore_0
    //   109: ldc 2
    //   111: monitorexit
    //   112: aload_0
    //   113: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	114	0	paramContext	Context
    //   9	2	1	bool	boolean
    //   100	2	2	localException	Exception
    // Exception table:
    //   from	to	target	type
    //   14	29	100	java/lang/Exception
    //   3	10	108	finally
    //   14	29	108	finally
    //   29	53	108	finally
    //   53	91	108	finally
    //   91	95	108	finally
    //   101	105	108	finally
  }
  
  /* Error */
  public static String getPhoneModel()
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: getstatic 118	sdk/meizu/traffic/util/PhoneUtils:sPhoneModel	Ljava/lang/String;
    //   6: invokestatic 51	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   9: ifeq +52 -> 61
    //   12: invokestatic 122	sdk/meizu/traffic/util/PhoneUtils:isFlymeRom	()Z
    //   15: ifeq +55 -> 70
    //   18: getstatic 127	android/os/Build:MODEL	Ljava/lang/String;
    //   21: putstatic 118	sdk/meizu/traffic/util/PhoneUtils:sPhoneModel	Ljava/lang/String;
    //   24: getstatic 118	sdk/meizu/traffic/util/PhoneUtils:sPhoneModel	Ljava/lang/String;
    //   27: invokestatic 51	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   30: ifne +17 -> 47
    //   33: getstatic 118	sdk/meizu/traffic/util/PhoneUtils:sPhoneModel	Ljava/lang/String;
    //   36: invokevirtual 130	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   39: ldc -124
    //   41: invokevirtual 136	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   44: ifeq +17 -> 61
    //   47: ldc 14
    //   49: ldc -118
    //   51: invokestatic 112	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   54: pop
    //   55: getstatic 127	android/os/Build:MODEL	Ljava/lang/String;
    //   58: putstatic 118	sdk/meizu/traffic/util/PhoneUtils:sPhoneModel	Ljava/lang/String;
    //   61: getstatic 118	sdk/meizu/traffic/util/PhoneUtils:sPhoneModel	Ljava/lang/String;
    //   64: astore_0
    //   65: ldc 2
    //   67: monitorexit
    //   68: aload_0
    //   69: areturn
    //   70: ldc 8
    //   72: ldc -116
    //   74: invokestatic 144	sdk/meizu/traffic/util/ReflectHelper:getStaticField	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/Object;
    //   77: checkcast 36	java/lang/String
    //   80: putstatic 118	sdk/meizu/traffic/util/PhoneUtils:sPhoneModel	Ljava/lang/String;
    //   83: goto -59 -> 24
    //   86: astore_0
    //   87: aload_0
    //   88: invokevirtual 115	java/lang/Exception:printStackTrace	()V
    //   91: goto -67 -> 24
    //   94: astore_0
    //   95: ldc 2
    //   97: monitorexit
    //   98: aload_0
    //   99: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   64	5	0	str	String
    //   86	2	0	localException	Exception
    //   94	5	0	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   70	83	86	java/lang/Exception
    //   3	24	94	finally
    //   24	47	94	finally
    //   47	61	94	finally
    //   61	65	94	finally
    //   70	83	94	finally
    //   87	91	94	finally
  }
  
  /* Error */
  public static boolean isFlymeRom()
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: getstatic 146	sdk/meizu/traffic/util/PhoneUtils:sIsFlymeRom	Ljava/lang/Boolean;
    //   6: ifnull +15 -> 21
    //   9: getstatic 146	sdk/meizu/traffic/util/PhoneUtils:sIsFlymeRom	Ljava/lang/Boolean;
    //   12: invokevirtual 151	java/lang/Boolean:booleanValue	()Z
    //   15: istore_0
    //   16: ldc 2
    //   18: monitorexit
    //   19: iload_0
    //   20: ireturn
    //   21: ldc 8
    //   23: ldc -104
    //   25: aconst_null
    //   26: invokestatic 155	sdk/meizu/traffic/util/ReflectHelper:invokeStatic	(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;
    //   29: checkcast 148	java/lang/Boolean
    //   32: invokevirtual 151	java/lang/Boolean:booleanValue	()Z
    //   35: ifeq +21 -> 56
    //   38: getstatic 158	java/lang/Boolean:TRUE	Ljava/lang/Boolean;
    //   41: astore_1
    //   42: aload_1
    //   43: putstatic 146	sdk/meizu/traffic/util/PhoneUtils:sIsFlymeRom	Ljava/lang/Boolean;
    //   46: getstatic 146	sdk/meizu/traffic/util/PhoneUtils:sIsFlymeRom	Ljava/lang/Boolean;
    //   49: invokevirtual 151	java/lang/Boolean:booleanValue	()Z
    //   52: istore_0
    //   53: goto -37 -> 16
    //   56: getstatic 161	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   59: astore_1
    //   60: goto -18 -> 42
    //   63: astore_1
    //   64: aload_1
    //   65: invokevirtual 115	java/lang/Exception:printStackTrace	()V
    //   68: iconst_0
    //   69: istore_0
    //   70: goto -54 -> 16
    //   73: astore_1
    //   74: ldc 2
    //   76: monitorexit
    //   77: aload_1
    //   78: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   15	55	0	bool	boolean
    //   41	19	1	localBoolean	Boolean
    //   63	2	1	localException	Exception
    //   73	5	1	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   21	42	63	java/lang/Exception
    //   42	53	63	java/lang/Exception
    //   56	60	63	java/lang/Exception
    //   3	16	73	finally
    //   21	42	73	finally
    //   42	53	73	finally
    //   56	60	73	finally
    //   64	68	73	finally
  }
  
  /* Error */
  public static boolean isGuestModeEnabled(Context paramContext)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: getstatic 165	sdk/meizu/traffic/util/PhoneUtils:sIsGuestMode	Ljava/lang/Boolean;
    //   6: ifnull +15 -> 21
    //   9: getstatic 165	sdk/meizu/traffic/util/PhoneUtils:sIsGuestMode	Ljava/lang/Boolean;
    //   12: invokevirtual 151	java/lang/Boolean:booleanValue	()Z
    //   15: istore_1
    //   16: ldc 2
    //   18: monitorexit
    //   19: iload_1
    //   20: ireturn
    //   21: aload_0
    //   22: ldc -89
    //   24: invokevirtual 89	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   27: checkcast 169	android/os/UserManager
    //   30: astore_0
    //   31: aload_0
    //   32: ldc -85
    //   34: aconst_null
    //   35: invokestatic 175	sdk/meizu/traffic/util/ReflectHelper:invoke	(Ljava/lang/Object;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;
    //   38: checkcast 148	java/lang/Boolean
    //   41: invokevirtual 151	java/lang/Boolean:booleanValue	()Z
    //   44: ifeq +21 -> 65
    //   47: getstatic 158	java/lang/Boolean:TRUE	Ljava/lang/Boolean;
    //   50: astore_0
    //   51: aload_0
    //   52: putstatic 165	sdk/meizu/traffic/util/PhoneUtils:sIsGuestMode	Ljava/lang/Boolean;
    //   55: getstatic 165	sdk/meizu/traffic/util/PhoneUtils:sIsGuestMode	Ljava/lang/Boolean;
    //   58: invokevirtual 151	java/lang/Boolean:booleanValue	()Z
    //   61: istore_1
    //   62: goto -46 -> 16
    //   65: getstatic 161	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   68: astore_0
    //   69: goto -18 -> 51
    //   72: astore_0
    //   73: aload_0
    //   74: invokevirtual 115	java/lang/Exception:printStackTrace	()V
    //   77: iconst_0
    //   78: istore_1
    //   79: goto -63 -> 16
    //   82: astore_0
    //   83: ldc 2
    //   85: monitorexit
    //   86: aload_0
    //   87: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	88	0	paramContext	Context
    //   15	64	1	bool	boolean
    // Exception table:
    //   from	to	target	type
    //   31	51	72	java/lang/Exception
    //   51	62	72	java/lang/Exception
    //   65	69	72	java/lang/Exception
    //   3	16	82	finally
    //   21	31	82	finally
    //   31	51	82	finally
    //   51	62	82	finally
    //   65	69	82	finally
    //   73	77	82	finally
  }
  
  public static boolean isPhoneRooted(Context paramContext)
  {
    for (;;)
    {
      try
      {
        if (sIsPhoneRooted != null)
        {
          bool = sIsPhoneRooted.booleanValue();
          return bool;
        }
        try
        {
          String str1 = (String)ReflectHelper.getStaticField("android.content.ContextExt", "DEVICE_STATE_SERVICE");
          if (!TextUtils.isEmpty(str1))
          {
            paramContext = paramContext.getSystemService(str1);
            if (paramContext != null) {}
            try
            {
              if (((Integer)ReflectHelper.invoke(paramContext, "doCheckState", new Class[] { Integer.TYPE }, new Object[] { Integer.valueOf(1) })).intValue() != 1) {
                continue;
              }
              paramContext = Boolean.TRUE;
              sIsPhoneRooted = paramContext;
            }
            catch (Exception paramContext)
            {
              String str2;
              paramContext.printStackTrace();
              continue;
            }
            bool = sIsPhoneRooted.booleanValue();
          }
        }
        catch (NoSuchFieldException localNoSuchFieldException)
        {
          localNoSuchFieldException.printStackTrace();
          str2 = "";
          continue;
          paramContext = Boolean.FALSE;
          continue;
        }
        boolean bool = false;
      }
      finally {}
    }
  }
  
  /* Error */
  public static boolean isProductInternational()
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: getstatic 207	sdk/meizu/traffic/util/PhoneUtils:sIsProductInternational	Ljava/lang/Boolean;
    //   6: ifnull +15 -> 21
    //   9: getstatic 207	sdk/meizu/traffic/util/PhoneUtils:sIsProductInternational	Ljava/lang/Boolean;
    //   12: invokevirtual 151	java/lang/Boolean:booleanValue	()Z
    //   15: istore_0
    //   16: ldc 2
    //   18: monitorexit
    //   19: iload_0
    //   20: ireturn
    //   21: ldc 8
    //   23: ldc -48
    //   25: aconst_null
    //   26: invokestatic 155	sdk/meizu/traffic/util/ReflectHelper:invokeStatic	(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/Object;
    //   29: checkcast 148	java/lang/Boolean
    //   32: invokevirtual 151	java/lang/Boolean:booleanValue	()Z
    //   35: ifeq +21 -> 56
    //   38: getstatic 158	java/lang/Boolean:TRUE	Ljava/lang/Boolean;
    //   41: astore_1
    //   42: aload_1
    //   43: putstatic 207	sdk/meizu/traffic/util/PhoneUtils:sIsProductInternational	Ljava/lang/Boolean;
    //   46: getstatic 207	sdk/meizu/traffic/util/PhoneUtils:sIsProductInternational	Ljava/lang/Boolean;
    //   49: invokevirtual 151	java/lang/Boolean:booleanValue	()Z
    //   52: istore_0
    //   53: goto -37 -> 16
    //   56: getstatic 161	java/lang/Boolean:FALSE	Ljava/lang/Boolean;
    //   59: astore_1
    //   60: goto -18 -> 42
    //   63: astore_1
    //   64: aload_1
    //   65: invokevirtual 115	java/lang/Exception:printStackTrace	()V
    //   68: iconst_0
    //   69: istore_0
    //   70: goto -54 -> 16
    //   73: astore_1
    //   74: ldc 2
    //   76: monitorexit
    //   77: aload_1
    //   78: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   15	55	0	bool	boolean
    //   41	19	1	localBoolean	Boolean
    //   63	2	1	localException	Exception
    //   73	5	1	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   21	42	63	java/lang/Exception
    //   42	53	63	java/lang/Exception
    //   56	60	63	java/lang/Exception
    //   3	16	73	finally
    //   21	42	73	finally
    //   42	53	73	finally
    //   56	60	73	finally
    //   64	68	73	finally
  }
}

/* Location:
 * Qualified Name:     sdk.meizu.traffic.util.PhoneUtils
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */