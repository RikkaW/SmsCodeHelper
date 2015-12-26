/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 */
package com.xiaomi.xmpush.thrift;

import com.xiaomi.channel.commonutils.logger.MyLog;
import org.apache.thrift.TBase;
import org.apache.thrift.TDeserializer;
import org.apache.thrift.TException;
import org.apache.thrift.TSerializer;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.protocol.XmPushTBinaryProtocol;

public class XmPushThriftSerializeUtils {
    public static <T extends TBase<T, ?>> void convertByteArrayToThriftObject(T t, byte[] arrby) throws TException {
        if (arrby == null) {
            throw new TException("the message byte is empty.");
        }
        new TDeserializer(new XmPushTBinaryProtocol.Factory(true, true, arrby.length)).deserialize((TBase)t, arrby);
    }

    public static <T extends TBase<T, ?>> byte[] convertThriftObjectToBytes(T object) {
        if (object == null) {
            return null;
        }
        try {
            object = new TSerializer(new TBinaryProtocol.Factory()).serialize((TBase)object);
            return object;
        }
        catch (TException var0_1) {
            MyLog.e("convertThriftObjectToBytes catch TException.", var0_1);
            return null;
        }
    }
}

