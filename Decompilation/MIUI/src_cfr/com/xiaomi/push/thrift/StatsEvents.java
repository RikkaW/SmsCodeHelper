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
package com.xiaomi.push.thrift;

import com.xiaomi.push.thrift.StatsEvent;
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

public class StatsEvents
implements TBase<StatsEvents, _Fields>,
Serializable,
Cloneable {
    private static final TField EVENTS_FIELD_DESC;
    private static final TField OPERATOR_FIELD_DESC;
    private static final TStruct STRUCT_DESC;
    private static final TField UUID_FIELD_DESC;
    public static final Map<_Fields, FieldMetaData> metaDataMap;
    public List<StatsEvent> events;
    public String operator;
    public String uuid;

    static {
        STRUCT_DESC = new TStruct("StatsEvents");
        UUID_FIELD_DESC = new TField("uuid", 11, 1);
        OPERATOR_FIELD_DESC = new TField("operator", 11, 2);
        EVENTS_FIELD_DESC = new TField("events", 15, 3);
        EnumMap enumMap = new EnumMap((Class)_Fields.class);
        enumMap.put(_Fields.UUID, new FieldMetaData("uuid", 1, new FieldValueMetaData(11)));
        enumMap.put(_Fields.OPERATOR, new FieldMetaData("operator", 2, new FieldValueMetaData(11)));
        enumMap.put(_Fields.EVENTS, new FieldMetaData("events", 1, new ListMetaData(15, new StructMetaData(12, StatsEvent.class))));
        metaDataMap = Collections.unmodifiableMap((Map)enumMap);
        FieldMetaData.addStructMetaDataMap(StatsEvents.class, metaDataMap);
    }

    public StatsEvents() {
    }

    public StatsEvents(String string2, List<StatsEvent> list) {
        this();
        this.uuid = string2;
        this.events = list;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public int compareTo(StatsEvents statsEvents) {
        int n;
        if (!this.getClass().equals((Object)statsEvents.getClass())) {
            return this.getClass().getName().compareTo(statsEvents.getClass().getName());
        }
        int n2 = n = Boolean.valueOf((boolean)this.isSetUuid()).compareTo(Boolean.valueOf((boolean)statsEvents.isSetUuid()));
        if (n != 0) return n2;
        if (this.isSetUuid()) {
            n2 = n = TBaseHelper.compareTo(this.uuid, statsEvents.uuid);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetOperator()).compareTo(Boolean.valueOf((boolean)statsEvents.isSetOperator()));
        if (n != 0) return n2;
        if (this.isSetOperator()) {
            n2 = n = TBaseHelper.compareTo(this.operator, statsEvents.operator);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetEvents()).compareTo(Boolean.valueOf((boolean)statsEvents.isSetEvents()));
        if (n != 0) return n2;
        if (!this.isSetEvents()) return 0;
        n2 = n = TBaseHelper.compareTo(this.events, statsEvents.events);
        if (n != 0) return n2;
        return 0;
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public boolean equals(StatsEvents statsEvents) {
        if (statsEvents == null) {
            return false;
        }
        boolean bl = this.isSetUuid();
        boolean bl2 = statsEvents.isSetUuid();
        if (bl || bl2) {
            if (!bl) return false;
            if (!bl2) return false;
            if (!this.uuid.equals((Object)statsEvents.uuid)) return false;
        }
        bl = this.isSetOperator();
        bl2 = statsEvents.isSetOperator();
        if (bl || bl2) {
            if (!bl) return false;
            if (!bl2) return false;
            if (!this.operator.equals((Object)statsEvents.operator)) return false;
        }
        bl = this.isSetEvents();
        bl2 = statsEvents.isSetEvents();
        if (!bl) {
            if (!bl2) return true;
        }
        if (!bl) return false;
        if (!bl2) return false;
        if (!this.events.equals(statsEvents.events)) return false;
        return true;
    }

    /*
     * Enabled aggressive block sorting
     */
    public boolean equals(Object object) {
        if (object == null || !(object instanceof StatsEvents)) {
            return false;
        }
        return this.equals((StatsEvents)object);
    }

    public int hashCode() {
        return 0;
    }

    public boolean isSetEvents() {
        if (this.events != null) {
            return true;
        }
        return false;
    }

    public boolean isSetOperator() {
        if (this.operator != null) {
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
                        this.uuid = tProtocol.readString();
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, object.type);
                    break;
                }
                case 2: {
                    if (object.type == 11) {
                        this.operator = tProtocol.readString();
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, object.type);
                    break;
                }
                case 3: {
                    if (object.type == 15) {
                        object = tProtocol.readListBegin();
                        this.events = new ArrayList(object.size);
                        for (int i = 0; i < object.size; ++i) {
                            StatsEvent statsEvent = new StatsEvent();
                            statsEvent.read(tProtocol);
                            this.events.add(statsEvent);
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

    public StatsEvents setOperator(String string2) {
        this.operator = string2;
        return this;
    }

    /*
     * Enabled aggressive block sorting
     */
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("StatsEvents(");
        stringBuilder.append("uuid:");
        if (this.uuid == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.uuid);
        }
        if (this.isSetOperator()) {
            if (!false) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("operator:");
            if (this.operator == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.operator);
            }
        }
        if (!false) {
            stringBuilder.append(", ");
        }
        stringBuilder.append("events:");
        if (this.events == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.events);
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public void validate() throws TException {
        if (this.uuid == null) {
            throw new TProtocolException("Required field 'uuid' was not present! Struct: " + this.toString());
        }
        if (this.events == null) {
            throw new TProtocolException("Required field 'events' was not present! Struct: " + this.toString());
        }
    }

    @Override
    public void write(TProtocol tProtocol) throws TException {
        this.validate();
        tProtocol.writeStructBegin(STRUCT_DESC);
        if (this.uuid != null) {
            tProtocol.writeFieldBegin(UUID_FIELD_DESC);
            tProtocol.writeString(this.uuid);
            tProtocol.writeFieldEnd();
        }
        if (this.operator != null && this.isSetOperator()) {
            tProtocol.writeFieldBegin(OPERATOR_FIELD_DESC);
            tProtocol.writeString(this.operator);
            tProtocol.writeFieldEnd();
        }
        if (this.events != null) {
            tProtocol.writeFieldBegin(EVENTS_FIELD_DESC);
            tProtocol.writeListBegin(new TList(12, this.events.size()));
            Iterator<StatsEvent> iterator = this.events.iterator();
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
        UUID(1, "uuid"),
        OPERATOR(2, "operator"),
        EVENTS(3, "events");
        
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

