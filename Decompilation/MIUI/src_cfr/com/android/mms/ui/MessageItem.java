/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ContentResolver
 *  android.content.ContentUris
 *  android.content.Context
 *  android.content.SharedPreferences
 *  android.database.Cursor
 *  android.net.Uri
 *  android.preference.PreferenceManager
 *  android.provider.Telephony
 *  android.provider.Telephony$Mms
 *  android.provider.Telephony$Sms
 *  android.text.TextUtils
 *  com.google.android.mms.MmsException
 *  com.google.android.mms.pdu.EncodedStringValue
 *  com.google.android.mms.pdu.MiuiPduPersister
 *  java.lang.Class
 *  java.lang.Enum
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 */
package com.android.mms.ui;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.provider.Telephony;
import android.text.TextUtils;
import com.android.mms.data.Contact;
import com.android.mms.model.ContactMessage;
import com.android.mms.model.ContactParser;
import com.android.mms.ui.MessageListAdapter;
import com.android.mms.ui.MessageListItem;
import com.android.mms.ui.MessageUtils;
import com.android.mms.ui.SimplePduDoc;
import com.android.mms.util.AddressUtils;
import com.google.android.mms.MmsException;
import com.google.android.mms.pdu.EncodedStringValue;
import com.google.android.mms.pdu.MiuiPduPersister;
import com.xiaomi.mms.mx.data.Attachment;
import com.xiaomi.mms.mx.utils.Mx2ExtentionHelper;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MessageItem {
    private String mAddress;
    private String mBody;
    private final int mBoxId;
    private Contact mContact;
    private ContactMessage mContactMessage;
    private final Context mContext;
    private long mDate;
    private long mDateSent;
    private int mDeliveRepotrMode = 0;
    private DeliveryStatus mDeliveryStatus;
    private int mErrorCode;
    private int mErrorType;
    private String mExpireTimestamp;
    private ArrayList<FailedItem> mFailedMsgInGroup;
    private boolean mHasBubbleDistance = false;
    private boolean mIsGroup;
    private boolean mIsReadOnly;
    private boolean mIsReferenceGroup;
    private int mLayoutStyle = -1;
    private boolean mLocked;
    private int mMessageSize;
    private int mMessageType;
    private Uri mMessageUri;
    private long mMmsPreviewDataTs;
    private int mMmsPreviewType;
    private String mMmsSnippet;
    private int mMmsStatus;
    private final long mMsgId;
    private List<Attachment> mMx2Attachments;
    private int mMx2Type;
    private int mMxType;
    private boolean mNeedDownload;
    private int mQueueMsgInGroup;
    private boolean mReadReport;
    private ArrayList<Uri> mResendMsgInGroup;
    private String mSendingAddressInGroup;
    private String mSendingBodyInGroup;
    private ContactMessage mSendingContactMessageInGroup;
    private int mSentMsgInGroup;
    private boolean mShowMxType = true;
    private boolean mShowTimeStamp = true;
    private final long mSimId;
    private SoftReference<SimplePduDoc> mSimplePduDocRef;
    private String mSubject;
    private String mTextContentType;
    private String mThreadAddress;
    private final long mThreadId;
    private long mTimedValue;
    private int mTotalMsgInGroup;
    private final String mType;

    public MessageItem(Context context, String string2, Cursor cursor, MessageListAdapter.ColumnsMap columnsMap, boolean bl) throws MmsException {
        this(context, string2, cursor, columnsMap, false, false, 0, null, bl);
        if (bl) {
            this.mLayoutStyle = 11;
        }
    }

    public MessageItem(Context context, String string2, Cursor cursor, MessageListAdapter.ColumnsMap columnsMap, boolean bl, boolean bl2, long l, String string3) throws MmsException {
        this(context, string2, cursor, columnsMap, bl, bl2, l, string3, false);
    }

    /*
     * Enabled aggressive block sorting
     */
    public MessageItem(Context context, String string2, Cursor object, MessageListAdapter.ColumnsMap columnsMap, boolean bl, boolean bl2, long l, String string3, boolean bl3) throws MmsException {
        this.mContext = context;
        this.mThreadAddress = string3;
        this.mMsgId = object.getLong(columnsMap.mColumnMsgId);
        this.mThreadId = object.getLong(columnsMap.mColumnThreadId);
        this.mSimId = object.getLong(columnsMap.mColumnSimId);
        this.mType = string2;
        this.mIsGroup = bl;
        this.mIsReadOnly = bl2;
        bl = l > 0 && this.mThreadId != l;
        this.mIsReferenceGroup = bl;
        this.mTotalMsgInGroup = 0;
        this.mFailedMsgInGroup = new ArrayList();
        this.mResendMsgInGroup = new ArrayList();
        this.mQueueMsgInGroup = 0;
        this.mSentMsgInGroup = 0;
        this.mSendingAddressInGroup = null;
        this.mSendingBodyInGroup = null;
        if ("sms".equals((Object)string2)) {
            this.mReadReport = false;
            this.mBoxId = object.getInt(columnsMap.mColumnSmsType);
            if (!bl3) {
                l = object.getLong(columnsMap.mColumnSmsStatus);
                this.mDeliveryStatus = l == -1 ? DeliveryStatus.NONE : (l >= 64 ? DeliveryStatus.FAILED : (l >= 32 ? (this.mBoxId == 2 ? DeliveryStatus.SENT : DeliveryStatus.PENDING) : DeliveryStatus.RECEIVED));
            }
            this.mMessageUri = ContentUris.withAppendedId((Uri)Telephony.Sms.CONTENT_URI, (long)this.mMsgId);
            this.mAddress = object.getString(columnsMap.mColumnSmsAddress);
            this.mBody = object.getString(columnsMap.mColumnSmsBody);
            this.mDate = object.getLong(columnsMap.mColumnSmsDate);
            this.mDateSent = object.getLong(columnsMap.mColumnSmsDateSent);
            bl = object.getInt(columnsMap.mColumnSmsLocked) != 0;
            this.mLocked = bl;
            this.mErrorCode = object.getInt(columnsMap.mColumnSmsErrorCode);
            this.mTimedValue = object.getLong(columnsMap.mColumnSmsTimed);
            this.mMxType = object.getInt(columnsMap.mColumnSmsMxType);
            if (this.mIsGroup) {
                this.mTotalMsgInGroup = object.getInt(columnsMap.mColumnCount);
                string2 = context.getContentResolver();
                object = Telephony.Sms.CONTENT_URI;
                l = this.mThreadId;
                long l2 = this.mDate;
                if ((string2 = string2.query((Uri)object, new String[]{"address", "_id"}, "thread_id=? AND date=? AND type=?", new String[]{String.valueOf((long)l), String.valueOf((long)l2), String.valueOf((int)5)}, null)) != null) {
                    while (string2.moveToNext()) {
                        object = string2.getString(0);
                        columnsMap = ContentUris.withAppendedId((Uri)Telephony.Sms.CONTENT_URI, (long)string2.getLong(1));
                        this.mFailedMsgInGroup.add((Object)new FailedItem((String)object, (Uri)columnsMap));
                        this.mResendMsgInGroup.add((Object)columnsMap);
                    }
                    string2.close();
                }
                string2 = context.getContentResolver();
                object = Telephony.Sms.CONTENT_URI;
                l = this.mThreadId;
                l2 = this.mDate;
                if ((string2 = string2.query((Uri)object, new String[]{"address", "body"}, "thread_id=? AND date=? AND type=?", new String[]{String.valueOf((long)l), String.valueOf((long)l2), String.valueOf((int)6)}, "mx_status DESC, date ASC")) != null) {
                    this.mQueueMsgInGroup = string2.getCount();
                    if (string2.moveToFirst()) {
                        this.mSendingAddressInGroup = string2.getString(0);
                        this.mSendingBodyInGroup = string2.getString(1);
                    }
                    string2.close();
                }
                if (this.mSendingAddressInGroup == null) {
                    this.mSendingAddressInGroup = this.mAddress;
                }
                if (this.mSendingBodyInGroup == null) {
                    this.mSendingBodyInGroup = this.mBody;
                }
                this.mSendingContactMessageInGroup = ContactParser.getContactParser(this.mContext).parseFromMessage(this.mSendingBodyInGroup);
                context = context.getContentResolver();
                string2 = Telephony.Sms.CONTENT_URI;
                l = this.mThreadId;
                l2 = this.mDate;
                if ((context = context.query((Uri)string2, new String[]{"address"}, "thread_id=? AND date=? AND (type=? OR type=? OR type=?)", new String[]{String.valueOf((long)l), String.valueOf((long)l2), String.valueOf((int)4), String.valueOf((int)5), String.valueOf((int)2)}, null)) != null) {
                    this.mSentMsgInGroup = context.getCount();
                    context.close();
                }
            }
            this.mContactMessage = ContactParser.getContactParser(this.mContext).parseFromMessage(this.mBody);
            if (!Telephony.Sms.isOutgoingFolder((int)this.mBoxId)) {
                this.mContact = Contact.get(this.mAddress).load(false, false);
                return;
            }
            if (this.mIsGroup) {
                this.mContact = Contact.get(this.mSendingAddressInGroup).load(false, false);
                return;
            } else {
                if (!this.mIsReadOnly) return;
                {
                    this.mContact = Contact.get(this.mAddress).load(false, false);
                    return;
                }
            }
        } else {
            if (!"mms".equals((Object)string2)) {
                throw new MmsException("Unknown type of the message: " + string2);
            }
            this.mMessageUri = ContentUris.withAppendedId((Uri)Telephony.Mms.CONTENT_URI, (long)this.mMsgId);
            this.mBoxId = object.getInt(columnsMap.mColumnMmsMessageBox);
            this.mMessageType = object.getInt(columnsMap.mColumnMmsMessageType);
            this.mDate = object.getLong(columnsMap.mColumnMmsDate) * 1000 + object.getLong(columnsMap.mColumnMmsDateMsPart);
            this.mDateSent = object.getLong(columnsMap.mColumnMmsDateSent) * 1000;
            this.mErrorType = object.getInt(columnsMap.mColumnMmsErrorType);
            this.mTimedValue = object.getLong(columnsMap.mColumnMmsTimed);
            this.mMxType = object.getInt(columnsMap.mColumnMmsMxType);
            this.mMmsStatus = object.getInt(columnsMap.mColumnMmsStatus);
            this.mMx2Type = object.getInt(columnsMap.mColumnMx2Type);
            string2 = object.getString(columnsMap.mColumnMxExtension);
            if (!TextUtils.isEmpty((CharSequence)string2)) {
                this.mMx2Attachments = Mx2ExtentionHelper.praseAttachmentsExtentionString(string2);
            }
            if (!TextUtils.isEmpty((CharSequence)(string2 = object.getString(columnsMap.mColumnMmsSubject)))) {
                this.mSubject = new EncodedStringValue(object.getInt(columnsMap.mColumnMmsSubjectCharset), MiuiPduPersister.getBytes((String)string2)).getString();
            }
            bl = object.getInt(columnsMap.mColumnMmsLocked) != 0;
            this.mLocked = bl;
            bl = object.getInt(columnsMap.mColumnMmsNeedDownload) != 0;
            this.mNeedDownload = bl;
            this.mMmsPreviewType = object.getInt(columnsMap.mColumnMmsPreviewType);
            if (!this.mNeedDownload && this.mMmsPreviewType == 0) {
                MessageUtils.startMmsPreviewService(this.mContext, this.mMsgId);
            }
            this.mMmsSnippet = object.getString(columnsMap.mColumnMmsSnippet);
            this.mMmsPreviewDataTs = object.getLong(columnsMap.mColumnMmsPreviewDataTs);
            if (130 == this.mMessageType) {
                this.mDeliveryStatus = DeliveryStatus.NONE;
                this.mBody = object.getString(columnsMap.mColumnMmsContentLocation);
                this.mMessageSize = object.getInt(columnsMap.mColumnMmsMessageSize);
                this.mExpireTimestamp = this.getExpireTimestampStr(context, (long)object.getInt(columnsMap.mColumnMmsExpiry) * 1000);
            } else {
                if (bl3) {
                    this.mAddress = this.mMessageType == 132 || this.mMessageType == 128 ? null : context.getString(2131361808);
                } else if (this.mMessageType == 132) {
                    this.mAddress = this.mThreadAddress;
                    this.interpretFrom(this.mMessageUri);
                } else if (this.mMessageType == 128) {
                    this.mAddress = this.mThreadAddress;
                    this.interpretTo(this.mMessageUri);
                } else {
                    this.mAddress = context.getString(2131361808);
                }
                this.mDeliveryStatus = DeliveryStatus.NONE;
                this.mReadReport = false;
                if (!this.isMiXin() && !bl3 && this.isOutMessage() && !this.mIsGroup) {
                    int n;
                    int n2 = n = this.mMmsStatus;
                    if (n <= 0) {
                        n2 = MessageUtils.getMmsDeliveryStatus(this.mContext, this);
                    }
                    switch (n2) {
                        default: {
                            this.mDeliveryStatus = DeliveryStatus.FAILED;
                            break;
                        }
                        case 0: {
                            this.mDeliveryStatus = DeliveryStatus.NONE;
                            break;
                        }
                        case 129: 
                        case 134: {
                            this.mDeliveryStatus = DeliveryStatus.RECEIVED;
                            break;
                        }
                        case 130: {
                            this.mDeliveryStatus = DeliveryStatus.REJECTED;
                            break;
                        }
                        case 128: {
                            this.mDeliveryStatus = DeliveryStatus.EXPIRED;
                        }
                    }
                }
                this.mBody = this.mMmsSnippet;
                this.mTextContentType = "text/plain";
                this.mMessageSize = 0;
            }
            if (!this.mIsGroup) return;
            {
                this.mSendingBodyInGroup = this.mBody;
                return;
            }
        }
    }

    public MessageItem(Context context, String string2, Uri uri, long l, String string3, long l2) {
        this.mContext = context;
        this.mType = string2;
        this.mMessageUri = uri;
        this.mDate = l;
        this.mBody = string3;
        this.mSimId = l2;
        this.mBoxId = 1;
        this.mMsgId = 0;
        this.mThreadId = 0;
        this.mThreadAddress = null;
    }

    private String getExpireTimestampStr(Context context, long l) {
        return context.getString(2131361811, new Object[]{MessageUtils.getRelativeTimeStamp(l)});
    }

    private void interpretFrom(Uri uri) {
        if (TextUtils.isEmpty((CharSequence)this.mAddress)) {
            this.mAddress = AddressUtils.getFrom(this.mContext, uri);
        }
        if (!TextUtils.isEmpty((CharSequence)this.mAddress)) {
            this.mContact = Contact.get(this.mAddress).load(false, false);
        }
    }

    private void interpretTo(Uri uri) {
        if (TextUtils.isEmpty((CharSequence)this.mAddress)) {
            this.mAddress = AddressUtils.getTo(this.mContext, this.mMessageUri);
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void addToResend(Uri uri) {
        synchronized (this) {
            boolean bl;
            block4 : {
                boolean bl2 = false;
                Iterator iterator = this.mResendMsgInGroup.iterator();
                do {
                    bl = bl2;
                    if (!iterator.hasNext()) break block4;
                } while (!((Uri)iterator.next()).equals((Object)uri));
                return;
            }
            if (!bl) {
                this.mResendMsgInGroup.add((Object)uri);
            }
            return;
        }
    }

    public ArrayList<FailedItem> failedMsgInGroup() {
        return this.mFailedMsgInGroup;
    }

    public String getAddress() {
        return this.mAddress;
    }

    public String getBody() {
        if (this.mNeedDownload && this.mMxType <= 0) {
            return this.mContext.getString(2131362195);
        }
        if (this.isMms() && this.mMmsPreviewType == 0 && this.mMxType <= 0) {
            return this.mContext.getString(2131362207);
        }
        return this.mBody;
    }

    public int getBoxId() {
        return this.mBoxId;
    }

    public Contact getContact() {
        return this.mContact;
    }

    public ContactMessage getContactMessage() {
        return this.mContactMessage;
    }

    public String getContactName() {
        if (this.mContact != null) {
            return this.mContact.getName();
        }
        return this.mContext.getString(2131361808);
    }

    public long getDate() {
        return this.mDate;
    }

    public long getDateSent() {
        return this.mDateSent;
    }

    public int getDeliverReportMode() {
        return this.mDeliveRepotrMode;
    }

    public DeliveryStatus getDeliveryStatus() {
        return this.mDeliveryStatus;
    }

    public long getDisplayDate() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences((Context)this.mContext);
        if (this.mDateSent > 0 && "1".equals((Object)MessageUtils.getDateType(sharedPreferences))) {
            return this.mDateSent;
        }
        return this.mDate;
    }

    public String getExpireTimestamp() {
        return this.mExpireTimestamp;
    }

    public boolean getHasBubbleDistance() {
        return this.mHasBubbleDistance;
    }

    public int getLayoutStyle() {
        return this.mLayoutStyle;
    }

    public int getMessageSize() {
        return this.mMessageSize;
    }

    public int getMessageType() {
        return this.mMessageType;
    }

    public Uri getMessageUri() {
        return this.mMessageUri;
    }

    public long getMmsPreviewDataTs() {
        return this.mMmsPreviewDataTs;
    }

    public int getMmsPreviewType() {
        return this.mMmsPreviewType;
    }

    public String getMmsSnippet() {
        return this.mMmsSnippet;
    }

    public int getMmsStatus() {
        return this.mMmsStatus;
    }

    public long getMsgId() {
        return this.mMsgId;
    }

    public List<Attachment> getMx2Attachments() {
        return this.mMx2Attachments;
    }

    public int getMx2Type() {
        return this.mMx2Type;
    }

    public int getMxType() {
        return this.mMxType;
    }

    public String getMxTypeName() {
        if (!this.mShowMxType) {
            return null;
        }
        if (this.mMxType != 0) {
            return this.mContext.getString(2131362170);
        }
        return this.mContext.getString(2131362169);
    }

    public String getSendingBodyInGroup() {
        return this.mSendingBodyInGroup;
    }

    public ContactMessage getSendingContactMessageInGroup() {
        return this.mSendingContactMessageInGroup;
    }

    public boolean getShowTimeStamp() {
        return this.mShowTimeStamp;
    }

    public long getSimId() {
        return this.mSimId;
    }

    public SimplePduDoc getSimplePduDoc() {
        SimplePduDoc simplePduDoc = null;
        if (this.mSimplePduDocRef != null) {
            simplePduDoc = this.mSimplePduDocRef.get();
        }
        SimplePduDoc simplePduDoc2 = simplePduDoc;
        if (simplePduDoc == null) {
            simplePduDoc2 = new SimplePduDoc(this.mContext);
            simplePduDoc2.load(this.mMessageUri);
            this.mSimplePduDocRef = new SoftReference<SimplePduDoc>(simplePduDoc2);
        }
        return simplePduDoc2;
    }

    public String getSubject() {
        return this.mSubject;
    }

    public String getTextContentType() {
        return this.mTextContentType;
    }

    public long getThreadId() {
        return this.mThreadId;
    }

    public long getTimedValue() {
        return this.mTimedValue;
    }

    public String getType() {
        return this.mType;
    }

    boolean hasText() {
        if (this.isSms() && !this.mBody.isEmpty() || this.isMms() && (this.mMessageType == 132 || this.mMessageType == 128) && !TextUtils.isEmpty((CharSequence)this.mMmsSnippet)) {
            return true;
        }
        return false;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void initLayoutStyle(String string2, boolean bl, int n, boolean bl2, boolean bl3, boolean bl4) {
        int n2 = 6;
        this.mShowTimeStamp = bl;
        this.mShowMxType = bl2;
        this.mDeliveRepotrMode = n;
        this.mHasBubbleDistance = bl3;
        if (MessageListItem.Style.list.toString().equals((Object)string2)) {
            if (!this.isOutMessage()) {
                this.mLayoutStyle = 0;
                return;
            }
            if (this.isGroup() && this.isSending()) {
                this.mLayoutStyle = 1;
                return;
            }
            if (this.isFailedMessage()) {
                this.mLayoutStyle = 2;
                return;
            }
            this.mLayoutStyle = 1;
            return;
        }
        if (this.isOutMessage()) {
            if (this.isGroup() && this.isSending()) {
                if (!bl4) {
                    n2 = 5;
                }
                this.mLayoutStyle = n2;
                return;
            }
            if (this.isFailedMessage()) {
                n = bl4 ? 8 : 7;
                this.mLayoutStyle = n;
                return;
            }
            if (!bl4) {
                n2 = 5;
            }
            this.mLayoutStyle = n2;
            return;
        }
        n = bl4 ? 4 : 3;
        this.mLayoutStyle = n;
    }

    boolean isBookmarkLayoutStyle() {
        if (this.mLayoutStyle == 11) {
            return true;
        }
        return false;
    }

    boolean isBubbleLayoutStyle() {
        if (this.mLayoutStyle >= 3 && this.mLayoutStyle <= 8) {
            return true;
        }
        return false;
    }

    boolean isBubbleOutLayoutStyle() {
        if (this.mLayoutStyle >= 5 && this.mLayoutStyle <= 8) {
            return true;
        }
        return false;
    }

    public boolean isDownloaded() {
        if (this.mMessageType != 130) {
            return true;
        }
        return false;
    }

    /*
     * Enabled aggressive block sorting
     */
    public boolean isFailedMessage() {
        boolean bl = false;
        boolean bl2 = this.isMms() && this.mErrorType >= 10;
        boolean bl3 = this.mMxType == 131073;
        boolean bl4 = this.mIsGroup ? this.isSms() && this.mFailedMsgInGroup.size() > 0 : this.isSms() && this.mBoxId == 5;
        if (bl2) return true;
        if (bl4) return true;
        if (!bl3) return bl;
        return true;
    }

    public boolean isGroup() {
        return this.mIsGroup;
    }

    boolean isListLayoutStyle() {
        if (this.mLayoutStyle >= 0 && this.mLayoutStyle <= 2) {
            return true;
        }
        return false;
    }

    public boolean isLocked() {
        return this.mLocked;
    }

    public boolean isMiXin() {
        if (this.mMxType != 0) {
            return true;
        }
        return false;
    }

    public boolean isMms() {
        return "mms".equals((Object)this.mType);
    }

    /*
     * Enabled aggressive block sorting
     */
    public boolean isOutMessage() {
        boolean bl = false;
        boolean bl2 = this.isMms() && (this.mBoxId == 4 || this.mBoxId == 2);
        boolean bl3 = this.isSms() && (this.mBoxId == 5 || this.mBoxId == 4 || this.mBoxId == 6 || this.mBoxId == 2);
        if (bl2) return true;
        if (bl3) return true;
        if (this.mIsGroup) return true;
        if (!this.mIsReadOnly) return bl;
        return true;
    }

    /*
     * Enabled aggressive block sorting
     */
    public boolean isOutgoingMessage() {
        boolean bl = false;
        boolean bl2 = this.isMms() && this.mBoxId == 4;
        if (!this.isSms()) return bl;
        boolean bl3 = this.mBoxId == 5 || this.mBoxId == 4 || this.mBoxId == 6;
        if (bl2) return true;
        if (!bl3) return bl;
        return true;
    }

    public boolean isReadOnly() {
        return this.mIsReadOnly;
    }

    public boolean isReadReport() {
        return this.mReadReport;
    }

    public boolean isReferenceGroup() {
        return this.mIsReferenceGroup;
    }

    /*
     * Enabled aggressive block sorting
     */
    public boolean isSending() {
        if (this.mIsGroup && this.isSms()) {
            if (this.mQueueMsgInGroup > 0) return true;
            return false;
        }
        if (this.isFailedMessage() || !this.isOutgoingMessage()) return false;
        return true;
    }

    public boolean isSms() {
        return "sms".equals((Object)this.mType);
    }

    public boolean isTimed() {
        if (this.mTimedValue > 0) {
            return true;
        }
        return false;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void removeFromResend(Uri uri) {
        synchronized (this) {
            int n = -1;
            int n2 = 0;
            do {
                int n3 = n;
                if (n2 >= this.mResendMsgInGroup.size()) return;
                if (((Uri)this.mResendMsgInGroup.get(n2)).equals((Object)uri)) {
                    n3 = n2;
                    if (n3 != -1) {
                        this.mResendMsgInGroup.remove(n3);
                    }
                    return;
                }
                ++n2;
            } while (true);
        }
    }

    public ArrayList<Uri> resendMsgInGroup() {
        return this.mResendMsgInGroup;
    }

    public void resetLayoutStyle() {
        this.mLayoutStyle = -1;
    }

    public int sentMsgInGroup() {
        return this.mSentMsgInGroup;
    }

    public void setContact(Contact contact) {
        if (contact != null) {
            this.mContact = contact;
            this.mAddress = this.mContact.getNumber();
        }
    }

    public String toString() {
        return "type: " + this.mType + " box: " + this.mBoxId + " uri: " + (Object)this.mMessageUri + " address: " + this.mAddress + " contact: " + this.getContactName() + " read: " + this.mReadReport + " delivery status: " + (Object)((Object)this.mDeliveryStatus);
    }

    public int totalMsgInGroup() {
        return this.mTotalMsgInGroup;
    }

    public static enum DeliveryStatus {
        NONE,
        INFO,
        FAILED,
        PENDING,
        SENT,
        RECEIVED,
        REJECTED,
        EXPIRED;
        

        private DeliveryStatus() {
        }
    }

    public class FailedItem {
        public final Uri messageUri;
        public final String number;

        public FailedItem(String string2, Uri uri) {
            this.number = string2;
            this.messageUri = uri;
        }
    }

}

