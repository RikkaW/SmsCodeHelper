package com.android.mms.ui;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.android.mms.data.Contact;
import com.android.mms.data.ContactList;

class MultipleRecipientsConversationHeader$MultiRecipientContactInfoListAdapter
  extends BaseAdapter
{
  private ContactList mContactList;
  private Context mContext;
  
  public MultipleRecipientsConversationHeader$MultiRecipientContactInfoListAdapter(MultipleRecipientsConversationHeader paramMultipleRecipientsConversationHeader, Context paramContext)
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
    for (paramView = new MultipleRecipientsConversationHeader.MultiRecipientContactInfoItem(this$0, mContext);; paramView = (MultipleRecipientsConversationHeader.MultiRecipientContactInfoItem)paramView)
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

/* Location:
 * Qualified Name:     com.android.mms.ui.MultipleRecipientsConversationHeader.MultiRecipientContactInfoListAdapter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */