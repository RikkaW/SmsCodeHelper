/*
 * Decompiled with CFR 0_110.
 */
package org.apache.thrift.meta_data;

import org.apache.thrift.meta_data.FieldValueMetaData;

public class ListMetaData
extends FieldValueMetaData {
    public final FieldValueMetaData elemMetaData;

    public ListMetaData(byte by, FieldValueMetaData fieldValueMetaData) {
        super(by);
        this.elemMetaData = fieldValueMetaData;
    }
}

