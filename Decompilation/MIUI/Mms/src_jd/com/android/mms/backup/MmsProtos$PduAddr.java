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

public final class MmsProtos$PduAddr
  extends GeneratedMessageLite
  implements MmsProtos.PduAddrOrBuilder
{
  private static final PduAddr defaultInstance = new PduAddr(true);
  private static final long serialVersionUID = 0L;
  private Object address_;
  private int bitField0_;
  private int charset_;
  private byte memoizedIsInitialized = -1;
  private int memoizedSerializedSize = -1;
  private int type_;
  
  static
  {
    defaultInstance.initFields();
  }
  
  private MmsProtos$PduAddr(Builder paramBuilder)
  {
    super(paramBuilder);
  }
  
  private MmsProtos$PduAddr(boolean paramBoolean) {}
  
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
  
  public static PduAddr getDefaultInstance()
  {
    return defaultInstance;
  }
  
  private void initFields()
  {
    address_ = "";
    type_ = 0;
    charset_ = 0;
  }
  
  public static Builder newBuilder()
  {
    return Builder.access$4300();
  }
  
  public static Builder newBuilder(PduAddr paramPduAddr)
  {
    return newBuilder().mergeFrom(paramPduAddr);
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
  
  public int getCharset()
  {
    return charset_;
  }
  
  public PduAddr getDefaultInstanceForType()
  {
    return defaultInstance;
  }
  
  public int getSerializedSize()
  {
    int i = memoizedSerializedSize;
    if (i != -1) {
      return i;
    }
    int j = 0;
    if ((bitField0_ & 0x1) == 1) {
      j = 0 + CodedOutputStream.computeBytesSize(1, getAddressBytes());
    }
    i = j;
    if ((bitField0_ & 0x2) == 2) {
      i = j + CodedOutputStream.computeInt32Size(2, type_);
    }
    j = i;
    if ((bitField0_ & 0x4) == 4) {
      j = i + CodedOutputStream.computeInt32Size(3, charset_);
    }
    memoizedSerializedSize = j;
    return j;
  }
  
  public int getType()
  {
    return type_;
  }
  
  public boolean hasAddress()
  {
    return (bitField0_ & 0x1) == 1;
  }
  
  public boolean hasCharset()
  {
    return (bitField0_ & 0x4) == 4;
  }
  
  public boolean hasType()
  {
    return (bitField0_ & 0x2) == 2;
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
      paramCodedOutputStream.writeBytes(1, getAddressBytes());
    }
    if ((bitField0_ & 0x2) == 2) {
      paramCodedOutputStream.writeInt32(2, type_);
    }
    if ((bitField0_ & 0x4) == 4) {
      paramCodedOutputStream.writeInt32(3, charset_);
    }
  }
  
  public static final class Builder
    extends GeneratedMessageLite.Builder<MmsProtos.PduAddr, Builder>
    implements MmsProtos.PduAddrOrBuilder
  {
    private Object address_ = "";
    private int bitField0_;
    private int charset_;
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
    
    public MmsProtos.PduAddr build()
    {
      MmsProtos.PduAddr localPduAddr = buildPartial();
      if (!localPduAddr.isInitialized()) {
        throw newUninitializedMessageException(localPduAddr);
      }
      return localPduAddr;
    }
    
    public MmsProtos.PduAddr buildPartial()
    {
      MmsProtos.PduAddr localPduAddr = new MmsProtos.PduAddr(this, null);
      int k = bitField0_;
      int j = 0;
      if ((k & 0x1) == 1) {
        j = 0x0 | 0x1;
      }
      MmsProtos.PduAddr.access$4502(localPduAddr, address_);
      int i = j;
      if ((k & 0x2) == 2) {
        i = j | 0x2;
      }
      MmsProtos.PduAddr.access$4602(localPduAddr, type_);
      j = i;
      if ((k & 0x4) == 4) {
        j = i | 0x4;
      }
      MmsProtos.PduAddr.access$4702(localPduAddr, charset_);
      MmsProtos.PduAddr.access$4802(localPduAddr, j);
      return localPduAddr;
    }
    
    public Builder clear()
    {
      super.clear();
      address_ = "";
      bitField0_ &= 0xFFFFFFFE;
      type_ = 0;
      bitField0_ &= 0xFFFFFFFD;
      charset_ = 0;
      bitField0_ &= 0xFFFFFFFB;
      return this;
    }
    
    public Builder clone()
    {
      return create().mergeFrom(buildPartial());
    }
    
    public MmsProtos.PduAddr getDefaultInstanceForType()
    {
      return MmsProtos.PduAddr.getDefaultInstance();
    }
    
    public final boolean isInitialized()
    {
      return true;
    }
    
    public Builder mergeFrom(MmsProtos.PduAddr paramPduAddr)
    {
      if (paramPduAddr == MmsProtos.PduAddr.getDefaultInstance()) {}
      do
      {
        return this;
        if (paramPduAddr.hasAddress()) {
          setAddress(paramPduAddr.getAddress());
        }
        if (paramPduAddr.hasType()) {
          setType(paramPduAddr.getType());
        }
      } while (!paramPduAddr.hasCharset());
      setCharset(paramPduAddr.getCharset());
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
          address_ = paramCodedInputStream.readBytes();
          break;
        case 16: 
          bitField0_ |= 0x2;
          type_ = paramCodedInputStream.readInt32();
          break;
        case 24: 
          bitField0_ |= 0x4;
          charset_ = paramCodedInputStream.readInt32();
        }
      }
    }
    
    public Builder setAddress(String paramString)
    {
      if (paramString == null) {
        throw new NullPointerException();
      }
      bitField0_ |= 0x1;
      address_ = paramString;
      return this;
    }
    
    public Builder setCharset(int paramInt)
    {
      bitField0_ |= 0x4;
      charset_ = paramInt;
      return this;
    }
    
    public Builder setType(int paramInt)
    {
      bitField0_ |= 0x2;
      type_ = paramInt;
      return this;
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.backup.MmsProtos.PduAddr
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */