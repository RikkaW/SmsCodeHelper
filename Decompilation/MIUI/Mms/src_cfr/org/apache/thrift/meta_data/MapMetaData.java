/*
 * Decompiled with CFR 0_110.
 */
package org.apache.thrift.meta_data;

import org.apache.thrift.meta_data.FieldValueMetaData;

public class MapMetaData
extends FieldValueMetaData {
    public final FieldValueMetaData keyMetaData;
    public final FieldValueMetaData valueMetaData;

    public MapMetaData(byte by, FieldValueMetaData fieldValueMetaData, FieldValueMetaData fieldValueMetaData2) {
        super(by);
        this.keyMetaData = fieldValueMetaData;
        this.valueMetaData = fieldValueMetaData2;
    }
}

