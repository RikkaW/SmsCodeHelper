/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.MiuiConfiguration
 *  android.content.res.Resources
 *  android.database.Cursor
 *  android.graphics.Canvas
 *  android.graphics.Paint
 *  android.graphics.Paint$FontMetrics
 *  android.graphics.Paint$FontMetricsInt
 *  android.graphics.Rect
 *  android.graphics.drawable.Drawable
 *  android.net.Uri
 *  android.os.Handler
 *  android.provider.MiuiSettings
 *  android.provider.MiuiSettings$System
 *  android.text.SpannableStringBuilder
 *  android.text.TextPaint
 *  android.text.style.BackgroundColorSpan
 *  android.text.style.ForegroundColorSpan
 *  android.util.AttributeSet
 *  android.util.Log
 *  android.view.View
 *  android.view.View$MeasureSpec
 *  android.view.View$OnClickListener
 *  android.view.ViewGroup
 *  android.view.animation.AccelerateInterpolator
 *  android.view.animation.Interpolator
 *  android.widget.CheckBox
 *  android.widget.ImageView
 *  android.widget.QuickContactBadge
 *  android.widget.TextView
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Thread
 *  miui.os.Build
 *  miui.util.AttributeResolver
 */
package com.android.mms.ui;

import android.content.Context;
import android.content.res.MiuiConfiguration;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.provider.MiuiSettings;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.QuickContactBadge;
import android.widget.TextView;
import com.android.mms.data.Contact;
import com.android.mms.data.ContactList;
import com.android.mms.data.Conversation;
import com.android.mms.ui.MessageUtils;
import com.android.mms.ui.ViewAnimator;
import com.android.mms.util.AddressUtils;
import com.android.mms.util.EditableListView;
import com.android.mms.util.MSimUtils;
import miui.os.Build;
import miui.util.AttributeResolver;

public class ConversationListItem
extends ViewGroup
implements Contact.UpdateListener,
EditableListView.Draggable {
    private static Rect sPaddingOuter = new Rect();
    private static int sRowHeightLarge = 0;
    private View mAttachmentIndicator;
    private final int mAvatarSize;
    private QuickContactBadge mAvatarView;
    private CheckBox mCheckBox;
    private final int mCheckBoxWidth;
    private Conversation mConversation;
    private char[] mCountBuffer = new char[16];
    private TextView mDateView;
    private View mDraftIndicator;
    private int mDragBackground;
    private int mDragPosition = 0;
    private int mDragTextColor;
    private int mDragTextSize;
    private boolean mDragToReaded = false;
    private View mErrorIndicator;
    private View mFetionPrefix;
    private char[] mFromBuffer = new char[64];
    private TextView mFromView;
    private Handler mHandler = new Handler();
    private int mIndicatorMargin;
    private boolean mIsSpSentinel;
    private boolean mMarkSpThreadStarted;
    private TextView mMsgCountView;
    private boolean mNeedUpdateContact = false;
    private Runnable mOnUpdateRunnable;
    private final int mPaddingAvatar;
    private final int mPaddingInner;
    private int mPlaceHolderType = 0;
    private String mReadedString;
    private ViewAnimator mResetAnimator;
    private ImageView mRightArrowIndicator;
    private final int mRowHeight;
    private boolean mShowStickIndicator;
    private boolean mShowUnreadMsgIndicator;
    private ImageView mSimIndicator;
    private Drawable mStickIndicator;
    private final int mSubjectPadding;
    private TextView mSubjectView;
    private char[] mTagBuffer = new char[64];
    private TextView mTagView;
    private View mTimedIndicator;
    private TextView mUnreadMsgCountView;
    private Drawable mUnreadMsgIndicator;

    public ConversationListItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mOnUpdateRunnable = new Runnable(){

            @Override
            public void run() {
                if (ConversationListItem.this.mNeedUpdateContact) {
                    ConversationListItem.this.updateFromView();
                    ConversationListItem.this.updateAvatarVisibility();
                }
            }
        };
        this.mAvatarSize = context.getResources().getDimensionPixelSize(2131427331);
        this.mPaddingInner = context.getResources().getDimensionPixelSize(2131427371);
        this.mPaddingAvatar = context.getResources().getDimensionPixelSize(2131427372);
        this.mRowHeight = (int)AttributeResolver.resolveDimension((Context)context, (int)16842829);
        this.mCheckBoxWidth = context.getResources().getDimensionPixelSize(2131427360);
        this.mIndicatorMargin = context.getResources().getDimensionPixelSize(2131427386);
        this.mSubjectPadding = context.getResources().getDimensionPixelSize(2131427361);
        this.mUnreadMsgIndicator = context.getResources().getDrawable(2130838039);
        this.mStickIndicator = context.getResources().getDrawable(2130837597);
        this.mReadedString = context.getString(2131362068);
        this.mDragTextSize = context.getResources().getDimensionPixelSize(2131427376);
        this.mDragBackground = context.getResources().getColor(2131296293);
        this.mDragTextColor = context.getResources().getColor(2131296294);
        context = this.getBackground();
        if (context != null) {
            context.getPadding(sPaddingOuter);
        }
    }

    private boolean isBlockedPlaceHolder() {
        return Conversation.isBlockedPlaceHolder(this.mPlaceHolderType);
    }

    private boolean isHugeMode() {
        int n = MiuiConfiguration.getScaleMode();
        if (n == 11 || n == 15) {
            return true;
        }
        return false;
    }

    private boolean isMipubPlaceHolder() {
        return Conversation.isMipubPlaceHolder(this.mPlaceHolderType);
    }

    private boolean isPlaceHolder() {
        if (this.mPlaceHolderType > 0) {
            return true;
        }
        return false;
    }

    private boolean isReadedRegion(int n) {
        if ((float)n >= 0.25f * (float)this.getWidth()) {
            return true;
        }
        return false;
    }

    private int layoutIndicatorsLeftward(View view, int n, int n2, int n3) {
        int n4 = n3;
        if (view.getVisibility() != 8) {
            n = (n2 + n - view.getMeasuredHeight()) / 2;
            view.layout(n3 - view.getMeasuredWidth(), n, n3, view.getMeasuredHeight() + n);
            n4 = n3 - view.getMeasuredWidth() - this.mIndicatorMargin;
        }
        return n4;
    }

    private int layoutIndicatorsRightward(View view, int n, int n2, int n3) {
        int n4 = n3;
        if (view.getVisibility() != 8) {
            n = (n2 + n - view.getMeasuredHeight()) / 2;
            view.layout(n3, n, view.getMeasuredWidth() + n3, view.getMeasuredHeight() + n);
            n4 = n3 + view.getMeasuredWidth() + this.mIndicatorMargin;
        }
        return n4;
    }

    /*
     * Enabled aggressive block sorting
     */
    private void markAsRead() {
        if (this.mIsSpSentinel) {
            if (this.mMarkSpThreadStarted) return;
            {
                this.mMarkSpThreadStarted = true;
                new Thread(new Runnable(){

                    @Override
                    public void run() {
                        Conversation.markSPAsReadSync(ConversationListItem.this.mContext, 1);
                        ConversationListItem.this.mHandler.post(new Runnable(){

                            @Override
                            public void run() {
                                ConversationListItem.this.mMarkSpThreadStarted = false;
                            }
                        });
                    }

                }).start();
                return;
            }
        }
        if (this.isBlockedPlaceHolder()) {
            if (this.mConversation.getUnreadMessageCount() <= 0 || this.mConversation.getPreMarkUnread()) return;
            {
                this.mConversation.setPreMarkUnread(true);
                new Thread(new Runnable(){

                    @Override
                    public void run() {
                        Log.v((String)"Anting", (String)" markBlockAsReadSync   ");
                        ConversationListItem.this.mConversation;
                        Conversation.markBlockAsReadSync(ConversationListItem.this.mContext);
                        ConversationListItem.this.mHandler.post(new Runnable(){

                            @Override
                            public void run() {
                                ConversationListItem.this.mConversation.setPreMarkUnread(false);
                            }
                        });
                    }

                }).start();
                return;
            }
        }
        if (this.isMipubPlaceHolder()) {
            Conversation conversation = this.mConversation;
            Conversation.markMipubAsReadAsync(this.mContext);
            return;
        }
        if (this.mConversation.getUnreadMessageCount() <= 0 || this.mConversation.getPreMarkUnread()) {
            return;
        }
        this.mConversation.setPreMarkUnread(true);
        new Thread(new Runnable(){

            @Override
            public void run() {
                ConversationListItem.this.mConversation.markAsReadSync();
            }
        }).start();
        this.updatePreMarkUnReadView();
    }

    private void onDraggedDraw(Canvas canvas) {
        int n;
        int n2 = n = this.mDragPosition;
        if (this.mResetAnimator != null) {
            n2 = n;
            if (this.mResetAnimator.isRunning()) {
                n2 = this.mResetAnimator.getPosition();
            }
        }
        if (n2 > 0) {
            canvas.save();
            n = this.getWidth();
            int n3 = this.getHeight();
            canvas.clipRect(0, 0, n2, n3);
            Rect rect = new Rect(0, 0, (int)(0.5f * (float)n), n3);
            Paint paint = new Paint(1);
            paint.setTextSize((float)this.mDragTextSize);
            paint.setColor(this.mDragBackground);
            canvas.drawRect(rect, paint);
            paint.setColor(this.mDragTextColor);
            rect = paint.getFontMetrics();
            float f2 = rect.bottom;
            float f3 = rect.top;
            float f4 = n3;
            f2 = ((float)n3 - (f2 - f3)) / 2.0f;
            f3 = rect.bottom;
            canvas.drawText(this.mReadedString, (float)(n / 12), f4 - f2 - f3, paint);
            canvas.restore();
            canvas.translate((float)n2, 0.0f);
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private void onHugeLayout(boolean bl, int n, int n2, int n3, int n4) {
        int n5 = this.mFromView.getMeasuredHeight() + this.mSubjectPadding;
        int n6 = this.mSubjectView.getMeasuredHeight() + this.mSubjectPadding * 2;
        int n7 = 0;
        if (this.mDateView.getVisibility() == 0) {
            n7 = this.mDateView.getMeasuredHeight() + this.mSubjectPadding;
        }
        if (this.mAvatarView.getVisibility() == 8) {
            int n8;
            int n9 = n3 - ConversationListItem.sPaddingOuter.right;
            if (this.mCheckBox.getVisibility() != 8) {
                n8 = (n4 - n2 - this.mCheckBox.getMeasuredHeight()) / 2;
                n9 = n9 - this.mCheckBoxWidth + (this.mCheckBoxWidth - this.mCheckBox.getMeasuredWidth()) / 2;
                this.mCheckBox.layout(n9, n8, this.mCheckBox.getMeasuredWidth() + n9, this.mCheckBox.getMeasuredHeight() + n8);
            } else if (this.mRightArrowIndicator.getVisibility() != 8) {
                n8 = (n4 - n2 - this.mRightArrowIndicator.getMeasuredHeight()) / 2;
                int n10 = this.mRightArrowIndicator.getMeasuredWidth();
                this.mRightArrowIndicator.layout(n9 - n10, n8, n9, this.mRightArrowIndicator.getMeasuredHeight() + n8);
            }
            n9 = ConversationListItem.sPaddingOuter.left;
            n7 = (n4 - n2 - this.mFromView.getMeasuredHeight() - n6 - n7) / 2;
            n2 = n4 = n9;
            if (this.mFetionPrefix.getVisibility() != 8) {
                n2 = this.mFromView.getBaseline() + n7 - this.mFetionPrefix.getBaseline();
                this.mFetionPrefix.layout(n4, n2, this.mFetionPrefix.getMeasuredWidth() + n4, this.mFetionPrefix.getMeasuredHeight() + n2);
                n2 = n4 + this.mFetionPrefix.getMeasuredWidth();
            }
            if (this.mShowUnreadMsgIndicator) {
                n4 = this.mUnreadMsgIndicator.getIntrinsicWidth();
                n8 = this.mUnreadMsgIndicator.getIntrinsicHeight();
                n8 = n7 + (this.mFromView.getMeasuredHeight() - n8) / 2;
                this.mUnreadMsgIndicator.setBounds((ConversationListItem.sPaddingOuter.left - n4) / 2 + n, n8, (ConversationListItem.sPaddingOuter.right + n4) / 2 + n, this.mUnreadMsgIndicator.getIntrinsicHeight() + n8);
            }
            this.mFromView.layout(n2, n7, this.mFromView.getMeasuredWidth() + n2, this.mFromView.getMeasuredHeight() + n7);
            n = n2 + this.mFromView.getMeasuredWidth();
            if (this.mMsgCountView.getVisibility() != 8) {
                n2 = n7 + (this.mFromView.getMeasuredHeight() - this.mMsgCountView.getMeasuredHeight()) / 2;
                this.mMsgCountView.layout(n, n2, this.mMsgCountView.getMeasuredWidth() + n, this.mMsgCountView.getMeasuredHeight() + n2);
                this.mMsgCountView.getMeasuredWidth();
            }
            n = n7 + n5;
            this.mSubjectView.layout(n9, n, this.mSubjectView.getMeasuredWidth() + n9, this.mSubjectView.getMeasuredHeight() + n);
            n2 = (n += n6) + this.mDateView.getMeasuredHeight();
            if (this.mDateView.getVisibility() != 8) {
                this.mDateView.layout(n9, n, this.mDateView.getMeasuredWidth() + n9, n + n2);
            }
            n4 = this.mDateView.getMeasuredWidth();
            n7 = this.mIndicatorMargin;
            n4 = this.layoutIndicatorsRightward((View)this.mSimIndicator, n, n2, n9 + (n4 + n7));
            n4 = this.layoutIndicatorsRightward(this.mErrorIndicator, n, n2, n4);
            n4 = this.layoutIndicatorsRightward(this.mDraftIndicator, n, n2, n4);
            n4 = this.layoutIndicatorsRightward(this.mTimedIndicator, n, n2, n4);
            this.layoutIndicatorsRightward(this.mAttachmentIndicator, n, n2, n4);
        } else {
            n = ConversationListItem.sPaddingOuter.left;
            int n11 = (sRowHeightLarge - this.mAvatarView.getMeasuredHeight()) / 2;
            this.mAvatarView.layout(n, n11, this.mAvatarView.getMeasuredWidth() + n, this.mAvatarView.getMeasuredHeight() + n11);
            n11 = n + (this.mAvatarView.getMeasuredWidth() + this.mPaddingAvatar);
            n = n3 - ConversationListItem.sPaddingOuter.right;
            if (this.mCheckBox.getVisibility() != 8) {
                int n12 = (n4 - n2 - this.mCheckBox.getMeasuredHeight()) / 2;
                n = n - this.mCheckBoxWidth + (this.mCheckBoxWidth - this.mCheckBox.getMeasuredWidth()) / 2;
                this.mCheckBox.layout(n, n12, this.mCheckBox.getMeasuredWidth() + n, this.mCheckBox.getMeasuredHeight() + n12);
                n = this.mCheckBoxWidth;
            } else if (this.mRightArrowIndicator.getVisibility() != 8) {
                int n13 = (n4 - n2 - this.mRightArrowIndicator.getMeasuredHeight()) / 2;
                int n14 = this.mRightArrowIndicator.getMeasuredWidth();
                this.mRightArrowIndicator.layout(n - n14, n13, n, this.mRightArrowIndicator.getMeasuredHeight() + n13);
                this.mRightArrowIndicator.getMeasuredWidth();
                n = this.mIndicatorMargin;
            }
            n4 = (n4 - n2 - this.mFromView.getMeasuredHeight() - n6 - n7) / 2;
            n2 = n = n11;
            if (this.mFetionPrefix.getVisibility() != 8) {
                n2 = this.mFromView.getBaseline() + n4 - this.mFetionPrefix.getBaseline();
                this.mFetionPrefix.layout(n, n2, this.mFetionPrefix.getMeasuredWidth() + n, this.mFetionPrefix.getMeasuredHeight() + n2);
                n2 = n + this.mFetionPrefix.getMeasuredWidth();
            }
            this.mFromView.layout(n2, n4, this.mFromView.getMeasuredWidth() + n2, this.mFromView.getMeasuredHeight() + n4);
            n = n2 + this.mFromView.getMeasuredWidth();
            if (this.mMsgCountView.getVisibility() != 8) {
                n2 = n4 + (this.mFromView.getMeasuredHeight() - this.mMsgCountView.getMeasuredHeight()) / 2;
                this.mMsgCountView.layout(n, n2, this.mMsgCountView.getMeasuredWidth() + n, this.mMsgCountView.getMeasuredHeight() + n2);
            }
            if (this.mUnreadMsgCountView.getVisibility() != 8) {
                n2 = n4 + (this.mFromView.getMeasuredHeight() - this.mUnreadMsgCountView.getMeasuredHeight()) / 2;
                this.mUnreadMsgCountView.layout(n, n2, this.mUnreadMsgCountView.getMeasuredWidth() + n, this.mUnreadMsgCountView.getMeasuredHeight() + n2);
                this.mUnreadMsgCountView.getMeasuredWidth();
            }
            n = n4 + n5;
            this.mSubjectView.layout(n11, n, this.mSubjectView.getMeasuredWidth() + n11, this.mSubjectView.getMeasuredHeight() + n);
            n2 = (n += n6) + this.mDateView.getMeasuredHeight();
            if (this.mDateView.getVisibility() != 8) {
                this.mDateView.layout(n11, n, this.mDateView.getMeasuredWidth() + n11, n + n2);
            }
            n4 = this.mDateView.getMeasuredWidth();
            n7 = this.mIndicatorMargin;
            n4 = this.layoutIndicatorsRightward((View)this.mSimIndicator, n, n2, n11 + (n4 + n7));
            n4 = this.layoutIndicatorsRightward(this.mErrorIndicator, n, n2, n4);
            n4 = this.layoutIndicatorsRightward(this.mDraftIndicator, n, n2, n4);
            n4 = this.layoutIndicatorsRightward(this.mTimedIndicator, n, n2, n4);
            this.layoutIndicatorsRightward(this.mAttachmentIndicator, n, n2, n4);
        }
        if (this.mShowStickIndicator) {
            this.mStickIndicator.setBounds(n3 - this.mStickIndicator.getIntrinsicWidth(), 0, n3, this.mStickIndicator.getIntrinsicHeight());
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private void onHugeMeasure(int n, int n2) {
        int n3;
        int n4;
        int n5 = n4 = View.MeasureSpec.getSize((int)n) - ConversationListItem.sPaddingOuter.left - ConversationListItem.sPaddingOuter.right;
        if (this.mAvatarView.getVisibility() != 8) {
            n5 = View.MeasureSpec.makeMeasureSpec((int)this.mAvatarSize, (int)1073741824);
            this.mAvatarView.measure(n5, n5);
            n5 = n4 - (this.mAvatarView.getMeasuredWidth() + this.mPaddingAvatar);
        }
        if (this.mCheckBox.getVisibility() != 8) {
            this.mCheckBox.measure(0, 0);
            n4 = n5 - this.mCheckBoxWidth;
        } else {
            n4 = n5;
            if (this.mRightArrowIndicator.getVisibility() != 8) {
                this.mRightArrowIndicator.measure(0, 0);
                n4 = n5 - (this.mRightArrowIndicator.getMeasuredWidth() + this.mIndicatorMargin);
            }
        }
        if (this.mMsgCountView.getVisibility() != 8) {
            this.mMsgCountView.measure(0, 0);
            n3 = n4 - this.mMsgCountView.getMeasuredWidth();
        } else {
            n3 = n4;
        }
        n5 = n3;
        if (this.mUnreadMsgCountView.getVisibility() != 8) {
            this.mUnreadMsgCountView.measure(0, 0);
            n5 = n3 - this.mUnreadMsgCountView.getMeasuredWidth();
        }
        n3 = n5;
        if (this.mFetionPrefix.getVisibility() != 8) {
            this.mFetionPrefix.measure(0, 0);
            n3 = n5 - this.mFetionPrefix.getMeasuredWidth();
        }
        this.mFromView.measure(View.MeasureSpec.makeMeasureSpec((int)n3, (int)Integer.MIN_VALUE), 0);
        this.mDateView.measure(0, 0);
        n5 = n3 = n4 - (this.mDateView.getMeasuredWidth() + this.mPaddingInner);
        if (this.mAttachmentIndicator.getVisibility() != 8) {
            this.mAttachmentIndicator.measure(0, 0);
            n5 = n3 - (this.mAttachmentIndicator.getMeasuredWidth() + this.mIndicatorMargin);
        }
        n3 = n5;
        if (this.mTimedIndicator.getVisibility() != 8) {
            this.mTimedIndicator.measure(0, 0);
            n3 = n5 - (this.mTimedIndicator.getMeasuredWidth() + this.mIndicatorMargin);
        }
        n5 = n3;
        if (this.mDraftIndicator.getVisibility() != 8) {
            this.mDraftIndicator.measure(0, 0);
            n5 = n3 - (this.mDraftIndicator.getMeasuredWidth() + this.mIndicatorMargin);
        }
        n3 = n5;
        if (this.mErrorIndicator.getVisibility() != 8) {
            this.mErrorIndicator.measure(0, 0);
            n3 = n5 - (this.mErrorIndicator.getMeasuredWidth() + this.mIndicatorMargin);
        }
        if (this.mSimIndicator.getVisibility() != 8) {
            this.mSimIndicator.measure(0, 0);
            this.mSimIndicator.getMeasuredWidth();
            n5 = this.mIndicatorMargin;
        }
        if (this.mSubjectView.getVisibility() != 8) {
            this.mSubjectView.measure(View.MeasureSpec.makeMeasureSpec((int)n4, (int)Integer.MIN_VALUE), 0);
        }
        n = View.MeasureSpec.getSize((int)n);
        if (sRowHeightLarge == 0) {
            sRowHeightLarge = (int)AttributeResolver.resolveDimension((Context)this.mContext, (int)16843654);
        }
        this.setMeasuredDimension(n, ConversationListItem.getDefaultSize((int)sRowHeightLarge, (int)n2));
    }

    private void setSimIndicatorVisibility() {
        if (MSimUtils.isMSim() && MSimUtils.isMSimInserted() && !this.mIsSpSentinel) {
            switch (MSimUtils.getSlotIdBySimInfoId(this.mConversation.getLastSimId())) {
                default: {
                    this.mSimIndicator.setVisibility(8);
                    return;
                }
                case 0: {
                    this.mSimIndicator.setVisibility(0);
                    this.mSimIndicator.setImageResource(2130837970);
                    return;
                }
                case 1: 
            }
            this.mSimIndicator.setVisibility(0);
            this.mSimIndicator.setImageResource(2130837971);
            return;
        }
        this.mSimIndicator.setVisibility(8);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void updateAvatarView() {
        int n = this.mConversation.getRecipients().size();
        this.mAvatarView.setOnClickListener((View.OnClickListener)this.mAvatarView);
        if (this.mIsSpSentinel) {
            this.mAvatarView.assignContactUri(null);
            this.mAvatarView.setImageResource(2130838000);
            Contact.cancelRequest((ImageView)this.mAvatarView);
        } else if (this.isBlockedPlaceHolder()) {
            this.mAvatarView.assignContactUri(null);
            this.mAvatarView.setImageResource(2130837712);
            Contact.cancelRequest((ImageView)this.mAvatarView);
        } else if (this.isMipubPlaceHolder()) {
            this.mAvatarView.assignContactUri(null);
            this.mAvatarView.setImageResource(2130837758);
            Contact.cancelRequest((ImageView)this.mAvatarView);
        } else if (n == 0) {
            this.mAvatarView.assignContactUri(null);
            this.mAvatarView.setImageResource(285343796);
            Contact.cancelRequest((ImageView)this.mAvatarView);
        } else if (n == 1) {
            Contact contact = (Contact)this.mConversation.getRecipients().get(0);
            if (contact.existsInDatabase()) {
                this.mAvatarView.assignContactUri(contact.getUri());
                Contact.loadPhoto((ImageView)this.mAvatarView, contact);
            } else if (contact.isB2cNumber()) {
                this.mAvatarView.assignContactUri(null);
                Contact.loadPhoto((ImageView)this.mAvatarView, contact);
            } else if (contact.isYellowPageNumber()) {
                this.mAvatarView.assignContactUri(null);
                this.mAvatarView.assignContactFromPhone(contact.getNumber(), true);
                Contact.loadPhoto((ImageView)this.mAvatarView, contact);
            } else if (contact.isEmail()) {
                Contact.cancelRequest((ImageView)this.mAvatarView);
                this.mAvatarView.assignContactUri(null);
                this.mAvatarView.assignContactFromEmail(contact.getNumber(), true);
                this.mAvatarView.setImageResource(285343796);
            } else {
                Contact.cancelRequest((ImageView)this.mAvatarView);
                this.mAvatarView.assignContactUri(null);
                this.mAvatarView.assignContactFromPhone(contact.getNumber(), true);
                this.mAvatarView.setImageResource(285343796);
            }
        } else {
            this.mAvatarView.assignContactUri(null);
            Contact.cancelRequest((ImageView)this.mAvatarView);
            this.mAvatarView.setImageResource(285343792);
        }
        this.mAvatarView.setVisibility(0);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void updateAvatarVisibility() {
        boolean bl = !MiuiSettings.System.isSimpleMode((Context)this.mContext);
        if (bl) {
            this.mAvatarView.setVisibility(0);
            this.updateAvatarView();
            return;
        }
        this.mAvatarView.setVisibility(8);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void updateFromView() {
        if (this.mIsSpSentinel) {
            this.mFromView.setText(2131362267);
        } else if (this.isBlockedPlaceHolder()) {
            this.mFromView.setText(2131362370);
        } else if (this.isMipubPlaceHolder()) {
            this.mFromView.setText(2131362371);
        } else {
            int n = this.mConversation.getRecipients().formatNames(this.mFromBuffer, ", ");
            this.mFromView.setText(this.mFromBuffer, 0, n);
        }
        this.updateTagView();
    }

    private void updateMsgCount(char[] arrc, int n, int n2) {
        if (Build.IS_CM_CUSTOMIZATION_TEST && this.mMsgCountView != null) {
            this.mMsgCountView.setText(arrc, n, n2);
        }
    }

    private void updateMsgCountVisibility(int n) {
        if (Build.IS_CM_CUSTOMIZATION_TEST && this.mMsgCountView != null) {
            this.mMsgCountView.setVisibility(n);
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private void updateTagView() {
        int n = 0;
        if (this.isPlaceHolder() || this.mIsSpSentinel) {
            this.mTagView.setVisibility(8);
            return;
        }
        int n2 = this.mConversation.getRecipients().formatTags(this.mTagBuffer);
        this.mTagView.setText(this.mTagBuffer, 0, n2);
        TextView textView = this.mTagView;
        if (n2 == 0) {
            n = 8;
        }
        textView.setVisibility(n);
    }

    /*
     * Enabled aggressive block sorting
     */
    public final void bind(Context context, Conversation conversation, boolean bl, Cursor object, int n, int n2) {
        int n3;
        this.mConversation = conversation;
        this.mDragPosition = 0;
        if (bl && !this.isPlaceHolder() && !this.mIsSpSentinel) {
            this.mCheckBox.setVisibility(0);
            this.mCheckBox.setEnabled(true);
        } else {
            this.mCheckBox.setVisibility(8);
            this.mCheckBox.setEnabled(false);
        }
        if (conversation.isChecked()) {
            this.mCheckBox.setChecked(true);
        } else {
            this.mCheckBox.setChecked(false);
        }
        if (!this.isPlaceHolder() && !this.mIsSpSentinel || this.isHugeMode()) {
            this.mDateView.setVisibility(0);
            this.mDateView.setText((CharSequence)this.mConversation.getDateString());
        } else {
            this.mDateView.setVisibility(8);
        }
        if (Contact.getLoadAllState() != 0) {
            this.mConversation.loadRecipients(false, false);
        }
        if (this.isPlaceHolder() || this.mIsSpSentinel) {
            this.mFromView.setText(2131362267);
            this.mRightArrowIndicator.setVisibility(0);
        } else {
            n3 = this.mConversation.getRecipients().formatNames(this.mFromBuffer, ", ");
            this.mFromView.setText(this.mFromBuffer, 0, n3);
            this.mRightArrowIndicator.setVisibility(8);
        }
        this.updateFromView();
        object = this.mFetionPrefix;
        n3 = !this.isPlaceHolder() && conversation.getRecipients().size() == 1 && AddressUtils.isFetionNumber(((Contact)conversation.getRecipients().get(0)).getNumber()) ? 0 : 8;
        object.setVisibility(n3);
        Contact.addListener(this);
        this.mNeedUpdateContact = true;
        Object object2 = object = conversation.getSnippet();
        if (this.isBlockedPlaceHolder()) {
            object2 = conversation.getRecipients().formatNames(",") + ":" + (String)object;
        }
        bl = MiuiSettings.System.isSimpleMode((Context)this.mContext);
        if (this.mIsSpSentinel) {
            if (n > 0) {
                if (!bl) {
                    this.mFromView.setTextAppearance(this.mContext, 2131689495);
                    this.mUnreadMsgCountView.setVisibility(0);
                    this.mShowUnreadMsgIndicator = false;
                    this.mCountBuffer[0] = 40;
                    n = MessageUtils.appendIntToCharArray(this.mCountBuffer, 1, n);
                    this.mCountBuffer[n] = 41;
                    this.mUnreadMsgCountView.setText(this.mCountBuffer, 0, n + 1);
                } else {
                    this.mFromView.setTextAppearance(this.mContext, 2131689494);
                    this.mUnreadMsgCountView.setVisibility(8);
                    this.mShowUnreadMsgIndicator = true;
                }
            } else {
                this.mFromView.setTextAppearance(this.mContext, 2131689494);
                this.mUnreadMsgCountView.setVisibility(8);
                this.mShowUnreadMsgIndicator = false;
                this.mUnreadMsgCountView.setText(this.mCountBuffer, 0, 0);
            }
            this.updateMsgCountVisibility(8);
        } else if (this.isBlockedPlaceHolder()) {
            if (n2 > 0) {
                if (!bl) {
                    this.mFromView.setTextAppearance(this.mContext, 2131689495);
                    this.mUnreadMsgCountView.setVisibility(0);
                    this.mShowUnreadMsgIndicator = false;
                    this.mCountBuffer[0] = 40;
                    n = MessageUtils.appendIntToCharArray(this.mCountBuffer, 1, n2);
                    this.mCountBuffer[n] = 41;
                    this.mUnreadMsgCountView.setText(this.mCountBuffer, 0, n + 1);
                } else {
                    this.mFromView.setTextAppearance(this.mContext, 2131689494);
                    this.mUnreadMsgCountView.setVisibility(8);
                    this.mShowUnreadMsgIndicator = true;
                }
            } else {
                this.mFromView.setTextAppearance(this.mContext, 2131689494);
                this.mUnreadMsgCountView.setVisibility(8);
                this.mShowUnreadMsgIndicator = false;
                this.mUnreadMsgCountView.setText(this.mCountBuffer, 0, 0);
            }
        } else if (conversation.getUnreadMessageCount() > 0 && !conversation.getPreMarkUnread()) {
            if (!bl) {
                this.mFromView.setTextAppearance(this.mContext, 2131689495);
                this.mTagView.setTextAppearance(this.mContext, 2131689501);
                this.mUnreadMsgCountView.setVisibility(0);
                this.updateMsgCountVisibility(8);
                this.mShowUnreadMsgIndicator = false;
                this.mCountBuffer[0] = 40;
                n = MessageUtils.appendIntToCharArray(this.mCountBuffer, 1, conversation.getUnreadMessageCount());
                this.mCountBuffer[n] = 41;
                this.mUnreadMsgCountView.setText(this.mCountBuffer, 0, n + 1);
            } else {
                this.mFromView.setTextAppearance(this.mContext, 2131689494);
                this.mTagView.setTextAppearance(this.mContext, 2131689500);
                this.mUnreadMsgCountView.setVisibility(8);
                this.mShowUnreadMsgIndicator = true;
                n = n2 = 8;
                if (Build.IS_CM_CUSTOMIZATION_TEST) {
                    n = n2;
                    if (conversation.getMessageCount() > 1) {
                        this.mCountBuffer[0] = 40;
                        n = MessageUtils.appendIntToCharArray(this.mCountBuffer, 1, conversation.getMessageCount());
                        this.mCountBuffer[n] = 41;
                        this.updateMsgCount(this.mCountBuffer, 0, n + 1);
                        n = 0;
                    }
                }
                this.updateMsgCountVisibility(n);
            }
        } else if (Build.IS_CM_CUSTOMIZATION_TEST && conversation.getMessageCount() > 1) {
            this.mFromView.setTextAppearance(this.mContext, 2131689494);
            this.mTagView.setTextAppearance(this.mContext, 2131689500);
            this.mUnreadMsgCountView.setVisibility(8);
            this.mShowUnreadMsgIndicator = false;
            this.mCountBuffer[0] = 40;
            n = MessageUtils.appendIntToCharArray(this.mCountBuffer, 1, conversation.getMessageCount());
            this.mCountBuffer[n] = 41;
            this.updateMsgCount(this.mCountBuffer, 0, n + 1);
            this.updateMsgCountVisibility(0);
        } else {
            this.mFromView.setTextAppearance(this.mContext, 2131689494);
            this.mTagView.setTextAppearance(this.mContext, 2131689500);
            this.mUnreadMsgCountView.setVisibility(8);
            this.updateMsgCountVisibility(8);
            this.mShowUnreadMsgIndicator = false;
        }
        if (!conversation.hasDraft()) {
            this.mSubjectView.setText((CharSequence)object2);
        } else {
            Object object3 = null;
            for (n = 0; n < object2.length(); ++n) {
                char c2 = object2.charAt(n);
                if (c2 == '\uffff') {
                    object = object3;
                    if (object3 == null) {
                        object = new SpannableStringBuilder((CharSequence)object2.substring(0, n));
                    }
                    object3 = context.getString(2131362052);
                    n2 = object3.length();
                    object.append((CharSequence)object3);
                    n3 = object.length();
                    object3 = this.mContext.getResources();
                    object.setSpan((Object)new BackgroundColorSpan(object3.getColor(2131296290)), n3 - n2, n3, 33);
                    object.setSpan((Object)new ForegroundColorSpan(object3.getColor(2131296289)), n3 - n2, n3, 33);
                } else {
                    object = object3;
                    if (object3 != null) {
                        object3.append(c2);
                        object = object3;
                    }
                }
                object3 = object;
            }
            this.mSubjectView.setText((CharSequence)object2);
        }
        bl = conversation.hasError();
        context = this.mErrorIndicator;
        n = bl && !this.mIsSpSentinel ? 0 : 8;
        context.setVisibility(n);
        bl = Conversation.TimedThreads.hasTimedMessage(this.mConversation.getThreadId());
        context = this.mTimedIndicator;
        n = bl && !this.mIsSpSentinel ? 0 : 8;
        context.setVisibility(n);
        bl = conversation.hasAttachment();
        context = this.mAttachmentIndicator;
        n = bl && !this.mIsSpSentinel ? 0 : 8;
        context.setVisibility(n);
        bl = conversation.hasDraft();
        context = this.mDraftIndicator;
        n = bl && !this.mIsSpSentinel ? 0 : 8;
        context.setVisibility(n);
        bl = conversation.isSticky() && !this.mIsSpSentinel;
        this.mShowStickIndicator = bl;
        this.setSimIndicatorVisibility();
        this.updateAvatarVisibility();
    }

    public void onAnimationDone() {
        if (this.mDragToReaded) {
            this.markAsRead();
            this.mDragToReaded = false;
        }
    }

    public void onDraw(Canvas canvas) {
        this.onDraggedDraw(canvas);
        super.onDraw(canvas);
        if (this.mShowUnreadMsgIndicator) {
            this.mUnreadMsgIndicator.draw(canvas);
        }
        if (this.mShowStickIndicator) {
            this.mStickIndicator.draw(canvas);
        }
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mAvatarView = (QuickContactBadge)this.findViewById(2131820584);
        this.mFetionPrefix = this.findViewById(2131820585);
        this.mFromView = (TextView)this.findViewById(2131820586);
        this.mTagView = (TextView)this.findViewById(2131820587);
        this.mUnreadMsgCountView = (TextView)this.findViewById(2131820589);
        this.mMsgCountView = (TextView)this.findViewById(2131820588);
        this.mSubjectView = (TextView)this.findViewById(2131820596);
        this.mDateView = (TextView)this.findViewById(2131820590);
        this.mSimIndicator = (ImageView)this.findViewById(2131820595);
        this.mAttachmentIndicator = this.findViewById(2131820591);
        this.mTimedIndicator = this.findViewById(2131820592);
        this.mDraftIndicator = this.findViewById(2131820593);
        this.mErrorIndicator = this.findViewById(2131820594);
        this.mCheckBox = (CheckBox)this.findViewById(16908289);
        this.mRightArrowIndicator = (ImageView)this.findViewById(2131820597);
    }

    @Override
    public void onItemDragReleased(int n) {
        int n2 = (int)(0.5f * (float)this.getWidth());
        int n3 = n;
        if (n > n2) {
            n3 = n2;
        }
        this.mDragPosition = 0;
        this.mResetAnimator = new ViewAnimator((View)this);
        this.mResetAnimator.animate((Interpolator)new AccelerateInterpolator(1.0f), 250, n3, 0);
    }

    @Override
    public void onItemDragged(int n) {
        int n2 = n;
        if (n < 0) {
            n2 = 0;
        }
        int n3 = (int)(0.5f * (float)this.getWidth());
        n = n2;
        if (n2 > n3) {
            n = n3;
        }
        if (this.mDragPosition != n) {
            if (this.isReadedRegion(n)) {
                this.mDragToReaded = true;
            }
            this.mDragPosition = n;
            this.invalidate();
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void onLayout(boolean bl, int n, int n2, int n3, int n4) {
        if (this.isHugeMode()) {
            this.onHugeLayout(bl, n, n2, n3, n4);
            return;
        } else {
            int n5;
            int n6;
            int n7;
            if (this.mShowUnreadMsgIndicator) {
                n5 = this.mUnreadMsgIndicator.getIntrinsicHeight();
                n6 = this.mUnreadMsgIndicator.getIntrinsicWidth();
                n7 = this.mSubjectView.getMeasuredHeight();
                this.mUnreadMsgIndicator.setBounds((ConversationListItem.sPaddingOuter.left - n6) / 2 + n, (n4 - n2 - n5 - n7 - this.mSubjectPadding) / 2, (ConversationListItem.sPaddingOuter.right + n6) / 2 + n, (n4 - n2 + n5 - n7 - this.mSubjectPadding) / 2);
            }
            if (this.mAvatarView.getVisibility() == 8) {
                n5 = n3 - ConversationListItem.sPaddingOuter.right;
                if (this.mCheckBox.getVisibility() != 8) {
                    n = (n4 - n2 - this.mCheckBox.getMeasuredHeight()) / 2;
                    n6 = n5 - this.mCheckBoxWidth + (this.mCheckBoxWidth - this.mCheckBox.getMeasuredWidth()) / 2;
                    this.mCheckBox.layout(n6, n, this.mCheckBox.getMeasuredWidth() + n6, this.mCheckBox.getMeasuredHeight() + n);
                    n = n5 - this.mCheckBoxWidth;
                } else {
                    n = n5;
                    if (this.mRightArrowIndicator.getVisibility() != 8) {
                        n = (n4 - n2 - this.mRightArrowIndicator.getMeasuredHeight()) / 2;
                        n6 = this.mRightArrowIndicator.getMeasuredWidth();
                        this.mRightArrowIndicator.layout(n5 - n6, n, n5, this.mRightArrowIndicator.getMeasuredHeight() + n);
                        n = n5 - (this.mRightArrowIndicator.getMeasuredWidth() + this.mIndicatorMargin);
                    }
                }
                n5 = ConversationListItem.sPaddingOuter.left;
                n6 = (n4 - n2 - this.mFromView.getMeasuredHeight() - this.mSubjectView.getMeasuredHeight() - this.mSubjectPadding) / 2;
                n4 = n2 = n5;
                if (this.mFetionPrefix.getVisibility() != 8) {
                    n4 = this.mFromView.getBaseline() + n6 - this.mFetionPrefix.getBaseline();
                    this.mFetionPrefix.layout(n2, n4, this.mFetionPrefix.getMeasuredWidth() + n2, this.mFetionPrefix.getMeasuredHeight() + n4);
                    n4 = n2 + this.mFetionPrefix.getMeasuredWidth();
                }
                this.mFromView.layout(n4, n6, this.mFromView.getMeasuredWidth() + n4, this.mFromView.getMeasuredHeight() + n6);
                n2 = n4 += this.mFromView.getMeasuredWidth();
                if (this.mTagView.getVisibility() != 8) {
                    n2 = this.mFromView.getBaseline() + n6 - this.mTagView.getBaseline();
                    this.mTagView.layout(n4, n2, this.mTagView.getMeasuredWidth() + n4, this.mTagView.getMeasuredHeight() + n2);
                    n2 = n4 + this.mTagView.getMeasuredWidth();
                }
                n7 = n6 + this.mFromView.getBaseline() - this.mDateView.getBaseline();
                int n8 = n7 + this.mDateView.getMeasuredHeight();
                n4 = n2;
                if (this.mUnreadMsgCountView.getVisibility() != 8) {
                    n4 = n6 + (this.mFromView.getMeasuredHeight() - this.mUnreadMsgCountView.getMeasuredHeight()) / 2;
                    this.mUnreadMsgCountView.layout(n2, n4, this.mUnreadMsgCountView.getMeasuredWidth() + n2, this.mUnreadMsgCountView.getMeasuredHeight() + n4);
                    n4 = n2 + this.mUnreadMsgCountView.getMeasuredWidth();
                }
                if (this.mMsgCountView.getVisibility() != 8) {
                    n2 = n6 + (this.mFromView.getMeasuredHeight() - this.mMsgCountView.getMeasuredHeight()) / 2;
                    this.mMsgCountView.layout(n4, n2, this.mMsgCountView.getMeasuredWidth() + n4, this.mMsgCountView.getMeasuredHeight() + n2);
                    this.mMsgCountView.getMeasuredWidth();
                }
                n = this.layoutIndicatorsLeftward((View)this.mSimIndicator, n7, n8, n);
                this.mDateView.layout(n - this.mDateView.getMeasuredWidth(), n7, n, n8);
                n2 = this.mDateView.getMeasuredWidth();
                n4 = this.mIndicatorMargin;
                n = this.layoutIndicatorsLeftward(this.mErrorIndicator, n7, n8, n - (n2 + n4));
                n = this.layoutIndicatorsLeftward(this.mDraftIndicator, n7, n8, n);
                n = this.layoutIndicatorsLeftward(this.mTimedIndicator, n7, n8, n);
                this.layoutIndicatorsLeftward(this.mAttachmentIndicator, n7, n8, n);
                n = n6 + (this.mFromView.getHeight() + this.mSubjectPadding);
                if (this.mSubjectView.getVisibility() != 8) {
                    this.mSubjectView.layout(n5, n, this.mSubjectView.getMeasuredWidth() + n5, this.mSubjectView.getMeasuredHeight() + n);
                }
            } else {
                n = ConversationListItem.sPaddingOuter.left;
                n5 = (this.mRowHeight - this.mAvatarView.getMeasuredHeight()) / 2;
                this.mAvatarView.layout(n, n5, this.mAvatarView.getMeasuredWidth() + n, this.mAvatarView.getMeasuredHeight() + n5);
                n5 = n + (this.mAvatarView.getMeasuredWidth() + this.mPaddingAvatar);
                n6 = n3 - ConversationListItem.sPaddingOuter.right;
                if (this.mCheckBox.getVisibility() != 8) {
                    n = (n4 - n2 - this.mCheckBox.getMeasuredHeight()) / 2;
                    n7 = n6 - this.mCheckBoxWidth + (this.mCheckBoxWidth - this.mCheckBox.getMeasuredWidth()) / 2;
                    this.mCheckBox.layout(n7, n, this.mCheckBox.getMeasuredWidth() + n7, this.mCheckBox.getMeasuredHeight() + n);
                    n = n6 - this.mCheckBoxWidth;
                } else {
                    n = n6;
                    if (this.mRightArrowIndicator.getVisibility() != 8) {
                        n = (n4 - n2 - this.mRightArrowIndicator.getMeasuredHeight()) / 2;
                        n7 = this.mRightArrowIndicator.getMeasuredWidth();
                        this.mRightArrowIndicator.layout(n6 - n7, n, n6, this.mRightArrowIndicator.getMeasuredHeight() + n);
                        n = n6 - (this.mRightArrowIndicator.getMeasuredWidth() + this.mIndicatorMargin);
                    }
                }
                n6 = (n4 - n2 - this.mFromView.getMeasuredHeight() - this.mSubjectView.getMeasuredHeight() - this.mSubjectPadding) / 2;
                n4 = n2 = n5;
                if (this.mFetionPrefix.getVisibility() != 8) {
                    n4 = this.mFromView.getBaseline() + n6 - this.mFetionPrefix.getBaseline();
                    this.mFetionPrefix.layout(n2, n4, this.mFetionPrefix.getMeasuredWidth() + n2, this.mFetionPrefix.getMeasuredHeight() + n4);
                    n4 = n2 + this.mFetionPrefix.getMeasuredWidth();
                }
                this.mFromView.layout(n4, n6, this.mFromView.getMeasuredWidth() + n4, this.mFromView.getMeasuredHeight() + n6);
                n2 = n4 += this.mFromView.getMeasuredWidth();
                if (this.mTagView.getVisibility() != 8) {
                    n2 = this.mFromView.getBaseline() + n6 - this.mTagView.getBaseline();
                    this.mTagView.layout(n4, n2, this.mTagView.getMeasuredWidth() + n4, this.mTagView.getMeasuredHeight() + n2);
                    n2 = n4 + this.mTagView.getMeasuredWidth();
                }
                this.mFromView.getBaseline();
                Paint.FontMetricsInt fontMetricsInt = this.mFromView.getPaint().getFontMetricsInt();
                n4 = fontMetricsInt.ascent;
                n4 = fontMetricsInt.descent;
                n4 = n2;
                if (this.mUnreadMsgCountView.getVisibility() != 8) {
                    n4 = n6 + (this.mFromView.getMeasuredHeight() - this.mUnreadMsgCountView.getMeasuredHeight()) / 2;
                    this.mUnreadMsgCountView.layout(n2, n4, this.mUnreadMsgCountView.getMeasuredWidth() + n2, this.mUnreadMsgCountView.getMeasuredHeight() + n4);
                    n4 = n2 + this.mUnreadMsgCountView.getMeasuredWidth();
                }
                if (this.mMsgCountView.getVisibility() != 8) {
                    n2 = n6 + (this.mFromView.getMeasuredHeight() - this.mMsgCountView.getMeasuredHeight()) / 2;
                    this.mMsgCountView.layout(n4, n2, this.mMsgCountView.getMeasuredWidth() + n4, this.mMsgCountView.getMeasuredHeight() + n2);
                    this.mMsgCountView.getMeasuredWidth();
                }
                n2 = n6 + this.mFromView.getBaseline() - this.mDateView.getBaseline();
                n4 = n2 + this.mDateView.getMeasuredHeight();
                n = this.layoutIndicatorsLeftward((View)this.mSimIndicator, n2, n4, n);
                this.mDateView.layout(n - this.mDateView.getMeasuredWidth(), n2, n, n4);
                n7 = this.mDateView.getMeasuredWidth();
                int n9 = this.mIndicatorMargin;
                n = this.layoutIndicatorsLeftward(this.mErrorIndicator, n2, n4, n - (n7 + n9));
                n = this.layoutIndicatorsLeftward(this.mDraftIndicator, n2, n4, n);
                n = this.layoutIndicatorsLeftward(this.mTimedIndicator, n2, n4, n);
                this.layoutIndicatorsLeftward(this.mAttachmentIndicator, n2, n4, n);
                n = n6 + (this.mFromView.getHeight() + this.mSubjectPadding);
                if (this.mSubjectView.getVisibility() != 8) {
                    this.mSubjectView.layout(n5, n, this.mSubjectView.getMeasuredWidth() + n5, this.mSubjectView.getMeasuredHeight() + n);
                }
            }
            if (!this.mShowStickIndicator) return;
            {
                this.mStickIndicator.setBounds(n3 - this.mStickIndicator.getIntrinsicWidth(), 0, n3, this.mStickIndicator.getIntrinsicHeight());
                return;
            }
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void onMeasure(int n, int n2) {
        int n3;
        int n4;
        if (this.isHugeMode()) {
            this.onHugeMeasure(n, n2);
            return;
        }
        int n5 = n4 = View.MeasureSpec.getSize((int)n) - ConversationListItem.sPaddingOuter.left - ConversationListItem.sPaddingOuter.right;
        if (this.mAvatarView.getVisibility() != 8) {
            n5 = View.MeasureSpec.makeMeasureSpec((int)this.mAvatarSize, (int)1073741824);
            this.mAvatarView.measure(n5, n5);
            n5 = n4 - (this.mAvatarView.getMeasuredWidth() + this.mPaddingAvatar);
        }
        if (this.mCheckBox.getVisibility() != 8) {
            this.mCheckBox.measure(0, 0);
            n4 = n5 - this.mCheckBoxWidth;
        } else {
            n4 = n5;
            if (this.mRightArrowIndicator.getVisibility() != 8) {
                this.mRightArrowIndicator.measure(0, 0);
                n4 = n5 - (this.mRightArrowIndicator.getMeasuredWidth() + this.mIndicatorMargin);
            }
        }
        if (this.mMsgCountView.getVisibility() != 8) {
            this.mMsgCountView.measure(0, 0);
            n3 = n4 - this.mMsgCountView.getMeasuredWidth();
        } else {
            n3 = n4;
        }
        n5 = n3;
        if (this.mUnreadMsgCountView.getVisibility() != 8) {
            this.mUnreadMsgCountView.measure(0, 0);
            n5 = n3 - this.mUnreadMsgCountView.getMeasuredWidth();
        }
        n3 = n5;
        if (this.mFetionPrefix.getVisibility() != 8) {
            this.mFetionPrefix.measure(0, 0);
            n3 = n5 - this.mFetionPrefix.getMeasuredWidth();
        }
        this.mDateView.measure(0, 0);
        n5 = n3 -= this.mDateView.getMeasuredWidth() + this.mPaddingInner;
        if (this.mAttachmentIndicator.getVisibility() != 8) {
            this.mAttachmentIndicator.measure(0, 0);
            n5 = n3 - (this.mAttachmentIndicator.getMeasuredWidth() + this.mIndicatorMargin);
        }
        n3 = n5;
        if (this.mTimedIndicator.getVisibility() != 8) {
            this.mTimedIndicator.measure(0, 0);
            n3 = n5 - (this.mTimedIndicator.getMeasuredWidth() + this.mIndicatorMargin);
        }
        n5 = n3;
        if (this.mDraftIndicator.getVisibility() != 8) {
            this.mDraftIndicator.measure(0, 0);
            n5 = n3 - (this.mDraftIndicator.getMeasuredWidth() + this.mIndicatorMargin);
        }
        n3 = n5;
        if (this.mErrorIndicator.getVisibility() != 8) {
            this.mErrorIndicator.measure(0, 0);
            n3 = n5 - (this.mErrorIndicator.getMeasuredWidth() + this.mIndicatorMargin);
        }
        n5 = n3;
        if (this.mTagView.getVisibility() != 8) {
            this.mTagView.measure(View.MeasureSpec.makeMeasureSpec((int)(n3 / 2), (int)Integer.MIN_VALUE), 0);
            n5 = n3 - this.mTagView.getMeasuredWidth();
        }
        n3 = n5;
        if (this.mSimIndicator.getVisibility() != 8) {
            this.mSimIndicator.measure(0, 0);
            n3 = n5 - (this.mSimIndicator.getMeasuredWidth() + this.mIndicatorMargin);
        }
        this.mFromView.measure(View.MeasureSpec.makeMeasureSpec((int)n3, (int)Integer.MIN_VALUE), 0);
        if (this.mSubjectView.getVisibility() != 8) {
            this.mSubjectView.measure(View.MeasureSpec.makeMeasureSpec((int)n4, (int)Integer.MIN_VALUE), 0);
        }
        this.setMeasuredDimension(View.MeasureSpec.getSize((int)n), ConversationListItem.getDefaultSize((int)this.mRowHeight, (int)n2));
    }

    @Override
    public void onUpdate(Contact contact) {
        if (this.mHandler != null) {
            this.mHandler.removeCallbacks(this.mOnUpdateRunnable);
            this.mHandler.postDelayed(this.mOnUpdateRunnable, 500);
        }
    }

    public void setIsSpSentinel(boolean bl) {
        this.mIsSpSentinel = bl;
    }

    public void setPlaceHolderType(int n) {
        this.mPlaceHolderType = n;
    }

    public final void unbind() {
        this.mDragPosition = 0;
        Contact.cancelRequest((ImageView)this.mAvatarView);
        Contact.removeListener(this);
        this.mNeedUpdateContact = false;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public boolean updatePreMarkUnReadView() {
        boolean bl;
        boolean bl2 = bl = false;
        if (this.mConversation == null) return bl2;
        bl2 = bl;
        if (!this.mConversation.getPreMarkUnread()) return bl2;
        if (this.mUnreadMsgCountView.getVisibility() != 0) {
            bl2 = bl;
            if (!this.mShowUnreadMsgIndicator) return bl2;
        }
        this.mFromView.setTextAppearance(this.mContext, 2131689494);
        this.mTagView.setTextAppearance(this.mContext, 2131689500);
        this.mUnreadMsgCountView.setVisibility(8);
        this.updateMsgCountVisibility(0);
        this.mShowUnreadMsgIndicator = false;
        return true;
    }

}

