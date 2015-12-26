package com.xiaomi.push.protobuf;

import com.google.protobuf.micro.CodedInputStreamMicro;
import com.google.protobuf.micro.CodedOutputStreamMicro;
import com.google.protobuf.micro.InvalidProtocolBufferMicroException;
import com.google.protobuf.micro.MessageMicro;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public final class ChannelConfig$PushServiceConfig
  extends MessageMicro
{
  private int cachedSize = -1;
  private int configVersion_ = 0;
  private int connectTimeoutMs_ = 0;
  private boolean enableDynamicPing_ = false;
  private boolean hasConfigVersion;
  private boolean hasConnectTimeoutMs;
  private boolean hasEnableDynamicPing;
  private boolean hasUseBucketV2;
  private List<String> testHosts_ = Collections.emptyList();
  private boolean useBucketV2_ = false;
  
  public static PushServiceConfig parseFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
    throws IOException
  {
    return new PushServiceConfig().mergeFrom(paramCodedInputStreamMicro);
  }
  
  public static PushServiceConfig parseFrom(byte[] paramArrayOfByte)
    throws InvalidProtocolBufferMicroException
  {
    return (PushServiceConfig)new PushServiceConfig().mergeFrom(paramArrayOfByte);
  }
  
  public PushServiceConfig addTestHosts(String paramString)
  {
    if (paramString == null) {
      throw new NullPointerException();
    }
    if (testHosts_.isEmpty()) {
      testHosts_ = new ArrayList();
    }
    testHosts_.add(paramString);
    return this;
  }
  
  public int getConfigVersion()
  {
    return configVersion_;
  }
  
  public int getConnectTimeoutMs()
  {
    return connectTimeoutMs_;
  }
  
  public boolean getEnableDynamicPing()
  {
    return enableDynamicPing_;
  }
  
  public int getSerializedSize()
  {
    int j = 0;
    if (hasConfigVersion()) {
      j = 0 + CodedOutputStreamMicro.computeUInt32Size(1, getConfigVersion());
    }
    int i = j;
    if (hasUseBucketV2()) {
      i = j + CodedOutputStreamMicro.computeBoolSize(2, getUseBucketV2());
    }
    j = i;
    if (hasConnectTimeoutMs()) {
      j = i + CodedOutputStreamMicro.computeInt32Size(3, getConnectTimeoutMs());
    }
    i = j;
    if (hasEnableDynamicPing()) {
      i = j + CodedOutputStreamMicro.computeBoolSize(4, getEnableDynamicPing());
    }
    j = 0;
    Iterator localIterator = getTestHostsList().iterator();
    while (localIterator.hasNext()) {
      j += CodedOutputStreamMicro.computeStringSizeNoTag((String)localIterator.next());
    }
    i = i + j + getTestHostsList().size() * 1;
    cachedSize = i;
    return i;
  }
  
  public int getTestHostsCount()
  {
    return testHosts_.size();
  }
  
  public List<String> getTestHostsList()
  {
    return testHosts_;
  }
  
  public boolean getUseBucketV2()
  {
    return useBucketV2_;
  }
  
  public boolean hasConfigVersion()
  {
    return hasConfigVersion;
  }
  
  public boolean hasConnectTimeoutMs()
  {
    return hasConnectTimeoutMs;
  }
  
  public boolean hasEnableDynamicPing()
  {
    return hasEnableDynamicPing;
  }
  
  public boolean hasUseBucketV2()
  {
    return hasUseBucketV2;
  }
  
  public PushServiceConfig mergeFrom(CodedInputStreamMicro paramCodedInputStreamMicro)
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
        setConfigVersion(paramCodedInputStreamMicro.readUInt32());
        break;
      case 16: 
        setUseBucketV2(paramCodedInputStreamMicro.readBool());
        break;
      case 24: 
        setConnectTimeoutMs(paramCodedInputStreamMicro.readInt32());
        break;
      case 32: 
        setEnableDynamicPing(paramCodedInputStreamMicro.readBool());
        break;
      case 42: 
        addTestHosts(paramCodedInputStreamMicro.readString());
      }
    }
  }
  
  public PushServiceConfig setConfigVersion(int paramInt)
  {
    hasConfigVersion = true;
    configVersion_ = paramInt;
    return this;
  }
  
  public PushServiceConfig setConnectTimeoutMs(int paramInt)
  {
    hasConnectTimeoutMs = true;
    connectTimeoutMs_ = paramInt;
    return this;
  }
  
  public PushServiceConfig setEnableDynamicPing(boolean paramBoolean)
  {
    hasEnableDynamicPing = true;
    enableDynamicPing_ = paramBoolean;
    return this;
  }
  
  public PushServiceConfig setUseBucketV2(boolean paramBoolean)
  {
    hasUseBucketV2 = true;
    useBucketV2_ = paramBoolean;
    return this;
  }
  
  public void writeTo(CodedOutputStreamMicro paramCodedOutputStreamMicro)
    throws IOException
  {
    if (hasConfigVersion()) {
      paramCodedOutputStreamMicro.writeUInt32(1, getConfigVersion());
    }
    if (hasUseBucketV2()) {
      paramCodedOutputStreamMicro.writeBool(2, getUseBucketV2());
    }
    if (hasConnectTimeoutMs()) {
      paramCodedOutputStreamMicro.writeInt32(3, getConnectTimeoutMs());
    }
    if (hasEnableDynamicPing()) {
      paramCodedOutputStreamMicro.writeBool(4, getEnableDynamicPing());
    }
    Iterator localIterator = getTestHostsList().iterator();
    while (localIterator.hasNext()) {
      paramCodedOutputStreamMicro.writeString(5, (String)localIterator.next());
    }
  }
}

/* Location:
 * Qualified Name:     com.xiaomi.push.protobuf.ChannelConfig.PushServiceConfig
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */