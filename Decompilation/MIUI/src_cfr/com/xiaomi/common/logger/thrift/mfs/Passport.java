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
 *  java.util.HashSet
 */
package com.xiaomi.common.logger.thrift.mfs;

import com.xiaomi.common.logger.thrift.mfs.Location;
import com.xiaomi.common.logger.thrift.mfs.PassportHostInfo;
import java.io.Serializable;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.apache.thrift.TBase;
import org.apache.thrift.TBaseHelper;
import org.apache.thrift.TException;
import org.apache.thrift.meta_data.FieldMetaData;
import org.apache.thrift.meta_data.FieldValueMetaData;
import org.apache.thrift.meta_data.SetMetaData;
import org.apache.thrift.meta_data.StructMetaData;
import org.apache.thrift.protocol.TField;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.protocol.TProtocolUtil;
import org.apache.thrift.protocol.TSet;
import org.apache.thrift.protocol.TStruct;

public class Passport
implements TBase<Passport, _Fields>,
Serializable,
Cloneable {
    private static final TField CATEGORY_FIELD_DESC;
    private static final TField HOST_INFO_FIELD_DESC;
    private static final TField LOCATION_FIELD_DESC;
    private static final TField NETWORK_FIELD_DESC;
    private static final TField RID_FIELD_DESC;
    private static final TStruct STRUCT_DESC;
    private static final TField UUID_FIELD_DESC;
    private static final TField VERSION_FIELD_DESC;
    public static final Map<_Fields, FieldMetaData> metaDataMap;
    private String category = "";
    private Set<PassportHostInfo> host_info;
    private Location location;
    private String network;
    private String rid;
    private String uuid;
    private String version;

    static {
        STRUCT_DESC = new TStruct("Passport");
        CATEGORY_FIELD_DESC = new TField("category", 11, 1);
        UUID_FIELD_DESC = new TField("uuid", 11, 2);
        VERSION_FIELD_DESC = new TField("version", 11, 3);
        NETWORK_FIELD_DESC = new TField("network", 11, 4);
        RID_FIELD_DESC = new TField("rid", 11, 5);
        LOCATION_FIELD_DESC = new TField("location", 12, 6);
        HOST_INFO_FIELD_DESC = new TField("host_info", 14, 7);
        EnumMap enumMap = new EnumMap((Class)_Fields.class);
        enumMap.put(_Fields.CATEGORY, new FieldMetaData("category", 1, new FieldValueMetaData(11)));
        enumMap.put(_Fields.UUID, new FieldMetaData("uuid", 1, new FieldValueMetaData(11)));
        enumMap.put(_Fields.VERSION, new FieldMetaData("version", 1, new FieldValueMetaData(11)));
        enumMap.put(_Fields.NETWORK, new FieldMetaData("network", 1, new FieldValueMetaData(11)));
        enumMap.put(_Fields.RID, new FieldMetaData("rid", 1, new FieldValueMetaData(11)));
        enumMap.put(_Fields.LOCATION, new FieldMetaData("location", 2, new StructMetaData(12, Location.class)));
        enumMap.put(_Fields.HOST_INFO, new FieldMetaData("host_info", 2, new SetMetaData(14, new StructMetaData(12, PassportHostInfo.class))));
        metaDataMap = Collections.unmodifiableMap((Map)enumMap);
        FieldMetaData.addStructMetaDataMap(Passport.class, metaDataMap);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public int compareTo(Passport passport) {
        int n;
        if (!this.getClass().equals((Object)passport.getClass())) {
            return this.getClass().getName().compareTo(passport.getClass().getName());
        }
        int n2 = n = Boolean.valueOf((boolean)this.isSetCategory()).compareTo(Boolean.valueOf((boolean)passport.isSetCategory()));
        if (n != 0) return n2;
        if (this.isSetCategory()) {
            n2 = n = TBaseHelper.compareTo(this.category, passport.category);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetUuid()).compareTo(Boolean.valueOf((boolean)passport.isSetUuid()));
        if (n != 0) return n2;
        if (this.isSetUuid()) {
            n2 = n = TBaseHelper.compareTo(this.uuid, passport.uuid);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetVersion()).compareTo(Boolean.valueOf((boolean)passport.isSetVersion()));
        if (n != 0) return n2;
        if (this.isSetVersion()) {
            n2 = n = TBaseHelper.compareTo(this.version, passport.version);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetNetwork()).compareTo(Boolean.valueOf((boolean)passport.isSetNetwork()));
        if (n != 0) return n2;
        if (this.isSetNetwork()) {
            n2 = n = TBaseHelper.compareTo(this.network, passport.network);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetRid()).compareTo(Boolean.valueOf((boolean)passport.isSetRid()));
        if (n != 0) return n2;
        if (this.isSetRid()) {
            n2 = n = TBaseHelper.compareTo(this.rid, passport.rid);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetLocation()).compareTo(Boolean.valueOf((boolean)passport.isSetLocation()));
        if (n != 0) return n2;
        if (this.isSetLocation()) {
            n2 = n = TBaseHelper.compareTo(this.location, passport.location);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetHost_info()).compareTo(Boolean.valueOf((boolean)passport.isSetHost_info()));
        if (n != 0) return n2;
        if (!this.isSetHost_info()) return 0;
        n2 = n = TBaseHelper.compareTo(this.host_info, passport.host_info);
        if (n != 0) return n2;
        return 0;
    }

    public boolean equals(Passport passport) {
        if (passport == null) {
            return false;
        }
        boolean bl = this.isSetCategory();
        boolean bl2 = passport.isSetCategory();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (!this.category.equals((Object)passport.category)) {
                return false;
            }
        }
        bl = this.isSetUuid();
        bl2 = passport.isSetUuid();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (!this.uuid.equals((Object)passport.uuid)) {
                return false;
            }
        }
        bl = this.isSetVersion();
        bl2 = passport.isSetVersion();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (!this.version.equals((Object)passport.version)) {
                return false;
            }
        }
        bl = this.isSetNetwork();
        bl2 = passport.isSetNetwork();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (!this.network.equals((Object)passport.network)) {
                return false;
            }
        }
        bl = this.isSetRid();
        bl2 = passport.isSetRid();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (!this.rid.equals((Object)passport.rid)) {
                return false;
            }
        }
        bl = this.isSetLocation();
        bl2 = passport.isSetLocation();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (!this.location.equals(passport.location)) {
                return false;
            }
        }
        bl = this.isSetHost_info();
        bl2 = passport.isSetHost_info();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (!this.host_info.equals(passport.host_info)) {
                return false;
            }
        }
        return true;
    }

    /*
     * Enabled aggressive block sorting
     */
    public boolean equals(Object object) {
        if (object == null || !(object instanceof Passport)) {
            return false;
        }
        return this.equals((Passport)object);
    }

    public int hashCode() {
        return 0;
    }

    public boolean isSetCategory() {
        if (this.category != null) {
            return true;
        }
        return false;
    }

    public boolean isSetHost_info() {
        if (this.host_info != null) {
            return true;
        }
        return false;
    }

    public boolean isSetLocation() {
        if (this.location != null) {
            return true;
        }
        return false;
    }

    public boolean isSetNetwork() {
        if (this.network != null) {
            return true;
        }
        return false;
    }

    public boolean isSetRid() {
        if (this.rid != null) {
            return true;
        }
        return false;
    }

    public boolean isSetUuid() {
        if (this.uuid != null) {
            return true;
        }
        return false;
    }

    public boolean isSetVersion() {
        if (this.version != null) {
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
                        this.category = tProtocol.readString();
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, object.type);
                    break;
                }
                case 2: {
                    if (object.type == 11) {
                        this.uuid = tProtocol.readString();
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, object.type);
                    break;
                }
                case 3: {
                    if (object.type == 11) {
                        this.version = tProtocol.readString();
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, object.type);
                    break;
                }
                case 4: {
                    if (object.type == 11) {
                        this.network = tProtocol.readString();
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, object.type);
                    break;
                }
                case 5: {
                    if (object.type == 11) {
                        this.rid = tProtocol.readString();
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, object.type);
                    break;
                }
                case 6: {
                    if (object.type == 12) {
                        this.location = new Location();
                        this.location.read(tProtocol);
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, object.type);
                    break;
                }
                case 7: {
                    if (object.type == 14) {
                        object = tProtocol.readSetBegin();
                        this.host_info = new HashSet(object.size * 2);
                        for (int i = 0; i < object.size; ++i) {
                            PassportHostInfo passportHostInfo = new PassportHostInfo();
                            passportHostInfo.read(tProtocol);
                            this.host_info.add(passportHostInfo);
                        }
                        tProtocol.readSetEnd();
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, object.type);
                }
            }
            tProtocol.readFieldEnd();
        } while (true);
    }

    /*
     * Enabled aggressive block sorting
     */
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("Passport(");
        stringBuilder.append("category:");
        if (this.category == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.category);
        }
        if (!false) {
            stringBuilder.append(", ");
        }
        stringBuilder.append("uuid:");
        if (this.uuid == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.uuid);
        }
        if (!false) {
            stringBuilder.append(", ");
        }
        stringBuilder.append("version:");
        if (this.version == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.version);
        }
        if (!false) {
            stringBuilder.append(", ");
        }
        stringBuilder.append("network:");
        if (this.network == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.network);
        }
        if (!false) {
            stringBuilder.append(", ");
        }
        stringBuilder.append("rid:");
        if (this.rid == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.rid);
        }
        if (this.isSetLocation()) {
            if (!false) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("location:");
            if (this.location == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.location);
            }
        }
        if (this.isSetHost_info()) {
            if (!false) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("host_info:");
            if (this.host_info == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.host_info);
            }
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public void validate() throws TException {
        if (this.category == null) {
            throw new TProtocolException("Required field 'category' was not present! Struct: " + this.toString());
        }
        if (this.uuid == null) {
            throw new TProtocolException("Required field 'uuid' was not present! Struct: " + this.toString());
        }
        if (this.version == null) {
            throw new TProtocolException("Required field 'version' was not present! Struct: " + this.toString());
        }
        if (this.network == null) {
            throw new TProtocolException("Required field 'network' was not present! Struct: " + this.toString());
        }
        if (this.rid == null) {
            throw new TProtocolException("Required field 'rid' was not present! Struct: " + this.toString());
        }
    }

    @Override
    public void write(TProtocol tProtocol) throws TException {
        this.validate();
        tProtocol.writeStructBegin(STRUCT_DESC);
        if (this.category != null) {
            tProtocol.writeFieldBegin(CATEGORY_FIELD_DESC);
            tProtocol.writeString(this.category);
            tProtocol.writeFieldEnd();
        }
        if (this.uuid != null) {
            tProtocol.writeFieldBegin(UUID_FIELD_DESC);
            tProtocol.writeString(this.uuid);
            tProtocol.writeFieldEnd();
        }
        if (this.version != null) {
            tProtocol.writeFieldBegin(VERSION_FIELD_DESC);
            tProtocol.writeString(this.version);
            tProtocol.writeFieldEnd();
        }
        if (this.network != null) {
            tProtocol.writeFieldBegin(NETWORK_FIELD_DESC);
            tProtocol.writeString(this.network);
            tProtocol.writeFieldEnd();
        }
        if (this.rid != null) {
            tProtocol.writeFieldBegin(RID_FIELD_DESC);
            tProtocol.writeString(this.rid);
            tProtocol.writeFieldEnd();
        }
        if (this.location != null && this.isSetLocation()) {
            tProtocol.writeFieldBegin(LOCATION_FIELD_DESC);
            this.location.write(tProtocol);
            tProtocol.writeFieldEnd();
        }
        if (this.host_info != null && this.isSetHost_info()) {
            tProtocol.writeFieldBegin(HOST_INFO_FIELD_DESC);
            tProtocol.writeSetBegin(new TSet(12, this.host_info.size()));
            Iterator<PassportHostInfo> iterator = this.host_info.iterator();
            while (iterator.hasNext()) {
                iterator.next().write(tProtocol);
            }
            tProtocol.writeSetEnd();
            tProtocol.writeFieldEnd();
        }
        tProtocol.writeFieldStop();
        tProtocol.writeStructEnd();
    }

    public static enum _Fields {
        CATEGORY(1, "category"),
        UUID(2, "uuid"),
        VERSION(3, "version"),
        NETWORK(4, "network"),
        RID(5, "rid"),
        LOCATION(6, "location"),
        HOST_INFO(7, "host_info");
        
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

