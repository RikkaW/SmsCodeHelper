package com.android.mms.ui;

import android.app.Activity;
import android.content.AsyncQueryHandler;
import android.content.BroadcastReceiver;
import android.content.ContentProviderOperation;
import android.content.ContentProviderOperation.Builder;
import android.content.ContentProviderResult;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnShowListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.OperationApplicationException;
import android.content.res.Resources;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.RemoteException;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceCategory;
import android.preference.PreferenceScreen;
import android.provider.Settings.System;
import android.security.ChooseLockSettingsHelper;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.android.mms.data.Contact;
import com.android.mms.data.Contact.UpdateListener;
import com.android.mms.data.ContactList;
import com.android.mms.data.Conversation;
import com.google.android.collect.Lists;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import miui.R.drawable;
import miui.app.AlertDialog.Builder;
import miui.app.ProgressDialog;
import miui.preference.PreferenceActivity;
import miui.provider.ExtraTelephony.PrivateAddresses;

public class PrivatePreferenceActivity
  extends PreferenceActivity
  implements Preference.OnPreferenceChangeListener, Contact.UpdateListener
{
  private Activity mActivity;
  private ChooseLockSettingsHelper mChooseLockSettingsHelper;
  private AsyncTask<Void, Void, ContentProviderResult[]> mDeleteTask;
  private ProgressDialog mExportingProgressDialog;
  private BroadcastReceiver mFinishReceiver = new BroadcastReceiver()
  {
    public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
    {
      paramAnonymousContext = paramAnonymousIntent.getAction();
      if ("android.intent.action.CLOSE_SYSTEM_DIALOGS".equals(paramAnonymousContext)) {
        if ("homekey".equals(paramAnonymousIntent.getStringExtra("reason"))) {
          finish();
        }
      }
      while (!"android.intent.action.SCREEN_OFF".equals(paramAnonymousContext)) {
        return;
      }
      finish();
    }
  };
  private ProgressDialog mImportingProgressDialog;
  private AsyncTask<Void, Void, ContentProviderResult[]> mInsertTask;
  private TextView mNumberEdit;
  PrivateContactPreference.OnClickDeleteBtnListener mOnClickDelBtnListener = new PrivateContactPreference.OnClickDeleteBtnListener()
  {
    public void onClick(long paramAnonymousLong, Contact paramAnonymousContact)
    {
      PrivatePreferenceActivity.this.confirmExportPrivateContactDialog(new PrivatePreferenceActivity.ExportPrivateContactListener(PrivatePreferenceActivity.this, paramAnonymousLong, paramAnonymousContact, mQueryHandler, mActivity), mActivity);
    }
  };
  private HashMap<Contact, PrivateContactPreference> mPreferenceMap;
  private PreferenceCategory mPrivateContactListPref;
  private CheckBoxPreference mPrivateNewNotificationPref;
  private CheckBoxPreference mPrivateProtectUsagePref;
  private CheckBoxPreference mPrivateProtectVisiblePref;
  private ContactListQueryHandler mQueryHandler;
  
  private void addManually()
  {
    View localView = LayoutInflater.from(mActivity).inflate(2130968690, null);
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(mActivity);
    localBuilder.setView(localView).setTitle(2131362217);
    mNumberEdit = ((TextView)localView.findViewById(2131820847));
    localBuilder.setOnShowListener(new DialogInterface.OnShowListener()
    {
      public void onShow(DialogInterface paramAnonymousDialogInterface)
      {
        MessageUtils.requestInputMethod(mActivity, mNumberEdit);
      }
    });
    localBuilder.setPositiveButton(2131362032, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface = mNumberEdit.getText().toString();
        if (TextUtils.isEmpty(paramAnonymousDialogInterface))
        {
          Toast.makeText(mActivity, 2131362264, 0).show();
          return;
        }
        HashSet localHashSet = new HashSet();
        localHashSet.add(paramAnonymousDialogInterface);
        PrivatePreferenceActivity.this.startAsyncInsertContact(mQueryHandler, localHashSet);
      }
    });
    localBuilder.setNegativeButton(17039360, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface.dismiss();
      }
    });
    localBuilder.show();
  }
  
  private void addNewPrivateContactDialog()
  {
    String str1 = mActivity.getResources().getString(2131362230);
    String str2 = mActivity.getResources().getString(2131362231);
    DialogInterface.OnClickListener local7 = new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface.dismiss();
        switch (paramAnonymousInt)
        {
        default: 
          return;
        case 0: 
          PrivatePreferenceActivity.this.addManually();
          return;
        }
        PrivatePreferenceActivity.this.launchMultiplePhonePicker();
      }
    };
    new AlertDialog.Builder(mActivity).setCancelable(true).setItems(new String[] { str1, str2 }, local7).show();
  }
  
  public static boolean checkPrivateMessage(Context paramContext, String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      return false;
    }
    paramContext = paramContext.getContentResolver().query(ExtraTelephony.PrivateAddresses.CONTENT_URI, null, "address=?", new String[] { paramString }, null);
    if (paramContext != null) {}
    try
    {
      int i = paramContext.getCount();
      return i > 0;
    }
    finally
    {
      paramContext.close();
    }
  }
  
  private void confirmExportPrivateContactDialog(ExportPrivateContactListener paramExportPrivateContactListener, Context paramContext)
  {
    View localView = View.inflate(paramContext, 2130968606, null);
    new AlertDialog.Builder(paramContext).setTitle(2131362227).setCancelable(true).setPositiveButton(2131361891, paramExportPrivateContactListener).setNegativeButton(2131361892, null).setView(localView).show();
  }
  
  private void launchMultiplePhonePicker()
  {
    Intent localIntent = new Intent("com.android.contacts.action.GET_MULTIPLE_PHONES");
    localIntent.addCategory("android.intent.category.DEFAULT");
    localIntent.setType("vnd.android.cursor.dir/phone_v2");
    localIntent.putExtra("android.intent.extra.include_unknown_numbers", true);
    startActivityForResult(localIntent, 2);
  }
  
  private void processPickResult(Intent paramIntent)
  {
    Object localObject = ContactList.blockingGetByUris(paramIntent.getParcelableArrayExtra("com.android.contacts.extra.PHONE_URIS"));
    paramIntent = new HashSet();
    localObject = ((ContactList)localObject).iterator();
    while (((Iterator)localObject).hasNext()) {
      paramIntent.add(((Contact)((Iterator)localObject).next()).getNumber());
    }
    mImportingProgressDialog = ProgressDialog.show(mActivity, null, mActivity.getString(2131362225), true, false);
    startAsyncInsertContact(mQueryHandler, paramIntent);
  }
  
  private void registerFinishReceiver()
  {
    IntentFilter localIntentFilter = new IntentFilter();
    localIntentFilter.addAction("android.intent.action.CLOSE_SYSTEM_DIALOGS");
    localIntentFilter.addAction("android.intent.action.SCREEN_OFF");
    registerReceiver(mFinishReceiver, localIntentFilter);
  }
  
  private void startAsyncDeleteContact(AsyncQueryHandler paramAsyncQueryHandler, final long paramLong, String paramString)
  {
    mDeleteTask = new AsyncTask()
    {
      protected ContentProviderResult[] doInBackground(Void... paramAnonymousVarArgs)
      {
        paramAnonymousVarArgs = Lists.newArrayList();
        paramAnonymousVarArgs.add(ContentProviderOperation.newDelete(ContentUris.withAppendedId(ExtraTelephony.PrivateAddresses.CONTENT_URI, paramLong)).build());
        try
        {
          paramAnonymousVarArgs = getContentResolver().applyBatch("mms-sms", paramAnonymousVarArgs);
          if (paramAnonymousVarArgs != null) {
            PrivatePreferenceActivity.this.updateConversation(PrivatePreferenceActivity.this, val$address, false);
          }
          return paramAnonymousVarArgs;
        }
        catch (RemoteException paramAnonymousVarArgs)
        {
          paramAnonymousVarArgs.printStackTrace();
          return null;
        }
        catch (OperationApplicationException paramAnonymousVarArgs)
        {
          for (;;)
          {
            paramAnonymousVarArgs.printStackTrace();
          }
        }
      }
      
      protected void onPostExecute(ContentProviderResult[] paramAnonymousArrayOfContentProviderResult)
      {
        PrivatePreferenceActivity.access$702(PrivatePreferenceActivity.this, null);
        if (paramAnonymousArrayOfContentProviderResult != null)
        {
          if (mExportingProgressDialog != null)
          {
            mExportingProgressDialog.dismiss();
            PrivatePreferenceActivity.access$802(PrivatePreferenceActivity.this, null);
          }
          PrivatePreferenceActivity.startAsyncQueryContact(mQueryHandler);
        }
      }
    }.execute(new Void[0]);
  }
  
  private void startAsyncInsertContact(AsyncQueryHandler paramAsyncQueryHandler, final HashSet<String> paramHashSet)
  {
    mInsertTask = new AsyncTask()
    {
      /* Error */
      protected ContentProviderResult[] doInBackground(Void... paramAnonymousVarArgs)
      {
        // Byte code:
        //   0: invokestatic 41	com/google/android/collect/Lists:newArrayList	()Ljava/util/ArrayList;
        //   3: astore_1
        //   4: new 43	android/content/ContentValues
        //   7: dup
        //   8: iconst_1
        //   9: invokespecial 46	android/content/ContentValues:<init>	(I)V
        //   12: astore_2
        //   13: aload_0
        //   14: getfield 20	com/android/mms/ui/PrivatePreferenceActivity$2:val$addresses	Ljava/util/HashSet;
        //   17: invokevirtual 52	java/util/HashSet:iterator	()Ljava/util/Iterator;
        //   20: astore_3
        //   21: aload_3
        //   22: invokeinterface 58 1 0
        //   27: ifeq +51 -> 78
        //   30: aload_3
        //   31: invokeinterface 62 1 0
        //   36: checkcast 64	java/lang/String
        //   39: astore 4
        //   41: aload 4
        //   43: invokestatic 70	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
        //   46: ifne -25 -> 21
        //   49: aload_2
        //   50: ldc 72
        //   52: aload 4
        //   54: invokevirtual 76	android/content/ContentValues:put	(Ljava/lang/String;Ljava/lang/String;)V
        //   57: aload_1
        //   58: getstatic 82	miui/provider/ExtraTelephony$PrivateAddresses:CONTENT_URI	Landroid/net/Uri;
        //   61: invokestatic 88	android/content/ContentProviderOperation:newInsert	(Landroid/net/Uri;)Landroid/content/ContentProviderOperation$Builder;
        //   64: aload_2
        //   65: invokevirtual 94	android/content/ContentProviderOperation$Builder:withValues	(Landroid/content/ContentValues;)Landroid/content/ContentProviderOperation$Builder;
        //   68: invokevirtual 98	android/content/ContentProviderOperation$Builder:build	()Landroid/content/ContentProviderOperation;
        //   71: invokevirtual 104	java/util/ArrayList:add	(Ljava/lang/Object;)Z
        //   74: pop
        //   75: goto -54 -> 21
        //   78: aload_0
        //   79: getfield 18	com/android/mms/ui/PrivatePreferenceActivity$2:this$0	Lcom/android/mms/ui/PrivatePreferenceActivity;
        //   82: invokevirtual 108	com/android/mms/ui/PrivatePreferenceActivity:getContentResolver	()Landroid/content/ContentResolver;
        //   85: ldc 110
        //   87: aload_1
        //   88: invokevirtual 116	android/content/ContentResolver:applyBatch	(Ljava/lang/String;Ljava/util/ArrayList;)[Landroid/content/ContentProviderResult;
        //   91: astore_2
        //   92: aload_2
        //   93: astore_1
        //   94: aload_2
        //   95: ifnull +55 -> 150
        //   98: aload_0
        //   99: getfield 20	com/android/mms/ui/PrivatePreferenceActivity$2:val$addresses	Ljava/util/HashSet;
        //   102: invokevirtual 52	java/util/HashSet:iterator	()Ljava/util/Iterator;
        //   105: astore_3
        //   106: aload_2
        //   107: astore_1
        //   108: aload_3
        //   109: invokeinterface 58 1 0
        //   114: ifeq +36 -> 150
        //   117: aload_3
        //   118: invokeinterface 62 1 0
        //   123: checkcast 64	java/lang/String
        //   126: astore_1
        //   127: aload_0
        //   128: getfield 18	com/android/mms/ui/PrivatePreferenceActivity$2:this$0	Lcom/android/mms/ui/PrivatePreferenceActivity;
        //   131: aload_0
        //   132: getfield 18	com/android/mms/ui/PrivatePreferenceActivity$2:this$0	Lcom/android/mms/ui/PrivatePreferenceActivity;
        //   135: aload_1
        //   136: iconst_1
        //   137: invokestatic 120	com/android/mms/ui/PrivatePreferenceActivity:access$300	(Lcom/android/mms/ui/PrivatePreferenceActivity;Landroid/content/Context;Ljava/lang/String;Z)V
        //   140: goto -34 -> 106
        //   143: astore_1
        //   144: aload_1
        //   145: invokevirtual 123	android/os/RemoteException:printStackTrace	()V
        //   148: aconst_null
        //   149: astore_1
        //   150: aload_1
        //   151: areturn
        //   152: astore_1
        //   153: aload_1
        //   154: invokevirtual 124	android/content/OperationApplicationException:printStackTrace	()V
        //   157: goto -9 -> 148
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	160	0	this	2
        //   0	160	1	paramAnonymousVarArgs	Void[]
        //   12	95	2	localObject	Object
        //   20	98	3	localIterator	Iterator
        //   39	14	4	str	String
        // Exception table:
        //   from	to	target	type
        //   78	92	143	android/os/RemoteException
        //   98	106	143	android/os/RemoteException
        //   108	140	143	android/os/RemoteException
        //   78	92	152	android/content/OperationApplicationException
        //   98	106	152	android/content/OperationApplicationException
        //   108	140	152	android/content/OperationApplicationException
      }
      
      protected void onPostExecute(ContentProviderResult[] paramAnonymousArrayOfContentProviderResult)
      {
        PrivatePreferenceActivity.access$402(PrivatePreferenceActivity.this, null);
        if (paramAnonymousArrayOfContentProviderResult != null)
        {
          if (mImportingProgressDialog != null)
          {
            mImportingProgressDialog.dismiss();
            PrivatePreferenceActivity.access$502(PrivatePreferenceActivity.this, null);
          }
          PrivatePreferenceActivity.startAsyncQueryContact(mQueryHandler);
        }
      }
    }.execute(new Void[0]);
  }
  
  private static void startAsyncQueryContact(AsyncQueryHandler paramAsyncQueryHandler)
  {
    paramAsyncQueryHandler.cancelOperation(1701);
    paramAsyncQueryHandler.startQuery(1701, null, ExtraTelephony.PrivateAddresses.CONTENT_URI, null, null, null, null);
  }
  
  private void unRegisterFinishReceiver()
  {
    unregisterReceiver(mFinishReceiver);
  }
  
  private void updateCacheAndUI(Cursor paramCursor)
  {
    paramCursor.moveToPosition(-1);
    mPreferenceMap.clear();
    mPrivateContactListPref.removeAll();
    while (paramCursor.moveToNext())
    {
      long l = paramCursor.getLong(0);
      Contact localContact = Contact.get(paramCursor.getString(1));
      PrivateContactPreference localPrivateContactPreference = new PrivateContactPreference(mActivity);
      localPrivateContactPreference.setShowInfo(l, localContact);
      localPrivateContactPreference.setOnDeleteBtnClickListener(mOnClickDelBtnListener);
      mPreferenceMap.put(localContact, localPrivateContactPreference);
      mPrivateContactListPref.addPreference(localPrivateContactPreference);
    }
    if (mPrivateContactListPref.getPreferenceCount() == 0) {
      getPreferenceScreen().removePreference(mPrivateContactListPref);
    }
    for (;;)
    {
      Contact.addListener(this);
      Contact.invalidatePhotoCache();
      Contact.asyncloadAllInBackground();
      return;
      getPreferenceScreen().addPreference(mPrivateContactListPref);
    }
  }
  
  private void updateConversation(Context paramContext, String paramString, boolean paramBoolean)
  {
    if (TextUtils.isEmpty(paramString)) {
      Log.d("PrivatePreferenceActivity", "updateConversation address is null");
    }
    do
    {
      return;
      paramContext = Conversation.getFromCache(paramContext, ContactList.getByNumbers(paramString, false));
      if (paramContext == null)
      {
        Log.d("PrivatePreferenceActivity", "updateConversation conv is null");
        return;
      }
      if ((paramBoolean) && (!paramContext.isPrivate()))
      {
        paramContext.setPrivate(true);
        return;
      }
    } while ((paramBoolean) || (!paramContext.isPrivate()));
    paramContext.setPrivate(false);
  }
  
  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (paramInt1 == 0) {
      if (paramInt2 == -1) {
        mPrivateProtectUsagePref.setChecked(true);
      }
    }
    do
    {
      return;
      mPrivateProtectUsagePref.setChecked(false);
      return;
      if (paramInt1 == 1)
      {
        if (paramInt2 == -1)
        {
          mPrivateProtectUsagePref.setChecked(false);
          return;
        }
        mPrivateProtectUsagePref.setChecked(true);
        return;
      }
    } while ((paramInt1 != 2) || (paramIntent == null));
    processPickResult(paramIntent);
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    addPreferencesFromResource(2131099655);
    mActivity = this;
    mQueryHandler = new ContactListQueryHandler(mActivity.getContentResolver());
    mPreferenceMap = new HashMap();
    mChooseLockSettingsHelper = new ChooseLockSettingsHelper(this, 1);
    mPrivateNewNotificationPref = ((CheckBoxPreference)findPreference("pref_key_private_new_notification"));
    mPrivateNewNotificationPref.setOnPreferenceChangeListener(this);
    mPrivateProtectUsagePref = ((CheckBoxPreference)findPreference("pref_key_private_protect_usage"));
    mPrivateProtectUsagePref.setOnPreferenceChangeListener(this);
    mPrivateProtectVisiblePref = ((CheckBoxPreference)findPreference("pref_key_private_protect_visible"));
    mPrivateProtectVisiblePref.setOnPreferenceChangeListener(this);
    mPrivateContactListPref = ((PreferenceCategory)findPreference("pref_key_private_contact_list"));
    registerFinishReceiver();
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    paramMenu.add(0, 0, 0, 2131362217).setIcon(R.drawable.action_button_new_light).setShowAsAction(1);
    return true;
  }
  
  protected void onDestroy()
  {
    unRegisterFinishReceiver();
    mQueryHandler.cancelOperation(1701);
    if (mInsertTask != null)
    {
      mInsertTask.cancel(true);
      mInsertTask = null;
    }
    if (mDeleteTask != null)
    {
      mDeleteTask.cancel(true);
      mDeleteTask = null;
    }
    if (mImportingProgressDialog != null)
    {
      mImportingProgressDialog.dismiss();
      mImportingProgressDialog = null;
    }
    if (mExportingProgressDialog != null)
    {
      mExportingProgressDialog.dismiss();
      mExportingProgressDialog = null;
    }
    super.onDestroy();
  }
  
  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    switch (paramMenuItem.getItemId())
    {
    default: 
      return false;
    case 0: 
      addNewPrivateContactDialog();
    }
    for (;;)
    {
      return true;
      finish();
    }
  }
  
  public boolean onPreferenceChange(Preference paramPreference, Object paramObject)
  {
    int j = 0;
    int i = 0;
    if (paramPreference == mPrivateNewNotificationPref)
    {
      bool = ((Boolean)paramObject).booleanValue();
      paramPreference = getContentResolver();
      if (bool) {
        i = 1;
      }
      Settings.System.putInt(paramPreference, "pref_key_enable_private_notification", i);
    }
    do
    {
      do
      {
        return true;
        if (paramPreference != mPrivateProtectUsagePref) {
          break;
        }
        if (((Boolean)paramObject).booleanValue())
        {
          paramPreference = new Intent();
          paramPreference.setClassName("com.android.settings", "com.android.settings.ChooseSmsLockPattern");
          paramPreference.setAction("android.app.action.SET_SMS_NEW_PASSWORD");
          startActivityForResult(paramPreference, 0);
          return true;
        }
      } while (!mChooseLockSettingsHelper.isPrivateSmsEnabled());
      paramPreference = new Intent();
      paramPreference.setClassName("com.android.settings", "com.android.settings.ConfirmSmsLockPattern");
      paramPreference.setAction("android.app.action.CONFIRM_SMS_PASSWORD");
      paramPreference.putExtra("confirm_purpose", 1);
      startActivityForResult(paramPreference, 1);
      return true;
    } while (paramPreference != mPrivateProtectVisiblePref);
    boolean bool = ((Boolean)paramObject).booleanValue();
    paramPreference = getContentResolver();
    i = j;
    if (bool) {
      i = 1;
    }
    Settings.System.putInt(paramPreference, "private_sms_lock_pattern_visible_pattern", i);
    return true;
  }
  
  public boolean onPrepareOptionsMenu(Menu paramMenu)
  {
    return true;
  }
  
  protected void onResume()
  {
    super.onResume();
    mPrivateNewNotificationPref.setChecked(MessageUtils.getPrefPrivateNotificationEnabled(this));
    mPrivateProtectUsagePref.setChecked(mChooseLockSettingsHelper.isPrivateSmsEnabled());
    mPrivateProtectVisiblePref.setChecked(MessageUtils.getPrefPrivateLockPatternVisible(this));
  }
  
  protected void onStart()
  {
    super.onStart();
    startAsyncQueryContact(mQueryHandler);
  }
  
  public void onUpdate(Contact paramContact)
  {
    paramContact = (PrivateContactPreference)mPreferenceMap.get(paramContact);
    if (paramContact != null) {
      paramContact.refreshShowInfo();
    }
  }
  
  private final class ContactListQueryHandler
    extends AsyncQueryHandler
  {
    public ContactListQueryHandler(ContentResolver paramContentResolver)
    {
      super();
    }
    
    protected void onQueryComplete(int paramInt, Object paramObject, Cursor paramCursor)
    {
      switch (paramInt)
      {
      }
      do
      {
        return;
      } while (paramCursor == null);
      try
      {
        PrivatePreferenceActivity.this.updateCacheAndUI(paramCursor);
        return;
      }
      finally
      {
        paramCursor.close();
      }
    }
  }
  
  private class ExportPrivateContactListener
    implements DialogInterface.OnClickListener
  {
    private final Contact mContact;
    private final Context mContext;
    private final AsyncQueryHandler mHandler;
    private final long mItemId;
    
    public ExportPrivateContactListener(long paramLong, Contact paramContact, AsyncQueryHandler paramAsyncQueryHandler, Context paramContext)
    {
      mItemId = paramLong;
      mContact = paramContact;
      mContext = paramContext;
      mHandler = paramAsyncQueryHandler;
    }
    
    public void onClick(DialogInterface paramDialogInterface, int paramInt)
    {
      PrivatePreferenceActivity.access$802(PrivatePreferenceActivity.this, ProgressDialog.show(mContext, null, mContext.getString(2131362226), true, false));
      PrivatePreferenceActivity.this.startAsyncDeleteContact(mHandler, mItemId, mContact.getNumber());
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.PrivatePreferenceActivity
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */