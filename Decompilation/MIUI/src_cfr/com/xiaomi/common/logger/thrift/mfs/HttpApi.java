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

import com.xiaomi.common.logger.thrift.mfs.HostInfo;
import com.xiaomi.common.logger.thrift.mfs.Location;
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

public class HttpApi
implements TBase<HttpApi, _Fields>,
Serializable,
Cloneable {
    private static final TField APP_NAME_FIELD_DESC;
    private static final TField APP_VERSION_FIELD_DESC;
    private static final TField CATEGORY_FIELD_DESC;
    private static final TField CLIENT_IP_FIELD_DESC;
    private static final TField HOST_INFO_FIELD_DESC;
    private static final TField LOCATION_FIELD_DESC;
    private static final TField NETWORK_FIELD_DESC;
    private static final TStruct STRUCT_DESC;
    private static final TField UUID_FIELD_DESC;
    private static final TField VERSION_FIELD_DESC;
    private static final TField VERSION_TYPE_FIELD_DESC;
    public static final Map<_Fields, FieldMetaData> metaDataMap;
    private String app_name = "";
    private String app_version = "";
    private String category = "";
    private String client_ip;
    private Set<HostInfo> host_info;
    private Location location;
    private String network;
    private String uuid;
    private String version;
    private String version_type = "";

    static {
        STRUCT_DESC = new TStruct("HttpApi");
        CATEGORY_FIELD_DESC = new TField("category", 11, 1);
        UUID_FIELD_DESC = new TField("uuid", 11, 2);
        VERSION_FIELD_DESC = new TField("version", 11, 3);
        NETWORK_FIELD_DESC = new TField("network", 11, 4);
        CLIENT_IP_FIELD_DESC = new TField("client_ip", 11, 5);
        LOCATION_FIELD_DESC = new TField("location", 12, 6);
        HOST_INFO_FIELD_DESC = new TField("host_info", 14, 7);
        VERSION_TYPE_FIELD_DESC = new TField("version_type", 11, 8);
        APP_NAME_FIELD_DESC = new TField("app_name", 11, 9);
        APP_VERSION_FIELD_DESC = new TField("app_version", 11, 10);
        EnumMap enumMap = new EnumMap((Class)_Fields.class);
        enumMap.put(_Fields.CATEGORY, new FieldMetaData("category", 1, new FieldValueMetaData(11)));
        enumMap.put(_Fields.UUID, new FieldMetaData("uuid", 1, new FieldValueMetaData(11)));
        enumMap.put(_Fields.VERSION, new FieldMetaData("version", 1, new FieldValueMetaData(11)));
        enumMap.put(_Fields.NETWORK, new FieldMetaData("network", 1, new FieldValueMetaData(11)));
        enumMap.put(_Fields.CLIENT_IP, new FieldMetaData("client_ip", 2, new FieldValueMetaData(11)));
        enumMap.put(_Fields.LOCATION, new FieldMetaData("location", 2, new StructMetaData(12, Location.class)));
        enumMap.put(_Fields.HOST_INFO, new FieldMetaData("host_info", 2, new SetMetaData(14, new StructMetaData(12, HostInfo.class))));
        enumMap.put(_Fields.VERSION_TYPE, new FieldMetaData("version_type", 2, new FieldValueMetaData(11)));
        enumMap.put(_Fields.APP_NAME, new FieldMetaData("app_name", 2, new FieldValueMetaData(11)));
        enumMap.put(_Fields.APP_VERSION, new FieldMetaData("app_version", 2, new FieldValueMetaData(11)));
        metaDataMap = Collections.unmodifiableMap((Map)enumMap);
        FieldMetaData.addStructMetaDataMap(HttpApi.class, metaDataMap);
    }

    public void addToHost_info(HostInfo hostInfo) {
        if (this.host_info == null) {
            this.host_info = new HashSet();
        }
        this.host_info.add(hostInfo);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public int compareTo(HttpApi httpApi) {
        int n;
        if (!this.getClass().equals((Object)httpApi.getClass())) {
            return this.getClass().getName().compareTo(httpApi.getClass().getName());
        }
        int n2 = n = Boolean.valueOf((boolean)this.isSetCategory()).compareTo(Boolean.valueOf((boolean)httpApi.isSetCategory()));
        if (n != 0) return n2;
        if (this.isSetCategory()) {
            n2 = n = TBaseHelper.compareTo(this.category, httpApi.category);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetUuid()).compareTo(Boolean.valueOf((boolean)httpApi.isSetUuid()));
        if (n != 0) return n2;
        if (this.isSetUuid()) {
            n2 = n = TBaseHelper.compareTo(this.uuid, httpApi.uuid);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetVersion()).compareTo(Boolean.valueOf((boolean)httpApi.isSetVersion()));
        if (n != 0) return n2;
        if (this.isSetVersion()) {
            n2 = n = TBaseHelper.compareTo(this.version, httpApi.version);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetNetwork()).compareTo(Boolean.valueOf((boolean)httpApi.isSetNetwork()));
        if (n != 0) return n2;
        if (this.isSetNetwork()) {
            n2 = n = TBaseHelper.compareTo(this.network, httpApi.network);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetClient_ip()).compareTo(Boolean.valueOf((boolean)httpApi.isSetClient_ip()));
        if (n != 0) return n2;
        if (this.isSetClient_ip()) {
            n2 = n = TBaseHelper.compareTo(this.client_ip, httpApi.client_ip);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetLocation()).compareTo(Boolean.valueOf((boolean)httpApi.isSetLocation()));
        if (n != 0) return n2;
        if (this.isSetLocation()) {
            n2 = n = TBaseHelper.compareTo(this.location, httpApi.location);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetHost_info()).compareTo(Boolean.valueOf((boolean)httpApi.isSetHost_info()));
        if (n != 0) return n2;
        if (this.isSetHost_info()) {
            n2 = n = TBaseHelper.compareTo(this.host_info, httpApi.host_info);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetVersion_type()).compareTo(Boolean.valueOf((boolean)httpApi.isSetVersion_type()));
        if (n != 0) return n2;
        if (this.isSetVersion_type()) {
            n2 = n = TBaseHelper.compareTo(this.version_type, httpApi.version_type);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetApp_name()).compareTo(Boolean.valueOf((boolean)httpApi.isSetApp_name()));
        if (n != 0) return n2;
        if (this.isSetApp_name()) {
            n2 = n = TBaseHelper.compareTo(this.app_name, httpApi.app_name);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetApp_version()).compareTo(Boolean.valueOf((boolean)httpApi.isSetApp_version()));
        if (n != 0) return n2;
        if (!this.isSetApp_version()) return 0;
        n2 = n = TBaseHelper.compareTo(this.app_version, httpApi.app_version);
        if (n != 0) return n2;
        return 0;
    }

    public boolean equals(HttpApi httpApi) {
        if (httpApi == null) {
            return false;
        }
        boolean bl = this.isSetCategory();
        boolean bl2 = httpApi.isSetCategory();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (!this.category.equals((Object)httpApi.category)) {
                return false;
            }
        }
        bl = this.isSetUuid();
        bl2 = httpApi.isSetUuid();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (!this.uuid.equals((Object)httpApi.uuid)) {
                return false;
            }
        }
        bl = this.isSetVersion();
        bl2 = httpApi.isSetVersion();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (!this.version.equals((Object)httpApi.version)) {
                return false;
            }
        }
        bl = this.isSetNetwork();
        bl2 = httpApi.isSetNetwork();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (!this.network.equals((Object)httpApi.network)) {
                return false;
            }
        }
        bl = this.isSetClient_ip();
        bl2 = httpApi.isSetClient_ip();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (!this.client_ip.equals((Object)httpApi.client_ip)) {
                return false;
            }
        }
        bl = this.isSetLocation();
        bl2 = httpApi.isSetLocation();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (!this.location.equals(httpApi.location)) {
                return false;
            }
        }
        bl = this.isSetHost_info();
        bl2 = httpApi.isSetHost_info();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (!this.host_info.equals(httpApi.host_info)) {
                return false;
            }
        }
        bl = this.isSetVersion_type();
        bl2 = httpApi.isSetVersion_type();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (!this.version_type.equals((Object)httpApi.version_type)) {
                return false;
            }
        }
        bl = this.isSetApp_name();
        bl2 = httpApi.isSetApp_name();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (!this.app_name.equals((Object)httpApi.app_name)) {
                return false;
            }
        }
        bl = this.isSetApp_version();
        bl2 = httpApi.isSetApp_version();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (!this.app_version.equals((Object)httpApi.app_version)) {
                return false;
            }
        }
        return true;
    }

    /*
     * Enabled aggressive block sorting
     */
    public boolean equals(Object object) {
        if (object == null || !(object instanceof HttpApi)) {
            return false;
        }
        return this.equals((HttpApi)object);
    }

    public int getHost_infoSize() {
        if (this.host_info == null) {
            return 0;
        }
        return this.host_info.size();
    }

    public int hashCode() {
        return 0;
    }

    public boolean isSetApp_name() {
        if (this.app_name != null) {
            return true;
        }
        return false;
    }

    public boolean isSetApp_version() {
        if (this.app_version != null) {
            return true;
        }
        return false;
    }

    public boolean isSetCategory() {
        if (this.category != null) {
            return true;
        }
        return false;
    }

    public boolean isSetClient_ip() {
        if (this.client_ip != null) {
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

    public boolean isSetVersion_type() {
        if (this.version_type != null) {
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
                        this.client_ip = tProtocol.readString();
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
                            HostInfo hostInfo = new HostInfo();
                            hostInfo.read(tProtocol);
                            this.host_info.add(hostInfo);
                        }
                        tProtocol.readSetEnd();
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, object.type);
                    break;
                }
                case 8: {
                    if (object.type == 11) {
                        this.version_type = tProtocol.readString();
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, object.type);
                    break;
                }
                case 9: {
                    if (object.type == 11) {
                        this.app_name = tProtocol.readString();
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, object.type);
                    break;
                }
                case 10: {
                    if (object.type == 11) {
                        this.app_version = tProtocol.readString();
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, object.type);
                }
            }
            tProtocol.readFieldEnd();
        } while (true);
    }

    public HttpApi setApp_name(String string2) {
        this.app_name = string2;
        return this;
    }

    public HttpApi setApp_version(String string2) {
        this.app_version = string2;
        return this;
    }

    public HttpApi setCategory(String string2) {
        this.category = string2;
        return this;
    }

    public HttpApi setClient_ip(String string2) {
        this.client_ip = string2;
        return this;
    }

    public HttpApi setLocation(Location location) {
        this.location = location;
        return this;
    }

    public HttpApi setNetwork(String string2) {
        this.network = string2;
        return this;
    }

    public HttpApi setUuid(String string2) {
        this.uuid = string2;
        return this;
    }

    public HttpApi setVersion(String string2) {
        this.version = string2;
        return this;
    }

    public HttpApi setVersion_type(String string2) {
        this.version_type = string2;
        return this;
    }

    /*
     * Enabled aggressive block sorting
     */
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("HttpApi(");
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
        if (this.isSetClient_ip()) {
            if (!false) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("client_ip:");
            if (this.client_ip == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.client_ip);
            }
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
        if (this.isSetVersion_type()) {
            if (!false) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("version_type:");
            if (this.version_type == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.version_type);
            }
        }
        if (this.isSetApp_name()) {
            if (!false) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("app_name:");
            if (this.app_name == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.app_name);
            }
        }
        if (this.isSetApp_version()) {
            if (!false) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("app_version:");
            if (this.app_version == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.app_version);
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
        if (this.client_ip != null && this.isSetClient_ip()) {
            tProtocol.writeFieldBegin(CLIENT_IP_FIELD_DESC);
            tProtocol.writeString(this.client_ip);
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
            Iterator<HostInfo> iterator = this.host_info.iterator();
            while (iterator.hasNext()) {
                iterator.next().write(tProtocol);
            }
            tProtocol.writeSetEnd();
            tProtocol.writeFieldEnd();
        }
        if (this.version_type != null && this.isSetVersion_type()) {
            tProtocol.writeFieldBegin(VERSION_TYPE_FIELD_DESC);
            tProtocol.writeString(this.version_type);
            tProtocol.writeFieldEnd();
        }
        if (this.app_name != null && this.isSetApp_name()) {
            tProtocol.writeFieldBegin(APP_NAME_FIELD_DESC);
            tProtocol.writeString(this.app_name);
            tProtocol.writeFieldEnd();
        }
        if (this.app_version != null && this.isSetApp_version()) {
            tProtocol.writeFieldBegin(APP_VERSION_FIELD_DESC);
            tProtocol.writeString(this.app_version);
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
        CLIENT_IP(5, "client_ip"),
        LOCATION(6, "location"),
        HOST_INFO(7, "host_info"),
        VERSION_TYPE(8, "version_type"),
        APP_NAME(9, "app_name"),
        APP_VERSION(10, "app_version");
        
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

