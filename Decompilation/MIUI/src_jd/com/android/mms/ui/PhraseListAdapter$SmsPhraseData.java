package com.android.mms.ui;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import com.android.mms.LogTag;
import java.util.Iterator;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.Vector;

class PhraseListAdapter$SmsPhraseData
{
  private final String PREF_SMS_PHRASE_DATA;
  private final String PREF_SMS_PHRASE_DATA_COUNT;
  private final String PREF_SMS_PHRASE_VERSION = "sms_phrase_version";
  private SharedPreferences mPref;
  private Vector<String> mSmsPhrases;
  private final String separator = "\t";
  
  public PhraseListAdapter$SmsPhraseData(PhraseListAdapter paramPhraseListAdapter)
  {
    mPref = PreferenceManager.getDefaultSharedPreferences(PhraseListAdapter.access$000(paramPhraseListAdapter));
    upgradeSmsPhraseData();
    paramPhraseListAdapter = Locale.getDefault().getLanguage();
    String str = Locale.getDefault().getCountry();
    PREF_SMS_PHRASE_DATA_COUNT = ("sms_phrase_data_count_" + paramPhraseListAdapter + "_" + str);
    PREF_SMS_PHRASE_DATA = ("sms_phrase_data_" + paramPhraseListAdapter + "_" + str);
    paramPhraseListAdapter = mPref.getString(PREF_SMS_PHRASE_DATA, "");
    if (mPref.getInt(PREF_SMS_PHRASE_DATA_COUNT, -1) == -1)
    {
      initSmsPhraseList();
      saveSmsPhraseDate();
      return;
    }
    parseSmsPhraseData(paramPhraseListAdapter);
  }
  
  /* Error */
  private void initSmsPhraseList()
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: aconst_null
    //   3: astore_1
    //   4: aconst_null
    //   5: astore 4
    //   7: aload_0
    //   8: new 111	java/util/Vector
    //   11: dup
    //   12: invokespecial 112	java/util/Vector:<init>	()V
    //   15: putfield 114	com/android/mms/ui/PhraseListAdapter$SmsPhraseData:mSmsPhrases	Ljava/util/Vector;
    //   18: new 116	java/io/BufferedReader
    //   21: dup
    //   22: new 118	java/io/InputStreamReader
    //   25: dup
    //   26: aload_0
    //   27: getfield 23	com/android/mms/ui/PhraseListAdapter$SmsPhraseData:this$0	Lcom/android/mms/ui/PhraseListAdapter;
    //   30: invokestatic 38	com/android/mms/ui/PhraseListAdapter:access$000	(Lcom/android/mms/ui/PhraseListAdapter;)Landroid/content/Context;
    //   33: invokevirtual 124	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   36: ldc 125
    //   38: invokevirtual 131	android/content/res/Resources:openRawResource	(I)Ljava/io/InputStream;
    //   41: invokespecial 134	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   44: invokespecial 137	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   47: astore_2
    //   48: aload_2
    //   49: invokevirtual 140	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   52: astore_1
    //   53: aload_1
    //   54: ifnull +31 -> 85
    //   57: aload_0
    //   58: getfield 114	com/android/mms/ui/PhraseListAdapter$SmsPhraseData:mSmsPhrases	Ljava/util/Vector;
    //   61: aload_1
    //   62: invokevirtual 144	java/util/Vector:add	(Ljava/lang/Object;)Z
    //   65: pop
    //   66: goto -18 -> 48
    //   69: astore_3
    //   70: aload_2
    //   71: astore_1
    //   72: aload_3
    //   73: invokevirtual 147	android/content/res/Resources$NotFoundException:printStackTrace	()V
    //   76: aload_2
    //   77: ifnull +7 -> 84
    //   80: aload_2
    //   81: invokevirtual 150	java/io/BufferedReader:close	()V
    //   84: return
    //   85: aload_2
    //   86: ifnull +7 -> 93
    //   89: aload_2
    //   90: invokevirtual 150	java/io/BufferedReader:close	()V
    //   93: return
    //   94: astore_1
    //   95: aload_1
    //   96: invokevirtual 151	java/io/IOException:printStackTrace	()V
    //   99: return
    //   100: astore_1
    //   101: aload_1
    //   102: invokevirtual 151	java/io/IOException:printStackTrace	()V
    //   105: return
    //   106: astore_1
    //   107: aload_3
    //   108: astore_2
    //   109: aload_1
    //   110: astore_3
    //   111: aload_2
    //   112: astore_1
    //   113: aload_3
    //   114: invokevirtual 151	java/io/IOException:printStackTrace	()V
    //   117: aload_2
    //   118: ifnull -34 -> 84
    //   121: aload_2
    //   122: invokevirtual 150	java/io/BufferedReader:close	()V
    //   125: return
    //   126: astore_1
    //   127: aload_1
    //   128: invokevirtual 151	java/io/IOException:printStackTrace	()V
    //   131: return
    //   132: astore_2
    //   133: aload_1
    //   134: ifnull +7 -> 141
    //   137: aload_1
    //   138: invokevirtual 150	java/io/BufferedReader:close	()V
    //   141: aload_2
    //   142: athrow
    //   143: astore_1
    //   144: aload_1
    //   145: invokevirtual 151	java/io/IOException:printStackTrace	()V
    //   148: goto -7 -> 141
    //   151: astore_3
    //   152: aload_2
    //   153: astore_1
    //   154: aload_3
    //   155: astore_2
    //   156: goto -23 -> 133
    //   159: astore_3
    //   160: goto -49 -> 111
    //   163: astore_3
    //   164: aload 4
    //   166: astore_2
    //   167: goto -97 -> 70
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	170	0	this	SmsPhraseData
    //   3	69	1	localObject1	Object
    //   94	2	1	localIOException1	java.io.IOException
    //   100	2	1	localIOException2	java.io.IOException
    //   106	4	1	localIOException3	java.io.IOException
    //   112	1	1	localObject2	Object
    //   126	12	1	localIOException4	java.io.IOException
    //   143	2	1	localIOException5	java.io.IOException
    //   153	1	1	localObject3	Object
    //   47	75	2	localObject4	Object
    //   132	21	2	localObject5	Object
    //   155	12	2	localObject6	Object
    //   1	1	3	localObject7	Object
    //   69	39	3	localNotFoundException1	android.content.res.Resources.NotFoundException
    //   110	4	3	localObject8	Object
    //   151	4	3	localObject9	Object
    //   159	1	3	localIOException6	java.io.IOException
    //   163	1	3	localNotFoundException2	android.content.res.Resources.NotFoundException
    //   5	160	4	localObject10	Object
    // Exception table:
    //   from	to	target	type
    //   48	53	69	android/content/res/Resources$NotFoundException
    //   57	66	69	android/content/res/Resources$NotFoundException
    //   89	93	94	java/io/IOException
    //   80	84	100	java/io/IOException
    //   18	48	106	java/io/IOException
    //   121	125	126	java/io/IOException
    //   18	48	132	finally
    //   72	76	132	finally
    //   113	117	132	finally
    //   137	141	143	java/io/IOException
    //   48	53	151	finally
    //   57	66	151	finally
    //   48	53	159	java/io/IOException
    //   57	66	159	java/io/IOException
    //   18	48	163	android/content/res/Resources$NotFoundException
  }
  
  private void parseSmsPhraseData(String paramString)
  {
    mSmsPhrases = new Vector();
    paramString = new StringTokenizer(paramString, "\t");
    while (paramString.hasMoreTokens()) {
      mSmsPhrases.add(paramString.nextToken());
    }
  }
  
  private void saveSmsPhraseDate()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    Object localObject = mSmsPhrases.iterator();
    while (((Iterator)localObject).hasNext())
    {
      localStringBuilder.append((String)((Iterator)localObject).next());
      localStringBuilder.append("\t");
    }
    if (localStringBuilder.length() != 0) {
      localStringBuilder.delete(localStringBuilder.lastIndexOf("\t"), localStringBuilder.length());
    }
    localObject = mPref.edit();
    ((SharedPreferences.Editor)localObject).putString(PREF_SMS_PHRASE_DATA, localStringBuilder.toString());
    ((SharedPreferences.Editor)localObject).putInt(PREF_SMS_PHRASE_DATA_COUNT, mSmsPhrases.size());
    ((SharedPreferences.Editor)localObject).commit();
  }
  
  private void upgradeSmsPhraseData()
  {
    int i = mPref.getInt("sms_phrase_version", 1);
    if (i == 2) {
      return;
    }
    LogTag.debug("Upgrading phrase data from ver %d to %d", new Object[] { Integer.valueOf(i), Integer.valueOf(2) });
    switch (i)
    {
    }
    for (;;)
    {
      mPref.edit().putInt("sms_phrase_version", 2).commit();
      return;
      String str = mPref.getString("sms_phrase_data_zh_TW", "");
      if (!str.isEmpty())
      {
        str = str.replaceAll("稍後回複", "稍後回覆");
        mPref.edit().putString("sms_phrase_data_zh_TW", str).commit();
      }
    }
  }
  
  public void addPhrase(String paramString)
  {
    mSmsPhrases.add(0, paramString);
    saveSmsPhraseDate();
  }
  
  public void deletePhrase(int paramInt)
  {
    if ((paramInt >= 0) && (paramInt < mSmsPhrases.size()))
    {
      mSmsPhrases.remove(paramInt);
      saveSmsPhraseDate();
    }
  }
  
  public int getCount()
  {
    return mSmsPhrases.size();
  }
  
  public String getPhrase(int paramInt)
  {
    if ((paramInt < 0) || (paramInt >= mSmsPhrases.size())) {
      return null;
    }
    return (String)mSmsPhrases.get(paramInt);
  }
  
  public void setPhrase(int paramInt, String paramString)
  {
    if ((paramInt >= 0) && (paramInt < mSmsPhrases.size()))
    {
      mSmsPhrases.set(paramInt, paramString);
      saveSmsPhraseDate();
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.PhraseListAdapter.SmsPhraseData
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */