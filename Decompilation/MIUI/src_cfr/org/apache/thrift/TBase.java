/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 */
package org.apache.thrift;

import java.io.Serializable;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TProtocol;

public interface TBase<T extends TBase, F>
extends Comparable<T>,
Serializable {
    public void read(TProtocol var1) throws TException;

    public void write(TProtocol var1) throws TException;
}

