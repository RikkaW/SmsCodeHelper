/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.StringBuffer
 *  java.util.HashMap
 *  java.util.Map$Entry
 */
package com.xiaomi.nlp;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class OntologyButton {
    private int actionCount = 1;
    public HashMap<String, String> attrs2Type;
    private String buttonName = "button";

    public OntologyButton(int n, HashMap<String, String> hashMap) {
        this.actionCount = n;
        this.attrs2Type = hashMap;
    }

    public int getActionCount() {
        return this.actionCount;
    }

    public HashMap<String, String> getAttrs2Type() {
        return this.attrs2Type;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("\tdefbutton").append("\r\n\t").append('{').append("\r\n");
        Iterator iterator = this.attrs2Type.entrySet().iterator();
        do {
            if (!iterator.hasNext()) {
                stringBuffer.append("\t}");
                return stringBuffer.toString();
            }
            Map.Entry entry = (Map.Entry)iterator.next();
            stringBuffer.append("\t\t").append((String)entry.getKey()).append('\uff1a').append((String)entry.getValue()).append("\r\n");
        } while (true);
    }
}

