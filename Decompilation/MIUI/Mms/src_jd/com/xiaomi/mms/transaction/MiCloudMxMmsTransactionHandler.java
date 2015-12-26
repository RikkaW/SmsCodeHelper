package com.xiaomi.mms.transaction;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Bundle;
import android.text.TextUtils;
import com.android.mms.data.Contact;
import com.android.mms.transaction.MessagingNotification;
import com.android.mms.util.DownloadManager;
import com.android.mms.util.MSimUtils;
import com.google.android.collect.Lists;
import com.google.android.mms.MmsException;
import com.google.android.mms.pdu.EncodedStringValue;
import com.google.android.mms.pdu.MiuiPduPersister;
import com.google.android.mms.pdu.SendReq;
import com.google.android.mms.util.SqliteWrapper;
import com.xiaomi.accountsdk.account.data.ExtendedAuthToken;
import com.xiaomi.accountsdk.activate.ActivateManager;
import com.xiaomi.accountsdk.activate.ActivateManager.ActivateManagerFuture;
import com.xiaomi.micloudsdk.micloudrichmedia.UploadData;
import com.xiaomi.micloudsdk.micloudrichmedia.UploadEntity;
import com.xiaomi.micloudsdk.micloudrichmedia.UploadResult;
import com.xiaomi.mms.data.MxIdCache;
import com.xiaomi.mms.data.MxIdCache.MxIdCacheItem;
import com.xiaomi.mms.net.exception.MxLogicException;
import com.xiaomi.mms.utils.MiCloudMmsCodec;
import com.xiaomi.mms.utils.MxConfigCompat;
import com.xiaomi.mms.utils.MxMessagePduHelper;
import com.xiaomi.mms.utils.MxMessageUtils;
import com.xiaomi.mms.utils.MxRequestEnv;
import com.xiaomi.mms.utils.PrefUtils;
import com.xiaomi.mms.utils.logger.MyLog;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MiCloudMxMmsTransactionHandler
  implements MxMmsTransactionHandler
{
  private static final String[] MX_MMS_PROJECT = { "ct_t", "m_size", "mx_id", "mx_status", "sim_id" };
  private MiCloudMediaManagerCallback mCallback;
  private Context mContext;
  private MiCloudRichMediaManagerFactory mFactory;
  private boolean mIsSendByMxV2;
  
  public MiCloudMxMmsTransactionHandler(Context paramContext, MiCloudMediaManagerCallback paramMiCloudMediaManagerCallback, MiCloudRichMediaManagerFactory paramMiCloudRichMediaManagerFactory)
  {
    mContext = paramContext;
    mCallback = paramMiCloudMediaManagerCallback;
    mFactory = paramMiCloudRichMediaManagerFactory;
  }
  
  public static String getAddr(Uri paramUri, Context paramContext)
  {
    MiuiPduPersister localMiuiPduPersister = MiuiPduPersister.getPduPersister(paramContext);
    paramContext = null;
    try
    {
      paramUri = (SendReq)localMiuiPduPersister.load(paramUri);
      if (paramUri == null) {
        return null;
      }
      return Contact.get(paramUri.getTo()[0].getString()).getMxPhoneNumber();
    }
    catch (MmsException paramUri)
    {
      for (;;)
      {
        paramUri = paramContext;
      }
    }
  }
  
  private ExtendedAuthToken getFileToken(String paramString1, String paramString2)
  {
    if ((TextUtils.isEmpty(paramString1)) || (TextUtils.isEmpty(paramString2))) {
      paramString2 = null;
    }
    boolean bool;
    ExtendedAuthToken localExtendedAuthToken;
    do
    {
      return paramString2;
      int i = PushSession.getInstance(mContext).getSimIdByMid(paramString1);
      bool = MxConfigCompat.isMxV2Richmedia(paramString2);
      if (!bool) {
        break;
      }
      paramString2 = "pref_mx2_file_token";
      localExtendedAuthToken = ExtendedAuthToken.parse(PrefUtils.getString(mContext, paramString2 + i));
      paramString2 = localExtendedAuthToken;
    } while (localExtendedAuthToken != null);
    if (bool) {}
    for (paramString2 = MxConfigCompat.getMxV2Sid(mContext, paramString1);; paramString2 = MxConfigCompat.getMxV1Sid(mContext, paramString1))
    {
      return getFileTokenFromServer(paramString1, paramString2);
      paramString2 = "pref_file_token";
      break;
    }
  }
  
  private ExtendedAuthToken getFileTokenFromServer(String paramString1, String paramString2)
  {
    int i = PushSession.getInstance(mContext).getSimIdByMid(paramString1);
    if ((i == -1) || (TextUtils.isEmpty(paramString1))) {}
    do
    {
      return null;
      String str2 = null;
      String str1 = null;
      paramString1 = str2;
      try
      {
        Bundle localBundle = (Bundle)ActivateManager.get(mContext).getSimAuthToken(i, paramString2).getResult();
        paramString1 = str2;
        paramString2 = localBundle.getString("user_token");
        paramString1 = paramString2;
        str2 = localBundle.getString("user_security");
        paramString1 = str2;
        str1 = paramString2;
        paramString2 = paramString1;
      }
      catch (Exception paramString2)
      {
        for (;;)
        {
          MyLog.e("MiCloudMxMmsTransactionHandler", "failed to get file upload token from server", paramString2);
          paramString2 = str1;
          str1 = paramString1;
        }
      }
    } while ((str1 == null) || (paramString2 == null));
    return ExtendedAuthToken.build(str1, paramString2);
  }
  
  private String getFileTokenPref(String paramString)
  {
    if (MxConfigCompat.isMxV2Richmedia(paramString)) {
      return "pref_mx2_file_token";
    }
    return "pref_file_token";
  }
  
  private ExtendedAuthToken getOtherIDCFileToken(String paramString1, String paramString2)
  {
    if ((TextUtils.isEmpty(paramString1)) || (TextUtils.isEmpty(paramString2))) {
      paramString2 = null;
    }
    boolean bool;
    ExtendedAuthToken localExtendedAuthToken;
    do
    {
      return paramString2;
      int i = PushSession.getInstance(mContext).getSimIdByMid(paramString1);
      bool = MxConfigCompat.isMxV2Richmedia(paramString2);
      if (!bool) {
        break;
      }
      paramString2 = "pref_mx2_file_token";
      localExtendedAuthToken = ExtendedAuthToken.parse(PrefUtils.getString(mContext, paramString2 + i + "_idc"));
      paramString2 = localExtendedAuthToken;
    } while (localExtendedAuthToken != null);
    if (bool) {}
    for (paramString2 = MxConfigCompat.getOtherIDCMxV2Sid(mContext, paramString1);; paramString2 = MxConfigCompat.getOtherIDCMxV1Sid(mContext, paramString1))
    {
      return getFileTokenFromServer(paramString1, paramString2);
      paramString2 = "pref_file_token";
      break;
    }
  }
  
  /* Error */
  private byte[] tryDownload(Uri paramUri, String paramString1, String paramString2)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 10
    //   3: iconst_3
    //   4: istore 8
    //   6: iconst_0
    //   7: istore 7
    //   9: iconst_0
    //   10: istore 4
    //   12: aconst_null
    //   13: astore 13
    //   15: iconst_0
    //   16: istore 5
    //   18: aconst_null
    //   19: astore 14
    //   21: aconst_null
    //   22: astore 11
    //   24: aload 14
    //   26: astore 12
    //   28: aload_3
    //   29: invokestatic 105	com/xiaomi/mms/utils/MxConfigCompat:isMxV2Richmedia	(Ljava/lang/String;)Z
    //   32: ifeq +17 -> 49
    //   35: aload 14
    //   37: astore 12
    //   39: aload_0
    //   40: getfield 42	com/xiaomi/mms/transaction/MiCloudMxMmsTransactionHandler:mContext	Landroid/content/Context;
    //   43: aload_2
    //   44: invokestatic 204	com/xiaomi/mms/utils/MxConfigCompat:getMxV2RichMediaUrl	(Landroid/content/Context;Ljava/lang/String;)Ljava/lang/String;
    //   47: astore 11
    //   49: aload 11
    //   51: astore 12
    //   53: aload_0
    //   54: aload_2
    //   55: aload_3
    //   56: invokespecial 206	com/xiaomi/mms/transaction/MiCloudMxMmsTransactionHandler:getFileToken	(Ljava/lang/String;Ljava/lang/String;)Lcom/xiaomi/accountsdk/account/data/ExtendedAuthToken;
    //   59: astore 14
    //   61: aload 14
    //   63: astore 12
    //   65: iload 5
    //   67: ifeq +60 -> 127
    //   70: aload_0
    //   71: getfield 42	com/xiaomi/mms/transaction/MiCloudMxMmsTransactionHandler:mContext	Landroid/content/Context;
    //   74: aload_1
    //   75: sipush 195
    //   78: invokestatic 212	com/xiaomi/mms/utils/MxMessagePduHelper:setResponseStatus	(Landroid/content/Context;Landroid/net/Uri;I)V
    //   81: iload 4
    //   83: istore 7
    //   85: aload 10
    //   87: astore 11
    //   89: iload 7
    //   91: ifeq +8 -> 99
    //   94: iload 8
    //   96: ifgt +619 -> 715
    //   99: aload 11
    //   101: areturn
    //   102: astore 11
    //   104: ldc -83
    //   106: ldc -42
    //   108: aload 11
    //   110: invokestatic 181	com/xiaomi/mms/utils/logger/MyLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   113: iconst_1
    //   114: istore 5
    //   116: aload 12
    //   118: astore 11
    //   120: aload 13
    //   122: astore 12
    //   124: goto -59 -> 65
    //   127: aload 12
    //   129: ifnonnull +32 -> 161
    //   132: ldc -83
    //   134: ldc -40
    //   136: invokestatic 220	com/xiaomi/mms/utils/logger/MyLog:w	(Ljava/lang/String;Ljava/lang/String;)V
    //   139: aload_0
    //   140: getfield 42	com/xiaomi/mms/transaction/MiCloudMxMmsTransactionHandler:mContext	Landroid/content/Context;
    //   143: aload_1
    //   144: sipush 225
    //   147: invokestatic 212	com/xiaomi/mms/utils/MxMessagePduHelper:setResponseStatus	(Landroid/content/Context;Landroid/net/Uri;I)V
    //   150: aload 10
    //   152: astore 11
    //   154: iload 4
    //   156: istore 7
    //   158: goto -69 -> 89
    //   161: iconst_0
    //   162: istore 9
    //   164: aload_0
    //   165: getfield 46	com/xiaomi/mms/transaction/MiCloudMxMmsTransactionHandler:mFactory	Lcom/xiaomi/mms/transaction/MiCloudRichMediaManagerFactory;
    //   168: aload_0
    //   169: getfield 42	com/xiaomi/mms/transaction/MiCloudMxMmsTransactionHandler:mContext	Landroid/content/Context;
    //   172: aload_2
    //   173: aload 12
    //   175: aload 11
    //   177: invokeinterface 226 5 0
    //   182: astore 11
    //   184: aload 10
    //   186: astore 12
    //   188: aload 10
    //   190: astore 13
    //   192: aload 10
    //   194: astore 14
    //   196: aload 10
    //   198: astore 15
    //   200: aload_3
    //   201: invokestatic 105	com/xiaomi/mms/utils/MxConfigCompat:isMxV2Richmedia	(Ljava/lang/String;)Z
    //   204: ifeq +205 -> 409
    //   207: aload 10
    //   209: astore 12
    //   211: aload 10
    //   213: astore 13
    //   215: aload 10
    //   217: astore 14
    //   219: aload 10
    //   221: astore 15
    //   223: aload_0
    //   224: getfield 42	com/xiaomi/mms/transaction/MiCloudMxMmsTransactionHandler:mContext	Landroid/content/Context;
    //   227: aload 11
    //   229: aload_3
    //   230: invokestatic 232	com/xiaomi/mms/utils/StorageServerHelper:downloadPduViaMxV2	(Landroid/content/Context;Lcom/xiaomi/micloudsdk/micloudrichmedia/MiCloudRichMediaManager;Ljava/lang/String;)[B
    //   233: astore 11
    //   235: aload 11
    //   237: astore 10
    //   239: iload 7
    //   241: istore 6
    //   243: iload 9
    //   245: istore 4
    //   247: aload 11
    //   249: ifnonnull +55 -> 304
    //   252: aload 11
    //   254: astore 12
    //   256: aload 11
    //   258: astore 13
    //   260: aload 11
    //   262: astore 14
    //   264: aload 11
    //   266: astore 15
    //   268: ldc -83
    //   270: ldc -22
    //   272: invokestatic 220	com/xiaomi/mms/utils/logger/MyLog:w	(Ljava/lang/String;Ljava/lang/String;)V
    //   275: aload 11
    //   277: astore 12
    //   279: aload 11
    //   281: astore 13
    //   283: aload 11
    //   285: astore 14
    //   287: aload 11
    //   289: astore 15
    //   291: iconst_0
    //   292: newarray <illegal type>
    //   294: astore 10
    //   296: iload 9
    //   298: istore 4
    //   300: iload 7
    //   302: istore 6
    //   304: aload 10
    //   306: astore 11
    //   308: iload 6
    //   310: istore 7
    //   312: iload 4
    //   314: ifeq -225 -> 89
    //   317: aload 10
    //   319: astore 11
    //   321: iload 6
    //   323: istore 7
    //   325: aload_3
    //   326: invokestatic 105	com/xiaomi/mms/utils/MxConfigCompat:isMxV2Richmedia	(Ljava/lang/String;)Z
    //   329: ifeq -240 -> 89
    //   332: aconst_null
    //   333: astore 14
    //   335: aconst_null
    //   336: astore 11
    //   338: aconst_null
    //   339: astore 13
    //   341: aload 14
    //   343: astore 12
    //   345: aload_3
    //   346: invokestatic 105	com/xiaomi/mms/utils/MxConfigCompat:isMxV2Richmedia	(Ljava/lang/String;)Z
    //   349: ifeq +17 -> 366
    //   352: aload 14
    //   354: astore 12
    //   356: aload_0
    //   357: getfield 42	com/xiaomi/mms/transaction/MiCloudMxMmsTransactionHandler:mContext	Landroid/content/Context;
    //   360: aload_2
    //   361: invokestatic 237	com/xiaomi/mms/utils/MxConfigCompat:getOtherIDCMxV2RichMediaUrl	(Landroid/content/Context;Ljava/lang/String;)Ljava/lang/String;
    //   364: astore 11
    //   366: aload 11
    //   368: astore 12
    //   370: aload_0
    //   371: aload_2
    //   372: aload_3
    //   373: invokespecial 239	com/xiaomi/mms/transaction/MiCloudMxMmsTransactionHandler:getOtherIDCFileToken	(Ljava/lang/String;Ljava/lang/String;)Lcom/xiaomi/accountsdk/account/data/ExtendedAuthToken;
    //   376: astore 14
    //   378: aload 14
    //   380: astore 12
    //   382: iload 5
    //   384: ifeq +206 -> 590
    //   387: aload_0
    //   388: getfield 42	com/xiaomi/mms/transaction/MiCloudMxMmsTransactionHandler:mContext	Landroid/content/Context;
    //   391: aload_1
    //   392: sipush 195
    //   395: invokestatic 212	com/xiaomi/mms/utils/MxMessagePduHelper:setResponseStatus	(Landroid/content/Context;Landroid/net/Uri;I)V
    //   398: aload 10
    //   400: astore 11
    //   402: iload 6
    //   404: istore 7
    //   406: goto -317 -> 89
    //   409: aload 10
    //   411: astore 12
    //   413: aload 10
    //   415: astore 13
    //   417: aload 10
    //   419: astore 14
    //   421: aload 10
    //   423: astore 15
    //   425: aload 11
    //   427: aload_3
    //   428: invokestatic 243	com/xiaomi/mms/utils/StorageServerHelper:downloadPdu	(Lcom/xiaomi/micloudsdk/micloudrichmedia/MiCloudRichMediaManager;Ljava/lang/String;)[B
    //   431: astore 11
    //   433: goto -198 -> 235
    //   436: astore 10
    //   438: iconst_1
    //   439: istore 4
    //   441: ldc -83
    //   443: ldc -11
    //   445: aload 10
    //   447: invokestatic 181	com/xiaomi/mms/utils/logger/MyLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   450: aload_0
    //   451: getfield 42	com/xiaomi/mms/transaction/MiCloudMxMmsTransactionHandler:mContext	Landroid/content/Context;
    //   454: aload_1
    //   455: sipush 195
    //   458: invokestatic 212	com/xiaomi/mms/utils/MxMessagePduHelper:setResponseStatus	(Landroid/content/Context;Landroid/net/Uri;I)V
    //   461: aload 12
    //   463: astore 10
    //   465: iload 7
    //   467: istore 6
    //   469: goto -165 -> 304
    //   472: astore 10
    //   474: iconst_1
    //   475: istore 4
    //   477: ldc -83
    //   479: ldc -9
    //   481: aload 10
    //   483: invokestatic 181	com/xiaomi/mms/utils/logger/MyLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   486: aload_0
    //   487: getfield 42	com/xiaomi/mms/transaction/MiCloudMxMmsTransactionHandler:mContext	Landroid/content/Context;
    //   490: aload_1
    //   491: sipush 195
    //   494: invokestatic 212	com/xiaomi/mms/utils/MxMessagePduHelper:setResponseStatus	(Landroid/content/Context;Landroid/net/Uri;I)V
    //   497: aload 13
    //   499: astore 10
    //   501: iload 7
    //   503: istore 6
    //   505: goto -201 -> 304
    //   508: astore 10
    //   510: ldc -83
    //   512: ldc -7
    //   514: aload 10
    //   516: invokestatic 181	com/xiaomi/mms/utils/logger/MyLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   519: iconst_1
    //   520: istore 6
    //   522: aload_0
    //   523: aload_2
    //   524: aload_3
    //   525: invokevirtual 252	com/xiaomi/mms/transaction/MiCloudMxMmsTransactionHandler:invalidFileToken	(Ljava/lang/String;Ljava/lang/String;)V
    //   528: aload 14
    //   530: astore 10
    //   532: iload 9
    //   534: istore 4
    //   536: goto -232 -> 304
    //   539: astore 10
    //   541: iconst_1
    //   542: istore 4
    //   544: ldc -83
    //   546: ldc -2
    //   548: aload 10
    //   550: invokestatic 181	com/xiaomi/mms/utils/logger/MyLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   553: aload 15
    //   555: astore 10
    //   557: iload 7
    //   559: istore 6
    //   561: goto -257 -> 304
    //   564: astore 11
    //   566: ldc -83
    //   568: ldc_w 256
    //   571: aload 11
    //   573: invokestatic 181	com/xiaomi/mms/utils/logger/MyLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   576: iconst_1
    //   577: istore 5
    //   579: aload 12
    //   581: astore 11
    //   583: aload 13
    //   585: astore 12
    //   587: goto -205 -> 382
    //   590: aload 12
    //   592: ifnonnull +33 -> 625
    //   595: ldc -83
    //   597: ldc_w 258
    //   600: invokestatic 220	com/xiaomi/mms/utils/logger/MyLog:w	(Ljava/lang/String;Ljava/lang/String;)V
    //   603: aload_0
    //   604: getfield 42	com/xiaomi/mms/transaction/MiCloudMxMmsTransactionHandler:mContext	Landroid/content/Context;
    //   607: aload_1
    //   608: sipush 225
    //   611: invokestatic 212	com/xiaomi/mms/utils/MxMessagePduHelper:setResponseStatus	(Landroid/content/Context;Landroid/net/Uri;I)V
    //   614: aload 10
    //   616: astore 11
    //   618: iload 6
    //   620: istore 7
    //   622: goto -533 -> 89
    //   625: aload_0
    //   626: getfield 46	com/xiaomi/mms/transaction/MiCloudMxMmsTransactionHandler:mFactory	Lcom/xiaomi/mms/transaction/MiCloudRichMediaManagerFactory;
    //   629: aload_0
    //   630: getfield 42	com/xiaomi/mms/transaction/MiCloudMxMmsTransactionHandler:mContext	Landroid/content/Context;
    //   633: aload_2
    //   634: aload 12
    //   636: aload 11
    //   638: invokeinterface 261 5 0
    //   643: astore 11
    //   645: aload_0
    //   646: getfield 42	com/xiaomi/mms/transaction/MiCloudMxMmsTransactionHandler:mContext	Landroid/content/Context;
    //   649: aload 11
    //   651: aload_3
    //   652: invokestatic 232	com/xiaomi/mms/utils/StorageServerHelper:downloadPduViaMxV2	(Landroid/content/Context;Lcom/xiaomi/micloudsdk/micloudrichmedia/MiCloudRichMediaManager;Ljava/lang/String;)[B
    //   655: astore 11
    //   657: iload 6
    //   659: istore 7
    //   661: goto -572 -> 89
    //   664: astore 11
    //   666: ldc -83
    //   668: ldc_w 263
    //   671: aload 11
    //   673: invokestatic 181	com/xiaomi/mms/utils/logger/MyLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   676: iconst_1
    //   677: istore 7
    //   679: aload_0
    //   680: aload_2
    //   681: aload_3
    //   682: invokevirtual 266	com/xiaomi/mms/transaction/MiCloudMxMmsTransactionHandler:invalidOtherIDCFileToken	(Ljava/lang/String;Ljava/lang/String;)V
    //   685: aload 10
    //   687: astore 11
    //   689: goto -600 -> 89
    //   692: astore 11
    //   694: ldc -83
    //   696: ldc_w 268
    //   699: aload 11
    //   701: invokestatic 181	com/xiaomi/mms/utils/logger/MyLog:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   704: aload 10
    //   706: astore 11
    //   708: iload 6
    //   710: istore 7
    //   712: goto -623 -> 89
    //   715: iload 8
    //   717: iconst_1
    //   718: isub
    //   719: istore 8
    //   721: aload 11
    //   723: astore 10
    //   725: goto -719 -> 6
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	728	0	this	MiCloudMxMmsTransactionHandler
    //   0	728	1	paramUri	Uri
    //   0	728	2	paramString1	String
    //   0	728	3	paramString2	String
    //   10	533	4	i	int
    //   16	562	5	j	int
    //   241	468	6	k	int
    //   7	704	7	m	int
    //   4	716	8	n	int
    //   162	371	9	i1	int
    //   1	421	10	localObject1	Object
    //   436	10	10	localIOException	IOException
    //   463	1	10	localObject2	Object
    //   472	10	10	localServerErrorException	com.xiaomi.mms.net.exception.ServerErrorException
    //   499	1	10	localObject3	Object
    //   508	7	10	localInvalidTokenException1	com.xiaomi.mms.net.exception.InvalidTokenException
    //   530	1	10	localObject4	Object
    //   539	10	10	localMxV2DownloadException	com.xiaomi.mms.net.exception.MxV2DownloadException
    //   555	169	10	localObject5	Object
    //   22	78	11	localObject6	Object
    //   102	7	11	localException1	Exception
    //   118	314	11	localObject7	Object
    //   564	8	11	localException2	Exception
    //   581	75	11	localObject8	Object
    //   664	8	11	localInvalidTokenException2	com.xiaomi.mms.net.exception.InvalidTokenException
    //   687	1	11	localObject9	Object
    //   692	8	11	localException3	Exception
    //   706	16	11	localObject10	Object
    //   26	609	12	localObject11	Object
    //   13	571	13	localObject12	Object
    //   19	510	14	localObject13	Object
    //   198	356	15	localObject14	Object
    // Exception table:
    //   from	to	target	type
    //   28	35	102	java/lang/Exception
    //   39	49	102	java/lang/Exception
    //   53	61	102	java/lang/Exception
    //   200	207	436	java/io/IOException
    //   223	235	436	java/io/IOException
    //   268	275	436	java/io/IOException
    //   291	296	436	java/io/IOException
    //   425	433	436	java/io/IOException
    //   200	207	472	com/xiaomi/mms/net/exception/ServerErrorException
    //   223	235	472	com/xiaomi/mms/net/exception/ServerErrorException
    //   268	275	472	com/xiaomi/mms/net/exception/ServerErrorException
    //   291	296	472	com/xiaomi/mms/net/exception/ServerErrorException
    //   425	433	472	com/xiaomi/mms/net/exception/ServerErrorException
    //   200	207	508	com/xiaomi/mms/net/exception/InvalidTokenException
    //   223	235	508	com/xiaomi/mms/net/exception/InvalidTokenException
    //   268	275	508	com/xiaomi/mms/net/exception/InvalidTokenException
    //   291	296	508	com/xiaomi/mms/net/exception/InvalidTokenException
    //   425	433	508	com/xiaomi/mms/net/exception/InvalidTokenException
    //   200	207	539	com/xiaomi/mms/net/exception/MxV2DownloadException
    //   223	235	539	com/xiaomi/mms/net/exception/MxV2DownloadException
    //   268	275	539	com/xiaomi/mms/net/exception/MxV2DownloadException
    //   291	296	539	com/xiaomi/mms/net/exception/MxV2DownloadException
    //   425	433	539	com/xiaomi/mms/net/exception/MxV2DownloadException
    //   345	352	564	java/lang/Exception
    //   356	366	564	java/lang/Exception
    //   370	378	564	java/lang/Exception
    //   645	657	664	com/xiaomi/mms/net/exception/InvalidTokenException
    //   645	657	692	java/lang/Exception
  }
  
  public boolean canSendByMxV2()
  {
    return mIsSendByMxV2;
  }
  
  public Uri handleReceiveMxMms(Uri paramUri)
  {
    Uri localUri1 = paramUri.buildUpon().appendQueryParameter("blocked_flag", "2").build();
    long l2 = -1L;
    paramUri = SqliteWrapper.query(mContext, mContext.getContentResolver(), localUri1, new String[] { "sim_id" }, null, null, null);
    long l1 = l2;
    if (paramUri != null) {
      l1 = l2;
    }
    int i;
    try
    {
      if (paramUri.moveToFirst())
      {
        i = paramUri.getInt(0);
        l1 = i;
      }
      paramUri.close();
      if (l1 == -1L)
      {
        MyLog.w("MiCloudMxMmsTransactionHandler", "sim id is null, cannot retrieve mms");
        MxMessagePduHelper.setResponseStatus(mContext, localUri1, 130);
        MxMessagePduHelper.handleMxMmsFailed(mContext, ContentUris.parseId(localUri1), false);
        return null;
      }
    }
    finally
    {
      paramUri.close();
    }
    DownloadManager.getInstance().markState(localUri2, 129, l1);
    l2 = ContentUris.parseId(localUri2);
    int k = 0;
    int m = MSimUtils.getSlotIdBySimInfoId(l1);
    String str = PushSession.getInstance(mContext).getMyMid(m);
    if (str == null)
    {
      MyLog.w("MiCloudMxMmsTransactionHandler", "mx user id is null, cannot retrieve mms");
      MxMessagePduHelper.setResponseStatus(mContext, localUri2, 130);
      paramUri = localUri2;
      i = k;
      l1 = l2;
      if (i == 0) {
        break label556;
      }
      MxMessagePduHelper.markMmsStatus(mContext, paramUri, 128);
    }
    for (;;)
    {
      return paramUri;
      paramUri = null;
      byte[] arrayOfByte = null;
      i = -1;
      Cursor localCursor = SqliteWrapper.query(mContext, mContext.getContentResolver(), localUri2, new String[] { "ct_l", "mx_status" }, null, null, null);
      int j = i;
      if (localCursor != null) {
        paramUri = arrayOfByte;
      }
      try
      {
        if (localCursor.moveToFirst())
        {
          paramUri = localCursor.getString(0);
          i = localCursor.getInt(1);
        }
        localCursor.close();
        j = i;
        if (j != 1)
        {
          i = 1;
          l1 = l2;
          paramUri = localUri2;
        }
      }
      finally
      {
        localCursor.close();
      }
      MyLog.w("MiCloudMxMmsTransactionHandler", "share id is empty");
      MxMessagePduHelper.setResponseStatus(mContext, localUri2, 224);
      l1 = l2;
      i = k;
      paramUri = localUri2;
      break;
      MxRequestEnv.getMxRequestEnv(mContext).setSimIndex(m).setIsMxV2(MxConfigCompat.isMxV2Richmedia(paramUri));
      arrayOfByte = tryDownload(localUri2, str, paramUri);
      if ((arrayOfByte != null) && (arrayOfByte.length > 0))
      {
        paramUri = MxMessagePduHelper.persistRetrieveConf(mContext, localUri2);
        l1 = ContentUris.parseId(paramUri);
        if (mCallback.onDownloadMediaSuccess(paramUri, str, arrayOfByte))
        {
          MxMessagePduHelper.setResponseStatus(mContext, paramUri, 128);
          MessagingNotification.blockingUpdateNewMessageIndicator(mContext, true, false);
          i = 1;
          break;
        }
        MxMessagePduHelper.setResponseStatus(mContext, paramUri, 226);
        i = k;
        break;
      }
      l1 = l2;
      i = k;
      paramUri = localUri2;
      if (arrayOfByte == null) {
        break;
      }
      MxMessagePduHelper.setResponseStatus(mContext, localUri2, 228);
      l1 = l2;
      i = k;
      paramUri = localUri2;
      break;
      label556:
      MxMessagePduHelper.handleMxMmsFailed(mContext, l1, false);
    }
  }
  
  public Uri handleSendMxMms(Uri paramUri)
  {
    Object localObject1 = MiuiPduPersister.getPduPersister(mContext);
    Object localObject3 = null;
    Object localObject2;
    Object localObject4;
    long l4;
    long l1;
    try
    {
      localObject1 = (SendReq)((MiuiPduPersister)localObject1).load(paramUri);
      localObject3 = localObject1;
    }
    catch (MmsException localMmsException)
    {
      for (;;)
      {
        MyLog.e("MiCloudMxMmsTransactionHandler", "failed to load mms", localMmsException);
        continue;
        i = 0;
        j = 0;
        localObject2 = null;
        localObject4 = null;
        long l6 = 0L;
        long l5 = 0L;
        l4 = -1L;
        localObject5 = mContext.getContentResolver().query(paramUri, MX_MMS_PROJECT, null, null, null);
        l1 = l6;
        l2 = l5;
        l3 = l4;
        if (localObject5 != null)
        {
          l1 = l6;
          localObject2 = localObject4;
          l2 = l5;
          i = j;
          l3 = l4;
        }
        try
        {
          if (((Cursor)localObject5).moveToFirst()) {
            if (1 != ((Cursor)localObject5).getInt(3)) {
              break label231;
            }
          }
          for (i = 1;; i = 0)
          {
            localObject2 = ((Cursor)localObject5).getString(0);
            l2 = ((Cursor)localObject5).getLong(1);
            l1 = ((Cursor)localObject5).getLong(2);
            j = ((Cursor)localObject5).getInt(4);
            l3 = j;
            ((Cursor)localObject5).close();
            if (i == 0) {
              break;
            }
            l4 = ContentUris.parseId(paramUri);
            i2 = MSimUtils.getSlotIdBySimInfoId(l3);
            if (i2 >= 0) {
              break label246;
            }
            MxMessagePduHelper.handleMxMmsFailed(mContext, l4);
            return paramUri;
          }
          arrayOfEncodedStringValue = ((SendReq)localObject3).getTo();
        }
        finally
        {
          ((Cursor)localObject5).close();
        }
      }
      bool = MxActivateService.isMxEnabled(mContext, i2);
      localObject4 = PushSession.getInstance(mContext);
      str2 = ((PushSession)localObject4).getMyFullMid(i2);
      str3 = ((PushSession)localObject4).getMyMid(i2);
      j = 0;
      k = 0;
      if (arrayOfEncodedStringValue != null) {
        break label332;
      }
    }
    if (localObject3 == null) {
      return paramUri;
    }
    int i;
    Object localObject5;
    long l2;
    long l3;
    int i2;
    label231:
    label246:
    EncodedStringValue[] arrayOfEncodedStringValue;
    boolean bool;
    String str2;
    String str3;
    int k;
    MxMessagePduHelper.setResponseStatus(mContext, paramUri, 224);
    int j = k;
    label332:
    ArrayList localArrayList1;
    int m;
    ArrayList localArrayList2;
    label571:
    int n;
    for (;;)
    {
      if (j != 0)
      {
        MxMessagePduHelper.markMmsStatus(mContext, paramUri, 128);
        return paramUri;
        if (!bool)
        {
          MxMessagePduHelper.setResponseStatus(mContext, paramUri, 224);
          j = k;
        }
        else if ((str2 == null) || (str3 == null))
        {
          MxMessagePduHelper.setResponseStatus(mContext, paramUri, 130);
          j = k;
        }
        else
        {
          localArrayList1 = Lists.newArrayList();
          m = arrayOfEncodedStringValue.length;
          i = 0;
          while (i < m)
          {
            localObject4 = Contact.get(arrayOfEncodedStringValue[i].getString());
            localObject4 = MxIdCache.getOrQuery(mContext, ((Contact)localObject4).getMxPhoneNumber());
            if ((localObject4 == null) || (!((MxIdCache.MxIdCacheItem)localObject4).allowMms())) {
              break;
            }
            localArrayList1.add(((MxIdCache.MxIdCacheItem)localObject4).getMId());
            i += 1;
          }
          localObject4 = null;
          localObject5 = ((SendReq)localObject3).getSubject();
          localObject3 = localObject4;
          if (localObject5 != null) {
            localObject3 = ((EncodedStringValue)localObject5).getString();
          }
          if (arrayOfEncodedStringValue.length == localArrayList1.size())
          {
            localObject4 = null;
            try
            {
              localObject5 = MiCloudMmsCodec.encodeMmsBody(mContext.getContentResolver(), l4, false);
              localObject4 = localObject5;
            }
            catch (Exception localException)
            {
              for (;;)
              {
                MyLog.e("MiCloudMxMmsTransactionHandler", "error when constructing mms", localException);
              }
              localArrayList2 = new ArrayList(4);
              k = 0;
              i = j;
            }
            if (localObject4 == null)
            {
              MxMessagePduHelper.setResponseStatus(mContext, paramUri, 228);
              j = k;
              continue;
            }
            j = i;
            if (k >= arrayOfEncodedStringValue.length) {
              continue;
            }
            j = k + 4;
            n = j;
            if (j > arrayOfEncodedStringValue.length) {
              n = arrayOfEncodedStringValue.length;
            }
            j = k;
            while (j < n)
            {
              localArrayList2.add(localArrayList1.get(j));
              j += 1;
            }
            MxRequestEnv.getMxRequestEnv(mContext).setSimIndex(i2);
            m = i;
          }
        }
      }
    }
    for (;;)
    {
      try
      {
        if (canSendByMxV2())
        {
          m = i;
          Object localObject6 = new UploadData((byte[])localObject4, "mixin2", ".gz");
          m = i;
          MxRequestEnv.getMxRequestEnv(mContext).setSimIndex(i2).setIsMxV2(canSendByMxV2());
          String str1 = null;
          m = i;
          if (canSendByMxV2())
          {
            m = i;
            str1 = MxConfigCompat.getMxV2RichMediaUrl(mContext, str3);
          }
          m = i;
          localObject6 = MxMessageUtils.tryUploadFile(mContext, paramUri, str3, (UploadEntity)localObject6, localArrayList2, str1, mCallback, mFactory);
          j = i;
          if (localObject6 != null)
          {
            m = i;
            str1 = shareId;
            m = i;
            l3 = expireAt;
            m = i;
            if ((TextUtils.isEmpty(str1)) || (l3 <= 0L)) {
              break label1020;
            }
            int i1 = 1;
            j = k;
            m = i1;
            if (j < n)
            {
              m = i;
              if (mCallback.onUploadMediaSuccess(paramUri, l1, (String)localObject3, str2, (String)localArrayList1.get(j), (String)localObject2, str1, MxMessageService.adjustExpiredTime(l3), l2, null)) {
                break label1112;
              }
              m = 0;
            }
            if (m == 0) {
              break label1001;
            }
            m = i;
            MxMessagePduHelper.setResponseStatus(mContext, paramUri, 128);
            m = 1;
            j = 1;
            MxMessageTrackService.startTrack(mContext);
          }
          i = j;
          if (j == 0)
          {
            m = j;
            MyLog.d("MiCloudMxMmsTransactionHandler", "sending progress stops, due to uploading failure");
          }
        }
      }
      catch (MxLogicException localMxLogicException)
      {
        MxMessagePduHelper.setResponseStatus(mContext, paramUri, 224);
        i = m;
        localArrayList2.clear();
        k += 4;
        break label571;
        m = i;
        UploadData localUploadData = new UploadData((byte[])localObject4, "mixin", ".gz");
        continue;
      }
      catch (IOException localIOException)
      {
        m = i;
        MyLog.e("MiCloudMxMmsTransactionHandler", "error when construct upload entity", localIOException);
        m = i;
        throw new MxLogicException("failed to construct upload entity", localIOException);
      }
      label1001:
      m = i;
      MxMessagePduHelper.setResponseStatus(mContext, paramUri, 224);
      j = i;
      continue;
      label1020:
      m = i;
      MyLog.e("MiCloudMxMmsTransactionHandler", "no shared id or expireAt value, uploading failed");
      m = i;
      MxMessagePduHelper.setResponseStatus(mContext, paramUri, 224);
      j = i;
      continue;
      MyLog.d("MiCloudMxMmsTransactionHandler", "some recipients are offline");
      MxMessagePduHelper.setResponseStatus(mContext, paramUri, 224);
      j = k;
      break;
      MyLog.d("MiCloudMxMmsTransactionHandler", "failed to send mi mms, id: " + l4);
      MxMessagePduHelper.handleMxMmsFailed(mContext, l4);
      return paramUri;
      label1112:
      j += 1;
    }
  }
  
  protected void invalidFileToken(String paramString1, String paramString2)
  {
    int i = PushSession.getInstance(mContext).getSimIdByMid(paramString1);
    if (i == -1)
    {
      MyLog.e("MiCloudMxMmsTransactionHandler", "refresh file token failed, because sim index == -1)");
      return;
    }
    PrefUtils.remove(mContext, getFileTokenPref(paramString2) + i);
  }
  
  protected void invalidOtherIDCFileToken(String paramString1, String paramString2)
  {
    int i = PushSession.getInstance(mContext).getSimIdByMid(paramString1);
    if (i == -1)
    {
      MyLog.e("MiCloudMxMmsTransactionHandler", "refresh other idc file token failed, because sim index == -1)");
      return;
    }
    PrefUtils.remove(mContext, getFileTokenPref(paramString2) + i + "_idc");
  }
  
  public void setSendByMxV2(boolean paramBoolean)
  {
    mIsSendByMxV2 = paramBoolean;
  }
  
  public static abstract interface MiCloudMediaManagerCallback
  {
    public abstract ExtendedAuthToken getMediaManagerToken(String paramString)
      throws IOException;
    
    public abstract boolean onDownloadMediaSuccess(Uri paramUri, String paramString, byte[] paramArrayOfByte);
    
    public abstract void onMediaManagerTokenInvalid(String paramString);
    
    public abstract boolean onUploadMediaSuccess(Uri paramUri, long paramLong1, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, long paramLong2, long paramLong3, String paramString6);
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.transaction.MiCloudMxMmsTransactionHandler
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */