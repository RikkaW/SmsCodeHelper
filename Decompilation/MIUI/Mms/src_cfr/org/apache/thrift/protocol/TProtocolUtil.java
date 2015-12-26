/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 *  java.nio.ByteBuffer
 */
package org.apache.thrift.protocol;

import java.nio.ByteBuffer;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TField;
import org.apache.thrift.protocol.TList;
import org.apache.thrift.protocol.TMap;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TSet;
import org.apache.thrift.protocol.TStruct;

public class TProtocolUtil {
    private static int maxSkipDepth = Integer.MAX_VALUE;

    public static void skip(TProtocol tProtocol, byte by) throws TException {
        TProtocolUtil.skip(tProtocol, by, maxSkipDepth);
    }

    public static void skip(TProtocol tProtocol, byte by, int n) throws TException {
        if (n <= 0) {
            throw new TException("Maximum skip depth exceeded");
        }
        switch (by) {
            default: {
                return;
            }
            case 2: {
                tProtocol.readBool();
                return;
            }
            case 3: {
                tProtocol.readByte();
                return;
            }
            case 6: {
                tProtocol.readI16();
                return;
            }
            case 8: {
                tProtocol.readI32();
                return;
            }
            case 10: {
                tProtocol.readI64();
                return;
            }
            case 4: {
                tProtocol.readDouble();
                return;
            }
            case 11: {
                tProtocol.readBinary();
                return;
            }
            case 12: {
                tProtocol.readStructBegin();
                do {
                    TField tField = tProtocol.readFieldBegin();
                    if (tField.type == 0) {
                        tProtocol.readStructEnd();
                        return;
                    }
                    TProtocolUtil.skip(tProtocol, tField.type, n - 1);
                    tProtocol.readFieldEnd();
                } while (true);
            }
            case 13: {
                TMap tMap = tProtocol.readMapBegin();
                for (by = 0; by < tMap.size; by = (byte)(by + 1)) {
                    TProtocolUtil.skip(tProtocol, tMap.keyType, n - 1);
                    TProtocolUtil.skip(tProtocol, tMap.valueType, n - 1);
                }
                tProtocol.readMapEnd();
                return;
            }
            case 14: {
                TSet tSet = tProtocol.readSetBegin();
                for (by = 0; by < tSet.size; by = (byte)(by + 1)) {
                    TProtocolUtil.skip(tProtocol, tSet.elemType, n - 1);
                }
                tProtocol.readSetEnd();
                return;
            }
            case 15: 
        }
        TList tList = tProtocol.readListBegin();
        for (by = 0; by < tList.size; by = (byte)(by + 1)) {
            TProtocolUtil.skip(tProtocol, tList.elemType, n - 1);
        }
        tProtocol.readListEnd();
    }
}

