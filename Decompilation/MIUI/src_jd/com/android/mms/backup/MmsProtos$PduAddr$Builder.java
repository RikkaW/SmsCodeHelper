package com.android.mms.backup;

import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite.Builder;
import java.io.IOException;

public final class MmsProtos$PduAddr$Builder
  extends GeneratedMessageLite.Builder<MmsProtos.PduAddr, Builder>
  implements MmsProtos.PduAddrOrBuilder
{
  private Object address_ = "";
  private int bitField0_;
  private int charset_;
  private int type_;
  
  private MmsProtos$PduAddr$Builder()
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

/* Location:
 * Qualified Name:     com.android.mms.backup.MmsProtos.PduAddr.Builder
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */