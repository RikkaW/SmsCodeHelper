package com.android.mms.ui;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.provider.CallLog.Calls;
import android.provider.ContactsContract.Contacts;
import android.provider.Telephony.Mms;
import android.text.ClipboardManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnKeyListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import com.android.mms.LogTag;
import com.android.mms.MmsConfig;
import com.android.mms.data.Contact;
import com.android.mms.data.ContactList;
import com.android.mms.data.Conversation;
import com.android.mms.data.WorkingMessage;
import com.android.mms.util.MSimUtils;
import com.google.android.collect.Lists;
import com.google.android.collect.Maps;
import com.xiaomi.mms.data.MxIdCache;
import com.xiaomi.mms.data.MxIdCache.MxIdCacheItem;
import com.xiaomi.mms.transaction.PushSession;
import com.xiaomi.mms.utils.B2cMessageUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import miui.app.ProgressDialog;
import miui.os.Build;
import miui.provider.ExtraTelephony.MmsSms;
import miui.telephony.PhoneNumberUtils;
import miui.util.AttributeResolver;
import miui.view.menu.ContextMenuDialog;
import miui.widget.GuidePopupWindow;

public class NewMessageActivity
  extends MessageEditableActivityBase
{
  private static float ADJUST_SPEED;
  private static final String RECIPIENT_SEPARATORS;
  private View mAddRecipientButton;
  private boolean mAdjustEditorHeight;
  private ContactList mCommitedRecipients = new ContactList();
  private View mConfirmRecipientButton;
  private Runnable mDrawContactPanelRunnable = new Runnable()
  {
    public void run()
    {
      Log.v("NewMessageActivity", "run mDrawContactPanelRunnable");
      int i = NewMessageActivity.this.switchRecipientsRowView();
      if (isVisible(mRecipientRowsScroller))
      {
        mRecipientEditorCount.setText(getBaseContext().getString(2131362055, new Object[] { Integer.valueOf(recipientCount()) }));
        if (i > 2)
        {
          mRecipientEditorCount.setVisibility(0);
          mRecipientRowsScroller.setVerticalScrollBarEnabled(true);
          return;
        }
        mRecipientEditorCount.setVisibility(8);
        mRecipientRowsScroller.setVerticalScrollBarEnabled(false);
        return;
      }
      mRecipientEditorCount.setVisibility(8);
    }
  };
  private boolean mExtraCallPrivateRecipientConv;
  private GuidePopupWindow mGuidePopupWindow;
  private Runnable mHideSoftKeyboardRunnable = new Runnable()
  {
    public void run()
    {
      hideSoftKeyboard();
    }
  };
  private ArrayList<String> mOriginalCommitedNumbers = Lists.newArrayList();
  private ProgressDialog mPickContactsProgressDialog;
  private AsyncTask<Void, Void, Void> mPickContactsTask;
  private HashMap<Contact, List<Uri>> mPickedUrisMap = Maps.newHashMap();
  private StaticGridView mRecentContactGrid;
  private ContactList mRecentContacts = new ContactList();
  private boolean mRecentContactsDrawn;
  private boolean mRecentContactsLoaded;
  private RecipientsAdapter mRecipientAdapter;
  private EditText mRecipientEditor;
  private TextView mRecipientEditorCount;
  private int mRecipientRowPadding;
  private RowLayout mRecipientRows;
  private ScrollView mRecipientRowsScroller;
  private final View.OnKeyListener mRecipientsKeyListener = new View.OnKeyListener()
  {
    public boolean onKey(View paramAnonymousView, int paramAnonymousInt, KeyEvent paramAnonymousKeyEvent)
    {
      if (paramAnonymousKeyEvent.getAction() != 0) {}
      do
      {
        return false;
        switch (paramAnonymousInt)
        {
        default: 
          return false;
        }
      } while ((mRecipientEditor.getSelectionEnd() != 0) || (mCommitedRecipients.isEmpty()));
      NewMessageActivity.this.removeRecipient(mCommitedRecipients.size() - 1);
      return true;
    }
  };
  private TextView mRecipientsViewer;
  private TextView mRecipientsViewerCount;
  private TextView mRecipientsViewerHead;
  private LinearLayout mRecipientsViewerLinearLayout;
  private final TextWatcher mRecipientsWatcher = new TextWatcher()
  {
    public void afterTextChanged(Editable paramAnonymousEditable)
    {
      if ((mCommitedRecipients.isEmpty()) && (mRecipientEditor.getText().length() == 0)) {
        mRecipientsViewerHead.setVisibility(0);
      }
      String str;
      ArrayList localArrayList;
      for (;;)
      {
        str = paramAnonymousEditable.toString() + '$';
        StringTokenizer localStringTokenizer = new StringTokenizer(str, NewMessageActivity.RECIPIENT_SEPARATORS);
        localArrayList = new ArrayList();
        while (localStringTokenizer.hasMoreTokens()) {
          localArrayList.add(localStringTokenizer.nextToken());
        }
        mRecipientsViewerHead.setVisibility(8);
      }
      if ((localArrayList.size() > 1) || (((String)localArrayList.get(0)).length() < str.length()))
      {
        str = (String)localArrayList.get(localArrayList.size() - 1);
        str = str.substring(0, str.length() - 1);
        int j = mRecipientEditor.getSelectionEnd() - (paramAnonymousEditable.length() - str.length());
        int i = j;
        if (j < 0) {
          i = 0;
        }
        mRecipientEditor.setText(str);
        mRecipientAdapter.start();
        mRecipientAdapter.getFilter().filter(str);
        mRecipientEditor.setSelection(i);
        j = 0;
        i = 0;
        while (i < localArrayList.size() - 1)
        {
          int k = j;
          if (NewMessageActivity.this.commitRecipient((String)localArrayList.get(i)))
          {
            k = j;
            if (j == 0) {
              k = 1;
            }
          }
          i += 1;
          j = k;
        }
        if (j != 0) {
          NewMessageActivity.this.updateCommitedRecipients();
        }
      }
      for (;;)
      {
        postUpdateSendButtonState();
        return;
        mRecipientAdapter.start();
        mRecipientAdapter.getFilter().filter(paramAnonymousEditable);
        mSuggestionList.setSelection(0);
      }
    }
    
    public void beforeTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3) {}
    
    public void onTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
    {
      onUserInteraction();
    }
  };
  private ListView mSuggestionList;
  private int mTitleViewHeightOneRow;
  private int mTitleViewHeightThreeRow;
  private int mTitleViewHeightTwoRow;
  private AsyncTask<Void, Void, Boolean> mUpdateCommitedRecipientsTask;
  protected Runnable mUpdateRecipientsBkgRunnable = new Runnable()
  {
    public void run()
    {
      if (mRecipientRowsScroller.getVisibility() == 0) {
        NewMessageActivity.this.updateRecipientsBackground();
      }
    }
  };
  
  static
  {
    if (Build.IS_INTERNATIONAL_BUILD) {}
    for (String str = "\n\r\t,，;；";; str = " \n\r\t,，;；")
    {
      RECIPIENT_SEPARATORS = str;
      ADJUST_SPEED = 0.5F;
      return;
    }
  }
  
  private void addRecipient(final Contact paramContact, final String paramString)
  {
    if (mCommitedRecipients.indexOf(paramContact) != -1) {
      return;
    }
    mOriginalCommitedNumbers.add(paramString);
    mCommitedRecipients.add(paramContact);
    TextView localTextView = (TextView)getLayoutInflater().inflate(2130968695, null, false);
    if (contactNameExists(paramContact)) {
      localTextView.setText(paramContact.getName());
    }
    for (;;)
    {
      String str = paramContact.getMxPhoneNumber();
      if (allowMx(str))
      {
        localTextView.setTextColor(getResources().getColor(2131296361));
        localTextView.setBackgroundResource(2130837872);
      }
      localTextView.setTag(str);
      localTextView.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View arg1)
        {
          mRecipientRows.removeView(???);
          mCommitedRecipients.remove(paramContact);
          synchronized (mPickedUrisMap)
          {
            mPickedUrisMap.remove(paramContact);
            NewMessageActivity.this.postDrawContactPanel();
            mOriginalCommitedNumbers.remove(paramString);
            NewMessageActivity.this.enableContactInRecentList(paramContact, true);
            onContactRemoved(paramContact);
            NewMessageActivity.this.syncNumbersToWorkingMessage();
            if ((mCommitedRecipients.isEmpty()) && (mRecipientEditor.getText().length() == 0)) {
              mRecipientsViewerHead.setVisibility(0);
            }
            return;
          }
        }
      });
      localTextView.setOnLongClickListener(new View.OnLongClickListener()
      {
        public boolean onLongClick(final View paramAnonymousView)
        {
          ContextMenuDialog localContextMenuDialog = new ContextMenuDialog(NewMessageActivity.this);
          localContextMenuDialog.setTitle(paramString);
          if (NewMessageActivity.this.contactNameExists(paramContact))
          {
            localContextMenuDialog.setTitle(String.format("%s (%s)", new Object[] { paramString, paramContact.getName() }));
            localContextMenuDialog.addMenuItem(2131361889, new Runnable()
            {
              public void run()
              {
                Intent localIntent;
                if (val$c.isYellowPageNumber())
                {
                  localIntent = new Intent("com.miui.yellowpage.action.VIEW");
                  localIntent.putExtra("android.intent.extra.PHONE_NUMBER", val$c.getNumber());
                  localIntent.putExtra("source", "sms_recent_contacts");
                }
                for (;;)
                {
                  localIntent.setFlags(524288);
                  startActivity(localIntent);
                  return;
                  localIntent = new Intent("android.intent.action.VIEW", val$c.getUri());
                }
              }
            });
          }
          label152:
          for (;;)
          {
            localContextMenuDialog.addMenuItem(2131362080, new Runnable()
            {
              public void run()
              {
                ((ClipboardManager)getSystemService("clipboard")).setText(val$origNumber);
                Toast.makeText(NewMessageActivity.this, 2131362081, 0).show();
              }
            });
            localContextMenuDialog.show();
            return true;
            if (Telephony.Mms.isPhoneNumber(paramString)) {
              paramAnonymousView = "phone";
            }
            for (;;)
            {
              if (paramAnonymousView == null) {
                break label152;
              }
              localContextMenuDialog.addMenuItem(2131362083, new Runnable()
              {
                public void run()
                {
                  Intent localIntent = new Intent("android.intent.action.INSERT", ContactsContract.Contacts.CONTENT_URI);
                  localIntent.putExtra(paramAnonymousView, val$origNumber);
                  localIntent.setFlags(524288);
                  startActivity(localIntent);
                }
              });
              break;
              if (Telephony.Mms.isEmailAddress(paramString)) {
                paramAnonymousView = "email";
              } else {
                paramAnonymousView = null;
              }
            }
          }
        }
      });
      mRecipientRows.addView(localTextView, mRecipientRows.getChildCount() - 1, new RowLayout.LayoutParams(-2, -2));
      postDrawContactPanel();
      enableContactInRecentList(paramContact, false);
      onContactAdded(paramContact);
      return;
      localTextView.setText(paramString);
    }
  }
  
  private boolean allowMx(String paramString)
  {
    paramString = MxIdCache.get(paramString);
    boolean bool = mWorkingMessage.requiresMms();
    return (paramString != null) && (((bool) && (paramString.allowMms())) || ((!bool) && (paramString.allowSms())));
  }
  
  private void cancelUpdateCommitedRecipients()
  {
    if (mUpdateCommitedRecipientsTask != null) {
      mUpdateCommitedRecipientsTask.cancel(true);
    }
    mUpdateCommitedRecipientsTask = null;
  }
  
  private void commitEditingRecipient()
  {
    Editable localEditable = mRecipientEditor.getText();
    if (localEditable.length() > 0)
    {
      mRecipientEditor.setText("");
      if (commitRecipient(localEditable.toString())) {
        updateCommitedRecipients();
      }
    }
  }
  
  private void commitRecipient(Contact paramContact)
  {
    commitRecipient(paramContact, paramContact.getNumber());
  }
  
  private boolean commitRecipient(Contact paramContact, String paramString)
  {
    if (mCommitedRecipients.indexOf(paramContact) != -1) {
      return false;
    }
    int i = 0;
    if (mCommitedRecipients.isEmpty()) {
      i = 1;
    }
    addRecipient(paramContact, paramString);
    syncNumbersToWorkingMessage();
    mRecipientsViewerHead.setVisibility(8);
    if (i != 0) {
      postUpdateSendButtonState();
    }
    return true;
  }
  
  private boolean commitRecipient(String paramString)
  {
    return commitRecipient(Contact.get(paramString).load(false, false), paramString);
  }
  
  private boolean contactNameExists(Contact paramContact)
  {
    return (paramContact.existsInDatabase()) || ((paramContact.isYellowPageNumber()) && (!TextUtils.isEmpty(paramContact.getName())) && (PhoneNumberUtils.isChinaMobileNumber(paramContact.getNumber())));
  }
  
  private GuidePopupWindow createUserGuide(View paramView, String paramString, PopupWindow.OnDismissListener paramOnDismissListener)
  {
    GuidePopupWindow localGuidePopupWindow = null;
    if (isResumed())
    {
      localGuidePopupWindow = new GuidePopupWindow(this);
      localGuidePopupWindow.setGuideText(paramString);
      localGuidePopupWindow.setOutsideTouchable(true);
      localGuidePopupWindow.setInputMethodMode(2);
      if (paramOnDismissListener != null) {
        localGuidePopupWindow.setOnDismissListener(paramOnDismissListener);
      }
      localGuidePopupWindow.show(paramView, true);
    }
    return localGuidePopupWindow;
  }
  
  private void drawRecipientContactsGrid(int paramInt)
  {
    if (mRecentContactsDrawn) {
      return;
    }
    int j = View.MeasureSpec.getSize(paramInt);
    int i;
    label70:
    View localView;
    if (!mIsLandscape)
    {
      paramInt = 3;
      i = getResources().getDimensionPixelSize(2131427364);
      j /= paramInt;
      mRecentContactGrid.set((mRecentContacts.size() + paramInt - 1) / paramInt, paramInt, j, i);
      j = mRecentContactGrid.getChildCount();
      i = 0;
      if (i >= j) {
        break label140;
      }
      localView = mRecentContactGrid.getChildAt(i).findViewById(2131820851);
      if (i % paramInt != paramInt - 1) {
        break label131;
      }
      localView.setVisibility(8);
    }
    for (;;)
    {
      i += 1;
      break label70;
      paramInt = 6;
      i = getResources().getDimensionPixelSize(2131427365);
      break;
      label131:
      localView.setVisibility(0);
    }
    label140:
    mRecentContactsDrawn = true;
  }
  
  private void enableContactInRecentList(Contact paramContact, boolean paramBoolean)
  {
    int j = mRecentContactGrid.getChildCount();
    int i = 0;
    for (;;)
    {
      if (i < j)
      {
        View localView = mRecentContactGrid.getChildAt(i);
        if (paramContact == (Contact)mRecentContacts.get(i)) {
          localView.findViewById(2131820852).setEnabled(paramBoolean);
        }
      }
      else
      {
        return;
      }
      i += 1;
    }
  }
  
  private String getCommitedRecipientName(int paramInt)
  {
    Contact localContact = (Contact)mCommitedRecipients.get(paramInt);
    if (contactNameExists(localContact)) {
      return localContact.getName();
    }
    return (String)mOriginalCommitedNumbers.get(paramInt);
  }
  
  private ArrayList<String> getRecipientNumbers()
  {
    ArrayList localArrayList = new ArrayList(mOriginalCommitedNumbers);
    if (hasUncommitedRecipient()) {
      localArrayList.add(mRecipientEditor.getText().toString());
    }
    return localArrayList;
  }
  
  private boolean hasUncommitedRecipient()
  {
    return mRecipientEditor.getText().length() > 0;
  }
  
  private void initRecipientsEditor()
  {
    mRecipientEditor = ((EditText)findViewById(2131820823));
    mRecipientEditor.setTextColor(AttributeResolver.resolveColor(this, 16843601));
    mRecipientEditor.addTextChangedListener(mRecipientsWatcher);
    mRecipientEditor.setOnKeyListener(mRecipientsKeyListener);
    mRecipientEditor.setOnFocusChangeListener(new View.OnFocusChangeListener()
    {
      public void onFocusChange(View paramAnonymousView, boolean paramAnonymousBoolean)
      {
        if (PreferenceManager.getDefaultSharedPreferences(NewMessageActivity.this).getBoolean("pref_key_show_recent_contacts", true)) {
          NewMessageActivity.this.showRecentContactGridWithAnim(paramAnonymousBoolean);
        }
        NewMessageActivity.this.onRecipientEditorFocusChange(paramAnonymousBoolean);
      }
    });
  }
  
  private boolean isValidAddress(String paramString)
  {
    if (mWorkingMessage.requiresMms()) {
      return MessageUtils.isValidMmsAddress(paramString);
    }
    boolean bool = true;
    String str = paramString;
    if (Build.IS_CM_CUSTOMIZATION_TEST)
    {
      str = Contact.removeSpaceForAddress(paramString);
      bool = PhoneNumberUtils.isDialable(str);
    }
    return ((bool) && (PhoneNumberUtils.isWellFormedSmsAddress(str))) || (Telephony.Mms.isEmailAddress(str));
  }
  
  private void launchMultiplePhonePicker()
  {
    commitEditingRecipient();
    Intent localIntent1 = new Intent("com.android.contacts.action.GET_MULTIPLE_PHONES");
    localIntent1.addCategory("android.intent.category.DEFAULT");
    localIntent1.setType("vnd.android.cursor.dir/phone_v2");
    localIntent1.setPackage(MessageUtils.getContactsPackageName());
    localIntent1.putExtra("android.intent.extra.include_unknown_numbers", true);
    localIntent1.putExtra("number_count", MmsConfig.getRecipientLimit());
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = mCommitedRecipients.iterator();
    while (localIterator.hasNext())
    {
      Contact localContact = (Contact)localIterator.next();
      synchronized (mPickedUrisMap)
      {
        List localList = (List)mPickedUrisMap.get(localContact);
        if ((localList != null) && (localList.size() > 0))
        {
          ??? = localList.iterator();
          if (!((Iterator)???).hasNext()) {
            continue;
          }
          localArrayList.add((Uri)((Iterator)???).next());
        }
      }
      int i = localContact.getContactMethodType();
      if (1 == i)
      {
        ??? = localContact.getPhoneUri();
        if (!localArrayList.contains(???)) {
          localArrayList.add(???);
        }
      }
      else if (2 == i)
      {
        ??? = localContact.getEmailUri();
        if (!localArrayList.contains(???)) {
          localArrayList.add(???);
        }
      }
    }
    if (localArrayList.size() > 0) {
      localIntent2.putExtra("com.android.contacts.extra.PHONE_URIS", (Parcelable[])localArrayList.toArray(new Uri[localArrayList.size()]));
    }
    startActivityForResult(localIntent2, 0);
  }
  
  private void onRecipientEditorFocusChange(boolean paramBoolean)
  {
    if (paramBoolean) {
      showSoftKeyboard();
    }
    for (;;)
    {
      postDrawContactPanel();
      return;
      commitEditingRecipient();
      updateRecipientsViewerText();
      if (mAllowAnimation)
      {
        mRecipientRowsScroller.startAnimation(AnimationUtils.loadAnimation(getBaseContext(), 2131034117));
        mRecipientsViewerLinearLayout.startAnimation(AnimationUtils.loadAnimation(getBaseContext(), 2131034116));
      }
      mRecipientRowsScroller.setVisibility(8);
      mRecipientsViewerLinearLayout.setVisibility(0);
    }
  }
  
  private void postDrawContactPanel()
  {
    if (mHandler != null)
    {
      mHandler.removeCallbacks(mDrawContactPanelRunnable);
      mHandler.postDelayed(mDrawContactPanelRunnable, 10L);
      return;
    }
    Log.v("NewMessageActivity", "postDrawContactPanel mHandler is null");
  }
  
  private void postHideSoftKeyboard()
  {
    mHandler.removeCallbacks(mHideSoftKeyboardRunnable);
    mHandler.postDelayed(mHideSoftKeyboardRunnable, 10L);
  }
  
  private void postUpdateRecipientsBackground(boolean paramBoolean)
  {
    mHandler.removeCallbacks(mUpdateRecipientsBkgRunnable);
    Handler localHandler = mHandler;
    Runnable localRunnable = mUpdateRecipientsBkgRunnable;
    if (paramBoolean) {}
    for (long l = 2000L;; l = 0L)
    {
      localHandler.postDelayed(localRunnable, l);
      return;
    }
  }
  
  private void processPickResult(final Intent paramIntent)
  {
    mWorkingMessage.asyncDeleteDraftSmsMessage(mConversation);
    removeAllRecipients();
    paramIntent = paramIntent.getParcelableArrayExtra("com.android.contacts.extra.PHONE_URIS");
    if (paramIntent != null) {}
    for (int i = paramIntent.length;; i = 0)
    {
      int j = MmsConfig.getRecipientLimit();
      if ((j == Integer.MAX_VALUE) || (i <= j)) {
        break;
      }
      new AlertDialog.Builder(this).setTitle(2131362037).setIconAttribute(16843605).setMessage(getString(2131361873, new Object[] { Integer.valueOf(i), Integer.valueOf(j) })).setPositiveButton(17039370, null).create().show();
      return;
    }
    if (mPickContactsTask != null) {
      mPickContactsTask.cancel(true);
    }
    mPickContactsTask = new AsyncTask()
    {
      private HashMap<Contact, List<Uri>> mMap;
      
      protected Void doInBackground(Void... arg1)
      {
        if ((paramIntent != null) && (paramIntent.length > 0) && (mPickedUrisMap != null))
        {
          ArrayList localArrayList = Lists.newArrayList();
          Parcelable[] arrayOfParcelable = paramIntent;
          int j = arrayOfParcelable.length;
          int i = 0;
          while (i < j)
          {
            ??? = arrayOfParcelable[i];
            if (isCancelled()) {
              return null;
            }
            Object localObject2 = (Uri)???;
            Contact localContact;
            if ("tel".equals(((Uri)localObject2).getScheme())) {
              localContact = Contact.get(((Uri)localObject2).getSchemeSpecificPart());
            }
            for (;;)
            {
              synchronized (mPickedUrisMap)
              {
                List localList = (List)mPickedUrisMap.get(localContact);
                ??? = localList;
                if (localList == null) {
                  ??? = new ArrayList(2);
                }
                ???.add(localObject2);
                mMap.put(localContact, ???);
                ??? = Contact.getByPhoneUris((Parcelable[])localArrayList.toArray(new Parcelable[localArrayList.size()]));
                if (!isCancelled()) {
                  break;
                }
                return null;
              }
              if ("content".equals(((Uri)localObject2).getScheme()))
              {
                localArrayList.add(localObject2);
              }
              else
              {
                ??? = ((Uri)localObject2).getSchemeSpecificPart();
                if (MessageUtils.isEmail(???))
                {
                  localObject2 = Contact.get(???);
                  ??? = null;
                  synchronized (mPickedUrisMap)
                  {
                    if (mPickedUrisMap.containsKey(localObject2)) {
                      ??? = (List)mPickedUrisMap.get(localObject2);
                    }
                    mMap.put(localObject2, ???);
                  }
                }
              }
            }
            if (??? != null)
            {
              localObject2 = ???.iterator();
              while (((Iterator)localObject2).hasNext())
              {
                localContact = (Contact)((Iterator)localObject2).next();
                if (isCancelled()) {
                  return null;
                }
                ??? = null;
                synchronized (mPickedUrisMap)
                {
                  if (mPickedUrisMap.containsKey(localContact)) {
                    ??? = (List)mPickedUrisMap.get(localContact);
                  }
                  mMap.put(localContact, ???);
                }
              }
            }
            i += 1;
          }
        }
        return null;
      }
      
      protected void onPostExecute(Void arg1)
      {
        Iterator localIterator = mMap.keySet().iterator();
        while (localIterator.hasNext())
        {
          Contact localContact = (Contact)localIterator.next();
          NewMessageActivity.this.addRecipient(localContact, localContact.getNumber());
          synchronized (mPickedUrisMap)
          {
            if (!mPickedUrisMap.containsKey(localContact)) {
              mPickedUrisMap.put(localContact, mMap.get(localContact));
            }
          }
        }
        mMap.clear();
        mMap = null;
        NewMessageActivity.this.syncNumbersToWorkingMessage();
        if (mRecipientsViewer.getVisibility() == 0) {
          NewMessageActivity.this.updateRecipientsViewerText();
        }
        if ((mPickContactsProgressDialog != null) && (mPickContactsProgressDialog.isShowing())) {
          mPickContactsProgressDialog.dismiss();
        }
        NewMessageActivity.access$3402(NewMessageActivity.this, null);
        NewMessageActivity.access$3702(NewMessageActivity.this, null);
        NewMessageActivity.this.textEditorRequestFocus();
      }
      
      protected void onPreExecute()
      {
        NewMessageActivity.access$3402(NewMessageActivity.this, new ProgressDialog(NewMessageActivity.this));
        mPickContactsProgressDialog.setMessage(getText(2131362038));
        mPickContactsProgressDialog.setIndeterminate(true);
        mPickContactsProgressDialog.setCancelable(false);
        mPickContactsProgressDialog.show();
        mRecipientsViewerHead.setVisibility(8);
        mMap = Maps.newHashMap();
      }
    };
    mPickContactsTask.execute(new Void[0]);
  }
  
  private void removeAllRecipients()
  {
    int i = 0;
    mRecipientRows.removeViews(0, mCommitedRecipients.size());
    mCommitedRecipients.clear();
    mOriginalCommitedNumbers.clear();
    mRecipientEditor.setText("");
    while (i < mRecentContactGrid.getChildCount())
    {
      View localView = mRecentContactGrid.getChildAt(i).findViewById(2131820852);
      if (localView != null) {
        localView.setEnabled(true);
      }
      i += 1;
    }
  }
  
  private void removeRecipient(int paramInt)
  {
    Contact localContact = mCommitedRecipients.remove(paramInt);
    if (localContact != null) {}
    synchronized (mPickedUrisMap)
    {
      mPickedUrisMap.remove(localContact);
      mOriginalCommitedNumbers.remove(paramInt);
      syncNumbersToWorkingMessage();
      mRecipientRows.removeViewAt(paramInt);
      postDrawContactPanel();
      if ((mCommitedRecipients.isEmpty()) && (mRecipientEditor.getText().length() == 0)) {
        mRecipientsViewerHead.setVisibility(0);
      }
      enableContactInRecentList(localContact, true);
      onContactRemoved(localContact);
      return;
    }
  }
  
  private void showAddAttachmentPanelGuide(SharedPreferences paramSharedPreferences)
  {
    PopupWindow.OnDismissListener local2 = new PopupWindow.OnDismissListener()
    {
      public void onDismiss()
      {
        NewMessageActivity.access$002(NewMessageActivity.this, null);
      }
    };
    mGuidePopupWindow = createUserGuide(mSwitchBtn, getString(2131362144), local2);
    if (mGuidePopupWindow != null)
    {
      paramSharedPreferences = paramSharedPreferences.edit();
      paramSharedPreferences.putBoolean("guide_add_attachments", false);
      paramSharedPreferences.commit();
    }
  }
  
  private void showRecentContactGrid(boolean paramBoolean)
  {
    if ((paramBoolean) && (mRecentContacts.size() > 0) && (mRecipientEditor.hasFocus()))
    {
      mRecentContactGrid.setVisibility(0);
      return;
    }
    mRecentContactGrid.setVisibility(8);
  }
  
  private void showRecentContactGridWithAnim(boolean paramBoolean)
  {
    Animation localAnimation;
    if ((mRecentContactGrid.getVisibility() == 8) && (paramBoolean))
    {
      if (mAllowAnimation)
      {
        localAnimation = AnimationUtils.loadAnimation(getBaseContext(), 2131034116);
        mRecentContactGrid.startAnimation(localAnimation);
      }
      mRecentContactGrid.setVisibility(0);
    }
    while ((mRecentContactGrid.getVisibility() != 0) || (paramBoolean)) {
      return;
    }
    if (mAllowAnimation)
    {
      localAnimation = AnimationUtils.loadAnimation(getBaseContext(), 2131034117);
      mRecentContactGrid.startAnimation(localAnimation);
    }
    mRecentContactGrid.setVisibility(8);
  }
  
  private int switchRecipientsRowView()
  {
    int i = mRecipientRows.getRowCount();
    int j;
    if ((mRecipientAdapter.getCount() > 0) && (mRecipientEditor.getText().length() > 0))
    {
      i = 1;
      mSuggestionList.setVisibility(0);
      mBottomPanel.setVisibility(8);
      showRecentContactGrid(false);
      if (mRecipientRowsScroller.getVisibility() == 0) {
        break label132;
      }
      j = 1;
      label69:
      i = mTitleViewHeightOneRow;
      switch (j)
      {
      }
    }
    for (;;)
    {
      setTopPlaceholderHeight(i);
      return j;
      mSuggestionList.setVisibility(8);
      mBottomPanel.setVisibility(0);
      showRecentContactGrid(true);
      break;
      label132:
      i = Math.min(i, 3);
      int k = mRecipientEditor.getHeight() * i + (i - 1) * mRecipientRowPadding;
      j = i;
      if (mRecipientRowsScroller.getLayoutParams().height == k) {
        break label69;
      }
      mRecipientRowsScroller.getLayoutParams().height = k;
      mRecipientRowsScroller.smoothScrollTo(0, k);
      mRecipientRowsScroller.requestLayout();
      j = i;
      break label69;
      mContactPanel.getLayoutParams().height = mTitleViewHeightOneRow;
      i = mTitleViewHeightOneRow;
      continue;
      mContactPanel.getLayoutParams().height = mTitleViewHeightTwoRow;
      i = mTitleViewHeightTwoRow;
      continue;
      mContactPanel.getLayoutParams().height = mTitleViewHeightThreeRow;
      i = mTitleViewHeightThreeRow;
    }
  }
  
  private void syncNumbersToWorkingMessage()
  {
    mWorkingMessage.setWorkingRecipients(getRecipientNumbers());
    mWorkingMessage.setHasEmail(containsEmail(), true);
  }
  
  private void textEditorRequestFocus()
  {
    if ((!mCommitedRecipients.isEmpty()) && (isVisible(mTextEditor))) {
      mTextEditor.requestFocus();
    }
  }
  
  private void updateCommitedRecipients()
  {
    cancelUpdateCommitedRecipients();
    if (!isResumed())
    {
      LogTag.verbose("updateCommitedRecipients is not resumed", new Object[0]);
      return;
    }
    mUpdateCommitedRecipientsTask = new AsyncTask()
    {
      private ContactList mContactList;
      
      protected Boolean doInBackground(Void... paramAnonymousVarArgs)
      {
        boolean bool3 = false;
        boolean bool2 = false;
        boolean bool1 = bool3;
        Contact localContact;
        if (mContactList != null)
        {
          bool1 = bool3;
          if (!mContactList.isEmpty())
          {
            paramAnonymousVarArgs = mContactList.iterator();
            bool1 = bool2;
            if (paramAnonymousVarArgs.hasNext())
            {
              localContact = (Contact)paramAnonymousVarArgs.next();
              if (!isCancelled()) {
                break label72;
              }
            }
          }
        }
        for (bool1 = false;; bool1 = false)
        {
          return Boolean.valueOf(bool1);
          label72:
          if (!localContact.existsInDatabase())
          {
            localContact.load(true, true);
            bool2 = true;
          }
          if (!isCancelled()) {
            break;
          }
        }
      }
      
      protected void onCancelled()
      {
        if (mContactList != null) {
          mContactList.clear();
        }
        mContactList = null;
      }
      
      protected void onPostExecute(Boolean paramAnonymousBoolean)
      {
        NewMessageActivity.access$4002(NewMessageActivity.this, null);
        if (mContactList != null) {
          mContactList.clear();
        }
        mContactList = null;
        if ((paramAnonymousBoolean.booleanValue()) && (isResumed()))
        {
          NewMessageActivity.this.updateRecipientsViewerText();
          NewMessageActivity.this.updateRecipientRows();
        }
      }
      
      protected void onPreExecute()
      {
        mContactList = ((ContactList)mCommitedRecipients.clone());
      }
    };
    mUpdateCommitedRecipientsTask.execute(new Void[0]);
  }
  
  private void updateRecipientRows()
  {
    int i = 0;
    while (i < mRecipientRows.getChildCount() - 1)
    {
      ((TextView)mRecipientRows.getChildAt(i)).setText(((Contact)mCommitedRecipients.get(i)).getName());
      i += 1;
    }
  }
  
  private void updateRecipientsBackground()
  {
    int j = mRecipientRows.getChildCount();
    int i = 0;
    if (i < j)
    {
      Object localObject = mRecipientRows.getChildAt(i);
      TextView localTextView;
      if (((localObject instanceof TextView)) && (((View)localObject).getTag() != null))
      {
        localTextView = (TextView)localObject;
        if (PushSession.getInstance(this).getConnectedSimIndex() < 0) {
          break label133;
        }
        localObject = MxIdCache.getOrQuery(this, (String)((View)localObject).getTag());
        if ((localObject == null) || (!((MxIdCache.MxIdCacheItem)localObject).allowSms())) {
          break label112;
        }
        localTextView.setTextColor(getResources().getColor(2131296361));
        localTextView.setBackgroundResource(2130837872);
      }
      for (;;)
      {
        i += 1;
        break;
        label112:
        localTextView.setTextColor(AttributeResolver.resolveColor(this, 16843601));
        localTextView.setBackgroundResource(2130837928);
        continue;
        label133:
        localTextView.setTextColor(AttributeResolver.resolveColor(this, 16843601));
        localTextView.setBackgroundResource(2130837928);
      }
    }
  }
  
  private void updateRecipientsViewerText()
  {
    int j = mCommitedRecipients.size();
    if (j == 0)
    {
      mRecipientsViewerHead.setVisibility(0);
      mRecipientsViewer.setText("");
      mRecipientsViewerCount.setText("");
      return;
    }
    StringBuilder localStringBuilder = new StringBuilder(getCommitedRecipientName(0));
    int i = 1;
    while (i < mCommitedRecipients.size())
    {
      localStringBuilder.append(", ");
      localStringBuilder.append(getCommitedRecipientName(i));
      i += 1;
    }
    mRecipientsViewerHead.setVisibility(8);
    mRecipientsViewer.setText(localStringBuilder);
    if (j > 1)
    {
      mRecipientsViewerCount.setVisibility(0);
      mRecipientsViewerCount.setText(getResources().getString(2131362054, new Object[] { Integer.valueOf(j) }));
      return;
    }
    mRecipientsViewerCount.setVisibility(8);
  }
  
  private void writeContactNumbers()
  {
    int i = 0;
    while (i < mCommitedRecipients.size())
    {
      ((Contact)mCommitedRecipients.get(i)).setNumber((String)mOriginalCommitedNumbers.get(i));
      i += 1;
    }
  }
  
  public boolean containsEmail()
  {
    Iterator localIterator = getRecipientNumbers().iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      Contact localContact = Contact.get(str);
      if ((Telephony.Mms.isEmailAddress(str)) && (!B2cMessageUtils.isB2cNumber(localContact))) {
        return true;
      }
    }
    return false;
  }
  
  public boolean dispatchTouchEvent(MotionEvent paramMotionEvent)
  {
    boolean bool2 = SmsTextSizeAdjust.getInstance().dispatchTouchEvent(paramMotionEvent);
    boolean bool1 = bool2;
    if (!bool2) {
      bool1 = super.dispatchTouchEvent(paramMotionEvent);
    }
    return bool1;
  }
  
  public String formatInvalidNumbers()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    Iterator localIterator = getRecipientNumbers().iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      if (!isValidAddress(str))
      {
        if (localStringBuilder.length() != 0) {
          localStringBuilder.append(',');
        }
        localStringBuilder.append(str);
      }
    }
    return localStringBuilder.toString();
  }
  
  protected int getContentViewResId()
  {
    return 2130968678;
  }
  
  protected ContactList getRecipients()
  {
    return mCommitedRecipients;
  }
  
  public boolean hasInvalidRecipient()
  {
    Iterator localIterator = getRecipientNumbers().iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      if (!isValidAddress(str))
      {
        if (MmsConfig.getEmailGateway() == null) {
          return true;
        }
        if (!MessageUtils.isAlias(str)) {
          return true;
        }
      }
    }
    return false;
  }
  
  public boolean hasValidRecipient()
  {
    Iterator localIterator = getRecipientNumbers().iterator();
    while (localIterator.hasNext()) {
      if (isValidAddress((String)localIterator.next())) {
        return true;
      }
    }
    return false;
  }
  
  void initRecentList()
  {
    if (!PreferenceManager.getDefaultSharedPreferences(this).getBoolean("pref_key_show_recent_contacts", true)) {
      if (mRecentContactsLoaded)
      {
        mRecentContactsLoaded = false;
        mRecentContacts.clear();
        mRecentContactGrid.removeAllViews();
      }
    }
    while (mRecentContactsLoaded) {
      return;
    }
    mRecentContactsLoaded = true;
    mRecentContacts.clear();
    mRecentContactGrid.removeAllViews();
    new AsyncTask()
    {
      private final HashSet<String> mExistingNames = new HashSet();
      
      private void addRecentRecipient(String paramAnonymousString)
      {
        paramAnonymousString = Contact.get(paramAnonymousString);
        paramAnonymousString.load(true, false);
        if ((NewMessageActivity.this.contactNameExists(paramAnonymousString)) && (!mExistingNames.contains(paramAnonymousString.getName())))
        {
          mExistingNames.add(paramAnonymousString.getName());
          mRecentContacts.add(paramAnonymousString);
        }
      }
      
      protected Void doInBackground(Void... paramAnonymousVarArgs)
      {
        Cursor localCursor1 = getContentResolver().query(CallLog.Calls.CONTENT_URI, new String[] { "number", "date" }, null, null, "date DESC LIMIT 50");
        if (localCursor1 == null) {
          return null;
        }
        Cursor localCursor2 = getContentResolver().query(ExtraTelephony.MmsSms.CONTENT_RECENT_RECIPIENTS_URI, new String[] { "address", "date" }, null, null, "date DESC LIMIT 50");
        if (localCursor2 == null)
        {
          localCursor1.close();
          return null;
        }
        try
        {
          localCursor1.moveToPosition(-1);
          localCursor2.moveToPosition(-1);
          Object localObject2 = null;
          paramAnonymousVarArgs = null;
          long l1 = 0L;
          long l2 = 0L;
          if (localCursor1.moveToNext())
          {
            localObject2 = localCursor1.getString(0);
            l1 = localCursor1.getLong(1);
          }
          long l3 = l1;
          Object localObject1 = localObject2;
          if (localCursor2.moveToNext())
          {
            paramAnonymousVarArgs = localCursor2.getString(0);
            l2 = localCursor2.getLong(1);
            localObject1 = localObject2;
            l3 = l1;
          }
          for (;;)
          {
            localObject2 = localObject1;
            if (localCursor1.isAfterLast()) {
              break;
            }
            localObject2 = localObject1;
            if (localCursor2.isAfterLast()) {
              break;
            }
            localObject2 = localObject1;
            if (mRecentContacts.size() >= 6) {
              break;
            }
            if (l3 > l2)
            {
              addRecentRecipient((String)localObject1);
              if (localCursor1.moveToNext())
              {
                localObject1 = localCursor1.getString(0);
                l3 = localCursor1.getLong(1);
              }
            }
            else
            {
              addRecentRecipient(paramAnonymousVarArgs);
              if (localCursor2.moveToNext())
              {
                paramAnonymousVarArgs = localCursor2.getString(0);
                l2 = localCursor2.getLong(1);
              }
            }
          }
          for (;;)
          {
            localObject1 = paramAnonymousVarArgs;
            if (localCursor1.isAfterLast()) {
              break;
            }
            localObject1 = paramAnonymousVarArgs;
            if (mRecentContacts.size() >= 6) {
              break;
            }
            addRecentRecipient((String)localObject2);
            if (localCursor1.moveToNext()) {
              localObject2 = localCursor1.getString(0);
            }
          }
          while ((!localCursor2.isAfterLast()) && (mRecentContacts.size() < 6))
          {
            addRecentRecipient((String)localObject1);
            if (localCursor2.moveToNext()) {
              localObject1 = localCursor2.getString(0);
            }
          }
          return null;
        }
        finally
        {
          localCursor1.close();
          localCursor2.close();
        }
      }
      
      protected void onPostExecute(Void paramAnonymousVoid)
      {
        boolean bool2 = false;
        paramAnonymousVoid = mRecentContacts.iterator();
        while (paramAnonymousVoid.hasNext())
        {
          final Contact localContact = (Contact)paramAnonymousVoid.next();
          View localView = getLayoutInflater().inflate(2130968693, mRecentContactGrid, false);
          TextView localTextView = (TextView)localView.findViewById(2131820852);
          if (mCommitedRecipients.contains(localContact)) {
            localTextView.setEnabled(false);
          }
          localTextView.setText(localContact.getName());
          localTextView.setOnClickListener(new View.OnClickListener()
          {
            public void onClick(View paramAnonymous2View)
            {
              NewMessageActivity.this.commitRecipient(localContact);
              NewMessageActivity.this.updateRecipientsViewerText();
            }
          });
          mRecentContactGrid.addView(localView);
          NewMessageActivity.access$2002(NewMessageActivity.this, false);
        }
        paramAnonymousVoid = NewMessageActivity.this;
        boolean bool1 = bool2;
        if (mRecentContacts.size() > 0)
        {
          bool1 = bool2;
          if (mRecipientEditor.hasFocus()) {
            bool1 = true;
          }
        }
        paramAnonymousVoid.showRecentContactGridWithAnim(bool1);
      }
    }.execute(new Void[0]);
  }
  
  protected void initResourceRefs()
  {
    super.initResourceRefs();
    mAddRecipientButton = findViewById(2131820828);
    mAddRecipientButton.setOnClickListener(this);
    mConfirmRecipientButton = findViewById(2131820829);
    mConfirmRecipientButton.setOnClickListener(this);
    mRecipientRowsScroller = ((ScrollView)findViewById(2131820821));
    mRecipientRows = ((RowLayout)findViewById(2131820822));
    mRecipientsViewerLinearLayout = ((LinearLayout)findViewById(2131820824));
    mRecipientsViewerLinearLayout.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        mRecipientsViewerLinearLayout.setVisibility(8);
        mRecipientRowsScroller.setVisibility(0);
        mRecipientEditor.requestFocus();
        mRecipientRowsScroller.scrollTo(0, mRecipientRows.getMeasuredHeight());
        showSoftKeyboard();
        NewMessageActivity.this.postUpdateRecipientsBackground(true);
      }
    });
    mRecipientEditorCount = ((TextView)findViewById(2131820830));
    mRecipientsViewerHead = ((TextView)findViewById(2131820820));
    mRecipientsViewerHead.setTextColor(getResources().getColor(2131296304));
    mRecipientsViewer = ((TextView)findViewById(2131820825));
    mRecipientsViewer.setTextColor(AttributeResolver.resolveColor(this, 16843601));
    mRecipientsViewerCount = ((TextView)findViewById(2131820826));
    mRecipientsViewerCount.setTextColor(AttributeResolver.resolveColor(this, 16843601));
    mSuggestionList = ((ListView)findViewById(2131820818));
    mRecipientAdapter = new RecipientsAdapter(this);
    mRecipientAdapter.registerDataSetObserver(new DataSetObserver()
    {
      public void onChanged()
      {
        if ((mRecipientAdapter.getCount() > 0) && (mRecipientEditor.getText().length() > 0))
        {
          mConfirmRecipientButton.setVisibility(0);
          mAddRecipientButton.setVisibility(8);
        }
        for (;;)
        {
          NewMessageActivity.this.switchRecipientsRowView();
          return;
          mConfirmRecipientButton.setVisibility(8);
          mAddRecipientButton.setVisibility(0);
        }
      }
    });
    mSuggestionList.setAdapter(mRecipientAdapter);
    mSuggestionList.setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        paramAnonymousAdapterView = (Cursor)mRecipientAdapter.getItem(paramAnonymousInt);
        mRecipientEditor.setText("");
        if (NewMessageActivity.this.commitRecipient(paramAnonymousAdapterView.getString(3))) {
          NewMessageActivity.this.updateCommitedRecipients();
        }
      }
    });
    mSuggestionList.setOnScrollListener(new AbsListView.OnScrollListener()
    {
      public void onScroll(AbsListView paramAnonymousAbsListView, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3) {}
      
      public void onScrollStateChanged(AbsListView paramAnonymousAbsListView, int paramAnonymousInt)
      {
        if (paramAnonymousInt == 2)
        {
          Contact.pauseCaching();
          return;
        }
        Contact.resumeCaching();
      }
    });
    mSuggestionList.setOnTouchListener(new View.OnTouchListener()
    {
      public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
      {
        NewMessageActivity.this.postHideSoftKeyboard();
        return false;
      }
    });
    mRecentContactGrid = ((StaticGridView)findViewById(2131820819));
    if (MSimUtils.isMSimInserted()) {
      updateSlotButtonStateBySlotId(this, mUseSlotId);
    }
  }
  
  public boolean isPreparedForSending()
  {
    int j = 1;
    if (mAirModeOn) {}
    label40:
    label82:
    label84:
    label87:
    for (;;)
    {
      return false;
      if (mWorkingMessage != null) {
        if ((mWorkingMessage.hasAttachment()) || (mWorkingMessage.hasText()))
        {
          i = 1;
          if (i == 0) {
            break label82;
          }
          i = recipientCount();
          if ((i <= 0) || (i > MmsConfig.getRecipientLimit())) {
            break label84;
          }
        }
      }
      for (int i = j;; i = 0)
      {
        if (i == 0) {
          break label87;
        }
        return MSimUtils.isMSimSlotIdValid(mUseSlotId);
        i = 0;
        break label40;
        i = 0;
        break label40;
        break;
      }
    }
  }
  
  protected void loadDraft()
  {
    if (!mExtraCallPrivateRecipientConv) {
      super.loadDraft();
    }
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    switch (paramInt1)
    {
    }
    do
    {
      return;
    } while (paramIntent == null);
    processPickResult(paramIntent);
  }
  
  protected void onChildSimInfoChanged()
  {
    if (MSimUtils.isMSimInserted()) {
      updateSlotButtonStateBySlotId(this, mUseSlotId);
    }
  }
  
  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default: 
      super.onClick(paramView);
      return;
    case 2131820828: 
      launchMultiplePhonePicker();
      return;
    }
    commitEditingRecipient();
  }
  
  protected void onContactStatusUpdate(final String paramString)
  {
    super.onContactStatusUpdate(paramString);
    runOnUiThread(new Runnable()
    {
      public void run()
      {
        updateRecipientBackground(paramString);
      }
    });
  }
  
  protected void onContactsUpdated(ContactList paramContactList)
  {
    super.onContactsUpdated(paramContactList);
    updateRecipientsViewerText();
    updateRecipientRows();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    Object localObject1 = getIntent();
    long l = ((Intent)localObject1).getLongExtra("thread_id", -1L);
    boolean bool = ((Intent)localObject1).getBooleanExtra("is_from_blocked", false);
    paramBundle = ((Intent)localObject1).getStringExtra("reply_address");
    mExtraCallPrivateRecipientConv = ((Intent)localObject1).getBooleanExtra("private_recipient", false);
    Object localObject2;
    if (l == -1L)
    {
      localObject2 = ((Intent)localObject1).getData();
      if (localObject2 == null)
      {
        localObject1 = ((Intent)localObject1).getStringExtra("address");
        if (!TextUtils.isEmpty((CharSequence)localObject1)) {
          mConversation = Conversation.createNew(this, ContactList.getByNumbers((String)localObject1, true));
        }
      }
    }
    for (;;)
    {
      initRecipientsEditor();
      initialize();
      if (bool) {
        mConversation.setRecipients(ContactList.getByNumbers(paramBundle, false));
      }
      paramBundle = mConversation.getRecipients();
      localObject1 = paramBundle.iterator();
      while (((Iterator)localObject1).hasNext())
      {
        localObject2 = (Contact)((Iterator)localObject1).next();
        addRecipient((Contact)localObject2, ((Contact)localObject2).getNumber());
      }
      LogTag.verbose("Creating an empty conversation", new Object[0]);
      mConversation = Conversation.createNew(this);
      continue;
      LogTag.verbose("Creating a conversation by uri", new Object[0]);
      mConversation = Conversation.createNew(this, (Uri)localObject2);
      continue;
      LogTag.verbose("Creating a conversation by threadId %d", new Object[] { Long.valueOf(l) });
      mConversation = Conversation.createNew(this, l);
    }
    updateSendButtonState();
    syncNumbersToWorkingMessage();
    updateRecipientsViewerText();
    if (paramBundle.size() > 0) {
      mRecipientsViewerHead.setVisibility(8);
    }
    for (;;)
    {
      localObject1 = getResources();
      mRecipientRowPadding = ((Resources)localObject1).getDimensionPixelSize(2131427346);
      mTitleViewHeightOneRow = ((Resources)localObject1).getDimensionPixelSize(2131427368);
      mTitleViewHeightTwoRow = ((Resources)localObject1).getDimensionPixelSize(2131427369);
      mTitleViewHeightThreeRow = ((Resources)localObject1).getDimensionPixelSize(2131427370);
      if (paramBundle.isEmpty()) {
        break;
      }
      mTextEditor.requestFocus();
      return;
      mRecipientsViewerHead.setVisibility(0);
    }
    mRecipientEditor.requestFocus();
  }
  
  protected void onDestroy()
  {
    if (mGuidePopupWindow != null) {
      mGuidePopupWindow.dismiss();
    }
    mGuidePopupWindow = null;
    mRecipientAdapter.changeCursor(null);
    mRecipientAdapter.stop();
    super.onDestroy();
  }
  
  public void onMessageSent()
  {
    super.onMessageSent();
    if (getWindow().isDestroyed()) {
      return;
    }
    runOnUiThread(new Runnable()
    {
      public void run()
      {
        Object localObject = Conversation.get(mConversation.getThreadId());
        ((Conversation)localObject).update();
        if ((!((Conversation)localObject).isPrivate()) || ((((Conversation)localObject).isPrivate()) && (mOrgMsgIsPrivate)))
        {
          localObject = ComposeMessageRouterActivity.createIntent(NewMessageActivity.this, mConversation.getThreadId());
          ((Intent)localObject).putExtra("new_message_count", 1);
          ((Intent)localObject).putExtra("was_soft_keyboard_on", mIsSoftInputEnabled);
          ((Intent)localObject).addFlags(65536);
          ComposeMessageRouterActivity.route(NewMessageActivity.this, (Intent)localObject);
        }
        finish();
        overridePendingTransition(17432576, 17432577);
      }
    });
  }
  
  protected void onNewIntent(Intent paramIntent)
  {
    super.onNewIntent(paramIntent);
    getWindow().setWindowAnimations(0);
    paramIntent.addFlags(65536);
    startActivity(paramIntent);
    finish();
  }
  
  public void onPostLayout()
  {
    if (mAdjustEditorHeight) {
      return;
    }
    Drawable localDrawable = getResources().getDrawable(2130837929);
    if (localDrawable != null)
    {
      Rect localRect = new Rect();
      localDrawable.getPadding(localRect);
      ViewGroup.LayoutParams localLayoutParams = mRecipientEditor.getLayoutParams();
      int i = localDrawable.getIntrinsicHeight();
      int j = mRecipientEditor.getMeasuredHeight();
      int k = bottom;
      height = Math.max(i, top + (j + k));
    }
    mAdjustEditorHeight = true;
  }
  
  public void onPreMeasure(SizeAwareLinearLayout paramSizeAwareLinearLayout, int paramInt1, int paramInt2)
  {
    drawRecipientContactsGrid(paramInt1);
    mDrawContactPanelRunnable.run();
    super.onPreMeasure(paramSizeAwareLinearLayout, paramInt1, paramInt2);
  }
  
  protected void onPushStatusChanged()
  {
    super.onPushStatusChanged();
    postUpdateRecipientsBackground(false);
  }
  
  protected void onStart()
  {
    super.onStart();
    initRecentList();
    Contact.resumeCaching();
  }
  
  protected void onStop()
  {
    super.onStop();
    cancelUpdateCommitedRecipients();
    Contact.pauseCaching();
    if (mPickContactsTask != null) {
      mPickContactsTask.cancel(true);
    }
    mPickContactsTask = null;
    if ((mPickContactsProgressDialog != null) && (mPickContactsProgressDialog.isShowing())) {
      mPickContactsProgressDialog.dismiss();
    }
    mPickContactsProgressDialog = null;
  }
  
  public void onWindowFocusChanged(boolean paramBoolean)
  {
    super.onWindowFocusChanged(paramBoolean);
  }
  
  protected void postExit()
  {
    super.postExit();
    int i = getIntent().getIntExtra("exit_animation_in", -1);
    int j = getIntent().getIntExtra("exit_animation_out", -1);
    if ((i != -1) && (j != -1)) {
      overridePendingTransition(i, j);
    }
  }
  
  protected int recipientCount()
  {
    if (hasUncommitedRecipient()) {
      return mCommitedRecipients.size() + 1;
    }
    return mCommitedRecipients.size();
  }
  
  protected void saveDraft(boolean paramBoolean)
  {
    commitEditingRecipient();
    writeContactNumbers();
    super.saveDraft(paramBoolean);
  }
  
  public void sendMessage(int paramInt)
  {
    commitEditingRecipient();
    if (hasInvalidRecipient())
    {
      if (hasValidRecipient())
      {
        String str = getString(2131361859, new Object[] { formatInvalidNumbers() });
        new AlertDialog.Builder(this).setIconAttribute(16843605).setTitle(str).setMessage(2131361864).setPositiveButton(2131361896, new SendIgnoreInvalidRecipientListener(paramInt)).setNegativeButton(2131361892, new CancelSendingListener(null)).show();
        return;
      }
      new AlertDialog.Builder(this).setIconAttribute(16843605).setTitle(2131361865).setMessage(2131361866).setPositiveButton(2131361891, new CancelSendingListener(null)).show();
      return;
    }
    writeContactNumbers();
    checkAndSendMessage(true, paramInt);
  }
  
  public void startNicknamePicker(boolean paramBoolean)
  {
    commitEditingRecipient();
    super.startNicknamePicker(paramBoolean);
  }
  
  protected void updateRecipientBackground(String paramString)
  {
    int j = mRecipientRows.getChildCount();
    int i = 0;
    for (;;)
    {
      Object localObject;
      if (i < j)
      {
        localObject = mRecipientRows.getChildAt(i);
        if ((!(localObject instanceof TextView)) || (!paramString.equals(((View)localObject).getTag()))) {
          break label105;
        }
        localObject = (TextView)localObject;
        if (allowMx(paramString))
        {
          ((TextView)localObject).setTextColor(getResources().getColor(2131296361));
          ((TextView)localObject).setBackgroundResource(2130837872);
        }
      }
      else
      {
        return;
      }
      ((TextView)localObject).setTextColor(AttributeResolver.resolveColor(this, 16843601));
      ((TextView)localObject).setBackgroundResource(2130837928);
      return;
      label105:
      i += 1;
    }
  }
  
  protected boolean willDiscardDraft()
  {
    return !hasValidRecipient();
  }
  
  private class CancelSendingListener
    implements DialogInterface.OnClickListener
  {
    private CancelSendingListener() {}
    
    public void onClick(DialogInterface paramDialogInterface, int paramInt)
    {
      paramDialogInterface.dismiss();
    }
  }
  
  private class SendIgnoreInvalidRecipientListener
    implements DialogInterface.OnClickListener
  {
    private final int mSlotId;
    
    public SendIgnoreInvalidRecipientListener(int paramInt)
    {
      mSlotId = paramInt;
    }
    
    public void onClick(DialogInterface paramDialogInterface, int paramInt)
    {
      checkAndSendMessage(true, mSlotId);
      paramDialogInterface.dismiss();
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.NewMessageActivity
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */