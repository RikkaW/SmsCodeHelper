/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 */
package org.apache.thrift.meta_data;

import java.io.Serializable;

public class FieldValueMetaData
implements Serializable {
    private final boolean isTypedefType;
    public final byte type;
    private final String typedefName;

    public FieldValueMetaData(byte by) {
        this.type = by;
        this.isTypedefType = false;
        this.typedefName = null;
    }
}

