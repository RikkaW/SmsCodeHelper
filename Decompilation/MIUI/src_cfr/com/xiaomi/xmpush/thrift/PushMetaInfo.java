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
 *  java.util.Map$Entry
 */
package com.xiaomi.xmpush.thrift;

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

public class PushMetaInfo
implements TBase<PushMetaInfo, _Fields>,
Serializable,
Cloneable {
    private static final TField DESCRIPTION_FIELD_DESC;
    private static final TField EXTRA_FIELD_DESC;
    private static final TField ID_FIELD_DESC;
    private static final TField IGNORE_REG_INFO_FIELD_DESC;
    private static final TField INTERNAL_FIELD_DESC;
    private static final TField MESSAGE_TS_FIELD_DESC;
    private static final TField NOTIFY_ID_FIELD_DESC;
    private static final TField NOTIFY_TYPE_FIELD_DESC;
    private static final TField PASS_THROUGH_FIELD_DESC;
    private static final TStruct STRUCT_DESC;
    private static final TField TITLE_FIELD_DESC;
    private static final TField TOPIC_FIELD_DESC;
    private static final TField URL_FIELD_DESC;
    public static final Map<_Fields, FieldMetaData> metaDataMap;
    private BitSet __isset_bit_vector = new BitSet(5);
    public String description;
    public Map<String, String> extra;
    public String id;
    public boolean ignoreRegInfo;
    public Map<String, String> internal;
    public long messageTs;
    public int notifyId;
    public int notifyType;
    public int passThrough;
    public String title;
    public String topic;
    public String url;

    static {
        STRUCT_DESC = new TStruct("PushMetaInfo");
        ID_FIELD_DESC = new TField("id", 11, 1);
        MESSAGE_TS_FIELD_DESC = new TField("messageTs", 10, 2);
        TOPIC_FIELD_DESC = new TField("topic", 11, 3);
        TITLE_FIELD_DESC = new TField("title", 11, 4);
        DESCRIPTION_FIELD_DESC = new TField("description", 11, 5);
        NOTIFY_TYPE_FIELD_DESC = new TField("notifyType", 8, 6);
        URL_FIELD_DESC = new TField("url", 11, 7);
        PASS_THROUGH_FIELD_DESC = new TField("passThrough", 8, 8);
        NOTIFY_ID_FIELD_DESC = new TField("notifyId", 8, 9);
        EXTRA_FIELD_DESC = new TField("extra", 13, 10);
        INTERNAL_FIELD_DESC = new TField("internal", 13, 11);
        IGNORE_REG_INFO_FIELD_DESC = new TField("ignoreRegInfo", 2, 12);
        EnumMap enumMap = new EnumMap((Class)_Fields.class);
        enumMap.put(_Fields.ID, new FieldMetaData("id", 1, new FieldValueMetaData(11)));
        enumMap.put(_Fields.MESSAGE_TS, new FieldMetaData("messageTs", 1, new FieldValueMetaData(10)));
        enumMap.put(_Fields.TOPIC, new FieldMetaData("topic", 2, new FieldValueMetaData(11)));
        enumMap.put(_Fields.TITLE, new FieldMetaData("title", 2, new FieldValueMetaData(11)));
        enumMap.put(_Fields.DESCRIPTION, new FieldMetaData("description", 2, new FieldValueMetaData(11)));
        enumMap.put(_Fields.NOTIFY_TYPE, new FieldMetaData("notifyType", 2, new FieldValueMetaData(8)));
        enumMap.put(_Fields.URL, new FieldMetaData("url", 2, new FieldValueMetaData(11)));
        enumMap.put(_Fields.PASS_THROUGH, new FieldMetaData("passThrough", 2, new FieldValueMetaData(8)));
        enumMap.put(_Fields.NOTIFY_ID, new FieldMetaData("notifyId", 2, new FieldValueMetaData(8)));
        enumMap.put(_Fields.EXTRA, new FieldMetaData("extra", 2, new MapMetaData(13, new FieldValueMetaData(11), new FieldValueMetaData(11))));
        enumMap.put(_Fields.INTERNAL, new FieldMetaData("internal", 2, new MapMetaData(13, new FieldValueMetaData(11), new FieldValueMetaData(11))));
        enumMap.put(_Fields.IGNORE_REG_INFO, new FieldMetaData("ignoreRegInfo", 2, new FieldValueMetaData(2)));
        metaDataMap = Collections.unmodifiableMap((Map)enumMap);
        FieldMetaData.addStructMetaDataMap(PushMetaInfo.class, metaDataMap);
    }

    public PushMetaInfo() {
        this.ignoreRegInfo = false;
    }

    public PushMetaInfo(PushMetaInfo pushMetaInfo) {
        HashMap hashMap;
        this.__isset_bit_vector.clear();
        this.__isset_bit_vector.or(pushMetaInfo.__isset_bit_vector);
        if (pushMetaInfo.isSetId()) {
            this.id = pushMetaInfo.id;
        }
        this.messageTs = pushMetaInfo.messageTs;
        if (pushMetaInfo.isSetTopic()) {
            this.topic = pushMetaInfo.topic;
        }
        if (pushMetaInfo.isSetTitle()) {
            this.title = pushMetaInfo.title;
        }
        if (pushMetaInfo.isSetDescription()) {
            this.description = pushMetaInfo.description;
        }
        this.notifyType = pushMetaInfo.notifyType;
        if (pushMetaInfo.isSetUrl()) {
            this.url = pushMetaInfo.url;
        }
        this.passThrough = pushMetaInfo.passThrough;
        this.notifyId = pushMetaInfo.notifyId;
        if (pushMetaInfo.isSetExtra()) {
            hashMap = new HashMap();
            for (Map.Entry<String, String> entry : pushMetaInfo.extra.entrySet()) {
                hashMap.put((String)entry.getKey(), (String)entry.getValue());
            }
            this.extra = hashMap;
        }
        if (pushMetaInfo.isSetInternal()) {
            hashMap = new HashMap();
            for (Map.Entry<String, String> entry : pushMetaInfo.internal.entrySet()) {
                hashMap.put((String)entry.getKey(), (String)entry.getValue());
            }
            this.internal = hashMap;
        }
        this.ignoreRegInfo = pushMetaInfo.ignoreRegInfo;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public int compareTo(PushMetaInfo pushMetaInfo) {
        int n;
        if (!this.getClass().equals((Object)pushMetaInfo.getClass())) {
            return this.getClass().getName().compareTo(pushMetaInfo.getClass().getName());
        }
        int n2 = n = Boolean.valueOf((boolean)this.isSetId()).compareTo(Boolean.valueOf((boolean)pushMetaInfo.isSetId()));
        if (n != 0) return n2;
        if (this.isSetId()) {
            n2 = n = TBaseHelper.compareTo(this.id, pushMetaInfo.id);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetMessageTs()).compareTo(Boolean.valueOf((boolean)pushMetaInfo.isSetMessageTs()));
        if (n != 0) return n2;
        if (this.isSetMessageTs()) {
            n2 = n = TBaseHelper.compareTo(this.messageTs, pushMetaInfo.messageTs);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetTopic()).compareTo(Boolean.valueOf((boolean)pushMetaInfo.isSetTopic()));
        if (n != 0) return n2;
        if (this.isSetTopic()) {
            n2 = n = TBaseHelper.compareTo(this.topic, pushMetaInfo.topic);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetTitle()).compareTo(Boolean.valueOf((boolean)pushMetaInfo.isSetTitle()));
        if (n != 0) return n2;
        if (this.isSetTitle()) {
            n2 = n = TBaseHelper.compareTo(this.title, pushMetaInfo.title);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetDescription()).compareTo(Boolean.valueOf((boolean)pushMetaInfo.isSetDescription()));
        if (n != 0) return n2;
        if (this.isSetDescription()) {
            n2 = n = TBaseHelper.compareTo(this.description, pushMetaInfo.description);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetNotifyType()).compareTo(Boolean.valueOf((boolean)pushMetaInfo.isSetNotifyType()));
        if (n != 0) return n2;
        if (this.isSetNotifyType()) {
            n2 = n = TBaseHelper.compareTo(this.notifyType, pushMetaInfo.notifyType);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetUrl()).compareTo(Boolean.valueOf((boolean)pushMetaInfo.isSetUrl()));
        if (n != 0) return n2;
        if (this.isSetUrl()) {
            n2 = n = TBaseHelper.compareTo(this.url, pushMetaInfo.url);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetPassThrough()).compareTo(Boolean.valueOf((boolean)pushMetaInfo.isSetPassThrough()));
        if (n != 0) return n2;
        if (this.isSetPassThrough()) {
            n2 = n = TBaseHelper.compareTo(this.passThrough, pushMetaInfo.passThrough);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetNotifyId()).compareTo(Boolean.valueOf((boolean)pushMetaInfo.isSetNotifyId()));
        if (n != 0) return n2;
        if (this.isSetNotifyId()) {
            n2 = n = TBaseHelper.compareTo(this.notifyId, pushMetaInfo.notifyId);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetExtra()).compareTo(Boolean.valueOf((boolean)pushMetaInfo.isSetExtra()));
        if (n != 0) return n2;
        if (this.isSetExtra()) {
            n2 = n = TBaseHelper.compareTo(this.extra, pushMetaInfo.extra);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetInternal()).compareTo(Boolean.valueOf((boolean)pushMetaInfo.isSetInternal()));
        if (n != 0) return n2;
        if (this.isSetInternal()) {
            n2 = n = TBaseHelper.compareTo(this.internal, pushMetaInfo.internal);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetIgnoreRegInfo()).compareTo(Boolean.valueOf((boolean)pushMetaInfo.isSetIgnoreRegInfo()));
        if (n != 0) return n2;
        if (!this.isSetIgnoreRegInfo()) return 0;
        n2 = n = TBaseHelper.compareTo(this.ignoreRegInfo, pushMetaInfo.ignoreRegInfo);
        if (n != 0) return n2;
        return 0;
    }

    public PushMetaInfo deepCopy() {
        return new PushMetaInfo(this);
    }

    public boolean equals(PushMetaInfo pushMetaInfo) {
        if (pushMetaInfo == null) {
            return false;
        }
        boolean bl = this.isSetId();
        boolean bl2 = pushMetaInfo.isSetId();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (!this.id.equals((Object)pushMetaInfo.id)) {
                return false;
            }
        }
        if (true || true) {
            if (!true || !true) {
                return false;
            }
            if (this.messageTs != pushMetaInfo.messageTs) {
                return false;
            }
        }
        bl = this.isSetTopic();
        bl2 = pushMetaInfo.isSetTopic();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (!this.topic.equals((Object)pushMetaInfo.topic)) {
                return false;
            }
        }
        bl = this.isSetTitle();
        bl2 = pushMetaInfo.isSetTitle();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (!this.title.equals((Object)pushMetaInfo.title)) {
                return false;
            }
        }
        bl = this.isSetDescription();
        bl2 = pushMetaInfo.isSetDescription();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (!this.description.equals((Object)pushMetaInfo.description)) {
                return false;
            }
        }
        bl = this.isSetNotifyType();
        bl2 = pushMetaInfo.isSetNotifyType();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (this.notifyType != pushMetaInfo.notifyType) {
                return false;
            }
        }
        bl = this.isSetUrl();
        bl2 = pushMetaInfo.isSetUrl();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (!this.url.equals((Object)pushMetaInfo.url)) {
                return false;
            }
        }
        bl = this.isSetPassThrough();
        bl2 = pushMetaInfo.isSetPassThrough();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (this.passThrough != pushMetaInfo.passThrough) {
                return false;
            }
        }
        bl = this.isSetNotifyId();
        bl2 = pushMetaInfo.isSetNotifyId();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (this.notifyId != pushMetaInfo.notifyId) {
                return false;
            }
        }
        bl = this.isSetExtra();
        bl2 = pushMetaInfo.isSetExtra();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (!this.extra.equals(pushMetaInfo.extra)) {
                return false;
            }
        }
        bl = this.isSetInternal();
        bl2 = pushMetaInfo.isSetInternal();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (!this.internal.equals(pushMetaInfo.internal)) {
                return false;
            }
        }
        bl = this.isSetIgnoreRegInfo();
        bl2 = pushMetaInfo.isSetIgnoreRegInfo();
        if (bl || bl2) {
            if (!bl || !bl2) {
                return false;
            }
            if (this.ignoreRegInfo != pushMetaInfo.ignoreRegInfo) {
                return false;
            }
        }
        return true;
    }

    /*
     * Enabled aggressive block sorting
     */
    public boolean equals(Object object) {
        if (object == null || !(object instanceof PushMetaInfo)) {
            return false;
        }
        return this.equals((PushMetaInfo)object);
    }

    public String getDescription() {
        return this.description;
    }

    public Map<String, String> getExtra() {
        return this.extra;
    }

    public String getId() {
        return this.id;
    }

    public long getMessageTs() {
        return this.messageTs;
    }

    public int getNotifyId() {
        return this.notifyId;
    }

    public int getNotifyType() {
        return this.notifyType;
    }

    public int getPassThrough() {
        return this.passThrough;
    }

    public String getTitle() {
        return this.title;
    }

    public String getTopic() {
        return this.topic;
    }

    public int hashCode() {
        return 0;
    }

    public boolean isIgnoreRegInfo() {
        return this.ignoreRegInfo;
    }

    public boolean isSetDescription() {
        if (this.description != null) {
            return true;
        }
        return false;
    }

    public boolean isSetExtra() {
        if (this.extra != null) {
            return true;
        }
        return false;
    }

    public boolean isSetId() {
        if (this.id != null) {
            return true;
        }
        return false;
    }

    public boolean isSetIgnoreRegInfo() {
        return this.__isset_bit_vector.get(4);
    }

    public boolean isSetInternal() {
        if (this.internal != null) {
            return true;
        }
        return false;
    }

    public boolean isSetMessageTs() {
        return this.__isset_bit_vector.get(0);
    }

    public boolean isSetNotifyId() {
        return this.__isset_bit_vector.get(3);
    }

    public boolean isSetNotifyType() {
        return this.__isset_bit_vector.get(1);
    }

    public boolean isSetPassThrough() {
        return this.__isset_bit_vector.get(2);
    }

    public boolean isSetTitle() {
        if (this.title != null) {
            return true;
        }
        return false;
    }

    public boolean isSetTopic() {
        if (this.topic != null) {
            return true;
        }
        return false;
    }

    public boolean isSetUrl() {
        if (this.url != null) {
            return true;
        }
        return false;
    }

    public void putToExtra(String string2, String string3) {
        if (this.extra == null) {
            this.extra = new HashMap();
        }
        this.extra.put(string2, string3);
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
                if (this.isSetMessageTs()) break;
                throw new TProtocolException("Required field 'messageTs' was not found in serialized data! Struct: " + this.toString());
            }
            switch (object.id) {
                int n;
                String string2;
                String string3;
                default: {
                    TProtocolUtil.skip(tProtocol, object.type);
                    break;
                }
                case 1: {
                    if (object.type == 11) {
                        this.id = tProtocol.readString();
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, object.type);
                    break;
                }
                case 2: {
                    if (object.type == 10) {
                        this.messageTs = tProtocol.readI64();
                        this.setMessageTsIsSet(true);
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, object.type);
                    break;
                }
                case 3: {
                    if (object.type == 11) {
                        this.topic = tProtocol.readString();
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, object.type);
                    break;
                }
                case 4: {
                    if (object.type == 11) {
                        this.title = tProtocol.readString();
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, object.type);
                    break;
                }
                case 5: {
                    if (object.type == 11) {
                        this.description = tProtocol.readString();
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, object.type);
                    break;
                }
                case 6: {
                    if (object.type == 8) {
                        this.notifyType = tProtocol.readI32();
                        this.setNotifyTypeIsSet(true);
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, object.type);
                    break;
                }
                case 7: {
                    if (object.type == 11) {
                        this.url = tProtocol.readString();
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, object.type);
                    break;
                }
                case 8: {
                    if (object.type == 8) {
                        this.passThrough = tProtocol.readI32();
                        this.setPassThroughIsSet(true);
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, object.type);
                    break;
                }
                case 9: {
                    if (object.type == 8) {
                        this.notifyId = tProtocol.readI32();
                        this.setNotifyIdIsSet(true);
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, object.type);
                    break;
                }
                case 10: {
                    if (object.type == 13) {
                        object = tProtocol.readMapBegin();
                        this.extra = new HashMap(object.size * 2);
                        for (n = 0; n < object.size; ++n) {
                            string2 = tProtocol.readString();
                            string3 = tProtocol.readString();
                            this.extra.put(string2, string3);
                        }
                        tProtocol.readMapEnd();
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, object.type);
                    break;
                }
                case 11: {
                    if (object.type == 13) {
                        object = tProtocol.readMapBegin();
                        this.internal = new HashMap(object.size * 2);
                        for (n = 0; n < object.size; ++n) {
                            string2 = tProtocol.readString();
                            string3 = tProtocol.readString();
                            this.internal.put(string2, string3);
                        }
                        tProtocol.readMapEnd();
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, object.type);
                    break;
                }
                case 12: {
                    if (object.type == 2) {
                        this.ignoreRegInfo = tProtocol.readBool();
                        this.setIgnoreRegInfoIsSet(true);
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, object.type);
                }
            }
            tProtocol.readFieldEnd();
        } while (true);
        this.validate();
    }

    public void setIgnoreRegInfoIsSet(boolean bl) {
        this.__isset_bit_vector.set(4, bl);
    }

    public void setMessageTsIsSet(boolean bl) {
        this.__isset_bit_vector.set(0, bl);
    }

    public void setNotifyIdIsSet(boolean bl) {
        this.__isset_bit_vector.set(3, bl);
    }

    public void setNotifyTypeIsSet(boolean bl) {
        this.__isset_bit_vector.set(1, bl);
    }

    public void setPassThroughIsSet(boolean bl) {
        this.__isset_bit_vector.set(2, bl);
    }

    /*
     * Enabled aggressive block sorting
     */
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("PushMetaInfo(");
        stringBuilder.append("id:");
        if (this.id == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.id);
        }
        if (!false) {
            stringBuilder.append(", ");
        }
        stringBuilder.append("messageTs:");
        stringBuilder.append(this.messageTs);
        if (this.isSetTopic()) {
            if (!false) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("topic:");
            if (this.topic == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.topic);
            }
        }
        if (this.isSetTitle()) {
            if (!false) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("title:");
            if (this.title == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.title);
            }
        }
        if (this.isSetDescription()) {
            if (!false) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("description:");
            if (this.description == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.description);
            }
        }
        if (this.isSetNotifyType()) {
            if (!false) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("notifyType:");
            stringBuilder.append(this.notifyType);
        }
        if (this.isSetUrl()) {
            if (!false) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("url:");
            if (this.url == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.url);
            }
        }
        if (this.isSetPassThrough()) {
            if (!false) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("passThrough:");
            stringBuilder.append(this.passThrough);
        }
        if (this.isSetNotifyId()) {
            if (!false) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("notifyId:");
            stringBuilder.append(this.notifyId);
        }
        if (this.isSetExtra()) {
            if (!false) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("extra:");
            if (this.extra == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.extra);
            }
        }
        if (this.isSetInternal()) {
            if (!false) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("internal:");
            if (this.internal == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.internal);
            }
        }
        if (this.isSetIgnoreRegInfo()) {
            if (!false) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("ignoreRegInfo:");
            stringBuilder.append(this.ignoreRegInfo);
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public void validate() throws TException {
        if (this.id == null) {
            throw new TProtocolException("Required field 'id' was not present! Struct: " + this.toString());
        }
    }

    @Override
    public void write(TProtocol tProtocol) throws TException {
        this.validate();
        tProtocol.writeStructBegin(STRUCT_DESC);
        if (this.id != null) {
            tProtocol.writeFieldBegin(ID_FIELD_DESC);
            tProtocol.writeString(this.id);
            tProtocol.writeFieldEnd();
        }
        tProtocol.writeFieldBegin(MESSAGE_TS_FIELD_DESC);
        tProtocol.writeI64(this.messageTs);
        tProtocol.writeFieldEnd();
        if (this.topic != null && this.isSetTopic()) {
            tProtocol.writeFieldBegin(TOPIC_FIELD_DESC);
            tProtocol.writeString(this.topic);
            tProtocol.writeFieldEnd();
        }
        if (this.title != null && this.isSetTitle()) {
            tProtocol.writeFieldBegin(TITLE_FIELD_DESC);
            tProtocol.writeString(this.title);
            tProtocol.writeFieldEnd();
        }
        if (this.description != null && this.isSetDescription()) {
            tProtocol.writeFieldBegin(DESCRIPTION_FIELD_DESC);
            tProtocol.writeString(this.description);
            tProtocol.writeFieldEnd();
        }
        if (this.isSetNotifyType()) {
            tProtocol.writeFieldBegin(NOTIFY_TYPE_FIELD_DESC);
            tProtocol.writeI32(this.notifyType);
            tProtocol.writeFieldEnd();
        }
        if (this.url != null && this.isSetUrl()) {
            tProtocol.writeFieldBegin(URL_FIELD_DESC);
            tProtocol.writeString(this.url);
            tProtocol.writeFieldEnd();
        }
        if (this.isSetPassThrough()) {
            tProtocol.writeFieldBegin(PASS_THROUGH_FIELD_DESC);
            tProtocol.writeI32(this.passThrough);
            tProtocol.writeFieldEnd();
        }
        if (this.isSetNotifyId()) {
            tProtocol.writeFieldBegin(NOTIFY_ID_FIELD_DESC);
            tProtocol.writeI32(this.notifyId);
            tProtocol.writeFieldEnd();
        }
        if (this.extra != null && this.isSetExtra()) {
            tProtocol.writeFieldBegin(EXTRA_FIELD_DESC);
            tProtocol.writeMapBegin(new TMap(11, 11, this.extra.size()));
            for (Map.Entry<String, String> entry : this.extra.entrySet()) {
                tProtocol.writeString((String)entry.getKey());
                tProtocol.writeString((String)entry.getValue());
            }
            tProtocol.writeMapEnd();
            tProtocol.writeFieldEnd();
        }
        if (this.internal != null && this.isSetInternal()) {
            tProtocol.writeFieldBegin(INTERNAL_FIELD_DESC);
            tProtocol.writeMapBegin(new TMap(11, 11, this.internal.size()));
            for (Map.Entry<String, String> entry : this.internal.entrySet()) {
                tProtocol.writeString((String)entry.getKey());
                tProtocol.writeString((String)entry.getValue());
            }
            tProtocol.writeMapEnd();
            tProtocol.writeFieldEnd();
        }
        if (this.isSetIgnoreRegInfo()) {
            tProtocol.writeFieldBegin(IGNORE_REG_INFO_FIELD_DESC);
            tProtocol.writeBool(this.ignoreRegInfo);
            tProtocol.writeFieldEnd();
        }
        tProtocol.writeFieldStop();
        tProtocol.writeStructEnd();
    }

    public static enum _Fields {
        ID(1, "id"),
        MESSAGE_TS(2, "messageTs"),
        TOPIC(3, "topic"),
        TITLE(4, "title"),
        DESCRIPTION(5, "description"),
        NOTIFY_TYPE(6, "notifyType"),
        URL(7, "url"),
        PASS_THROUGH(8, "passThrough"),
        NOTIFY_ID(9, "notifyId"),
        EXTRA(10, "extra"),
        INTERNAL(11, "internal"),
        IGNORE_REG_INFO(12, "ignoreRegInfo");
        
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

