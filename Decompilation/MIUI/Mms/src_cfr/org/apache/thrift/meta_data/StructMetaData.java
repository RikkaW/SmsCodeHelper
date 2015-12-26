/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Class
 */
package org.apache.thrift.meta_data;

import org.apache.thrift.TBase;
import org.apache.thrift.meta_data.FieldValueMetaData;

public class StructMetaData
extends FieldValueMetaData {
    public final Class<? extends TBase> structClass;

    public StructMetaData(byte by, Class<? extends TBase> class_) {
        super(by);
        this.structClass = class_;
    }
}

