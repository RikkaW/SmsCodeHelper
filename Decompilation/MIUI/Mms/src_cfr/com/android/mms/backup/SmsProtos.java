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

public final class SmsProtos {
    private SmsProtos() {
    }

    public static final class MmsSms
    extends GeneratedMessageLite
    implements MmsSmsOrBuilder {
        private static final MmsSms defaultInstance = new MmsSms(true);
        private static final long serialVersionUID = 0;
        private byte memoizedIsInitialized = -1;
        private int memoizedSerializedSize = -1;
        private List<SmsBookmark> smsBookmark_;
        private List<Sms> sms_;

        static {
            defaultInstance.initFields();
        }

        private MmsSms(Builder builder) {
            super((GeneratedMessageLite.Builder)builder);
        }

        private MmsSms(boolean bl) {
        }

        public static MmsSms getDefaultInstance() {
            return defaultInstance;
        }

        private void initFields() {
            this.sms_ = Collections.emptyList();
            this.smsBookmark_ = Collections.emptyList();
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public static Builder newBuilder(MmsSms mmsSms) {
            return MmsSms.newBuilder().mergeFrom(mmsSms);
        }

        public MmsSms getDefaultInstanceForType() {
            return defaultInstance;
        }

        public int getSerializedSize() {
            int n;
            int n2 = this.memoizedSerializedSize;
            if (n2 != -1) {
                return n2;
            }
            n2 = 0;
            for (n = 0; n < this.sms_.size(); ++n) {
                n2 += CodedOutputStream.computeMessageSize((int)1, (MessageLite)((MessageLite)this.sms_.get(n)));
            }
            int n3 = 0;
            n = n2;
            for (n2 = n3; n2 < this.smsBookmark_.size(); ++n2) {
                n += CodedOutputStream.computeMessageSize((int)2, (MessageLite)((MessageLite)this.smsBookmark_.get(n2)));
            }
            this.memoizedSerializedSize = n;
            return n;
        }

        public List<Sms> getSmsList() {
            return this.sms_;
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
            return MmsSms.newBuilder();
        }

        public Builder toBuilder() {
            return MmsSms.newBuilder(this);
        }

        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            int n;
            this.getSerializedSize();
            for (n = 0; n < this.sms_.size(); ++n) {
                codedOutputStream.writeMessage(1, (MessageLite)this.sms_.get(n));
            }
            for (n = 0; n < this.smsBookmark_.size(); ++n) {
                codedOutputStream.writeMessage(2, (MessageLite)this.smsBookmark_.get(n));
            }
        }

        public static final class Builder
        extends GeneratedMessageLite.Builder<MmsSms, Builder>
        implements MmsSmsOrBuilder {
            private int bitField0_;
            private List<SmsBookmark> smsBookmark_ = Collections.emptyList();
            private List<Sms> sms_ = Collections.emptyList();

            private Builder() {
                this.maybeForceBuilderInitialization();
            }

            private static Builder create() {
                return new Builder();
            }

            private void ensureSmsBookmarkIsMutable() {
                if ((this.bitField0_ & 2) != 2) {
                    this.smsBookmark_ = new ArrayList(this.smsBookmark_);
                    this.bitField0_ |= 2;
                }
            }

            private void ensureSmsIsMutable() {
                if ((this.bitField0_ & 1) != 1) {
                    this.sms_ = new ArrayList(this.sms_);
                    this.bitField0_ |= 1;
                }
            }

            private void maybeForceBuilderInitialization() {
            }

            public Builder addSms(Sms sms) {
                if (sms == null) {
                    throw new NullPointerException();
                }
                this.ensureSmsIsMutable();
                this.sms_.add(sms);
                return this;
            }

            public Builder addSmsBookmark(SmsBookmark smsBookmark) {
                if (smsBookmark == null) {
                    throw new NullPointerException();
                }
                this.ensureSmsBookmarkIsMutable();
                this.smsBookmark_.add(smsBookmark);
                return this;
            }

            public MmsSms build() {
                MmsSms mmsSms = this.buildPartial();
                if (!mmsSms.isInitialized()) {
                    throw Builder.newUninitializedMessageException((MessageLite)mmsSms);
                }
                return mmsSms;
            }

            public MmsSms buildPartial() {
                MmsSms mmsSms = new MmsSms(this);
                int n = this.bitField0_;
                if ((this.bitField0_ & 1) == 1) {
                    this.sms_ = Collections.unmodifiableList(this.sms_);
                    this.bitField0_ &= -2;
                }
                mmsSms.sms_ = this.sms_;
                if ((this.bitField0_ & 2) == 2) {
                    this.smsBookmark_ = Collections.unmodifiableList(this.smsBookmark_);
                    this.bitField0_ &= -3;
                }
                mmsSms.smsBookmark_ = this.smsBookmark_;
                return mmsSms;
            }

            public Builder clear() {
                super.clear();
                this.sms_ = Collections.emptyList();
                this.bitField0_ &= -2;
                this.smsBookmark_ = Collections.emptyList();
                this.bitField0_ &= -3;
                return this;
            }

            public Builder clone() {
                return Builder.create().mergeFrom(this.buildPartial());
            }

            public MmsSms getDefaultInstanceForType() {
                return MmsSms.getDefaultInstance();
            }

            public final boolean isInitialized() {
                return true;
            }

            /*
             * Enabled aggressive block sorting
             * Lifted jumps to return sites
             */
            public Builder mergeFrom(MmsSms mmsSms) {
                if (mmsSms == MmsSms.getDefaultInstance()) {
                    return this;
                }
                if (!mmsSms.sms_.isEmpty()) {
                    if (this.sms_.isEmpty()) {
                        this.sms_ = mmsSms.sms_;
                        this.bitField0_ &= -2;
                    } else {
                        this.ensureSmsIsMutable();
                        this.sms_.addAll(mmsSms.sms_);
                    }
                }
                if (mmsSms.smsBookmark_.isEmpty()) return this;
                if (this.smsBookmark_.isEmpty()) {
                    this.smsBookmark_ = mmsSms.smsBookmark_;
                    this.bitField0_ &= -3;
                    return this;
                }
                this.ensureSmsBookmarkIsMutable();
                this.smsBookmark_.addAll(mmsSms.smsBookmark_);
                return this;
            }

            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                block5 : do {
                    Sms.Builder builder;
                    int n = codedInputStream.readTag();
                    switch (n) {
                        default: {
                            if (this.parseUnknownField(codedInputStream, extensionRegistryLite, n)) continue block5;
                        }
                        case 0: {
                            return this;
                        }
                        case 10: {
                            builder = Sms.newBuilder();
                            codedInputStream.readMessage((MessageLite.Builder)builder, extensionRegistryLite);
                            this.addSms(builder.buildPartial());
                            continue block5;
                        }
                        case 18: 
                    }
                    builder = SmsBookmark.newBuilder();
                    codedInputStream.readMessage((MessageLite.Builder)builder, extensionRegistryLite);
                    this.addSmsBookmark(builder.buildPartial());
                } while (true);
            }
        }

    }

    public static interface MmsSmsOrBuilder
    extends MessageLiteOrBuilder {
    }

    public static final class Sms
    extends GeneratedMessageLite
    implements SmsOrBuilder {
        private static final Sms defaultInstance = new Sms(true);
        private static final long serialVersionUID = 0;
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

        static {
            defaultInstance.initFields();
        }

        private Sms(Builder builder) {
            super((GeneratedMessageLite.Builder)builder);
        }

        private Sms(boolean bl) {
        }

        private ByteString getAddressBytes() {
            Object object = this.address_;
            if (object instanceof String) {
                this.address_ = object = ByteString.copyFromUtf8((String)((String)object));
                return object;
            }
            return (ByteString)object;
        }

        private ByteString getBodyBytes() {
            Object object = this.body_;
            if (object instanceof String) {
                this.body_ = object = ByteString.copyFromUtf8((String)((String)object));
                return object;
            }
            return (ByteString)object;
        }

        public static Sms getDefaultInstance() {
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

        private ByteString getServiceCenterBytes() {
            Object object = this.serviceCenter_;
            if (object instanceof String) {
                this.serviceCenter_ = object = ByteString.copyFromUtf8((String)((String)object));
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

        private void initFields() {
            this.guid_ = "";
            this.luid_ = "";
            this.type_ = 0;
            this.address_ = "";
            this.subject_ = "";
            this.body_ = "";
            this.date_ = 0;
            this.read_ = false;
            this.seen_ = false;
            this.status_ = 0;
            this.serverDate_ = 0;
            this.serviceCenter_ = "";
            this.protocol_ = 0;
            this.replyPathPresent_ = false;
            this.locked_ = false;
            this.mxId_ = 0;
            this.mxStatus_ = 0;
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public static Builder newBuilder(Sms sms) {
            return Sms.newBuilder().mergeFrom(sms);
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

        public String getBody() {
            Object object = this.body_;
            if (object instanceof String) {
                return (String)object;
            }
            object = (ByteString)object;
            String string = object.toStringUtf8();
            if (Internal.isValidUtf8((ByteString)object)) {
                this.body_ = string;
            }
            return string;
        }

        public long getDate() {
            return this.date_;
        }

        public Sms getDefaultInstanceForType() {
            return defaultInstance;
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

        public long getMxId() {
            return this.mxId_;
        }

        public int getMxStatus() {
            return this.mxStatus_;
        }

        public int getProtocol() {
            return this.protocol_;
        }

        public boolean getRead() {
            return this.read_;
        }

        public boolean getReplyPathPresent() {
            return this.replyPathPresent_;
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
                n2 = n + CodedOutputStream.computeInt32Size((int)3, (int)this.type_);
            }
            n = n2;
            if ((this.bitField0_ & 8) == 8) {
                n = n2 + CodedOutputStream.computeBytesSize((int)4, (ByteString)this.getAddressBytes());
            }
            n2 = n;
            if ((this.bitField0_ & 16) == 16) {
                n2 = n + CodedOutputStream.computeBytesSize((int)5, (ByteString)this.getSubjectBytes());
            }
            n = n2;
            if ((this.bitField0_ & 32) == 32) {
                n = n2 + CodedOutputStream.computeBytesSize((int)6, (ByteString)this.getBodyBytes());
            }
            n2 = n;
            if ((this.bitField0_ & 64) == 64) {
                n2 = n + CodedOutputStream.computeInt64Size((int)7, (long)this.date_);
            }
            n = n2;
            if ((this.bitField0_ & 128) == 128) {
                n = n2 + CodedOutputStream.computeBoolSize((int)8, (boolean)this.read_);
            }
            n2 = n;
            if ((this.bitField0_ & 256) == 256) {
                n2 = n + CodedOutputStream.computeBoolSize((int)9, (boolean)this.seen_);
            }
            n = n2;
            if ((this.bitField0_ & 512) == 512) {
                n = n2 + CodedOutputStream.computeInt32Size((int)10, (int)this.status_);
            }
            n2 = n;
            if ((this.bitField0_ & 1024) == 1024) {
                n2 = n + CodedOutputStream.computeInt64Size((int)11, (long)this.serverDate_);
            }
            n = n2;
            if ((this.bitField0_ & 2048) == 2048) {
                n = n2 + CodedOutputStream.computeBytesSize((int)12, (ByteString)this.getServiceCenterBytes());
            }
            n2 = n;
            if ((this.bitField0_ & 4096) == 4096) {
                n2 = n + CodedOutputStream.computeInt32Size((int)13, (int)this.protocol_);
            }
            n = n2;
            if ((this.bitField0_ & 8192) == 8192) {
                n = n2 + CodedOutputStream.computeBoolSize((int)14, (boolean)this.replyPathPresent_);
            }
            n2 = n;
            if ((this.bitField0_ & 16384) == 16384) {
                n2 = n + CodedOutputStream.computeBoolSize((int)15, (boolean)this.locked_);
            }
            n = n2;
            if ((this.bitField0_ & 32768) == 32768) {
                n = n2 + CodedOutputStream.computeInt64Size((int)16, (long)this.mxId_);
            }
            n2 = n;
            if ((this.bitField0_ & 65536) == 65536) {
                n2 = n + CodedOutputStream.computeInt32Size((int)17, (int)this.mxStatus_);
            }
            this.memoizedSerializedSize = n2;
            return n2;
        }

        public long getServerDate() {
            return this.serverDate_;
        }

        public String getServiceCenter() {
            Object object = this.serviceCenter_;
            if (object instanceof String) {
                return (String)object;
            }
            object = (ByteString)object;
            String string = object.toStringUtf8();
            if (Internal.isValidUtf8((ByteString)object)) {
                this.serviceCenter_ = string;
            }
            return string;
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

        public int getType() {
            return this.type_;
        }

        public boolean hasAddress() {
            if ((this.bitField0_ & 8) == 8) {
                return true;
            }
            return false;
        }

        public boolean hasBody() {
            if ((this.bitField0_ & 32) == 32) {
                return true;
            }
            return false;
        }

        public boolean hasDate() {
            if ((this.bitField0_ & 64) == 64) {
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
            if ((this.bitField0_ & 16384) == 16384) {
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

        public boolean hasMxId() {
            if ((this.bitField0_ & 32768) == 32768) {
                return true;
            }
            return false;
        }

        public boolean hasMxStatus() {
            if ((this.bitField0_ & 65536) == 65536) {
                return true;
            }
            return false;
        }

        public boolean hasProtocol() {
            if ((this.bitField0_ & 4096) == 4096) {
                return true;
            }
            return false;
        }

        public boolean hasRead() {
            if ((this.bitField0_ & 128) == 128) {
                return true;
            }
            return false;
        }

        public boolean hasReplyPathPresent() {
            if ((this.bitField0_ & 8192) == 8192) {
                return true;
            }
            return false;
        }

        public boolean hasSeen() {
            if ((this.bitField0_ & 256) == 256) {
                return true;
            }
            return false;
        }

        public boolean hasServerDate() {
            if ((this.bitField0_ & 1024) == 1024) {
                return true;
            }
            return false;
        }

        public boolean hasServiceCenter() {
            if ((this.bitField0_ & 2048) == 2048) {
                return true;
            }
            return false;
        }

        public boolean hasStatus() {
            if ((this.bitField0_ & 512) == 512) {
                return true;
            }
            return false;
        }

        public boolean hasSubject() {
            if ((this.bitField0_ & 16) == 16) {
                return true;
            }
            return false;
        }

        public boolean hasType() {
            if ((this.bitField0_ & 4) == 4) {
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
            return Sms.newBuilder();
        }

        public Builder toBuilder() {
            return Sms.newBuilder(this);
        }

        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            this.getSerializedSize();
            if ((this.bitField0_ & 1) == 1) {
                codedOutputStream.writeBytes(1, this.getGuidBytes());
            }
            if ((this.bitField0_ & 2) == 2) {
                codedOutputStream.writeBytes(2, this.getLuidBytes());
            }
            if ((this.bitField0_ & 4) == 4) {
                codedOutputStream.writeInt32(3, this.type_);
            }
            if ((this.bitField0_ & 8) == 8) {
                codedOutputStream.writeBytes(4, this.getAddressBytes());
            }
            if ((this.bitField0_ & 16) == 16) {
                codedOutputStream.writeBytes(5, this.getSubjectBytes());
            }
            if ((this.bitField0_ & 32) == 32) {
                codedOutputStream.writeBytes(6, this.getBodyBytes());
            }
            if ((this.bitField0_ & 64) == 64) {
                codedOutputStream.writeInt64(7, this.date_);
            }
            if ((this.bitField0_ & 128) == 128) {
                codedOutputStream.writeBool(8, this.read_);
            }
            if ((this.bitField0_ & 256) == 256) {
                codedOutputStream.writeBool(9, this.seen_);
            }
            if ((this.bitField0_ & 512) == 512) {
                codedOutputStream.writeInt32(10, this.status_);
            }
            if ((this.bitField0_ & 1024) == 1024) {
                codedOutputStream.writeInt64(11, this.serverDate_);
            }
            if ((this.bitField0_ & 2048) == 2048) {
                codedOutputStream.writeBytes(12, this.getServiceCenterBytes());
            }
            if ((this.bitField0_ & 4096) == 4096) {
                codedOutputStream.writeInt32(13, this.protocol_);
            }
            if ((this.bitField0_ & 8192) == 8192) {
                codedOutputStream.writeBool(14, this.replyPathPresent_);
            }
            if ((this.bitField0_ & 16384) == 16384) {
                codedOutputStream.writeBool(15, this.locked_);
            }
            if ((this.bitField0_ & 32768) == 32768) {
                codedOutputStream.writeInt64(16, this.mxId_);
            }
            if ((this.bitField0_ & 65536) == 65536) {
                codedOutputStream.writeInt32(17, this.mxStatus_);
            }
        }

        public static final class Builder
        extends GeneratedMessageLite.Builder<Sms, Builder>
        implements SmsOrBuilder {
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

            private Builder() {
                this.maybeForceBuilderInitialization();
            }

            private static Builder create() {
                return new Builder();
            }

            private void maybeForceBuilderInitialization() {
            }

            public Sms build() {
                Sms sms = this.buildPartial();
                if (!sms.isInitialized()) {
                    throw Builder.newUninitializedMessageException((MessageLite)sms);
                }
                return sms;
            }

            public Sms buildPartial() {
                Sms sms = new Sms(this);
                int n = this.bitField0_;
                int n2 = 0;
                if ((n & 1) == 1) {
                    n2 = false | true;
                }
                sms.guid_ = this.guid_;
                int n3 = n2;
                if ((n & 2) == 2) {
                    n3 = n2 | 2;
                }
                sms.luid_ = this.luid_;
                n2 = n3;
                if ((n & 4) == 4) {
                    n2 = n3 | 4;
                }
                sms.type_ = this.type_;
                n3 = n2;
                if ((n & 8) == 8) {
                    n3 = n2 | 8;
                }
                sms.address_ = this.address_;
                n2 = n3;
                if ((n & 16) == 16) {
                    n2 = n3 | 16;
                }
                sms.subject_ = this.subject_;
                n3 = n2;
                if ((n & 32) == 32) {
                    n3 = n2 | 32;
                }
                sms.body_ = this.body_;
                n2 = n3;
                if ((n & 64) == 64) {
                    n2 = n3 | 64;
                }
                sms.date_ = this.date_;
                n3 = n2;
                if ((n & 128) == 128) {
                    n3 = n2 | 128;
                }
                sms.read_ = this.read_;
                n2 = n3;
                if ((n & 256) == 256) {
                    n2 = n3 | 256;
                }
                sms.seen_ = this.seen_;
                n3 = n2;
                if ((n & 512) == 512) {
                    n3 = n2 | 512;
                }
                sms.status_ = this.status_;
                n2 = n3;
                if ((n & 1024) == 1024) {
                    n2 = n3 | 1024;
                }
                sms.serverDate_ = this.serverDate_;
                n3 = n2;
                if ((n & 2048) == 2048) {
                    n3 = n2 | 2048;
                }
                sms.serviceCenter_ = this.serviceCenter_;
                n2 = n3;
                if ((n & 4096) == 4096) {
                    n2 = n3 | 4096;
                }
                sms.protocol_ = this.protocol_;
                n3 = n2;
                if ((n & 8192) == 8192) {
                    n3 = n2 | 8192;
                }
                sms.replyPathPresent_ = this.replyPathPresent_;
                n2 = n3;
                if ((n & 16384) == 16384) {
                    n2 = n3 | 16384;
                }
                sms.locked_ = this.locked_;
                n3 = n2;
                if ((n & 32768) == 32768) {
                    n3 = n2 | 32768;
                }
                sms.mxId_ = this.mxId_;
                n2 = n3;
                if ((n & 65536) == 65536) {
                    n2 = n3 | 65536;
                }
                sms.mxStatus_ = this.mxStatus_;
                sms.bitField0_ = n2;
                return sms;
            }

            public Builder clear() {
                super.clear();
                this.guid_ = "";
                this.bitField0_ &= -2;
                this.luid_ = "";
                this.bitField0_ &= -3;
                this.type_ = 0;
                this.bitField0_ &= -5;
                this.address_ = "";
                this.bitField0_ &= -9;
                this.subject_ = "";
                this.bitField0_ &= -17;
                this.body_ = "";
                this.bitField0_ &= -33;
                this.date_ = 0;
                this.bitField0_ &= -65;
                this.read_ = false;
                this.bitField0_ &= -129;
                this.seen_ = false;
                this.bitField0_ &= -257;
                this.status_ = 0;
                this.bitField0_ &= -513;
                this.serverDate_ = 0;
                this.bitField0_ &= -1025;
                this.serviceCenter_ = "";
                this.bitField0_ &= -2049;
                this.protocol_ = 0;
                this.bitField0_ &= -4097;
                this.replyPathPresent_ = false;
                this.bitField0_ &= -8193;
                this.locked_ = false;
                this.bitField0_ &= -16385;
                this.mxId_ = 0;
                this.bitField0_ &= -32769;
                this.mxStatus_ = 0;
                this.bitField0_ &= -65537;
                return this;
            }

            public Builder clone() {
                return Builder.create().mergeFrom(this.buildPartial());
            }

            public Sms getDefaultInstanceForType() {
                return Sms.getDefaultInstance();
            }

            public final boolean isInitialized() {
                return true;
            }

            /*
             * Enabled aggressive block sorting
             * Lifted jumps to return sites
             */
            public Builder mergeFrom(Sms sms) {
                if (sms == Sms.getDefaultInstance()) {
                    return this;
                }
                if (sms.hasGuid()) {
                    this.setGuid(sms.getGuid());
                }
                if (sms.hasLuid()) {
                    this.setLuid(sms.getLuid());
                }
                if (sms.hasType()) {
                    this.setType(sms.getType());
                }
                if (sms.hasAddress()) {
                    this.setAddress(sms.getAddress());
                }
                if (sms.hasSubject()) {
                    this.setSubject(sms.getSubject());
                }
                if (sms.hasBody()) {
                    this.setBody(sms.getBody());
                }
                if (sms.hasDate()) {
                    this.setDate(sms.getDate());
                }
                if (sms.hasRead()) {
                    this.setRead(sms.getRead());
                }
                if (sms.hasSeen()) {
                    this.setSeen(sms.getSeen());
                }
                if (sms.hasStatus()) {
                    this.setStatus(sms.getStatus());
                }
                if (sms.hasServerDate()) {
                    this.setServerDate(sms.getServerDate());
                }
                if (sms.hasServiceCenter()) {
                    this.setServiceCenter(sms.getServiceCenter());
                }
                if (sms.hasProtocol()) {
                    this.setProtocol(sms.getProtocol());
                }
                if (sms.hasReplyPathPresent()) {
                    this.setReplyPathPresent(sms.getReplyPathPresent());
                }
                if (sms.hasLocked()) {
                    this.setLocked(sms.getLocked());
                }
                if (sms.hasMxId()) {
                    this.setMxId(sms.getMxId());
                }
                if (!sms.hasMxStatus()) return this;
                this.setMxStatus(sms.getMxStatus());
                return this;
            }

            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                block20 : do {
                    int n = codedInputStream.readTag();
                    switch (n) {
                        default: {
                            if (this.parseUnknownField(codedInputStream, extensionRegistryLite, n)) continue block20;
                        }
                        case 0: {
                            return this;
                        }
                        case 10: {
                            this.bitField0_ |= 1;
                            this.guid_ = codedInputStream.readBytes();
                            continue block20;
                        }
                        case 18: {
                            this.bitField0_ |= 2;
                            this.luid_ = codedInputStream.readBytes();
                            continue block20;
                        }
                        case 24: {
                            this.bitField0_ |= 4;
                            this.type_ = codedInputStream.readInt32();
                            continue block20;
                        }
                        case 34: {
                            this.bitField0_ |= 8;
                            this.address_ = codedInputStream.readBytes();
                            continue block20;
                        }
                        case 42: {
                            this.bitField0_ |= 16;
                            this.subject_ = codedInputStream.readBytes();
                            continue block20;
                        }
                        case 50: {
                            this.bitField0_ |= 32;
                            this.body_ = codedInputStream.readBytes();
                            continue block20;
                        }
                        case 56: {
                            this.bitField0_ |= 64;
                            this.date_ = codedInputStream.readInt64();
                            continue block20;
                        }
                        case 64: {
                            this.bitField0_ |= 128;
                            this.read_ = codedInputStream.readBool();
                            continue block20;
                        }
                        case 72: {
                            this.bitField0_ |= 256;
                            this.seen_ = codedInputStream.readBool();
                            continue block20;
                        }
                        case 80: {
                            this.bitField0_ |= 512;
                            this.status_ = codedInputStream.readInt32();
                            continue block20;
                        }
                        case 88: {
                            this.bitField0_ |= 1024;
                            this.serverDate_ = codedInputStream.readInt64();
                            continue block20;
                        }
                        case 98: {
                            this.bitField0_ |= 2048;
                            this.serviceCenter_ = codedInputStream.readBytes();
                            continue block20;
                        }
                        case 104: {
                            this.bitField0_ |= 4096;
                            this.protocol_ = codedInputStream.readInt32();
                            continue block20;
                        }
                        case 112: {
                            this.bitField0_ |= 8192;
                            this.replyPathPresent_ = codedInputStream.readBool();
                            continue block20;
                        }
                        case 120: {
                            this.bitField0_ |= 16384;
                            this.locked_ = codedInputStream.readBool();
                            continue block20;
                        }
                        case 128: {
                            this.bitField0_ |= 32768;
                            this.mxId_ = codedInputStream.readInt64();
                            continue block20;
                        }
                        case 136: 
                    }
                    this.bitField0_ |= 65536;
                    this.mxStatus_ = codedInputStream.readInt32();
                } while (true);
            }

            public Builder setAddress(String string) {
                if (string == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 8;
                this.address_ = string;
                return this;
            }

            public Builder setBody(String string) {
                if (string == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 32;
                this.body_ = string;
                return this;
            }

            public Builder setDate(long l) {
                this.bitField0_ |= 64;
                this.date_ = l;
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
                this.bitField0_ |= 16384;
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

            public Builder setMxId(long l) {
                this.bitField0_ |= 32768;
                this.mxId_ = l;
                return this;
            }

            public Builder setMxStatus(int n) {
                this.bitField0_ |= 65536;
                this.mxStatus_ = n;
                return this;
            }

            public Builder setProtocol(int n) {
                this.bitField0_ |= 4096;
                this.protocol_ = n;
                return this;
            }

            public Builder setRead(boolean bl) {
                this.bitField0_ |= 128;
                this.read_ = bl;
                return this;
            }

            public Builder setReplyPathPresent(boolean bl) {
                this.bitField0_ |= 8192;
                this.replyPathPresent_ = bl;
                return this;
            }

            public Builder setSeen(boolean bl) {
                this.bitField0_ |= 256;
                this.seen_ = bl;
                return this;
            }

            public Builder setServerDate(long l) {
                this.bitField0_ |= 1024;
                this.serverDate_ = l;
                return this;
            }

            public Builder setServiceCenter(String string) {
                if (string == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 2048;
                this.serviceCenter_ = string;
                return this;
            }

            public Builder setStatus(int n) {
                this.bitField0_ |= 512;
                this.status_ = n;
                return this;
            }

            public Builder setSubject(String string) {
                if (string == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 16;
                this.subject_ = string;
                return this;
            }

            public Builder setType(int n) {
                this.bitField0_ |= 4;
                this.type_ = n;
                return this;
            }
        }

    }

    public static final class SmsBookmark
    extends GeneratedMessageLite
    implements SmsBookmarkOrBuilder {
        private static final SmsBookmark defaultInstance = new SmsBookmark(true);
        private static final long serialVersionUID = 0;
        private Object address_;
        private int bitField0_;
        private Object body_;
        private int forward_;
        private Object fromName_;
        private Object guid_;
        private Object luid_;
        private long markDate_;
        private byte memoizedIsInitialized = -1;
        private int memoizedSerializedSize = -1;
        private int status_;
        private Object subject_;

        static {
            defaultInstance.initFields();
        }

        private SmsBookmark(Builder builder) {
            super((GeneratedMessageLite.Builder)builder);
        }

        private SmsBookmark(boolean bl) {
        }

        private ByteString getAddressBytes() {
            Object object = this.address_;
            if (object instanceof String) {
                this.address_ = object = ByteString.copyFromUtf8((String)((String)object));
                return object;
            }
            return (ByteString)object;
        }

        private ByteString getBodyBytes() {
            Object object = this.body_;
            if (object instanceof String) {
                this.body_ = object = ByteString.copyFromUtf8((String)((String)object));
                return object;
            }
            return (ByteString)object;
        }

        public static SmsBookmark getDefaultInstance() {
            return defaultInstance;
        }

        private ByteString getFromNameBytes() {
            Object object = this.fromName_;
            if (object instanceof String) {
                this.fromName_ = object = ByteString.copyFromUtf8((String)((String)object));
                return object;
            }
            return (ByteString)object;
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

        private ByteString getSubjectBytes() {
            Object object = this.subject_;
            if (object instanceof String) {
                this.subject_ = object = ByteString.copyFromUtf8((String)((String)object));
                return object;
            }
            return (ByteString)object;
        }

        private void initFields() {
            this.guid_ = "";
            this.luid_ = "";
            this.fromName_ = "";
            this.address_ = "";
            this.subject_ = "";
            this.body_ = "";
            this.markDate_ = 0;
            this.forward_ = 0;
            this.status_ = 0;
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public static Builder newBuilder(SmsBookmark smsBookmark) {
            return SmsBookmark.newBuilder().mergeFrom(smsBookmark);
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

        public String getBody() {
            Object object = this.body_;
            if (object instanceof String) {
                return (String)object;
            }
            object = (ByteString)object;
            String string = object.toStringUtf8();
            if (Internal.isValidUtf8((ByteString)object)) {
                this.body_ = string;
            }
            return string;
        }

        public SmsBookmark getDefaultInstanceForType() {
            return defaultInstance;
        }

        public int getForward() {
            return this.forward_;
        }

        public String getFromName() {
            Object object = this.fromName_;
            if (object instanceof String) {
                return (String)object;
            }
            object = (ByteString)object;
            String string = object.toStringUtf8();
            if (Internal.isValidUtf8((ByteString)object)) {
                this.fromName_ = string;
            }
            return string;
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

        public long getMarkDate() {
            return this.markDate_;
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
                n2 = n + CodedOutputStream.computeBytesSize((int)3, (ByteString)this.getFromNameBytes());
            }
            n = n2;
            if ((this.bitField0_ & 8) == 8) {
                n = n2 + CodedOutputStream.computeBytesSize((int)4, (ByteString)this.getAddressBytes());
            }
            n2 = n;
            if ((this.bitField0_ & 16) == 16) {
                n2 = n + CodedOutputStream.computeBytesSize((int)5, (ByteString)this.getSubjectBytes());
            }
            n = n2;
            if ((this.bitField0_ & 32) == 32) {
                n = n2 + CodedOutputStream.computeBytesSize((int)6, (ByteString)this.getBodyBytes());
            }
            n2 = n;
            if ((this.bitField0_ & 64) == 64) {
                n2 = n + CodedOutputStream.computeInt64Size((int)7, (long)this.markDate_);
            }
            n = n2;
            if ((this.bitField0_ & 128) == 128) {
                n = n2 + CodedOutputStream.computeInt32Size((int)8, (int)this.forward_);
            }
            n2 = n;
            if ((this.bitField0_ & 256) == 256) {
                n2 = n + CodedOutputStream.computeInt32Size((int)9, (int)this.status_);
            }
            this.memoizedSerializedSize = n2;
            return n2;
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

        public boolean hasAddress() {
            if ((this.bitField0_ & 8) == 8) {
                return true;
            }
            return false;
        }

        public boolean hasBody() {
            if ((this.bitField0_ & 32) == 32) {
                return true;
            }
            return false;
        }

        public boolean hasForward() {
            if ((this.bitField0_ & 128) == 128) {
                return true;
            }
            return false;
        }

        public boolean hasFromName() {
            if ((this.bitField0_ & 4) == 4) {
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

        public boolean hasLuid() {
            if ((this.bitField0_ & 2) == 2) {
                return true;
            }
            return false;
        }

        public boolean hasMarkDate() {
            if ((this.bitField0_ & 64) == 64) {
                return true;
            }
            return false;
        }

        public boolean hasStatus() {
            if ((this.bitField0_ & 256) == 256) {
                return true;
            }
            return false;
        }

        public boolean hasSubject() {
            if ((this.bitField0_ & 16) == 16) {
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
            return SmsBookmark.newBuilder();
        }

        public Builder toBuilder() {
            return SmsBookmark.newBuilder(this);
        }

        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            this.getSerializedSize();
            if ((this.bitField0_ & 1) == 1) {
                codedOutputStream.writeBytes(1, this.getGuidBytes());
            }
            if ((this.bitField0_ & 2) == 2) {
                codedOutputStream.writeBytes(2, this.getLuidBytes());
            }
            if ((this.bitField0_ & 4) == 4) {
                codedOutputStream.writeBytes(3, this.getFromNameBytes());
            }
            if ((this.bitField0_ & 8) == 8) {
                codedOutputStream.writeBytes(4, this.getAddressBytes());
            }
            if ((this.bitField0_ & 16) == 16) {
                codedOutputStream.writeBytes(5, this.getSubjectBytes());
            }
            if ((this.bitField0_ & 32) == 32) {
                codedOutputStream.writeBytes(6, this.getBodyBytes());
            }
            if ((this.bitField0_ & 64) == 64) {
                codedOutputStream.writeInt64(7, this.markDate_);
            }
            if ((this.bitField0_ & 128) == 128) {
                codedOutputStream.writeInt32(8, this.forward_);
            }
            if ((this.bitField0_ & 256) == 256) {
                codedOutputStream.writeInt32(9, this.status_);
            }
        }

        public static final class Builder
        extends GeneratedMessageLite.Builder<SmsBookmark, Builder>
        implements SmsBookmarkOrBuilder {
            private Object address_ = "";
            private int bitField0_;
            private Object body_ = "";
            private int forward_;
            private Object fromName_ = "";
            private Object guid_ = "";
            private Object luid_ = "";
            private long markDate_;
            private int status_;
            private Object subject_ = "";

            private Builder() {
                this.maybeForceBuilderInitialization();
            }

            private static Builder create() {
                return new Builder();
            }

            private void maybeForceBuilderInitialization() {
            }

            public SmsBookmark build() {
                SmsBookmark smsBookmark = this.buildPartial();
                if (!smsBookmark.isInitialized()) {
                    throw Builder.newUninitializedMessageException((MessageLite)smsBookmark);
                }
                return smsBookmark;
            }

            public SmsBookmark buildPartial() {
                SmsBookmark smsBookmark = new SmsBookmark(this);
                int n = this.bitField0_;
                int n2 = 0;
                if ((n & 1) == 1) {
                    n2 = false | true;
                }
                smsBookmark.guid_ = this.guid_;
                int n3 = n2;
                if ((n & 2) == 2) {
                    n3 = n2 | 2;
                }
                smsBookmark.luid_ = this.luid_;
                n2 = n3;
                if ((n & 4) == 4) {
                    n2 = n3 | 4;
                }
                smsBookmark.fromName_ = this.fromName_;
                n3 = n2;
                if ((n & 8) == 8) {
                    n3 = n2 | 8;
                }
                smsBookmark.address_ = this.address_;
                n2 = n3;
                if ((n & 16) == 16) {
                    n2 = n3 | 16;
                }
                smsBookmark.subject_ = this.subject_;
                n3 = n2;
                if ((n & 32) == 32) {
                    n3 = n2 | 32;
                }
                smsBookmark.body_ = this.body_;
                n2 = n3;
                if ((n & 64) == 64) {
                    n2 = n3 | 64;
                }
                smsBookmark.markDate_ = this.markDate_;
                n3 = n2;
                if ((n & 128) == 128) {
                    n3 = n2 | 128;
                }
                smsBookmark.forward_ = this.forward_;
                n2 = n3;
                if ((n & 256) == 256) {
                    n2 = n3 | 256;
                }
                smsBookmark.status_ = this.status_;
                smsBookmark.bitField0_ = n2;
                return smsBookmark;
            }

            public Builder clear() {
                super.clear();
                this.guid_ = "";
                this.bitField0_ &= -2;
                this.luid_ = "";
                this.bitField0_ &= -3;
                this.fromName_ = "";
                this.bitField0_ &= -5;
                this.address_ = "";
                this.bitField0_ &= -9;
                this.subject_ = "";
                this.bitField0_ &= -17;
                this.body_ = "";
                this.bitField0_ &= -33;
                this.markDate_ = 0;
                this.bitField0_ &= -65;
                this.forward_ = 0;
                this.bitField0_ &= -129;
                this.status_ = 0;
                this.bitField0_ &= -257;
                return this;
            }

            public Builder clone() {
                return Builder.create().mergeFrom(this.buildPartial());
            }

            public SmsBookmark getDefaultInstanceForType() {
                return SmsBookmark.getDefaultInstance();
            }

            public final boolean isInitialized() {
                return true;
            }

            /*
             * Enabled aggressive block sorting
             * Lifted jumps to return sites
             */
            public Builder mergeFrom(SmsBookmark smsBookmark) {
                if (smsBookmark == SmsBookmark.getDefaultInstance()) {
                    return this;
                }
                if (smsBookmark.hasGuid()) {
                    this.setGuid(smsBookmark.getGuid());
                }
                if (smsBookmark.hasLuid()) {
                    this.setLuid(smsBookmark.getLuid());
                }
                if (smsBookmark.hasFromName()) {
                    this.setFromName(smsBookmark.getFromName());
                }
                if (smsBookmark.hasAddress()) {
                    this.setAddress(smsBookmark.getAddress());
                }
                if (smsBookmark.hasSubject()) {
                    this.setSubject(smsBookmark.getSubject());
                }
                if (smsBookmark.hasBody()) {
                    this.setBody(smsBookmark.getBody());
                }
                if (smsBookmark.hasMarkDate()) {
                    this.setMarkDate(smsBookmark.getMarkDate());
                }
                if (smsBookmark.hasForward()) {
                    this.setForward(smsBookmark.getForward());
                }
                if (!smsBookmark.hasStatus()) return this;
                this.setStatus(smsBookmark.getStatus());
                return this;
            }

            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                block12 : do {
                    int n = codedInputStream.readTag();
                    switch (n) {
                        default: {
                            if (this.parseUnknownField(codedInputStream, extensionRegistryLite, n)) continue block12;
                        }
                        case 0: {
                            return this;
                        }
                        case 10: {
                            this.bitField0_ |= 1;
                            this.guid_ = codedInputStream.readBytes();
                            continue block12;
                        }
                        case 18: {
                            this.bitField0_ |= 2;
                            this.luid_ = codedInputStream.readBytes();
                            continue block12;
                        }
                        case 26: {
                            this.bitField0_ |= 4;
                            this.fromName_ = codedInputStream.readBytes();
                            continue block12;
                        }
                        case 34: {
                            this.bitField0_ |= 8;
                            this.address_ = codedInputStream.readBytes();
                            continue block12;
                        }
                        case 42: {
                            this.bitField0_ |= 16;
                            this.subject_ = codedInputStream.readBytes();
                            continue block12;
                        }
                        case 50: {
                            this.bitField0_ |= 32;
                            this.body_ = codedInputStream.readBytes();
                            continue block12;
                        }
                        case 56: {
                            this.bitField0_ |= 64;
                            this.markDate_ = codedInputStream.readInt64();
                            continue block12;
                        }
                        case 64: {
                            this.bitField0_ |= 128;
                            this.forward_ = codedInputStream.readInt32();
                            continue block12;
                        }
                        case 72: 
                    }
                    this.bitField0_ |= 256;
                    this.status_ = codedInputStream.readInt32();
                } while (true);
            }

            public Builder setAddress(String string) {
                if (string == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 8;
                this.address_ = string;
                return this;
            }

            public Builder setBody(String string) {
                if (string == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 32;
                this.body_ = string;
                return this;
            }

            public Builder setForward(int n) {
                this.bitField0_ |= 128;
                this.forward_ = n;
                return this;
            }

            public Builder setFromName(String string) {
                if (string == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 4;
                this.fromName_ = string;
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

            public Builder setLuid(String string) {
                if (string == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 2;
                this.luid_ = string;
                return this;
            }

            public Builder setMarkDate(long l) {
                this.bitField0_ |= 64;
                this.markDate_ = l;
                return this;
            }

            public Builder setStatus(int n) {
                this.bitField0_ |= 256;
                this.status_ = n;
                return this;
            }

            public Builder setSubject(String string) {
                if (string == null) {
                    throw new NullPointerException();
                }
                this.bitField0_ |= 16;
                this.subject_ = string;
                return this;
            }
        }

    }

    public static interface SmsBookmarkOrBuilder
    extends MessageLiteOrBuilder {
    }

    public static interface SmsOrBuilder
    extends MessageLiteOrBuilder {
    }

}

