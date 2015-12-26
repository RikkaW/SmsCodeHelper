/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Character
 *  java.lang.Object
 *  java.lang.String
 */
package com.xiaomi.smsunderstand;

import com.xiaomi.common.StringProcess;
import com.xiaomi.smsunderstand.EntityType;

public class EntityInfo
implements Comparable<EntityInfo> {
    private static String nomalRegex = "[\\.\\-\\+\\s\\\u4e00]+";
    private double confidence;
    private int endPosition;
    private int engCharCount = -1;
    private EntityType entityType = EntityType.UNKNOWN;
    private int groupEndPosition;
    private String groupEntity;
    private int groupStartPosition;
    private int numberCount = -1;
    private String remark;
    private int startPosition;
    private String target;
    private String target_nomal;

    public static String nomal(String string2) {
        return StringProcess.trim(string2.replaceAll(nomalRegex, "").toLowerCase(), Character.valueOf((char)'*'), Character.valueOf((char)' '), Character.valueOf((char)'@'), Character.valueOf((char)'.'), Character.valueOf((char)'-'), Character.valueOf((char)'&'), Character.valueOf((char)'?'), Character.valueOf((char)'='), Character.valueOf((char)'_'), Character.valueOf((char)'\u4e00'));
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    @Override
    public int compareTo(EntityInfo entityInfo) {
        if (this.startPosition < entityInfo.startPosition) {
            return -1;
        }
        if (this.startPosition > entityInfo.startPosition) {
            return 1;
        }
        if (this.endPosition < entityInfo.endPosition) return -1;
        if (this.endPosition <= entityInfo.endPosition) return 0;
        return 1;
    }

    public double getConfidence() {
        return this.confidence;
    }

    public int getEndPosition() {
        return this.endPosition;
    }

    public int getEngCharCount() {
        if (this.engCharCount < 0) {
            this.engCharCount = StringProcess.getEngCharCount(this.target);
        }
        return this.engCharCount;
    }

    public EntityType getEntityType() {
        return this.entityType;
    }

    public int getGroupEndPosition() {
        return this.groupEndPosition;
    }

    public String getGroupEntity() {
        return this.groupEntity;
    }

    public int getGroupStartPosition() {
        return this.groupStartPosition;
    }

    public int getNumberCount() {
        if (this.numberCount < 0) {
            this.numberCount = StringProcess.getNumberCount(this.target);
        }
        return this.numberCount;
    }

    public String getRemark() {
        return this.remark;
    }

    public int getStartPosition() {
        return this.startPosition;
    }

    public String getTarget() {
        return this.target;
    }

    public String getTarget_nomal() {
        return this.target_nomal;
    }

    public void noNomal() {
        this.target_nomal = this.target;
    }

    public void setConfidence(double d2) {
        this.confidence = d2;
    }

    public void setEndPosition(int n) {
        this.endPosition = n;
    }

    public void setEngCharCount(int n) {
        this.engCharCount = n;
    }

    public void setEntityType(EntityType entityType) {
        this.entityType = entityType;
    }

    public void setGroupEndPosition(int n) {
        this.groupEndPosition = n;
    }

    public void setGroupEntity(String string2) {
        this.groupEntity = string2;
    }

    public void setGroupStartPosition(int n) {
        this.groupStartPosition = n;
    }

    public void setNumberCount(int n) {
        this.numberCount = n;
    }

    public void setRemark(String string2) {
        this.remark = string2;
    }

    public void setStartPosition(int n) {
        this.startPosition = n;
    }

    public void setTarget(String string2) {
        this.target = string2;
    }

    public void setTargetNomal(String string2) {
        this.target_nomal = string2;
    }

    public void setTarget_nomal(String string2) {
        this.target_nomal = EntityInfo.nomal(string2);
    }

    /*
     * Enabled aggressive block sorting
     */
    public void setTarget_nomalFLOW(String string2) {
        this.target_nomal = string2.toUpperCase();
        if (this.target_nomal.contains((CharSequence)"\uff08") || this.target_nomal.contains((CharSequence)"(")) {
            this.target_nomal = this.target_nomal.replace((CharSequence)"\uff08", (CharSequence)"");
            this.target_nomal = this.target_nomal.replace((CharSequence)"\uff09", (CharSequence)"");
            this.target_nomal = this.target_nomal.replace((CharSequence)"(", (CharSequence)"");
            this.target_nomal = this.target_nomal.replace((CharSequence)")", (CharSequence)"");
        }
        if (!this.target_nomal.contains((CharSequence)"B")) {
            int n;
            for (n = 0; n < this.target_nomal.length() && this.target_nomal.charAt(n) != 'K' && this.target_nomal.charAt(n) != 'M' && this.target_nomal.charAt(n) != 'g'; ++n) {
            }
            this.target_nomal = String.valueOf((Object)this.target_nomal.substring(0, n)) + "B" + this.target_nomal.substring(n);
        }
    }
}

