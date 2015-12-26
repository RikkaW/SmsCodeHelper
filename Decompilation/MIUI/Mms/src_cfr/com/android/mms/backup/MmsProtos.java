/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  com.google.protobuf.AbstractMessageLite
 *  com.google.protobuf.AbstractMessageLite$Builder
 *  com.google.protobuf.ByteString
 *  com.google.protobuf.CodedInputStream
 *  com.google.protobuf.CodedOutputStream
 *  com.google.protobuf.ExtensionRegistryLite
 *  com.google.protobuf.GeneratedMessageLite
 *  com.google.protobuf.GeneratedMessageLite$Builder
 *  com.google.protobuf.Internal
 *  com.google.protobuf.MessageLite
 *  com.google.protobuf.MessageLite$Builder
 *  com.google.protobuf.MessageLiteOrBuilder
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 *  java.util.Collections
 */
package com.android.mms.backup;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.MessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import java.io.IOException;
import java.io.ObjectStreamException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public final class MmsProtos {
    private MmsProtos() {
    }

    public static final class MmsCollection
    extends GeneratedMessageLite
    implements MmsCollectionOrBuilder {
        private static final MmsCollection defaultInstance = new MmsCollection(true);
        private static final long serialVersionUID = 0;
        private byte memoizedIsInitialized = -1;
        private int memoizedSerializedSize = -1;
        private List<Pdu> pdus_;

        static {
            defaultInstance.initFields();
        }

        private MmsCollection(Builder builder) {
            super((GeneratedMessageLite.Builder)builder);
        }

        private MmsCollection(boolean bl) {
        }

        public static MmsCollection getDefaultInstance() {
            return defaultInstance;
        }

        private void initFields() {
            this.pdus_ = Collections.emptyList();
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public static Builder newBuilder(MmsCollection mmsCollection) {
            return MmsCollection.newBuilder().mergeFrom(mmsCollection);
        }

        public MmsCollection getDefaultInstanceForType() {
            return defaultInstance;
        }

        public List<Pdu> getPdusList() {
            return this.pdus_;
        }

        public int getSerializedSize() {
            int n = this.memoizedSerializedSize;
            if (n != -1) {
                return n;
            }
            int n2 = 0;
            for (n = 0; n < this.pdus_.size(); ++n) {
                n2 += CodedOutputStream.computeMessageSize((int)1, (MessageLite)((MessageLite)this.pdus_.get(n)));
            }
            this.memoizedSerializedSize = n2;
            return n2;
        }

        public final boolean isInitialized() {
            byte by = this.memoizedIsInitialized;
            if (by != -1) {
                if (by == 1) {
                    return true;
                }
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        public Builder newBuilderForType() {
            return MmsCollection.newBuilder();
        }

        public Builder toBuilder() {
            return MmsCollection.newBuilder(this);
        }

        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            this.getSerializedSize();
            for (int i = 0; i < this.pdus_.size(); ++i) {
                codedOutputStream.writeMessage(1, (MessageLite)this.pdus_.get(i));
            }
        }

        public static final class Builder
        extends GeneratedMessageLite.Builder<MmsCollection, Builder>
        implements MmsCollectionOrBuilder {
            private int bitField0_;
            private List<Pdu> pdus_ = Collections.emptyList();

            private Builder() {
                this.maybeForceBuilderInitialization();
            }

            private static Builder create() {
                return new Builder();
            }

            private void ensurePdusIsMutable() {
                if ((this.bitField0_ & 1) != 1) {
                    this.pdus_ = new ArrayList(this.pdus_);
                    this.bitField0_ |= 1;
                }
            }

            private void maybeForceBuilderInitialization() {
            }

            public Builder addPdus(Pdu pdu) {
                if (pdu == null) {
                    throw new NullPointerException();
                }
                this.ensurePdusIsMutable();
                this.pdus_.add(pdu);
                return this;
            }

            public MmsCollection build() {
                MmsCollection mmsCollection = this.buildPartial();
                if (!mmsCollection.isInitialized()) {
                    throw Builder.newUninitializedMessageException((MessageLite)mmsCollection);
                }
                return mmsCollection;
            }

            public MmsCollection buildPartial() {
                MmsCollection mmsCollection = new MmsCollection(this);
                int n = this.bitField0_;
                if ((this.bitField0_ & 1) == 1) {
                    this.pdus_ = Collections.unmodifiableList(this.pdus_);
                    this.bitField0_ &= -2;
                }
                mmsCollection.pdus_ = this.pdus_;
                return mmsCollection;
            }

            public Builder clear() {
                super.clear();
                this.pdus_ = Collections.emptyList();
                this.bitField0_ &= -2;
                return this;
            }

            public Builder clone() {
                return Builder.create().mergeFrom(this.buildPartial());
            }

            public MmsCollection getDefaultInstanceForType() {
                return MmsCollection.getDefaultInstance();
            }

            public final boolean isInitialized() {
                return true;
            }

            /*
             * Enabled aggressive block sorting
             */
            public Builder mergeFrom(MmsCollection mmsCollection) {
                if (mmsCollection == MmsCollection.getDefaultInstance() || mmsCollection.pdus_.isEmpty()) {
                    return this;
                }
                if (this.pdus_.isEmpty()) {
                    this.pdus_ = mmsCollection.pdus_;
                    this.bitField0_ &= -2;
                    return this;
                }
                this.ensurePdusIsMutable();
                this.pdus_.addAll(mmsCollection.pdus_);
                return this;
            }

            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                block4 : do {
                    int n = codedInputStream.readTag();
                    switch (n) {
                        default: {
                            if (this.parseUnknownField(codedInputStream, extensionRegistryLite, n)) continue block4;
                        }
                        case 0: {
                            return this;
                        }
                        case 10: 
                    }
                    Pdu.Builder builder = Pdu.newBuilder();
                    codedInputStream.readMessage((MessageLite.Builder)builder, extensionRegistryLite);
                    this.addPdus(builder.buildPartial());
                } while (true);
            }
        }

    }

    public static interface MmsCollectionOrBuilder
    extends MessageLiteOrBuilder {
    }

    public static final class Pdu
    extends GeneratedMessageLite
    implements PduOrBuilder {
        private static final Pdu defaultInstance = new Pdu(true);
        private static final long serialVersionUID = 0;
        private List<PduAddr> addrs_;
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
        private List<PduPart> pduParts_;
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

        static {
            defaultInstance.initFields();
        }

        private Pdu(Builder builder) {
            super((GeneratedMessageLite.Builder)builder);
        }

        private Pdu(boolean bl) {
        }

        private ByteString getContentLocationBytes() {
            Object object = this.contentLocation_;
            if (object instanceof String) {
                this.contentLocation_ = object = ByteString.copyFromUtf8((String)((String)object));
                return object;
            }
            return (ByteString)object;
        }

        private ByteString getContentTypeBytes() {
            Object object = this.contentType_;
            if (object instanceof String) {
                this.contentType_ = object = ByteString.copyFromUtf8((String)((String)object));
                return object;
            }
            return (ByteString)object;
        }

        public static Pdu getDefaultInstance() {
            return defaultInstance;
        }

        private ByteString getGuidBytes() {
            Object object = this.guid_;
            if (object instanceof String) {
                this.guid_ = object = ByteString.copyFromUtf8((String)((String)object));
                return object;
            }
            return (ByteString)object;
        }

        private ByteString getLuidBytes() {
            Object object = this.luid_;
            if (object instanceof String) {
                this.luid_ = object = ByteString.copyFromUtf8((String)((String)object));
                return object;
            }
            return (ByteString)object;
        }

        private ByteString getMIdBytes() {
            Object object = this.mId_;
            if (object instanceof String) {
                this.mId_ = object = ByteString.copyFromUtf8((String)((String)object));
                return object;
            }
            return (ByteString)object;
        }

        private ByteString getMsgClassBytes() {
            Object object = this.msgClass_;
            if (object instanceof String) {
                this.msgClass_ = object = ByteString.copyFromUtf8((String)((String)object));
                return object;
            }
            return (ByteString)object;
        }

        private ByteString getResponseTextBytes() {
            Object object = this.responseText_;
            if (object instanceof String) {
                this.responseText_ = object = ByteString.copyFromUtf8((String)((String)object));
                return object;
            }
            return (ByteString)object;
        }

        private ByteString getRetrieveTextBytes() {
            Object object = this.retrieveText_;
            if (object instanceof String) {
                this.retrieveText_ = object = ByteString.copyFromUtf8((String)((String)object));
                return object;
            }
            return (ByteString)object;
        }

        private ByteString getSubjectBytes() {
            Object object = this.subject_;
            if (object instanceof String) {
                this.subject_ = object = ByteString.copyFromUtf8((String)((String)object));
                return object;
            }
            return (ByteString)object;
        }

        private ByteString getTransactionIdBytes() {
            Object object = this.transactionId_;
            if (object instanceof String) {
                this.transactionId_ = object = ByteString.copyFromUtf8((String)((String)object));
                return object;
            }
            return (ByteString)object;
        }

        private void initFields() {
            this.guid_ = "";
            this.luid_ = "";
            this.date_ = 0;
            this.serverDate_ = 0;
            this.msgBox_ = 0;
            this.read_ = false;
            this.mId_ = "";
            this.subject_ = "";
            this.subjectCharset_ = 0;
            this.contentType_ = "";
            this.contentLocation_ = "";
            this.expiry_ = 0;
            this.msgClass_ = "";
            this.msgType_ = 0;
            this.mmsVersion_ = 0;
            this.msgSize_ = 0;
            this.priority_ = 0;
            this.readReport_ = 0;
            this.reportAllowed_ = false;
            this.responseStatus_ = 0;
            this.status_ = 0;
            this.transactionId_ = "";
            this.retrieveStatus_ = 0;
            this.retrieveText_ = "";
            this.retrieveTextCharset_ = 0;
            this.readStatus_ = 0;
            this.contentClass_ = 0;
            this.responseText_ = "";
            this.deliveryTime_ = 0;
            this.deliveryReport_ = 0;
            this.locked_ = false;
            this.seen_ = false;
            this.addrs_ = Collections.emptyList();
            this.pduParts_ = Collections.emptyList();
            this.dateMsPart_ = 0;
            this.mxId_ = 0;
            this.mxStatus_ = 0;
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public static Builder newBuilder(Pdu pdu) {
            return Pdu.newBuilder().mergeFrom(pdu);
        }

        public PduAddr getAddrs(int n) {
            return this.addrs_.get(n);
        }

        public int getAddrsCount() {
            return this.addrs_.size();
        }

        public List<PduAddr> getAddrsList() {
            return this.addrs_;
        }

        public int getContentClass() {
            return this.contentClass_;
        }

        public String getContentLocation() {
            Object object = this.contentLocation_;
            if (object instanceof String) {
                return (String)object;
            }
            object = (ByteString)object;
            String string = object.toStringUtf8();
            if (Internal.isValidUtf8((ByteString)object)) {
                this.contentLocation_ = string;
            }
            return string;
        }

        public String getContentType() {
            Object object = this.contentType_;
            if (object instanceof String) {
                return (String)object;
            }
            object = (ByteString)object;
            String string = object.toStringUtf8();
            if (Internal.isValidUtf8((ByteString)object)) {
                this.contentType_ = string;
            }
            return string;
        }

        public long getDate() {
            return this.date_;
        }

        public int getDateMsPart() {
            return this.dateMsPart_;
        }

        public Pdu getDefaultInstanceForType() {
            return defaultInstance;
        }

        public int getDeliveryReport() {
            return this.deliveryReport_;
        }

        public long getDeliveryTime() {
            return this.deliveryTime_;
        }

        public long getExpiry() {
            return this.expiry_;
        }

        public String getGuid() {
            Object object = this.guid_;
            if (object instanceof String) {
                return (String)object;
            }
            object = (ByteString)object;
            String string = object.toStringUtf8();
            if (Internal.isValidUtf8((ByteString)object)) {
                this.guid_ = string;
            }
            return string;
        }

        public boolean getLocked() {
            return this.locked_;
        }

        public String getLuid() {
            Object object = this.luid_;
            if (object instanceof String) {
                return (String)object;
            }
            object = (ByteString)object;
            String string = object.toStringUtf8();
            if (Internal.isValidUtf8((ByteString)object)) {
                this.luid_ = string;
            }
            return string;
        }

        public String getMId() {
            Object object = this.mId_;
            if (object instanceof String) {
                return (String)object;
            }
            object = (ByteString)object;
            String string = object.toStringUtf8();
            if (Internal.isValidUtf8((ByteString)object)) {
                this.mId_ = string;
            }
            return string;
        }

        public int getMmsVersion() {
            return this.mmsVersion_;
        }

        public int getMsgBox() {
            return this.msgBox_;
        }

        public String getMsgClass() {
            Object object = this.msgClass_;
            if (object instanceof String) {
                return (String)object;
            }
            object = (ByteString)object;
            String string = object.toStringUtf8();
            if (Internal.isValidUtf8((ByteString)object)) {
                this.msgClass_ = string;
            }
            return string;
        }

        public int getMsgSize() {
            return this.msgSize_;
        }

        public int getMsgType() {
            return this.msgType_;
        }

        public long getMxId() {
            return this.mxId_;
        }

        public int getMxStatus() {
            return this.mxStatus_;
        }

        public PduPart getPduParts(int n) {
            return this.pduParts_.get(n);
        }

        public int getPduPartsCount() {
            return this.pduParts_.size();
        }

        public int getPriority() {
            return this.priority_;
        }

        public boolean getRead() {
            return this.read_;
        }

        public int getReadReport() {
            return this.readReport_;
        }

        public int getReadStatus() {
            return this.readStatus_;
        }

        public boolean getReportAllowed() {
            return this.reportAllowed_;
        }

        public int getResponseStatus() {
            return this.responseStatus_;
        }

        public String getResponseText() {
            Object object = this.responseText_;
            if (object instanceof String) {
                return (String)object;
            }
            object = (ByteString)object;
            String string = object.toStringUtf8();
            if (Internal.isValidUtf8((ByteString)object)) {
                this.responseText_ = string;
            }
            return string;
        }

        public int getRetrieveStatus() {
            return this.retrieveStatus_;
        }

        public String getRetrieveText() {
            Object object = this.retrieveText_;
            if (object instanceof String) {
                return (String)object;
            }
            object = (ByteString)object;
            String string = object.toStringUtf8();
            if (Internal.isValidUtf8((ByteString)object)) {
                this.retrieveText_ = string;
            }
            return string;
        }

        public int getRetrieveTextCharset() {
            return this.retrieveTextCharset_;
        }

        public boolean getSeen() {
            return this.seen_;
        }

        public int getSerializedSize() {
            int n = this.memoizedSerializedSize;
            if (n != -1) {
                return n;
            }
            int n2 = 0;
            if ((this.bitField0_ & 1) == 1) {
                n2 = 0 + CodedOutputStream.computeBytesSize((int)1, (ByteString)this.getGuidBytes());
            }
            n = n2;
            if ((this.bitField0_ & 2) == 2) {
                n = n2 + CodedOutputStream.computeBytesSize((int)2, (ByteString)this.getLuidBytes());
            }
            n2 = n;
            if ((this.bitField0_ & 4) == 4) {
                n2 = n + CodedOutputStream.computeInt64Size((int)3, (long)this.date_);
            }
            n = n2;
            if ((this.bitField0_ & 8) == 8) {
                n = n2 + CodedOutputStream.computeInt64Size((int)4, (long)this.serverDate_);
            }
            n2 = n;
            if ((this.bitField0_ & 16) == 16) {
                n2 = n + CodedOutputStream.computeInt32Size((int)5, (int)this.msgBox_);
            }
            n = n2;
            if ((this.bitField0_ & 32) == 32) {
                n = n2 + CodedOutputStream.computeBoolSize((int)6, (boolean)this.read_);
            }
            n2 = n;
            if ((this.bitField0_ & 64) == 64) {
                n2 = n + CodedOutputStream.computeBytesSize((int)7, (ByteString)this.getMIdBytes());
            }
            n = n2;
            if ((this.bitField0_ & 128) == 128) {
                n = n2 + CodedOutputStream.computeBytesSize((int)8, (ByteString)this.getSubjectBytes());
            }
            n2 = n;
            if ((this.bitField0_ & 256) == 256) {
                n2 = n + CodedOutputStream.computeInt32Size((int)9, (int)this.subjectCharset_);
            }
            n = n2;
            if ((this.bitField0_ & 512) == 512) {
                n = n2 + CodedOutputStream.computeBytesSize((int)10, (ByteString)this.getContentTypeBytes());
            }
            n2 = n;
            if ((this.bitField0_ & 1024) == 1024) {
                n2 = n + CodedOutputStream.computeBytesSize((int)11, (ByteString)this.getContentLocationBytes());
            }
            n = n2;
            if ((this.bitField0_ & 2048) == 2048) {
                n = n2 + CodedOutputStream.computeInt64Size((int)12, (long)this.expiry_);
            }
            n2 = n;
            if ((this.bitField0_ & 4096) == 4096) {
                n2 = n + CodedOutputStream.computeBytesSize((int)13, (ByteString)this.getMsgClassBytes());
            }
            n = n2;
            if ((this.bitField0_ & 8192) == 8192) {
                n = n2 + CodedOutputStream.computeInt32Size((int)14, (int)this.msgType_);
            }
            n2 = n;
            if ((this.bitField0_ & 16384) == 16384) {
                n2 = n + CodedOutputStream.computeInt32Size((int)15, (int)this.mmsVersion_);
            }
            n = n2;
            if ((this.bitField0_ & 32768) == 32768) {
                n = n2 + CodedOutputStream.computeInt32Size((int)16, (int)this.msgSize_);
            }
            n2 = n;
            if ((this.bitField0_ & 65536) == 65536) {
                n2 = n + CodedOutputStream.computeInt32Size((int)17, (int)this.priority_);
            }
            n = n2;
            if ((this.bitField0_ & 131072) == 131072) {
                n = n2 + CodedOutputStream.computeInt32Size((int)18, (int)this.readReport_);
            }
            n2 = n;
            if ((this.bitField0_ & 262144) == 262144) {
                n2 = n + CodedOutputStream.computeBoolSize((int)19, (boolean)this.reportAllowed_);
            }
            n = n2;
            if ((this.bitField0_ & 524288) == 524288) {
                n = n2 + CodedOutputStream.computeInt32Size((int)20, (int)this.responseStatus_);
            }
            n2 = n;
            if ((this.bitField0_ & 1048576) == 1048576) {
                n2 = n + CodedOutputStream.computeInt32Size((int)21, (int)this.status_);
            }
            n = n2;
            if ((this.bitField0_ & 2097152) == 2097152) {
                n = n2 + CodedOutputStream.computeBytesSize((int)22, (ByteString)this.getTransactionIdBytes());
            }
            n2 = n;
            if ((this.bitField0_ & 4194304) == 4194304) {
                n2 = n + CodedOutputStream.computeInt32Size((int)23, (int)this.retrieveStatus_);
            }
            n = n2;
            if ((this.bitField0_ & 8388608) == 8388608) {
                n = n2 + CodedOutputStream.computeBytesSize((int)24, (ByteString)this.getRetrieveTextBytes());
            }
            n2 = n;
            if ((this.bitField0_ & 16777216) == 16777216) {
                n2 = n + CodedOutputStream.computeInt32Size((int)25, (int)this.retrieveTextCharset_);
            }
            n = n2;
            if ((this.bitField0_ & 33554432) == 33554432) {
                n = n2 + CodedOutputStream.computeInt32Size((int)26, (int)this.readStatus_);
            }
            n2 = n;
            if ((this.bitField0_ & 67108864) == 67108864) {
                n2 = n + CodedOutputStream.computeInt32Size((int)27, (int)this.contentClass_);
            }
            n = n2;
            if ((this.bitField0_ & 134217728) == 134217728) {
                n = n2 + CodedOutputStream.computeBytesSize((int)28, (ByteString)this.getResponseTextBytes());
            }
            n2 = n;
            if ((this.bitField0_ & 268435456) == 268435456) {
                n2 = n + CodedOutputStream.computeInt64Size((int)29, (long)this.deliveryTime_);
            }
            n = n2;
            if ((this.bitField0_ & 536870912) == 536870912) {
                n = n2 + CodedOutputStream.computeInt32Size((int)30, (int)this.deliveryReport_);
            }
            n2 = n;
            if ((this.bitField0_ & 1073741824) == 1073741824) {
                n2 = n + CodedOutputStream.computeBoolSize((int)31, (boolean)this.locked_);
            }
            n = n2;
            if ((this.bitField0_ & Integer.MIN_VALUE) == Integer.MIN_VALUE) {
                n = n2 + CodedOutputStream.computeBoolSize((int)32, (boolean)this.seen_);
            }
            for (n2 = 0; n2 < this.addrs_.size(); ++n2) {
                n += CodedOutputStream.computeMessageSize((int)33, (MessageLite)((MessageLite)this.addrs_.get(n2)));
            }
            for (n2 = 0; n2 < this.pduParts_.size(); ++n2) {
                n += CodedOutputStream.computeMessageSize((int)34, (MessageLite)((MessageLite)this.pduParts_.get(n2)));
            }
            n2 = n;
            if ((this.bitField1_ & 1) == 1) {
                n2 = n + CodedOutputStream.computeInt32Size((int)35, (int)this.dateMsPart_);
            }
            n = n2;
            if ((this.bitField1_ & 2) == 2) {
                n = n2 + CodedOutputStream.computeInt64Size((int)36, (long)this.mxId_);
            }
            n2 = n;
            if ((this.bitField1_ & 4) == 4) {
                n2 = n + CodedOutputStream.computeInt32Size((int)37, (int)this.mxStatus_);
            }
            this.memoizedSerializedSize = n2;
            return n2;
        }

        public long getServerDate() {
            return this.serverDate_;
        }

        public int getStatus() {
            return this.status_;
        }

        public String getSubject() {
            Object object = this.subject_;
            if (object instanceof String) {
                return (String)object;
            }
            object = (ByteString)object;
            String string = object.toStringUtf8();
            if (Internal.isValidUtf8((ByteString)object)) {
                this.subject_ = string;
            }
            return string;
        }

        public int getSubjectCharset() {
            return this.subjectCharset_;
        }

        public String getTransactionId() {
            Object object = this.transactionId_;
            if (object instanceof String) {
                return (String)object;
            }
            object = (ByteString)object;
            String string = object.toStringUtf8();
            if (Internal.isValidUtf8((ByteString)object)) {
                this.transactionId_ = string;
            }
            return string;
        }

        public boolean hasContentClass() {
            if ((this.bitField0_ & 67108864) == 67108864) {
                return true;
            }
            return false;
        }

        public boolean hasContentLocation() {
            if ((this.bitField0_ & 1024) == 1024) {
                return true;
            }
            return false;
        }

        public boolean hasContentType() {
            if ((this.bitField0_ & 512) == 512) {
                return true;
            }
            return false;
        }

        public boolean hasDate() {
            if ((this.bitField0_ & 4) == 4) {
                return true;
            }
            return false;
        }

        public boolean hasDateMsPart() {
            if ((this.bitField1_ & 1) == 1) {
                return true;
            }
            return false;
        }

        public boolean hasDeliveryReport() {
            if ((this.bitField0_ & 536870912) == 536870912) {
                return true;
            }
            return false;
        }

        public boolean hasDeliveryTime() {
            if ((this.bitField0_ & 268435456) == 268435456) {
                return true;
            }
            return false;
        }

        public boolean hasExpiry() {
            if ((this.bitField0_ & 2048) == 2048) {
                return true;
            }
            return false;
        }

        public boolean hasGuid() {
            if ((this.bitField0_ & 1) == 1) {
                return true;
            }
            return false;
        }

        public boolean hasLocked() {
            if ((this.bitField0_ & 1073741824) == 1073741824) {
                return true;
            }
            return false;
        }

        public boolean hasLuid() {
            if ((this.bitField0_ & 2) == 2) {
                return true;
            }
            return false;
        }

        public boolean hasMId() {
            if ((this.bitField0_ & 64) == 64) {
                return true;
            }
            return false;
        }

        public boolean hasMmsVersion() {
            if ((this.bitField0_ & 16384) == 16384) {
                return true;
            }
            return false;
        }

        public boolean hasMsgBox() {
            if ((this.bitField0_ & 16) == 16) {
                return true;
            }
            return false;
        }

        public boolean hasMsgClass() {
            if ((this.bitField0_ & 4096) == 4096) {
                return true;
            }
            return false;
        }

        public boolean hasMsgSize() {
            if ((this.bitField0_ & 32768) == 32768) {
                return true;
            }
            return false;
        }

        public boolean hasMsgType() {
            if ((this.bitField0_ & 8192) == 8192) {
                return true;
            }
            return false;
        }

        public boolean hasMxId() {
            if ((this.bitField1_ & 2) == 2) {
                return true;
            }
            return false;
        }

        public boolean hasMxStatus() {
            if ((this.bitField1_ & 4) == 4) {
                return true;
            }
            return false;
        }

        public boolean hasPriority() {
            if ((this.bitField0_ & 65536) == 65536) {
                return true;
            }
            return false;
        }

        public boolean hasRead() {
            if ((this.bitField0_ & 32) == 32) {
                return true;
            }
            return false;
        }

        public boolean hasReadReport() {
            if ((this.bitField0_ & 131072) == 131072) {
                return true;
            }
            return false;
        }

        public boolean hasReadStatus() {
            if ((this.bitField0_ & 33554432) == 33554432) {
                return true;
            }
            return false;
        }

        public boolean hasReportAllowed() {
            if ((this.bitField0_ & 262144) == 262144) {
                return true;
            }
            return false;
        }

        public boolean hasResponseStatus() {
            if ((this.bitField0_ & 524288) == 524288) {
                return true;
            }
            return false;
        }

        public boolean hasResponseText() {
            if ((this.bitField0_ & 134217728) == 134217728) {
                return true;
            }
            return false;
        }

        public boolean hasRetrieveStatus() {
            if ((this.bitField0_ & 4194304) == 4194304) {
                return true;
            }
            return false;
        }

        public boolean hasRetrieveText() {
            if ((this.bitField0_ & 8388608) == 8388608) {
                return true;
            }
            return false;
        }

        public boolean hasRetrieveTextCharset() {
            if ((this.bitField0_ & 16777216) == 16777216) {
                return true;
            }
            return false;
        }

        public boolean hasSeen() {
            if ((this.bitField0_ & Integer.MIN_VALUE) == Integer.MIN_VALUE) {
                return true;
            }
            return false;
        }

        public boolean hasServerDate() {
            if ((this.bitField0_ & 8) == 8) {
                return true;
            }
            return false;
        }

        public boolean hasStatus() {
            if ((this.bitField0_ & 1048576) == 1048576) {
                return true;
            }
            return false;
        }

        public boolean hasSubject() {
            if ((this.bitField0_ & 128) == 128) {
                return true;
            }
            return false;
        }

        public boolean hasSubjectCharset() {
            if ((this.bitField0_ & 256) == 256) {
                return true;
            }
            return false;
        }

        public boolean hasTransactionId() {
            if ((this.bitField0_ & 2097152) == 2097152) {
                return true;
            }
            return false;
        }

        public final boolean isInitialized() {
            byte by = this.memoizedIsInitialized;
            if (by != -1) {
                if (by == 1) {
                    return true;
                }
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        public Builder newBuilderForType() {
            return Pdu.newBuilder();
        }

        public Builder toBuilder() {
            return Pdu.newBuilder(this);
        }

        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            int n;
            this.getSerializedSize();
            if ((this.bitField0_ & 1) == 1) {
                codedOutputStream.writeBytes(1, this.getGuidBytes());
            }
            if ((this.bitField0_ & 2) == 2) {
                codedOutputStream.writeBytes(2, this.getLuidBytes());
            }
            if ((this.bitField0_ & 4) == 4) {
                codedOutputStream.writeInt64(3, this.date_);
            }
            if ((this.bitField0_ & 8) == 8) {
                codedOutputStream.writeInt64(4, this.serverDate_);
            }
            if ((this.bitField0_ & 16) == 16) {
                codedOutputStream.writeInt32(5, this.msgBox_);
            }
            if ((this.bitField0_ & 32) == 32) {
                codedOutputStream.writeBool(6, this.read_);
            }
            if ((this.bitField0_ & 64) == 64) {
                codedOutputStream.writeBytes(7, this.getMIdBytes());
            }
            if ((this.bitField0_ & 128) == 128) {
                codedOutputStream.writeBytes(8, this.getSubjectBytes());
            }
            if ((this.bitField0_ & 256) == 256) {
                codedOutputStream.writeInt32(9, this.subjectCharset_);
            }
            if ((this.bitField0_ & 512) == 512) {
                codedOutputStream.writeBytes(10, this.getContentTypeBytes());
            }
            if ((this.bitField0_ & 1024) == 1024) {
                codedOutputStream.writeBytes(11, this.getContentLocationBytes());
            }
            if ((this.bitField0_ & 2048) == 2048) {
                codedOutputStream.writeInt64(12, this.expiry_);
            }
            if ((this.bitField0_ & 4096) == 4096) {
                codedOutputStream.writeBytes(13, this.getMsgClassBytes());
            }
            if ((this.bitField0_ & 8192) == 8192) {
                codedOutputStream.writeInt32(14, this.msgType_);
            }
            if ((this.bitField0_ & 16384) == 16384) {
                codedOutputStream.writeInt32(15, this.mmsVersion_);
            }
            if ((this.bitField0_ & 32768) == 32768) {
                codedOutputStream.writeInt32(16, this.msgSize_);
            }
            if ((this.bitField0_ & 65536) == 65536) {
                codedOutputStream.writeInt32(17, this.priority_);
            }
            if ((this.bitField0_ & 131072) == 131072) {
                codedOutputStream.writeInt32(18, this.readReport_);
            }
            if ((this.bitField0_ & 262144) == 262144) {
                codedOutputStream.writeBool(19, this.reportAllowed_);
            }
            if ((this.bitField0_ & 524288) == 524288) {
                codedOutputStream.writeInt32(20, this.responseStatus_);
            }
            if ((this.bitField0_ & 1048576) == 1048576) {
                codedOutputStream.writeInt32(21, this.status_);
            }
            if ((this.bitField0_ & 2097152) == 2097152) {
                codedOutputStream.writeBytes(22, this.getTransactionIdBytes());
            }
            if ((this.bitField0_ & 4194304) == 4194304) {
                codedOutputStream.writeInt32(23, this.retrieveStatus_);
            }
            if ((this.bitField0_ & 8388608) == 8388608) {
                codedOutputStream.writeBytes(24, this.getRetrieveTextBytes());
            }
            if ((this.bitField0_ & 16777216) == 16777216) {
                codedOutputStream.writeInt32(25, this.retrieveTextCharset_);
            }
            if ((this.bitField0_ & 33554432) == 33554432) {
                codedOutputStream.writeInt32(26, this.readStatus_);
            }
            if ((this.bitField0_ & 67108864) == 67108864) {
                codedOutputStream.writeInt32(27, this.contentClass_);
            }
            if ((this.bitField0_ & 134217728) == 134217728) {
                codedOutputStream.writeBytes(28, this.getResponseTextBytes());
            }
            if ((this.bitField0_ & 268435456) == 268435456) {
                codedOutputStream.writeInt64(29, this.deliveryTime_);
            }
            if ((this.bitField0_ & 536870912) == 536870912) {
                codedOutputStream.writeInt32(30, this.deliveryReport_);
            }
            if ((this.bitField0_ & 1073741824) == 1073741824) {
                codedOutputStream.writeBool(31, this.locked_);
            }
            if ((this.bitField0_ & Integer.MIN_VALUE) == Integer.MIN_VALUE) {
                codedOutputStream.writeBool(32, this.seen_);
            }
            for (n = 0; n < this.addrs_.size(); ++n) {
                codedOutputStream.writeMessage(33, (MessageLite)this.addrs_.get(n));
            }
            for (n = 0; n < this.pduParts_.size(); ++n) {
                codedOutputStream.writeMessage(34, (MessageLite)this.pduParts_.get(n));
            }
            if ((this.bitField1_ & 1) == 1) {
                codedOutputStream.writeInt32(35, this.dateMsPart_);
            }
            if ((this.bitField1_ & 2) == 2) {
                codedOutputStream.writeInt64(36, this.mxId_);
            }
            if ((this.bitField1_ & 4) == 4) {
                codedOutputStream.writeInt32(37, this.mxStatus_);
            }
        }

        public static final class Builder
        extends GeneratedMessageLite.Builder<Pdu, Builder>
        implements PduOrBuilder {
            private List<PduAddr> addrs_ = Collections.emptyList();
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
            private List<PduPart> pduParts_ = Collections.emptyList();
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

            private Builder() {
                this.maybeForceBuilderInitialization();
            }

            private static Builder create() {
                return new Builder();
            }

            private void ensureAddrsIsMutable() {
                if ((this.bitField1_ & 1) != 1) {
                    this.addrs_ = new ArrayList(this.addrs_);
                    this.bitField1_ |= 1;
                }
            }

            private void ensurePduPartsIsMutable() {
                if ((this.bitField1_ & 2) != 2) {
                    this.pduParts_ = new ArrayList(this.pduParts_);
                    this.bitField1_ |= 2;
                }
            }

            private void maybeForceBuilderInitialization() {
            }

            public Builder addAddrs(PduAddr pduAddr) {
                if (pduAddr == null) {
                    throw new NullPointerException();
                }
                this.ensureAddrsIsMutable();
                this.addrs_.add(pduAddr);
                return this;
            }

            public Builder addPduParts(PduPart pduPart) {
                if (pduPart == null) {
                    throw new NullPointerException();
                }
                this.ensurePduPartsIsMutable();
                this.pduParts_.add(pduPart);
                return this;
            }

            public Pdu build() {
                Pdu pdu = this.buildPartial();
                if (!pdu.isInitialized()) {
                    throw Builder.newUninitializedMessageException((MessageLite)pdu);
                }
                return pdu;
            }

            public Pdu buildPartial() {
                Pdu pdu = new Pdu(this);
                int n = this.bitField0_;
                int n2 = this.bitField1_;
                int n3 = 0;
                int n4 = 0;
                if ((n & 1) == 1) {
                    n3 = false | true;
                }
                pdu.guid_ = this.guid_;
                int n5 = n3;
                if ((n & 2) == 2) {
                    n5 = n3 | 2;
                }
                pdu.luid_ = this.luid_;
                n3 = n5;
                if ((n & 4) == 4) {
                    n3 = n5 | 4;
                }
                pdu.date_ = this.date_;
                n5 = n3;
                if ((n & 8) == 8) {
                    n5 = n3 | 8;
                }
                pdu.serverDate_ = this.serverDate_;
                n3 = n5;
                if ((n & 16) == 16) {
                    n3 = n5 | 16;
                }
                pdu.msgBox_ = this.msgBox_;
                n5 = n3;
                if ((n & 32) == 32) {
                    n5 = n3 | 32;
                }
                pdu.read_ = this.read_;
                n3 = n5;
                if ((n & 64) == 64) {
                    n3 = n5 | 64;
                }
                pdu.mId_ = this.mId_;
                n5 = n3;
                if ((n & 128) == 128) {
                    n5 = n3 | 128;
                }
                pdu.subject_ = this.subject_;
                n3 = n5;
                if ((n & 256) == 256) {
                    n3 = n5 | 256;
                }
                pdu.subjectCharset_ = this.subjectCharset_;
                n5 = n3;
                if ((n & 512) == 512) {
                    n5 = n3 | 512;
                }
                pdu.contentType_ = this.contentType_;
                n3 = n5;
                if ((n & 1024) == 1024) {
                    n3 = n5 | 1024;
                }
                pdu.contentLocation_ = this.contentLocation_;
                n5 = n3;
                if ((n & 2048) == 2048) {
                    n5 = n3 | 2048;
                }
                pdu.expiry_ = this.expiry_;
                n3 = n5;
                if ((n & 4096) == 4096) {
                    n3 = n5 | 4096;
                }
                pdu.msgClass_ = this.msgClass_;
                n5 = n3;
                if ((n & 8192) == 8192) {
                    n5 = n3 | 8192;
                }
                pdu.msgType_ = this.msgType_;
                n3 = n5;
                if ((n & 16384) == 16384) {
                    n3 = n5 | 16384;
                }
                pdu.mmsVersion_ = this.mmsVersion_;
                n5 = n3;
                if ((n & 32768) == 32768) {
                    n5 = n3 | 32768;
                }
                pdu.msgSize_ = this.msgSize_;
                n3 = n5;
                if ((n & 65536) == 65536) {
                    n3 = n5 | 65536;
                }
                pdu.priority_ = this.priority_;
                n5 = n3;
                if ((n & 131072) == 131072) {
                    n5 = n3 | 131072;
                }
                pdu.readReport_ = this.readReport_;
                n3 = n5;
                if ((n & 262144) == 262144) {
                    n3 = n5 | 262144;
                }
                pdu.reportAllowed_ = this.reportAllowed_;
                n5 = n3;
                if ((524288 & n) == 524288) {
                    n5 = n3 | 524288;
                }
                pdu.responseStatus_ = this.responseStatus_;
                n3 = n5;
                if ((1048576 & n) == 1048576) {
                    n3 = n5 | 1048576;
                }
                pdu.status_ = this.status_;
                n5 = n3;
                if ((2097152 & n) == 2097152) {
                    n5 = n3 | 2097152;
                }
                pdu.transactionId_ = this.transactionId_;
                n3 = n5;
                if ((4194304 & n) == 4194304) {
                    n3 = n5 | 4194304;
                }
                pdu.retrieveStatus_ = this.retrieveStatus_;
                n5 = n3;
                if ((8388608 & n) == 8388608) {
                    n5 = n3 | 8388608;
                }
                pdu.retrieveText_ = this.retrieveText_;
                n3 = n5;
                if ((16777216 & n) == 16777216) {
                    n3 = n5 | 16777216;
                }
                pdu.retrieveTextCharset_ = this.retrieveTextCharset_;
                n5 = n3;
                if ((33554432 & n) == 33554432) {
                    n5 = n3 | 33554432;
                }
                pdu.readStatus_ = this.readStatus_;
                n3 = n5;
                if ((67108864 & n) == 67108864) {
                    n3 = n5 | 67108864;
                }
                pdu.contentClass_ = this.contentClass_;
                n5 = n3;
                if ((134217728 & n) == 134217728) {
                    n5 = n3 | 134217728;
                }
                pdu.responseText_ = this.responseText_;
                n3 = n5;
                if ((268435456 & n) == 268435456) {
                    n3 = n5 | 268435456;
                }
                pdu.deliveryTime_ = this.deliveryTime_;
                n5 = n3;
                if ((536870912 & n) == 536870912) {
                    n5 = n3 | 536870912;
                }
                pdu.deliveryReport_ = this.deliveryReport_;
                n3 = n5;
                if ((1073741824 & n) == 1073741824) {
                    n3 = n5 | 1073741824;
                }
                pdu.locked_ = this.locked_;
                int n6 = n3;
                if ((n & Integer.MIN_VALUE) == Integer.MIN_VALUE) {
                    n6 = n3 | Integer.MIN_VALUE;
                }
                pdu.seen_ = this.seen_;
                if ((this.bitField1_ & 1) == 1) {
                    this.addrs_ = Collections.unmodifiableList(this.addrs_);
                    this.bitField1_ &= -2;
                }
                pdu.addrs_ = this.addrs_;
                if ((this.bitField1_ & 2) == 2) {
                    this.pduParts_ = Collections.unmodifiableList(this.pduParts_);
                    this.bitField1_ &= -3;
                }
                pdu.pduParts_ = this.pduParts_;
                n3 = n4;
                if ((n2 & 4) == 4) {
                    n3 = false | true;
                }
                pdu.dateMsPart_ = this.dateMsPart_;
                n5 = n3;
                if ((n2 & 8) == 8) {
                    n5 = n3 | 2;
                }
                pdu.mxId_ = this.mxId_;
                n3 = n5;
                if ((n2 & 16) == 16) {
                    n3 = n5 | 4;
                }
                pdu.mxStatus_ = this.mxStatus_;
                pdu.bitField0_ = n6;
                pdu.bitField1_ = n3;
                return pdu;
            }

            public Builder clear() {
                super.clear();
                this.guid_ = "";
                this.bitField0_ &= -2;
                this.luid_ = "";
                this.bitField0_ &= -3;
                this.date_ = 0;
                this.bitField0_ &= -5;
                this.serverDate_ = 0;
                this.bitField0_ &= -9;
                this.msgBox_ = 0;
                this.bitField0_ &= -17;
                this.read_ = false;
                this.bitField0_ &= -33;
                this.mId_ = "";
                this.bitField0_ &= -65;
                this.subject_ = "";
                this.bitField0_ &= -129;
                this.subjectCharset_ = 0;
                this.bitField0_ &= -257;
                this.contentType_ = "";
                this.bitField0_ &= -513;
                this.contentLocation_ = "";
                this.bitField0_ &= -1025;
                this.expiry_ = 0;
                this.bitField0_ &= -2049;
                this.msgClass_ = "";
                this.bitField0_ &= -4097;
                this.msgType_ = 0;
                this.bitField0_ &= -8193;
                this.mmsVersion_ = 0;
                this.bitField0_ &= -16385;
                this.msgSize_ = 0;
                this.bitField0_ &= -32769;
                this.priority_ = 0;
                this.bitField0_ &= -65537;
                this.readReport_ = 0;
                this.bitField0_ &= -131073;
                this.reportAllowed_ = false;
                this.bitField0_ &= -262145;
                this.responseStatus_ = 0;
                this.bitField0_ &= -524289;
                this.status_ = 0;
                this.bitField0_ &= -1048577;
                this.transactionId_ = "";
                this.bitField0_ &= -2097153;
                this.retrieveStatus_ = 0;
                this.bitField0_ &= -4194305;
                this.retrieveText_ = "";
                this.bitField0_ &= -8388609;
                this.retrieveTextCharset_ = 0;
                this.bitField0_ &= -16777217;
                this.readStatus_ = 0;
                this.bitField0_ &= -33554433;
                this.contentClass_ = 0;
                this.bitField0_ &= -67108865;
                this.responseText_ = "";
                this.bitField0_ &= -134217729;
                this.deliveryTime_ = 0;
                this.bitField0_ &= -268435457;
                this.deliveryReport_ = 0;
                this.bitField0_ &= -536870913;
                this.locked_ = false;
                this.bitField0_ &= -1073741825;
                this.seen_ = false;
                this.bitField0_ &= Integer.MAX_VALUE;
                this.addrs_ = Collections.emptyList();
                this.bitField1_ &= -2;
                this.pduParts_ = Collections.emptyList();
                this.bitField1_ &= -3;
                this.dateMsPart_ = 0;
                this.bitField1_ &= -5;
                this.mxId_ = 0;
                this.bitField1_ &= -9;
                this.mxStatus_ = 0;
                this.bitField1_ &= -17;
                return this;
            }

            public Builder clone() {
                return Builder.create().mergeFrom(this.buildPartial());
            }

            public Pdu getDefaultInstanceForType() {
                return Pdu.getDefaultInstance();
            }

            public final boolean isInitialized() {
                return true;
            }

            /*
             * Enabled aggressive block sorting
             * Lifted jumps to return sites
             */
            public Builder mergeFrom(Pdu pdu) {
                if (pdu == Pdu.getDefaultInstance()) {
                    return this;
                }
                if (pdu.hasGuid()) {
                    this.setGuid(pdu.getGuid());
                }
                if (pdu.hasLuid()) {
                    this.setLuid(pdu.getLuid());
                }
                if (pdu.hasDate()) {
                    this.setDate(pdu.getDate());
                }
                if (pdu.hasServerDate()) {
                    this.setServerDate(pdu.getServerDate());
                }
                if (pdu.hasMsgBox()) {
                    this.setMsgBox(pdu.getMsgBox());
                }
                if (pdu.hasRead()) {
                    this.setRead(pdu.getRead());
                }
                if (pdu.hasMId()) {
                    this.setMId(pdu.getMId());
                }
                if (pdu.hasSubject()) {
                    this.setSubject(pdu.getSubject());
                }
                if (pdu.hasSubjectCharset()) {
                    this.setSubjectCharset(pdu.getSubjectCharset());
                }
                if (pdu.hasContentType()) {
                    this.setContentType(pdu.getContentType());
                }
                if (pdu.hasContentLocation()) {
                    this.setContentLocation(pdu.getContentLocation());
                }
                if (pdu.hasExpiry()) {
                    this.setExpiry(pdu.getExpiry());
                }
                if (pdu.hasMsgClass()) {
                    this.setMsgClass(pdu.getMsgClass());
                }
                if (pdu.hasMsgType()) {
                    this.setMsgType(pdu.getMsgType());
                }
                if (pdu.hasMmsVersion()) {
                    this.setMmsVersion(pdu.getMmsVersion());
                }
                if (pdu.hasMsgSize()) {
                    this.setMsgSize(pdu.getMsgSize());
                }
                if (pdu.hasPriority()) {
                    this.setPriority(pdu.getPriority());
                }
                if (pdu.hasReadReport()) {
                    this.setReadReport(pdu.getReadReport());
                }
                if (pdu.hasReportAllowed()) {
                    this.setReportAllowed(pdu.getReportAllowed());
                }
                if (pdu.hasResponseStatus()) {
                    this.setResponseStatus(pdu.getResponseStatus());
                }
                if (pdu.hasStatus()) {
                    this.setStatus(pdu.getStatus());
                }
                if (pdu.hasTransactionId()) {
                    this.setTransactionId(pdu.getTransactionId());
                }
                if (pdu.hasRetrieveStatus()) {
                    this.setRetrieveStatus(pdu.getRetrieveStatus());
                }
                if (pdu.hasRetrieveText()) {
                    this.setRetrieveText(pdu.getRetrieveText());
                }
                if (pdu.hasRetrieveTextCharset()) {
                    this.setRetrieveTextCharset(pdu.getRetrieveTextCharset());
                }
                if (pdu.hasReadStatus()) {
                    this.setReadStatus(pdu.getReadStatus());
                }
                if (pdu.hasContentClass()) {
                    this.setContentClass(pdu.getContentClass());
                }
                if (pdu.hasResponseText()) {
                    this.setResponseText(pdu.getResponseText());
                }
                if (pdu.hasDeliveryTime()) {
                    this.setDeliveryTime(pdu.getDeliveryTime());
                }
                if (pdu.hasDeliveryReport()) {
                    this.setDeliveryReport(pdu.getDeliveryReport());
                }
                if (pdu.hasLocked()) {
                    this.setLocked(pdu.getLocked());
                }
                if (pdu.hasSeen()) {
                    this.setSeen(pdu.getSeen());
                }
                if (!pdu.addrs_.isEmpty()) {
                    if (this.addrs_.isEmpty()) {
                        this.addrs_ = pdu.addrs_;
                        this.bitField1_ &= -2;
                    } else {
                        this.ensureAddrsIsMutable();
                        this.addrs_.addAll(pdu.addrs_);
                    }
                }
                if (!pdu.pduParts_.isEmpty()) {
                    if (this.pduParts_.isEmpty()) {
                        this.pduParts_ = pdu.pduParts_;
                        this.bitField1_ &= -3;
                    } else {
                        this.ensurePduPartsIsMutable();
                        this.pduParts_.addAll(pdu.pduParts_);
                    }
                }
                if (pdu.hasDateMsPart()) {
                    this.setDateMsPart(pdu.getDateMsPart());
                }
                if (pdu.hasMxId()) {
                    this.setMxId(pdu.getMxId());
                }
                if (!pdu.hasMxStatus()) return this;
                this.setMxStatus(pdu.getMxStatus());
                return this;
            }

            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                block40 : do {
                    int n = codedInputStream.readTag();
                    switch (n) {
                        PduAddr.Builder builder;
                        default: {
                            if (this.parseUnknownField(codedInputStream, extensionRegistryLite, n)) continue block40;
                        }
                        case 0: {
                            return this;
                        }
                        case 10: {
                            this.bitField0_ |= 1;
                            this.guid_ = codedInputStream.readBytes();
                            continue block40;
                        }
                        case 18: {
                            this.bitField0_ |= 2;
                            this.luid_ = codedInputStream.readBytes();
                            continue block40;
                        }
                        case 24: {
                            this.bitField0_ |= 4;
                            this.date_ = codedInputStream.readInt64();
                            continue block40;
                        }
                        case 32: {
                            this.bitField0_ |= 8;
                            this.serverDate_ = codedInputStream.readInt64();
                            continue block40;
                        }
                        case 40: {
                            this.bitField0_ |= 16;
                            this.msgBox_ = codedInputStream.readInt32();
                            continue block40;
                        }
                        case 48: {
                            this.bitField0_ |= 32;
                            this.read_ = codedInputStream.readBool();
                            continue block40;
                        }
                        case 58: {
                            this.bitField0_ |= 64;
                            this.mId_ = codedInputStream.readBytes();
                            continue block40;
                        }
                        case 66: {
                            this.bitField0_ |= 128;
                            this.subject_ = codedInputStream.readBytes();
                            continue block40;
                        }
                        case 72: {
                            this.bitField0_ |= 256;
                            this.subjectCharset_ = codedInputStream.readInt32();
                            continue block40;
                        }
                        case 82: {
                            this.bitField0_ |= 512;
                            this.contentType_ = codedInputStream.readBytes();
                            continue block40;
                        }
                        case 90: {
                            this.bitField0_ |= 1024;
                            this.contentLocation_ = codedInputStream.readBytes();
                            continue block40;
                        }
                        case 96: {
                            this.bitField0_ |= 2048;
                            this.expiry_ = codedInputStream.readInt64();
                            continue block40;
                        }
                        case 106: {
                            this.bitField0_ |= 4096;
                            this.msgClass_ = codedInputStream.readBytes();
                            continue block40;
                        }
                        case 112: {
                            this.bitField0_ |= 8192;
                            this.msgType_ = codedInputStream.readInt32();
                            continue block40;
                        }
                        case 120: {
                            this.bitField0_ |= 16384;
                            this.mmsVersion_ = codedInputStream.readInt32();
                            continue block40;
                        }
                        case 128: {
                            this.bitField0_ |= 32768;
                            this.msgSize_ = codedInputStream.readInt32();
                            continue block40;
                        }
                        case 136: {
                            this.bitField0_ |= 65536;
                            this.priority_ = codedInputStream.readInt32();
                            continue block40;
                        }
                        case 144: {
                            this.bitField0_ |= 131072;
                            this.readReport_ = codedInputStream.readInt32();
                            continue block40;
                        }
                        case 152: {
                            this.bitField0_ |= 262144;
                            this.reportAllowed_ = codedInputStream.readBool();
                            continue block40;
                        }
                        case 160: {
                            this.bitField0_ |= 524288;
                            this.responseStatus_ = codedInputStream.readInt32();
                            continue block40;
                        }
                        case 168: {
                            this.bitField0_ |= 1048576;
                            this.status_ = codedInputStream.readInt32();
                            continue block40;
                        }
                        case 178: {
                            this.bitField0_ |= 2097152;
                            this.transactionId_ = codedInputStream.readBytes();
                            continue block40;
                        }
                        case 184: {
                            this.bitField0_ |= 4194304;
                            this.retrieveStatus_ = codedInputStream.readInt32();
                            continue block40;
                        }
                        case 194: {
                            this.bitField0_ |= 8388608;
                            this.retrieveText_ = codedInputStream.readBytes();
                            continue block40;
                        }
                        case 200: {
                            this.bitField0_ |= 16777216;
                            this.retrieveTextCharset_ = codedInputStream.readInt32();
                            continue block40;
                        }
                        case 208: {
                            this.bitField0_ |= 33554432;
                            this.readStatus_ = codedInputStream.readInt32();
                            continue block40;
                        }
                        case 216: {
                            this.bitField0_ |= 67108864;
                            this.contentClass_ = codedInputStream.readInt32();
                            continue block40;
                        }
                        case 226: {
                            this.bitField0_ |= 134217728;
                            this.responseText_ = codedInputStream.readBytes();
                            continue block40;
                        }
                        case 232: {
                            this.bitField0_ |= 268435456;
                            this.deliveryTime_ = codedInputStream.readInt64();
                            continue block40;
                        }
                        case 240: {
                            this.bitField0_ |= 536870912;
                            this.deliveryReport_ = codedInputStream.readInt32();
                            continue block40;
                        }
                        case 248: {
                            this.bitField0_ |= 1073741824;
                            this.locked_ = codedInputStream.readBool();
                            continue block40;
                        }
                        case 256: {
                            this.bitField0_ |= Integer.MIN_VALUE;
                            this.seen_ = codedInputStream.readBool();
                            continue block40;
                        }
                        case 266: {
                            builder = PduAddr.newBuilder();
                            codedInputStream.readMessage((MessageLite.Builder)builder, extensionRegistryLite);
                            this.addAddrs(builder.buildPartial());
                            continue block40;
                        }
                        case 274: {
                            builder = PduPart.newBuilder();
                            codedInputStream.readMessage((MessageLite.Builder)builder, extensionRegistryLite);
                            this.addPduParts(builder.buildPartial());
                            continue block40;
                        }
                        case 280: {
                            this.bitField1_ |= 4;
                            this.dateMsPart_ = codedInputStream.readInt32();
                            continue block40;
                        }
                        case 288: {
                            this.bitField1_ |= 8;
                            this.mxId_ = codedInputStream.readInt64();
                            continue block40;
                        }
                        case 296: 
                    }
                    this.bitField1_ |= 16;
                    this.mxStatus_ = codedInputStream.readInt32();
                } while (true);
            }

            public Builder setContentClass(int n) {
                this.bitField0_ |= 67108864;
                this.contentClass_ = n;
                return this;
            }

            public Builder setContentLocation(String string) {
                if (string == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 1024;
                this.contentLocation_ = string;
                return this;
            }

            public Builder setContentType(String string) {
                if (string == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 512;
                this.contentType_ = string;
                return this;
            }

            public Builder setDate(long l) {
                this.bitField0_ |= 4;
                this.date_ = l;
                return this;
            }

            public Builder setDateMsPart(int n) {
                this.bitField1_ |= 4;
                this.dateMsPart_ = n;
                return this;
            }

            public Builder setDeliveryReport(int n) {
                this.bitField0_ |= 536870912;
                this.deliveryReport_ = n;
                return this;
            }

            public Builder setDeliveryTime(long l) {
                this.bitField0_ |= 268435456;
                this.deliveryTime_ = l;
                return this;
            }

            public Builder setExpiry(long l) {
                this.bitField0_ |= 2048;
                this.expiry_ = l;
                return this;
            }

            public Builder setGuid(String string) {
                if (string == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 1;
                this.guid_ = string;
                return this;
            }

            public Builder setLocked(boolean bl) {
                this.bitField0_ |= 1073741824;
                this.locked_ = bl;
                return this;
            }

            public Builder setLuid(String string) {
                if (string == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 2;
                this.luid_ = string;
                return this;
            }

            public Builder setMId(String string) {
                if (string == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 64;
                this.mId_ = string;
                return this;
            }

            public Builder setMmsVersion(int n) {
                this.bitField0_ |= 16384;
                this.mmsVersion_ = n;
                return this;
            }

            public Builder setMsgBox(int n) {
                this.bitField0_ |= 16;
                this.msgBox_ = n;
                return this;
            }

            public Builder setMsgClass(String string) {
                if (string == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 4096;
                this.msgClass_ = string;
                return this;
            }

            public Builder setMsgSize(int n) {
                this.bitField0_ |= 32768;
                this.msgSize_ = n;
                return this;
            }

            public Builder setMsgType(int n) {
                this.bitField0_ |= 8192;
                this.msgType_ = n;
                return this;
            }

            public Builder setMxId(long l) {
                this.bitField1_ |= 8;
                this.mxId_ = l;
                return this;
            }

            public Builder setMxStatus(int n) {
                this.bitField1_ |= 16;
                this.mxStatus_ = n;
                return this;
            }

            public Builder setPriority(int n) {
                this.bitField0_ |= 65536;
                this.priority_ = n;
                return this;
            }

            public Builder setRead(boolean bl) {
                this.bitField0_ |= 32;
                this.read_ = bl;
                return this;
            }

            public Builder setReadReport(int n) {
                this.bitField0_ |= 131072;
                this.readReport_ = n;
                return this;
            }

            public Builder setReadStatus(int n) {
                this.bitField0_ |= 33554432;
                this.readStatus_ = n;
                return this;
            }

            public Builder setReportAllowed(boolean bl) {
                this.bitField0_ |= 262144;
                this.reportAllowed_ = bl;
                return this;
            }

            public Builder setResponseStatus(int n) {
                this.bitField0_ |= 524288;
                this.responseStatus_ = n;
                return this;
            }

            public Builder setResponseText(String string) {
                if (string == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 134217728;
                this.responseText_ = string;
                return this;
            }

            public Builder setRetrieveStatus(int n) {
                this.bitField0_ |= 4194304;
                this.retrieveStatus_ = n;
                return this;
            }

            public Builder setRetrieveText(String string) {
                if (string == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 8388608;
                this.retrieveText_ = string;
                return this;
            }

            public Builder setRetrieveTextCharset(int n) {
                this.bitField0_ |= 16777216;
                this.retrieveTextCharset_ = n;
                return this;
            }

            public Builder setSeen(boolean bl) {
                this.bitField0_ |= Integer.MIN_VALUE;
                this.seen_ = bl;
                return this;
            }

            public Builder setServerDate(long l) {
                this.bitField0_ |= 8;
                this.serverDate_ = l;
                return this;
            }

            public Builder setStatus(int n) {
                this.bitField0_ |= 1048576;
                this.status_ = n;
                return this;
            }

            public Builder setSubject(String string) {
                if (string == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 128;
                this.subject_ = string;
                return this;
            }

            public Builder setSubjectCharset(int n) {
                this.bitField0_ |= 256;
                this.subjectCharset_ = n;
                return this;
            }

            public Builder setTransactionId(String string) {
                if (string == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 2097152;
                this.transactionId_ = string;
                return this;
            }
        }

    }

    public static final class PduAddr
    extends GeneratedMessageLite
    implements PduAddrOrBuilder {
        private static final PduAddr defaultInstance = new PduAddr(true);
        private static final long serialVersionUID = 0;
        private Object address_;
        private int bitField0_;
        private int charset_;
        private byte memoizedIsInitialized = -1;
        private int memoizedSerializedSize = -1;
        private int type_;

        static {
            defaultInstance.initFields();
        }

        private PduAddr(Builder builder) {
            super((GeneratedMessageLite.Builder)builder);
        }

        private PduAddr(boolean bl) {
        }

        private ByteString getAddressBytes() {
            Object object = this.address_;
            if (object instanceof String) {
                this.address_ = object = ByteString.copyFromUtf8((String)((String)object));
                return object;
            }
            return (ByteString)object;
        }

        public static PduAddr getDefaultInstance() {
            return defaultInstance;
        }

        private void initFields() {
            this.address_ = "";
            this.type_ = 0;
            this.charset_ = 0;
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public static Builder newBuilder(PduAddr pduAddr) {
            return PduAddr.newBuilder().mergeFrom(pduAddr);
        }

        public String getAddress() {
            Object object = this.address_;
            if (object instanceof String) {
                return (String)object;
            }
            object = (ByteString)object;
            String string = object.toStringUtf8();
            if (Internal.isValidUtf8((ByteString)object)) {
                this.address_ = string;
            }
            return string;
        }

        public int getCharset() {
            return this.charset_;
        }

        public PduAddr getDefaultInstanceForType() {
            return defaultInstance;
        }

        public int getSerializedSize() {
            int n = this.memoizedSerializedSize;
            if (n != -1) {
                return n;
            }
            int n2 = 0;
            if ((this.bitField0_ & 1) == 1) {
                n2 = 0 + CodedOutputStream.computeBytesSize((int)1, (ByteString)this.getAddressBytes());
            }
            n = n2;
            if ((this.bitField0_ & 2) == 2) {
                n = n2 + CodedOutputStream.computeInt32Size((int)2, (int)this.type_);
            }
            n2 = n;
            if ((this.bitField0_ & 4) == 4) {
                n2 = n + CodedOutputStream.computeInt32Size((int)3, (int)this.charset_);
            }
            this.memoizedSerializedSize = n2;
            return n2;
        }

        public int getType() {
            return this.type_;
        }

        public boolean hasAddress() {
            if ((this.bitField0_ & 1) == 1) {
                return true;
            }
            return false;
        }

        public boolean hasCharset() {
            if ((this.bitField0_ & 4) == 4) {
                return true;
            }
            return false;
        }

        public boolean hasType() {
            if ((this.bitField0_ & 2) == 2) {
                return true;
            }
            return false;
        }

        public final boolean isInitialized() {
            byte by = this.memoizedIsInitialized;
            if (by != -1) {
                if (by == 1) {
                    return true;
                }
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        public Builder newBuilderForType() {
            return PduAddr.newBuilder();
        }

        public Builder toBuilder() {
            return PduAddr.newBuilder(this);
        }

        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            this.getSerializedSize();
            if ((this.bitField0_ & 1) == 1) {
                codedOutputStream.writeBytes(1, this.getAddressBytes());
            }
            if ((this.bitField0_ & 2) == 2) {
                codedOutputStream.writeInt32(2, this.type_);
            }
            if ((this.bitField0_ & 4) == 4) {
                codedOutputStream.writeInt32(3, this.charset_);
            }
        }

        public static final class Builder
        extends GeneratedMessageLite.Builder<PduAddr, Builder>
        implements PduAddrOrBuilder {
            private Object address_ = "";
            private int bitField0_;
            private int charset_;
            private int type_;

            private Builder() {
                this.maybeForceBuilderInitialization();
            }

            private static Builder create() {
                return new Builder();
            }

            private void maybeForceBuilderInitialization() {
            }

            public PduAddr build() {
                PduAddr pduAddr = this.buildPartial();
                if (!pduAddr.isInitialized()) {
                    throw Builder.newUninitializedMessageException((MessageLite)pduAddr);
                }
                return pduAddr;
            }

            public PduAddr buildPartial() {
                PduAddr pduAddr = new PduAddr(this);
                int n = this.bitField0_;
                int n2 = 0;
                if ((n & 1) == 1) {
                    n2 = false | true;
                }
                pduAddr.address_ = this.address_;
                int n3 = n2;
                if ((n & 2) == 2) {
                    n3 = n2 | 2;
                }
                pduAddr.type_ = this.type_;
                n2 = n3;
                if ((n & 4) == 4) {
                    n2 = n3 | 4;
                }
                pduAddr.charset_ = this.charset_;
                pduAddr.bitField0_ = n2;
                return pduAddr;
            }

            public Builder clear() {
                super.clear();
                this.address_ = "";
                this.bitField0_ &= -2;
                this.type_ = 0;
                this.bitField0_ &= -3;
                this.charset_ = 0;
                this.bitField0_ &= -5;
                return this;
            }

            public Builder clone() {
                return Builder.create().mergeFrom(this.buildPartial());
            }

            public PduAddr getDefaultInstanceForType() {
                return PduAddr.getDefaultInstance();
            }

            public final boolean isInitialized() {
                return true;
            }

            /*
             * Enabled aggressive block sorting
             * Lifted jumps to return sites
             */
            public Builder mergeFrom(PduAddr pduAddr) {
                if (pduAddr == PduAddr.getDefaultInstance()) {
                    return this;
                }
                if (pduAddr.hasAddress()) {
                    this.setAddress(pduAddr.getAddress());
                }
                if (pduAddr.hasType()) {
                    this.setType(pduAddr.getType());
                }
                if (!pduAddr.hasCharset()) return this;
                this.setCharset(pduAddr.getCharset());
                return this;
            }

            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                block6 : do {
                    int n = codedInputStream.readTag();
                    switch (n) {
                        default: {
                            if (this.parseUnknownField(codedInputStream, extensionRegistryLite, n)) continue block6;
                        }
                        case 0: {
                            return this;
                        }
                        case 10: {
                            this.bitField0_ |= 1;
                            this.address_ = codedInputStream.readBytes();
                            continue block6;
                        }
                        case 16: {
                            this.bitField0_ |= 2;
                            this.type_ = codedInputStream.readInt32();
                            continue block6;
                        }
                        case 24: 
                    }
                    this.bitField0_ |= 4;
                    this.charset_ = codedInputStream.readInt32();
                } while (true);
            }

            public Builder setAddress(String string) {
                if (string == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 1;
                this.address_ = string;
                return this;
            }

            public Builder setCharset(int n) {
                this.bitField0_ |= 4;
                this.charset_ = n;
                return this;
            }

            public Builder setType(int n) {
                this.bitField0_ |= 2;
                this.type_ = n;
                return this;
            }
        }

    }

    public static interface PduAddrOrBuilder
    extends MessageLiteOrBuilder {
    }

    public static interface PduOrBuilder
    extends MessageLiteOrBuilder {
    }

    public static final class PduPart
    extends GeneratedMessageLite
    implements PduPartOrBuilder {
        private static final PduPart defaultInstance = new PduPart(true);
        private static final long serialVersionUID = 0;
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

        static {
            defaultInstance.initFields();
        }

        private PduPart(Builder builder) {
            super((GeneratedMessageLite.Builder)builder);
        }

        private PduPart(boolean bl) {
        }

        private ByteString getContentDispositionBytes() {
            Object object = this.contentDisposition_;
            if (object instanceof String) {
                this.contentDisposition_ = object = ByteString.copyFromUtf8((String)((String)object));
                return object;
            }
            return (ByteString)object;
        }

        private ByteString getContentIdBytes() {
            Object object = this.contentId_;
            if (object instanceof String) {
                this.contentId_ = object = ByteString.copyFromUtf8((String)((String)object));
                return object;
            }
            return (ByteString)object;
        }

        private ByteString getContentLocationBytes() {
            Object object = this.contentLocation_;
            if (object instanceof String) {
                this.contentLocation_ = object = ByteString.copyFromUtf8((String)((String)object));
                return object;
            }
            return (ByteString)object;
        }

        private ByteString getContentTypeBytes() {
            Object object = this.contentType_;
            if (object instanceof String) {
                this.contentType_ = object = ByteString.copyFromUtf8((String)((String)object));
                return object;
            }
            return (ByteString)object;
        }

        private ByteString getContentTypeTypeBytes() {
            Object object = this.contentTypeType_;
            if (object instanceof String) {
                this.contentTypeType_ = object = ByteString.copyFromUtf8((String)((String)object));
                return object;
            }
            return (ByteString)object;
        }

        public static PduPart getDefaultInstance() {
            return defaultInstance;
        }

        private ByteString getFileNameBytes() {
            Object object = this.fileName_;
            if (object instanceof String) {
                this.fileName_ = object = ByteString.copyFromUtf8((String)((String)object));
                return object;
            }
            return (ByteString)object;
        }

        private ByteString getNameBytes() {
            Object object = this.name_;
            if (object instanceof String) {
                this.name_ = object = ByteString.copyFromUtf8((String)((String)object));
                return object;
            }
            return (ByteString)object;
        }

        private ByteString getTextBytes() {
            Object object = this.text_;
            if (object instanceof String) {
                this.text_ = object = ByteString.copyFromUtf8((String)((String)object));
                return object;
            }
            return (ByteString)object;
        }

        private void initFields() {
            this.sequence_ = 0;
            this.contentType_ = "";
            this.name_ = "";
            this.charset_ = 0;
            this.contentDisposition_ = "";
            this.fileName_ = "";
            this.contentId_ = "";
            this.contentLocation_ = "";
            this.contentTypeStart_ = 0;
            this.contentTypeType_ = "";
            this.text_ = "";
            this.data_ = ByteString.EMPTY;
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public static Builder newBuilder(PduPart pduPart) {
            return PduPart.newBuilder().mergeFrom(pduPart);
        }

        public int getCharset() {
            return this.charset_;
        }

        public String getContentDisposition() {
            Object object = this.contentDisposition_;
            if (object instanceof String) {
                return (String)object;
            }
            object = (ByteString)object;
            String string = object.toStringUtf8();
            if (Internal.isValidUtf8((ByteString)object)) {
                this.contentDisposition_ = string;
            }
            return string;
        }

        public String getContentId() {
            Object object = this.contentId_;
            if (object instanceof String) {
                return (String)object;
            }
            object = (ByteString)object;
            String string = object.toStringUtf8();
            if (Internal.isValidUtf8((ByteString)object)) {
                this.contentId_ = string;
            }
            return string;
        }

        public String getContentLocation() {
            Object object = this.contentLocation_;
            if (object instanceof String) {
                return (String)object;
            }
            object = (ByteString)object;
            String string = object.toStringUtf8();
            if (Internal.isValidUtf8((ByteString)object)) {
                this.contentLocation_ = string;
            }
            return string;
        }

        public String getContentType() {
            Object object = this.contentType_;
            if (object instanceof String) {
                return (String)object;
            }
            object = (ByteString)object;
            String string = object.toStringUtf8();
            if (Internal.isValidUtf8((ByteString)object)) {
                this.contentType_ = string;
            }
            return string;
        }

        public int getContentTypeStart() {
            return this.contentTypeStart_;
        }

        public String getContentTypeType() {
            Object object = this.contentTypeType_;
            if (object instanceof String) {
                return (String)object;
            }
            object = (ByteString)object;
            String string = object.toStringUtf8();
            if (Internal.isValidUtf8((ByteString)object)) {
                this.contentTypeType_ = string;
            }
            return string;
        }

        public ByteString getData() {
            return this.data_;
        }

        public PduPart getDefaultInstanceForType() {
            return defaultInstance;
        }

        public String getFileName() {
            Object object = this.fileName_;
            if (object instanceof String) {
                return (String)object;
            }
            object = (ByteString)object;
            String string = object.toStringUtf8();
            if (Internal.isValidUtf8((ByteString)object)) {
                this.fileName_ = string;
            }
            return string;
        }

        public String getName() {
            Object object = this.name_;
            if (object instanceof String) {
                return (String)object;
            }
            object = (ByteString)object;
            String string = object.toStringUtf8();
            if (Internal.isValidUtf8((ByteString)object)) {
                this.name_ = string;
            }
            return string;
        }

        public int getSequence() {
            return this.sequence_;
        }

        public int getSerializedSize() {
            int n = this.memoizedSerializedSize;
            if (n != -1) {
                return n;
            }
            int n2 = 0;
            if ((this.bitField0_ & 1) == 1) {
                n2 = 0 + CodedOutputStream.computeInt32Size((int)1, (int)this.sequence_);
            }
            n = n2;
            if ((this.bitField0_ & 2) == 2) {
                n = n2 + CodedOutputStream.computeBytesSize((int)2, (ByteString)this.getContentTypeBytes());
            }
            n2 = n;
            if ((this.bitField0_ & 4) == 4) {
                n2 = n + CodedOutputStream.computeBytesSize((int)3, (ByteString)this.getNameBytes());
            }
            n = n2;
            if ((this.bitField0_ & 8) == 8) {
                n = n2 + CodedOutputStream.computeInt32Size((int)4, (int)this.charset_);
            }
            n2 = n;
            if ((this.bitField0_ & 16) == 16) {
                n2 = n + CodedOutputStream.computeBytesSize((int)5, (ByteString)this.getContentDispositionBytes());
            }
            n = n2;
            if ((this.bitField0_ & 32) == 32) {
                n = n2 + CodedOutputStream.computeBytesSize((int)6, (ByteString)this.getFileNameBytes());
            }
            n2 = n;
            if ((this.bitField0_ & 64) == 64) {
                n2 = n + CodedOutputStream.computeBytesSize((int)7, (ByteString)this.getContentIdBytes());
            }
            n = n2;
            if ((this.bitField0_ & 128) == 128) {
                n = n2 + CodedOutputStream.computeBytesSize((int)8, (ByteString)this.getContentLocationBytes());
            }
            n2 = n;
            if ((this.bitField0_ & 256) == 256) {
                n2 = n + CodedOutputStream.computeInt32Size((int)9, (int)this.contentTypeStart_);
            }
            n = n2;
            if ((this.bitField0_ & 512) == 512) {
                n = n2 + CodedOutputStream.computeBytesSize((int)10, (ByteString)this.getContentTypeTypeBytes());
            }
            n2 = n;
            if ((this.bitField0_ & 1024) == 1024) {
                n2 = n + CodedOutputStream.computeBytesSize((int)11, (ByteString)this.getTextBytes());
            }
            n = n2;
            if ((this.bitField0_ & 2048) == 2048) {
                n = n2 + CodedOutputStream.computeBytesSize((int)12, (ByteString)this.data_);
            }
            this.memoizedSerializedSize = n;
            return n;
        }

        public String getText() {
            Object object = this.text_;
            if (object instanceof String) {
                return (String)object;
            }
            object = (ByteString)object;
            String string = object.toStringUtf8();
            if (Internal.isValidUtf8((ByteString)object)) {
                this.text_ = string;
            }
            return string;
        }

        public boolean hasCharset() {
            if ((this.bitField0_ & 8) == 8) {
                return true;
            }
            return false;
        }

        public boolean hasContentDisposition() {
            if ((this.bitField0_ & 16) == 16) {
                return true;
            }
            return false;
        }

        public boolean hasContentId() {
            if ((this.bitField0_ & 64) == 64) {
                return true;
            }
            return false;
        }

        public boolean hasContentLocation() {
            if ((this.bitField0_ & 128) == 128) {
                return true;
            }
            return false;
        }

        public boolean hasContentType() {
            if ((this.bitField0_ & 2) == 2) {
                return true;
            }
            return false;
        }

        public boolean hasContentTypeStart() {
            if ((this.bitField0_ & 256) == 256) {
                return true;
            }
            return false;
        }

        public boolean hasContentTypeType() {
            if ((this.bitField0_ & 512) == 512) {
                return true;
            }
            return false;
        }

        public boolean hasData() {
            if ((this.bitField0_ & 2048) == 2048) {
                return true;
            }
            return false;
        }

        public boolean hasFileName() {
            if ((this.bitField0_ & 32) == 32) {
                return true;
            }
            return false;
        }

        public boolean hasName() {
            if ((this.bitField0_ & 4) == 4) {
                return true;
            }
            return false;
        }

        public boolean hasSequence() {
            if ((this.bitField0_ & 1) == 1) {
                return true;
            }
            return false;
        }

        public boolean hasText() {
            if ((this.bitField0_ & 1024) == 1024) {
                return true;
            }
            return false;
        }

        public final boolean isInitialized() {
            byte by = this.memoizedIsInitialized;
            if (by != -1) {
                if (by == 1) {
                    return true;
                }
                return false;
            }
            this.memoizedIsInitialized = 1;
            return true;
        }

        public Builder newBuilderForType() {
            return PduPart.newBuilder();
        }

        public Builder toBuilder() {
            return PduPart.newBuilder(this);
        }

        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            this.getSerializedSize();
            if ((this.bitField0_ & 1) == 1) {
                codedOutputStream.writeInt32(1, this.sequence_);
            }
            if ((this.bitField0_ & 2) == 2) {
                codedOutputStream.writeBytes(2, this.getContentTypeBytes());
            }
            if ((this.bitField0_ & 4) == 4) {
                codedOutputStream.writeBytes(3, this.getNameBytes());
            }
            if ((this.bitField0_ & 8) == 8) {
                codedOutputStream.writeInt32(4, this.charset_);
            }
            if ((this.bitField0_ & 16) == 16) {
                codedOutputStream.writeBytes(5, this.getContentDispositionBytes());
            }
            if ((this.bitField0_ & 32) == 32) {
                codedOutputStream.writeBytes(6, this.getFileNameBytes());
            }
            if ((this.bitField0_ & 64) == 64) {
                codedOutputStream.writeBytes(7, this.getContentIdBytes());
            }
            if ((this.bitField0_ & 128) == 128) {
                codedOutputStream.writeBytes(8, this.getContentLocationBytes());
            }
            if ((this.bitField0_ & 256) == 256) {
                codedOutputStream.writeInt32(9, this.contentTypeStart_);
            }
            if ((this.bitField0_ & 512) == 512) {
                codedOutputStream.writeBytes(10, this.getContentTypeTypeBytes());
            }
            if ((this.bitField0_ & 1024) == 1024) {
                codedOutputStream.writeBytes(11, this.getTextBytes());
            }
            if ((this.bitField0_ & 2048) == 2048) {
                codedOutputStream.writeBytes(12, this.data_);
            }
        }

        public static final class Builder
        extends GeneratedMessageLite.Builder<PduPart, Builder>
        implements PduPartOrBuilder {
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

            private Builder() {
                this.maybeForceBuilderInitialization();
            }

            private static Builder create() {
                return new Builder();
            }

            private void maybeForceBuilderInitialization() {
            }

            public PduPart build() {
                PduPart pduPart = this.buildPartial();
                if (!pduPart.isInitialized()) {
                    throw Builder.newUninitializedMessageException((MessageLite)pduPart);
                }
                return pduPart;
            }

            public PduPart buildPartial() {
                PduPart pduPart = new PduPart(this);
                int n = this.bitField0_;
                int n2 = 0;
                if ((n & 1) == 1) {
                    n2 = false | true;
                }
                pduPart.sequence_ = this.sequence_;
                int n3 = n2;
                if ((n & 2) == 2) {
                    n3 = n2 | 2;
                }
                pduPart.contentType_ = this.contentType_;
                n2 = n3;
                if ((n & 4) == 4) {
                    n2 = n3 | 4;
                }
                pduPart.name_ = this.name_;
                n3 = n2;
                if ((n & 8) == 8) {
                    n3 = n2 | 8;
                }
                pduPart.charset_ = this.charset_;
                n2 = n3;
                if ((n & 16) == 16) {
                    n2 = n3 | 16;
                }
                pduPart.contentDisposition_ = this.contentDisposition_;
                n3 = n2;
                if ((n & 32) == 32) {
                    n3 = n2 | 32;
                }
                pduPart.fileName_ = this.fileName_;
                n2 = n3;
                if ((n & 64) == 64) {
                    n2 = n3 | 64;
                }
                pduPart.contentId_ = this.contentId_;
                n3 = n2;
                if ((n & 128) == 128) {
                    n3 = n2 | 128;
                }
                pduPart.contentLocation_ = this.contentLocation_;
                n2 = n3;
                if ((n & 256) == 256) {
                    n2 = n3 | 256;
                }
                pduPart.contentTypeStart_ = this.contentTypeStart_;
                n3 = n2;
                if ((n & 512) == 512) {
                    n3 = n2 | 512;
                }
                pduPart.contentTypeType_ = this.contentTypeType_;
                n2 = n3;
                if ((n & 1024) == 1024) {
                    n2 = n3 | 1024;
                }
                pduPart.text_ = this.text_;
                n3 = n2;
                if ((n & 2048) == 2048) {
                    n3 = n2 | 2048;
                }
                pduPart.data_ = this.data_;
                pduPart.bitField0_ = n3;
                return pduPart;
            }

            public Builder clear() {
                super.clear();
                this.sequence_ = 0;
                this.bitField0_ &= -2;
                this.contentType_ = "";
                this.bitField0_ &= -3;
                this.name_ = "";
                this.bitField0_ &= -5;
                this.charset_ = 0;
                this.bitField0_ &= -9;
                this.contentDisposition_ = "";
                this.bitField0_ &= -17;
                this.fileName_ = "";
                this.bitField0_ &= -33;
                this.contentId_ = "";
                this.bitField0_ &= -65;
                this.contentLocation_ = "";
                this.bitField0_ &= -129;
                this.contentTypeStart_ = 0;
                this.bitField0_ &= -257;
                this.contentTypeType_ = "";
                this.bitField0_ &= -513;
                this.text_ = "";
                this.bitField0_ &= -1025;
                this.data_ = ByteString.EMPTY;
                this.bitField0_ &= -2049;
                return this;
            }

            public Builder clone() {
                return Builder.create().mergeFrom(this.buildPartial());
            }

            public PduPart getDefaultInstanceForType() {
                return PduPart.getDefaultInstance();
            }

            public final boolean isInitialized() {
                return true;
            }

            /*
             * Enabled aggressive block sorting
             * Lifted jumps to return sites
             */
            public Builder mergeFrom(PduPart pduPart) {
                if (pduPart == PduPart.getDefaultInstance()) {
                    return this;
                }
                if (pduPart.hasSequence()) {
                    this.setSequence(pduPart.getSequence());
                }
                if (pduPart.hasContentType()) {
                    this.setContentType(pduPart.getContentType());
                }
                if (pduPart.hasName()) {
                    this.setName(pduPart.getName());
                }
                if (pduPart.hasCharset()) {
                    this.setCharset(pduPart.getCharset());
                }
                if (pduPart.hasContentDisposition()) {
                    this.setContentDisposition(pduPart.getContentDisposition());
                }
                if (pduPart.hasFileName()) {
                    this.setFileName(pduPart.getFileName());
                }
                if (pduPart.hasContentId()) {
                    this.setContentId(pduPart.getContentId());
                }
                if (pduPart.hasContentLocation()) {
                    this.setContentLocation(pduPart.getContentLocation());
                }
                if (pduPart.hasContentTypeStart()) {
                    this.setContentTypeStart(pduPart.getContentTypeStart());
                }
                if (pduPart.hasContentTypeType()) {
                    this.setContentTypeType(pduPart.getContentTypeType());
                }
                if (pduPart.hasText()) {
                    this.setText(pduPart.getText());
                }
                if (!pduPart.hasData()) return this;
                this.setData(pduPart.getData());
                return this;
            }

            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                block15 : do {
                    int n = codedInputStream.readTag();
                    switch (n) {
                        default: {
                            if (this.parseUnknownField(codedInputStream, extensionRegistryLite, n)) continue block15;
                        }
                        case 0: {
                            return this;
                        }
                        case 8: {
                            this.bitField0_ |= 1;
                            this.sequence_ = codedInputStream.readInt32();
                            continue block15;
                        }
                        case 18: {
                            this.bitField0_ |= 2;
                            this.contentType_ = codedInputStream.readBytes();
                            continue block15;
                        }
                        case 26: {
                            this.bitField0_ |= 4;
                            this.name_ = codedInputStream.readBytes();
                            continue block15;
                        }
                        case 32: {
                            this.bitField0_ |= 8;
                            this.charset_ = codedInputStream.readInt32();
                            continue block15;
                        }
                        case 42: {
                            this.bitField0_ |= 16;
                            this.contentDisposition_ = codedInputStream.readBytes();
                            continue block15;
                        }
                        case 50: {
                            this.bitField0_ |= 32;
                            this.fileName_ = codedInputStream.readBytes();
                            continue block15;
                        }
                        case 58: {
                            this.bitField0_ |= 64;
                            this.contentId_ = codedInputStream.readBytes();
                            continue block15;
                        }
                        case 66: {
                            this.bitField0_ |= 128;
                            this.contentLocation_ = codedInputStream.readBytes();
                            continue block15;
                        }
                        case 72: {
                            this.bitField0_ |= 256;
                            this.contentTypeStart_ = codedInputStream.readInt32();
                            continue block15;
                        }
                        case 82: {
                            this.bitField0_ |= 512;
                            this.contentTypeType_ = codedInputStream.readBytes();
                            continue block15;
                        }
                        case 90: {
                            this.bitField0_ |= 1024;
                            this.text_ = codedInputStream.readBytes();
                            continue block15;
                        }
                        case 98: 
                    }
                    this.bitField0_ |= 2048;
                    this.data_ = codedInputStream.readBytes();
                } while (true);
            }

            public Builder setCharset(int n) {
                this.bitField0_ |= 8;
                this.charset_ = n;
                return this;
            }

            public Builder setContentDisposition(String string) {
                if (string == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 16;
                this.contentDisposition_ = string;
                return this;
            }

            public Builder setContentId(String string) {
                if (string == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 64;
                this.contentId_ = string;
                return this;
            }

            public Builder setContentLocation(String string) {
                if (string == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 128;
                this.contentLocation_ = string;
                return this;
            }

            public Builder setContentType(String string) {
                if (string == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 2;
                this.contentType_ = string;
                return this;
            }

            public Builder setContentTypeStart(int n) {
                this.bitField0_ |= 256;
                this.contentTypeStart_ = n;
                return this;
            }

            public Builder setContentTypeType(String string) {
                if (string == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 512;
                this.contentTypeType_ = string;
                return this;
            }

            public Builder setData(ByteString byteString) {
                if (byteString == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 2048;
                this.data_ = byteString;
                return this;
            }

            public Builder setFileName(String string) {
                if (string == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 32;
                this.fileName_ = string;
                return this;
            }

            public Builder setName(String string) {
                if (string == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 4;
                this.name_ = string;
                return this;
            }

            public Builder setSequence(int n) {
                this.bitField0_ |= 1;
                this.sequence_ = n;
                return this;
            }

            public Builder setText(String string) {
                if (string == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 1024;
                this.text_ = string;
                return this;
            }
        }

    }

    public static interface PduPartOrBuilder
    extends MessageLiteOrBuilder {
    }

}

