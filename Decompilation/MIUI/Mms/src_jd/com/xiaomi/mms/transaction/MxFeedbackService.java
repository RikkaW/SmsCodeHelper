package com.xiaomi.mms.transaction;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.miui.Shell;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemProperties;
import android.preference.PreferenceManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.widget.Toast;
import com.xiaomi.mms.utils.logger.MyLog;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import org.json.JSONException;
import org.json.JSONObject;

public class MxFeedbackService
  extends IntentService
{
  private static final String BUG_REPORT_PATH = "/data/bugreport" + File.separator + "outbox";
  private static final String MX_BUGREPORT_PATH = "/data/data/com.android.mms" + File.separator + "bugreport";
  private static final String SUMMARY_FILE_PATH = MX_BUGREPORT_PATH + File.separator + "summary.txt";
  private Process mProcess;
  private Handler mUIHandler = new Handler();
  
  public MxFeedbackService()
  {
    super("MxFeedbackService");
  }
  
  private static void addAccount(Context paramContext, AccountManagerCallback<Bundle> paramAccountManagerCallback)
  {
    AccountManager.get(paramContext).addAccount("com.xiaomi", "micloud", null, null, (Activity)paramContext, paramAccountManagerCallback, null);
  }
  
  /* Error */
  private String dumpFeedback()
  {
    // Byte code:
    //   0: ldc 87
    //   2: iconst_0
    //   3: anewarray 89	java/lang/Object
    //   6: invokestatic 95	android/miui/Shell:runShell	(Ljava/lang/String;[Ljava/lang/Object;)Z
    //   9: pop
    //   10: new 20	java/lang/StringBuilder
    //   13: dup
    //   14: invokespecial 23	java/lang/StringBuilder:<init>	()V
    //   17: astore 7
    //   19: aconst_null
    //   20: astore 5
    //   22: aconst_null
    //   23: astore 6
    //   25: aload 5
    //   27: astore 4
    //   29: aload_0
    //   30: ldc 97
    //   32: invokespecial 101	com/xiaomi/mms/transaction/MxFeedbackService:getPidByPackageName	(Ljava/lang/String;)I
    //   35: istore_1
    //   36: aload 5
    //   38: astore 4
    //   40: aload_0
    //   41: ldc 103
    //   43: invokespecial 101	com/xiaomi/mms/transaction/MxFeedbackService:getPidByPackageName	(Ljava/lang/String;)I
    //   46: istore_2
    //   47: aload 5
    //   49: astore 4
    //   51: aload_0
    //   52: ldc 105
    //   54: invokespecial 101	com/xiaomi/mms/transaction/MxFeedbackService:getPidByPackageName	(Ljava/lang/String;)I
    //   57: istore_3
    //   58: aload 5
    //   60: astore 4
    //   62: aload_0
    //   63: invokestatic 111	java/lang/Runtime:getRuntime	()Ljava/lang/Runtime;
    //   66: ldc 113
    //   68: invokevirtual 117	java/lang/Runtime:exec	(Ljava/lang/String;)Ljava/lang/Process;
    //   71: putfield 119	com/xiaomi/mms/transaction/MxFeedbackService:mProcess	Ljava/lang/Process;
    //   74: aload 5
    //   76: astore 4
    //   78: new 121	java/io/BufferedReader
    //   81: dup
    //   82: new 123	java/io/InputStreamReader
    //   85: dup
    //   86: aload_0
    //   87: getfield 119	com/xiaomi/mms/transaction/MxFeedbackService:mProcess	Ljava/lang/Process;
    //   90: invokevirtual 129	java/lang/Process:getInputStream	()Ljava/io/InputStream;
    //   93: invokespecial 132	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   96: invokespecial 135	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   99: astore 5
    //   101: aload 5
    //   103: invokevirtual 138	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   106: astore 4
    //   108: aload 4
    //   110: ifnull +192 -> 302
    //   113: iload_1
    //   114: ifle +84 -> 198
    //   117: aload 4
    //   119: iload_1
    //   120: invokestatic 144	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   123: invokevirtual 148	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   126: ifeq +72 -> 198
    //   129: aload 7
    //   131: aload 4
    //   133: invokevirtual 29	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   136: pop
    //   137: aload 7
    //   139: bipush 10
    //   141: invokevirtual 151	java/lang/StringBuilder:append	(C)Ljava/lang/StringBuilder;
    //   144: pop
    //   145: goto -44 -> 101
    //   148: astore 6
    //   150: aload 5
    //   152: astore 4
    //   154: ldc 55
    //   156: ldc -103
    //   158: aload 6
    //   160: invokestatic 159	com/xiaomi/mms/utils/logger/MyLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   163: aload_0
    //   164: getfield 119	com/xiaomi/mms/transaction/MxFeedbackService:mProcess	Ljava/lang/Process;
    //   167: ifnull +15 -> 182
    //   170: aload_0
    //   171: getfield 119	com/xiaomi/mms/transaction/MxFeedbackService:mProcess	Ljava/lang/Process;
    //   174: invokevirtual 162	java/lang/Process:destroy	()V
    //   177: aload_0
    //   178: aconst_null
    //   179: putfield 119	com/xiaomi/mms/transaction/MxFeedbackService:mProcess	Ljava/lang/Process;
    //   182: aload 5
    //   184: ifnull +8 -> 192
    //   187: aload 5
    //   189: invokevirtual 165	java/io/BufferedReader:close	()V
    //   192: aload 7
    //   194: invokevirtual 40	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   197: areturn
    //   198: iload_2
    //   199: ifle +68 -> 267
    //   202: aload 4
    //   204: iload_2
    //   205: invokestatic 144	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   208: invokevirtual 148	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   211: ifeq +56 -> 267
    //   214: aload 7
    //   216: aload 4
    //   218: invokevirtual 29	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   221: pop
    //   222: aload 7
    //   224: bipush 10
    //   226: invokevirtual 151	java/lang/StringBuilder:append	(C)Ljava/lang/StringBuilder;
    //   229: pop
    //   230: goto -129 -> 101
    //   233: astore 4
    //   235: aload_0
    //   236: getfield 119	com/xiaomi/mms/transaction/MxFeedbackService:mProcess	Ljava/lang/Process;
    //   239: ifnull +15 -> 254
    //   242: aload_0
    //   243: getfield 119	com/xiaomi/mms/transaction/MxFeedbackService:mProcess	Ljava/lang/Process;
    //   246: invokevirtual 162	java/lang/Process:destroy	()V
    //   249: aload_0
    //   250: aconst_null
    //   251: putfield 119	com/xiaomi/mms/transaction/MxFeedbackService:mProcess	Ljava/lang/Process;
    //   254: aload 5
    //   256: ifnull +8 -> 264
    //   259: aload 5
    //   261: invokevirtual 165	java/io/BufferedReader:close	()V
    //   264: aload 4
    //   266: athrow
    //   267: iload_3
    //   268: ifle -167 -> 101
    //   271: aload 4
    //   273: iload_3
    //   274: invokestatic 144	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   277: invokevirtual 148	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   280: ifeq -179 -> 101
    //   283: aload 7
    //   285: aload 4
    //   287: invokevirtual 29	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   290: pop
    //   291: aload 7
    //   293: bipush 10
    //   295: invokevirtual 151	java/lang/StringBuilder:append	(C)Ljava/lang/StringBuilder;
    //   298: pop
    //   299: goto -198 -> 101
    //   302: aload_0
    //   303: getfield 119	com/xiaomi/mms/transaction/MxFeedbackService:mProcess	Ljava/lang/Process;
    //   306: ifnull +15 -> 321
    //   309: aload_0
    //   310: getfield 119	com/xiaomi/mms/transaction/MxFeedbackService:mProcess	Ljava/lang/Process;
    //   313: invokevirtual 162	java/lang/Process:destroy	()V
    //   316: aload_0
    //   317: aconst_null
    //   318: putfield 119	com/xiaomi/mms/transaction/MxFeedbackService:mProcess	Ljava/lang/Process;
    //   321: aload 5
    //   323: ifnull +8 -> 331
    //   326: aload 5
    //   328: invokevirtual 165	java/io/BufferedReader:close	()V
    //   331: goto -139 -> 192
    //   334: astore 5
    //   336: ldc 55
    //   338: ldc -89
    //   340: aload 5
    //   342: invokestatic 159	com/xiaomi/mms/utils/logger/MyLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   345: goto -81 -> 264
    //   348: astore 4
    //   350: ldc 55
    //   352: ldc -89
    //   354: aload 4
    //   356: invokestatic 159	com/xiaomi/mms/utils/logger/MyLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   359: goto -167 -> 192
    //   362: astore 4
    //   364: ldc 55
    //   366: ldc -89
    //   368: aload 4
    //   370: invokestatic 159	com/xiaomi/mms/utils/logger/MyLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   373: goto -42 -> 331
    //   376: astore 6
    //   378: aload 4
    //   380: astore 5
    //   382: aload 6
    //   384: astore 4
    //   386: goto -151 -> 235
    //   389: astore 4
    //   391: aload 6
    //   393: astore 5
    //   395: aload 4
    //   397: astore 6
    //   399: goto -249 -> 150
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	402	0	this	MxFeedbackService
    //   35	85	1	i	int
    //   46	159	2	j	int
    //   57	217	3	k	int
    //   27	190	4	localObject1	Object
    //   233	53	4	str	String
    //   348	7	4	localIOException1	IOException
    //   362	17	4	localIOException2	IOException
    //   384	1	4	localObject2	Object
    //   389	7	4	localIOException3	IOException
    //   20	307	5	localBufferedReader	java.io.BufferedReader
    //   334	7	5	localIOException4	IOException
    //   380	14	5	localObject3	Object
    //   23	1	6	localObject4	Object
    //   148	11	6	localIOException5	IOException
    //   376	16	6	localObject5	Object
    //   397	1	6	localObject6	Object
    //   17	275	7	localStringBuilder	StringBuilder
    // Exception table:
    //   from	to	target	type
    //   101	108	148	java/io/IOException
    //   117	145	148	java/io/IOException
    //   202	230	148	java/io/IOException
    //   271	299	148	java/io/IOException
    //   101	108	233	finally
    //   117	145	233	finally
    //   202	230	233	finally
    //   271	299	233	finally
    //   259	264	334	java/io/IOException
    //   187	192	348	java/io/IOException
    //   326	331	362	java/io/IOException
    //   29	36	376	finally
    //   40	47	376	finally
    //   51	58	376	finally
    //   62	74	376	finally
    //   78	101	376	finally
    //   154	163	376	finally
    //   29	36	389	java/io/IOException
    //   40	47	389	java/io/IOException
    //   51	58	389	java/io/IOException
    //   62	74	389	java/io/IOException
    //   78	101	389	java/io/IOException
  }
  
  private String formatDateString(Context paramContext, long paramLong)
  {
    return new SimpleDateFormat("yyyy-MM-dd-HHmmss").format(new Date(paramLong));
  }
  
  private String getMxFeedback(Context paramContext)
  {
    paramContext = MxActivateService.getMxActivateServiceFeedback(this);
    if (TextUtils.isEmpty(paramContext)) {
      return "";
    }
    return paramContext + "\n";
  }
  
  private int getPidByPackageName(String paramString)
  {
    List localList = ((ActivityManager)getSystemService("activity")).getRunningAppProcesses();
    if ((localList == null) || (localList.size() == 0)) {}
    for (;;)
    {
      return -1;
      int i = 0;
      while (i < localList.size()) {
        try
        {
          ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)localList.get(i);
          if (paramString.equals(processName))
          {
            int j = pid;
            return j;
          }
        }
        catch (Exception localException)
        {
          MyLog.e("MxFeedbackService", "getPidByPackageName Exception", localException);
          i += 1;
        }
      }
    }
  }
  
  private String getSummaryString(String paramString)
  {
    JSONObject localJSONObject = new JSONObject();
    TelephonyManager localTelephonyManager = (TelephonyManager)getSystemService("phone");
    try
    {
      localJSONObject.put("ro.product.board", Build.BOARD);
      localJSONObject.put("ro.product.brand", Build.BRAND);
      localJSONObject.put("ro.build.id", Build.DISPLAY);
      localJSONObject.put("ro.build.type", Build.TYPE);
      localJSONObject.put("ro.build.version.codename", Build.VERSION.CODENAME);
      localJSONObject.put("ro.product.device", Build.DEVICE);
      localJSONObject.put("ro.build.version.incremental", Build.VERSION.INCREMENTAL);
      localJSONObject.put("ro.soc.maxfreq", SystemProperties.get("ro.soc.maxfreq", ""));
      localJSONObject.put("ro.product.model", Build.MODEL);
      localJSONObject.put("network.name", localTelephonyManager.getNetworkOperatorName());
      localJSONObject.put("network.type", localTelephonyManager.getNetworkType());
      localJSONObject.put("phone.type", localTelephonyManager.getPhoneType());
      localJSONObject.put("ro.product.name", Build.PRODUCT);
      localJSONObject.put("ro.build.version.release", Build.VERSION.RELEASE);
      localJSONObject.put("ro.serialno", SystemProperties.get("ro.serialno", ""));
      localJSONObject.put("timestamp", formatDateString(this, System.currentTimeMillis()));
      localJSONObject.put("ui.language", Locale.getDefault().toString());
      localJSONObject.put("description", "[Cloud Messaging]:" + paramString);
      localJSONObject.put("type", "others");
      return localJSONObject.toString();
    }
    catch (JSONException paramString)
    {
      for (;;)
      {
        MyLog.e("MxFeedbackService", "getSummaryString JSONException", paramString);
      }
    }
  }
  
  private static Account getXiaomiAccount(Context paramContext)
  {
    paramContext = AccountManager.get(paramContext).getAccountsByType("com.xiaomi");
    if (paramContext.length > 0) {
      return paramContext[0];
    }
    return null;
  }
  
  private String getXmsfFeedback(Context paramContext)
  {
    return "";
  }
  
  private void initBugReportDirs()
  {
    File localFile = new File(MX_BUGREPORT_PATH);
    if (!localFile.exists())
    {
      Shell.runShell("mkdir -p %s", new Object[] { localFile });
      Shell.runShell("chmod 777 %s", new Object[] { localFile });
    }
    localFile = new File(BUG_REPORT_PATH);
    if (!localFile.exists())
    {
      Shell.runShell("mkdir -p %s", new Object[] { localFile });
      Shell.runShell("chmod 755 %s", new Object[] { localFile });
      Shell.runShell("chown system.system %s", new Object[] { localFile });
    }
  }
  
  private boolean isFeedbackEnabled()
  {
    boolean bool = false;
    Object localObject = PreferenceManager.getDefaultSharedPreferences(this);
    long l = ((SharedPreferences)localObject).getLong("pref_feedback_last_time", 0L);
    if ((l == 0L) || (System.currentTimeMillis() - l > 86400000L))
    {
      localObject = ((SharedPreferences)localObject).edit();
      ((SharedPreferences.Editor)localObject).putInt("pref_feedback_times", 0);
      ((SharedPreferences.Editor)localObject).putLong("pref_feedback_last_time", System.currentTimeMillis());
      ((SharedPreferences.Editor)localObject).apply();
      bool = true;
    }
    int i;
    do
    {
      return bool;
      i = ((SharedPreferences)localObject).getInt("pref_feedback_times", 0);
    } while (i > 1);
    localObject = ((SharedPreferences)localObject).edit();
    ((SharedPreferences.Editor)localObject).putInt("pref_feedback_times", i + 1);
    ((SharedPreferences.Editor)localObject).putLong("pref_feedback_last_time", System.currentTimeMillis());
    ((SharedPreferences.Editor)localObject).apply();
    return true;
  }
  
  private void removeFiles()
  {
    Shell.runShell("rm %s/*", new Object[] { MX_BUGREPORT_PATH });
  }
  
  /* Error */
  private void saveContentToFile(String paramString1, String paramString2)
  {
    // Byte code:
    //   0: new 31	java/io/File
    //   3: dup
    //   4: aload_1
    //   5: invokespecial 372	java/io/File:<init>	(Ljava/lang/String;)V
    //   8: astore 9
    //   10: aconst_null
    //   11: astore_1
    //   12: aconst_null
    //   13: astore 7
    //   15: aconst_null
    //   16: astore 8
    //   18: aconst_null
    //   19: astore 6
    //   21: aconst_null
    //   22: astore 5
    //   24: aload_1
    //   25: astore_3
    //   26: aload 8
    //   28: astore 4
    //   30: aload 9
    //   32: invokevirtual 376	java/io/File:exists	()Z
    //   35: ifne +15 -> 50
    //   38: aload_1
    //   39: astore_3
    //   40: aload 8
    //   42: astore 4
    //   44: aload 9
    //   46: invokevirtual 432	java/io/File:createNewFile	()Z
    //   49: pop
    //   50: aload_1
    //   51: astore_3
    //   52: aload 8
    //   54: astore 4
    //   56: new 434	java/io/FileOutputStream
    //   59: dup
    //   60: aload 9
    //   62: invokespecial 437	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   65: astore_1
    //   66: new 439	java/io/OutputStreamWriter
    //   69: dup
    //   70: aload_1
    //   71: invokespecial 442	java/io/OutputStreamWriter:<init>	(Ljava/io/OutputStream;)V
    //   74: astore_3
    //   75: aload_3
    //   76: aload_2
    //   77: invokevirtual 445	java/io/OutputStreamWriter:append	(Ljava/lang/CharSequence;)Ljava/io/Writer;
    //   80: pop
    //   81: aload_3
    //   82: invokevirtual 448	java/io/OutputStreamWriter:flush	()V
    //   85: aload_3
    //   86: ifnull +7 -> 93
    //   89: aload_3
    //   90: invokevirtual 449	java/io/OutputStreamWriter:close	()V
    //   93: aload_1
    //   94: ifnull +7 -> 101
    //   97: aload_1
    //   98: invokevirtual 450	java/io/FileOutputStream:close	()V
    //   101: return
    //   102: astore_2
    //   103: aload 7
    //   105: astore_1
    //   106: aload_1
    //   107: astore_3
    //   108: aload 5
    //   110: astore 4
    //   112: ldc 55
    //   114: ldc_w 452
    //   117: aload_2
    //   118: invokestatic 159	com/xiaomi/mms/utils/logger/MyLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   121: aload 5
    //   123: ifnull +8 -> 131
    //   126: aload 5
    //   128: invokevirtual 449	java/io/OutputStreamWriter:close	()V
    //   131: aload_1
    //   132: ifnull -31 -> 101
    //   135: aload_1
    //   136: invokevirtual 450	java/io/FileOutputStream:close	()V
    //   139: return
    //   140: astore_1
    //   141: ldc 55
    //   143: ldc_w 454
    //   146: aload_1
    //   147: invokestatic 159	com/xiaomi/mms/utils/logger/MyLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   150: return
    //   151: astore_1
    //   152: aload 4
    //   154: ifnull +8 -> 162
    //   157: aload 4
    //   159: invokevirtual 449	java/io/OutputStreamWriter:close	()V
    //   162: aload_3
    //   163: ifnull +7 -> 170
    //   166: aload_3
    //   167: invokevirtual 450	java/io/FileOutputStream:close	()V
    //   170: aload_1
    //   171: athrow
    //   172: astore_2
    //   173: ldc 55
    //   175: ldc_w 456
    //   178: aload_2
    //   179: invokestatic 159	com/xiaomi/mms/utils/logger/MyLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   182: goto -20 -> 162
    //   185: astore_2
    //   186: ldc 55
    //   188: ldc_w 454
    //   191: aload_2
    //   192: invokestatic 159	com/xiaomi/mms/utils/logger/MyLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   195: goto -25 -> 170
    //   198: astore_2
    //   199: ldc 55
    //   201: ldc_w 456
    //   204: aload_2
    //   205: invokestatic 159	com/xiaomi/mms/utils/logger/MyLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   208: goto -77 -> 131
    //   211: astore_2
    //   212: ldc 55
    //   214: ldc_w 456
    //   217: aload_2
    //   218: invokestatic 159	com/xiaomi/mms/utils/logger/MyLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   221: goto -128 -> 93
    //   224: astore_1
    //   225: ldc 55
    //   227: ldc_w 454
    //   230: aload_1
    //   231: invokestatic 159	com/xiaomi/mms/utils/logger/MyLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   234: goto -133 -> 101
    //   237: astore_2
    //   238: aload_1
    //   239: astore_3
    //   240: aload 6
    //   242: astore 4
    //   244: aload_2
    //   245: astore_1
    //   246: goto -94 -> 152
    //   249: astore_2
    //   250: aload_3
    //   251: astore 4
    //   253: aload_1
    //   254: astore_3
    //   255: aload_2
    //   256: astore_1
    //   257: goto -105 -> 152
    //   260: astore_2
    //   261: goto -155 -> 106
    //   264: astore_2
    //   265: aload_3
    //   266: astore 5
    //   268: goto -162 -> 106
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	271	0	this	MxFeedbackService
    //   0	271	1	paramString1	String
    //   0	271	2	paramString2	String
    //   25	241	3	localObject1	Object
    //   28	224	4	localObject2	Object
    //   22	245	5	localObject3	Object
    //   19	222	6	localObject4	Object
    //   13	91	7	localObject5	Object
    //   16	37	8	localObject6	Object
    //   8	53	9	localFile	File
    // Exception table:
    //   from	to	target	type
    //   30	38	102	java/io/IOException
    //   44	50	102	java/io/IOException
    //   56	66	102	java/io/IOException
    //   135	139	140	java/io/IOException
    //   30	38	151	finally
    //   44	50	151	finally
    //   56	66	151	finally
    //   112	121	151	finally
    //   157	162	172	java/io/IOException
    //   166	170	185	java/io/IOException
    //   126	131	198	java/io/IOException
    //   89	93	211	java/io/IOException
    //   97	101	224	java/io/IOException
    //   66	75	237	finally
    //   75	85	249	finally
    //   66	75	260	java/io/IOException
    //   75	85	264	java/io/IOException
  }
  
  private void startFeedback(String paramString)
  {
    String str1 = MX_BUGREPORT_PATH + File.separator;
    String str2 = getXmsfFeedback(this);
    String str3 = getMxFeedback(this);
    str2 = str2 + str3 + dumpFeedback();
    if (!TextUtils.isEmpty(str2)) {
      saveContentToFile(str1 + "logcat_" + System.currentTimeMillis() + ".log", str2);
    }
    paramString = getSummaryString(paramString);
    if (!TextUtils.isEmpty(paramString)) {
      saveContentToFile(SUMMARY_FILE_PATH, paramString);
    }
    if (zipFilesAndSubmit()) {
      toastFeedback(2131362284);
    }
  }
  
  public static void startMxFeedback(final Activity paramActivity, String paramString)
  {
    if (getXiaomiAccount(paramActivity) == null)
    {
      addAccount(paramActivity, new AccountManagerCallback()
      {
        public void run(AccountManagerFuture<Bundle> paramAnonymousAccountManagerFuture)
        {
          if (paramAnonymousAccountManagerFuture != null) {}
          try
          {
            paramAnonymousAccountManagerFuture = (Bundle)paramAnonymousAccountManagerFuture.getResult();
            if ((paramAnonymousAccountManagerFuture != null) && (!TextUtils.isEmpty(paramAnonymousAccountManagerFuture.getString("authAccount"))))
            {
              paramAnonymousAccountManagerFuture = new Intent("com.xiaomi.mms.mx.ACTION_START_MX_FEEDBACK");
              paramAnonymousAccountManagerFuture.putExtra("extra_description", val$description);
              paramAnonymousAccountManagerFuture.setPackage(paramActivity.getPackageName());
              paramActivity.startService(paramAnonymousAccountManagerFuture);
            }
            return;
          }
          catch (OperationCanceledException paramAnonymousAccountManagerFuture)
          {
            MyLog.e("MxFeedbackService", "startMxFeedback OperationCanceledException", paramAnonymousAccountManagerFuture);
            return;
          }
          catch (AuthenticatorException paramAnonymousAccountManagerFuture)
          {
            MyLog.e("MxFeedbackService", "startMxFeedback AuthenticatorException", paramAnonymousAccountManagerFuture);
            return;
          }
          catch (IOException paramAnonymousAccountManagerFuture)
          {
            MyLog.e("MxFeedbackService", "startMxFeedback IOException", paramAnonymousAccountManagerFuture);
          }
        }
      });
      return;
    }
    Intent localIntent = new Intent("com.xiaomi.mms.mx.ACTION_START_MX_FEEDBACK");
    localIntent.putExtra("extra_description", paramString);
    localIntent.setPackage(paramActivity.getPackageName());
    paramActivity.startService(localIntent);
  }
  
  private void submitZipToBugReport(String paramString)
  {
    Intent localIntent = new Intent();
    localIntent.setClassName("com.miui.bugreport", "com.miui.bugreport.service.FeedbackBackgroundService");
    localIntent.putExtra("service_command", "submit_feedback");
    localIntent.putExtra("feedback_file", paramString);
    startService(localIntent);
  }
  
  private void toastFeedback(final int paramInt)
  {
    mUIHandler.post(new Runnable()
    {
      public void run()
      {
        Toast.makeText(MxFeedbackService.this, paramInt, 0).show();
      }
    });
  }
  
  private void zipFile(File paramFile, ZipOutputStream paramZipOutputStream, String paramString)
    throws FileNotFoundException, IOException
  {
    Object localObject1 = new StringBuilder().append(paramString);
    if (paramString.trim().length() == 0) {}
    for (paramString = "";; paramString = File.separator)
    {
      localObject1 = paramString + paramFile.getName();
      if ((!paramFile.exists()) || (!paramFile.isFile())) {
        break label167;
      }
      byte[] arrayOfByte = new byte[524288];
      paramString = null;
      try
      {
        paramFile = new BufferedInputStream(new FileInputStream(paramFile), 524288);
        try
        {
          paramZipOutputStream.putNextEntry(new ZipEntry((String)localObject1));
          for (;;)
          {
            int i = paramFile.read(arrayOfByte);
            if (i == -1) {
              break;
            }
            paramZipOutputStream.write(arrayOfByte, 0, i);
          }
          if (paramFile == null) {
            break label134;
          }
        }
        finally {}
      }
      finally
      {
        for (;;)
        {
          label134:
          paramFile = paramString;
          paramString = (String)localObject2;
        }
      }
      paramFile.close();
      paramZipOutputStream.flush();
      paramZipOutputStream.closeEntry();
      throw paramString;
    }
    if (paramFile != null) {
      paramFile.close();
    }
    paramZipOutputStream.flush();
    paramZipOutputStream.closeEntry();
    label167:
  }
  
  private void zipFiles(Collection<File> paramCollection, File paramFile)
    throws IOException
  {
    paramFile = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(paramFile), 524288));
    paramCollection = paramCollection.iterator();
    while (paramCollection.hasNext())
    {
      File localFile = (File)paramCollection.next();
      if (localFile != null) {
        try
        {
          zipFile(localFile, paramFile, "");
        }
        catch (FileNotFoundException localFileNotFoundException)
        {
          MyLog.e("MxFeedbackService", "zipFiles FileNotFoundException", localFileNotFoundException);
        }
      }
    }
    paramFile.close();
  }
  
  private boolean zipFilesAndSubmit()
  {
    boolean bool = false;
    String str1 = formatDateString(this, System.currentTimeMillis()).trim().replace(" ", "-");
    File localFile1 = new File("/data/data/com.android.mms" + File.separator + str1 + ".tmp");
    File localFile2 = new File("/data/data/com.android.mms" + File.separator + str1 + ".zip");
    for (;;)
    {
      try
      {
        localArrayList = new ArrayList();
        arrayOfFile = new File(MX_BUGREPORT_PATH).listFiles();
        if (arrayOfFile == null) {
          throw new IOException("failed to get bug report files!");
        }
      }
      catch (IOException localIOException)
      {
        ArrayList localArrayList;
        File[] arrayOfFile;
        MyLog.e("MxFeedbackService", "zipFilesAndSubmit IOException", localIOException);
        localFile1.delete();
        localFile2.delete();
        removeFiles();
        return bool;
        int j = arrayOfFile.length;
        int i = 0;
        if (i < j)
        {
          localArrayList.add(arrayOfFile[i]);
          i += 1;
          continue;
        }
        zipFiles(localArrayList, localFile1);
        if (!localFile1.renameTo(localFile2)) {
          throw new IOException("failed to rename temporary file!");
        }
      }
      finally
      {
        localFile1.delete();
        localFile2.delete();
      }
      String str3 = BUG_REPORT_PATH + File.separator + str2 + ".zip";
      if (!Shell.move(localFile2.getAbsolutePath(), str3)) {
        throw new IOException("failed to move zip files!");
      }
      Shell.runShell("chown system.system %s", new Object[] { str3 });
      submitZipToBugReport(str3);
      bool = true;
      localFile1.delete();
    }
  }
  
  protected void onHandleIntent(Intent paramIntent)
  {
    if (paramIntent == null) {}
    while (!"com.xiaomi.mms.mx.ACTION_START_MX_FEEDBACK".equals(paramIntent.getAction())) {
      return;
    }
    if (!isFeedbackEnabled())
    {
      toastFeedback(2131362285);
      return;
    }
    toastFeedback(2131362283);
    initBugReportDirs();
    startFeedback(paramIntent.getStringExtra("extra_description"));
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.transaction.MxFeedbackService
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */