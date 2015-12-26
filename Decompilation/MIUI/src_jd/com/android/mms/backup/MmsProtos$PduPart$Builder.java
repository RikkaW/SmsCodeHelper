package com.android.mms.backup;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite.Builder;
import java.io.IOException;

public final class MmsProtos$PduPart$Builder
  extends GeneratedMessageLite.Builder<MmsProtos.PduPart, Builder>
  implements MmsProtos.PduPartOrBuilder
{
  private int bitField0_;
  private int charset_;
  private Object contentDisposition_ = "";
  private Object contentId_ = "";
  private Object contentLocation_ = "";
  private int contentTypeStart_;
  private Object contentTypeType_ = "";
  private Object contentType_ = "";
  private ByteString data_ = ByteString.EMPTY;
  private Object fileName_ = "";
  private Object name_ = "";
  private int sequence_;
  private Object text_ = "";
  
  private MmsProtos$PduPart$Builder()
  {
    maybeForceBuilderInitialization();
  }
  
  private static Builder create()
  {
    return new Builder();
  }
  
  private void maybeForceBuilderInitialization() {}
  
  public MmsProtos.PduPart build()
  {
    MmsProtos.PduPart localPduPart = buildPartial();
    if (!localPduPart.isInitialized()) {
      throw newUninitializedMessageException(localPduPart);
    }
    return localPduPart;
  }
  
  public MmsProtos.PduPart buildPartial()
  {
    MmsProtos.PduPart localPduPart = new MmsProtos.PduPart(this, null);
    int k = bitField0_;
    int j = 0;
    if ((k & 0x1) == 1) {
      j = 0x0 | 0x1;
    }
    MmsProtos.PduPart.access$5202(localPduPart, sequence_);
    int i = j;
    if ((k & 0x2) == 2) {
      i = j | 0x2;
    }
    MmsProtos.PduPart.access$5302(localPduPart, contentType_);
    j = i;
    if ((k & 0x4) == 4) {
      j = i | 0x4;
    }
    MmsProtos.PduPart.access$5402(localPduPart, name_);
    i = j;
    if ((k & 0x8) == 8) {
      i = j | 0x8;
    }
    MmsProtos.PduPart.access$5502(localPduPart, charset_);
    j = i;
    if ((k & 0x10) == 16) {
      j = i | 0x10;
    }
    MmsProtos.PduPart.access$5602(localPduPart, contentDisposition_);
    i = j;
    if ((k & 0x20) == 32) {
      i = j | 0x20;
    }
    MmsProtos.PduPart.access$5702(localPduPart, fileName_);
    j = i;
    if ((k & 0x40) == 64) {
      j = i | 0x40;
    }
    MmsProtos.PduPart.access$5802(localPduPart, contentId_);
    i = j;
    if ((k & 0x80) == 128) {
      i = j | 0x80;
    }
    MmsProtos.PduPart.access$5902(localPduPart, contentLocation_);
    j = i;
    if ((k & 0x100) == 256) {
      j = i | 0x100;
    }
    MmsProtos.PduPart.access$6002(localPduPart, contentTypeStart_);
    i = j;
    if ((k & 0x200) == 512) {
      i = j | 0x200;
    }
    MmsProtos.PduPart.access$6102(localPduPart, contentTypeType_);
    j = i;
    if ((k & 0x400) == 1024) {
      j = i | 0x400;
    }
    MmsProtos.PduPart.access$6202(localPduPart, text_);
    i = j;
    if ((k & 0x800) == 2048) {
      i = j | 0x800;
    }
    MmsProtos.PduPart.access$6302(localPduPart, data_);
    MmsProtos.PduPart.access$6402(localPduPart, i);
    return localPduPart;
  }
  
  public Builder clear()
  {
    super.clear();
    sequence_ = 0;
    bitField0_ &= 0xFFFFFFFE;
    contentType_ = "";
    bitField0_ &= 0xFFFFFFFD;
    name_ = "";
    bitField0_ &= 0xFFFFFFFB;
    charset_ = 0;
    bitField0_ &= 0xFFFFFFF7;
    contentDisposition_ = "";
    bitField0_ &= 0xFFFFFFEF;
    fileName_ = "";
    bitField0_ &= 0xFFFFFFDF;
    contentId_ = "";
    bitField0_ &= 0xFFFFFFBF;
    contentLocation_ = "";
    bitField0_ &= 0xFF7F;
    contentTypeStart_ = 0;
    bitField0_ &= 0xFEFF;
    contentTypeType_ = "";
    bitField0_ &= 0xFDFF;
    text_ = "";
    bitField0_ &= 0xFBFF;
    data_ = ByteString.EMPTY;
    bitField0_ &= 0xF7FF;
    return this;
  }
  
  public Builder clone()
  {
    return create().mergeFrom(buildPartial());
  }
  
  public MmsProtos.PduPart getDefaultInstanceForType()
  {
    return MmsProtos.PduPart.getDefaultInstance();
  }
  
  public final boolean isInitialized()
  {
    return true;
  }
  
  public Builder mergeFrom(MmsProtos.PduPart paramPduPart)
  {
    if (paramPduPart == MmsProtos.PduPart.getDefaultInstance()) {}
    do
    {
      return this;
      if (paramPduPart.hasSequence()) {
        setSequence(paramPduPart.getSequence());
      }
      if (paramPduPart.hasContentType()) {
        setContentType(paramPduPart.getContentType());
      }
      if (paramPduPart.hasName()) {
        setName(paramPduPart.getName());
      }
      if (paramPduPart.hasCharset()) {
        setCharset(paramPduPart.getCharset());
      }
      if (paramPduPart.hasContentDisposition()) {
        setContentDisposition(paramPduPart.getContentDisposition());
      }
      if (paramPduPart.hasFileName()) {
        setFileName(paramPduPart.getFileName());
      }
      if (paramPduPart.hasContentId()) {
        setContentId(paramPduPart.getContentId());
      }
      if (paramPduPart.hasContentLocation()) {
        setContentLocation(paramPduPart.getContentLocation());
      }
      if (paramPduPart.hasContentTypeStart()) {
        setContentTypeStart(paramPduPart.getContentTypeStart());
      }
      if (paramPduPart.hasContentTypeType()) {
        setContentTypeType(paramPduPart.getContentTypeType());
      }
      if (paramPduPart.hasText()) {
        setText(paramPduPart.getText());
      }
    } while (!paramPduPart.hasData());
    setData(paramPduPart.getData());
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
      case 8: 
        bitField0_ |= 0x1;
        sequence_ = paramCodedInputStream.readInt32();
        break;
      case 18: 
        bitField0_ |= 0x2;
        contentType_ = paramCodedInputStream.readBytes();
        break;
      case 26: 
        bitField0_ |= 0x4;
        name_ = paramCodedInputStream.readBytes();
        break;
      case 32: 
        bitField0_ |= 0x8;
        charset_ = paramCodedInputStream.readInt32();
        break;
      case 42: 
        bitField0_ |= 0x10;
        contentDisposition_ = paramCodedInputStream.readBytes();
        break;
      case 50: 
        bitField0_ |= 0x20;
        fileName_ = paramCodedInputStream.readBytes();
        break;
      case 58: 
        bitField0_ |= 0x40;
        contentId_ = paramCodedInputStream.readBytes();
        break;
      case 66: 
        bitField0_ |= 0x80;
        contentLocation_ = paramCodedInputStream.readBytes();
        break;
      case 72: 
        bitField0_ |= 0x100;
        contentTypeStart_ = paramCodedInputStream.readInt32();
        break;
      case 82: 
        bitField0_ |= 0x200;
        contentTypeType_ = paramCodedInputStream.readBytes();
        break;
      case 90: 
        bitField0_ |= 0x400;
        text_ = paramCodedInputStream.readBytes();
        break;
      case 98: 
        bitField0_ |= 0x800;
        data_ = paramCodedInputStream.readBytes();
      }
    }
  }
  
  public Builder setCharset(int paramInt)
  {
    bitField0_ |= 0x8;
    charset_ = paramInt;
    return this;
  }
  
  public Builder setContentDisposition(String paramString)
  {
    if (paramString == null) {
      throw new NullPointerException();
    }
    bitField0_ |= 0x10;
    contentDisposition_ = paramString;
    return this;
  }
  
  public Builder setContentId(String paramString)
  {
    if (paramString == null) {
      throw new NullPointerException();
    }
    bitField0_ |= 0x40;
    contentId_ = paramString;
    return this;
  }
  
  public Builder setContentLocation(String paramString)
  {
    if (paramString == null) {
      throw new NullPointerException();
    }
    bitField0_ |= 0x80;
    contentLocation_ = paramString;
    return this;
  }
  
  public Builder setContentType(String paramString)
  {
    if (paramString == null) {
      throw new NullPointerException();
    }
    bitField0_ |= 0x2;
    contentType_ = paramString;
    return this;
  }
  
  public Builder setContentTypeStart(int paramInt)
  {
    bitField0_ |= 0x100;
    contentTypeStart_ = paramInt;
    return this;
  }
  
  public Builder setContentTypeType(String paramString)
  {
    if (paramString == null) {
      throw new NullPointerException();
    }
    bitField0_ |= 0x200;
    contentTypeType_ = paramString;
    return this;
  }
  
  public Builder setData(ByteString paramByteString)
  {
    if (paramByteString == null) {
      throw new NullPointerException();
    }
    bitField0_ |= 0x800;
    data_ = paramByteString;
    return this;
  }
  
  public Builder setFileName(String paramString)
  {
    if (paramString == null) {
      throw new NullPointerException();
    }
    bitField0_ |= 0x20;
    fileName_ = paramString;
    return this;
  }
  
  public Builder setName(String paramString)
  {
    if (paramString == null) {
      throw new NullPointerException();
    }
    bitField0_ |= 0x4;
    name_ = paramString;
    return this;
  }
  
  public Builder setSequence(int paramInt)
  {
    bitField0_ |= 0x1;
    sequence_ = paramInt;
    return this;
  }
  
  public Builder setText(String paramString)
  {
    if (paramString == null) {
      throw new NullPointerException();
    }
    bitField0_ |= 0x400;
    text_ = paramString;
    return this;
  }
}

/* Location:
 * Qualified Name:     com.android.mms.backup.MmsProtos.PduPart.Builder
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */