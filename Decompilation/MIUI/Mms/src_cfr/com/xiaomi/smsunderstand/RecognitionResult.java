/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 */
package com.xiaomi.smsunderstand;

import com.xiaomi.smsunderstand.EntityType;

public class RecognitionResult {
    private double confidence;
    private int endPosition;
    private EntityType entityType;
    private int groupEndPosition;
    private String groupEntity;
    private int groupStartPosition;
    private String parameter;
    private String regularizationResult;
    private int startPosition;

    public RecognitionResult(int n, int n2, int n3, int n4, EntityType entityType, double d2, String string2, String string3, String string4) {
        this.startPosition = n;
        this.endPosition = n2;
        this.groupStartPosition = n3;
        this.groupEndPosition = n4;
        this.entityType = entityType;
        this.confidence = d2;
        this.regularizationResult = string2;
        this.parameter = string3;
        this.groupEntity = string4;
    }

    public int getEndPosition() {
        return this.endPosition;
    }

    public EntityType getEntityType() {
        return this.entityType;
    }

    public String getGroupEntity() {
        return this.groupEntity;
    }

    public String getParameter() {
        return this.parameter;
    }

    public String getRegularizationResult() {
        return this.regularizationResult;
    }

    public int getStartPosition() {
        return this.startPosition;
    }

    public String toString() {
        return " [startPosition=" + this.startPosition + ", endPosition=" + this.endPosition + ", regularizationResult=" + this.regularizationResult + ", entityType=" + (Object)((Object)this.entityType) + ", parameter=" + this.parameter + ", confidence=" + this.confidence + "]";
    }
}

