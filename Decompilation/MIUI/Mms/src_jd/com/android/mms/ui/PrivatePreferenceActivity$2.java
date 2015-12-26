package com.android.mms.ui;

import android.content.ContentProviderResult;
import android.os.AsyncTask;
import java.util.HashSet;
import miui.app.ProgressDialog;

class PrivatePreferenceActivity$2
  extends AsyncTask<Void, Void, ContentProviderResult[]>
{
  PrivatePreferenceActivity$2(PrivatePreferenceActivity paramPrivatePreferenceActivity, HashSet paramHashSet) {}
  
  /* Error */
  protected ContentProviderResult[] doInBackground(Void... paramVarArgs)
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
    //   0	160	1	paramVarArgs	Void[]
    //   12	95	2	localObject	Object
    //   20	98	3	localIterator	java.util.Iterator
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
  
  protected void onPostExecute(ContentProviderResult[] paramArrayOfContentProviderResult)
  {
    PrivatePreferenceActivity.access$402(this$0, null);
    if (paramArrayOfContentProviderResult != null)
    {
      if (PrivatePreferenceActivity.access$500(this$0) != null)
      {
        PrivatePreferenceActivity.access$500(this$0).dismiss();
        PrivatePreferenceActivity.access$502(this$0, null);
      }
      PrivatePreferenceActivity.access$600(PrivatePreferenceActivity.access$000(this$0));
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.PrivatePreferenceActivity.2
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */