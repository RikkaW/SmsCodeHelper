package com.android.mms.ui;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract.Data;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.android.mms.LogTag;
import com.android.mms.data.Contact;
import com.android.mms.data.ContactList;
import com.android.mms.util.AddressUtils;
import com.xiaomi.mms.utils.B2cMessageUtils;
import miui.telephony.PhoneNumberUtils;
import miui.yellowpage.YellowPageUtils;

public class SingleRecipientConversationHeader
  extends RelativeLayout
{
  private View.OnClickListener mCallClickListener = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      SingleRecipientConversationHeader.this.onMenuItemIdCall();
    }
  };
  private ImageView mCallContact;
  private Contact mContact;
  private View mFetionPrefix;
  private TextView mFromView;
  private View mHomeView;
  private View mMainTextView;
  private TextView mPhoneLocation;
  private TextView mPhoneTag;
  private ImageView mShowContact;
  private View.OnClickListener mVoipClickListener = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      SingleRecipientConversationHeader.this.onVoipCall();
    }
  };
  private boolean mVoipStyle;
  
  public SingleRecipientConversationHeader(Context paramContext)
  {
    super(paramContext);
    mContext = paramContext;
  }
  
  public SingleRecipientConversationHeader(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    mContext = paramContext;
  }
  
  private Intent getYellowPageDetailIntent()
  {
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.setData(Uri.parse("yellowpage://details?sid=" + mContact.getYellowPageId()));
    localIntent.putExtra("source", "sms_conv_menu");
    return localIntent;
  }
  
  private boolean isB2cContact()
  {
    return (mContact != null) && (B2cMessageUtils.isB2cNumber(mContact));
  }
  
  private void onMenuItemIdCall()
  {
    Intent localIntent;
    if (mContact != null)
    {
      if (!mContact.existsInDatabase()) {
        break label114;
      }
      localObject = Uri.withAppendedPath(ContactsContract.Data.CONTENT_URI, String.valueOf(mContact.getContactMethodId()));
      localIntent = new Intent("android.intent.action.CALL_PRIVILEGED");
      if (localObject == null) {
        LogTag.verbose("onMenuItemIdCall uri is null", new Object[0]);
      }
    }
    else
    {
      return;
    }
    localIntent.setDataAndType((Uri)localObject, "vnd.android.cursor.item/phone_v2");
    Object localObject = localIntent;
    if (!TextUtils.isEmpty(mContact.getName()))
    {
      localIntent.putExtra("android.phone.extra.CONTACT_NAME", mContact.getName());
      localObject = localIntent;
    }
    for (;;)
    {
      ((Intent)localObject).setPackage(MessageUtils.getPhonePackageName());
      mContext.startActivity((Intent)localObject);
      return;
      label114:
      localObject = mContact.getNumber();
      if (TextUtils.isEmpty((CharSequence)localObject))
      {
        LogTag.verbose("onMenuItemIdCall number is empty", new Object[0]);
        return;
      }
      localIntent = new Intent("android.intent.action.CALL_PRIVILEGED", Uri.parse("tel:" + (String)localObject));
      localObject = localIntent;
      if (!TextUtils.isEmpty(mContact.getName()))
      {
        localIntent.putExtra("android.phone.extra.CONTACT_NAME", mContact.getName());
        localObject = localIntent;
      }
    }
  }
  
  private void onMenuItemIdContactLook()
  {
    long l;
    if (mContact != null)
    {
      l = mContact.getPersonId();
      if (mContact.isB2cNumber())
      {
        localObject = B2cMessageUtils.getB2cContactDetailIntent(mContact.getNumber());
        mContext.startActivity((Intent)localObject);
      }
    }
    else
    {
      return;
    }
    if (mContact.isYellowPageNumber())
    {
      localObject = getYellowPageDetailIntent();
      mContext.startActivity((Intent)localObject);
      return;
    }
    if (l > 0L)
    {
      localObject = mContact.getUri();
      if (localObject == null)
      {
        LogTag.verbose("onMenuItemIdContactLook uri is null", new Object[0]);
        return;
      }
      localObject = new Intent("android.intent.action.VIEW", (Uri)localObject);
      ((Intent)localObject).putExtra("ignoreDefaultUpBehavior", true);
      ((Intent)localObject).setPackage(MessageUtils.getContactsPackageName());
      mContext.startActivity((Intent)localObject);
      return;
    }
    if (mContact.isEmail())
    {
      localObject = new Intent("android.intent.action.VIEW");
      ((Intent)localObject).putExtra("data1", mContact.getNumber());
      ((Intent)localObject).setPackage(MessageUtils.getContactsPackageName());
      mContext.startActivity((Intent)localObject);
      return;
    }
    Object localObject = new Intent("android.intent.action.VIEW");
    ((Intent)localObject).putExtra("number", mContact.getNumber());
    ((Intent)localObject).setPackage(MessageUtils.getContactsPackageName());
    mContext.startActivity((Intent)localObject);
  }
  
  private void onVoipCall()
  {
    if (mContact != null)
    {
      Intent localIntent = new Intent("com.miui.voip.action.MAKE_VOIP_CALL");
      localIntent.setPackage("com.miui.voip");
      localIntent.putExtra("extra_make_voip_call_from", 3);
      localIntent.putExtra("extra_contact_name", mContact.getName());
      localIntent.putExtra("extra_numbers", new String[] { mContact.getNumber() });
      mContext.startService(localIntent);
    }
  }
  
  private void setUpB2cContactInfo()
  {
    mFromView.setText(mContact.getName());
    mPhoneLocation.setText(2131362400);
    mPhoneLocation.setVisibility(0);
    mPhoneTag.setVisibility(8);
    mFetionPrefix.setVisibility(8);
  }
  
  private void setUpContactInfo()
  {
    int i = 0;
    Object localObject = mContact.getName();
    String str1 = mContact.getNumber();
    String str2 = mContact.getDisplayTag();
    String str3 = PhoneNumberUtils.parseTelocationString(mContext, str1);
    if (((String)localObject).equals(str1)) {
      if (!TextUtils.isEmpty(str3))
      {
        mPhoneLocation.setText(str3);
        mPhoneLocation.setVisibility(0);
        if (TextUtils.isEmpty(str2)) {
          break label172;
        }
        if (TextUtils.isEmpty(str3)) {
          break label160;
        }
        mPhoneTag.setText(" | " + str2);
        label114:
        mPhoneTag.setVisibility(0);
        label122:
        mFromView.setText(str1);
        localObject = mFetionPrefix;
        if (!AddressUtils.isFetionNumber(str1)) {
          break label263;
        }
      }
    }
    for (;;)
    {
      ((View)localObject).setVisibility(i);
      return;
      mPhoneLocation.setVisibility(8);
      break;
      label160:
      mPhoneTag.setText(str2);
      break label114;
      label172:
      mPhoneTag.setVisibility(8);
      break label122;
      mFromView.setText((CharSequence)localObject);
      if (!TextUtils.isEmpty(str3)) {
        mPhoneLocation.setText(str1 + " " + str3);
      }
      for (;;)
      {
        mPhoneLocation.setVisibility(0);
        mPhoneTag.setVisibility(8);
        break;
        mPhoneLocation.setText(str1);
      }
      label263:
      i = 8;
    }
  }
  
  private void updateYellowPageNumber()
  {
    if (mContact != null) {
      YellowPageUtils.updatePhoneInfo(mContext, mContact.getNumber());
    }
  }
  
  public void hideCallMenu()
  {
    mCallContact.setVisibility(8);
  }
  
  protected void onFinishInflate()
  {
    super.onFinishInflate();
    mHomeView = findViewById(2131820651);
    mMainTextView = findViewById(2131820867);
    mPhoneLocation = ((TextView)findViewById(2131820870));
    mPhoneTag = ((TextView)findViewById(2131820871));
    mFromView = ((TextView)findViewById(2131820586));
    mFetionPrefix = findViewById(2131820585);
    mCallContact = ((ImageView)findViewById(2131820872));
    mShowContact = ((ImageView)findViewById(2131820873));
    mShowContact.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        SingleRecipientConversationHeader.this.onMenuItemIdContactLook();
      }
    });
    if ((mContext instanceof B2cMessageConversationActivity)) {
      mShowContact.setVisibility(8);
    }
    for (;;)
    {
      mCallContact.setVisibility(0);
      updateState();
      return;
      mShowContact.setVisibility(0);
    }
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
    if ((mPhoneTag.getVisibility() == 8) && (mPhoneLocation.getVisibility() == 8))
    {
      localLayoutParams = (RelativeLayout.LayoutParams)mHomeView.getLayoutParams();
      localLayoutParams.addRule(6, 2131820868);
      mHomeView.setLayoutParams(localLayoutParams);
      paramInt1 = mHomeView.getTop() + (mHomeView.getMeasuredHeight() - mMainTextView.getMeasuredHeight()) / 2;
      mMainTextView.setTop(paramInt1);
      mMainTextView.setBottom(mMainTextView.getMeasuredHeight() + paramInt1);
    }
    do
    {
      return;
      paramInt2 = Math.max(mMainTextView.getTop() + (mMainTextView.getMeasuredHeight() - mHomeView.getMeasuredHeight()) / 2, 0);
      mHomeView.layout(paramInt1, paramInt2, mHomeView.getMeasuredWidth() + paramInt1, mHomeView.getMeasuredHeight() + paramInt2);
    } while (mPhoneLocation.getVisibility() != 8);
    RelativeLayout.LayoutParams localLayoutParams = (RelativeLayout.LayoutParams)mPhoneTag.getLayoutParams();
    localLayoutParams.addRule(5, 2131820867);
    mPhoneTag.setLayoutParams(localLayoutParams);
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    super.onMeasure(paramInt1, paramInt2);
    paramInt1 = mMainTextView.getMeasuredWidth();
    if ((mPhoneTag.getVisibility() == 0) && (mPhoneLocation.getVisibility() == 0))
    {
      mPhoneTag.measure(View.MeasureSpec.makeMeasureSpec(paramInt1 / 2, Integer.MIN_VALUE), 0);
      paramInt2 = mPhoneTag.getMeasuredWidth();
      mPhoneLocation.measure(View.MeasureSpec.makeMeasureSpec(paramInt1 - paramInt2, Integer.MIN_VALUE), 0);
    }
  }
  
  public void updateState()
  {
    if (mCallContact != null)
    {
      if (mVoipStyle)
      {
        mCallContact.setImageResource(2130837715);
        mCallContact.setOnClickListener(mVoipClickListener);
      }
    }
    else {
      return;
    }
    mCallContact.setImageResource(2130837714);
    mCallContact.setOnClickListener(mCallClickListener);
  }
  
  public void updateState(boolean paramBoolean)
  {
    mVoipStyle = paramBoolean;
    updateState();
  }
  
  public void updateTitle(ContactList paramContactList)
  {
    if ((paramContactList == null) || (paramContactList.size() == 0))
    {
      LogTag.verbose("updateTitle mContact is null", new Object[0]);
      return;
    }
    mContact = ((Contact)paramContactList.get(0));
    int i;
    if (isB2cContact())
    {
      setUpB2cContactInfo();
      paramContactList = mShowContact;
      if (mContact.isYellowPageB2cNumber())
      {
        i = 0;
        paramContactList.setVisibility(i);
      }
    }
    for (;;)
    {
      updateYellowPageNumber();
      return;
      i = 8;
      break;
      setUpContactInfo();
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.SingleRecipientConversationHeader
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */