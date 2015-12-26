/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.database.AbstractWindowedCursor
 *  android.database.Cursor
 *  android.database.CursorWindow
 *  android.database.CursorWrapper
 *  android.os.Handler
 *  android.provider.Telephony
 *  android.provider.Telephony$Sms
 *  android.util.Log
 *  android.util.LruCache
 *  android.view.LayoutInflater
 *  android.view.View
 *  android.view.ViewGroup
 *  android.widget.AbsListView
 *  android.widget.AbsListView$RecyclerListener
 *  android.widget.CursorAdapter
 *  android.widget.ListView
 *  com.google.android.collect.Sets
 *  com.google.android.mms.MmsException
 *  java.lang.Integer
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.util.ArrayList
 *  java.util.HashSet
 */
package com.android.mms.ui;

import android.content.Context;
import android.database.AbstractWindowedCursor;
import android.database.Cursor;
import android.database.CursorWindow;
import android.database.CursorWrapper;
import android.os.Handler;
import android.provider.Telephony;
import android.util.Log;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import com.android.mms.audio.AudioItemCache;
import com.android.mms.model.ContactMessage;
import com.android.mms.ui.MessageItem;
import com.android.mms.ui.MessageListItem;
import com.android.mms.understand.UnderstandFactory;
import com.android.mms.understand.UnderstandMessage;
import com.android.mms.util.EditableListView;
import com.android.mms.util.MSimUtils;
import com.google.android.collect.Sets;
import com.google.android.mms.MmsException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class MessageListAdapter
extends CursorAdapter
implements EditableListView.EditableListIdMapper {
    static final String[] GROUP_MSG_PROJECTION;
    static final String[] PROJECTION;
    private AudioItemCache mAudioItemCache = new AudioItemCache();
    private String mBodySubstitution;
    private HashSet<Long> mCheckedIds = Sets.newHashSet();
    private final ColumnsMap mColumnsMap;
    private Context mContext;
    private long mConversationThreadId;
    private int mDeliverReportMode;
    private List<UnderstandMessage> mEmptyList = new ArrayList();
    private GetMsgItem mGetMsgItem;
    private boolean mGroupByTime;
    private String mHighlight;
    private long mHighlightId;
    protected LayoutInflater mInflater;
    private boolean mIsB2c;
    private boolean mIsCheckMode = false;
    private boolean mIsGroup;
    private boolean mIsPrivate;
    private boolean mIsReadOnly;
    private String mItemStyle;
    private final LruCache<Long, List<UnderstandMessage>> mItemUnderstandCache;
    private final LruCache<Long, MessageItem> mMessageItemCache;
    private Handler mMsgListItemHandler;
    private OnDataSetChangedListener mOnDataSetChangedListener;
    private float mTextSize;
    private String mThreadAddress;

    static {
        PROJECTION = new String[]{"transport_type", "_id", "thread_id", "address", "body", "date", "date_sent", "read", "type", "status", "locked", "error_code", "timed", "mx_status", "block_type", "sub", "sub_cs", "date", "date_sent", "read", "m_type", "msg_box", "d_rpt", "rr", "err_type", "locked", "ct_l", "m_size", "exp", "timed", "date_ms_part", "mx_status", "need_download", "snippet", "preview_type", "preview_data_ts", "sim_id", "st", "block_type", "mx_type", "mx_extension"};
        GROUP_MSG_PROJECTION = new String[]{"transport_type", "_id", "thread_id", "address", "body", "date", "date_sent", "read", "type", "status", "locked", "error_code", "timed", "mx_status", "block_type", "sub", "sub_cs", "date", "date_sent", "read", "m_type", "msg_box", "d_rpt", "rr", "err_type", "locked", "ct_l", "m_size", "exp", "timed", "date_ms_part", "mx_status", "need_download", "snippet", "preview_type", "preview_data_ts", "sim_id", "st", "block_type", "mx_type", "mx_extension", "count(*)"};
    }

    public MessageListAdapter(Context context, Cursor cursor, ListView listView, boolean bl, String string2, String string3, long l) {
        this(context, cursor, listView, bl, false, false, 0, string2, string3, l, false, false);
        this.initLayoutStyle(MessageListItem.Style.list.toString(), false, 0);
    }

    /*
     * Enabled aggressive block sorting
     */
    public MessageListAdapter(Context context, Cursor cursor, ListView listView, boolean bl, boolean bl2, boolean bl3, long l, String string2, String string3, long l2, boolean bl4, boolean bl5) {
        super(context, cursor, false);
        this.mGetMsgItem = new GetMsgItem(){

            @Override
            public MessageItem getCurrMessageItem(int n) {
                return MessageListAdapter.this.getCachedMessageItem(MessageListAdapter.this.mCursor, false);
            }

            @Override
            public MessageItem getNextMessageItem(int n) {
                MessageItem messageItem = null;
                if (MessageListAdapter.this.mCursor.moveToNext()) {
                    messageItem = MessageListAdapter.this.getCachedMessageItem(MessageListAdapter.this.mCursor, false);
                }
                MessageListAdapter.this.mCursor.moveToPrevious();
                return messageItem;
            }

            @Override
            public MessageItem getPreMessageItem(int n) {
                MessageItem messageItem = null;
                if (MessageListAdapter.this.mCursor.moveToPrevious()) {
                    messageItem = MessageListAdapter.this.getCachedMessageItem(MessageListAdapter.this.mCursor, false);
                }
                MessageListAdapter.this.mCursor.moveToNext();
                return messageItem;
            }
        };
        this.mContext = context;
        this.mHighlight = string2;
        this.mBodySubstitution = string3;
        this.mHighlightId = l2;
        this.mIsPrivate = bl4;
        this.mIsB2c = bl5;
        this.mInflater = (LayoutInflater)context.getSystemService("layout_inflater");
        this.mMessageItemCache = new LruCache(500);
        this.mItemUnderstandCache = new LruCache(500);
        this.mColumnsMap = bl ? new ColumnsMap() : new ColumnsMap(cursor);
        this.mIsGroup = bl2;
        this.mIsReadOnly = bl3;
        this.mConversationThreadId = l;
        listView.setRecyclerListener(new AbsListView.RecyclerListener(){

            public void onMovedToScrapHeap(View view) {
                if (view instanceof MessageListItem) {
                    ((MessageListItem)view).unbind();
                }
            }
        });
    }

    /*
     * Enabled aggressive block sorting
     */
    private void adjustCursorStartPosition(Cursor cursor, int n) {
        CursorWindow cursorWindow;
        int n2 = n;
        if (n < 0) {
            n2 = 0;
        }
        if (!(cursor instanceof CursorWrapper) || !((cursor = ((CursorWrapper)cursor).getWrappedCursor()) instanceof AbstractWindowedCursor) || (cursorWindow = ((AbstractWindowedCursor)cursor).getWindow()) == null || n2 >= cursorWindow.getStartPosition()) {
            return;
        }
        n = n2 -= 1000;
        if (n2 < 0) {
            n = 0;
        }
        cursor.moveToPosition(n);
    }

    /*
     * Enabled aggressive block sorting
     */
    private List<UnderstandMessage> createCachedItemUnderstand(MessageItem list) {
        List<UnderstandMessage> list2;
        List<UnderstandMessage> list3 = list2 = null;
        if ("sms".equals((Object)list.getType())) {
            if (Telephony.Sms.isOutgoingFolder((int)list.getBoxId())) {
                list3 = list2;
                if (list.getContactMessage() == null) {
                    list3 = UnderstandFactory.getUnderstandMessageList(list.getAddress(), "", list.getBody(), list.getDate(), 1);
                }
            } else {
                list3 = list2;
                if (list.getContactMessage() == null) {
                    list3 = UnderstandFactory.getUnderstandMessageList(list.getAddress(), "", list.getBody(), list.getDate());
                }
            }
        }
        list = list3;
        if (list3 != null) return list;
        return this.mEmptyList;
    }

    private MessageItem createCachedMessageItem(String object, Cursor cursor) {
        if (cursor != null && this.isCursorValid(cursor)) {
            try {
                object = new MessageItem(this.mContext, (String)object, cursor, this.mColumnsMap, this.mIsGroup, this.mIsReadOnly, this.mConversationThreadId, this.mThreadAddress);
                return object;
            }
            catch (MmsException var1_2) {
                Log.e((String)"MessageListAdapter", (String)"createCachedMessageItem :", (Throwable)var1_2);
            }
        }
        return null;
    }

    private List<UnderstandMessage> getCachedItemUnderstand(Cursor cursor) {
        String string2 = cursor.getString(this.mColumnsMap.mColumnMsgType);
        long l = cursor.getLong(this.mColumnsMap.mColumnMsgId);
        return (List)this.mItemUnderstandCache.get((Object)MessageListAdapter.getKey(string2, l));
    }

    private MessageItem getCachedMessageItem(Cursor cursor, boolean bl) {
        return this.getCachedMessageItem(cursor.getString(this.mColumnsMap.mColumnMsgType), cursor.getLong(this.mColumnsMap.mColumnMsgId), cursor, bl);
    }

    /*
     * Enabled aggressive block sorting
     */
    public static int getItemLayoutStyle(Context object, String string2, boolean bl, int n, boolean bl2, GetMsgItem object2, int n2) {
        object = object2.getCurrMessageItem(n2);
        if (object.getLayoutStyle() == -1) {
            long l;
            MessageItem messageItem = object2.getPreMessageItem(n2);
            object2 = object2.getNextMessageItem(n2);
            boolean bl3 = object.isMiXin();
            boolean bl4 = !bl;
            boolean bl5 = true;
            boolean bl6 = true;
            boolean bl7 = true;
            if (messageItem != null) {
                l = object.getDisplayDate() - messageItem.getDisplayDate();
                if (!bl || MSimUtils.getSlotIdBySimInfoId(object.getSimId()) != MSimUtils.getSlotIdBySimInfoId(messageItem.getSimId()) || l >= 600000) {
                    bl4 = true;
                }
                bl5 = bl6;
                if (!bl4) {
                    bl5 = bl6;
                    if (l < 20000) {
                        bl5 = bl6;
                        if (MessageListAdapter.isSameKindMessage((MessageItem)object, messageItem)) {
                            bl5 = false;
                        }
                    }
                }
                bl6 = bl3 ^ messageItem.isMiXin();
            } else {
                bl4 = true;
                bl6 = true;
            }
            bl3 = bl7;
            if (object2 != null) {
                l = object2.getDisplayDate();
                long l2 = object.getDisplayDate();
                bl3 = bl7;
                if (bl) {
                    bl3 = bl7;
                    if (l - l2 < 20000) {
                        bl3 = bl7;
                        if (MSimUtils.getSlotIdBySimInfoId(object2.getSimId()) == MSimUtils.getSlotIdBySimInfoId(object.getSimId())) {
                            bl3 = bl7;
                            if (MessageListAdapter.isSameKindMessage((MessageItem)object2, (MessageItem)object)) {
                                bl3 = false;
                            }
                        }
                    }
                }
            }
            if (!bl2) {
                bl6 = false;
            }
            object.initLayoutStyle(string2, bl4, n, bl6, bl5, bl3);
        }
        return object.getLayoutStyle();
    }

    private static long getKey(String string2, long l) {
        long l2 = l;
        if ("mms".equals((Object)string2)) {
            l2 = - l;
        }
        return l2;
    }

    private boolean isCursorValid(Cursor cursor) {
        if (cursor.isClosed() || cursor.isBeforeFirst() || cursor.isAfterLast()) {
            return false;
        }
        return true;
    }

    private static boolean isSameKindMessage(MessageItem messageItem, MessageItem messageItem2) {
        if (messageItem.isOutMessage() == messageItem2.isOutMessage()) {
            return true;
        }
        return false;
    }

    /*
     * Exception decompiling
     */
    public void bindView(View var1_1, Context var2_2, Cursor var3_3) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Statement already marked as first in another block
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.markFirstStatementInBlock(Op03SimpleStatement.java:412)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.Misc.markWholeBlock(Misc.java:219)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.ConditionalRewriter.considerAsSimpleIf(ConditionalRewriter.java:619)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.op3rewriters.ConditionalRewriter.identifyNonjumpingConditionals(ConditionalRewriter.java:45)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:669)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:220)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:165)
        // org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:91)
        // org.benf.cfr.reader.entities.Method.analyse(Method.java:354)
        // org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:751)
        // org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:683)
        // org.benf.cfr.reader.Main.doJar(Main.java:128)
        // org.benf.cfr.reader.Main.main(Main.java:178)
        throw new IllegalStateException("Decompilation failed");
    }

    public void cleanCache() {
        this.mItemUnderstandCache.evictAll();
        Log.v((String)"MessageListAdapter", (String)("cleanCache size is " + this.mItemUnderstandCache.size()));
    }

    public void enterCheckMode() {
        this.mIsCheckMode = true;
    }

    public void exitCheckMode() {
        this.mIsCheckMode = false;
        this.mCheckedIds = Sets.newHashSet();
    }

    public AudioItemCache getAudioItemCache() {
        return this.mAudioItemCache;
    }

    /*
     * Enabled aggressive block sorting
     */
    public MessageItem getCachedMessageItem(String list, long l, Cursor cursor, boolean bl) {
        MessageItem messageItem;
        l = MessageListAdapter.getKey(list, l);
        MessageItem messageItem2 = messageItem = (MessageItem)this.mMessageItemCache.get((Object)l);
        if (messageItem == null) {
            messageItem2 = this.createCachedMessageItem((String)((Object)list), cursor);
            if (messageItem2 == null) {
                return null;
            }
            this.mMessageItemCache.put((Object)l, (Object)messageItem2);
        }
        if (!bl) return messageItem2;
        if ((List)this.mItemUnderstandCache.get((Object)l) != null) return messageItem2;
        list = this.createCachedItemUnderstand(messageItem2);
        if (list != null) {
            this.mItemUnderstandCache.put((Object)l, list);
            return messageItem2;
        }
        Log.e((String)"MessageListAdapter", (String)"createCached item understand is null");
        return messageItem2;
    }

    public List<MessageItem> getCheckedItems(HashSet<Integer> object) {
        ArrayList arrayList = new ArrayList();
        if (object != null) {
            object = object.iterator();
            while (object.hasNext()) {
                arrayList.add((Object)this.getCachedMessageItem((Cursor)this.getItem(((Integer)object.next()).intValue()), false));
            }
        }
        return arrayList;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public int getItemViewType(int n) {
        boolean bl;
        this.adjustCursorStartPosition(this.mCursor, n - 1);
        this.mCursor.moveToPosition(n);
        Context context = this.mContext;
        String string2 = this.mItemStyle;
        boolean bl2 = this.mGroupByTime;
        int n2 = this.mDeliverReportMode;
        if (!this.mIsB2c) {
            bl = true;
            do {
                return MessageListAdapter.getItemLayoutStyle(context, string2, bl2, n2, bl, this.mGetMsgItem, n);
                break;
            } while (true);
        }
        bl = false;
        return MessageListAdapter.getItemLayoutStyle(context, string2, bl2, n2, bl, this.mGetMsgItem, n);
    }

    public int getViewTypeCount() {
        return 12;
    }

    public void initLayoutStyle(String string2, boolean bl, int n) {
        this.mItemStyle = string2;
        this.mGroupByTime = bl;
        this.mDeliverReportMode = n;
    }

    @Override
    public long mapPositionToId(int n) {
        Cursor cursor = (Cursor)this.getItem(n);
        return MessageListAdapter.getKey(cursor.getString(this.mColumnsMap.mColumnMsgType), cursor.getLong(this.mColumnsMap.mColumnMsgId));
    }

    /*
     * Enabled aggressive block sorting
     */
    public View newView(Context object, Cursor object2, ViewGroup viewGroup) {
        object = (object2 = this.getCachedMessageItem((Cursor)object2, false)).isListLayoutStyle() ? (MessageListItem)this.mInflater.inflate(2130968661, viewGroup, false) : (MessageListItem)this.mInflater.inflate(2130968659, viewGroup, false);
        object.setAudioItemCache(this.mAudioItemCache);
        object.bind((MessageItem)object2);
        object.setMsgListItemHandler(this.mMsgListItemHandler);
        return object;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void notifyDataSetChanged() {
        if (this.mOnDataSetChangedListener == null || !this.mOnDataSetChangedListener.needHoldCache()) {
            this.mMessageItemCache.evictAll();
        } else {
            Log.v((String)"MessageListAdapter", (String)"hold cache item");
        }
        super.notifyDataSetChanged();
        if (this.mOnDataSetChangedListener != null) {
            this.mOnDataSetChangedListener.onDataSetChanged(this);
        }
    }

    protected void onContentChanged() {
        if (this.getCursor() != null && !this.getCursor().isClosed() && this.mOnDataSetChangedListener != null) {
            this.mOnDataSetChangedListener.onContentChanged(this);
        }
    }

    public void setCheckedItem(HashSet<Long> hashSet) {
        if (hashSet == null) {
            this.mCheckedIds = Sets.newHashSet();
            return;
        }
        this.mCheckedIds = hashSet;
    }

    public void setMsgListItemHandler(Handler handler) {
        this.mMsgListItemHandler = handler;
    }

    public void setOnDataSetChangedListener(OnDataSetChangedListener onDataSetChangedListener) {
        this.mOnDataSetChangedListener = onDataSetChangedListener;
    }

    public void setTextSize(float f2) {
        this.mTextSize = f2;
    }

    public void setThreadAddress(String string2) {
        this.mThreadAddress = string2;
    }

    public static class ColumnsMap {
        public int mColumnCount;
        public int mColumnMmsBlockType;
        public int mColumnMmsContentLocation;
        public int mColumnMmsDate;
        public int mColumnMmsDateMsPart;
        public int mColumnMmsDateSent;
        public int mColumnMmsDeliveryReport;
        public int mColumnMmsErrorType;
        public int mColumnMmsExpiry;
        public int mColumnMmsLocked;
        public int mColumnMmsMessageBox;
        public int mColumnMmsMessageSize;
        public int mColumnMmsMessageType;
        public int mColumnMmsMxType;
        public int mColumnMmsNeedDownload;
        public int mColumnMmsPreviewDataTs;
        public int mColumnMmsPreviewType;
        public int mColumnMmsReadReport;
        public int mColumnMmsSnippet;
        public int mColumnMmsStatus;
        public int mColumnMmsSubject;
        public int mColumnMmsSubjectCharset;
        public int mColumnMmsTimed;
        public int mColumnMsgId;
        public int mColumnMsgType;
        public int mColumnMx2Type;
        public int mColumnMxExtension;
        public int mColumnSimId;
        public int mColumnSmsAddress;
        public int mColumnSmsBlockType;
        public int mColumnSmsBody;
        public int mColumnSmsDate;
        public int mColumnSmsDateSent;
        public int mColumnSmsErrorCode;
        public int mColumnSmsLocked;
        public int mColumnSmsMxType;
        public int mColumnSmsStatus;
        public int mColumnSmsTimed;
        public int mColumnSmsType;
        public int mColumnThreadId;

        public ColumnsMap() {
            this.mColumnMsgType = 0;
            this.mColumnMsgId = 1;
            this.mColumnThreadId = 2;
            this.mColumnSmsAddress = 3;
            this.mColumnSmsBody = 4;
            this.mColumnSmsDate = 5;
            this.mColumnSmsDateSent = 6;
            this.mColumnSmsType = 8;
            this.mColumnSmsStatus = 9;
            this.mColumnSmsLocked = 10;
            this.mColumnSmsErrorCode = 11;
            this.mColumnSmsTimed = 12;
            this.mColumnSmsMxType = 13;
            this.mColumnSmsBlockType = 14;
            this.mColumnMmsSubject = 15;
            this.mColumnMmsSubjectCharset = 16;
            this.mColumnMmsDate = 17;
            this.mColumnMmsDateSent = 18;
            this.mColumnMmsMessageType = 20;
            this.mColumnMmsMessageBox = 21;
            this.mColumnMmsDeliveryReport = 22;
            this.mColumnMmsReadReport = 23;
            this.mColumnMmsErrorType = 24;
            this.mColumnMmsLocked = 25;
            this.mColumnMmsContentLocation = 26;
            this.mColumnMmsMessageSize = 27;
            this.mColumnMmsExpiry = 28;
            this.mColumnMmsTimed = 29;
            this.mColumnMmsDateMsPart = 30;
            this.mColumnMmsMxType = 31;
            this.mColumnMmsNeedDownload = 32;
            this.mColumnMmsSnippet = 33;
            this.mColumnMmsPreviewType = 34;
            this.mColumnMmsPreviewDataTs = 35;
            this.mColumnMmsBlockType = 38;
            this.mColumnSimId = 36;
            this.mColumnMmsStatus = 37;
            this.mColumnMx2Type = 39;
            this.mColumnCount = 41;
            this.mColumnMxExtension = 40;
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        public ColumnsMap(Cursor cursor) {
            try {
                this.mColumnMsgType = cursor.getColumnIndexOrThrow("transport_type");
            }
            catch (IllegalArgumentException var2_3) {
                Log.w((String)"colsMap", (String)var2_3.getMessage());
            }
            try {
                this.mColumnMsgId = cursor.getColumnIndexOrThrow("_id");
            }
            catch (IllegalArgumentException var2_5) {
                Log.w((String)"colsMap", (String)var2_5.getMessage());
            }
            try {
                this.mColumnThreadId = cursor.getColumnIndexOrThrow("thread_id");
            }
            catch (IllegalArgumentException var2_6) {
                Log.w((String)"colsMap", (String)var2_6.getMessage());
            }
            try {
                this.mColumnSmsAddress = cursor.getColumnIndexOrThrow("address");
            }
            catch (IllegalArgumentException var2_7) {
                Log.w((String)"colsMap", (String)var2_7.getMessage());
            }
            try {
                this.mColumnSmsBody = cursor.getColumnIndexOrThrow("body");
            }
            catch (IllegalArgumentException var2_8) {
                Log.w((String)"colsMap", (String)var2_8.getMessage());
            }
            try {
                this.mColumnSmsDate = cursor.getColumnIndexOrThrow("date");
            }
            catch (IllegalArgumentException var2_9) {
                Log.w((String)"colsMap", (String)var2_9.getMessage());
            }
            try {
                this.mColumnSmsDateSent = cursor.getColumnIndexOrThrow("date_sent");
            }
            catch (IllegalArgumentException var2_10) {
                Log.w((String)"colsMap", (String)var2_10.getMessage());
            }
            try {
                this.mColumnSmsType = cursor.getColumnIndexOrThrow("type");
            }
            catch (IllegalArgumentException var2_11) {
                Log.w((String)"colsMap", (String)var2_11.getMessage());
            }
            try {
                this.mColumnSmsStatus = cursor.getColumnIndexOrThrow("status");
            }
            catch (IllegalArgumentException var2_12) {
                Log.w((String)"colsMap", (String)var2_12.getMessage());
            }
            try {
                this.mColumnSmsLocked = cursor.getColumnIndexOrThrow("locked");
            }
            catch (IllegalArgumentException var2_13) {
                Log.w((String)"colsMap", (String)var2_13.getMessage());
            }
            try {
                this.mColumnSmsErrorCode = cursor.getColumnIndexOrThrow("error_code");
            }
            catch (IllegalArgumentException var2_14) {
                Log.w((String)"colsMap", (String)var2_14.getMessage());
            }
            try {
                this.mColumnSmsTimed = cursor.getColumnIndexOrThrow("timed");
            }
            catch (IllegalArgumentException var2_15) {
                Log.w((String)"colsMap", (String)var2_15.getMessage());
            }
            try {
                this.mColumnSmsMxType = cursor.getColumnIndexOrThrow("mx_status");
            }
            catch (IllegalArgumentException var2_16) {
                Log.w((String)"colsMap", (String)var2_16.getMessage());
            }
            try {
                this.mColumnSmsBlockType = cursor.getColumnIndexOrThrow("block_type");
            }
            catch (IllegalArgumentException var2_17) {
                Log.w((String)"colsMap", (String)var2_17.getMessage());
            }
            try {
                this.mColumnMmsSubject = cursor.getColumnIndexOrThrow("sub");
            }
            catch (IllegalArgumentException var2_18) {
                Log.w((String)"colsMap", (String)var2_18.getMessage());
            }
            try {
                this.mColumnMmsSubjectCharset = cursor.getColumnIndexOrThrow("sub_cs");
            }
            catch (IllegalArgumentException var2_19) {
                Log.w((String)"colsMap", (String)var2_19.getMessage());
            }
            try {
                this.mColumnMmsDate = cursor.getColumnIndexOrThrow("date");
            }
            catch (IllegalArgumentException var2_20) {
                Log.w((String)"colsMap", (String)var2_20.getMessage());
            }
            try {
                this.mColumnMmsDateSent = cursor.getColumnIndexOrThrow("date_sent");
            }
            catch (IllegalArgumentException var2_21) {
                Log.w((String)"colsMap", (String)var2_21.getMessage());
            }
            try {
                this.mColumnMmsMessageType = cursor.getColumnIndexOrThrow("m_type");
            }
            catch (IllegalArgumentException var2_22) {
                Log.w((String)"colsMap", (String)var2_22.getMessage());
            }
            try {
                this.mColumnMmsMessageBox = cursor.getColumnIndexOrThrow("msg_box");
            }
            catch (IllegalArgumentException var2_23) {
                Log.w((String)"colsMap", (String)var2_23.getMessage());
            }
            try {
                this.mColumnMmsDeliveryReport = cursor.getColumnIndexOrThrow("d_rpt");
            }
            catch (IllegalArgumentException var2_24) {
                Log.w((String)"colsMap", (String)var2_24.getMessage());
            }
            try {
                this.mColumnMmsReadReport = cursor.getColumnIndexOrThrow("rr");
            }
            catch (IllegalArgumentException var2_25) {
                Log.w((String)"colsMap", (String)var2_25.getMessage());
            }
            try {
                this.mColumnMmsErrorType = cursor.getColumnIndexOrThrow("err_type");
            }
            catch (IllegalArgumentException var2_26) {
                Log.w((String)"colsMap", (String)var2_26.getMessage());
            }
            try {
                this.mColumnMmsLocked = cursor.getColumnIndexOrThrow("locked");
            }
            catch (IllegalArgumentException var2_27) {
                Log.w((String)"colsMap", (String)var2_27.getMessage());
            }
            try {
                this.mColumnMmsContentLocation = cursor.getColumnIndexOrThrow("ct_l");
            }
            catch (IllegalArgumentException var2_28) {
                Log.w((String)"colsMap", (String)var2_28.getMessage());
            }
            try {
                this.mColumnMmsMessageSize = cursor.getColumnIndexOrThrow("m_size");
            }
            catch (IllegalArgumentException var2_29) {
                Log.w((String)"colsMap", (String)var2_29.getMessage());
            }
            try {
                this.mColumnMmsExpiry = cursor.getColumnIndexOrThrow("exp");
            }
            catch (IllegalArgumentException var2_30) {
                Log.w((String)"colsMap", (String)var2_30.getMessage());
            }
            try {
                this.mColumnMmsTimed = cursor.getColumnIndexOrThrow("timed");
            }
            catch (IllegalArgumentException var2_31) {
                Log.w((String)"colsMap", (String)var2_31.getMessage());
            }
            try {
                this.mColumnMmsDateMsPart = cursor.getColumnIndexOrThrow("date_ms_part");
            }
            catch (IllegalArgumentException var2_32) {
                Log.w((String)"colsMap", (String)var2_32.getMessage());
            }
            try {
                this.mColumnMmsMxType = cursor.getColumnIndexOrThrow("mx_status");
            }
            catch (IllegalArgumentException var2_33) {
                Log.w((String)"colsMap", (String)var2_33.getMessage());
            }
            try {
                this.mColumnMmsNeedDownload = cursor.getColumnIndexOrThrow("need_download");
            }
            catch (IllegalArgumentException var2_34) {
                Log.w((String)"colsMap", (String)var2_34.getMessage());
            }
            try {
                this.mColumnMmsSnippet = cursor.getColumnIndexOrThrow("snippet");
            }
            catch (IllegalArgumentException var2_35) {
                Log.w((String)"colsMap", (String)var2_35.getMessage());
            }
            try {
                this.mColumnMmsPreviewType = cursor.getColumnIndexOrThrow("preview_type");
            }
            catch (IllegalArgumentException var2_36) {
                Log.w((String)"colsMap", (String)var2_36.getMessage());
            }
            try {
                this.mColumnMmsPreviewDataTs = cursor.getColumnIndexOrThrow("preview_data_ts");
            }
            catch (IllegalArgumentException var2_37) {
                Log.w((String)"colsMap", (String)var2_37.getMessage());
            }
            try {
                this.mColumnMmsBlockType = cursor.getColumnIndexOrThrow("block_type");
            }
            catch (IllegalArgumentException var2_38) {
                Log.w((String)"colsMap", (String)var2_38.getMessage());
            }
            try {
                this.mColumnSimId = cursor.getColumnIndexOrThrow("sim_id");
            }
            catch (IllegalArgumentException var2_39) {
                Log.w((String)"colsMap", (String)var2_39.getMessage());
            }
            try {
                this.mColumnCount = cursor.getColumnIndexOrThrow("count(*)");
            }
            catch (IllegalArgumentException var2_40) {
                Log.w((String)"colsMap", (String)var2_40.getMessage());
            }
            try {
                this.mColumnMx2Type = cursor.getColumnIndexOrThrow("mx_type");
            }
            catch (IllegalArgumentException var2_41) {
                Log.w((String)"colsMap", (String)var2_41.getMessage());
            }
            try {
                this.mColumnMxExtension = cursor.getColumnIndexOrThrow("mx_extension");
                return;
            }
            catch (IllegalArgumentException var1_2) {
                Log.w((String)"colsMap", (String)var1_2.getMessage());
                return;
            }
        }
    }

    public static interface GetMsgItem {
        public MessageItem getCurrMessageItem(int var1);

        public MessageItem getNextMessageItem(int var1);

        public MessageItem getPreMessageItem(int var1);
    }

    public static interface OnDataSetChangedListener {
        public boolean needHoldCache();

        public void onContentChanged(MessageListAdapter var1);

        public void onDataSetChanged(MessageListAdapter var1);
    }

}

