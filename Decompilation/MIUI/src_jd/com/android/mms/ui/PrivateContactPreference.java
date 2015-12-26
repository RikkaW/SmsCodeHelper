package com.android.mms.ui;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.android.mms.data.Contact;
import miui.preference.ValuePreference;

public class PrivateContactPreference
  extends ValuePreference
{
  private Contact mContact;
  private Context mContext;
  private ImageView mDeleteBtn;
  View.OnClickListener mDeleteClickListener = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      if (mOnClickDeleteBtnListener != null) {
        mOnClickDeleteBtnListener.onClick(mId, mContact);
      }
    }
  };
  private Handler mHandler = new Handler();
  private long mId;
  private OnClickDeleteBtnListener mOnClickDeleteBtnListener;
  private ImageView mPhotoView;
  
  public PrivateContactPreference(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public PrivateContactPreference(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    mContext = paramContext;
    setLayoutResource(2130968691);
  }
  
  protected void onBindView(View paramView)
  {
    super.onBindView(paramView);
    mPhotoView.setVisibility(0);
    if ((mContact.existsInDatabase()) || (mContact.isYellowPageNumber()))
    {
      Contact.loadPhoto(mPhotoView, mContact);
      return;
    }
    mPhotoView.setImageResource(285343796);
  }
  
  protected View onCreateView(ViewGroup paramViewGroup)
  {
    paramViewGroup = super.onCreateView(paramViewGroup);
    mPhotoView = ((ImageView)paramViewGroup.findViewById(16908294));
    mDeleteBtn = ((ImageView)paramViewGroup.findViewById(2131820848));
    mDeleteBtn.setClickable(true);
    mDeleteBtn.setFocusable(true);
    mDeleteBtn.setOnClickListener(mDeleteClickListener);
    return paramViewGroup;
  }
  
  public void refreshShowInfo()
  {
    mHandler.post(new Runnable()
    {
      public void run()
      {
        if (mContact != null)
        {
          setTitle(mContact.getName());
          setSummary(mContact.getNumber());
        }
      }
    });
  }
  
  public void setOnDeleteBtnClickListener(OnClickDeleteBtnListener paramOnClickDeleteBtnListener)
  {
    mOnClickDeleteBtnListener = paramOnClickDeleteBtnListener;
  }
  
  public void setShowInfo(long paramLong, Contact paramContact)
  {
    mId = paramLong;
    mContact = paramContact;
    refreshShowInfo();
  }
  
  public static abstract interface OnClickDeleteBtnListener
  {
    public abstract void onClick(long paramLong, Contact paramContact);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.PrivateContactPreference
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */