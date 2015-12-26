/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.util.Map$Entry
 */
package com.google.common.collect;

import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.collect.AbstractIndexedListIterator;
import com.google.common.collect.Collections2;
import com.google.common.collect.Hashing;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableEntry;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.UnmodifiableIterator;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

final class RegularImmutableMap<K, V>
extends ImmutableMap<K, V> {
    private static final long serialVersionUID = 0;
    private final transient LinkedEntry<K, V>[] entries;
    private transient ImmutableSet<Map.Entry<K, V>> entrySet;
    private transient ImmutableSet<K> keySet;
    private final transient int keySetHashCode;
    private final transient int mask;
    private final transient LinkedEntry<K, V>[] table;
    private transient ImmutableCollection<V> values;

    /*
     * Enabled aggressive block sorting
     */
    /* varargs */ RegularImmutableMap(Map.Entry<?, ?> ... arrentry) {
        int n = arrentry.length;
        this.entries = this.createEntryArray(n);
        int n2 = RegularImmutableMap.chooseTableSize(n);
        this.table = this.createEntryArray(n2);
        this.mask = n2 - 1;
        int n3 = 0;
        n2 = 0;
        do {
            LinkedEntry<K, V> linkedEntry;
            if (n2 >= n) {
                this.keySetHashCode = n3;
                return;
            }
            Map.Entry entry = arrentry[n2];
            Object object = entry.getKey();
            int n4 = object.hashCode();
            n3 += n4;
            n4 = Hashing.smear(n4) & this.mask;
            this.table[n4] = entry = RegularImmutableMap.newLinkedEntry(object, entry.getValue(), linkedEntry);
            this.entries[n2] = entry;
            for (linkedEntry = this.table[n4]; linkedEntry != null; linkedEntry = linkedEntry.next()) {
                boolean bl = !object.equals(linkedEntry.getKey());
                Preconditions.checkArgument(bl, "duplicate key: %s", object);
            }
            ++n2;
        } while (true);
    }

    /*
     * Enabled aggressive block sorting
     */
    private static int chooseTableSize(int n) {
        int n2 = Integer.highestOneBit((int)n) << 1;
        boolean bl = n2 > 0;
        Preconditions.checkArgument(bl, "table too large: %s", n);
        return n2;
    }

    private LinkedEntry<K, V>[] createEntryArray(int n) {
        return new LinkedEntry[n];
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static <K, V> LinkedEntry<K, V> newLinkedEntry(K immutableEntry, V v, LinkedEntry<K, V> linkedEntry) {
        if (linkedEntry == null) {
            immutableEntry = new TerminalEntry<NonTerminalEntry<K, V>, V>((NonTerminalEntry<K, V>)immutableEntry, v);
            do {
                return immutableEntry;
                break;
            } while (true);
        }
        immutableEntry = new NonTerminalEntry<NonTerminalEntry<K, V>, V>((NonTerminalEntry<K, V>)immutableEntry, v, (LinkedEntry<NonTerminalEntry<K, V>, V>)linkedEntry);
        return immutableEntry;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public boolean containsValue(Object object) {
        if (object == null) {
            return false;
        }
        LinkedEntry<K, V>[] arrlinkedEntry = this.entries;
        int n = arrlinkedEntry.length;
        int n2 = 0;
        while (n2 < n) {
            if (arrlinkedEntry[n2].getValue().equals(object)) {
                return true;
            }
            ++n2;
        }
        return false;
    }

    @Override
    public ImmutableSet<Map.Entry<K, V>> entrySet() {
        ImmutableSet<Map.Entry<K, V>> immutableSet;
        ImmutableSet<Map.Entry<K, V>> immutableSet2 = immutableSet = this.entrySet;
        if (immutableSet == null) {
            this.entrySet = immutableSet2 = new EntrySet(this);
        }
        return immutableSet2;
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public V get(Object object) {
        if (object != null) {
            int n = Hashing.smear(object.hashCode());
            int n2 = this.mask;
            for (LinkedEntry<K, V> linkedEntry = this.table[n & n2]; linkedEntry != null; linkedEntry = linkedEntry.next()) {
                if (!object.equals(linkedEntry.getKey())) continue;
                return (V)linkedEntry.getValue();
            }
        }
        return null;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public ImmutableSet<K> keySet() {
        ImmutableSet<K> immutableSet;
        ImmutableSet<K> immutableSet2 = immutableSet = this.keySet;
        if (immutableSet == null) {
            this.keySet = immutableSet2 = new KeySet(this);
        }
        return immutableSet2;
    }

    @Override
    public int size() {
        return this.entries.length;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = Collections2.newStringBuilderForCollection(this.size()).append('{');
        Collections2.STANDARD_JOINER.appendTo(stringBuilder, this.entries);
        return stringBuilder.append('}').toString();
    }

    @Override
    public ImmutableCollection<V> values() {
        ImmutableCollection<V> immutableCollection;
        ImmutableCollection<V> immutableCollection2 = immutableCollection = this.values;
        if (immutableCollection == null) {
            this.values = immutableCollection2 = new Values(this);
        }
        return immutableCollection2;
    }

    private static class EntrySet<K, V>
    extends ImmutableSet.ArrayImmutableSet<Map.Entry<K, V>> {
        final transient RegularImmutableMap<K, V> map;

        EntrySet(RegularImmutableMap<K, V> regularImmutableMap) {
            super(regularImmutableMap.entries);
            this.map = regularImmutableMap;
        }

        @Override
        public boolean contains(Object object) {
            boolean bl;
            boolean bl2 = bl = false;
            if (object instanceof Map.Entry) {
                object = (Map.Entry)object;
                V v = this.map.get(object.getKey());
                bl2 = bl;
                if (v != null) {
                    bl2 = bl;
                    if (v.equals(object.getValue())) {
                        bl2 = true;
                    }
                }
            }
            return bl2;
        }
    }

    private static class KeySet<K, V>
    extends ImmutableSet.TransformedImmutableSet<Map.Entry<K, V>, K> {
        final RegularImmutableMap<K, V> map;

        KeySet(RegularImmutableMap<K, V> regularImmutableMap) {
            super(regularImmutableMap.entries, regularImmutableMap.keySetHashCode);
            this.map = regularImmutableMap;
        }

        @Override
        public boolean contains(Object object) {
            return this.map.containsKey(object);
        }

        @Override
        K transform(Map.Entry<K, V> entry) {
            return (K)entry.getKey();
        }
    }

    private static interface LinkedEntry<K, V>
    extends Map.Entry<K, V> {
        public LinkedEntry<K, V> next();
    }

    private static final class NonTerminalEntry<K, V>
    extends ImmutableEntry<K, V>
    implements LinkedEntry<K, V> {
        final LinkedEntry<K, V> next;

        NonTerminalEntry(K k, V v, LinkedEntry<K, V> linkedEntry) {
            super(k, v);
            this.next = linkedEntry;
        }

        @Override
        public LinkedEntry<K, V> next() {
            return this.next;
        }
    }

    private static final class TerminalEntry<K, V>
    extends ImmutableEntry<K, V>
    implements LinkedEntry<K, V> {
        TerminalEntry(K k, V v) {
            super(k, v);
        }

        @Override
        public LinkedEntry<K, V> next() {
            return null;
        }
    }

    private static class Values<V>
    extends ImmutableCollection<V> {
        final RegularImmutableMap<?, V> map;

        Values(RegularImmutableMap<?, V> regularImmutableMap) {
            this.map = regularImmutableMap;
        }

        @Override
        public boolean contains(Object object) {
            return this.map.containsValue(object);
        }

        @Override
        public UnmodifiableIterator<V> iterator() {
            return new AbstractIndexedListIterator<V>(this.map.entries.length){

                @Override
                protected V get(int n) {
                    return (V)Values.this.map.entries[n].getValue();
                }
            };
        }

        @Override
        public int size() {
            return this.map.entries.length;
        }

    }

}

