package com.android.mms.backup;

import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite.Builder;
import com.google.protobuf.MessageLite.Builder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class SmsProtos$MmsSms$Builder
  extends GeneratedMessageLite.Builder<SmsProtos.MmsSms, Builder>
  implements SmsProtos.MmsSmsOrBuilder
{
  private int bitField0_;
  private List<SmsProtos.SmsBookmark> smsBookmark_ = Collections.emptyList();
  private List<SmsProtos.Sms> sms_ = Collections.emptyList();
  
  private SmsProtos$MmsSms$Builder()
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
      if (!SmsProtos.MmsSms.access$3700(paramMmsSms).isEmpty())
      {
        if (!sms_.isEmpty()) {
          break label97;
        }
        sms_ = SmsProtos.MmsSms.access$3700(paramMmsSms);
        bitField0_ &= 0xFFFFFFFE;
      }
      while (!SmsProtos.MmsSms.access$3800(paramMmsSms).isEmpty())
      {
        if (!smsBookmark_.isEmpty()) {
          break label118;
        }
        smsBookmark_ = SmsProtos.MmsSms.access$3800(paramMmsSms);
        bitField0_ &= 0xFFFFFFFD;
        return this;
        label97:
        ensureSmsIsMutable();
        sms_.addAll(SmsProtos.MmsSms.access$3700(paramMmsSms));
      }
    }
    label118:
    ensureSmsBookmarkIsMutable();
    smsBookmark_.addAll(SmsProtos.MmsSms.access$3800(paramMmsSms));
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

/* Location:
 * Qualified Name:     com.android.mms.backup.SmsProtos.MmsSms.Builder
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */