package com.android.mms.backup;

import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite.Builder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class MmsProtos$MmsCollection$Builder
  extends GeneratedMessageLite.Builder<MmsProtos.MmsCollection, Builder>
  implements MmsProtos.MmsCollectionOrBuilder
{
  private int bitField0_;
  private List<MmsProtos.Pdu> pdus_ = Collections.emptyList();
  
  private MmsProtos$MmsCollection$Builder()
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
    while (MmsProtos.MmsCollection.access$6800(paramMmsCollection).isEmpty()) {
      return this;
    }
    if (pdus_.isEmpty())
    {
      pdus_ = MmsProtos.MmsCollection.access$6800(paramMmsCollection);
      bitField0_ &= 0xFFFFFFFE;
      return this;
    }
    ensurePdusIsMutable();
    pdus_.addAll(MmsProtos.MmsCollection.access$6800(paramMmsCollection));
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

/* Location:
 * Qualified Name:     com.android.mms.backup.MmsProtos.MmsCollection.Builder
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */