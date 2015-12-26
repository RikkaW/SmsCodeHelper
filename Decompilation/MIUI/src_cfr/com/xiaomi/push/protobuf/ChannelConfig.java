/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 *  java.util.Collections
 */
package com.xiaomi.push.protobuf;

import com.google.protobuf.micro.CodedInputStreamMicro;
import com.google.protobuf.micro.CodedOutputStreamMicro;
import com.google.protobuf.micro.InvalidProtocolBufferMicroException;
import com.google.protobuf.micro.MessageMicro;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public final class ChannelConfig {
    private ChannelConfig() {
    }

    public static final class PushServiceConfig
    extends MessageMicro {
        private int cachedSize = -1;
        private int configVersion_ = 0;
        private int connectTimeoutMs_ = 0;
        private boolean enableDynamicPing_ = false;
        private boolean hasConfigVersion;
        private boolean hasConnectTimeoutMs;
        private boolean hasEnableDynamicPing;
        private boolean hasUseBucketV2;
        private List<String> testHosts_ = Collections.emptyList();
        private boolean useBucketV2_ = false;

        public static PushServiceConfig parseFrom(CodedInputStreamMicro codedInputStreamMicro) throws IOException {
            return new PushServiceConfig().mergeFrom(codedInputStreamMicro);
        }

        public static PushServiceConfig parseFrom(byte[] arrby) throws InvalidProtocolBufferMicroException {
            return (PushServiceConfig)new PushServiceConfig().mergeFrom(arrby);
        }

        public PushServiceConfig addTestHosts(String string2) {
            if (string2 == null) {
                throw new NullPointerException();
            }
            if (this.testHosts_.isEmpty()) {
                this.testHosts_ = new ArrayList();
            }
            this.testHosts_.add(string2);
            return this;
        }

        public int getConfigVersion() {
            return this.configVersion_;
        }

        public int getConnectTimeoutMs() {
            return this.connectTimeoutMs_;
        }

        public boolean getEnableDynamicPing() {
            return this.enableDynamicPing_;
        }

        @Override
        public int getSerializedSize() {
            int n = 0;
            if (this.hasConfigVersion()) {
                n = 0 + CodedOutputStreamMicro.computeUInt32Size(1, this.getConfigVersion());
            }
            int n2 = n;
            if (this.hasUseBucketV2()) {
                n2 = n + CodedOutputStreamMicro.computeBoolSize(2, this.getUseBucketV2());
            }
            n = n2;
            if (this.hasConnectTimeoutMs()) {
                n = n2 + CodedOutputStreamMicro.computeInt32Size(3, this.getConnectTimeoutMs());
            }
            n2 = n;
            if (this.hasEnableDynamicPing()) {
                n2 = n + CodedOutputStreamMicro.computeBoolSize(4, this.getEnableDynamicPing());
            }
            n = 0;
            Iterator<String> iterator = this.getTestHostsList().iterator();
            while (iterator.hasNext()) {
                n += CodedOutputStreamMicro.computeStringSizeNoTag(iterator.next());
            }
            this.cachedSize = n2 = n2 + n + this.getTestHostsList().size() * 1;
            return n2;
        }

        public int getTestHostsCount() {
            return this.testHosts_.size();
        }

        public List<String> getTestHostsList() {
            return this.testHosts_;
        }

        public boolean getUseBucketV2() {
            return this.useBucketV2_;
        }

        public boolean hasConfigVersion() {
            return this.hasConfigVersion;
        }

        public boolean hasConnectTimeoutMs() {
            return this.hasConnectTimeoutMs;
        }

        public boolean hasEnableDynamicPing() {
            return this.hasEnableDynamicPing;
        }

        public boolean hasUseBucketV2() {
            return this.hasUseBucketV2;
        }

        @Override
        public PushServiceConfig mergeFrom(CodedInputStreamMicro codedInputStreamMicro) throws IOException {
            block8 : do {
                int n = codedInputStreamMicro.readTag();
                switch (n) {
                    default: {
                        if (this.parseUnknownField(codedInputStreamMicro, n)) continue block8;
                    }
                    case 0: {
                        return this;
                    }
                    case 8: {
                        this.setConfigVersion(codedInputStreamMicro.readUInt32());
                        continue block8;
                    }
                    case 16: {
                        this.setUseBucketV2(codedInputStreamMicro.readBool());
                        continue block8;
                    }
                    case 24: {
                        this.setConnectTimeoutMs(codedInputStreamMicro.readInt32());
                        continue block8;
                    }
                    case 32: {
                        this.setEnableDynamicPing(codedInputStreamMicro.readBool());
                        continue block8;
                    }
                    case 42: 
                }
                this.addTestHosts(codedInputStreamMicro.readString());
            } while (true);
        }

        public PushServiceConfig setConfigVersion(int n) {
            this.hasConfigVersion = true;
            this.configVersion_ = n;
            return this;
        }

        public PushServiceConfig setConnectTimeoutMs(int n) {
            this.hasConnectTimeoutMs = true;
            this.connectTimeoutMs_ = n;
            return this;
        }

        public PushServiceConfig setEnableDynamicPing(boolean bl) {
            this.hasEnableDynamicPing = true;
            this.enableDynamicPing_ = bl;
            return this;
        }

        public PushServiceConfig setUseBucketV2(boolean bl) {
            this.hasUseBucketV2 = true;
            this.useBucketV2_ = bl;
            return this;
        }

        @Override
        public void writeTo(CodedOutputStreamMicro codedOutputStreamMicro) throws IOException {
            if (this.hasConfigVersion()) {
                codedOutputStreamMicro.writeUInt32(1, this.getConfigVersion());
            }
            if (this.hasUseBucketV2()) {
                codedOutputStreamMicro.writeBool(2, this.getUseBucketV2());
            }
            if (this.hasConnectTimeoutMs()) {
                codedOutputStreamMicro.writeInt32(3, this.getConnectTimeoutMs());
            }
            if (this.hasEnableDynamicPing()) {
                codedOutputStreamMicro.writeBool(4, this.getEnableDynamicPing());
            }
            Iterator<String> iterator = this.getTestHostsList().iterator();
            while (iterator.hasNext()) {
                codedOutputStreamMicro.writeString(5, iterator.next());
            }
        }
    }

}

