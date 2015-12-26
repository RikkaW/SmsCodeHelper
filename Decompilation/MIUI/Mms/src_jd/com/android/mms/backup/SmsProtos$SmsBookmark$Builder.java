package com.android.mms.backup;

import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite.Builder;
import java.io.IOException;

public final class SmsProtos$SmsBookmark$Builder
  extends GeneratedMessageLite.Builder<SmsProtos.SmsBookmark, Builder>
  implements SmsProtos.SmsBookmarkOrBuilder
{
  private Object address_ = "";
  private int bitField0_;
  private Object body_ = "";
  private int forward_;
  private Object fromName_ = "";
  private Object guid_ = "";
  private Object luid_ = "";
  private long markDate_;
  private int status_;
  private Object subject_ = "";
  
  private SmsProtos$SmsBookmark$Builder()
  {
    maybeForceBuilderInitialization();
  }
  
  private static Builder create()
  {
    return new Builder();
  }
  
  private void maybeForceBuilderInitialization() {}
  
  public SmsProtos.SmsBookmark build()
  {
    SmsProtos.SmsBookmark localSmsBookmark = buildPartial();
    if (!localSmsBookmark.isInitialized()) {
      throw newUninitializedMessageException(localSmsBookmark);
    }
    return localSmsBookmark;
  }
  
  public SmsProtos.SmsBookmark buildPartial()
  {
    SmsProtos.SmsBookmark localSmsBookmark = new SmsProtos.SmsBookmark(this, null);
    int k = bitField0_;
    int j = 0;
    if ((k & 0x1) == 1) {
      j = 0x0 | 0x1;
    }
    SmsProtos.SmsBookmark.access$2402(localSmsBookmark, guid_);
    int i = j;
    if ((k & 0x2) == 2) {
      i = j | 0x2;
    }
    SmsProtos.SmsBookmark.access$2502(localSmsBookmark, luid_);
    j = i;
    if ((k & 0x4) == 4) {
      j = i | 0x4;
    }
    SmsProtos.SmsBookmark.access$2602(localSmsBookmark, fromName_);
    i = j;
    if ((k & 0x8) == 8) {
      i = j | 0x8;
    }
    SmsProtos.SmsBookmark.access$2702(localSmsBookmark, address_);
    j = i;
    if ((k & 0x10) == 16) {
      j = i | 0x10;
    }
    SmsProtos.SmsBookmark.access$2802(localSmsBookmark, subject_);
    i = j;
    if ((k & 0x20) == 32) {
      i = j | 0x20;
    }
    SmsProtos.SmsBookmark.access$2902(localSmsBookmark, body_);
    j = i;
    if ((k & 0x40) == 64) {
      j = i | 0x40;
    }
    SmsProtos.SmsBookmark.access$3002(localSmsBookmark, markDate_);
    i = j;
    if ((k & 0x80) == 128) {
      i = j | 0x80;
    }
    SmsProtos.SmsBookmark.access$3102(localSmsBookmark, forward_);
    j = i;
    if ((k & 0x100) == 256) {
      j = i | 0x100;
    }
    SmsProtos.SmsBookmark.access$3202(localSmsBookmark, status_);
    SmsProtos.SmsBookmark.access$3302(localSmsBookmark, j);
    return localSmsBookmark;
  }
  
  public Builder clear()
  {
    super.clear();
    guid_ = "";
    bitField0_ &= 0xFFFFFFFE;
    luid_ = "";
    bitField0_ &= 0xFFFFFFFD;
    fromName_ = "";
    bitField0_ &= 0xFFFFFFFB;
    address_ = "";
    bitField0_ &= 0xFFFFFFF7;
    subject_ = "";
    bitField0_ &= 0xFFFFFFEF;
    body_ = "";
    bitField0_ &= 0xFFFFFFDF;
    markDate_ = 0L;
    bitField0_ &= 0xFFFFFFBF;
    forward_ = 0;
    bitField0_ &= 0xFF7F;
    status_ = 0;
    bitField0_ &= 0xFEFF;
    return this;
  }
  
  public Builder clone()
  {
    return create().mergeFrom(buildPartial());
  }
  
  public SmsProtos.SmsBookmark getDefaultInstanceForType()
  {
    return SmsProtos.SmsBookmark.getDefaultInstance();
  }
  
  public final boolean isInitialized()
  {
    return true;
  }
  
  public Builder mergeFrom(SmsProtos.SmsBookmark paramSmsBookmark)
  {
    if (paramSmsBookmark == SmsProtos.SmsBookmark.getDefaultInstance()) {}
    do
    {
      return this;
      if (paramSmsBookmark.hasGuid()) {
        setGuid(paramSmsBookmark.getGuid());
      }
      if (paramSmsBookmark.hasLuid()) {
        setLuid(paramSmsBookmark.getLuid());
      }
      if (paramSmsBookmark.hasFromName()) {
        setFromName(paramSmsBookmark.getFromName());
      }
      if (paramSmsBookmark.hasAddress()) {
        setAddress(paramSmsBookmark.getAddress());
      }
      if (paramSmsBookmark.hasSubject()) {
        setSubject(paramSmsBookmark.getSubject());
      }
      if (paramSmsBookmark.hasBody()) {
        setBody(paramSmsBookmark.getBody());
      }
      if (paramSmsBookmark.hasMarkDate()) {
        setMarkDate(paramSmsBookmark.getMarkDate());
      }
      if (paramSmsBookmark.hasForward()) {
        setForward(paramSmsBookmark.getForward());
      }
    } while (!paramSmsBookmark.hasStatus());
    setStatus(paramSmsBookmark.getStatus());
    return this;
  }
  
  public Builder mergeFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
    throws IOException
  {
    for (;;)
    {
      int i = paramCodedInputStream.readTag();
      switch (i)
      {
      default: 
        if (parseUnknownField(paramCodedInputStream, paramExtensionRegistryLite, i)) {}
        break;
      case 0: 
        return this;
      case 10: 
        bitField0_ |= 0x1;
        guid_ = paramCodedInputStream.readBytes();
        break;
      case 18: 
        bitField0_ |= 0x2;
        luid_ = paramCodedInputStream.readBytes();
        break;
      case 26: 
        bitField0_ |= 0x4;
        fromName_ = paramCodedInputStream.readBytes();
        break;
      case 34: 
        bitField0_ |= 0x8;
        address_ = paramCodedInputStream.readBytes();
        break;
      case 42: 
        bitField0_ |= 0x10;
        subject_ = paramCodedInputStream.readBytes();
        break;
      case 50: 
        bitField0_ |= 0x20;
        body_ = paramCodedInputStream.readBytes();
        break;
      case 56: 
        bitField0_ |= 0x40;
        markDate_ = paramCodedInputStream.readInt64();
        break;
      case 64: 
        bitField0_ |= 0x80;
        forward_ = paramCodedInputStream.readInt32();
        break;
      case 72: 
        bitField0_ |= 0x100;
        status_ = paramCodedInputStream.readInt32();
      }
    }
  }
  
  public Builder setAddress(String paramString)
  {
    if (paramString == null) {
      throw new NullPointerException();
    }
    bitField0_ |= 0x8;
    address_ = paramString;
    return this;
  }
  
  public Builder setBody(String paramString)
  {
    if (paramString == null) {
      throw new NullPointerException();
    }
    bitField0_ |= 0x20;
    body_ = paramString;
    return this;
  }
  
  public Builder setForward(int paramInt)
  {
    bitField0_ |= 0x80;
    forward_ = paramInt;
    return this;
  }
  
  public Builder setFromName(String paramString)
  {
    if (paramString == null) {
      throw new NullPointerException();
    }
    bitField0_ |= 0x4;
    fromName_ = paramString;
    return this;
  }
  
  public Builder setGuid(String paramString)
  {
    if (paramString == null) {
      throw new NullPointerException();
    }
    bitField0_ |= 0x1;
    guid_ = paramString;
    return this;
  }
  
  public Builder setLuid(String paramString)
  {
    if (paramString == null) {
      throw new NullPointerException();
    }
    bitField0_ |= 0x2;
    luid_ = paramString;
    return this;
  }
  
  public Builder setMarkDate(long paramLong)
  {
    bitField0_ |= 0x40;
    markDate_ = paramLong;
    return this;
  }
  
  public Builder setStatus(int paramInt)
  {
    bitField0_ |= 0x100;
    status_ = paramInt;
    return this;
  }
  
  public Builder setSubject(String paramString)
  {
    if (paramString == null) {
      throw new NullPointerException();
    }
    bitField0_ |= 0x10;
    subject_ = paramString;
    return this;
  }
}

/* Location:
 * Qualified Name:     com.android.mms.backup.SmsProtos.SmsBookmark.Builder
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */