package com.android.mms.ui;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Application;
import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.CompatibilityInfo;
import android.content.res.MiuiConfiguration;
import android.content.res.Resources;
import android.database.sqlite.SqliteWrapper;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.provider.Telephony.Mms;
import android.provider.Telephony.Sms;
import android.text.Html;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.text.method.HideReturnsTransformationMethod;
import android.text.style.URLSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.ViewStub;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;
import com.android.mms.MmsApp;
import com.android.mms.audio.AudioItemCache;
import com.android.mms.audio.AudioItemController;
import com.android.mms.data.Contact;
import com.android.mms.data.Contact.UpdateListener;
import com.android.mms.model.ContactMessage;
import com.android.mms.model.ContactMessage.ContactRecord;
import com.android.mms.model.ContactParser;
import com.android.mms.transaction.Transaction;
import com.android.mms.transaction.TransactionService;
import com.android.mms.understand.UnderstandAction;
import com.android.mms.understand.UnderstandFactory;
import com.android.mms.understand.UnderstandMessage;
import com.android.mms.understand.UnderstandMessage.Item;
import com.android.mms.util.MSimUtils;
import com.android.mms.util.MiStatSdkHelper;
import com.miui.gallery.lib.MiuiGalleryUtils;
import com.xiaomi.mms.data.MxIdCache;
import com.xiaomi.mms.data.MxIdCache.MxIdCacheItem;
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
  implements Contact.UpdateListener
{
  private static final int LINK_MASK_WITH_TIME = Linkify.TIME_PHRASES | 0xF;
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
  private final int mBubbleAttachmentPreviewMargin = mContext.getResources().getDimensionPixelSize(2131427409);
  private final int mBubbleCheckBoxDistance = mContext.getResources().getDimensionPixelSize(2131427351);
  private final int mBubbleIndicatorTopMargin = mContext.getResources().getDimensionPixelSize(2131427396);
  private final int mBubbleTopMargin = mContext.getResources().getDimensionPixelSize(2131427394);
  private final int mBubbleTopMarginMinimum = mContext.getResources().getDimensionPixelSize(2131427395);
  private CheckBox mCheckBox;
  private View mCheckBoxContainer;
  private final int mCheckBoxWidth = mContext.getResources().getDimensionPixelSize(2131427360);
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
  private final int mMaxBubbleTextWidth = mContext.getResources().getDimensionPixelSize(2131427347);
  private MessageItem mMessageItem;
  private View mMessageItemLayout;
  private final int mMmsAttachmentPaddingWidth = mContext.getResources().getDimensionPixelSize(2131427336);
  private View mMmsDownloadPadding;
  private final int mMmsDownloadPaddingWidth = mContext.getResources().getDimensionPixelSize(2131427339);
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
  private final int mTimedMessagePaddingWidth = mContext.getResources().getDimensionPixelSize(2131427338);
  private Button mTimedMsgIndicator;
  private View mTimedMsgPadding;
  private List<UnderstandMessage> mUnderstandMessageList;
  private View mUnderstandPadding;
  
  static
  {
    long l1 = System.currentTimeMillis();
    Resources localResources = MmsApp.getApp().getResources();
    sDefaultThumbnailMaskDrawable = localResources.getDrawable(2130838013);
    sInThumbnailMaskDrawable = localResources.getDrawable(2130837809);
    sLastInThumbnailMaskDrawable = localResources.getDrawable(2130837812);
    sOutThumbnailMaskDrawable = localResources.getDrawable(2130837831);
    sLastOutThumbnailMaskDrawable = localResources.getDrawable(2130837840);
    sInThumbnailPressedMaskDrawable = localResources.getDrawable(2130837815);
    sLastInThumbnailPressedMaskDrawable = localResources.getDrawable(2130837817);
    sOutThumbnailPressedMaskDrawable = localResources.getDrawable(2130837843);
    sLastOutThumbnailPressedMaskDrawable = localResources.getDrawable(2130837845);
    sInThumbnailNormalMaskDrawable = localResources.getDrawable(2130837814);
    sLastInThumbnailNormalMaskDrawable = localResources.getDrawable(2130837816);
    sOutThumbnailNormalMaskDrawable = localResources.getDrawable(2130837842);
    sLastOutThumbnailNormalMaskDrawable = localResources.getDrawable(2130837844);
    sThumbnailNormalMaskDrawable = localResources.getDrawable(2130838014);
    long l2 = System.currentTimeMillis();
    Log.d("MessageListItem", "Initialize cacehed drawables of MessageListItem: " + (l2 - l1) + " ms");
  }
  
  public MessageListItem(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  private boolean IsMxConnected(String paramString, int paramInt)
  {
    return (paramString != null) && (PushSession.getInstance(mContext).isConnected(paramInt));
  }
  
  private void adjustAttachmentPreviewMargin(MessageItem paramMessageItem)
  {
    if ((mAttachmentPreview.getVisibility() != 8) && (!isThumbnail(paramMessageItem)) && ((mBodyTextView.getVisibility() != 8) || (mBodyLinearLayout.getVisibility() != 8) || (mSubjectTextView.getVisibility() != 8)))
    {
      if (mAttachmentPadding != null)
      {
        mAttachmentPadding.setVisibility(0);
        return;
      }
      RelativeLayout.LayoutParams localLayoutParams = (RelativeLayout.LayoutParams)mAttachmentPreview.getLayoutParams();
      if (paramMessageItem.isBubbleOutLayoutStyle()) {
        leftMargin = mBubbleAttachmentPreviewMargin;
      }
      for (;;)
      {
        mAttachmentPreview.setLayoutParams(localLayoutParams);
        return;
        rightMargin = mBubbleAttachmentPreviewMargin;
      }
    }
    if (mAttachmentPadding != null)
    {
      mAttachmentPadding.setVisibility(8);
      return;
    }
    paramMessageItem = (RelativeLayout.LayoutParams)mAttachmentPreview.getLayoutParams();
    leftMargin = 0;
    rightMargin = 0;
    mAttachmentPreview.setLayoutParams(paramMessageItem);
  }
  
  private void bindAttachmentView(MessageItem paramMessageItem)
  {
    if (isThumbnail(paramMessageItem))
    {
      mAttachmentPreview.setBackgroundDrawable(mThumbnailMaskDrawable, mThumbnailAlignType);
      mAttachmentPreview.setMaskDrawable(mThumbnailPressedMaskDrawable, mThumbnailNormalMaskDrawable);
      if (!isMxAudioAttachment(paramMessageItem)) {
        break label127;
      }
      if (!paramMessageItem.isBubbleLayoutStyle()) {
        break label100;
      }
      getAudioItemController().initListItem(paramMessageItem, mHandler);
    }
    label100:
    label127:
    label177:
    do
    {
      do
      {
        do
        {
          return;
          if (isVcardPreview(paramMessageItem))
          {
            mAttachmentPreview.setBackgroundDrawable(null, 0);
            break;
          }
          mAttachmentPreview.setBackgroundDrawable(sDefaultThumbnailMaskDrawable, 0);
          break;
        } while (!paramMessageItem.isBookmarkLayoutStyle());
        mAttachmentPreview.setVisibility(0);
        mAttachmentPreview.setImageResource(2130837801, true);
        return;
        if (paramMessageItem.getMmsPreviewDataTs() == 0L) {
          break label177;
        }
        mAttachmentPreview.setVisibility(0);
      } while (PreviewImageLoader.getInstance().request(paramMessageItem.getMsgId(), paramMessageItem.getMmsPreviewDataTs(), mAttachmentPreview));
      mAttachmentPreview.setImageResource(2130837803, true);
      return;
      switch (paramMessageItem.getMmsPreviewType())
      {
      }
    } while (paramMessageItem.getMx2Attachments() != null);
    mAttachmentPreview.setVisibility(0);
    mAttachmentPreview.setImageResource(2130837803, true);
    return;
    mAttachmentPreview.setVisibility(0);
    mAttachmentPreview.setImageResource(2130837801, true);
    return;
    mAttachmentPreview.setVisibility(0);
    mAttachmentPreview.setImageResource(2130837806, true);
    return;
    mAttachmentPreview.setVisibility(0);
    mAttachmentPreview.setImageResource(2130837802);
  }
  
  private void bindBackground(MessageItem paramMessageItem)
  {
    mThumbnailMaskDrawable = sDefaultThumbnailMaskDrawable;
    mThumbnailAlignType = 0;
    mThumbnailPressedMaskDrawable = null;
    mThumbnailNormalMaskDrawable = sThumbnailNormalMaskDrawable;
    int i;
    switch (paramMessageItem.getLayoutStyle())
    {
    default: 
      i = 0;
      if (((isThumbnail(paramMessageItem)) || (shouldDisableCardBubble(paramMessageItem))) && (mMessageItemLayout != null))
      {
        mMessageItemLayout.setBackground(null);
        mMessageItemLayout.setPadding(0, 0, 0, 0);
      }
      break;
    }
    for (;;)
    {
      mBackgroundRes = i;
      return;
      i = 0;
      break;
      i = 2130837808;
      mThumbnailAlignType = 1;
      mThumbnailMaskDrawable = sInThumbnailMaskDrawable;
      mThumbnailPressedMaskDrawable = sInThumbnailPressedMaskDrawable;
      mThumbnailNormalMaskDrawable = sInThumbnailNormalMaskDrawable;
      break;
      i = 2130837811;
      mThumbnailAlignType = 1;
      mThumbnailMaskDrawable = sLastInThumbnailMaskDrawable;
      mThumbnailPressedMaskDrawable = sLastInThumbnailPressedMaskDrawable;
      mThumbnailNormalMaskDrawable = sLastInThumbnailNormalMaskDrawable;
      break;
      if (paramMessageItem.getMxType() == 0) {
        if (paramMessageItem.isGroup()) {
          i = 2130837833;
        }
      }
      for (;;)
      {
        mThumbnailAlignType = 2;
        mThumbnailMaskDrawable = sOutThumbnailMaskDrawable;
        mThumbnailPressedMaskDrawable = sOutThumbnailPressedMaskDrawable;
        mThumbnailNormalMaskDrawable = sOutThumbnailNormalMaskDrawable;
        break;
        i = 2130837830;
        continue;
        if (paramMessageItem.isGroup()) {
          i = 2130837863;
        } else {
          i = 2130837860;
        }
      }
      if (paramMessageItem.getMxType() == 0) {
        if (paramMessageItem.isGroup()) {
          i = 2130837836;
        }
      }
      for (;;)
      {
        mThumbnailAlignType = 2;
        mThumbnailMaskDrawable = sLastOutThumbnailMaskDrawable;
        mThumbnailPressedMaskDrawable = sLastOutThumbnailPressedMaskDrawable;
        mThumbnailNormalMaskDrawable = sLastOutThumbnailNormalMaskDrawable;
        break;
        i = 2130837839;
        continue;
        if (paramMessageItem.isGroup()) {
          i = 2130837866;
        } else {
          i = 2130837869;
        }
      }
      if (paramMessageItem.isGroup()) {}
      for (i = 2130837821;; i = 2130837818)
      {
        mThumbnailAlignType = 2;
        mThumbnailMaskDrawable = sOutThumbnailMaskDrawable;
        mThumbnailPressedMaskDrawable = sOutThumbnailPressedMaskDrawable;
        mThumbnailNormalMaskDrawable = sOutThumbnailNormalMaskDrawable;
        break;
      }
      if (paramMessageItem.isGroup()) {}
      for (i = 2130837824;; i = 2130837827)
      {
        mThumbnailAlignType = 2;
        mThumbnailMaskDrawable = sLastOutThumbnailMaskDrawable;
        mThumbnailPressedMaskDrawable = sLastOutThumbnailPressedMaskDrawable;
        mThumbnailNormalMaskDrawable = sLastOutThumbnailNormalMaskDrawable;
        break;
      }
      if (i != 0)
      {
        mMessageItemLayout.setBackgroundResource(i);
        mThumbnailNormalMaskDrawable = null;
      }
    }
  }
  
  private void bindBodyTextForNonNotifInd(MessageItem paramMessageItem)
  {
    String str2 = paramMessageItem.getSubject();
    String str1 = null;
    Object localObject = str1;
    if (paramMessageItem.isBubbleLayoutStyle())
    {
      if ((!shouldMmsCollapse()) || (mBodySubstitution != null)) {
        break label124;
      }
      localObject = mBodyTextView;
      if (!TextUtils.isEmpty(str2)) {
        break label119;
      }
    }
    label119:
    for (int i = 3;; i = 2)
    {
      ((TextView)localObject).setMaxLines(i);
      mBodyTextView.setEllipsize(TextUtils.TruncateAt.END);
      mBodyTextView.setAutoLinkMask(0);
      localObject = str1;
      i = getMaxTextWidth(paramMessageItem);
      if ((!paramMessageItem.isGroup()) || (!paramMessageItem.isSending())) {
        break;
      }
      bindSubjectAndBody(str2, paramMessageItem.getSendingBodyInGroup(), paramMessageItem.getTextContentType(), paramMessageItem.getSendingContactMessageInGroup(), null, i, paramMessageItem.getLayoutStyle());
      return;
    }
    label124:
    mBodyTextView.setMaxHeight(Integer.MAX_VALUE);
    mBodyTextView.setEllipsize(null);
    if (MessageUtils.isMessagingTemplateAllowed(getContext())) {
      mBodyTextView.setAutoLinkMask(15);
    }
    for (;;)
    {
      localObject = mUnderstandMessageList;
      break;
      mBodyTextView.setAutoLinkMask(LINK_MASK_WITH_TIME);
    }
    if (mBodySubstitution != null) {}
    for (str1 = mBodySubstitution;; str1 = paramMessageItem.getBody())
    {
      bindSubjectAndBody(str2, str1, paramMessageItem.getTextContentType(), paramMessageItem.getContactMessage(), (List)localObject, i, paramMessageItem.getLayoutStyle());
      return;
    }
  }
  
  private void bindBodyTextForNotifInd(MessageItem paramMessageItem)
  {
    String str = mContext.getString(2131361953) + String.valueOf((paramMessageItem.getMessageSize() + 1023) / 1024) + mContext.getString(2131361812) + '\n' + paramMessageItem.getExpireTimestamp();
    int i = 0;
    if (paramMessageItem.isBubbleLayoutStyle())
    {
      if (mEditMode)
      {
        i = mContext.getResources().getDimensionPixelSize(2131427348);
        i = mMaxBubbleTextWidth - mMmsDownloadPaddingWidth - i;
      }
    }
    else
    {
      mBodyTextView.setMaxHeight(Integer.MAX_VALUE);
      mBodyTextView.setEllipsize(null);
      if (!MessageUtils.isMessagingTemplateAllowed(getContext())) {
        break label182;
      }
      mBodyTextView.setAutoLinkMask(15);
    }
    for (;;)
    {
      bindSubjectAndBody(paramMessageItem.getSubject(), str, "text/plain", null, mUnderstandMessageList, i, paramMessageItem.getLayoutStyle());
      return;
      i = mMaxBubbleTextWidth - mMmsDownloadPaddingWidth;
      break;
      label182:
      mBodyTextView.setAutoLinkMask(LINK_MASK_WITH_TIME);
    }
  }
  
  private void bindBookmark(MessageItem paramMessageItem)
  {
    bindBodyTextForNonNotifInd(paramMessageItem);
    if (mMessageItem.isMms())
    {
      bindAttachmentView(paramMessageItem);
      if ((mAttachmentPreview.getVisibility() != 8) && (mAttachmentPadding != null)) {
        mAttachmentPadding.setVisibility(0);
      }
    }
    bindTimestamp(paramMessageItem);
  }
  
  private void bindCardBody(ContactMessage.ContactRecord paramContactRecord, boolean paramBoolean)
  {
    LinearLayout localLinearLayout1 = (LinearLayout)inflate(mContext, 2130968593, null);
    int i;
    int j;
    Object localObject;
    ContactParser localContactParser;
    if (!shouldDisableCardBubble(mMessageItem))
    {
      i = 2130837555;
      j = (int)getResources().getDimension(2131427489);
      localLinearLayout1.setBackgroundResource(i);
      localObject = new RelativeLayout.LayoutParams(-2, -2);
      mBodyLinearLayout.addView(localLinearLayout1, (ViewGroup.LayoutParams)localObject);
      localObject = (TextView)localLinearLayout1.findViewById(2131820579);
      localLinearLayout1 = (LinearLayout)localLinearLayout1.findViewById(2131820581);
      localLinearLayout1.setMinimumWidth(j);
      localContactParser = ContactParser.getContactParser(mContext);
      paramContactRecord = paramContactRecord.getContactRecord().iterator();
    }
    for (;;)
    {
      if (!paramContactRecord.hasNext()) {
        return;
      }
      Pair localPair = (Pair)paramContactRecord.next();
      if ("type_name".equals(localContactParser.getTypeLabel(mContext, (String)first)))
      {
        ((TextView)localObject).setText((CharSequence)second);
        continue;
        if (mMessageItem.isGroup())
        {
          i = 2130837562;
          label200:
          if (!paramBoolean) {
            break label220;
          }
        }
        for (;;)
        {
          j = mMaxBubbleTextWidth;
          break;
          i = 2130837561;
          break label200;
          label220:
          i = 2130837558;
        }
      }
      else
      {
        LinearLayout localLinearLayout2 = (LinearLayout)LayoutInflater.from(mContext).inflate(2130968594, localLinearLayout1, false);
        TextView localTextView1 = (TextView)localLinearLayout2.findViewById(2131820582);
        TextView localTextView2 = (TextView)localLinearLayout2.findViewById(2131820583);
        localTextView1.setText(String.format("[%s]", new Object[] { localContactParser.getTypeLabel(getContext(), (String)first) }));
        localTextView2.setText((CharSequence)second);
        localLinearLayout1.addView(localLinearLayout2);
      }
    }
  }
  
  private void bindCheckStatus(MessageItem paramMessageItem, boolean paramBoolean1, boolean paramBoolean2)
  {
    if (mEditMode)
    {
      if (mCheckBoxContainer == null)
      {
        paramMessageItem = findViewById(2131820773);
        paramMessageItem.setVisibility(0);
        mCheckBoxContainer = paramMessageItem;
        mCheckBox = ((CheckBox)findViewById(16908289));
      }
      mCheckBoxContainer.setVisibility(0);
      if (paramBoolean2) {
        mCheckBox.setChecked(true);
      }
    }
    while (mCheckBoxContainer == null)
    {
      return;
      mCheckBox.setChecked(false);
      return;
    }
    mCheckBoxContainer.setVisibility(8);
  }
  
  private void bindCommonIndicators(MessageItem paramMessageItem)
  {
    bindMxType(paramMessageItem);
    bindTimestamp(paramMessageItem);
    bindDeliveryInfo(paramMessageItem);
    bindResentButton(paramMessageItem);
    updateStatusDivider();
    bindFavouriteMark(paramMessageItem);
  }
  
  private void bindDeliveryInfo(MessageItem paramMessageItem)
  {
    int j = 1;
    if (mDeliverStatus == null) {}
    Object localObject2;
    do
    {
      return;
      localObject2 = null;
    } while (!paramMessageItem.isOutMessage());
    if (paramMessageItem.isFailedMessage())
    {
      if (mEditMode)
      {
        mDeliverStatus.setVisibility(8);
        return;
      }
      if (paramMessageItem.isGroup())
      {
        mDeliverStatus.setVisibility(4);
        return;
      }
      if (paramMessageItem.isReadOnly())
      {
        paramMessageItem = getContext().getString(2131362066);
        mDeliverStatus.setText(paramMessageItem);
        mDeliverStatus.setVisibility(0);
        return;
      }
      mDeliverStatus.setVisibility(8);
      return;
    }
    int k = paramMessageItem.getDeliverReportMode();
    if (k != 0) {
      if (paramMessageItem.isSms()) {
        if (Build.IS_CM_CUSTOMIZATION_TEST) {
          if (!MSimUtils.isMSim()) {
            break label812;
          }
        }
      }
    }
    label240:
    label265:
    label304:
    label396:
    label426:
    label659:
    label734:
    label799:
    label802:
    label812:
    for (int i = MSimUtils.getSlotIdBySimInfoId(paramMessageItem.getSimId());; i = 0)
    {
      if (((k & 0x10) == 16) && (i == 0))
      {
        if (j == 0) {
          break label304;
        }
        if (paramMessageItem.getMxType() != 0) {
          break label265;
        }
        if (paramMessageItem.getDeliveryStatus() != MessageItem.DeliveryStatus.RECEIVED) {
          break label240;
        }
      }
      for (Object localObject1 = getContext().getString(2131362065);; localObject1 = getContext().getString(2131362067))
      {
        if (!mEditMode) {
          break label734;
        }
        mDeliverStatus.setVisibility(8);
        return;
        if (((k & 0x20) == 32) && (i == 1)) {
          break;
        }
        do
        {
          j = 0;
          break;
        } while ((k & 0x10) != 16);
        break;
        if (paramMessageItem.getDeliveryStatus() != MessageItem.DeliveryStatus.PENDING) {
          break label304;
        }
      }
      switch (paramMessageItem.getMxType())
      {
      }
      do
      {
        localObject1 = null;
        break;
        localObject1 = getContext().getString(2131362067);
        break;
        localObject1 = getContext().getString(2131362350);
        break;
        localObject1 = getContext().getString(2131362065);
        break;
      } while (!paramMessageItem.isMms());
      if (MSimUtils.isMSim()) {}
      for (j = MSimUtils.getSlotIdBySimInfoId(paramMessageItem.getSimId());; j = 0)
      {
        if (Build.IS_CM_CUSTOMIZATION_TEST) {
          if (((k & 0x4) == 4) && (j == 0)) {
            i = 1;
          }
        }
        for (;;)
        {
          localObject1 = localObject2;
          if (i != 0)
          {
            if (paramMessageItem.getDeliveryStatus() == MessageItem.DeliveryStatus.RECEIVED) {
              localObject1 = getContext().getString(2131362065);
            }
          }
          else
          {
            if (((k & 0x1) != 1) || (j != 0)) {
              break label659;
            }
            if ((paramMessageItem.getDeliveryStatus() != MessageItem.DeliveryStatus.RECEIVED) || (!paramMessageItem.isReadReport())) {
              break label799;
            }
            localObject1 = getContext().getString(2131362068);
          }
          for (;;)
          {
            switch (paramMessageItem.getMxType())
            {
            default: 
              break;
            case 1: 
              localObject1 = getContext().getString(2131362067);
              break;
              if (((k & 0x8) != 8) || (j != 1)) {
                break label802;
              }
              i = 1;
              break label396;
              if ((k & 0x4) != 4) {
                break label802;
              }
              i = 1;
              break label396;
              if (paramMessageItem.getDeliveryStatus() == MessageItem.DeliveryStatus.REJECTED)
              {
                if (Build.IS_CM_CUSTOMIZATION_TEST)
                {
                  localObject1 = getContext().getString(2131362065);
                  break label426;
                }
                localObject1 = getContext().getString(2131362069);
                break label426;
              }
              if ((Build.IS_CM_CUSTOMIZATION_TEST) && (paramMessageItem.getDeliveryStatus() == MessageItem.DeliveryStatus.EXPIRED))
              {
                localObject1 = getContext().getString(2131362070);
                break label426;
              }
              localObject1 = localObject2;
              if (paramMessageItem.getBoxId() != 4) {
                break label426;
              }
              localObject1 = getContext().getString(2131362067);
              break label426;
              if (((k & 0x2) == 2) && (j == 1) && (paramMessageItem.getDeliveryStatus() == MessageItem.DeliveryStatus.RECEIVED) && (paramMessageItem.isReadReport())) {
                localObject1 = getContext().getString(2131362068);
              }
              break;
            case 16: 
              localObject1 = getContext().getString(2131362350);
              break;
            case 17: 
              localObject1 = getContext().getString(2131362065);
              break;
              if ((paramMessageItem.isGroup()) && (paramMessageItem.isSms()))
              {
                mDeliverStatus.setVisibility(4);
                return;
              }
              if (localObject1 != null)
              {
                mDeliverStatus.setVisibility(0);
                mDeliverStatus.setText((CharSequence)localObject1);
                return;
              }
              mDeliverStatus.setVisibility(8);
              return;
              mDeliverStatus.setVisibility(4);
              return;
            }
          }
          i = 0;
        }
      }
    }
  }
  
  private void bindDownloadButtonForNonNotifInd(MessageItem paramMessageItem)
  {
    if (mDownloadView != null) {
      mDownloadView.setVisibility(8);
    }
    if (mMmsDownloadPadding != null) {
      mMmsDownloadPadding.setVisibility(8);
    }
  }
  
  private void bindDownloadButtonForNotifInd(final MessageItem paramMessageItem)
  {
    int i = paramMessageItem.getMmsStatus() & 0xFFFFFFFB;
    if (i == 0) {
      if (mDownloadView != null) {
        mDownloadView.setVisibility(8);
      }
    }
    ViewStub localViewStub;
    do
    {
      return;
      if (mDownloadView != null) {
        break;
      }
      localViewStub = (ViewStub)findViewById(2131820719);
    } while (localViewStub == null);
    mDownloadView = localViewStub.inflate();
    mDownloadButton = ((Button)findViewById(2131820721));
    mDownloadPending = findViewById(2131820720);
    mDownloadProgress = ((CircleProgressBar)findViewById(2131820722));
    if (mDownloadProgress != null)
    {
      mDownloadProgress.setDrawablesForLevels(new int[] { 2130837654 }, new int[] { 2130837655 }, null);
      mDownloadProgress.setMax(100);
    }
    mDownloadView.setVisibility(0);
    if (mMmsDownloadPadding != null) {
      mMmsDownloadPadding.setVisibility(0);
    }
    switch (i)
    {
    default: 
      mDownloadPending.clearAnimation();
      mDownloadPending.setVisibility(8);
      mDownloadProgress.setVisibility(8);
      mDownloadButton.setVisibility(0);
      mDownloadButton.setFocusable(false);
      if (mEditMode)
      {
        mDownloadButton.setEnabled(false);
        return;
      }
      break;
    case 129: 
      if (Transaction.getCurrentTransactionMsgId() == paramMessageItem.getMsgId())
      {
        mDownloadProgress.setVisibility(0);
        mDownloadPending.clearAnimation();
        mDownloadPending.setVisibility(8);
        mDownloadProgress.setProgress(Transaction.getCurrentTransactionProgress());
      }
      for (;;)
      {
        mDownloadButton.setVisibility(8);
        return;
        mDownloadProgress.setVisibility(8);
        mDownloadPending.setVisibility(0);
        mDownloadPending.startAnimation(AnimationUtils.loadAnimation(mContext, 2131034119));
      }
    }
    mDownloadButton.setEnabled(true);
    mDownloadButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (paramMessageItem.isMiXin())
        {
          MxMmsTransactionService.startRetrieveMms(mContext, paramMessageItem.getMessageUri());
          return;
        }
        paramAnonymousView = new Intent(mContext, TransactionService.class);
        paramAnonymousView.putExtra("uri", paramMessageItem.getMessageUri().toString());
        paramAnonymousView.putExtra("type", 1);
        mContext.startService(paramAnonymousView);
      }
    });
  }
  
  private void bindFavouriteMark(MessageItem paramMessageItem)
  {
    if ((!paramMessageItem.isGroup()) && (paramMessageItem.isLocked()) && (!mIsPrivate)) {
      if (isThumbnail(paramMessageItem))
      {
        if (mAttachmentFavouriteView != null) {
          mAttachmentFavouriteView.setVisibility(0);
        }
        if (mFavouriteMark != null) {
          mFavouriteMark.setVisibility(8);
        }
      }
    }
    do
    {
      do
      {
        return;
        if (mFavouriteMark != null) {
          mFavouriteMark.setVisibility(0);
        }
      } while (mAttachmentFavouriteView == null);
      mAttachmentFavouriteView.setVisibility(8);
      return;
      if (mFavouriteMark != null) {
        mFavouriteMark.setVisibility(8);
      }
    } while (mAttachmentFavouriteView == null);
    mAttachmentFavouriteView.setVisibility(8);
  }
  
  private void bindFileShareMessageBody()
  {
    LinearLayout localLinearLayout = (LinearLayout)inflate(mContext, 2130968618, null);
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(-2, -2);
    mFileShareLayout.addView(localLinearLayout, localLayoutParams);
    ((TextView)localLinearLayout.findViewById(2131820649)).setText(mContext.getString(2131362260));
    ((ImageView)localLinearLayout.findViewById(2131820647)).setVisibility(0);
    localLinearLayout.findViewById(2131820648).setVisibility(0);
  }
  
  private void bindGroupMessageCancelButton(MessageItem paramMessageItem)
  {
    final long l1 = paramMessageItem.getThreadId();
    long l2 = paramMessageItem.getDate();
    mGroupSendCancelButton.setFocusable(false);
    if (mEditMode)
    {
      mGroupSendCancelButton.setEnabled(false);
      return;
    }
    mGroupSendCancelButton.setEnabled(true);
    mGroupSendCancelButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        new Thread()
        {
          public void run()
          {
            val$context.getContentResolver().delete(Telephony.Sms.CONTENT_URI, "thread_id=? AND date=? AND type=5", new String[] { String.valueOf(val$thread_id), String.valueOf(val$date) });
          }
        }.start();
      }
    });
  }
  
  private void bindGroupMessageFailed(MessageItem paramMessageItem)
  {
    if (mGroupSendFailedRoot == null) {
      return;
    }
    if ((paramMessageItem.isSms()) && (paramMessageItem.isGroup()) && (paramMessageItem.isFailedMessage()) && (!paramMessageItem.isSending()))
    {
      mGroupSendFailedRoot.setVisibility(0);
      mGroupSendFailedTitle.setText(mContext.getString(2131362074, new Object[] { Integer.valueOf(paramMessageItem.failedMsgInGroup().size()) }));
      bindGroupMessageFailedItems(paramMessageItem);
      bindGroupMessageSendFailedButton(paramMessageItem);
      bindGroupMessageCancelButton(paramMessageItem);
      return;
    }
    mGroupSendFailedRoot.setVisibility(8);
  }
  
  private void bindGroupMessageFailedItems(final MessageItem paramMessageItem)
  {
    Object localObject1 = paramMessageItem.failedMsgInGroup();
    ViewGroup localViewGroup = (ViewGroup)findViewById(2131820757);
    localViewGroup.removeAllViews();
    localObject1 = ((ArrayList)localObject1).iterator();
    if (((Iterator)localObject1).hasNext())
    {
      final Object localObject2 = (MessageItem.FailedItem)((Iterator)localObject1).next();
      View localView = inflate(mContext, 2130968653, null);
      TextView localTextView1 = (TextView)localView.findViewById(2131820752);
      TextView localTextView2 = (TextView)localView.findViewById(2131820753);
      CheckBox localCheckBox = (CheckBox)localView.findViewById(2131820754);
      localCheckBox.setFocusable(false);
      if (mEditMode)
      {
        localCheckBox.setEnabled(false);
        label116:
        localObject2 = Contact.get(number);
        localTextView1.setText(((Contact)localObject2).getName());
        localTextView2.setText(((Contact)localObject2).getNumber());
        if (!((Contact)localObject2).getName().equals(((Contact)localObject2).getNumber())) {
          break label202;
        }
        localTextView2.setVisibility(4);
      }
      for (;;)
      {
        localViewGroup.addView(localView);
        break;
        localCheckBox.setEnabled(true);
        localCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
          public void onCheckedChanged(CompoundButton paramAnonymousCompoundButton, boolean paramAnonymousBoolean)
          {
            boolean bool = true;
            if (paramAnonymousBoolean)
            {
              paramMessageItem.addToResend(localObject2messageUri);
              int i = paramMessageItem.resendMsgInGroup().size();
              mGroupSendFailedButton.setText(mContext.getString(2131362072, new Object[] { Integer.valueOf(i) }));
              paramAnonymousCompoundButton = mGroupSendFailedButton;
              if (i <= 0) {
                break label103;
              }
            }
            label103:
            for (paramAnonymousBoolean = bool;; paramAnonymousBoolean = false)
            {
              paramAnonymousCompoundButton.setEnabled(paramAnonymousBoolean);
              return;
              paramMessageItem.removeFromResend(localObject2messageUri);
              break;
            }
          }
        });
        break label116;
        label202:
        localTextView2.setVisibility(0);
      }
    }
  }
  
  private void bindGroupMessageSendFailedButton(final MessageItem paramMessageItem)
  {
    mGroupSendFailedButton.setText(mContext.getString(2131362072, new Object[] { Integer.valueOf(paramMessageItem.resendMsgInGroup().size()) }));
    mGroupSendFailedButton.setFocusable(false);
    if (mEditMode)
    {
      mGroupSendFailedButton.setEnabled(false);
      return;
    }
    mGroupSendFailedButton.setEnabled(true);
    mGroupSendFailedButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (mHandler != null)
        {
          paramAnonymousView = Message.obtain(mHandler, 5);
          obj = Long.valueOf(paramMessageItem.getMsgId());
          arg1 = Integer.valueOf(MSimUtils.getSlotIdBySimInfoId(paramMessageItem.getSimId())).intValue();
          paramAnonymousView.sendToTarget();
        }
      }
    });
  }
  
  private void bindGroupSendCount(MessageItem paramMessageItem)
  {
    if (mGroupSendViewController != null) {
      mGroupSendViewController.mGroupSendCountTextView.setVisibility(8);
    }
    if (mGroupSendIcon != null) {
      mGroupSendIcon.setVisibility(8);
    }
    if ((paramMessageItem.isGroup()) && (paramMessageItem.isSending()))
    {
      int i = paramMessageItem.sentMsgInGroup() + 1;
      int j = paramMessageItem.totalMsgInGroup();
      getGroupSendViewStubControllermGroupSendCountTextView.setText(mContext.getResources().getString(2131362064, new Object[] { Integer.valueOf(i), Integer.valueOf(j) }));
      getGroupSendViewStubControllermGroupSendCountTextView.setVisibility(0);
      if (mGroupSendIcon == null) {
        mGroupSendIcon = ((CircleProgressBar)((ViewStub)findViewById(2131820743)).inflate());
      }
      if (mGroupSendIcon != null) {
        mGroupSendIcon.setDrawablesForLevels(new int[] { 2130837654 }, new int[] { 2130837655 }, null);
      }
      if (mGroupSendIcon != null)
      {
        mGroupSendIcon.setVisibility(0);
        mGroupSendIcon.setMax(j);
        mGroupSendIcon.setProgress(i - 1);
      }
    }
  }
  
  private void bindMxType(MessageItem paramMessageItem)
  {
    int i = 0;
    if (paramMessageItem.getMxTypeName() != null)
    {
      str = paramMessageItem.getMxTypeName();
      if (mMxTypeIndicatorPanel != null) {
        mMxTypeIndicatorPanel.setVisibility(0);
      }
      if (mMxTypeView != null) {
        mMxTypeView.setText(str);
      }
      if (mMxTypeGuideView != null)
      {
        if (!paramMessageItem.isMiXin()) {
          break label67;
        }
        mMxTypeGuideView.setVisibility(i);
      }
    }
    label67:
    while (mMxTypeIndicatorPanel == null) {
      for (;;)
      {
        String str;
        return;
        i = 8;
      }
    }
    mMxTypeIndicatorPanel.setVisibility(8);
  }
  
  private void bindNonCardBody(Object paramObject, int paramInt, boolean paramBoolean)
  {
    TextView localTextView = (TextView)inflate(mContext, 2130968592, null);
    localTextView.setTextColor(mBodyTextView.getTextColors());
    Resources localResources = getResources();
    int i;
    if (paramBoolean)
    {
      i = 2131296354;
      localTextView.setLinkTextColor(localResources.getColorStateList(i));
      if (paramInt > 0) {
        localTextView.setMaxWidth(paramInt);
      }
      if (!MessageUtils.isMessagingTemplateAllowed(getContext())) {
        break label160;
      }
      localTextView.setAutoLinkMask(15);
    }
    for (;;)
    {
      localTextView.setLinksClickable(false);
      localTextView.setText(paramObject.toString());
      paramObject = new RelativeLayout.LayoutParams(-2, -2);
      mBodyLinearLayout.addView(localTextView, (ViewGroup.LayoutParams)paramObject);
      mBodyTextViewList.add(localTextView);
      if (shouldDisableCardBubble(mMessageItem)) {
        localTextView.setBackgroundResource(mBackgroundRes);
      }
      return;
      i = 2131296353;
      break;
      label160:
      localTextView.setAutoLinkMask(LINK_MASK_WITH_TIME);
    }
  }
  
  private void bindNonNotifInd(MessageItem paramMessageItem)
  {
    bindBodyTextForNonNotifInd(paramMessageItem);
    bindDownloadButtonForNonNotifInd(paramMessageItem);
    if ((paramMessageItem.isBubbleOutLayoutStyle()) && (paramMessageItem.isSms()) && ((paramMessageItem.isReadOnly()) || (paramMessageItem.isGroup()) || (paramMessageItem.isReferenceGroup())))
    {
      bindSendToTitle(paramMessageItem);
      bindGroupSendCount(paramMessageItem);
      boolean bool = shouldDisableCardBubble(paramMessageItem);
      getGroupSendViewStubController().refreshSendView(bool);
    }
    bindGroupMessageFailed(paramMessageItem);
    bindTimedMessage(paramMessageItem);
    if (mMessageItem.isMms())
    {
      bindAttachmentView(paramMessageItem);
      if (paramMessageItem.isBubbleLayoutStyle()) {
        adjustAttachmentPreviewMargin(paramMessageItem);
      }
    }
  }
  
  private void bindNotifInd(MessageItem paramMessageItem)
  {
    bindBodyTextForNotifInd(paramMessageItem);
    bindDownloadButtonForNotifInd(paramMessageItem);
    bindTimedMessage(paramMessageItem);
  }
  
  private void bindResentButton(final MessageItem paramMessageItem)
  {
    if (mResentButton == null) {
      return;
    }
    if ((paramMessageItem.isOutMessage()) && (paramMessageItem.isFailedMessage()) && (!paramMessageItem.isReadOnly()) && (!paramMessageItem.isGroup()))
    {
      mResentButton.setFocusable(false);
      mResentButton.setVisibility(0);
      if ((mEditMode) || (MSimUtils.getInsertedSimCount() <= 0))
      {
        mResentButton.setEnabled(false);
        return;
      }
      mResentButton.setEnabled(true);
      mResentButton.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          boolean bool;
          long l;
          if (mHandler != null)
          {
            bool = paramMessageItem.isSms();
            l = paramMessageItem.getMsgId();
            if (B2cMessageUtils.isB2cNumber(Contact.get(paramMessageItem.getAddress()))) {
              MessageListItem.this.handleB2cMessageResend(paramAnonymousView, l, bool, paramMessageItem.getSimId());
            }
          }
          else
          {
            return;
          }
          String str = MessageListItem.this.getMidViaAddress(paramMessageItem.getAddress(), bool);
          if (MSimUtils.isMSimInserted())
          {
            MessageListItem.this.createSimPickerDialog(paramAnonymousView, l, bool, str);
            return;
          }
          int j = MSimUtils.getInsertedSlotId();
          if (MSimUtils.getSlotIdBySimInfoId(paramMessageItem.getSimId()) < 0) {
            MessageListItem.this.updateSimId(l, j, bool);
          }
          if (MessageListItem.this.IsMxConnected(str, j))
          {
            MessageListItem.this.createResentDialog(paramAnonymousView, l, bool, j);
            return;
          }
          paramAnonymousView = mHandler;
          if (bool) {}
          for (int i = 4;; i = 3)
          {
            paramAnonymousView = Message.obtain(paramAnonymousView, i);
            obj = Long.valueOf(l);
            arg1 = Integer.valueOf(j).intValue();
            paramAnonymousView.sendToTarget();
            return;
          }
        }
      });
      return;
    }
    mResentButton.setVisibility(8);
  }
  
  private void bindSendToTitle(MessageItem paramMessageItem)
  {
    if (paramMessageItem.isReadOnly())
    {
      getGroupSendViewStubControllermGroupSendToTitle.setVisibility(0);
      getGroupSendViewStubControllermGroupSendToTextView.setText(paramMessageItem.getContactName());
      getGroupSendViewStubControllermGroupSendToTextView.setVisibility(0);
    }
    do
    {
      return;
      if ((paramMessageItem.isGroup()) && (!paramMessageItem.isTimed()))
      {
        if (paramMessageItem.isSending())
        {
          getGroupSendViewStubControllermGroupSendToTitle.setVisibility(0);
          getGroupSendViewStubControllermGroupSendToTextView.setText(paramMessageItem.getContactName());
        }
        for (;;)
        {
          getGroupSendViewStubControllermGroupSendToTextView.setVisibility(0);
          return;
          getGroupSendViewStubControllermGroupSendToTitle.setVisibility(8);
          getGroupSendViewStubControllermGroupSendToTextView.setText(mContext.getResources().getString(2131362063, new Object[] { Integer.valueOf(paramMessageItem.totalMsgInGroup() - paramMessageItem.failedMsgInGroup().size()) }));
        }
      }
    } while (!paramMessageItem.isReferenceGroup());
    getGroupSendViewStubControllermGroupSendToTitle.setVisibility(8);
    getGroupSendViewStubControllermGroupSendToTextView.setText(2131362155);
    getGroupSendViewStubControllermGroupSendToTextView.setVisibility(0);
  }
  
  private void bindSlotId(MessageItem paramMessageItem)
  {
    if (!MSimUtils.isMSimInserted())
    {
      mSlotIdView.setVisibility(8);
      return;
    }
    switch (MSimUtils.getSlotIdBySimInfoId(paramMessageItem.getSimId()))
    {
    default: 
      mSlotIdView.setVisibility(8);
      return;
    case 0: 
      mSlotIdView.setVisibility(0);
      mSlotIdView.setImageResource(2130837970);
      return;
    }
    mSlotIdView.setVisibility(0);
    mSlotIdView.setImageResource(2130837971);
  }
  
  private void bindSubjectAndBody(String paramString1, String paramString2, String paramString3, ContactMessage paramContactMessage, List<UnderstandMessage> paramList, int paramInt1, int paramInt2)
  {
    if (TextUtils.isEmpty(paramString1))
    {
      mSubjectTextView.setVisibility(8);
      mBodyTextViewList.clear();
      mBodyLinearLayout.removeAllViews();
      paramString1 = MessageUtils.getFileShareMessage(paramString2);
      if ((paramString1 == null) || (paramString1.isEmpty()) || (mFileShareLayout == null)) {
        break label140;
      }
      mBodyTextView.setVisibility(8);
      if (mBodyLinearLayout != null) {
        mBodyLinearLayout.setVisibility(8);
      }
      mFileShareLayout.setVisibility(0);
      mFileShareLayout.removeAllViews();
      bindFileShareMessageBody();
    }
    for (;;)
    {
      return;
      mSubjectTextView.setVisibility(0);
      if (paramInt1 > 0) {
        mSubjectTextView.setMaxWidth(paramInt1);
      }
      MessageUtils.showTextWithHighlight(mSubjectTextView, paramString1, mHighlight, 2131689527);
      break;
      label140:
      if ((paramContactMessage == null) || (mBodyLinearLayout == null)) {
        break label236;
      }
      mBodyTextView.setVisibility(8);
      mBodyLinearLayout.setVisibility(0);
      paramString1 = paramContactMessage.split();
      boolean bool = isCardBodyOutLayoutStyle(paramInt2);
      paramString1 = paramString1.iterator();
      while (paramString1.hasNext())
      {
        paramString2 = paramString1.next();
        if (!(paramString2 instanceof ContactMessage.ContactRecord)) {
          bindNonCardBody(paramString2, paramInt1, bool);
        } else {
          bindCardBody((ContactMessage.ContactRecord)paramString2, bool);
        }
      }
    }
    label236:
    if (mBodyLinearLayout != null) {
      mBodyLinearLayout.setVisibility(8);
    }
    if (TextUtils.isEmpty(paramString2))
    {
      mBodyTextView.setVisibility(8);
      return;
    }
    mBodyTextView.setVisibility(0);
    if (paramInt1 > 0) {
      mBodyTextView.setMaxWidth(paramInt1);
    }
    if (paramList != null)
    {
      if (getMessageItem().isOutMessage()) {}
      for (paramInt1 = 2131689519;; paramInt1 = 2131689517)
      {
        UnderstandFactory.showTextWithUnderstand(mBodyTextView, paramString2, paramList, mHighlight, paramInt1, 2131689520);
        return;
      }
    }
    if ("text/html".equals(paramString3))
    {
      mBodyTextView.setText(Html.fromHtml(paramString2));
      return;
    }
    MessageUtils.showTextWithHighlight(mBodyTextView, paramString2, mHighlight, 2131689520);
  }
  
  private void bindTimedMessage(final MessageItem paramMessageItem)
  {
    if ((!paramMessageItem.isReadOnly()) && (!paramMessageItem.isReferenceGroup()) && (paramMessageItem.isTimed()))
    {
      if (mTimedMsgIndicator == null)
      {
        ViewStub localViewStub = (ViewStub)findViewById(2131820749);
        if (localViewStub != null) {
          mTimedMsgIndicator = ((Button)localViewStub.inflate());
        }
      }
      if (mTimedMsgIndicator != null)
      {
        mTimedMsgIndicator.setVisibility(0);
        if (paramMessageItem.isGroup())
        {
          mTimedMsgIndicator.setBackgroundResource(2130838017);
          mTimedMsgIndicator.setFocusable(false);
          if (mTimedMsgPadding != null) {
            mTimedMsgPadding.setVisibility(0);
          }
          if (!mEditMode) {
            break label138;
          }
          mTimedMsgIndicator.setEnabled(false);
        }
      }
    }
    label138:
    do
    {
      do
      {
        return;
        mTimedMsgIndicator.setBackgroundResource(2130838015);
        break;
        mTimedMsgIndicator.setEnabled(true);
        mTimedMsgIndicator.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            if ((mContext instanceof ConversationBase)) {
              ((ConversationBase)mContext).setSendTimeForSpecifiedMessage(paramMessageItem);
            }
          }
        });
        return;
        mTimedMsgIndicator.setVisibility(8);
      } while (mTimedMsgPadding == null);
      mTimedMsgPadding.setVisibility(8);
      return;
      if (mTimedMsgIndicator != null) {
        mTimedMsgIndicator.setVisibility(8);
      }
    } while (mTimedMsgPadding == null);
    mTimedMsgPadding.setVisibility(8);
  }
  
  private void bindTimestamp(MessageItem paramMessageItem)
  {
    long l = paramMessageItem.getDisplayDate();
    Object localObject2;
    Object localObject1;
    TextView localTextView;
    if (paramMessageItem.isListLayoutStyle())
    {
      localObject2 = MessageUtils.getRelativeTimeStamp(l);
      localObject1 = localObject2;
      if (localObject2 != null)
      {
        localObject1 = localObject2;
        if (paramMessageItem.isTimed()) {
          localObject1 = getResources().getString(2131362154, new Object[] { localObject2 });
        }
      }
      if (mSenderTextView != null)
      {
        if ((paramMessageItem.isGroup()) || (paramMessageItem.isReadOnly())) {
          mSenderTextView.setVisibility(8);
        }
      }
      else
      {
        if (mDateTextView != null)
        {
          localTextView = mDateTextView;
          localObject2 = localObject1;
          if (localObject1 == null) {
            localObject2 = "";
          }
          localTextView.setText((CharSequence)localObject2);
        }
        if (mSlotIdView != null) {
          bindSlotId(paramMessageItem);
        }
      }
    }
    do
    {
      return;
      mSenderTextView.setVisibility(0);
      mSenderTextView.setText(paramMessageItem.getContactName());
      break;
      if (paramMessageItem.isBubbleLayoutStyle())
      {
        localObject1 = null;
        if (paramMessageItem.getShowTimeStamp()) {
          localObject1 = MessageUtils.getPreciseTimeStamp(l, true, true);
        }
        localObject2 = localObject1;
        if (localObject1 != null)
        {
          localObject2 = localObject1;
          if (paramMessageItem.isTimed()) {
            localObject2 = getResources().getString(2131362154, new Object[] { localObject1 });
          }
        }
        if (localObject2 != null)
        {
          if (mDateTextView != null)
          {
            mDateTextView.setText((CharSequence)localObject2);
            mDateTextView.setVisibility(0);
          }
          if (mSlotIdView != null) {
            bindSlotId(paramMessageItem);
          }
        }
        while ((mDateIndicatorPanel != null) && (mDateTextView.getVisibility() == 8) && (mSlotIdView.getVisibility() == 8))
        {
          mDateIndicatorPanel.setVisibility(8);
          return;
          if (mDateTextView != null)
          {
            mDateTextView.setVisibility(8);
            mSlotIdView.setVisibility(8);
          }
        }
        mDateIndicatorPanel.setVisibility(0);
        return;
      }
      localObject2 = MessageUtils.getRelativeTimeStamp(l);
      if (mSenderTextView != null) {
        mSenderTextView.setText(mContext.getString(2131362102, new Object[] { paramMessageItem.getContactName() }));
      }
      if (mDateTextView != null)
      {
        localTextView = mDateTextView;
        localObject1 = localObject2;
        if (localObject2 == null) {
          localObject1 = "";
        }
        localTextView.setText((CharSequence)localObject1);
      }
    } while (mSlotIdView == null);
    bindSlotId(paramMessageItem);
  }
  
  private void bindUnderstandButton(MessageItem paramMessageItem)
  {
    if (!MessageUtils.isMessagingTemplateAllowed(getContext()))
    {
      hideUnderstandButton();
      return;
    }
    UnderstandMessage localUnderstandMessage = getUnderstandMessageWithButton(mUnderstandMessageList);
    if (localUnderstandMessage != null)
    {
      if (mBtnRegionLayout == null)
      {
        ViewStub localViewStub = (ViewStub)findViewById(2131820727);
        if (localViewStub != null)
        {
          localViewStub.inflate();
          mBtnRegionLayout = ((LinearLayout)findViewById(2131820728));
          mFirstButton = ((TextView)findViewById(2131820736));
          mSecondButton = ((TextView)findViewById(2131820738));
          mThirdButton = ((TextView)findViewById(2131820740));
          mDividerV1 = findViewById(2131820737);
          mDividerV2 = findViewById(2131820739);
        }
      }
      if (mBtnRegionLayout != null)
      {
        int i = UnderstandFactory.getButtonNumber(mActionID);
        if (i > 0) {
          MiStatSdkHelper.recordUnderstandButtonShown(mActionID);
        }
        switch (i)
        {
        default: 
          mBtnRegionLayout.setVisibility(8);
          return;
        case 1: 
          setUnderstandButton(paramMessageItem, mFirstButton, localUnderstandMessage, 0);
          if (mDividerV1 != null) {
            mDividerV1.setVisibility(8);
          }
          if (mSecondButton != null)
          {
            mSecondButton.setVisibility(8);
            mSecondButton.setOnClickListener(null);
          }
          if (mDividerV2 != null) {
            mDividerV2.setVisibility(8);
          }
          if (mThirdButton != null)
          {
            mThirdButton.setVisibility(8);
            mThirdButton.setOnClickListener(null);
          }
          break;
        }
      }
      for (;;)
      {
        mBtnRegionLayout.setVisibility(0);
        if (mUnderstandPadding != null) {
          mUnderstandPadding.setVisibility(0);
        }
        if ((mFavouriteMark == null) || (mFavouriteMark.getVisibility() != 0)) {
          break;
        }
        paramMessageItem = (RelativeLayout.LayoutParams)mFavouriteMark.getLayoutParams();
        paramMessageItem.addRule(2, 2131820728);
        paramMessageItem.removeRule(8);
        mFavouriteMark.setLayoutParams(paramMessageItem);
        return;
        setUnderstandButton(paramMessageItem, mFirstButton, localUnderstandMessage, 0);
        if (mDividerV1 != null) {
          mDividerV1.setVisibility(0);
        }
        setUnderstandButton(paramMessageItem, mSecondButton, localUnderstandMessage, 1);
        if (mDividerV2 != null) {
          mDividerV2.setVisibility(8);
        }
        if (mThirdButton != null)
        {
          mThirdButton.setVisibility(8);
          mThirdButton.setOnClickListener(null);
          continue;
          setUnderstandButton(paramMessageItem, mFirstButton, localUnderstandMessage, 0);
          if (mDividerV1 != null) {
            mDividerV1.setVisibility(0);
          }
          setUnderstandButton(paramMessageItem, mSecondButton, localUnderstandMessage, 1);
          if (mDividerV2 != null) {
            mDividerV2.setVisibility(0);
          }
          setUnderstandButton(paramMessageItem, mThirdButton, localUnderstandMessage, 2);
        }
      }
    }
    hideUnderstandButton();
  }
  
  private void createResentDialog(View paramView, final long paramLong, final boolean paramBoolean, int paramInt)
  {
    if (paramBoolean) {}
    for (int i = 2131230728;; i = 2131230729)
    {
      new AlertDialog.Builder(paramView.getContext()).setTitle(2131362071).setItems(i, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          if (paramAnonymousInt == 0)
          {
            paramAnonymousDialogInterface = mHandler;
            if (paramBoolean)
            {
              paramAnonymousInt = 4;
              paramAnonymousDialogInterface = Message.obtain(paramAnonymousDialogInterface, paramAnonymousInt);
              obj = Long.valueOf(paramLong);
              arg1 = Integer.valueOf(val$slotId).intValue();
              paramAnonymousDialogInterface.sendToTarget();
            }
          }
          while (paramAnonymousInt != 1) {
            for (;;)
            {
              return;
              paramAnonymousInt = 3;
            }
          }
          paramAnonymousDialogInterface = mHandler;
          if (paramBoolean) {}
          for (paramAnonymousInt = 8;; paramAnonymousInt = 9)
          {
            paramAnonymousDialogInterface = Message.obtain(paramAnonymousDialogInterface, paramAnonymousInt);
            obj = Long.valueOf(paramLong);
            arg1 = Integer.valueOf(val$slotId).intValue();
            paramAnonymousDialogInterface.sendToTarget();
            return;
          }
        }
      }).show();
      return;
    }
  }
  
  private void createSimPickerDialog(View paramView, long paramLong, boolean paramBoolean, String paramString)
  {
    LayoutInflater localLayoutInflater = (LayoutInflater)mContext.getSystemService("layout_inflater");
    LinearLayout localLinearLayout = (LinearLayout)localLayoutInflater.inflate(2130968704, null);
    createSimPickerItem(0, paramLong, paramBoolean, false, localLinearLayout, localLayoutInflater);
    if (IsMxConnected(paramString, 0)) {
      createSimPickerItem(0, paramLong, paramBoolean, true, localLinearLayout, localLayoutInflater);
    }
    createSimPickerItem(1, paramLong, paramBoolean, false, localLinearLayout, localLayoutInflater);
    if (IsMxConnected(paramString, 1)) {
      createSimPickerItem(1, paramLong, paramBoolean, true, localLinearLayout, localLayoutInflater);
    }
    mResendDialog = new AlertDialog.Builder(paramView.getContext()).setTitle(2131362071).setView(localLinearLayout).show();
  }
  
  private void createSimPickerDialogForB2cMessageResend(View paramView, long paramLong, boolean paramBoolean)
  {
    LayoutInflater localLayoutInflater = (LayoutInflater)mContext.getSystemService("layout_inflater");
    LinearLayout localLinearLayout = (LinearLayout)localLayoutInflater.inflate(2130968704, null);
    createSimPickerItem(0, paramLong, paramBoolean, true, localLinearLayout, localLayoutInflater);
    createSimPickerItem(1, paramLong, paramBoolean, true, localLinearLayout, localLayoutInflater);
    mResendDialog = new AlertDialog.Builder(paramView.getContext()).setTitle(2131362071).setView(localLinearLayout).show();
  }
  
  private void createSimPickerItem(int paramInt, final long paramLong, final boolean paramBoolean1, final boolean paramBoolean2, LinearLayout paramLinearLayout, LayoutInflater paramLayoutInflater)
  {
    LinearLayout localLinearLayout = (LinearLayout)paramLayoutInflater.inflate(2130968705, paramLinearLayout, false);
    ((ImageView)localLinearLayout.findViewById(2131820664)).setImageResource(MSimUtils.getSimIconResId(paramInt));
    switch (paramInt)
    {
    default: 
      return;
    case 0: 
      if (!MSimUtils.isSimInserted(0))
      {
        paramLayoutInflater = mContext.getString(2131362235);
        if (!paramBoolean2) {
          break label203;
        }
        paramLayoutInflater = mContext.getString(2131362237, new Object[] { paramLayoutInflater });
      }
      break;
    }
    label203:
    for (;;)
    {
      ((TextView)localLinearLayout.findViewById(2131820628)).setText(paramLayoutInflater);
      localLinearLayout.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          MessageListItem.this.updateSimId(paramLong, paramBoolean1, paramBoolean2);
          if (!val$isMx)
          {
            paramAnonymousView = mHandler;
            if (paramBoolean2) {}
            for (i = 4;; i = 3)
            {
              paramAnonymousView = Message.obtain(paramAnonymousView, i);
              obj = Long.valueOf(paramLong);
              arg1 = Integer.valueOf(paramBoolean1).intValue();
              paramAnonymousView.sendToTarget();
              if (mResendDialog != null)
              {
                mResendDialog.dismiss();
                MessageListItem.access$2502(MessageListItem.this, null);
              }
              return;
            }
          }
          paramAnonymousView = mHandler;
          if (paramBoolean2) {}
          for (int i = 8;; i = 9)
          {
            paramAnonymousView = Message.obtain(paramAnonymousView, i);
            obj = Long.valueOf(paramLong);
            arg1 = Integer.valueOf(paramBoolean1).intValue();
            paramAnonymousView.sendToTarget();
            break;
          }
        }
      });
      paramLinearLayout.addView(localLinearLayout, new LinearLayout.LayoutParams(-1, -2));
      return;
      paramLayoutInflater = MSimUtils.getSimDisplayName(mContext, 0);
      break;
      if (!MSimUtils.isSimInserted(1))
      {
        paramLayoutInflater = mContext.getString(2131362236);
        break;
      }
      paramLayoutInflater = MSimUtils.getSimDisplayName(mContext, 1);
      break;
    }
  }
  
  private List<UnderstandMessage> filterDatetimeUnderstandMessage(List<UnderstandMessage> paramList)
  {
    ArrayList localArrayList = new ArrayList();
    if ((paramList != null) && (paramList.size() > 0))
    {
      paramList = paramList.iterator();
      for (;;)
      {
        if (!paramList.hasNext()) {
          return localArrayList;
        }
        UnderstandMessage localUnderstandMessage = (UnderstandMessage)paramList.next();
        if ((localUnderstandMessage != null) && (mItems.size() > 0))
        {
          Iterator localIterator = mItems.iterator();
          if (localIterator.hasNext())
          {
            if (nextmType != 101) {
              break;
            }
            localArrayList.add(localUnderstandMessage);
          }
        }
      }
    }
    return localArrayList;
  }
  
  private AudioItemController getAudioItemController()
  {
    if (mAudioUiController == null) {
      mAudioUiController = new AudioItemController((ViewStub)findViewById(2131820735), (ViewStub)findViewById(2131820730), mAudioItemCache);
    }
    return mAudioUiController;
  }
  
  private GroupSendViewStubController getGroupSendViewStubController()
  {
    if (mGroupSendViewController == null)
    {
      mGroupSendViewController = new GroupSendViewStubController();
      View localView = ((ViewStub)findViewById(2131820660)).inflate();
      mGroupSendViewController.mGroupSendViewContainer = ((LinearLayout)localView);
      mGroupSendViewController.mGroupSendToTitle = ((TextView)localView.findViewById(2131820661));
      mGroupSendViewController.mGroupSendToTextView = ((TextView)localView.findViewById(2131820662));
      mGroupSendViewController.mGroupSendCountTextView = ((TextView)localView.findViewById(2131820663));
      mGroupSendViewController.mGroupSendToTitle.setVisibility(8);
      mGroupSendViewController.mGroupSendToTextView.setVisibility(8);
      mGroupSendViewController.mGroupSendCountTextView.setVisibility(8);
    }
    return mGroupSendViewController;
  }
  
  private int getMaxTextWidth(MessageItem paramMessageItem)
  {
    int i;
    if (!paramMessageItem.isBubbleLayoutStyle()) {
      i = 0;
    }
    int j;
    do
    {
      return i;
      j = mMaxBubbleTextWidth;
      i = j;
      if (mEditMode) {
        i = j - mContext.getResources().getDimensionPixelSize(2131427348);
      }
      j = i;
      if (paramMessageItem.isMms())
      {
        j = i;
        if (paramMessageItem.getMmsPreviewType() != 1) {
          j = i - mMmsAttachmentPaddingWidth;
        }
      }
      i = j;
    } while (!paramMessageItem.isTimed());
    return j - mTimedMessagePaddingWidth;
  }
  
  private String getMidViaAddress(String paramString, boolean paramBoolean)
  {
    Object localObject2 = null;
    Object localObject1 = localObject2;
    if (MxActivateService.isMxEnabled(mContext))
    {
      paramString = Contact.get(paramString);
      paramString = MxIdCache.getOrQuery(mContext, paramString.getMxPhoneNumber());
      localObject1 = localObject2;
      if (paramString != null)
      {
        if (!paramBoolean) {
          break label61;
        }
        localObject1 = localObject2;
        if (!paramString.allowSms()) {}
      }
    }
    for (;;)
    {
      localObject1 = paramString.getMId();
      label61:
      do
      {
        return (String)localObject1;
        localObject1 = localObject2;
      } while (!paramString.allowMms());
    }
  }
  
  private UnderstandMessage getUnderstandMessageWithButton(List<UnderstandMessage> paramList)
  {
    if ((paramList != null) && (paramList.size() > 0))
    {
      paramList = paramList.iterator();
      while (paramList.hasNext())
      {
        UnderstandMessage localUnderstandMessage = (UnderstandMessage)paramList.next();
        if (UnderstandFactory.getButtonNumber(mActionID) > 0) {
          return localUnderstandMessage;
        }
      }
    }
    return null;
  }
  
  private void handleB2cMessageResend(View paramView, long paramLong1, boolean paramBoolean, long paramLong2)
  {
    int i = 8;
    if (MSimUtils.isMSimInserted())
    {
      j = MSimUtils.getSlotIdBySimInfoId(paramLong2);
      if (MSimUtils.isMSimSlotIdValid(j))
      {
        if (PushSession.getInstance(mContext).isConnected(j))
        {
          paramView = mHandler;
          if (paramBoolean) {}
          for (;;)
          {
            paramView = Message.obtain(paramView, i);
            obj = Long.valueOf(paramLong1);
            arg1 = Integer.valueOf(j).intValue();
            paramView.sendToTarget();
            return;
            i = 9;
          }
        }
        Toast.makeText(mContext, 2131362405, 0).show();
        return;
      }
      if ((PushSession.getInstance(mContext).isConnected(0)) && (PushSession.getInstance(mContext).isConnected(1)))
      {
        createSimPickerDialogForB2cMessageResend(paramView, paramLong1, paramBoolean);
        return;
      }
      j = PushSession.getInstance(mContext).getConnectedSimIndex();
      if (MSimUtils.isMSimSlotIdValid(j))
      {
        paramView = mHandler;
        if (paramBoolean) {}
        for (;;)
        {
          paramView = Message.obtain(paramView, i);
          obj = Long.valueOf(paramLong1);
          arg1 = Integer.valueOf(j).intValue();
          paramView.sendToTarget();
          return;
          i = 9;
        }
      }
      Toast.makeText(mContext, 2131362405, 0).show();
      return;
    }
    int j = MSimUtils.getInsertedSlotId();
    if (!PushSession.getInstance(mContext).isConnected(j))
    {
      Toast.makeText(mContext, 2131362405, 0).show();
      return;
    }
    paramView = mHandler;
    if (paramBoolean) {}
    for (;;)
    {
      paramView = Message.obtain(paramView, i);
      obj = Long.valueOf(paramLong1);
      arg1 = Integer.valueOf(j).intValue();
      paramView.sendToTarget();
      return;
      i = 9;
    }
  }
  
  private void handleUnderstandButtonground(View paramView, MotionEvent paramMotionEvent)
  {
    if (paramMotionEvent.getAction() == 0) {
      paramView.setBackgroundColor(mContext.getResources().getColor(2131296328));
    }
    while ((paramMotionEvent.getAction() != 1) && (paramMotionEvent.getAction() != 3)) {
      return;
    }
    paramView.setBackgroundColor(mContext.getResources().getColor(2131296327));
  }
  
  private void hideGroupViewIfNeeded()
  {
    if (mGroupSendViewController != null)
    {
      mGroupSendViewController.mGroupSendToTitle.setVisibility(8);
      mGroupSendViewController.mGroupSendToTextView.setVisibility(8);
      mGroupSendViewController.mGroupSendCountTextView.setVisibility(8);
    }
  }
  
  private void hideMmsViewIfNeeded()
  {
    if (mAttachmentPreview != null) {
      mAttachmentPreview.setVisibility(8);
    }
    if (mAttachmentPadding != null) {
      mAttachmentPadding.setVisibility(8);
    }
    if (mGroupSendFailedRoot != null) {
      mGroupSendFailedRoot.setVisibility(8);
    }
    if (mFileShareLayout != null) {
      mFileShareLayout.setVisibility(8);
    }
    if ((mMessageItem.isBubbleLayoutStyle()) && (mAudioUiController != null)) {
      getAudioItemController().unbind();
    }
  }
  
  private void hideUnderstandButton()
  {
    if (mBtnRegionLayout != null) {
      mBtnRegionLayout.setVisibility(8);
    }
    if (mUnderstandPadding != null) {
      mUnderstandPadding.setVisibility(8);
    }
    if ((mFavouriteMark != null) && (mFavouriteMark.getVisibility() == 0))
    {
      RelativeLayout.LayoutParams localLayoutParams = (RelativeLayout.LayoutParams)mFavouriteMark.getLayoutParams();
      localLayoutParams.removeRule(2);
      localLayoutParams.addRule(8, 2131820723);
      mFavouriteMark.setLayoutParams(localLayoutParams);
    }
  }
  
  public static void initDummy() {}
  
  private boolean isCardBodyOutLayoutStyle(int paramInt)
  {
    return (paramInt == 5) || (paramInt == 6) || (paramInt == 7) || (paramInt == 8);
  }
  
  private boolean isMxAudioAttachment(MessageItem paramMessageItem)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (paramMessageItem.getMx2Attachments() != null)
    {
      bool1 = bool2;
      if (AttachmentUtils.getMessageTypeFromMimeType(getMx2Attachmentsget0mimeType) == 3) {
        bool1 = true;
      }
    }
    return bool1;
  }
  
  private boolean isThumbnail(MessageItem paramMessageItem)
  {
    return (paramMessageItem.isMms()) && (!isMxAudioAttachment(paramMessageItem)) && (TextUtils.isEmpty(paramMessageItem.getBody())) && (TextUtils.isEmpty(paramMessageItem.getSubject())) && (!paramMessageItem.isTimed()) && (!isVcardPreview(paramMessageItem));
  }
  
  private boolean isVcardPreview(MessageItem paramMessageItem)
  {
    return paramMessageItem.getMmsPreviewType() == 5;
  }
  
  private void launchSlideshow(Uri paramUri)
  {
    Intent localIntent = new Intent(mContext, SlideshowActivity.class);
    localIntent.setData(paramUri);
    localIntent.putExtra("highlight", mHighlight);
    mContext.startActivity(localIntent);
  }
  
  private void onPostMeasure()
  {
    if ((mBubble != null) && (mCheckBoxContainer != null) && (mCheckBoxContainer.getVisibility() == 0))
    {
      int j = 0;
      int i = j;
      if (mDateIndicatorPanel != null)
      {
        i = j;
        if (mDateIndicatorPanel.getVisibility() == 0) {
          i = (mDateIndicatorPanel.getMeasuredHeight() + mBubbleIndicatorTopMargin) / 2;
        }
      }
      mCheckBox.getLayoutParams()).topMargin = i;
    }
  }
  
  private void onPreMeasure()
  {
    if (mBubble != null) {
      if (!mMessageItem.getHasBubbleDistance()) {
        break label42;
      }
    }
    label42:
    for (int i = mBubbleTopMargin + mBubbleTopMarginMinimum;; i = mBubbleTopMarginMinimum)
    {
      mBubble.getLayoutParams()).topMargin = i;
      return;
    }
  }
  
  private void prepareStyle(MessageItem paramMessageItem)
  {
    switch (paramMessageItem.getLayoutStyle())
    {
    }
    for (;;)
    {
      mBubble = findViewById(2131820766);
      mSenderTextView = ((TextView)findViewById(2131820570));
      mMxTypeView = ((TextView)findViewById(2131820746));
      mMxTypeGuideView = ((ImageView)findViewById(2131820747));
      if (mMxTypeGuideView != null) {
        mMxTypeGuideView.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            mHandler.removeMessages(12);
            Message localMessage = Message.obtain(mHandler, 12);
            obj = paramAnonymousView;
            localMessage.sendToTarget();
          }
        });
      }
      mMxTypeIndicatorPanel = findViewById(2131820745);
      mDateIndicatorPanel = findViewById(2131820741);
      mDateTextView = ((TextView)findViewById(2131820569));
      mSubjectTextView = ((TextView)findViewById(2131820732));
      mBodyTextView = ((TextView)findViewById(2131820578));
      mBodyTextView.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
      mBodyLinearLayout = ((LinearLayout)findViewById(2131820733));
      mFileShareLayout = ((LinearLayout)findViewById(2131820734));
      mGroupSendFailedRoot = findViewById(2131820755);
      mGroupSendFailedTitle = ((TextView)findViewById(2131820756));
      mGroupSendFailedButton = ((Button)findViewById(2131820758));
      mGroupSendCancelButton = ((Button)findViewById(2131820759));
      mDeliverStatus = ((TextView)findViewById(2131820718));
      mResentButton = findViewById(2131820742);
      mBtnRegionLayout = ((LinearLayout)findViewById(2131820728));
      mFavouriteMark = ((ImageView)findViewById(2131820729));
      mMessageItemLayout = findViewById(2131820723);
      mAttachmentPadding = findViewById(2131820568);
      mAttachmentPreview = ((ThumbnailView)findViewById(2131820572));
      mAttachmentFavouriteView = ((ImageView)findViewById(2131820731));
      if (paramMessageItem.isBubbleLayoutStyle())
      {
        mMmsDownloadPadding = findViewById(2131820725);
        mTimedMsgPadding = findViewById(2131820748);
        mUnderstandPadding = findViewById(2131820726);
      }
      if ((paramMessageItem.isBubbleLayoutStyle()) || (paramMessageItem.isBookmarkLayoutStyle())) {
        mSlotIdView = ((ImageView)findViewById(2131820571));
      }
      if (paramMessageItem.getMx2Type() == 3) {
        getAudioItemController().initListItem(paramMessageItem, mHandler);
      }
      if ((MiuiConfiguration.getScaleMode() == 11) || (MiuiConfiguration.getScaleMode() == 15)) {
        mStatusDivider = findViewById(2131820744);
      }
      setSubjectAndBodyPaddingBottom();
      return;
      findViewById(2131820776).setVisibility(0);
      findViewById(2131820567).setVisibility(0);
      continue;
      findViewById(2131820768).setVisibility(0);
      findViewById(2131820770).setVisibility(0);
      continue;
      findViewById(2131820769).setVisibility(0);
      findViewById(2131820771).setVisibility(0);
      continue;
      findViewById(2131820769).setVisibility(0);
      findViewById(2131820772).setVisibility(0);
      continue;
      findViewById(2131820567).setVisibility(0);
    }
  }
  
  private void previewContactFromMessage(ContactMessage.ContactRecord paramContactRecord)
  {
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.setType("vnd.android.cursor.dir/preview_contact");
    float f = mContext.getResources().getCompatibilityInfo().applicationScale;
    int[] arrayOfInt = new int[2];
    getLocationOnScreen(arrayOfInt);
    Rect localRect = new Rect();
    left = ((int)(arrayOfInt[0] * f + 0.5F));
    top = ((int)(arrayOfInt[1] * f + 0.5F));
    right = ((int)((arrayOfInt[0] + getWidth()) * f + 0.5F));
    bottom = ((int)(f * (arrayOfInt[1] + getHeight()) + 0.5F));
    localIntent.setSourceBounds(localRect);
    ContactParser.getContactParser(mContext).putContactToIntent(paramContactRecord, localIntent);
    mContext.startActivity(localIntent);
  }
  
  private void setSubjectAndBodyPaddingBottom()
  {
    if (MSimUtils.isAndroid50())
    {
      if (mSubjectTextView != null) {
        mSubjectTextView.setPadding(mSubjectTextView.getPaddingLeft(), mSubjectTextView.getPaddingTop(), mSubjectTextView.getPaddingRight(), mSubjectTextView.getPaddingBottom() + mSubjectTextView.getLineHeight() - mSubjectTextView.getPaint().getFontMetricsInt(null));
      }
      if (mBodyTextView != null) {
        mBodyTextView.setPadding(mBodyTextView.getPaddingLeft(), mBodyTextView.getPaddingTop(), mBodyTextView.getPaddingRight(), mBodyTextView.getPaddingBottom() + mBodyTextView.getLineHeight() - mBodyTextView.getPaint().getFontMetricsInt(null));
      }
    }
  }
  
  private void setUnderstandButton(final MessageItem paramMessageItem, TextView paramTextView, final UnderstandMessage paramUnderstandMessage, final int paramInt)
  {
    if (paramTextView != null)
    {
      paramTextView.setText(UnderstandFactory.getButtonName(mActionID, paramInt));
      paramTextView.setVisibility(0);
      mBodyTextViewList.add(paramTextView);
      paramTextView.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          MiStatSdkHelper.recordUnderstandButtonClick(paramUnderstandMessagemActionID, paramInt);
          Object localObject = UnderstandFactory.getButtonActions(paramUnderstandMessagemActionID, paramInt);
          if (localObject != null)
          {
            paramAnonymousView = new ArrayList();
            localObject = ((List)localObject).iterator();
            while (((Iterator)localObject).hasNext())
            {
              UnderstandAction localUnderstandAction = UnderstandFactory.parseActionString((String)((Iterator)localObject).next());
              if (localUnderstandAction != null) {
                paramAnonymousView.add(localUnderstandAction);
              }
            }
            int i = MSimUtils.getSlotIdBySimInfoId(paramMessageItem.getSimId());
            UnderstandFactory.doAction(mContext, paramAnonymousView, paramUnderstandMessage, i);
          }
        }
      });
      paramTextView.setBackgroundColor(mContext.getResources().getColor(2131296327));
      paramTextView.setOnTouchListener(new View.OnTouchListener()
      {
        public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
        {
          MessageListItem.this.handleUnderstandButtonground(paramAnonymousView, paramAnonymousMotionEvent);
          return false;
        }
      });
    }
  }
  
  private boolean shouldDisableCardBubble(MessageItem paramMessageItem)
  {
    if ((paramMessageItem.isSms()) && (paramMessageItem.getContactMessage() != null)) {}
    for (int i = 1; (i != 0) && (!paramMessageItem.isLocked()) && (!paramMessageItem.isTimed()); i = 0) {
      return true;
    }
    return false;
  }
  
  private boolean shouldMmsCollapse()
  {
    return (mMessageItem.isMms()) && ((mMessageItem.getMmsPreviewType() == 6) || ((mMessageItem.getMmsPreviewType() > 1) && (mMessageItem.getMmsSnippet() != null) && (mMessageItem.getMmsSnippet().length() > 70)));
  }
  
  private void startPlayAudioItem(MessageItem paramMessageItem)
  {
    if (paramMessageItem.isOutMessage())
    {
      getAudioItemController().startPlayAudio();
      return;
    }
    if (!getAudioItemController().isDownloadingFile())
    {
      getAudioItemController().startPlayAudio();
      return;
    }
    Toast.makeText(mContext, 2131362360, 0).show();
  }
  
  private void updateSimId(long paramLong, int paramInt, boolean paramBoolean)
  {
    ContentValues localContentValues = new ContentValues(1);
    localContentValues.put("sim_id", Long.valueOf(MSimUtils.getSimIdBySlotId(paramInt)));
    Context localContext = mContext;
    ContentResolver localContentResolver = mContext.getContentResolver();
    if (paramBoolean) {}
    for (Uri localUri = Telephony.Sms.CONTENT_URI;; localUri = Telephony.Mms.CONTENT_URI)
    {
      SqliteWrapper.update(localContext, localContentResolver, localUri, localContentValues, "_id=" + paramLong, null);
      return;
    }
  }
  
  private void updateStatusDivider()
  {
    int k = 1;
    if (mStatusDivider == null) {
      return;
    }
    int i = 0;
    if (mDeliverStatus != null)
    {
      if (mDeliverStatus.getVisibility() == 0)
      {
        i = 1;
        i = 0x0 | i;
      }
    }
    else
    {
      j = i;
      if (mResentButton != null) {
        if (mResentButton.getVisibility() != 0) {
          break label78;
        }
      }
    }
    label78:
    for (int j = k;; j = 0)
    {
      j = i | j;
      if (j == 0) {
        break label83;
      }
      mStatusDivider.setVisibility(0);
      return;
      i = 0;
      break;
    }
    label83:
    mStatusDivider.setVisibility(8);
  }
  
  private void viewMmsMessageAttachment()
  {
    if ((mMessageItem != null) && (mMessageItem.isMms()))
    {
      if ((mMessageItem.getMmsPreviewType() != 3) || (mMessageItem.getMx2Attachments() == null) || (mMessageItem.getMx2Attachments().size() <= 0)) {
        break label62;
      }
      startPlayAudioItem(mMessageItem);
    }
    label62:
    Object localObject;
    do
    {
      do
      {
        return;
        if (shouldMmsCollapse())
        {
          launchSlideshow(mMessageItem.getMessageUri());
          return;
        }
        localObject = mMessageItem.getSimplePduDoc();
        if (((SimplePduDoc)localObject).isSlideShow())
        {
          launchSlideshow(((SimplePduDoc)localObject).getUri());
          return;
        }
      } while (!((SimplePduDoc)localObject).canShow());
      localObject = ((SimplePduDoc)localObject).getPageAppearancePart(0);
    } while (localObject == null);
    try
    {
      mContext.startActivity(((SimplePduPart)localObject).getIntent());
      return;
    }
    catch (ActivityNotFoundException localActivityNotFoundException)
    {
      Toast.makeText(mContext, mContext.getString(2131362136), 0).show();
    }
  }
  
  public void bind(MessageItem paramMessageItem)
  {
    prepareStyle(paramMessageItem);
  }
  
  public MessageItem getMessageItem()
  {
    return mMessageItem;
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    onPreMeasure();
    super.onMeasure(paramInt1, paramInt2);
    onPostMeasure();
  }
  
  public void onMessageListItemClick()
  {
    if (((mContext instanceof ConversationBase)) && (!((ConversationBase)mContext).isRunning())) {
      return;
    }
    Object localObject1;
    if (mBodyTextView.getVisibility() == 0) {
      localObject1 = mBodyTextView.getUrls();
    }
    for (;;)
    {
      int i;
      final Object localObject2;
      if ((mMessageItem.isGroup()) && (mMessageItem.isSms()))
      {
        long l1 = mMessageItem.getDate();
        long l2 = mMessageItem.getTimedValue();
        long l3 = mMessageItem.getThreadId();
        if (l1 < 0L) {
          break;
        }
        localObject1 = new Intent(mContext, MultipleRecipientsConversationDetailsActivity.class);
        ((Intent)localObject1).putExtra("timestamp", l1);
        ((Intent)localObject1).putExtra("timed_value", l2);
        ((Intent)localObject1).putExtra("thread_id", l3);
        mContext.startActivity((Intent)localObject1);
        return;
        if ((mBodyLinearLayout != null) && (mBodyLinearLayout.getVisibility() == 0))
        {
          localObject1 = new ArrayList();
          i = 0;
          while (i < mBodyLinearLayout.getChildCount())
          {
            localObject2 = mBodyLinearLayout.getChildAt(i);
            if ((localObject2 instanceof TextView))
            {
              localObject2 = ((TextView)localObject2).getUrls();
              int k = localObject2.length;
              int j = 0;
              while (j < k)
              {
                ((ArrayList)localObject1).add(localObject2[j]);
                j += 1;
              }
            }
            i += 1;
          }
          localObject1 = (URLSpan[])((ArrayList)localObject1).toArray(new URLSpan[((ArrayList)localObject1).size()]);
        }
      }
      for (;;)
      {
        if (mMessageItem.getMx2Type() == 3)
        {
          startPlayAudioItem(mMessageItem);
          return;
          localObject1 = new URLSpan[0];
          continue;
          if (mFileShareLayout.getVisibility() == 0)
          {
            localObject1 = MessageUtils.getFileShareMessage(mMessageItem.getBody());
            if ((localObject1 == null) || (((String)localObject1).isEmpty())) {
              break;
            }
            localObject2 = Uri.parse((String)localObject1);
            if (localObject2 == null) {
              break;
            }
            localObject1 = new Intent("cn.kuaipan.mishare.View");
            ((Intent)localObject1).setData((Uri)localObject2);
            if (mMessageItem.isOutMessage()) {
              ((Intent)localObject1).putExtra("MSG_TYPE", 2);
            }
            for (;;)
            {
              ((Intent)localObject1).putExtra("SENDER_NUM", mMessageItem.getAddress());
              localObject2 = mContext.getPackageManager().queryIntentActivities((Intent)localObject1, 65536);
              if ((localObject2 == null) || (((List)localObject2).isEmpty())) {
                break;
              }
              mContext.startActivity((Intent)localObject1);
              return;
              ((Intent)localObject1).putExtra("MSG_TYPE", 1);
            }
          }
          localObject2 = mMessageItem.getBody();
          Object localObject3 = mContext.getPackageName();
          if (MiuiGalleryUtils.handleAsAlbumShareInvitation(mContext, null, (String)localObject2, (String)localObject3, 8))
          {
            Log.d("MessageListItem", "handled as album share invitation");
            return;
          }
          final Object localObject5 = mMessageItem.getContactMessage();
          Object localObject4 = filterDatetimeUnderstandMessage(mUnderstandMessageList);
          if (localObject5 == null)
          {
            if ((localObject1.length == 0) && (((List)localObject4).size() == 0))
            {
              viewMmsMessageAttachment();
              return;
            }
            if ((localObject1.length + ((List)localObject4).size() != 1) || (!mMessageItem.isSms())) {
              break label782;
            }
            if (localObject1.length == 1)
            {
              localObject1 = MessageUtils.getUriInfo(localObject1[0].getURL());
              MessageUtils.performUriOperation(mContext, (MessageUtils.UriInfo)localObject1, mMessageItem);
              return;
            }
            if (((List)localObject4).size() != 1) {
              break;
            }
            localObject1 = new MessageUtils.UriInfo();
            localObject3 = mMessageItem;
            scheme = 4;
            localObject4 = (UnderstandMessage.Item)get0mItems.get(0);
            localObject5 = mValue;
            content = ((String)localObject2).substring(mStartPosition, mEndPosition);
            formatContent = ((String)localObject5);
            title = ((String)localObject5);
            MessageUtils.performUriOperation(mContext, (MessageUtils.UriInfo)localObject1, (MessageItem)localObject3);
            return;
          }
          if ((((ContactMessage)localObject5).count() == 1) && (localObject1.length == 0) && (((List)localObject4).size() == 0) && ((!mMessageItem.isMms()) || (mMessageItem.getMmsPreviewType() <= 1)))
          {
            previewContactFromMessage((ContactMessage.ContactRecord)((ContactMessage)localObject5).getContactRecords().get(0));
            return;
          }
          label782:
          localObject3 = new ContextMenuDialog(getContext());
          final Object localObject6;
          if (localObject5 != null)
          {
            localObject5 = ((ContactMessage)localObject5).getContactRecords().iterator();
            while (((Iterator)localObject5).hasNext())
            {
              localObject6 = (ContactMessage.ContactRecord)((Iterator)localObject5).next();
              ((ContextMenuDialog)localObject3).addMenuItem(getContext().getString(2131362085, new Object[] { ((ContactMessage.ContactRecord)localObject6).getPreviewString() }), new Runnable()
              {
                public void run()
                {
                  MessageListItem.this.previewContactFromMessage(localObject6);
                }
              });
            }
          }
          localObject5 = new HashSet();
          i = 0;
          while (i < localObject1.length)
          {
            final Object localObject7 = localObject1[i].getURL();
            if (!((HashSet)localObject5).contains(localObject7))
            {
              ((HashSet)localObject5).add(localObject7);
              localObject6 = mMessageItem;
              localObject7 = MessageUtils.getUriInfo((String)localObject7);
              ((ContextMenuDialog)localObject3).addMenuItem(title, new Runnable()
              {
                public void run()
                {
                  MessageUtils.performUriOperation(mContext, localObject7, localObject6);
                }
              });
            }
            i += 1;
          }
          if (((List)localObject4).size() > 0)
          {
            localObject1 = new HashSet();
            localObject4 = ((List)localObject4).iterator();
            while (((Iterator)localObject4).hasNext())
            {
              localObject5 = (UnderstandMessage.Item)nextmItems.get(0);
              localObject6 = mValue;
              if (!((HashSet)localObject1).contains(localObject6))
              {
                ((HashSet)localObject1).add(localObject6);
                ((ContextMenuDialog)localObject3).addMenuItem((String)localObject6, new Runnable()
                {
                  public void run()
                  {
                    MessageUtils.UriInfo localUriInfo = new MessageUtils.UriInfo();
                    scheme = 4;
                    formatContent = localObject6;
                    content = localObject2.substring(localObject5mStartPosition, localObject5mEndPosition);
                    title = localObject6;
                    MessageUtils.performUriOperation(mContext, localUriInfo, val$mi);
                  }
                });
              }
            }
          }
          if ((mMessageItem.isMms()) && (mMessageItem.getSimplePduDoc().canShow())) {
            ((ContextMenuDialog)localObject3).addMenuItem(2131362075, new Runnable()
            {
              public void run()
              {
                MessageListItem.this.viewMmsMessageAttachment();
              }
            });
          }
          ((ContextMenuDialog)localObject3).setTitle(2131362018);
          ((ContextMenuDialog)localObject3).show();
          return;
        }
      }
    }
  }
  
  public void onMessageListItemDoubleClick()
  {
    if ((mMessageItem.isSms()) && (mBodyTextView.getVisibility() == 0))
    {
      Intent localIntent = new Intent(mContext, MessageFullscreenActivity.class);
      localIntent.putExtra("body", mBodyTextView.getText().toString());
      mContext.startActivity(localIntent);
      ((Activity)mContext).overridePendingTransition(0, 2131034116);
    }
  }
  
  public void onUpdate(final Contact paramContact)
  {
    if (mHandler == null) {
      return;
    }
    mHandler.post(new Runnable()
    {
      public void run()
      {
        if ((mMessageItem != null) && (mMessageItem.getContact() == paramContact))
        {
          if ((mGroupSendViewController != null) && (getGroupSendViewStubControllermGroupSendToTitle.getVisibility() == 0)) {
            getGroupSendViewStubControllermGroupSendToTextView.setText(mMessageItem.getContactName());
          }
          if (!mMessageItem.isListLayoutStyle()) {
            break label146;
          }
          if ((mSenderTextView != null) && (!mMessageItem.isGroup()) && (!mMessageItem.isReadOnly())) {
            mSenderTextView.setText(mMessageItem.getContactName());
          }
        }
        label146:
        while ((mMessageItem.isBubbleLayoutStyle()) || (mSenderTextView == null)) {
          return;
        }
        mSenderTextView.setText(mContext.getString(2131362102, new Object[] { mMessageItem.getContactName() }));
      }
    });
  }
  
  public void rebind(MessageItem paramMessageItem, boolean paramBoolean1, boolean paramBoolean2, String paramString1, String paramString2)
  {
    rebind(paramMessageItem, paramBoolean1, paramBoolean2, paramString1, paramString2, false);
  }
  
  public void rebind(MessageItem paramMessageItem, boolean paramBoolean1, boolean paramBoolean2, String paramString1, String paramString2, boolean paramBoolean3)
  {
    mEditMode = paramBoolean1;
    mMessageItem = paramMessageItem;
    mHighlight = paramString1;
    mBodySubstitution = paramString2;
    mIsPrivate = paramBoolean3;
    hideMmsViewIfNeeded();
    bindBackground(paramMessageItem);
    if (paramMessageItem.isBookmarkLayoutStyle())
    {
      bindBookmark(paramMessageItem);
      return;
    }
    hideGroupViewIfNeeded();
    Contact.addListener(this);
    switch (paramMessageItem.getMessageType())
    {
    default: 
      bindNonNotifInd(paramMessageItem);
    }
    for (;;)
    {
      bindCommonIndicators(paramMessageItem);
      bindCheckStatus(paramMessageItem, paramBoolean1, paramBoolean2);
      if (!paramMessageItem.isBubbleLayoutStyle()) {
        break;
      }
      bindUnderstandButton(paramMessageItem);
      return;
      bindNotifInd(paramMessageItem);
    }
  }
  
  public void setAudioItemCache(AudioItemCache paramAudioItemCache)
  {
    mAudioItemCache = paramAudioItemCache;
  }
  
  public void setBodyTextSize(float paramFloat)
  {
    if ((mBodyTextView != null) && (mBodyTextView.getVisibility() == 0)) {
      mBodyTextView.setTextSize(0, paramFloat);
    }
    if ((mSubjectTextView != null) && (mSubjectTextView.getVisibility() == 0)) {
      mSubjectTextView.setTextSize(0, paramFloat);
    }
    int j = mBodyTextViewList.size();
    int i = 0;
    while (i < j)
    {
      TextView localTextView = (TextView)mBodyTextViewList.get(i);
      if ((localTextView != null) && (localTextView.getVisibility() == 0)) {
        localTextView.setTextSize(0, paramFloat);
      }
      i += 1;
    }
  }
  
  public void setMsgListItemHandler(Handler paramHandler)
  {
    mHandler = paramHandler;
  }
  
  public void setUnderstandMessageList(List<UnderstandMessage> paramList)
  {
    mUnderstandMessageList = paramList;
  }
  
  public void showEditModeAnimation(boolean paramBoolean)
  {
    boolean bool = mMessageItem.isBubbleOutLayoutStyle();
    Object localObject;
    if (mCheckBoxContainer == null)
    {
      localObject = findViewById(2131820773);
      ((View)localObject).setVisibility(0);
      mCheckBoxContainer = ((View)localObject);
      mCheckBox = ((CheckBox)findViewById(16908289));
    }
    if (paramBoolean) {
      if (mCheckBoxContainer.getVisibility() == 8)
      {
        mCheckBoxContainer.setVisibility(0);
        localObject = new AlphaAnimation(0.0F, 1.0F);
        ((AlphaAnimation)localObject).setDuration(150L);
        mCheckBoxContainer.startAnimation((Animation)localObject);
        if (bool)
        {
          localObject = new TranslateAnimation(mCheckBoxWidth + mBubbleCheckBoxDistance, 0.0F, 0.0F, 0.0F);
          ((TranslateAnimation)localObject).setDuration(150L);
          mBubble.startAnimation((Animation)localObject);
        }
        if (isMxAudioAttachment(mMessageItem)) {
          getAudioItemController().startEditMode();
        }
      }
    }
    do
    {
      do
      {
        return;
      } while (mCheckBoxContainer.getVisibility() != 0);
      mCheckBoxContainer.setVisibility(8);
      localObject = new AlphaAnimation(1.0F, 0.0F);
      ((AlphaAnimation)localObject).setDuration(150L);
      mCheckBoxContainer.startAnimation((Animation)localObject);
      if (bool)
      {
        localObject = new TranslateAnimation(-(mCheckBoxWidth + mBubbleCheckBoxDistance), 0.0F, 0.0F, 0.0F);
        ((TranslateAnimation)localObject).setDuration(150L);
        mBubble.startAnimation((Animation)localObject);
      }
    } while (!isMxAudioAttachment(mMessageItem));
    getAudioItemController().endEditMode();
  }
  
  public void unbind()
  {
    if (mMessageItem.isMms()) {
      PreviewImageLoader.getInstance().cancel(mMessageItem.getMsgId(), mAttachmentPreview);
    }
    Contact.removeListener(this);
    if (mMessageItem.isBubbleLayoutStyle()) {
      getAudioItemController().unbind();
    }
    mMessageItem = null;
    mUnderstandMessageList = null;
  }
  
  public class GroupSendViewStubController
  {
    public TextView mGroupSendCountTextView;
    public TextView mGroupSendToTextView;
    public TextView mGroupSendToTitle;
    public LinearLayout mGroupSendViewContainer;
    
    public GroupSendViewStubController() {}
    
    public void refreshSendView(boolean paramBoolean)
    {
      if (paramBoolean)
      {
        int i = (int)getResources().getDimension(2131427421);
        mGroupSendViewContainer.setPadding(0, 0, i, 0);
        mGroupSendToTitle.setTextColor(getResources().getColorStateList(2131296351));
        mGroupSendToTextView.setTextColor(getResources().getColorStateList(2131296351));
        mGroupSendCountTextView.setTextColor(getResources().getColorStateList(2131296351));
        return;
      }
      mGroupSendViewContainer.setPadding(0, 0, 0, 0);
      mGroupSendToTitle.setTextColor(getResources().getColorStateList(2131296360));
      mGroupSendToTextView.setTextColor(getResources().getColorStateList(2131296359));
      mGroupSendCountTextView.setTextColor(getResources().getColorStateList(2131296359));
    }
  }
  
  static enum Style
  {
    bubble,  list;
    
    private Style() {}
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.MessageListItem
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */