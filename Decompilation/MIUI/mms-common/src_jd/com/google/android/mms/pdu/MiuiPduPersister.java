package com.google.android.mms.pdu;

import android.content.ContentProviderOperation;
import android.content.ContentProviderOperation.Builder;
import android.content.ContentProviderResult;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.RemoteException;
import android.provider.Telephony.Mms;
import android.provider.Telephony.Mms.Draft;
import android.provider.Telephony.Mms.Inbox;
import android.provider.Telephony.Mms.Outbox;
import android.provider.Telephony.Mms.Sent;
import android.provider.Telephony.MmsSms.PendingMessages;
import android.provider.Telephony.Threads;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.collect.Lists;
import com.google.android.mms.ContentType;
import com.google.android.mms.InvalidHeaderValueException;
import com.google.android.mms.MmsException;
import com.google.android.mms.util.PduCache;
import com.google.android.mms.util.PduCacheEntry;
import com.google.android.mms.util.SqliteWrapper;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import miui.provider.ExtraTelephony;

public class MiuiPduPersister
{
  private static final int[] ADDRESS_FIELDS;
  private static final HashMap<Integer, Integer> CHARSET_COLUMN_INDEX_MAP;
  private static final HashMap<Integer, String> CHARSET_COLUMN_NAME_MAP;
  private static final boolean DEBUG = false;
  private static final HashMap<Integer, Integer> ENCODED_STRING_COLUMN_INDEX_MAP;
  private static final HashMap<Integer, String> ENCODED_STRING_COLUMN_NAME_MAP;
  private static final boolean LOCAL_LOGV = false;
  private static final HashMap<Integer, Integer> LONG_COLUMN_INDEX_MAP;
  private static final HashMap<Integer, String> LONG_COLUMN_NAME_MAP;
  private static final HashMap<Uri, Integer> MESSAGE_BOX_MAP;
  private static final HashMap<Integer, Integer> OCTET_COLUMN_INDEX_MAP;
  private static final HashMap<Integer, String> OCTET_COLUMN_NAME_MAP;
  private static final int PART_COLUMN_CHARSET = 1;
  private static final int PART_COLUMN_CONTENT_DISPOSITION = 2;
  private static final int PART_COLUMN_CONTENT_ID = 3;
  private static final int PART_COLUMN_CONTENT_LOCATION = 4;
  private static final int PART_COLUMN_CONTENT_TYPE = 5;
  private static final int PART_COLUMN_FILENAME = 6;
  private static final int PART_COLUMN_ID = 0;
  private static final int PART_COLUMN_NAME = 7;
  private static final int PART_COLUMN_TEXT = 8;
  private static final String[] PART_PROJECTION;
  private static final PduCache PDU_CACHE_INSTANCE;
  private static final int PDU_COLUMN_CONTENT_CLASS = 11;
  private static final int PDU_COLUMN_CONTENT_LOCATION = 5;
  private static final int PDU_COLUMN_CONTENT_TYPE = 6;
  private static final int PDU_COLUMN_DATE = 21;
  private static final int PDU_COLUMN_DELIVERY_REPORT = 12;
  private static final int PDU_COLUMN_DELIVERY_TIME = 22;
  private static final int PDU_COLUMN_EXPIRY = 23;
  private static final int PDU_COLUMN_ID = 0;
  private static final int PDU_COLUMN_MESSAGE_BOX = 1;
  private static final int PDU_COLUMN_MESSAGE_CLASS = 7;
  private static final int PDU_COLUMN_MESSAGE_ID = 8;
  private static final int PDU_COLUMN_MESSAGE_SIZE = 24;
  private static final int PDU_COLUMN_MESSAGE_TYPE = 13;
  private static final int PDU_COLUMN_MMS_VERSION = 14;
  private static final int PDU_COLUMN_PRIORITY = 15;
  private static final int PDU_COLUMN_READ_REPORT = 16;
  private static final int PDU_COLUMN_READ_STATUS = 17;
  private static final int PDU_COLUMN_REPORT_ALLOWED = 18;
  private static final int PDU_COLUMN_RESPONSE_TEXT = 9;
  private static final int PDU_COLUMN_RETRIEVE_STATUS = 19;
  private static final int PDU_COLUMN_RETRIEVE_TEXT = 3;
  private static final int PDU_COLUMN_RETRIEVE_TEXT_CHARSET = 26;
  private static final int PDU_COLUMN_STATUS = 20;
  private static final int PDU_COLUMN_SUBJECT = 4;
  private static final int PDU_COLUMN_SUBJECT_CHARSET = 25;
  private static final int PDU_COLUMN_THREAD_ID = 2;
  private static final int PDU_COLUMN_TRANSACTION_ID = 10;
  private static final String[] PDU_PROJECTION;
  public static final int PROC_STATUS_COMPLETED = 3;
  public static final int PROC_STATUS_PERMANENTLY_FAILURE = 2;
  public static final int PROC_STATUS_TRANSIENT_FAILURE = 1;
  private static final String TAG = "MiuiPduPersister";
  public static final String TEMPORARY_DRM_OBJECT_URI = "content://mms/9223372036854775807/part";
  private static final HashMap<Integer, Integer> TEXT_STRING_COLUMN_INDEX_MAP;
  private static final HashMap<Integer, String> TEXT_STRING_COLUMN_NAME_MAP;
  private static MiuiPduPersister sPersister;
  private final ContentResolver mContentResolver;
  private final Context mContext;
  
  static
  {
    if (!MiuiPduPersister.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      ADDRESS_FIELDS = new int[] { 129, 130, 137, 151 };
      PDU_PROJECTION = new String[] { "_id", "msg_box", "thread_id", "retr_txt", "sub", "ct_l", "ct_t", "m_cls", "m_id", "resp_txt", "tr_id", "ct_cls", "d_rpt", "m_type", "v", "pri", "rr", "read_status", "rpt_a", "retr_st", "st", "date", "d_tm", "exp", "m_size", "sub_cs", "retr_txt_cs" };
      PART_PROJECTION = new String[] { "_id", "chset", "cd", "cid", "cl", "ct", "fn", "name", "text" };
      MESSAGE_BOX_MAP = new HashMap();
      MESSAGE_BOX_MAP.put(Telephony.Mms.Inbox.CONTENT_URI, Integer.valueOf(1));
      MESSAGE_BOX_MAP.put(Telephony.Mms.Sent.CONTENT_URI, Integer.valueOf(2));
      MESSAGE_BOX_MAP.put(Telephony.Mms.Draft.CONTENT_URI, Integer.valueOf(3));
      MESSAGE_BOX_MAP.put(Telephony.Mms.Outbox.CONTENT_URI, Integer.valueOf(4));
      CHARSET_COLUMN_INDEX_MAP = new HashMap();
      CHARSET_COLUMN_INDEX_MAP.put(Integer.valueOf(150), Integer.valueOf(25));
      CHARSET_COLUMN_INDEX_MAP.put(Integer.valueOf(154), Integer.valueOf(26));
      CHARSET_COLUMN_NAME_MAP = new HashMap();
      CHARSET_COLUMN_NAME_MAP.put(Integer.valueOf(150), "sub_cs");
      CHARSET_COLUMN_NAME_MAP.put(Integer.valueOf(154), "retr_txt_cs");
      ENCODED_STRING_COLUMN_INDEX_MAP = new HashMap();
      ENCODED_STRING_COLUMN_INDEX_MAP.put(Integer.valueOf(154), Integer.valueOf(3));
      ENCODED_STRING_COLUMN_INDEX_MAP.put(Integer.valueOf(150), Integer.valueOf(4));
      ENCODED_STRING_COLUMN_NAME_MAP = new HashMap();
      ENCODED_STRING_COLUMN_NAME_MAP.put(Integer.valueOf(154), "retr_txt");
      ENCODED_STRING_COLUMN_NAME_MAP.put(Integer.valueOf(150), "sub");
      TEXT_STRING_COLUMN_INDEX_MAP = new HashMap();
      TEXT_STRING_COLUMN_INDEX_MAP.put(Integer.valueOf(131), Integer.valueOf(5));
      TEXT_STRING_COLUMN_INDEX_MAP.put(Integer.valueOf(132), Integer.valueOf(6));
      TEXT_STRING_COLUMN_INDEX_MAP.put(Integer.valueOf(138), Integer.valueOf(7));
      TEXT_STRING_COLUMN_INDEX_MAP.put(Integer.valueOf(139), Integer.valueOf(8));
      TEXT_STRING_COLUMN_INDEX_MAP.put(Integer.valueOf(147), Integer.valueOf(9));
      TEXT_STRING_COLUMN_INDEX_MAP.put(Integer.valueOf(152), Integer.valueOf(10));
      TEXT_STRING_COLUMN_NAME_MAP = new HashMap();
      TEXT_STRING_COLUMN_NAME_MAP.put(Integer.valueOf(131), "ct_l");
      TEXT_STRING_COLUMN_NAME_MAP.put(Integer.valueOf(132), "ct_t");
      TEXT_STRING_COLUMN_NAME_MAP.put(Integer.valueOf(138), "m_cls");
      TEXT_STRING_COLUMN_NAME_MAP.put(Integer.valueOf(139), "m_id");
      TEXT_STRING_COLUMN_NAME_MAP.put(Integer.valueOf(147), "resp_txt");
      TEXT_STRING_COLUMN_NAME_MAP.put(Integer.valueOf(152), "tr_id");
      OCTET_COLUMN_INDEX_MAP = new HashMap();
      OCTET_COLUMN_INDEX_MAP.put(Integer.valueOf(186), Integer.valueOf(11));
      OCTET_COLUMN_INDEX_MAP.put(Integer.valueOf(134), Integer.valueOf(12));
      OCTET_COLUMN_INDEX_MAP.put(Integer.valueOf(140), Integer.valueOf(13));
      OCTET_COLUMN_INDEX_MAP.put(Integer.valueOf(141), Integer.valueOf(14));
      OCTET_COLUMN_INDEX_MAP.put(Integer.valueOf(143), Integer.valueOf(15));
      OCTET_COLUMN_INDEX_MAP.put(Integer.valueOf(144), Integer.valueOf(16));
      OCTET_COLUMN_INDEX_MAP.put(Integer.valueOf(155), Integer.valueOf(17));
      OCTET_COLUMN_INDEX_MAP.put(Integer.valueOf(145), Integer.valueOf(18));
      OCTET_COLUMN_INDEX_MAP.put(Integer.valueOf(153), Integer.valueOf(19));
      OCTET_COLUMN_INDEX_MAP.put(Integer.valueOf(149), Integer.valueOf(20));
      OCTET_COLUMN_NAME_MAP = new HashMap();
      OCTET_COLUMN_NAME_MAP.put(Integer.valueOf(186), "ct_cls");
      OCTET_COLUMN_NAME_MAP.put(Integer.valueOf(134), "d_rpt");
      OCTET_COLUMN_NAME_MAP.put(Integer.valueOf(140), "m_type");
      OCTET_COLUMN_NAME_MAP.put(Integer.valueOf(141), "v");
      OCTET_COLUMN_NAME_MAP.put(Integer.valueOf(143), "pri");
      OCTET_COLUMN_NAME_MAP.put(Integer.valueOf(144), "rr");
      OCTET_COLUMN_NAME_MAP.put(Integer.valueOf(155), "read_status");
      OCTET_COLUMN_NAME_MAP.put(Integer.valueOf(145), "rpt_a");
      OCTET_COLUMN_NAME_MAP.put(Integer.valueOf(153), "retr_st");
      OCTET_COLUMN_NAME_MAP.put(Integer.valueOf(149), "st");
      LONG_COLUMN_INDEX_MAP = new HashMap();
      LONG_COLUMN_INDEX_MAP.put(Integer.valueOf(133), Integer.valueOf(21));
      LONG_COLUMN_INDEX_MAP.put(Integer.valueOf(135), Integer.valueOf(22));
      LONG_COLUMN_INDEX_MAP.put(Integer.valueOf(136), Integer.valueOf(23));
      LONG_COLUMN_INDEX_MAP.put(Integer.valueOf(142), Integer.valueOf(24));
      LONG_COLUMN_NAME_MAP = new HashMap();
      LONG_COLUMN_NAME_MAP.put(Integer.valueOf(133), "date_sent");
      LONG_COLUMN_NAME_MAP.put(Integer.valueOf(135), "d_tm");
      LONG_COLUMN_NAME_MAP.put(Integer.valueOf(136), "exp");
      LONG_COLUMN_NAME_MAP.put(Integer.valueOf(142), "m_size");
      PDU_CACHE_INSTANCE = PduCache.getInstance();
      return;
    }
  }
  
  private MiuiPduPersister(Context paramContext)
  {
    mContext = paramContext;
    mContentResolver = paramContext.getContentResolver();
  }
  
  public static String convertUriToPath(Context paramContext, Uri paramUri)
  {
    Object localObject1 = null;
    if (paramUri != null)
    {
      localObject1 = paramUri.getScheme();
      if ((localObject1 != null) && (!((String)localObject1).equals("")) && (!((String)localObject1).equals("file"))) {
        break label42;
      }
      localObject1 = paramUri.getPath();
    }
    label42:
    label165:
    do
    {
      return (String)localObject1;
      if (((String)localObject1).equals("http")) {
        return paramUri.toString();
      }
      if (!((String)localObject1).equals("content")) {
        break;
      }
      Object localObject2 = null;
      localObject1 = null;
      try
      {
        paramContext = paramContext.getContentResolver().query(paramUri, new String[] { "_data" }, null, null, null);
        if (paramContext != null)
        {
          localObject1 = paramContext;
          localObject2 = paramContext;
          if (paramContext.getCount() != 0)
          {
            localObject1 = paramContext;
            localObject2 = paramContext;
            if (paramContext.moveToFirst()) {
              break label165;
            }
          }
        }
        localObject1 = paramContext;
        localObject2 = paramContext;
        throw new IllegalArgumentException("Given Uri could not be found in media store");
      }
      catch (SQLiteException paramContext)
      {
        localObject2 = localObject1;
        throw new IllegalArgumentException("Given Uri is not formatted in a way so that it can be found in media store.");
      }
      finally
      {
        if (localObject2 != null) {
          ((Cursor)localObject2).close();
        }
      }
      localObject1 = paramContext;
      localObject2 = paramContext;
      paramUri = paramContext.getString(paramContext.getColumnIndexOrThrow("_data"));
      localObject1 = paramUri;
    } while (paramContext == null);
    paramContext.close();
    return paramUri;
    throw new IllegalArgumentException("Given Uri scheme is not supported");
  }
  
  private byte[] getByteArrayFromPartColumn(Cursor paramCursor, int paramInt)
  {
    if (!paramCursor.isNull(paramInt)) {
      return getBytes(paramCursor.getString(paramInt));
    }
    return null;
  }
  
  public static byte[] getBytes(String paramString)
  {
    try
    {
      paramString = paramString.getBytes("iso-8859-1");
      return paramString;
    }
    catch (UnsupportedEncodingException paramString)
    {
      Log.e("MiuiPduPersister", "ISO_8859_1 must be supported!", paramString);
    }
    return new byte[0];
  }
  
  private Integer getIntegerFromPartColumn(Cursor paramCursor, int paramInt)
  {
    if (!paramCursor.isNull(paramInt)) {
      return Integer.valueOf(paramCursor.getInt(paramInt));
    }
    return null;
  }
  
  public static MiuiPduPersister getPduPersister(Context paramContext)
  {
    if ((sPersister == null) || (!paramContext.equals(sPersistermContext))) {
      sPersister = new MiuiPduPersister(paramContext);
    }
    return sPersister;
  }
  
  private void loadAddress(long paramLong, PduHeaders paramPduHeaders)
  {
    Cursor localCursor = SqliteWrapper.query(mContext, mContentResolver, Uri.parse("content://mms/" + paramLong + "/addr"), new String[] { "address", "charset", "type" }, null, null, null);
    if (localCursor != null) {}
    for (;;)
    {
      String str;
      int i;
      try
      {
        if (!localCursor.moveToNext()) {
          break label254;
        }
        str = localCursor.getString(0);
        if (TextUtils.isEmpty(str)) {
          continue;
        }
        i = localCursor.getInt(2);
        switch (i)
        {
        case 137: 
          Log.e("MiuiPduPersister", "Unknown address type: " + i);
          continue;
          paramPduHeaders.setEncodedStringValue(new EncodedStringValue(localCursor.getInt(1), getBytes(str)), i);
        }
      }
      finally
      {
        localCursor.close();
      }
      continue;
      paramPduHeaders.appendEncodedStringValue(new EncodedStringValue(localCursor.getInt(1), getBytes(str)), i);
      continue;
      label254:
      localCursor.close();
      return;
    }
  }
  
  private PduPart[] loadParts(long paramLong)
    throws MmsException
  {
    Cursor localCursor = SqliteWrapper.query(mContext, mContentResolver, Uri.parse("content://mms/" + paramLong + "/part"), PART_PROJECTION, null, null, null);
    if (localCursor != null) {}
    for (;;)
    {
      int i;
      PduPart[] arrayOfPduPart;
      PduPart localPduPart;
      Object localObject5;
      Object localObject6;
      Object localObject7;
      ByteArrayOutputStream localByteArrayOutputStream;
      try
      {
        i = localCursor.getCount();
        if (i == 0) {
          return null;
        }
        arrayOfPduPart = new PduPart[localCursor.getCount()];
        i = 0;
        if (!localCursor.moveToNext()) {
          break label662;
        }
        localPduPart = new PduPart();
        Object localObject1 = getIntegerFromPartColumn(localCursor, 1);
        if (localObject1 != null) {
          localPduPart.setCharset(((Integer)localObject1).intValue());
        }
        localObject1 = getByteArrayFromPartColumn(localCursor, 2);
        if (localObject1 != null) {
          localPduPart.setContentDisposition((byte[])localObject1);
        }
        localObject1 = getByteArrayFromPartColumn(localCursor, 3);
        if (localObject1 != null) {
          localPduPart.setContentId((byte[])localObject1);
        }
        localObject1 = getByteArrayFromPartColumn(localCursor, 4);
        if (localObject1 != null) {
          localPduPart.setContentLocation((byte[])localObject1);
        }
        localObject1 = getByteArrayFromPartColumn(localCursor, 5);
        if (localObject1 != null)
        {
          localPduPart.setContentType((byte[])localObject1);
          localObject5 = getByteArrayFromPartColumn(localCursor, 6);
          if (localObject5 != null) {
            localPduPart.setFilename((byte[])localObject5);
          }
          localObject5 = getByteArrayFromPartColumn(localCursor, 7);
          if (localObject5 != null) {
            localPduPart.setName((byte[])localObject5);
          }
          paramLong = localCursor.getLong(0);
          localObject6 = Uri.parse("content://mms/part/" + paramLong);
          localPduPart.setDataUri((Uri)localObject6);
          localObject7 = toIsoString((byte[])localObject1);
          if ((ContentType.isImageType((String)localObject7)) || (ContentType.isAudioType((String)localObject7)) || (ContentType.isVideoType((String)localObject7))) {
            break label677;
          }
          localByteArrayOutputStream = new ByteArrayOutputStream();
          localObject5 = null;
          localObject1 = null;
          if ((!"text/plain".equals(localObject7)) && (!"application/smil".equals(localObject7)) && (!"text/html".equals(localObject7))) {
            break label474;
          }
          localObject1 = localCursor.getString(8);
          if (localObject1 != null)
          {
            localObject1 = new EncodedStringValue((String)localObject1).getTextString();
            localByteArrayOutputStream.write((byte[])localObject1, 0, localObject1.length);
            localPduPart.setData(localByteArrayOutputStream.toByteArray());
            break label677;
          }
        }
        else
        {
          throw new MmsException("Content-Type must be set.");
        }
      }
      finally
      {
        if (localCursor != null) {
          localCursor.close();
        }
      }
      Object localObject3 = "";
      continue;
      try
      {
        label474:
        localObject6 = mContentResolver.openInputStream((Uri)localObject6);
        localObject3 = localObject6;
        localObject5 = localObject6;
        localObject7 = new byte['Ā'];
        localObject3 = localObject6;
        localObject5 = localObject6;
        for (int j = ((InputStream)localObject6).read((byte[])localObject7); j >= 0; j = ((InputStream)localObject6).read((byte[])localObject7))
        {
          localObject3 = localObject6;
          localObject5 = localObject6;
          localByteArrayOutputStream.write((byte[])localObject7, 0, j);
          localObject3 = localObject6;
          localObject5 = localObject6;
        }
        if (localObject6 == null) {
          continue;
        }
        try
        {
          ((InputStream)localObject6).close();
        }
        catch (IOException localIOException1)
        {
          Log.e("MiuiPduPersister", "Failed to close stream", localIOException1);
        }
        continue;
      }
      catch (IOException localIOException3)
      {
        localObject5 = localIOException1;
        Log.e("MiuiPduPersister", "Failed to load part data", localIOException3);
        localObject5 = localIOException1;
        localCursor.close();
        localObject5 = localIOException1;
        throw new MmsException(localIOException3);
      }
      finally
      {
        if (localObject5 == null) {}
      }
      try
      {
        ((InputStream)localObject5).close();
        throw ((Throwable)localObject4);
      }
      catch (IOException localIOException2)
      {
        for (;;)
        {
          Log.e("MiuiPduPersister", "Failed to close stream", localIOException2);
        }
      }
      label662:
      if (localCursor != null) {
        localCursor.close();
      }
      return arrayOfPduPart;
      label677:
      arrayOfPduPart[i] = localPduPart;
      i += 1;
    }
  }
  
  private void persistAddress(ArrayList<ContentProviderOperation> paramArrayList, long paramLong, int paramInt, EncodedStringValue[] paramArrayOfEncodedStringValue)
  {
    ContentValues localContentValues = new ContentValues(3);
    int j = paramArrayOfEncodedStringValue.length;
    int i = 0;
    while (i < j)
    {
      EncodedStringValue localEncodedStringValue = paramArrayOfEncodedStringValue[i];
      localContentValues.clear();
      localContentValues.put("address", toIsoString(localEncodedStringValue.getTextString()));
      localContentValues.put("charset", Integer.valueOf(localEncodedStringValue.getCharacterSet()));
      localContentValues.put("type", Integer.valueOf(paramInt));
      paramArrayList.add(ContentProviderOperation.newInsert(Uri.parse("content://mms/" + paramLong + "/addr")).withValues(localContentValues).build());
      i += 1;
    }
  }
  
  /* Error */
  private void persistData(PduPart paramPduPart, Uri paramUri, String paramString)
    throws MmsException
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 32
    //   3: aconst_null
    //   4: astore 30
    //   6: aconst_null
    //   7: astore 24
    //   9: aconst_null
    //   10: astore 31
    //   12: aconst_null
    //   13: astore 27
    //   15: aconst_null
    //   16: astore 26
    //   18: aconst_null
    //   19: astore 29
    //   21: aconst_null
    //   22: astore 28
    //   24: aconst_null
    //   25: astore 35
    //   27: aconst_null
    //   28: astore 34
    //   30: aconst_null
    //   31: astore 39
    //   33: aconst_null
    //   34: astore 12
    //   36: aconst_null
    //   37: astore 33
    //   39: aconst_null
    //   40: astore 37
    //   42: aconst_null
    //   43: astore 19
    //   45: aconst_null
    //   46: astore 9
    //   48: aconst_null
    //   49: astore 36
    //   51: aconst_null
    //   52: astore 13
    //   54: aconst_null
    //   55: astore 25
    //   57: aconst_null
    //   58: astore 38
    //   60: aload 33
    //   62: astore 18
    //   64: aload 28
    //   66: astore 21
    //   68: aload 31
    //   70: astore 23
    //   72: aload 38
    //   74: astore 10
    //   76: aload 35
    //   78: astore 14
    //   80: aload 27
    //   82: astore 15
    //   84: aload 32
    //   86: astore 16
    //   88: aload 37
    //   90: astore 8
    //   92: aload 34
    //   94: astore 17
    //   96: aload 26
    //   98: astore 22
    //   100: aload 30
    //   102: astore 20
    //   104: aload 36
    //   106: astore 11
    //   108: aload_1
    //   109: invokevirtual 602	com/google/android/mms/pdu/PduPart:getData	()[B
    //   112: astore 40
    //   114: aload 33
    //   116: astore 18
    //   118: aload 28
    //   120: astore 21
    //   122: aload 31
    //   124: astore 23
    //   126: aload 38
    //   128: astore 10
    //   130: aload 35
    //   132: astore 14
    //   134: aload 27
    //   136: astore 15
    //   138: aload 32
    //   140: astore 16
    //   142: aload 37
    //   144: astore 8
    //   146: aload 34
    //   148: astore 17
    //   150: aload 26
    //   152: astore 22
    //   154: aload 30
    //   156: astore 20
    //   158: aload 36
    //   160: astore 11
    //   162: ldc_w 511
    //   165: aload_3
    //   166: invokevirtual 289	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   169: ifne +119 -> 288
    //   172: aload 33
    //   174: astore 18
    //   176: aload 28
    //   178: astore 21
    //   180: aload 31
    //   182: astore 23
    //   184: aload 38
    //   186: astore 10
    //   188: aload 35
    //   190: astore 14
    //   192: aload 27
    //   194: astore 15
    //   196: aload 32
    //   198: astore 16
    //   200: aload 37
    //   202: astore 8
    //   204: aload 34
    //   206: astore 17
    //   208: aload 26
    //   210: astore 22
    //   212: aload 30
    //   214: astore 20
    //   216: aload 36
    //   218: astore 11
    //   220: ldc_w 513
    //   223: aload_3
    //   224: invokevirtual 289	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   227: ifne +61 -> 288
    //   230: aload 33
    //   232: astore 18
    //   234: aload 28
    //   236: astore 21
    //   238: aload 31
    //   240: astore 23
    //   242: aload 38
    //   244: astore 10
    //   246: aload 35
    //   248: astore 14
    //   250: aload 27
    //   252: astore 15
    //   254: aload 32
    //   256: astore 16
    //   258: aload 37
    //   260: astore 8
    //   262: aload 34
    //   264: astore 17
    //   266: aload 26
    //   268: astore 22
    //   270: aload 30
    //   272: astore 20
    //   274: aload 36
    //   276: astore 11
    //   278: ldc_w 515
    //   281: aload_3
    //   282: invokevirtual 289	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   285: ifeq +423 -> 708
    //   288: aload 33
    //   290: astore 18
    //   292: aload 28
    //   294: astore 21
    //   296: aload 31
    //   298: astore 23
    //   300: aload 38
    //   302: astore 10
    //   304: aload 35
    //   306: astore 14
    //   308: aload 27
    //   310: astore 15
    //   312: aload 32
    //   314: astore 16
    //   316: aload 37
    //   318: astore 8
    //   320: aload 34
    //   322: astore 17
    //   324: aload 26
    //   326: astore 22
    //   328: aload 30
    //   330: astore 20
    //   332: aload 36
    //   334: astore 11
    //   336: new 556	android/content/ContentValues
    //   339: dup
    //   340: invokespecial 603	android/content/ContentValues:<init>	()V
    //   343: astore_1
    //   344: aload 33
    //   346: astore 18
    //   348: aload 28
    //   350: astore 21
    //   352: aload 31
    //   354: astore 23
    //   356: aload 38
    //   358: astore 10
    //   360: aload 35
    //   362: astore 14
    //   364: aload 27
    //   366: astore 15
    //   368: aload 32
    //   370: astore 16
    //   372: aload 37
    //   374: astore 8
    //   376: aload 34
    //   378: astore 17
    //   380: aload 26
    //   382: astore 22
    //   384: aload 30
    //   386: astore 20
    //   388: aload 36
    //   390: astore 11
    //   392: aload_1
    //   393: ldc -60
    //   395: new 427	com/google/android/mms/pdu/EncodedStringValue
    //   398: dup
    //   399: aload 40
    //   401: invokespecial 605	com/google/android/mms/pdu/EncodedStringValue:<init>	([B)V
    //   404: invokevirtual 607	com/google/android/mms/pdu/EncodedStringValue:getString	()Ljava/lang/String;
    //   407: invokevirtual 564	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   410: aload 33
    //   412: astore 18
    //   414: aload 28
    //   416: astore 21
    //   418: aload 31
    //   420: astore 23
    //   422: aload 38
    //   424: astore 10
    //   426: aload 35
    //   428: astore 14
    //   430: aload 27
    //   432: astore 15
    //   434: aload 32
    //   436: astore 16
    //   438: aload 37
    //   440: astore 8
    //   442: aload 34
    //   444: astore 17
    //   446: aload 26
    //   448: astore 22
    //   450: aload 30
    //   452: astore 20
    //   454: aload 36
    //   456: astore 11
    //   458: aload 12
    //   460: astore 9
    //   462: aload 29
    //   464: astore 19
    //   466: aload_0
    //   467: getfield 273	com/google/android/mms/pdu/MiuiPduPersister:mContentResolver	Landroid/content/ContentResolver;
    //   470: aload_2
    //   471: aload_1
    //   472: aconst_null
    //   473: aconst_null
    //   474: invokevirtual 611	android/content/ContentResolver:update	(Landroid/net/Uri;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I
    //   477: iconst_1
    //   478: if_icmpeq +1503 -> 1981
    //   481: aload 33
    //   483: astore 18
    //   485: aload 28
    //   487: astore 21
    //   489: aload 31
    //   491: astore 23
    //   493: aload 38
    //   495: astore 10
    //   497: aload 35
    //   499: astore 14
    //   501: aload 27
    //   503: astore 15
    //   505: aload 32
    //   507: astore 16
    //   509: aload 37
    //   511: astore 8
    //   513: aload 34
    //   515: astore 17
    //   517: aload 26
    //   519: astore 22
    //   521: aload 30
    //   523: astore 20
    //   525: aload 36
    //   527: astore 11
    //   529: new 443	com/google/android/mms/MmsException
    //   532: dup
    //   533: new 380	java/lang/StringBuilder
    //   536: dup
    //   537: invokespecial 381	java/lang/StringBuilder:<init>	()V
    //   540: ldc_w 613
    //   543: invokevirtual 387	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   546: aload_2
    //   547: invokevirtual 299	android/net/Uri:toString	()Ljava/lang/String;
    //   550: invokevirtual 387	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   553: invokevirtual 393	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   556: invokespecial 533	com/google/android/mms/MmsException:<init>	(Ljava/lang/String;)V
    //   559: athrow
    //   560: astore_1
    //   561: aload 18
    //   563: astore 14
    //   565: aload 21
    //   567: astore 15
    //   569: aload 23
    //   571: astore 16
    //   573: aload 10
    //   575: astore 8
    //   577: ldc 99
    //   579: ldc_w 615
    //   582: aload_1
    //   583: invokestatic 363	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   586: pop
    //   587: aload 18
    //   589: astore 14
    //   591: aload 21
    //   593: astore 15
    //   595: aload 23
    //   597: astore 16
    //   599: aload 10
    //   601: astore 8
    //   603: new 443	com/google/android/mms/MmsException
    //   606: dup
    //   607: aload_1
    //   608: invokespecial 551	com/google/android/mms/MmsException:<init>	(Ljava/lang/Throwable;)V
    //   611: athrow
    //   612: astore_1
    //   613: aload 16
    //   615: ifnull +8 -> 623
    //   618: aload 16
    //   620: invokevirtual 618	java/io/OutputStream:close	()V
    //   623: aload 15
    //   625: ifnull +8 -> 633
    //   628: aload 15
    //   630: invokevirtual 544	java/io/InputStream:close	()V
    //   633: aload 14
    //   635: ifnull +71 -> 706
    //   638: aload 14
    //   640: aload 8
    //   642: invokevirtual 622	com/google/android/mms/util/DrmConvertSession:close	(Ljava/lang/String;)I
    //   645: pop
    //   646: new 624	java/io/File
    //   649: dup
    //   650: aload 8
    //   652: invokespecial 625	java/io/File:<init>	(Ljava/lang/String;)V
    //   655: astore_2
    //   656: new 556	android/content/ContentValues
    //   659: dup
    //   660: iconst_0
    //   661: invokespecial 558	android/content/ContentValues:<init>	(I)V
    //   664: astore_3
    //   665: aload_0
    //   666: getfield 265	com/google/android/mms/pdu/MiuiPduPersister:mContext	Landroid/content/Context;
    //   669: aload_0
    //   670: getfield 273	com/google/android/mms/pdu/MiuiPduPersister:mContentResolver	Landroid/content/ContentResolver;
    //   673: new 380	java/lang/StringBuilder
    //   676: dup
    //   677: invokespecial 381	java/lang/StringBuilder:<init>	()V
    //   680: ldc_w 627
    //   683: invokevirtual 387	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   686: aload_2
    //   687: invokevirtual 630	java/io/File:getName	()Ljava/lang/String;
    //   690: invokevirtual 387	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   693: invokevirtual 393	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   696: invokestatic 397	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   699: aload_3
    //   700: aconst_null
    //   701: aconst_null
    //   702: invokestatic 633	com/google/android/mms/util/SqliteWrapper:update	(Landroid/content/Context;Landroid/content/ContentResolver;Landroid/net/Uri;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I
    //   705: pop
    //   706: aload_1
    //   707: athrow
    //   708: aload 33
    //   710: astore 18
    //   712: aload 28
    //   714: astore 21
    //   716: aload 31
    //   718: astore 23
    //   720: aload 38
    //   722: astore 10
    //   724: aload 35
    //   726: astore 14
    //   728: aload 27
    //   730: astore 15
    //   732: aload 32
    //   734: astore 16
    //   736: aload 37
    //   738: astore 8
    //   740: aload 34
    //   742: astore 17
    //   744: aload 26
    //   746: astore 22
    //   748: aload 30
    //   750: astore 20
    //   752: aload 36
    //   754: astore 11
    //   756: aload_3
    //   757: invokestatic 638	com/google/android/mms/util/DownloadDrmHelper:isDrmConvertNeeded	(Ljava/lang/String;)Z
    //   760: istore 5
    //   762: aload 39
    //   764: astore 12
    //   766: iload 5
    //   768: ifeq +480 -> 1248
    //   771: aload_2
    //   772: ifnull +271 -> 1043
    //   775: aload 33
    //   777: astore 18
    //   779: aload 28
    //   781: astore 21
    //   783: aload 31
    //   785: astore 23
    //   787: aload 38
    //   789: astore 10
    //   791: aload 35
    //   793: astore 14
    //   795: aload 27
    //   797: astore 15
    //   799: aload 32
    //   801: astore 16
    //   803: aload 37
    //   805: astore 8
    //   807: aload 19
    //   809: astore 9
    //   811: aload 34
    //   813: astore 17
    //   815: aload 26
    //   817: astore 22
    //   819: aload 30
    //   821: astore 20
    //   823: aload 36
    //   825: astore 11
    //   827: aload_0
    //   828: getfield 265	com/google/android/mms/pdu/MiuiPduPersister:mContext	Landroid/content/Context;
    //   831: aload_2
    //   832: invokestatic 640	com/google/android/mms/pdu/MiuiPduPersister:convertUriToPath	(Landroid/content/Context;Landroid/net/Uri;)Ljava/lang/String;
    //   835: astore 12
    //   837: aload 33
    //   839: astore 18
    //   841: aload 28
    //   843: astore 21
    //   845: aload 31
    //   847: astore 23
    //   849: aload 12
    //   851: astore 10
    //   853: aload 35
    //   855: astore 14
    //   857: aload 27
    //   859: astore 15
    //   861: aload 32
    //   863: astore 16
    //   865: aload 12
    //   867: astore 8
    //   869: aload 12
    //   871: astore 9
    //   873: aload 34
    //   875: astore 17
    //   877: aload 26
    //   879: astore 22
    //   881: aload 30
    //   883: astore 20
    //   885: aload 12
    //   887: astore 11
    //   889: new 624	java/io/File
    //   892: dup
    //   893: aload 12
    //   895: invokespecial 625	java/io/File:<init>	(Ljava/lang/String;)V
    //   898: invokevirtual 644	java/io/File:length	()J
    //   901: lstore 6
    //   903: aload 12
    //   905: astore 9
    //   907: lload 6
    //   909: lconst_0
    //   910: lcmp
    //   911: ifle +132 -> 1043
    //   914: iconst_0
    //   915: ifeq +11 -> 926
    //   918: new 646	java/lang/NullPointerException
    //   921: dup
    //   922: invokespecial 647	java/lang/NullPointerException:<init>	()V
    //   925: athrow
    //   926: iconst_0
    //   927: ifeq +11 -> 938
    //   930: new 646	java/lang/NullPointerException
    //   933: dup
    //   934: invokespecial 647	java/lang/NullPointerException:<init>	()V
    //   937: athrow
    //   938: iconst_0
    //   939: ifeq +22 -> 961
    //   942: new 646	java/lang/NullPointerException
    //   945: dup
    //   946: invokespecial 647	java/lang/NullPointerException:<init>	()V
    //   949: athrow
    //   950: aload_1
    //   951: aload_2
    //   952: aload_3
    //   953: aload 8
    //   955: aconst_null
    //   956: aconst_null
    //   957: invokestatic 633	com/google/android/mms/util/SqliteWrapper:update	(Landroid/content/Context;Landroid/content/ContentResolver;Landroid/net/Uri;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I
    //   960: pop
    //   961: return
    //   962: astore 12
    //   964: aload 33
    //   966: astore 18
    //   968: aload 28
    //   970: astore 21
    //   972: aload 31
    //   974: astore 23
    //   976: aload 9
    //   978: astore 10
    //   980: aload 35
    //   982: astore 14
    //   984: aload 27
    //   986: astore 15
    //   988: aload 32
    //   990: astore 16
    //   992: aload 9
    //   994: astore 8
    //   996: aload 34
    //   998: astore 17
    //   1000: aload 26
    //   1002: astore 22
    //   1004: aload 30
    //   1006: astore 20
    //   1008: aload 9
    //   1010: astore 11
    //   1012: ldc 99
    //   1014: new 380	java/lang/StringBuilder
    //   1017: dup
    //   1018: invokespecial 381	java/lang/StringBuilder:<init>	()V
    //   1021: ldc_w 649
    //   1024: invokevirtual 387	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1027: aload_1
    //   1028: invokevirtual 653	com/google/android/mms/pdu/PduPart:getDataUri	()Landroid/net/Uri;
    //   1031: invokevirtual 656	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   1034: invokevirtual 393	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1037: aload 12
    //   1039: invokestatic 363	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   1042: pop
    //   1043: aload 33
    //   1045: astore 18
    //   1047: aload 28
    //   1049: astore 21
    //   1051: aload 31
    //   1053: astore 23
    //   1055: aload 9
    //   1057: astore 10
    //   1059: aload 35
    //   1061: astore 14
    //   1063: aload 27
    //   1065: astore 15
    //   1067: aload 32
    //   1069: astore 16
    //   1071: aload 9
    //   1073: astore 8
    //   1075: aload 34
    //   1077: astore 17
    //   1079: aload 26
    //   1081: astore 22
    //   1083: aload 30
    //   1085: astore 20
    //   1087: aload 9
    //   1089: astore 11
    //   1091: aload_0
    //   1092: getfield 265	com/google/android/mms/pdu/MiuiPduPersister:mContext	Landroid/content/Context;
    //   1095: aload_3
    //   1096: invokestatic 660	com/google/android/mms/util/DrmConvertSession:open	(Landroid/content/Context;Ljava/lang/String;)Lcom/google/android/mms/util/DrmConvertSession;
    //   1099: astore 19
    //   1101: aload 19
    //   1103: astore 12
    //   1105: aload 9
    //   1107: astore 13
    //   1109: aload 19
    //   1111: ifnonnull +137 -> 1248
    //   1114: aload 19
    //   1116: astore 18
    //   1118: aload 28
    //   1120: astore 21
    //   1122: aload 31
    //   1124: astore 23
    //   1126: aload 9
    //   1128: astore 10
    //   1130: aload 19
    //   1132: astore 14
    //   1134: aload 27
    //   1136: astore 15
    //   1138: aload 32
    //   1140: astore 16
    //   1142: aload 9
    //   1144: astore 8
    //   1146: aload 19
    //   1148: astore 17
    //   1150: aload 26
    //   1152: astore 22
    //   1154: aload 30
    //   1156: astore 20
    //   1158: aload 9
    //   1160: astore 11
    //   1162: new 443	com/google/android/mms/MmsException
    //   1165: dup
    //   1166: new 380	java/lang/StringBuilder
    //   1169: dup
    //   1170: invokespecial 381	java/lang/StringBuilder:<init>	()V
    //   1173: ldc_w 662
    //   1176: invokevirtual 387	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1179: aload_3
    //   1180: invokevirtual 387	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1183: ldc_w 664
    //   1186: invokevirtual 387	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1189: invokevirtual 393	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1192: invokespecial 533	com/google/android/mms/MmsException:<init>	(Ljava/lang/String;)V
    //   1195: athrow
    //   1196: astore_1
    //   1197: aload 17
    //   1199: astore 14
    //   1201: aload 22
    //   1203: astore 15
    //   1205: aload 20
    //   1207: astore 16
    //   1209: aload 11
    //   1211: astore 8
    //   1213: ldc 99
    //   1215: ldc_w 666
    //   1218: aload_1
    //   1219: invokestatic 363	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   1222: pop
    //   1223: aload 17
    //   1225: astore 14
    //   1227: aload 22
    //   1229: astore 15
    //   1231: aload 20
    //   1233: astore 16
    //   1235: aload 11
    //   1237: astore 8
    //   1239: new 443	com/google/android/mms/MmsException
    //   1242: dup
    //   1243: aload_1
    //   1244: invokespecial 551	com/google/android/mms/MmsException:<init>	(Ljava/lang/Throwable;)V
    //   1247: athrow
    //   1248: aload 12
    //   1250: astore 18
    //   1252: aload 28
    //   1254: astore 21
    //   1256: aload 31
    //   1258: astore 23
    //   1260: aload 13
    //   1262: astore 10
    //   1264: aload 12
    //   1266: astore 14
    //   1268: aload 27
    //   1270: astore 15
    //   1272: aload 32
    //   1274: astore 16
    //   1276: aload 13
    //   1278: astore 8
    //   1280: aload 12
    //   1282: astore 17
    //   1284: aload 26
    //   1286: astore 22
    //   1288: aload 30
    //   1290: astore 20
    //   1292: aload 13
    //   1294: astore 11
    //   1296: aload_0
    //   1297: getfield 273	com/google/android/mms/pdu/MiuiPduPersister:mContentResolver	Landroid/content/ContentResolver;
    //   1300: aload_2
    //   1301: invokevirtual 670	android/content/ContentResolver:openOutputStream	(Landroid/net/Uri;)Ljava/io/OutputStream;
    //   1304: astore_3
    //   1305: aload 40
    //   1307: ifnonnull +603 -> 1910
    //   1310: aload 12
    //   1312: astore 18
    //   1314: aload 28
    //   1316: astore 21
    //   1318: aload_3
    //   1319: astore 23
    //   1321: aload 13
    //   1323: astore 10
    //   1325: aload 12
    //   1327: astore 14
    //   1329: aload 27
    //   1331: astore 15
    //   1333: aload_3
    //   1334: astore 16
    //   1336: aload 13
    //   1338: astore 8
    //   1340: aload 12
    //   1342: astore 17
    //   1344: aload 26
    //   1346: astore 22
    //   1348: aload_3
    //   1349: astore 20
    //   1351: aload 13
    //   1353: astore 11
    //   1355: aload_1
    //   1356: invokevirtual 653	com/google/android/mms/pdu/PduPart:getDataUri	()Landroid/net/Uri;
    //   1359: astore_1
    //   1360: aload_1
    //   1361: ifnull +8 -> 1369
    //   1364: aload_1
    //   1365: aload_2
    //   1366: if_acmpne +150 -> 1516
    //   1369: aload 12
    //   1371: astore 18
    //   1373: aload 28
    //   1375: astore 21
    //   1377: aload_3
    //   1378: astore 23
    //   1380: aload 13
    //   1382: astore 10
    //   1384: aload 12
    //   1386: astore 14
    //   1388: aload 27
    //   1390: astore 15
    //   1392: aload_3
    //   1393: astore 16
    //   1395: aload 13
    //   1397: astore 8
    //   1399: aload 12
    //   1401: astore 17
    //   1403: aload 26
    //   1405: astore 22
    //   1407: aload_3
    //   1408: astore 20
    //   1410: aload 13
    //   1412: astore 11
    //   1414: ldc 99
    //   1416: ldc_w 672
    //   1419: invokestatic 675	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   1422: pop
    //   1423: aload_3
    //   1424: ifnull +7 -> 1431
    //   1427: aload_3
    //   1428: invokevirtual 618	java/io/OutputStream:close	()V
    //   1431: iconst_0
    //   1432: ifeq +11 -> 1443
    //   1435: new 646	java/lang/NullPointerException
    //   1438: dup
    //   1439: invokespecial 647	java/lang/NullPointerException:<init>	()V
    //   1442: athrow
    //   1443: aload 12
    //   1445: ifnull -484 -> 961
    //   1448: aload 12
    //   1450: aload 13
    //   1452: invokevirtual 622	com/google/android/mms/util/DrmConvertSession:close	(Ljava/lang/String;)I
    //   1455: pop
    //   1456: new 624	java/io/File
    //   1459: dup
    //   1460: aload 13
    //   1462: invokespecial 625	java/io/File:<init>	(Ljava/lang/String;)V
    //   1465: astore_3
    //   1466: new 556	android/content/ContentValues
    //   1469: dup
    //   1470: iconst_0
    //   1471: invokespecial 558	android/content/ContentValues:<init>	(I)V
    //   1474: astore 8
    //   1476: aload_0
    //   1477: getfield 265	com/google/android/mms/pdu/MiuiPduPersister:mContext	Landroid/content/Context;
    //   1480: astore_1
    //   1481: aload_0
    //   1482: getfield 273	com/google/android/mms/pdu/MiuiPduPersister:mContentResolver	Landroid/content/ContentResolver;
    //   1485: astore_2
    //   1486: new 380	java/lang/StringBuilder
    //   1489: dup
    //   1490: invokespecial 381	java/lang/StringBuilder:<init>	()V
    //   1493: ldc_w 627
    //   1496: invokevirtual 387	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1499: aload_3
    //   1500: invokevirtual 630	java/io/File:getName	()Ljava/lang/String;
    //   1503: invokevirtual 387	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1506: invokevirtual 393	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1509: invokestatic 397	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   1512: astore_3
    //   1513: goto -563 -> 950
    //   1516: aload 12
    //   1518: astore 18
    //   1520: aload 28
    //   1522: astore 21
    //   1524: aload_3
    //   1525: astore 23
    //   1527: aload 13
    //   1529: astore 10
    //   1531: aload 12
    //   1533: astore 14
    //   1535: aload 27
    //   1537: astore 15
    //   1539: aload_3
    //   1540: astore 16
    //   1542: aload 13
    //   1544: astore 8
    //   1546: aload 12
    //   1548: astore 17
    //   1550: aload 26
    //   1552: astore 22
    //   1554: aload_3
    //   1555: astore 20
    //   1557: aload 13
    //   1559: astore 11
    //   1561: aload_0
    //   1562: getfield 273	com/google/android/mms/pdu/MiuiPduPersister:mContentResolver	Landroid/content/ContentResolver;
    //   1565: aload_1
    //   1566: invokevirtual 537	android/content/ContentResolver:openInputStream	(Landroid/net/Uri;)Ljava/io/InputStream;
    //   1569: astore_1
    //   1570: aload 12
    //   1572: astore 18
    //   1574: aload_1
    //   1575: astore 21
    //   1577: aload_3
    //   1578: astore 23
    //   1580: aload 13
    //   1582: astore 10
    //   1584: aload 12
    //   1586: astore 14
    //   1588: aload_1
    //   1589: astore 15
    //   1591: aload_3
    //   1592: astore 16
    //   1594: aload 13
    //   1596: astore 8
    //   1598: aload 12
    //   1600: astore 17
    //   1602: aload_1
    //   1603: astore 22
    //   1605: aload_3
    //   1606: astore 20
    //   1608: aload 13
    //   1610: astore 11
    //   1612: sipush 8192
    //   1615: newarray <illegal type>
    //   1617: astore_2
    //   1618: aload 12
    //   1620: astore 18
    //   1622: aload_1
    //   1623: astore 21
    //   1625: aload_3
    //   1626: astore 23
    //   1628: aload 13
    //   1630: astore 10
    //   1632: aload 12
    //   1634: astore 14
    //   1636: aload_1
    //   1637: astore 15
    //   1639: aload_3
    //   1640: astore 16
    //   1642: aload 13
    //   1644: astore 8
    //   1646: aload 12
    //   1648: astore 17
    //   1650: aload_1
    //   1651: astore 22
    //   1653: aload_3
    //   1654: astore 20
    //   1656: aload 13
    //   1658: astore 11
    //   1660: aload_1
    //   1661: aload_2
    //   1662: invokevirtual 543	java/io/InputStream:read	([B)I
    //   1665: istore 4
    //   1667: aload 12
    //   1669: astore 9
    //   1671: aload_1
    //   1672: astore 19
    //   1674: aload_3
    //   1675: astore 24
    //   1677: aload 13
    //   1679: astore 25
    //   1681: iload 4
    //   1683: iconst_m1
    //   1684: if_icmpeq +297 -> 1981
    //   1687: iload 5
    //   1689: ifne +56 -> 1745
    //   1692: aload 12
    //   1694: astore 18
    //   1696: aload_1
    //   1697: astore 21
    //   1699: aload_3
    //   1700: astore 23
    //   1702: aload 13
    //   1704: astore 10
    //   1706: aload 12
    //   1708: astore 14
    //   1710: aload_1
    //   1711: astore 15
    //   1713: aload_3
    //   1714: astore 16
    //   1716: aload 13
    //   1718: astore 8
    //   1720: aload 12
    //   1722: astore 17
    //   1724: aload_1
    //   1725: astore 22
    //   1727: aload_3
    //   1728: astore 20
    //   1730: aload 13
    //   1732: astore 11
    //   1734: aload_3
    //   1735: aload_2
    //   1736: iconst_0
    //   1737: iload 4
    //   1739: invokevirtual 676	java/io/OutputStream:write	([BII)V
    //   1742: goto -124 -> 1618
    //   1745: aload 12
    //   1747: astore 18
    //   1749: aload_1
    //   1750: astore 21
    //   1752: aload_3
    //   1753: astore 23
    //   1755: aload 13
    //   1757: astore 10
    //   1759: aload 12
    //   1761: astore 14
    //   1763: aload_1
    //   1764: astore 15
    //   1766: aload_3
    //   1767: astore 16
    //   1769: aload 13
    //   1771: astore 8
    //   1773: aload 12
    //   1775: astore 17
    //   1777: aload_1
    //   1778: astore 22
    //   1780: aload_3
    //   1781: astore 20
    //   1783: aload 13
    //   1785: astore 11
    //   1787: aload 12
    //   1789: aload_2
    //   1790: iload 4
    //   1792: invokevirtual 680	com/google/android/mms/util/DrmConvertSession:convert	([BI)[B
    //   1795: astore 9
    //   1797: aload 9
    //   1799: ifnull +58 -> 1857
    //   1802: aload 12
    //   1804: astore 18
    //   1806: aload_1
    //   1807: astore 21
    //   1809: aload_3
    //   1810: astore 23
    //   1812: aload 13
    //   1814: astore 10
    //   1816: aload 12
    //   1818: astore 14
    //   1820: aload_1
    //   1821: astore 15
    //   1823: aload_3
    //   1824: astore 16
    //   1826: aload 13
    //   1828: astore 8
    //   1830: aload 12
    //   1832: astore 17
    //   1834: aload_1
    //   1835: astore 22
    //   1837: aload_3
    //   1838: astore 20
    //   1840: aload 13
    //   1842: astore 11
    //   1844: aload_3
    //   1845: aload 9
    //   1847: iconst_0
    //   1848: aload 9
    //   1850: arraylength
    //   1851: invokevirtual 676	java/io/OutputStream:write	([BII)V
    //   1854: goto -236 -> 1618
    //   1857: aload 12
    //   1859: astore 18
    //   1861: aload_1
    //   1862: astore 21
    //   1864: aload_3
    //   1865: astore 23
    //   1867: aload 13
    //   1869: astore 10
    //   1871: aload 12
    //   1873: astore 14
    //   1875: aload_1
    //   1876: astore 15
    //   1878: aload_3
    //   1879: astore 16
    //   1881: aload 13
    //   1883: astore 8
    //   1885: aload 12
    //   1887: astore 17
    //   1889: aload_1
    //   1890: astore 22
    //   1892: aload_3
    //   1893: astore 20
    //   1895: aload 13
    //   1897: astore 11
    //   1899: new 443	com/google/android/mms/MmsException
    //   1902: dup
    //   1903: ldc_w 682
    //   1906: invokespecial 533	com/google/android/mms/MmsException:<init>	(Ljava/lang/String;)V
    //   1909: athrow
    //   1910: iload 5
    //   1912: ifne +162 -> 2074
    //   1915: aload 12
    //   1917: astore 18
    //   1919: aload 28
    //   1921: astore 21
    //   1923: aload_3
    //   1924: astore 23
    //   1926: aload 13
    //   1928: astore 10
    //   1930: aload 12
    //   1932: astore 14
    //   1934: aload 27
    //   1936: astore 15
    //   1938: aload_3
    //   1939: astore 16
    //   1941: aload 13
    //   1943: astore 8
    //   1945: aload 12
    //   1947: astore 17
    //   1949: aload 26
    //   1951: astore 22
    //   1953: aload_3
    //   1954: astore 20
    //   1956: aload 13
    //   1958: astore 11
    //   1960: aload_3
    //   1961: aload 40
    //   1963: invokevirtual 684	java/io/OutputStream:write	([B)V
    //   1966: aload 13
    //   1968: astore 25
    //   1970: aload_3
    //   1971: astore 24
    //   1973: aload 29
    //   1975: astore 19
    //   1977: aload 12
    //   1979: astore 9
    //   1981: aload 24
    //   1983: ifnull +8 -> 1991
    //   1986: aload 24
    //   1988: invokevirtual 618	java/io/OutputStream:close	()V
    //   1991: aload 19
    //   1993: ifnull +8 -> 2001
    //   1996: aload 19
    //   1998: invokevirtual 544	java/io/InputStream:close	()V
    //   2001: aload 9
    //   2003: ifnull -1042 -> 961
    //   2006: aload 9
    //   2008: aload 25
    //   2010: invokevirtual 622	com/google/android/mms/util/DrmConvertSession:close	(Ljava/lang/String;)I
    //   2013: pop
    //   2014: new 624	java/io/File
    //   2017: dup
    //   2018: aload 25
    //   2020: invokespecial 625	java/io/File:<init>	(Ljava/lang/String;)V
    //   2023: astore_3
    //   2024: new 556	android/content/ContentValues
    //   2027: dup
    //   2028: iconst_0
    //   2029: invokespecial 558	android/content/ContentValues:<init>	(I)V
    //   2032: astore 8
    //   2034: aload_0
    //   2035: getfield 265	com/google/android/mms/pdu/MiuiPduPersister:mContext	Landroid/content/Context;
    //   2038: astore_1
    //   2039: aload_0
    //   2040: getfield 273	com/google/android/mms/pdu/MiuiPduPersister:mContentResolver	Landroid/content/ContentResolver;
    //   2043: astore_2
    //   2044: new 380	java/lang/StringBuilder
    //   2047: dup
    //   2048: invokespecial 381	java/lang/StringBuilder:<init>	()V
    //   2051: ldc_w 627
    //   2054: invokevirtual 387	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2057: aload_3
    //   2058: invokevirtual 630	java/io/File:getName	()Ljava/lang/String;
    //   2061: invokevirtual 387	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2064: invokevirtual 393	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2067: invokestatic 397	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   2070: astore_3
    //   2071: goto -1121 -> 950
    //   2074: aload 12
    //   2076: astore 18
    //   2078: aload 28
    //   2080: astore 21
    //   2082: aload_3
    //   2083: astore 23
    //   2085: aload 13
    //   2087: astore 10
    //   2089: aload 12
    //   2091: astore 14
    //   2093: aload 27
    //   2095: astore 15
    //   2097: aload_3
    //   2098: astore 16
    //   2100: aload 13
    //   2102: astore 8
    //   2104: aload 12
    //   2106: astore 17
    //   2108: aload 26
    //   2110: astore 22
    //   2112: aload_3
    //   2113: astore 20
    //   2115: aload 13
    //   2117: astore 11
    //   2119: aload 12
    //   2121: aload 40
    //   2123: aload 40
    //   2125: arraylength
    //   2126: invokevirtual 680	com/google/android/mms/util/DrmConvertSession:convert	([BI)[B
    //   2129: astore_1
    //   2130: aload_1
    //   2131: ifnull +74 -> 2205
    //   2134: aload 12
    //   2136: astore 18
    //   2138: aload 28
    //   2140: astore 21
    //   2142: aload_3
    //   2143: astore 23
    //   2145: aload 13
    //   2147: astore 10
    //   2149: aload 12
    //   2151: astore 14
    //   2153: aload 27
    //   2155: astore 15
    //   2157: aload_3
    //   2158: astore 16
    //   2160: aload 13
    //   2162: astore 8
    //   2164: aload 12
    //   2166: astore 17
    //   2168: aload 26
    //   2170: astore 22
    //   2172: aload_3
    //   2173: astore 20
    //   2175: aload 13
    //   2177: astore 11
    //   2179: aload_3
    //   2180: aload_1
    //   2181: iconst_0
    //   2182: aload_1
    //   2183: arraylength
    //   2184: invokevirtual 676	java/io/OutputStream:write	([BII)V
    //   2187: aload 12
    //   2189: astore 9
    //   2191: aload 29
    //   2193: astore 19
    //   2195: aload_3
    //   2196: astore 24
    //   2198: aload 13
    //   2200: astore 25
    //   2202: goto -221 -> 1981
    //   2205: aload 12
    //   2207: astore 18
    //   2209: aload 28
    //   2211: astore 21
    //   2213: aload_3
    //   2214: astore 23
    //   2216: aload 13
    //   2218: astore 10
    //   2220: aload 12
    //   2222: astore 14
    //   2224: aload 27
    //   2226: astore 15
    //   2228: aload_3
    //   2229: astore 16
    //   2231: aload 13
    //   2233: astore 8
    //   2235: aload 12
    //   2237: astore 17
    //   2239: aload 26
    //   2241: astore 22
    //   2243: aload_3
    //   2244: astore 20
    //   2246: aload 13
    //   2248: astore 11
    //   2250: new 443	com/google/android/mms/MmsException
    //   2253: dup
    //   2254: ldc_w 682
    //   2257: invokespecial 533	com/google/android/mms/MmsException:<init>	(Ljava/lang/String;)V
    //   2260: athrow
    //   2261: astore_2
    //   2262: ldc 99
    //   2264: new 380	java/lang/StringBuilder
    //   2267: dup
    //   2268: invokespecial 381	java/lang/StringBuilder:<init>	()V
    //   2271: ldc_w 686
    //   2274: invokevirtual 387	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2277: aload 16
    //   2279: invokevirtual 656	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   2282: invokevirtual 393	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2285: aload_2
    //   2286: invokestatic 363	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   2289: pop
    //   2290: goto -1667 -> 623
    //   2293: astore_2
    //   2294: ldc 99
    //   2296: new 380	java/lang/StringBuilder
    //   2299: dup
    //   2300: invokespecial 381	java/lang/StringBuilder:<init>	()V
    //   2303: ldc_w 686
    //   2306: invokevirtual 387	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2309: aload 15
    //   2311: invokevirtual 656	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   2314: invokevirtual 393	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2317: aload_2
    //   2318: invokestatic 363	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   2321: pop
    //   2322: goto -1689 -> 633
    //   2325: astore_1
    //   2326: ldc 99
    //   2328: new 380	java/lang/StringBuilder
    //   2331: dup
    //   2332: invokespecial 381	java/lang/StringBuilder:<init>	()V
    //   2335: ldc_w 686
    //   2338: invokevirtual 387	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2341: aload 24
    //   2343: invokevirtual 656	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   2346: invokevirtual 393	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2349: aload_1
    //   2350: invokestatic 363	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   2353: pop
    //   2354: goto -363 -> 1991
    //   2357: astore_1
    //   2358: ldc 99
    //   2360: new 380	java/lang/StringBuilder
    //   2363: dup
    //   2364: invokespecial 381	java/lang/StringBuilder:<init>	()V
    //   2367: ldc_w 686
    //   2370: invokevirtual 387	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2373: aload 19
    //   2375: invokevirtual 656	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   2378: invokevirtual 393	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2381: aload_1
    //   2382: invokestatic 363	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   2385: pop
    //   2386: goto -385 -> 2001
    //   2389: astore_1
    //   2390: ldc 99
    //   2392: new 380	java/lang/StringBuilder
    //   2395: dup
    //   2396: invokespecial 381	java/lang/StringBuilder:<init>	()V
    //   2399: ldc_w 686
    //   2402: invokevirtual 387	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2405: aload_3
    //   2406: invokevirtual 656	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   2409: invokevirtual 393	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2412: aload_1
    //   2413: invokestatic 363	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   2416: pop
    //   2417: goto -986 -> 1431
    //   2420: astore_1
    //   2421: ldc 99
    //   2423: new 380	java/lang/StringBuilder
    //   2426: dup
    //   2427: invokespecial 381	java/lang/StringBuilder:<init>	()V
    //   2430: ldc_w 686
    //   2433: invokevirtual 387	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2436: aconst_null
    //   2437: invokevirtual 656	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   2440: invokevirtual 393	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2443: aload_1
    //   2444: invokestatic 363	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   2447: pop
    //   2448: goto -1005 -> 1443
    //   2451: astore_1
    //   2452: ldc 99
    //   2454: new 380	java/lang/StringBuilder
    //   2457: dup
    //   2458: invokespecial 381	java/lang/StringBuilder:<init>	()V
    //   2461: ldc_w 686
    //   2464: invokevirtual 387	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2467: aconst_null
    //   2468: invokevirtual 656	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   2471: invokevirtual 393	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2474: aload_1
    //   2475: invokestatic 363	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   2478: pop
    //   2479: goto -1553 -> 926
    //   2482: astore_1
    //   2483: ldc 99
    //   2485: new 380	java/lang/StringBuilder
    //   2488: dup
    //   2489: invokespecial 381	java/lang/StringBuilder:<init>	()V
    //   2492: ldc_w 686
    //   2495: invokevirtual 387	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2498: aconst_null
    //   2499: invokevirtual 656	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   2502: invokevirtual 393	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2505: aload_1
    //   2506: invokestatic 363	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   2509: pop
    //   2510: goto -1572 -> 938
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	2513	0	this	MiuiPduPersister
    //   0	2513	1	paramPduPart	PduPart
    //   0	2513	2	paramUri	Uri
    //   0	2513	3	paramString	String
    //   1665	126	4	i	int
    //   760	1151	5	bool	boolean
    //   901	7	6	l	long
    //   90	2144	8	localObject1	Object
    //   46	2144	9	localObject2	Object
    //   74	2145	10	localObject3	Object
    //   106	2143	11	localObject4	Object
    //   34	870	12	localObject5	Object
    //   962	76	12	localException	Exception
    //   1103	1133	12	localObject6	Object
    //   52	2195	13	localObject7	Object
    //   78	2145	14	localObject8	Object
    //   82	2228	15	localObject9	Object
    //   86	2192	16	localObject10	Object
    //   94	2144	17	localObject11	Object
    //   62	2146	18	localObject12	Object
    //   43	2331	19	localObject13	Object
    //   102	2143	20	localObject14	Object
    //   66	2146	21	localObject15	Object
    //   98	2144	22	localObject16	Object
    //   70	2145	23	localObject17	Object
    //   7	2335	24	str	String
    //   55	2146	25	localObject18	Object
    //   16	2224	26	localObject19	Object
    //   13	2212	27	localObject20	Object
    //   22	2188	28	localObject21	Object
    //   19	2173	29	localObject22	Object
    //   4	1285	30	localObject23	Object
    //   10	1247	31	localObject24	Object
    //   1	1272	32	localObject25	Object
    //   37	1007	33	localObject26	Object
    //   28	1048	34	localObject27	Object
    //   25	1035	35	localObject28	Object
    //   49	775	36	localObject29	Object
    //   40	764	37	localObject30	Object
    //   58	730	38	localObject31	Object
    //   31	732	39	localObject32	Object
    //   112	2012	40	arrayOfByte	byte[]
    // Exception table:
    //   from	to	target	type
    //   108	114	560	java/io/FileNotFoundException
    //   162	172	560	java/io/FileNotFoundException
    //   220	230	560	java/io/FileNotFoundException
    //   278	288	560	java/io/FileNotFoundException
    //   336	344	560	java/io/FileNotFoundException
    //   392	410	560	java/io/FileNotFoundException
    //   466	481	560	java/io/FileNotFoundException
    //   529	560	560	java/io/FileNotFoundException
    //   756	762	560	java/io/FileNotFoundException
    //   827	837	560	java/io/FileNotFoundException
    //   889	903	560	java/io/FileNotFoundException
    //   1012	1043	560	java/io/FileNotFoundException
    //   1091	1101	560	java/io/FileNotFoundException
    //   1162	1196	560	java/io/FileNotFoundException
    //   1296	1305	560	java/io/FileNotFoundException
    //   1355	1360	560	java/io/FileNotFoundException
    //   1414	1423	560	java/io/FileNotFoundException
    //   1561	1570	560	java/io/FileNotFoundException
    //   1612	1618	560	java/io/FileNotFoundException
    //   1660	1667	560	java/io/FileNotFoundException
    //   1734	1742	560	java/io/FileNotFoundException
    //   1787	1797	560	java/io/FileNotFoundException
    //   1844	1854	560	java/io/FileNotFoundException
    //   1899	1910	560	java/io/FileNotFoundException
    //   1960	1966	560	java/io/FileNotFoundException
    //   2119	2130	560	java/io/FileNotFoundException
    //   2179	2187	560	java/io/FileNotFoundException
    //   2250	2261	560	java/io/FileNotFoundException
    //   108	114	612	finally
    //   162	172	612	finally
    //   220	230	612	finally
    //   278	288	612	finally
    //   336	344	612	finally
    //   392	410	612	finally
    //   466	481	612	finally
    //   529	560	612	finally
    //   577	587	612	finally
    //   603	612	612	finally
    //   756	762	612	finally
    //   827	837	612	finally
    //   889	903	612	finally
    //   1012	1043	612	finally
    //   1091	1101	612	finally
    //   1162	1196	612	finally
    //   1213	1223	612	finally
    //   1239	1248	612	finally
    //   1296	1305	612	finally
    //   1355	1360	612	finally
    //   1414	1423	612	finally
    //   1561	1570	612	finally
    //   1612	1618	612	finally
    //   1660	1667	612	finally
    //   1734	1742	612	finally
    //   1787	1797	612	finally
    //   1844	1854	612	finally
    //   1899	1910	612	finally
    //   1960	1966	612	finally
    //   2119	2130	612	finally
    //   2179	2187	612	finally
    //   2250	2261	612	finally
    //   827	837	962	java/lang/Exception
    //   889	903	962	java/lang/Exception
    //   108	114	1196	java/io/IOException
    //   162	172	1196	java/io/IOException
    //   220	230	1196	java/io/IOException
    //   278	288	1196	java/io/IOException
    //   336	344	1196	java/io/IOException
    //   392	410	1196	java/io/IOException
    //   466	481	1196	java/io/IOException
    //   529	560	1196	java/io/IOException
    //   756	762	1196	java/io/IOException
    //   827	837	1196	java/io/IOException
    //   889	903	1196	java/io/IOException
    //   1012	1043	1196	java/io/IOException
    //   1091	1101	1196	java/io/IOException
    //   1162	1196	1196	java/io/IOException
    //   1296	1305	1196	java/io/IOException
    //   1355	1360	1196	java/io/IOException
    //   1414	1423	1196	java/io/IOException
    //   1561	1570	1196	java/io/IOException
    //   1612	1618	1196	java/io/IOException
    //   1660	1667	1196	java/io/IOException
    //   1734	1742	1196	java/io/IOException
    //   1787	1797	1196	java/io/IOException
    //   1844	1854	1196	java/io/IOException
    //   1899	1910	1196	java/io/IOException
    //   1960	1966	1196	java/io/IOException
    //   2119	2130	1196	java/io/IOException
    //   2179	2187	1196	java/io/IOException
    //   2250	2261	1196	java/io/IOException
    //   618	623	2261	java/io/IOException
    //   628	633	2293	java/io/IOException
    //   1986	1991	2325	java/io/IOException
    //   1996	2001	2357	java/io/IOException
    //   1427	1431	2389	java/io/IOException
    //   1435	1443	2420	java/io/IOException
    //   918	926	2451	java/io/IOException
    //   930	938	2482	java/io/IOException
  }
  
  private void setEncodedStringValueToHeaders(Cursor paramCursor, int paramInt1, PduHeaders paramPduHeaders, int paramInt2)
  {
    String str = paramCursor.getString(paramInt1);
    if ((str != null) && (str.length() > 0)) {
      paramPduHeaders.setEncodedStringValue(new EncodedStringValue(paramCursor.getInt(((Integer)CHARSET_COLUMN_INDEX_MAP.get(Integer.valueOf(paramInt2))).intValue()), getBytes(str)), paramInt2);
    }
  }
  
  private void setLongToHeaders(Cursor paramCursor, int paramInt1, PduHeaders paramPduHeaders, int paramInt2)
  {
    if (!paramCursor.isNull(paramInt1)) {
      paramPduHeaders.setLongInteger(paramCursor.getLong(paramInt1), paramInt2);
    }
  }
  
  private void setOctetToHeaders(Cursor paramCursor, int paramInt1, PduHeaders paramPduHeaders, int paramInt2)
    throws InvalidHeaderValueException
  {
    if (!paramCursor.isNull(paramInt1)) {
      paramPduHeaders.setOctet(paramCursor.getInt(paramInt1), paramInt2);
    }
  }
  
  private void setTextStringToHeaders(Cursor paramCursor, int paramInt1, PduHeaders paramPduHeaders, int paramInt2)
  {
    paramCursor = paramCursor.getString(paramInt1);
    if (paramCursor != null) {
      paramPduHeaders.setTextString(getBytes(paramCursor), paramInt2);
    }
  }
  
  public static String toIsoString(byte[] paramArrayOfByte)
  {
    try
    {
      paramArrayOfByte = new String(paramArrayOfByte, "iso-8859-1");
      return paramArrayOfByte;
    }
    catch (UnsupportedEncodingException paramArrayOfByte)
    {
      Log.e("MiuiPduPersister", "ISO_8859_1 must be supported!", paramArrayOfByte);
    }
    return "";
  }
  
  private void updateAddress(long paramLong, int paramInt, EncodedStringValue[] paramArrayOfEncodedStringValue)
  {
    ArrayList localArrayList = Lists.newArrayList();
    localArrayList.add(ContentProviderOperation.newDelete(Uri.parse("content://mms/" + paramLong + "/addr")).withSelection("type=" + paramInt, null).build());
    persistAddress(localArrayList, paramLong, paramInt, paramArrayOfEncodedStringValue);
    try
    {
      mContentResolver.applyBatch("mms", localArrayList);
      return;
    }
    catch (OperationApplicationException paramArrayOfEncodedStringValue)
    {
      Log.e("MiuiPduPersister", "Error while applying batch", paramArrayOfEncodedStringValue);
      return;
    }
    catch (RemoteException paramArrayOfEncodedStringValue)
    {
      Log.e("MiuiPduPersister", "Error while applying batch", paramArrayOfEncodedStringValue);
    }
  }
  
  private void updatePart(Uri paramUri, PduPart paramPduPart)
    throws MmsException
  {
    ContentValues localContentValues = new ContentValues(7);
    int i = paramPduPart.getCharset();
    if (i != 0) {
      localContentValues.put("chset", Integer.valueOf(i));
    }
    if (paramPduPart.getContentType() != null)
    {
      String str = toIsoString(paramPduPart.getContentType());
      localContentValues.put("ct", str);
      if (paramPduPart.getFilename() != null) {
        localContentValues.put("fn", new String(paramPduPart.getFilename()));
      }
      if (paramPduPart.getName() != null) {
        localContentValues.put("name", new String(paramPduPart.getName()));
      }
      if (paramPduPart.getContentDisposition() != null) {
        localContentValues.put("cd", (String)toIsoString(paramPduPart.getContentDisposition()));
      }
      if (paramPduPart.getContentId() != null) {
        localContentValues.put("cid", (String)toIsoString(paramPduPart.getContentId()));
      }
      if (paramPduPart.getContentLocation() != null) {
        localContentValues.put("cl", (String)toIsoString(paramPduPart.getContentLocation()));
      }
      SqliteWrapper.update(mContext, mContentResolver, paramUri, localContentValues, null, null);
      if ((paramPduPart.getData() != null) || (paramUri != paramPduPart.getDataUri())) {
        persistData(paramPduPart, paramUri, str);
      }
      return;
    }
    throw new MmsException("MIME type of the part must be set.");
  }
  
  public Cursor getPendingMessages(long paramLong)
  {
    Uri.Builder localBuilder = Telephony.MmsSms.PendingMessages.CONTENT_URI.buildUpon();
    localBuilder.appendQueryParameter("protocol", "mms");
    return SqliteWrapper.query(mContext, mContentResolver, localBuilder.build(), null, "err_type < ? AND due_time <= ?", new String[] { String.valueOf(10), String.valueOf(paramLong) }, "due_time");
  }
  
  /* Error */
  public GenericPdu load(Uri paramUri)
    throws MmsException
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 16
    //   3: iconst_0
    //   4: istore 4
    //   6: iconst_0
    //   7: istore_3
    //   8: iconst_0
    //   9: istore_2
    //   10: ldc2_w 805
    //   13: lstore 11
    //   15: getstatic 260	com/google/android/mms/pdu/MiuiPduPersister:PDU_CACHE_INSTANCE	Lcom/google/android/mms/util/PduCache;
    //   18: astore 18
    //   20: aload 18
    //   22: monitorenter
    //   23: getstatic 260	com/google/android/mms/pdu/MiuiPduPersister:PDU_CACHE_INSTANCE	Lcom/google/android/mms/util/PduCache;
    //   26: aload_1
    //   27: invokevirtual 810	com/google/android/mms/util/PduCache:isUpdating	(Landroid/net/Uri;)Z
    //   30: istore 15
    //   32: iload 15
    //   34: ifeq +150 -> 184
    //   37: getstatic 260	com/google/android/mms/pdu/MiuiPduPersister:PDU_CACHE_INSTANCE	Lcom/google/android/mms/util/PduCache;
    //   40: invokevirtual 813	java/lang/Object:wait	()V
    //   43: getstatic 260	com/google/android/mms/pdu/MiuiPduPersister:PDU_CACHE_INSTANCE	Lcom/google/android/mms/util/PduCache;
    //   46: aload_1
    //   47: invokevirtual 814	com/google/android/mms/util/PduCache:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   50: checkcast 816	com/google/android/mms/util/PduCacheEntry
    //   53: astore 17
    //   55: aload 17
    //   57: astore 16
    //   59: aload 17
    //   61: ifnull +123 -> 184
    //   64: aload 17
    //   66: invokevirtual 820	com/google/android/mms/util/PduCacheEntry:getPdu	()Lcom/google/android/mms/pdu/GenericPdu;
    //   69: astore 17
    //   71: aload 18
    //   73: monitorexit
    //   74: getstatic 260	com/google/android/mms/pdu/MiuiPduPersister:PDU_CACHE_INSTANCE	Lcom/google/android/mms/util/PduCache;
    //   77: astore 16
    //   79: aload 16
    //   81: monitorenter
    //   82: iconst_0
    //   83: ifeq +1284 -> 1367
    //   86: getstatic 120	com/google/android/mms/pdu/MiuiPduPersister:$assertionsDisabled	Z
    //   89: ifne +1254 -> 1343
    //   92: getstatic 260	com/google/android/mms/pdu/MiuiPduPersister:PDU_CACHE_INSTANCE	Lcom/google/android/mms/util/PduCache;
    //   95: aload_1
    //   96: invokevirtual 814	com/google/android/mms/util/PduCache:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   99: ifnull +1244 -> 1343
    //   102: new 822	java/lang/AssertionError
    //   105: dup
    //   106: invokespecial 823	java/lang/AssertionError:<init>	()V
    //   109: athrow
    //   110: aload 16
    //   112: monitorexit
    //   113: aload_1
    //   114: athrow
    //   115: astore 16
    //   117: ldc 99
    //   119: ldc_w 825
    //   122: aload 16
    //   124: invokestatic 363	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   127: pop
    //   128: goto -85 -> 43
    //   131: aload 18
    //   133: monitorexit
    //   134: aload 16
    //   136: athrow
    //   137: astore 16
    //   139: lload 11
    //   141: lstore 7
    //   143: getstatic 260	com/google/android/mms/pdu/MiuiPduPersister:PDU_CACHE_INSTANCE	Lcom/google/android/mms/util/PduCache;
    //   146: astore 17
    //   148: aload 17
    //   150: monitorenter
    //   151: iconst_0
    //   152: ifeq +1126 -> 1278
    //   155: getstatic 120	com/google/android/mms/pdu/MiuiPduPersister:$assertionsDisabled	Z
    //   158: ifne +1097 -> 1255
    //   161: getstatic 260	com/google/android/mms/pdu/MiuiPduPersister:PDU_CACHE_INSTANCE	Lcom/google/android/mms/util/PduCache;
    //   164: aload_1
    //   165: invokevirtual 814	com/google/android/mms/util/PduCache:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   168: ifnull +1087 -> 1255
    //   171: new 822	java/lang/AssertionError
    //   174: dup
    //   175: invokespecial 823	java/lang/AssertionError:<init>	()V
    //   178: athrow
    //   179: aload 17
    //   181: monitorexit
    //   182: aload_1
    //   183: athrow
    //   184: getstatic 260	com/google/android/mms/pdu/MiuiPduPersister:PDU_CACHE_INSTANCE	Lcom/google/android/mms/util/PduCache;
    //   187: aload_1
    //   188: iconst_1
    //   189: invokevirtual 829	com/google/android/mms/util/PduCache:setUpdating	(Landroid/net/Uri;Z)V
    //   192: aload 18
    //   194: monitorexit
    //   195: iload_3
    //   196: istore_2
    //   197: lload 11
    //   199: lstore 7
    //   201: aload_0
    //   202: getfield 265	com/google/android/mms/pdu/MiuiPduPersister:mContext	Landroid/content/Context;
    //   205: aload_0
    //   206: getfield 273	com/google/android/mms/pdu/MiuiPduPersister:mContentResolver	Landroid/content/ContentResolver;
    //   209: aload_1
    //   210: getstatic 180	com/google/android/mms/pdu/MiuiPduPersister:PDU_PROJECTION	[Ljava/lang/String;
    //   213: aconst_null
    //   214: aconst_null
    //   215: aconst_null
    //   216: invokestatic 408	com/google/android/mms/util/SqliteWrapper:query	(Landroid/content/Context;Landroid/content/ContentResolver;Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   219: astore 17
    //   221: iload_3
    //   222: istore_2
    //   223: lload 11
    //   225: lstore 7
    //   227: new 432	com/google/android/mms/pdu/PduHeaders
    //   230: dup
    //   231: invokespecial 830	com/google/android/mms/pdu/PduHeaders:<init>	()V
    //   234: astore 16
    //   236: iload_3
    //   237: istore_2
    //   238: lload 11
    //   240: lstore 7
    //   242: aload_1
    //   243: invokestatic 836	android/content/ContentUris:parseId	(Landroid/net/Uri;)J
    //   246: lstore 13
    //   248: aload 17
    //   250: ifnull +38 -> 288
    //   253: iload 4
    //   255: istore_3
    //   256: lload 11
    //   258: lstore 9
    //   260: aload 17
    //   262: invokeinterface 315 1 0
    //   267: iconst_1
    //   268: if_icmpne +20 -> 288
    //   271: iload 4
    //   273: istore_3
    //   274: lload 11
    //   276: lstore 9
    //   278: aload 17
    //   280: invokeinterface 318 1 0
    //   285: ifne +72 -> 357
    //   288: iload 4
    //   290: istore_3
    //   291: lload 11
    //   293: lstore 9
    //   295: new 443	com/google/android/mms/MmsException
    //   298: dup
    //   299: new 380	java/lang/StringBuilder
    //   302: dup
    //   303: invokespecial 381	java/lang/StringBuilder:<init>	()V
    //   306: ldc_w 838
    //   309: invokevirtual 387	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   312: aload_1
    //   313: invokevirtual 656	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   316: invokevirtual 393	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   319: invokespecial 533	com/google/android/mms/MmsException:<init>	(Ljava/lang/String;)V
    //   322: athrow
    //   323: astore 16
    //   325: aload 17
    //   327: ifnull +16 -> 343
    //   330: iload_3
    //   331: istore_2
    //   332: lload 9
    //   334: lstore 7
    //   336: aload 17
    //   338: invokeinterface 330 1 0
    //   343: iload_3
    //   344: istore_2
    //   345: lload 9
    //   347: lstore 7
    //   349: aload 16
    //   351: athrow
    //   352: astore 16
    //   354: goto -211 -> 143
    //   357: iload 4
    //   359: istore_3
    //   360: lload 11
    //   362: lstore 9
    //   364: aload 17
    //   366: iconst_1
    //   367: invokeinterface 369 2 0
    //   372: istore 4
    //   374: iload 4
    //   376: istore_3
    //   377: lload 11
    //   379: lstore 9
    //   381: aload 17
    //   383: iconst_2
    //   384: invokeinterface 484 2 0
    //   389: lstore 11
    //   391: iload 4
    //   393: istore_3
    //   394: lload 11
    //   396: lstore 9
    //   398: getstatic 236	com/google/android/mms/pdu/MiuiPduPersister:ENCODED_STRING_COLUMN_INDEX_MAP	Ljava/util/HashMap;
    //   401: invokevirtual 842	java/util/HashMap:entrySet	()Ljava/util/Set;
    //   404: invokeinterface 848 1 0
    //   409: astore 18
    //   411: iload 4
    //   413: istore_3
    //   414: lload 11
    //   416: lstore 9
    //   418: aload 18
    //   420: invokeinterface 853 1 0
    //   425: ifeq +66 -> 491
    //   428: iload 4
    //   430: istore_3
    //   431: lload 11
    //   433: lstore 9
    //   435: aload 18
    //   437: invokeinterface 857 1 0
    //   442: checkcast 859	java/util/Map$Entry
    //   445: astore 19
    //   447: iload 4
    //   449: istore_3
    //   450: lload 11
    //   452: lstore 9
    //   454: aload_0
    //   455: aload 17
    //   457: aload 19
    //   459: invokeinterface 862 1 0
    //   464: checkcast 213	java/lang/Integer
    //   467: invokevirtual 455	java/lang/Integer:intValue	()I
    //   470: aload 16
    //   472: aload 19
    //   474: invokeinterface 865 1 0
    //   479: checkcast 213	java/lang/Integer
    //   482: invokevirtual 455	java/lang/Integer:intValue	()I
    //   485: invokespecial 867	com/google/android/mms/pdu/MiuiPduPersister:setEncodedStringValueToHeaders	(Landroid/database/Cursor;ILcom/google/android/mms/pdu/PduHeaders;I)V
    //   488: goto -77 -> 411
    //   491: iload 4
    //   493: istore_3
    //   494: lload 11
    //   496: lstore 9
    //   498: getstatic 240	com/google/android/mms/pdu/MiuiPduPersister:TEXT_STRING_COLUMN_INDEX_MAP	Ljava/util/HashMap;
    //   501: invokevirtual 842	java/util/HashMap:entrySet	()Ljava/util/Set;
    //   504: invokeinterface 848 1 0
    //   509: astore 18
    //   511: iload 4
    //   513: istore_3
    //   514: lload 11
    //   516: lstore 9
    //   518: aload 18
    //   520: invokeinterface 853 1 0
    //   525: ifeq +66 -> 591
    //   528: iload 4
    //   530: istore_3
    //   531: lload 11
    //   533: lstore 9
    //   535: aload 18
    //   537: invokeinterface 857 1 0
    //   542: checkcast 859	java/util/Map$Entry
    //   545: astore 19
    //   547: iload 4
    //   549: istore_3
    //   550: lload 11
    //   552: lstore 9
    //   554: aload_0
    //   555: aload 17
    //   557: aload 19
    //   559: invokeinterface 862 1 0
    //   564: checkcast 213	java/lang/Integer
    //   567: invokevirtual 455	java/lang/Integer:intValue	()I
    //   570: aload 16
    //   572: aload 19
    //   574: invokeinterface 865 1 0
    //   579: checkcast 213	java/lang/Integer
    //   582: invokevirtual 455	java/lang/Integer:intValue	()I
    //   585: invokespecial 869	com/google/android/mms/pdu/MiuiPduPersister:setTextStringToHeaders	(Landroid/database/Cursor;ILcom/google/android/mms/pdu/PduHeaders;I)V
    //   588: goto -77 -> 511
    //   591: iload 4
    //   593: istore_3
    //   594: lload 11
    //   596: lstore 9
    //   598: getstatic 244	com/google/android/mms/pdu/MiuiPduPersister:OCTET_COLUMN_INDEX_MAP	Ljava/util/HashMap;
    //   601: invokevirtual 842	java/util/HashMap:entrySet	()Ljava/util/Set;
    //   604: invokeinterface 848 1 0
    //   609: astore 18
    //   611: iload 4
    //   613: istore_3
    //   614: lload 11
    //   616: lstore 9
    //   618: aload 18
    //   620: invokeinterface 853 1 0
    //   625: ifeq +66 -> 691
    //   628: iload 4
    //   630: istore_3
    //   631: lload 11
    //   633: lstore 9
    //   635: aload 18
    //   637: invokeinterface 857 1 0
    //   642: checkcast 859	java/util/Map$Entry
    //   645: astore 19
    //   647: iload 4
    //   649: istore_3
    //   650: lload 11
    //   652: lstore 9
    //   654: aload_0
    //   655: aload 17
    //   657: aload 19
    //   659: invokeinterface 862 1 0
    //   664: checkcast 213	java/lang/Integer
    //   667: invokevirtual 455	java/lang/Integer:intValue	()I
    //   670: aload 16
    //   672: aload 19
    //   674: invokeinterface 865 1 0
    //   679: checkcast 213	java/lang/Integer
    //   682: invokevirtual 455	java/lang/Integer:intValue	()I
    //   685: invokespecial 871	com/google/android/mms/pdu/MiuiPduPersister:setOctetToHeaders	(Landroid/database/Cursor;ILcom/google/android/mms/pdu/PduHeaders;I)V
    //   688: goto -77 -> 611
    //   691: iload 4
    //   693: istore_3
    //   694: lload 11
    //   696: lstore 9
    //   698: getstatic 248	com/google/android/mms/pdu/MiuiPduPersister:LONG_COLUMN_INDEX_MAP	Ljava/util/HashMap;
    //   701: invokevirtual 842	java/util/HashMap:entrySet	()Ljava/util/Set;
    //   704: invokeinterface 848 1 0
    //   709: astore 18
    //   711: iload 4
    //   713: istore_3
    //   714: lload 11
    //   716: lstore 9
    //   718: aload 18
    //   720: invokeinterface 853 1 0
    //   725: ifeq +66 -> 791
    //   728: iload 4
    //   730: istore_3
    //   731: lload 11
    //   733: lstore 9
    //   735: aload 18
    //   737: invokeinterface 857 1 0
    //   742: checkcast 859	java/util/Map$Entry
    //   745: astore 19
    //   747: iload 4
    //   749: istore_3
    //   750: lload 11
    //   752: lstore 9
    //   754: aload_0
    //   755: aload 17
    //   757: aload 19
    //   759: invokeinterface 862 1 0
    //   764: checkcast 213	java/lang/Integer
    //   767: invokevirtual 455	java/lang/Integer:intValue	()I
    //   770: aload 16
    //   772: aload 19
    //   774: invokeinterface 865 1 0
    //   779: checkcast 213	java/lang/Integer
    //   782: invokevirtual 455	java/lang/Integer:intValue	()I
    //   785: invokespecial 873	com/google/android/mms/pdu/MiuiPduPersister:setLongToHeaders	(Landroid/database/Cursor;ILcom/google/android/mms/pdu/PduHeaders;I)V
    //   788: goto -77 -> 711
    //   791: aload 17
    //   793: ifnull +17 -> 810
    //   796: iload 4
    //   798: istore_2
    //   799: lload 11
    //   801: lstore 7
    //   803: aload 17
    //   805: invokeinterface 330 1 0
    //   810: lload 13
    //   812: ldc2_w 805
    //   815: lcmp
    //   816: ifne +21 -> 837
    //   819: iload 4
    //   821: istore_2
    //   822: lload 11
    //   824: lstore 7
    //   826: new 443	com/google/android/mms/MmsException
    //   829: dup
    //   830: ldc_w 875
    //   833: invokespecial 533	com/google/android/mms/MmsException:<init>	(Ljava/lang/String;)V
    //   836: athrow
    //   837: iload 4
    //   839: istore_2
    //   840: lload 11
    //   842: lstore 7
    //   844: aload_0
    //   845: lload 13
    //   847: aload 16
    //   849: invokespecial 877	com/google/android/mms/pdu/MiuiPduPersister:loadAddress	(JLcom/google/android/mms/pdu/PduHeaders;)V
    //   852: iload 4
    //   854: istore_2
    //   855: lload 11
    //   857: lstore 7
    //   859: aload 16
    //   861: sipush 140
    //   864: invokevirtual 880	com/google/android/mms/pdu/PduHeaders:getOctet	(I)I
    //   867: istore 5
    //   869: iload 4
    //   871: istore_2
    //   872: lload 11
    //   874: lstore 7
    //   876: new 882	com/google/android/mms/pdu/PduBody
    //   879: dup
    //   880: invokespecial 883	com/google/android/mms/pdu/PduBody:<init>	()V
    //   883: astore 17
    //   885: iload 5
    //   887: sipush 132
    //   890: if_icmpeq +11 -> 901
    //   893: iload 5
    //   895: sipush 128
    //   898: if_icmpne +522 -> 1420
    //   901: iload 4
    //   903: istore_2
    //   904: lload 11
    //   906: lstore 7
    //   908: aload_0
    //   909: lload 13
    //   911: invokespecial 885	com/google/android/mms/pdu/MiuiPduPersister:loadParts	(J)[Lcom/google/android/mms/pdu/PduPart;
    //   914: astore 18
    //   916: aload 18
    //   918: ifnull +502 -> 1420
    //   921: iload 4
    //   923: istore_2
    //   924: lload 11
    //   926: lstore 7
    //   928: aload 18
    //   930: arraylength
    //   931: istore 6
    //   933: iconst_0
    //   934: istore_3
    //   935: iload_3
    //   936: iload 6
    //   938: if_icmpge +482 -> 1420
    //   941: iload 4
    //   943: istore_2
    //   944: lload 11
    //   946: lstore 7
    //   948: aload 17
    //   950: aload 18
    //   952: iload_3
    //   953: aaload
    //   954: invokevirtual 889	com/google/android/mms/pdu/PduBody:addPart	(Lcom/google/android/mms/pdu/PduPart;)Z
    //   957: pop
    //   958: iload_3
    //   959: iconst_1
    //   960: iadd
    //   961: istore_3
    //   962: goto -27 -> 935
    //   965: iload 4
    //   967: istore_2
    //   968: lload 11
    //   970: lstore 7
    //   972: new 443	com/google/android/mms/MmsException
    //   975: dup
    //   976: new 380	java/lang/StringBuilder
    //   979: dup
    //   980: invokespecial 381	java/lang/StringBuilder:<init>	()V
    //   983: ldc_w 891
    //   986: invokevirtual 387	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   989: iload 5
    //   991: invokestatic 894	java/lang/Integer:toHexString	(I)Ljava/lang/String;
    //   994: invokevirtual 387	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   997: invokevirtual 393	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1000: invokespecial 533	com/google/android/mms/MmsException:<init>	(Ljava/lang/String;)V
    //   1003: athrow
    //   1004: iload 4
    //   1006: istore_2
    //   1007: lload 11
    //   1009: lstore 7
    //   1011: new 896	com/google/android/mms/pdu/NotificationInd
    //   1014: dup
    //   1015: aload 16
    //   1017: invokespecial 899	com/google/android/mms/pdu/NotificationInd:<init>	(Lcom/google/android/mms/pdu/PduHeaders;)V
    //   1020: astore 16
    //   1022: getstatic 260	com/google/android/mms/pdu/MiuiPduPersister:PDU_CACHE_INSTANCE	Lcom/google/android/mms/util/PduCache;
    //   1025: astore 17
    //   1027: aload 17
    //   1029: monitorenter
    //   1030: aload 16
    //   1032: ifnull +372 -> 1404
    //   1035: getstatic 120	com/google/android/mms/pdu/MiuiPduPersister:$assertionsDisabled	Z
    //   1038: ifne +260 -> 1298
    //   1041: getstatic 260	com/google/android/mms/pdu/MiuiPduPersister:PDU_CACHE_INSTANCE	Lcom/google/android/mms/util/PduCache;
    //   1044: aload_1
    //   1045: invokevirtual 814	com/google/android/mms/util/PduCache:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   1048: ifnull +250 -> 1298
    //   1051: new 822	java/lang/AssertionError
    //   1054: dup
    //   1055: invokespecial 823	java/lang/AssertionError:<init>	()V
    //   1058: athrow
    //   1059: astore_1
    //   1060: aload 17
    //   1062: monitorexit
    //   1063: aload_1
    //   1064: athrow
    //   1065: iload 4
    //   1067: istore_2
    //   1068: lload 11
    //   1070: lstore 7
    //   1072: new 901	com/google/android/mms/pdu/DeliveryInd
    //   1075: dup
    //   1076: aload 16
    //   1078: invokespecial 902	com/google/android/mms/pdu/DeliveryInd:<init>	(Lcom/google/android/mms/pdu/PduHeaders;)V
    //   1081: astore 16
    //   1083: goto -61 -> 1022
    //   1086: iload 4
    //   1088: istore_2
    //   1089: lload 11
    //   1091: lstore 7
    //   1093: new 904	com/google/android/mms/pdu/ReadOrigInd
    //   1096: dup
    //   1097: aload 16
    //   1099: invokespecial 905	com/google/android/mms/pdu/ReadOrigInd:<init>	(Lcom/google/android/mms/pdu/PduHeaders;)V
    //   1102: astore 16
    //   1104: goto -82 -> 1022
    //   1107: iload 4
    //   1109: istore_2
    //   1110: lload 11
    //   1112: lstore 7
    //   1114: new 907	com/google/android/mms/pdu/RetrieveConf
    //   1117: dup
    //   1118: aload 16
    //   1120: aload 17
    //   1122: invokespecial 910	com/google/android/mms/pdu/RetrieveConf:<init>	(Lcom/google/android/mms/pdu/PduHeaders;Lcom/google/android/mms/pdu/PduBody;)V
    //   1125: astore 16
    //   1127: goto -105 -> 1022
    //   1130: iload 4
    //   1132: istore_2
    //   1133: lload 11
    //   1135: lstore 7
    //   1137: new 912	com/google/android/mms/pdu/SendReq
    //   1140: dup
    //   1141: aload 16
    //   1143: aload 17
    //   1145: invokespecial 913	com/google/android/mms/pdu/SendReq:<init>	(Lcom/google/android/mms/pdu/PduHeaders;Lcom/google/android/mms/pdu/PduBody;)V
    //   1148: astore 16
    //   1150: goto -128 -> 1022
    //   1153: iload 4
    //   1155: istore_2
    //   1156: lload 11
    //   1158: lstore 7
    //   1160: new 915	com/google/android/mms/pdu/AcknowledgeInd
    //   1163: dup
    //   1164: aload 16
    //   1166: invokespecial 916	com/google/android/mms/pdu/AcknowledgeInd:<init>	(Lcom/google/android/mms/pdu/PduHeaders;)V
    //   1169: astore 16
    //   1171: goto -149 -> 1022
    //   1174: iload 4
    //   1176: istore_2
    //   1177: lload 11
    //   1179: lstore 7
    //   1181: new 918	com/google/android/mms/pdu/NotifyRespInd
    //   1184: dup
    //   1185: aload 16
    //   1187: invokespecial 919	com/google/android/mms/pdu/NotifyRespInd:<init>	(Lcom/google/android/mms/pdu/PduHeaders;)V
    //   1190: astore 16
    //   1192: goto -170 -> 1022
    //   1195: iload 4
    //   1197: istore_2
    //   1198: lload 11
    //   1200: lstore 7
    //   1202: new 921	com/google/android/mms/pdu/ReadRecInd
    //   1205: dup
    //   1206: aload 16
    //   1208: invokespecial 922	com/google/android/mms/pdu/ReadRecInd:<init>	(Lcom/google/android/mms/pdu/PduHeaders;)V
    //   1211: astore 16
    //   1213: goto -191 -> 1022
    //   1216: iload 4
    //   1218: istore_2
    //   1219: lload 11
    //   1221: lstore 7
    //   1223: new 443	com/google/android/mms/MmsException
    //   1226: dup
    //   1227: new 380	java/lang/StringBuilder
    //   1230: dup
    //   1231: invokespecial 381	java/lang/StringBuilder:<init>	()V
    //   1234: ldc_w 924
    //   1237: invokevirtual 387	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1240: iload 5
    //   1242: invokestatic 894	java/lang/Integer:toHexString	(I)Ljava/lang/String;
    //   1245: invokevirtual 387	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1248: invokevirtual 393	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1251: invokespecial 533	com/google/android/mms/MmsException:<init>	(Ljava/lang/String;)V
    //   1254: athrow
    //   1255: new 816	com/google/android/mms/util/PduCacheEntry
    //   1258: dup
    //   1259: aconst_null
    //   1260: iload_2
    //   1261: lload 7
    //   1263: invokespecial 927	com/google/android/mms/util/PduCacheEntry:<init>	(Lcom/google/android/mms/pdu/GenericPdu;IJ)V
    //   1266: astore 18
    //   1268: getstatic 260	com/google/android/mms/pdu/MiuiPduPersister:PDU_CACHE_INSTANCE	Lcom/google/android/mms/util/PduCache;
    //   1271: aload_1
    //   1272: aload 18
    //   1274: invokevirtual 930	com/google/android/mms/util/PduCache:put	(Landroid/net/Uri;Lcom/google/android/mms/util/PduCacheEntry;)Z
    //   1277: pop
    //   1278: getstatic 260	com/google/android/mms/pdu/MiuiPduPersister:PDU_CACHE_INSTANCE	Lcom/google/android/mms/util/PduCache;
    //   1281: aload_1
    //   1282: iconst_0
    //   1283: invokevirtual 829	com/google/android/mms/util/PduCache:setUpdating	(Landroid/net/Uri;Z)V
    //   1286: getstatic 260	com/google/android/mms/pdu/MiuiPduPersister:PDU_CACHE_INSTANCE	Lcom/google/android/mms/util/PduCache;
    //   1289: invokevirtual 933	java/lang/Object:notifyAll	()V
    //   1292: aload 17
    //   1294: monitorexit
    //   1295: aload 16
    //   1297: athrow
    //   1298: new 816	com/google/android/mms/util/PduCacheEntry
    //   1301: dup
    //   1302: aload 16
    //   1304: iload 4
    //   1306: lload 11
    //   1308: invokespecial 927	com/google/android/mms/util/PduCacheEntry:<init>	(Lcom/google/android/mms/pdu/GenericPdu;IJ)V
    //   1311: astore 18
    //   1313: getstatic 260	com/google/android/mms/pdu/MiuiPduPersister:PDU_CACHE_INSTANCE	Lcom/google/android/mms/util/PduCache;
    //   1316: aload_1
    //   1317: aload 18
    //   1319: invokevirtual 930	com/google/android/mms/util/PduCache:put	(Landroid/net/Uri;Lcom/google/android/mms/util/PduCacheEntry;)Z
    //   1322: pop
    //   1323: getstatic 260	com/google/android/mms/pdu/MiuiPduPersister:PDU_CACHE_INSTANCE	Lcom/google/android/mms/util/PduCache;
    //   1326: aload_1
    //   1327: iconst_0
    //   1328: invokevirtual 829	com/google/android/mms/util/PduCache:setUpdating	(Landroid/net/Uri;Z)V
    //   1331: getstatic 260	com/google/android/mms/pdu/MiuiPduPersister:PDU_CACHE_INSTANCE	Lcom/google/android/mms/util/PduCache;
    //   1334: invokevirtual 933	java/lang/Object:notifyAll	()V
    //   1337: aload 17
    //   1339: monitorexit
    //   1340: aload 16
    //   1342: areturn
    //   1343: new 816	com/google/android/mms/util/PduCacheEntry
    //   1346: dup
    //   1347: aconst_null
    //   1348: iconst_0
    //   1349: ldc2_w 805
    //   1352: invokespecial 927	com/google/android/mms/util/PduCacheEntry:<init>	(Lcom/google/android/mms/pdu/GenericPdu;IJ)V
    //   1355: astore 18
    //   1357: getstatic 260	com/google/android/mms/pdu/MiuiPduPersister:PDU_CACHE_INSTANCE	Lcom/google/android/mms/util/PduCache;
    //   1360: aload_1
    //   1361: aload 18
    //   1363: invokevirtual 930	com/google/android/mms/util/PduCache:put	(Landroid/net/Uri;Lcom/google/android/mms/util/PduCacheEntry;)Z
    //   1366: pop
    //   1367: getstatic 260	com/google/android/mms/pdu/MiuiPduPersister:PDU_CACHE_INSTANCE	Lcom/google/android/mms/util/PduCache;
    //   1370: aload_1
    //   1371: iconst_0
    //   1372: invokevirtual 829	com/google/android/mms/util/PduCache:setUpdating	(Landroid/net/Uri;Z)V
    //   1375: getstatic 260	com/google/android/mms/pdu/MiuiPduPersister:PDU_CACHE_INSTANCE	Lcom/google/android/mms/util/PduCache;
    //   1378: invokevirtual 933	java/lang/Object:notifyAll	()V
    //   1381: aload 16
    //   1383: monitorexit
    //   1384: aload 17
    //   1386: areturn
    //   1387: astore_1
    //   1388: goto -328 -> 1060
    //   1391: astore_1
    //   1392: goto -1282 -> 110
    //   1395: astore_1
    //   1396: goto -1217 -> 179
    //   1399: astore 16
    //   1401: goto -1270 -> 131
    //   1404: goto -81 -> 1323
    //   1407: astore_1
    //   1408: goto -1298 -> 110
    //   1411: astore 16
    //   1413: goto -1282 -> 131
    //   1416: astore_1
    //   1417: goto -1238 -> 179
    //   1420: iload 5
    //   1422: tableswitch	default:+110->1532, 128:+-292->1130, 129:+-206->1216, 130:+-418->1004, 131:+-248->1174, 132:+-315->1107, 133:+-269->1153, 134:+-357->1065, 135:+-227->1195, 136:+-336->1086, 137:+-206->1216, 138:+-206->1216, 139:+-206->1216, 140:+-206->1216, 141:+-206->1216, 142:+-206->1216, 143:+-206->1216, 144:+-206->1216, 145:+-206->1216, 146:+-206->1216, 147:+-206->1216, 148:+-206->1216, 149:+-206->1216, 150:+-206->1216, 151:+-206->1216
    //   1532: goto -567 -> 965
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	1535	0	this	MiuiPduPersister
    //   0	1535	1	paramUri	Uri
    //   9	1252	2	i	int
    //   7	955	3	j	int
    //   4	1301	4	k	int
    //   867	554	5	m	int
    //   931	8	6	n	int
    //   141	1121	7	l1	long
    //   258	495	9	l2	long
    //   13	1294	11	l3	long
    //   246	664	13	l4	long
    //   30	3	15	bool	boolean
    //   1	110	16	localObject1	Object
    //   115	20	16	localInterruptedException	InterruptedException
    //   137	1	16	localObject2	Object
    //   234	1	16	localPduHeaders1	PduHeaders
    //   323	27	16	localObject3	Object
    //   352	664	16	localPduHeaders2	PduHeaders
    //   1020	362	16	localObject4	Object
    //   1399	1	16	localObject5	Object
    //   1411	1	16	localObject6	Object
    //   53	1332	17	localObject7	Object
    //   18	1344	18	localObject8	Object
    //   445	328	19	localEntry	Map.Entry
    // Exception table:
    //   from	to	target	type
    //   37	43	115	java/lang/InterruptedException
    //   15	23	137	finally
    //   134	137	137	finally
    //   260	271	323	finally
    //   278	288	323	finally
    //   295	323	323	finally
    //   364	374	323	finally
    //   381	391	323	finally
    //   398	411	323	finally
    //   418	428	323	finally
    //   435	447	323	finally
    //   454	488	323	finally
    //   498	511	323	finally
    //   518	528	323	finally
    //   535	547	323	finally
    //   554	588	323	finally
    //   598	611	323	finally
    //   618	628	323	finally
    //   635	647	323	finally
    //   654	688	323	finally
    //   698	711	323	finally
    //   718	728	323	finally
    //   735	747	323	finally
    //   754	788	323	finally
    //   201	221	352	finally
    //   227	236	352	finally
    //   242	248	352	finally
    //   336	343	352	finally
    //   349	352	352	finally
    //   803	810	352	finally
    //   826	837	352	finally
    //   844	852	352	finally
    //   859	869	352	finally
    //   876	885	352	finally
    //   908	916	352	finally
    //   928	933	352	finally
    //   948	958	352	finally
    //   972	1004	352	finally
    //   1011	1022	352	finally
    //   1072	1083	352	finally
    //   1093	1104	352	finally
    //   1114	1127	352	finally
    //   1137	1150	352	finally
    //   1160	1171	352	finally
    //   1181	1192	352	finally
    //   1202	1213	352	finally
    //   1223	1255	352	finally
    //   1035	1059	1059	finally
    //   1298	1313	1059	finally
    //   1060	1063	1387	finally
    //   1313	1323	1387	finally
    //   1323	1340	1387	finally
    //   1357	1367	1391	finally
    //   1268	1278	1395	finally
    //   184	195	1399	finally
    //   86	110	1407	finally
    //   110	113	1407	finally
    //   1343	1357	1407	finally
    //   1367	1384	1407	finally
    //   23	32	1411	finally
    //   37	43	1411	finally
    //   43	55	1411	finally
    //   64	74	1411	finally
    //   117	128	1411	finally
    //   131	134	1411	finally
    //   155	179	1416	finally
    //   179	182	1416	finally
    //   1255	1268	1416	finally
    //   1278	1295	1416	finally
  }
  
  public Uri move(Uri paramUri1, Uri paramUri2)
    throws MmsException
  {
    long l = ContentUris.parseId(paramUri1);
    if (l == -1L) {
      throw new MmsException("Error! ID of the message: -1.");
    }
    Integer localInteger = (Integer)MESSAGE_BOX_MAP.get(paramUri2);
    if (localInteger == null) {
      throw new MmsException("Bad destination, must be one of content://mms/inbox, content://mms/sent, content://mms/drafts, content://mms/outbox, content://mms/temp.");
    }
    ContentValues localContentValues = new ContentValues(1);
    localContentValues.put("msg_box", localInteger);
    SqliteWrapper.update(mContext, mContentResolver, paramUri1, localContentValues, null, null);
    return ContentUris.withAppendedId(paramUri2, l);
  }
  
  public Uri persist(GenericPdu paramGenericPdu, Uri paramUri1, Uri paramUri2, long paramLong)
    throws MmsException
  {
    return persist(paramGenericPdu, paramUri1, paramUri2, paramLong, 0);
  }
  
  public Uri persist(GenericPdu paramGenericPdu, Uri paramUri1, Uri paramUri2, long paramLong, int paramInt)
    throws MmsException
  {
    if (paramUri1 == null) {
      throw new MmsException("Uri may not be null.");
    }
    long l1 = -1L;
    try
    {
      l2 = ContentUris.parseId(paramUri1);
      l1 = l2;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      long l2;
      int i;
      ContentValues localContentValues;
      Object localObject2;
      Object localObject3;
      Object localObject4;
      int j;
      int k;
      ContentProviderResult[] arrayOfContentProviderResult;
      for (;;) {}
    }
    if (l1 != -1L) {}
    for (i = 1; (i == 0) && (MESSAGE_BOX_MAP.get(paramUri1) == null); i = 0) {
      throw new MmsException("Bad destination, must be one of content://mms/inbox, content://mms/sent, content://mms/drafts, content://mms/outbox, content://mms/temp.");
    }
    synchronized (PDU_CACHE_INSTANCE)
    {
      boolean bool = PDU_CACHE_INSTANCE.isUpdating(paramUri1);
      if (bool) {}
      try
      {
        PDU_CACHE_INSTANCE.wait();
        PDU_CACHE_INSTANCE.purge(paramUri1);
        PduHeaders localPduHeaders = paramGenericPdu.getPduHeaders();
        localContentValues = new ContentValues();
        localContentValues.put("block_type", Integer.valueOf(paramInt));
        ??? = ENCODED_STRING_COLUMN_NAME_MAP.entrySet().iterator();
        while (((Iterator)???).hasNext())
        {
          localObject2 = (Map.Entry)((Iterator)???).next();
          paramInt = ((Integer)((Map.Entry)localObject2).getKey()).intValue();
          localObject3 = localPduHeaders.getEncodedStringValue(paramInt);
          if (localObject3 != null)
          {
            localObject4 = (String)CHARSET_COLUMN_NAME_MAP.get(Integer.valueOf(paramInt));
            localContentValues.put((String)((Map.Entry)localObject2).getValue(), toIsoString(((EncodedStringValue)localObject3).getTextString()));
            localContentValues.put((String)localObject4, Integer.valueOf(((EncodedStringValue)localObject3).getCharacterSet()));
          }
        }
      }
      catch (InterruptedException localInterruptedException)
      {
        for (;;)
        {
          Log.e("MiuiPduPersister", "persist1: ", localInterruptedException);
        }
      }
    }
    ??? = TEXT_STRING_COLUMN_NAME_MAP.entrySet().iterator();
    while (((Iterator)???).hasNext())
    {
      localObject2 = (Map.Entry)((Iterator)???).next();
      localObject3 = localInterruptedException.getTextString(((Integer)((Map.Entry)localObject2).getKey()).intValue());
      if (localObject3 != null) {
        localContentValues.put((String)((Map.Entry)localObject2).getValue(), toIsoString((byte[])localObject3));
      }
    }
    ??? = OCTET_COLUMN_NAME_MAP.entrySet().iterator();
    while (((Iterator)???).hasNext())
    {
      localObject2 = (Map.Entry)((Iterator)???).next();
      paramInt = localInterruptedException.getOctet(((Integer)((Map.Entry)localObject2).getKey()).intValue());
      if (paramInt != 0) {
        localContentValues.put((String)((Map.Entry)localObject2).getValue(), Integer.valueOf(paramInt));
      }
    }
    ??? = LONG_COLUMN_NAME_MAP.entrySet().iterator();
    while (((Iterator)???).hasNext())
    {
      localObject2 = (Map.Entry)((Iterator)???).next();
      l2 = localInterruptedException.getLongInteger(((Integer)((Map.Entry)localObject2).getKey()).intValue());
      if (l2 != -1L) {
        localContentValues.put((String)((Map.Entry)localObject2).getValue(), Long.valueOf(l2));
      }
    }
    localContentValues.put("date_full", Long.valueOf(System.currentTimeMillis()));
    localObject2 = new HashMap(ADDRESS_FIELDS.length);
    localObject3 = ADDRESS_FIELDS;
    j = localObject3.length;
    paramInt = 0;
    if (paramInt < j)
    {
      k = localObject3[paramInt];
      ??? = null;
      if (k == 137)
      {
        localObject4 = localInterruptedException.getEncodedStringValue(k);
        if (localObject4 != null)
        {
          ??? = new EncodedStringValue[1];
          ???[0] = localObject4;
        }
      }
      for (;;)
      {
        ((HashMap)localObject2).put(Integer.valueOf(k), ???);
        paramInt += 1;
        break;
        ??? = localInterruptedException.getEncodedStringValues(k);
      }
    }
    localObject3 = new HashSet();
    j = paramGenericPdu.getMessageType();
    if (((paramLong == -1L) && (j == 130)) || (j == 132) || (j == 128))
    {
      arrayOfContentProviderResult = null;
      ??? = arrayOfContentProviderResult;
      switch (j)
      {
      default: 
        ??? = arrayOfContentProviderResult;
      }
      while (??? != null)
      {
        k = ???.length;
        paramInt = 0;
        while (paramInt < k)
        {
          arrayOfContentProviderResult = ???[paramInt];
          if (arrayOfContentProviderResult != null) {
            ((HashSet)localObject3).add(arrayOfContentProviderResult.getString());
          }
          paramInt += 1;
        }
        ??? = (EncodedStringValue[])((HashMap)localObject2).get(Integer.valueOf(137));
        continue;
        ??? = (EncodedStringValue[])((HashMap)localObject2).get(Integer.valueOf(151));
      }
      if (!((HashSet)localObject3).isEmpty())
      {
        localContentValues.put("thread_id", Long.valueOf(Telephony.Threads.getOrCreateThreadId(mContext, (Set)localObject3)));
        if ((((HashSet)localObject3).size() == 1) && ((j == 130) || (j == 132)))
        {
          if (!ExtraTelephony.isServiceNumber((String)((HashSet)localObject3).iterator().next())) {
            break label991;
          }
          localContentValues.put("advanced_seen", Integer.valueOf(1));
        }
      }
    }
    for (;;)
    {
      l2 = System.currentTimeMillis();
      if (!(paramGenericPdu instanceof MultimediaMessagePdu)) {
        break;
      }
      paramGenericPdu = ((MultimediaMessagePdu)paramGenericPdu).getBody();
      if (paramGenericPdu == null) {
        break;
      }
      j = paramGenericPdu.getPartsNum();
      paramInt = 0;
      while (paramInt < j)
      {
        persistPart(paramGenericPdu.getPart(paramInt), l2);
        paramInt += 1;
      }
      label991:
      localContentValues.put("advanced_seen", Integer.valueOf(0));
    }
    ??? = Lists.newArrayList();
    paramGenericPdu = null;
    if (i != 0)
    {
      paramGenericPdu = paramUri1;
      ((ArrayList)???).add(ContentProviderOperation.newUpdate(paramUri1).withValues(localContentValues).build());
    }
    for (;;)
    {
      if (paramUri2 != null) {
        ((ArrayList)???).add(ContentProviderOperation.newDelete(paramUri2).build());
      }
      try
      {
        arrayOfContentProviderResult = mContentResolver.applyBatch("mms", (ArrayList)???);
        paramLong = l1;
        paramUri2 = paramGenericPdu;
        if (paramGenericPdu == null)
        {
          paramUri2 = 0uri;
          paramLong = ContentUris.parseId(paramUri2);
        }
        ((ArrayList)???).clear();
        paramGenericPdu = new ContentValues(1);
        paramGenericPdu.put("mid", Long.valueOf(paramLong));
        ((ArrayList)???).add(ContentProviderOperation.newUpdate(Uri.parse("content://mms/" + l2 + "/part")).withValues(paramGenericPdu).build());
        if (i == 0) {
          paramUri2 = Uri.parse(paramUri1 + "/" + paramLong);
        }
        paramGenericPdu = ADDRESS_FIELDS;
        i = paramGenericPdu.length;
        paramInt = 0;
        while (paramInt < i)
        {
          j = paramGenericPdu[paramInt];
          paramUri1 = (EncodedStringValue[])((HashMap)localObject2).get(Integer.valueOf(j));
          if (paramUri1 != null) {
            persistAddress((ArrayList)???, paramLong, j, paramUri1);
          }
          paramInt += 1;
          continue;
          ((ArrayList)???).add(ContentProviderOperation.newInsert(paramUri1).withValues(localContentValues).build());
        }
      }
      catch (OperationApplicationException paramGenericPdu)
      {
        Log.e("MiuiPduPersister", "Error while applying batch", paramGenericPdu);
        throw new MmsException("Error while applying batch.");
      }
      catch (RemoteException paramGenericPdu)
      {
        Log.e("MiuiPduPersister", "Error while applying batch", paramGenericPdu);
        throw new MmsException("Error while applying batch.");
      }
    }
    try
    {
      mContentResolver.applyBatch("mms", (ArrayList)???);
      return paramUri2;
    }
    catch (OperationApplicationException paramGenericPdu)
    {
      Log.e("MiuiPduPersister", "Error while applying batch", paramGenericPdu);
      throw new MmsException("Error while applying batch.");
    }
    catch (RemoteException paramGenericPdu)
    {
      Log.e("MiuiPduPersister", "Error while applying batch", paramGenericPdu);
      throw new MmsException("Error while applying batch.");
    }
  }
  
  public Uri persistPart(PduPart paramPduPart, long paramLong)
    throws MmsException
  {
    Uri localUri = Uri.parse("content://mms/" + paramLong + "/part");
    ContentValues localContentValues = new ContentValues(8);
    int i = paramPduPart.getCharset();
    if (i != 0) {
      localContentValues.put("chset", Integer.valueOf(i));
    }
    Object localObject1;
    Object localObject2;
    if (paramPduPart.getContentType() != null)
    {
      localObject1 = toIsoString(paramPduPart.getContentType());
      localObject2 = localObject1;
      if ("text/x-vCard".equalsIgnoreCase((String)localObject1))
      {
        Log.e("MiuiPduPersister", (String)localObject1 + " -> " + "text/x-vCard");
        localObject2 = "text/x-vCard";
      }
      localObject1 = localObject2;
      if (paramPduPart.getContentLocation() != null) {
        if (!"text/plain".equalsIgnoreCase((String)localObject2))
        {
          localObject1 = localObject2;
          if (!"application/oct-stream".equalsIgnoreCase((String)localObject2)) {}
        }
        else
        {
          String str = new String(paramPduPart.getContentLocation());
          Object localObject3 = localObject2;
          if (str != null) {
            if (!str.endsWith(".vcf"))
            {
              localObject3 = localObject2;
              if (!str.endsWith(".VCF")) {}
            }
            else
            {
              Log.e("MiuiPduPersister", (String)localObject2 + " -> " + "text/x-vCard");
              localObject3 = "text/x-vCard";
            }
          }
          localObject1 = localObject3;
          if (str != null) {
            if (!str.endsWith(".ogg"))
            {
              localObject1 = localObject3;
              if (!str.endsWith(".OGG")) {}
            }
            else
            {
              Log.e("MiuiPduPersister", (String)localObject3 + " -> " + "application/ogg");
              localObject1 = "application/ogg";
            }
          }
        }
      }
      if ((paramPduPart.getContentLocation() != null) && ("text/plain".equalsIgnoreCase((String)localObject1)))
      {
        localObject2 = new String(paramPduPart.getContentLocation());
        if ((localObject2 != null) && ((((String)localObject2).endsWith(".txt")) || (((String)localObject2).endsWith(".TXT"))) && (paramPduPart.getCharset() == 0))
        {
          Log.i("MiuiPduPersister", "Set default text charset to UTF_8");
          localContentValues.put("chset", Integer.valueOf(106));
        }
      }
      localObject2 = localObject1;
      if ("image/jpg".equals(localObject1)) {
        localObject2 = "image/jpeg";
      }
      localContentValues.put("ct", (String)localObject2);
      if ("application/smil".equals(localObject2)) {
        localContentValues.put("seq", Integer.valueOf(-1));
      }
      if (paramPduPart.getFilename() != null) {
        localContentValues.put("fn", new String(paramPduPart.getFilename()));
      }
      if (paramPduPart.getName() != null) {
        localContentValues.put("name", new String(paramPduPart.getName()));
      }
      if (paramPduPart.getContentDisposition() != null) {
        localContentValues.put("cd", (String)toIsoString(paramPduPart.getContentDisposition()));
      }
      if (paramPduPart.getContentId() != null) {
        localContentValues.put("cid", (String)toIsoString(paramPduPart.getContentId()));
      }
      if (paramPduPart.getContentLocation() != null) {
        localContentValues.put("cl", (String)toIsoString(paramPduPart.getContentLocation()));
      }
      localObject1 = SqliteWrapper.insert(mContext, mContentResolver, localUri, localContentValues);
      if (localObject1 == null) {
        throw new MmsException("Failed to persist part, return null.");
      }
    }
    else
    {
      throw new MmsException("MIME type of the part must be set.");
    }
    persistData(paramPduPart, (Uri)localObject1, (String)localObject2);
    paramPduPart.setDataUri((Uri)localObject1);
    return (Uri)localObject1;
  }
  
  public ArrayList<Uri> recoverPersist(ArrayList<GenericPdu> paramArrayList, ArrayList<Uri> paramArrayList1)
    throws MmsException
  {
    int k = paramArrayList.size();
    if (k != paramArrayList1.size()) {
      throw new MmsException("pdus or uris number error");
    }
    ArrayList localArrayList1 = Lists.newArrayList();
    ArrayList localArrayList2 = Lists.newArrayList();
    ArrayList localArrayList3 = Lists.newArrayList();
    ArrayList localArrayList4 = Lists.newArrayList();
    ArrayList localArrayList5 = Lists.newArrayList();
    long[] arrayOfLong1 = new long[k];
    long[] arrayOfLong2 = new long[k];
    int i = 0;
    int j;
    long l1;
    int m;
    int n;
    EncodedStringValue[] arrayOfEncodedStringValue;
    while (i < k)
    {
      GenericPdu localGenericPdu = (GenericPdu)paramArrayList.get(i);
      Uri localUri = (Uri)paramArrayList1.get(i);
      if (localUri == null) {
        throw new MmsException("Uri may not be null.");
      }
      if (MESSAGE_BOX_MAP.get(localUri) == null) {
        throw new MmsException("Bad destination, must be one of content://mms/inbox, content://mms/sent, content://mms/drafts, content://mms/outbox, content://mms/temp.");
      }
      ContentValues localContentValues;
      Object localObject4;
      synchronized (PDU_CACHE_INSTANCE)
      {
        boolean bool = PDU_CACHE_INSTANCE.isUpdating(localUri);
        if (bool) {}
        try
        {
          PDU_CACHE_INSTANCE.wait();
          PDU_CACHE_INSTANCE.purge(localUri);
          PduHeaders localPduHeaders = localGenericPdu.getPduHeaders();
          localContentValues = new ContentValues();
          localContentValues.put("block_type", Integer.valueOf(0));
          ??? = ENCODED_STRING_COLUMN_NAME_MAP.entrySet().iterator();
          while (((Iterator)???).hasNext())
          {
            localObject2 = (Map.Entry)((Iterator)???).next();
            j = ((Integer)((Map.Entry)localObject2).getKey()).intValue();
            localObject3 = localPduHeaders.getEncodedStringValue(j);
            if (localObject3 != null)
            {
              localObject4 = (String)CHARSET_COLUMN_NAME_MAP.get(Integer.valueOf(j));
              localContentValues.put((String)((Map.Entry)localObject2).getValue(), toIsoString(((EncodedStringValue)localObject3).getTextString()));
              localContentValues.put((String)localObject4, Integer.valueOf(((EncodedStringValue)localObject3).getCharacterSet()));
            }
          }
        }
        catch (InterruptedException localInterruptedException)
        {
          for (;;)
          {
            Log.e("MiuiPduPersister", "persist1: ", localInterruptedException);
          }
        }
      }
      ??? = TEXT_STRING_COLUMN_NAME_MAP.entrySet().iterator();
      while (((Iterator)???).hasNext())
      {
        localObject2 = (Map.Entry)((Iterator)???).next();
        localObject3 = localInterruptedException.getTextString(((Integer)((Map.Entry)localObject2).getKey()).intValue());
        if (localObject3 != null) {
          localContentValues.put((String)((Map.Entry)localObject2).getValue(), toIsoString((byte[])localObject3));
        }
      }
      ??? = OCTET_COLUMN_NAME_MAP.entrySet().iterator();
      while (((Iterator)???).hasNext())
      {
        localObject2 = (Map.Entry)((Iterator)???).next();
        j = localInterruptedException.getOctet(((Integer)((Map.Entry)localObject2).getKey()).intValue());
        if (j != 0) {
          localContentValues.put((String)((Map.Entry)localObject2).getValue(), Integer.valueOf(j));
        }
      }
      ??? = LONG_COLUMN_NAME_MAP.entrySet().iterator();
      while (((Iterator)???).hasNext())
      {
        localObject2 = (Map.Entry)((Iterator)???).next();
        l1 = localInterruptedException.getLongInteger(((Integer)((Map.Entry)localObject2).getKey()).intValue());
        if (l1 != -1L) {
          localContentValues.put((String)((Map.Entry)localObject2).getValue(), Long.valueOf(l1));
        }
      }
      localContentValues.put("date_full", Long.valueOf(System.currentTimeMillis()));
      Object localObject2 = new HashMap(ADDRESS_FIELDS.length);
      Object localObject3 = ADDRESS_FIELDS;
      m = localObject3.length;
      j = 0;
      if (j < m)
      {
        n = localObject3[j];
        ??? = null;
        if (n == 137)
        {
          localObject4 = localInterruptedException.getEncodedStringValue(n);
          if (localObject4 != null)
          {
            ??? = new EncodedStringValue[1];
            ???[0] = localObject4;
          }
        }
        for (;;)
        {
          ((HashMap)localObject2).put(Integer.valueOf(n), ???);
          j += 1;
          break;
          ??? = localInterruptedException.getEncodedStringValues(n);
        }
      }
      localArrayList3.add(localObject2);
      localObject3 = new HashSet();
      m = localGenericPdu.getMessageType();
      if ((m == 130) || (m == 132) || (m == 128))
      {
        arrayOfEncodedStringValue = null;
        ??? = arrayOfEncodedStringValue;
        switch (m)
        {
        default: 
          ??? = arrayOfEncodedStringValue;
        }
        while (??? != null)
        {
          n = ???.length;
          j = 0;
          while (j < n)
          {
            arrayOfEncodedStringValue = ???[j];
            if (arrayOfEncodedStringValue != null) {
              ((HashSet)localObject3).add(arrayOfEncodedStringValue.getString());
            }
            j += 1;
          }
          ??? = (EncodedStringValue[])((HashMap)localObject2).get(Integer.valueOf(137));
          continue;
          ??? = (EncodedStringValue[])((HashMap)localObject2).get(Integer.valueOf(151));
        }
        if (!((HashSet)localObject3).isEmpty())
        {
          localContentValues.put("thread_id", Long.valueOf(Telephony.Threads.getOrCreateThreadId(mContext, (Set)localObject3)));
          if ((((HashSet)localObject3).size() == 1) && ((m == 130) || (m == 132)))
          {
            if (!ExtraTelephony.isServiceNumber((String)((HashSet)localObject3).iterator().next())) {
              break label1073;
            }
            localContentValues.put("advanced_seen", Integer.valueOf(1));
          }
        }
      }
      for (;;)
      {
        l1 = System.currentTimeMillis();
        arrayOfLong2[i] = l1;
        if (!(localGenericPdu instanceof MultimediaMessagePdu)) {
          break;
        }
        ??? = ((MultimediaMessagePdu)localGenericPdu).getBody();
        if (??? == null) {
          break;
        }
        m = ((PduBody)???).getPartsNum();
        j = 0;
        while (j < m)
        {
          localArrayList4.add(((PduBody)???).getPart(j));
          localArrayList5.add(String.valueOf(l1));
          j += 1;
        }
        label1073:
        localContentValues.put("advanced_seen", Integer.valueOf(0));
      }
      localArrayList1.add(ContentProviderOperation.newInsert(localUri).withValues(localContentValues).build());
      i += 1;
    }
    try
    {
      recoverPersistPart(localArrayList4, localArrayList5);
      paramArrayList = mContentResolver.applyBatch("mms", localArrayList1);
      if ((paramArrayList != null) && (paramArrayList.length == k)) {
        i = 0;
      }
      while (i < k)
      {
        arrayOfLong1[i] = ContentUris.parseId(uri);
        i += 1;
        continue;
        throw new MmsException("insert pdu error");
      }
    }
    catch (OperationApplicationException paramArrayList)
    {
      Log.e("MiuiPduPersister", "Error while applying batch", paramArrayList);
      throw new MmsException("Error while applying batch.");
    }
    catch (RemoteException paramArrayList)
    {
      Log.e("MiuiPduPersister", "Error while applying batch", paramArrayList);
      throw new MmsException("Error while applying batch.");
    }
    localArrayList1.clear();
    i = 0;
    while (i < k)
    {
      l1 = arrayOfLong1[i];
      long l2 = arrayOfLong2[i];
      paramArrayList = (Uri)paramArrayList1.get(i);
      ??? = new ContentValues(1);
      ((ContentValues)???).put("mid", Long.valueOf(l1));
      localArrayList1.add(ContentProviderOperation.newUpdate(Uri.parse("content://mms/" + l2 + "/part")).withValues((ContentValues)???).build());
      localArrayList2.add(Uri.parse(paramArrayList + "/" + l1));
      paramArrayList = (HashMap)localArrayList3.get(i);
      ??? = ADDRESS_FIELDS;
      m = ???.length;
      j = 0;
      while (j < m)
      {
        n = ???[j];
        arrayOfEncodedStringValue = (EncodedStringValue[])paramArrayList.get(Integer.valueOf(n));
        if (arrayOfEncodedStringValue != null) {
          persistAddress(localArrayList1, l1, n, arrayOfEncodedStringValue);
        }
        j += 1;
      }
      i += 1;
    }
    try
    {
      mContentResolver.applyBatch("mms", localArrayList1);
      return localArrayList2;
    }
    catch (OperationApplicationException paramArrayList)
    {
      Log.e("MiuiPduPersister", "Error while applying batch", paramArrayList);
      throw new MmsException("Error while applying batch.");
    }
    catch (RemoteException paramArrayList)
    {
      Log.e("MiuiPduPersister", "Error while applying batch", paramArrayList);
      throw new MmsException("Error while applying batch.");
    }
  }
  
  public void recoverPersistPart(ArrayList<PduPart> paramArrayList, ArrayList paramArrayList1)
    throws MmsException
  {
    int j = paramArrayList.size();
    if (j != paramArrayList1.size()) {
      throw new MmsException("recoverPersistPart parts or msgIds error");
    }
    ArrayList localArrayList1 = Lists.newArrayList();
    ArrayList localArrayList2 = Lists.newArrayList();
    ArrayList localArrayList3 = Lists.newArrayList();
    int i = 0;
    Object localObject1;
    while (i < j)
    {
      long l = Long.parseLong(paramArrayList1.get(i).toString());
      PduPart localPduPart = (PduPart)paramArrayList.get(i);
      Uri localUri = Uri.parse("content://mms/" + l + "/part");
      ContentValues localContentValues = new ContentValues(8);
      int k = localPduPart.getCharset();
      if (k != 0) {
        localContentValues.put("chset", Integer.valueOf(k));
      }
      if (localPduPart.getContentType() != null)
      {
        localObject1 = toIsoString(localPduPart.getContentType());
        Object localObject2 = localObject1;
        if ("text/x-vCard".equalsIgnoreCase((String)localObject1))
        {
          Log.e("MiuiPduPersister", (String)localObject1 + " -> " + "text/x-vCard");
          localObject2 = "text/x-vCard";
        }
        localObject1 = localObject2;
        if (localPduPart.getContentLocation() != null) {
          if (!"text/plain".equalsIgnoreCase((String)localObject2))
          {
            localObject1 = localObject2;
            if (!"application/oct-stream".equalsIgnoreCase((String)localObject2)) {}
          }
          else
          {
            String str = new String(localPduPart.getContentLocation());
            Object localObject3 = localObject2;
            if (str != null) {
              if (!str.endsWith(".vcf"))
              {
                localObject3 = localObject2;
                if (!str.endsWith(".VCF")) {}
              }
              else
              {
                Log.e("MiuiPduPersister", (String)localObject2 + " -> " + "text/x-vCard");
                localObject3 = "text/x-vCard";
              }
            }
            localObject1 = localObject3;
            if (str != null) {
              if (!str.endsWith(".ogg"))
              {
                localObject1 = localObject3;
                if (!str.endsWith(".OGG")) {}
              }
              else
              {
                Log.e("MiuiPduPersister", (String)localObject3 + " -> " + "application/ogg");
                localObject1 = "application/ogg";
              }
            }
          }
        }
        if ((localPduPart.getContentLocation() != null) && ("text/plain".equalsIgnoreCase((String)localObject1)))
        {
          localObject2 = new String(localPduPart.getContentLocation());
          if ((localObject2 != null) && ((((String)localObject2).endsWith(".txt")) || (((String)localObject2).endsWith(".TXT"))) && (localPduPart.getCharset() == 0))
          {
            Log.i("MiuiPduPersister", "Set default text charset to UTF_8");
            localContentValues.put("chset", Integer.valueOf(106));
          }
        }
        localObject2 = localObject1;
        if ("image/jpg".equals(localObject1)) {
          localObject2 = "image/jpeg";
        }
        localArrayList1.add(localObject2);
        localContentValues.put("ct", (String)localObject2);
        if ("application/smil".equals(localObject2)) {
          localContentValues.put("seq", Integer.valueOf(-1));
        }
        if (localPduPart.getFilename() != null) {
          localContentValues.put("fn", new String(localPduPart.getFilename()));
        }
        if (localPduPart.getName() != null) {
          localContentValues.put("name", new String(localPduPart.getName()));
        }
        if (localPduPart.getContentDisposition() != null) {
          localContentValues.put("cd", (String)toIsoString(localPduPart.getContentDisposition()));
        }
        if (localPduPart.getContentId() != null) {
          localContentValues.put("cid", (String)toIsoString(localPduPart.getContentId()));
        }
        if (localPduPart.getContentLocation() != null) {
          localContentValues.put("cl", (String)toIsoString(localPduPart.getContentLocation()));
        }
        localArrayList3.add(ContentProviderOperation.newInsert(localUri).withValues(localContentValues).build());
        i += 1;
      }
      else
      {
        throw new MmsException("MIME type of the part must be set.");
      }
    }
    try
    {
      paramArrayList1 = mContentResolver.applyBatch("mms", localArrayList3);
      if ((paramArrayList1 == null) || (paramArrayList1.length != j)) {
        break label819;
      }
      i = 0;
    }
    catch (OperationApplicationException paramArrayList)
    {
      for (;;)
      {
        Log.e("MiuiPduPersister", "Error while applying batch", paramArrayList);
        throw new MmsException("Error while applying batch.");
        localArrayList2.add(localObject1);
        i += 1;
      }
      throw new MmsException("insert part error");
    }
    catch (RemoteException paramArrayList)
    {
      Log.e("MiuiPduPersister", "Error while applying batch", paramArrayList);
      throw new MmsException("Error while applying batch.");
    }
    if (i < j)
    {
      localObject1 = uri;
      if (localArrayList2 == null) {
        throw new MmsException("Failed to persist part, return null.");
      }
    }
    label819:
    i = 0;
    while (i < j)
    {
      paramArrayList1 = (PduPart)paramArrayList.get(i);
      localObject1 = (Uri)localArrayList2.get(i);
      persistData(paramArrayList1, (Uri)localObject1, localArrayList1.get(i).toString());
      paramArrayList1.setDataUri((Uri)localObject1);
      i += 1;
    }
  }
  
  public void release()
  {
    Uri localUri = Uri.parse("content://mms/9223372036854775807/part");
    SqliteWrapper.delete(mContext, mContentResolver, localUri, null, null);
  }
  
  public void updateHeaders(Uri paramUri, SendReq paramSendReq)
  {
    HashSet localHashSet;
    for (;;)
    {
      int i;
      int j;
      synchronized (PDU_CACHE_INSTANCE)
      {
        boolean bool = PDU_CACHE_INSTANCE.isUpdating(paramUri);
        if (bool) {}
        try
        {
          PDU_CACHE_INSTANCE.wait();
          PDU_CACHE_INSTANCE.purge(paramUri);
          ??? = new ContentValues(10);
          Object localObject2 = paramSendReq.getContentType();
          if (localObject2 != null) {
            ((ContentValues)???).put("ct_t", toIsoString((byte[])localObject2));
          }
          long l = paramSendReq.getDate();
          if (l != -1L) {
            ((ContentValues)???).put("date", Long.valueOf(l));
          }
          i = paramSendReq.getDeliveryReport();
          if (i != 0) {
            ((ContentValues)???).put("d_rpt", Integer.valueOf(i));
          }
          l = paramSendReq.getExpiry();
          if (l != -1L) {
            ((ContentValues)???).put("exp", Long.valueOf(l));
          }
          localObject2 = paramSendReq.getMessageClass();
          if (localObject2 != null) {
            ((ContentValues)???).put("m_cls", toIsoString((byte[])localObject2));
          }
          i = paramSendReq.getPriority();
          if (i != 0) {
            ((ContentValues)???).put("pri", Integer.valueOf(i));
          }
          i = paramSendReq.getReadReport();
          if (i != 0) {
            ((ContentValues)???).put("rr", Integer.valueOf(i));
          }
          localObject2 = paramSendReq.getTransactionId();
          if (localObject2 != null) {
            ((ContentValues)???).put("tr_id", toIsoString((byte[])localObject2));
          }
          localObject2 = paramSendReq.getSubject();
          if (localObject2 != null)
          {
            ((ContentValues)???).put("sub", toIsoString(((EncodedStringValue)localObject2).getTextString()));
            ((ContentValues)???).put("sub_cs", Integer.valueOf(((EncodedStringValue)localObject2).getCharacterSet()));
            l = paramSendReq.getMessageSize();
            if (l > 0L) {
              ((ContentValues)???).put("m_size", Long.valueOf(l));
            }
            localObject2 = paramSendReq.getPduHeaders();
            localHashSet = new HashSet();
            int[] arrayOfInt = ADDRESS_FIELDS;
            int k = arrayOfInt.length;
            i = 0;
            if (i >= k) {
              break;
            }
            j = arrayOfInt[i];
            paramSendReq = null;
            if (j != 137) {
              break label475;
            }
            EncodedStringValue localEncodedStringValue = ((PduHeaders)localObject2).getEncodedStringValue(j);
            if (localEncodedStringValue != null)
            {
              paramSendReq = new EncodedStringValue[1];
              paramSendReq[0] = localEncodedStringValue;
            }
            if (paramSendReq == null) {
              break label486;
            }
            updateAddress(ContentUris.parseId(paramUri), j, paramSendReq);
            if (j != 151) {
              break label486;
            }
            int m = paramSendReq.length;
            j = 0;
            if (j >= m) {
              break label486;
            }
            localEncodedStringValue = paramSendReq[j];
            if (localEncodedStringValue != null) {
              localHashSet.add(localEncodedStringValue.getString());
            }
            j += 1;
            continue;
          }
        }
        catch (InterruptedException localInterruptedException)
        {
          Log.e("MiuiPduPersister", "updateHeaders: ", localInterruptedException);
          continue;
        }
      }
      ((ContentValues)???).put("sub", "");
      continue;
      label475:
      paramSendReq = localInterruptedException.getEncodedStringValues(j);
      continue;
      label486:
      i += 1;
    }
    if (!localHashSet.isEmpty()) {
      ((ContentValues)???).put("thread_id", Long.valueOf(Telephony.Threads.getOrCreateThreadId(mContext, localHashSet)));
    }
    SqliteWrapper.update(mContext, mContentResolver, paramUri, (ContentValues)???, null, null);
  }
  
  public void updateParts(Uri paramUri, PduBody arg2)
    throws MmsException
  {
    try
    {
      for (;;)
      {
        StringBuilder localStringBuilder;
        Object localObject4;
        Object localObject5;
        synchronized (PDU_CACHE_INSTANCE)
        {
          boolean bool = PDU_CACHE_INSTANCE.isUpdating(paramUri);
          if (bool) {}
          try
          {
            PDU_CACHE_INSTANCE.wait();
            Object localObject3 = (PduCacheEntry)PDU_CACHE_INSTANCE.get(paramUri);
            if (localObject3 != null) {
              ((MultimediaMessagePdu)((PduCacheEntry)localObject3).getPdu()).setBody(???);
            }
            PDU_CACHE_INSTANCE.setUpdating(paramUri, true);
            localObject3 = new ArrayList();
            ??? = new HashMap();
            int j = ???.getPartsNum();
            localStringBuilder = new StringBuilder().append('(');
            int i = 0;
            if (i < j)
            {
              localObject4 = ???.getPart(i);
              localObject5 = ((PduPart)localObject4).getDataUri();
              if ((localObject5 == null) || (!((Uri)localObject5).getAuthority().startsWith("mms")))
              {
                ((ArrayList)localObject3).add(localObject4);
                i += 1;
                continue;
              }
            }
          }
          catch (InterruptedException localInterruptedException)
          {
            Log.e("MiuiPduPersister", "updateParts: ", localInterruptedException);
            continue;
          }
        }
        synchronized (PDU_CACHE_INSTANCE)
        {
          PDU_CACHE_INSTANCE.setUpdating(paramUri, false);
          PDU_CACHE_INSTANCE.notifyAll();
          throw ((Throwable)localObject2);
          ((HashMap)localObject2).put(localObject5, localObject4);
          if (localStringBuilder.length() > 1) {
            localStringBuilder.append(" AND ");
          }
          localStringBuilder.append("_id");
          localStringBuilder.append("!=");
          DatabaseUtils.appendEscapedSQLString(localStringBuilder, ((Uri)localObject5).getLastPathSegment());
          continue;
          localStringBuilder.append(')');
          long l = ContentUris.parseId(paramUri);
          localObject4 = mContext;
          localObject5 = mContentResolver;
          Uri localUri = Uri.parse(Telephony.Mms.CONTENT_URI + "/" + l + "/part");
          if (localStringBuilder.length() > 2)
          {
            ??? = localStringBuilder.toString();
            SqliteWrapper.delete((Context)localObject4, (ContentResolver)localObject5, localUri, ???, null);
            ??? = localInterruptedException.iterator();
            while (???.hasNext()) {
              persistPart((PduPart)???.next(), l);
            }
            ??? = ((HashMap)localObject2).entrySet().iterator();
            if (???.hasNext())
            {
              Map.Entry localEntry = (Map.Entry)???.next();
              updatePart((Uri)localEntry.getKey(), (PduPart)localEntry.getValue());
            }
          }
        }
      }
    }
    finally {}
  }
}

/* Location:
 * Qualified Name:     com.google.android.mms.pdu.MiuiPduPersister
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */