package com.google.android.mms.pdu;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteException;
import android.drm.DrmManagerClient;
import android.net.Uri;
import android.net.Uri.Builder;
import android.provider.Telephony.Mms;
import android.provider.Telephony.Mms.Draft;
import android.provider.Telephony.Mms.Inbox;
import android.provider.Telephony.Mms.Outbox;
import android.provider.Telephony.Mms.Sent;
import android.provider.Telephony.MmsSms.PendingMessages;
import android.provider.Telephony.Threads;
import android.telephony.PhoneNumberUtils;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
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

public class PduPersister
{
  private static final int[] ADDRESS_FIELDS;
  private static final HashMap<Integer, Integer> CHARSET_COLUMN_INDEX_MAP;
  private static final HashMap<Integer, String> CHARSET_COLUMN_NAME_MAP;
  private static final boolean DEBUG = false;
  private static final int DEFAULT_SUBSCRIPTION = 0;
  private static final long DUMMY_THREAD_ID = Long.MAX_VALUE;
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
  private static final String TAG = "PduPersister";
  public static final String TEMPORARY_DRM_OBJECT_URI = "content://mms/9223372036854775807/part";
  private static final HashMap<Integer, Integer> TEXT_STRING_COLUMN_INDEX_MAP;
  private static final HashMap<Integer, String> TEXT_STRING_COLUMN_NAME_MAP;
  private static PduPersister sPersister;
  private final ContentResolver mContentResolver;
  private final Context mContext;
  private final DrmManagerClient mDrmManagerClient;
  private final TelephonyManager mTelephonyManager;
  
  static
  {
    if (!PduPersister.class.desiredAssertionStatus()) {}
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
      LONG_COLUMN_NAME_MAP.put(Integer.valueOf(133), "date");
      LONG_COLUMN_NAME_MAP.put(Integer.valueOf(135), "d_tm");
      LONG_COLUMN_NAME_MAP.put(Integer.valueOf(136), "exp");
      LONG_COLUMN_NAME_MAP.put(Integer.valueOf(142), "m_size");
      PDU_CACHE_INSTANCE = PduCache.getInstance();
      return;
    }
  }
  
  private PduPersister(Context paramContext)
  {
    mContext = paramContext;
    mContentResolver = paramContext.getContentResolver();
    mDrmManagerClient = new DrmManagerClient(paramContext);
    mTelephonyManager = ((TelephonyManager)paramContext.getSystemService("phone"));
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
      Log.e("PduPersister", "ISO_8859_1 must be supported!", paramString);
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
  
  private static String getPartContentType(PduPart paramPduPart)
  {
    if (paramPduPart.getContentType() == null) {
      return null;
    }
    return toIsoString(paramPduPart.getContentType());
  }
  
  public static PduPersister getPduPersister(Context paramContext)
  {
    if ((sPersister == null) || (!paramContext.equals(sPersistermContext))) {
      sPersister = new PduPersister(paramContext);
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
          Log.e("PduPersister", "Unknown address type: " + i);
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
          break label617;
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
        int j = ((InputStream)localObject6).read((byte[])localObject7);
        while (j >= 0)
        {
          localObject3 = localObject6;
          localObject5 = localObject6;
          localByteArrayOutputStream.write((byte[])localObject7, 0, j);
          localObject3 = localObject6;
          localObject5 = localObject6;
          j = ((InputStream)localObject6).read((byte[])localObject7);
          continue;
          try
          {
            ((InputStream)localObject5).close();
            throw ((Throwable)localObject4);
            if (localCursor != null) {
              localCursor.close();
            }
            return arrayOfPduPart;
          }
          catch (IOException localIOException2)
          {
            for (;;)
            {
              Log.e("PduPersister", "Failed to close stream", localIOException2);
            }
          }
        }
      }
      catch (IOException localIOException3)
      {
        localObject5 = localObject3;
        Log.e("PduPersister", "Failed to load part data", localIOException3);
        localObject5 = localObject3;
        localCursor.close();
        localObject5 = localObject3;
        throw new MmsException(localIOException3);
      }
      finally
      {
        if (localObject5 == null) {}
      }
      label617:
      if (localIOException3 != null)
      {
        try
        {
          localIOException3.close();
        }
        catch (IOException localIOException1)
        {
          Log.e("PduPersister", "Failed to close stream", localIOException1);
        }
        continue;
        label677:
        arrayOfPduPart[i] = localPduPart;
        i += 1;
      }
    }
  }
  
  private void loadRecipients(int paramInt, HashSet<String> paramHashSet, HashMap<Integer, EncodedStringValue[]> paramHashMap, boolean paramBoolean)
  {
    EncodedStringValue[] arrayOfEncodedStringValue = (EncodedStringValue[])paramHashMap.get(Integer.valueOf(paramInt));
    if (arrayOfEncodedStringValue == null) {}
    while ((paramBoolean) && (arrayOfEncodedStringValue.length == 1)) {
      return;
    }
    if (paramBoolean) {}
    for (paramHashMap = mTelephonyManager.getLine1Number();; paramHashMap = null)
    {
      int i = arrayOfEncodedStringValue.length;
      paramInt = 0;
      while (paramInt < i)
      {
        Object localObject = arrayOfEncodedStringValue[paramInt];
        if (localObject != null)
        {
          localObject = ((EncodedStringValue)localObject).getString();
          if (((paramHashMap == null) || (!PhoneNumberUtils.compare((String)localObject, paramHashMap))) && (!paramHashSet.contains(localObject))) {
            paramHashSet.add(localObject);
          }
        }
        paramInt += 1;
      }
      break;
    }
  }
  
  private void persistAddress(long paramLong, int paramInt, EncodedStringValue[] paramArrayOfEncodedStringValue)
  {
    ContentValues localContentValues = new ContentValues(3);
    int j = paramArrayOfEncodedStringValue.length;
    int i = 0;
    while (i < j)
    {
      Object localObject = paramArrayOfEncodedStringValue[i];
      localContentValues.clear();
      localContentValues.put("address", toIsoString(((EncodedStringValue)localObject).getTextString()));
      localContentValues.put("charset", Integer.valueOf(((EncodedStringValue)localObject).getCharacterSet()));
      localContentValues.put("type", Integer.valueOf(paramInt));
      localObject = Uri.parse("content://mms/" + paramLong + "/addr");
      SqliteWrapper.insert(mContext, mContentResolver, (Uri)localObject, localContentValues);
      i += 1;
    }
  }
  
  /* Error */
  private void persistData(PduPart paramPduPart, Uri paramUri, String paramString, HashMap<Uri, InputStream> paramHashMap)
    throws MmsException
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 32
    //   3: aconst_null
    //   4: astore 30
    //   6: aconst_null
    //   7: astore 22
    //   9: aconst_null
    //   10: astore 31
    //   12: aconst_null
    //   13: astore 26
    //   15: aconst_null
    //   16: astore 28
    //   18: aconst_null
    //   19: astore 25
    //   21: aconst_null
    //   22: astore 29
    //   24: aconst_null
    //   25: astore 27
    //   27: aconst_null
    //   28: astore 35
    //   30: aconst_null
    //   31: astore 34
    //   33: aconst_null
    //   34: astore 41
    //   36: aconst_null
    //   37: astore 40
    //   39: aconst_null
    //   40: astore 33
    //   42: aconst_null
    //   43: astore 37
    //   45: aconst_null
    //   46: astore 39
    //   48: aconst_null
    //   49: astore 10
    //   51: aconst_null
    //   52: astore 36
    //   54: aconst_null
    //   55: astore 14
    //   57: aconst_null
    //   58: astore 13
    //   60: aconst_null
    //   61: astore 38
    //   63: aload 33
    //   65: astore 19
    //   67: aload 27
    //   69: astore 21
    //   71: aload 31
    //   73: astore 24
    //   75: aload 38
    //   77: astore 12
    //   79: aload 35
    //   81: astore 15
    //   83: aload 26
    //   85: astore 16
    //   87: aload 32
    //   89: astore 17
    //   91: aload 37
    //   93: astore 9
    //   95: aload 34
    //   97: astore 18
    //   99: aload 28
    //   101: astore 20
    //   103: aload 30
    //   105: astore 23
    //   107: aload 36
    //   109: astore 11
    //   111: aload_1
    //   112: invokevirtual 639	com/google/android/mms/pdu/PduPart:getData	()[B
    //   115: astore 42
    //   117: aload 33
    //   119: astore 19
    //   121: aload 27
    //   123: astore 21
    //   125: aload 31
    //   127: astore 24
    //   129: aload 38
    //   131: astore 12
    //   133: aload 35
    //   135: astore 15
    //   137: aload 26
    //   139: astore 16
    //   141: aload 32
    //   143: astore 17
    //   145: aload 37
    //   147: astore 9
    //   149: aload 34
    //   151: astore 18
    //   153: aload 28
    //   155: astore 20
    //   157: aload 30
    //   159: astore 23
    //   161: aload 36
    //   163: astore 11
    //   165: ldc_w 539
    //   168: aload_3
    //   169: invokevirtual 312	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   172: ifne +119 -> 291
    //   175: aload 33
    //   177: astore 19
    //   179: aload 27
    //   181: astore 21
    //   183: aload 31
    //   185: astore 24
    //   187: aload 38
    //   189: astore 12
    //   191: aload 35
    //   193: astore 15
    //   195: aload 26
    //   197: astore 16
    //   199: aload 32
    //   201: astore 17
    //   203: aload 37
    //   205: astore 9
    //   207: aload 34
    //   209: astore 18
    //   211: aload 28
    //   213: astore 20
    //   215: aload 30
    //   217: astore 23
    //   219: aload 36
    //   221: astore 11
    //   223: ldc_w 541
    //   226: aload_3
    //   227: invokevirtual 312	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   230: ifne +61 -> 291
    //   233: aload 33
    //   235: astore 19
    //   237: aload 27
    //   239: astore 21
    //   241: aload 31
    //   243: astore 24
    //   245: aload 38
    //   247: astore 12
    //   249: aload 35
    //   251: astore 15
    //   253: aload 26
    //   255: astore 16
    //   257: aload 32
    //   259: astore 17
    //   261: aload 37
    //   263: astore 9
    //   265: aload 34
    //   267: astore 18
    //   269: aload 28
    //   271: astore 20
    //   273: aload 30
    //   275: astore 23
    //   277: aload 36
    //   279: astore 11
    //   281: ldc_w 543
    //   284: aload_3
    //   285: invokevirtual 312	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   288: ifeq +427 -> 715
    //   291: aload 33
    //   293: astore 19
    //   295: aload 27
    //   297: astore 21
    //   299: aload 31
    //   301: astore 24
    //   303: aload 38
    //   305: astore 12
    //   307: aload 35
    //   309: astore 15
    //   311: aload 26
    //   313: astore 16
    //   315: aload 32
    //   317: astore 17
    //   319: aload 37
    //   321: astore 9
    //   323: aload 34
    //   325: astore 18
    //   327: aload 28
    //   329: astore 20
    //   331: aload 30
    //   333: astore 23
    //   335: aload 36
    //   337: astore 11
    //   339: new 612	android/content/ContentValues
    //   342: dup
    //   343: invokespecial 640	android/content/ContentValues:<init>	()V
    //   346: astore_1
    //   347: aload 33
    //   349: astore 19
    //   351: aload 27
    //   353: astore 21
    //   355: aload 31
    //   357: astore 24
    //   359: aload 38
    //   361: astore 12
    //   363: aload 35
    //   365: astore 15
    //   367: aload 26
    //   369: astore 16
    //   371: aload 32
    //   373: astore 17
    //   375: aload 37
    //   377: astore 9
    //   379: aload 34
    //   381: astore 18
    //   383: aload 28
    //   385: astore 20
    //   387: aload 30
    //   389: astore 23
    //   391: aload 36
    //   393: astore 11
    //   395: aload_1
    //   396: ldc -51
    //   398: new 461	com/google/android/mms/pdu/EncodedStringValue
    //   401: dup
    //   402: aload 42
    //   404: invokespecial 642	com/google/android/mms/pdu/EncodedStringValue:<init>	([B)V
    //   407: invokevirtual 592	com/google/android/mms/pdu/EncodedStringValue:getString	()Ljava/lang/String;
    //   410: invokevirtual 620	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
    //   413: aload 33
    //   415: astore 19
    //   417: aload 27
    //   419: astore 21
    //   421: aload 31
    //   423: astore 24
    //   425: aload 38
    //   427: astore 12
    //   429: aload 35
    //   431: astore 15
    //   433: aload 26
    //   435: astore 16
    //   437: aload 32
    //   439: astore 17
    //   441: aload 37
    //   443: astore 9
    //   445: aload 34
    //   447: astore 18
    //   449: aload 28
    //   451: astore 20
    //   453: aload 30
    //   455: astore 23
    //   457: aload 36
    //   459: astore 11
    //   461: aload 40
    //   463: astore 4
    //   465: aload 29
    //   467: astore 10
    //   469: aload 13
    //   471: astore 25
    //   473: aload_0
    //   474: getfield 280	com/google/android/mms/pdu/PduPersister:mContentResolver	Landroid/content/ContentResolver;
    //   477: aload_2
    //   478: aload_1
    //   479: aconst_null
    //   480: aconst_null
    //   481: invokevirtual 646	android/content/ContentResolver:update	(Landroid/net/Uri;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I
    //   484: iconst_1
    //   485: if_icmpeq +1632 -> 2117
    //   488: aload 33
    //   490: astore 19
    //   492: aload 27
    //   494: astore 21
    //   496: aload 31
    //   498: astore 24
    //   500: aload 38
    //   502: astore 12
    //   504: aload 35
    //   506: astore 15
    //   508: aload 26
    //   510: astore 16
    //   512: aload 32
    //   514: astore 17
    //   516: aload 37
    //   518: astore 9
    //   520: aload 34
    //   522: astore 18
    //   524: aload 28
    //   526: astore 20
    //   528: aload 30
    //   530: astore 23
    //   532: aload 36
    //   534: astore 11
    //   536: new 477	com/google/android/mms/MmsException
    //   539: dup
    //   540: new 414	java/lang/StringBuilder
    //   543: dup
    //   544: invokespecial 415	java/lang/StringBuilder:<init>	()V
    //   547: ldc_w 648
    //   550: invokevirtual 421	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   553: aload_2
    //   554: invokevirtual 322	android/net/Uri:toString	()Ljava/lang/String;
    //   557: invokevirtual 421	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   560: invokevirtual 427	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   563: invokespecial 560	com/google/android/mms/MmsException:<init>	(Ljava/lang/String;)V
    //   566: athrow
    //   567: astore_1
    //   568: aload 19
    //   570: astore 15
    //   572: aload 21
    //   574: astore 16
    //   576: aload 24
    //   578: astore 17
    //   580: aload 12
    //   582: astore 9
    //   584: ldc 104
    //   586: ldc_w 650
    //   589: aload_1
    //   590: invokestatic 386	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   593: pop
    //   594: aload 19
    //   596: astore 15
    //   598: aload 21
    //   600: astore 16
    //   602: aload 24
    //   604: astore 17
    //   606: aload 12
    //   608: astore 9
    //   610: new 477	com/google/android/mms/MmsException
    //   613: dup
    //   614: aload_1
    //   615: invokespecial 575	com/google/android/mms/MmsException:<init>	(Ljava/lang/Throwable;)V
    //   618: athrow
    //   619: astore_1
    //   620: aload 17
    //   622: ifnull +8 -> 630
    //   625: aload 17
    //   627: invokevirtual 653	java/io/OutputStream:close	()V
    //   630: aload 16
    //   632: ifnull +8 -> 640
    //   635: aload 16
    //   637: invokevirtual 576	java/io/InputStream:close	()V
    //   640: aload 15
    //   642: ifnull +71 -> 713
    //   645: aload 15
    //   647: aload 9
    //   649: invokevirtual 657	com/google/android/mms/util/DrmConvertSession:close	(Ljava/lang/String;)I
    //   652: pop
    //   653: new 659	java/io/File
    //   656: dup
    //   657: aload 9
    //   659: invokespecial 660	java/io/File:<init>	(Ljava/lang/String;)V
    //   662: astore_2
    //   663: new 612	android/content/ContentValues
    //   666: dup
    //   667: iconst_0
    //   668: invokespecial 614	android/content/ContentValues:<init>	(I)V
    //   671: astore_3
    //   672: aload_0
    //   673: getfield 272	com/google/android/mms/pdu/PduPersister:mContext	Landroid/content/Context;
    //   676: aload_0
    //   677: getfield 280	com/google/android/mms/pdu/PduPersister:mContentResolver	Landroid/content/ContentResolver;
    //   680: new 414	java/lang/StringBuilder
    //   683: dup
    //   684: invokespecial 415	java/lang/StringBuilder:<init>	()V
    //   687: ldc_w 662
    //   690: invokevirtual 421	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   693: aload_2
    //   694: invokevirtual 665	java/io/File:getName	()Ljava/lang/String;
    //   697: invokevirtual 421	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   700: invokevirtual 427	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   703: invokestatic 431	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   706: aload_3
    //   707: aconst_null
    //   708: aconst_null
    //   709: invokestatic 668	com/google/android/mms/util/SqliteWrapper:update	(Landroid/content/Context;Landroid/content/ContentResolver;Landroid/net/Uri;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I
    //   712: pop
    //   713: aload_1
    //   714: athrow
    //   715: aload 33
    //   717: astore 19
    //   719: aload 27
    //   721: astore 21
    //   723: aload 31
    //   725: astore 24
    //   727: aload 38
    //   729: astore 12
    //   731: aload 35
    //   733: astore 15
    //   735: aload 26
    //   737: astore 16
    //   739: aload 32
    //   741: astore 17
    //   743: aload 37
    //   745: astore 9
    //   747: aload 34
    //   749: astore 18
    //   751: aload 28
    //   753: astore 20
    //   755: aload 30
    //   757: astore 23
    //   759: aload 36
    //   761: astore 11
    //   763: aload_3
    //   764: invokestatic 673	com/google/android/mms/util/DownloadDrmHelper:isDrmConvertNeeded	(Ljava/lang/String;)Z
    //   767: istore 6
    //   769: aload 41
    //   771: astore 13
    //   773: iload 6
    //   775: ifeq +480 -> 1255
    //   778: aload_2
    //   779: ifnull +271 -> 1050
    //   782: aload 33
    //   784: astore 19
    //   786: aload 27
    //   788: astore 21
    //   790: aload 31
    //   792: astore 24
    //   794: aload 38
    //   796: astore 12
    //   798: aload 35
    //   800: astore 15
    //   802: aload 26
    //   804: astore 16
    //   806: aload 32
    //   808: astore 17
    //   810: aload 37
    //   812: astore 9
    //   814: aload 39
    //   816: astore 10
    //   818: aload 34
    //   820: astore 18
    //   822: aload 28
    //   824: astore 20
    //   826: aload 30
    //   828: astore 23
    //   830: aload 36
    //   832: astore 11
    //   834: aload_0
    //   835: getfield 272	com/google/android/mms/pdu/PduPersister:mContext	Landroid/content/Context;
    //   838: aload_2
    //   839: invokestatic 675	com/google/android/mms/pdu/PduPersister:convertUriToPath	(Landroid/content/Context;Landroid/net/Uri;)Ljava/lang/String;
    //   842: astore 13
    //   844: aload 33
    //   846: astore 19
    //   848: aload 27
    //   850: astore 21
    //   852: aload 31
    //   854: astore 24
    //   856: aload 13
    //   858: astore 12
    //   860: aload 35
    //   862: astore 15
    //   864: aload 26
    //   866: astore 16
    //   868: aload 32
    //   870: astore 17
    //   872: aload 13
    //   874: astore 9
    //   876: aload 13
    //   878: astore 10
    //   880: aload 34
    //   882: astore 18
    //   884: aload 28
    //   886: astore 20
    //   888: aload 30
    //   890: astore 23
    //   892: aload 13
    //   894: astore 11
    //   896: new 659	java/io/File
    //   899: dup
    //   900: aload 13
    //   902: invokespecial 660	java/io/File:<init>	(Ljava/lang/String;)V
    //   905: invokevirtual 679	java/io/File:length	()J
    //   908: lstore 7
    //   910: aload 13
    //   912: astore 10
    //   914: lload 7
    //   916: lconst_0
    //   917: lcmp
    //   918: ifle +132 -> 1050
    //   921: iconst_0
    //   922: ifeq +11 -> 933
    //   925: new 681	java/lang/NullPointerException
    //   928: dup
    //   929: invokespecial 682	java/lang/NullPointerException:<init>	()V
    //   932: athrow
    //   933: iconst_0
    //   934: ifeq +11 -> 945
    //   937: new 681	java/lang/NullPointerException
    //   940: dup
    //   941: invokespecial 682	java/lang/NullPointerException:<init>	()V
    //   944: athrow
    //   945: iconst_0
    //   946: ifeq +22 -> 968
    //   949: new 681	java/lang/NullPointerException
    //   952: dup
    //   953: invokespecial 682	java/lang/NullPointerException:<init>	()V
    //   956: athrow
    //   957: aload_1
    //   958: aload_2
    //   959: aload_3
    //   960: aload 4
    //   962: aconst_null
    //   963: aconst_null
    //   964: invokestatic 668	com/google/android/mms/util/SqliteWrapper:update	(Landroid/content/Context;Landroid/content/ContentResolver;Landroid/net/Uri;Landroid/content/ContentValues;Ljava/lang/String;[Ljava/lang/String;)I
    //   967: pop
    //   968: return
    //   969: astore 13
    //   971: aload 33
    //   973: astore 19
    //   975: aload 27
    //   977: astore 21
    //   979: aload 31
    //   981: astore 24
    //   983: aload 10
    //   985: astore 12
    //   987: aload 35
    //   989: astore 15
    //   991: aload 26
    //   993: astore 16
    //   995: aload 32
    //   997: astore 17
    //   999: aload 10
    //   1001: astore 9
    //   1003: aload 34
    //   1005: astore 18
    //   1007: aload 28
    //   1009: astore 20
    //   1011: aload 30
    //   1013: astore 23
    //   1015: aload 10
    //   1017: astore 11
    //   1019: ldc 104
    //   1021: new 414	java/lang/StringBuilder
    //   1024: dup
    //   1025: invokespecial 415	java/lang/StringBuilder:<init>	()V
    //   1028: ldc_w 684
    //   1031: invokevirtual 421	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1034: aload_1
    //   1035: invokevirtual 688	com/google/android/mms/pdu/PduPart:getDataUri	()Landroid/net/Uri;
    //   1038: invokevirtual 691	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   1041: invokevirtual 427	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1044: aload 13
    //   1046: invokestatic 386	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   1049: pop
    //   1050: aload 33
    //   1052: astore 19
    //   1054: aload 27
    //   1056: astore 21
    //   1058: aload 31
    //   1060: astore 24
    //   1062: aload 10
    //   1064: astore 12
    //   1066: aload 35
    //   1068: astore 15
    //   1070: aload 26
    //   1072: astore 16
    //   1074: aload 32
    //   1076: astore 17
    //   1078: aload 10
    //   1080: astore 9
    //   1082: aload 34
    //   1084: astore 18
    //   1086: aload 28
    //   1088: astore 20
    //   1090: aload 30
    //   1092: astore 23
    //   1094: aload 10
    //   1096: astore 11
    //   1098: aload_0
    //   1099: getfield 272	com/google/android/mms/pdu/PduPersister:mContext	Landroid/content/Context;
    //   1102: aload_3
    //   1103: invokestatic 695	com/google/android/mms/util/DrmConvertSession:open	(Landroid/content/Context;Ljava/lang/String;)Lcom/google/android/mms/util/DrmConvertSession;
    //   1106: astore 22
    //   1108: aload 22
    //   1110: astore 13
    //   1112: aload 10
    //   1114: astore 14
    //   1116: aload 22
    //   1118: ifnonnull +137 -> 1255
    //   1121: aload 22
    //   1123: astore 19
    //   1125: aload 27
    //   1127: astore 21
    //   1129: aload 31
    //   1131: astore 24
    //   1133: aload 10
    //   1135: astore 12
    //   1137: aload 22
    //   1139: astore 15
    //   1141: aload 26
    //   1143: astore 16
    //   1145: aload 32
    //   1147: astore 17
    //   1149: aload 10
    //   1151: astore 9
    //   1153: aload 22
    //   1155: astore 18
    //   1157: aload 28
    //   1159: astore 20
    //   1161: aload 30
    //   1163: astore 23
    //   1165: aload 10
    //   1167: astore 11
    //   1169: new 477	com/google/android/mms/MmsException
    //   1172: dup
    //   1173: new 414	java/lang/StringBuilder
    //   1176: dup
    //   1177: invokespecial 415	java/lang/StringBuilder:<init>	()V
    //   1180: ldc_w 697
    //   1183: invokevirtual 421	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1186: aload_3
    //   1187: invokevirtual 421	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1190: ldc_w 699
    //   1193: invokevirtual 421	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1196: invokevirtual 427	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1199: invokespecial 560	com/google/android/mms/MmsException:<init>	(Ljava/lang/String;)V
    //   1202: athrow
    //   1203: astore_1
    //   1204: aload 18
    //   1206: astore 15
    //   1208: aload 20
    //   1210: astore 16
    //   1212: aload 23
    //   1214: astore 17
    //   1216: aload 11
    //   1218: astore 9
    //   1220: ldc 104
    //   1222: ldc_w 701
    //   1225: aload_1
    //   1226: invokestatic 386	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   1229: pop
    //   1230: aload 18
    //   1232: astore 15
    //   1234: aload 20
    //   1236: astore 16
    //   1238: aload 23
    //   1240: astore 17
    //   1242: aload 11
    //   1244: astore 9
    //   1246: new 477	com/google/android/mms/MmsException
    //   1249: dup
    //   1250: aload_1
    //   1251: invokespecial 575	com/google/android/mms/MmsException:<init>	(Ljava/lang/Throwable;)V
    //   1254: athrow
    //   1255: aload 13
    //   1257: astore 19
    //   1259: aload 27
    //   1261: astore 21
    //   1263: aload 31
    //   1265: astore 24
    //   1267: aload 14
    //   1269: astore 12
    //   1271: aload 13
    //   1273: astore 15
    //   1275: aload 26
    //   1277: astore 16
    //   1279: aload 32
    //   1281: astore 17
    //   1283: aload 14
    //   1285: astore 9
    //   1287: aload 13
    //   1289: astore 18
    //   1291: aload 28
    //   1293: astore 20
    //   1295: aload 30
    //   1297: astore 23
    //   1299: aload 14
    //   1301: astore 11
    //   1303: aload_0
    //   1304: getfield 280	com/google/android/mms/pdu/PduPersister:mContentResolver	Landroid/content/ContentResolver;
    //   1307: aload_2
    //   1308: invokevirtual 705	android/content/ContentResolver:openOutputStream	(Landroid/net/Uri;)Ljava/io/OutputStream;
    //   1311: astore_3
    //   1312: aload 42
    //   1314: ifnonnull +732 -> 2046
    //   1317: aload 13
    //   1319: astore 19
    //   1321: aload 27
    //   1323: astore 21
    //   1325: aload_3
    //   1326: astore 24
    //   1328: aload 14
    //   1330: astore 12
    //   1332: aload 13
    //   1334: astore 15
    //   1336: aload 26
    //   1338: astore 16
    //   1340: aload_3
    //   1341: astore 17
    //   1343: aload 14
    //   1345: astore 9
    //   1347: aload 13
    //   1349: astore 18
    //   1351: aload 28
    //   1353: astore 20
    //   1355: aload_3
    //   1356: astore 23
    //   1358: aload 14
    //   1360: astore 11
    //   1362: aload_1
    //   1363: invokevirtual 688	com/google/android/mms/pdu/PduPart:getDataUri	()Landroid/net/Uri;
    //   1366: astore 10
    //   1368: aload 10
    //   1370: ifnull +9 -> 1379
    //   1373: aload 10
    //   1375: aload_2
    //   1376: if_acmpne +150 -> 1526
    //   1379: aload 13
    //   1381: astore 19
    //   1383: aload 27
    //   1385: astore 21
    //   1387: aload_3
    //   1388: astore 24
    //   1390: aload 14
    //   1392: astore 12
    //   1394: aload 13
    //   1396: astore 15
    //   1398: aload 26
    //   1400: astore 16
    //   1402: aload_3
    //   1403: astore 17
    //   1405: aload 14
    //   1407: astore 9
    //   1409: aload 13
    //   1411: astore 18
    //   1413: aload 28
    //   1415: astore 20
    //   1417: aload_3
    //   1418: astore 23
    //   1420: aload 14
    //   1422: astore 11
    //   1424: ldc 104
    //   1426: ldc_w 707
    //   1429: invokestatic 710	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   1432: pop
    //   1433: aload_3
    //   1434: ifnull +7 -> 1441
    //   1437: aload_3
    //   1438: invokevirtual 653	java/io/OutputStream:close	()V
    //   1441: iconst_0
    //   1442: ifeq +11 -> 1453
    //   1445: new 681	java/lang/NullPointerException
    //   1448: dup
    //   1449: invokespecial 682	java/lang/NullPointerException:<init>	()V
    //   1452: athrow
    //   1453: aload 13
    //   1455: ifnull -487 -> 968
    //   1458: aload 13
    //   1460: aload 14
    //   1462: invokevirtual 657	com/google/android/mms/util/DrmConvertSession:close	(Ljava/lang/String;)I
    //   1465: pop
    //   1466: new 659	java/io/File
    //   1469: dup
    //   1470: aload 14
    //   1472: invokespecial 660	java/io/File:<init>	(Ljava/lang/String;)V
    //   1475: astore_3
    //   1476: new 612	android/content/ContentValues
    //   1479: dup
    //   1480: iconst_0
    //   1481: invokespecial 614	android/content/ContentValues:<init>	(I)V
    //   1484: astore 4
    //   1486: aload_0
    //   1487: getfield 272	com/google/android/mms/pdu/PduPersister:mContext	Landroid/content/Context;
    //   1490: astore_1
    //   1491: aload_0
    //   1492: getfield 280	com/google/android/mms/pdu/PduPersister:mContentResolver	Landroid/content/ContentResolver;
    //   1495: astore_2
    //   1496: new 414	java/lang/StringBuilder
    //   1499: dup
    //   1500: invokespecial 415	java/lang/StringBuilder:<init>	()V
    //   1503: ldc_w 662
    //   1506: invokevirtual 421	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1509: aload_3
    //   1510: invokevirtual 665	java/io/File:getName	()Ljava/lang/String;
    //   1513: invokevirtual 421	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1516: invokevirtual 427	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1519: invokestatic 431	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   1522: astore_3
    //   1523: goto -566 -> 957
    //   1526: aload 25
    //   1528: astore_2
    //   1529: aload 4
    //   1531: ifnull +117 -> 1648
    //   1534: aload 13
    //   1536: astore 19
    //   1538: aload 27
    //   1540: astore 21
    //   1542: aload_3
    //   1543: astore 24
    //   1545: aload 14
    //   1547: astore 12
    //   1549: aload 13
    //   1551: astore 15
    //   1553: aload 26
    //   1555: astore 16
    //   1557: aload_3
    //   1558: astore 17
    //   1560: aload 14
    //   1562: astore 9
    //   1564: aload 13
    //   1566: astore 18
    //   1568: aload 28
    //   1570: astore 20
    //   1572: aload_3
    //   1573: astore 23
    //   1575: aload 14
    //   1577: astore 11
    //   1579: aload 25
    //   1581: astore_2
    //   1582: aload 4
    //   1584: aload 10
    //   1586: invokevirtual 713	java/util/HashMap:containsKey	(Ljava/lang/Object;)Z
    //   1589: ifeq +59 -> 1648
    //   1592: aload 13
    //   1594: astore 19
    //   1596: aload 27
    //   1598: astore 21
    //   1600: aload_3
    //   1601: astore 24
    //   1603: aload 14
    //   1605: astore 12
    //   1607: aload 13
    //   1609: astore 15
    //   1611: aload 26
    //   1613: astore 16
    //   1615: aload_3
    //   1616: astore 17
    //   1618: aload 14
    //   1620: astore 9
    //   1622: aload 13
    //   1624: astore 18
    //   1626: aload 28
    //   1628: astore 20
    //   1630: aload_3
    //   1631: astore 23
    //   1633: aload 14
    //   1635: astore 11
    //   1637: aload 4
    //   1639: aload 10
    //   1641: invokevirtual 585	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   1644: checkcast 566	java/io/InputStream
    //   1647: astore_2
    //   1648: aload_2
    //   1649: astore_1
    //   1650: aload_2
    //   1651: ifnonnull +55 -> 1706
    //   1654: aload 13
    //   1656: astore 19
    //   1658: aload_2
    //   1659: astore 21
    //   1661: aload_3
    //   1662: astore 24
    //   1664: aload 14
    //   1666: astore 12
    //   1668: aload 13
    //   1670: astore 15
    //   1672: aload_2
    //   1673: astore 16
    //   1675: aload_3
    //   1676: astore 17
    //   1678: aload 14
    //   1680: astore 9
    //   1682: aload 13
    //   1684: astore 18
    //   1686: aload_2
    //   1687: astore 20
    //   1689: aload_3
    //   1690: astore 23
    //   1692: aload 14
    //   1694: astore 11
    //   1696: aload_0
    //   1697: getfield 280	com/google/android/mms/pdu/PduPersister:mContentResolver	Landroid/content/ContentResolver;
    //   1700: aload 10
    //   1702: invokevirtual 564	android/content/ContentResolver:openInputStream	(Landroid/net/Uri;)Ljava/io/InputStream;
    //   1705: astore_1
    //   1706: aload 13
    //   1708: astore 19
    //   1710: aload_1
    //   1711: astore 21
    //   1713: aload_3
    //   1714: astore 24
    //   1716: aload 14
    //   1718: astore 12
    //   1720: aload 13
    //   1722: astore 15
    //   1724: aload_1
    //   1725: astore 16
    //   1727: aload_3
    //   1728: astore 17
    //   1730: aload 14
    //   1732: astore 9
    //   1734: aload 13
    //   1736: astore 18
    //   1738: aload_1
    //   1739: astore 20
    //   1741: aload_3
    //   1742: astore 23
    //   1744: aload 14
    //   1746: astore 11
    //   1748: sipush 8192
    //   1751: newarray <illegal type>
    //   1753: astore_2
    //   1754: aload 13
    //   1756: astore 19
    //   1758: aload_1
    //   1759: astore 21
    //   1761: aload_3
    //   1762: astore 24
    //   1764: aload 14
    //   1766: astore 12
    //   1768: aload 13
    //   1770: astore 15
    //   1772: aload_1
    //   1773: astore 16
    //   1775: aload_3
    //   1776: astore 17
    //   1778: aload 14
    //   1780: astore 9
    //   1782: aload 13
    //   1784: astore 18
    //   1786: aload_1
    //   1787: astore 20
    //   1789: aload_3
    //   1790: astore 23
    //   1792: aload 14
    //   1794: astore 11
    //   1796: aload_1
    //   1797: aload_2
    //   1798: invokevirtual 570	java/io/InputStream:read	([B)I
    //   1801: istore 5
    //   1803: aload 13
    //   1805: astore 4
    //   1807: aload_1
    //   1808: astore 10
    //   1810: aload_3
    //   1811: astore 22
    //   1813: aload 14
    //   1815: astore 25
    //   1817: iload 5
    //   1819: iconst_m1
    //   1820: if_icmpeq +297 -> 2117
    //   1823: iload 6
    //   1825: ifne +56 -> 1881
    //   1828: aload 13
    //   1830: astore 19
    //   1832: aload_1
    //   1833: astore 21
    //   1835: aload_3
    //   1836: astore 24
    //   1838: aload 14
    //   1840: astore 12
    //   1842: aload 13
    //   1844: astore 15
    //   1846: aload_1
    //   1847: astore 16
    //   1849: aload_3
    //   1850: astore 17
    //   1852: aload 14
    //   1854: astore 9
    //   1856: aload 13
    //   1858: astore 18
    //   1860: aload_1
    //   1861: astore 20
    //   1863: aload_3
    //   1864: astore 23
    //   1866: aload 14
    //   1868: astore 11
    //   1870: aload_3
    //   1871: aload_2
    //   1872: iconst_0
    //   1873: iload 5
    //   1875: invokevirtual 714	java/io/OutputStream:write	([BII)V
    //   1878: goto -124 -> 1754
    //   1881: aload 13
    //   1883: astore 19
    //   1885: aload_1
    //   1886: astore 21
    //   1888: aload_3
    //   1889: astore 24
    //   1891: aload 14
    //   1893: astore 12
    //   1895: aload 13
    //   1897: astore 15
    //   1899: aload_1
    //   1900: astore 16
    //   1902: aload_3
    //   1903: astore 17
    //   1905: aload 14
    //   1907: astore 9
    //   1909: aload 13
    //   1911: astore 18
    //   1913: aload_1
    //   1914: astore 20
    //   1916: aload_3
    //   1917: astore 23
    //   1919: aload 14
    //   1921: astore 11
    //   1923: aload 13
    //   1925: aload_2
    //   1926: iload 5
    //   1928: invokevirtual 718	com/google/android/mms/util/DrmConvertSession:convert	([BI)[B
    //   1931: astore 4
    //   1933: aload 4
    //   1935: ifnull +58 -> 1993
    //   1938: aload 13
    //   1940: astore 19
    //   1942: aload_1
    //   1943: astore 21
    //   1945: aload_3
    //   1946: astore 24
    //   1948: aload 14
    //   1950: astore 12
    //   1952: aload 13
    //   1954: astore 15
    //   1956: aload_1
    //   1957: astore 16
    //   1959: aload_3
    //   1960: astore 17
    //   1962: aload 14
    //   1964: astore 9
    //   1966: aload 13
    //   1968: astore 18
    //   1970: aload_1
    //   1971: astore 20
    //   1973: aload_3
    //   1974: astore 23
    //   1976: aload 14
    //   1978: astore 11
    //   1980: aload_3
    //   1981: aload 4
    //   1983: iconst_0
    //   1984: aload 4
    //   1986: arraylength
    //   1987: invokevirtual 714	java/io/OutputStream:write	([BII)V
    //   1990: goto -236 -> 1754
    //   1993: aload 13
    //   1995: astore 19
    //   1997: aload_1
    //   1998: astore 21
    //   2000: aload_3
    //   2001: astore 24
    //   2003: aload 14
    //   2005: astore 12
    //   2007: aload 13
    //   2009: astore 15
    //   2011: aload_1
    //   2012: astore 16
    //   2014: aload_3
    //   2015: astore 17
    //   2017: aload 14
    //   2019: astore 9
    //   2021: aload 13
    //   2023: astore 18
    //   2025: aload_1
    //   2026: astore 20
    //   2028: aload_3
    //   2029: astore 23
    //   2031: aload 14
    //   2033: astore 11
    //   2035: new 477	com/google/android/mms/MmsException
    //   2038: dup
    //   2039: ldc_w 720
    //   2042: invokespecial 560	com/google/android/mms/MmsException:<init>	(Ljava/lang/String;)V
    //   2045: athrow
    //   2046: iload 6
    //   2048: ifne +162 -> 2210
    //   2051: aload 13
    //   2053: astore 19
    //   2055: aload 27
    //   2057: astore 21
    //   2059: aload_3
    //   2060: astore 24
    //   2062: aload 14
    //   2064: astore 12
    //   2066: aload 13
    //   2068: astore 15
    //   2070: aload 26
    //   2072: astore 16
    //   2074: aload_3
    //   2075: astore 17
    //   2077: aload 14
    //   2079: astore 9
    //   2081: aload 13
    //   2083: astore 18
    //   2085: aload 28
    //   2087: astore 20
    //   2089: aload_3
    //   2090: astore 23
    //   2092: aload 14
    //   2094: astore 11
    //   2096: aload_3
    //   2097: aload 42
    //   2099: invokevirtual 722	java/io/OutputStream:write	([B)V
    //   2102: aload 14
    //   2104: astore 25
    //   2106: aload_3
    //   2107: astore 22
    //   2109: aload 29
    //   2111: astore 10
    //   2113: aload 13
    //   2115: astore 4
    //   2117: aload 22
    //   2119: ifnull +8 -> 2127
    //   2122: aload 22
    //   2124: invokevirtual 653	java/io/OutputStream:close	()V
    //   2127: aload 10
    //   2129: ifnull +8 -> 2137
    //   2132: aload 10
    //   2134: invokevirtual 576	java/io/InputStream:close	()V
    //   2137: aload 4
    //   2139: ifnull -1171 -> 968
    //   2142: aload 4
    //   2144: aload 25
    //   2146: invokevirtual 657	com/google/android/mms/util/DrmConvertSession:close	(Ljava/lang/String;)I
    //   2149: pop
    //   2150: new 659	java/io/File
    //   2153: dup
    //   2154: aload 25
    //   2156: invokespecial 660	java/io/File:<init>	(Ljava/lang/String;)V
    //   2159: astore_3
    //   2160: new 612	android/content/ContentValues
    //   2163: dup
    //   2164: iconst_0
    //   2165: invokespecial 614	android/content/ContentValues:<init>	(I)V
    //   2168: astore 4
    //   2170: aload_0
    //   2171: getfield 272	com/google/android/mms/pdu/PduPersister:mContext	Landroid/content/Context;
    //   2174: astore_1
    //   2175: aload_0
    //   2176: getfield 280	com/google/android/mms/pdu/PduPersister:mContentResolver	Landroid/content/ContentResolver;
    //   2179: astore_2
    //   2180: new 414	java/lang/StringBuilder
    //   2183: dup
    //   2184: invokespecial 415	java/lang/StringBuilder:<init>	()V
    //   2187: ldc_w 662
    //   2190: invokevirtual 421	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2193: aload_3
    //   2194: invokevirtual 665	java/io/File:getName	()Ljava/lang/String;
    //   2197: invokevirtual 421	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2200: invokevirtual 427	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2203: invokestatic 431	android/net/Uri:parse	(Ljava/lang/String;)Landroid/net/Uri;
    //   2206: astore_3
    //   2207: goto -1250 -> 957
    //   2210: aload 13
    //   2212: astore 19
    //   2214: aload 27
    //   2216: astore 21
    //   2218: aload_3
    //   2219: astore 24
    //   2221: aload 14
    //   2223: astore 12
    //   2225: aload 13
    //   2227: astore 15
    //   2229: aload 26
    //   2231: astore 16
    //   2233: aload_3
    //   2234: astore 17
    //   2236: aload 14
    //   2238: astore 9
    //   2240: aload 13
    //   2242: astore 18
    //   2244: aload 28
    //   2246: astore 20
    //   2248: aload_3
    //   2249: astore 23
    //   2251: aload 14
    //   2253: astore 11
    //   2255: aload 13
    //   2257: aload 42
    //   2259: aload 42
    //   2261: arraylength
    //   2262: invokevirtual 718	com/google/android/mms/util/DrmConvertSession:convert	([BI)[B
    //   2265: astore_1
    //   2266: aload_1
    //   2267: ifnull +74 -> 2341
    //   2270: aload 13
    //   2272: astore 19
    //   2274: aload 27
    //   2276: astore 21
    //   2278: aload_3
    //   2279: astore 24
    //   2281: aload 14
    //   2283: astore 12
    //   2285: aload 13
    //   2287: astore 15
    //   2289: aload 26
    //   2291: astore 16
    //   2293: aload_3
    //   2294: astore 17
    //   2296: aload 14
    //   2298: astore 9
    //   2300: aload 13
    //   2302: astore 18
    //   2304: aload 28
    //   2306: astore 20
    //   2308: aload_3
    //   2309: astore 23
    //   2311: aload 14
    //   2313: astore 11
    //   2315: aload_3
    //   2316: aload_1
    //   2317: iconst_0
    //   2318: aload_1
    //   2319: arraylength
    //   2320: invokevirtual 714	java/io/OutputStream:write	([BII)V
    //   2323: aload 13
    //   2325: astore 4
    //   2327: aload 29
    //   2329: astore 10
    //   2331: aload_3
    //   2332: astore 22
    //   2334: aload 14
    //   2336: astore 25
    //   2338: goto -221 -> 2117
    //   2341: aload 13
    //   2343: astore 19
    //   2345: aload 27
    //   2347: astore 21
    //   2349: aload_3
    //   2350: astore 24
    //   2352: aload 14
    //   2354: astore 12
    //   2356: aload 13
    //   2358: astore 15
    //   2360: aload 26
    //   2362: astore 16
    //   2364: aload_3
    //   2365: astore 17
    //   2367: aload 14
    //   2369: astore 9
    //   2371: aload 13
    //   2373: astore 18
    //   2375: aload 28
    //   2377: astore 20
    //   2379: aload_3
    //   2380: astore 23
    //   2382: aload 14
    //   2384: astore 11
    //   2386: new 477	com/google/android/mms/MmsException
    //   2389: dup
    //   2390: ldc_w 720
    //   2393: invokespecial 560	com/google/android/mms/MmsException:<init>	(Ljava/lang/String;)V
    //   2396: athrow
    //   2397: astore_2
    //   2398: ldc 104
    //   2400: new 414	java/lang/StringBuilder
    //   2403: dup
    //   2404: invokespecial 415	java/lang/StringBuilder:<init>	()V
    //   2407: ldc_w 724
    //   2410: invokevirtual 421	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2413: aload 17
    //   2415: invokevirtual 691	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   2418: invokevirtual 427	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2421: aload_2
    //   2422: invokestatic 386	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   2425: pop
    //   2426: goto -1796 -> 630
    //   2429: astore_2
    //   2430: ldc 104
    //   2432: new 414	java/lang/StringBuilder
    //   2435: dup
    //   2436: invokespecial 415	java/lang/StringBuilder:<init>	()V
    //   2439: ldc_w 724
    //   2442: invokevirtual 421	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2445: aload 16
    //   2447: invokevirtual 691	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   2450: invokevirtual 427	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2453: aload_2
    //   2454: invokestatic 386	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   2457: pop
    //   2458: goto -1818 -> 640
    //   2461: astore_1
    //   2462: ldc 104
    //   2464: new 414	java/lang/StringBuilder
    //   2467: dup
    //   2468: invokespecial 415	java/lang/StringBuilder:<init>	()V
    //   2471: ldc_w 724
    //   2474: invokevirtual 421	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2477: aload 22
    //   2479: invokevirtual 691	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   2482: invokevirtual 427	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2485: aload_1
    //   2486: invokestatic 386	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   2489: pop
    //   2490: goto -363 -> 2127
    //   2493: astore_1
    //   2494: ldc 104
    //   2496: new 414	java/lang/StringBuilder
    //   2499: dup
    //   2500: invokespecial 415	java/lang/StringBuilder:<init>	()V
    //   2503: ldc_w 724
    //   2506: invokevirtual 421	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2509: aload 10
    //   2511: invokevirtual 691	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   2514: invokevirtual 427	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2517: aload_1
    //   2518: invokestatic 386	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   2521: pop
    //   2522: goto -385 -> 2137
    //   2525: astore_1
    //   2526: ldc 104
    //   2528: new 414	java/lang/StringBuilder
    //   2531: dup
    //   2532: invokespecial 415	java/lang/StringBuilder:<init>	()V
    //   2535: ldc_w 724
    //   2538: invokevirtual 421	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2541: aload_3
    //   2542: invokevirtual 691	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   2545: invokevirtual 427	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2548: aload_1
    //   2549: invokestatic 386	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   2552: pop
    //   2553: goto -1112 -> 1441
    //   2556: astore_1
    //   2557: ldc 104
    //   2559: new 414	java/lang/StringBuilder
    //   2562: dup
    //   2563: invokespecial 415	java/lang/StringBuilder:<init>	()V
    //   2566: ldc_w 724
    //   2569: invokevirtual 421	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2572: aconst_null
    //   2573: invokevirtual 691	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   2576: invokevirtual 427	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2579: aload_1
    //   2580: invokestatic 386	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   2583: pop
    //   2584: goto -1131 -> 1453
    //   2587: astore_1
    //   2588: ldc 104
    //   2590: new 414	java/lang/StringBuilder
    //   2593: dup
    //   2594: invokespecial 415	java/lang/StringBuilder:<init>	()V
    //   2597: ldc_w 724
    //   2600: invokevirtual 421	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2603: aconst_null
    //   2604: invokevirtual 691	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   2607: invokevirtual 427	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2610: aload_1
    //   2611: invokestatic 386	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   2614: pop
    //   2615: goto -1682 -> 933
    //   2618: astore_1
    //   2619: ldc 104
    //   2621: new 414	java/lang/StringBuilder
    //   2624: dup
    //   2625: invokespecial 415	java/lang/StringBuilder:<init>	()V
    //   2628: ldc_w 724
    //   2631: invokevirtual 421	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2634: aconst_null
    //   2635: invokevirtual 691	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   2638: invokevirtual 427	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   2641: aload_1
    //   2642: invokestatic 386	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   2645: pop
    //   2646: goto -1701 -> 945
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	2649	0	this	PduPersister
    //   0	2649	1	paramPduPart	PduPart
    //   0	2649	2	paramUri	Uri
    //   0	2649	3	paramString	String
    //   0	2649	4	paramHashMap	HashMap<Uri, InputStream>
    //   1801	126	5	i	int
    //   767	1280	6	bool	boolean
    //   908	7	7	l	long
    //   93	2277	9	localObject1	Object
    //   49	2461	10	localObject2	Object
    //   109	2276	11	localObject3	Object
    //   77	2278	12	localObject4	Object
    //   58	853	13	localObject5	Object
    //   969	76	13	localException	Exception
    //   1110	1262	13	localObject6	Object
    //   55	2328	14	localObject7	Object
    //   81	2278	15	localObject8	Object
    //   85	2361	16	localObject9	Object
    //   89	2325	17	localObject10	Object
    //   97	2277	18	localObject11	Object
    //   65	2279	19	localObject12	Object
    //   101	2277	20	localObject13	Object
    //   69	2279	21	localObject14	Object
    //   7	2471	22	localObject15	Object
    //   105	2276	23	localObject16	Object
    //   73	2278	24	localObject17	Object
    //   19	2318	25	localObject18	Object
    //   13	2348	26	localObject19	Object
    //   25	2321	27	localObject20	Object
    //   16	2360	28	localObject21	Object
    //   22	2306	29	localObject22	Object
    //   4	1292	30	localObject23	Object
    //   10	1254	31	localObject24	Object
    //   1	1279	32	localObject25	Object
    //   40	1011	33	localObject26	Object
    //   31	1052	34	localObject27	Object
    //   28	1039	35	localObject28	Object
    //   52	779	36	localObject29	Object
    //   43	768	37	localObject30	Object
    //   61	734	38	localObject31	Object
    //   46	769	39	localObject32	Object
    //   37	425	40	localObject33	Object
    //   34	736	41	localObject34	Object
    //   115	2145	42	arrayOfByte	byte[]
    // Exception table:
    //   from	to	target	type
    //   111	117	567	java/io/FileNotFoundException
    //   165	175	567	java/io/FileNotFoundException
    //   223	233	567	java/io/FileNotFoundException
    //   281	291	567	java/io/FileNotFoundException
    //   339	347	567	java/io/FileNotFoundException
    //   395	413	567	java/io/FileNotFoundException
    //   473	488	567	java/io/FileNotFoundException
    //   536	567	567	java/io/FileNotFoundException
    //   763	769	567	java/io/FileNotFoundException
    //   834	844	567	java/io/FileNotFoundException
    //   896	910	567	java/io/FileNotFoundException
    //   1019	1050	567	java/io/FileNotFoundException
    //   1098	1108	567	java/io/FileNotFoundException
    //   1169	1203	567	java/io/FileNotFoundException
    //   1303	1312	567	java/io/FileNotFoundException
    //   1362	1368	567	java/io/FileNotFoundException
    //   1424	1433	567	java/io/FileNotFoundException
    //   1582	1592	567	java/io/FileNotFoundException
    //   1637	1648	567	java/io/FileNotFoundException
    //   1696	1706	567	java/io/FileNotFoundException
    //   1748	1754	567	java/io/FileNotFoundException
    //   1796	1803	567	java/io/FileNotFoundException
    //   1870	1878	567	java/io/FileNotFoundException
    //   1923	1933	567	java/io/FileNotFoundException
    //   1980	1990	567	java/io/FileNotFoundException
    //   2035	2046	567	java/io/FileNotFoundException
    //   2096	2102	567	java/io/FileNotFoundException
    //   2255	2266	567	java/io/FileNotFoundException
    //   2315	2323	567	java/io/FileNotFoundException
    //   2386	2397	567	java/io/FileNotFoundException
    //   111	117	619	finally
    //   165	175	619	finally
    //   223	233	619	finally
    //   281	291	619	finally
    //   339	347	619	finally
    //   395	413	619	finally
    //   473	488	619	finally
    //   536	567	619	finally
    //   584	594	619	finally
    //   610	619	619	finally
    //   763	769	619	finally
    //   834	844	619	finally
    //   896	910	619	finally
    //   1019	1050	619	finally
    //   1098	1108	619	finally
    //   1169	1203	619	finally
    //   1220	1230	619	finally
    //   1246	1255	619	finally
    //   1303	1312	619	finally
    //   1362	1368	619	finally
    //   1424	1433	619	finally
    //   1582	1592	619	finally
    //   1637	1648	619	finally
    //   1696	1706	619	finally
    //   1748	1754	619	finally
    //   1796	1803	619	finally
    //   1870	1878	619	finally
    //   1923	1933	619	finally
    //   1980	1990	619	finally
    //   2035	2046	619	finally
    //   2096	2102	619	finally
    //   2255	2266	619	finally
    //   2315	2323	619	finally
    //   2386	2397	619	finally
    //   834	844	969	java/lang/Exception
    //   896	910	969	java/lang/Exception
    //   111	117	1203	java/io/IOException
    //   165	175	1203	java/io/IOException
    //   223	233	1203	java/io/IOException
    //   281	291	1203	java/io/IOException
    //   339	347	1203	java/io/IOException
    //   395	413	1203	java/io/IOException
    //   473	488	1203	java/io/IOException
    //   536	567	1203	java/io/IOException
    //   763	769	1203	java/io/IOException
    //   834	844	1203	java/io/IOException
    //   896	910	1203	java/io/IOException
    //   1019	1050	1203	java/io/IOException
    //   1098	1108	1203	java/io/IOException
    //   1169	1203	1203	java/io/IOException
    //   1303	1312	1203	java/io/IOException
    //   1362	1368	1203	java/io/IOException
    //   1424	1433	1203	java/io/IOException
    //   1582	1592	1203	java/io/IOException
    //   1637	1648	1203	java/io/IOException
    //   1696	1706	1203	java/io/IOException
    //   1748	1754	1203	java/io/IOException
    //   1796	1803	1203	java/io/IOException
    //   1870	1878	1203	java/io/IOException
    //   1923	1933	1203	java/io/IOException
    //   1980	1990	1203	java/io/IOException
    //   2035	2046	1203	java/io/IOException
    //   2096	2102	1203	java/io/IOException
    //   2255	2266	1203	java/io/IOException
    //   2315	2323	1203	java/io/IOException
    //   2386	2397	1203	java/io/IOException
    //   625	630	2397	java/io/IOException
    //   635	640	2429	java/io/IOException
    //   2122	2127	2461	java/io/IOException
    //   2132	2137	2493	java/io/IOException
    //   1437	1441	2525	java/io/IOException
    //   1445	1453	2556	java/io/IOException
    //   925	933	2587	java/io/IOException
    //   937	945	2618	java/io/IOException
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
      Log.e("PduPersister", "ISO_8859_1 must be supported!", paramArrayOfByte);
    }
    return "";
  }
  
  private void updateAddress(long paramLong, int paramInt, EncodedStringValue[] paramArrayOfEncodedStringValue)
  {
    SqliteWrapper.delete(mContext, mContentResolver, Uri.parse("content://mms/" + paramLong + "/addr"), "type=" + paramInt, null);
    persistAddress(paramLong, paramInt, paramArrayOfEncodedStringValue);
  }
  
  private void updatePart(Uri paramUri, PduPart paramPduPart, HashMap<Uri, InputStream> paramHashMap)
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
        persistData(paramPduPart, paramUri, str, paramHashMap);
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
    //   10: ldc2_w 819
    //   13: lstore 11
    //   15: getstatic 267	com/google/android/mms/pdu/PduPersister:PDU_CACHE_INSTANCE	Lcom/google/android/mms/util/PduCache;
    //   18: astore 18
    //   20: aload 18
    //   22: monitorenter
    //   23: getstatic 267	com/google/android/mms/pdu/PduPersister:PDU_CACHE_INSTANCE	Lcom/google/android/mms/util/PduCache;
    //   26: aload_1
    //   27: invokevirtual 824	com/google/android/mms/util/PduCache:isUpdating	(Landroid/net/Uri;)Z
    //   30: istore 15
    //   32: iload 15
    //   34: ifeq +150 -> 184
    //   37: getstatic 267	com/google/android/mms/pdu/PduPersister:PDU_CACHE_INSTANCE	Lcom/google/android/mms/util/PduCache;
    //   40: invokevirtual 827	java/lang/Object:wait	()V
    //   43: getstatic 267	com/google/android/mms/pdu/PduPersister:PDU_CACHE_INSTANCE	Lcom/google/android/mms/util/PduCache;
    //   46: aload_1
    //   47: invokevirtual 828	com/google/android/mms/util/PduCache:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   50: checkcast 830	com/google/android/mms/util/PduCacheEntry
    //   53: astore 17
    //   55: aload 17
    //   57: astore 16
    //   59: aload 17
    //   61: ifnull +123 -> 184
    //   64: aload 17
    //   66: invokevirtual 834	com/google/android/mms/util/PduCacheEntry:getPdu	()Lcom/google/android/mms/pdu/GenericPdu;
    //   69: astore 17
    //   71: aload 18
    //   73: monitorexit
    //   74: getstatic 267	com/google/android/mms/pdu/PduPersister:PDU_CACHE_INSTANCE	Lcom/google/android/mms/util/PduCache;
    //   77: astore 16
    //   79: aload 16
    //   81: monitorenter
    //   82: iconst_0
    //   83: ifeq +1284 -> 1367
    //   86: getstatic 129	com/google/android/mms/pdu/PduPersister:$assertionsDisabled	Z
    //   89: ifne +1254 -> 1343
    //   92: getstatic 267	com/google/android/mms/pdu/PduPersister:PDU_CACHE_INSTANCE	Lcom/google/android/mms/util/PduCache;
    //   95: aload_1
    //   96: invokevirtual 828	com/google/android/mms/util/PduCache:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   99: ifnull +1244 -> 1343
    //   102: new 836	java/lang/AssertionError
    //   105: dup
    //   106: invokespecial 837	java/lang/AssertionError:<init>	()V
    //   109: athrow
    //   110: aload 16
    //   112: monitorexit
    //   113: aload_1
    //   114: athrow
    //   115: astore 16
    //   117: ldc 104
    //   119: ldc_w 839
    //   122: aload 16
    //   124: invokestatic 386	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   127: pop
    //   128: goto -85 -> 43
    //   131: aload 18
    //   133: monitorexit
    //   134: aload 16
    //   136: athrow
    //   137: astore 16
    //   139: lload 11
    //   141: lstore 7
    //   143: getstatic 267	com/google/android/mms/pdu/PduPersister:PDU_CACHE_INSTANCE	Lcom/google/android/mms/util/PduCache;
    //   146: astore 17
    //   148: aload 17
    //   150: monitorenter
    //   151: iconst_0
    //   152: ifeq +1126 -> 1278
    //   155: getstatic 129	com/google/android/mms/pdu/PduPersister:$assertionsDisabled	Z
    //   158: ifne +1097 -> 1255
    //   161: getstatic 267	com/google/android/mms/pdu/PduPersister:PDU_CACHE_INSTANCE	Lcom/google/android/mms/util/PduCache;
    //   164: aload_1
    //   165: invokevirtual 828	com/google/android/mms/util/PduCache:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   168: ifnull +1087 -> 1255
    //   171: new 836	java/lang/AssertionError
    //   174: dup
    //   175: invokespecial 837	java/lang/AssertionError:<init>	()V
    //   178: athrow
    //   179: aload 17
    //   181: monitorexit
    //   182: aload_1
    //   183: athrow
    //   184: getstatic 267	com/google/android/mms/pdu/PduPersister:PDU_CACHE_INSTANCE	Lcom/google/android/mms/util/PduCache;
    //   187: aload_1
    //   188: iconst_1
    //   189: invokevirtual 843	com/google/android/mms/util/PduCache:setUpdating	(Landroid/net/Uri;Z)V
    //   192: aload 18
    //   194: monitorexit
    //   195: iload_3
    //   196: istore_2
    //   197: lload 11
    //   199: lstore 7
    //   201: aload_0
    //   202: getfield 272	com/google/android/mms/pdu/PduPersister:mContext	Landroid/content/Context;
    //   205: aload_0
    //   206: getfield 280	com/google/android/mms/pdu/PduPersister:mContentResolver	Landroid/content/ContentResolver;
    //   209: aload_1
    //   210: getstatic 189	com/google/android/mms/pdu/PduPersister:PDU_PROJECTION	[Ljava/lang/String;
    //   213: aconst_null
    //   214: aconst_null
    //   215: aconst_null
    //   216: invokestatic 442	com/google/android/mms/util/SqliteWrapper:query	(Landroid/content/Context;Landroid/content/ContentResolver;Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   219: astore 17
    //   221: iload_3
    //   222: istore_2
    //   223: lload 11
    //   225: lstore 7
    //   227: new 466	com/google/android/mms/pdu/PduHeaders
    //   230: dup
    //   231: invokespecial 844	com/google/android/mms/pdu/PduHeaders:<init>	()V
    //   234: astore 16
    //   236: iload_3
    //   237: istore_2
    //   238: lload 11
    //   240: lstore 7
    //   242: aload_1
    //   243: invokestatic 850	android/content/ContentUris:parseId	(Landroid/net/Uri;)J
    //   246: lstore 13
    //   248: aload 17
    //   250: ifnull +38 -> 288
    //   253: iload 4
    //   255: istore_3
    //   256: lload 11
    //   258: lstore 9
    //   260: aload 17
    //   262: invokeinterface 338 1 0
    //   267: iconst_1
    //   268: if_icmpne +20 -> 288
    //   271: iload 4
    //   273: istore_3
    //   274: lload 11
    //   276: lstore 9
    //   278: aload 17
    //   280: invokeinterface 341 1 0
    //   285: ifne +72 -> 357
    //   288: iload 4
    //   290: istore_3
    //   291: lload 11
    //   293: lstore 9
    //   295: new 477	com/google/android/mms/MmsException
    //   298: dup
    //   299: new 414	java/lang/StringBuilder
    //   302: dup
    //   303: invokespecial 415	java/lang/StringBuilder:<init>	()V
    //   306: ldc_w 852
    //   309: invokevirtual 421	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   312: aload_1
    //   313: invokevirtual 691	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   316: invokevirtual 427	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   319: invokespecial 560	com/google/android/mms/MmsException:<init>	(Ljava/lang/String;)V
    //   322: athrow
    //   323: astore 16
    //   325: aload 17
    //   327: ifnull +16 -> 343
    //   330: iload_3
    //   331: istore_2
    //   332: lload 9
    //   334: lstore 7
    //   336: aload 17
    //   338: invokeinterface 353 1 0
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
    //   367: invokeinterface 392 2 0
    //   372: istore 4
    //   374: iload 4
    //   376: istore_3
    //   377: lload 11
    //   379: lstore 9
    //   381: aload 17
    //   383: iconst_2
    //   384: invokeinterface 516 2 0
    //   389: lstore 11
    //   391: iload 4
    //   393: istore_3
    //   394: lload 11
    //   396: lstore 9
    //   398: getstatic 245	com/google/android/mms/pdu/PduPersister:ENCODED_STRING_COLUMN_INDEX_MAP	Ljava/util/HashMap;
    //   401: invokevirtual 856	java/util/HashMap:entrySet	()Ljava/util/Set;
    //   404: invokeinterface 862 1 0
    //   409: astore 18
    //   411: iload 4
    //   413: istore_3
    //   414: lload 11
    //   416: lstore 9
    //   418: aload 18
    //   420: invokeinterface 867 1 0
    //   425: ifeq +66 -> 491
    //   428: iload 4
    //   430: istore_3
    //   431: lload 11
    //   433: lstore 9
    //   435: aload 18
    //   437: invokeinterface 871 1 0
    //   442: checkcast 873	java/util/Map$Entry
    //   445: astore 19
    //   447: iload 4
    //   449: istore_3
    //   450: lload 11
    //   452: lstore 9
    //   454: aload_0
    //   455: aload 17
    //   457: aload 19
    //   459: invokeinterface 876 1 0
    //   464: checkcast 222	java/lang/Integer
    //   467: invokevirtual 487	java/lang/Integer:intValue	()I
    //   470: aload 16
    //   472: aload 19
    //   474: invokeinterface 879 1 0
    //   479: checkcast 222	java/lang/Integer
    //   482: invokevirtual 487	java/lang/Integer:intValue	()I
    //   485: invokespecial 881	com/google/android/mms/pdu/PduPersister:setEncodedStringValueToHeaders	(Landroid/database/Cursor;ILcom/google/android/mms/pdu/PduHeaders;I)V
    //   488: goto -77 -> 411
    //   491: iload 4
    //   493: istore_3
    //   494: lload 11
    //   496: lstore 9
    //   498: getstatic 249	com/google/android/mms/pdu/PduPersister:TEXT_STRING_COLUMN_INDEX_MAP	Ljava/util/HashMap;
    //   501: invokevirtual 856	java/util/HashMap:entrySet	()Ljava/util/Set;
    //   504: invokeinterface 862 1 0
    //   509: astore 18
    //   511: iload 4
    //   513: istore_3
    //   514: lload 11
    //   516: lstore 9
    //   518: aload 18
    //   520: invokeinterface 867 1 0
    //   525: ifeq +66 -> 591
    //   528: iload 4
    //   530: istore_3
    //   531: lload 11
    //   533: lstore 9
    //   535: aload 18
    //   537: invokeinterface 871 1 0
    //   542: checkcast 873	java/util/Map$Entry
    //   545: astore 19
    //   547: iload 4
    //   549: istore_3
    //   550: lload 11
    //   552: lstore 9
    //   554: aload_0
    //   555: aload 17
    //   557: aload 19
    //   559: invokeinterface 876 1 0
    //   564: checkcast 222	java/lang/Integer
    //   567: invokevirtual 487	java/lang/Integer:intValue	()I
    //   570: aload 16
    //   572: aload 19
    //   574: invokeinterface 879 1 0
    //   579: checkcast 222	java/lang/Integer
    //   582: invokevirtual 487	java/lang/Integer:intValue	()I
    //   585: invokespecial 883	com/google/android/mms/pdu/PduPersister:setTextStringToHeaders	(Landroid/database/Cursor;ILcom/google/android/mms/pdu/PduHeaders;I)V
    //   588: goto -77 -> 511
    //   591: iload 4
    //   593: istore_3
    //   594: lload 11
    //   596: lstore 9
    //   598: getstatic 253	com/google/android/mms/pdu/PduPersister:OCTET_COLUMN_INDEX_MAP	Ljava/util/HashMap;
    //   601: invokevirtual 856	java/util/HashMap:entrySet	()Ljava/util/Set;
    //   604: invokeinterface 862 1 0
    //   609: astore 18
    //   611: iload 4
    //   613: istore_3
    //   614: lload 11
    //   616: lstore 9
    //   618: aload 18
    //   620: invokeinterface 867 1 0
    //   625: ifeq +66 -> 691
    //   628: iload 4
    //   630: istore_3
    //   631: lload 11
    //   633: lstore 9
    //   635: aload 18
    //   637: invokeinterface 871 1 0
    //   642: checkcast 873	java/util/Map$Entry
    //   645: astore 19
    //   647: iload 4
    //   649: istore_3
    //   650: lload 11
    //   652: lstore 9
    //   654: aload_0
    //   655: aload 17
    //   657: aload 19
    //   659: invokeinterface 876 1 0
    //   664: checkcast 222	java/lang/Integer
    //   667: invokevirtual 487	java/lang/Integer:intValue	()I
    //   670: aload 16
    //   672: aload 19
    //   674: invokeinterface 879 1 0
    //   679: checkcast 222	java/lang/Integer
    //   682: invokevirtual 487	java/lang/Integer:intValue	()I
    //   685: invokespecial 885	com/google/android/mms/pdu/PduPersister:setOctetToHeaders	(Landroid/database/Cursor;ILcom/google/android/mms/pdu/PduHeaders;I)V
    //   688: goto -77 -> 611
    //   691: iload 4
    //   693: istore_3
    //   694: lload 11
    //   696: lstore 9
    //   698: getstatic 257	com/google/android/mms/pdu/PduPersister:LONG_COLUMN_INDEX_MAP	Ljava/util/HashMap;
    //   701: invokevirtual 856	java/util/HashMap:entrySet	()Ljava/util/Set;
    //   704: invokeinterface 862 1 0
    //   709: astore 18
    //   711: iload 4
    //   713: istore_3
    //   714: lload 11
    //   716: lstore 9
    //   718: aload 18
    //   720: invokeinterface 867 1 0
    //   725: ifeq +66 -> 791
    //   728: iload 4
    //   730: istore_3
    //   731: lload 11
    //   733: lstore 9
    //   735: aload 18
    //   737: invokeinterface 871 1 0
    //   742: checkcast 873	java/util/Map$Entry
    //   745: astore 19
    //   747: iload 4
    //   749: istore_3
    //   750: lload 11
    //   752: lstore 9
    //   754: aload_0
    //   755: aload 17
    //   757: aload 19
    //   759: invokeinterface 876 1 0
    //   764: checkcast 222	java/lang/Integer
    //   767: invokevirtual 487	java/lang/Integer:intValue	()I
    //   770: aload 16
    //   772: aload 19
    //   774: invokeinterface 879 1 0
    //   779: checkcast 222	java/lang/Integer
    //   782: invokevirtual 487	java/lang/Integer:intValue	()I
    //   785: invokespecial 887	com/google/android/mms/pdu/PduPersister:setLongToHeaders	(Landroid/database/Cursor;ILcom/google/android/mms/pdu/PduHeaders;I)V
    //   788: goto -77 -> 711
    //   791: aload 17
    //   793: ifnull +17 -> 810
    //   796: iload 4
    //   798: istore_2
    //   799: lload 11
    //   801: lstore 7
    //   803: aload 17
    //   805: invokeinterface 353 1 0
    //   810: lload 13
    //   812: ldc2_w 819
    //   815: lcmp
    //   816: ifne +21 -> 837
    //   819: iload 4
    //   821: istore_2
    //   822: lload 11
    //   824: lstore 7
    //   826: new 477	com/google/android/mms/MmsException
    //   829: dup
    //   830: ldc_w 889
    //   833: invokespecial 560	com/google/android/mms/MmsException:<init>	(Ljava/lang/String;)V
    //   836: athrow
    //   837: iload 4
    //   839: istore_2
    //   840: lload 11
    //   842: lstore 7
    //   844: aload_0
    //   845: lload 13
    //   847: aload 16
    //   849: invokespecial 891	com/google/android/mms/pdu/PduPersister:loadAddress	(JLcom/google/android/mms/pdu/PduHeaders;)V
    //   852: iload 4
    //   854: istore_2
    //   855: lload 11
    //   857: lstore 7
    //   859: aload 16
    //   861: sipush 140
    //   864: invokevirtual 894	com/google/android/mms/pdu/PduHeaders:getOctet	(I)I
    //   867: istore 5
    //   869: iload 4
    //   871: istore_2
    //   872: lload 11
    //   874: lstore 7
    //   876: new 896	com/google/android/mms/pdu/PduBody
    //   879: dup
    //   880: invokespecial 897	com/google/android/mms/pdu/PduBody:<init>	()V
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
    //   911: invokespecial 899	com/google/android/mms/pdu/PduPersister:loadParts	(J)[Lcom/google/android/mms/pdu/PduPart;
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
    //   954: invokevirtual 903	com/google/android/mms/pdu/PduBody:addPart	(Lcom/google/android/mms/pdu/PduPart;)Z
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
    //   972: new 477	com/google/android/mms/MmsException
    //   975: dup
    //   976: new 414	java/lang/StringBuilder
    //   979: dup
    //   980: invokespecial 415	java/lang/StringBuilder:<init>	()V
    //   983: ldc_w 905
    //   986: invokevirtual 421	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   989: iload 5
    //   991: invokestatic 908	java/lang/Integer:toHexString	(I)Ljava/lang/String;
    //   994: invokevirtual 421	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   997: invokevirtual 427	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1000: invokespecial 560	com/google/android/mms/MmsException:<init>	(Ljava/lang/String;)V
    //   1003: athrow
    //   1004: iload 4
    //   1006: istore_2
    //   1007: lload 11
    //   1009: lstore 7
    //   1011: new 910	com/google/android/mms/pdu/NotificationInd
    //   1014: dup
    //   1015: aload 16
    //   1017: invokespecial 913	com/google/android/mms/pdu/NotificationInd:<init>	(Lcom/google/android/mms/pdu/PduHeaders;)V
    //   1020: astore 16
    //   1022: getstatic 267	com/google/android/mms/pdu/PduPersister:PDU_CACHE_INSTANCE	Lcom/google/android/mms/util/PduCache;
    //   1025: astore 17
    //   1027: aload 17
    //   1029: monitorenter
    //   1030: aload 16
    //   1032: ifnull +372 -> 1404
    //   1035: getstatic 129	com/google/android/mms/pdu/PduPersister:$assertionsDisabled	Z
    //   1038: ifne +260 -> 1298
    //   1041: getstatic 267	com/google/android/mms/pdu/PduPersister:PDU_CACHE_INSTANCE	Lcom/google/android/mms/util/PduCache;
    //   1044: aload_1
    //   1045: invokevirtual 828	com/google/android/mms/util/PduCache:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   1048: ifnull +250 -> 1298
    //   1051: new 836	java/lang/AssertionError
    //   1054: dup
    //   1055: invokespecial 837	java/lang/AssertionError:<init>	()V
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
    //   1072: new 915	com/google/android/mms/pdu/DeliveryInd
    //   1075: dup
    //   1076: aload 16
    //   1078: invokespecial 916	com/google/android/mms/pdu/DeliveryInd:<init>	(Lcom/google/android/mms/pdu/PduHeaders;)V
    //   1081: astore 16
    //   1083: goto -61 -> 1022
    //   1086: iload 4
    //   1088: istore_2
    //   1089: lload 11
    //   1091: lstore 7
    //   1093: new 918	com/google/android/mms/pdu/ReadOrigInd
    //   1096: dup
    //   1097: aload 16
    //   1099: invokespecial 919	com/google/android/mms/pdu/ReadOrigInd:<init>	(Lcom/google/android/mms/pdu/PduHeaders;)V
    //   1102: astore 16
    //   1104: goto -82 -> 1022
    //   1107: iload 4
    //   1109: istore_2
    //   1110: lload 11
    //   1112: lstore 7
    //   1114: new 921	com/google/android/mms/pdu/RetrieveConf
    //   1117: dup
    //   1118: aload 16
    //   1120: aload 17
    //   1122: invokespecial 924	com/google/android/mms/pdu/RetrieveConf:<init>	(Lcom/google/android/mms/pdu/PduHeaders;Lcom/google/android/mms/pdu/PduBody;)V
    //   1125: astore 16
    //   1127: goto -105 -> 1022
    //   1130: iload 4
    //   1132: istore_2
    //   1133: lload 11
    //   1135: lstore 7
    //   1137: new 926	com/google/android/mms/pdu/SendReq
    //   1140: dup
    //   1141: aload 16
    //   1143: aload 17
    //   1145: invokespecial 927	com/google/android/mms/pdu/SendReq:<init>	(Lcom/google/android/mms/pdu/PduHeaders;Lcom/google/android/mms/pdu/PduBody;)V
    //   1148: astore 16
    //   1150: goto -128 -> 1022
    //   1153: iload 4
    //   1155: istore_2
    //   1156: lload 11
    //   1158: lstore 7
    //   1160: new 929	com/google/android/mms/pdu/AcknowledgeInd
    //   1163: dup
    //   1164: aload 16
    //   1166: invokespecial 930	com/google/android/mms/pdu/AcknowledgeInd:<init>	(Lcom/google/android/mms/pdu/PduHeaders;)V
    //   1169: astore 16
    //   1171: goto -149 -> 1022
    //   1174: iload 4
    //   1176: istore_2
    //   1177: lload 11
    //   1179: lstore 7
    //   1181: new 932	com/google/android/mms/pdu/NotifyRespInd
    //   1184: dup
    //   1185: aload 16
    //   1187: invokespecial 933	com/google/android/mms/pdu/NotifyRespInd:<init>	(Lcom/google/android/mms/pdu/PduHeaders;)V
    //   1190: astore 16
    //   1192: goto -170 -> 1022
    //   1195: iload 4
    //   1197: istore_2
    //   1198: lload 11
    //   1200: lstore 7
    //   1202: new 935	com/google/android/mms/pdu/ReadRecInd
    //   1205: dup
    //   1206: aload 16
    //   1208: invokespecial 936	com/google/android/mms/pdu/ReadRecInd:<init>	(Lcom/google/android/mms/pdu/PduHeaders;)V
    //   1211: astore 16
    //   1213: goto -191 -> 1022
    //   1216: iload 4
    //   1218: istore_2
    //   1219: lload 11
    //   1221: lstore 7
    //   1223: new 477	com/google/android/mms/MmsException
    //   1226: dup
    //   1227: new 414	java/lang/StringBuilder
    //   1230: dup
    //   1231: invokespecial 415	java/lang/StringBuilder:<init>	()V
    //   1234: ldc_w 938
    //   1237: invokevirtual 421	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1240: iload 5
    //   1242: invokestatic 908	java/lang/Integer:toHexString	(I)Ljava/lang/String;
    //   1245: invokevirtual 421	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1248: invokevirtual 427	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1251: invokespecial 560	com/google/android/mms/MmsException:<init>	(Ljava/lang/String;)V
    //   1254: athrow
    //   1255: new 830	com/google/android/mms/util/PduCacheEntry
    //   1258: dup
    //   1259: aconst_null
    //   1260: iload_2
    //   1261: lload 7
    //   1263: invokespecial 941	com/google/android/mms/util/PduCacheEntry:<init>	(Lcom/google/android/mms/pdu/GenericPdu;IJ)V
    //   1266: astore 18
    //   1268: getstatic 267	com/google/android/mms/pdu/PduPersister:PDU_CACHE_INSTANCE	Lcom/google/android/mms/util/PduCache;
    //   1271: aload_1
    //   1272: aload 18
    //   1274: invokevirtual 944	com/google/android/mms/util/PduCache:put	(Landroid/net/Uri;Lcom/google/android/mms/util/PduCacheEntry;)Z
    //   1277: pop
    //   1278: getstatic 267	com/google/android/mms/pdu/PduPersister:PDU_CACHE_INSTANCE	Lcom/google/android/mms/util/PduCache;
    //   1281: aload_1
    //   1282: iconst_0
    //   1283: invokevirtual 843	com/google/android/mms/util/PduCache:setUpdating	(Landroid/net/Uri;Z)V
    //   1286: getstatic 267	com/google/android/mms/pdu/PduPersister:PDU_CACHE_INSTANCE	Lcom/google/android/mms/util/PduCache;
    //   1289: invokevirtual 947	java/lang/Object:notifyAll	()V
    //   1292: aload 17
    //   1294: monitorexit
    //   1295: aload 16
    //   1297: athrow
    //   1298: new 830	com/google/android/mms/util/PduCacheEntry
    //   1301: dup
    //   1302: aload 16
    //   1304: iload 4
    //   1306: lload 11
    //   1308: invokespecial 941	com/google/android/mms/util/PduCacheEntry:<init>	(Lcom/google/android/mms/pdu/GenericPdu;IJ)V
    //   1311: astore 18
    //   1313: getstatic 267	com/google/android/mms/pdu/PduPersister:PDU_CACHE_INSTANCE	Lcom/google/android/mms/util/PduCache;
    //   1316: aload_1
    //   1317: aload 18
    //   1319: invokevirtual 944	com/google/android/mms/util/PduCache:put	(Landroid/net/Uri;Lcom/google/android/mms/util/PduCacheEntry;)Z
    //   1322: pop
    //   1323: getstatic 267	com/google/android/mms/pdu/PduPersister:PDU_CACHE_INSTANCE	Lcom/google/android/mms/util/PduCache;
    //   1326: aload_1
    //   1327: iconst_0
    //   1328: invokevirtual 843	com/google/android/mms/util/PduCache:setUpdating	(Landroid/net/Uri;Z)V
    //   1331: getstatic 267	com/google/android/mms/pdu/PduPersister:PDU_CACHE_INSTANCE	Lcom/google/android/mms/util/PduCache;
    //   1334: invokevirtual 947	java/lang/Object:notifyAll	()V
    //   1337: aload 17
    //   1339: monitorexit
    //   1340: aload 16
    //   1342: areturn
    //   1343: new 830	com/google/android/mms/util/PduCacheEntry
    //   1346: dup
    //   1347: aconst_null
    //   1348: iconst_0
    //   1349: ldc2_w 819
    //   1352: invokespecial 941	com/google/android/mms/util/PduCacheEntry:<init>	(Lcom/google/android/mms/pdu/GenericPdu;IJ)V
    //   1355: astore 18
    //   1357: getstatic 267	com/google/android/mms/pdu/PduPersister:PDU_CACHE_INSTANCE	Lcom/google/android/mms/util/PduCache;
    //   1360: aload_1
    //   1361: aload 18
    //   1363: invokevirtual 944	com/google/android/mms/util/PduCache:put	(Landroid/net/Uri;Lcom/google/android/mms/util/PduCacheEntry;)Z
    //   1366: pop
    //   1367: getstatic 267	com/google/android/mms/pdu/PduPersister:PDU_CACHE_INSTANCE	Lcom/google/android/mms/util/PduCache;
    //   1370: aload_1
    //   1371: iconst_0
    //   1372: invokevirtual 843	com/google/android/mms/util/PduCache:setUpdating	(Landroid/net/Uri;Z)V
    //   1375: getstatic 267	com/google/android/mms/pdu/PduPersister:PDU_CACHE_INSTANCE	Lcom/google/android/mms/util/PduCache;
    //   1378: invokevirtual 947	java/lang/Object:notifyAll	()V
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
    //   0	1535	0	this	PduPersister
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
  
  public Uri persist(GenericPdu paramGenericPdu, Uri paramUri, boolean paramBoolean1, boolean paramBoolean2, HashMap<Uri, InputStream> paramHashMap)
    throws MmsException
  {
    return persist(paramGenericPdu, paramUri, paramBoolean1, paramBoolean2, paramHashMap, 0);
  }
  
  public Uri persist(GenericPdu paramGenericPdu, Uri paramUri, boolean paramBoolean1, boolean paramBoolean2, HashMap<Uri, InputStream> paramHashMap, int paramInt)
    throws MmsException
  {
    if (paramUri == null) {
      throw new MmsException("Uri may not be null.");
    }
    long l1 = -1L;
    try
    {
      l2 = ContentUris.parseId(paramUri);
      l1 = l2;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      long l2;
      int j;
      PduHeaders localPduHeaders;
      Object localObject2;
      int i;
      Object localObject3;
      Object localObject4;
      int k;
      int m;
      for (;;) {}
    }
    if (l1 != -1L) {}
    for (j = 1; (j == 0) && (MESSAGE_BOX_MAP.get(paramUri) == null); j = 0) {
      throw new MmsException("Bad destination, must be one of content://mms/inbox, content://mms/sent, content://mms/drafts, content://mms/outbox, content://mms/temp.");
    }
    synchronized (PDU_CACHE_INSTANCE)
    {
      boolean bool = PDU_CACHE_INSTANCE.isUpdating(paramUri);
      if (bool) {}
      try
      {
        PDU_CACHE_INSTANCE.wait();
        PDU_CACHE_INSTANCE.purge(paramUri);
        localPduHeaders = paramGenericPdu.getPduHeaders();
        ContentValues localContentValues = new ContentValues();
        ??? = ENCODED_STRING_COLUMN_NAME_MAP.entrySet().iterator();
        while (((Iterator)???).hasNext())
        {
          localObject2 = (Map.Entry)((Iterator)???).next();
          i = ((Integer)((Map.Entry)localObject2).getKey()).intValue();
          localObject3 = localPduHeaders.getEncodedStringValue(i);
          if (localObject3 != null)
          {
            localObject4 = (String)CHARSET_COLUMN_NAME_MAP.get(Integer.valueOf(i));
            localContentValues.put((String)((Map.Entry)localObject2).getValue(), toIsoString(((EncodedStringValue)localObject3).getTextString()));
            localContentValues.put((String)localObject4, Integer.valueOf(((EncodedStringValue)localObject3).getCharacterSet()));
          }
        }
      }
      catch (InterruptedException localInterruptedException)
      {
        for (;;)
        {
          Log.e("PduPersister", "persist1: ", localInterruptedException);
        }
      }
    }
    ??? = TEXT_STRING_COLUMN_NAME_MAP.entrySet().iterator();
    while (((Iterator)???).hasNext())
    {
      localObject2 = (Map.Entry)((Iterator)???).next();
      localObject3 = localPduHeaders.getTextString(((Integer)((Map.Entry)localObject2).getKey()).intValue());
      if (localObject3 != null) {
        localInterruptedException.put((String)((Map.Entry)localObject2).getValue(), toIsoString((byte[])localObject3));
      }
    }
    ??? = OCTET_COLUMN_NAME_MAP.entrySet().iterator();
    while (((Iterator)???).hasNext())
    {
      localObject2 = (Map.Entry)((Iterator)???).next();
      i = localPduHeaders.getOctet(((Integer)((Map.Entry)localObject2).getKey()).intValue());
      if (i != 0) {
        localInterruptedException.put((String)((Map.Entry)localObject2).getValue(), Integer.valueOf(i));
      }
    }
    ??? = LONG_COLUMN_NAME_MAP.entrySet().iterator();
    while (((Iterator)???).hasNext())
    {
      localObject2 = (Map.Entry)((Iterator)???).next();
      l2 = localPduHeaders.getLongInteger(((Integer)((Map.Entry)localObject2).getKey()).intValue());
      if (l2 != -1L) {
        localInterruptedException.put((String)((Map.Entry)localObject2).getValue(), Long.valueOf(l2));
      }
    }
    localObject2 = new HashMap(ADDRESS_FIELDS.length);
    localObject3 = ADDRESS_FIELDS;
    k = localObject3.length;
    i = 0;
    if (i < k)
    {
      m = localObject3[i];
      ??? = null;
      if (m == 137)
      {
        localObject4 = localPduHeaders.getEncodedStringValue(m);
        if (localObject4 != null)
        {
          ??? = new EncodedStringValue[1];
          ???[0] = localObject4;
        }
      }
      for (;;)
      {
        ((HashMap)localObject2).put(Integer.valueOf(m), ???);
        i += 1;
        break;
        ??? = localPduHeaders.getEncodedStringValues(m);
      }
    }
    ??? = new HashSet();
    i = paramGenericPdu.getMessageType();
    if ((i == 130) || (i == 132) || (i == 128)) {
      switch (i)
      {
      }
    }
    for (;;)
    {
      long l3 = 0L;
      l2 = l3;
      if (paramBoolean1)
      {
        l2 = l3;
        if (!((HashSet)???).isEmpty()) {
          l2 = Telephony.Threads.getOrCreateThreadId(mContext, (Set)???);
        }
      }
      localInterruptedException.put("thread_id", Long.valueOf(l2));
      l2 = System.currentTimeMillis();
      k = 1;
      i = 1;
      m = k;
      if (!(paramGenericPdu instanceof MultimediaMessagePdu)) {
        break;
      }
      paramGenericPdu = ((MultimediaMessagePdu)paramGenericPdu).getBody();
      m = k;
      if (paramGenericPdu == null) {
        break;
      }
      int n = paramGenericPdu.getPartsNum();
      if (n > 2) {
        i = 0;
      }
      k = 0;
      for (;;)
      {
        m = i;
        if (k >= n) {
          break;
        }
        ??? = paramGenericPdu.getPart(k);
        persistPart((PduPart)???, l2, paramHashMap);
        ??? = getPartContentType((PduPart)???);
        m = i;
        if (??? != null)
        {
          m = i;
          if (!"application/smil".equals(???))
          {
            m = i;
            if (!"text/plain".equals(???)) {
              m = 0;
            }
          }
        }
        k += 1;
        i = m;
      }
      loadRecipients(137, (HashSet)???, (HashMap)localObject2, false);
      if (paramBoolean2)
      {
        loadRecipients(151, (HashSet)???, (HashMap)localObject2, true);
        continue;
        loadRecipients(151, (HashSet)???, (HashMap)localObject2, false);
      }
    }
    if (m != 0)
    {
      i = 1;
      localInterruptedException.put("text_only", Integer.valueOf(i));
      localInterruptedException.put("sub_id", Integer.valueOf(paramInt));
      if (j == 0) {
        break label1183;
      }
      paramGenericPdu = paramUri;
      SqliteWrapper.update(mContext, mContentResolver, paramGenericPdu, localInterruptedException, null, null);
    }
    for (;;)
    {
      paramHashMap = new ContentValues(1);
      paramHashMap.put("mid", Long.valueOf(l1));
      SqliteWrapper.update(mContext, mContentResolver, Uri.parse("content://mms/" + l2 + "/part"), paramHashMap, null, null);
      if (j == 0) {
        paramGenericPdu = Uri.parse(paramUri + "/" + l1);
      }
      paramUri = ADDRESS_FIELDS;
      i = paramUri.length;
      paramInt = 0;
      while (paramInt < i)
      {
        j = paramUri[paramInt];
        paramHashMap = (EncodedStringValue[])((HashMap)localObject2).get(Integer.valueOf(j));
        if (paramHashMap != null) {
          persistAddress(l1, j, paramHashMap);
        }
        paramInt += 1;
      }
      i = 0;
      break;
      label1183:
      paramGenericPdu = SqliteWrapper.insert(mContext, mContentResolver, paramUri, localInterruptedException);
      if (paramGenericPdu == null) {
        throw new MmsException("persist() failed: return null.");
      }
      l1 = ContentUris.parseId(paramGenericPdu);
    }
    return paramGenericPdu;
  }
  
  public Uri persistPart(PduPart paramPduPart, long paramLong, HashMap<Uri, InputStream> paramHashMap)
    throws MmsException
  {
    Uri localUri = Uri.parse("content://mms/" + paramLong + "/part");
    ContentValues localContentValues = new ContentValues(8);
    int i = paramPduPart.getCharset();
    if (i != 0) {
      localContentValues.put("chset", Integer.valueOf(i));
    }
    Object localObject1 = getPartContentType(paramPduPart);
    Object localObject2;
    if (localObject1 != null)
    {
      localObject2 = localObject1;
      if ("text/x-vCard".equalsIgnoreCase((String)localObject1))
      {
        Log.d("PduPersister", (String)localObject1 + " -> " + "text/x-vCard");
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
          Log.d("PduPersister", "cl = " + str);
          localObject1 = localObject2;
          if (str != null) {
            if (!str.endsWith(".vcf"))
            {
              localObject1 = localObject2;
              if (!str.endsWith(".VCF")) {}
            }
            else
            {
              Log.e("PduPersister", (String)localObject2 + " -> " + "text/x-vCard");
              localObject1 = "text/x-vCard";
            }
          }
          localObject2 = localObject1;
          if (str != null) {
            if (!str.endsWith(".ogg"))
            {
              localObject2 = localObject1;
              if (!str.endsWith(".OGG")) {}
            }
            else
            {
              Log.d("PduPersister", (String)localObject1 + " -> " + "application/ogg");
              localObject2 = "application/ogg";
            }
          }
          localObject1 = localObject2;
          if (str != null) {
            if (!str.endsWith(".mp3"))
            {
              localObject1 = localObject2;
              if (!str.endsWith(".MP3")) {}
            }
            else
            {
              Log.d("PduPersister", (String)localObject2 + " -> " + "application/ogg");
              localObject1 = "audio/mp3";
            }
          }
        }
      }
      if ((paramPduPart.getContentLocation() != null) && ("text/plain".equalsIgnoreCase((String)localObject1)))
      {
        localObject2 = new String(paramPduPart.getContentLocation());
        if ((localObject2 != null) && ((((String)localObject2).endsWith(".txt")) || (((String)localObject2).endsWith(".TXT"))) && (paramPduPart.getCharset() == 0))
        {
          Log.i("PduPersister", "Set default text charset to UTF_8");
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
    persistData(paramPduPart, (Uri)localObject1, (String)localObject2, paramHashMap);
    paramPduPart.setDataUri((Uri)localObject1);
    return (Uri)localObject1;
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
          Log.e("PduPersister", "updateHeaders: ", localInterruptedException);
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
  
  public void updateParts(Uri paramUri, PduBody arg2, HashMap<Uri, InputStream> paramHashMap)
    throws MmsException
  {
    try
    {
      for (;;)
      {
        StringBuilder localStringBuilder;
        Object localObject3;
        Object localObject4;
        synchronized (PDU_CACHE_INSTANCE)
        {
          boolean bool = PDU_CACHE_INSTANCE.isUpdating(paramUri);
          if (bool) {}
          try
          {
            PDU_CACHE_INSTANCE.wait();
            Object localObject2 = (PduCacheEntry)PDU_CACHE_INSTANCE.get(paramUri);
            if (localObject2 != null) {
              ((MultimediaMessagePdu)((PduCacheEntry)localObject2).getPdu()).setBody(???);
            }
            PDU_CACHE_INSTANCE.setUpdating(paramUri, true);
            localObject2 = new ArrayList();
            ??? = new HashMap();
            int j = ???.getPartsNum();
            localStringBuilder = new StringBuilder().append('(');
            int i = 0;
            if (i < j)
            {
              localObject3 = ???.getPart(i);
              localObject4 = ((PduPart)localObject3).getDataUri();
              if ((localObject4 == null) || (TextUtils.isEmpty(((Uri)localObject4).getAuthority())) || (!((Uri)localObject4).getAuthority().startsWith("mms")))
              {
                ((ArrayList)localObject2).add(localObject3);
                i += 1;
                continue;
              }
            }
          }
          catch (InterruptedException localInterruptedException)
          {
            Log.e("PduPersister", "updateParts: ", localInterruptedException);
            continue;
          }
        }
        synchronized (PDU_CACHE_INSTANCE)
        {
          PDU_CACHE_INSTANCE.setUpdating(paramUri, false);
          PDU_CACHE_INSTANCE.notifyAll();
          throw paramHashMap;
          ((HashMap)???).put(localObject4, localObject3);
          if (localStringBuilder.length() > 1) {
            localStringBuilder.append(" AND ");
          }
          localStringBuilder.append("_id");
          localStringBuilder.append("!=");
          DatabaseUtils.appendEscapedSQLString(localStringBuilder, ((Uri)localObject4).getLastPathSegment());
          continue;
          localStringBuilder.append(')');
          long l = ContentUris.parseId(paramUri);
          localObject3 = mContext;
          localObject4 = mContentResolver;
          Uri localUri = Uri.parse(Telephony.Mms.CONTENT_URI + "/" + l + "/part");
          if (localStringBuilder.length() > 2)
          {
            ??? = localStringBuilder.toString();
            SqliteWrapper.delete((Context)localObject3, (ContentResolver)localObject4, localUri, ???, null);
            ??? = localInterruptedException.iterator();
            while (???.hasNext()) {
              persistPart((PduPart)???.next(), l, paramHashMap);
            }
            ??? = ((HashMap)???).entrySet().iterator();
            if (???.hasNext())
            {
              ??? = (Map.Entry)???.next();
              updatePart((Uri)((Map.Entry)???).getKey(), (PduPart)((Map.Entry)???).getValue(), paramHashMap);
            }
          }
        }
      }
    }
    finally {}
  }
}

/* Location:
 * Qualified Name:     com.google.android.mms.pdu.PduPersister
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */