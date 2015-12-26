package com.android.mms.ui;

import android.content.res.MiuiConfiguration;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import com.android.mms.data.ContactList;
import com.android.mms.data.WorkingMessage;
import com.android.mms.util.MSimUtils;
import com.xiaomi.mms.transaction.MxActivateService;
import com.xiaomi.mms.transaction.PushSession;
import java.util.Map;

class MessageEditableActivityBase$27
  implements Runnable
{
  MessageEditableActivityBase$27(MessageEditableActivityBase paramMessageEditableActivityBase) {}
  
  public void run()
  {
    Log.v("MessageEditableActivityBase", "run mSwitchMsgTypeRunnable");
    ContactList localContactList = this$0.getRecipients();
    if (localContactList == null)
    {
      Log.v("MessageEditableActivityBase", "mSwitchMsgTypeRunnable recipients is null");
      return;
    }
    boolean bool4 = this$0.mWorkingMessage.requiresMms();
    this$0.mMxStyle = false;
    boolean bool1 = false;
    boolean bool2 = false;
    if (MSimUtils.isSimInserted(0)) {
      bool1 = MessageEditableActivityBase.access$1400(this$0, 0);
    }
    if (MSimUtils.isSimInserted(1)) {
      bool2 = MessageEditableActivityBase.access$1400(this$0, 1);
    }
    int i;
    label119:
    boolean bool3;
    int j;
    label232:
    int k;
    label258:
    label307:
    Object localObject;
    if (MessageEditableActivityBase.access$1500(this$0))
    {
      this$0.mMxStyle = true;
      if (MiuiConfiguration.getScaleMode() != 11) {
        break label619;
      }
      i = 1;
      if (!MessageEditableActivityBase.access$1500(this$0)) {
        break label676;
      }
      this$0.mEditorContainer.setBackgroundResource(2130837588);
      if (this$0.mSendButton != null) {
        this$0.mSendButton.setBackgroundResource(2130837941);
      }
      this$0.mTextEditor.setCursorDrawableRes(MessageEditableActivityBase.access$1600(this$0));
      bool3 = MxActivateService.isMxEnabled(this$0.getApplicationContext(), 0);
      boolean bool5 = MxActivateService.isMxEnabled(this$0.getApplicationContext(), 1);
      if ((!MSimUtils.isMSimSlotIdValid(0)) || (!PushSession.getInstance(this$0.getApplicationContext()).isConnected(0))) {
        break label624;
      }
      j = 1;
      if ((!MSimUtils.isMSimSlotIdValid(1)) || (!PushSession.getInstance(this$0.getApplicationContext()).isConnected(1))) {
        break label629;
      }
      k = 1;
      if (((!bool3) || (!bool5) || (j == 0) || (k == 0)) && ((this$0.mUseSlotId != 0) || (j == 0)) && ((this$0.mUseSlotId != 1) || (k == 0))) {
        break label634;
      }
      j = 1;
      if (j == 0) {
        break label645;
      }
      localObject = this$0.mTextEditor;
      if (i == 0) {
        break label639;
      }
      i = 2131362182;
      label327:
      ((EditText)localObject).setHint(i);
      label333:
      if (!bool1) {
        break label955;
      }
      if (!bool4) {
        break label942;
      }
      MessageEditableActivityBase.access$1700(this$0)[0] = 1L;
      label353:
      if (this$0.mSendButton1 != null) {
        this$0.mSendButton1.setBackgroundResource(2130837958);
      }
      label375:
      if (!bool2) {
        break label1053;
      }
      if (!bool4) {
        break label1040;
      }
      MessageEditableActivityBase.access$1700(this$0)[1] = 1L;
      label395:
      if (MessageEditableActivityBase.access$1900(this$0) != null) {
        MessageEditableActivityBase.access$1900(this$0).setBackgroundResource(2130837958);
      }
    }
    label619:
    label624:
    label629:
    label634:
    label639:
    label645:
    label676:
    label942:
    label955:
    label1038:
    label1040:
    label1053:
    label1136:
    for (;;)
    {
      Log.v("MessageEditableActivityBase", "switch to mx mode: " + this$0.mMxStyle + ", recipients: " + localContactList.size() + ", sms: " + MessageEditableActivityBase.access$2000(this$0).size() + ", mms: " + MessageEditableActivityBase.access$2100(this$0).size());
      this$0.postUpdateSendButtonState();
      return;
      if (MSimUtils.isMSimInserted())
      {
        localObject = this$0;
        if (((bool1) && (bool2)) || ((this$0.mUseSlotId == 0) && (bool1)) || ((this$0.mUseSlotId == 1) && (bool2))) {}
        for (bool3 = true;; bool3 = false)
        {
          mMxStyle = bool3;
          break;
        }
      }
      if (MSimUtils.isSimInserted(0))
      {
        this$0.mMxStyle = bool1;
        break;
      }
      if (!MSimUtils.isSimInserted(1)) {
        break;
      }
      this$0.mMxStyle = bool2;
      break;
      i = 0;
      break label119;
      j = 0;
      break label232;
      k = 0;
      break label258;
      j = 0;
      break label307;
      i = 2131362183;
      break label327;
      localObject = this$0.mTextEditor;
      if (i != 0) {}
      for (i = 2131362407;; i = 2131362406)
      {
        ((EditText)localObject).setHint(i);
        break;
      }
      if (this$0.mMxStyle)
      {
        this$0.mEditorContainer.setBackgroundResource(2130837589);
        if (this$0.mSendButton != null) {
          this$0.mSendButton.setBackgroundResource(2130837951);
        }
        this$0.mTextEditor.setCursorDrawableRes(2130838007);
        if (localContactList.size() > 0)
        {
          if (i != 0)
          {
            this$0.mTextEditor.setHint(2131362182);
            break label333;
          }
          this$0.mTextEditor.setHint(2131362183);
          break label333;
        }
        this$0.mTextEditor.setHint("");
        break label333;
      }
      this$0.mEditorContainer.setBackgroundResource(2130837588);
      if (this$0.mSendButton != null) {
        this$0.mSendButton.setBackgroundResource(2130837941);
      }
      this$0.mTextEditor.setCursorDrawableRes(MessageEditableActivityBase.access$1600(this$0));
      if (i != 0)
      {
        localEditText = this$0.mTextEditor;
        if (bool4) {}
        for (localObject = this$0.getString(2131362181);; localObject = this$0.getString(2131362180))
        {
          localEditText.setHint((CharSequence)localObject);
          break;
        }
      }
      EditText localEditText = this$0.mTextEditor;
      if (bool4) {}
      for (localObject = this$0.getString(2131362179);; localObject = this$0.getString(2131362178))
      {
        localEditText.setHint((CharSequence)localObject);
        break;
      }
      MessageEditableActivityBase.access$1800(this$0)[0] = 1L;
      break label353;
      if (bool4) {
        if (MessageEditableActivityBase.access$1700(this$0)[0] == 1L) {
          MessageEditableActivityBase.access$1700(this$0)[0] = System.currentTimeMillis();
        }
      }
      for (;;)
      {
        if (this$0.mSendButton1 == null) {
          break label1038;
        }
        this$0.mSendButton1.setBackgroundResource(2130837961);
        break;
        if (MessageEditableActivityBase.access$1800(this$0)[0] == 1L) {
          MessageEditableActivityBase.access$1800(this$0)[0] = System.currentTimeMillis();
        }
      }
      break label375;
      MessageEditableActivityBase.access$1800(this$0)[1] = 1L;
      break label395;
      if (bool4) {
        if (MessageEditableActivityBase.access$1700(this$0)[1] == 1L) {
          MessageEditableActivityBase.access$1700(this$0)[1] = System.currentTimeMillis();
        }
      }
      for (;;)
      {
        if (MessageEditableActivityBase.access$1900(this$0) == null) {
          break label1136;
        }
        MessageEditableActivityBase.access$1900(this$0).setBackgroundResource(2130837961);
        break;
        if (MessageEditableActivityBase.access$1800(this$0)[1] == 1L) {
          MessageEditableActivityBase.access$1800(this$0)[1] = System.currentTimeMillis();
        }
      }
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.MessageEditableActivityBase.27
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */