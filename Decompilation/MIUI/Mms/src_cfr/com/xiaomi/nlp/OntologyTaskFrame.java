/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.StringBuffer
 *  java.lang.System
 */
package com.xiaomi.nlp;

import com.xiaomi.nlp.ActionIdPeriod;
import com.xiaomi.nlp.Ontology;
import com.xiaomi.smsunderstand.EntityType;
import com.xiaomi.smsunderstand.SMSUnderstand;
import java.util.Iterator;
import java.util.List;

public class OntologyTaskFrame
extends Ontology {
    protected List<ActionIdPeriod> actionIDs;
    private String area = "\u4e2d\u56fd";
    private long endPeriodOfValidity = 4102416000000L;
    protected int operateID = -1;
    protected int priority = 0;
    private long startPeriodOfValidity = 1104508800000L;
    protected int summation = -1;
    protected EntityType taskType;

    public OntologyTaskFrame() {
        this.version = SMSUnderstand.getVersion();
        this.platform = 7;
    }

    public OntologyTaskFrame(List<ActionIdPeriod> list, String string2, List<String> list2, List<Integer> list3) {
        this.actionIDs = list;
        this.ontologyName = string2;
        this.setTaskType(string2);
        this.attrNames = list2;
        this.attrType = list3;
        this.version = SMSUnderstand.getVersion();
        this.platform = 7;
    }

    public static String getFatherAttrName(String string2) {
        int n = string2.lastIndexOf("_");
        if (n >= 0) {
            return string2.substring(0, n);
        }
        return "";
    }

    public int getActionID() {
        ActionIdPeriod actionIdPeriod;
        Iterator<ActionIdPeriod> iterator = this.actionIDs.iterator();
        do {
            if (iterator.hasNext()) continue;
            return -1;
        } while (!(actionIdPeriod = iterator.next()).matchPeriod());
        return actionIdPeriod.getActionId();
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public int getAttrIndexByName(String string2) {
        int n = 0;
        while (n < this.attrNames.size()) {
            int n2 = n;
            if (((String)this.attrNames.get(n)).equals((Object)string2)) return n2;
            ++n;
        }
        return -1;
    }

    public int getAttrIndexByName(String string2, int n) {
        if (this.attrNames.size() > n && n >= 0 && ((String)this.attrNames.get(n)).equals((Object)string2)) {
            return n;
        }
        return this.getAttrIndexByName(string2);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public int getAttrIndexByType(int n) {
        int n2 = 0;
        while (n2 < this.attrType.size()) {
            int n3 = n2;
            if ((Integer)this.attrType.get(n2) == n) return n3;
            ++n2;
        }
        return -1;
    }

    @Override
    public List<Integer> getAttrType() {
        return this.attrType;
    }

    public long getEndPeriodOfValidity() {
        return this.endPeriodOfValidity;
    }

    public int getFatherAttrIndex(String string2) {
        return this.getAttrIndexByName(OntologyTaskFrame.getFatherAttrName(string2));
    }

    public int getOperateID() {
        return this.operateID;
    }

    public int getPriority() {
        return this.priority;
    }

    public long getStartPeriodOfValidity() {
        return this.startPeriodOfValidity;
    }

    public int getSummation() {
        return this.summation;
    }

    public EntityType getTaskType() {
        return this.taskType;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public boolean isEffective(int var1_1, int var2_2, String var3_3, String var4_4) {
        var8_5 = System.currentTimeMillis();
        if (this.startPeriodOfValidity > var8_5) return false;
        if (this.endPeriodOfValidity < var8_5) {
            return false;
        }
        if (var3_3 == null || this.area.equals((Object)"\u4e2d\u56fd")) ** GOTO lbl17
        var10_6 = this.area.split("[,\uff0c]+");
        var6_7 = 0;
        var7_8 = var10_6.length;
        var5_9 = 0;
        do {
            if (var5_9 < var7_8) ** GOTO lbl14
            var5_9 = var6_7;
            ** GOTO lbl16
lbl14: // 1 sources:
            if (var3_3.startsWith(var10_6[var5_9])) {
                var5_9 = 1;
lbl16: // 2 sources:
                if (var5_9 == 0) return false;
lbl17: // 2 sources:
                if (this.matchVersion(var1_1, var2_2, var4_4) == null) return false;
                return true;
            }
            ++var5_9;
        } while (true);
    }

    public void setArea(String string2) {
        this.area = string2;
    }

    public void setEndPeriodOfValidity(long l) {
        this.endPeriodOfValidity = l;
    }

    public void setOperateID(int n) {
        this.operateID = n;
    }

    public void setPriority(int n) {
        this.priority = n;
    }

    public void setStartPeriodOfValidity(long l) {
        this.startPeriodOfValidity = l;
    }

    public void setSummation(int n) {
        this.summation = n;
    }

    public void setTaskType(String string2) {
        if (string2.equals((Object)"express")) {
            this.taskType = EntityType.EXPRESSNUMBER;
            return;
        }
        if (string2.equals((Object)"verificationcode")) {
            this.taskType = EntityType.VERIFICATIONCODE;
            return;
        }
        if (string2.equals((Object)"topup")) {
            this.taskType = EntityType.TOPUP;
            return;
        }
        if (string2.equals((Object)"creditcardhuankuan")) {
            this.taskType = EntityType.CREDITCARDHUANKUAN;
            return;
        }
        if (string2.equals((Object)"datetime")) {
            this.taskType = EntityType.TIME;
            return;
        }
        this.taskType = EntityType.SPECIALENTITY;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("defframe").append(" ").append(this.ontologyName).append("\r\n").append('{').append("\r\n");
        int n = 0;
        do {
            if (n >= this.getAttrCounts()) {
                String string2;
                if (this.getActionID() >= 0) {
                    stringBuffer.append("\tactionID\uff1a").append(this.getActionID()).append("\r\n");
                }
                if (this.operateID >= 0) {
                    stringBuffer.append("\toperateID\uff1a").append(this.operateID).append("\r\n");
                }
                if (this.summation >= 0) {
                    stringBuffer.append("\tsummation\uff1a").append(this.summation).append("\r\n");
                }
                if (!this.version.equals((Object)SMSUnderstand.getVersion())) {
                    stringBuffer.append("\tversion\uff1a").append(this.version).append("\r\n");
                }
                if (!this.area.equals((Object)"\u4e2d\u56fd")) {
                    stringBuffer.append("\tarea\uff1a").append(this.area).append("\r\n");
                }
                if ((string2 = OntologyTaskFrame.generalPeriod(this.startPeriodOfValidity, this.endPeriodOfValidity)) != null) {
                    stringBuffer.append("\tperiod\uff1a").append(string2).append("\r\n");
                }
                if (this.platform != 7) {
                    stringBuffer.append("\tplatform\uff1a").append(this.platform).append("\r\n");
                }
                if (this.systemLevel != 1) {
                    stringBuffer.append("\tsystemLevel\uff1a").append(this.systemLevel).append("\r\n");
                }
                stringBuffer.append('}').append("\r\n");
                return stringBuffer.toString();
            }
            stringBuffer.append("\t").append((String)this.attrNames.get(n)).append('\uff1a').append(this.attrType.get(n)).append("\r\n");
            ++n;
        } while (true);
    }
}

