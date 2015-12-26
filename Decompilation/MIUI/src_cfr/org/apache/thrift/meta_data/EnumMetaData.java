/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Class
 */
package org.apache.thrift.meta_data;

import org.apache.thrift.meta_data.FieldValueMetaData;

public class EnumMetaData
extends FieldValueMetaData {
    public final Class enumClass;

    public EnumMetaData(byte by, Class class_) {
        super(by);
        this.enumClass = class_;
    }
}

