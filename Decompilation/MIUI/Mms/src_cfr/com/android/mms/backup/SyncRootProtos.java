/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  com.google.protobuf.AbstractMessageLite
 *  com.google.protobuf.AbstractMessageLite$Builder
 *  com.google.protobuf.CodedInputStream
 *  com.google.protobuf.CodedOutputStream
 *  com.google.protobuf.ExtensionRegistryLite
 *  com.google.protobuf.GeneratedMessageLite
 *  com.google.protobuf.GeneratedMessageLite$Builder
 *  com.google.protobuf.InvalidProtocolBufferException
 *  com.google.protobuf.MessageLite
 *  com.google.protobuf.MessageLite$Builder
 *  com.google.protobuf.MessageLiteOrBuilder
 *  java.lang.Object
 */
package com.android.mms.backup;

import com.android.mms.backup.MmsProtos;
import com.android.mms.backup.SmsProtos;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectStreamException;

public final class SyncRootProtos {
    private SyncRootProtos() {
    }

    public static final class SyncRoot
    extends GeneratedMessageLite
    implements SyncRootOrBuilder {
        private static final SyncRoot defaultInstance = new SyncRoot(true);
        private static final long serialVersionUID = 0;
        private int bitField0_;
        private byte memoizedIsInitialized = -1;
        private int memoizedSerializedSize = -1;
        private MmsProtos.MmsCollection mmsCollection_;
        private SmsProtos.MmsSms mmsSms_;

        static {
            defaultInstance.initFields();
        }

        private SyncRoot(Builder builder) {
            super((GeneratedMessageLite.Builder)builder);
        }

        private SyncRoot(boolean bl) {
        }

        public static SyncRoot getDefaultInstance() {
            return defaultInstance;
        }

        private void initFields() {
            this.mmsSms_ = SmsProtos.MmsSms.getDefaultInstance();
            this.mmsCollection_ = MmsProtos.MmsCollection.getDefaultInstance();
        }

        public static Builder newBuilder() {
            return Builder.create();
        }

        public static Builder newBuilder(SyncRoot syncRoot) {
            return SyncRoot.newBuilder().mergeFrom(syncRoot);
        }

        public static SyncRoot parseFrom(InputStream inputStream) throws IOException {
            return ((Builder)SyncRoot.newBuilder().mergeFrom(inputStream)).buildParsed();
        }

        public SyncRoot getDefaultInstanceForType() {
            return defaultInstance;
        }

        public MmsProtos.MmsCollection getMmsCollection() {
            return this.mmsCollection_;
        }

        public SmsProtos.MmsSms getMmsSms() {
            return this.mmsSms_;
        }

        public int getSerializedSize() {
            int n = this.memoizedSerializedSize;
            if (n != -1) {
                return n;
            }
            n = 0;
            if ((this.bitField0_ & 1) == 1) {
                n = 0 + CodedOutputStream.computeMessageSize((int)4, (MessageLite)this.mmsSms_);
            }
            int n2 = n;
            if ((this.bitField0_ & 2) == 2) {
                n2 = n + CodedOutputStream.computeMessageSize((int)9, (MessageLite)this.mmsCollection_);
            }
            this.memoizedSerializedSize = n2;
            return n2;
        }

        public boolean hasMmsCollection() {
            if ((this.bitField0_ & 2) == 2) {
                return true;
            }
            return false;
        }

        public boolean hasMmsSms() {
            if ((this.bitField0_ & 1) == 1) {
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
            return SyncRoot.newBuilder();
        }

        public Builder toBuilder() {
            return SyncRoot.newBuilder(this);
        }

        protected Object writeReplace() throws ObjectStreamException {
            return super.writeReplace();
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            this.getSerializedSize();
            if ((this.bitField0_ & 1) == 1) {
                codedOutputStream.writeMessage(4, (MessageLite)this.mmsSms_);
            }
            if ((this.bitField0_ & 2) == 2) {
                codedOutputStream.writeMessage(9, (MessageLite)this.mmsCollection_);
            }
        }

        public static final class Builder
        extends GeneratedMessageLite.Builder<SyncRoot, Builder>
        implements SyncRootOrBuilder {
            private int bitField0_;
            private MmsProtos.MmsCollection mmsCollection_ = MmsProtos.MmsCollection.getDefaultInstance();
            private SmsProtos.MmsSms mmsSms_ = SmsProtos.MmsSms.getDefaultInstance();

            private Builder() {
                this.maybeForceBuilderInitialization();
            }

            private SyncRoot buildParsed() throws InvalidProtocolBufferException {
                SyncRoot syncRoot = this.buildPartial();
                if (!syncRoot.isInitialized()) {
                    throw Builder.newUninitializedMessageException((MessageLite)syncRoot).asInvalidProtocolBufferException();
                }
                return syncRoot;
            }

            private static Builder create() {
                return new Builder();
            }

            private void maybeForceBuilderInitialization() {
            }

            public SyncRoot build() {
                SyncRoot syncRoot = this.buildPartial();
                if (!syncRoot.isInitialized()) {
                    throw Builder.newUninitializedMessageException((MessageLite)syncRoot);
                }
                return syncRoot;
            }

            public SyncRoot buildPartial() {
                SyncRoot syncRoot = new SyncRoot(this);
                int n = this.bitField0_;
                int n2 = 0;
                if ((n & 1) == 1) {
                    n2 = false | true;
                }
                syncRoot.mmsSms_ = this.mmsSms_;
                int n3 = n2;
                if ((n & 2) == 2) {
                    n3 = n2 | 2;
                }
                syncRoot.mmsCollection_ = this.mmsCollection_;
                syncRoot.bitField0_ = n3;
                return syncRoot;
            }

            public Builder clear() {
                super.clear();
                this.mmsSms_ = SmsProtos.MmsSms.getDefaultInstance();
                this.bitField0_ &= -2;
                this.mmsCollection_ = MmsProtos.MmsCollection.getDefaultInstance();
                this.bitField0_ &= -3;
                return this;
            }

            public Builder clone() {
                return Builder.create().mergeFrom(this.buildPartial());
            }

            public SyncRoot getDefaultInstanceForType() {
                return SyncRoot.getDefaultInstance();
            }

            public MmsProtos.MmsCollection getMmsCollection() {
                return this.mmsCollection_;
            }

            public SmsProtos.MmsSms getMmsSms() {
                return this.mmsSms_;
            }

            public boolean hasMmsCollection() {
                if ((this.bitField0_ & 2) == 2) {
                    return true;
                }
                return false;
            }

            public boolean hasMmsSms() {
                if ((this.bitField0_ & 1) == 1) {
                    return true;
                }
                return false;
            }

            public final boolean isInitialized() {
                return true;
            }

            /*
             * Enabled aggressive block sorting
             * Lifted jumps to return sites
             */
            public Builder mergeFrom(SyncRoot syncRoot) {
                if (syncRoot == SyncRoot.getDefaultInstance()) {
                    return this;
                }
                if (syncRoot.hasMmsSms()) {
                    this.mergeMmsSms(syncRoot.getMmsSms());
                }
                if (!syncRoot.hasMmsCollection()) return this;
                this.mergeMmsCollection(syncRoot.getMmsCollection());
                return this;
            }

            public Builder mergeFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                block5 : do {
                    SmsProtos.MmsSms.Builder builder;
                    int n = codedInputStream.readTag();
                    switch (n) {
                        default: {
                            if (this.parseUnknownField(codedInputStream, extensionRegistryLite, n)) continue block5;
                        }
                        case 0: {
                            return this;
                        }
                        case 34: {
                            builder = SmsProtos.MmsSms.newBuilder();
                            if (this.hasMmsSms()) {
                                builder.mergeFrom(this.getMmsSms());
                            }
                            codedInputStream.readMessage((MessageLite.Builder)builder, extensionRegistryLite);
                            this.setMmsSms(builder.buildPartial());
                            continue block5;
                        }
                        case 74: 
                    }
                    builder = MmsProtos.MmsCollection.newBuilder();
                    if (this.hasMmsCollection()) {
                        builder.mergeFrom(this.getMmsCollection());
                    }
                    codedInputStream.readMessage((MessageLite.Builder)builder, extensionRegistryLite);
                    this.setMmsCollection(builder.buildPartial());
                } while (true);
            }

            /*
             * Enabled aggressive block sorting
             */
            public Builder mergeMmsCollection(MmsProtos.MmsCollection mmsCollection) {
                this.mmsCollection_ = (this.bitField0_ & 2) == 2 && this.mmsCollection_ != MmsProtos.MmsCollection.getDefaultInstance() ? MmsProtos.MmsCollection.newBuilder(this.mmsCollection_).mergeFrom(mmsCollection).buildPartial() : mmsCollection;
                this.bitField0_ |= 2;
                return this;
            }

            /*
             * Enabled aggressive block sorting
             */
            public Builder mergeMmsSms(SmsProtos.MmsSms mmsSms) {
                this.mmsSms_ = (this.bitField0_ & 1) == 1 && this.mmsSms_ != SmsProtos.MmsSms.getDefaultInstance() ? SmsProtos.MmsSms.newBuilder(this.mmsSms_).mergeFrom(mmsSms).buildPartial() : mmsSms;
                this.bitField0_ |= 1;
                return this;
            }

            public Builder setMmsCollection(MmsProtos.MmsCollection mmsCollection) {
                if (mmsCollection == null) {
                    throw new NullPointerException();
                }
                this.mmsCollection_ = mmsCollection;
                this.bitField0_ |= 2;
                return this;
            }

            public Builder setMmsSms(SmsProtos.MmsSms mmsSms) {
                if (mmsSms == null) {
                    throw new NullPointerException();
                }
                this.mmsSms_ = mmsSms;
                this.bitField0_ |= 1;
                return this;
            }
        }

    }

    public static interface SyncRootOrBuilder
    extends MessageLiteOrBuilder {
    }

}

