/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Boolean
 *  java.lang.Class
 *  java.lang.Enum
 *  java.lang.Object
 *  java.lang.String
 *  java.util.Collections
 *  java.util.EnumMap
 *  java.util.EnumSet
 *  java.util.HashMap
 */
package com.xiaomi.common.logger.thrift.mfs;

import java.io.Serializable;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import org.apache.thrift.TBase;
import org.apache.thrift.TBaseHelper;
import org.apache.thrift.TException;
import org.apache.thrift.meta_data.FieldMetaData;
import org.apache.thrift.meta_data.FieldValueMetaData;
import org.apache.thrift.protocol.TField;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolUtil;
import org.apache.thrift.protocol.TStruct;

public class Location
implements TBase<Location, _Fields>,
Serializable,
Cloneable {
    private static final TField CITY_FIELD_DESC;
    private static final TField CONTRY_FIELD_DESC;
    private static final TField ISP_FIELD_DESC;
    private static final TField PROVINCE_FIELD_DESC;
    private static final TStruct STRUCT_DESC;
    public static final Map<_Fields, FieldMetaData> metaDataMap;
    private String city;
    private String contry;
    private String isp;
    private String province;

    static {
        STRUCT_DESC = new TStruct("Location");
        CONTRY_FIELD_DESC = new TField("contry", 11, 1);
        PROVINCE_FIELD_DESC = new TField("province", 11, 2);
        CITY_FIELD_DESC = new TField("city", 11, 3);
        ISP_FIELD_DESC = new TField("isp", 11, 4);
        EnumMap enumMap = new EnumMap((Class)_Fields.class);
        enumMap.put(_Fields.CONTRY, new FieldMetaData("contry", 2, new FieldValueMetaData(11)));
        enumMap.put(_Fields.PROVINCE, new FieldMetaData("province", 2, new FieldValueMetaData(11)));
        enumMap.put(_Fields.CITY, new FieldMetaData("city", 2, new FieldValueMetaData(11)));
        enumMap.put(_Fields.ISP, new FieldMetaData("isp", 2, new FieldValueMetaData(11)));
        metaDataMap = Collections.unmodifiableMap((Map)enumMap);
        FieldMetaData.addStructMetaDataMap(Location.class, metaDataMap);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public int compareTo(Location location) {
        int n;
        if (!this.getClass().equals((Object)location.getClass())) {
            return this.getClass().getName().compareTo(location.getClass().getName());
        }
        int n2 = n = Boolean.valueOf((boolean)this.isSetContry()).compareTo(Boolean.valueOf((boolean)location.isSetContry()));
        if (n != 0) return n2;
        if (this.isSetContry()) {
            n2 = n = TBaseHelper.compareTo(this.contry, location.contry);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetProvince()).compareTo(Boolean.valueOf((boolean)location.isSetProvince()));
        if (n != 0) return n2;
        if (this.isSetProvince()) {
            n2 = n = TBaseHelper.compareTo(this.province, location.province);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetCity()).compareTo(Boolean.valueOf((boolean)location.isSetCity()));
        if (n != 0) return n2;
        if (this.isSetCity()) {
            n2 = n = TBaseHelper.compareTo(this.city, location.city);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetIsp()).compareTo(Boolean.valueOf((boolean)location.isSetIsp()));
        if (n != 0) return n2;
        if (!this.isSetIsp()) return 0;
        n2 = n = TBaseHelper.compareTo(this.isp, location.isp);
        if (n != 0) return n2;
        return 0;
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public boolean equals(Location location) {
        if (location == null) {
            return false;
        }
        boolean bl = this.isSetContry();
        boolean bl2 = location.isSetContry();
        if (bl || bl2) {
            if (!bl) return false;
            if (!bl2) return false;
            if (!this.contry.equals((Object)location.contry)) return false;
        }
        bl = this.isSetProvince();
        bl2 = location.isSetProvince();
        if (bl || bl2) {
            if (!bl) return false;
            if (!bl2) return false;
            if (!this.province.equals((Object)location.province)) return false;
        }
        bl = this.isSetCity();
        bl2 = location.isSetCity();
        if (bl || bl2) {
            if (!bl) return false;
            if (!bl2) return false;
            if (!this.city.equals((Object)location.city)) return false;
        }
        bl = this.isSetIsp();
        bl2 = location.isSetIsp();
        if (!bl) {
            if (!bl2) return true;
        }
        if (!bl) return false;
        if (!bl2) return false;
        if (!this.isp.equals((Object)location.isp)) return false;
        return true;
    }

    /*
     * Enabled aggressive block sorting
     */
    public boolean equals(Object object) {
        if (object == null || !(object instanceof Location)) {
            return false;
        }
        return this.equals((Location)object);
    }

    public int hashCode() {
        return 0;
    }

    public boolean isSetCity() {
        if (this.city != null) {
            return true;
        }
        return false;
    }

    public boolean isSetContry() {
        if (this.contry != null) {
            return true;
        }
        return false;
    }

    public boolean isSetIsp() {
        if (this.isp != null) {
            return true;
        }
        return false;
    }

    public boolean isSetProvince() {
        if (this.province != null) {
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
            TField tField = tProtocol.readFieldBegin();
            if (tField.type == 0) {
                tProtocol.readStructEnd();
                this.validate();
                return;
            }
            switch (tField.id) {
                default: {
                    TProtocolUtil.skip(tProtocol, tField.type);
                    break;
                }
                case 1: {
                    if (tField.type == 11) {
                        this.contry = tProtocol.readString();
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, tField.type);
                    break;
                }
                case 2: {
                    if (tField.type == 11) {
                        this.province = tProtocol.readString();
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, tField.type);
                    break;
                }
                case 3: {
                    if (tField.type == 11) {
                        this.city = tProtocol.readString();
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, tField.type);
                    break;
                }
                case 4: {
                    if (tField.type == 11) {
                        this.isp = tProtocol.readString();
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, tField.type);
                }
            }
            tProtocol.readFieldEnd();
        } while (true);
    }

    public Location setCity(String string2) {
        this.city = string2;
        return this;
    }

    public Location setContry(String string2) {
        this.contry = string2;
        return this;
    }

    public Location setIsp(String string2) {
        this.isp = string2;
        return this;
    }

    public Location setProvince(String string2) {
        this.province = string2;
        return this;
    }

    /*
     * Enabled aggressive block sorting
     */
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("Location(");
        boolean bl = true;
        if (this.isSetContry()) {
            stringBuilder.append("contry:");
            if (this.contry == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.contry);
            }
            bl = false;
        }
        boolean bl2 = bl;
        if (this.isSetProvince()) {
            if (!bl) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("province:");
            if (this.province == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.province);
            }
            bl2 = false;
        }
        bl = bl2;
        if (this.isSetCity()) {
            if (!bl2) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("city:");
            if (this.city == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.city);
            }
            bl = false;
        }
        if (this.isSetIsp()) {
            if (!bl) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("isp:");
            if (this.isp == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.isp);
            }
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public void validate() throws TException {
    }

    @Override
    public void write(TProtocol tProtocol) throws TException {
        this.validate();
        tProtocol.writeStructBegin(STRUCT_DESC);
        if (this.contry != null && this.isSetContry()) {
            tProtocol.writeFieldBegin(CONTRY_FIELD_DESC);
            tProtocol.writeString(this.contry);
            tProtocol.writeFieldEnd();
        }
        if (this.province != null && this.isSetProvince()) {
            tProtocol.writeFieldBegin(PROVINCE_FIELD_DESC);
            tProtocol.writeString(this.province);
            tProtocol.writeFieldEnd();
        }
        if (this.city != null && this.isSetCity()) {
            tProtocol.writeFieldBegin(CITY_FIELD_DESC);
            tProtocol.writeString(this.city);
            tProtocol.writeFieldEnd();
        }
        if (this.isp != null && this.isSetIsp()) {
            tProtocol.writeFieldBegin(ISP_FIELD_DESC);
            tProtocol.writeString(this.isp);
            tProtocol.writeFieldEnd();
        }
        tProtocol.writeFieldStop();
        tProtocol.writeStructEnd();
    }

    public static enum _Fields {
        CONTRY(1, "contry"),
        PROVINCE(2, "province"),
        CITY(3, "city"),
        ISP(4, "isp");
        
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

