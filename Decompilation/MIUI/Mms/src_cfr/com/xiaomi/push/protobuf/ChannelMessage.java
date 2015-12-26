/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 */
package com.xiaomi.push.protobuf;

import com.google.protobuf.micro.CodedInputStreamMicro;
import com.google.protobuf.micro.CodedOutputStreamMicro;
import com.google.protobuf.micro.InvalidProtocolBufferMicroException;
import com.google.protobuf.micro.MessageMicro;
import java.io.IOException;

public final class ChannelMessage {
    private ChannelMessage() {
    }

    public static final class PushServiceConfigMsg
    extends MessageMicro {
        private int cachedSize = -1;
        private int clientVersion_ = 0;
        private int cloudVersion_ = 0;
        private int dots_ = 0;
        private boolean fetchBucket_ = false;
        private boolean hasClientVersion;
        private boolean hasCloudVersion;
        private boolean hasDots;
        private boolean hasFetchBucket;

        public static PushServiceConfigMsg parseFrom(byte[] arrby) throws InvalidProtocolBufferMicroException {
            return (PushServiceConfigMsg)new PushServiceConfigMsg().mergeFrom(arrby);
        }

        public int getClientVersion() {
            return this.clientVersion_;
        }

        public int getCloudVersion() {
            return this.cloudVersion_;
        }

        public int getDots() {
            return this.dots_;
        }

        public boolean getFetchBucket() {
            return this.fetchBucket_;
        }

        @Override
        public int getSerializedSize() {
            int n = 0;
            if (this.hasFetchBucket()) {
                n = 0 + CodedOutputStreamMicro.computeBoolSize(1, this.getFetchBucket());
            }
            int n2 = n;
            if (this.hasClientVersion()) {
                n2 = n + CodedOutputStreamMicro.computeInt32Size(3, this.getClientVersion());
            }
            n = n2;
            if (this.hasCloudVersion()) {
                n = n2 + CodedOutputStreamMicro.computeInt32Size(4, this.getCloudVersion());
            }
            n2 = n;
            if (this.hasDots()) {
                n2 = n + CodedOutputStreamMicro.computeInt32Size(5, this.getDots());
            }
            this.cachedSize = n2;
            return n2;
        }

        public boolean hasClientVersion() {
            return this.hasClientVersion;
        }

        public boolean hasCloudVersion() {
            return this.hasCloudVersion;
        }

        public boolean hasDots() {
            return this.hasDots;
        }

        public boolean hasFetchBucket() {
            return this.hasFetchBucket;
        }

        @Override
        public PushServiceConfigMsg mergeFrom(CodedInputStreamMicro codedInputStreamMicro) throws IOException {
            block7 : do {
                int n = codedInputStreamMicro.readTag();
                switch (n) {
                    default: {
                        if (this.parseUnknownField(codedInputStreamMicro, n)) continue block7;
                    }
                    case 0: {
                        return this;
                    }
                    case 8: {
                        this.setFetchBucket(codedInputStreamMicro.readBool());
                        continue block7;
                    }
                    case 24: {
                        this.setClientVersion(codedInputStreamMicro.readInt32());
                        continue block7;
                    }
                    case 32: {
                        this.setCloudVersion(codedInputStreamMicro.readInt32());
                        continue block7;
                    }
                    case 40: 
                }
                this.setDots(codedInputStreamMicro.readInt32());
            } while (true);
        }

        public PushServiceConfigMsg setClientVersion(int n) {
            this.hasClientVersion = true;
            this.clientVersion_ = n;
            return this;
        }

        public PushServiceConfigMsg setCloudVersion(int n) {
            this.hasCloudVersion = true;
            this.cloudVersion_ = n;
            return this;
        }

        public PushServiceConfigMsg setDots(int n) {
            this.hasDots = true;
            this.dots_ = n;
            return this;
        }

        public PushServiceConfigMsg setFetchBucket(boolean bl) {
            this.hasFetchBucket = true;
            this.fetchBucket_ = bl;
            return this;
        }

        @Override
        public void writeTo(CodedOutputStreamMicro codedOutputStreamMicro) throws IOException {
            if (this.hasFetchBucket()) {
                codedOutputStreamMicro.writeBool(1, this.getFetchBucket());
            }
            if (this.hasClientVersion()) {
                codedOutputStreamMicro.writeInt32(3, this.getClientVersion());
            }
            if (this.hasCloudVersion()) {
                codedOutputStreamMicro.writeInt32(4, this.getCloudVersion());
            }
            if (this.hasDots()) {
                codedOutputStreamMicro.writeInt32(5, this.getDots());
            }
        }
    }

}

