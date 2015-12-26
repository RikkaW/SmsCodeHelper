package com.xiaomi.push.protobuf;

import com.google.protobuf.micro.CodedInputStreamMicro;
import com.google.protobuf.micro.CodedOutputStreamMicro;
import com.google.protobuf.micro.InvalidProtocolBufferMicroException;
import com.google.protobuf.micro.MessageMicro;
import java.io.IOException;

public final class ChannelMessage$PushServiceConfigMsg
  extends MessageMicro
{
  private int cachedSize = -1;
  private int clientVersion_ = 0;
  private int cloudVersion_ = 0;
  private int dots_ = 0;
  private boolean fetchBucket_ = false;
  private boolean hasClientVersion;
  private boolean hasCloudVersion;
  private boolean hasDots;
  private boolean hasFetchBucket;
  
  public static PushServiceConfigMsg parseFrom(byte[] paramArrayOfByte)
    throws InvalidProtocolBufferMicroException
  {
    return (PushServiceConfigMsg)new PushServiceConfigMsg().mergeFrom(paramArrayOfByte);
  }
  
  public int getClientVersion()
  {
    return clientVersion_;
  }
  
  public int getCloudVersion()
  {
    return cloudVersion_;
  }
  
  public int getDots()
  {
    return dots_;
  }
  
  public boolean getFetchBucket()
  {
    return fetchBucket_;
  }
  
  public int getSerializedSize()
  {
    int j = 0;
    if (hasFetchBucket()) {
      j = 0 + CodedOutputStreamMicro.computeBoolSize(1, getFetchBucket());
    }
    int i = j;
    if (hasClientVersion()) {
      i = j + CodedOutputStreamMicro.computeInt32Size(3, getClientVersion());
    }
    j = i;
    if (hasCloudVersion()) {
      j = i + CodedOutputStreamMicro.computeInt32Size(4, getCloudVersion());
    }
    i = j;
    if (hasDots()) {
      i = j + CodedOutputStreamMicro.computeInt32Size(5, getDots());
    }
    cachedSize = i;
    return i;
  }
  
  public boolean hasClientVersion()
  {
    return hasClientVersion;
  }
  
  public boolean hasCloudVersion()
  {
    return hasCloudVersion;
  }
  
  public boolean hasDots()
  {
    return hasDots;
  }
  
  public boolean hasFetchBucket()
  {
    return hasFetchBucket;
  }
  
  public PushServiceConfigMsg mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
    throws IOException
  {
    for (;;)
    {
      int i = paramCodedInputStreamMicro.readTag();
      switch (i)
      {
      default: 
        if (parseUnknownField(paramCodedInputStreamMicro, i)) {}
        break;
      case 0: 
        return this;
      case 8: 
        setFetchBucket(paramCodedInputStreamMicro.readBool());
        break;
      case 24: 
        setClientVersion(paramCodedInputStreamMicro.readInt32());
        break;
      case 32: 
        setCloudVersion(paramCodedInputStreamMicro.readInt32());
        break;
      case 40: 
        setDots(paramCodedInputStreamMicro.readInt32());
      }
    }
  }
  
  public PushServiceConfigMsg setClientVersion(int paramInt)
  {
    hasClientVersion = true;
    clientVersion_ = paramInt;
    return this;
  }
  
  public PushServiceConfigMsg setCloudVersion(int paramInt)
  {
    hasCloudVersion = true;
    cloudVersion_ = paramInt;
    return this;
  }
  
  public PushServiceConfigMsg setDots(int paramInt)
  {
    hasDots = true;
    dots_ = paramInt;
    return this;
  }
  
  public PushServiceConfigMsg setFetchBucket(boolean paramBoolean)
  {
    hasFetchBucket = true;
    fetchBucket_ = paramBoolean;
    return this;
  }
  
  public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
    throws IOException
  {
    if (hasFetchBucket()) {
      paramCodedOutputStreamMicro.writeBool(1, getFetchBucket());
    }
    if (hasClientVersion()) {
      paramCodedOutputStreamMicro.writeInt32(3, getClientVersion());
    }
    if (hasCloudVersion()) {
      paramCodedOutputStreamMicro.writeInt32(4, getCloudVersion());
    }
    if (hasDots()) {
      paramCodedOutputStreamMicro.writeInt32(5, getDots());
    }
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.push.protobuf.ChannelMessage.PushServiceConfigMsg
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */