package com.android.mms.ui;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SqliteWrapper;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Telephony.MmsSms;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.android.mms.LogTag;
import com.android.mms.data.Contact;
import com.android.mms.data.Contact.UpdateListener;
import com.android.mms.data.ContactList;
import com.android.mms.data.Conversation;
import com.android.mms.util.MiStatSdkHelper;
import com.google.android.collect.Lists;
import com.google.android.collect.Sets;
import com.google.android.mms.pdu.EncodedStringValue;
import com.google.android.mms.pdu.MiuiPduPersister;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import miui.R.drawable;
import miui.date.DateUtils;

public class SearchFragment
  extends Fragment
  implements View.OnTouchListener
{
  private Activity mActivity;
  private SearchAdapter mAdapter;
  Contact.UpdateListener mContactListener = new Contact.UpdateListener()
  {
    public void onUpdate(Contact paramAnonymousContact)
    {
      mHandler.removeCallbacks(mRequestRequery);
      mHandler.postDelayed(mRequestRequery, 500L);
    }
  };
  Handler mHandler = new Handler();
  private ListView mListView;
  private boolean mPrivateMode = false;
  Runnable mRequestRequery = new Runnable()
  {
    public void run()
    {
      if (!mAdapter.hasRunningTask()) {
        mAdapter.query();
      }
    }
  };
  private boolean mRetryQuery = false;
  private TextView mSearchCount;
  
  public SearchFragment()
  {
    mPrivateMode = false;
  }
  
  public SearchFragment(boolean paramBoolean)
  {
    mPrivateMode = paramBoolean;
  }
  
  private void hideSoftKeyboard()
  {
    ((InputMethodManager)mActivity.getSystemService("input_method")).hideSoftInputFromWindow(mListView.getWindowToken(), 0);
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    paramViewGroup = paramLayoutInflater.inflate(2130968697, paramViewGroup, false);
    mActivity = getActivity();
    mListView = ((ListView)paramViewGroup.findViewById(16908298));
    mListView.setFocusable(true);
    mListView.setFocusableInTouchMode(true);
    mListView.setOnTouchListener(this);
    mSearchCount = ((TextView)paramLayoutInflater.inflate(2130968700, null));
    mListView.addFooterView(mSearchCount, null, false);
    mAdapter = new SearchAdapter();
    mListView.setAdapter(mAdapter);
    mListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        paramAnonymousInt -= mListView.getHeaderViewsCount();
        if (paramAnonymousInt >= 0)
        {
          paramAnonymousAdapterView = mAdapter.getIntent(paramAnonymousInt);
          if (paramAnonymousAdapterView != null)
          {
            MiStatSdkHelper.recordSearch("search_click_item");
            ComposeMessageRouterActivity.route(mActivity, paramAnonymousAdapterView);
            SearchFragment.access$302(SearchFragment.this, true);
          }
        }
      }
    });
    paramLayoutInflater = paramViewGroup.findViewById(2131820564);
    mListView.setEmptyView(paramLayoutInflater);
    return paramViewGroup;
  }
  
  public void onDestroyView()
  {
    mAdapter.closeCursor();
    super.onDestroyView();
  }
  
  public void onStart()
  {
    super.onStart();
    if (mRetryQuery) {
      mHandler.post(mRequestRequery);
    }
    mRetryQuery = false;
  }
  
  public void onStop()
  {
    super.onStop();
  }
  
  public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
  {
    if (paramView == mListView) {
      hideSoftKeyboard();
    }
    return false;
  }
  
  public void query(String paramString)
  {
    mAdapter.query(paramString);
  }
  
  public void selectToFirst()
  {
    mListView.setSelection(0);
  }
  
  class SearchAdapter
    extends BaseAdapter
  {
    private int mBodyPos;
    private ContactList mContacts;
    private ArrayList<Conversation> mConversationForMessages;
    private ArrayList<Conversation> mConversations;
    private int mDatePos;
    private HashSet<String> mDuplicatedNames;
    private HashSet<Conversation> mExpandedConversations = Sets.newHashSet();
    private AsyncTask<Void, Void, Void> mLatestTask = null;
    private Cursor mMsgCursor;
    private ArrayList<Integer> mMsgPosition;
    private String mQueryString;
    private int mRowIdPos;
    private int mSubjectPos;
    private int mThreadIdPos;
    
    SearchAdapter() {}
    
    public void closeCursor()
    {
      if (mMsgCursor != null) {
        mMsgCursor.close();
      }
      mMsgCursor = null;
    }
    
    public int getCount()
    {
      if ((mMsgCursor == null) || (mConversations == null)) {
        return 0;
      }
      return mMsgCursor.getCount() + mConversations.size() + mContacts.size();
    }
    
    public Intent getIntent(int paramInt)
    {
      if (mMsgCursor == null) {}
      do
      {
        return null;
        long l1;
        if (paramInt < mConversations.size())
        {
          l1 = ((Conversation)mConversations.get(paramInt)).getThreadId();
          return ComposeMessageRouterActivity.createIntent(mActivity, l1);
        }
        if (paramInt < mConversations.size() + mConversationForMessages.size())
        {
          paramInt -= mConversations.size();
          if (!mMsgCursor.moveToPosition(((Integer)mMsgPosition.get(paramInt)).intValue()))
          {
            LogTag.error("SearchFragment failed to move cursor to position " + paramInt, new Object[0]);
            return null;
          }
          l1 = mMsgCursor.getLong(mThreadIdPos);
          long l2 = mMsgCursor.getLong(mRowIdPos);
          localObject = null;
          if (l2 < 0L) {
            localObject = mMsgCursor.getString(mBodyPos);
          }
          localObject = new Intent("android.intent.action.VIEW", ComposeMessageRouterActivity.createSearchResultDataUri(l1, l2, mQueryString, (String)localObject));
          ComposeMessageRouterActivity.prepareIntent((Intent)localObject, l1);
          return (Intent)localObject;
        }
        paramInt = paramInt - mConversations.size() - mConversationForMessages.size();
      } while (paramInt >= mContacts.size());
      Object localObject = ((Contact)mContacts.get(paramInt)).getNumber();
      Intent localIntent = ComposeMessageRouterActivity.createIntent(mActivity, -1L);
      localIntent.putExtra("address", (String)localObject);
      return localIntent;
    }
    
    public Object getItem(int paramInt)
    {
      return null;
    }
    
    public long getItemId(int paramInt)
    {
      return paramInt;
    }
    
    public int getItemViewType(int paramInt)
    {
      if (paramInt < mConversations.size()) {
        return 0;
      }
      if (paramInt < mConversations.size() + mConversationForMessages.size())
      {
        int m = paramInt - mConversations.size();
        Conversation localConversation = (Conversation)mConversationForMessages.get(m);
        int j;
        label96:
        int i;
        label130:
        int k;
        if (mExpandedConversations.contains(localConversation))
        {
          paramInt = 10000;
          if ((m != 0) && (localConversation == mConversationForMessages.get(m - 1))) {
            break label203;
          }
          j = 1;
          if ((m != mMsgCursor.getCount() - 1) && (localConversation == mConversationForMessages.get(m + 1))) {
            break label208;
          }
          i = 1;
          if ((m < paramInt - 1) || (localConversation != mConversationForMessages.get(m - paramInt + 1)) || ((m != paramInt - 1) && (localConversation == mConversationForMessages.get(m - paramInt)))) {
            break label213;
          }
          k = 1;
        }
        for (;;)
        {
          if (j != 0)
          {
            if ((i != 0) || (k != 0))
            {
              return 1;
              paramInt = 5;
              break;
              label203:
              j = 0;
              break label96;
              label208:
              i = 0;
              break label130;
              label213:
              k = 0;
              continue;
            }
            return 2;
          }
        }
        if (k != 0)
        {
          if (i != 0) {
            return 3;
          }
          return 4;
        }
        if ((m >= paramInt) && (localConversation == mConversationForMessages.get(m - paramInt))) {}
        for (paramInt = 1; paramInt != 0; paramInt = 0) {
          return 6;
        }
        if (i != 0) {
          return 3;
        }
        return 5;
      }
      return 7;
    }
    
    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      if ((mMsgCursor == null) || (mConversations == null))
      {
        paramViewGroup = null;
        return paramViewGroup;
      }
      int i = getItemViewType(paramInt);
      Object localObject4;
      Object localObject3;
      Object localObject1;
      final Object localObject2;
      switch (i)
      {
      default: 
        if ((paramView instanceof LinearLayout))
        {
          paramViewGroup = (TextView)paramView.findViewById(2131820846);
          localObject4 = (SearchFragment.TextViewSnippet)paramView.findViewById(2131820614);
          localObject3 = (TextView)paramView.findViewById(2131820590);
          localObject1 = (Button)paramView.findViewById(2131820862);
          paramInt -= mConversations.size();
          mMsgCursor.moveToPosition(((Integer)mMsgPosition.get(paramInt)).intValue());
          localObject2 = (Conversation)mConversationForMessages.get(paramInt);
          if (paramViewGroup.getVisibility() == 0) {
            paramViewGroup.setText(((Conversation)localObject2).getContactNames());
          }
          paramViewGroup = mMsgCursor.getString(mSubjectPos);
          if (paramViewGroup == null) {
            break label1035;
          }
          if (TextUtils.isEmpty(paramViewGroup)) {
            break label1028;
          }
          paramViewGroup = new EncodedStringValue(106, MiuiPduPersister.getBytes(paramViewGroup)).getString();
        }
        break;
      }
      for (;;)
      {
        ((SearchFragment.TextViewSnippet)localObject4).setText(paramViewGroup, mQueryString, 2131689499);
        long l = mMsgCursor.getLong(mDatePos);
        paramViewGroup = new StringBuilder();
        DateUtils.formatRelativeTime(paramViewGroup, l, false);
        ((TextView)localObject3).setText(paramViewGroup.toString());
        paramViewGroup = paramView;
        if (i != 4) {
          break;
        }
        ((Button)localObject1).setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            mExpandedConversations.add(localObject2);
            notifyDataSetChanged();
          }
        });
        return paramView;
        if ((paramView instanceof LinearLayout))
        {
          paramViewGroup = (SearchFragment.TextViewSnippet)paramView.findViewById(2131820846);
          localObject1 = (SearchFragment.TextViewSnippet)paramView.findViewById(2131820854);
          localObject2 = (TextView)paramView.findViewById(2131820859);
          localObject3 = (Conversation)mConversations.get(paramInt);
          localObject4 = (Contact)((Conversation)localObject3).getRecipients().get(0);
          paramViewGroup.setText(((Contact)localObject4).getName(), mQueryString, 2131689495);
          if ((!mDuplicatedNames.contains(((Contact)localObject4).getName())) && ((((Contact)localObject4).getName().indexOf(mQueryString) != -1) || (((Contact)localObject4).getNumber().indexOf(mQueryString) == -1))) {
            break label539;
          }
          ((SearchFragment.TextViewSnippet)localObject1).setVisibility(0);
          ((SearchFragment.TextViewSnippet)localObject1).setText(((Contact)localObject4).getNumber());
          ((SearchFragment.TextViewSnippet)localObject1).measure(0, 0);
          getLayoutParamswidth = ((SearchFragment.TextViewSnippet)localObject1).getMeasuredWidth();
          ((SearchFragment.TextViewSnippet)localObject1).setText(((Contact)localObject4).getNumber(), mQueryString, 2131689499);
        }
        for (;;)
        {
          ((TextView)localObject2).setText(getResources().getQuantityString(2131623943, ((Conversation)localObject3).getMessageCount(), new Object[] { Integer.valueOf(((Conversation)localObject3).getMessageCount()) }));
          return paramView;
          paramView = LayoutInflater.from(mActivity).inflate(2130968699, paramViewGroup, false);
          break;
          label539:
          ((SearchFragment.TextViewSnippet)localObject1).setVisibility(8);
        }
        if (paramView != null) {
          return paramView;
        }
        return new View(mActivity);
        if ((paramView instanceof LinearLayout)) {}
        for (;;)
        {
          paramViewGroup = (SearchFragment.TextViewSnippet)paramView.findViewById(2131820846);
          localObject1 = (SearchFragment.TextViewSnippet)paramView.findViewById(2131820854);
          i = mConversations.size();
          int j = mConversationForMessages.size();
          localObject2 = (Contact)mContacts.get(paramInt - i - j);
          paramViewGroup.setText(((Contact)localObject2).getName(), mQueryString, 2131689495);
          ((SearchFragment.TextViewSnippet)localObject1).setText(((Contact)localObject2).getNumber());
          ((SearchFragment.TextViewSnippet)localObject1).measure(0, 0);
          getLayoutParamswidth = ((SearchFragment.TextViewSnippet)localObject1).getMeasuredWidth();
          ((SearchFragment.TextViewSnippet)localObject1).setText(((Contact)localObject2).getNumber(), mQueryString, 2131689499);
          return paramView;
          paramView = LayoutInflater.from(mActivity).inflate(2130968698, paramViewGroup, false);
        }
        paramView = LayoutInflater.from(mActivity).inflate(2130968701, paramViewGroup, false);
        paramViewGroup = (TextView)paramView.findViewById(2131820846);
        localObject1 = paramView.findViewById(2131820861);
        localObject2 = paramView.findViewById(2131820797);
        localObject3 = (Button)paramView.findViewById(2131820862);
        localObject4 = getActivity().getResources().getDrawable(R.drawable.preference_item_bg);
        switch (i)
        {
        default: 
          LogTag.error("Invalid item type in search result", new Object[0]);
          break;
        case 1: 
          ((Drawable)localObject4).setLevel(0);
          ((View)localObject1).setBackground((Drawable)localObject4);
          paramViewGroup.setVisibility(0);
          ((View)localObject2).setVisibility(0);
          ((Button)localObject3).setVisibility(8);
          break;
        case 2: 
          ((Drawable)localObject4).setLevel(1);
          ((View)localObject1).setBackground((Drawable)localObject4);
          paramViewGroup.setVisibility(0);
          ((View)localObject2).setVisibility(8);
          ((Button)localObject3).setVisibility(8);
          break;
        case 3: 
          ((Drawable)localObject4).setLevel(3);
          ((View)localObject1).setBackground((Drawable)localObject4);
          paramViewGroup.setVisibility(8);
          ((View)localObject2).setVisibility(0);
          ((Button)localObject3).setVisibility(8);
          break;
        case 4: 
          ((Drawable)localObject4).setLevel(3);
          ((View)localObject1).setBackground((Drawable)localObject4);
          paramViewGroup.setVisibility(8);
          ((View)localObject2).setVisibility(0);
          ((Button)localObject3).setVisibility(0);
          break;
        case 5: 
          ((Drawable)localObject4).setLevel(2);
          ((View)localObject1).setBackground((Drawable)localObject4);
          paramViewGroup.setVisibility(8);
          ((View)localObject2).setVisibility(8);
          ((Button)localObject3).setVisibility(8);
          break;
          label1028:
          paramViewGroup = "";
          continue;
          label1035:
          paramViewGroup = mMsgCursor.getString(mBodyPos);
        }
      }
    }
    
    public int getViewTypeCount()
    {
      return 8;
    }
    
    public boolean hasRunningTask()
    {
      return mLatestTask != null;
    }
    
    public void query()
    {
      mLatestTask = new AsyncTask()
      {
        private String mQueryInTask = mQueryString;
        private ContactList mResultContacts;
        private ArrayList<Conversation> mResultConversationForMessages;
        private ArrayList<Conversation> mResultConversations;
        private Cursor mResultCursor;
        private HashSet<String> mResultDuplicatedNames;
        private HashMap<Long, ArrayList<Integer>> mResultMsgPos;
        private ArrayList<Long> mResultMsgThreadIds;
        
        protected Void doInBackground(Void... paramAnonymousVarArgs)
        {
          if (TextUtils.isEmpty(mQueryInTask)) {
            return null;
          }
          Object localObject1 = Telephony.MmsSms.SEARCH_URI.buildUpon().appendQueryParameter("pattern", mQueryInTask);
          if (mPrivateMode) {}
          for (paramAnonymousVarArgs = "1";; paramAnonymousVarArgs = "0")
          {
            paramAnonymousVarArgs = ((Uri.Builder)localObject1).appendQueryParameter("privacy_flag", paramAnonymousVarArgs).build();
            mResultCursor = SqliteWrapper.query(mActivity, mActivity.getContentResolver(), paramAnonymousVarArgs, null, null, null, null);
            if (mResultCursor != null) {
              break;
            }
            return null;
          }
          if (mLatestTask != this) {
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
              paramAnonymousVarArgs = Lists.newArrayList();
              paramAnonymousVarArgs.add(Integer.valueOf(i));
              mResultMsgPos.put(Long.valueOf(l), paramAnonymousVarArgs);
              mResultMsgThreadIds.add(Long.valueOf(l));
            }
          }
          paramAnonymousVarArgs = mResultMsgThreadIds.iterator();
          while (paramAnonymousVarArgs.hasNext())
          {
            l = ((Long)paramAnonymousVarArgs.next()).longValue();
            j = ((ArrayList)mResultMsgPos.get(Long.valueOf(l))).size();
            localObject1 = Conversation.get(l);
            i = 0;
            while (i < j)
            {
              mResultConversationForMessages.add(localObject1);
              i += 1;
            }
          }
          mResultConversations = Conversation.searchForConversations(mActivity, mQueryInTask, mPrivateMode);
          paramAnonymousVarArgs = Sets.newHashSet();
          localObject1 = Sets.newHashSet();
          mResultDuplicatedNames = Sets.newHashSet();
          Object localObject2 = mResultConversations.iterator();
          while (((Iterator)localObject2).hasNext())
          {
            Contact localContact = (Contact)((Conversation)((Iterator)localObject2).next()).getRecipients().get(0);
            String str = localContact.getName();
            ((HashSet)localObject1).add(localContact);
            if (paramAnonymousVarArgs.contains(str)) {
              mResultDuplicatedNames.add(str);
            } else {
              paramAnonymousVarArgs.add(str);
            }
          }
          mResultContacts = Contact.searchForContacts(mQueryInTask);
          mResultContacts.removeAll((Collection)localObject1);
          localObject1 = mResultContacts.iterator();
          while (((Iterator)localObject1).hasNext())
          {
            localObject2 = ((Contact)((Iterator)localObject1).next()).getName();
            if (paramAnonymousVarArgs.contains(localObject2)) {
              mResultDuplicatedNames.add(localObject2);
            } else {
              paramAnonymousVarArgs.add(localObject2);
            }
          }
          return null;
        }
        
        protected void onPostExecute(Void paramAnonymousVoid)
        {
          if ((mLatestTask != this) || (!isAdded()))
          {
            if (mResultCursor != null) {
              mResultCursor.close();
            }
            return;
          }
          SearchFragment.SearchAdapter.access$602(SearchFragment.SearchAdapter.this, null);
          closeCursor();
          SearchFragment.SearchAdapter.access$702(SearchFragment.SearchAdapter.this, mResultCursor);
          SearchFragment.SearchAdapter.access$802(SearchFragment.SearchAdapter.this, mResultConversations);
          SearchFragment.SearchAdapter.access$902(SearchFragment.SearchAdapter.this, mResultConversationForMessages);
          SearchFragment.SearchAdapter.access$1002(SearchFragment.SearchAdapter.this, mResultDuplicatedNames);
          SearchFragment.SearchAdapter.access$1102(SearchFragment.SearchAdapter.this, mResultContacts);
          mExpandedConversations.clear();
          SearchFragment.SearchAdapter.access$1302(SearchFragment.SearchAdapter.this, Lists.newArrayList());
          if ((mResultMsgThreadIds != null) && (mResultMsgPos != null))
          {
            paramAnonymousVoid = mResultMsgThreadIds.iterator();
            while (paramAnonymousVoid.hasNext())
            {
              long l = ((Long)paramAnonymousVoid.next()).longValue();
              Iterator localIterator = ((ArrayList)mResultMsgPos.get(Long.valueOf(l))).iterator();
              while (localIterator.hasNext())
              {
                int i = ((Integer)localIterator.next()).intValue();
                mMsgPosition.add(Integer.valueOf(i));
              }
            }
          }
          if (mMsgCursor != null)
          {
            SearchFragment.SearchAdapter.access$1402(SearchFragment.SearchAdapter.this, mMsgCursor.getColumnIndex("thread_id"));
            SearchFragment.SearchAdapter.access$1502(SearchFragment.SearchAdapter.this, mMsgCursor.getColumnIndex("body"));
            SearchFragment.SearchAdapter.access$1602(SearchFragment.SearchAdapter.this, mMsgCursor.getColumnIndex("sub"));
            SearchFragment.SearchAdapter.access$1702(SearchFragment.SearchAdapter.this, mMsgCursor.getColumnIndex("_id"));
            SearchFragment.SearchAdapter.access$1802(SearchFragment.SearchAdapter.this, mMsgCursor.getColumnIndex("date"));
          }
          mSearchCount.setText(getResources().getQuantityString(2131623939, getCount(), new Object[] { Integer.valueOf(getCount()), mQueryString }));
          notifyDataSetChanged();
        }
      };
      mLatestTask.execute(new Void[0]);
    }
    
    public void query(String paramString)
    {
      if (TextUtils.isEmpty(paramString)) {}
      for (paramString = null;; paramString = paramString.trim())
      {
        mQueryString = paramString;
        query();
        return;
      }
    }
  }
  
  public static class TextViewSnippet
    extends TextView
  {
    private String mFullText;
    private int mHlAppearance;
    private String mTargetString;
    private String[] mTargetTokens;
    
    public TextViewSnippet(Context paramContext)
    {
      super();
    }
    
    public TextViewSnippet(Context paramContext, AttributeSet paramAttributeSet)
    {
      super(paramAttributeSet);
    }
    
    public TextViewSnippet(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
    {
      super(paramAttributeSet, paramInt);
    }
    
    protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    {
      String str1 = mTargetTokens[0].toLowerCase();
      Object localObject = mFullText.toLowerCase();
      String str2 = str1.toLowerCase();
      int j = ((String)localObject).indexOf(str1);
      if ((mHlAppearance == 0) || (j == -1))
      {
        setText(mFullText);
        super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
        return;
      }
      int i = str2.length();
      int m = ((String)localObject).length();
      if (i > m)
      {
        setText(mFullText);
        super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
        return;
      }
      localObject = getPaint();
      float f1 = ((TextPaint)localObject).measureText(str1);
      float f2 = ((TextPaint)localObject).measureText(mFullText);
      float f3 = getWidth();
      str1 = mFullText;
      if (f1 > f3) {}
      for (str1 = mFullText.substring(j, j + i);; str1 = mFullText)
      {
        MessageUtils.showTextWithHighlight(this, str1, mTargetString, mHlAppearance);
        super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
        return;
        if (f2 > f3) {
          break;
        }
      }
      float f4 = ((TextPaint)localObject).measureText("…");
      i = j + mTargetTokens[0].length();
      f1 = ((TextPaint)localObject).measureText(mFullText, j, i);
      while ((f1 < f3 - 2.0F * f4) && ((j > 0) || (i < m)))
      {
        int k = j;
        f2 = f1;
        if (j > 0)
        {
          k = j - 1;
          f2 = f1 + ((TextPaint)localObject).measureText(mFullText, k, k + 1);
        }
        j = k;
        f1 = f2;
        if (i < m)
        {
          i += 1;
          f1 = f2 + ((TextPaint)localObject).measureText(mFullText, i - 1, i);
          j = k;
        }
      }
      if (j == 0)
      {
        str1 = "";
        label375:
        str2 = mFullText.substring(j, i);
        if (i != m) {
          break label435;
        }
      }
      label435:
      for (localObject = "";; localObject = "…")
      {
        str1 = String.format("%s%s%s", new Object[] { str1, str2, localObject });
        break;
        str1 = "…";
        break label375;
      }
    }
    
    public void setText(String paramString1, String paramString2, int paramInt)
    {
      mFullText = paramString1;
      mTargetString = paramString2;
      mTargetTokens = paramString2.split(" +");
      mHlAppearance = paramInt;
      requestLayout();
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.SearchFragment
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */