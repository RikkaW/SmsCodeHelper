package com.android.mms.ui;

import android.content.ContentProviderOperation;
import android.content.ContentProviderOperation.Builder;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.OperationApplicationException;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.AsyncTask;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import com.android.mms.MmsApp;
import com.android.mms.data.Contact;
import com.android.mms.data.ContactList;
import com.android.mms.data.Conversation;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import miui.app.ProgressDialog;
import miui.provider.ExtraTelephony;
import miui.provider.ExtraTelephony.MmsSms;
import miui.provider.ExtraTelephony.Phonelist;
import miui.telephony.PhoneNumberUtils.PhoneNumber;

class ConversationFragment$14$1
  extends AsyncTask<Void, Void, Void>
{
  private ContentResolver mContentResolver;
  private Context mContext;
  private Set<Long> mThreadIds;
  
  ConversationFragment$14$1(ConversationFragment.14 param14) {}
  
  private Uri getBlockedConvUriByNumber(String paramString)
  {
    return ContentUris.withAppendedId(ExtraTelephony.MmsSms.BLOCKED_CONVERSATION_CONTENT_URI.buildUpon().appendQueryParameter("blocked_conv_addr", paramString).build(), 0L);
  }
  
  protected Void doInBackground(Void... paramVarArgs)
  {
    paramVarArgs = new ArrayList();
    ArrayList localArrayList = new ArrayList();
    int j = 0;
    int i = 0;
    Iterator localIterator = mThreadIds.iterator();
    while (localIterator.hasNext())
    {
      Object localObject = (Long)localIterator.next();
      localObject = ((Contact)Conversation.get(MmsApp.getApp(), ((Long)localObject).longValue()).getRecipients().get(0)).getNumber().replace("+86", "");
      if (TextUtils.isEmpty((CharSequence)localObject))
      {
        Log.d("ConversationFragment", "number is null");
      }
      else
      {
        localObject = PhoneNumberUtils.PhoneNumber.parse((CharSequence)localObject).getNormalizedNumber(false, true);
        ContentValues localContentValues;
        if (!ExtraTelephony.isInBlacklist(mContext, (String)localObject))
        {
          localContentValues = new ContentValues(1);
          localContentValues.put("number", (String)localObject);
          localContentValues.put("type", "1");
          localContentValues.put("state", Integer.valueOf(0));
          localContentValues.put("isdisplay", Integer.valueOf(0));
          localContentValues.put("notes", Integer.valueOf(0));
          paramVarArgs.add(ContentProviderOperation.newInsert(ExtraTelephony.Phonelist.CONTENT_URI).withValues(localContentValues).build());
        }
        for (;;)
        {
          localContentValues = new ContentValues(1);
          localContentValues.put("block_type", Integer.valueOf(5));
          localArrayList.add(ContentProviderOperation.newUpdate(getBlockedConvUriByNumber((String)localObject)).withValues(localContentValues).build());
          int k = paramVarArgs.size();
          int m = localArrayList.size();
          if (k <= 10)
          {
            j = k;
            i = m;
            if (m <= 10) {
              break;
            }
          }
          if (k > 0) {}
          try
          {
            mContentResolver.applyBatch("antispam", paramVarArgs);
            mContentResolver.applyBatch("mms-sms", localArrayList);
          }
          catch (RemoteException localRemoteException)
          {
            for (;;)
            {
              localRemoteException.printStackTrace();
            }
          }
          catch (OperationApplicationException localOperationApplicationException)
          {
            for (;;)
            {
              localOperationApplicationException.printStackTrace();
            }
          }
          paramVarArgs.clear();
          localArrayList.clear();
          j = k;
          i = m;
          break;
          localContentValues = new ContentValues(1);
          localContentValues.put("state", Integer.valueOf(0));
          paramVarArgs.add(ContentProviderOperation.newUpdate(ExtraTelephony.Phonelist.CONTENT_URI).withSelection("number=?", new String[] { localObject }).withValues(localContentValues).build());
          Log.d("ConversationFragment", "number has been in black list");
        }
      }
    }
    if (((j <= 0) && (i <= 0)) || (j > 0)) {}
    try
    {
      mContentResolver.applyBatch("antispam", paramVarArgs);
      mContentResolver.applyBatch("mms-sms", localArrayList);
    }
    catch (RemoteException paramVarArgs)
    {
      for (;;)
      {
        paramVarArgs.printStackTrace();
      }
    }
    catch (OperationApplicationException paramVarArgs)
    {
      for (;;)
      {
        paramVarArgs.printStackTrace();
      }
    }
    return null;
  }
  
  protected void onPostExecute(Void paramVoid)
  {
    ConversationFragment.access$2502(this$1.this$0, null);
    if (ConversationFragment.access$2600() != null) {
      ConversationFragment.access$2600().dismiss();
    }
    ConversationFragment.access$2602(null);
    this$1.this$0.mListView.exitEditMode();
  }
  
  protected void onPreExecute()
  {
    mContext = this$1.val$context;
    mThreadIds = this$1.val$threadIds;
    mContentResolver = this$1.val$context.getContentResolver();
    ConversationFragment.access$2602(ProgressDialog.show(mContext, null, mContext.getString(2131362263), true, false));
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.ConversationFragment.14.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */