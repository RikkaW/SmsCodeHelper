/*
 * Decompiled with CFR 0_110.
 */
package org.apache.thrift.meta_data;

import org.apache.thrift.meta_data.FieldValueMetaData;

public class SetMetaData
extends FieldValueMetaData {
    public final FieldValueMetaData elemMetaData;

    public SetMetaData(byte by, FieldValueMetaData fieldValueMetaData) {
        super(by);
        this.elemMetaData = fieldValueMetaData;
    }
}

