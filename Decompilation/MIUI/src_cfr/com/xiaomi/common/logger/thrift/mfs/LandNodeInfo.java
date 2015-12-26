/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Boolean
 *  java.lang.Class
 *  java.lang.Enum
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.util.BitSet
 *  java.util.Collections
 *  java.util.EnumMap
 *  java.util.EnumSet
 *  java.util.HashMap
 *  java.util.Map$Entry
 */
package com.xiaomi.common.logger.thrift.mfs;

import java.io.Serializable;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.apache.thrift.TBase;
import org.apache.thrift.TBaseHelper;
import org.apache.thrift.TException;
import org.apache.thrift.meta_data.FieldMetaData;
import org.apache.thrift.meta_data.FieldValueMetaData;
import org.apache.thrift.meta_data.MapMetaData;
import org.apache.thrift.protocol.TField;
import org.apache.thrift.protocol.TMap;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.protocol.TProtocolUtil;
import org.apache.thrift.protocol.TStruct;

public class LandNodeInfo
implements TBase<LandNodeInfo, _Fields>,
Serializable,
Cloneable {
    private static final TField DURATION_FIELD_DESC;
    private static final TField EXP_INFO_FIELD_DESC;
    private static final TField FAILED_COUNT_FIELD_DESC;
    private static final TField HTTP_INFO_FIELD_DESC;
    private static final TField IP_FIELD_DESC;
    private static final TField SIZE_FIELD_DESC;
    private static final TStruct STRUCT_DESC;
    private static final TField SUCCESS_COUNT_FIELD_DESC;
    public static final Map<_Fields, FieldMetaData> metaDataMap;
    private BitSet __isset_bit_vector = new BitSet(4);
    private long duration;
    private Map<String, Integer> exp_info;
    private int failed_count;
    private Map<Integer, Integer> http_info;
    private String ip;
    private int size;
    private int success_count;

    static {
        STRUCT_DESC = new TStruct("LandNodeInfo");
        IP_FIELD_DESC = new TField("ip", 11, 1);
        FAILED_COUNT_FIELD_DESC = new TField("failed_count", 8, 2);
        SUCCESS_COUNT_FIELD_DESC = new TField("success_count", 8, 3);
        DURATION_FIELD_DESC = new TField("duration", 10, 4);
        SIZE_FIELD_DESC = new TField("size", 8, 5);
        EXP_INFO_FIELD_DESC = new TField("exp_info", 13, 6);
        HTTP_INFO_FIELD_DESC = new TField("http_info", 13, 7);
        EnumMap enumMap = new EnumMap((Class)_Fields.class);
        enumMap.put(_Fields.IP, new FieldMetaData("ip", 1, new FieldValueMetaData(11)));
        enumMap.put(_Fields.FAILED_COUNT, new FieldMetaData("failed_count", 1, new FieldValueMetaData(8)));
        enumMap.put(_Fields.SUCCESS_COUNT, new FieldMetaData("success_count", 1, new FieldValueMetaData(8)));
        enumMap.put(_Fields.DURATION, new FieldMetaData("duration", 1, new FieldValueMetaData(10)));
        enumMap.put(_Fields.SIZE, new FieldMetaData("size", 1, new FieldValueMetaData(8)));
        enumMap.put(_Fields.EXP_INFO, new FieldMetaData("exp_info", 2, new MapMetaData(13, new FieldValueMetaData(11), new FieldValueMetaData(8))));
        enumMap.put(_Fields.HTTP_INFO, new FieldMetaData("http_info", 2, new MapMetaData(13, new FieldValueMetaData(8), new FieldValueMetaData(8))));
        metaDataMap = Collections.unmodifiableMap((Map)enumMap);
        FieldMetaData.addStructMetaDataMap(LandNodeInfo.class, metaDataMap);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public int compareTo(LandNodeInfo landNodeInfo) {
        int n;
        if (!this.getClass().equals((Object)landNodeInfo.getClass())) {
            return this.getClass().getName().compareTo(landNodeInfo.getClass().getName());
        }
        int n2 = n = Boolean.valueOf((boolean)this.isSetIp()).compareTo(Boolean.valueOf((boolean)landNodeInfo.isSetIp()));
        if (n != 0) return n2;
        if (this.isSetIp()) {
            n2 = n = TBaseHelper.compareTo(this.ip, landNodeInfo.ip);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetFailed_count()).compareTo(Boolean.valueOf((boolean)landNodeInfo.isSetFailed_count()));
        if (n != 0) return n2;
        if (this.isSetFailed_count()) {
            n2 = n = TBaseHelper.compareTo(this.failed_count, landNodeInfo.failed_count);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetSuccess_count()).compareTo(Boolean.valueOf((boolean)landNodeInfo.isSetSuccess_count()));
        if (n != 0) return n2;
        if (this.isSetSuccess_count()) {
            n2 = n = TBaseHelper.compareTo(this.success_count, landNodeInfo.success_count);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetDuration()).compareTo(Boolean.valueOf((boolean)landNodeInfo.isSetDuration()));
        if (n != 0) return n2;
        if (this.isSetDuration()) {
            n2 = n = TBaseHelper.compareTo(this.duration, landNodeInfo.duration);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetSize()).compareTo(Boolean.valueOf((boolean)landNodeInfo.isSetSize()));
        if (n != 0) return n2;
        if (this.isSetSize()) {
            n2 = n = TBaseHelper.compareTo(this.size, landNodeInfo.size);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetExp_info()).compareTo(Boolean.valueOf((boolean)landNodeInfo.isSetExp_info()));
        if (n != 0) return n2;
        if (this.isSetExp_info()) {
            n2 = n = TBaseHelper.compareTo(this.exp_info, landNodeInfo.exp_info);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetHttp_info()).compareTo(Boolean.valueOf((boolean)landNodeInfo.isSetHttp_info()));
        if (n != 0) return n2;
        if (!this.isSetHttp_info()) return 0;
        n2 = n = TBaseHelper.compareTo(this.http_info, landNodeInfo.http_info);
        if (n != 0) return n2;
        return 0;
    }

    public boolean equals(LandNodeInfo landNodeInfo) {
        if (landNodeInfo == null) {
            return false;
        }
        boolean bl = this.isSetIp();
        boolean bl2 = landNodeInfo.isSetIp();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (!this.ip.equals((Object)landNodeInfo.ip)) {
                return false;
            }
        }
        if (true || true) {
            if (!true || !true) {
                return false;
            }
            if (this.failed_count != landNodeInfo.failed_count) {
                return false;
            }
        }
        if (true || true) {
            if (!true || !true) {
                return false;
            }
            if (this.success_count != landNodeInfo.success_count) {
                return false;
            }
        }
        if (true || true) {
            if (!true || !true) {
                return false;
            }
            if (this.duration != landNodeInfo.duration) {
                return false;
            }
        }
        if (true || true) {
            if (!true || !true) {
                return false;
            }
            if (this.size != landNodeInfo.size) {
                return false;
            }
        }
        bl = this.isSetExp_info();
        bl2 = landNodeInfo.isSetExp_info();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (!this.exp_info.equals(landNodeInfo.exp_info)) {
                return false;
            }
        }
        bl = this.isSetHttp_info();
        bl2 = landNodeInfo.isSetHttp_info();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (!this.http_info.equals(landNodeInfo.http_info)) {
                return false;
            }
        }
        return true;
    }

    /*
     * Enabled aggressive block sorting
     */
    public boolean equals(Object object) {
        if (object == null || !(object instanceof LandNodeInfo)) {
            return false;
        }
        return this.equals((LandNodeInfo)object);
    }

    public int hashCode() {
        return 0;
    }

    public boolean isSetDuration() {
        return this.__isset_bit_vector.get(2);
    }

    public boolean isSetExp_info() {
        if (this.exp_info != null) {
            return true;
        }
        return false;
    }

    public boolean isSetFailed_count() {
        return this.__isset_bit_vector.get(0);
    }

    public boolean isSetHttp_info() {
        if (this.http_info != null) {
            return true;
        }
        return false;
    }

    public boolean isSetIp() {
        if (this.ip != null) {
            return true;
        }
        return false;
    }

    public boolean isSetSize() {
        return this.__isset_bit_vector.get(3);
    }

    public boolean isSetSuccess_count() {
        return this.__isset_bit_vector.get(1);
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void read(TProtocol tProtocol) throws TException {
        tProtocol.readStructBegin();
        do {
            Object object = tProtocol.readFieldBegin();
            if (object.type == 0) {
                tProtocol.readStructEnd();
                if (this.isSetFailed_count()) break;
                throw new TProtocolException("Required field 'failed_count' was not found in serialized data! Struct: " + this.toString());
            }
            switch (object.id) {
                int n;
                int n2;
                default: {
                    TProtocolUtil.skip(tProtocol, object.type);
                    break;
                }
                case 1: {
                    if (object.type == 11) {
                        this.ip = tProtocol.readString();
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, object.type);
                    break;
                }
                case 2: {
                    if (object.type == 8) {
                        this.failed_count = tProtocol.readI32();
                        this.setFailed_countIsSet(true);
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, object.type);
                    break;
                }
                case 3: {
                    if (object.type == 8) {
                        this.success_count = tProtocol.readI32();
                        this.setSuccess_countIsSet(true);
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, object.type);
                    break;
                }
                case 4: {
                    if (object.type == 10) {
                        this.duration = tProtocol.readI64();
                        this.setDurationIsSet(true);
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, object.type);
                    break;
                }
                case 5: {
                    if (object.type == 8) {
                        this.size = tProtocol.readI32();
                        this.setSizeIsSet(true);
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, object.type);
                    break;
                }
                case 6: {
                    if (object.type == 13) {
                        object = tProtocol.readMapBegin();
                        this.exp_info = new HashMap(object.size * 2);
                        for (n = 0; n < object.size; ++n) {
                            String string2 = tProtocol.readString();
                            n2 = tProtocol.readI32();
                            this.exp_info.put(string2, n2);
                        }
                        tProtocol.readMapEnd();
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, object.type);
                    break;
                }
                case 7: {
                    if (object.type == 13) {
                        object = tProtocol.readMapBegin();
                        this.http_info = new HashMap(object.size * 2);
                        for (n = 0; n < object.size; ++n) {
                            n2 = tProtocol.readI32();
                            int n3 = tProtocol.readI32();
                            this.http_info.put(n2, n3);
                        }
                        tProtocol.readMapEnd();
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, object.type);
                }
            }
            tProtocol.readFieldEnd();
        } while (true);
        if (!this.isSetSuccess_count()) {
            throw new TProtocolException("Required field 'success_count' was not found in serialized data! Struct: " + this.toString());
        }
        if (!this.isSetDuration()) {
            throw new TProtocolException("Required field 'duration' was not found in serialized data! Struct: " + this.toString());
        }
        if (!this.isSetSize()) {
            throw new TProtocolException("Required field 'size' was not found in serialized data! Struct: " + this.toString());
        }
        this.validate();
    }

    public LandNodeInfo setDuration(long l) {
        this.duration = l;
        this.setDurationIsSet(true);
        return this;
    }

    public void setDurationIsSet(boolean bl) {
        this.__isset_bit_vector.set(2, bl);
    }

    public LandNodeInfo setExp_info(Map<String, Integer> map) {
        this.exp_info = map;
        return this;
    }

    public LandNodeInfo setFailed_count(int n) {
        this.failed_count = n;
        this.setFailed_countIsSet(true);
        return this;
    }

    public void setFailed_countIsSet(boolean bl) {
        this.__isset_bit_vector.set(0, bl);
    }

    public LandNodeInfo setIp(String string2) {
        this.ip = string2;
        return this;
    }

    public LandNodeInfo setSize(int n) {
        this.size = n;
        this.setSizeIsSet(true);
        return this;
    }

    public void setSizeIsSet(boolean bl) {
        this.__isset_bit_vector.set(3, bl);
    }

    public LandNodeInfo setSuccess_count(int n) {
        this.success_count = n;
        this.setSuccess_countIsSet(true);
        return this;
    }

    public void setSuccess_countIsSet(boolean bl) {
        this.__isset_bit_vector.set(1, bl);
    }

    /*
     * Enabled aggressive block sorting
     */
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("LandNodeInfo(");
        stringBuilder.append("ip:");
        if (this.ip == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.ip);
        }
        if (!false) {
            stringBuilder.append(", ");
        }
        stringBuilder.append("failed_count:");
        stringBuilder.append(this.failed_count);
        if (!false) {
            stringBuilder.append(", ");
        }
        stringBuilder.append("success_count:");
        stringBuilder.append(this.success_count);
        if (!false) {
            stringBuilder.append(", ");
        }
        stringBuilder.append("duration:");
        stringBuilder.append(this.duration);
        if (!false) {
            stringBuilder.append(", ");
        }
        stringBuilder.append("size:");
        stringBuilder.append(this.size);
        if (this.isSetExp_info()) {
            if (!false) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("exp_info:");
            if (this.exp_info == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.exp_info);
            }
        }
        if (this.isSetHttp_info()) {
            if (!false) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("http_info:");
            if (this.http_info == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.http_info);
            }
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public void validate() throws TException {
        if (this.ip == null) {
            throw new TProtocolException("Required field 'ip' was not present! Struct: " + this.toString());
        }
    }

    @Override
    public void write(TProtocol tProtocol) throws TException {
        this.validate();
        tProtocol.writeStructBegin(STRUCT_DESC);
        if (this.ip != null) {
            tProtocol.writeFieldBegin(IP_FIELD_DESC);
            tProtocol.writeString(this.ip);
            tProtocol.writeFieldEnd();
        }
        tProtocol.writeFieldBegin(FAILED_COUNT_FIELD_DESC);
        tProtocol.writeI32(this.failed_count);
        tProtocol.writeFieldEnd();
        tProtocol.writeFieldBegin(SUCCESS_COUNT_FIELD_DESC);
        tProtocol.writeI32(this.success_count);
        tProtocol.writeFieldEnd();
        tProtocol.writeFieldBegin(DURATION_FIELD_DESC);
        tProtocol.writeI64(this.duration);
        tProtocol.writeFieldEnd();
        tProtocol.writeFieldBegin(SIZE_FIELD_DESC);
        tProtocol.writeI32(this.size);
        tProtocol.writeFieldEnd();
        if (this.exp_info != null && this.isSetExp_info()) {
            tProtocol.writeFieldBegin(EXP_INFO_FIELD_DESC);
            tProtocol.writeMapBegin(new TMap(11, 8, this.exp_info.size()));
            for (Map.Entry<String, Integer> entry : this.exp_info.entrySet()) {
                tProtocol.writeString((String)entry.getKey());
                tProtocol.writeI32((Integer)entry.getValue());
            }
            tProtocol.writeMapEnd();
            tProtocol.writeFieldEnd();
        }
        if (this.http_info != null && this.isSetHttp_info()) {
            tProtocol.writeFieldBegin(HTTP_INFO_FIELD_DESC);
            tProtocol.writeMapBegin(new TMap(8, 8, this.http_info.size()));
            for (Map.Entry<String, Integer> entry : this.http_info.entrySet()) {
                tProtocol.writeI32((Integer)entry.getKey());
                tProtocol.writeI32((Integer)entry.getValue());
            }
            tProtocol.writeMapEnd();
            tProtocol.writeFieldEnd();
        }
        tProtocol.writeFieldStop();
        tProtocol.writeStructEnd();
    }

    public static enum _Fields {
        IP(1, "ip"),
        FAILED_COUNT(2, "failed_count"),
        SUCCESS_COUNT(3, "success_count"),
        DURATION(4, "duration"),
        SIZE(5, "size"),
        EXP_INFO(6, "exp_info"),
        HTTP_INFO(7, "http_info");
        
        private static final Map<String, _Fields> byName;
        private final String _fieldName;
        private final short _thriftId;

        static {
            byName = new HashMap();
            for (_Fields _Fields2 : EnumSet.allOf((Class)_Fields.class)) {
                byName.put(_Fields2.getFieldName(), _Fields2);
            }
        }

        private _Fields(short s, String string3) {
            this._thriftId = s;
            this._fieldName = string3;
        }

        public String getFieldName() {
            return this._fieldName;
        }
    }

}

