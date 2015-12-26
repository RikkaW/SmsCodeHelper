/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Boolean
 *  java.lang.Class
 *  java.lang.Enum
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 *  java.util.Collections
 *  java.util.EnumMap
 *  java.util.EnumSet
 *  java.util.HashMap
 */
package com.xiaomi.common.logger.thrift.mfs;

import com.xiaomi.common.logger.thrift.mfs.LandNodeInfo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.thrift.TBase;
import org.apache.thrift.TBaseHelper;
import org.apache.thrift.TException;
import org.apache.thrift.meta_data.FieldMetaData;
import org.apache.thrift.meta_data.FieldValueMetaData;
import org.apache.thrift.meta_data.ListMetaData;
import org.apache.thrift.meta_data.StructMetaData;
import org.apache.thrift.protocol.TField;
import org.apache.thrift.protocol.TList;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.protocol.TProtocolUtil;
import org.apache.thrift.protocol.TStruct;

public class HostInfo
implements TBase<HostInfo, _Fields>,
Serializable,
Cloneable {
    private static final TField HOST_FIELD_DESC;
    private static final TField LAND_NODE_INFO_FIELD_DESC;
    private static final TStruct STRUCT_DESC;
    public static final Map<_Fields, FieldMetaData> metaDataMap;
    private String host;
    private List<LandNodeInfo> land_node_info;

    static {
        STRUCT_DESC = new TStruct("HostInfo");
        HOST_FIELD_DESC = new TField("host", 11, 1);
        LAND_NODE_INFO_FIELD_DESC = new TField("land_node_info", 15, 2);
        EnumMap enumMap = new EnumMap((Class)_Fields.class);
        enumMap.put(_Fields.HOST, new FieldMetaData("host", 1, new FieldValueMetaData(11)));
        enumMap.put(_Fields.LAND_NODE_INFO, new FieldMetaData("land_node_info", 1, new ListMetaData(15, new StructMetaData(12, LandNodeInfo.class))));
        metaDataMap = Collections.unmodifiableMap((Map)enumMap);
        FieldMetaData.addStructMetaDataMap(HostInfo.class, metaDataMap);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public int compareTo(HostInfo hostInfo) {
        int n;
        if (!this.getClass().equals((Object)hostInfo.getClass())) {
            return this.getClass().getName().compareTo(hostInfo.getClass().getName());
        }
        int n2 = n = Boolean.valueOf((boolean)this.isSetHost()).compareTo(Boolean.valueOf((boolean)hostInfo.isSetHost()));
        if (n != 0) return n2;
        if (this.isSetHost()) {
            n2 = n = TBaseHelper.compareTo(this.host, hostInfo.host);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetLand_node_info()).compareTo(Boolean.valueOf((boolean)hostInfo.isSetLand_node_info()));
        if (n != 0) return n2;
        if (!this.isSetLand_node_info()) return 0;
        n2 = n = TBaseHelper.compareTo(this.land_node_info, hostInfo.land_node_info);
        if (n != 0) return n2;
        return 0;
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public boolean equals(HostInfo hostInfo) {
        if (hostInfo == null) {
            return false;
        }
        boolean bl = this.isSetHost();
        boolean bl2 = hostInfo.isSetHost();
        if (bl || bl2) {
            if (!bl) return false;
            if (!bl2) return false;
            if (!this.host.equals((Object)hostInfo.host)) return false;
        }
        bl = this.isSetLand_node_info();
        bl2 = hostInfo.isSetLand_node_info();
        if (!bl) {
            if (!bl2) return true;
        }
        if (!bl) return false;
        if (!bl2) return false;
        if (!this.land_node_info.equals(hostInfo.land_node_info)) return false;
        return true;
    }

    /*
     * Enabled aggressive block sorting
     */
    public boolean equals(Object object) {
        if (object == null || !(object instanceof HostInfo)) {
            return false;
        }
        return this.equals((HostInfo)object);
    }

    public int hashCode() {
        return 0;
    }

    public boolean isSetHost() {
        if (this.host != null) {
            return true;
        }
        return false;
    }

    public boolean isSetLand_node_info() {
        if (this.land_node_info != null) {
            return true;
        }
        return false;
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
                this.validate();
                return;
            }
            switch (object.id) {
                default: {
                    TProtocolUtil.skip(tProtocol, object.type);
                    break;
                }
                case 1: {
                    if (object.type == 11) {
                        this.host = tProtocol.readString();
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, object.type);
                    break;
                }
                case 2: {
                    if (object.type == 15) {
                        object = tProtocol.readListBegin();
                        this.land_node_info = new ArrayList(object.size);
                        for (int i = 0; i < object.size; ++i) {
                            LandNodeInfo landNodeInfo = new LandNodeInfo();
                            landNodeInfo.read(tProtocol);
                            this.land_node_info.add(landNodeInfo);
                        }
                        tProtocol.readListEnd();
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, object.type);
                }
            }
            tProtocol.readFieldEnd();
        } while (true);
    }

    public HostInfo setHost(String string2) {
        this.host = string2;
        return this;
    }

    public HostInfo setLand_node_info(List<LandNodeInfo> list) {
        this.land_node_info = list;
        return this;
    }

    /*
     * Enabled aggressive block sorting
     */
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("HostInfo(");
        stringBuilder.append("host:");
        if (this.host == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.host);
        }
        if (!false) {
            stringBuilder.append(", ");
        }
        stringBuilder.append("land_node_info:");
        if (this.land_node_info == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.land_node_info);
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public void validate() throws TException {
        if (this.host == null) {
            throw new TProtocolException("Required field 'host' was not present! Struct: " + this.toString());
        }
        if (this.land_node_info == null) {
            throw new TProtocolException("Required field 'land_node_info' was not present! Struct: " + this.toString());
        }
    }

    @Override
    public void write(TProtocol tProtocol) throws TException {
        this.validate();
        tProtocol.writeStructBegin(STRUCT_DESC);
        if (this.host != null) {
            tProtocol.writeFieldBegin(HOST_FIELD_DESC);
            tProtocol.writeString(this.host);
            tProtocol.writeFieldEnd();
        }
        if (this.land_node_info != null) {
            tProtocol.writeFieldBegin(LAND_NODE_INFO_FIELD_DESC);
            tProtocol.writeListBegin(new TList(12, this.land_node_info.size()));
            Iterator<LandNodeInfo> iterator = this.land_node_info.iterator();
            while (iterator.hasNext()) {
                iterator.next().write(tProtocol);
            }
            tProtocol.writeListEnd();
            tProtocol.writeFieldEnd();
        }
        tProtocol.writeFieldStop();
        tProtocol.writeStructEnd();
    }

    public static enum _Fields {
        HOST(1, "host"),
        LAND_NODE_INFO(2, "land_node_info");
        
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

