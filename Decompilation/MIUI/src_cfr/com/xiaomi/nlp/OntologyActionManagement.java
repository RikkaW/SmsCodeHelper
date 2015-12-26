/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.StringBuffer
 *  java.lang.System
 *  java.util.ArrayList
 *  java.util.HashMap
 *  java.util.HashSet
 */
package com.xiaomi.nlp;

import com.xiaomi.common.FileOperator;
import com.xiaomi.common.Log;
import com.xiaomi.nlp.OntologyAction;
import com.xiaomi.nlp.OntologyButton;
import com.xiaomi.nlp.OntologyTaskFrame;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class OntologyActionManagement {
    private static Object actionExtendLock = new Object();
    private HashMap<Integer, List<Integer>> actionID2actionOntologyIndexs = new HashMap();
    private List<OntologyAction> actionOntology = new ArrayList();
    private boolean changedAction = false;
    private String dir = null;
    private Set<Integer> updatedActions;

    public int getActionCount(int n, int n2) {
        OntologyAction ontologyAction = this.getOntology(n);
        if (ontologyAction != null) {
            return ontologyAction.getActionCount(n2);
        }
        return 0;
    }

    public String getBtnAction(int n, int n2, int n3) {
        OntologyAction ontologyAction = this.getOntology(n);
        if (ontologyAction != null) {
            return ontologyAction.getBtnAction(n2, n3);
        }
        return "";
    }

    public int getBtnNumber(int n) {
        OntologyAction ontologyAction = this.getOntology(n);
        if (ontologyAction != null) {
            return ontologyAction.getBtnNumber();
        }
        return 0;
    }

    public String getBtnTitle(int n, int n2) {
        OntologyAction ontologyAction = this.getOntology(n);
        if (ontologyAction != null) {
            return ontologyAction.getBtnTitle(n2);
        }
        return "";
    }

    public OntologyAction getOntology(int n) {
        Integer n2;
        Object object = (List)this.actionID2actionOntologyIndexs.get((Object)n);
        if (object == null || object.size() == 0) {
            return null;
        }
        object = object.iterator();
        do {
            if (object.hasNext()) continue;
            return null;
        } while (!this.actionOntology.get(n2 = (Integer)object.next()).matchPeriod());
        return this.actionOntology.get(n2);
    }

    /*
     * Enabled aggressive block sorting
     */
    public boolean loadOntology(String string2) {
        boolean bl = false;
        this.dir = string2;
        int n = 0;
        do {
            String string3;
            StringBuilder stringBuilder;
            if (!this.loadSingleOntology((stringBuilder = new StringBuilder(String.valueOf((Object)string2)).append("/Action")).append(string3 = n != 0 ? String.valueOf((int)n) : "").append(".Ontology").toString(), false)) {
                if (!this.loadSingleOntology(String.valueOf((Object)string2) + "/ActionExtend.Ontology", true)) {
                    Log.i("OntologyActionManagement", "Load ActionExtend Ontology Error!!!");
                }
                if (this.actionOntology.size() <= 0) return bl;
                return true;
            }
            ++n;
        } while (true);
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    public boolean loadSingleOntology(String var1_1, boolean var2_3) {
        block33 : {
            try {
                if (FileOperator.fileExist(var1_1) == false) return false;
                if (!var2_3) break block33;
                var15_4 = OntologyActionManagement.actionExtendLock;
                // MONITORENTER : var15_4
            }
            catch (IOException var1_2) {
                Log.i("OntologyActionManagement", "Load Action Ontology Error!!!" + var1_2.getMessage());
                return false;
            }
            var1_1 = FileOperator.readFile(var1_1);
            // MONITOREXIT : var15_4
            ** GOTO lbl15
        }
        var1_1 = FileOperator.readFile(var1_1);
lbl15: // 2 sources:
        if (var2_3 && this.updatedActions == null) {
            this.updatedActions = new HashSet();
        }
        var5_5 = 0;
        block7 : do {
            if (var5_5 >= var1_1.size()) {
                return true;
            }
            var15_4 = var1_1.get(var5_5).trim();
            if (!var15_4.startsWith("defframe")) ** GOTO lbl67
            var15_4 = (var15_4 = var15_4.split("(//)+")[0].split("[\\t\\s]+")).length < 2 ? "actionframe" : var15_4[1].trim().toLowerCase();
            var18_16 = new ArrayList();
            var3_6 = var5_5 + 1;
            var4_7 = -1;
            var9_11 = -1;
            var11_12 = -1;
            block8 : do {
                if (var3_6 >= var1_1.size()) ** GOTO lbl67
                var16_14 = var1_1.get(var3_6).trim();
                if (!var16_14.startsWith("{")) ** GOTO lbl41
                var6_8 = var4_7;
                var13_13 = var11_12;
                var4_7 = var3_6;
                var11_12 = var9_11;
                var9_11 = var13_13;
                var3_6 = var6_8;
                ** GOTO lbl109
lbl41: // 1 sources:
                if (!var16_14.startsWith("//")) ** GOTO lbl49
                var6_8 = var3_6;
                var3_6 = var4_7;
                var13_13 = var9_11;
                var9_11 = var11_12;
                var11_12 = var13_13;
                var4_7 = var6_8;
                ** GOTO lbl109
lbl49: // 1 sources:
                if (!var16_14.startsWith("}")) ** GOTO lbl69
                if (var11_12 < 0) ** GOTO lbl-1000
                var5_5 = var3_6;
                if (var11_12 >= System.currentTimeMillis()) lbl-1000: // 2 sources:
                {
                    var16_14 = var17_15 = (List)this.actionID2actionOntologyIndexs.get((Object)var4_7);
                    if (var17_15 == null) {
                        var16_14 = new ArrayList(1);
                    }
                    var16_14.add((int)this.actionOntology.size());
                    this.actionID2actionOntologyIndexs.put((Object)var4_7, (Object)var16_14);
                    if (var2_3) {
                        this.updatedActions.add(var4_7);
                    }
                    var15_4 = new OntologyAction((String)var15_4, var4_7, (List<OntologyButton>)var18_16);
                    if (var9_11 >= 0) {
                        var15_4.setStartPeriodOfValidity((long)var9_11);
                    }
                    if (var11_12 >= 0) {
                        var15_4.setEndPeriodOfValidity((long)var11_12);
                    }
                    this.actionOntology.add((OntologyAction)var15_4);
                    var5_5 = var3_6;
                }
lbl67: // 5 sources:
                ++var5_5;
                continue block7;
lbl69: // 1 sources:
                if (var16_14.toLowerCase().startsWith("actionid")) {
                    if ((var16_14 = var16_14.split("[:\uff1a]", 2)).length != 2) {
                        Log.i("OntologyActionManagement", "loadOntology Error:" + var1_1.get(var3_6));
                        var6_8 = var3_6;
                        var3_6 = var4_7;
                        var13_13 = var9_11;
                        var9_11 = var11_12;
                        var11_12 = var13_13;
                        var4_7 = var6_8;
                    } else {
                        var6_8 = Integer.parseInt((String)var16_14[1]);
                        var4_7 = var3_6;
                        var3_6 = var6_8;
                        var13_13 = var9_11;
                        var9_11 = var11_12;
                        var11_12 = var13_13;
                    }
                } else {
                    if (var16_14.startsWith("defbutton") && var4_7 >= 0) break block7;
                    if (var16_14.startsWith("period") && (var16_14 = (String[])OntologyTaskFrame.parsePeriod((String)var16_14)) != null) {
                        if (var16_14.length == 1) {
                            var11_12 = var16_14[0];
                            var6_8 = var3_6;
                            var3_6 = var4_7;
                            var13_13 = var9_11;
                            var9_11 = var11_12;
                            var11_12 = var13_13;
                            var4_7 = var6_8;
                        } else {
                            var11_12 = var16_14[0];
                            var9_11 = var16_14[1];
                            var6_8 = var3_6;
                            var3_6 = var4_7;
                            var4_7 = var6_8;
                        }
                    } else {
                        var6_8 = var3_6;
                        var3_6 = var4_7;
                        var13_13 = var9_11;
                        var9_11 = var11_12;
                        var11_12 = var13_13;
                        var4_7 = var6_8;
                    }
                }
lbl109: // 9 sources:
                do {
                    var6_8 = var4_7 + 1;
                    var4_7 = var3_6;
                    var13_13 = var11_12;
                    var3_6 = var6_8;
                    var11_12 = var9_11;
                    var9_11 = var13_13;
                    continue block8;
                    break;
                } while (true);
                break;
            } while (true);
            break;
        } while (true);
        var16_14 = new HashMap();
        var7_9 = 0;
        var6_8 = var3_6 + 1;
        do {
            if (var6_8 < var1_1.size()) ** GOTO lbl129
            var6_8 = var3_6;
            var3_6 = var4_7;
            var13_13 = var9_11;
            var9_11 = var11_12;
            var11_12 = var13_13;
            var4_7 = var6_8;
            ** GOTO lbl109
lbl129: // 1 sources:
            var17_15 = var1_1.get(var6_8).trim();
            if (var17_15.startsWith("{")) {
                var8_10 = var7_9;
            } else {
                var8_10 = var7_9;
                if (!var17_15.startsWith("//")) {
                    if (var17_15.startsWith("}")) {
                        var18_16.add(new OntologyButton(var7_9, (HashMap<String, String>)var16_14));
                        var3_6 = var4_7;
                        var13_13 = var9_11;
                        var9_11 = var11_12;
                        var11_12 = var13_13;
                        var4_7 = var6_8;
                        ** continue;
                    }
                    if ((var17_15 = var17_15.split("[:\uff1a]", 2)).length != 2) {
                        Log.i("OntologyActionManagement", "loadOntology Error:" + var1_1.get(var3_6));
                        var8_10 = var7_9;
                    } else {
                        var8_10 = var7_9;
                        if (var17_15[0].startsWith("action")) {
                            var17_15[0] = "action" + var7_9;
                            var8_10 = var7_9 + 1;
                        }
                        var16_14.put((Object)var17_15[0], (Object)var17_15[1]);
                    }
                }
            }
            ++var6_8;
            var7_9 = var8_10;
        } while (true);
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        Iterator<OntologyAction> iterator = this.actionOntology.iterator();
        while (iterator.hasNext()) {
            stringBuffer.append(iterator.next().toString()).append("\r\n");
        }
        return stringBuffer.toString();
    }
}

