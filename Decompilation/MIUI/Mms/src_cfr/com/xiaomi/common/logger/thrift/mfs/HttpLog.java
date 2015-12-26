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

import com.xiaomi.common.logger.thrift.Common;
import com.xiaomi.common.logger.thrift.mfs.HttpApi;
import com.xiaomi.common.logger.thrift.mfs.Passport;
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
import org.apache.thrift.meta_data.StructMetaData;
import org.apache.thrift.protocol.TField;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.protocol.TProtocolUtil;
import org.apache.thrift.protocol.TStruct;

public class HttpLog
implements TBase<HttpLog, _Fields>,
Serializable,
Cloneable {
    private static final TField CATEGORY_FIELD_DESC;
    private static final TField COMMON_FIELD_DESC;
    private static final TField HTTP_API_FIELD_DESC;
    private static final TField PASSPORT_FIELD_DESC;
    private static final TStruct STRUCT_DESC;
    public static final Map<_Fields, FieldMetaData> metaDataMap;
    private String category = "";
    private Common common;
    private HttpApi httpApi;
    private Passport passport;

    static {
        STRUCT_DESC = new TStruct("HttpLog");
        COMMON_FIELD_DESC = new TField("common", 12, 1);
        CATEGORY_FIELD_DESC = new TField("category", 11, 2);
        HTTP_API_FIELD_DESC = new TField("httpApi", 12, 3);
        PASSPORT_FIELD_DESC = new TField("passport", 12, 4);
        EnumMap enumMap = new EnumMap((Class)_Fields.class);
        enumMap.put(_Fields.COMMON, new FieldMetaData("common", 1, new StructMetaData(12, Common.class)));
        enumMap.put(_Fields.CATEGORY, new FieldMetaData("category", 1, new FieldValueMetaData(11)));
        enumMap.put(_Fields.HTTP_API, new FieldMetaData("httpApi", 2, new StructMetaData(12, HttpApi.class)));
        enumMap.put(_Fields.PASSPORT, new FieldMetaData("passport", 2, new StructMetaData(12, Passport.class)));
        metaDataMap = Collections.unmodifiableMap((Map)enumMap);
        FieldMetaData.addStructMetaDataMap(HttpLog.class, metaDataMap);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public int compareTo(HttpLog httpLog) {
        int n;
        if (!this.getClass().equals((Object)httpLog.getClass())) {
            return this.getClass().getName().compareTo(httpLog.getClass().getName());
        }
        int n2 = n = Boolean.valueOf((boolean)this.isSetCommon()).compareTo(Boolean.valueOf((boolean)httpLog.isSetCommon()));
        if (n != 0) return n2;
        if (this.isSetCommon()) {
            n2 = n = TBaseHelper.compareTo(this.common, httpLog.common);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetCategory()).compareTo(Boolean.valueOf((boolean)httpLog.isSetCategory()));
        if (n != 0) return n2;
        if (this.isSetCategory()) {
            n2 = n = TBaseHelper.compareTo(this.category, httpLog.category);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetHttpApi()).compareTo(Boolean.valueOf((boolean)httpLog.isSetHttpApi()));
        if (n != 0) return n2;
        if (this.isSetHttpApi()) {
            n2 = n = TBaseHelper.compareTo(this.httpApi, httpLog.httpApi);
            if (n != 0) return n2;
        }
        n2 = n = Boolean.valueOf((boolean)this.isSetPassport()).compareTo(Boolean.valueOf((boolean)httpLog.isSetPassport()));
        if (n != 0) return n2;
        if (!this.isSetPassport()) return 0;
        n2 = n = TBaseHelper.compareTo(this.passport, httpLog.passport);
        if (n != 0) return n2;
        return 0;
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public boolean equals(HttpLog httpLog) {
        if (httpLog == null) {
            return false;
        }
        boolean bl = this.isSetCommon();
        boolean bl2 = httpLog.isSetCommon();
        if (bl || bl2) {
            if (!bl) return false;
            if (!bl2) return false;
            if (!this.common.equals(httpLog.common)) return false;
        }
        bl = this.isSetCategory();
        bl2 = httpLog.isSetCategory();
        if (bl || bl2) {
            if (!bl) return false;
            if (!bl2) return false;
            if (!this.category.equals((Object)httpLog.category)) return false;
        }
        bl = this.isSetHttpApi();
        bl2 = httpLog.isSetHttpApi();
        if (bl || bl2) {
            if (!bl) return false;
            if (!bl2) return false;
            if (!this.httpApi.equals(httpLog.httpApi)) return false;
        }
        bl = this.isSetPassport();
        bl2 = httpLog.isSetPassport();
        if (!bl) {
            if (!bl2) return true;
        }
        if (!bl) return false;
        if (!bl2) return false;
        if (!this.passport.equals(httpLog.passport)) return false;
        return true;
    }

    /*
     * Enabled aggressive block sorting
     */
    public boolean equals(Object object) {
        if (object == null || !(object instanceof HttpLog)) {
            return false;
        }
        return this.equals((HttpLog)object);
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

    public boolean isSetCommon() {
        if (this.common != null) {
            return true;
        }
        return false;
    }

    public boolean isSetHttpApi() {
        if (this.httpApi != null) {
            return true;
        }
        return false;
    }

    public boolean isSetPassport() {
        if (this.passport != null) {
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
                    if (tField.type == 12) {
                        this.common = new Common();
                        this.common.read(tProtocol);
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, tField.type);
                    break;
                }
                case 2: {
                    if (tField.type == 11) {
                        this.category = tProtocol.readString();
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, tField.type);
                    break;
                }
                case 3: {
                    if (tField.type == 12) {
                        this.httpApi = new HttpApi();
                        this.httpApi.read(tProtocol);
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, tField.type);
                    break;
                }
                case 4: {
                    if (tField.type == 12) {
                        this.passport = new Passport();
                        this.passport.read(tProtocol);
                        break;
                    }
                    TProtocolUtil.skip(tProtocol, tField.type);
                }
            }
            tProtocol.readFieldEnd();
        } while (true);
    }

    public HttpLog setCategory(String string2) {
        this.category = string2;
        return this;
    }

    public HttpLog setCommon(Common common) {
        this.common = common;
        return this;
    }

    public HttpLog setHttpApi(HttpApi httpApi) {
        this.httpApi = httpApi;
        return this;
    }

    /*
     * Enabled aggressive block sorting
     */
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("HttpLog(");
        stringBuilder.append("common:");
        if (this.common == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.common);
        }
        if (!false) {
            stringBuilder.append(", ");
        }
        stringBuilder.append("category:");
        if (this.category == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.category);
        }
        if (this.isSetHttpApi()) {
            if (!false) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("httpApi:");
            if (this.httpApi == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.httpApi);
            }
        }
        if (this.isSetPassport()) {
            if (!false) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("passport:");
            if (this.passport == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.passport);
            }
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public void validate() throws TException {
        if (this.common == null) {
            throw new TProtocolException("Required field 'common' was not present! Struct: " + this.toString());
        }
        if (this.category == null) {
            throw new TProtocolException("Required field 'category' was not present! Struct: " + this.toString());
        }
    }

    @Override
    public void write(TProtocol tProtocol) throws TException {
        this.validate();
        tProtocol.writeStructBegin(STRUCT_DESC);
        if (this.common != null) {
            tProtocol.writeFieldBegin(COMMON_FIELD_DESC);
            this.common.write(tProtocol);
            tProtocol.writeFieldEnd();
        }
        if (this.category != null) {
            tProtocol.writeFieldBegin(CATEGORY_FIELD_DESC);
            tProtocol.writeString(this.category);
            tProtocol.writeFieldEnd();
        }
        if (this.httpApi != null && this.isSetHttpApi()) {
            tProtocol.writeFieldBegin(HTTP_API_FIELD_DESC);
            this.httpApi.write(tProtocol);
            tProtocol.writeFieldEnd();
        }
        if (this.passport != null && this.isSetPassport()) {
            tProtocol.writeFieldBegin(PASSPORT_FIELD_DESC);
            this.passport.write(tProtocol);
            tProtocol.writeFieldEnd();
        }
        tProtocol.writeFieldStop();
        tProtocol.writeStructEnd();
    }

    public static enum _Fields {
        COMMON(1, "common"),
        CATEGORY(2, "category"),
        HTTP_API(3, "httpApi"),
        PASSPORT(4, "passport");
        
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

