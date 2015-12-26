package com.android.mms.ui;

import android.app.Activity;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SqliteWrapper;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.AsyncTask;
import android.provider.Telephony.MmsSms;
import android.text.TextUtils;
import android.widget.TextView;
import com.android.mms.data.Contact;
import com.android.mms.data.ContactList;
import com.android.mms.data.Conversation;
import com.google.android.collect.Lists;
import com.google.android.collect.Sets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

class SearchFragment$SearchAdapter$1
  extends AsyncTask<Void, Void, Void>
{
  private String mQueryInTask = SearchFragment.SearchAdapter.access$400(this$1);
  private ContactList mResultContacts;
  private ArrayList<Conversation> mResultConversationForMessages;
  private ArrayList<Conversation> mResultConversations;
  private Cursor mResultCursor;
  private HashSet<String> mResultDuplicatedNames;
  private HashMap<Long, ArrayList<Integer>> mResultMsgPos;
  private ArrayList<Long> mResultMsgThreadIds;
  
  SearchFragment$SearchAdapter$1(SearchFragment.SearchAdapter paramSearchAdapter) {}
  
  protected Void doInBackground(Void... paramVarArgs)
  {
    if (TextUtils.isEmpty(mQueryInTask)) {
      return null;
    }
    Object localObject1 = Telephony.MmsSms.SEARCH_URI.buildUpon().appendQueryParameter("pattern", mQueryInTask);
    if (SearchFragment.access$500(this$1.this$0)) {}
    for (paramVarArgs = "1";; paramVarArgs = "0")
    {
      paramVarArgs = ((Uri.Builder)localObject1).appendQueryParameter("privacy_flag", paramVarArgs).build();
      mResultCursor = SqliteWrapper.query(SearchFragment.access$200(this$1.this$0), SearchFragment.access$200(this$1.this$0).getContentResolver(), paramVarArgs, null, null, null, null);
      if (mResultCursor != null) {
        break;
      }
      return null;
    }
    if (SearchFragment.SearchAdapter.access$600(this$1) != this) {
      return null;
    }
    int j = mResultCursor.getColumnIndex("thread_id");
    mResultConversationForMessages = Lists.newArrayList();
    mResultMsgPos = new HashMap();
    mResultMsgThreadIds = Lists.newArrayList();
    mResultCursor.moveToPosition(-1);
    int i = 0;
    long l;
    if (mResultCursor.moveToNext())
    {
      l = mResultCursor.getLong(j);
      if (mResultMsgPos.containsKey(Long.valueOf(l))) {
        ((ArrayList)mResultMsgPos.get(Long.valueOf(l))).add(Integer.valueOf(i));
      }
      for (;;)
      {
        i += 1;
        break;
        paramVarArgs = Lists.newArrayList();
        paramVarArgs.add(Integer.valueOf(i));
        mResultMsgPos.put(Long.valueOf(l), paramVarArgs);
        mResultMsgThreadIds.add(Long.valueOf(l));
      }
    }
    paramVarArgs = mResultMsgThreadIds.iterator();
    while (paramVarArgs.hasNext())
    {
      l = ((Long)paramVarArgs.next()).longValue();
      j = ((ArrayList)mResultMsgPos.get(Long.valueOf(l))).size();
      localObject1 = Conversation.get(l);
      i = 0;
      while (i < j)
      {
        mResultConversationForMessages.add(localObject1);
        i += 1;
      }
    }
    mResultConversations = Conversation.searchForConversations(SearchFragment.access$200(this$1.this$0), mQueryInTask, SearchFragment.access$500(this$1.this$0));
    paramVarArgs = Sets.newHashSet();
    localObject1 = Sets.newHashSet();
    mResultDuplicatedNames = Sets.newHashSet();
    Object localObject2 = mResultConversations.iterator();
    while (((Iterator)localObject2).hasNext())
    {
      Contact localContact = (Contact)((Conversation)((Iterator)localObject2).next()).getRecipients().get(0);
      String str = localContact.getName();
      ((HashSet)localObject1).add(localContact);
      if (paramVarArgs.contains(str)) {
        mResultDuplicatedNames.add(str);
      } else {
        paramVarArgs.add(str);
      }
    }
    mResultContacts = Contact.searchForContacts(mQueryInTask);
    mResultContacts.removeAll((Collection)localObject1);
    localObject1 = mResultContacts.iterator();
    while (((Iterator)localObject1).hasNext())
    {
      localObject2 = ((Contact)((Iterator)localObject1).next()).getName();
      if (paramVarArgs.contains(localObject2)) {
        mResultDuplicatedNames.add(localObject2);
      } else {
        paramVarArgs.add(localObject2);
      }
    }
    return null;
  }
  
  protected void onPostExecute(Void paramVoid)
  {
    if ((SearchFragment.SearchAdapter.access$600(this$1) != this) || (!this$1.this$0.isAdded()))
    {
      if (mResultCursor != null) {
        mResultCursor.close();
      }
      return;
    }
    SearchFragment.SearchAdapter.access$602(this$1, null);
    this$1.closeCursor();
    SearchFragment.SearchAdapter.access$702(this$1, mResultCursor);
    SearchFragment.SearchAdapter.access$802(this$1, mResultConversations);
    SearchFragment.SearchAdapter.access$902(this$1, mResultConversationForMessages);
    SearchFragment.SearchAdapter.access$1002(this$1, mResultDuplicatedNames);
    SearchFragment.SearchAdapter.access$1102(this$1, mResultContacts);
    SearchFragment.SearchAdapter.access$1200(this$1).clear();
    SearchFragment.SearchAdapter.access$1302(this$1, Lists.newArrayList());
    if ((mResultMsgThreadIds != null) && (mResultMsgPos != null))
    {
      paramVoid = mResultMsgThreadIds.iterator();
      while (paramVoid.hasNext())
      {
        long l = ((Long)paramVoid.next()).longValue();
        Iterator localIterator = ((ArrayList)mResultMsgPos.get(Long.valueOf(l))).iterator();
        while (localIterator.hasNext())
        {
          int i = ((Integer)localIterator.next()).intValue();
          SearchFragment.SearchAdapter.access$1300(this$1).add(Integer.valueOf(i));
        }
      }
    }
    if (SearchFragment.SearchAdapter.access$700(this$1) != null)
    {
      SearchFragment.SearchAdapter.access$1402(this$1, SearchFragment.SearchAdapter.access$700(this$1).getColumnIndex("thread_id"));
      SearchFragment.SearchAdapter.access$1502(this$1, SearchFragment.SearchAdapter.access$700(this$1).getColumnIndex("body"));
      SearchFragment.SearchAdapter.access$1602(this$1, SearchFragment.SearchAdapter.access$700(this$1).getColumnIndex("sub"));
      SearchFragment.SearchAdapter.access$1702(this$1, SearchFragment.SearchAdapter.access$700(this$1).getColumnIndex("_id"));
      SearchFragment.SearchAdapter.access$1802(this$1, SearchFragment.SearchAdapter.access$700(this$1).getColumnIndex("date"));
    }
    SearchFragment.access$1900(this$1.this$0).setText(this$1.this$0.getResources().getQuantityString(2131623939, this$1.getCount(), new Object[] { Integer.valueOf(this$1.getCount()), SearchFragment.SearchAdapter.access$400(this$1) }));
    this$1.notifyDataSetChanged();
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.SearchFragment.SearchAdapter.1
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */