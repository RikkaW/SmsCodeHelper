/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.StringBuffer
 *  java.util.ArrayList
 */
package com.xiaomi.smsunderstand;

import com.xiaomi.nlp.OntologyTaskFrame;
import com.xiaomi.smsunderstand.AttrInfo;
import com.xiaomi.smsunderstand.EntityType;
import java.util.ArrayList;
import java.util.List;

public class OntologyResults
extends OntologyTaskFrame
implements Comparable<OntologyResults> {
    private static String valueDefault = null;
    private int actionID;
    private List<AttrInfo> attrInfos;

    public OntologyResults(OntologyTaskFrame ontologyTaskFrame) {
        this.actionID = ontologyTaskFrame.getActionID();
        this.operateID = ontologyTaskFrame.getOperateID();
        this.summation = ontologyTaskFrame.getSummation();
        this.priority = ontologyTaskFrame.getPriority();
        this.ontologyName = ontologyTaskFrame.getOntologyName();
        this.taskType = ontologyTaskFrame.getTaskType();
        this.setStartPeriodOfValidity(ontologyTaskFrame.getStartPeriodOfValidity());
        this.setEndPeriodOfValidity(ontologyTaskFrame.getEndPeriodOfValidity());
        this.attrInfos = new ArrayList(ontologyTaskFrame.getAttrCounts());
        int n = 0;
        while (n < ontologyTaskFrame.getAttrCounts()) {
            this.attrInfos.add(new AttrInfo(ontologyTaskFrame.getAttrNames().get(n), valueDefault, ontologyTaskFrame.getAttrType().get(n), 0, 0, 0));
            ++n;
        }
        return;
    }

    public static String getValueDefault() {
        return valueDefault;
    }

    @Override
    public int compareTo(OntologyResults ontologyResults) {
        if (this.priority > ontologyResults.priority) {
            return -1;
        }
        if (this.priority < ontologyResults.priority) {
            return 1;
        }
        return 0;
    }

    @Override
    public int getActionID() {
        return this.actionID;
    }

    public AttrInfo getAttrByName(String string2, OntologyTaskFrame ontologyTaskFrame) {
        int n;
        int n2 = n = ontologyTaskFrame.getAttrIndexByName(string2);
        if (n < 0) {
            n2 = n;
            if (this.summation > 0) {
                n2 = ontologyTaskFrame.getFatherAttrIndex(string2);
            }
        }
        if (n2 >= 0 && n2 < this.attrInfos.size()) {
            return this.attrInfos.get(n2);
        }
        return null;
    }

    public AttrInfo getAttrByType(int n, OntologyTaskFrame ontologyTaskFrame) {
        if ((n = ontologyTaskFrame.getAttrIndexByType(n)) >= 0 && n < this.attrInfos.size()) {
            return this.attrInfos.get(n);
        }
        return null;
    }

    public List<AttrInfo> getAttrInfos() {
        return this.attrInfos;
    }

    @Override
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("frameResult").append(" ").append(this.ontologyName).append("\r\n").append('{').append("\r\n");
        int n = 0;
        do {
            if (n >= this.attrInfos.size()) {
                String string2;
                if (this.actionID >= 0) {
                    stringBuffer.append("\tactionID\uff1a").append(this.actionID).append("\r\n");
                }
                if (this.operateID >= 0) {
                    stringBuffer.append("\toperateID\uff1a").append(this.operateID).append("\r\n");
                }
                if ((string2 = OntologyResults.generalPeriod(this.getStartPeriodOfValidity(), this.getEndPeriodOfValidity())) != null) {
                    stringBuffer.append("\tperiod\uff1a").append(string2).append("\r\n");
                }
                if (this.summation >= 0) {
                    stringBuffer.append("\tsummation\uff1a").append(this.summation).append("\r\n");
                }
                stringBuffer.append('}').append("\r\n");
                return stringBuffer.toString();
            }
            stringBuffer.append("\t").append(this.attrInfos.get(n).getName()).append('\uff1a').append(this.attrInfos.get(n).getType()).append('\uff1a').append(this.attrInfos.get(n).getValue()).append("\r\n");
            ++n;
        } while (true);
    }
}

