package com.android.mms.backup;

import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.GeneratedMessageLite.Builder;
import com.google.protobuf.MessageLite;
import java.io.IOException;
import java.io.ObjectStreamException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class MmsProtos$MmsCollection
  extends GeneratedMessageLite
  implements MmsProtos.MmsCollectionOrBuilder
{
  private static final MmsCollection defaultInstance = new MmsCollection(true);
  private static final long serialVersionUID = 0L;
  private byte memoizedIsInitialized = -1;
  private int memoizedSerializedSize = -1;
  private List<MmsProtos.Pdu> pdus_;
  
  static
  {
    defaultInstance.initFields();
  }
  
  private MmsProtos$MmsCollection(Builder paramBuilder)
  {
    super(paramBuilder);
  }
  
  private MmsProtos$MmsCollection(boolean paramBoolean) {}
  
  public static MmsCollection getDefaultInstance()
  {
    return defaultInstance;
  }
  
  private void initFields()
  {
    pdus_ = Collections.emptyList();
  }
  
  public static Builder newBuilder()
  {
    return Builder.access$6600();
  }
  
  public static Builder newBuilder(MmsCollection paramMmsCollection)
  {
    return newBuilder().mergeFrom(paramMmsCollection);
  }
  
  public MmsCollection getDefaultInstanceForType()
  {
    return defaultInstance;
  }
  
  public List<MmsProtos.Pdu> getPdusList()
  {
    return pdus_;
  }
  
  public int getSerializedSize()
  {
    int i = memoizedSerializedSize;
    if (i != -1) {
      return i;
    }
    int j = 0;
    i = 0;
    while (i < pdus_.size())
    {
      j += CodedOutputStream.computeMessageSize(1, (MessageLite)pdus_.get(i));
      i += 1;
    }
    memoizedSerializedSize = j;
    return j;
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
    while (i < pdus_.size())
    {
      paramCodedOutputStream.writeMessage(1, (MessageLite)pdus_.get(i));
      i += 1;
    }
  }
  
  public static final class Builder
    extends GeneratedMessageLite.Builder<MmsProtos.MmsCollection, Builder>
    implements MmsProtos.MmsCollectionOrBuilder
  {
    private int bitField0_;
    private List<MmsProtos.Pdu> pdus_ = Collections.emptyList();
    
    private Builder()
    {
      maybeForceBuilderInitialization();
    }
    
    private static Builder create()
    {
      return new Builder();
    }
    
    private void ensurePdusIsMutable()
    {
      if ((bitField0_ & 0x1) != 1)
      {
        pdus_ = new ArrayList(pdus_);
        bitField0_ |= 0x1;
      }
    }
    
    private void maybeForceBuilderInitialization() {}
    
    public Builder addPdus(MmsProtos.Pdu paramPdu)
    {
      if (paramPdu == null) {
        throw new NullPointerException();
      }
      ensurePdusIsMutable();
      pdus_.add(paramPdu);
      return this;
    }
    
    public MmsProtos.MmsCollection build()
    {
      MmsProtos.MmsCollection localMmsCollection = buildPartial();
      if (!localMmsCollection.isInitialized()) {
        throw newUninitializedMessageException(localMmsCollection);
      }
      return localMmsCollection;
    }
    
    public MmsProtos.MmsCollection buildPartial()
    {
      MmsProtos.MmsCollection localMmsCollection = new MmsProtos.MmsCollection(this, null);
      int i = bitField0_;
      if ((bitField0_ & 0x1) == 1)
      {
        pdus_ = Collections.unmodifiableList(pdus_);
        bitField0_ &= 0xFFFFFFFE;
      }
      MmsProtos.MmsCollection.access$6802(localMmsCollection, pdus_);
      return localMmsCollection;
    }
    
    public Builder clear()
    {
      super.clear();
      pdus_ = Collections.emptyList();
      bitField0_ &= 0xFFFFFFFE;
      return this;
    }
    
    public Builder clone()
    {
      return create().mergeFrom(buildPartial());
    }
    
    public MmsProtos.MmsCollection getDefaultInstanceForType()
    {
      return MmsProtos.MmsCollection.getDefaultInstance();
    }
    
    public final boolean isInitialized()
    {
      return true;
    }
    
    public Builder mergeFrom(MmsProtos.MmsCollection paramMmsCollection)
    {
      if (paramMmsCollection == MmsProtos.MmsCollection.getDefaultInstance()) {}
      while (pdus_.isEmpty()) {
        return this;
      }
      if (pdus_.isEmpty())
      {
        pdus_ = pdus_;
        bitField0_ &= 0xFFFFFFFE;
        return this;
      }
      ensurePdusIsMutable();
      pdus_.addAll(pdus_);
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
          MmsProtos.Pdu.Builder localBuilder = MmsProtos.Pdu.newBuilder();
          paramCodedInputStream.readMessage(localBuilder, paramExtensionRegistryLite);
          addPdus(localBuilder.buildPartial());
        }
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.backup.MmsProtos.MmsCollection
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */