/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.AsyncQueryHandler
 *  android.content.ContentProviderOperation
 *  android.content.ContentProviderOperation$Builder
 *  android.content.ContentProviderResult
 *  android.content.ContentResolver
 *  android.content.ContentUris
 *  android.content.ContentValues
 *  android.content.Context
 *  android.content.Intent
 *  android.content.OperationApplicationException
 *  android.database.Cursor
 *  android.database.DatabaseUtils
 *  android.net.Uri
 *  android.net.Uri$Builder
 *  android.os.RemoteException
 *  android.provider.Telephony
 *  android.provider.Telephony$Mms
 *  android.provider.Telephony$Mms$Inbox
 *  android.provider.Telephony$MmsSms
 *  android.provider.Telephony$Sms
 *  android.provider.Telephony$Sms$Inbox
 *  android.provider.Telephony$Threads
 *  android.text.TextUtils
 *  android.util.Log
 *  com.google.android.collect.Lists
 *  com.google.android.collect.Sets
 *  com.google.android.mms.util.PduCache
 *  com.google.android.mms.util.PduCacheEntry
 *  java.lang.Integer
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.lang.Thread
 *  java.lang.Throwable
 *  java.util.ArrayList
 *  java.util.Collections
 *  java.util.Comparator
 *  java.util.HashMap
 *  java.util.HashSet
 *  miui.date.DateUtils
 *  miui.provider.ExtraTelephony
 *  miui.provider.ExtraTelephony$Hms
 *  miui.provider.ExtraTelephony$MmsSms
 *  miui.telephony.PhoneNumberUtils
 */
package com.android.mms.data;

import android.app.Activity;
import android.content.AsyncQueryHandler;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.net.Uri;
import android.os.RemoteException;
import android.provider.Telephony;
import android.text.TextUtils;
import android.util.Log;
import com.android.mms.LogTag;
import com.android.mms.MmsApp;
import com.android.mms.data.Contact;
import com.android.mms.data.ContactList;
import com.android.mms.data.RecipientIdCache;
import com.android.mms.transaction.MessagingNotification;
import com.android.mms.ui.MessageUtils;
import com.android.mms.ui.PrivatePreferenceActivity;
import com.android.mms.util.AddressUtils;
import com.android.mms.util.DraftCache;
import com.google.android.collect.Lists;
import com.google.android.collect.Sets;
import com.google.android.mms.util.PduCache;
import com.google.android.mms.util.PduCacheEntry;
import com.xiaomi.mms.transaction.MipubThread;
import java.nio.BufferOverflowException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import miui.date.DateUtils;
import miui.provider.ExtraTelephony;
import miui.telephony.PhoneNumberUtils;

public class Conversation {
    public static final String[] ALL_BLOCKED_THREADS_PROJECTION;
    public static final String[] ALL_THREADS_PROJECTION;
    private static final String[] COUNT_PROJECTION;
    static final String[] SMS_PROJECTION;
    private static final String[] THREAD_ID;
    private static final String[] UNREAD_PROJECTION;
    private static boolean mLoadingThreads;
    private static ContentValues mReadContentValues;
    private static final Uri nAllThreadsUri;
    private static final Uri sAllThreadsUri;
    private static final Uri sBlockedUri;
    private static Thread sMarkAllConversationsAsSeenThread;
    private static Thread sMarkConversationAsNotifiedThread;
    private static Thread sMarkServiceProviderConversationAsSeenThread;
    private static final Uri sMmsSeenUri;
    private static final Uri sSmsSeenUri;
    private final Context mContext;
    private long mDate;
    private String mDateString = "";
    private boolean mHasAttachment;
    private boolean mHasError;
    private boolean mInCache = false;
    private boolean mIsB2cConv = false;
    private boolean mIsBlocked = false;
    private boolean mIsChecked;
    private boolean mIsServiceProvider;
    private long mLastSimId;
    private Object mMarkAsBlockedSyncer = new Object();
    private int mMessageCount;
    private long mPostMarkUnread;
    private Object mPostMarkUnreadObj = new Object();
    private boolean mPreMarkUnread;
    private boolean mPrivate;
    private ContactList mRecipients;
    private String mSeq = null;
    private String mSnippet;
    private long mStickTime;
    private long mThreadId;
    private int mUnreadMessageCount;

    static {
        sAllThreadsUri = Telephony.Threads.CONTENT_URI.buildUpon().appendQueryParameter("simple", "true").appendQueryParameter("privacy_flag", "2").build();
        nAllThreadsUri = Telephony.Threads.CONTENT_URI.buildUpon().appendQueryParameter("simple", "true").build();
        sBlockedUri = ExtraTelephony.MmsSms.BLOCKED_CONVERSATION_CONTENT_URI;
        sSmsSeenUri = Uri.parse((String)"content://sms/seen");
        sMmsSeenUri = Uri.parse((String)"content://mms/seen");
        ALL_THREADS_PROJECTION = new String[]{"_id", "date", "message_count", "unread_count", "recipient_ids", "snippet", "snippet_cs", "read", "error", "has_attachment", "stick_time", "private_addr_ids", "last_sim_id", "sp_type", "mx_seq"};
        ALL_BLOCKED_THREADS_PROJECTION = new String[]{"blocked_threads._id", "recipient_ids", "date", "message_count", "unread_count", "snippet", "snippet_cs", "read"};
        UNREAD_PROJECTION = new String[]{"_id", "read"};
        COUNT_PROJECTION = new String[]{"count(*)"};
        THREAD_ID = new String[]{"_id"};
        SMS_PROJECTION = new String[]{"_id", "thread_id", "address", "body", "date", "read", "type", "status", "locked", "error_code"};
    }

    private Conversation(Context context) {
        this.mContext = context;
        this.mRecipients = new ContactList();
        this.mThreadId = 0;
    }

    private Conversation(Context context, long l) {
        this.mContext = context;
        if (!this.loadFromThreadId(l)) {
            this.mRecipients = new ContactList();
            this.mThreadId = 0;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private Conversation(Context context, long l, String string) {
        this.mContext = context;
        if (l != -1) {
            string = context.getContentResolver().query(Uri.withAppendedPath((Uri)sBlockedUri, (String)Long.toString((long)l)), ALL_BLOCKED_THREADS_PROJECTION, null, null, null);
        } else {
            string = "PHONE_NUMBERS_EQUAL(address, '" + string + "', 0)";
            string = context.getContentResolver().query(sBlockedUri, ALL_BLOCKED_THREADS_PROJECTION, string, null, null);
        }
        boolean bl = false;
        boolean bl2 = false;
        if (string != null) {
            if (string.moveToFirst()) {
                Conversation.fillFromBlockedCursor(context, this, (Cursor)string);
                return;
            }
            LogTag.debug("Cannot move to first cursor", new Object[0]);
        } else {
            LogTag.debug("cursor is null", new Object[0]);
            bl2 = bl;
        }
        if (!bl2) {
            LogTag.debug("Cannot load thread %d, forcing threadId=0", l);
            this.mRecipients = new ContactList();
            this.mThreadId = 0;
        }
    }

    private Conversation(Context context, Cursor cursor) {
        this.mContext = context;
        Conversation.fillFromCursor(context, this, cursor);
    }

    public static void asyncDeleteObsoleteThreads(AsyncQueryHandler asyncQueryHandler, int n) {
        asyncQueryHandler.startDelete(n, (Object)null, Telephony.Threads.OBSOLETE_THREADS_URI, null, null);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private static void blockingMarkAllHmsAsNotified(Context context) {
        int n;
        Cursor cursor = (context = context.getContentResolver()).query(ExtraTelephony.Hms.CONTENT_URI, COUNT_PROJECTION, "(advanced_seen=1)", null, null);
        if (cursor != null) {
            try {
                n = cursor.moveToFirst() ? cursor.getInt(0) : 0;
            }
            finally {
                cursor.close();
            }
        }
        n = 0;
        if (Thread.currentThread().isInterrupted() || n <= 0) {
            return;
        }
        cursor = new ContentValues(2);
        cursor.put("seen", Integer.valueOf((int)1));
        cursor.put("advanced_seen", Integer.valueOf((int)2));
        context.update(ExtraTelephony.Hms.CONTENT_URI, (ContentValues)cursor, "(advanced_seen=1 AND seen = 0)", null);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private static void blockingMarkAllMipubMessagesAsSeen(Context context) {
        int n;
        Cursor cursor = (context = context.getContentResolver()).query(MipubThread.Mipub.CONTENT_URI, new String[]{"count(*)"}, "seen=0", null, null);
        if (cursor == null) {
            return;
        }
        try {
            n = cursor.moveToFirst() ? cursor.getInt(0) : 0;
        }
        finally {
            cursor.close();
        }
        if (n == 0 || Thread.currentThread().isInterrupted()) {
            return;
        }
        LogTag.debug("mark %d Mipub msgs as seen", n);
        cursor = new ContentValues(2);
        cursor.put("seen", Integer.valueOf((int)1));
        cursor.put("advanced_seen", Integer.valueOf((int)3));
        context.update(MipubThread.Mipub.CONTENT_URI, (ContentValues)cursor, "seen = ?", new String[]{"0"});
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private static void blockingMarkAllMmsMessagesAsSeen(Context context) {
        int n;
        Cursor cursor = (context = context.getContentResolver()).query(Telephony.Mms.Inbox.CONTENT_URI, COUNT_PROJECTION, "seen=0", null, null);
        if (cursor == null) {
            return;
        }
        try {
            n = cursor.moveToFirst() ? cursor.getInt(0) : 0;
        }
        finally {
            cursor.close();
        }
        if (n == 0 || Thread.currentThread().isInterrupted()) {
            return;
        }
        cursor = new ContentValues(1);
        cursor.put("seen", Integer.valueOf((int)1));
        cursor.put("advanced_seen", Integer.valueOf((int)3));
        context.update(Telephony.Mms.Inbox.CONTENT_URI, (ContentValues)cursor, "seen=0", null);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private static void blockingMarkAllSmsMessagesAsSeen(Context context) {
        int n;
        Cursor cursor = (context = context.getContentResolver()).query(Telephony.Sms.Inbox.CONTENT_URI, COUNT_PROJECTION, "seen=0", null, null);
        if (cursor == null) {
            return;
        }
        try {
            n = cursor.moveToFirst() ? cursor.getInt(0) : 0;
        }
        finally {
            cursor.close();
        }
        if (n == 0 || Thread.currentThread().isInterrupted()) {
            return;
        }
        cursor = new ContentValues(1);
        cursor.put("seen", Integer.valueOf((int)1));
        cursor.put("advanced_seen", Integer.valueOf((int)3));
        context.update(Telephony.Sms.Inbox.CONTENT_URI, (ContentValues)cursor, "seen=0", null);
    }

    private void buildReadContentValues() {
        if (mReadContentValues == null) {
            mReadContentValues = new ContentValues(2);
            mReadContentValues.put("read", Integer.valueOf((int)1));
            mReadContentValues.put("seen", Integer.valueOf((int)1));
        }
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    private static void cacheAllThreads(Context var0) {
        if (Log.isLoggable((String)"Mms:threadcache", (int)2)) {
            LogTag.debug("cacheAllThreads: begin", new Object[0]);
        }
        var3_2 = Cache.getInstance();
        // MONITORENTER : var3_2
        if (Conversation.mLoadingThreads) {
            // MONITOREXIT : var3_2
            return;
        }
        Conversation.mLoadingThreads = true;
        // MONITOREXIT : var3_2
        var4_3 = new HashSet();
        var3_2 = var0.getContentResolver().query(Conversation.sAllThreadsUri, Conversation.ALL_THREADS_PROJECTION, null, null, null);
        if (var3_2 == null) ** GOTO lbl45
        do {
            try {
                if (!var3_2.moveToNext()) break;
                var1_4 = var3_2.getLong(0);
                var4_3.add((Object)var1_4);
                var5_5 = Cache.getInstance();
                // MONITORENTER : var5_5
            }
            catch (Throwable var0_1) {
                if (var3_2 != null) {
                    var3_2.close();
                }
                var3_2 = Cache.getInstance();
                // MONITORENTER : var3_2
                Conversation.mLoadingThreads = false;
                // MONITOREXIT : var3_2
                throw var0_1;
            }
            var6_7 = Cache.get(var1_4);
            // MONITOREXIT : var5_5
            if (var6_7 == null) {
                var6_7 = new Conversation((Context)var0, (Cursor)var3_2);
                var5_5 = Cache.getInstance();
                // MONITORENTER : var5_5
                {
                    catch (IllegalStateException var5_6) {
                        LogTag.error("Tried to add duplicate Conversation to Cache", new Object[0]);
                    }
                }
                Cache.put(var6_7);
                // MONITOREXIT : var5_5
            }
            Conversation.fillFromCursor((Context)var0, var6_7, (Cursor)var3_2);
        } while (true);
lbl45: // 2 sources:
        if (var3_2 != null) {
            var3_2.close();
        }
        var0 = Cache.getInstance();
        // MONITORENTER : var0
        Conversation.mLoadingThreads = false;
        // MONITOREXIT : var0
        Cache.keepOnly(var4_3);
    }

    /*
     * Enabled aggressive block sorting
     */
    public static boolean checkContentScheme(Uri object) {
        if (TextUtils.isEmpty((CharSequence)(object = object.getScheme())) || !object.equals((Object)"content")) {
            return false;
        }
        return true;
    }

    public static Conversation createNew(Context context) {
        return new Conversation(context);
    }

    public static Conversation createNew(Context context, long l) {
        return new Conversation(context, l);
    }

    public static Conversation createNew(Context context, Uri uri) {
        if (uri == null) {
            return Conversation.createNew(context);
        }
        if (uri.getPathSegments().size() >= 2) {
            try {
                Conversation conversation = Conversation.createNew(context, Long.parseLong((String)((String)uri.getPathSegments().get(1))));
                return conversation;
            }
            catch (NumberFormatException var2_3) {
                LogTag.error("Invalid URI: %s", new Object[]{uri});
            }
        }
        if (Conversation.checkContentScheme(uri)) {
            return Conversation.createNew(context);
        }
        return Conversation.createNew(context, ContactList.getByNumbers(Conversation.getRecipientsFromUri(uri), true));
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static Conversation createNew(Context object, ContactList contactList) {
        Conversation conversation;
        if (contactList.size() < 1) {
            return Conversation.createNew((Context)object);
        }
        object = conversation = new Conversation((Context)object, Conversation.getOrCreateThreadId((Context)object, contactList));
        if (conversation.getThreadId() != 0) return object;
        conversation.setRecipients(contactList);
        return conversation;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private static void fillFromBlockedCursor(Context object, Conversation conversation, Cursor cursor) {
        synchronized (conversation) {
            String string;
            StringBuilder stringBuilder /* !! */ ;
            conversation.mIsBlocked = true;
            conversation.mThreadId = cursor.getLong(0);
            conversation.mDate = cursor.getLong(2);
            conversation.mDateString = "";
            try {
                stringBuilder /* !! */  = new StringBuilder();
                DateUtils.formatRelativeTime((StringBuilder)stringBuilder /* !! */ , (long)conversation.mDate, (boolean)false);
                conversation.mDateString = stringBuilder /* !! */ .toString();
            }
            catch (BufferOverflowException var3_4) {
                Log.w((String)"Mms/conv", (String)"Not enough space for date buffer.");
            }
            conversation.mMessageCount = cursor.getInt(3);
            conversation.mUnreadMessageCount = cursor.getInt(4);
            stringBuilder /* !! */  = string = MessageUtils.extractEncStrFromCursor(cursor, 5, 6);
            if (TextUtils.isEmpty((CharSequence)string)) {
                stringBuilder /* !! */  = object.getString(2131361807);
            }
            conversation.mSnippet = Conversation.formatSnippet((Context)object, (String)((Object)stringBuilder /* !! */ ));
        }
        object = ContactList.getByIds(cursor.getString(1));
        synchronized (conversation) {
            if (conversation.mInCache && !object.equals((Object)conversation.mRecipients)) {
                throw new IllegalStateException(String.format((String)"Unexpected recipient change in conversation %d", (Object[])new Object[]{conversation.mThreadId}));
            }
            conversation.mRecipients = object;
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private static void fillFromCursor(Context object, Conversation conversation, Cursor cursor) {
        boolean bl = true;
        synchronized (conversation) {
            String string;
            StringBuilder stringBuilder /* !! */ ;
            conversation.mThreadId = cursor.getLong(0);
            conversation.mDate = cursor.getLong(1);
            conversation.mDateString = "";
            try {
                stringBuilder /* !! */  = new StringBuilder();
                DateUtils.formatRelativeTime((StringBuilder)stringBuilder /* !! */ , (long)conversation.mDate, (boolean)false);
                conversation.mDateString = stringBuilder /* !! */ .toString();
            }
            catch (BufferOverflowException var5_5) {
                Log.w((String)"Mms/conv", (String)"Not enough space for date buffer.");
            }
            conversation.mMessageCount = cursor.getInt(2);
            conversation.mUnreadMessageCount = cursor.getInt(3);
            stringBuilder /* !! */  = string = MessageUtils.extractEncStrFromCursor(cursor, 5, 6);
            if (TextUtils.isEmpty((CharSequence)string)) {
                stringBuilder /* !! */  = object.getString(2131361807);
            }
            conversation.mSnippet = Conversation.formatSnippet((Context)object, (String)((Object)stringBuilder /* !! */ ));
            boolean bl2 = cursor.getInt(8) != 0;
            conversation.mHasError = bl2;
            bl2 = cursor.getInt(9) != 0;
            conversation.mHasAttachment = bl2;
            conversation.mStickTime = cursor.getLong(10);
            bl2 = cursor.getString(11) != null;
            conversation.mPrivate = bl2;
            conversation.mLastSimId = cursor.getInt(12);
            bl2 = cursor.getInt(13) != 0 ? bl : false;
            conversation.mIsServiceProvider = bl2;
            conversation.mSeq = cursor.getString(14);
            if (!TextUtils.isEmpty((CharSequence)conversation.mSeq)) {
                conversation.mIsB2cConv = true;
            }
        }
        object = ContactList.getByIds(cursor.getString(4));
        synchronized (conversation) {
            if (conversation.mInCache && !object.equals((Object)conversation.mRecipients)) {
                throw new IllegalStateException(String.format((String)"Unexpected recipient change in conversation %d from %s to %s", (Object[])new Object[]{conversation.mThreadId, conversation.mRecipients, object}));
            }
            conversation.mRecipients = object;
            return;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static String formatSnippet(Context context, String string) {
        if ("[audio]".equals((Object)string)) {
            return context.getString(2131362363);
        }
        String string2 = string;
        if (!"[picture]".equals((Object)string)) return string2;
        return context.getString(2131362364);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static Conversation from(Context object, Cursor cursor) {
        Conversation conversation;
        long l = cursor.getLong(0);
        if (l > 0 && (conversation = Cache.get(l)) != null) {
            Conversation.fillFromCursor((Context)object, conversation, cursor);
            return conversation;
        }
        object = new Conversation((Context)object, cursor);
        try {
            Cache.put((Conversation)object);
            do {
                return object;
                break;
            } while (true);
        }
        catch (IllegalStateException var1_2) {
            LogTag.error("Tried to add duplicate Conversation to Cache", new Object[0]);
            return object;
        }
    }

    public static Conversation get(long l) {
        return Conversation.get((Context)MmsApp.getApp(), l);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static Conversation get(Context object, long l) {
        Conversation conversation = Cache.get(l);
        if (conversation != null) {
            return conversation;
        }
        object = new Conversation((Context)object, l);
        try {
            Cache.put((Conversation)object);
            return object;
        }
        catch (IllegalStateException var3_3) {
            LogTag.error("Tried to add duplicate Conversation to Cache", new Object[0]);
            if (Cache.replace((Conversation)object)) return object;
            LogTag.error("get by recipients cache.replace failed on", new Object[0]);
            return object;
        }
    }

    public static Conversation get(Context context, Uri uri) {
        if (uri == null) {
            return Conversation.createNew(context);
        }
        if (uri.getPathSegments().size() >= 2) {
            try {
                Conversation conversation = Conversation.get(context, Long.parseLong((String)((String)uri.getPathSegments().get(1))));
                return conversation;
            }
            catch (NumberFormatException var2_3) {
                LogTag.error("Invalid URI: %s", new Object[]{uri});
            }
        }
        return Conversation.get(context, ContactList.getByNumbers(Conversation.getRecipientsFromUri(uri), true));
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static Conversation get(Context object, ContactList contactList) {
        Conversation conversation;
        if (contactList.size() < 1) {
            return Conversation.createNew((Context)object);
        }
        Conversation conversation2 = conversation = Cache.get(contactList);
        if (conversation != null) return conversation2;
        long l = Conversation.getOrCreateThreadId((Context)object, contactList);
        if ((object = new Conversation((Context)object, l)).getThreadId() == 0) {
            object.setRecipients(contactList);
            return object;
        }
        if (!object.getRecipients().equals((Object)contactList)) {
            LogTag.error(String.format((String)"Conversation.get: new conv %d 's recipients %s don't match input recpients %s", (Object[])new Object[]{l, object.getRecipients(), contactList}), new Object[0]);
        }
        try {
            Cache.put((Conversation)object);
            return object;
        }
        catch (IllegalStateException var1_2) {
            LogTag.error("Tried to add duplicate Conversation to Cache", new Object[0]);
            return object;
        }
    }

    public static Conversation get(Uri uri) {
        return Conversation.get((Context)MmsApp.getApp(), uri);
    }

    public static Conversation get(ContactList contactList) {
        return Conversation.get((Context)MmsApp.getApp(), contactList);
    }

    public static Conversation getBlockedConv(Context context, long l, String string) {
        return new Conversation(context, l, string);
    }

    public static Conversation getFromCache(Context context, ContactList contactList) {
        if (contactList.size() < 1) {
            return null;
        }
        return Cache.get(contactList);
    }

    private static long getOrCreateThreadId(Context context, ContactList object) {
        HashSet hashSet = new HashSet();
        StringBuilder stringBuilder = new StringBuilder("[");
        object = object.iterator();
        while (object.hasNext()) {
            Contact contact = (Contact)object.next();
            Contact contact2 = Contact.get(contact.getNumber());
            if (stringBuilder.length() > 1) {
                stringBuilder.append(",");
            }
            if (contact2 != null) {
                hashSet.add((Object)contact2.getNumber());
                stringBuilder.append(AddressUtils.cutPhoneNumberTail(contact2.getNumber()));
                stringBuilder.append("(cached)");
                continue;
            }
            hashSet.add((Object)contact.getNumber());
            stringBuilder.append(AddressUtils.cutPhoneNumberTail(contact.getNumber()));
        }
        stringBuilder.append("]");
        return Telephony.Threads.getOrCreateThreadId((Context)context, (Set)hashSet);
    }

    public static String getRecipients(Uri object) {
        int n = (object = object.getSchemeSpecificPart()).indexOf(63);
        if (n == -1) {
            return object;
        }
        return object.substring(0, n);
    }

    private static String getRecipientsFromUri(Uri object) {
        int n = (object = object.getSchemeSpecificPart()).indexOf(63);
        if (n == -1) {
            return object;
        }
        return object.substring(0, n);
    }

    public static void init(final Context context) {
        new Thread(new Runnable(){

            @Override
            public void run() {
                Conversation.cacheAllThreads(context);
            }
        }).start();
    }

    private void initPrivateState() {
        if (this.mRecipients.size() != 1) {
            this.setPrivate(false);
            return;
        }
        Contact contact = (Contact)this.mRecipients.get(0);
        this.setPrivate(PrivatePreferenceActivity.checkPrivateMessage(this.mContext, contact.getNumber()));
    }

    public static boolean isBlockedPlaceHolder(int n) {
        if ((n & 1) == 1) {
            return true;
        }
        return false;
    }

    public static boolean isMipubPlaceHolder(int n) {
        if ((n & 2) == 2) {
            return true;
        }
        return false;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private boolean loadFromThreadId(long l) {
        Cursor cursor = this.mIsBlocked ? this.mContext.getContentResolver().query(ContentUris.withAppendedId((Uri)sBlockedUri, (long)l), ALL_BLOCKED_THREADS_PROJECTION, null, null, null) : this.mContext.getContentResolver().query(sAllThreadsUri, ALL_THREADS_PROJECTION, "_id=" + Long.toString((long)l), null, null);
        if (cursor == null) {
            LogTag.error("loadFromThreadId: Can't find thread ID %d", l);
            return false;
        }
        try {
            if (cursor.moveToFirst()) {
                if (this.mIsBlocked) {
                    Conversation.fillFromBlockedCursor(this.mContext, this, cursor);
                } else {
                    Conversation.fillFromCursor(this.mContext, this, cursor);
                }
                if (l == this.mThreadId) return true;
                {
                    throw new IllegalStateException("loadFromThreadId: fillFromCursor returned different thread_id! threadId=" + l + ", mThreadId=" + this.mThreadId);
                }
            }
            LogTag.error("loadFromThreadId: Can't find thread ID %d, no such record", l);
            return false;
        }
        finally {
            cursor.close();
        }
    }

    public static void markAllConversationsAsSeen(final Context context) {
        if (sMarkAllConversationsAsSeenThread != null) {
            sMarkAllConversationsAsSeenThread.interrupt();
        }
        sMarkAllConversationsAsSeenThread = new Thread(new Runnable(){

            /*
             * Enabled aggressive block sorting
             * Lifted jumps to return sites
             */
            @Override
            public void run() {
                long l = System.currentTimeMillis();
                if (Thread.currentThread().isInterrupted()) {
                    return;
                }
                Conversation.blockingMarkAllSmsMessagesAsSeen(context);
                if (Thread.currentThread().isInterrupted()) return;
                Conversation.blockingMarkAllMmsMessagesAsSeen(context);
                if (Thread.currentThread().isInterrupted()) return;
                Conversation.blockingMarkAllMipubMessagesAsSeen(context);
                long l2 = System.currentTimeMillis();
                Log.d((String)"Mms/conv", (String)("update all seen: " + (l2 - l) + "ms"));
                if (Thread.currentThread().isInterrupted()) return;
                MessagingNotification.blockingUpdateAllNotifications(context);
            }
        });
        sMarkAllConversationsAsSeenThread.start();
    }

    public static void markBlockAsReadSync(Context context) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("read", Integer.valueOf((int)1));
        context.getContentResolver().update(ExtraTelephony.MmsSms.BLOCKED_CONVERSATION_CONTENT_URI, contentValues, null, null);
    }

    public static void markConversationAsNotified(final Context context) {
        if (sMarkConversationAsNotifiedThread != null) {
            sMarkConversationAsNotifiedThread.interrupt();
        }
        sMarkConversationAsNotifiedThread = new Thread(new Runnable(){

            /*
             * Enabled aggressive block sorting
             * Enabled unnecessary exception pruning
             * Enabled aggressive exception aggregation
             * Lifted jumps to return sites
             */
            @Override
            public void run() {
                if (Thread.currentThread().isInterrupted()) {
                    return;
                }
                ContentResolver contentResolver = context.getContentResolver();
                long l = System.currentTimeMillis();
                int n = 0;
                Cursor cursor = contentResolver.query(Telephony.Sms.Inbox.CONTENT_URI, COUNT_PROJECTION, "seen=0 AND advanced_seen=0", null, null);
                if (cursor != null) {
                    block21 : {
                        if (cursor.moveToFirst()) {
                            n = cursor.getInt(0);
                        }
                        if (Thread.currentThread().isInterrupted()) return;
                        if (n <= 0) break block21;
                        cursor = new ContentValues(2);
                        cursor.put("seen", Integer.valueOf((int)1));
                        cursor.put("advanced_seen", Integer.valueOf((int)3));
                        contentResolver.update(Telephony.Sms.Inbox.CONTENT_URI, (ContentValues)cursor, "seen = 0 AND advanced_seen=0", null);
                    }
                    System.currentTimeMillis();
                    Log.d((String)"Mms/conv", (String)("count:" + n + ",update notified non-sp sms:" + (System.currentTimeMillis() - l) + "ms"));
                }
                if (Thread.currentThread().isInterrupted()) return;
                n = 0;
                l = System.currentTimeMillis();
                cursor = contentResolver.query(Telephony.Mms.Inbox.CONTENT_URI, COUNT_PROJECTION, "seen=0 AND advanced_seen=0", null, null);
                if (cursor != null) {
                    block22 : {
                        if (cursor.moveToFirst()) {
                            n = cursor.getInt(0);
                        }
                        if (Thread.currentThread().isInterrupted()) return;
                        if (n <= 0) break block22;
                        cursor = new ContentValues(2);
                        cursor.put("seen", Integer.valueOf((int)1));
                        cursor.put("advanced_seen", Integer.valueOf((int)3));
                        contentResolver.update(Telephony.Mms.Inbox.CONTENT_URI, (ContentValues)cursor, "seen=0 AND advanced_seen=0", null);
                    }
                    Log.d((String)"Mms/conv", (String)("count:" + n + ",update notified non-sp mms:" + (System.currentTimeMillis() - l) + "ms"));
                }
                l = System.currentTimeMillis();
                n = 0;
                cursor = contentResolver.query(Telephony.Sms.Inbox.CONTENT_URI, COUNT_PROJECTION, "seen=0 AND advanced_seen=1", null, null);
                if (cursor != null) {
                    block23 : {
                        if (cursor.moveToFirst()) {
                            n = cursor.getInt(0);
                        }
                        if (n <= 0) break block23;
                        cursor = new ContentValues(2);
                        cursor.put("seen", Integer.valueOf((int)1));
                        cursor.put("advanced_seen", Integer.valueOf((int)2));
                        contentResolver.update(Telephony.Sms.Inbox.CONTENT_URI, (ContentValues)cursor, "seen=0 AND advanced_seen=1", null);
                    }
                    Log.d((String)"Mms/conv", (String)("count:" + n + ",update notified sp sms:" + (System.currentTimeMillis() - l) + "ms"));
                }
                if (Thread.currentThread().isInterrupted()) return;
                l = System.currentTimeMillis();
                n = 0;
                cursor = contentResolver.query(Telephony.Mms.Inbox.CONTENT_URI, COUNT_PROJECTION, "seen=0 AND advanced_seen=1", null, null);
                if (cursor != null) {
                    block24 : {
                        if (cursor.moveToFirst()) {
                            n = cursor.getInt(0);
                        }
                        if (Thread.currentThread().isInterrupted()) return;
                        if (n <= 0) break block24;
                        cursor = new ContentValues(2);
                        cursor.put("seen", Integer.valueOf((int)1));
                        cursor.put("advanced_seen", Integer.valueOf((int)2));
                        contentResolver.update(Telephony.Mms.Inbox.CONTENT_URI, (ContentValues)cursor, "seen=0 AND advanced_seen=1", null);
                    }
                    Log.d((String)"Mms/conv", (String)("count:" + n + ",update notified sp mms:" + (System.currentTimeMillis() - l) + "ms"));
                }
                if (Thread.currentThread().isInterrupted()) return;
                Conversation.blockingMarkAllHmsAsNotified(context);
                if (Thread.currentThread().isInterrupted()) return;
                l = System.currentTimeMillis();
                MessagingNotification.blockingUpdateAllNotifications(context);
                Log.d((String)"Mms/conv", (String)("update notified sp update all notification:" + (System.currentTimeMillis() - l) + "ms"));
                return;
                finally {
                    cursor.close();
                }
                finally {
                    cursor.close();
                }
                finally {
                    cursor.close();
                }
                finally {
                    cursor.close();
                }
            }
        });
        sMarkConversationAsNotifiedThread.start();
    }

    public static void markMipubAsReadAsync(Context context) {
        Intent intent = new Intent();
        intent.setAction("com.miui.mipub.MARK_ALL_MSG_READ");
        intent.setPackage("com.miui.mipub");
        context.sendBroadcast(intent);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static void markSPAsReadSync(Context context, int n) {
        Uri.Builder builder;
        Object object;
        String string;
        if (n <= 0) {
            return;
        }
        string = n == 1 ? "read=0 and sp_type>=1 and stick_time=0" : "read=0 and sp_type=" + n + " and stick_time=0";
        builder = nAllThreadsUri.buildUpon();
        string = context.getContentResolver().query(builder.appendQueryParameter("privacy_flag", "0").build(), UNREAD_PROJECTION, string, null, null);
        object = new ArrayList(8);
        if (string != null) {
            try {
                while (string.moveToNext()) {
                    long l = string.getLong(0);
                    if (l <= 0) continue;
                    object.add(l);
                }
            }
            finally {
                string.close();
            }
        }
        if (object.isEmpty()) return;
        string = new ArrayList();
        builder = new ContentValues(2);
        builder.put("read", Integer.valueOf((int)1));
        builder.put("seen", Integer.valueOf((int)1));
        ContentResolver contentResolver = context.getContentResolver();
        object = object.iterator();
        while (object.hasNext()) {
            Long l = (Long)object.next();
            if (l <= 0) continue;
            string.add((Object)ContentProviderOperation.newUpdate((Uri)ContentUris.withAppendedId((Uri)Telephony.Threads.CONTENT_URI, (long)l)).withValues((ContentValues)builder).withSelection("(read=0 OR seen=0)", null).build());
            if (string.size() < 50) continue;
            try {
                contentResolver.applyBatch("mms-sms", (ArrayList)string);
            }
            catch (RemoteException var8_10) {
                Log.d((String)"Mms/conv", (String)var8_10.getMessage());
            }
            catch (OperationApplicationException var8_11) {
                Log.d((String)"Mms/conv", (String)var8_11.getMessage());
            }
            string.clear();
        }
        if (!string.isEmpty()) {
            try {
                contentResolver.applyBatch("mms-sms", (ArrayList)string);
            }
            catch (Exception var5_5) {
                Log.d((String)"Mms/conv", (String)var5_5.getMessage());
            }
            string.clear();
        }
        MessagingNotification.blockingUpdateNewMessageIndicator(context, false, false);
    }

    public static void markServiceProviderConversationAsSeen(final Context context, final int n) {
        if (n <= 0) {
            return;
        }
        if (sMarkServiceProviderConversationAsSeenThread != null) {
            sMarkServiceProviderConversationAsSeenThread.interrupt();
        }
        sMarkServiceProviderConversationAsSeenThread = new Thread(new Runnable(){

            /*
             * Enabled aggressive block sorting
             * Enabled unnecessary exception pruning
             * Enabled aggressive exception aggregation
             * Lifted jumps to return sites
             */
            @Override
            public void run() {
                Cursor cursor;
                if (Thread.currentThread().isInterrupted()) {
                    return;
                }
                ContentResolver contentResolver = context.getContentResolver();
                long l = System.currentTimeMillis();
                int n2 = 0;
                Uri uri = Telephony.Sms.Inbox.CONTENT_URI;
                if (n > 1) {
                    uri = ContentUris.withAppendedId((Uri)sSmsSeenUri, (long)n);
                }
                if ((cursor = contentResolver.query(uri, COUNT_PROJECTION, "(advanced_seen=1 OR advanced_seen=2)", null, null)) != null) {
                    block13 : {
                        if (cursor.moveToFirst()) {
                            n2 = cursor.getInt(0);
                        }
                        if (Thread.currentThread().isInterrupted()) return;
                        if (n2 <= 0) break block13;
                        cursor = new ContentValues(2);
                        cursor.put("seen", Integer.valueOf((int)1));
                        cursor.put("advanced_seen", Integer.valueOf((int)3));
                        contentResolver.update(uri, (ContentValues)cursor, "(advanced_seen=1 OR advanced_seen=2)", null);
                    }
                    Log.d((String)"Mms/conv", (String)("count:" + n2 + ",update ad_seen sp sms:" + (System.currentTimeMillis() - l) + "ms"));
                }
                if (Thread.currentThread().isInterrupted()) return;
                n2 = 0;
                l = System.currentTimeMillis();
                uri = Telephony.Mms.Inbox.CONTENT_URI;
                if (n > 1) {
                    uri = ContentUris.withAppendedId((Uri)sMmsSeenUri, (long)n);
                }
                if ((cursor = contentResolver.query(uri, COUNT_PROJECTION, "(advanced_seen=1 OR advanced_seen=2)", null, null)) != null) {
                    block14 : {
                        if (cursor.moveToFirst()) {
                            n2 = cursor.getInt(0);
                        }
                        if (Thread.currentThread().isInterrupted()) return;
                        if (n2 <= 0) break block14;
                        cursor = new ContentValues(2);
                        cursor.put("seen", Integer.valueOf((int)1));
                        cursor.put("advanced_seen", Integer.valueOf((int)3));
                        contentResolver.update(uri, (ContentValues)cursor, "(advanced_seen=1 OR advanced_seen=2)", null);
                    }
                    Log.d((String)"Mms/conv", (String)("count:" + n2 + ",update ad_seen sp mms:" + (System.currentTimeMillis() - l) + "ms"));
                }
                if (Thread.currentThread().isInterrupted()) return;
                l = System.currentTimeMillis();
                MessagingNotification.blockingUpdateAllNotifications(context);
                Log.d((String)"Mms/conv", (String)("update ad_seen sp update all notification:" + (System.currentTimeMillis() - l) + "ms"));
                return;
                finally {
                    cursor.close();
                }
                finally {
                    cursor.close();
                }
            }
        });
        sMarkServiceProviderConversationAsSeenThread.start();
    }

    public static ArrayList<Conversation> searchForConversations(Context context, String string, boolean bl) {
        return Cache.searchForConversations(context, string, bl);
    }

    /*
     * Enabled aggressive block sorting
     */
    public static void startDelete(AsyncQueryHandler asyncQueryHandler, int n, boolean bl, Collection<Long> iterator) {
        String string = bl ? null : "locked=0";
        String string2 = "thread_id IN (" + TextUtils.join((CharSequence)",", iterator) + ")";
        iterator = iterator.iterator();
        do {
            if (!iterator.hasNext()) {
                asyncQueryHandler.startDelete(n, (Object)null, Telephony.Threads.CONTENT_URI, DatabaseUtils.concatenateWhere((String)string2, (String)string), null);
                return;
            }
            Long l = (Long)iterator.next();
            l = ContentUris.withAppendedId((Uri)Telephony.Threads.CONTENT_URI, (long)l);
            PduCache.getInstance().purge((Uri)l);
        } while (true);
    }

    /*
     * Enabled aggressive block sorting
     */
    public static void startDeleteAll(AsyncQueryHandler asyncQueryHandler, int n, boolean bl) {
        String string = bl ? null : "locked=0";
        PduCache.getInstance().purge(Telephony.Threads.CONTENT_URI);
        asyncQueryHandler.startDelete(n, (Object)null, Telephony.Threads.CONTENT_URI, string, null);
    }

    /*
     * Enabled aggressive block sorting
     */
    public static void startQueryForAll(AsyncQueryHandler asyncQueryHandler, int n, boolean bl, boolean bl2, int n2) {
        asyncQueryHandler.cancelOperation(n);
        Uri.Builder builder = nAllThreadsUri.buildUpon();
        String string = bl ? "1" : "0";
        builder.appendQueryParameter("privacy_flag", string);
        if (bl2) {
            builder.appendQueryParameter("threads_list_type", String.valueOf((int)0));
        }
        if (n2 != 0) {
            builder.appendQueryParameter("placeholder", String.valueOf((int)n2));
        }
        asyncQueryHandler.startQuery(n, (Object)null, builder.build(), ALL_THREADS_PROJECTION, null, null, "stick_time desc, date DESC");
    }

    public static void startQueryForServiceProvider(AsyncQueryHandler asyncQueryHandler, int n, int n2) {
        asyncQueryHandler.cancelOperation(n);
        asyncQueryHandler.startQuery(n, (Object)null, nAllThreadsUri.buildUpon().appendQueryParameter("privacy_flag", "0").appendQueryParameter("threads_list_type", String.valueOf((int)n2)).build(), ALL_THREADS_PROJECTION, null, null, "stick_time desc, date DESC");
    }

    /*
     * Enabled aggressive block sorting
     */
    public static void startQueryHaveLockedMessages(AsyncQueryHandler asyncQueryHandler, long l, int n, boolean bl) {
        Uri uri;
        asyncQueryHandler.cancelOperation(n);
        Uri uri2 = Telephony.MmsSms.CONTENT_LOCKED_URI;
        if (l != -1) {
            uri = ContentUris.withAppendedId((Uri)uri2, (long)l);
        } else {
            uri = uri2;
            if (!bl) {
                uri = uri2.buildUpon().appendQueryParameter("privacy_flag", "0").build();
            }
        }
        asyncQueryHandler.startQuery(n, (Object)new Long(l), uri, ALL_THREADS_PROJECTION, null, null, "date DESC limit 1");
    }

    public static void startQueryHaveLockedMessages(AsyncQueryHandler asyncQueryHandler, Collection<Long> collection, int n) {
        asyncQueryHandler.cancelOperation(n);
        String string = "thread_id IN (" + TextUtils.join((CharSequence)",", collection) + ")";
        asyncQueryHandler.startQuery(n, collection, Telephony.MmsSms.CONTENT_LOCKED_URI, ALL_THREADS_PROJECTION, string, null, "date DESC limit 1");
    }

    public static void startQuerySpSentinelId(AsyncQueryHandler asyncQueryHandler, int n) {
        asyncQueryHandler.cancelOperation(n);
        asyncQueryHandler.startQuery(n, (Object)null, nAllThreadsUri.buildUpon().appendQueryParameter("privacy_flag", "0").build(), THREAD_ID, "(sp_type=1 AND stick_time=0)", null, "stick_time desc, date DESC limit 1");
    }

    public static void startUpdateThreads(AsyncQueryHandler asyncQueryHandler, int n, Set<Long> set, ContentValues contentValues) {
        asyncQueryHandler.startUpdate(n, (Object)null, Telephony.Threads.CONTENT_URI, contentValues, "_id in (" + TextUtils.join((CharSequence)",", set) + ")", null);
    }

    public static void updateCachedThreadStickTime(final Set<Long> set, final long l) {
        new Thread(new Runnable(){

            /*
             * Enabled aggressive block sorting
             * Enabled unnecessary exception pruning
             * Enabled aggressive exception aggregation
             */
            @Override
            public void run() {
                Iterator iterator = set.iterator();
                while (iterator.hasNext()) {
                    Object object = (Long)iterator.next();
                    Cache cache = Cache.getInstance();
                    synchronized (cache) {
                        object = Cache.get(object.longValue());
                        if (object == null) continue;
                    }
                    ((Conversation)object).mStickTime = l;
                }
                return;
            }
        }).start();
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static String verifySingleRecipient(Context context, long l, String string) {
        if (l <= 0) {
            return string;
        }
        String[] arrstring = context.getContentResolver().query(sAllThreadsUri, ALL_THREADS_PROJECTION, "_id=" + Long.toString((long)l), null, null);
        if (arrstring == null) {
            LogTag.error("verifySingleRecipient threadId: " + l + " resulted in NULL cursor", new Object[0]);
            return string;
        }
        if (!arrstring.moveToFirst()) {
            LogTag.error("verifySingleRecipient threadId: " + l + " can't moveToFirst", new Object[0]);
            return string;
        }
        String string2 = arrstring.getString(4);
        arrstring = string2.split(" ");
        if (arrstring.length != 1) return string;
        if (TextUtils.isEmpty((CharSequence)(arrstring = RecipientIdCache.getSingleAddressFromCanonicalAddressInDb(context, arrstring[0])))) return string;
        if (PhoneNumberUtils.compareLoosely((String)string, (String)arrstring)) return string;
        if (context instanceof Activity) {
            LogTag.warnPossibleRecipientMismatch("verifySingleRecipient for threadId: " + l, (Activity)context);
        }
        if (!Log.isLoggable((String)"Mms:threadcache", (int)2)) return arrstring;
        LogTag.debug("verifySingleRecipient for threadId: " + l, new Object[0]);
        return arrstring;
        finally {
            arrstring.close();
        }
    }

    public void clearThreadId() {
        synchronized (this) {
            Cache.remove(this.mThreadId);
            this.mThreadId = 0;
            return;
        }
    }

    public long ensureThreadId() {
        synchronized (this) {
            if (this.mThreadId <= 0 || this.mMessageCount == 0) {
                this.mThreadId = Conversation.getOrCreateThreadId(this.mContext, this.mRecipients);
            }
            long l = this.mThreadId;
            return l;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public boolean equals(Object object) {
        synchronized (this) {
            try {
                object = (Conversation)object;
                return this.mRecipients.equals((Object)object.mRecipients);
            }
            catch (ClassCastException var1_2) {
                return false;
            }
            catch (Throwable var1_3) {
                throw var1_3;
            }
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public Uri getBlockedUri() {
        synchronized (this) {
            block6 : {
                long l = this.mThreadId;
                if (l > 0) break block6;
                return null;
            }
            Uri uri = ContentUris.withAppendedId((Uri)ExtraTelephony.MmsSms.BLOCKED_THREAD_CONTENT_URI, (long)this.mThreadId);
            return uri;
        }
    }

    public String getContactNames() {
        StringBuilder stringBuilder = new StringBuilder();
        Iterator iterator = this.mRecipients.iterator();
        while (iterator.hasNext()) {
            Contact contact = (Contact)iterator.next();
            if (stringBuilder.length() > 0) {
                stringBuilder.append(", ");
            }
            stringBuilder.append(contact.getName());
        }
        return stringBuilder.toString();
    }

    public long getDate() {
        synchronized (this) {
            long l = this.mDate;
            return l;
        }
    }

    public String getDateString() {
        synchronized (this) {
            String string = this.mDateString;
            return string;
        }
    }

    public long getLastSimId() {
        synchronized (this) {
            long l = this.mLastSimId;
            return l;
        }
    }

    public int getMessageCount() {
        synchronized (this) {
            int n = this.mMessageCount;
            return n;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public long getPostMarkUnread() {
        synchronized (this) {
            Object object = this.mPostMarkUnreadObj;
            synchronized (object) {
                return this.mPostMarkUnread;
            }
        }
    }

    public boolean getPreMarkUnread() {
        return this.mPreMarkUnread;
    }

    public ContactList getRecipients() {
        synchronized (this) {
            ContactList contactList = this.mRecipients;
            return contactList;
        }
    }

    public String getSnippet() {
        synchronized (this) {
            String string = this.mSnippet;
            return string;
        }
    }

    public long getThreadId() {
        synchronized (this) {
            long l = this.mThreadId;
            return l;
        }
    }

    public int getUnreadMessageCount() {
        synchronized (this) {
            int n = this.mUnreadMessageCount;
            return n;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public Uri getUri() {
        synchronized (this) {
            block6 : {
                long l = this.mThreadId;
                if (l > 0) break block6;
                return null;
            }
            Uri uri = ContentUris.withAppendedId((Uri)Telephony.Threads.CONTENT_URI, (long)this.mThreadId);
            return uri;
        }
    }

    public boolean hasAttachment() {
        synchronized (this) {
            boolean bl = this.mHasAttachment;
            return bl;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public boolean hasDraft() {
        synchronized (this) {
            block6 : {
                long l = this.mThreadId;
                if (l > 0) break block6;
                return false;
            }
            boolean bl = DraftCache.getInstance().hasDraft(this.mThreadId);
            return bl;
        }
    }

    public boolean hasError() {
        synchronized (this) {
            boolean bl = this.mHasError;
            return bl;
        }
    }

    public int hashCode() {
        synchronized (this) {
            int n = this.mRecipients.hashCode();
            return n;
        }
    }

    public boolean isB2c() {
        synchronized (this) {
            boolean bl = this.mIsB2cConv;
            return bl;
        }
    }

    public boolean isChecked() {
        synchronized (this) {
            boolean bl = this.mIsChecked;
            return bl;
        }
    }

    public boolean isPrivate() {
        synchronized (this) {
            boolean bl = this.mPrivate;
            return bl;
        }
    }

    public boolean isServiceProvider() {
        synchronized (this) {
            boolean bl = this.mIsServiceProvider;
            return bl;
        }
    }

    public boolean isSticky() {
        if (this.mStickTime > 0) {
            return true;
        }
        return false;
    }

    public void loadRecipients(boolean bl, boolean bl2) {
        this.mRecipients.load(bl, bl2);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void markAsReadSync() {
        Object object = this.mMarkAsBlockedSyncer;
        synchronized (object) {
            Cursor cursor;
            Uri uri = this.getUri();
            if (uri != null) {
                this.buildReadContentValues();
                int n = 1;
                cursor = this.mContext.getContentResolver().query(uri, UNREAD_PROJECTION, "(read=0 OR seen=0)", null, null);
                if (cursor != null) {
                    n = cursor.getCount();
                    n = n > 0 ? 1 : 0;
                }
                if (n != 0) {
                    LogTag.debug("markAsRead: update read/seen for thread uri: %s", new Object[]{uri});
                    this.mContext.getContentResolver().update(uri, mReadContentValues, "(read=0 OR seen=0)", null);
                }
                this.setPostMarkUnread(System.currentTimeMillis());
                if (n != 0) {
                    MessagingNotification.blockingUpdateNewMessageIndicator(this.mContext, false, false);
                }
            }
            return;
            finally {
                cursor.close();
            }
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public void setDraftState(boolean bl) {
        synchronized (this) {
            block6 : {
                long l = this.mThreadId;
                if (l > 0) break block6;
                do {
                    return;
                    break;
                } while (true);
            }
            DraftCache.getInstance().setDraftState(this.mThreadId, bl);
            return;
        }
    }

    public void setIsChecked(boolean bl) {
        synchronized (this) {
            this.mIsChecked = bl;
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void setPostMarkUnread(long l) {
        synchronized (this) {
            Object object = this.mPostMarkUnreadObj;
            synchronized (object) {
                this.mPostMarkUnread = l;
                return;
            }
        }
    }

    public void setPreMarkUnread(boolean bl) {
        this.mPreMarkUnread = bl;
    }

    public void setPrivate(boolean bl) {
        synchronized (this) {
            this.mPrivate = bl;
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void setRecipients(ContactList contactList) {
        synchronized (this) {
            if (this.mInCache) {
                throw new IllegalStateException(String.format((String)"Unexpected recipient change in cached conversation %d", (Object[])new Object[]{this.mThreadId}));
            }
            this.mRecipients = contactList;
            this.initPrivateState();
            this.mThreadId = 0;
            return;
        }
    }

    public String toString() {
        synchronized (this) {
            String string = String.format((String)"%s (tid %d)", (Object[])new Object[]{this.mRecipients.toString(), this.mThreadId});
            return string;
        }
    }

    public void update() {
        if (this.mThreadId <= 0) {
            return;
        }
        this.loadFromThreadId(this.mThreadId);
    }

    private static class Cache {
        private static Cache sInstance = new Cache();
        private final HashMap<ContactList, Conversation> mRecipientsConversationMap = new HashMap(10);
        private final HashMap<Long, Conversation> mThreadIdConversationMap = new HashMap(10);

        private Cache() {
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        static Conversation get(long l) {
            Cache cache = sInstance;
            synchronized (cache) {
                return (Conversation)Cache.sInstance.mThreadIdConversationMap.get((Object)l);
            }
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        static Conversation get(ContactList object) {
            Cache cache = sInstance;
            synchronized (cache) {
                return (Conversation)Cache.sInstance.mRecipientsConversationMap.get(object);
            }
        }

        public static Cache getInstance() {
            return sInstance;
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        static void keepOnly(Set<Long> object) {
            Cache cache = sInstance;
            synchronized (cache) {
                ArrayList arrayList = new ArrayList();
                for (Conversation conversation : Cache.sInstance.mThreadIdConversationMap.values()) {
                    if (object.contains(conversation.getThreadId())) continue;
                    arrayList.add((Object)conversation.getThreadId());
                }
                object = arrayList.iterator();
                while (object.hasNext()) {
                    Cache.remove((Long)object.next());
                }
                return;
            }
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        static void put(Conversation conversation) {
            Cache cache = sInstance;
            synchronized (cache) {
                if (conversation.getThreadId() == 0) {
                    throw new IllegalStateException("cannot put a conversation with threadId = 0 into cache");
                }
                if (conversation.getThreadId() < 0) {
                    Conversation conversation2 = (Conversation)Cache.sInstance.mThreadIdConversationMap.get((Object)conversation.getThreadId());
                    if (conversation2 != null && conversation2 != conversation) {
                        LogTag.error("cache already contains conversation for thread %d", conversation.getThreadId());
                    }
                    Cache.sInstance.mThreadIdConversationMap.put((Object)conversation.getThreadId(), (Object)conversation);
                    conversation.mInCache = true;
                    return;
                }
                Conversation conversation3 = (Conversation)Cache.sInstance.mThreadIdConversationMap.get((Object)conversation.getThreadId());
                if (conversation3 != null && conversation3 != conversation) {
                    LogTag.error("cache already contains conversation for thread %d", conversation.getThreadId());
                }
                if ((conversation3 = (Conversation)Cache.sInstance.mRecipientsConversationMap.get((Object)conversation.getRecipients())) != null && conversation3 != conversation) {
                    LogTag.error("cache already contains conversation for recipeints %s", new Object[]{conversation.getRecipients()});
                }
                Cache.sInstance.mThreadIdConversationMap.put((Object)conversation.getThreadId(), (Object)conversation);
                Cache.sInstance.mRecipientsConversationMap.put((Object)conversation.getRecipients(), (Object)conversation);
                conversation.mInCache = true;
                return;
            }
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        static void remove(long l) {
            Cache cache = sInstance;
            synchronized (cache) {
                Conversation conversation = (Conversation)Cache.sInstance.mThreadIdConversationMap.get((Object)l);
                if (conversation == null) {
                    LogTag.debug("Failed removing thread %d", l);
                } else {
                    conversation.mInCache = false;
                    if (Cache.sInstance.mRecipientsConversationMap.remove((Object)conversation.getRecipients()) == null) {
                        LogTag.error("Failed removing thread %d from mRecipientsConversationMap", l);
                    }
                    if (Cache.sInstance.mThreadIdConversationMap.remove((Object)l) == null) {
                        LogTag.error("Failed removing thread %d from mThreadIdConversationMap", l);
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
        static boolean replace(Conversation conversation) {
            Cache cache = sInstance;
            synchronized (cache) {
                if (Cache.sInstance.mRecipientsConversationMap.remove((Object)conversation.getRecipients()) != null) {
                    Cache.sInstance.mRecipientsConversationMap.put((Object)conversation.getRecipients(), (Object)conversation);
                }
                if (Cache.sInstance.mThreadIdConversationMap.remove((Object)conversation.getThreadId()) != null) {
                    Cache.sInstance.mThreadIdConversationMap.put((Object)conversation.getThreadId(), (Object)conversation);
                }
                return true;
            }
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         * Converted monitor instructions to comments
         * Lifted jumps to return sites
         */
        static ArrayList<Conversation> searchForConversations(Context context, String object, boolean bl) {
            String string = object.toLowerCase();
            ArrayList arrayList = Lists.newArrayList();
            object = Lists.newArrayList();
            Object object2 = sInstance;
            // MONITORENTER : object2
            for (Conversation conversation : Cache.sInstance.mThreadIdConversationMap.values()) {
                Object object3 = conversation.getRecipients();
                if (object3.size() != 1) continue;
                object3 = (Contact)object3.get(0);
                if ((conversation.isB2c() || object3.getNumber().toLowerCase().indexOf(string) == -1) && object3.getName().toLowerCase().indexOf(string) == -1) continue;
                object.add((Object)conversation.getThreadId());
            }
            // MONITOREXIT : object2
            if (object.size() - 1 >= 0) {
                object2 = new StringBuilder();
                object2.append("_id IN ( " + TextUtils.join((CharSequence)",", (Iterable)object) + ")");
                string = nAllThreadsUri.buildUpon();
                object = bl ? "1" : "0";
                object = string.appendQueryParameter("privacy_flag", (String)object).build();
                context = context.getContentResolver();
                object2 = object2.toString();
                if ((context = context.query((Uri)object, new String[]{"_id"}, (String)object2, null, null)) != null) {
                    try {
                        context.moveToPosition(-1);
                    }
                    catch (Throwable var1_2) {
                        context.close();
                        throw var1_2;
                    }
                    while (context.moveToNext()) {
                        object = sInstance;
                        // MONITORENTER : object
                        object2 = (Conversation)Cache.sInstance.mThreadIdConversationMap.get((Object)context.getLong(0));
                        if (object2 != null) {
                            arrayList.add(object2);
                        }
                        // MONITOREXIT : object
                    }
                    context.close();
                }
            }
            Collections.sort((List)arrayList, (Comparator)new Comparator<Conversation>(){

                public int compare(Conversation conversation, Conversation conversation2) {
                    return Long.signum((long)(conversation2.getDate() - conversation.getDate()));
                }
            });
            return arrayList;
        }

    }

    public static final class TimedThreads {
        private static final HashSet<Long> mTimedThreads = Sets.newHashSet();

        public static void clear() {
            mTimedThreads.clear();
        }

        public static boolean hasTimedMessage(long l) {
            return mTimedThreads.contains((Object)l);
        }

        public static void setHasTimedMessage(long l) {
            mTimedThreads.add((Object)l);
        }
    }

}

