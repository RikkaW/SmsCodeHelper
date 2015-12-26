package com.android.mms.backup;

import android.content.ContentProviderOperation;
import android.content.ContentProviderOperation.Builder;
import android.content.ContentProviderResult;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.OperationApplicationException;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.RemoteException;
import android.provider.Telephony.Mms;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.collect.Lists;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import miui.app.backup.BackupManager;
import miui.app.backup.FullBackupAgent;

public class MmsBackupAgent
  extends FullBackupAgent
{
  private HashMap<String, Uri> mAttach2Uri;
  private BackupManager mBackupManager;
  private MmsManager mMmsManager;
  private SmsManager mSmsManager;
  
  private void applyMms(ContentResolver paramContentResolver, ArrayList<MmsProtos.Pdu> paramArrayList, ArrayList<ContentProviderOperation> paramArrayList1)
    throws RemoteException, OperationApplicationException
  {
    ContentProviderResult[] arrayOfContentProviderResult = paramContentResolver.applyBatch("mms", paramArrayList1);
    ArrayList localArrayList2 = Lists.newArrayList();
    ArrayList localArrayList1 = Lists.newArrayList();
    paramArrayList1 = new long[arrayOfContentProviderResult.length];
    int i = 0;
    while (i < arrayOfContentProviderResult.length)
    {
      Object localObject2 = uri.getPathSegments();
      Object localObject1 = (String)((List)localObject2).get(0);
      try
      {
        long l = Long.valueOf((String)((List)localObject2).get(1)).longValue();
        paramArrayList1[i] = l;
        if (((String)localObject1).equals("restored"))
        {
          localObject2 = ContentProviderOperation.newDelete(Uri.withAppendedPath(Telephony.Mms.CONTENT_URI, l + "/part")).build();
          localArrayList1.add(null);
          localArrayList2.add(localObject2);
        }
        if ((((String)localObject1).equals("inserted")) || (((String)localObject1).equals("restored")))
        {
          localObject1 = (MmsProtos.Pdu)paramArrayList.get(i);
          int j = 0;
          while (j < ((MmsProtos.Pdu)localObject1).getPduPartsCount())
          {
            localObject2 = ((MmsProtos.Pdu)localObject1).getPduParts(j);
            localArrayList1.add(localObject2);
            localArrayList2.add(mMmsManager.restorePduPart(l, (MmsProtos.PduPart)localObject2));
            j += 1;
          }
        }
        i += 1;
      }
      catch (NumberFormatException paramContentResolver)
      {
        Log.e("SmsController", "NumberFormatException", paramContentResolver);
        return;
      }
    }
    paramContentResolver = paramContentResolver.applyBatch("mms", localArrayList2);
    localArrayList2.clear();
    i = 0;
    while (i < paramContentResolver.length)
    {
      if (localArrayList1.get(i) != null)
      {
        paramArrayList = uri.buildUpon().appendQueryParameter("supress_making_mms_preview", "1").build();
        mMmsManager.restorePduPartFile(paramArrayList, (MmsProtos.PduPart)localArrayList1.get(i));
      }
      i += 1;
    }
    paramContentResolver = new Intent("android.provider.Telephony.MAKE_MMS_PREVIEW");
    paramContentResolver.putExtra("_id", paramArrayList1);
    paramContentResolver.setPackage("com.android.providers.telephony");
    startService(paramContentResolver);
  }
  
  private void applySms()
  {
    ContentProviderResult[] arrayOfContentProviderResult = mSmsManager.apply();
    int i = 0;
    while (i < arrayOfContentProviderResult.length)
    {
      String str = (String)uri.getPathSegments().get(0);
      if ((!str.equals("inserted")) && (str.equals("restored"))) {}
      i += 1;
    }
  }
  
  protected int getVersion(int paramInt)
  {
    return 1;
  }
  
  /* Error */
  protected int onAttachRestore(miui.app.backup.BackupMeta paramBackupMeta, android.os.ParcelFileDescriptor paramParcelFileDescriptor, String paramString)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore 4
    //   3: aload_0
    //   4: getfield 224	com/android/mms/backup/MmsBackupAgent:mAttach2Uri	Ljava/util/HashMap;
    //   7: aload_3
    //   8: invokevirtual 229	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   11: checkcast 52	android/net/Uri
    //   14: astore_1
    //   15: aconst_null
    //   16: astore_3
    //   17: aconst_null
    //   18: astore 8
    //   20: aconst_null
    //   21: astore 7
    //   23: aconst_null
    //   24: astore 9
    //   26: aconst_null
    //   27: astore 11
    //   29: aconst_null
    //   30: astore 10
    //   32: aload 11
    //   34: astore 6
    //   36: aload_0
    //   37: invokevirtual 233	com/android/mms/backup/MmsBackupAgent:getContentResolver	()Landroid/content/ContentResolver;
    //   40: aload_1
    //   41: ldc -21
    //   43: invokevirtual 239	android/content/ContentResolver:openOutputStream	(Landroid/net/Uri;Ljava/lang/String;)Ljava/io/OutputStream;
    //   46: astore_1
    //   47: aload_1
    //   48: ifnonnull +29 -> 77
    //   51: iconst_1
    //   52: istore 4
    //   54: aload_1
    //   55: ifnull +7 -> 62
    //   58: aload_1
    //   59: invokevirtual 244	java/io/OutputStream:close	()V
    //   62: iconst_0
    //   63: ifeq +11 -> 74
    //   66: new 246	java/lang/NullPointerException
    //   69: dup
    //   70: invokespecial 247	java/lang/NullPointerException:<init>	()V
    //   73: athrow
    //   74: iload 4
    //   76: ireturn
    //   77: aload_1
    //   78: astore 7
    //   80: aload 11
    //   82: astore 6
    //   84: aload_1
    //   85: astore_3
    //   86: aload_1
    //   87: astore 8
    //   89: new 249	java/io/FileInputStream
    //   92: dup
    //   93: aload_2
    //   94: invokevirtual 255	android/os/ParcelFileDescriptor:getFileDescriptor	()Ljava/io/FileDescriptor;
    //   97: invokespecial 258	java/io/FileInputStream:<init>	(Ljava/io/FileDescriptor;)V
    //   100: astore_2
    //   101: sipush 8192
    //   104: newarray <illegal type>
    //   106: astore_3
    //   107: aload_2
    //   108: aload_3
    //   109: invokevirtual 264	java/io/InputStream:read	([B)I
    //   112: istore 5
    //   114: iload 5
    //   116: ifle +43 -> 159
    //   119: aload_1
    //   120: aload_3
    //   121: iconst_0
    //   122: iload 5
    //   124: invokevirtual 268	java/io/OutputStream:write	([BII)V
    //   127: goto -20 -> 107
    //   130: astore_3
    //   131: aload_1
    //   132: astore 8
    //   134: aload_2
    //   135: astore_1
    //   136: aload 8
    //   138: ifnull +8 -> 146
    //   141: aload 8
    //   143: invokevirtual 244	java/io/OutputStream:close	()V
    //   146: aload_1
    //   147: ifnull -73 -> 74
    //   150: aload_1
    //   151: invokevirtual 269	java/io/InputStream:close	()V
    //   154: iconst_0
    //   155: ireturn
    //   156: astore_1
    //   157: iconst_0
    //   158: ireturn
    //   159: aload_1
    //   160: ifnull +7 -> 167
    //   163: aload_1
    //   164: invokevirtual 244	java/io/OutputStream:close	()V
    //   167: aload_2
    //   168: ifnull +124 -> 292
    //   171: aload_2
    //   172: invokevirtual 269	java/io/InputStream:close	()V
    //   175: iconst_0
    //   176: ireturn
    //   177: astore_1
    //   178: iconst_0
    //   179: ireturn
    //   180: astore 8
    //   182: aload 7
    //   184: astore_1
    //   185: aload 9
    //   187: astore_2
    //   188: aload_2
    //   189: astore 6
    //   191: aload_1
    //   192: astore_3
    //   193: aload 8
    //   195: invokevirtual 272	java/io/IOException:printStackTrace	()V
    //   198: aload_1
    //   199: ifnull +7 -> 206
    //   202: aload_1
    //   203: invokevirtual 244	java/io/OutputStream:close	()V
    //   206: aload_2
    //   207: ifnull -133 -> 74
    //   210: aload_2
    //   211: invokevirtual 269	java/io/InputStream:close	()V
    //   214: iconst_0
    //   215: ireturn
    //   216: astore_1
    //   217: iconst_0
    //   218: ireturn
    //   219: astore_1
    //   220: aload_3
    //   221: ifnull +7 -> 228
    //   224: aload_3
    //   225: invokevirtual 244	java/io/OutputStream:close	()V
    //   228: aload 6
    //   230: ifnull +8 -> 238
    //   233: aload 6
    //   235: invokevirtual 269	java/io/InputStream:close	()V
    //   238: aload_1
    //   239: athrow
    //   240: astore_1
    //   241: goto -179 -> 62
    //   244: astore_1
    //   245: iconst_1
    //   246: ireturn
    //   247: astore_1
    //   248: goto -81 -> 167
    //   251: astore_2
    //   252: goto -106 -> 146
    //   255: astore_1
    //   256: goto -50 -> 206
    //   259: astore_2
    //   260: goto -32 -> 228
    //   263: astore_2
    //   264: goto -26 -> 238
    //   267: astore 7
    //   269: aload_2
    //   270: astore 6
    //   272: aload_1
    //   273: astore_3
    //   274: aload 7
    //   276: astore_1
    //   277: goto -57 -> 220
    //   280: astore 8
    //   282: goto -94 -> 188
    //   285: astore_1
    //   286: aload 10
    //   288: astore_1
    //   289: goto -153 -> 136
    //   292: iconst_0
    //   293: ireturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	294	0	this	MmsBackupAgent
    //   0	294	1	paramBackupMeta	miui.app.backup.BackupMeta
    //   0	294	2	paramParcelFileDescriptor	android.os.ParcelFileDescriptor
    //   0	294	3	paramString	String
    //   1	74	4	i	int
    //   112	11	5	j	int
    //   34	237	6	localObject1	Object
    //   21	162	7	localBackupMeta1	miui.app.backup.BackupMeta
    //   267	8	7	localObject2	Object
    //   18	124	8	localBackupMeta2	miui.app.backup.BackupMeta
    //   180	14	8	localIOException1	java.io.IOException
    //   280	1	8	localIOException2	java.io.IOException
    //   24	162	9	localObject3	Object
    //   30	257	10	localObject4	Object
    //   27	54	11	localObject5	Object
    // Exception table:
    //   from	to	target	type
    //   101	107	130	java/lang/IllegalArgumentException
    //   107	114	130	java/lang/IllegalArgumentException
    //   119	127	130	java/lang/IllegalArgumentException
    //   150	154	156	java/io/IOException
    //   171	175	177	java/io/IOException
    //   36	47	180	java/io/IOException
    //   89	101	180	java/io/IOException
    //   210	214	216	java/io/IOException
    //   36	47	219	finally
    //   89	101	219	finally
    //   193	198	219	finally
    //   58	62	240	java/io/IOException
    //   66	74	244	java/io/IOException
    //   163	167	247	java/io/IOException
    //   141	146	251	java/io/IOException
    //   202	206	255	java/io/IOException
    //   224	228	259	java/io/IOException
    //   233	238	263	java/io/IOException
    //   101	107	267	finally
    //   107	114	267	finally
    //   119	127	267	finally
    //   101	107	280	java/io/IOException
    //   107	114	280	java/io/IOException
    //   119	127	280	java/io/IOException
    //   36	47	285	java/lang/IllegalArgumentException
    //   89	101	285	java/lang/IllegalArgumentException
  }
  
  /* Error */
  protected int onDataRestore(miui.app.backup.BackupMeta paramBackupMeta, android.os.ParcelFileDescriptor paramParcelFileDescriptor)
    throws java.io.IOException
  {
    // Byte code:
    //   0: aload_0
    //   1: aload_0
    //   2: invokestatic 284	miui/app/backup/BackupManager:getBackupManager	(Landroid/content/Context;)Lmiui/app/backup/BackupManager;
    //   5: putfield 286	com/android/mms/backup/MmsBackupAgent:mBackupManager	Lmiui/app/backup/BackupManager;
    //   8: aload_1
    //   9: getfield 292	miui/app/backup/BackupMeta:feature	I
    //   12: iconst_1
    //   13: if_icmpne +319 -> 332
    //   16: aload_1
    //   17: getfield 295	miui/app/backup/BackupMeta:version	I
    //   20: iconst_1
    //   21: if_icmpne +238 -> 259
    //   24: aconst_null
    //   25: astore 10
    //   27: aconst_null
    //   28: astore_1
    //   29: aconst_null
    //   30: astore 9
    //   32: aload_0
    //   33: new 210	com/android/mms/backup/SmsManager
    //   36: dup
    //   37: aload_0
    //   38: invokevirtual 299	com/android/mms/backup/MmsBackupAgent:getApplicationContext	()Landroid/content/Context;
    //   41: invokespecial 302	com/android/mms/backup/SmsManager:<init>	(Landroid/content/Context;)V
    //   44: putfield 208	com/android/mms/backup/MmsBackupAgent:mSmsManager	Lcom/android/mms/backup/SmsManager;
    //   47: new 249	java/io/FileInputStream
    //   50: dup
    //   51: aload_2
    //   52: invokevirtual 255	android/os/ParcelFileDescriptor:getFileDescriptor	()Ljava/io/FileDescriptor;
    //   55: invokespecial 258	java/io/FileInputStream:<init>	(Ljava/io/FileDescriptor;)V
    //   58: astore_2
    //   59: aload_2
    //   60: invokestatic 308	com/android/mms/backup/SyncRootProtos$SyncRoot:parseFrom	(Ljava/io/InputStream;)Lcom/android/mms/backup/SyncRootProtos$SyncRoot;
    //   63: invokevirtual 312	com/android/mms/backup/SyncRootProtos$SyncRoot:getMmsSms	()Lcom/android/mms/backup/SmsProtos$MmsSms;
    //   66: astore_1
    //   67: aload_1
    //   68: ifnonnull +14 -> 82
    //   71: aload_2
    //   72: ifnull +7 -> 79
    //   75: aload_2
    //   76: invokevirtual 313	java/io/FileInputStream:close	()V
    //   79: bipush 6
    //   81: ireturn
    //   82: new 119	java/util/ArrayList
    //   85: dup
    //   86: aload_1
    //   87: invokevirtual 318	com/android/mms/backup/SmsProtos$MmsSms:getSmsList	()Ljava/util/List;
    //   90: invokespecial 321	java/util/ArrayList:<init>	(Ljava/util/Collection;)V
    //   93: astore_1
    //   94: aload_1
    //   95: new 6	com/android/mms/backup/MmsBackupAgent$1
    //   98: dup
    //   99: aload_0
    //   100: invokespecial 324	com/android/mms/backup/MmsBackupAgent$1:<init>	(Lcom/android/mms/backup/MmsBackupAgent;)V
    //   103: invokestatic 330	java/util/Collections:sort	(Ljava/util/List;Ljava/util/Comparator;)V
    //   106: aload_0
    //   107: getfield 208	com/android/mms/backup/MmsBackupAgent:mSmsManager	Lcom/android/mms/backup/SmsManager;
    //   110: invokevirtual 333	com/android/mms/backup/SmsManager:prepareSms	()V
    //   113: iconst_0
    //   114: istore 4
    //   116: iconst_0
    //   117: istore 6
    //   119: aload_1
    //   120: invokevirtual 336	java/util/ArrayList:size	()I
    //   123: istore 7
    //   125: aload_0
    //   126: getfield 286	com/android/mms/backup/MmsBackupAgent:mBackupManager	Lmiui/app/backup/BackupManager;
    //   129: iconst_1
    //   130: iconst_0
    //   131: iload 7
    //   133: invokevirtual 340	miui/app/backup/BackupManager:setCustomProgress	(III)V
    //   136: aload_1
    //   137: invokevirtual 344	java/util/ArrayList:iterator	()Ljava/util/Iterator;
    //   140: astore_1
    //   141: aload_1
    //   142: invokeinterface 350 1 0
    //   147: ifeq +159 -> 306
    //   150: aload_1
    //   151: invokeinterface 354 1 0
    //   156: checkcast 356	com/android/mms/backup/SmsProtos$Sms
    //   159: astore 8
    //   161: iload 4
    //   163: istore_3
    //   164: iload 4
    //   166: istore 5
    //   168: aload_0
    //   169: getfield 208	com/android/mms/backup/MmsBackupAgent:mSmsManager	Lcom/android/mms/backup/SmsManager;
    //   172: aload 8
    //   174: invokevirtual 359	com/android/mms/backup/SmsManager:add	(Lcom/android/mms/backup/SmsProtos$Sms;)Z
    //   177: ifeq +31 -> 208
    //   180: iload 4
    //   182: iconst_1
    //   183: iadd
    //   184: istore 4
    //   186: iload 4
    //   188: istore_3
    //   189: iload 4
    //   191: bipush 50
    //   193: irem
    //   194: ifne +14 -> 208
    //   197: iload 4
    //   199: istore 5
    //   201: aload_0
    //   202: invokespecial 361	com/android/mms/backup/MmsBackupAgent:applySms	()V
    //   205: iload 4
    //   207: istore_3
    //   208: aload_0
    //   209: getfield 286	com/android/mms/backup/MmsBackupAgent:mBackupManager	Lmiui/app/backup/BackupManager;
    //   212: astore 8
    //   214: iload 6
    //   216: iconst_1
    //   217: iadd
    //   218: istore 6
    //   220: aload 8
    //   222: iconst_1
    //   223: iload 6
    //   225: iload 7
    //   227: invokevirtual 340	miui/app/backup/BackupManager:setCustomProgress	(III)V
    //   230: iload_3
    //   231: istore 4
    //   233: goto -92 -> 141
    //   236: astore 8
    //   238: aload_2
    //   239: astore_1
    //   240: ldc -111
    //   242: ldc_w 363
    //   245: aload 8
    //   247: invokestatic 153	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   250: pop
    //   251: aload_2
    //   252: ifnull +7 -> 259
    //   255: aload_2
    //   256: invokevirtual 313	java/io/FileInputStream:close	()V
    //   259: iconst_0
    //   260: ireturn
    //   261: astore 8
    //   263: ldc -111
    //   265: ldc_w 365
    //   268: aload 8
    //   270: invokestatic 153	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   273: pop
    //   274: iload 5
    //   276: istore_3
    //   277: goto -69 -> 208
    //   280: astore 8
    //   282: aload_2
    //   283: astore_1
    //   284: ldc -111
    //   286: ldc_w 363
    //   289: aload 8
    //   291: invokestatic 153	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   294: pop
    //   295: aload_2
    //   296: ifnull -37 -> 259
    //   299: aload_2
    //   300: invokevirtual 313	java/io/FileInputStream:close	()V
    //   303: goto -44 -> 259
    //   306: aload_0
    //   307: invokespecial 361	com/android/mms/backup/MmsBackupAgent:applySms	()V
    //   310: aload_2
    //   311: ifnull +500 -> 811
    //   314: aload_2
    //   315: invokevirtual 313	java/io/FileInputStream:close	()V
    //   318: goto -59 -> 259
    //   321: astore_2
    //   322: aload_1
    //   323: ifnull +7 -> 330
    //   326: aload_1
    //   327: invokevirtual 313	java/io/FileInputStream:close	()V
    //   330: aload_2
    //   331: athrow
    //   332: aload_1
    //   333: getfield 292	miui/app/backup/BackupMeta:feature	I
    //   336: iconst_2
    //   337: if_icmpne -78 -> 259
    //   340: aload_1
    //   341: getfield 295	miui/app/backup/BackupMeta:version	I
    //   344: iconst_1
    //   345: if_icmpne -86 -> 259
    //   348: aconst_null
    //   349: astore 8
    //   351: aconst_null
    //   352: astore 10
    //   354: aconst_null
    //   355: astore 11
    //   357: aconst_null
    //   358: astore_1
    //   359: aconst_null
    //   360: astore 9
    //   362: aload_0
    //   363: new 226	java/util/HashMap
    //   366: dup
    //   367: invokespecial 366	java/util/HashMap:<init>	()V
    //   370: putfield 224	com/android/mms/backup/MmsBackupAgent:mAttach2Uri	Ljava/util/HashMap;
    //   373: aload_0
    //   374: new 139	com/android/mms/backup/MmsManager
    //   377: dup
    //   378: aload_0
    //   379: invokevirtual 299	com/android/mms/backup/MmsBackupAgent:getApplicationContext	()Landroid/content/Context;
    //   382: invokespecial 367	com/android/mms/backup/MmsManager:<init>	(Landroid/content/Context;)V
    //   385: putfield 137	com/android/mms/backup/MmsBackupAgent:mMmsManager	Lcom/android/mms/backup/MmsManager;
    //   388: new 249	java/io/FileInputStream
    //   391: dup
    //   392: aload_2
    //   393: invokevirtual 255	android/os/ParcelFileDescriptor:getFileDescriptor	()Ljava/io/FileDescriptor;
    //   396: invokespecial 258	java/io/FileInputStream:<init>	(Ljava/io/FileDescriptor;)V
    //   399: astore_2
    //   400: aload_2
    //   401: invokestatic 308	com/android/mms/backup/SyncRootProtos$SyncRoot:parseFrom	(Ljava/io/InputStream;)Lcom/android/mms/backup/SyncRootProtos$SyncRoot;
    //   404: astore_1
    //   405: aload_2
    //   406: invokevirtual 313	java/io/FileInputStream:close	()V
    //   409: aload_1
    //   410: invokevirtual 371	com/android/mms/backup/SyncRootProtos$SyncRoot:getMmsCollection	()Lcom/android/mms/backup/MmsProtos$MmsCollection;
    //   413: astore_1
    //   414: aload_1
    //   415: ifnonnull +14 -> 429
    //   418: aload_2
    //   419: ifnull -340 -> 79
    //   422: aload_2
    //   423: invokevirtual 313	java/io/FileInputStream:close	()V
    //   426: bipush 6
    //   428: ireturn
    //   429: new 119	java/util/ArrayList
    //   432: dup
    //   433: aload_1
    //   434: invokevirtual 376	com/android/mms/backup/MmsProtos$MmsCollection:getPdusList	()Ljava/util/List;
    //   437: invokespecial 321	java/util/ArrayList:<init>	(Ljava/util/Collection;)V
    //   440: astore 10
    //   442: aload 10
    //   444: new 8	com/android/mms/backup/MmsBackupAgent$2
    //   447: dup
    //   448: aload_0
    //   449: invokespecial 377	com/android/mms/backup/MmsBackupAgent$2:<init>	(Lcom/android/mms/backup/MmsBackupAgent;)V
    //   452: invokestatic 330	java/util/Collections:sort	(Ljava/util/List;Ljava/util/Comparator;)V
    //   455: invokestatic 44	com/google/android/collect/Lists:newArrayList	()Ljava/util/ArrayList;
    //   458: astore_1
    //   459: aload_0
    //   460: invokevirtual 233	com/android/mms/backup/MmsBackupAgent:getContentResolver	()Landroid/content/ContentResolver;
    //   463: astore 8
    //   465: aload_0
    //   466: getfield 137	com/android/mms/backup/MmsBackupAgent:mMmsManager	Lcom/android/mms/backup/MmsManager;
    //   469: aload_0
    //   470: getfield 224	com/android/mms/backup/MmsBackupAgent:mAttach2Uri	Ljava/util/HashMap;
    //   473: invokevirtual 381	com/android/mms/backup/MmsManager:setMmsAttach	(Ljava/util/HashMap;)V
    //   476: invokestatic 44	com/google/android/collect/Lists:newArrayList	()Ljava/util/ArrayList;
    //   479: astore 9
    //   481: iconst_0
    //   482: istore_3
    //   483: aload 10
    //   485: invokevirtual 336	java/util/ArrayList:size	()I
    //   488: istore 4
    //   490: aload_0
    //   491: getfield 286	com/android/mms/backup/MmsBackupAgent:mBackupManager	Lmiui/app/backup/BackupManager;
    //   494: iconst_1
    //   495: iconst_0
    //   496: iload 4
    //   498: invokevirtual 340	miui/app/backup/BackupManager:setCustomProgress	(III)V
    //   501: aload 10
    //   503: invokevirtual 344	java/util/ArrayList:iterator	()Ljava/util/Iterator;
    //   506: astore 10
    //   508: aload 10
    //   510: invokeinterface 350 1 0
    //   515: ifeq +117 -> 632
    //   518: aload 10
    //   520: invokeinterface 354 1 0
    //   525: checkcast 127	com/android/mms/backup/MmsProtos$Pdu
    //   528: astore 11
    //   530: aload_0
    //   531: getfield 137	com/android/mms/backup/MmsBackupAgent:mMmsManager	Lcom/android/mms/backup/MmsManager;
    //   534: aload 11
    //   536: invokevirtual 385	com/android/mms/backup/MmsManager:restorePdu	(Lcom/android/mms/backup/MmsProtos$Pdu;)Landroid/content/ContentProviderOperation;
    //   539: astore 12
    //   541: aload_1
    //   542: aload 11
    //   544: invokevirtual 122	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   547: pop
    //   548: aload 9
    //   550: aload 12
    //   552: invokevirtual 122	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   555: pop
    //   556: aload 9
    //   558: invokevirtual 336	java/util/ArrayList:size	()I
    //   561: bipush 20
    //   563: if_icmpne +21 -> 584
    //   566: aload_0
    //   567: aload 8
    //   569: aload_1
    //   570: aload 9
    //   572: invokespecial 387	com/android/mms/backup/MmsBackupAgent:applyMms	(Landroid/content/ContentResolver;Ljava/util/ArrayList;Ljava/util/ArrayList;)V
    //   575: aload_1
    //   576: invokevirtual 156	java/util/ArrayList:clear	()V
    //   579: aload 9
    //   581: invokevirtual 156	java/util/ArrayList:clear	()V
    //   584: aload_0
    //   585: getfield 286	com/android/mms/backup/MmsBackupAgent:mBackupManager	Lmiui/app/backup/BackupManager;
    //   588: astore 11
    //   590: iload_3
    //   591: iconst_1
    //   592: iadd
    //   593: istore_3
    //   594: aload 11
    //   596: iconst_1
    //   597: iload_3
    //   598: iload 4
    //   600: invokevirtual 340	miui/app/backup/BackupManager:setCustomProgress	(III)V
    //   603: goto -95 -> 508
    //   606: astore 8
    //   608: aload_2
    //   609: astore_1
    //   610: ldc -111
    //   612: ldc_w 389
    //   615: aload 8
    //   617: invokestatic 153	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   620: pop
    //   621: aload_2
    //   622: ifnull -363 -> 259
    //   625: aload_2
    //   626: invokevirtual 313	java/io/FileInputStream:close	()V
    //   629: goto -370 -> 259
    //   632: aload_0
    //   633: aload 8
    //   635: aload_1
    //   636: aload 9
    //   638: invokespecial 387	com/android/mms/backup/MmsBackupAgent:applyMms	(Landroid/content/ContentResolver;Ljava/util/ArrayList;Ljava/util/ArrayList;)V
    //   641: aload_2
    //   642: ifnull -383 -> 259
    //   645: aload_2
    //   646: invokevirtual 313	java/io/FileInputStream:close	()V
    //   649: goto -390 -> 259
    //   652: astore_1
    //   653: aload 8
    //   655: astore_2
    //   656: aload_1
    //   657: astore 8
    //   659: aload_2
    //   660: astore_1
    //   661: ldc -111
    //   663: ldc_w 389
    //   666: aload 8
    //   668: invokestatic 153	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   671: pop
    //   672: aload_2
    //   673: ifnull -414 -> 259
    //   676: aload_2
    //   677: invokevirtual 313	java/io/FileInputStream:close	()V
    //   680: goto -421 -> 259
    //   683: astore 8
    //   685: aload 10
    //   687: astore_2
    //   688: aload_2
    //   689: astore_1
    //   690: ldc -111
    //   692: ldc_w 389
    //   695: aload 8
    //   697: invokestatic 153	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   700: pop
    //   701: aload_2
    //   702: ifnull -443 -> 259
    //   705: aload_2
    //   706: invokevirtual 313	java/io/FileInputStream:close	()V
    //   709: goto -450 -> 259
    //   712: astore 8
    //   714: aload 11
    //   716: astore_2
    //   717: aload_2
    //   718: astore_1
    //   719: ldc -111
    //   721: ldc_w 389
    //   724: aload 8
    //   726: invokestatic 153	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   729: pop
    //   730: aload_2
    //   731: ifnull -472 -> 259
    //   734: aload_2
    //   735: invokevirtual 313	java/io/FileInputStream:close	()V
    //   738: goto -479 -> 259
    //   741: astore_2
    //   742: aload_1
    //   743: ifnull +7 -> 750
    //   746: aload_1
    //   747: invokevirtual 313	java/io/FileInputStream:close	()V
    //   750: aload_2
    //   751: athrow
    //   752: astore 8
    //   754: aload_2
    //   755: astore_1
    //   756: aload 8
    //   758: astore_2
    //   759: goto -17 -> 742
    //   762: astore 8
    //   764: goto -47 -> 717
    //   767: astore 8
    //   769: goto -81 -> 688
    //   772: astore 8
    //   774: goto -115 -> 659
    //   777: astore 8
    //   779: aload 9
    //   781: astore_2
    //   782: goto -174 -> 608
    //   785: astore 8
    //   787: aload_2
    //   788: astore_1
    //   789: aload 8
    //   791: astore_2
    //   792: goto -470 -> 322
    //   795: astore 8
    //   797: aload 10
    //   799: astore_2
    //   800: goto -518 -> 282
    //   803: astore 8
    //   805: aload 9
    //   807: astore_2
    //   808: goto -570 -> 238
    //   811: goto -552 -> 259
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	814	0	this	MmsBackupAgent
    //   0	814	1	paramBackupMeta	miui.app.backup.BackupMeta
    //   0	814	2	paramParcelFileDescriptor	android.os.ParcelFileDescriptor
    //   163	435	3	i	int
    //   114	485	4	j	int
    //   166	109	5	k	int
    //   117	107	6	m	int
    //   123	103	7	n	int
    //   159	62	8	localObject1	Object
    //   236	10	8	localFileNotFoundException1	java.io.FileNotFoundException
    //   261	8	8	localException	Exception
    //   280	10	8	localIOException1	java.io.IOException
    //   349	219	8	localContentResolver	ContentResolver
    //   606	48	8	localFileNotFoundException2	java.io.FileNotFoundException
    //   657	10	8	localBackupMeta	miui.app.backup.BackupMeta
    //   683	13	8	localRemoteException1	RemoteException
    //   712	13	8	localOperationApplicationException1	OperationApplicationException
    //   752	5	8	localObject2	Object
    //   762	1	8	localOperationApplicationException2	OperationApplicationException
    //   767	1	8	localRemoteException2	RemoteException
    //   772	1	8	localIOException2	java.io.IOException
    //   777	1	8	localFileNotFoundException3	java.io.FileNotFoundException
    //   785	5	8	localObject3	Object
    //   795	1	8	localIOException3	java.io.IOException
    //   803	1	8	localFileNotFoundException4	java.io.FileNotFoundException
    //   30	776	9	localArrayList	ArrayList
    //   25	773	10	localObject4	Object
    //   355	360	11	localObject5	Object
    //   539	12	12	localContentProviderOperation	ContentProviderOperation
    // Exception table:
    //   from	to	target	type
    //   59	67	236	java/io/FileNotFoundException
    //   82	113	236	java/io/FileNotFoundException
    //   119	141	236	java/io/FileNotFoundException
    //   141	161	236	java/io/FileNotFoundException
    //   168	180	236	java/io/FileNotFoundException
    //   201	205	236	java/io/FileNotFoundException
    //   208	214	236	java/io/FileNotFoundException
    //   220	230	236	java/io/FileNotFoundException
    //   263	274	236	java/io/FileNotFoundException
    //   306	310	236	java/io/FileNotFoundException
    //   168	180	261	java/lang/Exception
    //   201	205	261	java/lang/Exception
    //   59	67	280	java/io/IOException
    //   82	113	280	java/io/IOException
    //   119	141	280	java/io/IOException
    //   141	161	280	java/io/IOException
    //   168	180	280	java/io/IOException
    //   201	205	280	java/io/IOException
    //   208	214	280	java/io/IOException
    //   220	230	280	java/io/IOException
    //   263	274	280	java/io/IOException
    //   306	310	280	java/io/IOException
    //   47	59	321	finally
    //   240	251	321	finally
    //   284	295	321	finally
    //   400	414	606	java/io/FileNotFoundException
    //   429	481	606	java/io/FileNotFoundException
    //   483	508	606	java/io/FileNotFoundException
    //   508	584	606	java/io/FileNotFoundException
    //   584	590	606	java/io/FileNotFoundException
    //   594	603	606	java/io/FileNotFoundException
    //   632	641	606	java/io/FileNotFoundException
    //   388	400	652	java/io/IOException
    //   388	400	683	android/os/RemoteException
    //   388	400	712	android/content/OperationApplicationException
    //   388	400	741	finally
    //   610	621	741	finally
    //   661	672	741	finally
    //   690	701	741	finally
    //   719	730	741	finally
    //   400	414	752	finally
    //   429	481	752	finally
    //   483	508	752	finally
    //   508	584	752	finally
    //   584	590	752	finally
    //   594	603	752	finally
    //   632	641	752	finally
    //   400	414	762	android/content/OperationApplicationException
    //   429	481	762	android/content/OperationApplicationException
    //   483	508	762	android/content/OperationApplicationException
    //   508	584	762	android/content/OperationApplicationException
    //   584	590	762	android/content/OperationApplicationException
    //   594	603	762	android/content/OperationApplicationException
    //   632	641	762	android/content/OperationApplicationException
    //   400	414	767	android/os/RemoteException
    //   429	481	767	android/os/RemoteException
    //   483	508	767	android/os/RemoteException
    //   508	584	767	android/os/RemoteException
    //   584	590	767	android/os/RemoteException
    //   594	603	767	android/os/RemoteException
    //   632	641	767	android/os/RemoteException
    //   400	414	772	java/io/IOException
    //   429	481	772	java/io/IOException
    //   483	508	772	java/io/IOException
    //   508	584	772	java/io/IOException
    //   584	590	772	java/io/IOException
    //   594	603	772	java/io/IOException
    //   632	641	772	java/io/IOException
    //   388	400	777	java/io/FileNotFoundException
    //   59	67	785	finally
    //   82	113	785	finally
    //   119	141	785	finally
    //   141	161	785	finally
    //   168	180	785	finally
    //   201	205	785	finally
    //   208	214	785	finally
    //   220	230	785	finally
    //   263	274	785	finally
    //   306	310	785	finally
    //   47	59	795	java/io/IOException
    //   47	59	803	java/io/FileNotFoundException
  }
  
  /* Error */
  protected int onFullBackup(android.os.ParcelFileDescriptor paramParcelFileDescriptor, int paramInt)
    throws java.io.IOException
  {
    // Byte code:
    //   0: aload_0
    //   1: aload_0
    //   2: invokestatic 284	miui/app/backup/BackupManager:getBackupManager	(Landroid/content/Context;)Lmiui/app/backup/BackupManager;
    //   5: putfield 286	com/android/mms/backup/MmsBackupAgent:mBackupManager	Lmiui/app/backup/BackupManager;
    //   8: iload_2
    //   9: iconst_1
    //   10: if_icmpne +209 -> 219
    //   13: invokestatic 395	com/android/mms/backup/SyncRootProtos$SyncRoot:newBuilder	()Lcom/android/mms/backup/SyncRootProtos$SyncRoot$Builder;
    //   16: astore 4
    //   18: aload_0
    //   19: new 210	com/android/mms/backup/SmsManager
    //   22: dup
    //   23: aload_0
    //   24: invokevirtual 299	com/android/mms/backup/MmsBackupAgent:getApplicationContext	()Landroid/content/Context;
    //   27: invokespecial 302	com/android/mms/backup/SmsManager:<init>	(Landroid/content/Context;)V
    //   30: putfield 208	com/android/mms/backup/MmsBackupAgent:mSmsManager	Lcom/android/mms/backup/SmsManager;
    //   33: invokestatic 398	com/android/mms/backup/SmsProtos$MmsSms:newBuilder	()Lcom/android/mms/backup/SmsProtos$MmsSms$Builder;
    //   36: astore 5
    //   38: aload_0
    //   39: getfield 208	com/android/mms/backup/MmsBackupAgent:mSmsManager	Lcom/android/mms/backup/SmsManager;
    //   42: invokevirtual 402	com/android/mms/backup/SmsManager:prepareAllSmsIds	()Ljava/util/Vector;
    //   45: astore 6
    //   47: iconst_0
    //   48: istore_2
    //   49: aload 6
    //   51: invokevirtual 405	java/util/Vector:size	()I
    //   54: istore_3
    //   55: aload_0
    //   56: getfield 286	com/android/mms/backup/MmsBackupAgent:mBackupManager	Lmiui/app/backup/BackupManager;
    //   59: iconst_1
    //   60: iconst_0
    //   61: iload_3
    //   62: invokevirtual 340	miui/app/backup/BackupManager:setCustomProgress	(III)V
    //   65: aload 6
    //   67: invokevirtual 406	java/util/Vector:iterator	()Ljava/util/Iterator;
    //   70: astore 6
    //   72: aload 6
    //   74: invokeinterface 350 1 0
    //   79: ifeq +79 -> 158
    //   82: aload 6
    //   84: invokeinterface 354 1 0
    //   89: checkcast 64	java/lang/String
    //   92: astore 7
    //   94: aload_0
    //   95: getfield 208	com/android/mms/backup/MmsBackupAgent:mSmsManager	Lcom/android/mms/backup/SmsManager;
    //   98: aload 7
    //   100: invokestatic 410	java/lang/Long:parseLong	(Ljava/lang/String;)J
    //   103: invokevirtual 414	com/android/mms/backup/SmsManager:load	(J)Lcom/android/mms/backup/SmsProtos$Sms;
    //   106: astore 7
    //   108: aload 7
    //   110: ifnull +11 -> 121
    //   113: aload 5
    //   115: aload 7
    //   117: invokevirtual 420	com/android/mms/backup/SmsProtos$MmsSms$Builder:addSms	(Lcom/android/mms/backup/SmsProtos$Sms;)Lcom/android/mms/backup/SmsProtos$MmsSms$Builder;
    //   120: pop
    //   121: aload_0
    //   122: getfield 286	com/android/mms/backup/MmsBackupAgent:mBackupManager	Lmiui/app/backup/BackupManager;
    //   125: astore 7
    //   127: iload_2
    //   128: iconst_1
    //   129: iadd
    //   130: istore_2
    //   131: aload 7
    //   133: iconst_1
    //   134: iload_2
    //   135: iload_3
    //   136: invokevirtual 340	miui/app/backup/BackupManager:setCustomProgress	(III)V
    //   139: goto -67 -> 72
    //   142: astore 7
    //   144: ldc -111
    //   146: ldc_w 422
    //   149: aload 7
    //   151: invokestatic 153	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   154: pop
    //   155: goto -34 -> 121
    //   158: aload 4
    //   160: aload 5
    //   162: invokevirtual 424	com/android/mms/backup/SmsProtos$MmsSms$Builder:build	()Lcom/android/mms/backup/SmsProtos$MmsSms;
    //   165: invokevirtual 430	com/android/mms/backup/SyncRootProtos$SyncRoot$Builder:setMmsSms	(Lcom/android/mms/backup/SmsProtos$MmsSms;)Lcom/android/mms/backup/SyncRootProtos$SyncRoot$Builder;
    //   168: pop
    //   169: aconst_null
    //   170: astore 5
    //   172: new 432	java/io/FileOutputStream
    //   175: dup
    //   176: aload_1
    //   177: invokevirtual 255	android/os/ParcelFileDescriptor:getFileDescriptor	()Ljava/io/FileDescriptor;
    //   180: invokespecial 433	java/io/FileOutputStream:<init>	(Ljava/io/FileDescriptor;)V
    //   183: astore_1
    //   184: aload 4
    //   186: invokevirtual 436	com/android/mms/backup/SyncRootProtos$SyncRoot$Builder:build	()Lcom/android/mms/backup/SyncRootProtos$SyncRoot;
    //   189: aload_1
    //   190: invokevirtual 440	com/android/mms/backup/SyncRootProtos$SyncRoot:writeTo	(Ljava/io/OutputStream;)V
    //   193: aload_1
    //   194: ifnull +7 -> 201
    //   197: aload_1
    //   198: invokevirtual 441	java/io/FileOutputStream:close	()V
    //   201: iconst_0
    //   202: ireturn
    //   203: astore 4
    //   205: aload 5
    //   207: astore_1
    //   208: aload_1
    //   209: ifnull +7 -> 216
    //   212: aload_1
    //   213: invokevirtual 441	java/io/FileOutputStream:close	()V
    //   216: aload 4
    //   218: athrow
    //   219: iload_2
    //   220: iconst_2
    //   221: if_icmpne -20 -> 201
    //   224: invokestatic 395	com/android/mms/backup/SyncRootProtos$SyncRoot:newBuilder	()Lcom/android/mms/backup/SyncRootProtos$SyncRoot$Builder;
    //   227: astore 4
    //   229: aload_0
    //   230: new 139	com/android/mms/backup/MmsManager
    //   233: dup
    //   234: aload_0
    //   235: invokevirtual 299	com/android/mms/backup/MmsBackupAgent:getApplicationContext	()Landroid/content/Context;
    //   238: invokespecial 367	com/android/mms/backup/MmsManager:<init>	(Landroid/content/Context;)V
    //   241: putfield 137	com/android/mms/backup/MmsBackupAgent:mMmsManager	Lcom/android/mms/backup/MmsManager;
    //   244: invokestatic 444	com/android/mms/backup/MmsProtos$MmsCollection:newBuilder	()Lcom/android/mms/backup/MmsProtos$MmsCollection$Builder;
    //   247: astore 5
    //   249: aload_0
    //   250: getfield 137	com/android/mms/backup/MmsBackupAgent:mMmsManager	Lcom/android/mms/backup/MmsManager;
    //   253: invokevirtual 447	com/android/mms/backup/MmsManager:prepareAllMmsIds	()Ljava/util/Vector;
    //   256: astore 6
    //   258: aload_0
    //   259: new 226	java/util/HashMap
    //   262: dup
    //   263: invokespecial 366	java/util/HashMap:<init>	()V
    //   266: putfield 224	com/android/mms/backup/MmsBackupAgent:mAttach2Uri	Ljava/util/HashMap;
    //   269: aload_0
    //   270: getfield 137	com/android/mms/backup/MmsBackupAgent:mMmsManager	Lcom/android/mms/backup/MmsManager;
    //   273: aload_0
    //   274: getfield 224	com/android/mms/backup/MmsBackupAgent:mAttach2Uri	Ljava/util/HashMap;
    //   277: invokevirtual 381	com/android/mms/backup/MmsManager:setMmsAttach	(Ljava/util/HashMap;)V
    //   280: iconst_0
    //   281: istore_2
    //   282: aload 6
    //   284: invokevirtual 405	java/util/Vector:size	()I
    //   287: istore_3
    //   288: aload_0
    //   289: getfield 286	com/android/mms/backup/MmsBackupAgent:mBackupManager	Lmiui/app/backup/BackupManager;
    //   292: iconst_1
    //   293: iconst_0
    //   294: iload_3
    //   295: invokevirtual 340	miui/app/backup/BackupManager:setCustomProgress	(III)V
    //   298: aload 6
    //   300: invokevirtual 406	java/util/Vector:iterator	()Ljava/util/Iterator;
    //   303: astore 6
    //   305: aload 6
    //   307: invokeinterface 350 1 0
    //   312: ifeq +79 -> 391
    //   315: aload 6
    //   317: invokeinterface 354 1 0
    //   322: checkcast 64	java/lang/String
    //   325: astore 7
    //   327: aload_0
    //   328: getfield 137	com/android/mms/backup/MmsBackupAgent:mMmsManager	Lcom/android/mms/backup/MmsManager;
    //   331: aload 7
    //   333: invokestatic 410	java/lang/Long:parseLong	(Ljava/lang/String;)J
    //   336: invokevirtual 451	com/android/mms/backup/MmsManager:backupToProtocolBuffer	(J)Lcom/android/mms/backup/MmsProtos$Pdu;
    //   339: astore 7
    //   341: aload 7
    //   343: ifnull +11 -> 354
    //   346: aload 5
    //   348: aload 7
    //   350: invokevirtual 457	com/android/mms/backup/MmsProtos$MmsCollection$Builder:addPdus	(Lcom/android/mms/backup/MmsProtos$Pdu;)Lcom/android/mms/backup/MmsProtos$MmsCollection$Builder;
    //   353: pop
    //   354: aload_0
    //   355: getfield 286	com/android/mms/backup/MmsBackupAgent:mBackupManager	Lmiui/app/backup/BackupManager;
    //   358: astore 7
    //   360: iload_2
    //   361: iconst_1
    //   362: iadd
    //   363: istore_2
    //   364: aload 7
    //   366: iconst_1
    //   367: iload_2
    //   368: iload_3
    //   369: invokevirtual 340	miui/app/backup/BackupManager:setCustomProgress	(III)V
    //   372: goto -67 -> 305
    //   375: astore 7
    //   377: ldc -111
    //   379: ldc_w 459
    //   382: aload 7
    //   384: invokestatic 153	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   387: pop
    //   388: goto -34 -> 354
    //   391: aload 4
    //   393: aload 5
    //   395: invokevirtual 461	com/android/mms/backup/MmsProtos$MmsCollection$Builder:build	()Lcom/android/mms/backup/MmsProtos$MmsCollection;
    //   398: invokevirtual 465	com/android/mms/backup/SyncRootProtos$SyncRoot$Builder:setMmsCollection	(Lcom/android/mms/backup/MmsProtos$MmsCollection;)Lcom/android/mms/backup/SyncRootProtos$SyncRoot$Builder;
    //   401: pop
    //   402: aconst_null
    //   403: astore 5
    //   405: new 432	java/io/FileOutputStream
    //   408: dup
    //   409: aload_1
    //   410: invokevirtual 255	android/os/ParcelFileDescriptor:getFileDescriptor	()Ljava/io/FileDescriptor;
    //   413: invokespecial 433	java/io/FileOutputStream:<init>	(Ljava/io/FileDescriptor;)V
    //   416: astore_1
    //   417: aload 4
    //   419: invokevirtual 436	com/android/mms/backup/SyncRootProtos$SyncRoot$Builder:build	()Lcom/android/mms/backup/SyncRootProtos$SyncRoot;
    //   422: aload_1
    //   423: invokevirtual 440	com/android/mms/backup/SyncRootProtos$SyncRoot:writeTo	(Ljava/io/OutputStream;)V
    //   426: aload_1
    //   427: ifnull +7 -> 434
    //   430: aload_1
    //   431: invokevirtual 441	java/io/FileOutputStream:close	()V
    //   434: aload_0
    //   435: getfield 224	com/android/mms/backup/MmsBackupAgent:mAttach2Uri	Ljava/util/HashMap;
    //   438: invokevirtual 469	java/util/HashMap:entrySet	()Ljava/util/Set;
    //   441: invokeinterface 472 1 0
    //   446: astore_1
    //   447: aload_1
    //   448: invokeinterface 350 1 0
    //   453: ifeq -252 -> 201
    //   456: aload_1
    //   457: invokeinterface 354 1 0
    //   462: checkcast 474	java/util/Map$Entry
    //   465: astore 4
    //   467: aload 4
    //   469: invokeinterface 477 1 0
    //   474: checkcast 64	java/lang/String
    //   477: astore 5
    //   479: aload_0
    //   480: aload 4
    //   482: invokeinterface 480 1 0
    //   487: checkcast 52	android/net/Uri
    //   490: aload 5
    //   492: invokevirtual 484	com/android/mms/backup/MmsBackupAgent:addAttachedFile	(Landroid/net/Uri;Ljava/lang/String;)V
    //   495: goto -48 -> 447
    //   498: astore 4
    //   500: aload 5
    //   502: astore_1
    //   503: aload_1
    //   504: ifnull +7 -> 511
    //   507: aload_1
    //   508: invokevirtual 441	java/io/FileOutputStream:close	()V
    //   511: aload 4
    //   513: athrow
    //   514: astore 4
    //   516: goto -13 -> 503
    //   519: astore 4
    //   521: goto -313 -> 208
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	524	0	this	MmsBackupAgent
    //   0	524	1	paramParcelFileDescriptor	android.os.ParcelFileDescriptor
    //   0	524	2	paramInt	int
    //   54	315	3	i	int
    //   16	169	4	localBuilder	SyncRootProtos.SyncRoot.Builder
    //   203	14	4	localObject1	Object
    //   227	254	4	localObject2	Object
    //   498	14	4	localObject3	Object
    //   514	1	4	localObject4	Object
    //   519	1	4	localObject5	Object
    //   36	465	5	localObject6	Object
    //   45	271	6	localObject7	Object
    //   92	40	7	localObject8	Object
    //   142	8	7	localException1	Exception
    //   325	40	7	localObject9	Object
    //   375	8	7	localException2	Exception
    // Exception table:
    //   from	to	target	type
    //   94	108	142	java/lang/Exception
    //   113	121	142	java/lang/Exception
    //   172	184	203	finally
    //   327	341	375	java/lang/Exception
    //   346	354	375	java/lang/Exception
    //   405	417	498	finally
    //   417	426	514	finally
    //   184	193	519	finally
  }
}

/* Location:
 * Qualified Name:     com.android.mms.backup.MmsBackupAgent
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */