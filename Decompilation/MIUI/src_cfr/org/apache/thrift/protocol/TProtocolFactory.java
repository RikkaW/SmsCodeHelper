/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 */
package org.apache.thrift.protocol;

import java.io.Serializable;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TTransport;

public interface TProtocolFactory
extends Serializable {
    public TProtocol getProtocol(TTransport var1);
}

