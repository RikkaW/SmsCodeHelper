/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.util.LinkedList
 */
package com.amap.api.mapcore2d;

import com.amap.api.mapcore2d.cy;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

class bw<T>
implements List<T> {
    private LinkedList<T> a = new LinkedList();

    bw() {
    }

    public void a(T t2) {
        synchronized (this) {
            this.add(t2);
            return;
        }
    }

    @Override
    public void add(int n2, T t2) {
        synchronized (this) {
            this.a.add(n2, t2);
            return;
        }
    }

    @Override
    public boolean add(T t2) {
        synchronized (this) {
            boolean bl2 = this.a.add(t2);
            return bl2;
        }
    }

    @Override
    public boolean addAll(int n2, Collection<? extends T> collection) {
        synchronized (this) {
            boolean bl2 = this.a.addAll(n2, collection);
            return bl2;
        }
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {
        synchronized (this) {
            boolean bl2 = this.a.addAll(collection);
            return bl2;
        }
    }

    @Override
    public void clear() {
        synchronized (this) {
            this.a.clear();
            return;
        }
    }

    @Override
    public boolean contains(Object object) {
        synchronized (this) {
            boolean bl2 = this.a.contains(object);
            return bl2;
        }
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        synchronized (this) {
            boolean bl2 = this.a.containsAll(collection);
            return bl2;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    public T get(int n2) {
        synchronized (this) {
            Object object = null;
            try {
                Object object2;
                object = object2 = this.a.get(n2);
            }
            catch (Exception var3_5) {
                cy.a(var3_5, "SyncList", "get");
                return (T)object;
            }
            finally {
            }
            do {
                return (T)object;
                break;
            } while (true);
        }
    }

    @Override
    public int indexOf(Object object) {
        synchronized (this) {
            int n2 = this.a.indexOf(object);
            return n2;
        }
    }

    @Override
    public boolean isEmpty() {
        synchronized (this) {
            boolean bl2 = this.a.isEmpty();
            return bl2;
        }
    }

    @Override
    public Iterator<T> iterator() {
        synchronized (this) {
            ListIterator listIterator = this.a.listIterator();
            return listIterator;
        }
    }

    @Override
    public int lastIndexOf(Object object) {
        synchronized (this) {
            int n2 = this.a.lastIndexOf(object);
            return n2;
        }
    }

    @Override
    public ListIterator<T> listIterator() {
        synchronized (this) {
            ListIterator listIterator = this.a.listIterator();
            return listIterator;
        }
    }

    @Override
    public ListIterator<T> listIterator(int n2) {
        synchronized (this) {
            ListIterator listIterator = this.a.listIterator(n2);
            return listIterator;
        }
    }

    @Override
    public T remove(int n2) {
        synchronized (this) {
            Object object = this.a.remove(n2);
            return (T)object;
        }
    }

    @Override
    public boolean remove(Object object) {
        synchronized (this) {
            boolean bl2 = this.a.remove(object);
            return bl2;
        }
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        synchronized (this) {
            boolean bl2 = this.a.removeAll(collection);
            return bl2;
        }
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        synchronized (this) {
            boolean bl2 = this.a.retainAll(collection);
            return bl2;
        }
    }

    @Override
    public T set(int n2, T object) {
        synchronized (this) {
            object = this.a.set(n2, object);
            return (T)object;
        }
    }

    @Override
    public int size() {
        synchronized (this) {
            int n2 = this.a.size();
            return n2;
        }
    }

    @Override
    public List<T> subList(int n2, int n3) {
        synchronized (this) {
            List list = this.a.subList(n2, n3);
            return list;
        }
    }

    @Override
    public Object[] toArray() {
        synchronized (this) {
            Object[] arrobject = this.a.toArray();
            return arrobject;
        }
    }

    @Override
    public <V> V[] toArray(V[] arrV) {
        synchronized (this) {
            arrV = this.a.toArray((Object[])arrV);
            return arrV;
        }
    }
}

