package com.android.mms.backup;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.GeneratedMessageLite.Builder;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLite;
import com.google.protobuf.MessageLite.Builder;
import com.google.protobuf.MessageLiteOrBuilder;
import java.io.IOException;
import java.io.ObjectStreamException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class MmsProtos
{
  public static final class MmsCollection
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
    
    private MmsCollection(Builder paramBuilder)
    {
      super();
    }
    
    private MmsCollection(boolean paramBoolean) {}
    
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
  
  public static abstract interface MmsCollectionOrBuilder
    extends MessageLiteOrBuilder
  {}
  
  public static final class Pdu
    extends GeneratedMessageLite
    implements MmsProtos.PduOrBuilder
  {
    private static final Pdu defaultInstance = new Pdu(true);
    private static final long serialVersionUID = 0L;
    private List<MmsProtos.PduAddr> addrs_;
    private int bitField0_;
    private int bitField1_;
    private int contentClass_;
    private Object contentLocation_;
    private Object contentType_;
    private int dateMsPart_;
    private long date_;
    private int deliveryReport_;
    private long deliveryTime_;
    private long expiry_;
    private Object guid_;
    private boolean locked_;
    private Object luid_;
    private Object mId_;
    private byte memoizedIsInitialized = -1;
    private int memoizedSerializedSize = -1;
    private int mmsVersion_;
    private int msgBox_;
    private Object msgClass_;
    private int msgSize_;
    private int msgType_;
    private long mxId_;
    private int mxStatus_;
    private List<MmsProtos.PduPart> pduParts_;
    private int priority_;
    private int readReport_;
    private int readStatus_;
    private boolean read_;
    private boolean reportAllowed_;
    private int responseStatus_;
    private Object responseText_;
    private int retrieveStatus_;
    private int retrieveTextCharset_;
    private Object retrieveText_;
    private boolean seen_;
    private long serverDate_;
    private int status_;
    private int subjectCharset_;
    private Object subject_;
    private Object transactionId_;
    
    static
    {
      defaultInstance.initFields();
    }
    
    private Pdu(Builder paramBuilder)
    {
      super();
    }
    
    private Pdu(boolean paramBoolean) {}
    
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
    
    public static Pdu getDefaultInstance()
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
    
    private ByteString getMIdBytes()
    {
      Object localObject = mId_;
      if ((localObject instanceof String))
      {
        localObject = ByteString.copyFromUtf8((String)localObject);
        mId_ = localObject;
        return (ByteString)localObject;
      }
      return (ByteString)localObject;
    }
    
    private ByteString getMsgClassBytes()
    {
      Object localObject = msgClass_;
      if ((localObject instanceof String))
      {
        localObject = ByteString.copyFromUtf8((String)localObject);
        msgClass_ = localObject;
        return (ByteString)localObject;
      }
      return (ByteString)localObject;
    }
    
    private ByteString getResponseTextBytes()
    {
      Object localObject = responseText_;
      if ((localObject instanceof String))
      {
        localObject = ByteString.copyFromUtf8((String)localObject);
        responseText_ = localObject;
        return (ByteString)localObject;
      }
      return (ByteString)localObject;
    }
    
    private ByteString getRetrieveTextBytes()
    {
      Object localObject = retrieveText_;
      if ((localObject instanceof String))
      {
        localObject = ByteString.copyFromUtf8((String)localObject);
        retrieveText_ = localObject;
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
    
    private ByteString getTransactionIdBytes()
    {
      Object localObject = transactionId_;
      if ((localObject instanceof String))
      {
        localObject = ByteString.copyFromUtf8((String)localObject);
        transactionId_ = localObject;
        return (ByteString)localObject;
      }
      return (ByteString)localObject;
    }
    
    private void initFields()
    {
      guid_ = "";
      luid_ = "";
      date_ = 0L;
      serverDate_ = 0L;
      msgBox_ = 0;
      read_ = false;
      mId_ = "";
      subject_ = "";
      subjectCharset_ = 0;
      contentType_ = "";
      contentLocation_ = "";
      expiry_ = 0L;
      msgClass_ = "";
      msgType_ = 0;
      mmsVersion_ = 0;
      msgSize_ = 0;
      priority_ = 0;
      readReport_ = 0;
      reportAllowed_ = false;
      responseStatus_ = 0;
      status_ = 0;
      transactionId_ = "";
      retrieveStatus_ = 0;
      retrieveText_ = "";
      retrieveTextCharset_ = 0;
      readStatus_ = 0;
      contentClass_ = 0;
      responseText_ = "";
      deliveryTime_ = 0L;
      deliveryReport_ = 0;
      locked_ = false;
      seen_ = false;
      addrs_ = Collections.emptyList();
      pduParts_ = Collections.emptyList();
      dateMsPart_ = 0;
      mxId_ = 0L;
      mxStatus_ = 0;
    }
    
    public static Builder newBuilder()
    {
      return Builder.access$100();
    }
    
    public static Builder newBuilder(Pdu paramPdu)
    {
      return newBuilder().mergeFrom(paramPdu);
    }
    
    public MmsProtos.PduAddr getAddrs(int paramInt)
    {
      return (MmsProtos.PduAddr)addrs_.get(paramInt);
    }
    
    public int getAddrsCount()
    {
      return addrs_.size();
    }
    
    public List<MmsProtos.PduAddr> getAddrsList()
    {
      return addrs_;
    }
    
    public int getContentClass()
    {
      return contentClass_;
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
    
    public long getDate()
    {
      return date_;
    }
    
    public int getDateMsPart()
    {
      return dateMsPart_;
    }
    
    public Pdu getDefaultInstanceForType()
    {
      return defaultInstance;
    }
    
    public int getDeliveryReport()
    {
      return deliveryReport_;
    }
    
    public long getDeliveryTime()
    {
      return deliveryTime_;
    }
    
    public long getExpiry()
    {
      return expiry_;
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
    
    public String getMId()
    {
      Object localObject = mId_;
      if ((localObject instanceof String)) {
        return (String)localObject;
      }
      localObject = (ByteString)localObject;
      String str = ((ByteString)localObject).toStringUtf8();
      if (Internal.isValidUtf8((ByteString)localObject)) {
        mId_ = str;
      }
      return str;
    }
    
    public int getMmsVersion()
    {
      return mmsVersion_;
    }
    
    public int getMsgBox()
    {
      return msgBox_;
    }
    
    public String getMsgClass()
    {
      Object localObject = msgClass_;
      if ((localObject instanceof String)) {
        return (String)localObject;
      }
      localObject = (ByteString)localObject;
      String str = ((ByteString)localObject).toStringUtf8();
      if (Internal.isValidUtf8((ByteString)localObject)) {
        msgClass_ = str;
      }
      return str;
    }
    
    public int getMsgSize()
    {
      return msgSize_;
    }
    
    public int getMsgType()
    {
      return msgType_;
    }
    
    public long getMxId()
    {
      return mxId_;
    }
    
    public int getMxStatus()
    {
      return mxStatus_;
    }
    
    public MmsProtos.PduPart getPduParts(int paramInt)
    {
      return (MmsProtos.PduPart)pduParts_.get(paramInt);
    }
    
    public int getPduPartsCount()
    {
      return pduParts_.size();
    }
    
    public int getPriority()
    {
      return priority_;
    }
    
    public boolean getRead()
    {
      return read_;
    }
    
    public int getReadReport()
    {
      return readReport_;
    }
    
    public int getReadStatus()
    {
      return readStatus_;
    }
    
    public boolean getReportAllowed()
    {
      return reportAllowed_;
    }
    
    public int getResponseStatus()
    {
      return responseStatus_;
    }
    
    public String getResponseText()
    {
      Object localObject = responseText_;
      if ((localObject instanceof String)) {
        return (String)localObject;
      }
      localObject = (ByteString)localObject;
      String str = ((ByteString)localObject).toStringUtf8();
      if (Internal.isValidUtf8((ByteString)localObject)) {
        responseText_ = str;
      }
      return str;
    }
    
    public int getRetrieveStatus()
    {
      return retrieveStatus_;
    }
    
    public String getRetrieveText()
    {
      Object localObject = retrieveText_;
      if ((localObject instanceof String)) {
        return (String)localObject;
      }
      localObject = (ByteString)localObject;
      String str = ((ByteString)localObject).toStringUtf8();
      if (Internal.isValidUtf8((ByteString)localObject)) {
        retrieveText_ = str;
      }
      return str;
    }
    
    public int getRetrieveTextCharset()
    {
      return retrieveTextCharset_;
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
        j = i + CodedOutputStream.computeInt64Size(3, date_);
      }
      i = j;
      if ((bitField0_ & 0x8) == 8) {
        i = j + CodedOutputStream.computeInt64Size(4, serverDate_);
      }
      j = i;
      if ((bitField0_ & 0x10) == 16) {
        j = i + CodedOutputStream.computeInt32Size(5, msgBox_);
      }
      i = j;
      if ((bitField0_ & 0x20) == 32) {
        i = j + CodedOutputStream.computeBoolSize(6, read_);
      }
      j = i;
      if ((bitField0_ & 0x40) == 64) {
        j = i + CodedOutputStream.computeBytesSize(7, getMIdBytes());
      }
      i = j;
      if ((bitField0_ & 0x80) == 128) {
        i = j + CodedOutputStream.computeBytesSize(8, getSubjectBytes());
      }
      j = i;
      if ((bitField0_ & 0x100) == 256) {
        j = i + CodedOutputStream.computeInt32Size(9, subjectCharset_);
      }
      i = j;
      if ((bitField0_ & 0x200) == 512) {
        i = j + CodedOutputStream.computeBytesSize(10, getContentTypeBytes());
      }
      j = i;
      if ((bitField0_ & 0x400) == 1024) {
        j = i + CodedOutputStream.computeBytesSize(11, getContentLocationBytes());
      }
      i = j;
      if ((bitField0_ & 0x800) == 2048) {
        i = j + CodedOutputStream.computeInt64Size(12, expiry_);
      }
      j = i;
      if ((bitField0_ & 0x1000) == 4096) {
        j = i + CodedOutputStream.computeBytesSize(13, getMsgClassBytes());
      }
      i = j;
      if ((bitField0_ & 0x2000) == 8192) {
        i = j + CodedOutputStream.computeInt32Size(14, msgType_);
      }
      j = i;
      if ((bitField0_ & 0x4000) == 16384) {
        j = i + CodedOutputStream.computeInt32Size(15, mmsVersion_);
      }
      i = j;
      if ((bitField0_ & 0x8000) == 32768) {
        i = j + CodedOutputStream.computeInt32Size(16, msgSize_);
      }
      j = i;
      if ((bitField0_ & 0x10000) == 65536) {
        j = i + CodedOutputStream.computeInt32Size(17, priority_);
      }
      i = j;
      if ((bitField0_ & 0x20000) == 131072) {
        i = j + CodedOutputStream.computeInt32Size(18, readReport_);
      }
      j = i;
      if ((bitField0_ & 0x40000) == 262144) {
        j = i + CodedOutputStream.computeBoolSize(19, reportAllowed_);
      }
      i = j;
      if ((bitField0_ & 0x80000) == 524288) {
        i = j + CodedOutputStream.computeInt32Size(20, responseStatus_);
      }
      j = i;
      if ((bitField0_ & 0x100000) == 1048576) {
        j = i + CodedOutputStream.computeInt32Size(21, status_);
      }
      i = j;
      if ((bitField0_ & 0x200000) == 2097152) {
        i = j + CodedOutputStream.computeBytesSize(22, getTransactionIdBytes());
      }
      j = i;
      if ((bitField0_ & 0x400000) == 4194304) {
        j = i + CodedOutputStream.computeInt32Size(23, retrieveStatus_);
      }
      i = j;
      if ((bitField0_ & 0x800000) == 8388608) {
        i = j + CodedOutputStream.computeBytesSize(24, getRetrieveTextBytes());
      }
      j = i;
      if ((bitField0_ & 0x1000000) == 16777216) {
        j = i + CodedOutputStream.computeInt32Size(25, retrieveTextCharset_);
      }
      i = j;
      if ((bitField0_ & 0x2000000) == 33554432) {
        i = j + CodedOutputStream.computeInt32Size(26, readStatus_);
      }
      j = i;
      if ((bitField0_ & 0x4000000) == 67108864) {
        j = i + CodedOutputStream.computeInt32Size(27, contentClass_);
      }
      i = j;
      if ((bitField0_ & 0x8000000) == 134217728) {
        i = j + CodedOutputStream.computeBytesSize(28, getResponseTextBytes());
      }
      j = i;
      if ((bitField0_ & 0x10000000) == 268435456) {
        j = i + CodedOutputStream.computeInt64Size(29, deliveryTime_);
      }
      i = j;
      if ((bitField0_ & 0x20000000) == 536870912) {
        i = j + CodedOutputStream.computeInt32Size(30, deliveryReport_);
      }
      j = i;
      if ((bitField0_ & 0x40000000) == 1073741824) {
        j = i + CodedOutputStream.computeBoolSize(31, locked_);
      }
      i = j;
      if ((bitField0_ & 0x80000000) == Integer.MIN_VALUE) {
        i = j + CodedOutputStream.computeBoolSize(32, seen_);
      }
      j = 0;
      while (j < addrs_.size())
      {
        i += CodedOutputStream.computeMessageSize(33, (MessageLite)addrs_.get(j));
        j += 1;
      }
      j = 0;
      while (j < pduParts_.size())
      {
        i += CodedOutputStream.computeMessageSize(34, (MessageLite)pduParts_.get(j));
        j += 1;
      }
      j = i;
      if ((bitField1_ & 0x1) == 1) {
        j = i + CodedOutputStream.computeInt32Size(35, dateMsPart_);
      }
      i = j;
      if ((bitField1_ & 0x2) == 2) {
        i = j + CodedOutputStream.computeInt64Size(36, mxId_);
      }
      j = i;
      if ((bitField1_ & 0x4) == 4) {
        j = i + CodedOutputStream.computeInt32Size(37, mxStatus_);
      }
      memoizedSerializedSize = j;
      return j;
    }
    
    public long getServerDate()
    {
      return serverDate_;
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
    
    public int getSubjectCharset()
    {
      return subjectCharset_;
    }
    
    public String getTransactionId()
    {
      Object localObject = transactionId_;
      if ((localObject instanceof String)) {
        return (String)localObject;
      }
      localObject = (ByteString)localObject;
      String str = ((ByteString)localObject).toStringUtf8();
      if (Internal.isValidUtf8((ByteString)localObject)) {
        transactionId_ = str;
      }
      return str;
    }
    
    public boolean hasContentClass()
    {
      return (bitField0_ & 0x4000000) == 67108864;
    }
    
    public boolean hasContentLocation()
    {
      return (bitField0_ & 0x400) == 1024;
    }
    
    public boolean hasContentType()
    {
      return (bitField0_ & 0x200) == 512;
    }
    
    public boolean hasDate()
    {
      return (bitField0_ & 0x4) == 4;
    }
    
    public boolean hasDateMsPart()
    {
      return (bitField1_ & 0x1) == 1;
    }
    
    public boolean hasDeliveryReport()
    {
      return (bitField0_ & 0x20000000) == 536870912;
    }
    
    public boolean hasDeliveryTime()
    {
      return (bitField0_ & 0x10000000) == 268435456;
    }
    
    public boolean hasExpiry()
    {
      return (bitField0_ & 0x800) == 2048;
    }
    
    public boolean hasGuid()
    {
      return (bitField0_ & 0x1) == 1;
    }
    
    public boolean hasLocked()
    {
      return (bitField0_ & 0x40000000) == 1073741824;
    }
    
    public boolean hasLuid()
    {
      return (bitField0_ & 0x2) == 2;
    }
    
    public boolean hasMId()
    {
      return (bitField0_ & 0x40) == 64;
    }
    
    public boolean hasMmsVersion()
    {
      return (bitField0_ & 0x4000) == 16384;
    }
    
    public boolean hasMsgBox()
    {
      return (bitField0_ & 0x10) == 16;
    }
    
    public boolean hasMsgClass()
    {
      return (bitField0_ & 0x1000) == 4096;
    }
    
    public boolean hasMsgSize()
    {
      return (bitField0_ & 0x8000) == 32768;
    }
    
    public boolean hasMsgType()
    {
      return (bitField0_ & 0x2000) == 8192;
    }
    
    public boolean hasMxId()
    {
      return (bitField1_ & 0x2) == 2;
    }
    
    public boolean hasMxStatus()
    {
      return (bitField1_ & 0x4) == 4;
    }
    
    public boolean hasPriority()
    {
      return (bitField0_ & 0x10000) == 65536;
    }
    
    public boolean hasRead()
    {
      return (bitField0_ & 0x20) == 32;
    }
    
    public boolean hasReadReport()
    {
      return (bitField0_ & 0x20000) == 131072;
    }
    
    public boolean hasReadStatus()
    {
      return (bitField0_ & 0x2000000) == 33554432;
    }
    
    public boolean hasReportAllowed()
    {
      return (bitField0_ & 0x40000) == 262144;
    }
    
    public boolean hasResponseStatus()
    {
      return (bitField0_ & 0x80000) == 524288;
    }
    
    public boolean hasResponseText()
    {
      return (bitField0_ & 0x8000000) == 134217728;
    }
    
    public boolean hasRetrieveStatus()
    {
      return (bitField0_ & 0x400000) == 4194304;
    }
    
    public boolean hasRetrieveText()
    {
      return (bitField0_ & 0x800000) == 8388608;
    }
    
    public boolean hasRetrieveTextCharset()
    {
      return (bitField0_ & 0x1000000) == 16777216;
    }
    
    public boolean hasSeen()
    {
      return (bitField0_ & 0x80000000) == Integer.MIN_VALUE;
    }
    
    public boolean hasServerDate()
    {
      return (bitField0_ & 0x8) == 8;
    }
    
    public boolean hasStatus()
    {
      return (bitField0_ & 0x100000) == 1048576;
    }
    
    public boolean hasSubject()
    {
      return (bitField0_ & 0x80) == 128;
    }
    
    public boolean hasSubjectCharset()
    {
      return (bitField0_ & 0x100) == 256;
    }
    
    public boolean hasTransactionId()
    {
      return (bitField0_ & 0x200000) == 2097152;
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
        paramCodedOutputStream.writeInt64(3, date_);
      }
      if ((bitField0_ & 0x8) == 8) {
        paramCodedOutputStream.writeInt64(4, serverDate_);
      }
      if ((bitField0_ & 0x10) == 16) {
        paramCodedOutputStream.writeInt32(5, msgBox_);
      }
      if ((bitField0_ & 0x20) == 32) {
        paramCodedOutputStream.writeBool(6, read_);
      }
      if ((bitField0_ & 0x40) == 64) {
        paramCodedOutputStream.writeBytes(7, getMIdBytes());
      }
      if ((bitField0_ & 0x80) == 128) {
        paramCodedOutputStream.writeBytes(8, getSubjectBytes());
      }
      if ((bitField0_ & 0x100) == 256) {
        paramCodedOutputStream.writeInt32(9, subjectCharset_);
      }
      if ((bitField0_ & 0x200) == 512) {
        paramCodedOutputStream.writeBytes(10, getContentTypeBytes());
      }
      if ((bitField0_ & 0x400) == 1024) {
        paramCodedOutputStream.writeBytes(11, getContentLocationBytes());
      }
      if ((bitField0_ & 0x800) == 2048) {
        paramCodedOutputStream.writeInt64(12, expiry_);
      }
      if ((bitField0_ & 0x1000) == 4096) {
        paramCodedOutputStream.writeBytes(13, getMsgClassBytes());
      }
      if ((bitField0_ & 0x2000) == 8192) {
        paramCodedOutputStream.writeInt32(14, msgType_);
      }
      if ((bitField0_ & 0x4000) == 16384) {
        paramCodedOutputStream.writeInt32(15, mmsVersion_);
      }
      if ((bitField0_ & 0x8000) == 32768) {
        paramCodedOutputStream.writeInt32(16, msgSize_);
      }
      if ((bitField0_ & 0x10000) == 65536) {
        paramCodedOutputStream.writeInt32(17, priority_);
      }
      if ((bitField0_ & 0x20000) == 131072) {
        paramCodedOutputStream.writeInt32(18, readReport_);
      }
      if ((bitField0_ & 0x40000) == 262144) {
        paramCodedOutputStream.writeBool(19, reportAllowed_);
      }
      if ((bitField0_ & 0x80000) == 524288) {
        paramCodedOutputStream.writeInt32(20, responseStatus_);
      }
      if ((bitField0_ & 0x100000) == 1048576) {
        paramCodedOutputStream.writeInt32(21, status_);
      }
      if ((bitField0_ & 0x200000) == 2097152) {
        paramCodedOutputStream.writeBytes(22, getTransactionIdBytes());
      }
      if ((bitField0_ & 0x400000) == 4194304) {
        paramCodedOutputStream.writeInt32(23, retrieveStatus_);
      }
      if ((bitField0_ & 0x800000) == 8388608) {
        paramCodedOutputStream.writeBytes(24, getRetrieveTextBytes());
      }
      if ((bitField0_ & 0x1000000) == 16777216) {
        paramCodedOutputStream.writeInt32(25, retrieveTextCharset_);
      }
      if ((bitField0_ & 0x2000000) == 33554432) {
        paramCodedOutputStream.writeInt32(26, readStatus_);
      }
      if ((bitField0_ & 0x4000000) == 67108864) {
        paramCodedOutputStream.writeInt32(27, contentClass_);
      }
      if ((bitField0_ & 0x8000000) == 134217728) {
        paramCodedOutputStream.writeBytes(28, getResponseTextBytes());
      }
      if ((bitField0_ & 0x10000000) == 268435456) {
        paramCodedOutputStream.writeInt64(29, deliveryTime_);
      }
      if ((bitField0_ & 0x20000000) == 536870912) {
        paramCodedOutputStream.writeInt32(30, deliveryReport_);
      }
      if ((bitField0_ & 0x40000000) == 1073741824) {
        paramCodedOutputStream.writeBool(31, locked_);
      }
      if ((bitField0_ & 0x80000000) == Integer.MIN_VALUE) {
        paramCodedOutputStream.writeBool(32, seen_);
      }
      int i = 0;
      while (i < addrs_.size())
      {
        paramCodedOutputStream.writeMessage(33, (MessageLite)addrs_.get(i));
        i += 1;
      }
      i = 0;
      while (i < pduParts_.size())
      {
        paramCodedOutputStream.writeMessage(34, (MessageLite)pduParts_.get(i));
        i += 1;
      }
      if ((bitField1_ & 0x1) == 1) {
        paramCodedOutputStream.writeInt32(35, dateMsPart_);
      }
      if ((bitField1_ & 0x2) == 2) {
        paramCodedOutputStream.writeInt64(36, mxId_);
      }
      if ((bitField1_ & 0x4) == 4) {
        paramCodedOutputStream.writeInt32(37, mxStatus_);
      }
    }
    
    public static final class Builder
      extends GeneratedMessageLite.Builder<MmsProtos.Pdu, Builder>
      implements MmsProtos.PduOrBuilder
    {
      private List<MmsProtos.PduAddr> addrs_ = Collections.emptyList();
      private int bitField0_;
      private int bitField1_;
      private int contentClass_;
      private Object contentLocation_ = "";
      private Object contentType_ = "";
      private int dateMsPart_;
      private long date_;
      private int deliveryReport_;
      private long deliveryTime_;
      private long expiry_;
      private Object guid_ = "";
      private boolean locked_;
      private Object luid_ = "";
      private Object mId_ = "";
      private int mmsVersion_;
      private int msgBox_;
      private Object msgClass_ = "";
      private int msgSize_;
      private int msgType_;
      private long mxId_;
      private int mxStatus_;
      private List<MmsProtos.PduPart> pduParts_ = Collections.emptyList();
      private int priority_;
      private int readReport_;
      private int readStatus_;
      private boolean read_;
      private boolean reportAllowed_;
      private int responseStatus_;
      private Object responseText_ = "";
      private int retrieveStatus_;
      private int retrieveTextCharset_;
      private Object retrieveText_ = "";
      private boolean seen_;
      private long serverDate_;
      private int status_;
      private int subjectCharset_;
      private Object subject_ = "";
      private Object transactionId_ = "";
      
      private Builder()
      {
        maybeForceBuilderInitialization();
      }
      
      private static Builder create()
      {
        return new Builder();
      }
      
      private void ensureAddrsIsMutable()
      {
        if ((bitField1_ & 0x1) != 1)
        {
          addrs_ = new ArrayList(addrs_);
          bitField1_ |= 0x1;
        }
      }
      
      private void ensurePduPartsIsMutable()
      {
        if ((bitField1_ & 0x2) != 2)
        {
          pduParts_ = new ArrayList(pduParts_);
          bitField1_ |= 0x2;
        }
      }
      
      private void maybeForceBuilderInitialization() {}
      
      public Builder addAddrs(MmsProtos.PduAddr paramPduAddr)
      {
        if (paramPduAddr == null) {
          throw new NullPointerException();
        }
        ensureAddrsIsMutable();
        addrs_.add(paramPduAddr);
        return this;
      }
      
      public Builder addPduParts(MmsProtos.PduPart paramPduPart)
      {
        if (paramPduPart == null) {
          throw new NullPointerException();
        }
        ensurePduPartsIsMutable();
        pduParts_.add(paramPduPart);
        return this;
      }
      
      public MmsProtos.Pdu build()
      {
        MmsProtos.Pdu localPdu = buildPartial();
        if (!localPdu.isInitialized()) {
          throw newUninitializedMessageException(localPdu);
        }
        return localPdu;
      }
      
      public MmsProtos.Pdu buildPartial()
      {
        MmsProtos.Pdu localPdu = new MmsProtos.Pdu(this, null);
        int i1 = bitField0_;
        int n = bitField1_;
        int j = 0;
        int m = 0;
        if ((i1 & 0x1) == 1) {
          j = 0x0 | 0x1;
        }
        MmsProtos.Pdu.access$302(localPdu, guid_);
        int i = j;
        if ((i1 & 0x2) == 2) {
          i = j | 0x2;
        }
        MmsProtos.Pdu.access$402(localPdu, luid_);
        j = i;
        if ((i1 & 0x4) == 4) {
          j = i | 0x4;
        }
        MmsProtos.Pdu.access$502(localPdu, date_);
        i = j;
        if ((i1 & 0x8) == 8) {
          i = j | 0x8;
        }
        MmsProtos.Pdu.access$602(localPdu, serverDate_);
        j = i;
        if ((i1 & 0x10) == 16) {
          j = i | 0x10;
        }
        MmsProtos.Pdu.access$702(localPdu, msgBox_);
        i = j;
        if ((i1 & 0x20) == 32) {
          i = j | 0x20;
        }
        MmsProtos.Pdu.access$802(localPdu, read_);
        j = i;
        if ((i1 & 0x40) == 64) {
          j = i | 0x40;
        }
        MmsProtos.Pdu.access$902(localPdu, mId_);
        i = j;
        if ((i1 & 0x80) == 128) {
          i = j | 0x80;
        }
        MmsProtos.Pdu.access$1002(localPdu, subject_);
        j = i;
        if ((i1 & 0x100) == 256) {
          j = i | 0x100;
        }
        MmsProtos.Pdu.access$1102(localPdu, subjectCharset_);
        i = j;
        if ((i1 & 0x200) == 512) {
          i = j | 0x200;
        }
        MmsProtos.Pdu.access$1202(localPdu, contentType_);
        j = i;
        if ((i1 & 0x400) == 1024) {
          j = i | 0x400;
        }
        MmsProtos.Pdu.access$1302(localPdu, contentLocation_);
        i = j;
        if ((i1 & 0x800) == 2048) {
          i = j | 0x800;
        }
        MmsProtos.Pdu.access$1402(localPdu, expiry_);
        j = i;
        if ((i1 & 0x1000) == 4096) {
          j = i | 0x1000;
        }
        MmsProtos.Pdu.access$1502(localPdu, msgClass_);
        i = j;
        if ((i1 & 0x2000) == 8192) {
          i = j | 0x2000;
        }
        MmsProtos.Pdu.access$1602(localPdu, msgType_);
        j = i;
        if ((i1 & 0x4000) == 16384) {
          j = i | 0x4000;
        }
        MmsProtos.Pdu.access$1702(localPdu, mmsVersion_);
        i = j;
        if ((i1 & 0x8000) == 32768) {
          i = j | 0x8000;
        }
        MmsProtos.Pdu.access$1802(localPdu, msgSize_);
        j = i;
        if ((i1 & 0x10000) == 65536) {
          j = i | 0x10000;
        }
        MmsProtos.Pdu.access$1902(localPdu, priority_);
        i = j;
        if ((i1 & 0x20000) == 131072) {
          i = j | 0x20000;
        }
        MmsProtos.Pdu.access$2002(localPdu, readReport_);
        j = i;
        if ((i1 & 0x40000) == 262144) {
          j = i | 0x40000;
        }
        MmsProtos.Pdu.access$2102(localPdu, reportAllowed_);
        i = j;
        if ((0x80000 & i1) == 524288) {
          i = j | 0x80000;
        }
        MmsProtos.Pdu.access$2202(localPdu, responseStatus_);
        j = i;
        if ((0x100000 & i1) == 1048576) {
          j = i | 0x100000;
        }
        MmsProtos.Pdu.access$2302(localPdu, status_);
        i = j;
        if ((0x200000 & i1) == 2097152) {
          i = j | 0x200000;
        }
        MmsProtos.Pdu.access$2402(localPdu, transactionId_);
        j = i;
        if ((0x400000 & i1) == 4194304) {
          j = i | 0x400000;
        }
        MmsProtos.Pdu.access$2502(localPdu, retrieveStatus_);
        i = j;
        if ((0x800000 & i1) == 8388608) {
          i = j | 0x800000;
        }
        MmsProtos.Pdu.access$2602(localPdu, retrieveText_);
        j = i;
        if ((0x1000000 & i1) == 16777216) {
          j = i | 0x1000000;
        }
        MmsProtos.Pdu.access$2702(localPdu, retrieveTextCharset_);
        i = j;
        if ((0x2000000 & i1) == 33554432) {
          i = j | 0x2000000;
        }
        MmsProtos.Pdu.access$2802(localPdu, readStatus_);
        j = i;
        if ((0x4000000 & i1) == 67108864) {
          j = i | 0x4000000;
        }
        MmsProtos.Pdu.access$2902(localPdu, contentClass_);
        i = j;
        if ((0x8000000 & i1) == 134217728) {
          i = j | 0x8000000;
        }
        MmsProtos.Pdu.access$3002(localPdu, responseText_);
        j = i;
        if ((0x10000000 & i1) == 268435456) {
          j = i | 0x10000000;
        }
        MmsProtos.Pdu.access$3102(localPdu, deliveryTime_);
        i = j;
        if ((0x20000000 & i1) == 536870912) {
          i = j | 0x20000000;
        }
        MmsProtos.Pdu.access$3202(localPdu, deliveryReport_);
        j = i;
        if ((0x40000000 & i1) == 1073741824) {
          j = i | 0x40000000;
        }
        MmsProtos.Pdu.access$3302(localPdu, locked_);
        int k = j;
        if ((i1 & 0x80000000) == Integer.MIN_VALUE) {
          k = j | 0x80000000;
        }
        MmsProtos.Pdu.access$3402(localPdu, seen_);
        if ((bitField1_ & 0x1) == 1)
        {
          addrs_ = Collections.unmodifiableList(addrs_);
          bitField1_ &= 0xFFFFFFFE;
        }
        MmsProtos.Pdu.access$3502(localPdu, addrs_);
        if ((bitField1_ & 0x2) == 2)
        {
          pduParts_ = Collections.unmodifiableList(pduParts_);
          bitField1_ &= 0xFFFFFFFD;
        }
        MmsProtos.Pdu.access$3602(localPdu, pduParts_);
        j = m;
        if ((n & 0x4) == 4) {
          j = 0x0 | 0x1;
        }
        MmsProtos.Pdu.access$3702(localPdu, dateMsPart_);
        i = j;
        if ((n & 0x8) == 8) {
          i = j | 0x2;
        }
        MmsProtos.Pdu.access$3802(localPdu, mxId_);
        j = i;
        if ((n & 0x10) == 16) {
          j = i | 0x4;
        }
        MmsProtos.Pdu.access$3902(localPdu, mxStatus_);
        MmsProtos.Pdu.access$4002(localPdu, k);
        MmsProtos.Pdu.access$4102(localPdu, j);
        return localPdu;
      }
      
      public Builder clear()
      {
        super.clear();
        guid_ = "";
        bitField0_ &= 0xFFFFFFFE;
        luid_ = "";
        bitField0_ &= 0xFFFFFFFD;
        date_ = 0L;
        bitField0_ &= 0xFFFFFFFB;
        serverDate_ = 0L;
        bitField0_ &= 0xFFFFFFF7;
        msgBox_ = 0;
        bitField0_ &= 0xFFFFFFEF;
        read_ = false;
        bitField0_ &= 0xFFFFFFDF;
        mId_ = "";
        bitField0_ &= 0xFFFFFFBF;
        subject_ = "";
        bitField0_ &= 0xFF7F;
        subjectCharset_ = 0;
        bitField0_ &= 0xFEFF;
        contentType_ = "";
        bitField0_ &= 0xFDFF;
        contentLocation_ = "";
        bitField0_ &= 0xFBFF;
        expiry_ = 0L;
        bitField0_ &= 0xF7FF;
        msgClass_ = "";
        bitField0_ &= 0xEFFF;
        msgType_ = 0;
        bitField0_ &= 0xDFFF;
        mmsVersion_ = 0;
        bitField0_ &= 0xBFFF;
        msgSize_ = 0;
        bitField0_ &= 0xFFFF7FFF;
        priority_ = 0;
        bitField0_ &= 0xFFFEFFFF;
        readReport_ = 0;
        bitField0_ &= 0xFFFDFFFF;
        reportAllowed_ = false;
        bitField0_ &= 0xFFFBFFFF;
        responseStatus_ = 0;
        bitField0_ &= 0xFFF7FFFF;
        status_ = 0;
        bitField0_ &= 0xFFEFFFFF;
        transactionId_ = "";
        bitField0_ &= 0xFFDFFFFF;
        retrieveStatus_ = 0;
        bitField0_ &= 0xFFBFFFFF;
        retrieveText_ = "";
        bitField0_ &= 0xFF7FFFFF;
        retrieveTextCharset_ = 0;
        bitField0_ &= 0xFEFFFFFF;
        readStatus_ = 0;
        bitField0_ &= 0xFDFFFFFF;
        contentClass_ = 0;
        bitField0_ &= 0xFBFFFFFF;
        responseText_ = "";
        bitField0_ &= 0xF7FFFFFF;
        deliveryTime_ = 0L;
        bitField0_ &= 0xEFFFFFFF;
        deliveryReport_ = 0;
        bitField0_ &= 0xDFFFFFFF;
        locked_ = false;
        bitField0_ &= 0xBFFFFFFF;
        seen_ = false;
        bitField0_ &= 0x7FFFFFFF;
        addrs_ = Collections.emptyList();
        bitField1_ &= 0xFFFFFFFE;
        pduParts_ = Collections.emptyList();
        bitField1_ &= 0xFFFFFFFD;
        dateMsPart_ = 0;
        bitField1_ &= 0xFFFFFFFB;
        mxId_ = 0L;
        bitField1_ &= 0xFFFFFFF7;
        mxStatus_ = 0;
        bitField1_ &= 0xFFFFFFEF;
        return this;
      }
      
      public Builder clone()
      {
        return create().mergeFrom(buildPartial());
      }
      
      public MmsProtos.Pdu getDefaultInstanceForType()
      {
        return MmsProtos.Pdu.getDefaultInstance();
      }
      
      public final boolean isInitialized()
      {
        return true;
      }
      
      public Builder mergeFrom(MmsProtos.Pdu paramPdu)
      {
        if (paramPdu == MmsProtos.Pdu.getDefaultInstance()) {
          return this;
        }
        if (paramPdu.hasGuid()) {
          setGuid(paramPdu.getGuid());
        }
        if (paramPdu.hasLuid()) {
          setLuid(paramPdu.getLuid());
        }
        if (paramPdu.hasDate()) {
          setDate(paramPdu.getDate());
        }
        if (paramPdu.hasServerDate()) {
          setServerDate(paramPdu.getServerDate());
        }
        if (paramPdu.hasMsgBox()) {
          setMsgBox(paramPdu.getMsgBox());
        }
        if (paramPdu.hasRead()) {
          setRead(paramPdu.getRead());
        }
        if (paramPdu.hasMId()) {
          setMId(paramPdu.getMId());
        }
        if (paramPdu.hasSubject()) {
          setSubject(paramPdu.getSubject());
        }
        if (paramPdu.hasSubjectCharset()) {
          setSubjectCharset(paramPdu.getSubjectCharset());
        }
        if (paramPdu.hasContentType()) {
          setContentType(paramPdu.getContentType());
        }
        if (paramPdu.hasContentLocation()) {
          setContentLocation(paramPdu.getContentLocation());
        }
        if (paramPdu.hasExpiry()) {
          setExpiry(paramPdu.getExpiry());
        }
        if (paramPdu.hasMsgClass()) {
          setMsgClass(paramPdu.getMsgClass());
        }
        if (paramPdu.hasMsgType()) {
          setMsgType(paramPdu.getMsgType());
        }
        if (paramPdu.hasMmsVersion()) {
          setMmsVersion(paramPdu.getMmsVersion());
        }
        if (paramPdu.hasMsgSize()) {
          setMsgSize(paramPdu.getMsgSize());
        }
        if (paramPdu.hasPriority()) {
          setPriority(paramPdu.getPriority());
        }
        if (paramPdu.hasReadReport()) {
          setReadReport(paramPdu.getReadReport());
        }
        if (paramPdu.hasReportAllowed()) {
          setReportAllowed(paramPdu.getReportAllowed());
        }
        if (paramPdu.hasResponseStatus()) {
          setResponseStatus(paramPdu.getResponseStatus());
        }
        if (paramPdu.hasStatus()) {
          setStatus(paramPdu.getStatus());
        }
        if (paramPdu.hasTransactionId()) {
          setTransactionId(paramPdu.getTransactionId());
        }
        if (paramPdu.hasRetrieveStatus()) {
          setRetrieveStatus(paramPdu.getRetrieveStatus());
        }
        if (paramPdu.hasRetrieveText()) {
          setRetrieveText(paramPdu.getRetrieveText());
        }
        if (paramPdu.hasRetrieveTextCharset()) {
          setRetrieveTextCharset(paramPdu.getRetrieveTextCharset());
        }
        if (paramPdu.hasReadStatus()) {
          setReadStatus(paramPdu.getReadStatus());
        }
        if (paramPdu.hasContentClass()) {
          setContentClass(paramPdu.getContentClass());
        }
        if (paramPdu.hasResponseText()) {
          setResponseText(paramPdu.getResponseText());
        }
        if (paramPdu.hasDeliveryTime()) {
          setDeliveryTime(paramPdu.getDeliveryTime());
        }
        if (paramPdu.hasDeliveryReport()) {
          setDeliveryReport(paramPdu.getDeliveryReport());
        }
        if (paramPdu.hasLocked()) {
          setLocked(paramPdu.getLocked());
        }
        if (paramPdu.hasSeen()) {
          setSeen(paramPdu.getSeen());
        }
        if (!addrs_.isEmpty())
        {
          if (addrs_.isEmpty())
          {
            addrs_ = addrs_;
            bitField1_ &= 0xFFFFFFFE;
          }
        }
        else {
          label564:
          if (!pduParts_.isEmpty())
          {
            if (!pduParts_.isEmpty()) {
              break label678;
            }
            pduParts_ = pduParts_;
            bitField1_ &= 0xFFFFFFFD;
          }
        }
        for (;;)
        {
          if (paramPdu.hasDateMsPart()) {
            setDateMsPart(paramPdu.getDateMsPart());
          }
          if (paramPdu.hasMxId()) {
            setMxId(paramPdu.getMxId());
          }
          if (!paramPdu.hasMxStatus()) {
            break;
          }
          setMxStatus(paramPdu.getMxStatus());
          return this;
          ensureAddrsIsMutable();
          addrs_.addAll(addrs_);
          break label564;
          label678:
          ensurePduPartsIsMutable();
          pduParts_.addAll(pduParts_);
        }
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
            bitField0_ |= 0x1;
            guid_ = paramCodedInputStream.readBytes();
            break;
          case 18: 
            bitField0_ |= 0x2;
            luid_ = paramCodedInputStream.readBytes();
            break;
          case 24: 
            bitField0_ |= 0x4;
            date_ = paramCodedInputStream.readInt64();
            break;
          case 32: 
            bitField0_ |= 0x8;
            serverDate_ = paramCodedInputStream.readInt64();
            break;
          case 40: 
            bitField0_ |= 0x10;
            msgBox_ = paramCodedInputStream.readInt32();
            break;
          case 48: 
            bitField0_ |= 0x20;
            read_ = paramCodedInputStream.readBool();
            break;
          case 58: 
            bitField0_ |= 0x40;
            mId_ = paramCodedInputStream.readBytes();
            break;
          case 66: 
            bitField0_ |= 0x80;
            subject_ = paramCodedInputStream.readBytes();
            break;
          case 72: 
            bitField0_ |= 0x100;
            subjectCharset_ = paramCodedInputStream.readInt32();
            break;
          case 82: 
            bitField0_ |= 0x200;
            contentType_ = paramCodedInputStream.readBytes();
            break;
          case 90: 
            bitField0_ |= 0x400;
            contentLocation_ = paramCodedInputStream.readBytes();
            break;
          case 96: 
            bitField0_ |= 0x800;
            expiry_ = paramCodedInputStream.readInt64();
            break;
          case 106: 
            bitField0_ |= 0x1000;
            msgClass_ = paramCodedInputStream.readBytes();
            break;
          case 112: 
            bitField0_ |= 0x2000;
            msgType_ = paramCodedInputStream.readInt32();
            break;
          case 120: 
            bitField0_ |= 0x4000;
            mmsVersion_ = paramCodedInputStream.readInt32();
            break;
          case 128: 
            bitField0_ |= 0x8000;
            msgSize_ = paramCodedInputStream.readInt32();
            break;
          case 136: 
            bitField0_ |= 0x10000;
            priority_ = paramCodedInputStream.readInt32();
            break;
          case 144: 
            bitField0_ |= 0x20000;
            readReport_ = paramCodedInputStream.readInt32();
            break;
          case 152: 
            bitField0_ |= 0x40000;
            reportAllowed_ = paramCodedInputStream.readBool();
            break;
          case 160: 
            bitField0_ |= 0x80000;
            responseStatus_ = paramCodedInputStream.readInt32();
            break;
          case 168: 
            bitField0_ |= 0x100000;
            status_ = paramCodedInputStream.readInt32();
            break;
          case 178: 
            bitField0_ |= 0x200000;
            transactionId_ = paramCodedInputStream.readBytes();
            break;
          case 184: 
            bitField0_ |= 0x400000;
            retrieveStatus_ = paramCodedInputStream.readInt32();
            break;
          case 194: 
            bitField0_ |= 0x800000;
            retrieveText_ = paramCodedInputStream.readBytes();
            break;
          case 200: 
            bitField0_ |= 0x1000000;
            retrieveTextCharset_ = paramCodedInputStream.readInt32();
            break;
          case 208: 
            bitField0_ |= 0x2000000;
            readStatus_ = paramCodedInputStream.readInt32();
            break;
          case 216: 
            bitField0_ |= 0x4000000;
            contentClass_ = paramCodedInputStream.readInt32();
            break;
          case 226: 
            bitField0_ |= 0x8000000;
            responseText_ = paramCodedInputStream.readBytes();
            break;
          case 232: 
            bitField0_ |= 0x10000000;
            deliveryTime_ = paramCodedInputStream.readInt64();
            break;
          case 240: 
            bitField0_ |= 0x20000000;
            deliveryReport_ = paramCodedInputStream.readInt32();
            break;
          case 248: 
            bitField0_ |= 0x40000000;
            locked_ = paramCodedInputStream.readBool();
            break;
          case 256: 
            bitField0_ |= 0x80000000;
            seen_ = paramCodedInputStream.readBool();
            break;
          case 266: 
            localObject = MmsProtos.PduAddr.newBuilder();
            paramCodedInputStream.readMessage((MessageLite.Builder)localObject, paramExtensionRegistryLite);
            addAddrs(((MmsProtos.PduAddr.Builder)localObject).buildPartial());
            break;
          case 274: 
            localObject = MmsProtos.PduPart.newBuilder();
            paramCodedInputStream.readMessage((MessageLite.Builder)localObject, paramExtensionRegistryLite);
            addPduParts(((MmsProtos.PduPart.Builder)localObject).buildPartial());
            break;
          case 280: 
            bitField1_ |= 0x4;
            dateMsPart_ = paramCodedInputStream.readInt32();
            break;
          case 288: 
            bitField1_ |= 0x8;
            mxId_ = paramCodedInputStream.readInt64();
            break;
          case 296: 
            bitField1_ |= 0x10;
            mxStatus_ = paramCodedInputStream.readInt32();
          }
        }
      }
      
      public Builder setContentClass(int paramInt)
      {
        bitField0_ |= 0x4000000;
        contentClass_ = paramInt;
        return this;
      }
      
      public Builder setContentLocation(String paramString)
      {
        if (paramString == null) {
          throw new NullPointerException();
        }
        bitField0_ |= 0x400;
        contentLocation_ = paramString;
        return this;
      }
      
      public Builder setContentType(String paramString)
      {
        if (paramString == null) {
          throw new NullPointerException();
        }
        bitField0_ |= 0x200;
        contentType_ = paramString;
        return this;
      }
      
      public Builder setDate(long paramLong)
      {
        bitField0_ |= 0x4;
        date_ = paramLong;
        return this;
      }
      
      public Builder setDateMsPart(int paramInt)
      {
        bitField1_ |= 0x4;
        dateMsPart_ = paramInt;
        return this;
      }
      
      public Builder setDeliveryReport(int paramInt)
      {
        bitField0_ |= 0x20000000;
        deliveryReport_ = paramInt;
        return this;
      }
      
      public Builder setDeliveryTime(long paramLong)
      {
        bitField0_ |= 0x10000000;
        deliveryTime_ = paramLong;
        return this;
      }
      
      public Builder setExpiry(long paramLong)
      {
        bitField0_ |= 0x800;
        expiry_ = paramLong;
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
        bitField0_ |= 0x40000000;
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
      
      public Builder setMId(String paramString)
      {
        if (paramString == null) {
          throw new NullPointerException();
        }
        bitField0_ |= 0x40;
        mId_ = paramString;
        return this;
      }
      
      public Builder setMmsVersion(int paramInt)
      {
        bitField0_ |= 0x4000;
        mmsVersion_ = paramInt;
        return this;
      }
      
      public Builder setMsgBox(int paramInt)
      {
        bitField0_ |= 0x10;
        msgBox_ = paramInt;
        return this;
      }
      
      public Builder setMsgClass(String paramString)
      {
        if (paramString == null) {
          throw new NullPointerException();
        }
        bitField0_ |= 0x1000;
        msgClass_ = paramString;
        return this;
      }
      
      public Builder setMsgSize(int paramInt)
      {
        bitField0_ |= 0x8000;
        msgSize_ = paramInt;
        return this;
      }
      
      public Builder setMsgType(int paramInt)
      {
        bitField0_ |= 0x2000;
        msgType_ = paramInt;
        return this;
      }
      
      public Builder setMxId(long paramLong)
      {
        bitField1_ |= 0x8;
        mxId_ = paramLong;
        return this;
      }
      
      public Builder setMxStatus(int paramInt)
      {
        bitField1_ |= 0x10;
        mxStatus_ = paramInt;
        return this;
      }
      
      public Builder setPriority(int paramInt)
      {
        bitField0_ |= 0x10000;
        priority_ = paramInt;
        return this;
      }
      
      public Builder setRead(boolean paramBoolean)
      {
        bitField0_ |= 0x20;
        read_ = paramBoolean;
        return this;
      }
      
      public Builder setReadReport(int paramInt)
      {
        bitField0_ |= 0x20000;
        readReport_ = paramInt;
        return this;
      }
      
      public Builder setReadStatus(int paramInt)
      {
        bitField0_ |= 0x2000000;
        readStatus_ = paramInt;
        return this;
      }
      
      public Builder setReportAllowed(boolean paramBoolean)
      {
        bitField0_ |= 0x40000;
        reportAllowed_ = paramBoolean;
        return this;
      }
      
      public Builder setResponseStatus(int paramInt)
      {
        bitField0_ |= 0x80000;
        responseStatus_ = paramInt;
        return this;
      }
      
      public Builder setResponseText(String paramString)
      {
        if (paramString == null) {
          throw new NullPointerException();
        }
        bitField0_ |= 0x8000000;
        responseText_ = paramString;
        return this;
      }
      
      public Builder setRetrieveStatus(int paramInt)
      {
        bitField0_ |= 0x400000;
        retrieveStatus_ = paramInt;
        return this;
      }
      
      public Builder setRetrieveText(String paramString)
      {
        if (paramString == null) {
          throw new NullPointerException();
        }
        bitField0_ |= 0x800000;
        retrieveText_ = paramString;
        return this;
      }
      
      public Builder setRetrieveTextCharset(int paramInt)
      {
        bitField0_ |= 0x1000000;
        retrieveTextCharset_ = paramInt;
        return this;
      }
      
      public Builder setSeen(boolean paramBoolean)
      {
        bitField0_ |= 0x80000000;
        seen_ = paramBoolean;
        return this;
      }
      
      public Builder setServerDate(long paramLong)
      {
        bitField0_ |= 0x8;
        serverDate_ = paramLong;
        return this;
      }
      
      public Builder setStatus(int paramInt)
      {
        bitField0_ |= 0x100000;
        status_ = paramInt;
        return this;
      }
      
      public Builder setSubject(String paramString)
      {
        if (paramString == null) {
          throw new NullPointerException();
        }
        bitField0_ |= 0x80;
        subject_ = paramString;
        return this;
      }
      
      public Builder setSubjectCharset(int paramInt)
      {
        bitField0_ |= 0x100;
        subjectCharset_ = paramInt;
        return this;
      }
      
      public Builder setTransactionId(String paramString)
      {
        if (paramString == null) {
          throw new NullPointerException();
        }
        bitField0_ |= 0x200000;
        transactionId_ = paramString;
        return this;
      }
    }
  }
  
  public static final class PduAddr
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
    
    private PduAddr(Builder paramBuilder)
    {
      super();
    }
    
    private PduAddr(boolean paramBoolean) {}
    
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
  
  public static abstract interface PduAddrOrBuilder
    extends MessageLiteOrBuilder
  {}
  
  public static abstract interface PduOrBuilder
    extends MessageLiteOrBuilder
  {}
  
  public static final class PduPart
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
    
    private PduPart(Builder paramBuilder)
    {
      super();
    }
    
    private PduPart(boolean paramBoolean) {}
    
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
  
  public static abstract interface PduPartOrBuilder
    extends MessageLiteOrBuilder
  {}
}

/* Location:
 * Qualified Name:     com.android.mms.backup.MmsProtos
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */