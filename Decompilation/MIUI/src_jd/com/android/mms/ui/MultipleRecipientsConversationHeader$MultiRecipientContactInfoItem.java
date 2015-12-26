package com.android.mms.ui;

import android.content.Context;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.android.mms.data.Contact;

class MultipleRecipientsConversationHeader$MultiRecipientContactInfoItem
  extends RelativeLayout
{
  private TextView mNameView;
  private TextView mNumberView;
  
  public MultipleRecipientsConversationHeader$MultiRecipientContactInfoItem(MultipleRecipientsConversationHeader paramMultipleRecipientsConversationHeader, Context paramContext)
  {
    super(paramContext);
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

/* Location:
 * Qualified Name:     com.android.mms.ui.MultipleRecipientsConversationHeader.MultiRecipientContactInfoItem
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */