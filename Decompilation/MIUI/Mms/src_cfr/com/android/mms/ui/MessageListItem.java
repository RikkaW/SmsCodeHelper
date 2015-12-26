/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.AlertDialog
 *  android.app.AlertDialog$Builder
 *  android.content.ActivityNotFoundException
 *  android.content.ComponentName
 *  android.content.ContentResolver
 *  android.content.ContentValues
 *  android.content.Context
 *  android.content.DialogInterface
 *  android.content.DialogInterface$OnClickListener
 *  android.content.Intent
 *  android.content.pm.PackageManager
 *  android.content.res.ColorStateList
 *  android.content.res.CompatibilityInfo
 *  android.content.res.MiuiConfiguration
 *  android.content.res.Resources
 *  android.database.sqlite.SqliteWrapper
 *  android.graphics.Paint
 *  android.graphics.Paint$FontMetricsInt
 *  android.graphics.Rect
 *  android.graphics.drawable.Drawable
 *  android.net.Uri
 *  android.os.Handler
 *  android.os.Message
 *  android.provider.Telephony
 *  android.provider.Telephony$Mms
 *  android.provider.Telephony$Sms
 *  android.text.Html
 *  android.text.TextPaint
 *  android.text.TextUtils
 *  android.text.TextUtils$TruncateAt
 *  android.text.method.HideReturnsTransformationMethod
 *  android.text.method.TransformationMethod
 *  android.text.style.URLSpan
 *  android.util.AttributeSet
 *  android.util.Log
 *  android.util.Pair
 *  android.view.LayoutInflater
 *  android.view.MotionEvent
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.View$OnTouchListener
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.view.ViewGroup$MarginLayoutParams
 *  android.view.ViewStub
 *  android.view.animation.AlphaAnimation
 *  android.view.animation.Animation
 *  android.view.animation.AnimationUtils
 *  android.view.animation.TranslateAnimation
 *  android.widget.Button
 *  android.widget.CheckBox
 *  android.widget.CompoundButton
 *  android.widget.CompoundButton$OnCheckedChangeListener
 *  android.widget.ImageView
 *  android.widget.LinearLayout
 *  android.widget.LinearLayout$LayoutParams
 *  android.widget.RelativeLayout
 *  android.widget.RelativeLayout$LayoutParams
 *  android.widget.TextView
 *  android.widget.Toast
 *  java.lang.Class
 *  java.lang.Enum
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.lang.Thread
 *  java.util.ArrayList
 *  java.util.HashSet
 *  miui.app.Activity
 *  miui.os.Build
 *  miui.text.util.Linkify
 *  miui.view.menu.ContextMenuDialog
 *  miui.widget.CircleProgressBar
 */
package com.android.mms.ui;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.content.res.CompatibilityInfo;
import android.content.res.MiuiConfiguration;
import android.content.res.Resources;
import android.database.sqlite.SqliteWrapper;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.provider.Telephony;
import android.text.Html;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.TransformationMethod;
import android.text.style.URLSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.android.mms.MmsApp;
import com.android.mms.audio.AudioItemCache;
import com.android.mms.audio.AudioItemController;
import com.android.mms.data.Contact;
import com.android.mms.model.ContactMessage;
import com.android.mms.model.ContactParser;
import com.android.mms.transaction.Transaction;
import com.android.mms.transaction.TransactionService;
import com.android.mms.ui.ConversationBase;
import com.android.mms.ui.MessageFullscreenActivity;
import com.android.mms.ui.MessageItem;
import com.android.mms.ui.MessageUtils;
import com.android.mms.ui.MultipleRecipientsConversationDetailsActivity;
import com.android.mms.ui.PreviewImageLoader;
import com.android.mms.ui.SimplePduDoc;
import com.android.mms.ui.SimplePduPart;
import com.android.mms.ui.SlideshowActivity;
import com.android.mms.ui.ThumbnailView;
import com.android.mms.understand.UnderstandAction;
import com.android.mms.understand.UnderstandFactory;
import com.android.mms.understand.UnderstandMessage;
import com.android.mms.util.MSimUtils;
import com.android.mms.util.MiStatSdkHelper;
import com.miui.gallery.lib.MiuiGalleryUtils;
import com.xiaomi.mms.data.MxIdCache;
import com.xiaomi.mms.mx.data.Attachment;
import com.xiaomi.mms.mx.utils.AttachmentUtils;
import com.xiaomi.mms.transaction.MxActivateService;
import com.xiaomi.mms.transaction.MxMmsTransactionService;
import com.xiaomi.mms.transaction.PushSession;
import com.xiaomi.mms.utils.B2cMessageUtils;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import miui.app.Activity;
import miui.os.Build;
import miui.text.util.Linkify;
import miui.view.menu.ContextMenuDialog;
import miui.widget.CircleProgressBar;

public class MessageListItem
extends RelativeLayout
implements Contact.UpdateListener {
    private static final int LINK_MASK_WITH_TIME = Linkify.TIME_PHRASES | 15;
    private static final Drawable sDefaultThumbnailMaskDrawable;
    private static final Drawable sInThumbnailMaskDrawable;
    private static final Drawable sInThumbnailNormalMaskDrawable;
    private static final Drawable sInThumbnailPressedMaskDrawable;
    private static final Drawable sLastInThumbnailMaskDrawable;
    private static final Drawable sLastInThumbnailNormalMaskDrawable;
    private static final Drawable sLastInThumbnailPressedMaskDrawable;
    private static final Drawable sLastOutThumbnailMaskDrawable;
    private static final Drawable sLastOutThumbnailNormalMaskDrawable;
    private static final Drawable sLastOutThumbnailPressedMaskDrawable;
    private static final Drawable sOutThumbnailMaskDrawable;
    private static final Drawable sOutThumbnailNormalMaskDrawable;
    private static final Drawable sOutThumbnailPressedMaskDrawable;
    private static final Drawable sThumbnailNormalMaskDrawable;
    private ImageView mAttachmentFavouriteView;
    private View mAttachmentPadding;
    private ThumbnailView mAttachmentPreview;
    private AudioItemCache mAudioItemCache;
    private AudioItemController mAudioUiController;
    private int mBackgroundRes;
    private LinearLayout mBodyLinearLayout;
    private String mBodySubstitution;
    private TextView mBodyTextView;
    private List<TextView> mBodyTextViewList = new ArrayList();
    private LinearLayout mBtnRegionLayout;
    private View mBubble;
    private final int mBubbleAttachmentPreviewMargin;
    private final int mBubbleCheckBoxDistance;
    private final int mBubbleIndicatorTopMargin;
    private final int mBubbleTopMargin;
    private final int mBubbleTopMarginMinimum;
    private CheckBox mCheckBox;
    private View mCheckBoxContainer;
    private final int mCheckBoxWidth;
    private View mDateIndicatorPanel;
    private TextView mDateTextView;
    private TextView mDeliverStatus;
    private View mDividerV1;
    private View mDividerV2;
    private Button mDownloadButton;
    private View mDownloadPending;
    private CircleProgressBar mDownloadProgress;
    private View mDownloadView;
    private boolean mEditMode;
    private ImageView mFavouriteMark;
    private LinearLayout mFileShareLayout;
    private TextView mFirstButton;
    private Button mGroupSendCancelButton;
    private Button mGroupSendFailedButton;
    private View mGroupSendFailedRoot;
    private TextView mGroupSendFailedTitle;
    private CircleProgressBar mGroupSendIcon;
    private GroupSendViewStubController mGroupSendViewController;
    private Handler mHandler;
    private String mHighlight;
    private boolean mIsPrivate;
    private final int mMaxBubbleTextWidth;
    private MessageItem mMessageItem;
    private View mMessageItemLayout;
    private final int mMmsAttachmentPaddingWidth;
    private View mMmsDownloadPadding;
    private final int mMmsDownloadPaddingWidth;
    private ImageView mMxTypeGuideView;
    private View mMxTypeIndicatorPanel;
    private TextView mMxTypeView;
    private AlertDialog mResendDialog = null;
    private View mResentButton;
    private TextView mSecondButton;
    private TextView mSenderTextView;
    private ImageView mSlotIdView;
    private View mStatusDivider;
    private TextView mSubjectTextView;
    private TextView mThirdButton;
    private int mThumbnailAlignType;
    private Drawable mThumbnailMaskDrawable;
    private Drawable mThumbnailNormalMaskDrawable;
    private Drawable mThumbnailPressedMaskDrawable;
    private final int mTimedMessagePaddingWidth;
    private Button mTimedMsgIndicator;
    private View mTimedMsgPadding;
    private List<UnderstandMessage> mUnderstandMessageList;
    private View mUnderstandPadding;

    static {
        long l = System.currentTimeMillis();
        Resources resources = MmsApp.getApp().getResources();
        sDefaultThumbnailMaskDrawable = resources.getDrawable(2130838013);
        sInThumbnailMaskDrawable = resources.getDrawable(2130837809);
        sLastInThumbnailMaskDrawable = resources.getDrawable(2130837812);
        sOutThumbnailMaskDrawable = resources.getDrawable(2130837831);
        sLastOutThumbnailMaskDrawable = resources.getDrawable(2130837840);
        sInThumbnailPressedMaskDrawable = resources.getDrawable(2130837815);
        sLastInThumbnailPressedMaskDrawable = resources.getDrawable(2130837817);
        sOutThumbnailPressedMaskDrawable = resources.getDrawable(2130837843);
        sLastOutThumbnailPressedMaskDrawable = resources.getDrawable(2130837845);
        sInThumbnailNormalMaskDrawable = resources.getDrawable(2130837814);
        sLastInThumbnailNormalMaskDrawable = resources.getDrawable(2130837816);
        sOutThumbnailNormalMaskDrawable = resources.getDrawable(2130837842);
        sLastOutThumbnailNormalMaskDrawable = resources.getDrawable(2130837844);
        sThumbnailNormalMaskDrawable = resources.getDrawable(2130838014);
        long l2 = System.currentTimeMillis();
        Log.d((String)"MessageListItem", (String)("Initialize cacehed drawables of MessageListItem: " + (l2 - l) + " ms"));
    }

    public MessageListItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mMmsAttachmentPaddingWidth = this.mContext.getResources().getDimensionPixelSize(2131427336);
        this.mTimedMessagePaddingWidth = this.mContext.getResources().getDimensionPixelSize(2131427338);
        this.mMmsDownloadPaddingWidth = this.mContext.getResources().getDimensionPixelSize(2131427339);
        this.mCheckBoxWidth = this.mContext.getResources().getDimensionPixelSize(2131427360);
        this.mMaxBubbleTextWidth = this.mContext.getResources().getDimensionPixelSize(2131427347);
        this.mBubbleCheckBoxDistance = this.mContext.getResources().getDimensionPixelSize(2131427351);
        this.mBubbleTopMargin = this.mContext.getResources().getDimensionPixelSize(2131427394);
        this.mBubbleTopMarginMinimum = this.mContext.getResources().getDimensionPixelSize(2131427395);
        this.mBubbleIndicatorTopMargin = this.mContext.getResources().getDimensionPixelSize(2131427396);
        this.mBubbleAttachmentPreviewMargin = this.mContext.getResources().getDimensionPixelSize(2131427409);
    }

    private boolean IsMxConnected(String string2, int n) {
        if (string2 != null && PushSession.getInstance(this.mContext).isConnected(n)) {
            return true;
        }
        return false;
    }

    static /* synthetic */ GroupSendViewStubController access$1000(MessageListItem messageListItem) {
        return messageListItem.getGroupSendViewStubController();
    }

    /*
     * Enabled aggressive block sorting
     */
    private void adjustAttachmentPreviewMargin(MessageItem messageItem) {
        if (!(this.mAttachmentPreview.getVisibility() == 8 || this.isThumbnail(messageItem) || this.mBodyTextView.getVisibility() == 8 && this.mBodyLinearLayout.getVisibility() == 8 && this.mSubjectTextView.getVisibility() == 8)) {
            if (this.mAttachmentPadding != null) {
                this.mAttachmentPadding.setVisibility(0);
                return;
            }
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams)this.mAttachmentPreview.getLayoutParams();
            if (messageItem.isBubbleOutLayoutStyle()) {
                layoutParams.leftMargin = this.mBubbleAttachmentPreviewMargin;
            } else {
                layoutParams.rightMargin = this.mBubbleAttachmentPreviewMargin;
            }
            this.mAttachmentPreview.setLayoutParams((ViewGroup.LayoutParams)layoutParams);
            return;
        }
        if (this.mAttachmentPadding != null) {
            this.mAttachmentPadding.setVisibility(8);
            return;
        }
        messageItem = (RelativeLayout.LayoutParams)this.mAttachmentPreview.getLayoutParams();
        messageItem.leftMargin = 0;
        messageItem.rightMargin = 0;
        this.mAttachmentPreview.setLayoutParams((ViewGroup.LayoutParams)messageItem);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void bindAttachmentView(MessageItem messageItem) {
        if (this.isThumbnail(messageItem)) {
            this.mAttachmentPreview.setBackgroundDrawable(this.mThumbnailMaskDrawable, this.mThumbnailAlignType);
        } else if (this.isVcardPreview(messageItem)) {
            this.mAttachmentPreview.setBackgroundDrawable(null, 0);
        } else {
            this.mAttachmentPreview.setBackgroundDrawable(sDefaultThumbnailMaskDrawable, 0);
        }
        this.mAttachmentPreview.setMaskDrawable(this.mThumbnailPressedMaskDrawable, this.mThumbnailNormalMaskDrawable);
        if (this.isMxAudioAttachment(messageItem)) {
            if (messageItem.isBubbleLayoutStyle()) {
                this.getAudioItemController().initListItem(messageItem, this.mHandler);
                return;
            }
            if (!messageItem.isBookmarkLayoutStyle()) return;
            this.mAttachmentPreview.setVisibility(0);
            this.mAttachmentPreview.setImageResource(2130837801, true);
            return;
        }
        if (messageItem.getMmsPreviewDataTs() != 0) {
            this.mAttachmentPreview.setVisibility(0);
            if (PreviewImageLoader.getInstance().request(messageItem.getMsgId(), messageItem.getMmsPreviewDataTs(), this.mAttachmentPreview)) return;
            this.mAttachmentPreview.setImageResource(2130837803, true);
            return;
        }
        switch (messageItem.getMmsPreviewType()) {
            case 1: {
                return;
            }
            default: {
                if (messageItem.getMx2Attachments() != null) return;
                this.mAttachmentPreview.setVisibility(0);
                this.mAttachmentPreview.setImageResource(2130837803, true);
                return;
            }
            case 3: {
                this.mAttachmentPreview.setVisibility(0);
                this.mAttachmentPreview.setImageResource(2130837801, true);
                return;
            }
            case 4: {
                this.mAttachmentPreview.setVisibility(0);
                this.mAttachmentPreview.setImageResource(2130837806, true);
                return;
            }
            case 5: 
        }
        this.mAttachmentPreview.setVisibility(0);
        this.mAttachmentPreview.setImageResource(2130837802);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void bindBackground(MessageItem messageItem) {
        int n;
        this.mThumbnailMaskDrawable = sDefaultThumbnailMaskDrawable;
        this.mThumbnailAlignType = 0;
        this.mThumbnailPressedMaskDrawable = null;
        this.mThumbnailNormalMaskDrawable = sThumbnailNormalMaskDrawable;
        switch (messageItem.getLayoutStyle()) {
            default: {
                n = 0;
                break;
            }
            case 0: 
            case 1: 
            case 2: {
                n = 0;
                break;
            }
            case 3: {
                n = 2130837808;
                this.mThumbnailAlignType = 1;
                this.mThumbnailMaskDrawable = sInThumbnailMaskDrawable;
                this.mThumbnailPressedMaskDrawable = sInThumbnailPressedMaskDrawable;
                this.mThumbnailNormalMaskDrawable = sInThumbnailNormalMaskDrawable;
                break;
            }
            case 4: {
                n = 2130837811;
                this.mThumbnailAlignType = 1;
                this.mThumbnailMaskDrawable = sLastInThumbnailMaskDrawable;
                this.mThumbnailPressedMaskDrawable = sLastInThumbnailPressedMaskDrawable;
                this.mThumbnailNormalMaskDrawable = sLastInThumbnailNormalMaskDrawable;
                break;
            }
            case 5: {
                n = messageItem.getMxType() == 0 ? (messageItem.isGroup() ? 2130837833 : 2130837830) : (messageItem.isGroup() ? 2130837863 : 2130837860);
                this.mThumbnailAlignType = 2;
                this.mThumbnailMaskDrawable = sOutThumbnailMaskDrawable;
                this.mThumbnailPressedMaskDrawable = sOutThumbnailPressedMaskDrawable;
                this.mThumbnailNormalMaskDrawable = sOutThumbnailNormalMaskDrawable;
                break;
            }
            case 6: {
                n = messageItem.getMxType() == 0 ? (messageItem.isGroup() ? 2130837836 : 2130837839) : (messageItem.isGroup() ? 2130837866 : 2130837869);
                this.mThumbnailAlignType = 2;
                this.mThumbnailMaskDrawable = sLastOutThumbnailMaskDrawable;
                this.mThumbnailPressedMaskDrawable = sLastOutThumbnailPressedMaskDrawable;
                this.mThumbnailNormalMaskDrawable = sLastOutThumbnailNormalMaskDrawable;
                break;
            }
            case 7: {
                n = messageItem.isGroup() ? 2130837821 : 2130837818;
                this.mThumbnailAlignType = 2;
                this.mThumbnailMaskDrawable = sOutThumbnailMaskDrawable;
                this.mThumbnailPressedMaskDrawable = sOutThumbnailPressedMaskDrawable;
                this.mThumbnailNormalMaskDrawable = sOutThumbnailNormalMaskDrawable;
                break;
            }
            case 8: {
                n = messageItem.isGroup() ? 2130837824 : 2130837827;
                this.mThumbnailAlignType = 2;
                this.mThumbnailMaskDrawable = sLastOutThumbnailMaskDrawable;
                this.mThumbnailPressedMaskDrawable = sLastOutThumbnailPressedMaskDrawable;
                this.mThumbnailNormalMaskDrawable = sLastOutThumbnailNormalMaskDrawable;
            }
        }
        if ((this.isThumbnail(messageItem) || this.shouldDisableCardBubble(messageItem)) && this.mMessageItemLayout != null) {
            this.mMessageItemLayout.setBackground(null);
            this.mMessageItemLayout.setPadding(0, 0, 0, 0);
        } else if (n != 0) {
            this.mMessageItemLayout.setBackgroundResource(n);
            this.mThumbnailNormalMaskDrawable = null;
        }
        this.mBackgroundRes = n;
    }

    /*
     * Enabled aggressive block sorting
     */
    private void bindBodyTextForNonNotifInd(MessageItem messageItem) {
        Object object;
        int n;
        String string2 = messageItem.getSubject();
        Object object2 = object = null;
        if (messageItem.isBubbleLayoutStyle()) {
            if (this.shouldMmsCollapse() && this.mBodySubstitution == null) {
                object2 = this.mBodyTextView;
                n = TextUtils.isEmpty((CharSequence)string2) ? 3 : 2;
                object2.setMaxLines(n);
                this.mBodyTextView.setEllipsize(TextUtils.TruncateAt.END);
                this.mBodyTextView.setAutoLinkMask(0);
                object2 = object;
            } else {
                this.mBodyTextView.setMaxHeight(Integer.MAX_VALUE);
                this.mBodyTextView.setEllipsize(null);
                if (MessageUtils.isMessagingTemplateAllowed(this.getContext())) {
                    this.mBodyTextView.setAutoLinkMask(15);
                } else {
                    this.mBodyTextView.setAutoLinkMask(LINK_MASK_WITH_TIME);
                }
                object2 = this.mUnderstandMessageList;
            }
        }
        n = this.getMaxTextWidth(messageItem);
        if (messageItem.isGroup() && messageItem.isSending()) {
            this.bindSubjectAndBody(string2, messageItem.getSendingBodyInGroup(), messageItem.getTextContentType(), messageItem.getSendingContactMessageInGroup(), null, n, messageItem.getLayoutStyle());
            return;
        }
        object = this.mBodySubstitution != null ? this.mBodySubstitution : messageItem.getBody();
        this.bindSubjectAndBody(string2, (String)object, messageItem.getTextContentType(), messageItem.getContactMessage(), (List<UnderstandMessage>)object2, n, messageItem.getLayoutStyle());
    }

    /*
     * Enabled aggressive block sorting
     */
    private void bindBodyTextForNotifInd(MessageItem messageItem) {
        String string2 = this.mContext.getString(2131361953) + String.valueOf((int)((messageItem.getMessageSize() + 1023) / 1024)) + this.mContext.getString(2131361812) + '\n' + messageItem.getExpireTimestamp();
        int n = 0;
        if (messageItem.isBubbleLayoutStyle()) {
            if (this.mEditMode) {
                n = this.mContext.getResources().getDimensionPixelSize(2131427348);
                n = this.mMaxBubbleTextWidth - this.mMmsDownloadPaddingWidth - n;
            } else {
                n = this.mMaxBubbleTextWidth - this.mMmsDownloadPaddingWidth;
            }
        }
        this.mBodyTextView.setMaxHeight(Integer.MAX_VALUE);
        this.mBodyTextView.setEllipsize(null);
        if (MessageUtils.isMessagingTemplateAllowed(this.getContext())) {
            this.mBodyTextView.setAutoLinkMask(15);
        } else {
            this.mBodyTextView.setAutoLinkMask(LINK_MASK_WITH_TIME);
        }
        this.bindSubjectAndBody(messageItem.getSubject(), string2, "text/plain", null, this.mUnderstandMessageList, n, messageItem.getLayoutStyle());
    }

    private void bindBookmark(MessageItem messageItem) {
        this.bindBodyTextForNonNotifInd(messageItem);
        if (this.mMessageItem.isMms()) {
            this.bindAttachmentView(messageItem);
            if (this.mAttachmentPreview.getVisibility() != 8 && this.mAttachmentPadding != null) {
                this.mAttachmentPadding.setVisibility(0);
            }
        }
        this.bindTimestamp(messageItem);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void bindCardBody(ContactMessage.ContactRecord object, boolean bl) {
        int n;
        int n2;
        LinearLayout linearLayout = (LinearLayout)MessageListItem.inflate((Context)this.mContext, (int)2130968593, (ViewGroup)null);
        if (!this.shouldDisableCardBubble(this.mMessageItem)) {
            n = 2130837555;
            n2 = (int)this.getResources().getDimension(2131427489);
        } else {
            n = this.mMessageItem.isGroup() ? 2130837562 : 2130837561;
            if (!bl) {
                n = 2130837558;
            }
            n2 = this.mMaxBubbleTextWidth;
        }
        linearLayout.setBackgroundResource(n);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        this.mBodyLinearLayout.addView((View)linearLayout, (ViewGroup.LayoutParams)layoutParams);
        layoutParams = (TextView)linearLayout.findViewById(2131820579);
        linearLayout = (LinearLayout)linearLayout.findViewById(2131820581);
        linearLayout.setMinimumWidth(n2);
        ContactParser contactParser = ContactParser.getContactParser(this.mContext);
        object = object.getContactRecord().iterator();
        while (object.hasNext()) {
            Pair pair = (Pair)object.next();
            if ("type_name".equals((Object)contactParser.getTypeLabel(this.mContext, (String)pair.first))) {
                layoutParams.setText((CharSequence)pair.second);
                continue;
            }
            LinearLayout linearLayout2 = (LinearLayout)LayoutInflater.from((Context)this.mContext).inflate(2130968594, (ViewGroup)linearLayout, false);
            TextView textView = (TextView)linearLayout2.findViewById(2131820582);
            TextView textView2 = (TextView)linearLayout2.findViewById(2131820583);
            textView.setText((CharSequence)String.format((String)"[%s]", (Object[])new Object[]{contactParser.getTypeLabel(this.getContext(), (String)pair.first)}));
            textView2.setText((CharSequence)pair.second);
            linearLayout.addView((View)linearLayout2);
        }
        return;
    }

    /*
     * Enabled aggressive block sorting
     */
    private void bindCheckStatus(MessageItem messageItem, boolean bl, boolean bl2) {
        if (this.mEditMode) {
            if (this.mCheckBoxContainer == null) {
                messageItem = this.findViewById(2131820773);
                messageItem.setVisibility(0);
                this.mCheckBoxContainer = messageItem;
                this.mCheckBox = (CheckBox)this.findViewById(16908289);
            }
            this.mCheckBoxContainer.setVisibility(0);
            if (!bl2) {
                this.mCheckBox.setChecked(false);
                return;
            }
            this.mCheckBox.setChecked(true);
            return;
        } else {
            if (this.mCheckBoxContainer == null) return;
            {
                this.mCheckBoxContainer.setVisibility(8);
                return;
            }
        }
    }

    private void bindCommonIndicators(MessageItem messageItem) {
        this.bindMxType(messageItem);
        this.bindTimestamp(messageItem);
        this.bindDeliveryInfo(messageItem);
        this.bindResentButton(messageItem);
        this.updateStatusDivider();
        this.bindFavouriteMark(messageItem);
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private void bindDeliveryInfo(MessageItem var1_1) {
        var3_2 = 1;
        if (this.mDeliverStatus == null) {
            return;
        }
        var6_3 = null;
        if (var1_1.isOutMessage() == false) return;
        if (var1_1.isFailedMessage()) {
            if (this.mEditMode) {
                this.mDeliverStatus.setVisibility(8);
                return;
            }
            if (var1_1.isGroup()) {
                this.mDeliverStatus.setVisibility(4);
                return;
            }
            if (var1_1.isReadOnly()) {
                var1_1 = this.getContext().getString(2131362066);
                this.mDeliverStatus.setText((CharSequence)var1_1);
                this.mDeliverStatus.setVisibility(0);
                return;
            }
            this.mDeliverStatus.setVisibility(8);
            return;
        }
        var4_4 = var1_1.getDeliverReportMode();
        if (var4_4 == 0) {
            this.mDeliverStatus.setVisibility(4);
            return;
        }
        if (!var1_1.isSms()) ** GOTO lbl52
        if (!Build.IS_CM_CUSTOMIZATION_TEST) ** GOTO lbl29
        var2_5 = MSimUtils.isMSim() != false ? MSimUtils.getSlotIdBySimInfoId(var1_1.getSimId()) : 0;
        if ((var4_4 & 16) == 16 && var2_5 == 0 || (var4_4 & 32) == 32 && var2_5 == 1) ** GOTO lbl31
        ** GOTO lbl-1000
lbl29: // 1 sources:
        if ((var4_4 & 16) != 16) lbl-1000: // 2 sources:
        {
            var3_2 = 0;
        }
lbl31: // 4 sources:
        if (var3_2 == 0) ** GOTO lbl54
        if (var1_1.getMxType() != 0) ** GOTO lbl39
        if (var1_1.getDeliveryStatus() != MessageItem.DeliveryStatus.RECEIVED) ** GOTO lbl36
        var5_7 = this.getContext().getString(2131362065);
        ** GOTO lbl98
lbl36: // 1 sources:
        if (var1_1.getDeliveryStatus() != MessageItem.DeliveryStatus.PENDING) ** GOTO lbl54
        var5_7 = this.getContext().getString(2131362067);
        ** GOTO lbl98
lbl39: // 1 sources:
        switch (var1_1.getMxType()) {
            default: {
                ** GOTO lbl54
            }
            case 1: {
                var5_7 = this.getContext().getString(2131362067);
                break;
            }
            case 16: {
                var5_7 = this.getContext().getString(2131362350);
                break;
            }
            case 17: {
                var5_7 = this.getContext().getString(2131362065);
                break;
            }
        }
        ** GOTO lbl98
lbl52: // 1 sources:
        if (var1_1.isMms()) {
            var3_2 = MSimUtils.isMSim() != false ? MSimUtils.getSlotIdBySimInfoId(var1_1.getSimId()) : 0;
        }
lbl54: // 5 sources:
        var5_7 = null;
        ** GOTO lbl98
        if (!Build.IS_CM_CUSTOMIZATION_TEST) ** GOTO lbl63
        if ((var4_4 & 4) != 4 || var3_2 != 0) ** GOTO lbl60
        var2_6 = true;
        ** GOTO lbl67
lbl60: // 1 sources:
        if ((var4_4 & 8) != 8 || var3_2 != 1) ** GOTO lbl-1000
        var2_6 = true;
        ** GOTO lbl67
lbl63: // 1 sources:
        if ((var4_4 & 4) == 4) {
            var2_6 = true;
        } else lbl-1000: // 2 sources:
        {
            var2_6 = false;
        }
lbl67: // 4 sources:
        var5_7 = var6_3;
        if (var2_6) {
            if (var1_1.getDeliveryStatus() == MessageItem.DeliveryStatus.RECEIVED) {
                var5_7 = this.getContext().getString(2131362065);
            } else if (var1_1.getDeliveryStatus() == MessageItem.DeliveryStatus.REJECTED) {
                var5_7 = Build.IS_CM_CUSTOMIZATION_TEST ? this.getContext().getString(2131362065) : this.getContext().getString(2131362069);
            } else if (Build.IS_CM_CUSTOMIZATION_TEST && var1_1.getDeliveryStatus() == MessageItem.DeliveryStatus.EXPIRED) {
                var5_7 = this.getContext().getString(2131362070);
            } else {
                var5_7 = var6_3;
                if (var1_1.getBoxId() == 4) {
                    var5_7 = this.getContext().getString(2131362067);
                }
            }
        }
        if ((var4_4 & 1) == 1 && var3_2 == 0) {
            if (var1_1.getDeliveryStatus() == MessageItem.DeliveryStatus.RECEIVED && var1_1.isReadReport()) {
                var5_7 = this.getContext().getString(2131362068);
            }
        } else if ((var4_4 & 2) == 2 && var3_2 == 1 && var1_1.getDeliveryStatus() == MessageItem.DeliveryStatus.RECEIVED && var1_1.isReadReport()) {
            var5_7 = this.getContext().getString(2131362068);
        }
        switch (var1_1.getMxType()) {
            default: {
                break;
            }
            case 1: {
                var5_7 = this.getContext().getString(2131362067);
                break;
            }
            case 16: {
                var5_7 = this.getContext().getString(2131362350);
                break;
            }
            case 17: {
                var5_7 = this.getContext().getString(2131362065);
            }
        }
lbl98: // 8 sources:
        if (this.mEditMode) {
            this.mDeliverStatus.setVisibility(8);
            return;
        }
        if (var1_1.isGroup() && var1_1.isSms()) {
            this.mDeliverStatus.setVisibility(4);
            return;
        }
        if (var5_7 != null) {
            this.mDeliverStatus.setVisibility(0);
            this.mDeliverStatus.setText((CharSequence)var5_7);
            return;
        }
        this.mDeliverStatus.setVisibility(8);
    }

    private void bindDownloadButtonForNonNotifInd(MessageItem messageItem) {
        if (this.mDownloadView != null) {
            this.mDownloadView.setVisibility(8);
        }
        if (this.mMmsDownloadPadding != null) {
            this.mMmsDownloadPadding.setVisibility(8);
        }
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private void bindDownloadButtonForNotifInd(final MessageItem messageItem) {
        int n = messageItem.getMmsStatus() & -5;
        if (n == 0) {
            if (this.mDownloadView == null) return;
            this.mDownloadView.setVisibility(8);
            return;
        }
        if (this.mDownloadView == null) {
            ViewStub viewStub = (ViewStub)this.findViewById(2131820719);
            if (viewStub == null) return;
            this.mDownloadView = viewStub.inflate();
            this.mDownloadButton = (Button)this.findViewById(2131820721);
            this.mDownloadPending = this.findViewById(2131820720);
            this.mDownloadProgress = (CircleProgressBar)this.findViewById(2131820722);
            if (this.mDownloadProgress != null) {
                this.mDownloadProgress.setDrawablesForLevels(new int[]{2130837654}, new int[]{2130837655}, null);
                this.mDownloadProgress.setMax(100);
            }
        }
        this.mDownloadView.setVisibility(0);
        if (this.mMmsDownloadPadding != null) {
            this.mMmsDownloadPadding.setVisibility(0);
        }
        switch (n) {
            default: {
                this.mDownloadPending.clearAnimation();
                this.mDownloadPending.setVisibility(8);
                this.mDownloadProgress.setVisibility(8);
                this.mDownloadButton.setVisibility(0);
                this.mDownloadButton.setFocusable(false);
                if (!this.mEditMode) break;
                this.mDownloadButton.setEnabled(false);
                return;
            }
            case 129: {
                if (Transaction.getCurrentTransactionMsgId() == messageItem.getMsgId()) {
                    this.mDownloadProgress.setVisibility(0);
                    this.mDownloadPending.clearAnimation();
                    this.mDownloadPending.setVisibility(8);
                    this.mDownloadProgress.setProgress(Transaction.getCurrentTransactionProgress());
                } else {
                    this.mDownloadProgress.setVisibility(8);
                    this.mDownloadPending.setVisibility(0);
                    this.mDownloadPending.startAnimation(AnimationUtils.loadAnimation((Context)this.mContext, (int)2131034119));
                }
                this.mDownloadButton.setVisibility(8);
                return;
            }
        }
        this.mDownloadButton.setEnabled(true);
        this.mDownloadButton.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                if (messageItem.isMiXin()) {
                    MxMmsTransactionService.startRetrieveMms(MessageListItem.this.mContext, messageItem.getMessageUri());
                    return;
                }
                view = new Intent(MessageListItem.this.mContext, (Class)TransactionService.class);
                view.putExtra("uri", messageItem.getMessageUri().toString());
                view.putExtra("type", 1);
                MessageListItem.this.mContext.startService((Intent)view);
            }
        });
    }

    /*
     * Enabled aggressive block sorting
     */
    private void bindFavouriteMark(MessageItem messageItem) {
        if (!messageItem.isGroup() && messageItem.isLocked() && !this.mIsPrivate) {
            if (this.isThumbnail(messageItem)) {
                if (this.mAttachmentFavouriteView != null) {
                    this.mAttachmentFavouriteView.setVisibility(0);
                }
                if (this.mFavouriteMark == null) return;
                {
                    this.mFavouriteMark.setVisibility(8);
                    return;
                }
            } else {
                if (this.mFavouriteMark != null) {
                    this.mFavouriteMark.setVisibility(0);
                }
                if (this.mAttachmentFavouriteView == null) return;
                {
                    this.mAttachmentFavouriteView.setVisibility(8);
                    return;
                }
            }
        } else {
            if (this.mFavouriteMark != null) {
                this.mFavouriteMark.setVisibility(8);
            }
            if (this.mAttachmentFavouriteView == null) return;
            {
                this.mAttachmentFavouriteView.setVisibility(8);
                return;
            }
        }
    }

    private void bindFileShareMessageBody() {
        LinearLayout linearLayout = (LinearLayout)MessageListItem.inflate((Context)this.mContext, (int)2130968618, (ViewGroup)null);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        this.mFileShareLayout.addView((View)linearLayout, (ViewGroup.LayoutParams)layoutParams);
        ((TextView)linearLayout.findViewById(2131820649)).setText((CharSequence)this.mContext.getString(2131362260));
        ((ImageView)linearLayout.findViewById(2131820647)).setVisibility(0);
        linearLayout.findViewById(2131820648).setVisibility(0);
    }

    private void bindGroupMessageCancelButton(MessageItem messageItem) {
        final long l = messageItem.getThreadId();
        final long l2 = messageItem.getDate();
        this.mGroupSendCancelButton.setFocusable(false);
        if (this.mEditMode) {
            this.mGroupSendCancelButton.setEnabled(false);
            return;
        }
        this.mGroupSendCancelButton.setEnabled(true);
        this.mGroupSendCancelButton.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                new Thread(view.getContext()){
                    final /* synthetic */ Context val$context;

                    public void run() {
                        this.val$context.getContentResolver().delete(Telephony.Sms.CONTENT_URI, "thread_id=? AND date=? AND type=5", new String[]{String.valueOf((long)l), String.valueOf((long)l2)});
                    }
                }.start();
            }

        });
    }

    private void bindGroupMessageFailed(MessageItem messageItem) {
        if (this.mGroupSendFailedRoot == null) {
            return;
        }
        if (messageItem.isSms() && messageItem.isGroup() && messageItem.isFailedMessage() && !messageItem.isSending()) {
            this.mGroupSendFailedRoot.setVisibility(0);
            this.mGroupSendFailedTitle.setText((CharSequence)this.mContext.getString(2131362074, new Object[]{messageItem.failedMsgInGroup().size()}));
            this.bindGroupMessageFailedItems(messageItem);
            this.bindGroupMessageSendFailedButton(messageItem);
            this.bindGroupMessageCancelButton(messageItem);
            return;
        }
        this.mGroupSendFailedRoot.setVisibility(8);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void bindGroupMessageFailedItems(final MessageItem messageItem) {
        Object object = messageItem.failedMsgInGroup();
        ViewGroup viewGroup = (ViewGroup)this.findViewById(2131820757);
        viewGroup.removeAllViews();
        object = object.iterator();
        while (object.hasNext()) {
            Object object2 = (MessageItem.FailedItem)object.next();
            View view = MessageListItem.inflate((Context)this.mContext, (int)2130968653, (ViewGroup)null);
            TextView textView = (TextView)view.findViewById(2131820752);
            TextView textView2 = (TextView)view.findViewById(2131820753);
            CheckBox checkBox = (CheckBox)view.findViewById(2131820754);
            checkBox.setFocusable(false);
            if (this.mEditMode) {
                checkBox.setEnabled(false);
            } else {
                checkBox.setEnabled(true);
                checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener((MessageItem.FailedItem)object2){
                    final /* synthetic */ MessageItem.FailedItem val$failedItem;

                    /*
                     * Enabled aggressive block sorting
                     */
                    public void onCheckedChanged(CompoundButton compoundButton, boolean bl) {
                        boolean bl2 = true;
                        if (bl) {
                            messageItem.addToResend(this.val$failedItem.messageUri);
                        } else {
                            messageItem.removeFromResend(this.val$failedItem.messageUri);
                        }
                        int n = messageItem.resendMsgInGroup().size();
                        MessageListItem.this.mGroupSendFailedButton.setText((CharSequence)MessageListItem.this.mContext.getString(2131362072, new Object[]{n}));
                        compoundButton = MessageListItem.this.mGroupSendFailedButton;
                        bl = n > 0 ? bl2 : false;
                        compoundButton.setEnabled(bl);
                    }
                });
            }
            object2 = Contact.get(object2.number);
            textView.setText((CharSequence)object2.getName());
            textView2.setText((CharSequence)object2.getNumber());
            if (object2.getName().equals((Object)object2.getNumber())) {
                textView2.setVisibility(4);
            } else {
                textView2.setVisibility(0);
            }
            viewGroup.addView(view);
        }
        return;
    }

    private void bindGroupMessageSendFailedButton(final MessageItem messageItem) {
        this.mGroupSendFailedButton.setText((CharSequence)this.mContext.getString(2131362072, new Object[]{messageItem.resendMsgInGroup().size()}));
        this.mGroupSendFailedButton.setFocusable(false);
        if (this.mEditMode) {
            this.mGroupSendFailedButton.setEnabled(false);
            return;
        }
        this.mGroupSendFailedButton.setEnabled(true);
        this.mGroupSendFailedButton.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                if (MessageListItem.this.mHandler != null) {
                    view = Message.obtain((Handler)MessageListItem.this.mHandler, (int)5);
                    view.obj = messageItem.getMsgId();
                    view.arg1 = MSimUtils.getSlotIdBySimInfoId(messageItem.getSimId());
                    view.sendToTarget();
                }
            }
        });
    }

    private void bindGroupSendCount(MessageItem messageItem) {
        if (this.mGroupSendViewController != null) {
            this.mGroupSendViewController.mGroupSendCountTextView.setVisibility(8);
        }
        if (this.mGroupSendIcon != null) {
            this.mGroupSendIcon.setVisibility(8);
        }
        if (messageItem.isGroup() && messageItem.isSending()) {
            int n = messageItem.sentMsgInGroup() + 1;
            int n2 = messageItem.totalMsgInGroup();
            this.getGroupSendViewStubController().mGroupSendCountTextView.setText((CharSequence)this.mContext.getResources().getString(2131362064, new Object[]{n, n2}));
            this.getGroupSendViewStubController().mGroupSendCountTextView.setVisibility(0);
            if (this.mGroupSendIcon == null) {
                this.mGroupSendIcon = (CircleProgressBar)((ViewStub)this.findViewById(2131820743)).inflate();
            }
            if (this.mGroupSendIcon != null) {
                this.mGroupSendIcon.setDrawablesForLevels(new int[]{2130837654}, new int[]{2130837655}, null);
            }
            if (this.mGroupSendIcon != null) {
                this.mGroupSendIcon.setVisibility(0);
                this.mGroupSendIcon.setMax(n2);
                this.mGroupSendIcon.setProgress(n - 1);
            }
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private void bindMxType(MessageItem messageItem) {
        int n = 0;
        if (messageItem.getMxTypeName() != null) {
            String string2 = messageItem.getMxTypeName();
            if (this.mMxTypeIndicatorPanel != null) {
                this.mMxTypeIndicatorPanel.setVisibility(0);
            }
            if (this.mMxTypeView != null) {
                this.mMxTypeView.setText((CharSequence)string2);
            }
            if (this.mMxTypeGuideView == null) return;
            {
                if (!messageItem.isMiXin()) {
                    n = 8;
                }
                this.mMxTypeGuideView.setVisibility(n);
                return;
            }
        } else {
            if (this.mMxTypeIndicatorPanel == null) return;
            {
                this.mMxTypeIndicatorPanel.setVisibility(8);
                return;
            }
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private void bindNonCardBody(Object object, int n, boolean bl) {
        TextView textView = (TextView)MessageListItem.inflate((Context)this.mContext, (int)2130968592, (ViewGroup)null);
        textView.setTextColor(this.mBodyTextView.getTextColors());
        Resources resources = this.getResources();
        int n2 = bl ? 2131296354 : 2131296353;
        textView.setLinkTextColor(resources.getColorStateList(n2));
        if (n > 0) {
            textView.setMaxWidth(n);
        }
        if (MessageUtils.isMessagingTemplateAllowed(this.getContext())) {
            textView.setAutoLinkMask(15);
        } else {
            textView.setAutoLinkMask(LINK_MASK_WITH_TIME);
        }
        textView.setLinksClickable(false);
        textView.setText((CharSequence)object.toString());
        object = new RelativeLayout.LayoutParams(-2, -2);
        this.mBodyLinearLayout.addView((View)textView, (ViewGroup.LayoutParams)object);
        this.mBodyTextViewList.add(textView);
        if (this.shouldDisableCardBubble(this.mMessageItem)) {
            textView.setBackgroundResource(this.mBackgroundRes);
        }
    }

    private void bindNonNotifInd(MessageItem messageItem) {
        this.bindBodyTextForNonNotifInd(messageItem);
        this.bindDownloadButtonForNonNotifInd(messageItem);
        if (messageItem.isBubbleOutLayoutStyle() && messageItem.isSms() && (messageItem.isReadOnly() || messageItem.isGroup() || messageItem.isReferenceGroup())) {
            this.bindSendToTitle(messageItem);
            this.bindGroupSendCount(messageItem);
            boolean bl = this.shouldDisableCardBubble(messageItem);
            this.getGroupSendViewStubController().refreshSendView(bl);
        }
        this.bindGroupMessageFailed(messageItem);
        this.bindTimedMessage(messageItem);
        if (this.mMessageItem.isMms()) {
            this.bindAttachmentView(messageItem);
            if (messageItem.isBubbleLayoutStyle()) {
                this.adjustAttachmentPreviewMargin(messageItem);
            }
        }
    }

    private void bindNotifInd(MessageItem messageItem) {
        this.bindBodyTextForNotifInd(messageItem);
        this.bindDownloadButtonForNotifInd(messageItem);
        this.bindTimedMessage(messageItem);
    }

    private void bindResentButton(final MessageItem messageItem) {
        if (this.mResentButton == null) {
            return;
        }
        if (messageItem.isOutMessage() && messageItem.isFailedMessage() && !messageItem.isReadOnly() && !messageItem.isGroup()) {
            this.mResentButton.setFocusable(false);
            this.mResentButton.setVisibility(0);
            if (this.mEditMode || MSimUtils.getInsertedSimCount() <= 0) {
                this.mResentButton.setEnabled(false);
                return;
            }
            this.mResentButton.setEnabled(true);
            this.mResentButton.setOnClickListener(new View.OnClickListener(){

                /*
                 * Enabled aggressive block sorting
                 * Lifted jumps to return sites
                 */
                public void onClick(View view) {
                    if (MessageListItem.this.mHandler == null) return;
                    boolean bl = messageItem.isSms();
                    long l = messageItem.getMsgId();
                    if (B2cMessageUtils.isB2cNumber(Contact.get(messageItem.getAddress()))) {
                        MessageListItem.this.handleB2cMessageResend(view, l, bl, messageItem.getSimId());
                        return;
                    }
                    String string2 = MessageListItem.this.getMidViaAddress(messageItem.getAddress(), bl);
                    if (MSimUtils.isMSimInserted()) {
                        MessageListItem.this.createSimPickerDialog(view, l, bl, string2);
                        return;
                    }
                    int n = MSimUtils.getInsertedSlotId();
                    if (MSimUtils.getSlotIdBySimInfoId(messageItem.getSimId()) < 0) {
                        MessageListItem.this.updateSimId(l, n, bl);
                    }
                    if (MessageListItem.this.IsMxConnected(string2, n)) {
                        MessageListItem.this.createResentDialog(view, l, bl, n);
                        return;
                    }
                    view = MessageListItem.this.mHandler;
                    int n2 = bl ? 4 : 3;
                    view = Message.obtain((Handler)view, (int)n2);
                    view.obj = l;
                    view.arg1 = n;
                    view.sendToTarget();
                }
            });
            return;
        }
        this.mResentButton.setVisibility(8);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void bindSendToTitle(MessageItem messageItem) {
        if (messageItem.isReadOnly()) {
            this.getGroupSendViewStubController().mGroupSendToTitle.setVisibility(0);
            this.getGroupSendViewStubController().mGroupSendToTextView.setText((CharSequence)messageItem.getContactName());
            this.getGroupSendViewStubController().mGroupSendToTextView.setVisibility(0);
            return;
        } else {
            if (messageItem.isGroup() && !messageItem.isTimed()) {
                if (messageItem.isSending()) {
                    this.getGroupSendViewStubController().mGroupSendToTitle.setVisibility(0);
                    this.getGroupSendViewStubController().mGroupSendToTextView.setText((CharSequence)messageItem.getContactName());
                } else {
                    this.getGroupSendViewStubController().mGroupSendToTitle.setVisibility(8);
                    this.getGroupSendViewStubController().mGroupSendToTextView.setText((CharSequence)this.mContext.getResources().getString(2131362063, new Object[]{messageItem.totalMsgInGroup() - messageItem.failedMsgInGroup().size()}));
                }
                this.getGroupSendViewStubController().mGroupSendToTextView.setVisibility(0);
                return;
            }
            if (!messageItem.isReferenceGroup()) return;
            {
                this.getGroupSendViewStubController().mGroupSendToTitle.setVisibility(8);
                this.getGroupSendViewStubController().mGroupSendToTextView.setText(2131362155);
                this.getGroupSendViewStubController().mGroupSendToTextView.setVisibility(0);
                return;
            }
        }
    }

    private void bindSlotId(MessageItem messageItem) {
        if (!MSimUtils.isMSimInserted()) {
            this.mSlotIdView.setVisibility(8);
            return;
        }
        switch (MSimUtils.getSlotIdBySimInfoId(messageItem.getSimId())) {
            default: {
                this.mSlotIdView.setVisibility(8);
                return;
            }
            case 0: {
                this.mSlotIdView.setVisibility(0);
                this.mSlotIdView.setImageResource(2130837970);
                return;
            }
            case 1: 
        }
        this.mSlotIdView.setVisibility(0);
        this.mSlotIdView.setImageResource(2130837971);
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private void bindSubjectAndBody(String object, String string2, String string3, ContactMessage contactMessage, List<UnderstandMessage> list, int n, int n2) {
        int n3;
        void var4_6;
        void var7_9;
        if (TextUtils.isEmpty((CharSequence)object)) {
            this.mSubjectTextView.setVisibility(8);
        } else {
            this.mSubjectTextView.setVisibility(0);
            if (n3 > 0) {
                this.mSubjectTextView.setMaxWidth(n3);
            }
            MessageUtils.showTextWithHighlight(this.mSubjectTextView, (String)object, this.mHighlight, 2131689527);
        }
        this.mBodyTextViewList.clear();
        this.mBodyLinearLayout.removeAllViews();
        object = MessageUtils.getFileShareMessage(string2);
        if (object != null && !object.isEmpty() && this.mFileShareLayout != null) {
            this.mBodyTextView.setVisibility(8);
            if (this.mBodyLinearLayout != null) {
                this.mBodyLinearLayout.setVisibility(8);
            }
            this.mFileShareLayout.setVisibility(0);
            this.mFileShareLayout.removeAllViews();
            this.bindFileShareMessageBody();
            return;
        }
        if (var4_6 == null || this.mBodyLinearLayout == null) {
            void var5_7;
            void var3_5;
            if (this.mBodyLinearLayout != null) {
                this.mBodyLinearLayout.setVisibility(8);
            }
            if (TextUtils.isEmpty((CharSequence)string2)) {
                this.mBodyTextView.setVisibility(8);
                return;
            }
            this.mBodyTextView.setVisibility(0);
            if (n3 > 0) {
                this.mBodyTextView.setMaxWidth(n3);
            }
            if (var5_7 != null) {
                n3 = this.getMessageItem().isOutMessage() ? 2131689519 : 2131689517;
                UnderstandFactory.showTextWithUnderstand(this.mBodyTextView, string2, var5_7, this.mHighlight, n3, 2131689520);
                return;
            }
            if ("text/html".equals((Object)var3_5)) {
                this.mBodyTextView.setText((CharSequence)Html.fromHtml((String)string2));
                return;
            }
            MessageUtils.showTextWithHighlight(this.mBodyTextView, string2, this.mHighlight, 2131689520);
            return;
        }
        this.mBodyTextView.setVisibility(8);
        this.mBodyLinearLayout.setVisibility(0);
        object = var4_6.split();
        boolean bl = this.isCardBodyOutLayoutStyle((int)var7_9);
        object = object.iterator();
        while (object.hasNext()) {
            Object e2 = object.next();
            if (!(e2 instanceof ContactMessage.ContactRecord)) {
                this.bindNonCardBody(e2, n3, bl);
                continue;
            }
            this.bindCardBody((ContactMessage.ContactRecord)e2, bl);
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private void bindTimedMessage(final MessageItem messageItem) {
        if (!messageItem.isReadOnly() && !messageItem.isReferenceGroup() && messageItem.isTimed()) {
            ViewStub viewStub;
            if (this.mTimedMsgIndicator == null && (viewStub = (ViewStub)this.findViewById(2131820749)) != null) {
                this.mTimedMsgIndicator = (Button)viewStub.inflate();
            }
            if (this.mTimedMsgIndicator != null) {
                this.mTimedMsgIndicator.setVisibility(0);
                if (messageItem.isGroup()) {
                    this.mTimedMsgIndicator.setBackgroundResource(2130838017);
                } else {
                    this.mTimedMsgIndicator.setBackgroundResource(2130838015);
                }
                this.mTimedMsgIndicator.setFocusable(false);
                if (this.mTimedMsgPadding != null) {
                    this.mTimedMsgPadding.setVisibility(0);
                }
                if (!this.mEditMode) {
                    this.mTimedMsgIndicator.setEnabled(true);
                    this.mTimedMsgIndicator.setOnClickListener(new View.OnClickListener(){

                        public void onClick(View view) {
                            if (MessageListItem.this.mContext instanceof ConversationBase) {
                                ((ConversationBase)MessageListItem.this.mContext).setSendTimeForSpecifiedMessage(messageItem);
                            }
                        }
                    });
                    return;
                }
                this.mTimedMsgIndicator.setEnabled(false);
                return;
            } else {
                this.mTimedMsgIndicator.setVisibility(8);
                if (this.mTimedMsgPadding == null) return;
                {
                    this.mTimedMsgPadding.setVisibility(8);
                    return;
                }
            }
        } else {
            if (this.mTimedMsgIndicator != null) {
                this.mTimedMsgIndicator.setVisibility(8);
            }
            if (this.mTimedMsgPadding == null) return;
            {
                this.mTimedMsgPadding.setVisibility(8);
                return;
            }
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private void bindTimestamp(MessageItem messageItem) {
        long l = messageItem.getDisplayDate();
        if (messageItem.isListLayoutStyle()) {
            String string2;
            String string3 = string2 = MessageUtils.getRelativeTimeStamp(l);
            if (string2 != null) {
                string3 = string2;
                if (messageItem.isTimed()) {
                    string3 = this.getResources().getString(2131362154, new Object[]{string2});
                }
            }
            if (this.mSenderTextView != null) {
                if (messageItem.isGroup() || messageItem.isReadOnly()) {
                    this.mSenderTextView.setVisibility(8);
                } else {
                    this.mSenderTextView.setVisibility(0);
                    this.mSenderTextView.setText((CharSequence)messageItem.getContactName());
                }
            }
            if (this.mDateTextView != null) {
                TextView textView = this.mDateTextView;
                string2 = string3;
                if (string3 == null) {
                    string2 = "";
                }
                textView.setText((CharSequence)string2);
            }
            if (this.mSlotIdView == null) return;
            {
                this.bindSlotId(messageItem);
                return;
            }
        } else {
            if (messageItem.isBubbleLayoutStyle()) {
                String string4 = null;
                if (messageItem.getShowTimeStamp()) {
                    string4 = MessageUtils.getPreciseTimeStamp(l, true, true);
                }
                String string5 = string4;
                if (string4 != null) {
                    string5 = string4;
                    if (messageItem.isTimed()) {
                        string5 = this.getResources().getString(2131362154, new Object[]{string4});
                    }
                }
                if (string5 != null) {
                    if (this.mDateTextView != null) {
                        this.mDateTextView.setText((CharSequence)string5);
                        this.mDateTextView.setVisibility(0);
                    }
                    if (this.mSlotIdView != null) {
                        this.bindSlotId(messageItem);
                    }
                } else if (this.mDateTextView != null) {
                    this.mDateTextView.setVisibility(8);
                    this.mSlotIdView.setVisibility(8);
                }
                if (this.mDateIndicatorPanel != null && this.mDateTextView.getVisibility() == 8 && this.mSlotIdView.getVisibility() == 8) {
                    this.mDateIndicatorPanel.setVisibility(8);
                    return;
                }
                this.mDateIndicatorPanel.setVisibility(0);
                return;
            }
            String string6 = MessageUtils.getRelativeTimeStamp(l);
            if (this.mSenderTextView != null) {
                this.mSenderTextView.setText((CharSequence)this.mContext.getString(2131362102, new Object[]{messageItem.getContactName()}));
            }
            if (this.mDateTextView != null) {
                TextView textView = this.mDateTextView;
                String string7 = string6;
                if (string6 == null) {
                    string7 = "";
                }
                textView.setText((CharSequence)string7);
            }
            if (this.mSlotIdView == null) return;
            {
                this.bindSlotId(messageItem);
                return;
            }
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private void bindUnderstandButton(MessageItem messageItem) {
        if (!MessageUtils.isMessagingTemplateAllowed(this.getContext())) {
            this.hideUnderstandButton();
            return;
        } else {
            ViewStub viewStub;
            UnderstandMessage understandMessage = this.getUnderstandMessageWithButton(this.mUnderstandMessageList);
            if (understandMessage == null) {
                this.hideUnderstandButton();
                return;
            }
            if (this.mBtnRegionLayout == null && (viewStub = (ViewStub)this.findViewById(2131820727)) != null) {
                viewStub.inflate();
                this.mBtnRegionLayout = (LinearLayout)this.findViewById(2131820728);
                this.mFirstButton = (TextView)this.findViewById(2131820736);
                this.mSecondButton = (TextView)this.findViewById(2131820738);
                this.mThirdButton = (TextView)this.findViewById(2131820740);
                this.mDividerV1 = this.findViewById(2131820737);
                this.mDividerV2 = this.findViewById(2131820739);
            }
            if (this.mBtnRegionLayout != null) {
                int n = UnderstandFactory.getButtonNumber(understandMessage.mActionID);
                if (n > 0) {
                    MiStatSdkHelper.recordUnderstandButtonShown(understandMessage.mActionID);
                }
                switch (n) {
                    default: {
                        this.mBtnRegionLayout.setVisibility(8);
                        return;
                    }
                    case 1: {
                        this.setUnderstandButton(messageItem, this.mFirstButton, understandMessage, 0);
                        if (this.mDividerV1 != null) {
                            this.mDividerV1.setVisibility(8);
                        }
                        if (this.mSecondButton != null) {
                            this.mSecondButton.setVisibility(8);
                            this.mSecondButton.setOnClickListener(null);
                        }
                        if (this.mDividerV2 != null) {
                            this.mDividerV2.setVisibility(8);
                        }
                        if (this.mThirdButton == null) break;
                        this.mThirdButton.setVisibility(8);
                        this.mThirdButton.setOnClickListener(null);
                        break;
                    }
                    case 2: {
                        this.setUnderstandButton(messageItem, this.mFirstButton, understandMessage, 0);
                        if (this.mDividerV1 != null) {
                            this.mDividerV1.setVisibility(0);
                        }
                        this.setUnderstandButton(messageItem, this.mSecondButton, understandMessage, 1);
                        if (this.mDividerV2 != null) {
                            this.mDividerV2.setVisibility(8);
                        }
                        if (this.mThirdButton == null) break;
                        this.mThirdButton.setVisibility(8);
                        this.mThirdButton.setOnClickListener(null);
                        break;
                    }
                    case 3: {
                        this.setUnderstandButton(messageItem, this.mFirstButton, understandMessage, 0);
                        if (this.mDividerV1 != null) {
                            this.mDividerV1.setVisibility(0);
                        }
                        this.setUnderstandButton(messageItem, this.mSecondButton, understandMessage, 1);
                        if (this.mDividerV2 != null) {
                            this.mDividerV2.setVisibility(0);
                        }
                        this.setUnderstandButton(messageItem, this.mThirdButton, understandMessage, 2);
                    }
                }
                this.mBtnRegionLayout.setVisibility(0);
                if (this.mUnderstandPadding != null) {
                    this.mUnderstandPadding.setVisibility(0);
                }
            }
            if (this.mFavouriteMark == null || this.mFavouriteMark.getVisibility() != 0) return;
            {
                messageItem = (RelativeLayout.LayoutParams)this.mFavouriteMark.getLayoutParams();
                messageItem.addRule(2, 2131820728);
                messageItem.removeRule(8);
                this.mFavouriteMark.setLayoutParams((ViewGroup.LayoutParams)messageItem);
                return;
            }
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private void createResentDialog(View view, final long l, final boolean bl, final int n) {
        int n2 = bl ? 2131230728 : 2131230729;
        new AlertDialog.Builder(view.getContext()).setTitle(2131362071).setItems(n2, new DialogInterface.OnClickListener(){

            /*
             * Enabled aggressive block sorting
             */
            public void onClick(DialogInterface dialogInterface, int n2) {
                if (n2 == 0) {
                    dialogInterface = MessageListItem.this.mHandler;
                    n2 = bl ? 4 : 3;
                    dialogInterface = Message.obtain((Handler)dialogInterface, (int)n2);
                    dialogInterface.obj = l;
                    dialogInterface.arg1 = n;
                    dialogInterface.sendToTarget();
                    return;
                } else {
                    if (n2 != 1) return;
                    {
                        dialogInterface = MessageListItem.this.mHandler;
                        n2 = bl ? 8 : 9;
                    }
                }
                dialogInterface = Message.obtain((Handler)dialogInterface, (int)n2);
                dialogInterface.obj = l;
                dialogInterface.arg1 = n;
                dialogInterface.sendToTarget();
            }
        }).show();
    }

    private void createSimPickerDialog(View view, long l, boolean bl, String string2) {
        LayoutInflater layoutInflater = (LayoutInflater)this.mContext.getSystemService("layout_inflater");
        LinearLayout linearLayout = (LinearLayout)layoutInflater.inflate(2130968704, null);
        this.createSimPickerItem(0, l, bl, false, linearLayout, layoutInflater);
        if (this.IsMxConnected(string2, 0)) {
            this.createSimPickerItem(0, l, bl, true, linearLayout, layoutInflater);
        }
        this.createSimPickerItem(1, l, bl, false, linearLayout, layoutInflater);
        if (this.IsMxConnected(string2, 1)) {
            this.createSimPickerItem(1, l, bl, true, linearLayout, layoutInflater);
        }
        this.mResendDialog = new AlertDialog.Builder(view.getContext()).setTitle(2131362071).setView((View)linearLayout).show();
    }

    private void createSimPickerDialogForB2cMessageResend(View view, long l, boolean bl) {
        LayoutInflater layoutInflater = (LayoutInflater)this.mContext.getSystemService("layout_inflater");
        LinearLayout linearLayout = (LinearLayout)layoutInflater.inflate(2130968704, null);
        this.createSimPickerItem(0, l, bl, true, linearLayout, layoutInflater);
        this.createSimPickerItem(1, l, bl, true, linearLayout, layoutInflater);
        this.mResendDialog = new AlertDialog.Builder(view.getContext()).setTitle(2131362071).setView((View)linearLayout).show();
    }

    /*
     * Enabled aggressive block sorting
     */
    private void createSimPickerItem(final int n, final long l, final boolean bl, final boolean bl2, LinearLayout linearLayout, LayoutInflater object) {
        LinearLayout linearLayout2 = (LinearLayout)object.inflate(2130968705, (ViewGroup)linearLayout, false);
        ((ImageView)linearLayout2.findViewById(2131820664)).setImageResource(MSimUtils.getSimIconResId(n));
        switch (n) {
            default: {
                return;
            }
            case 0: {
                if (!MSimUtils.isSimInserted(0)) {
                    object = this.mContext.getString(2131362235);
                    break;
                }
                object = MSimUtils.getSimDisplayName(this.mContext, 0);
                break;
            }
            case 1: {
                object = !MSimUtils.isSimInserted(1) ? this.mContext.getString(2131362236) : MSimUtils.getSimDisplayName(this.mContext, 1);
            }
        }
        if (bl2) {
            object = this.mContext.getString(2131362237, new Object[]{object});
        }
        ((TextView)linearLayout2.findViewById(2131820628)).setText((CharSequence)object);
        linearLayout2.setOnClickListener(new View.OnClickListener(){

            /*
             * Enabled aggressive block sorting
             */
            public void onClick(View view) {
                MessageListItem.this.updateSimId(l, n, bl);
                if (!bl2) {
                    view = MessageListItem.this.mHandler;
                    int n2 = bl ? 4 : 3;
                    view = Message.obtain((Handler)view, (int)n2);
                    view.obj = l;
                    view.arg1 = n;
                    view.sendToTarget();
                } else {
                    view = MessageListItem.this.mHandler;
                    int n3 = bl ? 8 : 9;
                    view = Message.obtain((Handler)view, (int)n3);
                    view.obj = l;
                    view.arg1 = n;
                    view.sendToTarget();
                }
                if (MessageListItem.this.mResendDialog != null) {
                    MessageListItem.this.mResendDialog.dismiss();
                    MessageListItem.this.mResendDialog = null;
                }
            }
        });
        linearLayout.addView((View)linearLayout2, (ViewGroup.LayoutParams)new LinearLayout.LayoutParams(-1, -2));
    }

    private List<UnderstandMessage> filterDatetimeUnderstandMessage(List<UnderstandMessage> object) {
        ArrayList arrayList = new ArrayList();
        if (object != null && object.size() > 0) {
            object = object.iterator();
            block0 : while (object.hasNext()) {
                UnderstandMessage understandMessage = (UnderstandMessage)object.next();
                if (understandMessage == null || understandMessage.mItems.size() <= 0) continue;
                Iterator iterator = understandMessage.mItems.iterator();
                while (iterator.hasNext()) {
                    if (((UnderstandMessage.Item)iterator.next()).mType != 101) continue;
                    arrayList.add(understandMessage);
                    continue block0;
                }
            }
        }
        return arrayList;
    }

    private AudioItemController getAudioItemController() {
        if (this.mAudioUiController == null) {
            this.mAudioUiController = new AudioItemController((ViewStub)this.findViewById(2131820735), (ViewStub)this.findViewById(2131820730), this.mAudioItemCache);
        }
        return this.mAudioUiController;
    }

    private GroupSendViewStubController getGroupSendViewStubController() {
        if (this.mGroupSendViewController == null) {
            this.mGroupSendViewController = new GroupSendViewStubController();
            View view = ((ViewStub)this.findViewById(2131820660)).inflate();
            this.mGroupSendViewController.mGroupSendViewContainer = (LinearLayout)view;
            this.mGroupSendViewController.mGroupSendToTitle = (TextView)view.findViewById(2131820661);
            this.mGroupSendViewController.mGroupSendToTextView = (TextView)view.findViewById(2131820662);
            this.mGroupSendViewController.mGroupSendCountTextView = (TextView)view.findViewById(2131820663);
            this.mGroupSendViewController.mGroupSendToTitle.setVisibility(8);
            this.mGroupSendViewController.mGroupSendToTextView.setVisibility(8);
            this.mGroupSendViewController.mGroupSendCountTextView.setVisibility(8);
        }
        return this.mGroupSendViewController;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private int getMaxTextWidth(MessageItem messageItem) {
        int n;
        if (!messageItem.isBubbleLayoutStyle()) {
            return 0;
        }
        int n2 = n = this.mMaxBubbleTextWidth;
        if (this.mEditMode) {
            n2 = n - this.mContext.getResources().getDimensionPixelSize(2131427348);
        }
        n = n2;
        if (messageItem.isMms()) {
            n = n2;
            if (messageItem.getMmsPreviewType() != 1) {
                n = n2 - this.mMmsAttachmentPaddingWidth;
            }
        }
        n2 = n;
        if (!messageItem.isTimed()) return n2;
        return n - this.mTimedMessagePaddingWidth;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private String getMidViaAddress(String object, boolean bl) {
        String string2;
        String string3 = string2 = null;
        if (!MxActivateService.isMxEnabled(this.mContext)) return string3;
        object = Contact.get((String)object);
        object = MxIdCache.getOrQuery(this.mContext, object.getMxPhoneNumber());
        string3 = string2;
        if (object == null) return string3;
        if (bl) {
            string3 = string2;
            if (!object.allowSms()) return string3;
            do {
                return object.getMId();
                break;
            } while (true);
        }
        string3 = string2;
        if (!object.allowMms()) return string3;
        return object.getMId();
    }

    private UnderstandMessage getUnderstandMessageWithButton(List<UnderstandMessage> object) {
        if (object != null && object.size() > 0) {
            object = object.iterator();
            while (object.hasNext()) {
                UnderstandMessage understandMessage = (UnderstandMessage)object.next();
                if (UnderstandFactory.getButtonNumber(understandMessage.mActionID) <= 0) continue;
                return understandMessage;
            }
        }
        return null;
    }

    /*
     * Enabled aggressive block sorting
     */
    private void handleB2cMessageResend(View view, long l, boolean bl, long l2) {
        int n = 8;
        if (MSimUtils.isMSimInserted()) {
            int n2 = MSimUtils.getSlotIdBySimInfoId(l2);
            if (MSimUtils.isMSimSlotIdValid(n2)) {
                if (!PushSession.getInstance(this.mContext).isConnected(n2)) {
                    Toast.makeText((Context)this.mContext, (int)2131362405, (int)0).show();
                    return;
                }
                view = this.mHandler;
                if (!bl) {
                    n = 9;
                }
                view = Message.obtain((Handler)view, (int)n);
                view.obj = l;
                view.arg1 = n2;
                view.sendToTarget();
                return;
            }
            if (PushSession.getInstance(this.mContext).isConnected(0) && PushSession.getInstance(this.mContext).isConnected(1)) {
                this.createSimPickerDialogForB2cMessageResend(view, l, bl);
                return;
            }
            n2 = PushSession.getInstance(this.mContext).getConnectedSimIndex();
            if (!MSimUtils.isMSimSlotIdValid(n2)) {
                Toast.makeText((Context)this.mContext, (int)2131362405, (int)0).show();
                return;
            }
            view = this.mHandler;
            if (!bl) {
                n = 9;
            }
            view = Message.obtain((Handler)view, (int)n);
            view.obj = l;
            view.arg1 = n2;
            view.sendToTarget();
            return;
        }
        int n3 = MSimUtils.getInsertedSlotId();
        if (!PushSession.getInstance(this.mContext).isConnected(n3)) {
            Toast.makeText((Context)this.mContext, (int)2131362405, (int)0).show();
            return;
        }
        view = this.mHandler;
        if (!bl) {
            n = 9;
        }
        view = Message.obtain((Handler)view, (int)n);
        view.obj = l;
        view.arg1 = n3;
        view.sendToTarget();
    }

    /*
     * Enabled aggressive block sorting
     */
    private void handleUnderstandButtonground(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            view.setBackgroundColor(this.mContext.getResources().getColor(2131296328));
            return;
        } else {
            if (motionEvent.getAction() != 1 && motionEvent.getAction() != 3) return;
            {
                view.setBackgroundColor(this.mContext.getResources().getColor(2131296327));
                return;
            }
        }
    }

    private void hideGroupViewIfNeeded() {
        if (this.mGroupSendViewController != null) {
            this.mGroupSendViewController.mGroupSendToTitle.setVisibility(8);
            this.mGroupSendViewController.mGroupSendToTextView.setVisibility(8);
            this.mGroupSendViewController.mGroupSendCountTextView.setVisibility(8);
        }
    }

    private void hideMmsViewIfNeeded() {
        if (this.mAttachmentPreview != null) {
            this.mAttachmentPreview.setVisibility(8);
        }
        if (this.mAttachmentPadding != null) {
            this.mAttachmentPadding.setVisibility(8);
        }
        if (this.mGroupSendFailedRoot != null) {
            this.mGroupSendFailedRoot.setVisibility(8);
        }
        if (this.mFileShareLayout != null) {
            this.mFileShareLayout.setVisibility(8);
        }
        if (this.mMessageItem.isBubbleLayoutStyle() && this.mAudioUiController != null) {
            this.getAudioItemController().unbind();
        }
    }

    private void hideUnderstandButton() {
        if (this.mBtnRegionLayout != null) {
            this.mBtnRegionLayout.setVisibility(8);
        }
        if (this.mUnderstandPadding != null) {
            this.mUnderstandPadding.setVisibility(8);
        }
        if (this.mFavouriteMark != null && this.mFavouriteMark.getVisibility() == 0) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams)this.mFavouriteMark.getLayoutParams();
            layoutParams.removeRule(2);
            layoutParams.addRule(8, 2131820723);
            this.mFavouriteMark.setLayoutParams((ViewGroup.LayoutParams)layoutParams);
        }
    }

    public static void initDummy() {
    }

    private boolean isCardBodyOutLayoutStyle(int n) {
        if (n == 5 || n == 6 || n == 7 || n == 8) {
            return true;
        }
        return false;
    }

    private boolean isMxAudioAttachment(MessageItem messageItem) {
        boolean bl;
        boolean bl2 = bl = false;
        if (messageItem.getMx2Attachments() != null) {
            bl2 = bl;
            if (AttachmentUtils.getMessageTypeFromMimeType(messageItem.getMx2Attachments().get((int)0).mimeType) == 3) {
                bl2 = true;
            }
        }
        return bl2;
    }

    private boolean isThumbnail(MessageItem messageItem) {
        if (messageItem.isMms() && !this.isMxAudioAttachment(messageItem) && TextUtils.isEmpty((CharSequence)messageItem.getBody()) && TextUtils.isEmpty((CharSequence)messageItem.getSubject()) && !messageItem.isTimed() && !this.isVcardPreview(messageItem)) {
            return true;
        }
        return false;
    }

    private boolean isVcardPreview(MessageItem messageItem) {
        if (messageItem.getMmsPreviewType() == 5) {
            return true;
        }
        return false;
    }

    private void launchSlideshow(Uri uri) {
        Intent intent = new Intent(this.mContext, (Class)SlideshowActivity.class);
        intent.setData(uri);
        intent.putExtra("highlight", this.mHighlight);
        this.mContext.startActivity(intent);
    }

    private void onPostMeasure() {
        if (this.mBubble != null && this.mCheckBoxContainer != null && this.mCheckBoxContainer.getVisibility() == 0) {
            int n;
            int n2 = n = 0;
            if (this.mDateIndicatorPanel != null) {
                n2 = n;
                if (this.mDateIndicatorPanel.getVisibility() == 0) {
                    n2 = (this.mDateIndicatorPanel.getMeasuredHeight() + this.mBubbleIndicatorTopMargin) / 2;
                }
            }
            ((ViewGroup.MarginLayoutParams)this.mCheckBox.getLayoutParams()).topMargin = n2;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private void onPreMeasure() {
        if (this.mBubble != null) {
            int n = this.mMessageItem.getHasBubbleDistance() ? this.mBubbleTopMargin + this.mBubbleTopMarginMinimum : this.mBubbleTopMarginMinimum;
            ((ViewGroup.MarginLayoutParams)this.mBubble.getLayoutParams()).topMargin = n;
        }
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private void prepareStyle(MessageItem var1_1) {
        switch (var1_1.getLayoutStyle()) {
            case 0: 
            case 1: 
            case 2: {
                this.findViewById(2131820776).setVisibility(0);
                this.findViewById(2131820567).setVisibility(0);
                ** break;
            }
            case 3: 
            case 4: {
                this.findViewById(2131820768).setVisibility(0);
                this.findViewById(2131820770).setVisibility(0);
                ** break;
            }
            case 5: 
            case 6: {
                this.findViewById(2131820769).setVisibility(0);
                this.findViewById(2131820771).setVisibility(0);
                ** break;
            }
            case 7: 
            case 8: {
                this.findViewById(2131820769).setVisibility(0);
                this.findViewById(2131820772).setVisibility(0);
            }
lbl17: // 5 sources:
            default: {
                ** GOTO lbl21
            }
            case 11: 
        }
        this.findViewById(2131820567).setVisibility(0);
lbl21: // 2 sources:
        this.mBubble = this.findViewById(2131820766);
        this.mSenderTextView = (TextView)this.findViewById(2131820570);
        this.mMxTypeView = (TextView)this.findViewById(2131820746);
        this.mMxTypeGuideView = (ImageView)this.findViewById(2131820747);
        if (this.mMxTypeGuideView != null) {
            this.mMxTypeGuideView.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    MessageListItem.this.mHandler.removeMessages(12);
                    Message message = Message.obtain((Handler)MessageListItem.this.mHandler, (int)12);
                    message.obj = view;
                    message.sendToTarget();
                }
            });
        }
        this.mMxTypeIndicatorPanel = this.findViewById(2131820745);
        this.mDateIndicatorPanel = this.findViewById(2131820741);
        this.mDateTextView = (TextView)this.findViewById(2131820569);
        this.mSubjectTextView = (TextView)this.findViewById(2131820732);
        this.mBodyTextView = (TextView)this.findViewById(2131820578);
        this.mBodyTextView.setTransformationMethod((TransformationMethod)HideReturnsTransformationMethod.getInstance());
        this.mBodyLinearLayout = (LinearLayout)this.findViewById(2131820733);
        this.mFileShareLayout = (LinearLayout)this.findViewById(2131820734);
        this.mGroupSendFailedRoot = this.findViewById(2131820755);
        this.mGroupSendFailedTitle = (TextView)this.findViewById(2131820756);
        this.mGroupSendFailedButton = (Button)this.findViewById(2131820758);
        this.mGroupSendCancelButton = (Button)this.findViewById(2131820759);
        this.mDeliverStatus = (TextView)this.findViewById(2131820718);
        this.mResentButton = this.findViewById(2131820742);
        this.mBtnRegionLayout = (LinearLayout)this.findViewById(2131820728);
        this.mFavouriteMark = (ImageView)this.findViewById(2131820729);
        this.mMessageItemLayout = this.findViewById(2131820723);
        this.mAttachmentPadding = this.findViewById(2131820568);
        this.mAttachmentPreview = (ThumbnailView)this.findViewById(2131820572);
        this.mAttachmentFavouriteView = (ImageView)this.findViewById(2131820731);
        if (var1_1.isBubbleLayoutStyle()) {
            this.mMmsDownloadPadding = this.findViewById(2131820725);
            this.mTimedMsgPadding = this.findViewById(2131820748);
            this.mUnderstandPadding = this.findViewById(2131820726);
        }
        if (var1_1.isBubbleLayoutStyle() || var1_1.isBookmarkLayoutStyle()) {
            this.mSlotIdView = (ImageView)this.findViewById(2131820571);
        }
        if (var1_1.getMx2Type() == 3) {
            this.getAudioItemController().initListItem(var1_1, this.mHandler);
        }
        if (MiuiConfiguration.getScaleMode() == 11 || MiuiConfiguration.getScaleMode() == 15) {
            this.mStatusDivider = this.findViewById(2131820744);
        }
        this.setSubjectAndBodyPaddingBottom();
    }

    private void previewContactFromMessage(ContactMessage.ContactRecord contactRecord) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setType("vnd.android.cursor.dir/preview_contact");
        float f2 = this.mContext.getResources().getCompatibilityInfo().applicationScale;
        int[] arrn = new int[2];
        this.getLocationOnScreen(arrn);
        Rect rect = new Rect();
        rect.left = (int)((float)arrn[0] * f2 + 0.5f);
        rect.top = (int)((float)arrn[1] * f2 + 0.5f);
        rect.right = (int)((float)(arrn[0] + this.getWidth()) * f2 + 0.5f);
        rect.bottom = (int)(f2 * (float)(arrn[1] + this.getHeight()) + 0.5f);
        intent.setSourceBounds(rect);
        ContactParser.getContactParser(this.mContext).putContactToIntent(contactRecord, intent);
        this.mContext.startActivity(intent);
    }

    private void setSubjectAndBodyPaddingBottom() {
        if (MSimUtils.isAndroid50()) {
            if (this.mSubjectTextView != null) {
                this.mSubjectTextView.setPadding(this.mSubjectTextView.getPaddingLeft(), this.mSubjectTextView.getPaddingTop(), this.mSubjectTextView.getPaddingRight(), this.mSubjectTextView.getPaddingBottom() + this.mSubjectTextView.getLineHeight() - this.mSubjectTextView.getPaint().getFontMetricsInt(null));
            }
            if (this.mBodyTextView != null) {
                this.mBodyTextView.setPadding(this.mBodyTextView.getPaddingLeft(), this.mBodyTextView.getPaddingTop(), this.mBodyTextView.getPaddingRight(), this.mBodyTextView.getPaddingBottom() + this.mBodyTextView.getLineHeight() - this.mBodyTextView.getPaint().getFontMetricsInt(null));
            }
        }
    }

    private void setUnderstandButton(final MessageItem messageItem, TextView textView, final UnderstandMessage understandMessage, final int n) {
        if (textView != null) {
            textView.setText((CharSequence)UnderstandFactory.getButtonName(understandMessage.mActionID, n));
            textView.setVisibility(0);
            this.mBodyTextViewList.add(textView);
            textView.setOnClickListener(new View.OnClickListener(){

                public void onClick(View view) {
                    MiStatSdkHelper.recordUnderstandButtonClick(understandMessage.mActionID, n);
                    Object object = UnderstandFactory.getButtonActions(understandMessage.mActionID, n);
                    if (object != null) {
                        view = new ArrayList();
                        object = object.iterator();
                        while (object.hasNext()) {
                            UnderstandAction understandAction = UnderstandFactory.parseActionString((String)object.next());
                            if (understandAction == null) continue;
                            view.add(understandAction);
                        }
                        int n2 = MSimUtils.getSlotIdBySimInfoId(messageItem.getSimId());
                        UnderstandFactory.doAction(MessageListItem.this.mContext, view, understandMessage, n2);
                    }
                }
            });
            textView.setBackgroundColor(this.mContext.getResources().getColor(2131296327));
            textView.setOnTouchListener(new View.OnTouchListener(){

                public boolean onTouch(View view, MotionEvent motionEvent) {
                    MessageListItem.this.handleUnderstandButtonground(view, motionEvent);
                    return false;
                }
            });
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private boolean shouldDisableCardBubble(MessageItem messageItem) {
        if (!messageItem.isSms() || messageItem.getContactMessage() == null) return false;
        boolean bl = true;
        if (!bl) return false;
        if (messageItem.isLocked()) return false;
        if (messageItem.isTimed()) return false;
        return true;
    }

    private boolean shouldMmsCollapse() {
        if (this.mMessageItem.isMms() && (this.mMessageItem.getMmsPreviewType() == 6 || this.mMessageItem.getMmsPreviewType() > 1 && this.mMessageItem.getMmsSnippet() != null && this.mMessageItem.getMmsSnippet().length() > 70)) {
            return true;
        }
        return false;
    }

    private void startPlayAudioItem(MessageItem messageItem) {
        if (messageItem.isOutMessage()) {
            this.getAudioItemController().startPlayAudio();
            return;
        }
        if (!this.getAudioItemController().isDownloadingFile()) {
            this.getAudioItemController().startPlayAudio();
            return;
        }
        Toast.makeText((Context)this.mContext, (int)2131362360, (int)0).show();
    }

    /*
     * Enabled aggressive block sorting
     */
    private void updateSimId(long l, int n, boolean bl) {
        ContentValues contentValues = new ContentValues(1);
        contentValues.put("sim_id", Long.valueOf((long)MSimUtils.getSimIdBySlotId(n)));
        Context context = this.mContext;
        ContentResolver contentResolver = this.mContext.getContentResolver();
        Uri uri = bl ? Telephony.Sms.CONTENT_URI : Telephony.Mms.CONTENT_URI;
        SqliteWrapper.update((Context)context, (ContentResolver)contentResolver, (Uri)uri, (ContentValues)contentValues, (String)("_id=" + l), (String[])null);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void updateStatusDivider() {
        boolean bl = true;
        if (this.mStatusDivider == null) {
            return;
        }
        boolean bl2 = false;
        if (this.mDeliverStatus != null) {
            bl2 = this.mDeliverStatus.getVisibility() == 0;
            bl2 = false | bl2;
        }
        boolean bl3 = bl2;
        if (this.mResentButton != null) {
            bl3 = this.mResentButton.getVisibility() == 0 ? bl : false;
            bl3 = bl2 | bl3;
        }
        if (bl3) {
            this.mStatusDivider.setVisibility(0);
            return;
        }
        this.mStatusDivider.setVisibility(8);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void viewMmsMessageAttachment() {
        if (this.mMessageItem == null || !this.mMessageItem.isMms()) return;
        if (this.mMessageItem.getMmsPreviewType() == 3 && this.mMessageItem.getMx2Attachments() != null && this.mMessageItem.getMx2Attachments().size() > 0) {
            this.startPlayAudioItem(this.mMessageItem);
            return;
        }
        if (this.shouldMmsCollapse()) {
            this.launchSlideshow(this.mMessageItem.getMessageUri());
            return;
        }
        Object object = this.mMessageItem.getSimplePduDoc();
        if (object.isSlideShow()) {
            this.launchSlideshow(object.getUri());
            return;
        }
        if (!object.canShow() || (object = object.getPageAppearancePart(0)) == null) return;
        try {
            this.mContext.startActivity(object.getIntent());
            return;
        }
        catch (ActivityNotFoundException var1_2) {
            Toast.makeText((Context)this.mContext, (CharSequence)this.mContext.getString(2131362136), (int)0).show();
            return;
        }
    }

    public void bind(MessageItem messageItem) {
        this.prepareStyle(messageItem);
    }

    public MessageItem getMessageItem() {
        return this.mMessageItem;
    }

    protected void onMeasure(int n, int n2) {
        this.onPreMeasure();
        super.onMeasure(n, n2);
        this.onPostMeasure();
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public void onMessageListItemClick() {
        if (this.mContext instanceof ConversationBase && !((ConversationBase)this.mContext).isRunning()) {
            return;
        }
        if (this.mBodyTextView.getVisibility() != 0) ** GOTO lbl6
        var10_1 = this.mBodyTextView.getUrls();
        ** GOTO lbl-1000
lbl6: // 1 sources:
        if (this.mBodyLinearLayout == null || this.mBodyLinearLayout.getVisibility() != 0) ** GOTO lbl9
        var10_1 = new ArrayList();
        ** GOTO lbl100
lbl9: // 1 sources:
        var10_1 = new URLSpan[]{};
lbl10: // 2 sources:
        while (this.mMessageItem.getMx2Type() != 3) lbl-1000: // 2 sources:
        {
            if (this.mMessageItem.isGroup() && this.mMessageItem.isSms()) {
                var4_6 = this.mMessageItem.getDate();
                var6_7 = this.mMessageItem.getTimedValue();
                var8_8 = this.mMessageItem.getThreadId();
                if (var4_6 < 0) return;
                var10_1 = new Intent(this.mContext, (Class)MultipleRecipientsConversationDetailsActivity.class);
                var10_1.putExtra("timestamp", var4_6);
                var10_1.putExtra("timed_value", var6_7);
                var10_1.putExtra("thread_id", var8_8);
                this.mContext.startActivity((Intent)var10_1);
                return;
            }
            if (this.mFileShareLayout.getVisibility() == 0) {
                var10_1 = MessageUtils.getFileShareMessage(this.mMessageItem.getBody());
                if (var10_1 == null) return;
                if (var10_1.isEmpty() != false) return;
                var11_5 = Uri.parse((String)var10_1);
                if (var11_5 == null) return;
                var10_1 = new Intent("cn.kuaipan.mishare.View");
                var10_1.setData((Uri)var11_5);
                if (this.mMessageItem.isOutMessage()) {
                    var10_1.putExtra("MSG_TYPE", 2);
                } else {
                    var10_1.putExtra("MSG_TYPE", 1);
                }
                var10_1.putExtra("SENDER_NUM", this.mMessageItem.getAddress());
                var11_5 = this.mContext.getPackageManager().queryIntentActivities((Intent)var10_1, 65536);
                if (var11_5 == null) return;
                if (var11_5.isEmpty() != false) return;
                this.mContext.startActivity((Intent)var10_1);
                return;
            }
            var11_5 = this.mMessageItem.getBody();
            if (MiuiGalleryUtils.handleAsAlbumShareInvitation(this.mContext, null, (String)var11_5, (String)(var12_9 = this.mContext.getPackageName()), 8)) {
                Log.d((String)"MessageListItem", (String)"handled as album share invitation");
                return;
            }
            var14_10 = this.mMessageItem.getContactMessage();
            var13_11 = this.filterDatetimeUnderstandMessage(this.mUnderstandMessageList);
            if (var14_10 == null) {
                if (var10_1.length == 0 && var13_11.size() == 0) {
                    this.viewMmsMessageAttachment();
                    return;
                }
                if (var10_1.length + var13_11.size() == 1 && this.mMessageItem.isSms()) {
                    if (var10_1.length == 1) {
                        var10_1 = MessageUtils.getUriInfo(var10_1[0].getURL());
                        MessageUtils.performUriOperation(this.mContext, (MessageUtils.UriInfo)var10_1, this.mMessageItem);
                        return;
                    }
                    if (var13_11.size() != 1) return;
                    var10_1 = new MessageUtils.UriInfo();
                    var12_9 = this.mMessageItem;
                    var10_1.scheme = 4;
                    var13_11 = (UnderstandMessage.Item)((UnderstandMessage)var13_11.get((int)0)).mItems.get(0);
                    var14_10 = var13_11.mValue;
                    var10_1.content = var11_5.substring(var13_11.mStartPosition, var13_11.mEndPosition);
                    var10_1.formatContent = var14_10;
                    var10_1.title = var14_10;
                    MessageUtils.performUriOperation(this.mContext, (MessageUtils.UriInfo)var10_1, (MessageItem)var12_9);
                    return;
                }
            } else if (!(var14_10.count() != 1 || var10_1.length != 0 || var13_11.size() != 0 || this.mMessageItem.isMms() && this.mMessageItem.getMmsPreviewType() > 1)) {
                this.previewContactFromMessage((ContactMessage.ContactRecord)var14_10.getContactRecords().get(0));
                return;
            }
            var12_9 = new ContextMenuDialog(this.getContext());
            if (var14_10 != null) {
                for (Object var15_12 : var14_10.getContactRecords()) {
                    var12_9.addMenuItem(this.getContext().getString(2131362085, new Object[]{var15_12.getPreviewString()}), new Runnable((ContactMessage.ContactRecord)var15_12){
                        final /* synthetic */ ContactMessage.ContactRecord val$record;

                        @Override
                        public void run() {
                            MessageListItem.this.previewContactFromMessage(this.val$record);
                        }
                    });
                }
            }
            var14_10 = new HashSet();
            for (var1_2 = 0; var1_2 < var10_1.length; ++var1_2) {
                var16_13 = var10_1[var1_2].getURL();
                if (var14_10.contains(var16_13)) continue;
                var14_10.add(var16_13);
                var15_12 = this.mMessageItem;
                var16_13 = MessageUtils.getUriInfo((String)var16_13);
                var12_9.addMenuItem(var16_13.title, new Runnable((MessageUtils.UriInfo)var16_13, (MessageItem)var15_12){
                    final /* synthetic */ MessageUtils.UriInfo val$info;
                    final /* synthetic */ MessageItem val$mi;

                    @Override
                    public void run() {
                        MessageUtils.performUriOperation(MessageListItem.this.mContext, this.val$info, this.val$mi);
                    }
                });
            }
            if (var13_11.size() > 0) {
                var10_1 = new HashSet();
                var13_11 = var13_11.iterator();
                while (var13_11.hasNext()) {
                    var14_10 = (UnderstandMessage.Item)((UnderstandMessage)var13_11.next()).mItems.get(0);
                    var15_12 = var14_10.mValue;
                    if (var10_1.contains(var15_12)) continue;
                    var10_1.add(var15_12);
                    var12_9.addMenuItem((String)var15_12, new Runnable((String)var15_12, (String)var11_5, (UnderstandMessage.Item)var14_10, this.mMessageItem){
                        final /* synthetic */ String val$body;
                        final /* synthetic */ UnderstandMessage.Item val$item;
                        final /* synthetic */ MessageItem val$mi;
                        final /* synthetic */ String val$time;

                        @Override
                        public void run() {
                            MessageUtils.UriInfo uriInfo = new MessageUtils.UriInfo();
                            uriInfo.scheme = 4;
                            uriInfo.formatContent = this.val$time;
                            uriInfo.content = this.val$body.substring(this.val$item.mStartPosition, this.val$item.mEndPosition);
                            uriInfo.title = this.val$time;
                            MessageUtils.performUriOperation(MessageListItem.this.mContext, uriInfo, this.val$mi);
                        }
                    });
                }
            }
            if (this.mMessageItem.isMms() && this.mMessageItem.getSimplePduDoc().canShow()) {
                var12_9.addMenuItem(2131362075, new Runnable(){

                    @Override
                    public void run() {
                        MessageListItem.this.viewMmsMessageAttachment();
                    }
                });
            }
            var12_9.setTitle(2131362018);
            var12_9.show();
            return;
        }
        ** GOTO lbl111
lbl100: // 3 sources:
        for (var1_2 = 0; var1_2 < this.mBodyLinearLayout.getChildCount(); ++var1_2) {
            var11_5 = this.mBodyLinearLayout.getChildAt(var1_2);
            if (!(var11_5 instanceof TextView)) continue;
            var11_5 = ((TextView)var11_5).getUrls();
            var3_4 = var11_5.length;
            for (var2_3 = 0; var2_3 < var3_4; ++var2_3) {
                var10_1.add((Object)var11_5[var2_3]);
            }
        }
        var10_1 = (Intent)var10_1.toArray((Object[])new URLSpan[var10_1.size()]);
        ** GOTO lbl10
lbl111: // 1 sources:
        this.startPlayAudioItem(this.mMessageItem);
    }

    public void onMessageListItemDoubleClick() {
        if (this.mMessageItem.isSms() && this.mBodyTextView.getVisibility() == 0) {
            Intent intent = new Intent(this.mContext, (Class)MessageFullscreenActivity.class);
            intent.putExtra("body", this.mBodyTextView.getText().toString());
            this.mContext.startActivity(intent);
            ((Activity)this.mContext).overridePendingTransition(0, 2131034116);
        }
    }

    @Override
    public void onUpdate(final Contact contact) {
        if (this.mHandler == null) {
            return;
        }
        this.mHandler.post(new Runnable(){

            /*
             * Enabled aggressive block sorting
             */
            @Override
            public void run() {
                if (MessageListItem.this.mMessageItem == null || MessageListItem.this.mMessageItem.getContact() != contact) return;
                {
                    if (MessageListItem.this.mGroupSendViewController != null && MessageListItem.access$1000((MessageListItem)MessageListItem.this).mGroupSendToTitle.getVisibility() == 0) {
                        MessageListItem.access$1000((MessageListItem)MessageListItem.this).mGroupSendToTextView.setText((CharSequence)MessageListItem.this.mMessageItem.getContactName());
                    }
                    if (MessageListItem.this.mMessageItem.isListLayoutStyle()) {
                        if (MessageListItem.this.mSenderTextView == null || MessageListItem.this.mMessageItem.isGroup() || MessageListItem.this.mMessageItem.isReadOnly()) return;
                        {
                            MessageListItem.this.mSenderTextView.setText((CharSequence)MessageListItem.this.mMessageItem.getContactName());
                            return;
                        }
                    } else {
                        if (MessageListItem.this.mMessageItem.isBubbleLayoutStyle() || MessageListItem.this.mSenderTextView == null) return;
                        {
                            MessageListItem.this.mSenderTextView.setText((CharSequence)MessageListItem.this.mContext.getString(2131362102, new Object[]{MessageListItem.this.mMessageItem.getContactName()}));
                            return;
                        }
                    }
                }
            }
        });
    }

    public void rebind(MessageItem messageItem, boolean bl, boolean bl2, String string2, String string3) {
        this.rebind(messageItem, bl, bl2, string2, string3, false);
    }

    /*
     * Enabled aggressive block sorting
     */
    public void rebind(MessageItem messageItem, boolean bl, boolean bl2, String string2, String string3, boolean bl3) {
        this.mEditMode = bl;
        this.mMessageItem = messageItem;
        this.mHighlight = string2;
        this.mBodySubstitution = string3;
        this.mIsPrivate = bl3;
        this.hideMmsViewIfNeeded();
        this.bindBackground(messageItem);
        if (messageItem.isBookmarkLayoutStyle()) {
            this.bindBookmark(messageItem);
            return;
        } else {
            this.hideGroupViewIfNeeded();
            Contact.addListener(this);
            switch (messageItem.getMessageType()) {
                default: {
                    this.bindNonNotifInd(messageItem);
                    break;
                }
                case 130: {
                    this.bindNotifInd(messageItem);
                }
            }
            this.bindCommonIndicators(messageItem);
            this.bindCheckStatus(messageItem, bl, bl2);
            if (!messageItem.isBubbleLayoutStyle()) return;
            {
                this.bindUnderstandButton(messageItem);
                return;
            }
        }
    }

    public void setAudioItemCache(AudioItemCache audioItemCache) {
        this.mAudioItemCache = audioItemCache;
    }

    public void setBodyTextSize(float f2) {
        if (this.mBodyTextView != null && this.mBodyTextView.getVisibility() == 0) {
            this.mBodyTextView.setTextSize(0, f2);
        }
        if (this.mSubjectTextView != null && this.mSubjectTextView.getVisibility() == 0) {
            this.mSubjectTextView.setTextSize(0, f2);
        }
        int n = this.mBodyTextViewList.size();
        for (int i = 0; i < n; ++i) {
            TextView textView = this.mBodyTextViewList.get(i);
            if (textView == null || textView.getVisibility() != 0) continue;
            textView.setTextSize(0, f2);
        }
    }

    public void setMsgListItemHandler(Handler handler) {
        this.mHandler = handler;
    }

    public void setUnderstandMessageList(List<UnderstandMessage> list) {
        this.mUnderstandMessageList = list;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void showEditModeAnimation(boolean bl) {
        View view;
        boolean bl2 = this.mMessageItem.isBubbleOutLayoutStyle();
        if (this.mCheckBoxContainer == null) {
            view = this.findViewById(2131820773);
            view.setVisibility(0);
            this.mCheckBoxContainer = view;
            this.mCheckBox = (CheckBox)this.findViewById(16908289);
        }
        if (bl) {
            if (this.mCheckBoxContainer.getVisibility() != 8) return;
            {
                this.mCheckBoxContainer.setVisibility(0);
                view = new AlphaAnimation(0.0f, 1.0f);
                view.setDuration(150);
                this.mCheckBoxContainer.startAnimation((Animation)view);
                if (bl2) {
                    view = new TranslateAnimation((float)(this.mCheckBoxWidth + this.mBubbleCheckBoxDistance), 0.0f, 0.0f, 0.0f);
                    view.setDuration(150);
                    this.mBubble.startAnimation((Animation)view);
                }
                if (!this.isMxAudioAttachment(this.mMessageItem)) return;
                {
                    this.getAudioItemController().startEditMode();
                    return;
                }
            }
        } else {
            if (this.mCheckBoxContainer.getVisibility() != 0) return;
            {
                this.mCheckBoxContainer.setVisibility(8);
                view = new AlphaAnimation(1.0f, 0.0f);
                view.setDuration(150);
                this.mCheckBoxContainer.startAnimation((Animation)view);
                if (bl2) {
                    view = new TranslateAnimation((float)(- this.mCheckBoxWidth + this.mBubbleCheckBoxDistance), 0.0f, 0.0f, 0.0f);
                    view.setDuration(150);
                    this.mBubble.startAnimation((Animation)view);
                }
                if (!this.isMxAudioAttachment(this.mMessageItem)) return;
                {
                    this.getAudioItemController().endEditMode();
                    return;
                }
            }
        }
    }

    public void unbind() {
        if (this.mMessageItem.isMms()) {
            PreviewImageLoader.getInstance().cancel(this.mMessageItem.getMsgId(), this.mAttachmentPreview);
        }
        Contact.removeListener(this);
        if (this.mMessageItem.isBubbleLayoutStyle()) {
            this.getAudioItemController().unbind();
        }
        this.mMessageItem = null;
        this.mUnderstandMessageList = null;
    }

    public class GroupSendViewStubController {
        public TextView mGroupSendCountTextView;
        public TextView mGroupSendToTextView;
        public TextView mGroupSendToTitle;
        public LinearLayout mGroupSendViewContainer;

        public void refreshSendView(boolean bl) {
            if (bl) {
                int n = (int)MessageListItem.this.getResources().getDimension(2131427421);
                this.mGroupSendViewContainer.setPadding(0, 0, n, 0);
                this.mGroupSendToTitle.setTextColor(MessageListItem.this.getResources().getColorStateList(2131296351));
                this.mGroupSendToTextView.setTextColor(MessageListItem.this.getResources().getColorStateList(2131296351));
                this.mGroupSendCountTextView.setTextColor(MessageListItem.this.getResources().getColorStateList(2131296351));
                return;
            }
            this.mGroupSendViewContainer.setPadding(0, 0, 0, 0);
            this.mGroupSendToTitle.setTextColor(MessageListItem.this.getResources().getColorStateList(2131296360));
            this.mGroupSendToTextView.setTextColor(MessageListItem.this.getResources().getColorStateList(2131296359));
            this.mGroupSendCountTextView.setTextColor(MessageListItem.this.getResources().getColorStateList(2131296359));
        }
    }

    static enum Style {
        bubble,
        list;
        

        private Style() {
        }
    }

}

