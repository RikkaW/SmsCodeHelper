/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.util.HashMap
 *  java.util.LinkedList
 */
package com.xiaomi.mms.data;

import android.content.Context;
import com.android.mms.data.Contact;
import com.xiaomi.mms.transaction.MxTaskService;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MxIdCache {
    private static final List<MxCacheStatusListener> mStatusListener;
    private static final Map<String, MxIdCacheItem> map;

    static {
        map = new HashMap();
        mStatusListener = new LinkedList();
    }

    private MxIdCache() {
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void addStatusListener(MxCacheStatusListener mxCacheStatusListener) {
        List<MxCacheStatusListener> list = mStatusListener;
        synchronized (list) {
            if (!mStatusListener.contains(mxCacheStatusListener)) {
                mStatusListener.add(mxCacheStatusListener);
                return;
            }
            throw new IllegalStateException("listener already added to cache");
        }
    }

    public static MxIdCacheItem get(String object) {
        synchronized (MxIdCache.class) {
            object = MxIdCache.get((String)object, false);
            return object;
        }
    }

    public static MxIdCacheItem get(String object, boolean bl) {
        synchronized (MxIdCache.class) {
            object = MxIdCache.get((String)object, bl, true);
            return object;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private static MxIdCacheItem get(String object, boolean bl, boolean bl2) {
        synchronized (MxIdCache.class) {
            void var1_1;
            void var2_2;
            Object object2 = object;
            if (var2_2 != false) {
                object2 = Contact.normalizePhoneNumber((String)object);
            }
            if ((object2 = map.get(object2)) == null) return null;
            long l = object2.expire;
            long l2 = System.currentTimeMillis();
            object = object2;
            if (l > l2) return object;
            if (var1_1 == false) return null;
            return object2;
        }
    }

    public static MxIdCacheItem getOrQuery(Context object, String string2) {
        synchronized (MxIdCache.class) {
            object = MxIdCache.getOrQuery((Context)object, string2, true);
            return object;
        }
    }

    public static MxIdCacheItem getOrQuery(Context context, String string2, boolean bl) {
        synchronized (MxIdCache.class) {
            string2 = Contact.normalizePhoneNumber(string2);
            MxIdCacheItem mxIdCacheItem = MxIdCache.get(string2, bl, false);
            if (mxIdCacheItem == null || mxIdCacheItem.expire <= System.currentTimeMillis() || mxIdCacheItem.capability == Integer.MIN_VALUE) {
                MxTaskService.queryStatus(context, string2);
            }
            return mxIdCacheItem;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void offline(String object) {
        boolean bl = true;
        synchronized (MxIdCache.class) {
            String string2 = Contact.normalizePhoneNumber((String)object);
            MxIdCacheItem mxIdCacheItem = map.get(string2);
            if (mxIdCacheItem != null && mxIdCacheItem.mid != null) {
                boolean bl2 = bl;
                if (mxIdCacheItem.capability <= 0) {
                    bl2 = mxIdCacheItem.expire < System.currentTimeMillis() ? bl : false;
                }
                mxIdCacheItem.capability = 0;
                mxIdCacheItem.transientState = true;
                mxIdCacheItem.updateExpire();
                if (bl2) {
                    object = mStatusListener;
                    synchronized (object) {
                        Iterator<MxCacheStatusListener> iterator = mStatusListener.iterator();
                        while (iterator.hasNext()) {
                            iterator.next().onMxIdOffline(mxIdCacheItem.mid, string2);
                        }
                    }
                }
            }
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static boolean online(String object, long l, boolean bl) {
        boolean bl2 = false;
        synchronized (MxIdCache.class) {
            String string2 = Contact.normalizePhoneNumber((String)object);
            MxIdCacheItem mxIdCacheItem = map.get(string2);
            boolean bl3 = bl2;
            if (mxIdCacheItem == null) return bl3;
            bl3 = bl2;
            if (mxIdCacheItem.mid == null) return bl3;
            long l2 = mxIdCacheItem.capability;
            long l3 = l;
            if (bl) {
                l3 = l | mxIdCacheItem.capability;
            }
            mxIdCacheItem.capability = l3;
            boolean bl4 = l2 != mxIdCacheItem.capability || mxIdCacheItem.expire < System.currentTimeMillis();
            mxIdCacheItem.updateExpire();
            if (!bl) {
                mxIdCacheItem.transientState = bl;
            }
            if (!bl4) return mxIdCacheItem.transientState;
            object = mStatusListener;
            synchronized (object) {
                Iterator<MxCacheStatusListener> iterator = mStatusListener.iterator();
                while (iterator.hasNext()) {
                    iterator.next().onMxIdOnline(mxIdCacheItem.mid, string2);
                }
                return mxIdCacheItem.transientState;
            }
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void put(String object, String object2) {
        synchronized (MxIdCache.class) {
            Object object3;
            String string2 = Contact.normalizePhoneNumber((String)object);
            object = map.get(string2);
            boolean bl = false;
            boolean bl2 = false;
            if (object == null) {
                object3 = new MxIdCacheItem((String)object2);
                map.put(string2, (MxIdCacheItem)object3);
                object = object3;
                if (object2 != null) {
                    bl2 = true;
                    object = object3;
                }
            } else {
                bl2 = bl;
                if (object.mid == null) {
                    bl2 = bl;
                    if (object2 != null) {
                        bl2 = true;
                    }
                }
                object.mid = object2;
                object.updateExpire();
            }
            if (bl2) {
                object2 = mStatusListener;
                synchronized (object2) {
                    object3 = mStatusListener.iterator();
                    while (object3.hasNext()) {
                        ((MxCacheStatusListener)object3.next()).onMxIdAdded(object.mid, string2);
                    }
                }
            }
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void removeStatusListener(MxCacheStatusListener mxCacheStatusListener) {
        List<MxCacheStatusListener> list = mStatusListener;
        synchronized (list) {
            int n = mStatusListener.indexOf(mxCacheStatusListener);
            if (n < 0) {
                throw new IllegalStateException("listener not in cached");
            }
            mStatusListener.remove(n);
            return;
        }
    }

    public static interface MxCacheStatusListener {
        public void onMxIdAdded(String var1, String var2);

        public void onMxIdOffline(String var1, String var2);

        public void onMxIdOnline(String var1, String var2);
    }

    public static class MxIdCacheItem {
        protected long capability = Integer.MIN_VALUE;
        protected long expire = System.currentTimeMillis();
        protected String mid;
        protected boolean transientState = true;

        private MxIdCacheItem(String string2) {
            this.mid = string2;
        }

        public boolean allowMms() {
            if (this.mid != null && (this.capability & 2) > 0) {
                return true;
            }
            return false;
        }

        public boolean allowMxV2() {
            if (this.mid != null && (this.capability & 0x400000000L) > 0) {
                return true;
            }
            return false;
        }

        public boolean allowSms() {
            if (this.mid != null && (this.capability & 1) > 0) {
                return true;
            }
            return false;
        }

        public long getCapability() {
            return this.capability;
        }

        public String getMId() {
            return this.mid;
        }

        public boolean isExpired() {
            if (this.expire <= System.currentTimeMillis()) {
                return true;
            }
            return false;
        }

        public String toString() {
            return "MxIdCacheItem{mid='" + this.mid + '\'' + ", expire=" + this.expire + ", capability=" + this.capability + '}';
        }

        /*
         * Enabled aggressive block sorting
         */
        protected void updateExpire() {
            long l = System.currentTimeMillis();
            long l2 = this.mid != null ? 300000 : 120000;
            this.expire = l2 + l;
        }
    }

}

