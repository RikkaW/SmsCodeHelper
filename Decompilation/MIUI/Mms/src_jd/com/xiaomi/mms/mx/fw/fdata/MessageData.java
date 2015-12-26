package com.xiaomi.mms.mx.fw.fdata;

public class MessageData
{
  public String mBody;
  public String mExt;
  public String mFrom;
  public String mFromDeviceId;
  public String mFseq;
  public boolean mIsGlobal = false;
  public int mIsRead;
  public boolean mIsTemplate = false;
  public boolean mIsTransient = false;
  public String mPacketID;
  public long mReceivedTime;
  public long mSentTime;
  public String mSeq;
  public String mTo;
  private String mType;
  
  public MessageData(String paramString1, int paramInt, long paramLong1, long paramLong2, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7, String paramString8, String paramString9, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
  {
    mPacketID = paramString1;
    mIsRead = paramInt;
    mSentTime = paramLong1;
    mReceivedTime = paramLong2;
    mFrom = paramString2;
    mType = paramString3;
    mExt = paramString4;
    mSeq = paramString5;
    mFseq = paramString6;
    mFromDeviceId = paramString7;
    mTo = paramString8;
    mBody = paramString9;
    mIsTransient = paramBoolean1;
    mIsGlobal = paramBoolean2;
    mIsTemplate = paramBoolean3;
  }
  
  public int getType()
  {
    if (mType.equalsIgnoreCase("subscribe")) {
      return 1;
    }
    return 0;
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.mms.mx.fw.fdata.MessageData
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */