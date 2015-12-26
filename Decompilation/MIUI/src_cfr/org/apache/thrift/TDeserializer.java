/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 */
package org.apache.thrift;

import org.apache.thrift.TBase;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.transport.TMemoryInputTransport;
import org.apache.thrift.transport.TTransport;

public class TDeserializer {
    private final TProtocol protocol_;
    private final TMemoryInputTransport trans_ = new TMemoryInputTransport();

    public TDeserializer() {
        this(new TBinaryProtocol.Factory());
    }

    public TDeserializer(TProtocolFactory tProtocolFactory) {
        this.protocol_ = tProtocolFactory.getProtocol(this.trans_);
    }

    public void deserialize(TBase tBase, byte[] arrby) throws TException {
        try {
            this.trans_.reset(arrby);
            tBase.read(this.protocol_);
            return;
        }
        finally {
            this.protocol_.reset();
        }
    }
}

