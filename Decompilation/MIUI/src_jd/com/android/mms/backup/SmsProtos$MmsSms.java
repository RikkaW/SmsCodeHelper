package com.android.mms.backup;

import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.GeneratedMessageLite.Builder;
import com.google.protobuf.MessageLite;
import com.google.protobuf.MessageLite.Builder;
import java.io.IOException;
import java.io.ObjectStreamException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class SmsProtos$MmsSms
  extends GeneratedMessageLite
  implements SmsProtos.MmsSmsOrBuilder
{
  private static final MmsSms defaultInstance = new MmsSms(true);
  private static final long serialVersionUID = 0L;
  private byte memoizedIsInitialized = -1;
  private int memoizedSerializedSize = -1;
  private List<SmsProtos.SmsBookmark> smsBookmark_;
  private List<SmsProtos.Sms> sms_;
  
  static
  {
    defaultInstance.initFields();
  }
  
  private SmsProtos$MmsSms(Builder paramBuilder)
  {
    super(paramBuilder);
  }
  
  private SmsProtos$MmsSms(boolean paramBoolean) {}
  
  public static MmsSms getDefaultInstance()
  {
    return defaultInstance;
  }
  
  private void initFields()
  {
    sms_ = Collections.emptyList();
    smsBookmark_ = Collections.emptyList();
  }
  
  public static Builder newBuilder()
  {
    return Builder.access$3500();
  }
  
  public static Builder newBuilder(MmsSms paramMmsSms)
  {
    return newBuilder().mergeFrom(paramMmsSms);
  }
  
  public MmsSms getDefaultInstanceForType()
  {
    return defaultInstance;
  }
  
  public int getSerializedSize()
  {
    int i = memoizedSerializedSize;
    if (i != -1) {
      return i;
    }
    i = 0;
    int j = 0;
    while (j < sms_.size())
    {
      i += CodedOutputStream.computeMessageSize(1, (MessageLite)sms_.get(j));
      j += 1;
    }
    int k = 0;
    j = i;
    i = k;
    while (i < smsBookmark_.size())
    {
      j += CodedOutputStream.computeMessageSize(2, (MessageLite)smsBookmark_.get(i));
      i += 1;
    }
    memoizedSerializedSize = j;
    return j;
  }
  
  public List<SmsProtos.Sms> getSmsList()
  {
    return sms_;
  }
  
  public final boolean isInitialized()
  {
    int i = memoizedIsInitialized;
    if (i != -1) {
      return i == 1;
    }
    memoizedIsInitialized = 1;
    return true;
  }
  
  public Builder newBuilderForType()
  {
    return newBuilder();
  }
  
  public Builder toBuilder()
  {
    return newBuilder(this);
  }
  
  protected Object writeReplace()
    throws ObjectStreamException
  {
    return super.writeReplace();
  }
  
  public void writeTo(CodedOutputStream paramCodedOutputStream)
    throws IOException
  {
    getSerializedSize();
    int i = 0;
    while (i < sms_.size())
    {
      paramCodedOutputStream.writeMessage(1, (MessageLite)sms_.get(i));
      i += 1;
    }
    i = 0;
    while (i < smsBookmark_.size())
    {
      paramCodedOutputStream.writeMessage(2, (MessageLite)smsBookmark_.get(i));
      i += 1;
    }
  }
  
  public static final class Builder
    extends GeneratedMessageLite.Builder<SmsProtos.MmsSms, Builder>
    implements SmsProtos.MmsSmsOrBuilder
  {
    private int bitField0_;
    private List<SmsProtos.SmsBookmark> smsBookmark_ = Collections.emptyList();
    private List<SmsProtos.Sms> sms_ = Collections.emptyList();
    
    private Builder()
    {
      maybeForceBuilderInitialization();
    }
    
    private static Builder create()
    {
      return new Builder();
    }
    
    private void ensureSmsBookmarkIsMutable()
    {
      if ((bitField0_ & 0x2) != 2)
      {
        smsBookmark_ = new ArrayList(smsBookmark_);
        bitField0_ |= 0x2;
      }
    }
    
    private void ensureSmsIsMutable()
    {
      if ((bitField0_ & 0x1) != 1)
      {
        sms_ = new ArrayList(sms_);
        bitField0_ |= 0x1;
      }
    }
    
    private void maybeForceBuilderInitialization() {}
    
    public Builder addSms(SmsProtos.Sms paramSms)
    {
      if (paramSms == null) {
        throw new NullPointerException();
      }
      ensureSmsIsMutable();
      sms_.add(paramSms);
      return this;
    }
    
    public Builder addSmsBookmark(SmsProtos.SmsBookmark paramSmsBookmark)
    {
      if (paramSmsBookmark == null) {
        throw new NullPointerException();
      }
      ensureSmsBookmarkIsMutable();
      smsBookmark_.add(paramSmsBookmark);
      return this;
    }
    
    public SmsProtos.MmsSms build()
    {
      SmsProtos.MmsSms localMmsSms = buildPartial();
      if (!localMmsSms.isInitialized()) {
        throw newUninitializedMessageException(localMmsSms);
      }
      return localMmsSms;
    }
    
    public SmsProtos.MmsSms buildPartial()
    {
      SmsProtos.MmsSms localMmsSms = new SmsProtos.MmsSms(this, null);
      int i = bitField0_;
      if ((bitField0_ & 0x1) == 1)
      {
        sms_ = Collections.unmodifiableList(sms_);
        bitField0_ &= 0xFFFFFFFE;
      }
      SmsProtos.MmsSms.access$3702(localMmsSms, sms_);
      if ((bitField0_ & 0x2) == 2)
      {
        smsBookmark_ = Collections.unmodifiableList(smsBookmark_);
        bitField0_ &= 0xFFFFFFFD;
      }
      SmsProtos.MmsSms.access$3802(localMmsSms, smsBookmark_);
      return localMmsSms;
    }
    
    public Builder clear()
    {
      super.clear();
      sms_ = Collections.emptyList();
      bitField0_ &= 0xFFFFFFFE;
      smsBookmark_ = Collections.emptyList();
      bitField0_ &= 0xFFFFFFFD;
      return this;
    }
    
    public Builder clone()
    {
      return create().mergeFrom(buildPartial());
    }
    
    public SmsProtos.MmsSms getDefaultInstanceForType()
    {
      return SmsProtos.MmsSms.getDefaultInstance();
    }
    
    public final boolean isInitialized()
    {
      return true;
    }
    
    public Builder mergeFrom(SmsProtos.MmsSms paramMmsSms)
    {
      if (paramMmsSms == SmsProtos.MmsSms.getDefaultInstance()) {}
      for (;;)
      {
        return this;
        if (!sms_.isEmpty())
        {
          if (!sms_.isEmpty()) {
            break label97;
          }
          sms_ = sms_;
          bitField0_ &= 0xFFFFFFFE;
        }
        while (!smsBookmark_.isEmpty())
        {
          if (!smsBookmark_.isEmpty()) {
            break label118;
          }
          smsBookmark_ = smsBookmark_;
          bitField0_ &= 0xFFFFFFFD;
          return this;
          label97:
          ensureSmsIsMutable();
          sms_.addAll(sms_);
        }
      }
      label118:
      ensureSmsBookmarkIsMutable();
      smsBookmark_.addAll(smsBookmark_);
      return this;
    }
    
    public Builder mergeFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      for (;;)
      {
        int i = paramCodedInputStream.readTag();
        Object localObject;
        switch (i)
        {
        default: 
          if (parseUnknownField(paramCodedInputStream, paramExtensionRegistryLite, i)) {}
          break;
        case 0: 
          return this;
        case 10: 
          localObject = SmsProtos.Sms.newBuilder();
          paramCodedInputStream.readMessage((MessageLite.Builder)localObject, paramExtensionRegistryLite);
          addSms(((SmsProtos.Sms.Builder)localObject).buildPartial());
          break;
        case 18: 
          localObject = SmsProtos.SmsBookmark.newBuilder();
          paramCodedInputStream.readMessage((MessageLite.Builder)localObject, paramExtensionRegistryLite);
          addSmsBookmark(((SmsProtos.SmsBookmark.Builder)localObject).buildPartial());
        }
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.backup.SmsProtos.MmsSms
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */