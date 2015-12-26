/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.io.ByteArrayOutputStream
 *  java.io.OutputStream
 *  java.lang.Object
 */
package org.apache.thrift;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import org.apache.thrift.TBase;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.transport.TIOStreamTransport;
import org.apache.thrift.transport.TTransport;

public class TSerializer {
    private final ByteArrayOutputStream baos_ = new ByteArrayOutputStream();
    private TProtocol protocol_;
    private final TIOStreamTransport transport_ = new TIOStreamTransport((OutputStream)this.baos_);

    public TSerializer() {
        this(new TBinaryProtocol.Factory());
    }

    public TSerializer(TProtocolFactory tProtocolFactory) {
        this.protocol_ = tProtocolFactory.getProtocol(this.transport_);
    }

    public byte[] serialize(TBase tBase) throws TException {
        this.baos_.reset();
        tBase.write(this.protocol_);
        return this.baos_.toByteArray();
    }
}

