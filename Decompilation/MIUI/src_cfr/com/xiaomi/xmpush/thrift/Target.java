/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Boolean
 *  java.lang.Class
 *  java.lang.Enum
 *  java.lang.Object
 *  java.lang.String
 *  java.util.BitSet
 *  java.util.Collections
 *  java.util.EnumMap
 *  java.util.EnumSet
 *  java.util.HashMap
 */
package com.xiaomi.xmpush.thrift;

import java.io.Serializable;
import java.util.BitSet;
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
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.protocol.TProtocolUtil;
import org.apache.thrift.protocol.TStruct;

public class Target
implements TBase<Target, _Fields>,
Serializable,
Cloneable {
    private static final TField CHANNEL_ID_FIELD_DESC;
    private static final TField IS_PREVIEW_FIELD_DESC;
    private static final TField RESOURCE_FIELD_DESC;
    private static final TField SERVER_FIELD_DESC;
    private static final TStruct STRUCT_DESC;
    private static final TField USER_ID_FIELD_DESC;
    public static final Map<_Fields, FieldMetaData> metaDataMap;
    private BitSet __isset_bit_vector = new BitSet(2);
    public long channelId = 5;
    public boolean isPreview = false;
    public String resource = "";
    public String server = "xiaomi.com";
    public String userId;

    static {
        STRUCT_DESC = new TStruct("Target");
        CHANNEL_ID_FIELD_DESC = new TField("channelId", 10, 1);
        USER_ID_FIELD_DESC = new TField("userId", 11, 2);
        SERVER_FIELD_DESC = new TField("server", 11, 3);
        RESOURCE_FIELD_DESC = new TField("resource", 11, 4);
        IS_PREVIEW_FIELD_DESC = new TField("isPreview", 2, 5);
        EnumMap enumMap = new EnumMap((Class)_Fields.class);
        enumMap.put(_Fields.CHANNEL_ID, new FieldMetaData("channelId", 1, new FieldValueMetaData(10)));
        enumMap.put(_Fields.USER_ID, new FieldMetaData("userId", 1, new FieldValueMetaData(11)));
        enumMap.put(_Fields.SERVER, new FieldMetaData("server", 2, new FieldValueMetaData(11)));
        enumMap.put(_Fields.RESOURCE, new FieldMetaData("resource", 2, new FieldValueMetaData(11)));
        enumMap.put(_Fields.IS_PREVIEW, new FieldMetaData("isPreview", 2, new FieldValueMetaData(2)));
        metaDataMap = Collections.unmodifiableMap((Map)enumMap);
        FieldMetaData.addStructMetaDataMap(Target.class, metaDataMap);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public int compareTo(Target target) {
        int n;
        if (!this.getClass().equals((Object)target.getClass())) {
            return this.getClass().getName().compareTo(target.getClass().getName());
        }
        int n2 = n = Boolean.valueOf((boolean)this.isSetChannelId()).compareTo(Boolean.valueOf((boolean)target.isSetChannelId()));
        if (n != 0) return n2;
        if (this.isSetChannelId()) {
            n2 = n = TBaseHelper.compareTo(this.channelId, target.channelId);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetUserId()).compareTo(Boolean.valueOf((boolean)target.isSetUserId()));
        if (n != 0) return n2;
        if (this.isSetUserId()) {
            n2 = n = TBaseHelper.compareTo(this.userId, target.userId);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetServer()).compareTo(Boolean.valueOf((boolean)target.isSetServer()));
        if (n != 0) return n2;
        if (this.isSetServer()) {
            n2 = n = TBaseHelper.compareTo(this.server, target.server);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetResource()).compareTo(Boolean.valueOf((boolean)target.isSetResource()));
        if (n != 0) return n2;
        if (this.isSetResource()) {
            n2 = n = TBaseHelper.compareTo(this.resource, target.resource);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetIsPreview()).compareTo(Boolean.valueOf((boolean)target.isSetIsPreview()));
        if (n != 0) return n2;
        if (!this.isSetIsPreview()) return 0;
        n2 = n = TBaseHelper.compareTo(this.isPreview, target.isPreview);
        if (n != 0) return n2;
        return 0;
    }

    public boolean equals(Target target) {
        if (target == null) {
            return false;
        }
        if (true || true) {
            if (!true || !true) {
                return false;
            }
            if (this.channelId != target.channelId) {
                return false;
            }
        }
        boolean bl = this.isSetUserId();
        boolean bl2 = target.isSetUserId();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (!this.userId.equals((Object)target.userId)) {
                return false;
            }
        }
        bl = this.isSetServer();
        bl2 = target.isSetServer();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (!this.server.equals((Object)target.server)) {
                return false;
            }
        }
        bl = this.isSetResource();
        bl2 = target.isSetResource();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (!this.resource.equals((Object)target.resource)) {
                return false;
            }
        }
        bl = this.isSetIsPreview();
        bl2 = target.isSetIsPreview();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (this.isPreview != target.isPreview) {
                return false;
            }
        }
        return true;
    }

    /*
     * Enabled aggressive block sorting
     */
    public boolean equals(Object object) {
        if (object == null || !(object instanceof Target)) {
            return false;
        }
        return this.equals((Target)object);
    }

    public int hashCode() {
        return 0;
    }

    public boolean isSetChannelId() {
        return this.__isset_bit_vector.get(0);
    }

    public boolean isSetIsPreview() {
        return this.__isset_bit_vector.get(1);
    }

    public boolean isSetResource() {
        if (this.resource != null) {
            return true;
        }
        return false;
    }

    public boolean isSetServer() {
        if (this.server != null) {
            return true;
        }
        return false;
    }

    public boolean isSetUserId() {
        if (this.userId != null) {
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
                if (this.isSetChannelId()) break;
                throw new TProtocolException("Required field 'channelId' was not found in serialized data! Struct: " + this.toString());
            }
            switch (tField.id) {
                default: {
                    TProtocolUtil.skip(tProtocol, tField.type);
                    break;
                }
                case 1: {
                    if (tField.type == 10) {
                        this.channelId = tProtocol.readI64();
                        this.setChannelIdIsSet(true);
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, tField.type);
                    break;
                }
                case 2: {
                    if (tField.type == 11) {
                        this.userId = tProtocol.readString();
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, tField.type);
                    break;
                }
                case 3: {
                    if (tField.type == 11) {
                        this.server = tProtocol.readString();
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, tField.type);
                    break;
                }
                case 4: {
                    if (tField.type == 11) {
                        this.resource = tProtocol.readString();
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, tField.type);
                    break;
                }
                case 5: {
                    if (tField.type == 2) {
                        this.isPreview = tProtocol.readBool();
                        this.setIsPreviewIsSet(true);
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, tField.type);
                }
            }
            tProtocol.readFieldEnd();
        } while (true);
        this.validate();
    }

    public void setChannelIdIsSet(boolean bl) {
        this.__isset_bit_vector.set(0, bl);
    }

    public void setIsPreviewIsSet(boolean bl) {
        this.__isset_bit_vector.set(1, bl);
    }

    /*
     * Enabled aggressive block sorting
     */
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("Target(");
        stringBuilder.append("channelId:");
        stringBuilder.append(this.channelId);
        if (!false) {
            stringBuilder.append(", ");
        }
        stringBuilder.append("userId:");
        if (this.userId == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.userId);
        }
        if (this.isSetServer()) {
            if (!false) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("server:");
            if (this.server == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.server);
            }
        }
        if (this.isSetResource()) {
            if (!false) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("resource:");
            if (this.resource == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.resource);
            }
        }
        if (this.isSetIsPreview()) {
            if (!false) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("isPreview:");
            stringBuilder.append(this.isPreview);
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public void validate() throws TException {
        if (this.userId == null) {
            throw new TProtocolException("Required field 'userId' was not present! Struct: " + this.toString());
        }
    }

    @Override
    public void write(TProtocol tProtocol) throws TException {
        this.validate();
        tProtocol.writeStructBegin(STRUCT_DESC);
        tProtocol.writeFieldBegin(CHANNEL_ID_FIELD_DESC);
        tProtocol.writeI64(this.channelId);
        tProtocol.writeFieldEnd();
        if (this.userId != null) {
            tProtocol.writeFieldBegin(USER_ID_FIELD_DESC);
            tProtocol.writeString(this.userId);
            tProtocol.writeFieldEnd();
        }
        if (this.server != null && this.isSetServer()) {
            tProtocol.writeFieldBegin(SERVER_FIELD_DESC);
            tProtocol.writeString(this.server);
            tProtocol.writeFieldEnd();
        }
        if (this.resource != null && this.isSetResource()) {
            tProtocol.writeFieldBegin(RESOURCE_FIELD_DESC);
            tProtocol.writeString(this.resource);
            tProtocol.writeFieldEnd();
        }
        if (this.isSetIsPreview()) {
            tProtocol.writeFieldBegin(IS_PREVIEW_FIELD_DESC);
            tProtocol.writeBool(this.isPreview);
            tProtocol.writeFieldEnd();
        }
        tProtocol.writeFieldStop();
        tProtocol.writeStructEnd();
    }

    public static enum _Fields {
        CHANNEL_ID(1, "channelId"),
        USER_ID(2, "userId"),
        SERVER(3, "server"),
        RESOURCE(4, "resource"),
        IS_PREVIEW(5, "isPreview");
        
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

