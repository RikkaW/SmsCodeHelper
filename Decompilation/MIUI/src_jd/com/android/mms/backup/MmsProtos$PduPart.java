package com.android.mms.backup;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.GeneratedMessageLite.Builder;
import com.google.protobuf.Internal;
import java.io.IOException;
import java.io.ObjectStreamException;

public final class MmsProtos$PduPart
  extends GeneratedMessageLite
  implements MmsProtos.PduPartOrBuilder
{
  private static final PduPart defaultInstance = new PduPart(true);
  private static final long serialVersionUID = 0L;
  private int bitField0_;
  private int charset_;
  private Object contentDisposition_;
  private Object contentId_;
  private Object contentLocation_;
  private int contentTypeStart_;
  private Object contentTypeType_;
  private Object contentType_;
  private ByteString data_;
  private Object fileName_;
  private byte memoizedIsInitialized = -1;
  private int memoizedSerializedSize = -1;
  private Object name_;
  private int sequence_;
  private Object text_;
  
  static
  {
    defaultInstance.initFields();
  }
  
  private MmsProtos$PduPart(Builder paramBuilder)
  {
    super(paramBuilder);
  }
  
  private MmsProtos$PduPart(boolean paramBoolean) {}
  
  private ByteString getContentDispositionBytes()
  {
    Object localObject = contentDisposition_;
    if ((localObject instanceof String))
    {
      localObject = ByteString.copyFromUtf8((String)localObject);
      contentDisposition_ = localObject;
      return (ByteString)localObject;
    }
    return (ByteString)localObject;
  }
  
  private ByteString getContentIdBytes()
  {
    Object localObject = contentId_;
    if ((localObject instanceof String))
    {
      localObject = ByteString.copyFromUtf8((String)localObject);
      contentId_ = localObject;
      return (ByteString)localObject;
    }
    return (ByteString)localObject;
  }
  
  private ByteString getContentLocationBytes()
  {
    Object localObject = contentLocation_;
    if ((localObject instanceof String))
    {
      localObject = ByteString.copyFromUtf8((String)localObject);
      contentLocation_ = localObject;
      return (ByteString)localObject;
    }
    return (ByteString)localObject;
  }
  
  private ByteString getContentTypeBytes()
  {
    Object localObject = contentType_;
    if ((localObject instanceof String))
    {
      localObject = ByteString.copyFromUtf8((String)localObject);
      contentType_ = localObject;
      return (ByteString)localObject;
    }
    return (ByteString)localObject;
  }
  
  private ByteString getContentTypeTypeBytes()
  {
    Object localObject = contentTypeType_;
    if ((localObject instanceof String))
    {
      localObject = ByteString.copyFromUtf8((String)localObject);
      contentTypeType_ = localObject;
      return (ByteString)localObject;
    }
    return (ByteString)localObject;
  }
  
  public static PduPart getDefaultInstance()
  {
    return defaultInstance;
  }
  
  private ByteString getFileNameBytes()
  {
    Object localObject = fileName_;
    if ((localObject instanceof String))
    {
      localObject = ByteString.copyFromUtf8((String)localObject);
      fileName_ = localObject;
      return (ByteString)localObject;
    }
    return (ByteString)localObject;
  }
  
  private ByteString getNameBytes()
  {
    Object localObject = name_;
    if ((localObject instanceof String))
    {
      localObject = ByteString.copyFromUtf8((String)localObject);
      name_ = localObject;
      return (ByteString)localObject;
    }
    return (ByteString)localObject;
  }
  
  private ByteString getTextBytes()
  {
    Object localObject = text_;
    if ((localObject instanceof String))
    {
      localObject = ByteString.copyFromUtf8((String)localObject);
      text_ = localObject;
      return (ByteString)localObject;
    }
    return (ByteString)localObject;
  }
  
  private void initFields()
  {
    sequence_ = 0;
    contentType_ = "";
    name_ = "";
    charset_ = 0;
    contentDisposition_ = "";
    fileName_ = "";
    contentId_ = "";
    contentLocation_ = "";
    contentTypeStart_ = 0;
    contentTypeType_ = "";
    text_ = "";
    data_ = ByteString.EMPTY;
  }
  
  public static Builder newBuilder()
  {
    return Builder.access$5000();
  }
  
  public static Builder newBuilder(PduPart paramPduPart)
  {
    return newBuilder().mergeFrom(paramPduPart);
  }
  
  public int getCharset()
  {
    return charset_;
  }
  
  public String getContentDisposition()
  {
    Object localObject = contentDisposition_;
    if ((localObject instanceof String)) {
      return (String)localObject;
    }
    localObject = (ByteString)localObject;
    String str = ((ByteString)localObject).toStringUtf8();
    if (Internal.isValidUtf8((ByteString)localObject)) {
      contentDisposition_ = str;
    }
    return str;
  }
  
  public String getContentId()
  {
    Object localObject = contentId_;
    if ((localObject instanceof String)) {
      return (String)localObject;
    }
    localObject = (ByteString)localObject;
    String str = ((ByteString)localObject).toStringUtf8();
    if (Internal.isValidUtf8((ByteString)localObject)) {
      contentId_ = str;
    }
    return str;
  }
  
  public String getContentLocation()
  {
    Object localObject = contentLocation_;
    if ((localObject instanceof String)) {
      return (String)localObject;
    }
    localObject = (ByteString)localObject;
    String str = ((ByteString)localObject).toStringUtf8();
    if (Internal.isValidUtf8((ByteString)localObject)) {
      contentLocation_ = str;
    }
    return str;
  }
  
  public String getContentType()
  {
    Object localObject = contentType_;
    if ((localObject instanceof String)) {
      return (String)localObject;
    }
    localObject = (ByteString)localObject;
    String str = ((ByteString)localObject).toStringUtf8();
    if (Internal.isValidUtf8((ByteString)localObject)) {
      contentType_ = str;
    }
    return str;
  }
  
  public int getContentTypeStart()
  {
    return contentTypeStart_;
  }
  
  public String getContentTypeType()
  {
    Object localObject = contentTypeType_;
    if ((localObject instanceof String)) {
      return (String)localObject;
    }
    localObject = (ByteString)localObject;
    String str = ((ByteString)localObject).toStringUtf8();
    if (Internal.isValidUtf8((ByteString)localObject)) {
      contentTypeType_ = str;
    }
    return str;
  }
  
  public ByteString getData()
  {
    return data_;
  }
  
  public PduPart getDefaultInstanceForType()
  {
    return defaultInstance;
  }
  
  public String getFileName()
  {
    Object localObject = fileName_;
    if ((localObject instanceof String)) {
      return (String)localObject;
    }
    localObject = (ByteString)localObject;
    String str = ((ByteString)localObject).toStringUtf8();
    if (Internal.isValidUtf8((ByteString)localObject)) {
      fileName_ = str;
    }
    return str;
  }
  
  public String getName()
  {
    Object localObject = name_;
    if ((localObject instanceof String)) {
      return (String)localObject;
    }
    localObject = (ByteString)localObject;
    String str = ((ByteString)localObject).toStringUtf8();
    if (Internal.isValidUtf8((ByteString)localObject)) {
      name_ = str;
    }
    return str;
  }
  
  public int getSequence()
  {
    return sequence_;
  }
  
  public int getSerializedSize()
  {
    int i = memoizedSerializedSize;
    if (i != -1) {
      return i;
    }
    int j = 0;
    if ((bitField0_ & 0x1) == 1) {
      j = 0 + CodedOutputStream.computeInt32Size(1, sequence_);
    }
    i = j;
    if ((bitField0_ & 0x2) == 2) {
      i = j + CodedOutputStream.computeBytesSize(2, getContentTypeBytes());
    }
    j = i;
    if ((bitField0_ & 0x4) == 4) {
      j = i + CodedOutputStream.computeBytesSize(3, getNameBytes());
    }
    i = j;
    if ((bitField0_ & 0x8) == 8) {
      i = j + CodedOutputStream.computeInt32Size(4, charset_);
    }
    j = i;
    if ((bitField0_ & 0x10) == 16) {
      j = i + CodedOutputStream.computeBytesSize(5, getContentDispositionBytes());
    }
    i = j;
    if ((bitField0_ & 0x20) == 32) {
      i = j + CodedOutputStream.computeBytesSize(6, getFileNameBytes());
    }
    j = i;
    if ((bitField0_ & 0x40) == 64) {
      j = i + CodedOutputStream.computeBytesSize(7, getContentIdBytes());
    }
    i = j;
    if ((bitField0_ & 0x80) == 128) {
      i = j + CodedOutputStream.computeBytesSize(8, getContentLocationBytes());
    }
    j = i;
    if ((bitField0_ & 0x100) == 256) {
      j = i + CodedOutputStream.computeInt32Size(9, contentTypeStart_);
    }
    i = j;
    if ((bitField0_ & 0x200) == 512) {
      i = j + CodedOutputStream.computeBytesSize(10, getContentTypeTypeBytes());
    }
    j = i;
    if ((bitField0_ & 0x400) == 1024) {
      j = i + CodedOutputStream.computeBytesSize(11, getTextBytes());
    }
    i = j;
    if ((bitField0_ & 0x800) == 2048) {
      i = j + CodedOutputStream.computeBytesSize(12, data_);
    }
    memoizedSerializedSize = i;
    return i;
  }
  
  public String getText()
  {
    Object localObject = text_;
    if ((localObject instanceof String)) {
      return (String)localObject;
    }
    localObject = (ByteString)localObject;
    String str = ((ByteString)localObject).toStringUtf8();
    if (Internal.isValidUtf8((ByteString)localObject)) {
      text_ = str;
    }
    return str;
  }
  
  public boolean hasCharset()
  {
    return (bitField0_ & 0x8) == 8;
  }
  
  public boolean hasContentDisposition()
  {
    return (bitField0_ & 0x10) == 16;
  }
  
  public boolean hasContentId()
  {
    return (bitField0_ & 0x40) == 64;
  }
  
  public boolean hasContentLocation()
  {
    return (bitField0_ & 0x80) == 128;
  }
  
  public boolean hasContentType()
  {
    return (bitField0_ & 0x2) == 2;
  }
  
  public boolean hasContentTypeStart()
  {
    return (bitField0_ & 0x100) == 256;
  }
  
  public boolean hasContentTypeType()
  {
    return (bitField0_ & 0x200) == 512;
  }
  
  public boolean hasData()
  {
    return (bitField0_ & 0x800) == 2048;
  }
  
  public boolean hasFileName()
  {
    return (bitField0_ & 0x20) == 32;
  }
  
  public boolean hasName()
  {
    return (bitField0_ & 0x4) == 4;
  }
  
  public boolean hasSequence()
  {
    return (bitField0_ & 0x1) == 1;
  }
  
  public boolean hasText()
  {
    return (bitField0_ & 0x400) == 1024;
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
    if ((bitField0_ & 0x1) == 1) {
      paramCodedOutputStream.writeInt32(1, sequence_);
    }
    if ((bitField0_ & 0x2) == 2) {
      paramCodedOutputStream.writeBytes(2, getContentTypeBytes());
    }
    if ((bitField0_ & 0x4) == 4) {
      paramCodedOutputStream.writeBytes(3, getNameBytes());
    }
    if ((bitField0_ & 0x8) == 8) {
      paramCodedOutputStream.writeInt32(4, charset_);
    }
    if ((bitField0_ & 0x10) == 16) {
      paramCodedOutputStream.writeBytes(5, getContentDispositionBytes());
    }
    if ((bitField0_ & 0x20) == 32) {
      paramCodedOutputStream.writeBytes(6, getFileNameBytes());
    }
    if ((bitField0_ & 0x40) == 64) {
      paramCodedOutputStream.writeBytes(7, getContentIdBytes());
    }
    if ((bitField0_ & 0x80) == 128) {
      paramCodedOutputStream.writeBytes(8, getContentLocationBytes());
    }
    if ((bitField0_ & 0x100) == 256) {
      paramCodedOutputStream.writeInt32(9, contentTypeStart_);
    }
    if ((bitField0_ & 0x200) == 512) {
      paramCodedOutputStream.writeBytes(10, getContentTypeTypeBytes());
    }
    if ((bitField0_ & 0x400) == 1024) {
      paramCodedOutputStream.writeBytes(11, getTextBytes());
    }
    if ((bitField0_ & 0x800) == 2048) {
      paramCodedOutputStream.writeBytes(12, data_);
    }
  }
  
  public static final class Builder
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
    
    private Builder()
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
}

/* Location:
 * Qualified Name:     com.android.mms.backup.MmsProtos.PduPart
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */