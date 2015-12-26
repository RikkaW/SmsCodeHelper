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

public final class SmsProtos$Sms
  extends GeneratedMessageLite
  implements SmsProtos.SmsOrBuilder
{
  private static final Sms defaultInstance = new Sms(true);
  private static final long serialVersionUID = 0L;
  private Object address_;
  private int bitField0_;
  private Object body_;
  private long date_;
  private Object guid_;
  private boolean locked_;
  private Object luid_;
  private byte memoizedIsInitialized = -1;
  private int memoizedSerializedSize = -1;
  private long mxId_;
  private int mxStatus_;
  private int protocol_;
  private boolean read_;
  private boolean replyPathPresent_;
  private boolean seen_;
  private long serverDate_;
  private Object serviceCenter_;
  private int status_;
  private Object subject_;
  private int type_;
  
  static
  {
    defaultInstance.initFields();
  }
  
  private SmsProtos$Sms(Builder paramBuilder)
  {
    super(paramBuilder);
  }
  
  private SmsProtos$Sms(boolean paramBoolean) {}
  
  private ByteString getAddressBytes()
  {
    Object localObject = address_;
    if ((localObject instanceof String))
    {
      localObject = ByteString.copyFromUtf8((String)localObject);
      address_ = localObject;
      return (ByteString)localObject;
    }
    return (ByteString)localObject;
  }
  
  private ByteString getBodyBytes()
  {
    Object localObject = body_;
    if ((localObject instanceof String))
    {
      localObject = ByteString.copyFromUtf8((String)localObject);
      body_ = localObject;
      return (ByteString)localObject;
    }
    return (ByteString)localObject;
  }
  
  public static Sms getDefaultInstance()
  {
    return defaultInstance;
  }
  
  private ByteString getGuidBytes()
  {
    Object localObject = guid_;
    if ((localObject instanceof String))
    {
      localObject = ByteString.copyFromUtf8((String)localObject);
      guid_ = localObject;
      return (ByteString)localObject;
    }
    return (ByteString)localObject;
  }
  
  private ByteString getLuidBytes()
  {
    Object localObject = luid_;
    if ((localObject instanceof String))
    {
      localObject = ByteString.copyFromUtf8((String)localObject);
      luid_ = localObject;
      return (ByteString)localObject;
    }
    return (ByteString)localObject;
  }
  
  private ByteString getServiceCenterBytes()
  {
    Object localObject = serviceCenter_;
    if ((localObject instanceof String))
    {
      localObject = ByteString.copyFromUtf8((String)localObject);
      serviceCenter_ = localObject;
      return (ByteString)localObject;
    }
    return (ByteString)localObject;
  }
  
  private ByteString getSubjectBytes()
  {
    Object localObject = subject_;
    if ((localObject instanceof String))
    {
      localObject = ByteString.copyFromUtf8((String)localObject);
      subject_ = localObject;
      return (ByteString)localObject;
    }
    return (ByteString)localObject;
  }
  
  private void initFields()
  {
    guid_ = "";
    luid_ = "";
    type_ = 0;
    address_ = "";
    subject_ = "";
    body_ = "";
    date_ = 0L;
    read_ = false;
    seen_ = false;
    status_ = 0;
    serverDate_ = 0L;
    serviceCenter_ = "";
    protocol_ = 0;
    replyPathPresent_ = false;
    locked_ = false;
    mxId_ = 0L;
    mxStatus_ = 0;
  }
  
  public static Builder newBuilder()
  {
    return Builder.access$100();
  }
  
  public static Builder newBuilder(Sms paramSms)
  {
    return newBuilder().mergeFrom(paramSms);
  }
  
  public String getAddress()
  {
    Object localObject = address_;
    if ((localObject instanceof String)) {
      return (String)localObject;
    }
    localObject = (ByteString)localObject;
    String str = ((ByteString)localObject).toStringUtf8();
    if (Internal.isValidUtf8((ByteString)localObject)) {
      address_ = str;
    }
    return str;
  }
  
  public String getBody()
  {
    Object localObject = body_;
    if ((localObject instanceof String)) {
      return (String)localObject;
    }
    localObject = (ByteString)localObject;
    String str = ((ByteString)localObject).toStringUtf8();
    if (Internal.isValidUtf8((ByteString)localObject)) {
      body_ = str;
    }
    return str;
  }
  
  public long getDate()
  {
    return date_;
  }
  
  public Sms getDefaultInstanceForType()
  {
    return defaultInstance;
  }
  
  public String getGuid()
  {
    Object localObject = guid_;
    if ((localObject instanceof String)) {
      return (String)localObject;
    }
    localObject = (ByteString)localObject;
    String str = ((ByteString)localObject).toStringUtf8();
    if (Internal.isValidUtf8((ByteString)localObject)) {
      guid_ = str;
    }
    return str;
  }
  
  public boolean getLocked()
  {
    return locked_;
  }
  
  public String getLuid()
  {
    Object localObject = luid_;
    if ((localObject instanceof String)) {
      return (String)localObject;
    }
    localObject = (ByteString)localObject;
    String str = ((ByteString)localObject).toStringUtf8();
    if (Internal.isValidUtf8((ByteString)localObject)) {
      luid_ = str;
    }
    return str;
  }
  
  public long getMxId()
  {
    return mxId_;
  }
  
  public int getMxStatus()
  {
    return mxStatus_;
  }
  
  public int getProtocol()
  {
    return protocol_;
  }
  
  public boolean getRead()
  {
    return read_;
  }
  
  public boolean getReplyPathPresent()
  {
    return replyPathPresent_;
  }
  
  public boolean getSeen()
  {
    return seen_;
  }
  
  public int getSerializedSize()
  {
    int i = memoizedSerializedSize;
    if (i != -1) {
      return i;
    }
    int j = 0;
    if ((bitField0_ & 0x1) == 1) {
      j = 0 + CodedOutputStream.computeBytesSize(1, getGuidBytes());
    }
    i = j;
    if ((bitField0_ & 0x2) == 2) {
      i = j + CodedOutputStream.computeBytesSize(2, getLuidBytes());
    }
    j = i;
    if ((bitField0_ & 0x4) == 4) {
      j = i + CodedOutputStream.computeInt32Size(3, type_);
    }
    i = j;
    if ((bitField0_ & 0x8) == 8) {
      i = j + CodedOutputStream.computeBytesSize(4, getAddressBytes());
    }
    j = i;
    if ((bitField0_ & 0x10) == 16) {
      j = i + CodedOutputStream.computeBytesSize(5, getSubjectBytes());
    }
    i = j;
    if ((bitField0_ & 0x20) == 32) {
      i = j + CodedOutputStream.computeBytesSize(6, getBodyBytes());
    }
    j = i;
    if ((bitField0_ & 0x40) == 64) {
      j = i + CodedOutputStream.computeInt64Size(7, date_);
    }
    i = j;
    if ((bitField0_ & 0x80) == 128) {
      i = j + CodedOutputStream.computeBoolSize(8, read_);
    }
    j = i;
    if ((bitField0_ & 0x100) == 256) {
      j = i + CodedOutputStream.computeBoolSize(9, seen_);
    }
    i = j;
    if ((bitField0_ & 0x200) == 512) {
      i = j + CodedOutputStream.computeInt32Size(10, status_);
    }
    j = i;
    if ((bitField0_ & 0x400) == 1024) {
      j = i + CodedOutputStream.computeInt64Size(11, serverDate_);
    }
    i = j;
    if ((bitField0_ & 0x800) == 2048) {
      i = j + CodedOutputStream.computeBytesSize(12, getServiceCenterBytes());
    }
    j = i;
    if ((bitField0_ & 0x1000) == 4096) {
      j = i + CodedOutputStream.computeInt32Size(13, protocol_);
    }
    i = j;
    if ((bitField0_ & 0x2000) == 8192) {
      i = j + CodedOutputStream.computeBoolSize(14, replyPathPresent_);
    }
    j = i;
    if ((bitField0_ & 0x4000) == 16384) {
      j = i + CodedOutputStream.computeBoolSize(15, locked_);
    }
    i = j;
    if ((bitField0_ & 0x8000) == 32768) {
      i = j + CodedOutputStream.computeInt64Size(16, mxId_);
    }
    j = i;
    if ((bitField0_ & 0x10000) == 65536) {
      j = i + CodedOutputStream.computeInt32Size(17, mxStatus_);
    }
    memoizedSerializedSize = j;
    return j;
  }
  
  public long getServerDate()
  {
    return serverDate_;
  }
  
  public String getServiceCenter()
  {
    Object localObject = serviceCenter_;
    if ((localObject instanceof String)) {
      return (String)localObject;
    }
    localObject = (ByteString)localObject;
    String str = ((ByteString)localObject).toStringUtf8();
    if (Internal.isValidUtf8((ByteString)localObject)) {
      serviceCenter_ = str;
    }
    return str;
  }
  
  public int getStatus()
  {
    return status_;
  }
  
  public String getSubject()
  {
    Object localObject = subject_;
    if ((localObject instanceof String)) {
      return (String)localObject;
    }
    localObject = (ByteString)localObject;
    String str = ((ByteString)localObject).toStringUtf8();
    if (Internal.isValidUtf8((ByteString)localObject)) {
      subject_ = str;
    }
    return str;
  }
  
  public int getType()
  {
    return type_;
  }
  
  public boolean hasAddress()
  {
    return (bitField0_ & 0x8) == 8;
  }
  
  public boolean hasBody()
  {
    return (bitField0_ & 0x20) == 32;
  }
  
  public boolean hasDate()
  {
    return (bitField0_ & 0x40) == 64;
  }
  
  public boolean hasGuid()
  {
    return (bitField0_ & 0x1) == 1;
  }
  
  public boolean hasLocked()
  {
    return (bitField0_ & 0x4000) == 16384;
  }
  
  public boolean hasLuid()
  {
    return (bitField0_ & 0x2) == 2;
  }
  
  public boolean hasMxId()
  {
    return (bitField0_ & 0x8000) == 32768;
  }
  
  public boolean hasMxStatus()
  {
    return (bitField0_ & 0x10000) == 65536;
  }
  
  public boolean hasProtocol()
  {
    return (bitField0_ & 0x1000) == 4096;
  }
  
  public boolean hasRead()
  {
    return (bitField0_ & 0x80) == 128;
  }
  
  public boolean hasReplyPathPresent()
  {
    return (bitField0_ & 0x2000) == 8192;
  }
  
  public boolean hasSeen()
  {
    return (bitField0_ & 0x100) == 256;
  }
  
  public boolean hasServerDate()
  {
    return (bitField0_ & 0x400) == 1024;
  }
  
  public boolean hasServiceCenter()
  {
    return (bitField0_ & 0x800) == 2048;
  }
  
  public boolean hasStatus()
  {
    return (bitField0_ & 0x200) == 512;
  }
  
  public boolean hasSubject()
  {
    return (bitField0_ & 0x10) == 16;
  }
  
  public boolean hasType()
  {
    return (bitField0_ & 0x4) == 4;
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
      paramCodedOutputStream.writeBytes(1, getGuidBytes());
    }
    if ((bitField0_ & 0x2) == 2) {
      paramCodedOutputStream.writeBytes(2, getLuidBytes());
    }
    if ((bitField0_ & 0x4) == 4) {
      paramCodedOutputStream.writeInt32(3, type_);
    }
    if ((bitField0_ & 0x8) == 8) {
      paramCodedOutputStream.writeBytes(4, getAddressBytes());
    }
    if ((bitField0_ & 0x10) == 16) {
      paramCodedOutputStream.writeBytes(5, getSubjectBytes());
    }
    if ((bitField0_ & 0x20) == 32) {
      paramCodedOutputStream.writeBytes(6, getBodyBytes());
    }
    if ((bitField0_ & 0x40) == 64) {
      paramCodedOutputStream.writeInt64(7, date_);
    }
    if ((bitField0_ & 0x80) == 128) {
      paramCodedOutputStream.writeBool(8, read_);
    }
    if ((bitField0_ & 0x100) == 256) {
      paramCodedOutputStream.writeBool(9, seen_);
    }
    if ((bitField0_ & 0x200) == 512) {
      paramCodedOutputStream.writeInt32(10, status_);
    }
    if ((bitField0_ & 0x400) == 1024) {
      paramCodedOutputStream.writeInt64(11, serverDate_);
    }
    if ((bitField0_ & 0x800) == 2048) {
      paramCodedOutputStream.writeBytes(12, getServiceCenterBytes());
    }
    if ((bitField0_ & 0x1000) == 4096) {
      paramCodedOutputStream.writeInt32(13, protocol_);
    }
    if ((bitField0_ & 0x2000) == 8192) {
      paramCodedOutputStream.writeBool(14, replyPathPresent_);
    }
    if ((bitField0_ & 0x4000) == 16384) {
      paramCodedOutputStream.writeBool(15, locked_);
    }
    if ((bitField0_ & 0x8000) == 32768) {
      paramCodedOutputStream.writeInt64(16, mxId_);
    }
    if ((bitField0_ & 0x10000) == 65536) {
      paramCodedOutputStream.writeInt32(17, mxStatus_);
    }
  }
  
  public static final class Builder
    extends GeneratedMessageLite.Builder<SmsProtos.Sms, Builder>
    implements SmsProtos.SmsOrBuilder
  {
    private Object address_ = "";
    private int bitField0_;
    private Object body_ = "";
    private long date_;
    private Object guid_ = "";
    private boolean locked_;
    private Object luid_ = "";
    private long mxId_;
    private int mxStatus_;
    private int protocol_;
    private boolean read_;
    private boolean replyPathPresent_;
    private boolean seen_;
    private long serverDate_;
    private Object serviceCenter_ = "";
    private int status_;
    private Object subject_ = "";
    private int type_;
    
    private Builder()
    {
      maybeForceBuilderInitialization();
    }
    
    private static Builder create()
    {
      return new Builder();
    }
    
    private void maybeForceBuilderInitialization() {}
    
    public SmsProtos.Sms build()
    {
      SmsProtos.Sms localSms = buildPartial();
      if (!localSms.isInitialized()) {
        throw newUninitializedMessageException(localSms);
      }
      return localSms;
    }
    
    public SmsProtos.Sms buildPartial()
    {
      SmsProtos.Sms localSms = new SmsProtos.Sms(this, null);
      int k = bitField0_;
      int j = 0;
      if ((k & 0x1) == 1) {
        j = 0x0 | 0x1;
      }
      SmsProtos.Sms.access$302(localSms, guid_);
      int i = j;
      if ((k & 0x2) == 2) {
        i = j | 0x2;
      }
      SmsProtos.Sms.access$402(localSms, luid_);
      j = i;
      if ((k & 0x4) == 4) {
        j = i | 0x4;
      }
      SmsProtos.Sms.access$502(localSms, type_);
      i = j;
      if ((k & 0x8) == 8) {
        i = j | 0x8;
      }
      SmsProtos.Sms.access$602(localSms, address_);
      j = i;
      if ((k & 0x10) == 16) {
        j = i | 0x10;
      }
      SmsProtos.Sms.access$702(localSms, subject_);
      i = j;
      if ((k & 0x20) == 32) {
        i = j | 0x20;
      }
      SmsProtos.Sms.access$802(localSms, body_);
      j = i;
      if ((k & 0x40) == 64) {
        j = i | 0x40;
      }
      SmsProtos.Sms.access$902(localSms, date_);
      i = j;
      if ((k & 0x80) == 128) {
        i = j | 0x80;
      }
      SmsProtos.Sms.access$1002(localSms, read_);
      j = i;
      if ((k & 0x100) == 256) {
        j = i | 0x100;
      }
      SmsProtos.Sms.access$1102(localSms, seen_);
      i = j;
      if ((k & 0x200) == 512) {
        i = j | 0x200;
      }
      SmsProtos.Sms.access$1202(localSms, status_);
      j = i;
      if ((k & 0x400) == 1024) {
        j = i | 0x400;
      }
      SmsProtos.Sms.access$1302(localSms, serverDate_);
      i = j;
      if ((k & 0x800) == 2048) {
        i = j | 0x800;
      }
      SmsProtos.Sms.access$1402(localSms, serviceCenter_);
      j = i;
      if ((k & 0x1000) == 4096) {
        j = i | 0x1000;
      }
      SmsProtos.Sms.access$1502(localSms, protocol_);
      i = j;
      if ((k & 0x2000) == 8192) {
        i = j | 0x2000;
      }
      SmsProtos.Sms.access$1602(localSms, replyPathPresent_);
      j = i;
      if ((k & 0x4000) == 16384) {
        j = i | 0x4000;
      }
      SmsProtos.Sms.access$1702(localSms, locked_);
      i = j;
      if ((k & 0x8000) == 32768) {
        i = j | 0x8000;
      }
      SmsProtos.Sms.access$1802(localSms, mxId_);
      j = i;
      if ((k & 0x10000) == 65536) {
        j = i | 0x10000;
      }
      SmsProtos.Sms.access$1902(localSms, mxStatus_);
      SmsProtos.Sms.access$2002(localSms, j);
      return localSms;
    }
    
    public Builder clear()
    {
      super.clear();
      guid_ = "";
      bitField0_ &= 0xFFFFFFFE;
      luid_ = "";
      bitField0_ &= 0xFFFFFFFD;
      type_ = 0;
      bitField0_ &= 0xFFFFFFFB;
      address_ = "";
      bitField0_ &= 0xFFFFFFF7;
      subject_ = "";
      bitField0_ &= 0xFFFFFFEF;
      body_ = "";
      bitField0_ &= 0xFFFFFFDF;
      date_ = 0L;
      bitField0_ &= 0xFFFFFFBF;
      read_ = false;
      bitField0_ &= 0xFF7F;
      seen_ = false;
      bitField0_ &= 0xFEFF;
      status_ = 0;
      bitField0_ &= 0xFDFF;
      serverDate_ = 0L;
      bitField0_ &= 0xFBFF;
      serviceCenter_ = "";
      bitField0_ &= 0xF7FF;
      protocol_ = 0;
      bitField0_ &= 0xEFFF;
      replyPathPresent_ = false;
      bitField0_ &= 0xDFFF;
      locked_ = false;
      bitField0_ &= 0xBFFF;
      mxId_ = 0L;
      bitField0_ &= 0xFFFF7FFF;
      mxStatus_ = 0;
      bitField0_ &= 0xFFFEFFFF;
      return this;
    }
    
    public Builder clone()
    {
      return create().mergeFrom(buildPartial());
    }
    
    public SmsProtos.Sms getDefaultInstanceForType()
    {
      return SmsProtos.Sms.getDefaultInstance();
    }
    
    public final boolean isInitialized()
    {
      return true;
    }
    
    public Builder mergeFrom(SmsProtos.Sms paramSms)
    {
      if (paramSms == SmsProtos.Sms.getDefaultInstance()) {}
      do
      {
        return this;
        if (paramSms.hasGuid()) {
          setGuid(paramSms.getGuid());
        }
        if (paramSms.hasLuid()) {
          setLuid(paramSms.getLuid());
        }
        if (paramSms.hasType()) {
          setType(paramSms.getType());
        }
        if (paramSms.hasAddress()) {
          setAddress(paramSms.getAddress());
        }
        if (paramSms.hasSubject()) {
          setSubject(paramSms.getSubject());
        }
        if (paramSms.hasBody()) {
          setBody(paramSms.getBody());
        }
        if (paramSms.hasDate()) {
          setDate(paramSms.getDate());
        }
        if (paramSms.hasRead()) {
          setRead(paramSms.getRead());
        }
        if (paramSms.hasSeen()) {
          setSeen(paramSms.getSeen());
        }
        if (paramSms.hasStatus()) {
          setStatus(paramSms.getStatus());
        }
        if (paramSms.hasServerDate()) {
          setServerDate(paramSms.getServerDate());
        }
        if (paramSms.hasServiceCenter()) {
          setServiceCenter(paramSms.getServiceCenter());
        }
        if (paramSms.hasProtocol()) {
          setProtocol(paramSms.getProtocol());
        }
        if (paramSms.hasReplyPathPresent()) {
          setReplyPathPresent(paramSms.getReplyPathPresent());
        }
        if (paramSms.hasLocked()) {
          setLocked(paramSms.getLocked());
        }
        if (paramSms.hasMxId()) {
          setMxId(paramSms.getMxId());
        }
      } while (!paramSms.hasMxStatus());
      setMxStatus(paramSms.getMxStatus());
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
        case 24: 
          bitField0_ |= 0x4;
          type_ = paramCodedInputStream.readInt32();
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
          date_ = paramCodedInputStream.readInt64();
          break;
        case 64: 
          bitField0_ |= 0x80;
          read_ = paramCodedInputStream.readBool();
          break;
        case 72: 
          bitField0_ |= 0x100;
          seen_ = paramCodedInputStream.readBool();
          break;
        case 80: 
          bitField0_ |= 0x200;
          status_ = paramCodedInputStream.readInt32();
          break;
        case 88: 
          bitField0_ |= 0x400;
          serverDate_ = paramCodedInputStream.readInt64();
          break;
        case 98: 
          bitField0_ |= 0x800;
          serviceCenter_ = paramCodedInputStream.readBytes();
          break;
        case 104: 
          bitField0_ |= 0x1000;
          protocol_ = paramCodedInputStream.readInt32();
          break;
        case 112: 
          bitField0_ |= 0x2000;
          replyPathPresent_ = paramCodedInputStream.readBool();
          break;
        case 120: 
          bitField0_ |= 0x4000;
          locked_ = paramCodedInputStream.readBool();
          break;
        case 128: 
          bitField0_ |= 0x8000;
          mxId_ = paramCodedInputStream.readInt64();
          break;
        case 136: 
          bitField0_ |= 0x10000;
          mxStatus_ = paramCodedInputStream.readInt32();
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
    
    public Builder setDate(long paramLong)
    {
      bitField0_ |= 0x40;
      date_ = paramLong;
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
    
    public Builder setLocked(boolean paramBoolean)
    {
      bitField0_ |= 0x4000;
      locked_ = paramBoolean;
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
    
    public Builder setMxId(long paramLong)
    {
      bitField0_ |= 0x8000;
      mxId_ = paramLong;
      return this;
    }
    
    public Builder setMxStatus(int paramInt)
    {
      bitField0_ |= 0x10000;
      mxStatus_ = paramInt;
      return this;
    }
    
    public Builder setProtocol(int paramInt)
    {
      bitField0_ |= 0x1000;
      protocol_ = paramInt;
      return this;
    }
    
    public Builder setRead(boolean paramBoolean)
    {
      bitField0_ |= 0x80;
      read_ = paramBoolean;
      return this;
    }
    
    public Builder setReplyPathPresent(boolean paramBoolean)
    {
      bitField0_ |= 0x2000;
      replyPathPresent_ = paramBoolean;
      return this;
    }
    
    public Builder setSeen(boolean paramBoolean)
    {
      bitField0_ |= 0x100;
      seen_ = paramBoolean;
      return this;
    }
    
    public Builder setServerDate(long paramLong)
    {
      bitField0_ |= 0x400;
      serverDate_ = paramLong;
      return this;
    }
    
    public Builder setServiceCenter(String paramString)
    {
      if (paramString == null) {
        throw new NullPointerException();
      }
      bitField0_ |= 0x800;
      serviceCenter_ = paramString;
      return this;
    }
    
    public Builder setStatus(int paramInt)
    {
      bitField0_ |= 0x200;
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
    
    public Builder setType(int paramInt)
    {
      bitField0_ |= 0x4;
      type_ = paramInt;
      return this;
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.backup.SmsProtos.Sms
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */