package com.android.mms.ui;

import android.content.Context;
import android.content.res.MiuiConfiguration;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.graphics.Paint.FontMetricsInt;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.provider.MiuiSettings.System;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.QuickContactBadge;
import android.widget.TextView;
import com.android.mms.data.Contact;
import com.android.mms.data.Contact.UpdateListener;
import com.android.mms.data.ContactList;
import com.android.mms.data.Conversation;
import com.android.mms.data.Conversation.TimedThreads;
import com.android.mms.util.AddressUtils;
import com.android.mms.util.EditableListView.Draggable;
import com.android.mms.util.MSimUtils;
import miui.os.Build;
import miui.util.AttributeResolver;

public class ConversationListItem
  extends ViewGroup
  implements Contact.UpdateListener, EditableListView.Draggable
{
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
  private Runnable mOnUpdateRunnable = new Runnable()
  {
    public void run()
    {
      if (mNeedUpdateContact)
      {
        ConversationListItem.this.updateFromView();
        ConversationListItem.this.updateAvatarVisibility();
      }
    }
  };
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
  
  public ConversationListItem(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    mAvatarSize = paramContext.getResources().getDimensionPixelSize(2131427331);
    mPaddingInner = paramContext.getResources().getDimensionPixelSize(2131427371);
    mPaddingAvatar = paramContext.getResources().getDimensionPixelSize(2131427372);
    mRowHeight = ((int)AttributeResolver.resolveDimension(paramContext, 16842829));
    mCheckBoxWidth = paramContext.getResources().getDimensionPixelSize(2131427360);
    mIndicatorMargin = paramContext.getResources().getDimensionPixelSize(2131427386);
    mSubjectPadding = paramContext.getResources().getDimensionPixelSize(2131427361);
    mUnreadMsgIndicator = paramContext.getResources().getDrawable(2130838039);
    mStickIndicator = paramContext.getResources().getDrawable(2130837597);
    mReadedString = paramContext.getString(2131362068);
    mDragTextSize = paramContext.getResources().getDimensionPixelSize(2131427376);
    mDragBackground = paramContext.getResources().getColor(2131296293);
    mDragTextColor = paramContext.getResources().getColor(2131296294);
    paramContext = getBackground();
    if (paramContext != null) {
      paramContext.getPadding(sPaddingOuter);
    }
  }
  
  private boolean isBlockedPlaceHolder()
  {
    return Conversation.isBlockedPlaceHolder(mPlaceHolderType);
  }
  
  private boolean isHugeMode()
  {
    int i = MiuiConfiguration.getScaleMode();
    return (i == 11) || (i == 15);
  }
  
  private boolean isMipubPlaceHolder()
  {
    return Conversation.isMipubPlaceHolder(mPlaceHolderType);
  }
  
  private boolean isPlaceHolder()
  {
    return mPlaceHolderType > 0;
  }
  
  private boolean isReadedRegion(int paramInt)
  {
    return paramInt >= 0.25F * getWidth();
  }
  
  private int layoutIndicatorsLeftward(View paramView, int paramInt1, int paramInt2, int paramInt3)
  {
    int i = paramInt3;
    if (paramView.getVisibility() != 8)
    {
      paramInt1 = (paramInt2 + paramInt1 - paramView.getMeasuredHeight()) / 2;
      paramView.layout(paramInt3 - paramView.getMeasuredWidth(), paramInt1, paramInt3, paramView.getMeasuredHeight() + paramInt1);
      i = paramInt3 - paramView.getMeasuredWidth() - mIndicatorMargin;
    }
    return i;
  }
  
  private int layoutIndicatorsRightward(View paramView, int paramInt1, int paramInt2, int paramInt3)
  {
    int i = paramInt3;
    if (paramView.getVisibility() != 8)
    {
      paramInt1 = (paramInt2 + paramInt1 - paramView.getMeasuredHeight()) / 2;
      paramView.layout(paramInt3, paramInt1, paramView.getMeasuredWidth() + paramInt3, paramView.getMeasuredHeight() + paramInt1);
      i = paramInt3 + paramView.getMeasuredWidth() + mIndicatorMargin;
    }
    return i;
  }
  
  private void markAsRead()
  {
    if (mIsSpSentinel) {
      if (!mMarkSpThreadStarted) {}
    }
    do
    {
      do
      {
        return;
        mMarkSpThreadStarted = true;
        new Thread(new Runnable()
        {
          public void run()
          {
            Conversation.markSPAsReadSync(mContext, 1);
            mHandler.post(new Runnable()
            {
              public void run()
              {
                ConversationListItem.access$102(ConversationListItem.this, false);
              }
            });
          }
        }).start();
        return;
        if (!isBlockedPlaceHolder()) {
          break;
        }
      } while ((mConversation.getUnreadMessageCount() <= 0) || (mConversation.getPreMarkUnread()));
      mConversation.setPreMarkUnread(true);
      new Thread(new Runnable()
      {
        public void run()
        {
          Log.v("Anting", " markBlockAsReadSync   ");
          Conversation.markBlockAsReadSync(mContext);
          mHandler.post(new Runnable()
          {
            public void run()
            {
              mConversation.setPreMarkUnread(false);
            }
          });
        }
      }).start();
      return;
      if (isMipubPlaceHolder())
      {
        Conversation localConversation = mConversation;
        Conversation.markMipubAsReadAsync(mContext);
        return;
      }
    } while ((mConversation.getUnreadMessageCount() <= 0) || (mConversation.getPreMarkUnread()));
    mConversation.setPreMarkUnread(true);
    new Thread(new Runnable()
    {
      public void run()
      {
        mConversation.markAsReadSync();
      }
    }).start();
    updatePreMarkUnReadView();
  }
  
  private void onDraggedDraw(Canvas paramCanvas)
  {
    int j = mDragPosition;
    int i = j;
    if (mResetAnimator != null)
    {
      i = j;
      if (mResetAnimator.isRunning()) {
        i = mResetAnimator.getPosition();
      }
    }
    if (i > 0)
    {
      paramCanvas.save();
      j = getWidth();
      int k = getHeight();
      paramCanvas.clipRect(0, 0, i, k);
      Object localObject = new Rect(0, 0, (int)(0.5F * j), k);
      Paint localPaint = new Paint(1);
      localPaint.setTextSize(mDragTextSize);
      localPaint.setColor(mDragBackground);
      paramCanvas.drawRect((Rect)localObject, localPaint);
      localPaint.setColor(mDragTextColor);
      localObject = localPaint.getFontMetrics();
      float f2 = bottom;
      float f3 = top;
      float f1 = k;
      f2 = (k - (f2 - f3)) / 2.0F;
      f3 = bottom;
      paramCanvas.drawText(mReadedString, j / 12, f1 - f2 - f3, localPaint);
      paramCanvas.restore();
      paramCanvas.translate(i, 0.0F);
    }
  }
  
  private void onHugeLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    int m = mFromView.getMeasuredHeight() + mSubjectPadding;
    int k = mSubjectView.getMeasuredHeight() + mSubjectPadding * 2;
    int i = 0;
    if (mDateView.getVisibility() == 0) {
      i = mDateView.getMeasuredHeight() + mSubjectPadding;
    }
    int n;
    int i1;
    if (mAvatarView.getVisibility() == 8)
    {
      j = paramInt3 - sPaddingOuterright;
      if (mCheckBox.getVisibility() != 8)
      {
        n = (paramInt4 - paramInt2 - mCheckBox.getMeasuredHeight()) / 2;
        j = j - mCheckBoxWidth + (mCheckBoxWidth - mCheckBox.getMeasuredWidth()) / 2;
        mCheckBox.layout(j, n, mCheckBox.getMeasuredWidth() + j, mCheckBox.getMeasuredHeight() + n);
      }
      for (;;)
      {
        j = sPaddingOuterleft;
        i = (paramInt4 - paramInt2 - mFromView.getMeasuredHeight() - k - i) / 2;
        paramInt4 = j;
        paramInt2 = paramInt4;
        if (mFetionPrefix.getVisibility() != 8)
        {
          paramInt2 = mFromView.getBaseline() + i - mFetionPrefix.getBaseline();
          mFetionPrefix.layout(paramInt4, paramInt2, mFetionPrefix.getMeasuredWidth() + paramInt4, mFetionPrefix.getMeasuredHeight() + paramInt2);
          paramInt2 = paramInt4 + mFetionPrefix.getMeasuredWidth();
        }
        if (mShowUnreadMsgIndicator)
        {
          paramInt4 = mUnreadMsgIndicator.getIntrinsicWidth();
          n = mUnreadMsgIndicator.getIntrinsicHeight();
          n = i + (mFromView.getMeasuredHeight() - n) / 2;
          mUnreadMsgIndicator.setBounds((sPaddingOuterleft - paramInt4) / 2 + paramInt1, n, (sPaddingOuterright + paramInt4) / 2 + paramInt1, mUnreadMsgIndicator.getIntrinsicHeight() + n);
        }
        mFromView.layout(paramInt2, i, mFromView.getMeasuredWidth() + paramInt2, mFromView.getMeasuredHeight() + i);
        paramInt1 = paramInt2 + mFromView.getMeasuredWidth();
        if (mMsgCountView.getVisibility() != 8)
        {
          paramInt2 = i + (mFromView.getMeasuredHeight() - mMsgCountView.getMeasuredHeight()) / 2;
          mMsgCountView.layout(paramInt1, paramInt2, mMsgCountView.getMeasuredWidth() + paramInt1, mMsgCountView.getMeasuredHeight() + paramInt2);
          mMsgCountView.getMeasuredWidth();
        }
        paramInt1 = i + m;
        mSubjectView.layout(j, paramInt1, mSubjectView.getMeasuredWidth() + j, mSubjectView.getMeasuredHeight() + paramInt1);
        paramInt1 += k;
        paramInt2 = paramInt1 + mDateView.getMeasuredHeight();
        if (mDateView.getVisibility() != 8) {
          mDateView.layout(j, paramInt1, mDateView.getMeasuredWidth() + j, paramInt1 + paramInt2);
        }
        paramInt4 = mDateView.getMeasuredWidth();
        i = mIndicatorMargin;
        paramInt4 = layoutIndicatorsRightward(mSimIndicator, paramInt1, paramInt2, j + (paramInt4 + i));
        paramInt4 = layoutIndicatorsRightward(mErrorIndicator, paramInt1, paramInt2, paramInt4);
        paramInt4 = layoutIndicatorsRightward(mDraftIndicator, paramInt1, paramInt2, paramInt4);
        paramInt4 = layoutIndicatorsRightward(mTimedIndicator, paramInt1, paramInt2, paramInt4);
        layoutIndicatorsRightward(mAttachmentIndicator, paramInt1, paramInt2, paramInt4);
        if (mShowStickIndicator) {
          mStickIndicator.setBounds(paramInt3 - mStickIndicator.getIntrinsicWidth(), 0, paramInt3, mStickIndicator.getIntrinsicHeight());
        }
        return;
        if (mRightArrowIndicator.getVisibility() != 8)
        {
          n = (paramInt4 - paramInt2 - mRightArrowIndicator.getMeasuredHeight()) / 2;
          i1 = mRightArrowIndicator.getMeasuredWidth();
          mRightArrowIndicator.layout(j - i1, n, j, mRightArrowIndicator.getMeasuredHeight() + n);
        }
      }
    }
    paramInt1 = sPaddingOuterleft;
    int j = (sRowHeightLarge - mAvatarView.getMeasuredHeight()) / 2;
    mAvatarView.layout(paramInt1, j, mAvatarView.getMeasuredWidth() + paramInt1, mAvatarView.getMeasuredHeight() + j);
    j = paramInt1 + (mAvatarView.getMeasuredWidth() + mPaddingAvatar);
    paramInt1 = paramInt3 - sPaddingOuterright;
    if (mCheckBox.getVisibility() != 8)
    {
      n = (paramInt4 - paramInt2 - mCheckBox.getMeasuredHeight()) / 2;
      paramInt1 = paramInt1 - mCheckBoxWidth + (mCheckBoxWidth - mCheckBox.getMeasuredWidth()) / 2;
      mCheckBox.layout(paramInt1, n, mCheckBox.getMeasuredWidth() + paramInt1, mCheckBox.getMeasuredHeight() + n);
      paramInt1 = mCheckBoxWidth;
    }
    for (;;)
    {
      paramInt4 = (paramInt4 - paramInt2 - mFromView.getMeasuredHeight() - k - i) / 2;
      paramInt1 = j;
      paramInt2 = paramInt1;
      if (mFetionPrefix.getVisibility() != 8)
      {
        paramInt2 = mFromView.getBaseline() + paramInt4 - mFetionPrefix.getBaseline();
        mFetionPrefix.layout(paramInt1, paramInt2, mFetionPrefix.getMeasuredWidth() + paramInt1, mFetionPrefix.getMeasuredHeight() + paramInt2);
        paramInt2 = paramInt1 + mFetionPrefix.getMeasuredWidth();
      }
      mFromView.layout(paramInt2, paramInt4, mFromView.getMeasuredWidth() + paramInt2, mFromView.getMeasuredHeight() + paramInt4);
      paramInt1 = paramInt2 + mFromView.getMeasuredWidth();
      if (mMsgCountView.getVisibility() != 8)
      {
        paramInt2 = paramInt4 + (mFromView.getMeasuredHeight() - mMsgCountView.getMeasuredHeight()) / 2;
        mMsgCountView.layout(paramInt1, paramInt2, mMsgCountView.getMeasuredWidth() + paramInt1, mMsgCountView.getMeasuredHeight() + paramInt2);
      }
      if (mUnreadMsgCountView.getVisibility() != 8)
      {
        paramInt2 = paramInt4 + (mFromView.getMeasuredHeight() - mUnreadMsgCountView.getMeasuredHeight()) / 2;
        mUnreadMsgCountView.layout(paramInt1, paramInt2, mUnreadMsgCountView.getMeasuredWidth() + paramInt1, mUnreadMsgCountView.getMeasuredHeight() + paramInt2);
        mUnreadMsgCountView.getMeasuredWidth();
      }
      paramInt1 = paramInt4 + m;
      mSubjectView.layout(j, paramInt1, mSubjectView.getMeasuredWidth() + j, mSubjectView.getMeasuredHeight() + paramInt1);
      paramInt1 += k;
      paramInt2 = paramInt1 + mDateView.getMeasuredHeight();
      if (mDateView.getVisibility() != 8) {
        mDateView.layout(j, paramInt1, mDateView.getMeasuredWidth() + j, paramInt1 + paramInt2);
      }
      paramInt4 = mDateView.getMeasuredWidth();
      i = mIndicatorMargin;
      paramInt4 = layoutIndicatorsRightward(mSimIndicator, paramInt1, paramInt2, j + (paramInt4 + i));
      paramInt4 = layoutIndicatorsRightward(mErrorIndicator, paramInt1, paramInt2, paramInt4);
      paramInt4 = layoutIndicatorsRightward(mDraftIndicator, paramInt1, paramInt2, paramInt4);
      paramInt4 = layoutIndicatorsRightward(mTimedIndicator, paramInt1, paramInt2, paramInt4);
      layoutIndicatorsRightward(mAttachmentIndicator, paramInt1, paramInt2, paramInt4);
      break;
      if (mRightArrowIndicator.getVisibility() != 8)
      {
        n = (paramInt4 - paramInt2 - mRightArrowIndicator.getMeasuredHeight()) / 2;
        i1 = mRightArrowIndicator.getMeasuredWidth();
        mRightArrowIndicator.layout(paramInt1 - i1, n, paramInt1, mRightArrowIndicator.getMeasuredHeight() + n);
        mRightArrowIndicator.getMeasuredWidth();
        paramInt1 = mIndicatorMargin;
      }
    }
  }
  
  private void onHugeMeasure(int paramInt1, int paramInt2)
  {
    int i = View.MeasureSpec.getSize(paramInt1) - sPaddingOuterleft - sPaddingOuterright;
    int j = i;
    if (mAvatarView.getVisibility() != 8)
    {
      j = View.MeasureSpec.makeMeasureSpec(mAvatarSize, 1073741824);
      mAvatarView.measure(j, j);
      j = i - (mAvatarView.getMeasuredWidth() + mPaddingAvatar);
    }
    if (mCheckBox.getVisibility() != 8)
    {
      mCheckBox.measure(0, 0);
      i = j - mCheckBoxWidth;
      if (mMsgCountView.getVisibility() == 8) {
        break label560;
      }
      mMsgCountView.measure(0, 0);
    }
    label560:
    for (int k = i - mMsgCountView.getMeasuredWidth();; k = i)
    {
      j = k;
      if (mUnreadMsgCountView.getVisibility() != 8)
      {
        mUnreadMsgCountView.measure(0, 0);
        j = k - mUnreadMsgCountView.getMeasuredWidth();
      }
      k = j;
      if (mFetionPrefix.getVisibility() != 8)
      {
        mFetionPrefix.measure(0, 0);
        k = j - mFetionPrefix.getMeasuredWidth();
      }
      mFromView.measure(View.MeasureSpec.makeMeasureSpec(k, Integer.MIN_VALUE), 0);
      mDateView.measure(0, 0);
      k = i - (mDateView.getMeasuredWidth() + mPaddingInner);
      j = k;
      if (mAttachmentIndicator.getVisibility() != 8)
      {
        mAttachmentIndicator.measure(0, 0);
        j = k - (mAttachmentIndicator.getMeasuredWidth() + mIndicatorMargin);
      }
      k = j;
      if (mTimedIndicator.getVisibility() != 8)
      {
        mTimedIndicator.measure(0, 0);
        k = j - (mTimedIndicator.getMeasuredWidth() + mIndicatorMargin);
      }
      j = k;
      if (mDraftIndicator.getVisibility() != 8)
      {
        mDraftIndicator.measure(0, 0);
        j = k - (mDraftIndicator.getMeasuredWidth() + mIndicatorMargin);
      }
      k = j;
      if (mErrorIndicator.getVisibility() != 8)
      {
        mErrorIndicator.measure(0, 0);
        k = j - (mErrorIndicator.getMeasuredWidth() + mIndicatorMargin);
      }
      if (mSimIndicator.getVisibility() != 8)
      {
        mSimIndicator.measure(0, 0);
        mSimIndicator.getMeasuredWidth();
        j = mIndicatorMargin;
      }
      if (mSubjectView.getVisibility() != 8) {
        mSubjectView.measure(View.MeasureSpec.makeMeasureSpec(i, Integer.MIN_VALUE), 0);
      }
      paramInt1 = View.MeasureSpec.getSize(paramInt1);
      if (sRowHeightLarge == 0) {
        sRowHeightLarge = (int)AttributeResolver.resolveDimension(mContext, 16843654);
      }
      setMeasuredDimension(paramInt1, getDefaultSize(sRowHeightLarge, paramInt2));
      return;
      i = j;
      if (mRightArrowIndicator.getVisibility() == 8) {
        break;
      }
      mRightArrowIndicator.measure(0, 0);
      i = j - (mRightArrowIndicator.getMeasuredWidth() + mIndicatorMargin);
      break;
    }
  }
  
  private void setSimIndicatorVisibility()
  {
    if ((MSimUtils.isMSim()) && (MSimUtils.isMSimInserted()) && (!mIsSpSentinel))
    {
      switch (MSimUtils.getSlotIdBySimInfoId(mConversation.getLastSimId()))
      {
      default: 
        mSimIndicator.setVisibility(8);
        return;
      case 0: 
        mSimIndicator.setVisibility(0);
        mSimIndicator.setImageResource(2130837970);
        return;
      }
      mSimIndicator.setVisibility(0);
      mSimIndicator.setImageResource(2130837971);
      return;
    }
    mSimIndicator.setVisibility(8);
  }
  
  private void updateAvatarView()
  {
    int i = mConversation.getRecipients().size();
    mAvatarView.setOnClickListener(mAvatarView);
    if (mIsSpSentinel)
    {
      mAvatarView.assignContactUri(null);
      mAvatarView.setImageResource(2130838000);
      Contact.cancelRequest(mAvatarView);
    }
    for (;;)
    {
      mAvatarView.setVisibility(0);
      return;
      if (isBlockedPlaceHolder())
      {
        mAvatarView.assignContactUri(null);
        mAvatarView.setImageResource(2130837712);
        Contact.cancelRequest(mAvatarView);
      }
      else if (isMipubPlaceHolder())
      {
        mAvatarView.assignContactUri(null);
        mAvatarView.setImageResource(2130837758);
        Contact.cancelRequest(mAvatarView);
      }
      else if (i == 0)
      {
        mAvatarView.assignContactUri(null);
        mAvatarView.setImageResource(285343796);
        Contact.cancelRequest(mAvatarView);
      }
      else if (i == 1)
      {
        Contact localContact = (Contact)mConversation.getRecipients().get(0);
        if (localContact.existsInDatabase())
        {
          mAvatarView.assignContactUri(localContact.getUri());
          Contact.loadPhoto(mAvatarView, localContact);
        }
        else if (localContact.isB2cNumber())
        {
          mAvatarView.assignContactUri(null);
          Contact.loadPhoto(mAvatarView, localContact);
        }
        else if (localContact.isYellowPageNumber())
        {
          mAvatarView.assignContactUri(null);
          mAvatarView.assignContactFromPhone(localContact.getNumber(), true);
          Contact.loadPhoto(mAvatarView, localContact);
        }
        else if (localContact.isEmail())
        {
          Contact.cancelRequest(mAvatarView);
          mAvatarView.assignContactUri(null);
          mAvatarView.assignContactFromEmail(localContact.getNumber(), true);
          mAvatarView.setImageResource(285343796);
        }
        else
        {
          Contact.cancelRequest(mAvatarView);
          mAvatarView.assignContactUri(null);
          mAvatarView.assignContactFromPhone(localContact.getNumber(), true);
          mAvatarView.setImageResource(285343796);
        }
      }
      else
      {
        mAvatarView.assignContactUri(null);
        Contact.cancelRequest(mAvatarView);
        mAvatarView.setImageResource(285343792);
      }
    }
  }
  
  private void updateAvatarVisibility()
  {
    if (!MiuiSettings.System.isSimpleMode(mContext)) {}
    for (int i = 1; i != 0; i = 0)
    {
      mAvatarView.setVisibility(0);
      updateAvatarView();
      return;
    }
    mAvatarView.setVisibility(8);
  }
  
  private void updateFromView()
  {
    if (mIsSpSentinel) {
      mFromView.setText(2131362267);
    }
    for (;;)
    {
      updateTagView();
      return;
      if (isBlockedPlaceHolder())
      {
        mFromView.setText(2131362370);
      }
      else if (isMipubPlaceHolder())
      {
        mFromView.setText(2131362371);
      }
      else
      {
        int i = mConversation.getRecipients().formatNames(mFromBuffer, ", ");
        mFromView.setText(mFromBuffer, 0, i);
      }
    }
  }
  
  private void updateMsgCount(char[] paramArrayOfChar, int paramInt1, int paramInt2)
  {
    if ((Build.IS_CM_CUSTOMIZATION_TEST) && (mMsgCountView != null)) {
      mMsgCountView.setText(paramArrayOfChar, paramInt1, paramInt2);
    }
  }
  
  private void updateMsgCountVisibility(int paramInt)
  {
    if ((Build.IS_CM_CUSTOMIZATION_TEST) && (mMsgCountView != null)) {
      mMsgCountView.setVisibility(paramInt);
    }
  }
  
  private void updateTagView()
  {
    int i = 0;
    if ((isPlaceHolder()) || (mIsSpSentinel))
    {
      mTagView.setVisibility(8);
      return;
    }
    int j = mConversation.getRecipients().formatTags(mTagBuffer);
    mTagView.setText(mTagBuffer, 0, j);
    TextView localTextView = mTagView;
    if (j != 0) {}
    for (;;)
    {
      localTextView.setVisibility(i);
      return;
      i = 8;
    }
  }
  
  public final void bind(Context paramContext, Conversation paramConversation, boolean paramBoolean, Cursor paramCursor, int paramInt1, int paramInt2)
  {
    mConversation = paramConversation;
    mDragPosition = 0;
    label59:
    label102:
    label149:
    int j;
    label200:
    Object localObject2;
    label365:
    label371:
    Object localObject1;
    label384:
    int i;
    if ((paramBoolean) && (!isPlaceHolder()) && (!mIsSpSentinel))
    {
      mCheckBox.setVisibility(0);
      mCheckBox.setEnabled(true);
      if (!paramConversation.isChecked()) {
        break label568;
      }
      mCheckBox.setChecked(true);
      if (((isPlaceHolder()) || (mIsSpSentinel)) && (!isHugeMode())) {
        break label579;
      }
      mDateView.setVisibility(0);
      mDateView.setText(mConversation.getDateString());
      if (Contact.getLoadAllState() != 0) {
        mConversation.loadRecipients(false, false);
      }
      if ((!isPlaceHolder()) && (!mIsSpSentinel)) {
        break label591;
      }
      mFromView.setText(2131362267);
      mRightArrowIndicator.setVisibility(0);
      updateFromView();
      paramCursor = mFetionPrefix;
      if ((isPlaceHolder()) || (paramConversation.getRecipients().size() != 1) || (!AddressUtils.isFetionNumber(((Contact)paramConversation.getRecipients().get(0)).getNumber()))) {
        break label636;
      }
      j = 0;
      paramCursor.setVisibility(j);
      Contact.addListener(this);
      mNeedUpdateContact = true;
      paramCursor = paramConversation.getSnippet();
      localObject2 = paramCursor;
      if (isBlockedPlaceHolder()) {
        localObject2 = paramConversation.getRecipients().formatNames(",") + ":" + paramCursor;
      }
      paramBoolean = MiuiSettings.System.isSimpleMode(mContext);
      if (!mIsSpSentinel) {
        break label718;
      }
      if (paramInt1 <= 0) {
        break label674;
      }
      if (paramBoolean) {
        break label643;
      }
      mFromView.setTextAppearance(mContext, 2131689495);
      mUnreadMsgCountView.setVisibility(0);
      mShowUnreadMsgIndicator = false;
      mCountBuffer[0] = '(';
      paramInt1 = MessageUtils.appendIntToCharArray(mCountBuffer, 1, paramInt1);
      mCountBuffer[paramInt1] = ')';
      mUnreadMsgCountView.setText(mCountBuffer, 0, paramInt1 + 1);
      updateMsgCountVisibility(8);
      if (!paramConversation.hasDraft()) {
        break label1488;
      }
      localObject1 = null;
      paramInt1 = 0;
      if (paramInt1 >= ((String)localObject2).length()) {
        break label1306;
      }
      i = ((String)localObject2).charAt(paramInt1);
      if (i != 65535) {
        break label1282;
      }
      paramCursor = (Cursor)localObject1;
      if (localObject1 == null) {
        paramCursor = new SpannableStringBuilder(((String)localObject2).substring(0, paramInt1));
      }
      localObject1 = paramContext.getString(2131362052);
      paramInt2 = ((String)localObject1).length();
      paramCursor.append((CharSequence)localObject1);
      j = paramCursor.length();
      localObject1 = mContext.getResources();
      paramCursor.setSpan(new BackgroundColorSpan(((Resources)localObject1).getColor(2131296290)), j - paramInt2, j, 33);
      paramCursor.setSpan(new ForegroundColorSpan(((Resources)localObject1).getColor(2131296289)), j - paramInt2, j, 33);
    }
    for (;;)
    {
      paramInt1 += 1;
      localObject1 = paramCursor;
      break label384;
      mCheckBox.setVisibility(8);
      mCheckBox.setEnabled(false);
      break;
      label568:
      mCheckBox.setChecked(false);
      break label59;
      label579:
      mDateView.setVisibility(8);
      break label102;
      label591:
      j = mConversation.getRecipients().formatNames(mFromBuffer, ", ");
      mFromView.setText(mFromBuffer, 0, j);
      mRightArrowIndicator.setVisibility(8);
      break label149;
      label636:
      j = 8;
      break label200;
      label643:
      mFromView.setTextAppearance(mContext, 2131689494);
      mUnreadMsgCountView.setVisibility(8);
      mShowUnreadMsgIndicator = true;
      break label365;
      label674:
      mFromView.setTextAppearance(mContext, 2131689494);
      mUnreadMsgCountView.setVisibility(8);
      mShowUnreadMsgIndicator = false;
      mUnreadMsgCountView.setText(mCountBuffer, 0, 0);
      break label365;
      label718:
      if (isBlockedPlaceHolder())
      {
        if (paramInt2 > 0)
        {
          if (!paramBoolean)
          {
            mFromView.setTextAppearance(mContext, 2131689495);
            mUnreadMsgCountView.setVisibility(0);
            mShowUnreadMsgIndicator = false;
            mCountBuffer[0] = '(';
            paramInt1 = MessageUtils.appendIntToCharArray(mCountBuffer, 1, paramInt2);
            mCountBuffer[paramInt1] = ')';
            mUnreadMsgCountView.setText(mCountBuffer, 0, paramInt1 + 1);
            break label371;
          }
          mFromView.setTextAppearance(mContext, 2131689494);
          mUnreadMsgCountView.setVisibility(8);
          mShowUnreadMsgIndicator = true;
          break label371;
        }
        mFromView.setTextAppearance(mContext, 2131689494);
        mUnreadMsgCountView.setVisibility(8);
        mShowUnreadMsgIndicator = false;
        mUnreadMsgCountView.setText(mCountBuffer, 0, 0);
        break label371;
      }
      if ((paramConversation.getUnreadMessageCount() > 0) && (!paramConversation.getPreMarkUnread()))
      {
        if (!paramBoolean)
        {
          mFromView.setTextAppearance(mContext, 2131689495);
          mTagView.setTextAppearance(mContext, 2131689501);
          mUnreadMsgCountView.setVisibility(0);
          updateMsgCountVisibility(8);
          mShowUnreadMsgIndicator = false;
          mCountBuffer[0] = '(';
          paramInt1 = MessageUtils.appendIntToCharArray(mCountBuffer, 1, paramConversation.getUnreadMessageCount());
          mCountBuffer[paramInt1] = ')';
          mUnreadMsgCountView.setText(mCountBuffer, 0, paramInt1 + 1);
          break label371;
        }
        mFromView.setTextAppearance(mContext, 2131689494);
        mTagView.setTextAppearance(mContext, 2131689500);
        mUnreadMsgCountView.setVisibility(8);
        mShowUnreadMsgIndicator = true;
        paramInt2 = 8;
        paramInt1 = paramInt2;
        if (Build.IS_CM_CUSTOMIZATION_TEST)
        {
          paramInt1 = paramInt2;
          if (paramConversation.getMessageCount() > 1)
          {
            mCountBuffer[0] = '(';
            paramInt1 = MessageUtils.appendIntToCharArray(mCountBuffer, 1, paramConversation.getMessageCount());
            mCountBuffer[paramInt1] = ')';
            updateMsgCount(mCountBuffer, 0, paramInt1 + 1);
            paramInt1 = 0;
          }
        }
        updateMsgCountVisibility(paramInt1);
        break label371;
      }
      if ((Build.IS_CM_CUSTOMIZATION_TEST) && (paramConversation.getMessageCount() > 1))
      {
        mFromView.setTextAppearance(mContext, 2131689494);
        mTagView.setTextAppearance(mContext, 2131689500);
        mUnreadMsgCountView.setVisibility(8);
        mShowUnreadMsgIndicator = false;
        mCountBuffer[0] = '(';
        paramInt1 = MessageUtils.appendIntToCharArray(mCountBuffer, 1, paramConversation.getMessageCount());
        mCountBuffer[paramInt1] = ')';
        updateMsgCount(mCountBuffer, 0, paramInt1 + 1);
        updateMsgCountVisibility(0);
        break label371;
      }
      mFromView.setTextAppearance(mContext, 2131689494);
      mTagView.setTextAppearance(mContext, 2131689500);
      mUnreadMsgCountView.setVisibility(8);
      updateMsgCountVisibility(8);
      mShowUnreadMsgIndicator = false;
      break label371;
      label1282:
      paramCursor = (Cursor)localObject1;
      if (localObject1 != null)
      {
        ((SpannableStringBuilder)localObject1).append(i);
        paramCursor = (Cursor)localObject1;
      }
    }
    label1306:
    if (localObject1 != null)
    {
      mSubjectView.setText((CharSequence)localObject1);
      paramBoolean = paramConversation.hasError();
      paramContext = mErrorIndicator;
      if ((!paramBoolean) || (mIsSpSentinel)) {
        break label1500;
      }
      paramInt1 = 0;
      label1344:
      paramContext.setVisibility(paramInt1);
      paramBoolean = Conversation.TimedThreads.hasTimedMessage(mConversation.getThreadId());
      paramContext = mTimedIndicator;
      if ((!paramBoolean) || (mIsSpSentinel)) {
        break label1507;
      }
      paramInt1 = 0;
      label1380:
      paramContext.setVisibility(paramInt1);
      paramBoolean = paramConversation.hasAttachment();
      paramContext = mAttachmentIndicator;
      if ((!paramBoolean) || (mIsSpSentinel)) {
        break label1514;
      }
      paramInt1 = 0;
      label1410:
      paramContext.setVisibility(paramInt1);
      paramBoolean = paramConversation.hasDraft();
      paramContext = mDraftIndicator;
      if ((!paramBoolean) || (mIsSpSentinel)) {
        break label1521;
      }
      paramInt1 = 0;
      label1440:
      paramContext.setVisibility(paramInt1);
      if ((!paramConversation.isSticky()) || (mIsSpSentinel)) {
        break label1528;
      }
    }
    label1488:
    label1500:
    label1507:
    label1514:
    label1521:
    label1528:
    for (paramBoolean = true;; paramBoolean = false)
    {
      mShowStickIndicator = paramBoolean;
      setSimIndicatorVisibility();
      updateAvatarVisibility();
      return;
      mSubjectView.setText((CharSequence)localObject2);
      break;
      mSubjectView.setText((CharSequence)localObject2);
      break;
      paramInt1 = 8;
      break label1344;
      paramInt1 = 8;
      break label1380;
      paramInt1 = 8;
      break label1410;
      paramInt1 = 8;
      break label1440;
    }
  }
  
  public void onAnimationDone()
  {
    if (mDragToReaded)
    {
      markAsRead();
      mDragToReaded = false;
    }
  }
  
  public void onDraw(Canvas paramCanvas)
  {
    onDraggedDraw(paramCanvas);
    super.onDraw(paramCanvas);
    if (mShowUnreadMsgIndicator) {
      mUnreadMsgIndicator.draw(paramCanvas);
    }
    if (mShowStickIndicator) {
      mStickIndicator.draw(paramCanvas);
    }
  }
  
  protected void onFinishInflate()
  {
    super.onFinishInflate();
    mAvatarView = ((QuickContactBadge)findViewById(2131820584));
    mFetionPrefix = findViewById(2131820585);
    mFromView = ((TextView)findViewById(2131820586));
    mTagView = ((TextView)findViewById(2131820587));
    mUnreadMsgCountView = ((TextView)findViewById(2131820589));
    mMsgCountView = ((TextView)findViewById(2131820588));
    mSubjectView = ((TextView)findViewById(2131820596));
    mDateView = ((TextView)findViewById(2131820590));
    mSimIndicator = ((ImageView)findViewById(2131820595));
    mAttachmentIndicator = findViewById(2131820591);
    mTimedIndicator = findViewById(2131820592);
    mDraftIndicator = findViewById(2131820593);
    mErrorIndicator = findViewById(2131820594);
    mCheckBox = ((CheckBox)findViewById(16908289));
    mRightArrowIndicator = ((ImageView)findViewById(2131820597));
  }
  
  public void onItemDragReleased(int paramInt)
  {
    int j = (int)(0.5F * getWidth());
    int i = paramInt;
    if (paramInt > j) {
      i = j;
    }
    mDragPosition = 0;
    mResetAnimator = new ViewAnimator(this);
    mResetAnimator.animate(new AccelerateInterpolator(1.0F), 250L, new int[] { i, 0 });
  }
  
  public void onItemDragged(int paramInt)
  {
    int i = paramInt;
    if (paramInt < 0) {
      i = 0;
    }
    int j = (int)(0.5F * getWidth());
    paramInt = i;
    if (i > j) {
      paramInt = j;
    }
    if (mDragPosition != paramInt)
    {
      if (isReadedRegion(paramInt)) {
        mDragToReaded = true;
      }
      mDragPosition = paramInt;
      invalidate();
    }
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if (isHugeMode())
    {
      onHugeLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
      return;
    }
    int k;
    if (mShowUnreadMsgIndicator)
    {
      i = mUnreadMsgIndicator.getIntrinsicHeight();
      j = mUnreadMsgIndicator.getIntrinsicWidth();
      k = mSubjectView.getMeasuredHeight();
      mUnreadMsgIndicator.setBounds((sPaddingOuterleft - j) / 2 + paramInt1, (paramInt4 - paramInt2 - i - k - mSubjectPadding) / 2, (sPaddingOuterright + j) / 2 + paramInt1, (paramInt4 - paramInt2 + i - k - mSubjectPadding) / 2);
    }
    int m;
    if (mAvatarView.getVisibility() == 8)
    {
      i = paramInt3 - sPaddingOuterright;
      if (mCheckBox.getVisibility() != 8)
      {
        paramInt1 = (paramInt4 - paramInt2 - mCheckBox.getMeasuredHeight()) / 2;
        j = i - mCheckBoxWidth + (mCheckBoxWidth - mCheckBox.getMeasuredWidth()) / 2;
        mCheckBox.layout(j, paramInt1, mCheckBox.getMeasuredWidth() + j, mCheckBox.getMeasuredHeight() + paramInt1);
        paramInt1 = i - mCheckBoxWidth;
      }
      for (;;)
      {
        i = sPaddingOuterleft;
        j = (paramInt4 - paramInt2 - mFromView.getMeasuredHeight() - mSubjectView.getMeasuredHeight() - mSubjectPadding) / 2;
        paramInt2 = i;
        paramInt4 = paramInt2;
        if (mFetionPrefix.getVisibility() != 8)
        {
          paramInt4 = mFromView.getBaseline() + j - mFetionPrefix.getBaseline();
          mFetionPrefix.layout(paramInt2, paramInt4, mFetionPrefix.getMeasuredWidth() + paramInt2, mFetionPrefix.getMeasuredHeight() + paramInt4);
          paramInt4 = paramInt2 + mFetionPrefix.getMeasuredWidth();
        }
        mFromView.layout(paramInt4, j, mFromView.getMeasuredWidth() + paramInt4, mFromView.getMeasuredHeight() + j);
        paramInt4 += mFromView.getMeasuredWidth();
        paramInt2 = paramInt4;
        if (mTagView.getVisibility() != 8)
        {
          paramInt2 = mFromView.getBaseline() + j - mTagView.getBaseline();
          mTagView.layout(paramInt4, paramInt2, mTagView.getMeasuredWidth() + paramInt4, mTagView.getMeasuredHeight() + paramInt2);
          paramInt2 = paramInt4 + mTagView.getMeasuredWidth();
        }
        k = j + mFromView.getBaseline() - mDateView.getBaseline();
        m = k + mDateView.getMeasuredHeight();
        paramInt4 = paramInt2;
        if (mUnreadMsgCountView.getVisibility() != 8)
        {
          paramInt4 = j + (mFromView.getMeasuredHeight() - mUnreadMsgCountView.getMeasuredHeight()) / 2;
          mUnreadMsgCountView.layout(paramInt2, paramInt4, mUnreadMsgCountView.getMeasuredWidth() + paramInt2, mUnreadMsgCountView.getMeasuredHeight() + paramInt4);
          paramInt4 = paramInt2 + mUnreadMsgCountView.getMeasuredWidth();
        }
        if (mMsgCountView.getVisibility() != 8)
        {
          paramInt2 = j + (mFromView.getMeasuredHeight() - mMsgCountView.getMeasuredHeight()) / 2;
          mMsgCountView.layout(paramInt4, paramInt2, mMsgCountView.getMeasuredWidth() + paramInt4, mMsgCountView.getMeasuredHeight() + paramInt2);
          mMsgCountView.getMeasuredWidth();
        }
        paramInt1 = layoutIndicatorsLeftward(mSimIndicator, k, m, paramInt1);
        mDateView.layout(paramInt1 - mDateView.getMeasuredWidth(), k, paramInt1, m);
        paramInt2 = mDateView.getMeasuredWidth();
        paramInt4 = mIndicatorMargin;
        paramInt1 = layoutIndicatorsLeftward(mErrorIndicator, k, m, paramInt1 - (paramInt2 + paramInt4));
        paramInt1 = layoutIndicatorsLeftward(mDraftIndicator, k, m, paramInt1);
        paramInt1 = layoutIndicatorsLeftward(mTimedIndicator, k, m, paramInt1);
        layoutIndicatorsLeftward(mAttachmentIndicator, k, m, paramInt1);
        paramInt1 = j + (mFromView.getHeight() + mSubjectPadding);
        if (mSubjectView.getVisibility() != 8) {
          mSubjectView.layout(i, paramInt1, mSubjectView.getMeasuredWidth() + i, mSubjectView.getMeasuredHeight() + paramInt1);
        }
        label809:
        if (!mShowStickIndicator) {
          break;
        }
        mStickIndicator.setBounds(paramInt3 - mStickIndicator.getIntrinsicWidth(), 0, paramInt3, mStickIndicator.getIntrinsicHeight());
        return;
        paramInt1 = i;
        if (mRightArrowIndicator.getVisibility() != 8)
        {
          paramInt1 = (paramInt4 - paramInt2 - mRightArrowIndicator.getMeasuredHeight()) / 2;
          j = mRightArrowIndicator.getMeasuredWidth();
          mRightArrowIndicator.layout(i - j, paramInt1, i, mRightArrowIndicator.getMeasuredHeight() + paramInt1);
          paramInt1 = i - (mRightArrowIndicator.getMeasuredWidth() + mIndicatorMargin);
        }
      }
    }
    paramInt1 = sPaddingOuterleft;
    int i = (mRowHeight - mAvatarView.getMeasuredHeight()) / 2;
    mAvatarView.layout(paramInt1, i, mAvatarView.getMeasuredWidth() + paramInt1, mAvatarView.getMeasuredHeight() + i);
    i = paramInt1 + (mAvatarView.getMeasuredWidth() + mPaddingAvatar);
    int j = paramInt3 - sPaddingOuterright;
    if (mCheckBox.getVisibility() != 8)
    {
      paramInt1 = (paramInt4 - paramInt2 - mCheckBox.getMeasuredHeight()) / 2;
      k = j - mCheckBoxWidth + (mCheckBoxWidth - mCheckBox.getMeasuredWidth()) / 2;
      mCheckBox.layout(k, paramInt1, mCheckBox.getMeasuredWidth() + k, mCheckBox.getMeasuredHeight() + paramInt1);
      paramInt1 = j - mCheckBoxWidth;
    }
    for (;;)
    {
      j = (paramInt4 - paramInt2 - mFromView.getMeasuredHeight() - mSubjectView.getMeasuredHeight() - mSubjectPadding) / 2;
      paramInt2 = i;
      paramInt4 = paramInt2;
      if (mFetionPrefix.getVisibility() != 8)
      {
        paramInt4 = mFromView.getBaseline() + j - mFetionPrefix.getBaseline();
        mFetionPrefix.layout(paramInt2, paramInt4, mFetionPrefix.getMeasuredWidth() + paramInt2, mFetionPrefix.getMeasuredHeight() + paramInt4);
        paramInt4 = paramInt2 + mFetionPrefix.getMeasuredWidth();
      }
      mFromView.layout(paramInt4, j, mFromView.getMeasuredWidth() + paramInt4, mFromView.getMeasuredHeight() + j);
      paramInt4 += mFromView.getMeasuredWidth();
      paramInt2 = paramInt4;
      if (mTagView.getVisibility() != 8)
      {
        paramInt2 = mFromView.getBaseline() + j - mTagView.getBaseline();
        mTagView.layout(paramInt4, paramInt2, mTagView.getMeasuredWidth() + paramInt4, mTagView.getMeasuredHeight() + paramInt2);
        paramInt2 = paramInt4 + mTagView.getMeasuredWidth();
      }
      mFromView.getBaseline();
      Paint.FontMetricsInt localFontMetricsInt = mFromView.getPaint().getFontMetricsInt();
      paramInt4 = ascent;
      paramInt4 = descent;
      paramInt4 = paramInt2;
      if (mUnreadMsgCountView.getVisibility() != 8)
      {
        paramInt4 = j + (mFromView.getMeasuredHeight() - mUnreadMsgCountView.getMeasuredHeight()) / 2;
        mUnreadMsgCountView.layout(paramInt2, paramInt4, mUnreadMsgCountView.getMeasuredWidth() + paramInt2, mUnreadMsgCountView.getMeasuredHeight() + paramInt4);
        paramInt4 = paramInt2 + mUnreadMsgCountView.getMeasuredWidth();
      }
      if (mMsgCountView.getVisibility() != 8)
      {
        paramInt2 = j + (mFromView.getMeasuredHeight() - mMsgCountView.getMeasuredHeight()) / 2;
        mMsgCountView.layout(paramInt4, paramInt2, mMsgCountView.getMeasuredWidth() + paramInt4, mMsgCountView.getMeasuredHeight() + paramInt2);
        mMsgCountView.getMeasuredWidth();
      }
      paramInt2 = j + mFromView.getBaseline() - mDateView.getBaseline();
      paramInt4 = paramInt2 + mDateView.getMeasuredHeight();
      paramInt1 = layoutIndicatorsLeftward(mSimIndicator, paramInt2, paramInt4, paramInt1);
      mDateView.layout(paramInt1 - mDateView.getMeasuredWidth(), paramInt2, paramInt1, paramInt4);
      k = mDateView.getMeasuredWidth();
      m = mIndicatorMargin;
      paramInt1 = layoutIndicatorsLeftward(mErrorIndicator, paramInt2, paramInt4, paramInt1 - (k + m));
      paramInt1 = layoutIndicatorsLeftward(mDraftIndicator, paramInt2, paramInt4, paramInt1);
      paramInt1 = layoutIndicatorsLeftward(mTimedIndicator, paramInt2, paramInt4, paramInt1);
      layoutIndicatorsLeftward(mAttachmentIndicator, paramInt2, paramInt4, paramInt1);
      paramInt1 = j + (mFromView.getHeight() + mSubjectPadding);
      if (mSubjectView.getVisibility() == 8) {
        break label809;
      }
      mSubjectView.layout(i, paramInt1, mSubjectView.getMeasuredWidth() + i, mSubjectView.getMeasuredHeight() + paramInt1);
      break label809;
      break;
      paramInt1 = j;
      if (mRightArrowIndicator.getVisibility() != 8)
      {
        paramInt1 = (paramInt4 - paramInt2 - mRightArrowIndicator.getMeasuredHeight()) / 2;
        k = mRightArrowIndicator.getMeasuredWidth();
        mRightArrowIndicator.layout(j - k, paramInt1, j, mRightArrowIndicator.getMeasuredHeight() + paramInt1);
        paramInt1 = j - (mRightArrowIndicator.getMeasuredWidth() + mIndicatorMargin);
      }
    }
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    if (isHugeMode())
    {
      onHugeMeasure(paramInt1, paramInt2);
      return;
    }
    int i = View.MeasureSpec.getSize(paramInt1) - sPaddingOuterleft - sPaddingOuterright;
    int j = i;
    if (mAvatarView.getVisibility() != 8)
    {
      j = View.MeasureSpec.makeMeasureSpec(mAvatarSize, 1073741824);
      mAvatarView.measure(j, j);
      j = i - (mAvatarView.getMeasuredWidth() + mPaddingAvatar);
    }
    if (mCheckBox.getVisibility() != 8)
    {
      mCheckBox.measure(0, 0);
      i = j - mCheckBoxWidth;
      if (mMsgCountView.getVisibility() == 8) {
        break label607;
      }
      mMsgCountView.measure(0, 0);
    }
    label607:
    for (int k = i - mMsgCountView.getMeasuredWidth();; k = i)
    {
      j = k;
      if (mUnreadMsgCountView.getVisibility() != 8)
      {
        mUnreadMsgCountView.measure(0, 0);
        j = k - mUnreadMsgCountView.getMeasuredWidth();
      }
      k = j;
      if (mFetionPrefix.getVisibility() != 8)
      {
        mFetionPrefix.measure(0, 0);
        k = j - mFetionPrefix.getMeasuredWidth();
      }
      mDateView.measure(0, 0);
      k -= mDateView.getMeasuredWidth() + mPaddingInner;
      j = k;
      if (mAttachmentIndicator.getVisibility() != 8)
      {
        mAttachmentIndicator.measure(0, 0);
        j = k - (mAttachmentIndicator.getMeasuredWidth() + mIndicatorMargin);
      }
      k = j;
      if (mTimedIndicator.getVisibility() != 8)
      {
        mTimedIndicator.measure(0, 0);
        k = j - (mTimedIndicator.getMeasuredWidth() + mIndicatorMargin);
      }
      j = k;
      if (mDraftIndicator.getVisibility() != 8)
      {
        mDraftIndicator.measure(0, 0);
        j = k - (mDraftIndicator.getMeasuredWidth() + mIndicatorMargin);
      }
      k = j;
      if (mErrorIndicator.getVisibility() != 8)
      {
        mErrorIndicator.measure(0, 0);
        k = j - (mErrorIndicator.getMeasuredWidth() + mIndicatorMargin);
      }
      j = k;
      if (mTagView.getVisibility() != 8)
      {
        mTagView.measure(View.MeasureSpec.makeMeasureSpec(k / 2, Integer.MIN_VALUE), 0);
        j = k - mTagView.getMeasuredWidth();
      }
      k = j;
      if (mSimIndicator.getVisibility() != 8)
      {
        mSimIndicator.measure(0, 0);
        k = j - (mSimIndicator.getMeasuredWidth() + mIndicatorMargin);
      }
      mFromView.measure(View.MeasureSpec.makeMeasureSpec(k, Integer.MIN_VALUE), 0);
      if (mSubjectView.getVisibility() != 8) {
        mSubjectView.measure(View.MeasureSpec.makeMeasureSpec(i, Integer.MIN_VALUE), 0);
      }
      setMeasuredDimension(View.MeasureSpec.getSize(paramInt1), getDefaultSize(mRowHeight, paramInt2));
      return;
      i = j;
      if (mRightArrowIndicator.getVisibility() == 8) {
        break;
      }
      mRightArrowIndicator.measure(0, 0);
      i = j - (mRightArrowIndicator.getMeasuredWidth() + mIndicatorMargin);
      break;
    }
  }
  
  public void onUpdate(Contact paramContact)
  {
    if (mHandler != null)
    {
      mHandler.removeCallbacks(mOnUpdateRunnable);
      mHandler.postDelayed(mOnUpdateRunnable, 500L);
    }
  }
  
  public void setIsSpSentinel(boolean paramBoolean)
  {
    mIsSpSentinel = paramBoolean;
  }
  
  public void setPlaceHolderType(int paramInt)
  {
    mPlaceHolderType = paramInt;
  }
  
  public final void unbind()
  {
    mDragPosition = 0;
    Contact.cancelRequest(mAvatarView);
    Contact.removeListener(this);
    mNeedUpdateContact = false;
  }
  
  public boolean updatePreMarkUnReadView()
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (mConversation != null)
    {
      bool1 = bool2;
      if (mConversation.getPreMarkUnread()) {
        if (mUnreadMsgCountView.getVisibility() != 0)
        {
          bool1 = bool2;
          if (!mShowUnreadMsgIndicator) {}
        }
        else
        {
          mFromView.setTextAppearance(mContext, 2131689494);
          mTagView.setTextAppearance(mContext, 2131689500);
          mUnreadMsgCountView.setVisibility(8);
          updateMsgCountVisibility(0);
          mShowUnreadMsgIndicator = false;
          bool1 = true;
        }
      }
    }
    return bool1;
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.ConversationListItem
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */