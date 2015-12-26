/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ContentResolver
 *  android.content.Context
 *  android.database.Cursor
 *  android.net.Uri
 *  android.provider.Telephony
 *  android.provider.Telephony$MmsSms
 *  android.util.Log
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Thread
 *  java.util.HashSet
 *  miui.provider.ExtraTelephony
 *  miui.provider.ExtraTelephony$Hms
 */
package com.android.mms.util;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.Telephony;
import android.util.Log;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import miui.provider.ExtraTelephony;

public class DraftCache {
    static final String[] DRAFT_PROJECTION = new String[]{"thread_id"};
    private static DraftCache sInstance;
    private final HashSet<OnDraftChangedListener> mChangeListeners = new HashSet(1);
    private final Context mContext;
    private HashSet<Long> mDraftSet = new HashSet(4);
    private boolean mSavingDraft;

    private DraftCache(Context context) {
        if (Log.isLoggable((String)"Mms:app", (int)3)) {
            this.log("DraftCache.constructor", new Object[0]);
        }
        this.mContext = context;
        this.refresh();
    }

    public static DraftCache getInstance() {
        return sInstance;
    }

    public static void init(Context context) {
        sInstance = new DraftCache(context.getApplicationContext());
    }

    private /* varargs */ void log(String string2, Object ... arrobject) {
        string2 = String.format((String)string2, (Object[])arrobject);
        Log.d((String)"Mms/draft", (String)("[DraftCache/" + Thread.currentThread().getId() + "] " + string2));
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    private void rebuildCache() {
        HashSet hashSet;
        HashSet<OnDraftChangedListener> hashSet2;
        HashSet hashSet3;
        // MONITORENTER : this
        if (Log.isLoggable((String)"Mms:app", (int)3)) {
            this.log("rebuildCache", new Object[0]);
        }
        hashSet = this.mDraftSet;
        hashSet2 = new HashSet<OnDraftChangedListener>(hashSet.size());
        hashSet3 = this.mContext.getContentResolver().query(Telephony.MmsSms.CONTENT_DRAFT_URI, DRAFT_PROJECTION, null, null, null);
        if (hashSet3 != null) {
            try {
                if (hashSet3.moveToFirst()) {
                    while (!hashSet3.isAfterLast()) {
                        long l = hashSet3.getLong(0);
                        hashSet2.add((Object)l);
                        if (Log.isLoggable((String)"Mms:app", (int)3)) {
                            this.log("rebuildCache: add tid=" + l, new Object[0]);
                        }
                        hashSet3.moveToNext();
                    }
                }
            }
            finally {
                hashSet3.close();
            }
        }
        if ((hashSet3 = this.mContext.getContentResolver().query(ExtraTelephony.Hms.CONTENT_URI, DRAFT_PROJECTION, "type = 3", null, null)) != null) {
            try {
                if (hashSet3.moveToFirst()) {
                    while (!hashSet3.isAfterLast()) {
                        hashSet2.add((Object)hashSet3.getLong(0));
                        hashSet3.moveToNext();
                    }
                }
            }
            finally {
                hashSet3.close();
            }
        }
        this.mDraftSet = hashSet2;
        hashSet3 = this.mChangeListeners;
        // MONITORENTER : hashSet3
        if (this.mChangeListeners.size() < 1) {
            // MONITOREXIT : hashSet3
            // MONITOREXIT : this
            return;
        }
        // MONITOREXIT : hashSet3
        hashSet3 = new HashSet((Collection)hashSet2);
        hashSet3.removeAll(hashSet);
        hashSet = new HashSet(hashSet);
        hashSet.removeAll(hashSet2);
        hashSet2 = this.mChangeListeners;
        // MONITORENTER : hashSet2
        Iterator iterator = this.mChangeListeners.iterator();
        block22 : do {
            if (!iterator.hasNext()) {
                // MONITOREXIT : hashSet2
                return;
            }
            OnDraftChangedListener onDraftChangedListener = (OnDraftChangedListener)iterator.next();
            Iterator iterator2 = hashSet3.iterator();
            while (iterator2.hasNext()) {
                onDraftChangedListener.onDraftChanged((Long)iterator2.next(), true);
            }
            iterator2 = hashSet.iterator();
            do {
                if (!iterator2.hasNext()) continue block22;
                onDraftChangedListener.onDraftChanged((Long)iterator2.next(), false);
            } while (true);
            break;
        } while (true);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void addOnDraftChangedListener(OnDraftChangedListener onDraftChangedListener) {
        HashSet<OnDraftChangedListener> hashSet = this.mChangeListeners;
        synchronized (hashSet) {
            if (Log.isLoggable((String)"Mms:app", (int)3)) {
                this.log("addOnDraftChangedListener " + onDraftChangedListener, new Object[0]);
            }
            this.mChangeListeners.add((Object)onDraftChangedListener);
            return;
        }
    }

    public boolean getSavingDraft() {
        synchronized (this) {
            boolean bl = this.mSavingDraft;
            return bl;
        }
    }

    public boolean hasDraft(long l) {
        synchronized (this) {
            boolean bl = this.mDraftSet.contains((Object)l);
            return bl;
        }
    }

    public void refresh() {
        if (Log.isLoggable((String)"Mms:app", (int)3)) {
            this.log("refresh", new Object[0]);
        }
        new Thread(new Runnable(){

            @Override
            public void run() {
                DraftCache.this.rebuildCache();
            }
        }).start();
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void removeOnDraftChangedListener(OnDraftChangedListener onDraftChangedListener) {
        HashSet<OnDraftChangedListener> hashSet = this.mChangeListeners;
        synchronized (hashSet) {
            if (Log.isLoggable((String)"Mms:app", (int)3)) {
                this.log("removeOnDraftChangedListener " + onDraftChangedListener, new Object[0]);
            }
            this.mChangeListeners.remove((Object)onDraftChangedListener);
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void setDraftState(long l, boolean bl) {
        synchronized (this) {
            if (l > 0) {
                boolean bl2 = bl ? this.mDraftSet.add((Object)l) : this.mDraftSet.remove((Object)l);
                if (Log.isLoggable((String)"Mms:app", (int)3)) {
                    this.log("setDraftState: tid=" + l + ", value=" + bl + ", changed=" + bl2, new Object[0]);
                }
                if (bl2) {
                    HashSet<OnDraftChangedListener> hashSet = this.mChangeListeners;
                    synchronized (hashSet) {
                        Iterator iterator = this.mChangeListeners.iterator();
                        while (iterator.hasNext()) {
                            ((OnDraftChangedListener)iterator.next()).onDraftChanged(l, bl);
                        }
                    }
                }
            }
            return;
        }
    }

    public void setSavingDraft(boolean bl) {
        synchronized (this) {
            this.mSavingDraft = bl;
            return;
        }
    }

    public static interface OnDraftChangedListener {
        public void onDraftChanged(long var1, boolean var3);
    }

}

