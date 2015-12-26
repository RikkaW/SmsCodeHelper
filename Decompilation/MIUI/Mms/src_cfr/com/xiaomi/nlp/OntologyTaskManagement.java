/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Character
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.StringBuffer
 *  java.util.ArrayList
 *  java.util.HashMap
 */
package com.xiaomi.nlp;

import com.xiaomi.common.FileOperator;
import com.xiaomi.common.Log;
import com.xiaomi.common.StringProcess;
import com.xiaomi.nlp.ActionIdPeriod;
import com.xiaomi.nlp.Ontology;
import com.xiaomi.nlp.OntologyTaskFrame;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class OntologyTaskManagement {
    private List<OntologyTaskFrame> ontologyFrame = new ArrayList();
    private HashMap<String, List<Integer>> ontologyFrameAttrs2FramIndex = new HashMap();
    private HashMap<String, Integer> ontologyName2FrameIndex = new HashMap();
    private int ontologySize = 0;

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    private boolean loadSingleOntology(String var1_1) {
        try {
            var22_3 = FileOperator.readFile((String)var1_1);
            var6_4 = 0;
        }
        catch (IOException var1_2) {
            return false;
        }
        block4 : do {
            if (var6_4 >= var22_3.size()) {
                return true;
            }
            var1_1 = var22_3.get(var6_4).trim().toLowerCase();
            var2_5 = var6_4;
            if (!var1_1.startsWith("defframe")) ** GOTO lbl274
            if ((var1_1 = var1_1.split("(//)+")[0].split("[\\t\\s]+")).length < 2) {
                Log.i("OntologyTaskManagement", "loadOntology Error:" + var22_3.get(var6_4));
                return false;
            }
            var23_21 = var1_1[1].trim().toLowerCase();
            var24_22 = new ArrayList();
            var25_23 = new ArrayList();
            var26_24 = new ArrayList(1);
            var2_5 = -1;
            var4_7 = -1;
            var3_6 = -1;
            var5_8 = -1;
            var1_1 = null;
            var20_18 = null;
            var12_14 = -1;
            var14_15 = -1;
            var7_9 = var6_4 + 1;
            do {
                if (var7_9 >= var22_3.size()) {
                    var2_5 = var6_4;
                    break;
                }
                var21_19 = StringProcess.trimStart(StringProcess.trimEnd(var22_3.get(var7_9).toLowerCase(), new Character[]{Character.valueOf((char)' '), Character.valueOf((char)'\t')}), new Character[]{Character.valueOf((char)' '), Character.valueOf((char)'\t')});
                if (!var21_19.equals((Object)"")) ** GOTO lbl45
                var16_16 = var14_15;
                var21_19 = var20_18;
                var8_10 = var5_8;
                var5_8 = var2_5;
                var2_5 = var8_10;
                var20_18 = var1_1;
                var1_1 = var21_19;
                var14_15 = var12_14;
                var12_14 = var16_16;
                ** GOTO lbl262
lbl45: // 1 sources:
                if (!var21_19.startsWith("{")) ** GOTO lbl56
                var16_16 = var12_14;
                var21_19 = var1_1;
                var8_10 = var2_5;
                var12_14 = var14_15;
                var14_15 = var16_16;
                var1_1 = var20_18;
                var20_18 = var21_19;
                var2_5 = var5_8;
                var5_8 = var8_10;
                ** GOTO lbl262
lbl56: // 1 sources:
                if (!var21_19.startsWith("//")) ** GOTO lbl67
                var16_16 = var12_14;
                var21_19 = var1_1;
                var8_10 = var2_5;
                var12_14 = var14_15;
                var14_15 = var16_16;
                var1_1 = var20_18;
                var20_18 = var21_19;
                var2_5 = var5_8;
                var5_8 = var8_10;
                ** GOTO lbl262
lbl67: // 1 sources:
                if (var21_19.startsWith("}")) {
                    var21_19 = new OntologyTaskFrame((List<ActionIdPeriod>)var26_24, var23_21, (List<String>)var24_22, (List<Integer>)var25_23);
                    var6_4 = this.ontologySize;
                    this.ontologySize = var6_4 + 1;
                    var21_19.setPriority(var6_4);
                    if (var2_5 >= 0) {
                        var21_19.setOperateID(var2_5);
                    }
                    if (var4_7 >= 0) {
                        var21_19.setSummation(var4_7);
                    }
                    if (var12_14 >= 0) {
                        var21_19.setStartPeriodOfValidity((long)var12_14);
                    }
                    if (var14_15 >= 0) {
                        var21_19.setEndPeriodOfValidity((long)var14_15);
                    }
                    if (var1_1 != null) {
                        var21_19.setVersion((String)var1_1);
                    }
                    if (var20_18 != null) {
                        var21_19.setArea((String)var20_18);
                    }
                    if (var3_6 >= 0) {
                        var21_19.setPlatform(var3_6);
                    }
                    if (var5_8 >= 0) {
                        var21_19.setSystemLevel(var5_8);
                        break block4;
                    }
                    break block4;
                }
                var27_25 = var21_19.split("(:|\uff1a|(//))+");
                if (var27_25.length >= 2) ** GOTO lbl102
                var16_16 = var12_14;
                var21_19 = var1_1;
                var8_10 = var2_5;
                var12_14 = var14_15;
                var14_15 = var16_16;
                var1_1 = var20_18;
                var20_18 = var21_19;
                var2_5 = var5_8;
                var5_8 = var8_10;
                ** GOTO lbl262
lbl102: // 1 sources:
                if (!var27_25[0].equals((Object)"actionid")) ** GOTO lbl154
                var11_13 = Integer.valueOf((String)var27_25[1].trim());
                if (var27_25.length > 2) {
                    var8_10 = var9_11 = var21_19.indexOf(":", 10);
                    if (var9_11 < 0) {
                        var8_10 = Integer.MAX_VALUE;
                    }
                    var9_11 = var10_12 = var21_19.indexOf("\uff1a", 10);
                    if (var10_12 < 0) {
                        var9_11 = Integer.MAX_VALUE;
                    }
                    var10_12 = var8_10;
                    if (var8_10 > var9_11) {
                        var10_12 = var9_11;
                    }
                    var16_16 = 1104508800000L;
                    if ((var21_19 = (String[])OntologyTaskFrame.parsePeriod(var21_19.substring(var10_12 + 1))) != null) {
                        if (var21_19.length == 1) {
                            var18_17 = var21_19[0];
                        } else {
                            var16_16 = var21_19[0];
                            var18_17 = var21_19[1];
                        }
                        var26_24.add(new ActionIdPeriod(var11_13, (long)var16_16, (long)var18_17));
                        var16_16 = var12_14;
                        var21_19 = var1_1;
                        var8_10 = var2_5;
                        var12_14 = var14_15;
                        var14_15 = var16_16;
                        var1_1 = var20_18;
                        var20_18 = var21_19;
                        var2_5 = var5_8;
                        var5_8 = var8_10;
                    } else {
                        var26_24.add(new ActionIdPeriod(var11_13));
                        var16_16 = var12_14;
                        var21_19 = var1_1;
                        var8_10 = var2_5;
                        var12_14 = var14_15;
                        var14_15 = var16_16;
                        var1_1 = var20_18;
                        var20_18 = var21_19;
                        var2_5 = var5_8;
                        var5_8 = var8_10;
                    }
                } else {
                    var26_24.add(new ActionIdPeriod(var11_13));
                    var16_16 = var12_14;
                    var21_19 = var1_1;
                    var8_10 = var2_5;
                    var12_14 = var14_15;
                    var14_15 = var16_16;
                    var1_1 = var20_18;
                    var20_18 = var21_19;
                    var2_5 = var5_8;
                    var5_8 = var8_10;
                }
                ** GOTO lbl262
lbl154: // 1 sources:
                if (!var27_25[0].equals((Object)"operateid")) ** GOTO lbl165
                var8_10 = Integer.valueOf((String)var27_25[1].trim());
                var16_16 = var12_14;
                var21_19 = var1_1;
                var12_14 = var14_15;
                var2_5 = var5_8;
                var14_15 = var16_16;
                var1_1 = var20_18;
                var20_18 = var21_19;
                var5_8 = var8_10;
                ** GOTO lbl262
lbl165: // 1 sources:
                if (!var27_25[0].equals((Object)"summation")) ** GOTO lbl177
                var4_7 = Integer.valueOf((String)var27_25[1].trim());
                var16_16 = var12_14;
                var21_19 = var1_1;
                var8_10 = var2_5;
                var12_14 = var14_15;
                var2_5 = var5_8;
                var14_15 = var16_16;
                var1_1 = var20_18;
                var20_18 = var21_19;
                var5_8 = var8_10;
                ** GOTO lbl262
lbl177: // 1 sources:
                if (!var27_25[0].equals((Object)"platform")) ** GOTO lbl189
                var3_6 = Integer.valueOf((String)var27_25[1].trim());
                var16_16 = var12_14;
                var21_19 = var1_1;
                var8_10 = var2_5;
                var12_14 = var14_15;
                var2_5 = var5_8;
                var14_15 = var16_16;
                var1_1 = var20_18;
                var20_18 = var21_19;
                var5_8 = var8_10;
                ** GOTO lbl262
lbl189: // 1 sources:
                if (!var27_25[0].equals((Object)"version")) ** GOTO lbl200
                var21_19 = var27_25[1].trim();
                var16_16 = var12_14;
                var1_1 = var20_18;
                var20_18 = var21_19;
                var12_14 = var14_15;
                var8_10 = var2_5;
                var2_5 = var5_8;
                var14_15 = var16_16;
                var5_8 = var8_10;
                ** GOTO lbl262
lbl200: // 1 sources:
                if (!var27_25[0].equals((Object)"area")) ** GOTO lbl212
                var20_18 = var27_25[1].trim();
                var16_16 = var12_14;
                var21_19 = var1_1;
                var12_14 = var14_15;
                var8_10 = var2_5;
                var2_5 = var5_8;
                var14_15 = var16_16;
                var1_1 = var20_18;
                var20_18 = var21_19;
                var5_8 = var8_10;
                ** GOTO lbl262
lbl212: // 1 sources:
                if (!var27_25[0].equals((Object)"systemLevel")) ** GOTO lbl223
                var8_10 = Integer.valueOf((String)var27_25[1].trim());
                var16_16 = var12_14;
                var21_19 = var1_1;
                var5_8 = var2_5;
                var2_5 = var8_10;
                var12_14 = var14_15;
                var14_15 = var16_16;
                var1_1 = var20_18;
                var20_18 = var21_19;
                ** GOTO lbl262
lbl223: // 1 sources:
                if (!var27_25[0].equals((Object)"period")) ** GOTO lbl245
                if ((var21_19 = (String[])OntologyTaskFrame.parsePeriod((String)var21_19)) == null) ** GOTO lbl253
                if (var21_19.length == 1) {
                    var16_16 = var21_19[0];
                    var14_15 = var12_14;
                    var21_19 = var1_1;
                    var8_10 = var2_5;
                    var12_14 = var16_16;
                    var1_1 = var20_18;
                    var20_18 = var21_19;
                    var2_5 = var5_8;
                    var5_8 = var8_10;
                } else {
                    var14_15 = var21_19[0];
                    var12_14 = var21_19[1];
                    var21_19 = var1_1;
                    var8_10 = var2_5;
                    var1_1 = var20_18;
                    var20_18 = var21_19;
                    var2_5 = var5_8;
                    var5_8 = var8_10;
                }
                ** GOTO lbl262
lbl245: // 1 sources:
                var24_22.add(var27_25[0].trim());
                var8_10 = -1;
                try {
                    var8_10 = var9_11 = Integer.valueOf((String)var27_25[1].trim()).intValue();
                }
                catch (Exception var21_20) {}
                var25_23.add((int)var8_10);
lbl253: // 2 sources:
                var16_16 = var12_14;
                var21_19 = var1_1;
                var8_10 = var2_5;
                var12_14 = var14_15;
                var14_15 = var16_16;
                var1_1 = var20_18;
                var20_18 = var21_19;
                var2_5 = var5_8;
                var5_8 = var8_10;
lbl262: // 16 sources:
                var8_10 = var7_9 + 1;
                var21_19 = var20_18;
                var7_9 = var5_8;
                var16_16 = var14_15;
                var14_15 = var12_14;
                var12_14 = var16_16;
                var20_18 = var1_1;
                var1_1 = var21_19;
                var5_8 = var2_5;
                var2_5 = var7_9;
                var7_9 = var8_10;
            } while (true);
lbl274: // 3 sources:
            do {
                var6_4 = var2_5 + 1;
                continue block4;
                break;
            } while (true);
            break;
        } while (true);
        this.ontologyFrame.add((OntologyTaskFrame)var21_19);
        var1_1 = var24_22.iterator();
        do {
            if (!var1_1.hasNext()) {
                this.ontologyName2FrameIndex.put((Object)var23_21, (Object)(this.ontologyFrame.size() - 1));
                var2_5 = var7_9;
                ** continue;
            }
            var20_18 = (String)var1_1.next();
            if (!this.ontologyFrameAttrs2FramIndex.containsKey(var20_18)) {
                this.ontologyFrameAttrs2FramIndex.put(var20_18, (Object)new ArrayList());
            }
            ((List)this.ontologyFrameAttrs2FramIndex.get(var20_18)).add((int)(this.ontologyFrame.size() - 1));
        } while (true);
    }

    public Ontology getOntologyByOntologyName(String string2) {
        if (this.ontologyName2FrameIndex.containsKey((Object)string2)) {
            return this.ontologyFrame.get((Integer)this.ontologyName2FrameIndex.get((Object)string2));
        }
        return null;
    }

    /*
     * Enabled aggressive block sorting
     */
    public boolean loadOntology(String string2) {
        boolean bl = false;
        int n = 0;
        do {
            String string3;
            StringBuilder stringBuilder;
            if (!this.loadSingleOntology((stringBuilder = new StringBuilder(String.valueOf((Object)string2)).append("/Task")).append(string3 = n != 0 ? String.valueOf((int)n) : "").append(".Ontology").toString())) {
                if (this.ontologyFrame.size() <= 0) return bl;
                return true;
            }
            ++n;
        } while (true);
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        Iterator<OntologyTaskFrame> iterator = this.ontologyFrame.iterator();
        while (iterator.hasNext()) {
            stringBuffer.append(iterator.next().toString()).append("\r\n");
        }
        return stringBuffer.toString();
    }
}

