/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ContentUris
 *  android.content.UriMatcher
 *  android.net.Uri
 *  android.provider.Telephony
 *  android.provider.Telephony$Mms
 *  java.lang.Integer
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.String
 *  java.util.HashMap
 *  java.util.HashSet
 */
package com.google.android.mms.util;

import android.content.ContentUris;
import android.content.UriMatcher;
import android.net.Uri;
import android.provider.Telephony;
import com.google.android.mms.util.AbstractCache;
import com.google.android.mms.util.PduCacheEntry;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public final class PduCache
extends AbstractCache<Uri, PduCacheEntry> {
    private static final boolean DEBUG = false;
    private static final boolean LOCAL_LOGV = false;
    private static final HashMap<Integer, Integer> MATCH_TO_MSGBOX_ID_MAP;
    private static final int MMS_ALL = 0;
    private static final int MMS_ALL_ID = 1;
    private static final int MMS_CONVERSATION = 10;
    private static final int MMS_CONVERSATION_ID = 11;
    private static final int MMS_DRAFTS = 6;
    private static final int MMS_DRAFTS_ID = 7;
    private static final int MMS_INBOX = 2;
    private static final int MMS_INBOX_ID = 3;
    private static final int MMS_OUTBOX = 8;
    private static final int MMS_OUTBOX_ID = 9;
    private static final int MMS_SENT = 4;
    private static final int MMS_SENT_ID = 5;
    private static final String TAG = "PduCache";
    private static final UriMatcher URI_MATCHER;
    private static PduCache sInstance;
    private final HashMap<Integer, HashSet<Uri>> mMessageBoxes = new HashMap();
    private final HashMap<Long, HashSet<Uri>> mThreads = new HashMap();
    private final HashSet<Uri> mUpdating = new HashSet();

    static {
        URI_MATCHER = new UriMatcher(-1);
        URI_MATCHER.addURI("mms", null, 0);
        URI_MATCHER.addURI("mms", "#", 1);
        URI_MATCHER.addURI("mms", "inbox", 2);
        URI_MATCHER.addURI("mms", "inbox/#", 3);
        URI_MATCHER.addURI("mms", "sent", 4);
        URI_MATCHER.addURI("mms", "sent/#", 5);
        URI_MATCHER.addURI("mms", "drafts", 6);
        URI_MATCHER.addURI("mms", "drafts/#", 7);
        URI_MATCHER.addURI("mms", "outbox", 8);
        URI_MATCHER.addURI("mms", "outbox/#", 9);
        URI_MATCHER.addURI("mms-sms", "conversations", 10);
        URI_MATCHER.addURI("mms-sms", "conversations/#", 11);
        MATCH_TO_MSGBOX_ID_MAP = new HashMap();
        MATCH_TO_MSGBOX_ID_MAP.put((Object)2, (Object)1);
        MATCH_TO_MSGBOX_ID_MAP.put((Object)4, (Object)2);
        MATCH_TO_MSGBOX_ID_MAP.put((Object)6, (Object)3);
        MATCH_TO_MSGBOX_ID_MAP.put((Object)8, (Object)4);
    }

    private PduCache() {
    }

    public static final PduCache getInstance() {
        synchronized (PduCache.class) {
            if (sInstance == null) {
                sInstance = new PduCache();
            }
            PduCache pduCache = sInstance;
            return pduCache;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private Uri normalizeKey(Uri object) {
        switch (URI_MATCHER.match((Uri)object)) {
            default: {
                return null;
            }
            case 1: {
                return object;
            }
            case 3: 
            case 5: 
            case 7: 
            case 9: 
        }
        object = object.getLastPathSegment();
        return Uri.withAppendedPath((Uri)Telephony.Mms.CONTENT_URI, (String)object);
    }

    private void purgeByMessageBox(Integer object) {
        if (object != null && (object = (HashSet)this.mMessageBoxes.remove(object)) != null) {
            object = object.iterator();
            while (object.hasNext()) {
                Uri uri = (Uri)object.next();
                this.mUpdating.remove((Object)uri);
                PduCacheEntry pduCacheEntry = (PduCacheEntry)super.purge(uri);
                if (pduCacheEntry == null) continue;
                this.removeFromThreads(uri, pduCacheEntry);
            }
        }
    }

    private void purgeByThreadId(long l) {
        Object object = (HashSet)this.mThreads.remove((Object)l);
        if (object != null) {
            object = object.iterator();
            while (object.hasNext()) {
                Uri uri = (Uri)object.next();
                this.mUpdating.remove((Object)uri);
                PduCacheEntry pduCacheEntry = (PduCacheEntry)super.purge(uri);
                if (pduCacheEntry == null) continue;
                this.removeFromMessageBoxes(uri, pduCacheEntry);
            }
        }
    }

    private PduCacheEntry purgeSingleEntry(Uri uri) {
        this.mUpdating.remove((Object)uri);
        PduCacheEntry pduCacheEntry = (PduCacheEntry)super.purge(uri);
        if (pduCacheEntry != null) {
            this.removeFromThreads(uri, pduCacheEntry);
            this.removeFromMessageBoxes(uri, pduCacheEntry);
            return pduCacheEntry;
        }
        return null;
    }

    private void removeFromMessageBoxes(Uri uri, PduCacheEntry pduCacheEntry) {
        if ((pduCacheEntry = (HashSet)this.mThreads.get((Object)Long.valueOf((long)pduCacheEntry.getMessageBox()))) != null) {
            pduCacheEntry.remove((Object)uri);
        }
    }

    private void removeFromThreads(Uri uri, PduCacheEntry pduCacheEntry) {
        if ((pduCacheEntry = (HashSet)this.mThreads.get((Object)pduCacheEntry.getThreadId())) != null) {
            pduCacheEntry.remove((Object)uri);
        }
    }

    public boolean isUpdating(Uri uri) {
        synchronized (this) {
            boolean bl = this.mUpdating.contains((Object)uri);
            return bl;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public PduCacheEntry purge(Uri object) {
        synchronized (this) {
            int n = URI_MATCHER.match((Uri)object);
            switch (n) {
                default: {
                    return null;
                }
                case 1: {
                    return this.purgeSingleEntry((Uri)object);
                }
                case 3: 
                case 5: 
                case 7: 
                case 9: {
                    object = object.getLastPathSegment();
                    return this.purgeSingleEntry(Uri.withAppendedPath((Uri)Telephony.Mms.CONTENT_URI, (String)object));
                }
                case 0: 
                case 10: {
                    this.purgeAll();
                    return null;
                }
                case 2: 
                case 4: 
                case 6: 
                case 8: {
                    this.purgeByMessageBox((Integer)MATCH_TO_MSGBOX_ID_MAP.get((Object)n));
                    return null;
                }
                case 11: {
                    this.purgeByThreadId(ContentUris.parseId((Uri)object));
                    return null;
                }
            }
        }
    }

    @Override
    public void purgeAll() {
        synchronized (this) {
            super.purgeAll();
            this.mMessageBoxes.clear();
            this.mThreads.clear();
            this.mUpdating.clear();
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public boolean put(Uri uri, PduCacheEntry pduCacheEntry) {
        synchronized (this) {
            boolean bl;
            void var2_2;
            HashSet hashSet;
            HashSet hashSet2;
            int n = var2_2.getMessageBox();
            HashSet hashSet3 = hashSet2 = (HashSet)this.mMessageBoxes.get((Object)n);
            if (hashSet2 == null) {
                hashSet3 = new HashSet();
                this.mMessageBoxes.put((Object)n, (Object)hashSet3);
            }
            long l = var2_2.getThreadId();
            hashSet2 = hashSet = (HashSet)this.mThreads.get((Object)l);
            if (hashSet == null) {
                hashSet2 = new HashSet();
                this.mThreads.put((Object)l, (Object)hashSet2);
            }
            if (bl = super.put(hashSet = this.normalizeKey(uri), var2_2)) {
                hashSet3.add((Object)hashSet);
                hashSet2.add((Object)hashSet);
            }
            this.setUpdating(uri, false);
            return bl;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void setUpdating(Uri uri, boolean bl) {
        synchronized (this) {
            void var2_2;
            if (var2_2 != false) {
                this.mUpdating.add((Object)uri);
            } else {
                this.mUpdating.remove((Object)uri);
            }
            return;
        }
    }
}

