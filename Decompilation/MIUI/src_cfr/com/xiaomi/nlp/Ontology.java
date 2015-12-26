/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Character
 *  java.lang.Integer
 *  java.lang.String
 *  java.lang.StringBuffer
 *  java.text.SimpleDateFormat
 *  java.util.Date
 */
package com.xiaomi.nlp;

import com.xiaomi.common.StringProcess;
import com.xiaomi.nlp.VersionControl;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Ontology
extends VersionControl {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    protected List<String> attrNames;
    protected List<Integer> attrType;
    protected String ontologyName;

    /*
     * Enabled aggressive block sorting
     */
    public static String generalPeriod(long l, long l2) {
        StringBuffer stringBuffer = new StringBuffer(23);
        stringBuffer.append('[');
        Date date = new Date();
        if (l != 1104508800000L) {
            date.setTime(l);
            stringBuffer.append(sdf.format(date)).append(',');
            date.setTime(l2);
            stringBuffer.append(sdf.format(date));
        } else {
            if (l2 == 4102416000000L) {
                return null;
            }
            date.setTime(l2);
            stringBuffer.append(sdf.format(date));
        }
        stringBuffer.append(']');
        return stringBuffer.toString();
    }

    public static long[] parsePeriod(String arrstring) {
        long l;
        long l2;
        String[] arrstring2 = arrstring;
        if (arrstring.indexOf("period") >= 0) {
            arrstring2 = arrstring.substring(7);
        }
        arrstring = StringProcess.trim((String)arrstring2, Character.valueOf((char)'['), Character.valueOf((char)']')).split("[,\uff0c]");
        switch (arrstring.length) {
            default: {
                return null;
            }
            case 1: {
                long l3;
                try {
                    l3 = sdf.parse(arrstring[0]).getTime();
                }
                catch (ParseException var0_1) {
                    return null;
                }
                return new long[]{l3};
            }
            case 2: 
        }
        try {
            l2 = sdf.parse(arrstring[0]).getTime();
            l = sdf.parse(arrstring[1]).getTime();
        }
        catch (ParseException var0_2) {
            return null;
        }
        return new long[]{l2, l};
    }

    public int getAttrCounts() {
        return this.attrNames.size();
    }

    public List<String> getAttrNames() {
        return this.attrNames;
    }

    public List<Integer> getAttrType() {
        return this.attrType;
    }

    public String getOntologyName() {
        return this.ontologyName;
    }
}

