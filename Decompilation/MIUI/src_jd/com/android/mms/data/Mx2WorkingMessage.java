package com.android.mms.data;

import android.content.ContentUris;
import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.google.android.mms.pdu.EncodedStringValue;
import com.xiaomi.mms.data.Mx2MessageModel;
import com.xiaomi.mms.utils.Mx2PduPersister;

public class Mx2WorkingMessage
  extends WorkingMessage
{
  private Mx2MessageModel mMxMessage;
  
  public Mx2WorkingMessage(WorkingMessage.MessageStatusListener paramMessageStatusListener)
  {
    super(paramMessageStatusListener);
  }
  
  private static Uri createDraftMx2Message(Context paramContext, Mx2MessageModel paramMx2MessageModel)
  {
    paramMx2MessageModel.setBoxId(3);
    return Mx2PduPersister.insertMxMessage(paramContext, paramMx2MessageModel);
  }
  
  public static Mx2WorkingMessage createEmpty(WorkingMessage.MessageStatusListener paramMessageStatusListener)
  {
    return new Mx2WorkingMessage(paramMessageStatusListener);
  }
  
  public static Mx2WorkingMessage loadDraft(WorkingMessage.MessageStatusListener paramMessageStatusListener, Conversation paramConversation)
  {
    Mx2WorkingMessage localMx2WorkingMessage = new Mx2WorkingMessage(paramMessageStatusListener);
    if (localMx2WorkingMessage.loadFromConversation(paramConversation)) {
      return localMx2WorkingMessage;
    }
    return createEmpty(paramMessageStatusListener);
  }
  
  private static int updateDraftMx2Message(Context paramContext, Uri paramUri, Mx2MessageModel paramMx2MessageModel)
  {
    return Mx2PduPersister.updateMxMessage(paramContext, paramMx2MessageModel, ContentUris.parseId(paramUri));
  }
  
  public void persistMxMessage()
  {
    if (!TextUtils.isEmpty(mText)) {
      mMxMessage.setBody(mText.toString());
    }
    long l;
    if (mMessageUri == null)
    {
      l = mConversation.getThreadId();
      mMxMessage.setThreadId(l);
      mMessageUri = createDraftMx2Message(mContext, mMxMessage);
    }
    for (;;)
    {
      if (mConversation != null)
      {
        l = ContentUris.parseId(mMessageUri);
        if (l > 0L)
        {
          Object localObject = mConversation.getRecipients();
          if ((localObject != null) && (!((ContactList)localObject).isEmpty()))
          {
            localObject = EncodedStringValue.encodeStrings(((ContactList)localObject).getNumbers());
            Mx2PduPersister.updateAddress(mContext, l, 151, (EncodedStringValue[])localObject);
          }
        }
      }
      mHasMmsDraft = true;
      return;
      updateDraftMx2Message(mContext, mMessageUri, mMxMessage);
    }
  }
  
  public void removeAttachment(boolean paramBoolean)
  {
    mMxMessage = null;
    updateState(32, false, paramBoolean);
    super.removeAttachment(paramBoolean);
  }
  
  public void setMessageUri(Uri paramUri)
  {
    mMessageUri = paramUri;
  }
  
  public void setMxMessage(Mx2MessageModel paramMx2MessageModel)
  {
    mMxMessage = paramMx2MessageModel;
  }
}

/* Location:
 * Qualified Name:     com.android.mms.data.Mx2WorkingMessage
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */