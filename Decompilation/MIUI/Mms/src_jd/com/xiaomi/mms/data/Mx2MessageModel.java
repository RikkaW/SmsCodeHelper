package com.xiaomi.mms.data;

import android.database.Cursor;
import android.text.TextUtils;
import com.android.mms.data.Conversation;
import com.google.android.mms.pdu.EncodedStringValue;
import com.google.android.mms.pdu.MiuiPduPersister;
import com.xiaomi.mms.mx.data.Attachment;
import com.xiaomi.mms.mx.utils.Mx2ExtentionHelper;
import java.util.ArrayList;
import java.util.List;

public class Mx2MessageModel
{
  private List<Attachment> mAttachments;
  private String mBody;
  private int mBoxId;
  public Conversation mConversation;
  private long mDate;
  private long mDateMsPart;
  private long mDateSent;
  private String mExpireTimestamp;
  private boolean mLocked;
  private long mMsgId;
  private String mMxExtension;
  private long mMxId;
  private String mMxType;
  private long mSimId;
  private long mThreadId;
  private int mType;
  
  public Mx2MessageModel() {}
  
  public Mx2MessageModel(Cursor paramCursor)
  {
    if ((paramCursor != null) && (paramCursor.moveToFirst()) && (paramCursor.getCount() == 1))
    {
      mBody = paramCursor.getString(3);
      if (!TextUtils.isEmpty(mBody)) {
        mBody = new EncodedStringValue(MiuiPduPersister.getBytes(mBody)).getString();
      }
      mMsgId = paramCursor.getLong(0);
      mDate = paramCursor.getLong(6);
      mDateMsPart = paramCursor.getLong(7);
      mDateSent = paramCursor.getLong(8);
      mBoxId = paramCursor.getInt(1);
      mThreadId = paramCursor.getLong(2);
      mSimId = paramCursor.getLong(10);
      mType = paramCursor.getInt(5);
      mExpireTimestamp = paramCursor.getString(9);
      if (paramCursor.getInt(11) != 1) {
        break label239;
      }
    }
    for (;;)
    {
      mLocked = bool;
      mMxExtension = paramCursor.getString(13);
      mMxType = paramCursor.getString(12);
      mMxId = paramCursor.getLong(4);
      mAttachments = Mx2ExtentionHelper.praseAttachmentsExtentionString(mMxExtension);
      return;
      label239:
      bool = false;
    }
  }
  
  public boolean addAttachment(Attachment paramAttachment)
  {
    if (mAttachments == null) {
      mAttachments = new ArrayList();
    }
    return mAttachments.add(paramAttachment);
  }
  
  public String buildLocalExtension()
  {
    return Mx2ExtentionHelper.generateAttachmentsExtentionString(mAttachments, false);
  }
  
  public String buildXmppExtension()
  {
    return Mx2ExtentionHelper.generateAttachmentsExtentionString(mAttachments, true);
  }
  
  public Attachment getAttachment()
  {
    return getAttachment(0);
  }
  
  public Attachment getAttachment(int paramInt)
  {
    if ((mAttachments != null) && (mAttachments.size() > paramInt)) {
      return (Attachment)mAttachments.get(paramInt);
    }
    return null;
  }
  
  public List<Attachment> getAttachments()
  {
    return mAttachments;
  }
  
  public String getBody()
  {
    return mBody;
  }
  
  public int getBoxId()
  {
    return mBoxId;
  }
  
  public long getDate()
  {
    return mDate;
  }
  
  public long getDateMsPart()
  {
    return mDateMsPart;
  }
  
  public long getDateSent()
  {
    return mDateSent;
  }
  
  public String getExpireTimestamp()
  {
    return mExpireTimestamp;
  }
  
  public long getMsgId()
  {
    return mMsgId;
  }
  
  public String getMxType()
  {
    return mMxType;
  }
  
  public long getSimId()
  {
    return mSimId;
  }
  
  public long getThreadId()
  {
    return mThreadId;
  }
  
  public int getType()
  {
    return mType;
  }
  
  public boolean hasAttachment()
  {
    return (mAttachments != null) && (mAttachments.size() > 0);
  }
  
  public boolean isLocked()
  {
    return mLocked;
  }
  
  public void setAttachments(List<Attachment> paramList)
  {
    mAttachments = paramList;
  }
  
  public void setBody(String paramString)
  {
    mBody = paramString;
  }
  
  public void setBoxId(int paramInt)
  {
    mBoxId = paramInt;
  }
  
  public void setDate(long paramLong)
  {
    mDate = paramLong;
  }
  
  public void setDateMsPart(long paramLong)
  {
    mDateMsPart = paramLong;
  }
  
  public void setDateSent(long paramLong)
  {
    mDateSent = paramLong;
  }
  
  public void setMxExtension(String paramString)
  {
    mMxExtension = paramString;
  }
  
  public void setMxType(String paramString)
  {
    mMxType = paramString;
  }
  
  public void setSimId(long paramLong)
  {
    mSimId = paramLong;
  }
  
  public void setThreadId(long paramLong)
  {
    mThreadId = paramLong;
  }
  
  public void setType(int paramInt)
  {
    mType = paramInt;
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.data.Mx2MessageModel
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */