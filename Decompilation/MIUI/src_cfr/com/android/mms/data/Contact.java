/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ContentResolver
 *  android.content.ContentUris
 *  android.content.Context
 *  android.database.Cursor
 *  android.database.sqlite.SqliteWrapper
 *  android.net.Uri
 *  android.net.Uri$Builder
 *  android.os.Parcelable
 *  android.provider.ContactsContract
 *  android.provider.ContactsContract$CommonDataKinds
 *  android.provider.ContactsContract$CommonDataKinds$Phone
 *  android.provider.ContactsContract$Contacts
 *  android.provider.ContactsContract$Data
 *  android.provider.ContactsContract$Presence
 *  android.provider.ContactsContract$Profile
 *  android.provider.Telephony
 *  android.provider.Telephony$Mms
 *  android.telephony.TelephonyManager
 *  android.text.TextUtils
 *  android.util.Log
 *  android.widget.ImageView
 *  com.android.internal.util.ArrayUtils
 *  java.lang.Character
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Thread
 *  java.nio.CharBuffer
 *  java.util.ArrayList
 *  java.util.HashMap
 *  java.util.HashSet
 *  java.util.concurrent.atomic.AtomicInteger
 *  miui.telephony.PhoneNumberUtils
 *  miui.telephony.PhoneNumberUtils$PhoneNumber
 *  miui.yellowpage.MiPubUtils
 *  miui.yellowpage.YellowPage
 *  miui.yellowpage.YellowPagePhone
 *  miui.yellowpage.YellowPageUtils
 */
package com.android.mms.data;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SqliteWrapper;
import android.net.Uri;
import android.os.Parcelable;
import android.provider.ContactsContract;
import android.provider.Telephony;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;
import com.android.internal.util.ArrayUtils;
import com.android.mms.MmsApp;
import com.android.mms.data.ContactList;
import com.android.mms.data.ContactPhotoLoader;
import com.android.mms.data.RecipientIdCache;
import com.android.mms.ui.MessageUtils;
import com.xiaomi.mms.utils.B2cMessageUtils;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import miui.telephony.PhoneNumberUtils;
import miui.yellowpage.MiPubUtils;
import miui.yellowpage.YellowPage;
import miui.yellowpage.YellowPagePhone;
import miui.yellowpage.YellowPageUtils;

public class Contact {
    private static final String[] PREFIXES = new String[]{"12520"};
    private static final HashSet<UpdateListener> mListeners = new HashSet();
    private static char[] mSpecialCharacters = new char[]{',', ';'};
    private static ContactsCache sContactCache;
    private static ContactPhotoLoader sContactPhotoLoader;
    private String mAccountType;
    private String mCompareKey;
    private long mContactMethodId;
    private int mContactMethodType;
    private boolean mIsB2cNumber;
    private boolean mIsEmail;
    private boolean mIsMe;
    private boolean mIsPhoneNumber;
    private volatile boolean mIsStale;
    private boolean mIsYellowPageNumber;
    private String mLabel;
    private String mMxPhoneNumber;
    private String mName;
    private String mNameAndNumber;
    private String mNick;
    private String mNumber;
    private String mNumberE164;
    private boolean mNumberIsModified;
    private Uri mPeopleReferenceUri;
    private long mPersonId;
    private String mPhoneTag;
    private long mPhotoId;
    private int mPresenceResId;
    private String mPresenceText;
    private volatile boolean mQueryPending;
    private long mRawContactId;
    private long mRecipientId;
    private boolean mSendToVoicemail;
    private long mYellowPageId;
    private String mYellowPageThumbnailName;

    private Contact(String string) {
        this.init(string, "");
    }

    private Contact(boolean bl) {
        this.init("Self_Item_Key", "");
        this.mIsMe = bl;
    }

    static /* synthetic */ boolean access$400(Contact contact) {
        return contact.mIsStale;
    }

    static /* synthetic */ boolean access$402(Contact contact, boolean bl) {
        contact.mIsStale = bl;
        return bl;
    }

    static /* synthetic */ void access$500(Contact contact) {
        contact.setToUnknown();
    }

    static /* synthetic */ boolean access$800(Contact contact) {
        return contact.mQueryPending;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void addListener(UpdateListener updateListener) {
        HashSet<UpdateListener> hashSet = mListeners;
        synchronized (hashSet) {
            mListeners.add((Object)updateListener);
            return;
        }
    }

    public static void asyncloadAllInBackground() {
        if (sContactCache.mLoadAllState.get() != 1) {
            Thread thread = new Thread(){

                public void run() {
                    sContactCache.loadAll();
                }
            };
            thread.setName("AllContactsLoader");
            thread.setPriority(1);
            thread.start();
        }
    }

    public static void cancelRequest(ImageView imageView) {
        sContactPhotoLoader.cancelRequest(imageView);
    }

    private static String emptyIfNull(String string) {
        if (string != null) {
            return string;
        }
        return "";
    }

    public static String formatNameAndNumber(String string, String string2, String string3) {
        string3 = string2;
        if (string != null) {
            string3 = string + " <" + string2 + ">";
        }
        return string3;
    }

    public static Contact get(String string) {
        return sContactCache.get(string);
    }

    public static List<Contact> getByPhoneUris(Parcelable[] arrparcelable) {
        return sContactCache.getContactInfoForPhoneUris(arrparcelable);
    }

    public static int getLoadAllState() {
        return sContactCache.mLoadAllState.get();
    }

    public static void init(Context context) {
        context = context.getApplicationContext();
        sContactCache = new ContactsCache(context);
        sContactPhotoLoader = new ContactPhotoLoader(context, 285343793, 2130837723);
        RecipientIdCache.init(context);
    }

    private void init(String string, String string2) {
        this.mContactMethodId = -1;
        this.mName = string2;
        this.mNick = "";
        this.setNumber(string);
        this.mNumberIsModified = false;
        this.mLabel = "";
        this.mPersonId = 0;
        this.mPhotoId = 0;
        this.mPresenceResId = 0;
        this.mIsStale = true;
        this.mIsYellowPageNumber = false;
        this.mIsB2cNumber = false;
        this.mYellowPageThumbnailName = null;
        this.mSendToVoicemail = false;
        this.mPhoneTag = "";
    }

    public static void invalidatePhotoCache() {
        sContactPhotoLoader.clear();
    }

    public static boolean isSimContact(Contact contact) {
        return "com.android.contacts.sim".equals((Object)contact.mAccountType);
    }

    public static void loadPhoto(ImageView imageView, Contact contact) {
        sContactPhotoLoader.loadPhoto(imageView, contact);
    }

    public static String normalizePhoneNumber(String string) {
        PhoneNumberUtils.PhoneNumber phoneNumber = PhoneNumberUtils.PhoneNumber.parse((CharSequence)string);
        string = Contact.normalizePhoneNumber(phoneNumber, string);
        phoneNumber.recycle();
        return string;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static String normalizePhoneNumber(PhoneNumberUtils.PhoneNumber object, String string) {
        String string2 = string;
        String string3 = object.getEffectiveNumber();
        String string4 = object.getCountryCode();
        if (!TextUtils.isEmpty((CharSequence)string4)) {
            if (!object.isChineseNumber()) return "+" + string4 + string3;
        }
        if (object.isChineseNumber()) {
            return string3;
        }
        string3 = ((TelephonyManager)MmsApp.getApp().getSystemService("phone")).getSimCountryIso();
        object = string2;
        if (TextUtils.isEmpty((CharSequence)string3)) return object;
        string3 = string3.toUpperCase();
        object = string2;
        if (TextUtils.isEmpty((CharSequence)string3)) return object;
        string = PhoneNumberUtils.formatNumberToE164((String)string, (String)string3);
        object = string2;
        if (TextUtils.isEmpty((CharSequence)string)) return object;
        return string;
    }

    private void notSynchronizedUpdateNameAndNumber() {
        this.mNameAndNumber = Contact.formatNameAndNumber(this.mName, this.mNumber, this.mNumberE164);
    }

    public static void pauseCaching() {
        sContactPhotoLoader.pause();
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void removeAllListener() {
        HashSet<UpdateListener> hashSet = mListeners;
        synchronized (hashSet) {
            mListeners.clear();
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void removeListener(UpdateListener updateListener) {
        HashSet<UpdateListener> hashSet = mListeners;
        synchronized (hashSet) {
            mListeners.remove((Object)updateListener);
            return;
        }
    }

    public static String removeSpaceForAddress(String string) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < string.length(); ++i) {
            char c2 = string.charAt(i);
            if (c2 <= ' ') continue;
            stringBuilder.append(Character.toUpperCase((char)c2));
        }
        return stringBuilder.toString();
    }

    public static void requestClear(boolean bl) {
        sContactPhotoLoader.requestClear(bl);
    }

    public static void resumeCaching() {
        sContactPhotoLoader.resume();
    }

    public static ContactList searchForContacts(String string) {
        return sContactCache.searchForContacts(string);
    }

    private void setToUnknown() {
        this.mContactMethodId = -1;
        this.mContactMethodType = 0;
        this.mRawContactId = 0;
        this.mName = null;
        this.mNick = null;
        this.mNameAndNumber = this.mNumber;
        this.mLabel = null;
        this.mPersonId = 0;
        this.mPresenceResId = 0;
        this.mPresenceText = null;
        this.mPhotoId = 0;
        this.mIsYellowPageNumber = false;
        this.mIsB2cNumber = false;
        this.mYellowPageThumbnailName = null;
        this.mSendToVoicemail = false;
        this.mAccountType = null;
    }

    public static void stopCaching() {
        sContactPhotoLoader.stop();
    }

    public boolean equals(Object object) {
        block4 : {
            object = (Contact)object;
            if (this.mCompareKey != null) break block4;
            if (object.mCompareKey == null) {
                return true;
            }
        }
        try {
            boolean bl = this.mCompareKey.equals((Object)object.mCompareKey);
            return bl;
        }
        catch (ClassCastException var1_2) {
            // empty catch block
        }
        return false;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public boolean existsInDatabase() {
        synchronized (this) {
            long l = this.mPersonId;
            if (l <= 0) return false;
            return true;
        }
    }

    public String getCompareKey() {
        synchronized (this) {
            String string = this.mCompareKey;
            return string;
        }
    }

    public long getContactMethodId() {
        return this.mContactMethodId;
    }

    public int getContactMethodType() {
        return this.mContactMethodType;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public String getDisplayTag() {
        synchronized (this) {
            block6 : {
                if (this.mPhoneTag == null) return "";
                if (!this.mPhoneTag.equals((Object)this.mName)) break block6;
                return "";
            }
            String string = this.mPhoneTag;
            return string;
        }
    }

    public Uri getEmailUri() {
        synchronized (this) {
            Uri uri;
            if (this.existsInDatabase()) {
                uri = ContentUris.withAppendedId((Uri)ContactsContract.CommonDataKinds.Phone.CONTENT_URI, (long)this.mContactMethodId);
                return uri;
            }
            uri = new Uri.Builder();
            uri.scheme("mailto");
            uri.encodedOpaquePart(this.mNumber);
            uri = uri.build();
        }
    }

    public String getMxPhoneNumber() {
        synchronized (this) {
            String string = this.mMxPhoneNumber;
            return string;
        }
    }

    public String getName() {
        synchronized (this) {
            String string;
            if (TextUtils.isEmpty((CharSequence)this.mName)) {
                string = this.getNumber();
                return string;
            }
            string = this.mName;
        }
    }

    public String getNameAndNumber() {
        synchronized (this) {
            String string = this.mNameAndNumber;
            return string;
        }
    }

    public String getNickname() {
        synchronized (this) {
            String string = this.mNick;
            return string;
        }
    }

    public String getNumber() {
        synchronized (this) {
            String string = this.mNumber;
            return string;
        }
    }

    public Uri getPeopleReferenceUri() {
        return this.mPeopleReferenceUri;
    }

    public long getPersonId() {
        return this.mPersonId;
    }

    public Uri getPhoneUri() {
        synchronized (this) {
            Uri uri;
            if (this.existsInDatabase()) {
                uri = ContentUris.withAppendedId((Uri)ContactsContract.CommonDataKinds.Phone.CONTENT_URI, (long)this.mContactMethodId);
                return uri;
            }
            uri = new Uri.Builder();
            uri.scheme("tel");
            uri.encodedOpaquePart(this.mNumber);
            uri = uri.build();
        }
    }

    public long getPhotoId() {
        return this.mPhotoId;
    }

    public String getRealName() {
        synchronized (this) {
            String string = this.mName;
            return string;
        }
    }

    public long getRecipientId() {
        synchronized (this) {
            long l = this.mRecipientId;
            return l;
        }
    }

    public boolean getSendToVoicemail() {
        return this.mSendToVoicemail;
    }

    public String getTag() {
        synchronized (this) {
            String string = this.mPhoneTag;
            return string;
        }
    }

    public Uri getUri() {
        synchronized (this) {
            Uri uri = ContentUris.withAppendedId((Uri)ContactsContract.Contacts.CONTENT_URI, (long)this.mPersonId);
            return uri;
        }
    }

    public long getYellowPageId() {
        synchronized (this) {
            long l = this.mYellowPageId;
            return l;
        }
    }

    public String getYellowPageThumbnailName() {
        synchronized (this) {
            String string = this.mYellowPageThumbnailName;
            return string;
        }
    }

    public int hashCode() {
        return this.mCompareKey.hashCode();
    }

    public boolean isB2cNumber() {
        synchronized (this) {
            boolean bl = this.mIsB2cNumber;
            return bl;
        }
    }

    public boolean isEmail() {
        synchronized (this) {
            boolean bl = this.mIsEmail;
            return bl;
        }
    }

    public boolean isNumberModified() {
        return this.mNumberIsModified;
    }

    public boolean isPhoneNumber() {
        synchronized (this) {
            boolean bl = this.mIsPhoneNumber;
            return bl;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public boolean isYellowPageB2cNumber() {
        synchronized (this) {
            int n = this.mContactMethodType;
            if (n != 4) return false;
            return true;
        }
    }

    public boolean isYellowPageNumber() {
        synchronized (this) {
            boolean bl = this.mIsYellowPageNumber;
            return bl;
        }
    }

    public Contact load(boolean bl, boolean bl2) {
        sContactCache.loadContact(this, bl, bl2);
        return this;
    }

    public void setIsB2cNumber(boolean bl) {
        synchronized (this) {
            this.mIsB2cNumber = bl;
            return;
        }
    }

    public void setIsNumberModified(boolean bl) {
        this.mNumberIsModified = bl;
    }

    public void setName(String string) {
        synchronized (this) {
            this.mName = string;
            return;
        }
    }

    public void setNickname(String string) {
        synchronized (this) {
            this.mNick = string;
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void setNumber(String string) {
        synchronized (this) {
            this.mNumber = string;
            this.mIsPhoneNumber = MessageUtils.isPhoneNumber(string);
            if (this.mIsPhoneNumber) {
                String string2;
                PhoneNumberUtils.PhoneNumber phoneNumber = PhoneNumberUtils.PhoneNumber.parse((CharSequence)string);
                String string3 = phoneNumber.getEffectiveNumber();
                String string4 = string2 = phoneNumber.getPrefix();
                if (string2 == null) {
                    string4 = "";
                }
                this.mCompareKey = Contact.removeSpaceForAddress(string4 + string3);
                this.mMxPhoneNumber = Contact.normalizePhoneNumber(phoneNumber, this.mNumber);
                phoneNumber.recycle();
            } else {
                this.mCompareKey = string;
                this.mMxPhoneNumber = string;
            }
            this.mIsB2cNumber = B2cMessageUtils.isB2cNumber(string);
            if (!this.mIsB2cNumber) {
                this.mIsEmail = MessageUtils.isEmail(string);
            }
            this.notSynchronizedUpdateNameAndNumber();
            this.mNumberIsModified = true;
            return;
        }
    }

    public void setRecipientId(long l) {
        synchronized (this) {
            this.mRecipientId = l;
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public String toString() {
        String string = this.mNumber != null ? this.mNumber : "null";
        String string2 = this.mName != null ? this.mName : "null";
        String string3 = this.mNick != null ? this.mNick : "null";
        String string4 = this.mNameAndNumber != null ? this.mNameAndNumber : "null";
        String string5 = this.mLabel != null ? this.mLabel : "null";
        return String.format((String)"{ number=%s, name=%s, nickname=%s, nameAndNumber=%s, label=%s, person_id=%d, hash=%d method_id=%d }", (Object[])new Object[]{string, string2, string3, string4, string5, this.mPersonId, this.hashCode(), this.mContactMethodId});
    }

    private static class ContactsCache {
        private static final String[] CALLER_ID_PROJECTION;
        private static final String[] EMAIL_PROJECTION;
        private static final Uri EMAIL_WITH_PRESENCE_URI;
        private static final Uri PHONES_WITH_PRESENCE_URI;
        private static final String[] SELF_PROJECTION;
        static CharBuffer sStaticKeyBuffer;
        private final HashMap<String, ArrayList<Contact>> mContactsHash = new HashMap();
        private final Context mContext;
        private AtomicInteger mLoadAllState = new AtomicInteger(0);
        private final TaskQueue mTaskQueue = new TaskQueue();

        static {
            PHONES_WITH_PRESENCE_URI = ContactsContract.Data.CONTENT_URI;
            CALLER_ID_PROJECTION = new String[]{"_id", "data1", "data3", "display_name", "contact_id", "contact_presence", "contact_status", "data4", "photo_id", "nickname", "send_to_voicemail", "raw_contact_id", "account_type"};
            SELF_PROJECTION = new String[]{"_id", "display_name"};
            EMAIL_WITH_PRESENCE_URI = ContactsContract.Data.CONTENT_URI;
            EMAIL_PROJECTION = new String[]{"_id", "data4", "contact_presence", "contact_id", "display_name", "photo_id", "nickname", "send_to_voicemail", "raw_contact_id", "data1"};
            sStaticKeyBuffer = CharBuffer.allocate((int)5);
        }

        private ContactsCache(Context context) {
            this.mContext = context;
        }

        /*
         * Enabled aggressive block sorting
         */
        private boolean contactChanged(Contact contact, Contact contact2) {
            if (!(contact.mContactMethodType == contact2.mContactMethodType && Contact.emptyIfNull(contact.mNick).equals((Object)Contact.emptyIfNull(contact2.mNick)) && contact.mContactMethodId == contact2.mContactMethodId && contact.mPersonId == contact2.mPersonId && contact.mPresenceResId == contact2.mPresenceResId && contact.mSendToVoicemail == contact2.mSendToVoicemail && Contact.emptyIfNull(contact.mName).equals((Object)Contact.emptyIfNull(contact2.mName)) && Contact.emptyIfNull(contact.mLabel).equals((Object)Contact.emptyIfNull(contact2.mLabel)) && contact.mPhotoId == contact2.mPhotoId && contact.mIsYellowPageNumber == contact2.mIsYellowPageNumber && contact.mIsB2cNumber == contact2.mIsB2cNumber && contact.mPhoneTag == contact2.mPhoneTag && contact.mPeopleReferenceUri == contact2.mPeopleReferenceUri)) {
                return true;
            }
            return false;
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        private boolean containSpecialCharacter(String string) {
            if (string == null) {
                return false;
            }
            char[] arrc = mSpecialCharacters;
            int n = arrc.length;
            int n2 = 0;
            while (n2 < n) {
                if (string.indexOf((int)arrc[n2]) != -1) {
                    return true;
                }
                ++n2;
            }
            return false;
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        private void fillB2cContact(Contact contact, String string) {
            if (!TextUtils.isEmpty((CharSequence)string)) {
                synchronized (contact) {
                    contact.mContactMethodType = 5;
                    contact.mName = string;
                    contact.mIsB2cNumber = true;
                    contact.mYellowPageThumbnailName = null;
                    return;
                }
            }
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        private void fillB2cContactByYellowPage(Contact contact, YellowPage yellowPage) {
            synchronized (contact) {
                contact.mContactMethodType = 4;
                contact.mName = yellowPage.getName();
                contact.mIsB2cNumber = true;
                contact.mYellowPageThumbnailName = yellowPage.getThumbnailName();
                return;
            }
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        private boolean fillEmailTypeContact(Contact contact, Cursor cursor) {
            synchronized (contact) {
                String string;
                contact.mContactMethodId = cursor.getLong(0);
                contact.mRawContactId = cursor.getLong(8);
                contact.mPersonId = cursor.getLong(3);
                boolean bl = cursor.getInt(7) == 1;
                contact.mSendToVoicemail = bl;
                contact.mPresenceResId = this.getPresenceIconResourceId(cursor.getInt(2));
                contact.mPhotoId = cursor.getLong(5);
                contact.mNick = cursor.getString(6);
                String string2 = string = cursor.getString(1);
                if (TextUtils.isEmpty((CharSequence)string)) {
                    string2 = cursor.getString(4);
                }
                if (!TextUtils.isEmpty((CharSequence)string2)) {
                    contact.mName = string2;
                    return true;
                }
                return false;
            }
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        private void fillPhoneTypeContact(Contact contact, Cursor cursor) {
            boolean bl = true;
            synchronized (contact) {
                contact.mContactMethodType = 1;
                contact.mContactMethodId = cursor.getLong(0);
                contact.mRawContactId = cursor.getLong(11);
                contact.mAccountType = cursor.getString(12);
                contact.mLabel = cursor.getString(2);
                contact.mName = cursor.getString(3);
                contact.mNick = cursor.getString(9);
                contact.mPersonId = cursor.getLong(4);
                contact.mPhotoId = cursor.getLong(8);
                contact.mPresenceResId = this.getPresenceIconResourceId(cursor.getInt(5));
                contact.mPresenceText = cursor.getString(6);
                contact.mNumberE164 = cursor.getString(7);
                if (cursor.getInt(10) != 1) {
                    bl = false;
                }
                contact.mSendToVoicemail = bl;
                return;
            }
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        private void fillSelfContact(Contact contact, Cursor cursor) {
            synchronized (contact) {
                contact.mName = cursor.getString(1);
                if (TextUtils.isEmpty((CharSequence)contact.mName)) {
                    contact.mName = this.mContext.getString(2131361808);
                }
                return;
            }
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        private void fillYellowPageContact(Contact contact, YellowPagePhone yellowPagePhone) {
            synchronized (contact) {
                contact.mContactMethodType = 1;
                contact.mName = yellowPagePhone.getYellowPageName();
                contact.mPhoneTag = yellowPagePhone.getTag();
                contact.mIsYellowPageNumber = true;
                contact.mYellowPageId = yellowPagePhone.getYellowPageId();
                return;
            }
        }

        private Contact get(String string, boolean bl) {
            String string2 = string;
            if (TextUtils.isEmpty((CharSequence)string)) {
                string2 = "";
            }
            return this.internalGet(string2, bl);
        }

        private Contact getContactInfo(Contact contact) {
            if (contact.mIsMe) {
                return this.getContactInfoForSelf();
            }
            if (B2cMessageUtils.isB2cNumber(contact)) {
                return this.getContactInfoForB2cNumber(contact.getNumber());
            }
            if (this.shouldMatchEmailField(contact.mNumber)) {
                return this.getContactInfoForEmailAddress(contact.mNumber);
            }
            return this.getContactInfoForPhoneNumber(contact.mNumber);
        }

        private Contact getContactInfoForB2cNumber(String string) {
            Contact contact = new Contact(string);
            contact.mContactMethodType = 5;
            this.getYellowPageInfoForB2cContact(string, contact);
            return contact;
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        private Contact getContactInfoForEmailAddress(String string) {
            Contact contact = new Contact(string);
            contact.mContactMethodType = 2;
            contact.mPeopleReferenceUri = Uri.fromParts((String)"mailto", (String)string, (String)null);
            string = SqliteWrapper.query((Context)this.mContext, (ContentResolver)this.mContext.getContentResolver(), (Uri)EMAIL_WITH_PRESENCE_URI, (String[])EMAIL_PROJECTION, (String)"UPPER(data1)=UPPER(?) AND mimetype='vnd.android.cursor.item/email_v2'", (String[])new String[]{string}, (String)null);
            if (string != null) {
                boolean bl;
                while (string.moveToNext() && !(bl = this.fillEmailTypeContact(contact, (Cursor)string))) {
                }
            }
            return contact;
            finally {
                string.close();
            }
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         * Lifted jumps to return sites
         */
        private Contact getContactInfoForPhoneNumber(String string) {
            Contact contact = new Contact(string);
            contact.mContactMethodType = 1;
            contact.mPeopleReferenceUri = Uri.fromParts((String)"tel", (String)string, (String)null);
            string = this.removePrefix(string);
            String string2 = PhoneNumberUtils.toCallerIDMinMatch((String)string);
            if (TextUtils.isEmpty((CharSequence)string)) return contact;
            if (TextUtils.isEmpty((CharSequence)string2)) return contact;
            String[] arrstring = new String[]{string2};
            string2 = this.mContext.getContentResolver().query(PHONES_WITH_PRESENCE_URI, CALLER_ID_PROJECTION, " Data._ID IN  (SELECT data_id FROM phone_lookup WHERE min_match = ?)", arrstring, "length(data1)");
            if (string2 == null) return this.getYellowPageInfoForPhoneNumber(string, contact);
            try {
                String string3;
                string2.moveToPosition(-1);
                do {
                    if (!string2.moveToNext()) return this.getYellowPageInfoForPhoneNumber(string, contact);
                } while (this.containSpecialCharacter(string3 = this.removePrefix(string2.getString(1))) || !PhoneNumberUtils.compareStrictly((String)MessageUtils.deleteSpecialIntlCode(string), (String)MessageUtils.deleteSpecialIntlCode(string3)));
                this.fillPhoneTypeContact(contact, (Cursor)string2);
                return contact;
            }
            finally {
                string2.close();
            }
        }

        private Contact getContactInfoForSelf() {
            Contact contact = new Contact(true);
            contact.mContactMethodType = 3;
            Cursor cursor = this.mContext.getContentResolver().query(ContactsContract.Profile.CONTENT_URI, SELF_PROJECTION, null, null, null);
            if (cursor == null) {
                Log.w((String)"Contact", (String)("getContactInfoForSelf() returned NULL cursor! contact uri used " + (Object)ContactsContract.Profile.CONTENT_URI));
                return contact;
            }
            try {
                if (cursor.moveToFirst()) {
                    this.fillSelfContact(contact, cursor);
                }
                return contact;
            }
            finally {
                cursor.close();
            }
        }

        private int getPresenceIconResourceId(int n) {
            if (n != 0) {
                return ContactsContract.Presence.getPresenceIconResourceId((int)n);
            }
            return 0;
        }

        private void getYellowPageInfoForB2cContact(String string, Contact contact) {
            YellowPage yellowPage = MiPubUtils.getLocalYellowPage((Context)MmsApp.getApp(), (String)string);
            if (yellowPage != null) {
                this.fillB2cContactByYellowPage(contact, yellowPage);
                return;
            }
            this.fillB2cContact(contact, B2cMessageUtils.getB2cAddressDisplayName(this.mContext, string));
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        private Contact getYellowPageInfoForPhoneNumber(String string, Contact contact) {
            string = YellowPageUtils.getPhoneInfo((Context)this.mContext, (String)string, (boolean)false);
            contact.mPhoneTag = "";
            if (string == null) return contact;
            if (string.isYellowPage()) {
                this.fillYellowPageContact(contact, (YellowPagePhone)string);
                return contact;
            }
            contact.mPhoneTag = string.getTag();
            return contact;
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        private Contact internalGet(String object, boolean bl) {
            boolean bl2 = false;
            synchronized (this) {
                if (bl || Telephony.Mms.isEmailAddress((String)object) || MessageUtils.isAlias((String)object)) {
                    bl2 = true;
                }
                Object object2 = bl2 ? object : this.simplifyPhoneNumber((String)object);
                ArrayList arrayList = (ArrayList)this.mContactsHash.get(object2);
                if (arrayList == null) {
                    arrayList = new ArrayList();
                    this.mContactsHash.put(object2, (Object)arrayList);
                    object2 = arrayList;
                } else {
                    int n = arrayList.size();
                    int n2 = 0;
                    do {
                        object2 = arrayList;
                        if (n2 >= n) break;
                        object2 = (Contact)arrayList.get(n2);
                        if (bl2) {
                            if (object.equals((Object)((Contact)object2).mNumber)) {
                                return object2;
                            }
                        } else if (PhoneNumberUtils.compareStrictly((String)MessageUtils.deleteSpecialIntlCode((String)object), (String)MessageUtils.deleteSpecialIntlCode(((Contact)object2).mNumber), (boolean)false)) {
                            return object2;
                        }
                        ++n2;
                    } while (true);
                }
                object = bl ? new Contact(true) : new Contact((String)object);
                object2.add(object);
                return object;
            }
        }

        /*
         * Enabled aggressive block sorting
         */
        private boolean isAlphaNumber(String string) {
            if (!PhoneNumberUtils.isWellFormedSmsAddress((String)string) || MessageUtils.isAlias(string) || TextUtils.isEmpty((CharSequence)(string = PhoneNumberUtils.extractNetworkPortion((String)string))) || string.length() < 3) {
                return true;
            }
            return false;
        }

        /*
         * Unable to fully structure code
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         * Converted monitor instructions to comments
         * Lifted jumps to return sites
         */
        private void loadContact(Contact var1_1, boolean var2_2, boolean var3_3) {
            var5_4 = false;
            // MONITORENTER : var1_1
            while (var2_2 && (var6_5 = Contact.access$800(var1_1))) {
                try {
                    var1_1.wait();
                }
                catch (InterruptedException var7_6) {}
            }
            var4_7 = var5_4;
            if (Contact.access$800(var1_1)) ** GOTO lbl17
            if (var3_3) ** GOTO lbl-1000
            var4_7 = var5_4;
            if (Contact.access$400(var1_1)) lbl-1000: // 2 sources:
            {
                Contact.access$402(var1_1, false);
                Contact.access$802(var1_1, true);
                var4_7 = true;
            }
lbl17: // 4 sources:
            // MONITOREXIT : var1_1
            if (var4_7 == false) return;
            if (var2_2) {
                this.updateContact(var1_1);
                return;
            }
            this.pushTask(var1_1);
        }

        private String removePrefix(String string) {
            String string2 = string;
            if (string.length() >= 12) {
                string2 = string;
                if (ArrayUtils.contains((Object[])PREFIXES, (Object)string.substring(0, 5))) {
                    string2 = string.substring(5);
                }
            }
            return string2;
        }

        private boolean shouldMatchEmailField(String string) {
            if (Telephony.Mms.isEmailAddress((String)string) || this.isAlphaNumber(string)) {
                return true;
            }
            return false;
        }

        private String simplifyPhoneNumber(String string) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = string.length() - 1; i >= 0 && stringBuilder.length() < 5; --i) {
                char c2 = string.charAt(i);
                if (!Character.isDigit((char)c2)) continue;
                stringBuilder.append(c2);
            }
            if (stringBuilder.length() == 0) {
                return string;
            }
            return stringBuilder.toString();
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        private void updateContact(Contact contact) {
            if (contact == null) {
                return;
            }
            Object object = this.getContactInfo(contact);
            synchronized (contact) {
                if (this.contactChanged(contact, (Contact)object)) {
                    contact.mNick = ((Contact)object).mNick;
                    contact.mNumber = ((Contact)object).mNumber;
                    contact.mCompareKey = ((Contact)object).mCompareKey;
                    contact.mLabel = ((Contact)object).mLabel;
                    contact.mPersonId = ((Contact)object).mPersonId;
                    contact.mPhotoId = ((Contact)object).mPhotoId;
                    contact.mPresenceResId = ((Contact)object).mPresenceResId;
                    contact.mPresenceText = ((Contact)object).mPresenceText;
                    contact.mIsYellowPageNumber = ((Contact)object).mIsYellowPageNumber;
                    contact.mIsB2cNumber = ((Contact)object).mIsB2cNumber;
                    contact.mYellowPageThumbnailName = ((Contact)object).mYellowPageThumbnailName;
                    contact.mYellowPageId = ((Contact)object).mYellowPageId;
                    contact.mContactMethodId = ((Contact)object).mContactMethodId;
                    contact.mRawContactId = ((Contact)object).mRawContactId;
                    contact.mContactMethodType = ((Contact)object).mContactMethodType;
                    contact.mNumberE164 = ((Contact)object).mNumberE164;
                    contact.mName = ((Contact)object).mName;
                    contact.mSendToVoicemail = ((Contact)object).mSendToVoicemail;
                    contact.mAccountType = ((Contact)object).mAccountType;
                    contact.mPhoneTag = ((Contact)object).mPhoneTag;
                    contact.mPeopleReferenceUri = ((Contact)object).mPeopleReferenceUri;
                    contact.notSynchronizedUpdateNameAndNumber();
                    if (!TextUtils.isEmpty((CharSequence)contact.mNumber)) {
                        HashSet hashSet;
                        object = mListeners;
                        synchronized (object) {
                            hashSet = (HashSet)mListeners.clone();
                        }
                        object = hashSet.iterator();
                        while (object.hasNext()) {
                            ((UpdateListener)object.next()).onUpdate(contact);
                        }
                    }
                }
                contact.mQueryPending = false;
                contact.notifyAll();
                return;
            }
        }

        public Contact get(String string) {
            return this.get(string, false);
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        public List<Contact> getContactInfoForPhoneUris(Parcelable[] object) {
            Object object2;
            if (object.length == 0) {
                return null;
            }
            StringBuilder stringBuilder = new StringBuilder();
            boolean bl = true;
            int n = object.length;
            for (int i = 0; i < n; ++i) {
                object2 = (Uri)object[i];
                boolean bl2 = bl;
                if ("content".equals((Object)object2.getScheme())) {
                    if (bl) {
                        bl2 = false;
                        stringBuilder.append(object2.getLastPathSegment());
                    } else {
                        stringBuilder.append(',').append(object2.getLastPathSegment());
                        bl2 = bl;
                    }
                }
                bl = bl2;
            }
            if (bl) {
                return null;
            }
            object = null;
            if (stringBuilder.length() <= 0) return null;
            object = "_id IN (" + stringBuilder.toString() + ")";
            object = this.mContext.getContentResolver().query(PHONES_WITH_PRESENCE_URI, CALLER_ID_PROJECTION, (String)object, null, null);
            if (object == null) {
                return null;
            }
            stringBuilder = new ArrayList();
            try {
                while (object.moveToNext()) {
                    object2 = Contact.get(object.getString(1));
                    this.fillPhoneTypeContact((Contact)object2, (Cursor)object);
                    stringBuilder.add(object2);
                }
                return stringBuilder;
            }
            finally {
                object.close();
            }
        }

        /*
         * Exception decompiling
         */
        void loadAll() {
            // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
            // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [4[TRYBLOCK]], but top level block is 48[UNCONDITIONALDOLOOP]
            // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.processEndingBlocks(Op04StructuredStatement.java:394)
            // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:446)
            // org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:2859)
            // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:805)
            // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:220)
            // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:165)
            // org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:91)
            // org.benf.cfr.reader.entities.Method.analyse(Method.java:354)
            // org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:751)
            // org.benf.cfr.reader.entities.ClassFile.analyseInnerClassesPass1(ClassFile.java:664)
            // org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:747)
            // org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:683)
            // org.benf.cfr.reader.Main.doJar(Main.java:128)
            // org.benf.cfr.reader.Main.main(Main.java:178)
            throw new IllegalStateException("Decompilation failed");
        }

        public void pushTask(Contact contact) {
            this.mTaskQueue.push(contact);
        }

        /*
         * Exception decompiling
         */
        ContactList searchForContacts(String var1_1) {
            // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
            // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [0[TRYBLOCK]], but top level block is 8[WHILELOOP]
            // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.processEndingBlocks(Op04StructuredStatement.java:394)
            // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:446)
            // org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:2859)
            // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:805)
            // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:220)
            // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:165)
            // org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:91)
            // org.benf.cfr.reader.entities.Method.analyse(Method.java:354)
            // org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:751)
            // org.benf.cfr.reader.entities.ClassFile.analyseInnerClassesPass1(ClassFile.java:664)
            // org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:747)
            // org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:683)
            // org.benf.cfr.reader.Main.doJar(Main.java:128)
            // org.benf.cfr.reader.Main.main(Main.java:178)
            throw new IllegalStateException("Decompilation failed");
        }

        private static class TaskQueue {
            private final ArrayList<Contact> mThingsToLoad = new ArrayList();
            Thread mWorkerThread;

            public TaskQueue() {
                this.mWorkerThread = new Thread(new Runnable(){

                    /*
                     * Loose catch block
                     * Enabled aggressive block sorting
                     * Enabled unnecessary exception pruning
                     * Enabled aggressive exception aggregation
                     * Converted monitor instructions to comments
                     * Lifted jumps to return sites
                     */
                    @Override
                    public void run() {
                        do {
                            Contact contact = null;
                            ArrayList arrayList = TaskQueue.this.mThingsToLoad;
                            // MONITORENTER : arrayList
                            int n = TaskQueue.this.mThingsToLoad.size();
                            if (n == 0) {
                                TaskQueue.this.mThingsToLoad.wait();
                            }
                            if (TaskQueue.this.mThingsToLoad.size() > 0) {
                                contact = (Contact)TaskQueue.this.mThingsToLoad.remove(0);
                            }
                            // MONITOREXIT : arrayList
                            if (contact == null) continue;
                            sContactCache.updateContact(contact);
                        } while (true);
                        catch (InterruptedException interruptedException) {
                            // MONITOREXIT : arrayList
                            return;
                        }
                    }
                });
                this.mWorkerThread.setName("SingleContactLoader");
                this.mWorkerThread.setPriority(1);
                this.mWorkerThread.start();
            }

            /*
             * Enabled aggressive block sorting
             * Enabled unnecessary exception pruning
             * Enabled aggressive exception aggregation
             */
            public void push(Contact contact) {
                ArrayList<Contact> arrayList = this.mThingsToLoad;
                synchronized (arrayList) {
                    if (!this.mThingsToLoad.contains((Object)contact)) {
                        this.mThingsToLoad.add((Object)contact);
                        this.mThingsToLoad.notify();
                    }
                    return;
                }
            }

        }

    }

    public static interface UpdateListener {
        public void onUpdate(Contact var1);
    }

}

