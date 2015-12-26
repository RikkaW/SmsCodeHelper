/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Character
 *  java.lang.Math
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 *  java.util.Collections
 *  java.util.Comparator
 *  java.util.HashMap
 *  java.util.LinkedList
 */
package com.xiaomi.common;

import com.xiaomi.common.ACAutomationable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ACAutomation {
    Node root;

    public ACAutomation() {
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public ACAutomation(List<String> var1_1) {
        super();
        this.root = new Node(0, '\u0000', this.root);
        var2_2 = 0;
        do {
            if (var2_2 >= var1_1.size()) break;
            this.insert((String)var1_1.get(var2_2), var2_2);
            ++var2_2;
        } while (true);
        var3_3 = new LinkedList();
        var3_3.add(this.root);
        block1 : do {
            if (var3_3.isEmpty()) {
                return;
            }
            var4_4 = (Node)var3_3.poll();
            var4_4.depth = var4_4.fa.depth + 1;
            if (var4_4 == this.root || var4_4.fa == this.root) {
                var4_4.fail = this.root;
            } else {
                var4_4.fail = var4_4.fa.fail;
                break;
            }
lbl21: // 3 sources:
            do {
                var1_1 = var4_4.fail.type == 1 ? var4_4.fail : var4_4.fail.lastPattern;
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
            if (var4_4.fail != this.root && !var4_4.fail.childs.containsKey((Object)Character.valueOf((char)var4_4.c))) ** GOTO lbl34
            if (!var4_4.fail.childs.containsKey((Object)Character.valueOf((char)var4_4.c))) ** GOTO lbl21
            var4_4.fail = var4_4.fail.childs.get((Object)Character.valueOf((char)var4_4.c));
            ** continue;
lbl34: // 1 sources:
            var4_4.fail = var4_4.fail.fail;
        } while (true);
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public ACAutomation(List<? extends ACAutomationable> var1_1, boolean var2_2) {
        super();
        this.root = new Node(0, '\u0000', this.root);
        var3_3 = 0;
        do {
            if (var3_3 >= var1_1.size()) break;
            this.insert(((ACAutomationable)var1_1.get(var3_3)).getWord(), var3_3);
            ++var3_3;
        } while (true);
        var4_4 = new LinkedList();
        var4_4.add(this.root);
        block1 : do {
            if (var4_4.isEmpty()) {
                return;
            }
            var5_5 = (Node)var4_4.poll();
            var5_5.depth = var5_5.fa.depth + 1;
            if (var5_5 == this.root || var5_5.fa == this.root) {
                var5_5.fail = this.root;
            } else {
                var5_5.fail = var5_5.fa.fail;
                break;
            }
lbl21: // 3 sources:
            do {
                var1_1 = var5_5.fail.type == 1 ? var5_5.fail : var5_5.fail.lastPattern;
                var5_5.lastPattern = var1_1;
                var1_1 = var5_5.childs.values().iterator();
                do {
                    if (!var1_1.hasNext()) continue block1;
                    var4_4.add(var1_1.next());
                } while (true);
                break;
            } while (true);
            break;
        } while (true);
        do {
            if (var5_5.fail != this.root && !var5_5.fail.childs.containsKey((Object)Character.valueOf((char)var5_5.c))) ** GOTO lbl34
            if (!var5_5.fail.childs.containsKey((Object)Character.valueOf((char)var5_5.c))) ** GOTO lbl21
            var5_5.fail = var5_5.fail.childs.get((Object)Character.valueOf((char)var5_5.c));
            ** continue;
lbl34: // 1 sources:
            var5_5.fail = var5_5.fail.fail;
        } while (true);
    }

    private void insert(String string2, int n) {
        Node node = this.root;
        int n2 = 0;
        do {
            if (n2 >= string2.length()) {
                node.type = 1;
                node.patternId = n;
                return;
            }
            char c2 = string2.charAt(n2);
            if (!node.childs.containsKey((Object)Character.valueOf((char)c2))) {
                node.childs.put(Character.valueOf((char)c2), new Node(0, c2, node));
            }
            node = node.childs.get((Object)Character.valueOf((char)c2));
            ++n2;
        } while (true);
    }

    /*
     * Enabled aggressive block sorting
     */
    protected List<int[]> filterLeftMost(List<int[]> list) {
        ArrayList arrayList = new ArrayList();
        if (list.size() == 0) {
            return arrayList;
        }
        int n = 0;
        int n2 = Integer.MAX_VALUE;
        while (n < list.size()) {
            int n3 = list.get(n)[1];
            if (n2 >= n3) {
                if (n3 < n2) {
                    arrayList.clear();
                }
                n2 = Math.min((int)n3, (int)n2);
                arrayList.add(list.get(n));
            }
            ++n;
        }
        return arrayList;
    }

    /*
     * Enabled aggressive block sorting
     */
    protected int[] filterLongest(List<int[]> list) {
        if (list.size() == 0) {
            return null;
        }
        int n = 0;
        int n2 = 0;
        while (n2 < list.size()) {
            if (list.get(n2)[2] - list.get(n2)[1] + 1 > list.get(n)[2] - list.get(n)[1] + 1) {
                n = n2;
            }
            ++n2;
        }
        return list.get(n);
    }

    /*
     * Enabled aggressive block sorting
     */
    public List<int[]> filterNoOverlay(List<int[]> list) {
        ArrayList arrayList = new ArrayList();
        if (list.size() == 0) {
            return arrayList;
        }
        Collections.sort(list, (Comparator)new Comparator<int[]>(){

            public int compare(int[] arrn, int[] arrn2) {
                if (arrn[1] != arrn2[1]) {
                    return arrn[1] - arrn2[1];
                }
                return arrn2[2] - arrn[2];
            }
        });
        int n = 0;
        while (n < list.size()) {
            if (arrayList.size() <= 0 || list.get(n)[1] > ((int[])arrayList.get(arrayList.size() - 1))[2]) {
                arrayList.add(list.get(n));
            }
            ++n;
        }
        return arrayList;
    }

    public List<int[]> find(String string2) {
        return this.filterNoOverlay(this.match(string2, 0));
    }

    public List<int[]> find(String string2, int n) {
        return this.filterNoOverlay(this.match(string2, n));
    }

    public int[] findFirst(String string2, int n) {
        return this.filterLongest(this.findFirstAll(string2, n));
    }

    public int[] findFirst(String string2, int n, int n2) {
        return this.filterLongest(this.findFirstAll(string2, n, n2));
    }

    public List<int[]> findFirstAll(String object, int n) {
        object = this.filterLeftMost(this.match((String)object, n));
        Collections.sort((List)object, (Comparator)new Comparator<int[]>(){

            public int compare(int[] arrn, int[] arrn2) {
                return arrn2[2] - arrn[2];
            }
        });
        return object;
    }

    public List<int[]> findFirstAll(String object, int n, int n2) {
        object = this.filterLeftMost(this.match((String)object, n, n2));
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
    public List<int[]> match(String var1_1, int var2_2) {
        var6_3 = new ArrayList();
        if (var1_1.length() <= var2_2) return var6_3;
        if (var2_2 < 0) {
            return var6_3;
        }
        var4_4 = this.root;
        block0 : do {
            if (var2_2 >= var1_1.length()) return var6_3;
            var3_5 = var1_1.charAt(var2_2);
            do {
                if (!var4_4.isRoot() && !var4_4.childs.containsKey((Object)Character.valueOf((char)var3_5))) ** GOTO lbl15
                var5_6 = var4_4;
                if (var4_4.childs.containsKey((Object)Character.valueOf((char)var3_5))) {
                    break;
                }
                ** GOTO lbl-1000
lbl15: // 1 sources:
                var4_4 = var4_4.fail;
            } while (true);
            var4_4 = var5_6 = var4_4.childs.get((Object)Character.valueOf((char)var3_5));
            do {
                if (var4_4 == this.root) lbl-1000: // 2 sources:
                {
                    ++var2_2;
                    var4_4 = var5_6;
                    continue block0;
                }
                if (var4_4.type != 0) {
                    var6_3.add(new int[]{var4_4.patternId, var2_2 - var4_4.depth + 1, var2_2});
                }
                var4_4 = var4_4.lastPattern;
            } while (true);
            break;
        } while (true);
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public List<int[]> match(String var1_1, int var2_2, int var3_3) {
        var7_4 = new ArrayList();
        if (var1_1.length() <= var2_2) return var7_4;
        if (var2_2 < 0) {
            return var7_4;
        }
        var5_5 = this.root;
        block0 : do {
            if (var2_2 > var3_3) return var7_4;
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
                if (var5_5.type != 0) {
                    var7_4.add(new int[]{var5_5.patternId, var2_2 - var5_5.depth + 1, var2_2});
                }
                var5_5 = var5_5.lastPattern;
            } while (true);
            break;
        } while (true);
    }

    /*
     * Enabled aggressive block sorting
     */
    public List<int[]> startWith(String string2, int n) {
        Node node = this.root;
        ArrayList arrayList = new ArrayList();
        int n2 = n;
        while (n2 < string2.length() && node.childs.containsKey((Object)Character.valueOf((char)string2.charAt(n2)))) {
            node = node.childs.get((Object)Character.valueOf((char)string2.charAt(n2)));
            if (node.type == 1) {
                arrayList.add(new int[]{node.patternId, n, n + n2});
            }
            ++n2;
        }
        return arrayList;
    }

    class Node {
        char c;
        Map<Character, Node> childs;
        int depth;
        Node fa;
        Node fail;
        Node lastPattern;
        int patternId;
        int type;

        Node(int n, char c2, Node node) {
            this.type = n;
            this.depth = -1;
            this.c = c2;
            this.childs = new HashMap();
            ACAutomation.this = node;
            if (node == null) {
                ACAutomation.this = this;
            }
            this.fa = ACAutomation.this;
            this.fail = this;
            this.lastPattern = this;
        }

        public boolean isRoot() {
            if (this.fa == this) {
                return true;
            }
            return false;
        }
    }

}

