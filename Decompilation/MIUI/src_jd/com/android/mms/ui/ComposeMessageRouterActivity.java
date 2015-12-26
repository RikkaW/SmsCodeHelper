package com.android.mms.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Bundle;
import android.text.TextUtils;
import com.android.mms.LogTag;
import com.android.mms.data.ContactList;
import com.android.mms.data.Conversation;

public class ComposeMessageRouterActivity
  extends Activity
{
  public static Intent createIntent(Context paramContext, long paramLong)
  {
    Intent localIntent = new Intent();
    prepareIntent(localIntent, paramLong);
    processIntent(paramContext, localIntent);
    return localIntent;
  }
  
  public static Uri createSearchResultDataUri(long paramLong1, long paramLong2, String paramString1, String paramString2)
  {
    Uri.Builder localBuilder = new Uri.Builder().scheme("sms").appendPath("conversation").appendPath(String.valueOf(paramLong1)).appendQueryParameter("select_id", String.valueOf(paramLong2));
    if (!TextUtils.isEmpty(paramString1)) {
      localBuilder.appendQueryParameter("highlight_text", paramString1);
    }
    if (!TextUtils.isEmpty(paramString2)) {
      localBuilder.appendQueryParameter("body_substitution", MessageUtils.getSnippet(paramString2, paramString1, 30, 10)).build();
    }
    return localBuilder.build();
  }
  
  public static Uri createViewOrginDataUri(long paramLong1, long paramLong2)
  {
    return createSearchResultDataUri(paramLong1, paramLong2, null, null);
  }
  
  public static void prepareIntent(Intent paramIntent, long paramLong)
  {
    if (paramLong > 0L) {
      paramIntent.putExtra("thread_id", paramLong);
    }
  }
  
  public static void processIntent(Context paramContext, Intent paramIntent)
  {
    Conversation localConversation = null;
    long l = paramIntent.getLongExtra("thread_id", -1L);
    boolean bool;
    if (l != -1L)
    {
      localConversation = Conversation.get(l);
      bool = false;
    }
    for (;;)
    {
      int i;
      if (localConversation != null)
      {
        l = localConversation.getThreadId();
        int j = localConversation.getMessageCount();
        if (localConversation.getRecipients() == null)
        {
          i = 0;
          label61:
          LogTag.debug(String.format("routing: threadId=%d, messageCount=%d, recipientCount=%d", new Object[] { Long.valueOf(l), Integer.valueOf(j), Integer.valueOf(i) }), new Object[0]);
        }
      }
      for (;;)
      {
        if ((localConversation != null) && (localConversation.getMessageCount() != 0) && (localConversation.getRecipients() != null) && (localConversation.getRecipients().size() != 0) && (localConversation.getThreadId() != 0L) && (!bool)) {
          break label255;
        }
        if (bool) {
          paramIntent.putExtra("private_recipient", true);
        }
        paramIntent.setClass(paramContext, NewMessageActivity.class);
        return;
        Object localObject = paramIntent.getData();
        if (localObject != null) {
          localConversation = Conversation.get((Uri)localObject);
        }
        for (;;)
        {
          if (localConversation == null) {
            break label311;
          }
          bool = localConversation.isPrivate();
          break;
          localObject = paramIntent.getStringExtra("address");
          if (!TextUtils.isEmpty((CharSequence)localObject)) {
            localConversation = Conversation.get(ContactList.getByNumbers((String)localObject, true));
          }
        }
        i = localConversation.getRecipients().size();
        break label61;
        LogTag.debug("routing: conv=null", new Object[0]);
      }
      label255:
      prepareIntent(paramIntent, localConversation.getThreadId());
      if (localConversation.getRecipients().size() > 1)
      {
        paramIntent.setClass(paramContext, MultipleRecipientsConversationActivity.class);
        return;
      }
      if (localConversation.isB2c())
      {
        paramIntent.setClass(paramContext, B2cMessageConversationActivity.class);
        return;
      }
      paramIntent.setClass(paramContext, SingleRecipientConversationActivity.class);
      return;
      label311:
      bool = false;
    }
  }
  
  public static void route(Context paramContext, Intent paramIntent)
  {
    processIntent(paramContext, paramIntent);
    paramContext.startActivity(paramIntent);
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    NewMessagePopupActivity.dismiss(this);
    paramBundle = getIntent();
    paramBundle.putExtra("from_activity", "ComposeMessageRouterActivity");
    route(this, paramBundle);
    finish();
    overridePendingTransition(0, 0);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.ComposeMessageRouterActivity
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */