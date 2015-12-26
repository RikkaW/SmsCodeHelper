/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Character
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 *  java.util.Collections
 *  java.util.Comparator
 *  java.util.LinkedList
 */
package com.xiaomi.common;

import com.xiaomi.common.ACAutomation;
import com.xiaomi.common.ConstraintACAutomationable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ConstraintACAutomation<T extends ConstraintACAutomationable>
extends ACAutomation {
    public List<ACAutomation.Node> InnerNode;

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public ConstraintACAutomation(List<T> var1_1) {
        super();
        this.InnerNode = new ArrayList(var1_1.size());
        this.root = new ACAutomation.Node(0, '\u0000', this.root);
        var2_2 = 0;
        do {
            if (var2_2 >= var1_1.size()) break;
            this.insert((ConstraintACAutomationable)var1_1.get(var2_2), var2_2);
            ++var2_2;
        } while (true);
        var3_3 = new LinkedList();
        var3_3.add(this.root);
        block1 : do {
            if (var3_3.isEmpty()) {
                return;
            }
            var4_4 = (ACAutomation.Node)var3_3.poll();
            var4_4.depth = var4_4.fa.depth + 1;
            if (var4_4 == this.root || var4_4.fa == this.root) {
                var4_4.fail = this.root;
            } else {
                var4_4.fail = var4_4.fa.fail;
                break;
            }
lbl22: // 3 sources:
            do {
                var1_1 = var4_4.fail.type != 0 ? var4_4.fail : var4_4.fail.lastPattern;
                var4_4.lastPattern = var1_1;
                var1_1 = var4_4.childs.values().iterator();
                do {
                    if (!var1_1.hasNext()) continue block1;
                    var3_3.add(var1_1.next());
                } while (true);
                break;
            } while (true);
            break;
        } while (true);
        do {
            if (var4_4.fail != this.root && !var4_4.fail.childs.containsKey((Object)Character.valueOf((char)var4_4.c))) ** GOTO lbl35
            if (!var4_4.fail.childs.containsKey((Object)Character.valueOf((char)var4_4.c))) ** GOTO lbl22
            var4_4.fail = var4_4.fail.childs.get((Object)Character.valueOf((char)var4_4.c));
            ** continue;
lbl35: // 1 sources:
            var4_4.fail = var4_4.fail.fail;
        } while (true);
    }

    private void insert(T t, int n) {
        if (n != this.InnerNode.size()) {
            throw new RuntimeException("unconsistent in here");
        }
        ACAutomation.Node node = this.root;
        String string2 = t.getWord();
        int n2 = 0;
        do {
            if (n2 >= string2.length()) {
                node.type = t.getType();
                node.patternId = n;
                this.InnerNode.add(node);
                return;
            }
            char c2 = string2.charAt(n2);
            if (!node.childs.containsKey((Object)Character.valueOf((char)c2))) {
                node.childs.put(Character.valueOf((char)c2), new ACAutomation.Node(0, c2, node));
            }
            node = node.childs.get((Object)Character.valueOf((char)c2));
            ++n2;
        } while (true);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public int contains(String string2, int n) {
        int n2 = -1;
        ACAutomation.Node node = this.root;
        int n3 = 0;
        do {
            int n4;
            if (n3 >= string2.length()) {
                n4 = n2;
                if ((node.type & n) == 0) return n4;
                return node.patternId;
            }
            char c2 = string2.charAt(n3);
            n4 = n2;
            if (!node.childs.containsKey((Object)Character.valueOf((char)c2))) return n4;
            node = node.childs.get((Object)Character.valueOf((char)c2));
            ++n3;
        } while (true);
    }

    @Override
    public List<int[]> find(String string2, int n) {
        return this.filterNoOverlay(this.match(string2, 0, n));
    }

    public int[] findFirst(String string2, int n, int n2, int n3) {
        return this.filterLongest(this.findFirstAll(string2, n, n2, n3));
    }

    public List<int[]> findFirstAll(String object, int n, int n2, int n3) {
        object = this.filterLeftMost(this.match((String)object, n, n2, n3));
        Collections.sort((List)object, (Comparator)new Comparator<int[]>(){

            public int compare(int[] arrn, int[] arrn2) {
                return arrn2[2] - arrn[2];
            }
        });
        return object;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    @Override
    public List<int[]> match(String var1_1, int var2_2, int var3_3) {
        var7_4 = new ArrayList();
        if (var1_1.length() <= var2_2) return var7_4;
        if (var2_2 < 0) {
            return var7_4;
        }
        var5_5 = this.root;
        block0 : do {
            if (var2_2 >= var1_1.length()) return var7_4;
            var4_6 = var1_1.charAt(var2_2);
            do {
                if (!var5_5.isRoot() && !var5_5.childs.containsKey((Object)Character.valueOf((char)var4_6))) ** GOTO lbl15
                var6_7 = var5_5;
                if (var5_5.childs.containsKey((Object)Character.valueOf((char)var4_6))) {
                    break;
                }
                ** GOTO lbl-1000
lbl15: // 1 sources:
                var5_5 = var5_5.fail;
            } while (true);
            var5_5 = var6_7 = var5_5.childs.get((Object)Character.valueOf((char)var4_6));
            do {
                if (var5_5 == this.root) lbl-1000: // 2 sources:
                {
                    ++var2_2;
                    var5_5 = var6_7;
                    continue block0;
                }
                if ((var5_5.type & var3_3) != 0) {
                    var7_4.add(new int[]{var5_5.patternId, var2_2 - var5_5.depth + 1, var2_2});
                }
                var5_5 = var5_5.lastPattern;
            } while (true);
            break;
        } while (true);
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public List<int[]> match(String var1_1, int var2_2, int var3_3, int var4_4) {
        var8_5 = new ArrayList();
        if (var1_1.length() <= var2_2) return var8_5;
        if (var2_2 < 0) {
            return var8_5;
        }
        var6_6 = this.root;
        block0 : do {
            if (var2_2 >= var1_1.length()) return var8_5;
            if (var2_2 > var3_3) return var8_5;
            var5_7 = var1_1.charAt(var2_2);
            do {
                if (!var6_6.isRoot() && !var6_6.childs.containsKey((Object)Character.valueOf((char)var5_7))) ** GOTO lbl16
                var7_8 = var6_6;
                if (var6_6.childs.containsKey((Object)Character.valueOf((char)var5_7))) {
                    break;
                }
                ** GOTO lbl-1000
lbl16: // 1 sources:
                var6_6 = var6_6.fail;
            } while (true);
            var6_6 = var7_8 = var6_6.childs.get((Object)Character.valueOf((char)var5_7));
            do {
                if (var6_6 == this.root) lbl-1000: // 2 sources:
                {
                    ++var2_2;
                    var6_6 = var7_8;
                    continue block0;
                }
                if ((var6_6.type & var4_4) != 0) {
                    var8_5.add(new int[]{var6_6.patternId, var2_2 - var6_6.depth + 1, var2_2});
                }
                var6_6 = var6_6.lastPattern;
            } while (true);
            break;
        } while (true);
    }

    /*
     * Enabled aggressive block sorting
     */
    public List<int[]> startWith(String string2, int n, int n2) {
        ACAutomation.Node node = this.root;
        ArrayList arrayList = new ArrayList();
        int n3 = n;
        while (n3 < string2.length() && node.childs.containsKey((Object)Character.valueOf((char)string2.charAt(n3)))) {
            node = node.childs.get((Object)Character.valueOf((char)string2.charAt(n3)));
            if ((node.type & n2) != 0) {
                arrayList.add(new int[]{node.patternId, n, n + n3});
            }
            ++n3;
        }
        return arrayList;
    }

}

