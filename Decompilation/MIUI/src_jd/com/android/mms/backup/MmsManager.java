package com.android.mms.backup;

import android.content.ContentProviderOperation;
import android.content.ContentProviderOperation.Builder;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.net.Uri.Builder;
import android.provider.Telephony.Mms;
import com.google.protobuf.ByteString;
import java.io.File;
import java.util.HashMap;
import java.util.Vector;

public class MmsManager
{
  private static final String[] PDU_ADDR_COLUMNS = { "address", "type", "charset" };
  private static final String[] PDU_COLUMNS = { "_id", "date", "date_sent", "msg_box", "read", "m_id", "sub", "sub_cs", "ct_t", "ct_l", "exp", "m_cls", "m_type", "v", "m_size", "pri", "rr", "rpt_a", "resp_st", "st", "tr_id", "retr_st", "retr_txt", "retr_txt_cs", "read_status", "ct_cls", "resp_txt", "d_tm", "d_rpt", "locked", "seen", "date_ms_part", "mx_id", "mx_status" };
  private static final String[] PDU_PART_COLUMNS = { "_id", "seq", "ct", "name", "chset", "cd", "fn", "cid", "cl", "ctt_s", "ctt_t", "text" };
  private Uri CONTENT_URI = Telephony.Mms.CONTENT_URI;
  private Uri INSERT_URI = CONTENT_URI.buildUpon().appendQueryParameter("need_full_insert_uri", "1").appendQueryParameter("check_duplication", "1").build();
  private HashMap<String, Uri> mAttach2Uri;
  private ContentResolver mResolver;
  
  public MmsManager(Context paramContext)
  {
    mResolver = paramContext.getContentResolver();
  }
  
  private MmsProtos.PduAddr buildPduAddr(Cursor paramCursor)
  {
    MmsProtos.PduAddr.Builder localBuilder = MmsProtos.PduAddr.newBuilder();
    if (!paramCursor.isNull(0)) {
      localBuilder.setAddress(paramCursor.getString(0));
    }
    if (!paramCursor.isNull(1)) {
      localBuilder.setType(paramCursor.getInt(1));
    }
    if (!paramCursor.isNull(2)) {
      localBuilder.setCharset(paramCursor.getInt(2));
    }
    return localBuilder.build();
  }
  
  private MmsProtos.PduPart buildPduPart(Cursor paramCursor)
  {
    MmsProtos.PduPart.Builder localBuilder = MmsProtos.PduPart.newBuilder();
    long l = paramCursor.getLong(0);
    if (!paramCursor.isNull(1)) {
      localBuilder.setSequence(paramCursor.getInt(1));
    }
    if (!paramCursor.isNull(2)) {
      localBuilder.setContentType(paramCursor.getString(2));
    }
    if (!paramCursor.isNull(3)) {
      localBuilder.setName(paramCursor.getString(3));
    }
    if (!paramCursor.isNull(4)) {
      localBuilder.setCharset(paramCursor.getInt(4));
    }
    if (!paramCursor.isNull(5)) {
      localBuilder.setContentDisposition(paramCursor.getString(5));
    }
    if (!paramCursor.isNull(6)) {
      localBuilder.setFileName(paramCursor.getString(6));
    }
    if (!paramCursor.isNull(7)) {
      localBuilder.setContentId(paramCursor.getString(7));
    }
    if (!paramCursor.isNull(8)) {
      localBuilder.setContentLocation(paramCursor.getString(8));
    }
    if (!paramCursor.isNull(9)) {
      localBuilder.setContentTypeStart(paramCursor.getInt(9));
    }
    if (!paramCursor.isNull(10)) {
      localBuilder.setContentTypeType(paramCursor.getString(10));
    }
    if (!paramCursor.isNull(11)) {
      localBuilder.setText(paramCursor.getString(11));
    }
    paramCursor = Uri.withAppendedPath(Telephony.Mms.CONTENT_URI, "part/" + l);
    String str = Uri.encode(paramCursor.toString());
    mAttach2Uri.put(str, paramCursor);
    localBuilder.setData(ByteString.copyFrom(str.getBytes()));
    return localBuilder.build();
  }
  
  private String getAddresses(MmsProtos.Pdu paramPdu)
  {
    int j = 0;
    int i = j;
    switch (paramPdu.getMsgType())
    {
    default: 
      i = j;
    }
    StringBuilder localStringBuilder;
    for (;;)
    {
      localStringBuilder = new StringBuilder();
      j = 0;
      while (j < paramPdu.getAddrsCount())
      {
        MmsProtos.PduAddr localPduAddr = paramPdu.getAddrs(j);
        if ((localPduAddr.hasAddress()) && (localPduAddr.hasType()) && (localPduAddr.getType() == i))
        {
          if (localStringBuilder.length() > 0) {
            localStringBuilder.append(',');
          }
          localStringBuilder.append(localPduAddr.getAddress());
        }
        j += 1;
      }
      i = 137;
      continue;
      i = 151;
    }
    return localStringBuilder.toString();
  }
  
  private void setBasicPduFields(MmsProtos.Pdu.Builder paramBuilder, Cursor paramCursor)
  {
    boolean bool2 = true;
    if (!paramCursor.isNull(0)) {
      paramBuilder.setLuid(paramCursor.getString(0));
    }
    if (!paramCursor.isNull(1)) {
      paramBuilder.setDate(paramCursor.getLong(1));
    }
    if (!paramCursor.isNull(2)) {
      paramBuilder.setServerDate(paramCursor.getLong(2));
    }
    if (!paramCursor.isNull(3)) {
      paramBuilder.setMsgBox(paramCursor.getInt(3));
    }
    if (!paramCursor.isNull(4))
    {
      if (paramCursor.getInt(4) > 0)
      {
        bool1 = true;
        paramBuilder.setRead(bool1);
      }
    }
    else
    {
      if (!paramCursor.isNull(5)) {
        paramBuilder.setMId(paramCursor.getString(5));
      }
      if (!paramCursor.isNull(6)) {
        paramBuilder.setSubject(paramCursor.getString(6));
      }
      if (!paramCursor.isNull(7)) {
        paramBuilder.setSubjectCharset(paramCursor.getInt(7));
      }
      if (!paramCursor.isNull(8)) {
        paramBuilder.setContentType(paramCursor.getString(8));
      }
      if (!paramCursor.isNull(9)) {
        paramBuilder.setContentLocation(paramCursor.getString(9));
      }
      if (!paramCursor.isNull(10)) {
        paramBuilder.setExpiry(paramCursor.getLong(10));
      }
      if (!paramCursor.isNull(11)) {
        paramBuilder.setMsgClass(paramCursor.getString(11));
      }
      if (!paramCursor.isNull(12)) {
        paramBuilder.setMsgType(paramCursor.getInt(12));
      }
      if (!paramCursor.isNull(13)) {
        paramBuilder.setMmsVersion(paramCursor.getInt(13));
      }
      if (!paramCursor.isNull(14)) {
        paramBuilder.setMsgSize(paramCursor.getInt(14));
      }
      if (!paramCursor.isNull(15)) {
        paramBuilder.setPriority(paramCursor.getInt(15));
      }
      if (!paramCursor.isNull(16)) {
        paramBuilder.setReadReport(paramCursor.getInt(16));
      }
      if (!paramCursor.isNull(17))
      {
        if (paramCursor.getInt(17) <= 0) {
          break label838;
        }
        bool1 = true;
        label429:
        paramBuilder.setReportAllowed(bool1);
      }
      if (!paramCursor.isNull(18)) {
        paramBuilder.setResponseStatus(paramCursor.getInt(18));
      }
      if (!paramCursor.isNull(19)) {
        paramBuilder.setStatus(paramCursor.getInt(19));
      }
      if (!paramCursor.isNull(20)) {
        paramBuilder.setTransactionId(paramCursor.getString(20));
      }
      if (!paramCursor.isNull(21)) {
        paramBuilder.setRetrieveStatus(paramCursor.getInt(21));
      }
      if (!paramCursor.isNull(22)) {
        paramBuilder.setRetrieveText(paramCursor.getString(22));
      }
      if (!paramCursor.isNull(23)) {
        paramBuilder.setRetrieveTextCharset(paramCursor.getInt(23));
      }
      if (!paramCursor.isNull(24)) {
        paramBuilder.setReadStatus(paramCursor.getInt(24));
      }
      if (!paramCursor.isNull(25)) {
        paramBuilder.setContentClass(paramCursor.getInt(25));
      }
      if (!paramCursor.isNull(26)) {
        paramBuilder.setResponseText(paramCursor.getString(26));
      }
      if (!paramCursor.isNull(27)) {
        paramBuilder.setDeliveryTime(paramCursor.getLong(27));
      }
      if (!paramCursor.isNull(28)) {
        paramBuilder.setDeliveryReport(paramCursor.getInt(28));
      }
      if (!paramCursor.isNull(29))
      {
        if (paramCursor.getInt(29) <= 0) {
          break label843;
        }
        bool1 = true;
        label723:
        paramBuilder.setLocked(bool1);
      }
      if (!paramCursor.isNull(30)) {
        if (paramCursor.getInt(30) <= 0) {
          break label848;
        }
      }
    }
    label838:
    label843:
    label848:
    for (boolean bool1 = bool2;; bool1 = false)
    {
      paramBuilder.setSeen(bool1);
      if (!paramCursor.isNull(31)) {
        paramBuilder.setDateMsPart(paramCursor.getInt(31));
      }
      if (!paramCursor.isNull(32)) {
        paramBuilder.setMxId(paramCursor.getLong(32));
      }
      if (!paramCursor.isNull(33)) {
        paramBuilder.setMxStatus(paramCursor.getInt(33));
      }
      return;
      bool1 = false;
      break;
      bool1 = false;
      break label429;
      bool1 = false;
      break label723;
    }
  }
  
  public MmsProtos.Pdu backupToProtocolBuffer(long paramLong)
  {
    MmsProtos.Pdu.Builder localBuilder = MmsProtos.Pdu.newBuilder();
    Cursor localCursor1 = null;
    Object localObject1 = localCursor1;
    Uri localUri;
    try
    {
      localUri = Uri.withAppendedPath(Telephony.Mms.CONTENT_URI, String.valueOf(paramLong));
      localObject1 = localCursor1;
      localCursor1 = mResolver.query(localUri, PDU_COLUMNS, null, null, null);
      if (localCursor1 != null)
      {
        localObject1 = localCursor1;
        if (localCursor1.moveToFirst())
        {
          localObject1 = localCursor1;
          setBasicPduFields(localBuilder, localCursor1);
        }
      }
      if (localCursor1 != null) {
        localCursor1.close();
      }
      localObject1 = localCursor1;
      try
      {
        localUri = Uri.withAppendedPath(Telephony.Mms.CONTENT_URI, paramLong + "/addr");
        localObject1 = localCursor1;
        localCursor1 = mResolver.query(localUri, PDU_ADDR_COLUMNS, null, null, null);
        if (localCursor1 != null)
        {
          localObject1 = localCursor1;
          localCursor1.moveToPosition(-1);
          for (;;)
          {
            localObject1 = localCursor1;
            if (!localCursor1.moveToNext()) {
              break;
            }
            localObject1 = localCursor1;
            localBuilder.addAddrs(buildPduAddr(localCursor1));
          }
          localObject3 = finally;
        }
      }
      finally {}
      if (localObject3 == null) {
        break label225;
      }
    }
    finally
    {
      if (localObject1 != null) {
        ((Cursor)localObject1).close();
      }
    }
    ((Cursor)localObject3).close();
    label225:
    localObject1 = localObject3;
    try
    {
      localUri = Uri.withAppendedPath(Telephony.Mms.CONTENT_URI, paramLong + "/part");
      localObject1 = localObject3;
      Cursor localCursor2 = mResolver.query(localUri, PDU_PART_COLUMNS, null, null, null);
      if (localCursor2 != null)
      {
        localObject1 = localCursor2;
        localCursor2.moveToPosition(-1);
        for (;;)
        {
          localObject1 = localCursor2;
          if (!localCursor2.moveToNext()) {
            break;
          }
          localObject1 = localCursor2;
          localBuilder.addPduParts(buildPduPart(localCursor2));
        }
      }
      if (localObject4 == null) {
        break label351;
      }
    }
    finally
    {
      if (localObject1 != null) {
        ((Cursor)localObject1).close();
      }
    }
    ((Cursor)localObject4).close();
    label351:
    return localBuilder.build();
  }
  
  public Vector<String> prepareAllMmsIds()
  {
    localObject3 = new Vector();
    try
    {
      localObject1 = mResolver.query(Telephony.Mms.CONTENT_URI, new String[] { "_id" }, "timed=0 AND mx_status!=1 AND mx_status!=16 AND (msg_box=1 OR msg_box=2)", null, "date ASC");
      if (localObject1 == null)
      {
        if (localObject1 != null) {
          ((Cursor)localObject1).close();
        }
        return (Vector<String>)localObject3;
      }
      try
      {
        int i = ((Cursor)localObject1).getColumnIndexOrThrow("_id");
        ((Cursor)localObject1).moveToFirst();
        while (!((Cursor)localObject1).isAfterLast())
        {
          ((Vector)localObject3).add(String.valueOf(((Cursor)localObject1).getLong(i)));
          ((Cursor)localObject1).moveToNext();
        }
        if (localObject3 == null) {
          break label118;
        }
      }
      finally
      {
        localObject3 = localObject1;
        localObject1 = localObject4;
      }
    }
    finally
    {
      for (;;)
      {
        Object localObject1;
        localObject3 = null;
      }
    }
    ((Cursor)localObject3).close();
    label118:
    throw ((Throwable)localObject1);
    if (localObject1 != null) {
      ((Cursor)localObject1).close();
    }
    return (Vector<String>)localObject3;
  }
  
  public ContentProviderOperation restorePdu(MmsProtos.Pdu paramPdu)
  {
    int j = 1;
    ContentValues localContentValues = new ContentValues();
    if (paramPdu.hasDate()) {
      localContentValues.put("date", Long.valueOf(paramPdu.getDate()));
    }
    if (paramPdu.hasServerDate()) {
      localContentValues.put("date_sent", Long.valueOf(paramPdu.getServerDate()));
    }
    if (paramPdu.hasMsgBox()) {
      localContentValues.put("msg_box", Integer.valueOf(paramPdu.getMsgBox()));
    }
    if (paramPdu.hasRead())
    {
      if (paramPdu.getRead())
      {
        i = 1;
        localContentValues.put("read", Integer.valueOf(i));
      }
    }
    else
    {
      if (paramPdu.hasMId()) {
        localContentValues.put("m_id", paramPdu.getMId());
      }
      if (paramPdu.hasSubject()) {
        localContentValues.put("sub", paramPdu.getSubject());
      }
      if (paramPdu.hasSubjectCharset()) {
        localContentValues.put("sub_cs", Integer.valueOf(paramPdu.getSubjectCharset()));
      }
      if (paramPdu.hasContentType()) {
        localContentValues.put("ct_t", paramPdu.getContentType());
      }
      if (paramPdu.hasContentLocation()) {
        localContentValues.put("ct_l", paramPdu.getContentLocation());
      }
      if (paramPdu.hasExpiry()) {
        localContentValues.put("exp", Long.valueOf(paramPdu.getExpiry()));
      }
      if (paramPdu.hasMsgClass()) {
        localContentValues.put("m_cls", paramPdu.getMsgClass());
      }
      if (paramPdu.hasMsgType()) {
        localContentValues.put("m_type", Integer.valueOf(paramPdu.getMsgType()));
      }
      if (paramPdu.hasMmsVersion()) {
        localContentValues.put("v", Integer.valueOf(paramPdu.getMmsVersion()));
      }
      if (paramPdu.hasMsgSize()) {
        localContentValues.put("m_size", Integer.valueOf(paramPdu.getMsgSize()));
      }
      if (paramPdu.hasPriority()) {
        localContentValues.put("pri", Integer.valueOf(paramPdu.getPriority()));
      }
      if (paramPdu.hasReadReport()) {
        localContentValues.put("rr", Integer.valueOf(paramPdu.getReadReport()));
      }
      if (paramPdu.hasReportAllowed())
      {
        if (!paramPdu.getReportAllowed()) {
          break label747;
        }
        i = 1;
        label354:
        localContentValues.put("rpt_a", Integer.valueOf(i));
      }
      if (paramPdu.hasResponseStatus()) {
        localContentValues.put("resp_st", Integer.valueOf(paramPdu.getResponseStatus()));
      }
      if (paramPdu.hasStatus()) {
        localContentValues.put("st", Integer.valueOf(paramPdu.getStatus()));
      }
      if (paramPdu.hasTransactionId()) {
        localContentValues.put("tr_id", paramPdu.getTransactionId());
      }
      if (paramPdu.hasRetrieveStatus()) {
        localContentValues.put("retr_st", Integer.valueOf(paramPdu.getRetrieveStatus()));
      }
      if (paramPdu.hasRetrieveText()) {
        localContentValues.put("retr_txt", paramPdu.getRetrieveText());
      }
      if (paramPdu.hasRetrieveTextCharset()) {
        localContentValues.put("retr_txt_cs", Integer.valueOf(paramPdu.getRetrieveTextCharset()));
      }
      if (paramPdu.hasReadStatus()) {
        localContentValues.put("read_status", Integer.valueOf(paramPdu.getReadStatus()));
      }
      if (paramPdu.hasContentClass()) {
        localContentValues.put("ct_cls", Integer.valueOf(paramPdu.getContentClass()));
      }
      if (paramPdu.hasResponseText()) {
        localContentValues.put("resp_txt", paramPdu.getResponseText());
      }
      if (paramPdu.hasDeliveryTime()) {
        localContentValues.put("d_tm", Long.valueOf(paramPdu.getDeliveryTime()));
      }
      if (paramPdu.hasDeliveryReport()) {
        localContentValues.put("d_rpt", Integer.valueOf(paramPdu.getDeliveryReport()));
      }
      if (paramPdu.hasLocked())
      {
        if (!paramPdu.getLocked()) {
          break label752;
        }
        i = 1;
        label603:
        localContentValues.put("locked", Integer.valueOf(i));
      }
      if (paramPdu.hasSeen()) {
        if (!paramPdu.getSeen()) {
          break label757;
        }
      }
    }
    label747:
    label752:
    label757:
    for (int i = j;; i = 0)
    {
      localContentValues.put("seen", Integer.valueOf(i));
      if (paramPdu.hasDateMsPart()) {
        localContentValues.put("date_ms_part", Integer.valueOf(paramPdu.getDateMsPart()));
      }
      if (paramPdu.hasMxId()) {
        localContentValues.put("mx_id", Long.valueOf(paramPdu.getMxId()));
      }
      if (paramPdu.hasMxStatus()) {
        localContentValues.put("mx_status", Integer.valueOf(paramPdu.getMxStatus()));
      }
      paramPdu = getAddresses(paramPdu);
      if (paramPdu.length() > 0) {
        localContentValues.put("addresses", paramPdu);
      }
      return ContentProviderOperation.newInsert(INSERT_URI).withValues(localContentValues).build();
      i = 0;
      break;
      i = 0;
      break label354;
      i = 0;
      break label603;
    }
  }
  
  public ContentProviderOperation restorePduPart(long paramLong, MmsProtos.PduPart paramPduPart)
  {
    ContentValues localContentValues = new ContentValues();
    if (paramPduPart.hasSequence()) {
      localContentValues.put("seq", Integer.valueOf(paramPduPart.getSequence()));
    }
    if (paramPduPart.hasContentType()) {
      localContentValues.put("ct", paramPduPart.getContentType());
    }
    if (paramPduPart.hasName()) {
      localContentValues.put("name", paramPduPart.getName());
    }
    if (paramPduPart.hasCharset()) {
      localContentValues.put("chset", Integer.valueOf(paramPduPart.getCharset()));
    }
    if (paramPduPart.hasContentDisposition()) {
      localContentValues.put("cd", paramPduPart.getContentDisposition());
    }
    if (paramPduPart.hasFileName()) {
      localContentValues.put("fn", paramPduPart.getFileName());
    }
    if (paramPduPart.hasContentId()) {
      localContentValues.put("cid", paramPduPart.getContentId());
    }
    if (paramPduPart.hasContentLocation()) {
      localContentValues.put("cl", paramPduPart.getContentLocation());
    }
    if (paramPduPart.hasContentTypeStart()) {
      localContentValues.put("ctt_s", Integer.valueOf(paramPduPart.getContentTypeStart()));
    }
    if (paramPduPart.hasContentTypeType()) {
      localContentValues.put("ctt_t", paramPduPart.getContentTypeType());
    }
    if (paramPduPart.hasText()) {
      localContentValues.put("text", paramPduPart.getText());
    }
    return ContentProviderOperation.newInsert(Telephony.Mms.CONTENT_URI.buildUpon().appendEncodedPath(paramLong + "/part").appendQueryParameter("supress_making_mms_preview", "1").build()).withValues(localContentValues).build();
  }
  
  public void restorePduPartFile(Uri paramUri, MmsProtos.PduPart paramPduPart)
  {
    if (paramPduPart.hasData())
    {
      paramPduPart = new File(new String(paramPduPart.getData().toByteArray())).getName();
      mAttach2Uri.put(paramPduPart, paramUri);
    }
  }
  
  public void setMmsAttach(HashMap<String, Uri> paramHashMap)
  {
    mAttach2Uri = paramHashMap;
  }
}

/* Location:
 * Qualified Name:     com.android.mms.backup.MmsManager
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */