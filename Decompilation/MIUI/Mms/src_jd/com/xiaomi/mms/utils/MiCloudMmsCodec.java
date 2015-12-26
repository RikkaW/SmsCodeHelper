package com.xiaomi.mms.utils;

import android.content.ContentProviderOperation;
import android.content.ContentProviderOperation.Builder;
import android.content.ContentProviderResult;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.RemoteException;
import android.provider.Telephony.Mms;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import miui.util.GZIPCodec;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MiCloudMmsCodec
{
  private static final String[] MMS_PART_PROJECTION = { "_id", "ct", "cid", "cl", "text" };
  
  public static void decodeMmsBody(Context paramContext, long paramLong, byte[] paramArrayOfByte, boolean paramBoolean)
    throws IOException, JSONException, RemoteException, OperationApplicationException
  {
    ArrayList localArrayList1 = new ArrayList();
    ArrayList localArrayList2 = new ArrayList();
    makeMmsPartWriteOps(localArrayList1, localArrayList2, paramLong, paramArrayOfByte, paramBoolean);
    writeMmsPartFiles(paramContext.getContentResolver(), localArrayList1, localArrayList2);
  }
  
  public static byte[] encodeMmsBody(ContentResolver paramContentResolver, long paramLong, boolean paramBoolean)
    throws JSONException, IOException
  {
    Object localObject2 = Telephony.Mms.CONTENT_URI.buildUpon().appendPath(String.valueOf(paramLong)).appendPath("part");
    if (paramBoolean) {}
    for (Object localObject1 = "1";; localObject1 = "0")
    {
      localObject1 = paramContentResolver.query(((Uri.Builder)localObject2).appendQueryParameter("caller_is_syncadapter", (String)localObject1).build(), MMS_PART_PROJECTION, null, null, null);
      if (localObject1 != null) {
        break;
      }
      Log.e("MiCloudMmsCodec", "encodeMmsBody: Failed to access mms info in database");
      throw new IOException();
    }
    try
    {
      if (((Cursor)localObject1).getCount() <= 0) {
        break label292;
      }
      localObject2 = new JSONArray();
      while (((Cursor)localObject1).moveToNext())
      {
        paramLong = ((Cursor)localObject1).getLong(0);
        Object localObject3 = ((Cursor)localObject1).getString(1);
        String str1 = ((Cursor)localObject1).getString(2);
        String str2 = ((Cursor)localObject1).getString(3);
        String str3 = ((Cursor)localObject1).getString(4);
        JSONObject localJSONObject = new JSONObject();
        localJSONObject.put("content_type", localObject3);
        localJSONObject.put("content_id", str1);
        if (str2 != null) {
          localJSONObject.put("content_location", str2);
        }
        if (!TextUtils.isEmpty(str3)) {
          localJSONObject.put("text", str3);
        }
        localObject3 = readMmsPart(paramContentResolver, paramLong, paramBoolean);
        if (localObject3 != null) {
          localJSONObject.put("data", Base64.encodeToString((byte[])localObject3, 0));
        }
        ((JSONArray)localObject2).put(localJSONObject);
      }
      paramContentResolver = GZIPCodec.encode(((JSONArray)localObject2).toString().getBytes(StandardCharsets.UTF_8));
    }
    finally
    {
      ((Cursor)localObject1).close();
    }
    ((Cursor)localObject1).close();
    return paramContentResolver;
    label292:
    ((Cursor)localObject1).close();
    return null;
  }
  
  public static void makeMmsPartWriteOps(ArrayList<ContentProviderOperation> paramArrayList, ArrayList<byte[]> paramArrayList1, long paramLong, byte[] paramArrayOfByte, boolean paramBoolean)
    throws IOException, JSONException
  {
    Object localObject2 = Telephony.Mms.CONTENT_URI.buildUpon().appendPath(String.valueOf(paramLong)).appendPath("part");
    Object localObject1;
    int i;
    label100:
    Object localObject3;
    int j;
    label141:
    ContentValues localContentValues;
    if (paramBoolean)
    {
      localObject1 = "1";
      localObject1 = ((Uri.Builder)localObject2).appendQueryParameter("caller_is_syncadapter", (String)localObject1).appendQueryParameter("supress_making_mms_preview", "1").build();
      localObject2 = ContentProviderOperation.newDelete((Uri)localObject1).build();
      paramArrayList1.add(null);
      paramArrayList.add(localObject2);
      paramArrayOfByte = new JSONArray(new String(GZIPCodec.decode(paramArrayOfByte), StandardCharsets.UTF_8));
      i = 0;
      if (i >= paramArrayOfByte.length()) {
        return;
      }
      localObject3 = paramArrayOfByte.getJSONObject(i);
      localObject2 = ((JSONObject)localObject3).getString("content_type");
      if (!((String)localObject2).equals("application/smil")) {
        break label336;
      }
      j = -1;
      String str1 = ((JSONObject)localObject3).optString("content_id", null);
      String str2 = ((JSONObject)localObject3).optString("content_location", null);
      String str3 = ((JSONObject)localObject3).optString("text");
      localObject3 = Base64.decode(((JSONObject)localObject3).optString("data"), 0);
      localContentValues = new ContentValues();
      localContentValues.put("mid", Long.valueOf(paramLong));
      localContentValues.put("seq", Integer.valueOf(j));
      localContentValues.put("ct", (String)localObject2);
      localContentValues.put("cid", str1);
      localContentValues.put("cl", str2);
      if (((String)localObject2).equals("text/plain")) {
        localContentValues.put("chset", Integer.valueOf(106));
      }
      if ((!((String)localObject2).equals("text/plain")) && (!((String)localObject2).equals("application/smil"))) {
        break label342;
      }
      localContentValues.put("text", str3);
      paramArrayList1.add(null);
    }
    for (;;)
    {
      paramArrayList.add(ContentProviderOperation.newInsert((Uri)localObject1).withValues(localContentValues).build());
      i += 1;
      break label100;
      localObject1 = "0";
      break;
      label336:
      j = 0;
      break label141;
      label342:
      paramArrayList1.add(localObject3);
    }
  }
  
  /* Error */
  private static byte[] readMmsPart(ContentResolver paramContentResolver, long paramLong, boolean paramBoolean)
    throws IOException
  {
    // Byte code:
    //   0: getstatic 62	android/provider/Telephony$Mms:CONTENT_URI	Landroid/net/Uri;
    //   3: invokevirtual 68	android/net/Uri:buildUpon	()Landroid/net/Uri$Builder;
    //   6: ldc 80
    //   8: invokevirtual 78	android/net/Uri$Builder:appendPath	(Ljava/lang/String;)Landroid/net/Uri$Builder;
    //   11: lload_1
    //   12: invokestatic 72	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   15: invokevirtual 78	android/net/Uri$Builder:appendPath	(Ljava/lang/String;)Landroid/net/Uri$Builder;
    //   18: astore 6
    //   20: iload_3
    //   21: ifeq +105 -> 126
    //   24: ldc 82
    //   26: astore 5
    //   28: aload 6
    //   30: ldc 84
    //   32: aload 5
    //   34: invokevirtual 88	android/net/Uri$Builder:appendQueryParameter	(Ljava/lang/String;Ljava/lang/String;)Landroid/net/Uri$Builder;
    //   37: invokevirtual 92	android/net/Uri$Builder:build	()Landroid/net/Uri;
    //   40: astore 10
    //   42: aconst_null
    //   43: astore 6
    //   45: aconst_null
    //   46: astore 5
    //   48: aconst_null
    //   49: astore 9
    //   51: aconst_null
    //   52: astore 8
    //   54: aload_0
    //   55: aload 10
    //   57: invokevirtual 283	android/content/ContentResolver:openInputStream	(Landroid/net/Uri;)Ljava/io/InputStream;
    //   60: astore_0
    //   61: aload_0
    //   62: astore 5
    //   64: aload_0
    //   65: astore 6
    //   67: new 285	java/io/ByteArrayOutputStream
    //   70: dup
    //   71: invokespecial 286	java/io/ByteArrayOutputStream:<init>	()V
    //   74: astore 7
    //   76: aload_0
    //   77: ifnonnull +56 -> 133
    //   80: new 30	java/io/IOException
    //   83: dup
    //   84: new 288	java/lang/StringBuilder
    //   87: dup
    //   88: invokespecial 289	java/lang/StringBuilder:<init>	()V
    //   91: ldc_w 291
    //   94: invokevirtual 295	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   97: aload 10
    //   99: invokevirtual 298	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   102: invokevirtual 299	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   105: invokespecial 300	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   108: athrow
    //   109: astore 5
    //   111: aload 7
    //   113: astore 5
    //   115: aload_0
    //   116: invokestatic 306	com/xiaomi/mms/utils/IOUtils:closeQuietly	(Ljava/io/InputStream;)V
    //   119: aload 5
    //   121: invokestatic 309	com/xiaomi/mms/utils/IOUtils:closeQuietly	(Ljava/io/OutputStream;)V
    //   124: aconst_null
    //   125: areturn
    //   126: ldc 111
    //   128: astore 5
    //   130: goto -102 -> 28
    //   133: sipush 4096
    //   136: newarray <illegal type>
    //   138: astore 5
    //   140: aload_0
    //   141: aload 5
    //   143: invokevirtual 315	java/io/InputStream:read	([B)I
    //   146: istore 4
    //   148: iload 4
    //   150: ifle +34 -> 184
    //   153: aload 7
    //   155: aload 5
    //   157: iconst_0
    //   158: iload 4
    //   160: invokevirtual 319	java/io/ByteArrayOutputStream:write	([BII)V
    //   163: goto -23 -> 140
    //   166: astore 6
    //   168: aload 7
    //   170: astore 5
    //   172: aload_0
    //   173: invokestatic 306	com/xiaomi/mms/utils/IOUtils:closeQuietly	(Ljava/io/InputStream;)V
    //   176: aload 5
    //   178: invokestatic 309	com/xiaomi/mms/utils/IOUtils:closeQuietly	(Ljava/io/OutputStream;)V
    //   181: aload 6
    //   183: athrow
    //   184: aload 7
    //   186: invokevirtual 323	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   189: astore 5
    //   191: aload_0
    //   192: invokestatic 306	com/xiaomi/mms/utils/IOUtils:closeQuietly	(Ljava/io/InputStream;)V
    //   195: aload 7
    //   197: invokestatic 309	com/xiaomi/mms/utils/IOUtils:closeQuietly	(Ljava/io/OutputStream;)V
    //   200: aload 5
    //   202: areturn
    //   203: astore 6
    //   205: aload 5
    //   207: astore_0
    //   208: aload 9
    //   210: astore 5
    //   212: goto -40 -> 172
    //   215: astore_0
    //   216: aload 6
    //   218: astore_0
    //   219: aload 8
    //   221: astore 5
    //   223: goto -108 -> 115
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	226	0	paramContentResolver	ContentResolver
    //   0	226	1	paramLong	long
    //   0	226	3	paramBoolean	boolean
    //   146	13	4	i	int
    //   26	37	5	localObject1	Object
    //   109	1	5	localFileNotFoundException	java.io.FileNotFoundException
    //   113	109	5	localObject2	Object
    //   18	48	6	localObject3	Object
    //   166	16	6	localObject4	Object
    //   203	14	6	localObject5	Object
    //   74	122	7	localByteArrayOutputStream	java.io.ByteArrayOutputStream
    //   52	168	8	localObject6	Object
    //   49	160	9	localObject7	Object
    //   40	58	10	localUri	Uri
    // Exception table:
    //   from	to	target	type
    //   80	109	109	java/io/FileNotFoundException
    //   133	140	109	java/io/FileNotFoundException
    //   140	148	109	java/io/FileNotFoundException
    //   153	163	109	java/io/FileNotFoundException
    //   184	191	109	java/io/FileNotFoundException
    //   80	109	166	finally
    //   133	140	166	finally
    //   140	148	166	finally
    //   153	163	166	finally
    //   184	191	166	finally
    //   54	61	203	finally
    //   67	76	203	finally
    //   54	61	215	java/io/FileNotFoundException
    //   67	76	215	java/io/FileNotFoundException
  }
  
  public static void writeMmsPartFiles(ContentResolver paramContentResolver, ArrayList<ContentProviderOperation> paramArrayList, ArrayList<byte[]> paramArrayList1)
    throws RemoteException, OperationApplicationException, IOException
  {
    ContentProviderResult[] arrayOfContentProviderResult = paramContentResolver.applyBatch("mms", paramArrayList);
    int i = 0;
    byte[] arrayOfByte;
    if (i < arrayOfContentProviderResult.length)
    {
      arrayOfByte = (byte[])paramArrayList1.get(i);
      if (arrayOfByte != null) {}
    }
    for (;;)
    {
      i += 1;
      break;
      Uri localUri = uri.buildUpon().appendQueryParameter("supress_making_mms_preview", "1").build();
      OutputStream localOutputStream = paramContentResolver.openOutputStream(localUri);
      try
      {
        localOutputStream.write(arrayOfByte);
        Log.v("MiCloudMmsCodec", "wrote " + arrayOfByte.length + " bytes to " + localUri);
        IOUtils.closeQuietly(localOutputStream);
      }
      finally
      {
        IOUtils.closeQuietly(localOutputStream);
      }
    }
    paramArrayList1.clear();
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.utils.MiCloudMmsCodec
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */