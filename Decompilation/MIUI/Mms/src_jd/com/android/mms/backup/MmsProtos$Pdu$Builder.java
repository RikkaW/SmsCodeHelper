package com.android.mms.backup;

import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite.Builder;
import com.google.protobuf.MessageLite.Builder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class MmsProtos$Pdu$Builder
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
  
  private MmsProtos$Pdu$Builder()
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
    if (!MmsProtos.Pdu.access$3500(paramPdu).isEmpty())
    {
      if (addrs_.isEmpty())
      {
        addrs_ = MmsProtos.Pdu.access$3500(paramPdu);
        bitField1_ &= 0xFFFFFFFE;
      }
    }
    else {
      label564:
      if (!MmsProtos.Pdu.access$3600(paramPdu).isEmpty())
      {
        if (!pduParts_.isEmpty()) {
          break label678;
        }
        pduParts_ = MmsProtos.Pdu.access$3600(paramPdu);
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
      addrs_.addAll(MmsProtos.Pdu.access$3500(paramPdu));
      break label564;
      label678:
      ensurePduPartsIsMutable();
      pduParts_.addAll(MmsProtos.Pdu.access$3600(paramPdu));
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

/* Location:
 * Qualified Name:     com.android.mms.backup.MmsProtos.Pdu.Builder
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */