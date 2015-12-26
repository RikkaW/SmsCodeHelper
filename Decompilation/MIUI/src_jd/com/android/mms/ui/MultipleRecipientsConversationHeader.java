package com.android.mms.ui;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.android.mms.data.Contact;
import com.android.mms.data.ContactList;

public class MultipleRecipientsConversationHeader
  extends RelativeLayout
  implements View.OnClickListener
{
  private ContactList mContactList;
  private final Context mContext;
  private Button mHomeBtn;
  private MultiRecipientContactInfoListAdapter mMultiRecipientContactInfoListAdapter;
  private TextView mTitle;
  private ImageView mTitleArrow;
  private View mTitleBar;
  private TextView mTitleCount;
  private PopupWindow mTitlePopupWindow;
  
  public MultipleRecipientsConversationHeader(Context paramContext)
  {
    super(paramContext);
    mContext = paramContext;
  }
  
  public MultipleRecipientsConversationHeader(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    mContext = paramContext;
  }
  
  private void showTitleContactInfo(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      if (mTitlePopupWindow == null)
      {
        mMultiRecipientContactInfoListAdapter = new MultiRecipientContactInfoListAdapter(mContext);
        localView = LayoutInflater.from(mContext).inflate(2130968668, null);
        ((ListView)localView.findViewById(2131820796)).setAdapter(mMultiRecipientContactInfoListAdapter);
        mMultiRecipientContactInfoListAdapter.setContactList(mContactList);
        mTitlePopupWindow = new PopupWindow(localView, -1, -2, true);
        mTitlePopupWindow.setOutsideTouchable(true);
        mTitlePopupWindow.setBackgroundDrawable(new ColorDrawable());
        mTitlePopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener()
        {
          public void onDismiss()
          {
            mTitleArrow.setImageResource(2130837967);
          }
        });
      }
      mTitlePopupWindow.showAsDropDown(mTitleBar, 0, 0);
      mTitleArrow.setImageResource(2130837964);
    }
    while (mTitlePopupWindow == null)
    {
      View localView;
      return;
    }
    mTitlePopupWindow.dismiss();
  }
  
  public void onClick(View paramView)
  {
    if (paramView == mTitleBar)
    {
      if ((mTitlePopupWindow == null) || (!mTitlePopupWindow.isShowing())) {
        showTitleContactInfo(true);
      }
    }
    else {
      return;
    }
    showTitleContactInfo(false);
  }
  
  protected void onFinishInflate()
  {
    super.onFinishInflate();
    mTitleBar = this;
    mTitleBar.setOnClickListener(this);
    mHomeBtn = ((Button)findViewById(2131820651));
    mTitle = ((TextView)findViewById(2131820800));
    mTitleCount = ((TextView)findViewById(2131820801));
    mTitleArrow = ((ImageView)findViewById(2131820799));
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
    paramInt2 = Math.max(mTitle.getTop() + (mTitle.getMeasuredHeight() - mHomeBtn.getMeasuredHeight()) / 2, 0);
    mHomeBtn.layout(paramInt1, paramInt2, mHomeBtn.getMeasuredWidth() + paramInt1, mHomeBtn.getMeasuredHeight() + paramInt2);
  }
  
  public void updateTitle(ContactList paramContactList)
  {
    mContactList = paramContactList;
    String str2 = "";
    int i = 0;
    String str1 = str2;
    int j;
    if (paramContactList != null)
    {
      j = paramContactList.size();
      i = j;
      str1 = str2;
      switch (j)
      {
      default: 
        str1 = paramContactList.formatNames(", ");
      }
    }
    for (i = j;; i = j)
    {
      if (mMultiRecipientContactInfoListAdapter != null) {
        mMultiRecipientContactInfoListAdapter.notifyDataSetChanged();
      }
      mTitle.setText(str1);
      if (i > 0) {
        mTitleCount.setText(mContext.getString(2131362054, new Object[] { Integer.valueOf(i) }));
      }
      return;
      str1 = ((Contact)paramContactList.get(0)).getNameAndNumber();
    }
  }
  
  private class MultiRecipientContactInfoItem
    extends RelativeLayout
  {
    private TextView mNameView;
    private TextView mNumberView;
    
    public MultiRecipientContactInfoItem(Context paramContext)
    {
      super();
      inflate(paramContext, 2130968669, this);
      mNameView = ((TextView)findViewById(2131820752));
      mNumberView = ((TextView)findViewById(2131820753));
    }
    
    public void bind(Contact paramContact)
    {
      mNameView.setText(paramContact.getName());
      mNumberView.setText(paramContact.getNumber());
      if (paramContact.getName().equals(paramContact.getNumber()))
      {
        mNumberView.setVisibility(8);
        return;
      }
      mNumberView.setVisibility(0);
    }
  }
  
  private class MultiRecipientContactInfoListAdapter
    extends BaseAdapter
  {
    private ContactList mContactList;
    private Context mContext;
    
    public MultiRecipientContactInfoListAdapter(Context paramContext)
    {
      mContext = paramContext;
      mContactList = new ContactList();
    }
    
    public int getCount()
    {
      return mContactList.size();
    }
    
    public Contact getItem(int paramInt)
    {
      return (Contact)mContactList.get(paramInt);
    }
    
    public long getItemId(int paramInt)
    {
      return paramInt;
    }
    
    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      if ((paramView == null) || (!(paramView instanceof MultipleRecipientsConversationHeader.MultiRecipientContactInfoItem))) {}
      for (paramView = new MultipleRecipientsConversationHeader.MultiRecipientContactInfoItem(MultipleRecipientsConversationHeader.this, mContext);; paramView = (MultipleRecipientsConversationHeader.MultiRecipientContactInfoItem)paramView)
      {
        paramView.bind(getItem(paramInt));
        return paramView;
      }
    }
    
    void setContactList(ContactList paramContactList)
    {
      if (paramContactList != null) {
        mContactList.addAll(paramContactList);
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.MultipleRecipientsConversationHeader
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */