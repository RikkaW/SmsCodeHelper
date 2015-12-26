/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 *  java.util.LinkedList
 */
package com.xiaomi.channel.commonutils.stats;

import java.util.LinkedList;

public class Stats {
    private LinkedList<Item> statsQueue = new LinkedList();

    private void checkSize() {
        if (this.statsQueue.size() > 100) {
            this.statsQueue.removeFirst();
        }
    }

    public static Stats instance() {
        return sStats;
    }

    public int getCount() {
        synchronized (this) {
            int n = this.statsQueue.size();
            return n;
        }
    }

    public LinkedList<Item> getStats() {
        synchronized (this) {
            LinkedList<Item> linkedList = this.statsQueue;
            this.statsQueue = new LinkedList();
            return linkedList;
        }
    }

    public void stat(Object object) {
        synchronized (this) {
            this.statsQueue.add((Object)new Item(0, object));
            this.checkSize();
            return;
        }
    }

    public static class Item {
        private static final Stats sStats = new Stats();
        public String annotation;
        public int key;
        public Object obj;

        Item(int n, Object object) {
            this.key = n;
            this.obj = object;
        }
    }

}

