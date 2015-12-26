package com.android.mms.backup;

import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite.Builder;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLite.Builder;
import com.google.protobuf.UninitializedMessageException;
import java.io.IOException;

public final class SyncRootProtos$SyncRoot$Builder
  extends GeneratedMessageLite.Builder<SyncRootProtos.SyncRoot, Builder>
  implements SyncRootProtos.SyncRootOrBuilder
{
  private int bitField0_;
  private MmsProtos.MmsCollection mmsCollection_ = MmsProtos.MmsCollection.getDefaultInstance();
  private SmsProtos.MmsSms mmsSms_ = SmsProtos.MmsSms.getDefaultInstance();
  
  private SyncRootProtos$SyncRoot$Builder()
  {
    maybeForceBuilderInitialization();
  }
  
  private SyncRootProtos.SyncRoot buildParsed()
    throws InvalidProtocolBufferException
  {
    SyncRootProtos.SyncRoot localSyncRoot = buildPartial();
    if (!localSyncRoot.isInitialized()) {
      throw newUninitializedMessageException(localSyncRoot).asInvalidProtocolBufferException();
    }
    return localSyncRoot;
  }
  
  private static Builder create()
  {
    return new Builder();
  }
  
  private void maybeForceBuilderInitialization() {}
  
  public SyncRootProtos.SyncRoot build()
  {
    SyncRootProtos.SyncRoot localSyncRoot = buildPartial();
    if (!localSyncRoot.isInitialized()) {
      throw newUninitializedMessageException(localSyncRoot);
    }
    return localSyncRoot;
  }
  
  public SyncRootProtos.SyncRoot buildPartial()
  {
    SyncRootProtos.SyncRoot localSyncRoot = new SyncRootProtos.SyncRoot(this, null);
    int k = bitField0_;
    int i = 0;
    if ((k & 0x1) == 1) {
      i = 0x0 | 0x1;
    }
    SyncRootProtos.SyncRoot.access$302(localSyncRoot, mmsSms_);
    int j = i;
    if ((k & 0x2) == 2) {
      j = i | 0x2;
    }
    SyncRootProtos.SyncRoot.access$402(localSyncRoot, mmsCollection_);
    SyncRootProtos.SyncRoot.access$502(localSyncRoot, j);
    return localSyncRoot;
  }
  
  public Builder clear()
  {
    super.clear();
    mmsSms_ = SmsProtos.MmsSms.getDefaultInstance();
    bitField0_ &= 0xFFFFFFFE;
    mmsCollection_ = MmsProtos.MmsCollection.getDefaultInstance();
    bitField0_ &= 0xFFFFFFFD;
    return this;
  }
  
  public Builder clone()
  {
    return create().mergeFrom(buildPartial());
  }
  
  public SyncRootProtos.SyncRoot getDefaultInstanceForType()
  {
    return SyncRootProtos.SyncRoot.getDefaultInstance();
  }
  
  public MmsProtos.MmsCollection getMmsCollection()
  {
    return mmsCollection_;
  }
  
  public SmsProtos.MmsSms getMmsSms()
  {
    return mmsSms_;
  }
  
  public boolean hasMmsCollection()
  {
    return (bitField0_ & 0x2) == 2;
  }
  
  public boolean hasMmsSms()
  {
    return (bitField0_ & 0x1) == 1;
  }
  
  public final boolean isInitialized()
  {
    return true;
  }
  
  public Builder mergeFrom(SyncRootProtos.SyncRoot paramSyncRoot)
  {
    if (paramSyncRoot == SyncRootProtos.SyncRoot.getDefaultInstance()) {}
    do
    {
      return this;
      if (paramSyncRoot.hasMmsSms()) {
        mergeMmsSms(paramSyncRoot.getMmsSms());
      }
    } while (!paramSyncRoot.hasMmsCollection());
    mergeMmsCollection(paramSyncRoot.getMmsCollection());
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
      case 34: 
        localObject = SmsProtos.MmsSms.newBuilder();
        if (hasMmsSms()) {
          ((SmsProtos.MmsSms.Builder)localObject).mergeFrom(getMmsSms());
        }
        paramCodedInputStream.readMessage((MessageLite.Builder)localObject, paramExtensionRegistryLite);
        setMmsSms(((SmsProtos.MmsSms.Builder)localObject).buildPartial());
        break;
      case 74: 
        localObject = MmsProtos.MmsCollection.newBuilder();
        if (hasMmsCollection()) {
          ((MmsProtos.MmsCollection.Builder)localObject).mergeFrom(getMmsCollection());
        }
        paramCodedInputStream.readMessage((MessageLite.Builder)localObject, paramExtensionRegistryLite);
        setMmsCollection(((MmsProtos.MmsCollection.Builder)localObject).buildPartial());
      }
    }
  }
  
  public Builder mergeMmsCollection(MmsProtos.MmsCollection paramMmsCollection)
  {
    if (((bitField0_ & 0x2) == 2) && (mmsCollection_ != MmsProtos.MmsCollection.getDefaultInstance())) {}
    for (mmsCollection_ = MmsProtos.MmsCollection.newBuilder(mmsCollection_).mergeFrom(paramMmsCollection).buildPartial();; mmsCollection_ = paramMmsCollection)
    {
      bitField0_ |= 0x2;
      return this;
    }
  }
  
  public Builder mergeMmsSms(SmsProtos.MmsSms paramMmsSms)
  {
    if (((bitField0_ & 0x1) == 1) && (mmsSms_ != SmsProtos.MmsSms.getDefaultInstance())) {}
    for (mmsSms_ = SmsProtos.MmsSms.newBuilder(mmsSms_).mergeFrom(paramMmsSms).buildPartial();; mmsSms_ = paramMmsSms)
    {
      bitField0_ |= 0x1;
      return this;
    }
  }
  
  public Builder setMmsCollection(MmsProtos.MmsCollection paramMmsCollection)
  {
    if (paramMmsCollection == null) {
      throw new NullPointerException();
    }
    mmsCollection_ = paramMmsCollection;
    bitField0_ |= 0x2;
    return this;
  }
  
  public Builder setMmsSms(SmsProtos.MmsSms paramMmsSms)
  {
    if (paramMmsSms == null) {
      throw new NullPointerException();
    }
    mmsSms_ = paramMmsSms;
    bitField0_ |= 0x1;
    return this;
  }
}

/* Location:
 * Qualified Name:     com.android.mms.backup.SyncRootProtos.SyncRoot.Builder
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */