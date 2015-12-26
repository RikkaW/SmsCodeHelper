package com.android.mms.update;

import android.accounts.Account;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import com.android.mms.MmsApp;
import com.android.mms.understand.TemplateUpdate;
import com.android.mms.understand.TemplateUpdate.UpdatePatch;
import com.android.mms.util.MiStatSdkHelper;
import com.android.mms.util.ThreadPool;
import com.android.mms.util.XiaomiAccount;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import miui.os.Build;
import miui.os.Environment;
import miui.yellowpage.MiPubUtils;
import org.json.JSONObject;

public class TemplateRequest
{
  public static final File EXTERNAL_STORAGE_DIRECTORY = new File(Environment.getExternalStorageMiuiDirectory(), "Mms");
  private static String RESULT_ALL;
  private static String RESULT_DIFF;
  private static String RESULT_NO_UPDATE = "no-update";
  public static String TEMPLATE_URL = "http://open.mp.huangye.miui.com/sms/smartr/template";
  private static boolean sIsRequesting;
  
  static
  {
    RESULT_DIFF = "diff";
    RESULT_ALL = "all";
    if (!Build.IS_STABLE_VERSION)
    {
      TEMPLATE_URL = "http://trial.open.mp.huangye.miui.com/sms/smartr/template";
      return;
    }
  }
  
