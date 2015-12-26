/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.app.Dialog
 *  android.content.ContentResolver
 *  android.content.ContentUris
 *  android.content.ContentValues
 *  android.content.Context
 *  android.database.Cursor
 *  android.database.sqlite.SqliteWrapper
 *  android.graphics.drawable.AnimatedRotateDrawable
 *  android.graphics.drawable.Drawable
 *  android.net.Uri
 *  android.os.AsyncTask
 *  android.provider.Telephony
 *  android.provider.Telephony$Mms
 *  android.provider.Telephony$Mms$Draft
 *  android.provider.Telephony$Mms$Outbox
 *  android.provider.Telephony$MmsSms
 *  android.provider.Telephony$MmsSms$PendingMessages
 *  android.provider.Telephony$Sms
 *  android.provider.Telephony$Sms$Conversations
 *  android.telephony.SmsMessage
 *  android.text.TextUtils
 *  android.text.format.DateFormat
 *  android.text.format.DateUtils
 *  android.util.Log
 *  android.view.View
 *  android.view.Window
 *  android.widget.ProgressBar
 *  com.google.android.mms.MmsException
 *  com.google.android.mms.pdu.EncodedStringValue
 *  com.google.android.mms.pdu.GenericPdu
 *  com.google.android.mms.pdu.MiuiPduPersister
 *  com.google.android.mms.pdu.PduBody
 *  com.google.android.mms.pdu.SendReq
 *  java.lang.Integer
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.lang.Thread
 *  java.lang.Throwable
 *  java.util.ArrayList
 *  java.util.Arrays
 *  java.util.concurrent.Executors
 *  miui.os.Build
 */
package com.android.mms.data;

import android.app.Activity;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SqliteWrapper;
import android.graphics.drawable.AnimatedRotateDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ProgressBar;
import com.android.common.contacts.DataUsageStatUpdater;
import com.android.common.userhappiness.UserHappinessSignals;
import com.android.mms.ContentRestrictionException;
import com.android.mms.ExceedMessageSizeException;
import com.android.mms.LogTag;
import com.android.mms.MmsApp;
import com.android.mms.MmsConfig;
import com.android.mms.ResolutionException;
import com.android.mms.UnsupportContentTypeException;
import com.android.mms.data.Contact;
import com.android.mms.data.ContactList;
import com.android.mms.data.Conversation;
import com.android.mms.data.RecipientIdCache;
import com.android.mms.model.AudioModel;
import com.android.mms.model.FileAttachmentModel;
import com.android.mms.model.ImageModel;
import com.android.mms.model.LayoutModel;
import com.android.mms.model.MediaModel;
import com.android.mms.model.Model;
import com.android.mms.model.RegionModel;
import com.android.mms.model.SlideModel;
import com.android.mms.model.SlideshowModel;
import com.android.mms.model.TextModel;
import com.android.mms.model.VCardModel;
import com.android.mms.model.VideoModel;
import com.android.mms.transaction.MmsMessageSender;
import com.android.mms.transaction.SmsMessageSender;
import com.android.mms.ui.MessageEditableActivityBase;
import com.android.mms.ui.MessageUtils;
import com.android.mms.ui.SlideshowEditor;
import com.android.mms.util.DraftCache;
import com.google.android.mms.MmsException;
import com.google.android.mms.pdu.EncodedStringValue;
import com.google.android.mms.pdu.GenericPdu;
import com.google.android.mms.pdu.MiuiPduPersister;
import com.google.android.mms.pdu.PduBody;
import com.google.android.mms.pdu.SendReq;
import com.xiaomi.mms.utils.B2cMessageUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import miui.os.Build;

public class WorkingMessage {
    private static final String[] MMS_DRAFT_PROJECTION;
    private static final String[] MMS_OUTBOX_PROJECTION;
    private static final String[] SMS_DRAFT_PROJECTION;
    private static final Executor sAsyncTaskExecutor;
    private static boolean sMmsEnabled;
    protected Activity mActivity;
    protected int mAttachmentType;
    protected ContentResolver mContentResolver;
    protected Context mContext = MmsApp.getApp();
    protected Conversation mConversation;
    protected boolean mDiscarded = false;
    protected volatile boolean mHasMmsDraft;
    protected volatile boolean mHasSmsDraft;
    protected Uri mMessageUri;
    private int mMmsState;
    private boolean mSendByMx;
    private boolean mSendByMxV2;
    protected SlideshowModel mSlideshow;
    protected MessageStatusListener mStatusListener;
    protected CharSequence mSubject;
    protected CharSequence mText;
    private long mTimeToSend = 0;
    private String mTimeToSendDesc;
    private List<String> mWorkingRecipients;

    static {
        sMmsEnabled = MmsConfig.getMmsEnabled();
        MMS_OUTBOX_PROJECTION = new String[]{"_id", "m_size"};
        sAsyncTaskExecutor = Executors.newCachedThreadPool();
        MMS_DRAFT_PROJECTION = new String[]{"_id", "sub", "sub_cs", "date_full", "timed"};
        SMS_DRAFT_PROJECTION = new String[]{"body", "date", "timed"};
    }

    public WorkingMessage(MessageStatusListener messageStatusListener) {
        this.mContentResolver = this.mContext.getContentResolver();
        this.mStatusListener = messageStatusListener;
        if (this.mStatusListener != null) {
            this.mActivity = messageStatusListener.getHostedActivity();
        }
        this.mAttachmentType = 0;
        this.mText = "";
    }

    static /* synthetic */ String[] access$400() {
        return MMS_OUTBOX_PROJECTION;
    }

    static /* synthetic */ void access$700(WorkingMessage workingMessage, Uri uri) {
        workingMessage.markMmsMessageWithError(uri);
    }

    private boolean addressContainsEmailToMms(Conversation arrstring, String string) {
        if (MmsConfig.getEmailGateway() != null) {
            arrstring = arrstring.getRecipients().getNumbers();
            int n = arrstring.length;
            for (int i = 0; i < n; ++i) {
                Contact contact = Contact.get(arrstring[i]);
                if ((!Telephony.Mms.isEmailAddress((String)arrstring[i]) || B2cMessageUtils.isB2cNumber(contact)) && !MessageUtils.isAlias(arrstring[i]) || SmsMessage.calculateLength((String)(arrstring[i] + " " + string), (boolean)false)[0] <= 1) continue;
                this.updateState(1, true, true);
                this.ensureSlideshow();
                this.syncTextToSlideshow();
                return true;
            }
        }
        return false;
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private void appendMedia(int n, Uri object) throws MmsException {
        block11 : {
            boolean bl;
            if (n == 0) {
                return;
            }
            boolean bl2 = bl = true;
            if (this.mSlideshow.size() == 1) {
                bl2 = bl;
                if (!this.mSlideshow.get(0).hasAudio()) {
                    bl2 = bl;
                    if (!this.mSlideshow.get(0).hasVideo()) {
                        bl2 = bl;
                        if (!this.mSlideshow.get(0).hasImage()) {
                            bl2 = false;
                        }
                    }
                }
                if (!bl2) break block11;
            }
            if (!new SlideshowEditor(this.mContext, this.mSlideshow).addNewSlide()) return;
        }
        SlideModel slideModel = this.mSlideshow.get(this.mSlideshow.size() - 1);
        if (n == 1) {
            object = new ImageModel(this.mContext, (Uri)object, this.mSlideshow.getLayout().getImageRegion());
        } else if (n == 2) {
            object = new VideoModel(this.mContext, (Uri)object, this.mSlideshow.getLayout().getImageRegion());
        } else {
            if (n != 3) throw new IllegalArgumentException("changeMedia type=" + n + ", uri=" + object);
            object = new AudioModel(this.mContext, (Uri)object);
        }
        slideModel.add((MediaModel)object);
        if (n != 2) {
            if (n != 3) return;
        }
        slideModel.updateDuration(object.getDuration());
    }

    private void asyncDelete(final Uri uri, final String string, final String[] arrstring) {
        new Thread(new Runnable(){

            @Override
            public void run() {
                SqliteWrapper.delete((Context)WorkingMessage.this.mContext, (ContentResolver)WorkingMessage.this.mContentResolver, (Uri)uri, (String)string, (String[])arrstring);
            }
        }).start();
    }

    /*
     * Enabled aggressive block sorting
     */
    private void asyncDeleteDraftMmsMessage(Conversation object) {
        this.mHasMmsDraft = false;
        long l = object.getThreadId();
        if (l > 0) {
            object = "thread_id = " + l;
            this.asyncDelete(Telephony.Mms.Draft.CONTENT_URI, (String)object, null);
            this.mMessageUri = null;
            return;
        } else {
            if (this.mMessageUri == null) return;
            {
                this.asyncDelete(this.mMessageUri, null, null);
                this.mMessageUri = null;
                return;
            }
        }
    }

    private void asyncUpdateDraftMmsMessage(final Conversation conversation, final boolean bl) {
        new Thread(new Runnable(){

            /*
             * Enabled aggressive block sorting
             * Enabled unnecessary exception pruning
             * Enabled aggressive exception aggregation
             */
            @Override
            public void run() {
                try {
                    DraftCache.getInstance().setSavingDraft(true);
                    MiuiPduPersister miuiPduPersister = MiuiPduPersister.getPduPersister((Context)WorkingMessage.this.mContext);
                    SendReq sendReq = WorkingMessage.makeSendReq(conversation, WorkingMessage.this.mSubject);
                    if (WorkingMessage.this.mMessageUri == null) {
                        WorkingMessage.this.mMessageUri = WorkingMessage.createDraftMmsMessage(miuiPduPersister, sendReq, WorkingMessage.this.mSlideshow);
                    } else {
                        WorkingMessage.this.updateDraftMmsMessage(WorkingMessage.this.mMessageUri, miuiPduPersister, WorkingMessage.this.mSlideshow, sendReq);
                    }
                    if (bl && conversation.getMessageCount() == 0) {
                        conversation.clearThreadId();
                    }
                    WorkingMessage.this.updateDraftMmsMessageThreadId(conversation.ensureThreadId());
                    conversation.setDraftState(true);
                    WorkingMessage.this.asyncDeleteDraftSmsMessage(conversation);
                    return;
                }
                finally {
                    DraftCache.getInstance().setSavingDraft(false);
                }
            }
        }).start();
    }

    private void asyncUpdateDraftSmsMessage(final Conversation conversation, final String string) {
        new Thread(new Runnable(){

            @Override
            public void run() {
                try {
                    DraftCache.getInstance().setSavingDraft(true);
                    conversation.ensureThreadId();
                    conversation.setDraftState(true);
                    WorkingMessage.this.updateDraftSmsMessage(conversation, string);
                    return;
                }
                finally {
                    DraftCache.getInstance().setSavingDraft(false);
                }
            }
        }).start();
    }

    /*
     * Enabled aggressive block sorting
     */
    private void changeMedia(int n, Uri object) throws MmsException {
        this.mSlideshow.removeAllAttachFiles();
        SlideModel slideModel = this.mSlideshow.get(0);
        if (slideModel == null) {
            Log.w((String)"Mms", (String)"changeMedia: no slides!");
            return;
        } else {
            if (n == 1) {
                object = new ImageModel(this.mContext, (Uri)object, this.mSlideshow.getLayout().getImageRegion());
            } else if (n == 2) {
                object = new VideoModel(this.mContext, (Uri)object, this.mSlideshow.getLayout().getImageRegion());
            } else {
                if (n != 3) {
                    throw new IllegalArgumentException("changeMedia type=" + n + ", uri=" + object);
                }
                object = new AudioModel(this.mContext, (Uri)object);
            }
            this.checkMessageSize(slideModel, (MediaModel)object);
            if (Build.IS_CM_CUSTOMIZATION_TEST && n != 2) {
                if (n == 1) {
                    slideModel.removeImage();
                } else if (n == 3) {
                    slideModel.removeAudio();
                }
            } else {
                slideModel.removeImage();
                slideModel.removeVideo();
                slideModel.removeAudio();
                this.mAttachmentType = 0;
            }
            if (n == 0) return;
            {
                slideModel.add((MediaModel)object);
                if (n != 2 && n != 3) return;
                {
                    slideModel.updateDuration(object.getDuration());
                    return;
                }
            }
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private void checkMessageSize(SlideModel slideModel, MediaModel mediaModel) {
        MediaModel mediaModel2 = null;
        if (slideModel.hasImage()) {
            mediaModel2 = slideModel.getImage();
        } else if (slideModel.hasAudio()) {
            mediaModel2 = slideModel.getAudio();
        } else if (slideModel.hasVideo()) {
            mediaModel2 = slideModel.getVideo();
        }
        if (mediaModel2 != null) {
            int n = mediaModel.getMediaResizable() ? 0 : mediaModel.getMediaSize();
            int n2 = mediaModel2.getMediaResizable() ? 0 : mediaModel2.getMediaSize();
            if (n <= n2) return;
            this.mSlideshow.checkMessageSize(n - n2);
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private void correctAttachmentState(boolean bl) {
        int n = this.mSlideshow.sizeOfFilesAttach();
        int n2 = this.mSlideshow.size();
        if (n2 + n == 0) {
            this.removeAttachment(false);
        } else if (n > 1 || n2 > 1) {
            this.mAttachmentType = 5;
        } else {
            n2 = 0;
            if (n == 1) {
                if (!((FileAttachmentModel)this.mSlideshow.getAttachFiles().get(0)).isVCard()) {
                    throw new IllegalStateException("Unknown attachment file type");
                }
                n2 = 1;
            }
            SlideModel slideModel = this.mSlideshow.get(0);
            if (n2 != 0) {
                this.mAttachmentType = slideModel.hasImage() || slideModel.hasAudio() || slideModel.hasVideo() ? 5 : 4;
            } else if (slideModel.hasImage() && slideModel.hasAudio()) {
                this.mAttachmentType = Build.IS_CM_CUSTOMIZATION_TEST ? 1 : 5;
            } else if (slideModel.hasImage()) {
                this.mAttachmentType = 1;
            } else if (slideModel.hasVideo()) {
                this.mAttachmentType = 2;
            } else if (slideModel.hasAudio()) {
                this.mAttachmentType = 3;
            }
        }
        this.updateState(4, this.hasAttachment(), bl);
    }

    private static Uri createDraftMmsMessage(MiuiPduPersister miuiPduPersister, SendReq sendReq, SlideshowModel slideshowModel) {
        try {
            PduBody pduBody = slideshowModel.toPduBody();
            sendReq.setBody(pduBody);
            miuiPduPersister = miuiPduPersister.persist((GenericPdu)sendReq, Telephony.Mms.Draft.CONTENT_URI, null, -1);
            slideshowModel.sync(pduBody);
            return miuiPduPersister;
        }
        catch (MmsException var0_1) {
            return null;
        }
    }

    public static WorkingMessage createEmpty(MessageStatusListener messageStatusListener) {
        return new WorkingMessage(messageStatusListener);
    }

    private Dialog createProgressDialog(Context context) {
        context = new Dialog(context, 2131689560);
        context.setContentView(2130968629);
        context.setCancelable(false);
        context.getWindow().setLayout(-2, -2);
        context.getWindow().setGravity(17);
        AnimatedRotateDrawable animatedRotateDrawable = (AnimatedRotateDrawable)((ProgressBar)context.findViewById(2131820671)).getIndeterminateDrawable();
        animatedRotateDrawable.setFramesCount(60);
        animatedRotateDrawable.setFramesDuration(20);
        return context;
    }

    private void deleteDraftSmsMessage(long l) {
        SqliteWrapper.delete((Context)this.mContext, (ContentResolver)this.mContentResolver, (Uri)ContentUris.withAppendedId((Uri)Telephony.Sms.Conversations.CONTENT_URI, (long)l), (String)"type=3", (String[])null);
    }

    private void ensureSlideshow() {
        if (this.mSlideshow != null) {
            return;
        }
        SlideshowModel slideshowModel = SlideshowModel.createNew(this.mContext);
        slideshowModel.add(new SlideModel(slideshowModel));
        this.mSlideshow = slideshowModel;
    }

    /*
     * Loose catch block
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static WorkingMessage load(MessageStatusListener object, Uri uri, boolean bl) {
        Uri uri2 = uri;
        if (!uri.toString().startsWith(Telephony.Mms.Draft.CONTENT_URI.toString())) {
            uri2 = uri;
            if (!bl) {
                uri2 = MiuiPduPersister.getPduPersister((Context)object.getHostedActivity());
                if (Log.isLoggable((String)"Mms:app", (int)2)) {
                    LogTag.debug("load: moving %s to drafts", new Object[]{uri});
                }
                uri2 = uri2.move(uri, Telephony.Mms.Draft.CONTENT_URI);
            }
        }
        if (!(object = new WorkingMessage((MessageStatusListener)object)).loadFromUri(uri2, bl)) return null;
        return object;
        catch (MmsException mmsException) {
            LogTag.error("Can't move %s to drafts", new Object[]{uri});
            return null;
        }
    }

    public static WorkingMessage loadDraft(MessageStatusListener messageStatusListener, Conversation conversation) {
        WorkingMessage workingMessage = new WorkingMessage(messageStatusListener);
        if (workingMessage.loadFromConversation(conversation)) {
            return workingMessage;
        }
        return WorkingMessage.createEmpty(messageStatusListener);
    }

    private static SendReq makeSendReq(Conversation conversation, CharSequence charSequence) {
        String[] arrstring = conversation.getRecipients().getNumbers(true);
        conversation = new SendReq();
        if ((arrstring = EncodedStringValue.encodeStrings((String[])arrstring)) != null) {
            conversation.setTo((EncodedStringValue[])arrstring);
        }
        if (!TextUtils.isEmpty((CharSequence)charSequence)) {
            conversation.setSubject(new EncodedStringValue(charSequence.toString()));
        }
        conversation.setDate(System.currentTimeMillis() / 1000);
        return conversation;
    }

    private void markMmsMessageWithError(Uri uri) {
        ContentValues contentValues = new ContentValues(1);
        contentValues.put("err_type", Integer.valueOf((int)10));
        long l = ContentUris.parseId((Uri)uri);
        SqliteWrapper.update((Context)this.mContext, (ContentResolver)this.mContentResolver, (Uri)Telephony.MmsSms.PendingMessages.CONTENT_URI, (ContentValues)contentValues, (String)("msg_id=" + l), (String[])null);
    }

    private void prepareForSave(boolean bl) {
        this.syncWorkingRecipients();
        if (this.requiresMms()) {
            this.ensureSlideshow();
            this.syncTextToSlideshow();
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private Uri readDraftMmsMessage(Context context, Conversation object, StringBuilder stringBuilder) {
        block6 : {
            ContentResolver contentResolver;
            block7 : {
                String string = null;
                contentResolver = context.getContentResolver();
                object = "thread_id = " + object.getThreadId();
                contentResolver = SqliteWrapper.query((Context)context, (ContentResolver)contentResolver, (Uri)Telephony.Mms.Draft.CONTENT_URI, (String[])MMS_DRAFT_PROJECTION, (String)object, (String[])null, (String)null);
                object = string;
                if (contentResolver != null) {
                    if (!contentResolver.moveToFirst()) break block6;
                    object = ContentUris.withAppendedId((Uri)Telephony.Mms.Draft.CONTENT_URI, (long)contentResolver.getLong(0));
                    string = MessageUtils.extractEncStrFromCursor((Cursor)contentResolver, 1, 2);
                    this.mTimeToSend = contentResolver.getLong(4) > 0 ? contentResolver.getLong(3) : 0;
                    this.mTimeToSendDesc = this.formatDateTime(context, this.mTimeToSend);
                    if (string != null) {
                        stringBuilder.append(string);
                    }
                    if (!Log.isLoggable((String)"Mms:app", (int)2)) break block7;
                    LogTag.debug("readDraftMmsMessage uri: ", object);
                }
            }
            return object;
            finally {
                contentResolver.close();
            }
        }
        return null;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void readDraftSmsMessage(Conversation conversation) {
        boolean bl;
        boolean bl2 = true;
        long l = conversation.getThreadId();
        if (l <= 0 || !conversation.hasDraft()) {
            this.mText = "";
            return;
        }
        Uri uri = ContentUris.withAppendedId((Uri)Telephony.Sms.Conversations.CONTENT_URI, (long)l);
        this.mText = "";
        if ((uri = SqliteWrapper.query((Context)this.mContext, (ContentResolver)this.mContentResolver, (Uri)uri, (String[])SMS_DRAFT_PROJECTION, (String)"type=3", (String[])null, (String)null)) != null) {
            block9 : {
                block8 : {
                    try {
                        if (!uri.moveToFirst()) break block8;
                        this.mText = uri.getString(0);
                        this.mTimeToSend = uri.getLong(2) > 0 ? uri.getLong(1) : 0;
                        this.mTimeToSendDesc = this.formatDateTime(this.mContext, this.mTimeToSend);
                        bl = true;
                        break block9;
                    }
                    catch (Throwable var1_2) {
                        uri.close();
                        throw var1_2;
                    }
                }
                bl = false;
            }
            uri.close();
        } else {
            bl = false;
        }
        if (bl && conversation.getMessageCount() == 0) {
            this.asyncDeleteDraftSmsMessage(conversation);
            this.clearConversation(conversation, true);
        }
        if (!Log.isLoggable((String)"Mms:app", (int)2)) return;
        if (TextUtils.isEmpty((CharSequence)this.mText)) {
            bl2 = false;
        }
        LogTag.debug("readDraftSmsMessage haveDraft: ", bl2);
    }

    private void removeSubjectIfEmpty(boolean bl) {
        if (!this.hasSubject(true)) {
            this.setSubject(null, bl);
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private void setOrAppendFileAttachment(int n, Uri object, boolean bl) throws MmsException {
        if (n != 4) {
            throw new UnsupportContentTypeException("setOrAppendFileAttachment type=" + n + ", uri=" + object);
        }
        object = new VCardModel(this.mContext.getApplicationContext(), (Uri)object);
        if (bl) {
            this.mSlideshow.addFileAttachment((FileAttachmentModel)object);
            return;
        } else {
            this.mSlideshow.removeAllAttachFiles();
            this.mSlideshow.addFileAttachment((FileAttachmentModel)object);
            object = this.mSlideshow.get(0);
            object.removeImage();
            object.removeVideo();
            object.removeAudio();
            for (n = this.mSlideshow.size() - 1; n >= 1; --n) {
                this.mSlideshow.remove(n);
            }
        }
    }

    private static String stateString(int n) {
        if (n == 0) {
            return "<none>";
        }
        StringBuilder stringBuilder = new StringBuilder();
        if ((n & 1) > 0) {
            stringBuilder.append("RECIPIENTS_REQUIRE_MMS | ");
        }
        if ((n & 2) > 0) {
            stringBuilder.append("HAS_SUBJECT | ");
        }
        if ((n & 4) > 0) {
            stringBuilder.append("HAS_ATTACHMENT | ");
        }
        if ((n & 8) > 0) {
            stringBuilder.append("LENGTH_REQUIRES_MMS | ");
        }
        if ((n & 16) > 0) {
            stringBuilder.append("FORCE_MMS | ");
        }
        stringBuilder.delete(stringBuilder.length() - 3, stringBuilder.length());
        return stringBuilder.toString();
    }

    /*
     * Enabled aggressive block sorting
     */
    private void syncTextFromSlideshow() {
        SlideModel slideModel;
        if (this.mSlideshow.size() != 1 || (slideModel = this.mSlideshow.get(0)) == null || !slideModel.hasText()) {
            return;
        }
        this.mText = slideModel.getText().getText();
    }

    private void syncTextToSlideshow() {
        if (this.mSlideshow == null || this.mSlideshow.size() != 1) {
            return;
        }
        SlideModel slideModel = this.mSlideshow.get(0);
        byte[] arrby = MessageUtils.charSequence2Byte(this.mText, "utf-8");
        slideModel.add(new TextModel(this.mContext, "text/plain", "text_0.txt", 106, arrby, this.mSlideshow.getLayout().getTextRegion()));
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void updateDraftMmsMessage(Uri uri, MiuiPduPersister miuiPduPersister, SlideshowModel slideshowModel, SendReq sendReq) {
        if (Log.isLoggable((String)"Mms:app", (int)2)) {
            LogTag.debug("updateDraftMmsMessage uri=%s", new Object[]{uri});
        }
        if (uri == null) {
            Log.e((String)"WorkingMessage", (String)"updateDraftMmsMessage null uri");
            return;
        }
        miuiPduPersister.updateHeaders(uri, sendReq);
        sendReq = slideshowModel.toPduBody();
        try {
            miuiPduPersister.updateParts(uri, (PduBody)sendReq);
        }
        catch (MmsException var2_3) {
            Log.e((String)"WorkingMessage", (String)("updateDraftMmsMessage: cannot update message " + (Object)uri));
        }
        MessageUtils.setMmsSendTime(this.mContext, uri, this.mTimeToSend, System.currentTimeMillis());
        slideshowModel.sync((PduBody)sendReq);
    }

    private void updateDraftMmsMessageThreadId(long l) {
        if (this.mMessageUri != null && l >= 0) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("thread_id", Long.valueOf((long)l));
            SqliteWrapper.update((Context)this.mContext, (ContentResolver)this.mContentResolver, (Uri)this.mMessageUri, (ContentValues)contentValues, (String)null, (String[])null);
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private void updateDraftSmsMessage(Conversation conversation, String string) {
        long l = conversation.getThreadId();
        if (l <= 0) {
            return;
        }
        ContentValues contentValues = new ContentValues(3);
        contentValues.put("thread_id", Long.valueOf((long)l));
        contentValues.put("body", string);
        contentValues.put("type", Integer.valueOf((int)3));
        if (this.mTimeToSend > 0) {
            contentValues.put("timed", Long.valueOf((long)System.currentTimeMillis()));
            contentValues.put("date", Long.valueOf((long)this.mTimeToSend));
        } else {
            contentValues.put("timed", Integer.valueOf((int)0));
            contentValues.put("date", Long.valueOf((long)System.currentTimeMillis()));
        }
        SqliteWrapper.insert((Context)this.mContext, (ContentResolver)this.mContentResolver, (Uri)Telephony.Sms.CONTENT_URI, (ContentValues)contentValues);
        this.asyncDeleteDraftMmsMessage(conversation);
    }

    public void asyncDeleteDraftSmsMessage(Conversation conversation) {
        this.mHasSmsDraft = false;
        long l = conversation.getThreadId();
        if (l > 0) {
            this.asyncDelete(ContentUris.withAppendedId((Uri)Telephony.Sms.Conversations.CONTENT_URI, (long)l), "type=3", null);
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public boolean calcLengthRequiresMms() {
        boolean bl;
        boolean bl2 = bl = false;
        if (MmsConfig.getMultipartSmsEnabled()) return bl2;
        if (this.mConversation != null) {
            bl2 = bl;
            if (this.mConversation.isB2c()) return bl2;
        }
        bl2 = bl;
        if (this.mAttachmentType != 0) return bl2;
        bl2 = bl;
        if (!this.hasText()) return bl2;
        bl2 = bl;
        if (SmsMessage.calculateLength((CharSequence)this.getText(), (boolean)false)[0] < MmsConfig.getSmsToMmsTextThreshold()) return bl2;
        return true;
    }

    public void clearConversation(Conversation conversation, boolean bl) {
        if (bl && conversation.getMessageCount() == 0) {
            conversation.clearThreadId();
        }
        conversation.setDraftState(false);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void discard() {
        synchronized (this) {
            LogTag.debug("[WorkingMessage] discard", new Object[0]);
            boolean bl = this.mDiscarded;
            if (!bl) {
                this.mDiscarded = true;
                if (this.mHasMmsDraft) {
                    this.asyncDeleteDraftMmsMessage(this.mConversation);
                    this.clearConversation(this.mConversation, true);
                }
                if (this.mHasSmsDraft) {
                    this.asyncDeleteDraftSmsMessage(this.mConversation);
                    this.clearConversation(this.mConversation, true);
                }
            }
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public String formatDateTime(Context object, long l) {
        String string;
        if (l <= 0) {
            return "";
        }
        int n = DateFormat.is24HourFormat((Context)object) ? 128 : 64;
        object = string = DateUtils.formatDateTime((Context)object, (long)l, (int)(n | 21));
        if (!TextUtils.isEmpty((CharSequence)string)) return object;
        return "";
    }

    public Conversation getConversation() {
        return this.mConversation;
    }

    public int getCurrentMmsSize() {
        return MessageUtils.getCurrentMmsSize(this.mText, this.mSlideshow);
    }

    public SlideshowModel getSlideshow() {
        return this.mSlideshow;
    }

    public CharSequence getSubject() {
        return this.mSubject;
    }

    public CharSequence getText() {
        return this.mText;
    }

    public long getTimeToSend() {
        return this.mTimeToSend;
    }

    public String getTimeToSendDesc() {
        return this.mTimeToSendDesc;
    }

    public String getWorkingRecipients() {
        if (this.mWorkingRecipients == null) {
            return null;
        }
        return ContactList.getByNumbers(this.mWorkingRecipients).serialize();
    }

    public boolean hasAttachment() {
        if (this.mAttachmentType < 32 && this.mAttachmentType > 0) {
            return true;
        }
        return false;
    }

    public boolean hasSlideshow() {
        if (this.mAttachmentType == 5) {
            return true;
        }
        return false;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public boolean hasSubject(boolean bl) {
        boolean bl2 = false;
        if ((this.mMmsState & 2) != 0) {
            bl2 = true;
        }
        boolean bl3 = bl2;
        if (!bl) return bl3;
        bl3 = bl2;
        if (!bl2) return bl3;
        if (this.mSubject == null) return false;
        bl3 = bl2;
        if (TextUtils.getTrimmedLength((CharSequence)this.mSubject) > 0) return bl3;
        return false;
    }

    public boolean hasText() {
        boolean bl;
        boolean bl2 = bl = false;
        if (this.mText != null) {
            int n;
            char c2;
            int n2 = this.mText.length();
            for (n = 0; n < n2 && ((c2 = this.mText.charAt(n)) < ' ' || c2 == '\uffff'); ++n) {
            }
            while (n2 > n && ((c2 = this.mText.charAt(n2 - 1)) < ' ' || c2 == '\uffff')) {
                --n2;
            }
            bl2 = bl;
            if (n2 > n) {
                bl2 = true;
            }
        }
        return bl2;
    }

    public boolean isDiscarded() {
        return this.mDiscarded;
    }

    public boolean isFakeMmsForDraft() {
        if ((this.mMmsState & 16) > 0) {
            return true;
        }
        return false;
    }

    public boolean isWorthSaving() {
        if (this.hasText() || this.hasSubject(true) || this.hasAttachment() || this.hasSlideshow() || this.isFakeMmsForDraft()) {
            return true;
        }
        return false;
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    protected boolean loadFromConversation(Conversation conversation) {
        if (conversation.getThreadId() <= 0) {
            return false;
        }
        this.readDraftSmsMessage(conversation);
        if (!TextUtils.isEmpty((CharSequence)this.mText)) {
            this.mHasSmsDraft = true;
            return true;
        }
        StringBuilder stringBuilder = new StringBuilder();
        if ((conversation = this.readDraftMmsMessage(this.mContext, conversation, stringBuilder)) == null) return false;
        if (!this.loadFromUri((Uri)conversation, false)) return false;
        if (stringBuilder.length() > 0) {
            this.setSubject(stringBuilder.toString(), false);
        }
        this.setLengthRequiresMms(this.calcLengthRequiresMms(), false);
        return true;
    }

    public boolean loadFromUri(Uri uri, boolean bl) {
        return this.loadFromUri(uri, bl, false);
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public boolean loadFromUri(Uri var1_1, boolean var2_2, boolean var3_3) {
        block3 : {
            if (Log.isLoggable((String)"Mms:app", (int)2)) {
                LogTag.debug("loadFromUri %s", new Object[]{var1_1});
            }
            try {
                this.mSlideshow = SlideshowModel.createFromMessageUri(this.mContext, var1_1);
                if (!var2_2) break block3;
            }
            catch (MmsException var4_4) {
                LogTag.error("Couldn't load URI %s", new Object[]{var1_1});
                return false;
            }
            this.mMessageUri = null;
            this.mSlideshow.onModelChanged(null, true);
            ** GOTO lbl14
        }
        this.mMessageUri = var1_1;
lbl14: // 2 sources:
        this.mHasMmsDraft = true;
        this.syncTextFromSlideshow();
        this.correctAttachmentState(var3_3);
        return true;
    }

    public void removeAttachment(boolean bl) {
        this.mAttachmentType = 0;
        this.mSlideshow = null;
        if (this.mMessageUri != null) {
            this.asyncDelete(this.mMessageUri, null, null);
            this.mMessageUri = null;
        }
        this.updateState(4, false, bl);
        if (bl && this.mStatusListener != null) {
            this.mStatusListener.onAttachmentChanged();
        }
    }

    public void removeFakeMmsForDraft() {
        this.updateState(16, false, false);
    }

    public boolean requiresMms() {
        if (this.mMmsState > 0) {
            return true;
        }
        return false;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public Uri saveAsMms(boolean bl) {
        if (this.mDiscarded) {
            LogTag.warn("saveAsMms mDiscarded: true mConversation: returning NULL uri and bailing", new Object[0]);
            return null;
        }
        this.updateState(16, true, bl);
        this.prepareForSave(true);
        try {
            DraftCache.getInstance().setSavingDraft(true);
            this.mConversation.ensureThreadId();
            this.mConversation.setDraftState(true);
            MiuiPduPersister miuiPduPersister = MiuiPduPersister.getPduPersister((Context)this.mContext);
            SendReq sendReq = WorkingMessage.makeSendReq(this.mConversation, this.mSubject);
            if (this.mMessageUri == null) {
                this.mMessageUri = WorkingMessage.createDraftMmsMessage(miuiPduPersister, sendReq, this.mSlideshow);
            } else {
                this.updateDraftMmsMessage(this.mMessageUri, miuiPduPersister, this.mSlideshow, sendReq);
            }
            this.mHasMmsDraft = true;
            return this.mMessageUri;
        }
        finally {
            DraftCache.getInstance().setSavingDraft(false);
        }
    }

    public void saveDraft(boolean bl) {
        if (this.mDiscarded) {
            LogTag.warn("saveDraft mDiscarded: true mConversation: skipping saving draft and bailing", new Object[0]);
            return;
        }
        if (this.mConversation == null) {
            throw new IllegalStateException("saveDraft() called with no conversation");
        }
        this.prepareForSave(false);
        if (this.requiresMms()) {
            this.asyncUpdateDraftMmsMessage(this.mConversation, bl);
            this.mHasMmsDraft = true;
            this.mConversation.setDraftState(true);
            return;
        }
        String string = this.mText.toString();
        if (!TextUtils.isEmpty((CharSequence)string)) {
            this.asyncUpdateDraftSmsMessage(this.mConversation, string);
            this.mHasSmsDraft = true;
            this.mConversation.setDraftState(true);
            return;
        }
        this.asyncDeleteDraftMmsMessage(this.mConversation);
    }

    public void send(String string, int n) {
        this.sendSmsAndMms(string, n);
    }

    /*
     * Enabled aggressive block sorting
     */
    public void sendSmsAndMms(String object, int n) {
        if (Log.isLoggable((String)"Mms:transaction", (int)2)) {
            LogTag.debug("send", new Object[0]);
        }
        this.removeSubjectIfEmpty(true);
        this.prepareForSave(true);
        Conversation conversation = this.mConversation;
        String string = this.mText.toString();
        if (this.requiresMms() || this.addressContainsEmailToMms(conversation, string)) {
            if (MmsConfig.getUaProfUrl() == null) {
                object = new ContentRestrictionException("WorkingMessage.send MMS sending failure. mms_config.xml is missing uaProfUrl setting.  uaProfUrl is required for MMS service, but can be absent for SMS.");
                Log.e((String)"WorkingMessage", (String)"WorkingMessage.send MMS sending failure. mms_config.xml is missing uaProfUrl setting.  uaProfUrl is required for MMS service, but can be absent for SMS.", (Throwable)object);
                throw object;
            }
            object = new SendMessageTask(this, conversation, this.mMessageUri, this.mSlideshow);
        } else {
            object = new SendMessageTask(this, conversation, this.mText.toString(), (String)object);
        }
        object.setSlotId(n);
        object.setSendByMx(this.mSendByMx);
        object.setSendByMx2(this.mSendByMxV2);
        object.executeOnExecutor(sAsyncTaskExecutor, (Object[])new Void[0]);
        RecipientIdCache.updateNumbers(conversation.getThreadId(), conversation.getRecipients());
        this.mDiscarded = true;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public int setAttachment(int var1_1, Uri var2_2, boolean var3_8) {
        block16 : {
            if (Log.isLoggable((String)"Mms:app", (int)2)) {
                LogTag.debug("setAttachment type=%d uri %s", new Object[]{var1_1, var2_2});
            }
            var4_9 = 0;
            if (var1_1 == 0 && this.mAttachmentType == 5 && this.mSlideshow != null && var2_2 == null && !var3_8) {
                new SlideshowEditor(this.mContext, this.mSlideshow).removeAllSlides();
            }
            this.ensureSlideshow();
            if (var1_1 == 4) {
                this.setOrAppendFileAttachment(var1_1, var2_2, var3_8);
            }
            if (!var3_8) ** GOTO lbl15
            try {
                this.appendMedia(var1_1, var2_2);
                break block16;
lbl15: // 1 sources:
                this.changeMedia(var1_1, var2_2);
            }
            catch (UnsupportContentTypeException var2_3) {
                var4_9 = -3;
            }
            catch (ExceedMessageSizeException var2_4) {
                var4_9 = -2;
            }
            catch (ResolutionException var2_5) {
                var4_9 = -4;
            }
            catch (ContentRestrictionException var2_6) {
                var4_9 = -1;
            }
            catch (MmsException var2_7) {
                var4_9 = -1;
            }
        }
        if (var4_9 == 0) {
            this.mAttachmentType = var1_1;
        } else if (var3_8 && var1_1 != 4) {
            new SlideshowEditor(this.mContext, this.mSlideshow).removeSlide(this.mSlideshow.size() - 1);
        }
        if (this.mStatusListener != null) {
            this.mStatusListener.onAttachmentChanged();
        }
        if (!var3_8 && this.calcLengthRequiresMms()) {
            this.setLengthRequiresMms(true, false);
        } else {
            this.updateState(4, this.hasAttachment(), true);
        }
        this.correctAttachmentState(false);
        return var4_9;
    }

    public void setConversation(Conversation conversation) {
        this.mConversation = conversation;
        this.setHasEmail(conversation.getRecipients().containsEmail(), false);
    }

    public void setHasEmail(boolean bl, boolean bl2) {
        if (MmsConfig.getEmailGateway() != null) {
            this.updateState(1, false, bl2);
            return;
        }
        this.updateState(1, bl, bl2);
    }

    public void setLengthRequiresMms(boolean bl, boolean bl2) {
        this.updateState(8, bl, bl2);
    }

    public void setSendByMx(boolean bl) {
        this.mSendByMx = bl;
    }

    public void setSendByMxV2(boolean bl) {
        this.mSendByMxV2 = bl;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void setSubject(CharSequence charSequence, boolean bl) {
        this.mSubject = charSequence;
        boolean bl2 = charSequence != null;
        this.updateState(2, bl2, bl);
    }

    public void setText(CharSequence charSequence) {
        this.mText = charSequence;
        this.setLengthRequiresMms(this.calcLengthRequiresMms(), true);
    }

    public void setTimeToSend(long l) {
        this.mTimeToSend = l;
    }

    public void setTimeToSendDesc(String string) {
        this.mTimeToSendDesc = string;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public void setWorkingRecipients(List<String> object) {
        this.mWorkingRecipients = object;
        if (object == null) return;
        int n = object.size();
        switch (n) {
            default: {
                "{...} len=" + n;
                return;
            }
            case 1: {
                object = (String)object.get(0);
                return;
            }
            case 0: 
        }
    }

    public void syncWorkingRecipients() {
        if (this.mWorkingRecipients != null) {
            ContactList contactList = ContactList.getByNumbers(this.mWorkingRecipients);
            if (!contactList.equals((Object)this.mConversation.getRecipients())) {
                if (this.mConversation.getThreadId() != 0) {
                    this.asyncDeleteDraftSmsMessage(this.mConversation);
                }
                this.mConversation.setRecipients(contactList);
            }
            this.mWorkingRecipients = null;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public void tryInsertExtraText(String string) {
        if (this.mSlideshow == null) {
            LogTag.error("error: slideshow is null, create an empty one", new Object[0]);
            this.ensureSlideshow();
        }
        int n = 0;
        while (n < this.mSlideshow.size()) {
            SlideModel slideModel = this.mSlideshow.get(n);
            if (slideModel != null && !slideModel.hasText()) {
                TextModel textModel = new TextModel((Context)this.mActivity, "text/plain", "text_" + n + ".txt", this.mSlideshow.getLayout().getTextRegion());
                textModel.setText(string);
                slideModel.add(textModel);
                return;
            }
            ++n;
        }
    }

    public void unDiscard() {
        this.mDiscarded = false;
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    protected void updateState(int n, boolean bl, boolean bl2) {
        if (!sMmsEnabled) {
            return;
        }
        int n2 = this.mMmsState;
        this.mMmsState = bl ? (this.mMmsState |= n) : (this.mMmsState &= ~ n);
        if (this.mMmsState == 16 && (n2 & -17) > 0) {
            this.mMmsState = 0;
        }
        if (bl2) {
            if (n2 == 0 && this.mMmsState != 0 && this.mStatusListener != null) {
                this.mStatusListener.onProtocolChanged(true);
            } else if (n2 != 0 && this.mMmsState == 0 && this.mStatusListener != null) {
                this.mStatusListener.onProtocolChanged(false);
            }
        }
        if (n2 == this.mMmsState) return;
        if (!Log.isLoggable((String)"Mms:app", (int)2)) return;
        String string = bl ? "+" : "-";
        LogTag.debug("updateState: %s%s = %s", string, WorkingMessage.stateString(n), WorkingMessage.stateString(this.mMmsState));
    }

    public static interface MessageStatusListener {
        public Activity getHostedActivity();

        public void onAttachmentChanged();

        public void onAttachmentError(int var1, Uri var2, boolean var3);

        public void onMaxPendingMessagesReached();

        public void onMessageSent();

        public void onPreMessageSent();

        public void onProtocolChanged(boolean var1);
    }

    private class SendMessageTask
    extends AsyncTask<Void, Void, Void> {
        private Conversation mConv;
        private Uri mMmsUri;
        private String mMsgText;
        private boolean mNeedShow;
        private MiuiPduPersister mPersister;
        private Dialog mProgressDialog;
        private String mRecipientsInUI;
        private volatile boolean mSendByMx;
        private volatile boolean mSendByMxV2;
        private SlideshowModel mSlideshow;
        private int mSlotId;
        private boolean mWasSent;
        final /* synthetic */ WorkingMessage this$0;

        public SendMessageTask(WorkingMessage workingMessage, Conversation conversation, Uri uri, SlideshowModel slideshowModel) {
            this.this$0 = workingMessage;
            this.mConv = conversation;
            this.mMmsUri = workingMessage.mMessageUri;
            this.mPersister = MiuiPduPersister.getPduPersister((Context)workingMessage.mContext);
            this.mSlideshow = slideshowModel;
            this.mNeedShow = true;
        }

        /*
         * Enabled aggressive block sorting
         */
        public SendMessageTask(WorkingMessage workingMessage, Conversation conversation, String string, String string2) {
            boolean bl = true;
            this.this$0 = workingMessage;
            this.mConv = conversation;
            this.mMsgText = string;
            this.mRecipientsInUI = string2;
            if (conversation.getRecipients().size() <= 1) {
                bl = false;
            }
            this.mNeedShow = bl;
        }

        /*
         * Enabled aggressive block sorting
         */
        private void preSendSmsWorker(Conversation object, String string, String string2) {
            UserHappinessSignals.userAcceptedImeText(this.this$0.mContext);
            if (this.this$0.mStatusListener != null) {
                this.this$0.mStatusListener.onPreMessageSent();
            }
            long l = object.getThreadId();
            long l2 = object.ensureThreadId();
            String string3 = object.getRecipients().serialize();
            if (l != 0 && l != l2 || !string3.equals((Object)string2) && !TextUtils.isEmpty((CharSequence)string2)) {
                object = l != 0 && l != l2 ? "WorkingMessage.preSendSmsWorker threadId changed or recipients changed. origThreadId: " + l + " new threadId: " + l2 + " also mConversation.getThreadId(): " + this.this$0.mConversation.getThreadId() : "Recipients in window: ";
                if (this.this$0.mActivity != null) {
                    LogTag.warnPossibleRecipientMismatch((String)object, this.this$0.mActivity);
                }
            }
            this.sendSmsWorker(string, string3, l2);
            this.this$0.deleteDraftSmsMessage(l2);
        }

        /*
         * Unable to fully structure code
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         * Lifted jumps to return sites
         */
        private void sendMmsWorker(Conversation var1_1, Uri var2_7, MiuiPduPersister var3_8, SlideshowModel var4_9, SendReq var5_10) {
            block22 : {
                UserHappinessSignals.userAcceptedImeText(this.this$0.mContext);
                var11_11 = null;
                var12_12 = SqliteWrapper.query((Context)this.this$0.mContext, (ContentResolver)this.this$0.mContentResolver, (Uri)Telephony.Mms.Outbox.CONTENT_URI, (String[])WorkingMessage.access$400(), (String)null, (String[])null, (String)null);
                if (var12_12 == null) break block22;
                var11_11 = var12_12;
                var9_13 = MmsConfig.getMaxSizeScaleForPendingMmsAllowed() * MmsConfig.getMaxMessageSize();
                var7_14 = 0;
                do {
                    var11_11 = var12_12;
                    if (!var12_12.moveToNext()) break;
                    var11_11 = var12_12;
                    var7_14 += var12_12.getLong(1);
                } while (true);
                if (var7_14 < var9_13) break block22;
                var11_11 = var12_12;
                this.this$0.unDiscard();
                var11_11 = var12_12;
                if (this.this$0.mStatusListener != null) {
                    var11_11 = var12_12;
                    this.this$0.mStatusListener.onMaxPendingMessagesReached();
                }
                if (var12_12 == null) return;
                var12_12.close();
                return;
            }
            if (var12_12 != null) {
                var12_12.close();
            }
            if (this.this$0.mStatusListener != null) {
                this.this$0.mStatusListener.onPreMessageSent();
            }
            DraftCache.getInstance().setSavingDraft(true);
            var7_14 = var1_1.ensureThreadId();
            if (Log.isLoggable((String)"Mms:app", (int)2)) {
                LogTag.debug("sendMmsWorker: update draft MMS message " + (Object)var2_7, new Object[0]);
            }
            if ((var11_11 = var1_1.getRecipients().getNumbers(true)).length != 1 || (var1_1 = Conversation.verifySingleRecipient(this.this$0.mContext, var1_1.getThreadId(), var11_11[0])).equals((Object)var11_11[0])) ** GOTO lbl41
            var11_11[0] = var1_1;
            try {
                var1_1 = EncodedStringValue.encodeStrings((String[])var11_11);
                if (var1_1 != null) {
                    var5_10.setTo((EncodedStringValue[])var1_1);
                }
lbl41: // 4 sources:
                if (var2_7 == null) {
                    var2_7 = WorkingMessage.access$500(var3_8, var5_10, var4_9);
lbl43: // 2 sources:
                    do {
                        WorkingMessage.access$200(this.this$0, var7_14);
                        var6_15 = 0;
                        var4_9.finalResize(var2_7);
                        break;
                    } while (true);
                }
                ** GOTO lbl54
                catch (Throwable var1_2) {
                    if (var11_11 == null) throw var1_2;
                    var11_11.close();
                    throw var1_2;
                }
lbl54: // 1 sources:
                WorkingMessage.access$600(this.this$0, var2_7, var3_8, var4_9, var5_10);
                ** continue;
            }
            catch (Throwable var1_3) {
                throw var1_3;
            }
            finally {
                DraftCache.getInstance().setSavingDraft(false);
            }
            catch (ExceedMessageSizeException var1_4) {
                var6_15 = -2;
            }
            catch (MmsException var1_5) {
                var6_15 = -1;
            }
            if (var6_15 != 0) {
                WorkingMessage.access$700(this.this$0, var2_7);
                if (this.this$0.mStatusListener == null) return;
                this.this$0.mStatusListener.onAttachmentError(var6_15, var2_7, true);
                return;
            }
            LogTag.debug("sendMmsWorker sending message: threadId=%d", new Object[]{var7_14});
            var1_1 = new MmsMessageSender(this.this$0.mContext, var2_7, var4_9.getCurrentMessageSize(), WorkingMessage.access$300(this.this$0), this.mSendByMx, this.mSlotId);
            try {
                if (!var1_1.sendMessage()) {
                    SqliteWrapper.delete((Context)this.this$0.mContext, (ContentResolver)this.this$0.mContentResolver, (Uri)var2_7, (String)null, (String[])null);
                }
            }
            catch (Exception var1_6) {
                Log.e((String)"WorkingMessage", (String)("Failed to send message: " + (Object)var2_7 + ", threadId=" + var7_14), (Throwable)var1_6);
            }
            this.mWasSent = true;
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        private void sendSmsWorker(String object, String arrstring, long l) {
            arrstring = TextUtils.split((String)arrstring, (String)";");
            Log.d((String)"Mms:transaction", (String)("sendSmsWorker sending message: threadId=" + l));
            object = new SmsMessageSender(this.this$0.mContext, arrstring, (String)object, l, this.this$0.mTimeToSend, this.mSendByMx, this.mSlotId);
            try {
                object.sendMessage();
            }
            catch (Exception var1_2) {
                Log.e((String)"WorkingMessage", (String)("Failed to send SMS message, threadId=" + l), (Throwable)var1_2);
            }
            this.mWasSent = true;
        }

        /*
         * Enabled aggressive block sorting
         */
        private void updateSendStats(Conversation conversation) {
            conversation = new ArrayList((Collection)Arrays.asList((Object[])conversation.getRecipients().getNumbers()));
            DataUsageStatUpdater dataUsageStatUpdater = new DataUsageStatUpdater(this.this$0.mContext);
            int n = conversation.size();
            if (n <= 50) {
                dataUsageStatUpdater.updateWithPhoneNumber((Collection<String>)((Object)conversation));
                return;
            } else {
                ArrayList arrayList = new ArrayList();
                int n2 = 0;
                for (int i = 0; i < n; ++i) {
                    int n3 = n2 + 1;
                    arrayList.add(conversation.get(i));
                    n2 = n3;
                    if (n3 <= 50) continue;
                    dataUsageStatUpdater.updateWithPhoneNumber((Collection<String>)arrayList);
                    arrayList.clear();
                    n2 = 0;
                }
                if (n2 <= 0) return;
                {
                    dataUsageStatUpdater.updateWithPhoneNumber((Collection<String>)arrayList);
                    return;
                }
            }
        }

        /*
         * Enabled aggressive block sorting
         */
        protected /* varargs */ Void doInBackground(Void ... sendReq) {
            if (this.mSlideshow != null) {
                this.mSlideshow.prepareForSend();
                sendReq = WorkingMessage.makeSendReq(this.mConv, this.this$0.mSubject);
                this.sendMmsWorker(this.mConv, this.mMmsUri, this.mPersister, this.mSlideshow, sendReq);
            } else {
                this.preSendSmsWorker(this.mConv, this.mMsgText, this.mRecipientsInUI);
            }
            this.updateSendStats(this.mConv);
            this.mConv.setDraftState(false);
            return null;
        }

        protected void onPostExecute(Void void_) {
            if (this.mWasSent && this.this$0.mStatusListener != null) {
                this.this$0.mStatusListener.onMessageSent();
            }
            if (this.this$0.mStatusListener != null && this.this$0.mStatusListener.getHostedActivity().getWindow().isDestroyed()) {
                this.mNeedShow = false;
                this.mProgressDialog = null;
            }
            if (this.mNeedShow && this.mProgressDialog != null && this.mProgressDialog.isShowing()) {
                this.mProgressDialog.dismiss();
                this.mProgressDialog = null;
            }
            if (this.this$0.mActivity != null && this.this$0.mActivity instanceof MessageEditableActivityBase) {
                ((MessageEditableActivityBase)this.this$0.mActivity).setTaskDialog(null);
            }
        }

        protected void onPreExecute() {
            if (this.mNeedShow) {
                this.mProgressDialog = this.this$0.createProgressDialog((Context)this.this$0.mActivity);
                if (this.this$0.mActivity != null && this.this$0.mActivity instanceof MessageEditableActivityBase) {
                    ((MessageEditableActivityBase)this.this$0.mActivity).setTaskDialog(this.mProgressDialog);
                }
                this.mProgressDialog.show();
            }
        }

        public void setSendByMx(boolean bl) {
            this.mSendByMx = bl;
        }

        public void setSendByMx2(boolean bl) {
            this.mSendByMxV2 = bl;
        }

        public void setSlotId(int n) {
            this.mSlotId = n;
        }
    }

}

