/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Class
 *  java.lang.Object
 *  java.lang.String
 *  java.util.HashMap
 */
package org.apache.thrift.meta_data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import org.apache.thrift.TBase;
import org.apache.thrift.meta_data.FieldValueMetaData;

public class FieldMetaData
implements Serializable {
    private static Map<Class<? extends TBase>, Map<?, FieldMetaData>> structMap = new HashMap();
    public final String fieldName;
    public final byte requirementType;
    public final FieldValueMetaData valueMetaData;

    public FieldMetaData(String string2, byte by, FieldValueMetaData fieldValueMetaData) {
        this.fieldName = string2;
        this.requirementType = by;
        this.valueMetaData = fieldValueMetaData;
    }

    public static void addStructMetaDataMap(Class<? extends TBase> class_, Map<?, FieldMetaData> map) {
        structMap.put(class_, map);
    }
}