  /* Error */
  private void copyFileToTarget(File paramFile)
    throws IOException
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: aconst_null
    //   4: astore_3
    //   5: new 79	java/io/FileInputStream
    //   8: dup
    //   9: aload_1
    //   10: invokespecial 81	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   13: astore_1
    //   14: aload_1
    //   15: invokevirtual 87	java/io/InputStream:available	()I
    //   18: newarray <illegal type>
    //   20: astore_2
    //   21: aload_1
    //   22: aload_2
    //   23: invokevirtual 91	java/io/InputStream:read	([B)I
    //   26: pop
    //   27: new 19	java/io/File
    //   30: dup
    //   31: invokestatic 97	com/android/mms/understand/TemplateUpdate:getDownloadedFile	()Ljava/lang/String;
    //   34: invokespecial 100	java/io/File:<init>	(Ljava/lang/String;)V
    //   37: invokevirtual 104	java/io/File:exists	()Z
    //   40: ifne +10 -> 50
    //   43: invokestatic 97	com/android/mms/understand/TemplateUpdate:getDownloadedFile	()Ljava/lang/String;
    //   46: invokestatic 110	com/android/mms/update/FileUtil:createFile	(Ljava/lang/String;)Ljava/io/File;
    //   49: pop
    //   50: new 112	java/io/FileOutputStream
    //   53: dup
    //   54: invokestatic 97	com/android/mms/understand/TemplateUpdate:getDownloadedFile	()Ljava/lang/String;
    //   57: invokespecial 113	java/io/FileOutputStream:<init>	(Ljava/lang/String;)V
    //   60: astore 4
    //   62: aload 4
    //   64: aload_2
    //   65: invokevirtual 117	java/io/FileOutputStream:write	([B)V
    //   68: aload_1
    //   69: ifnull +17 -> 86
    //   72: aload_1
    //   73: invokevirtual 120	java/io/InputStream:close	()V
    //   76: aload 4
    //   78: ifnull +8 -> 86
    //   81: aload 4
    //   83: invokevirtual 121	java/io/FileOutputStream:close	()V
    //   86: return
    //   87: astore_2
    //   88: aload 4
    //   90: astore_1
    //   91: aload_1
    //   92: ifnull +15 -> 107
    //   95: aload_1
    //   96: invokevirtual 120	java/io/InputStream:close	()V
    //   99: aload_3
    //   100: ifnull +7 -> 107
    //   103: aload_3
    //   104: invokevirtual 121	java/io/FileOutputStream:close	()V
    //   107: aload_2
    //   108: athrow
    //   109: astore_1
    //   110: aload_3
    //   111: ifnull +7 -> 118
    //   114: aload_3
    //   115: invokevirtual 121	java/io/FileOutputStream:close	()V
    //   118: aload_1
    //   119: athrow
    //   120: astore_1
    //   121: aload 4
    //   123: ifnull +8 -> 131
    //   126: aload 4
    //   128: invokevirtual 121	java/io/FileOutputStream:close	()V
    //   131: aload_1
    //   132: athrow
    //   133: astore_2
    //   134: goto -43 -> 91
    //   137: astore_2
    //   138: aload 4
    //   140: astore_3
    //   141: goto -50 -> 91
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	144	0	this	TemplateRequest
    //   0	144	1	paramFile	File
    //   20	45	2	arrayOfByte	byte[]
    //   87	21	2	localObject1	Object
    //   133	1	2	localObject2	Object
    //   137	1	2	localObject3	Object
    //   4	137	3	localFileOutputStream1	java.io.FileOutputStream
    //   1	138	4	localFileOutputStream2	java.io.FileOutputStream
    // Exception table:
    //   from	to	target	type
    //   5	14	87	finally
    //   95	99	109	finally
    //   72	76	120	finally
    //   14	50	133	finally
    //   50	62	133	finally
    //   62	68	137	finally
  }
  
  /* Error */
  private static long getDataVersion()
  {
    // Byte code:
    //   0: lconst_0
    //   1: lstore_3
    //   2: aconst_null
    //   3: astore 12
    //   5: aconst_null
    //   6: astore 8
    //   8: aconst_null
    //   9: astore 9
    //   11: aconst_null
    //   12: astore 11
    //   14: aconst_null
    //   15: astore 16
    //   17: aconst_null
    //   18: astore 6
    //   20: aconst_null
    //   21: astore 15
    //   23: aconst_null
    //   24: astore 13
    //   26: aconst_null
    //   27: astore 7
    //   29: aconst_null
    //   30: astore 14
    //   32: aconst_null
    //   33: astore 10
    //   35: new 79	java/io/FileInputStream
    //   38: dup
    //   39: invokestatic 128	com/android/mms/understand/TemplateUpdate:getVersionFile	()Ljava/lang/String;
    //   42: invokespecial 129	java/io/FileInputStream:<init>	(Ljava/lang/String;)V
    //   45: astore 5
    //   47: new 131	java/io/InputStreamReader
    //   50: dup
    //   51: aload 5
    //   53: invokespecial 134	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   56: astore 6
    //   58: new 136	java/io/BufferedReader
    //   61: dup
    //   62: aload 6
    //   64: invokespecial 139	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   67: astore 7
    //   69: aload 7
    //   71: invokevirtual 142	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   74: astore 8
    //   76: lload_3
    //   77: lstore_1
    //   78: aload 8
    //   80: invokestatic 148	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   83: ifne +12 -> 95
    //   86: aload 8
    //   88: invokestatic 154	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   91: istore_0
    //   92: iload_0
    //   93: i2l
    //   94: lstore_1
    //   95: aload 7
    //   97: ifnull +8 -> 105
    //   100: aload 7
    //   102: invokevirtual 155	java/io/BufferedReader:close	()V
    //   105: aload 6
    //   107: ifnull +8 -> 115
    //   110: aload 6
    //   112: invokevirtual 156	java/io/InputStreamReader:close	()V
    //   115: aload 5
    //   117: ifnull +8 -> 125
    //   120: aload 5
    //   122: invokevirtual 120	java/io/InputStream:close	()V
    //   125: lload_1
    //   126: lreturn
    //   127: astore 9
    //   129: aload 15
    //   131: astore 5
    //   133: aload 5
    //   135: astore 6
    //   137: aload 10
    //   139: astore 7
    //   141: aload 11
    //   143: astore 8
    //   145: aload 9
    //   147: invokevirtual 159	java/io/IOException:printStackTrace	()V
    //   150: aload 11
    //   152: ifnull +8 -> 160
    //   155: aload 11
    //   157: invokevirtual 155	java/io/BufferedReader:close	()V
    //   160: aload 10
    //   162: ifnull +8 -> 170
    //   165: aload 10
    //   167: invokevirtual 156	java/io/InputStreamReader:close	()V
    //   170: lload_3
    //   171: lstore_1
    //   172: aload 5
    //   174: ifnull -49 -> 125
    //   177: aload 5
    //   179: invokevirtual 120	java/io/InputStream:close	()V
    //   182: lconst_0
    //   183: lreturn
    //   184: astore 5
    //   186: aload 5
    //   188: invokevirtual 159	java/io/IOException:printStackTrace	()V
    //   191: lconst_0
    //   192: lreturn
    //   193: astore 9
    //   195: aload 12
    //   197: astore 11
    //   199: aload 13
    //   201: astore 10
    //   203: aload 16
    //   205: astore 5
    //   207: aload 5
    //   209: astore 6
    //   211: aload 10
    //   213: astore 7
    //   215: aload 11
    //   217: astore 8
    //   219: aload 9
    //   221: invokevirtual 160	java/lang/NumberFormatException:printStackTrace	()V
    //   224: aload 11
    //   226: ifnull +8 -> 234
    //   229: aload 11
    //   231: invokevirtual 155	java/io/BufferedReader:close	()V
    //   234: aload 10
    //   236: ifnull +8 -> 244
    //   239: aload 10
    //   241: invokevirtual 156	java/io/InputStreamReader:close	()V
    //   244: lload_3
    //   245: lstore_1
    //   246: aload 5
    //   248: ifnull -123 -> 125
    //   251: aload 5
    //   253: invokevirtual 120	java/io/InputStream:close	()V
    //   256: lconst_0
    //   257: lreturn
    //   258: astore 5
    //   260: goto -74 -> 186
    //   263: astore 5
    //   265: aload 8
    //   267: ifnull +8 -> 275
    //   270: aload 8
    //   272: invokevirtual 155	java/io/BufferedReader:close	()V
    //   275: aload 7
    //   277: ifnull +8 -> 285
    //   280: aload 7
    //   282: invokevirtual 156	java/io/InputStreamReader:close	()V
    //   285: aload 6
    //   287: ifnull +8 -> 295
    //   290: aload 6
    //   292: invokevirtual 120	java/io/InputStream:close	()V
    //   295: aload 5
    //   297: athrow
    //   298: astore 8
    //   300: aload 8
    //   302: invokevirtual 159	java/io/IOException:printStackTrace	()V
    //   305: goto -30 -> 275
    //   308: astore 7
    //   310: aload 7
    //   312: invokevirtual 159	java/io/IOException:printStackTrace	()V
    //   315: goto -30 -> 285
    //   318: astore 6
    //   320: aload 6
    //   322: invokevirtual 159	java/io/IOException:printStackTrace	()V
    //   325: goto -30 -> 295
    //   328: astore 6
    //   330: aload 6
    //   332: invokevirtual 159	java/io/IOException:printStackTrace	()V
    //   335: goto -175 -> 160
    //   338: astore 6
    //   340: aload 6
    //   342: invokevirtual 159	java/io/IOException:printStackTrace	()V
    //   345: goto -175 -> 170
    //   348: astore 6
    //   350: aload 6
    //   352: invokevirtual 159	java/io/IOException:printStackTrace	()V
    //   355: goto -121 -> 234
    //   358: astore 6
    //   360: aload 6
    //   362: invokevirtual 159	java/io/IOException:printStackTrace	()V
    //   365: goto -121 -> 244
    //   368: astore 7
    //   370: aload 7
    //   372: invokevirtual 159	java/io/IOException:printStackTrace	()V
    //   375: goto -270 -> 105
    //   378: astore 6
    //   380: aload 6
    //   382: invokevirtual 159	java/io/IOException:printStackTrace	()V
    //   385: goto -270 -> 115
    //   388: astore 5
    //   390: aload 5
    //   392: invokevirtual 159	java/io/IOException:printStackTrace	()V
    //   395: goto -270 -> 125
    //   398: astore 10
    //   400: aload 5
    //   402: astore 6
    //   404: aload 14
    //   406: astore 7
    //   408: aload 9
    //   410: astore 8
    //   412: aload 10
    //   414: astore 5
    //   416: goto -151 -> 265
    //   419: astore 10
    //   421: aload 6
    //   423: astore 7
    //   425: aload 5
    //   427: astore 6
    //   429: aload 9
    //   431: astore 8
    //   433: aload 10
    //   435: astore 5
    //   437: goto -172 -> 265
    //   440: astore 9
    //   442: aload 6
    //   444: astore 8
    //   446: aload 5
    //   448: astore 6
    //   450: aload 7
    //   452: astore 5
    //   454: aload 8
    //   456: astore 7
    //   458: aload 5
    //   460: astore 8
    //   462: aload 9
    //   464: astore 5
    //   466: goto -201 -> 265
    //   469: astore 9
    //   471: aload 13
    //   473: astore 10
    //   475: aload 12
    //   477: astore 11
    //   479: goto -272 -> 207
    //   482: astore 9
    //   484: aload 6
    //   486: astore 10
    //   488: aload 12
    //   490: astore 11
    //   492: goto -285 -> 207
    //   495: astore 9
    //   497: aload 6
    //   499: astore 10
    //   501: aload 7
    //   503: astore 11
    //   505: goto -298 -> 207
    //   508: astore 9
    //   510: goto -377 -> 133
    //   513: astore 9
    //   515: aload 6
    //   517: astore 10
    //   519: goto -386 -> 133
    //   522: astore 9
    //   524: aload 6
    //   526: astore 10
    //   528: aload 7
    //   530: astore 11
    //   532: goto -399 -> 133
    // Local variable table:
    //   start	length	slot	name	signature
    //   91	2	0	i	int
    //   77	169	1	l1	long
    //   1	244	3	l2	long
    //   45	133	5	localObject1	Object
    //   184	3	5	localIOException1	IOException
    //   205	47	5	localObject2	Object
    //   258	1	5	localIOException2	IOException
    //   263	33	5	localObject3	Object
    //   388	13	5	localIOException3	IOException
    //   414	51	5	localObject4	Object
    //   18	273	6	localObject5	Object
    //   318	3	6	localIOException4	IOException
    //   328	3	6	localIOException5	IOException
    //   338	3	6	localIOException6	IOException
    //   348	3	6	localIOException7	IOException
    //   358	3	6	localIOException8	IOException
    //   378	3	6	localIOException9	IOException
    //   402	123	6	localObject6	Object
    //   27	254	7	localObject7	Object
    //   308	3	7	localIOException10	IOException
    //   368	3	7	localIOException11	IOException
    //   406	123	7	localObject8	Object
    //   6	265	8	localObject9	Object
    //   298	3	8	localIOException12	IOException
    //   410	51	8	localObject10	Object
    //   9	1	9	localObject11	Object
    //   127	19	9	localIOException13	IOException
    //   193	237	9	localNumberFormatException1	NumberFormatException
    //   440	23	9	localObject12	Object
    //   469	1	9	localNumberFormatException2	NumberFormatException
    //   482	1	9	localNumberFormatException3	NumberFormatException
    //   495	1	9	localNumberFormatException4	NumberFormatException
    //   508	1	9	localIOException14	IOException
    //   513	1	9	localIOException15	IOException
    //   522	1	9	localIOException16	IOException
    //   33	207	10	localObject13	Object
    //   398	15	10	localObject14	Object
    //   419	15	10	localObject15	Object
    //   473	54	10	localObject16	Object
    //   12	519	11	localObject17	Object
    //   3	486	12	localObject18	Object
    //   24	448	13	localObject19	Object
    //   30	375	14	localObject20	Object
    //   21	109	15	localObject21	Object
    //   15	189	16	localObject22	Object
    // Exception table:
    //   from	to	target	type
    //   35	47	127	java/io/IOException
    //   177	182	184	java/io/IOException
    //   35	47	193	java/lang/NumberFormatException
    //   251	256	258	java/io/IOException
    //   35	47	263	finally
    //   145	150	263	finally
    //   219	224	263	finally
    //   270	275	298	java/io/IOException
    //   280	285	308	java/io/IOException
    //   290	295	318	java/io/IOException
    //   155	160	328	java/io/IOException
    //   165	170	338	java/io/IOException
    //   229	234	348	java/io/IOException
    //   239	244	358	java/io/IOException
    //   100	105	368	java/io/IOException
    //   110	115	378	java/io/IOException
    //   120	125	388	java/io/IOException
    //   47	58	398	finally
    //   58	69	419	finally
    //   69	76	440	finally
    //   78	92	440	finally
    //   47	58	469	java/lang/NumberFormatException
    //   58	69	482	java/lang/NumberFormatException
    //   69	76	495	java/lang/NumberFormatException
    //   78	92	495	java/lang/NumberFormatException
    //   47	58	508	java/io/IOException
    //   58	69	513	java/io/IOException
    //   69	76	522	java/io/IOException
    //   78	92	522	java/io/IOException
  }
  
  public static long getLocalVersion()
  {
    Log.v("TemplateRequest", " getLocalVersion ");
    return getDataVersion();
  }
  
  private JSONRequest getRequest(Context paramContext)
  {
    Log.v("TemplateRequest", " request uri is " + TEMPLATE_URL);
    JSONRequest localJSONRequest = new JSONRequest(paramContext, TEMPLATE_URL);
    long l = getLocalVersion();
    Log.v("TemplateRequest", " local version : " + l);
    localJSONRequest.addParam("version", String.valueOf(l));
    localJSONRequest.addParam("deviceId", MiPubUtils.getDeviceId(paramContext));
    Account localAccount = XiaomiAccount.getAccount(paramContext);
    String str = "";
    paramContext = str;
    if (!Build.IS_STABLE_VERSION)
    {
      paramContext = str;
      if (localAccount != null)
      {
        paramContext = str;
        if (!TextUtils.isEmpty(name)) {
          paramContext = name;
        }
      }
    }
    localJSONRequest.addParam("userId", paramContext);
    return localJSONRequest;
  }
  
  private static boolean isRequesting()
  {
    try
    {
      boolean bool = sIsRequesting;
      return bool;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  private void parseMsgTemplateInfo(Context paramContext, JSONObject paramJSONObject)
    throws Exception
  {
    Object localObject = paramJSONObject.getString("type");
    boolean bool3 = paramJSONObject.getBoolean("wifiOnly");
    if (RESULT_NO_UPDATE.equals(localObject))
    {
      Log.d("TemplateRequest", " no update needed! ");
      return;
    }
    if (RESULT_DIFF.equals(localObject)) {}
    int k;
    for (boolean bool1 = true;; bool1 = false)
    {
      String str = paramJSONObject.getString("uri");
      long l = paramJSONObject.getLong("new_version");
      Log.v("TemplateRequest", " uri and isIncremental " + str + " " + (String)localObject);
      paramJSONObject = new File(EXTERNAL_STORAGE_DIRECTORY, ".msgTemplate.tmp");
      int i = 0;
      k = 0;
      try
      {
        localObject = new HashMap();
        ((Map)localObject).put("x-xiaomi-meta-template-old-md5", "");
        ((Map)localObject).put("x-xiaomi-meta-template-md5", "");
        while ((i < 3) && (k == 0) && (Network.allowUpdate(bool3, true)))
        {
          Log.v("TemplateRequest", " begin to download file: " + str);
          boolean bool2 = FileUtil.downLoadFileWithHeader(paramContext, str, paramJSONObject.getAbsolutePath(), (Map)localObject);
          Log.v("TemplateRequest", " downLoadFileWithHeader: " + (String)((Map)localObject).get("x-xiaomi-meta-template-md5"));
          int j = i + 1;
          i = j;
          k = bool2;
          if (bool2)
          {
            copyFileToTarget(paramJSONObject);
            Log.v("TemplateRequest", " download files success!");
            TemplateUpdate.UpdatePatch localUpdatePatch = new TemplateUpdate.UpdatePatch();
            mVersion = l;
            mIsIncremental = bool1;
            mMd5 = ((String)((Map)localObject).get("x-xiaomi-meta-template-md5"));
            mOldMd5 = ((String)((Map)localObject).get("x-xiaomi-meta-template-old-md5"));
            MiStatSdkHelper.recourdUpdateEvent("mms");
            TemplateUpdate.onTemplateDownloaded(localUpdatePatch);
            i = j;
            k = bool2;
          }
        }
        if (!RESULT_ALL.equals(localObject)) {
          break;
        }
      }
      finally
      {
        FileUtil.deleteFile(paramJSONObject);
      }
    }
    Log.e("TemplateRequest", " unknown type!");
    return;
    if (k == 0)
    {
      Log.e("TemplateRequest", "failed to download file!");
      throw new IOException("failed to download file!");
    }
    FileUtil.deleteFile(paramJSONObject);
  }
  
  private static void setRequesting(boolean paramBoolean)
  {
    try
    {
      sIsRequesting = paramBoolean;
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public static void tryUpdateTemplate()
  {
    Object localObject = PreferenceManager.getDefaultSharedPreferences(MmsApp.getApp()).getString("update_content_preference", "");
    if (TextUtils.isEmpty((CharSequence)localObject)) {
      Log.w("TemplateRequest", " No update content");
    }
    boolean bool2;
    boolean bool3;
    boolean bool1;
    do
    {
      return;
      bool2 = true;
      bool3 = false;
      bool1 = bool2;
      try
      {
        localObject = new JSONObject((String)localObject);
        bool1 = bool2;
        bool2 = ((JSONObject)localObject).optBoolean("wifiOnly", true);
        bool1 = bool2;
        boolean bool4 = ((JSONObject)localObject).optBoolean("forced", false);
        bool1 = bool4;
        bool3 = bool2;
        bool2 = bool1;
      }
      catch (Exception localException)
      {
        for (;;)
        {
          localException.printStackTrace();
          bool2 = bool3;
          bool3 = bool1;
        }
      }
      if (!Network.allowUpdate(bool3, bool2))
      {
        Log.v("TemplateRequest", " Not allowing");
        return;
      }
    } while (isRequesting());
    setRequesting(true);
    ThreadPool.execute(new Runnable()
    {
      public void run()
      {
        TemplateRequest localTemplateRequest = new TemplateRequest();
        try
        {
          Object localObject2 = localTemplateRequest.requestUpdate();
          Log.v("TemplateRequest", "result is " + (String)localObject2);
          localObject2 = new JSONObject((String)localObject2);
          Log.d("TemplateRequest", "pulled data in json is: " + localObject2);
          localTemplateRequest.parseMsgTemplateInfo(MmsApp.getApp(), (JSONObject)localObject2);
          return;
        }
        catch (Exception localException)
        {
          for (;;)
          {
            localException.printStackTrace();
          }
        }
        finally
        {
          TemplateRequest.setRequesting(false);
        }
      }
    });
  }
  
  public String requestUpdate()
  {
    JSONRequest localJSONRequest = getRequest(MmsApp.getApp());
    if (localJSONRequest == null) {
      return null;
    }
    int i = localJSONRequest.getStatus();
    Log.d("TemplateRequest", "request status:" + i);
    switch (i)
    {
    case 5: 
    default: 
      throw new IllegalStateException();
    case 0: 
      return localJSONRequest.requestData();
    case 3: 
      throw new IllegalStateException();
    case 4: 
      throw new IllegalStateException();
    case 1: 
      throw new IllegalStateException();
    case 2: 
      throw new IllegalStateException();
    }
    throw new IllegalStateException();
  }
}

/* Location:
 * Qualified Name:     com.android.mms.update.TemplateRequest
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */