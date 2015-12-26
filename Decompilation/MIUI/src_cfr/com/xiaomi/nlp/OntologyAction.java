/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.StringBuffer
 *  java.lang.System
 *  java.util.HashMap
 */
package com.xiaomi.nlp;

import com.xiaomi.nlp.Ontology;
import com.xiaomi.nlp.OntologyButton;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class OntologyAction
extends Ontology {
    private int actionID = -1;
    public List<OntologyButton> buttons;
    private long endPeriodOfValidity = 4102416000000L;
    private long startPeriodOfValidity = 1104508800000L;

    public OntologyAction(String string2, int n, List<OntologyButton> list) {
        this.ontologyName = string2;
        this.actionID = n;
        this.buttons = list;
    }

    public int getActionCount(int n) {
        if (this.buttons != null && this.buttons.size() > n) {
            return this.buttons.get(n).getActionCount();
        }
        return 0;
    }

    public String getBtnAction(int n, int n2) {
        if (this.buttons != null && this.buttons.size() > n) {
            return (String)this.buttons.get(n).getAttrs2Type().get((Object)("action" + n2));
        }
        return "";
    }

    public int getBtnNumber() {
        return this.buttons.size();
    }

    public String getBtnTitle(int n) {
        if (this.buttons != null && this.buttons.size() > n) {
            return (String)this.buttons.get(n).getAttrs2Type().get((Object)"title");
        }
        return "";
    }

    public boolean matchPeriod() {
        long l = System.currentTimeMillis();
        if (this.startPeriodOfValidity > l || this.endPeriodOfValidity < l) {
            return false;
        }
        return true;
    }

    public void setEndPeriodOfValidity(long l) {
        this.endPeriodOfValidity = l;
    }

    public void setStartPeriodOfValidity(long l) {
        this.startPeriodOfValidity = l;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("defframe").append(' ').append(this.ontologyName).append("\r\n").append('{').append("\r\n");
        stringBuffer.append("\tactionID\uff1a").append(this.actionID).append("\r\n");
        Object object = OntologyAction.generalPeriod(this.startPeriodOfValidity, this.endPeriodOfValidity);
        if (object != null) {
            stringBuffer.append("\tperiod\uff1a").append((String)object).append("\r\n");
        }
        object = this.buttons.iterator();
        do {
            if (!object.hasNext()) {
                stringBuffer.append('}').append("\r\n");
                return stringBuffer.toString();
            }
            stringBuffer.append(((OntologyButton)object.next()).toString()).append("\r\n");
        } while (true);
    }
}

