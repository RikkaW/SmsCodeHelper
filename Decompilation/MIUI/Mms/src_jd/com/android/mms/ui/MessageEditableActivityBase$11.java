package com.android.mms.ui;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;
import com.android.mms.data.Contact;
import com.android.mms.data.ContactList;
import com.android.mms.data.Conversation;
import com.android.mms.data.WorkingMessage;
import com.xiaomi.mms.data.MxIdCache;
import com.xiaomi.mms.transaction.MxActivateService;
import java.util.Iterator;
import miui.os.Build;

class MessageEditableActivityBase$11
  implements TextWatcher
{
  private final long MX_STATUS_CHECK_DURATION = 10000L;
  private long lastCheckTime = System.currentTimeMillis();
  private int mOldEnd = 0;
  private int mOldStart = 0;
  private String mOldText = null;
  
  MessageEditableActivityBase$11(MessageEditableActivityBase paramMessageEditableActivityBase) {}
  
  public void afterTextChanged(final Editable paramEditable)
  {
    if (this$0.mWorkingMessage.requiresMms())
    {
      paramEditable = mOldText;
      final int i = mOldStart;
      final int j = mOldEnd;
      this$0.confirmRemovingChenghu(null, new Runnable()
      {
        public void run()
        {
          this$0.mTextEditor.setText(paramEditable);
          this$0.mTextEditor.setSelection(i, j);
        }
      });
    }
    if (System.currentTimeMillis() - lastCheckTime > 10000L)
    {
      lastCheckTime = System.currentTimeMillis();
      if (this$0.mConversation != null)
      {
        paramEditable = this$0.mConversation.getRecipients();
        if (paramEditable != null)
        {
          this$0.postSwitchMsgType();
          if (MxActivateService.isMxEnabled(this$0))
          {
            paramEditable = paramEditable.iterator();
            while (paramEditable.hasNext())
            {
              Contact localContact = (Contact)paramEditable.next();
              MxIdCache.getOrQuery(this$0, localContact.getMxPhoneNumber());
            }
          }
        }
      }
    }
  }
  
  public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
    mOldText = this$0.mTextEditor.getText().toString();
    mOldStart = this$0.mTextEditor.getSelectionStart();
    mOldEnd = this$0.mTextEditor.getSelectionEnd();
  }
  
  public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
    if ((Build.IS_CM_CUSTOMIZATION_TEST) && (paramCharSequence.length() > 3100))
    {
      Toast.makeText(this$0, this$0.getString(2131361850), 1).show();
      this$0.mTextEditor.setText(mOldText);
      return;
    }
    this$0.onUserInteraction();
    this$0.mWorkingMessage.setText(paramCharSequence);
    if (MessageEditableActivityBase.access$400(this$0))
    {
      Toast.makeText(this$0, this$0.getString(2131361849), 1).show();
      this$0.mWorkingMessage.setText(mOldText);
      this$0.mTextEditor.setText(mOldText);
      return;
    }
    this$0.postUpdateSendButtonState();
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.MessageEditableActivityBase.11
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */