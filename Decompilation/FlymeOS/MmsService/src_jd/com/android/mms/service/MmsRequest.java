package com.android.mms.service;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.service.carrier.ICarrierMessagingCallback.Stub;
import android.util.Log;
import com.android.mms.service.exception.MmsHttpException;

public abstract class MmsRequest
{
  protected String mCreator;
  protected Uri mMessageUri;
  protected MmsConfig.Overridden mMmsConfig;
  protected Bundle mMmsConfigOverrides;
  protected RequestManager mRequestManager;
  protected int mSubId;
  
  public MmsRequest(RequestManager paramRequestManager, Uri paramUri, int paramInt, String paramString, Bundle paramBundle)
  {
    mRequestManager = paramRequestManager;
    mMessageUri = paramUri;
    mSubId = paramInt;
    mCreator = paramString;
    mMmsConfigOverrides = paramBundle;
    mMmsConfig = null;
  }
  
  private boolean ensureMmsConfigLoaded()
  {
    if (mMmsConfig == null)
    {
      MmsConfig localMmsConfig = MmsConfigManager.getInstance().getMmsConfigBySubId(mSubId);
      if (localMmsConfig != null) {
        mMmsConfig = new MmsConfig.Overridden(localMmsConfig, mMmsConfigOverrides);
      }
    }
    return mMmsConfig != null;
  }
  
  protected static int toSmsManagerResult(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return 1;
    case 0: 
      return -1;
    }
    return 6;
  }
  
  protected abstract int checkResponse(byte[] paramArrayOfByte);
  
  protected abstract byte[] doHttp(Context paramContext, MmsNetworkManager paramMmsNetworkManager, ApnSettings paramApnSettings)
    throws MmsHttpException;
  
  /* Error */
  public void execute(Context paramContext, MmsNetworkManager paramMmsNetworkManager)
  {
    // Byte code:
    //   0: iconst_1
    //   1: istore 4
    //   3: iconst_0
    //   4: istore 6
    //   6: iconst_0
    //   7: istore 7
    //   9: aconst_null
    //   10: astore 14
    //   12: aconst_null
    //   13: astore 13
    //   15: iconst_0
    //   16: istore_3
    //   17: ldc 77
    //   19: ldc 79
    //   21: invokestatic 85	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   24: pop
    //   25: aload_0
    //   26: invokespecial 87	com/android/mms/service/MmsRequest:ensureMmsConfigLoaded	()Z
    //   29: ifne +55 -> 84
    //   32: ldc 77
    //   34: ldc 89
    //   36: invokestatic 92	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   39: pop
    //   40: bipush 7
    //   42: istore_3
    //   43: iload_3
    //   44: iconst_m1
    //   45: if_icmpeq +28 -> 73
    //   48: ldc 77
    //   50: new 94	java/lang/StringBuilder
    //   53: dup
    //   54: invokespecial 95	java/lang/StringBuilder:<init>	()V
    //   57: ldc 97
    //   59: invokevirtual 101	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   62: iload_3
    //   63: invokevirtual 104	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   66: invokevirtual 108	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   69: invokestatic 85	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   72: pop
    //   73: aload_0
    //   74: aload_1
    //   75: iload_3
    //   76: aload 13
    //   78: iload 7
    //   80: invokevirtual 112	com/android/mms/service/MmsRequest:processResult	(Landroid/content/Context;I[BI)V
    //   83: return
    //   84: aload_0
    //   85: invokevirtual 115	com/android/mms/service/MmsRequest:prepareForHttpRequest	()Z
    //   88: ifne +16 -> 104
    //   91: ldc 77
    //   93: ldc 117
    //   95: invokestatic 92	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   98: pop
    //   99: iconst_5
    //   100: istore_3
    //   101: goto -58 -> 43
    //   104: ldc2_w 118
    //   107: lstore 11
    //   109: iconst_0
    //   110: istore 9
    //   112: aload 14
    //   114: astore 13
    //   116: iload_3
    //   117: istore 5
    //   119: aload 13
    //   121: astore 14
    //   123: iload 9
    //   125: iconst_3
    //   126: if_icmpge +243 -> 369
    //   129: iload_3
    //   130: istore 5
    //   132: aload 13
    //   134: astore 15
    //   136: iload_3
    //   137: istore 4
    //   139: aload 13
    //   141: astore 16
    //   143: iload_3
    //   144: istore 7
    //   146: aload 13
    //   148: astore 17
    //   150: iload_3
    //   151: istore 8
    //   153: aload 13
    //   155: astore 18
    //   157: aload_2
    //   158: invokevirtual 124	com/android/mms/service/MmsNetworkManager:acquireNetwork	()V
    //   161: iload_3
    //   162: istore 5
    //   164: aload 13
    //   166: astore 15
    //   168: iload_3
    //   169: istore 4
    //   171: aload 13
    //   173: astore 16
    //   175: iload_3
    //   176: istore 7
    //   178: aload 13
    //   180: astore 17
    //   182: iload_3
    //   183: istore 8
    //   185: aload 13
    //   187: astore 18
    //   189: aload_2
    //   190: invokevirtual 127	com/android/mms/service/MmsNetworkManager:getApnName	()Ljava/lang/String;
    //   193: astore 19
    //   195: aload 13
    //   197: astore 14
    //   199: aload_1
    //   200: aload 19
    //   202: aload_0
    //   203: getfield 33	com/android/mms/service/MmsRequest:mSubId	I
    //   206: invokestatic 133	com/android/mms/service/ApnSettings:load	(Landroid/content/Context;Ljava/lang/String;I)Lcom/android/mms/service/ApnSettings;
    //   209: astore 15
    //   211: aload 13
    //   213: astore 14
    //   215: ldc 77
    //   217: new 94	java/lang/StringBuilder
    //   220: dup
    //   221: invokespecial 95	java/lang/StringBuilder:<init>	()V
    //   224: ldc -121
    //   226: invokevirtual 101	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   229: aload 15
    //   231: invokevirtual 136	com/android/mms/service/ApnSettings:toString	()Ljava/lang/String;
    //   234: invokevirtual 101	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   237: ldc -118
    //   239: invokevirtual 101	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   242: aload_0
    //   243: getfield 33	com/android/mms/service/MmsRequest:mSubId	I
    //   246: invokevirtual 104	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   249: invokevirtual 108	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   252: invokestatic 85	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   255: pop
    //   256: aload 13
    //   258: astore 14
    //   260: aload_0
    //   261: aload_1
    //   262: aload_2
    //   263: aload 15
    //   265: invokevirtual 140	com/android/mms/service/MmsRequest:doHttp	(Landroid/content/Context;Lcom/android/mms/service/MmsNetworkManager;Lcom/android/mms/service/ApnSettings;)[B
    //   268: astore 13
    //   270: aload 13
    //   272: astore 14
    //   274: aload_0
    //   275: aload 13
    //   277: invokevirtual 142	com/android/mms/service/MmsRequest:checkResponse	([B)I
    //   280: istore 10
    //   282: aload 13
    //   284: astore 14
    //   286: ldc 77
    //   288: new 94	java/lang/StringBuilder
    //   291: dup
    //   292: invokespecial 95	java/lang/StringBuilder:<init>	()V
    //   295: ldc -112
    //   297: invokevirtual 101	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   300: iload 10
    //   302: invokevirtual 104	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   305: ldc -118
    //   307: invokevirtual 101	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   310: aload_0
    //   311: getfield 33	com/android/mms/service/MmsRequest:mSubId	I
    //   314: invokevirtual 104	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   317: invokevirtual 108	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   320: invokestatic 85	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   323: pop
    //   324: iconst_1
    //   325: istore 5
    //   327: iconst_1
    //   328: istore 4
    //   330: iconst_1
    //   331: istore 7
    //   333: iconst_1
    //   334: istore 8
    //   336: iconst_1
    //   337: istore_3
    //   338: aload 13
    //   340: astore 15
    //   342: aload 13
    //   344: astore 16
    //   346: aload 13
    //   348: astore 17
    //   350: aload 13
    //   352: astore 18
    //   354: aload_2
    //   355: invokevirtual 147	com/android/mms/service/MmsNetworkManager:releaseNetwork	()V
    //   358: iload 10
    //   360: istore 4
    //   362: aload 13
    //   364: astore 14
    //   366: iload_3
    //   367: istore 5
    //   369: iload 6
    //   371: istore 7
    //   373: aload 14
    //   375: astore 13
    //   377: iload 4
    //   379: istore_3
    //   380: iload 5
    //   382: ifeq -339 -> 43
    //   385: iload 6
    //   387: istore 7
    //   389: aload 14
    //   391: astore 13
    //   393: iload 4
    //   395: istore_3
    //   396: aload_2
    //   397: invokevirtual 150	com/android/mms/service/MmsNetworkManager:isNetworkReleased	()Z
    //   400: ifeq -357 -> 43
    //   403: ldc 77
    //   405: ldc -104
    //   407: invokestatic 85	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   410: pop
    //   411: ldc2_w 153
    //   414: iconst_0
    //   415: invokestatic 160	java/lang/Thread:sleep	(JI)V
    //   418: iload 6
    //   420: istore 7
    //   422: aload 14
    //   424: astore 13
    //   426: iload 4
    //   428: istore_3
    //   429: goto -386 -> 43
    //   432: astore_2
    //   433: iload 6
    //   435: istore 7
    //   437: aload 14
    //   439: astore 13
    //   441: iload 4
    //   443: istore_3
    //   444: goto -401 -> 43
    //   447: astore 15
    //   449: aload 19
    //   451: ifnonnull +97 -> 548
    //   454: aload 13
    //   456: astore 14
    //   458: aload 15
    //   460: athrow
    //   461: astore 13
    //   463: iload_3
    //   464: istore 5
    //   466: aload 14
    //   468: astore 15
    //   470: iload_3
    //   471: istore 4
    //   473: aload 14
    //   475: astore 16
    //   477: iload_3
    //   478: istore 7
    //   480: aload 14
    //   482: astore 17
    //   484: iload_3
    //   485: istore 8
    //   487: aload 14
    //   489: astore 18
    //   491: aload_2
    //   492: invokevirtual 147	com/android/mms/service/MmsNetworkManager:releaseNetwork	()V
    //   495: iload_3
    //   496: istore 5
    //   498: aload 14
    //   500: astore 15
    //   502: iload_3
    //   503: istore 4
    //   505: aload 14
    //   507: astore 16
    //   509: iload_3
    //   510: istore 7
    //   512: aload 14
    //   514: astore 17
    //   516: iload_3
    //   517: istore 8
    //   519: aload 14
    //   521: astore 18
    //   523: aload 13
    //   525: athrow
    //   526: astore 13
    //   528: ldc 77
    //   530: ldc -94
    //   532: aload 13
    //   534: invokestatic 165	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   537: pop
    //   538: iconst_2
    //   539: istore 4
    //   541: aload 15
    //   543: astore 14
    //   545: goto -176 -> 369
    //   548: aload 13
    //   550: astore 14
    //   552: ldc 77
    //   554: new 94	java/lang/StringBuilder
    //   557: dup
    //   558: invokespecial 95	java/lang/StringBuilder:<init>	()V
    //   561: ldc -89
    //   563: invokevirtual 101	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   566: aload 19
    //   568: invokevirtual 101	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   571: ldc -87
    //   573: invokevirtual 101	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   576: invokevirtual 108	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   579: invokestatic 172	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   582: pop
    //   583: aload 13
    //   585: astore 14
    //   587: aload_1
    //   588: aconst_null
    //   589: aload_0
    //   590: getfield 33	com/android/mms/service/MmsRequest:mSubId	I
    //   593: invokestatic 133	com/android/mms/service/ApnSettings:load	(Landroid/content/Context;Ljava/lang/String;I)Lcom/android/mms/service/ApnSettings;
    //   596: astore 15
    //   598: goto -387 -> 211
    //   601: astore 13
    //   603: ldc 77
    //   605: ldc -82
    //   607: aload 13
    //   609: invokestatic 165	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   612: pop
    //   613: iconst_3
    //   614: istore 5
    //   616: aload 16
    //   618: astore 13
    //   620: iload 4
    //   622: istore_3
    //   623: ldc 77
    //   625: new 94	java/lang/StringBuilder
    //   628: dup
    //   629: invokespecial 95	java/lang/StringBuilder:<init>	()V
    //   632: ldc -80
    //   634: invokevirtual 101	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   637: ldc2_w 177
    //   640: lload 11
    //   642: lmul
    //   643: invokevirtual 181	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   646: invokevirtual 108	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   649: invokestatic 85	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   652: pop
    //   653: ldc2_w 177
    //   656: lload 11
    //   658: lmul
    //   659: iconst_0
    //   660: invokestatic 160	java/lang/Thread:sleep	(JI)V
    //   663: lload 11
    //   665: iconst_1
    //   666: lshl
    //   667: lstore 11
    //   669: iload 9
    //   671: iconst_1
    //   672: iadd
    //   673: istore 9
    //   675: iload 5
    //   677: istore 4
    //   679: goto -563 -> 116
    //   682: astore 13
    //   684: ldc 77
    //   686: ldc -73
    //   688: aload 13
    //   690: invokestatic 165	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   693: pop
    //   694: aload_2
    //   695: invokevirtual 186	com/android/mms/service/MmsNetworkManager:releaseNetworkImmediately	()V
    //   698: iconst_4
    //   699: istore 5
    //   701: aload 13
    //   703: invokevirtual 190	com/android/mms/service/exception/MmsHttpException:getStatusCode	()I
    //   706: istore 6
    //   708: iload 7
    //   710: istore_3
    //   711: aload 17
    //   713: astore 13
    //   715: goto -92 -> 623
    //   718: astore 13
    //   720: ldc 77
    //   722: ldc -64
    //   724: aload 13
    //   726: invokestatic 165	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   729: pop
    //   730: iconst_1
    //   731: istore 4
    //   733: iload 8
    //   735: istore 5
    //   737: aload 18
    //   739: astore 14
    //   741: goto -372 -> 369
    //   744: astore 14
    //   746: goto -83 -> 663
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	749	0	this	MmsRequest
    //   0	749	1	paramContext	Context
    //   0	749	2	paramMmsNetworkManager	MmsNetworkManager
    //   16	695	3	i	int
    //   1	731	4	j	int
    //   117	619	5	k	int
    //   4	703	6	m	int
    //   7	702	7	n	int
    //   151	583	8	i1	int
    //   110	564	9	i2	int
    //   280	79	10	i3	int
    //   107	561	11	l	long
    //   13	442	13	localObject1	Object
    //   461	63	13	localObject2	Object
    //   526	58	13	localApnException1	com.android.mms.service.exception.ApnException
    //   601	7	13	localMmsNetworkException	com.android.mms.service.exception.MmsNetworkException
    //   618	1	13	localObject3	Object
    //   682	20	13	localMmsHttpException	MmsHttpException
    //   713	1	13	localObject4	Object
    //   718	7	13	localException	Exception
    //   10	730	14	localObject5	Object
    //   744	1	14	localInterruptedException	InterruptedException
    //   134	207	15	localObject6	Object
    //   447	12	15	localApnException2	com.android.mms.service.exception.ApnException
    //   468	129	15	localObject7	Object
    //   141	476	16	localObject8	Object
    //   148	564	17	localObject9	Object
    //   155	583	18	localObject10	Object
    //   193	374	19	str	String
    // Exception table:
    //   from	to	target	type
    //   403	418	432	java/lang/InterruptedException
    //   199	211	447	com/android/mms/service/exception/ApnException
    //   199	211	461	finally
    //   215	256	461	finally
    //   260	270	461	finally
    //   274	282	461	finally
    //   286	324	461	finally
    //   458	461	461	finally
    //   552	583	461	finally
    //   587	598	461	finally
    //   157	161	526	com/android/mms/service/exception/ApnException
    //   189	195	526	com/android/mms/service/exception/ApnException
    //   354	358	526	com/android/mms/service/exception/ApnException
    //   491	495	526	com/android/mms/service/exception/ApnException
    //   523	526	526	com/android/mms/service/exception/ApnException
    //   157	161	601	com/android/mms/service/exception/MmsNetworkException
    //   189	195	601	com/android/mms/service/exception/MmsNetworkException
    //   354	358	601	com/android/mms/service/exception/MmsNetworkException
    //   491	495	601	com/android/mms/service/exception/MmsNetworkException
    //   523	526	601	com/android/mms/service/exception/MmsNetworkException
    //   157	161	682	com/android/mms/service/exception/MmsHttpException
    //   189	195	682	com/android/mms/service/exception/MmsHttpException
    //   354	358	682	com/android/mms/service/exception/MmsHttpException
    //   491	495	682	com/android/mms/service/exception/MmsHttpException
    //   523	526	682	com/android/mms/service/exception/MmsHttpException
    //   157	161	718	java/lang/Exception
    //   189	195	718	java/lang/Exception
    //   354	358	718	java/lang/Exception
    //   491	495	718	java/lang/Exception
    //   523	526	718	java/lang/Exception
    //   623	663	744	java/lang/InterruptedException
  }
  
  protected abstract PendingIntent getPendingIntent();
  
  public int getSubId()
  {
    return mSubId;
  }
  
  protected boolean maybeFallbackToRegularDelivery(int paramInt)
  {
    if ((paramInt == 1) || (paramInt == 1))
    {
      Log.d("MmsService", "Sending/downloading MMS by IP failed.");
      mRequestManager.addSimRequest(this);
      return true;
    }
    return false;
  }
  
  protected abstract Uri persistIfRequired(Context paramContext, int paramInt, byte[] paramArrayOfByte);
  
  protected abstract boolean prepareForHttpRequest();
  
  /* Error */
  public void processResult(Context paramContext, int paramInt1, byte[] paramArrayOfByte, int paramInt2)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 209	com/android/mms/service/MmsRequest:getPendingIntent	()Landroid/app/PendingIntent;
    //   4: astore 9
    //   6: aload 9
    //   8: ifnull +152 -> 160
    //   11: aload 9
    //   13: invokevirtual 215	android/app/PendingIntent:getIntent	()Landroid/content/Intent;
    //   16: ldc -39
    //   18: invokevirtual 223	android/content/Intent:getStringExtra	(Ljava/lang/String;)Ljava/lang/String;
    //   21: invokestatic 229	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   24: astore 8
    //   26: ldc 77
    //   28: new 94	java/lang/StringBuilder
    //   31: dup
    //   32: invokespecial 95	java/lang/StringBuilder:<init>	()V
    //   35: ldc -25
    //   37: invokevirtual 101	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   40: aload 8
    //   42: invokevirtual 232	android/net/Uri:toString	()Ljava/lang/String;
    //   45: invokevirtual 101	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   48: invokevirtual 108	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   51: invokestatic 85	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   54: pop
    //   55: aconst_null
    //   56: astore 7
    //   58: aconst_null
    //   59: astore 6
    //   61: aload_1
    //   62: aload_1
    //   63: invokevirtual 238	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   66: aload 8
    //   68: iconst_3
    //   69: anewarray 240	java/lang/String
    //   72: dup
    //   73: iconst_0
    //   74: ldc -14
    //   76: aastore
    //   77: dup
    //   78: iconst_1
    //   79: ldc -12
    //   81: aastore
    //   82: dup
    //   83: iconst_2
    //   84: ldc -10
    //   86: aastore
    //   87: aconst_null
    //   88: aconst_null
    //   89: aconst_null
    //   90: invokestatic 252	com/meizu/android/mms/util/MzSqliteWrapper:query	(Landroid/content/Context;Landroid/content/ContentResolver;Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   93: astore 8
    //   95: aload 8
    //   97: ifnull +21 -> 118
    //   100: aload 8
    //   102: astore 6
    //   104: aload 8
    //   106: astore 7
    //   108: aload 8
    //   110: invokeinterface 257 1 0
    //   115: ifne +33 -> 148
    //   118: aload 8
    //   120: astore 6
    //   122: aload 8
    //   124: astore 7
    //   126: ldc 77
    //   128: ldc_w 259
    //   131: invokestatic 92	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   134: pop
    //   135: aload 8
    //   137: ifnull +10 -> 147
    //   140: aload 8
    //   142: invokeinterface 262 1 0
    //   147: return
    //   148: aload 8
    //   150: ifnull +10 -> 160
    //   153: aload 8
    //   155: invokeinterface 262 1 0
    //   160: aload_0
    //   161: aload_1
    //   162: iload_2
    //   163: aload_3
    //   164: invokevirtual 264	com/android/mms/service/MmsRequest:persistIfRequired	(Landroid/content/Context;I[B)Landroid/net/Uri;
    //   167: astore 6
    //   169: ldc 77
    //   171: ldc_w 266
    //   174: invokestatic 85	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   177: pop
    //   178: aload 9
    //   180: ifnull +126 -> 306
    //   183: iconst_1
    //   184: istore 5
    //   186: new 219	android/content/Intent
    //   189: dup
    //   190: invokespecial 267	android/content/Intent:<init>	()V
    //   193: astore 7
    //   195: aload_3
    //   196: ifnull +12 -> 208
    //   199: aload_0
    //   200: aload 7
    //   202: aload_3
    //   203: invokevirtual 271	com/android/mms/service/MmsRequest:transferResponse	(Landroid/content/Intent;[B)Z
    //   206: istore 5
    //   208: aload 6
    //   210: ifnull +44 -> 254
    //   213: ldc 77
    //   215: new 94	java/lang/StringBuilder
    //   218: dup
    //   219: invokespecial 95	java/lang/StringBuilder:<init>	()V
    //   222: ldc_w 273
    //   225: invokevirtual 101	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   228: aload 6
    //   230: invokevirtual 276	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   233: invokevirtual 108	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   236: invokestatic 85	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   239: pop
    //   240: aload 7
    //   242: ldc_w 278
    //   245: aload 6
    //   247: invokevirtual 232	android/net/Uri:toString	()Ljava/lang/String;
    //   250: invokevirtual 282	android/content/Intent:putExtra	(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
    //   253: pop
    //   254: aload_0
    //   255: aload 7
    //   257: invokevirtual 286	com/android/mms/service/MmsRequest:putOriginalUrl	(Landroid/content/Intent;)V
    //   260: iload_2
    //   261: iconst_4
    //   262: if_icmpne +19 -> 281
    //   265: iload 4
    //   267: ifeq +14 -> 281
    //   270: aload 7
    //   272: ldc_w 288
    //   275: iload 4
    //   277: invokevirtual 291	android/content/Intent:putExtra	(Ljava/lang/String;I)Landroid/content/Intent;
    //   280: pop
    //   281: iload 5
    //   283: ifne +5 -> 288
    //   286: iconst_5
    //   287: istore_2
    //   288: ldc 77
    //   290: ldc_w 293
    //   293: invokestatic 85	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   296: pop
    //   297: aload 9
    //   299: aload_1
    //   300: iload_2
    //   301: aload 7
    //   303: invokevirtual 297	android/app/PendingIntent:send	(Landroid/content/Context;ILandroid/content/Intent;)V
    //   306: aload_0
    //   307: aload_1
    //   308: invokevirtual 301	com/android/mms/service/MmsRequest:revokeUriPermission	(Landroid/content/Context;)V
    //   311: return
    //   312: astore 8
    //   314: aload 6
    //   316: astore 7
    //   318: ldc 77
    //   320: ldc_w 303
    //   323: aload 8
    //   325: invokestatic 165	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   328: pop
    //   329: aload 6
    //   331: ifnull -171 -> 160
    //   334: aload 6
    //   336: invokeinterface 262 1 0
    //   341: goto -181 -> 160
    //   344: astore_1
    //   345: aload 7
    //   347: ifnull +10 -> 357
    //   350: aload 7
    //   352: invokeinterface 262 1 0
    //   357: aload_1
    //   358: athrow
    //   359: astore_3
    //   360: ldc 77
    //   362: ldc_w 305
    //   365: aload_3
    //   366: invokestatic 165	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   369: pop
    //   370: goto -64 -> 306
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	373	0	this	MmsRequest
    //   0	373	1	paramContext	Context
    //   0	373	2	paramInt1	int
    //   0	373	3	paramArrayOfByte	byte[]
    //   0	373	4	paramInt2	int
    //   184	98	5	bool	boolean
    //   59	276	6	localObject1	Object
    //   56	295	7	localObject2	Object
    //   24	130	8	localObject3	Object
    //   312	12	8	localException	Exception
    //   4	294	9	localPendingIntent	PendingIntent
    // Exception table:
    //   from	to	target	type
    //   61	95	312	java/lang/Exception
    //   108	118	312	java/lang/Exception
    //   126	135	312	java/lang/Exception
    //   61	95	344	finally
    //   108	118	344	finally
    //   126	135	344	finally
    //   318	329	344	finally
    //   288	306	359	android/app/PendingIntent$CanceledException
  }
  
  protected abstract void putOriginalUrl(Intent paramIntent);
  
  protected abstract void revokeUriPermission(Context paramContext);
  
  protected abstract boolean transferResponse(Intent paramIntent, byte[] paramArrayOfByte);
  
  protected abstract class CarrierMmsActionCallback
    extends ICarrierMessagingCallback.Stub
  {
    protected CarrierMmsActionCallback() {}
    
    public void onFilterComplete(boolean paramBoolean)
    {
      Log.e("MmsService", "Unexpected onFilterComplete call with result: " + paramBoolean);
    }
    
    public void onSendMultipartSmsComplete(int paramInt, int[] paramArrayOfInt)
    {
      Log.e("MmsService", "Unexpected onSendMultipartSmsComplete call with result: " + paramInt);
    }
    
    public void onSendSmsComplete(int paramInt1, int paramInt2)
    {
      Log.e("MmsService", "Unexpected onSendSmsComplete call with result: " + paramInt1);
    }
  }
  
  public static abstract interface RequestManager
  {
    public abstract void addSimRequest(MmsRequest paramMmsRequest);
    
    public abstract boolean getAutoPersistingPref();
    
    public abstract byte[] readPduFromContentUri(Uri paramUri, int paramInt);
    
    public abstract boolean writePduToContentUri(Uri paramUri, byte[] paramArrayOfByte);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.service.MmsRequest
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */