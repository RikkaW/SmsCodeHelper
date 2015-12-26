package com.android.mms.understand;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.SpannableString;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.android.mms.ui.ConversationBase;
import com.android.mms.ui.TextSizeAdjustSpan;
import com.android.mms.util.MSimUtils;
import com.android.mms.util.MiStatSdkHelper;
import com.xiaomi.smsunderstand.AttrInfo;
import com.xiaomi.smsunderstand.CardIndex;
import com.xiaomi.smsunderstand.EntityType;
import com.xiaomi.smsunderstand.OntologyResults;
import com.xiaomi.smsunderstand.SMSUnderstand;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import miui.os.Build;

public class UnderstandFactory
{
  private static final int ROM_VERSION = 1;
  private static Object sInitLockObj;
  private static boolean sInitialized = false;
  
  static
  {
    sInitLockObj = new Object();
    if (Build.IS_STABLE_VERSION)
    {
      ROM_VERSION = 4;
      return;
    }
    if (Build.IS_DEVELOPMENT_VERSION)
    {
      ROM_VERSION = 2;
      return;
    }
  }
  
  private static String convertValue(String paramString, UnderstandMessage paramUnderstandMessage, int paramInt)
  {
    Object localObject = paramString;
    int i;
    if (!TextUtils.isEmpty(paramString))
    {
      localObject = paramString;
      if (paramUnderstandMessage != null)
      {
        localObject = paramString;
        if (paramString.length() > 2)
        {
          localObject = paramString;
          if (paramString.startsWith("#"))
          {
            localObject = paramString;
            if (paramString.endsWith("#"))
            {
              i = Integer.parseInt(paramString.substring(1, paramString.length() - 1));
              if (i != -1) {
                break label96;
              }
              localObject = paramInt + "";
            }
          }
        }
      }
    }
    return (String)localObject;
    label96:
    paramUnderstandMessage = mItems.iterator();
    do
    {
      localObject = paramString;
      if (!paramUnderstandMessage.hasNext()) {
        break;
      }
      localObject = (UnderstandMessage.Item)paramUnderstandMessage.next();
    } while (mType != i);
    return mValue;
  }
  
  public static void copyResults(UnderstandMessage paramUnderstandMessage, OntologyResults paramOntologyResults)
  {
    if ((paramOntologyResults != null) && (paramUnderstandMessage != null))
    {
      mActionID = paramOntologyResults.getActionID();
      mFrameName = paramOntologyResults.getOntologyName();
      paramOntologyResults = paramOntologyResults.getAttrInfos();
      mItems = new ArrayList();
      paramOntologyResults = paramOntologyResults.iterator();
      while (paramOntologyResults.hasNext())
      {
        AttrInfo localAttrInfo = (AttrInfo)paramOntologyResults.next();
        if (localAttrInfo != null)
        {
          UnderstandMessage.Item localItem = new UnderstandMessage.Item();
          mValue = localAttrInfo.getValue();
          mHas = localAttrInfo.getHas();
          mEndPosition = localAttrInfo.getEndPosition();
          mStartPosition = localAttrInfo.getStartPosition();
          mType = localAttrInfo.getType();
          mItems.add(localItem);
        }
      }
    }
  }
  
  private static Intent createIntent(UnderstandAction paramUnderstandAction, int paramInt)
  {
    if ((!TextUtils.isEmpty(mAction)) && (!TextUtils.isEmpty(mPackageName)))
    {
      Intent localIntent = new Intent(mAction);
      if (TextUtils.isEmpty(mClassName)) {
        localIntent.setPackage(mPackageName);
      }
      for (;;)
      {
        if (mFlag != 0) {
          localIntent.addFlags(mFlag);
        }
        String str = MSimUtils.SLOT_ID;
        if (!TextUtils.isEmpty(mSlotIdName)) {
          str = mSlotIdName;
        }
        localIntent.putExtra(str, paramInt);
        return localIntent;
        localIntent.setClassName(mPackageName, mClassName);
      }
    }
    return null;
  }
  
  private static Intent createMainIntent(Context paramContext, String paramString1, int paramInt, String paramString2)
  {
    if (!TextUtils.isEmpty(paramString1))
    {
      paramString1 = paramContext.getPackageManager().getLaunchIntentForPackage(paramString1);
      if (paramString1 != null)
      {
        paramContext = MSimUtils.SLOT_ID;
        if (!TextUtils.isEmpty(paramString2)) {
          paramContext = paramString2;
        }
        paramString1.putExtra(paramContext, paramInt);
      }
      return paramString1;
    }
    return null;
  }
  
  public static void doAction(Context paramContext, List<UnderstandAction> paramList, UnderstandMessage paramUnderstandMessage, int paramInt)
  {
    int i;
    if (paramList != null)
    {
      i = 0;
      if (paramList.size() > 5) {}
      for (int j = 5;; j = paramList.size())
      {
        int k = 0;
        for (;;)
        {
          if ((k >= j) || (i != 0)) {
            break label620;
          }
          localUnderstandAction = (UnderstandAction)paramList.get(k);
          if (localUnderstandAction != null) {
            break;
          }
          k += 1;
        }
      }
      switch (mCallType)
      {
      default: 
        Log.e("UnderstandFactory", " unknown type id configured: " + mCallType);
      }
    }
    label477:
    label620:
    while (i != 0) {
      for (;;)
      {
        UnderstandAction localUnderstandAction;
        return;
        Intent localIntent1 = prepareIntent(localUnderstandAction, paramUnderstandMessage, paramInt);
        if ((localIntent1 != null) && (paramContext.getPackageManager().resolveActivity(localIntent1, 0) != null))
        {
          try
          {
            paramContext.startActivity(localIntent1);
            i = 1;
          }
          catch (Exception localException1)
          {
            localException1.printStackTrace();
          }
        }
        else
        {
          Intent localIntent2 = prepareMainIntent(paramContext, localUnderstandAction, paramUnderstandMessage, paramInt);
          if (localIntent2 != null)
          {
            try
            {
              paramContext.startActivity(localIntent2);
              i = 1;
            }
            catch (Exception localException2)
            {
              localException2.printStackTrace();
            }
          }
          else if (!TextUtils.isEmpty(mFallback))
          {
            Log.v("UnderstandFactory", " Trying to fallback");
            Uri localUri = Uri.parse(findUriValue(mFallback, paramUnderstandMessage));
            try
            {
              paramContext.startActivity(new Intent("android.intent.action.VIEW", localUri));
              i = 1;
            }
            catch (Exception localException3)
            {
              localException3.printStackTrace();
            }
          }
          else
          {
            Log.w("UnderstandFactory", " no action!");
            i = 0;
            continue;
            Intent localIntent3 = prepareIntent(localUnderstandAction, paramUnderstandMessage, paramInt);
            if (localIntent3 != null)
            {
              try
              {
                paramContext.startService(localIntent3);
                i = 1;
              }
              catch (Exception localException4)
              {
                localException4.printStackTrace();
              }
            }
            else
            {
              i = 0;
              continue;
              Object localObject = mItems.get(0)).mValue;
              ((ClipboardManager)paramContext.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText(null, (CharSequence)localObject));
              Toast.makeText(paramContext, paramContext.getString(2131361821), 0).show();
              i = 1;
              continue;
              if ((paramContext instanceof ConversationBase))
              {
                ((ConversationBase)paramContext).getTextEditor().setText(mItems.get(0)).mValue);
                if (mItems.get(0)).mValue != null) {
                  break label477;
                }
              }
              for (i = 0;; i = mItems.get(0)).mValue.length())
              {
                ((ConversationBase)paramContext).getTextEditor().setSelection(i);
                i = 1;
                break;
              }
              String str = "";
              localObject = str;
              if (mUriBase != null)
              {
                localObject = str;
                if (mUriBase.length() > 0) {
                  localObject = findUriValue(mUriBase, paramUnderstandMessage);
                }
              }
              if (TextUtils.isEmpty((CharSequence)localObject))
              {
                Log.e("UnderstandFactory", " empty uri got");
                return;
              }
              localObject = new Intent("android.intent.action.VIEW", Uri.parse((String)localObject));
              ((Intent)localObject).putExtra("com.android.browser.application_id", paramContext.getPackageName());
              ((Intent)localObject).setFlags(524288);
              try
              {
                paramContext.startActivity((Intent)localObject);
                i = 1;
              }
              catch (Exception localException5)
              {
                localException5.printStackTrace();
              }
            }
          }
        }
      }
    }
    Toast.makeText(paramContext, paramContext.getString(2131362136), 0).show();
  }
  
  public static String findUriValue(String paramString, UnderstandMessage paramUnderstandMessage)
  {
    if ((!TextUtils.isEmpty(paramString)) && (paramUnderstandMessage != null) && (paramString.length() > 2) && (paramString.startsWith("#")) && (paramString.endsWith("#")))
    {
      int i = Integer.parseInt(paramString.substring(1, paramString.length() - 1));
      paramUnderstandMessage = mItems.iterator();
      while (paramUnderstandMessage.hasNext())
      {
        UnderstandMessage.Item localItem = (UnderstandMessage.Item)paramUnderstandMessage.next();
        if (mType == i) {
          return mValue;
        }
      }
    }
    Log.w("UnderstandFactory", "Can't find matched value, fallback to original string.");
    return paramString;
  }
  
  public static void freeAllResourcesForResident()
  {
    if (!isInitialized())
    {
      Log.e("UnderstandFactory", "not init understand");
      return;
    }
    try
    {
      SMSUnderstand.freeAllResource();
      setInitialized(false);
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  public static void freeResourceForResident(String paramString1, String paramString2)
  {
    if (!isInitialized()) {
      Log.e("UnderstandFactory", "not init understand");
    }
    while (TextUtils.isEmpty(paramString1)) {
      return;
    }
    try
    {
      SMSUnderstand.freeResource(paramString1, paramString2);
      return;
    }
    catch (Exception paramString1)
    {
      paramString1.printStackTrace();
    }
  }
  
  public static List<String> getButtonActions(int paramInt1, int paramInt2)
  {
    localArrayList2 = null;
    ArrayList localArrayList1;
    if (!isInitialized())
    {
      Log.w("UnderstandFactory", "not init understand");
      localArrayList1 = localArrayList2;
    }
    int i;
    do
    {
      do
      {
        do
        {
          return localArrayList1;
          localArrayList1 = localArrayList2;
        } while (paramInt1 < 0);
        i = 0;
        try
        {
          j = SMSUnderstand.getActionCount(paramInt1, paramInt2);
          i = j;
        }
        catch (Exception localException1)
        {
          try
          {
            for (;;)
            {
              int j;
              localArrayList2.add(SMSUnderstand.getBtnAction(paramInt1, paramInt2, j));
              j += 1;
            }
            localException1 = localException1;
            localException1.printStackTrace();
          }
          catch (Exception localException2)
          {
            for (;;)
            {
              localException2.printStackTrace();
            }
          }
        }
        localArrayList1 = localArrayList2;
      } while (i <= 0);
      localArrayList2 = new ArrayList();
      j = 0;
      localArrayList1 = localArrayList2;
    } while (j >= i);
  }
  
  public static String getButtonName(int paramInt1, int paramInt2)
  {
    if (!isInitialized()) {
      Log.w("UnderstandFactory", "not init understand");
    }
    while (paramInt1 < 0) {
      return "";
    }
    try
    {
      String str = SMSUnderstand.getBtnTitle(paramInt1, paramInt2);
      return str;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return "";
  }
  
  public static int getButtonNumber(int paramInt)
  {
    if (!isInitialized()) {
      Log.w("UnderstandFactory", "not init understand");
    }
    while (paramInt < 0) {
      return 0;
    }
    try
    {
      paramInt = SMSUnderstand.getBtnNumber(paramInt);
      return paramInt;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return 0;
  }
  
  public static SpannableString getTextWithUnderstand(Context paramContext, String paramString1, List<UnderstandMessage> paramList, int paramInt1, int paramInt2, String paramString2)
  {
    int j = 0;
    Object localObject = (UnderstandMessage.Item)get0mItems.get(0);
    int i = 0;
    String str = paramString1;
    if (localObject != null)
    {
      int k = mStartPosition;
      int m = mEndPosition;
      i = m - paramInt2;
      if ((m <= paramInt2) || (m <= k) || (i >= k)) {
        break label263;
      }
    }
    for (str = paramString2 + paramString1.substring(paramString2.length() + i, paramString1.length());; str = paramString1)
    {
      paramString1 = new SpannableString(str);
      paramList = paramList.iterator();
      paramInt2 = j;
      for (;;)
      {
        if (!paramList.hasNext()) {
          break label272;
        }
        localObject = nextmItems.iterator();
        if (((Iterator)localObject).hasNext())
        {
          paramString2 = (UnderstandMessage.Item)((Iterator)localObject).next();
          if ((mHas <= 0) || (mEndPosition <= mStartPosition) || (mEndPosition >= str.length())) {
            break;
          }
          localObject = new TextSizeAdjustSpan(paramContext, paramInt1);
          ((TextSizeAdjustSpan)localObject).needUnderline();
          paramString1.setSpan(localObject, mStartPosition - i, mEndPosition - i, 33);
          paramInt2 = 1;
        }
      }
      label263:
      i = 0;
    }
    label272:
    if (paramInt2 != 0) {
      return paramString1;
    }
    return null;
  }
  
  public static List<UnderstandMessage> getUnderstandMessageList(String paramString1, String paramString2, String paramString3, long paramLong)
  {
    Object localObject2 = null;
    Object localObject1;
    if (!isInitialized())
    {
      Log.w("UnderstandFactory", "not init understand");
      localObject1 = localObject2;
    }
    for (;;)
    {
      return (List<UnderstandMessage>)localObject1;
      localObject1 = localObject2;
      if (!TextUtils.isEmpty(paramString1))
      {
        localObject1 = localObject2;
        if (!TextUtils.isEmpty(paramString3))
        {
          paramString1 = new SMSUnderstand(paramString1, paramString2);
          if (paramLong > 0L) {}
          try
          {
            for (paramString1 = paramString1.understand(paramString3, paramLong);; paramString1 = paramString1.understand(paramString3))
            {
              if ((paramString1 == null) || (paramString1.size() <= 0)) {
                break label194;
              }
              Log.v("UnderstandFactory", " OntologyResults size are " + paramString1.size());
              paramString2 = new ArrayList();
              paramString1 = paramString1.iterator();
              for (;;)
              {
                localObject1 = paramString2;
                if (!paramString1.hasNext()) {
                  break;
                }
                paramString3 = (OntologyResults)paramString1.next();
                localObject1 = new UnderstandMessage();
                copyResults((UnderstandMessage)localObject1, paramString3);
                paramString2.add(localObject1);
              }
            }
            Log.v("UnderstandFactory", " no OntologyResults");
          }
          catch (Exception paramString1)
          {
            paramString1.printStackTrace();
            return null;
          }
        }
      }
    }
    label194:
    return null;
  }
  
  public static List<UnderstandMessage> getUnderstandMessageList(String paramString1, String paramString2, String paramString3, long paramLong, int paramInt)
  {
    if (!isInitialized())
    {
      Log.w("UnderstandFactory", "not init understand");
      paramString1 = null;
    }
    for (;;)
    {
      return paramString1;
      if ((!TextUtils.isEmpty(paramString1)) && (!TextUtils.isEmpty(paramString3)))
      {
        Object localObject = new HashSet();
        if ((paramInt & 0x1) != 0) {
          ((Set)localObject).add(EntityType.TIME);
        }
        if ((paramInt & 0x2) != 0) {
          ((Set)localObject).add(EntityType.VERIFICATIONCODE);
        }
        paramString1 = new SMSUnderstand(paramString1, paramString2, (Set)localObject);
        if (paramLong > 0L) {}
        try
        {
          for (paramString1 = paramString1.understand(paramString3, paramLong);; paramString1 = paramString1.understand(paramString3))
          {
            if ((paramString1 == null) || (paramString1.size() <= 0)) {
              break label237;
            }
            Log.v("UnderstandFactory", "mask: " + paramInt + " OntologyResults size are " + paramString1.size());
            paramString2 = new ArrayList();
            paramString3 = paramString1.iterator();
            for (;;)
            {
              paramString1 = paramString2;
              if (!paramString3.hasNext()) {
                break;
              }
              paramString1 = (OntologyResults)paramString3.next();
              localObject = new UnderstandMessage();
              copyResults((UnderstandMessage)localObject, paramString1);
              paramString2.add(localObject);
            }
          }
          return null;
        }
        catch (Exception paramString1)
        {
          paramString1.printStackTrace();
          return null;
        }
      }
    }
  }
  
  /* Error */
  public static long getVersion()
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: new 475	java/io/File
    //   6: dup
    //   7: invokestatic 480	com/android/mms/understand/TemplateUpdate:getVersionFile	()Ljava/lang/String;
    //   10: invokespecial 481	java/io/File:<init>	(Ljava/lang/String;)V
    //   13: astore 5
    //   15: aconst_null
    //   16: astore 4
    //   18: aconst_null
    //   19: astore 6
    //   21: new 483	java/io/BufferedReader
    //   24: dup
    //   25: new 485	java/io/InputStreamReader
    //   28: dup
    //   29: new 487	java/io/FileInputStream
    //   32: dup
    //   33: aload 5
    //   35: invokespecial 490	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   38: invokespecial 493	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   41: invokespecial 496	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   44: astore 5
    //   46: aload 5
    //   48: invokevirtual 499	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   51: astore 4
    //   53: aload 4
    //   55: invokestatic 38	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   58: ifne +52 -> 110
    //   61: aload 4
    //   63: invokestatic 505	java/lang/Long:parseLong	(Ljava/lang/String;)J
    //   66: lstore_0
    //   67: lload_0
    //   68: lstore_2
    //   69: aload 5
    //   71: ifnull +10 -> 81
    //   74: aload 5
    //   76: invokevirtual 508	java/io/BufferedReader:close	()V
    //   79: lload_0
    //   80: lstore_2
    //   81: ldc 2
    //   83: monitorexit
    //   84: lload_2
    //   85: lreturn
    //   86: astore 4
    //   88: aload 4
    //   90: invokevirtual 509	java/io/IOException:printStackTrace	()V
    //   93: lload_0
    //   94: lstore_2
    //   95: goto -14 -> 81
    //   98: astore 4
    //   100: aload 4
    //   102: invokevirtual 262	java/lang/Exception:printStackTrace	()V
    //   105: lconst_0
    //   106: lstore_2
    //   107: goto -26 -> 81
    //   110: aload 5
    //   112: ifnull +119 -> 231
    //   115: aload 5
    //   117: invokevirtual 508	java/io/BufferedReader:close	()V
    //   120: goto -15 -> 105
    //   123: astore 4
    //   125: aload 4
    //   127: invokevirtual 509	java/io/IOException:printStackTrace	()V
    //   130: goto -25 -> 105
    //   133: astore 4
    //   135: aload 6
    //   137: astore 5
    //   139: aload 4
    //   141: astore 6
    //   143: aload 5
    //   145: astore 4
    //   147: aload 6
    //   149: invokevirtual 509	java/io/IOException:printStackTrace	()V
    //   152: aload 5
    //   154: ifnull -49 -> 105
    //   157: aload 5
    //   159: invokevirtual 508	java/io/BufferedReader:close	()V
    //   162: goto -57 -> 105
    //   165: astore 4
    //   167: aload 4
    //   169: invokevirtual 509	java/io/IOException:printStackTrace	()V
    //   172: goto -67 -> 105
    //   175: astore 4
    //   177: goto -77 -> 100
    //   180: astore 5
    //   182: aload 4
    //   184: ifnull +8 -> 192
    //   187: aload 4
    //   189: invokevirtual 508	java/io/BufferedReader:close	()V
    //   192: aload 5
    //   194: athrow
    //   195: astore 4
    //   197: ldc 2
    //   199: monitorexit
    //   200: aload 4
    //   202: athrow
    //   203: astore 4
    //   205: aload 4
    //   207: invokevirtual 509	java/io/IOException:printStackTrace	()V
    //   210: goto -18 -> 192
    //   213: astore 6
    //   215: aload 5
    //   217: astore 4
    //   219: aload 6
    //   221: astore 5
    //   223: goto -41 -> 182
    //   226: astore 6
    //   228: goto -85 -> 143
    //   231: goto -126 -> 105
    // Local variable table:
    //   start	length	slot	name	signature
    //   66	28	0	l1	long
    //   68	39	2	l2	long
    //   16	46	4	str	String
    //   86	3	4	localIOException1	java.io.IOException
    //   98	3	4	localException1	Exception
    //   123	3	4	localIOException2	java.io.IOException
    //   133	7	4	localIOException3	java.io.IOException
    //   145	1	4	localObject1	Object
    //   165	3	4	localIOException4	java.io.IOException
    //   175	13	4	localException2	Exception
    //   195	6	4	localObject2	Object
    //   203	3	4	localIOException5	java.io.IOException
    //   217	1	4	localObject3	Object
    //   13	145	5	localObject4	Object
    //   180	36	5	localObject5	Object
    //   221	1	5	localObject6	Object
    //   19	129	6	localIOException6	java.io.IOException
    //   213	7	6	localObject7	Object
    //   226	1	6	localIOException7	java.io.IOException
    // Exception table:
    //   from	to	target	type
    //   74	79	86	java/io/IOException
    //   74	79	98	java/lang/Exception
    //   88	93	98	java/lang/Exception
    //   115	120	98	java/lang/Exception
    //   125	130	98	java/lang/Exception
    //   115	120	123	java/io/IOException
    //   21	46	133	java/io/IOException
    //   157	162	165	java/io/IOException
    //   157	162	175	java/lang/Exception
    //   167	172	175	java/lang/Exception
    //   187	192	175	java/lang/Exception
    //   192	195	175	java/lang/Exception
    //   205	210	175	java/lang/Exception
    //   21	46	180	finally
    //   147	152	180	finally
    //   3	15	195	finally
    //   74	79	195	finally
    //   88	93	195	finally
    //   100	105	195	finally
    //   115	120	195	finally
    //   125	130	195	finally
    //   157	162	195	finally
    //   167	172	195	finally
    //   187	192	195	finally
    //   192	195	195	finally
    //   205	210	195	finally
    //   187	192	203	java/io/IOException
    //   46	67	213	finally
    //   46	67	226	java/io/IOException
  }
  
  public static void initUnderstand()
  {
    SMSUnderstand.setPlatform(ROM_VERSION);
    try
    {
      if (!SMSUnderstand.initial("/data/data/com.android.mms/app_understand/smsUnderstand.config"))
      {
        Log.e("UnderstandFactory", "init understand failed");
        setInitialized(false);
        return;
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      return;
    }
    Log.v("UnderstandFactory", " understand initial done!");
    setInitialized(true);
  }
  
  public static void initUnderstandFiles()
  {
    for (;;)
    {
      long l;
      try
      {
        Log.v("UnderstandFactory", "init understand files");
        File localFile1 = new File(TemplateUpdate.getVersionFile());
        i = 0;
        if (localFile1.exists())
        {
          l = getVersion();
          if (Build.IS_INTERNATIONAL_BUILD) {
            break label210;
          }
          Log.d("UnderstandFactory", "current using version is " + l + ", latest version is: " + 17L);
          if (l >= 17L)
          {
            MiStatSdkHelper.recordStringPropertyEvent("understand_category", "template_version", Long.toString(l));
            return;
          }
          MiStatSdkHelper.recordStringPropertyEvent("understand_category", "template_version", Long.toString(l));
          j = 1;
          localFile1 = new File(TemplateUpdate.getUpdatedFile());
          i = j;
          if (localFile1.exists())
          {
            localFile1.delete();
            i = j;
          }
        }
        initUnderstandFiles("/data/data/com.android.mms/app_understand");
        if (i != 0) {
          UnderstandLoader.update();
        }
        if (new File(TemplateUpdate.getVersionFile()).exists()) {
          break;
        }
        updateVersionFile(0L);
        return;
      }
      catch (Exception localException)
      {
        Log.e("UnderstandFactory", " Copy files failed! " + localException);
        return;
      }
      label210:
      Log.d("UnderstandFactory", "current using version is " + l + ", latest version is: " + 1L);
      if (l == 1L) {
        break;
      }
      int j = 1;
      File localFile2 = new File(TemplateUpdate.getUpdatedFile());
      int i = j;
      if (localFile2.exists())
      {
        localFile2.delete();
        i = j;
      }
    }
  }
  
  /* Error */
  private static void initUnderstandFiles(String paramString)
    throws java.io.IOException
  {
    // Byte code:
    //   0: new 475	java/io/File
    //   3: dup
    //   4: aload_0
    //   5: invokespecial 481	java/io/File:<init>	(Ljava/lang/String;)V
    //   8: astore 6
    //   10: aload 6
    //   12: invokevirtual 585	java/io/File:mkdirs	()Z
    //   15: pop
    //   16: aconst_null
    //   17: astore_3
    //   18: aconst_null
    //   19: astore 4
    //   21: aload_3
    //   22: astore_2
    //   23: invokestatic 591	miui/os/Environment:getMiuiDataDirectory	()Ljava/io/File;
    //   26: astore 5
    //   28: aload_3
    //   29: astore_2
    //   30: new 475	java/io/File
    //   33: dup
    //   34: aload 5
    //   36: ldc_w 593
    //   39: invokespecial 596	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   42: astore 7
    //   44: aload_3
    //   45: astore_2
    //   46: aload 7
    //   48: invokevirtual 528	java/io/File:exists	()Z
    //   51: ifne +51 -> 102
    //   54: aload_3
    //   55: astore_2
    //   56: ldc -17
    //   58: new 65	java/lang/StringBuilder
    //   61: dup
    //   62: invokespecial 66	java/lang/StringBuilder:<init>	()V
    //   65: ldc_w 598
    //   68: invokevirtual 75	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   71: aload 7
    //   73: invokevirtual 582	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   76: invokevirtual 79	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   79: invokestatic 247	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   82: pop
    //   83: iconst_0
    //   84: ifeq +11 -> 95
    //   87: new 600	java/lang/NullPointerException
    //   90: dup
    //   91: invokespecial 601	java/lang/NullPointerException:<init>	()V
    //   94: athrow
    //   95: return
    //   96: astore_0
    //   97: aload_0
    //   98: invokevirtual 509	java/io/IOException:printStackTrace	()V
    //   101: return
    //   102: aload_3
    //   103: astore_2
    //   104: new 603	java/util/zip/ZipInputStream
    //   107: dup
    //   108: new 605	java/io/BufferedInputStream
    //   111: dup
    //   112: new 487	java/io/FileInputStream
    //   115: dup
    //   116: aload 7
    //   118: invokespecial 490	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   121: invokespecial 606	java/io/BufferedInputStream:<init>	(Ljava/io/InputStream;)V
    //   124: invokespecial 607	java/util/zip/ZipInputStream:<init>	(Ljava/io/InputStream;)V
    //   127: astore_3
    //   128: sipush 4096
    //   131: newarray <illegal type>
    //   133: astore 7
    //   135: ldc -17
    //   137: new 65	java/lang/StringBuilder
    //   140: dup
    //   141: invokespecial 66	java/lang/StringBuilder:<init>	()V
    //   144: ldc_w 609
    //   147: invokevirtual 75	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   150: aload 5
    //   152: invokevirtual 610	java/io/File:toString	()Ljava/lang/String;
    //   155: invokevirtual 75	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   158: ldc_w 612
    //   161: invokevirtual 75	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   164: aload_0
    //   165: invokevirtual 75	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   168: invokevirtual 79	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   171: invokestatic 274	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   174: pop
    //   175: aload_3
    //   176: invokevirtual 616	java/util/zip/ZipInputStream:getNextEntry	()Ljava/util/zip/ZipEntry;
    //   179: astore_0
    //   180: aload_0
    //   181: ifnull +205 -> 386
    //   184: new 475	java/io/File
    //   187: dup
    //   188: aload 6
    //   190: aload_0
    //   191: invokevirtual 621	java/util/zip/ZipEntry:getName	()Ljava/lang/String;
    //   194: invokespecial 596	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   197: astore_2
    //   198: ldc -17
    //   200: new 65	java/lang/StringBuilder
    //   203: dup
    //   204: invokespecial 66	java/lang/StringBuilder:<init>	()V
    //   207: ldc_w 623
    //   210: invokevirtual 75	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   213: aload_2
    //   214: invokevirtual 582	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   217: invokevirtual 79	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   220: invokestatic 545	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   223: pop
    //   224: aload_0
    //   225: invokevirtual 626	java/util/zip/ZipEntry:isDirectory	()Z
    //   228: ifeq +37 -> 265
    //   231: aload_2
    //   232: invokevirtual 585	java/io/File:mkdirs	()Z
    //   235: pop
    //   236: goto -61 -> 175
    //   239: astore_2
    //   240: aload_3
    //   241: astore_0
    //   242: aload_2
    //   243: astore_3
    //   244: aload_0
    //   245: astore_2
    //   246: aload_3
    //   247: invokevirtual 509	java/io/IOException:printStackTrace	()V
    //   250: aload_0
    //   251: ifnull -156 -> 95
    //   254: aload_0
    //   255: invokevirtual 627	java/util/zip/ZipInputStream:close	()V
    //   258: return
    //   259: astore_0
    //   260: aload_0
    //   261: invokevirtual 509	java/io/IOException:printStackTrace	()V
    //   264: return
    //   265: aload_2
    //   266: invokevirtual 630	java/io/File:createNewFile	()Z
    //   269: pop
    //   270: aconst_null
    //   271: astore_0
    //   272: aconst_null
    //   273: astore 5
    //   275: new 632	java/io/FileOutputStream
    //   278: dup
    //   279: aload_2
    //   280: invokespecial 633	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   283: astore_2
    //   284: aload_3
    //   285: aload 7
    //   287: invokevirtual 637	java/util/zip/ZipInputStream:read	([B)I
    //   290: istore_1
    //   291: iload_1
    //   292: iconst_m1
    //   293: if_icmpeq +55 -> 348
    //   296: aload_2
    //   297: aload 7
    //   299: iconst_0
    //   300: iload_1
    //   301: invokevirtual 641	java/io/FileOutputStream:write	([BII)V
    //   304: goto -20 -> 284
    //   307: astore 4
    //   309: aload_2
    //   310: astore_0
    //   311: aload 4
    //   313: invokevirtual 509	java/io/IOException:printStackTrace	()V
    //   316: aload_2
    //   317: ifnull -142 -> 175
    //   320: aload_2
    //   321: invokevirtual 642	java/io/FileOutputStream:close	()V
    //   324: goto -149 -> 175
    //   327: astore_0
    //   328: aload_0
    //   329: invokevirtual 509	java/io/IOException:printStackTrace	()V
    //   332: goto -157 -> 175
    //   335: astore_0
    //   336: aload_3
    //   337: astore_2
    //   338: aload_2
    //   339: ifnull +7 -> 346
    //   342: aload_2
    //   343: invokevirtual 627	java/util/zip/ZipInputStream:close	()V
    //   346: aload_0
    //   347: athrow
    //   348: aload_2
    //   349: ifnull +90 -> 439
    //   352: aload_2
    //   353: invokevirtual 642	java/io/FileOutputStream:close	()V
    //   356: goto -181 -> 175
    //   359: astore_0
    //   360: aload_0
    //   361: invokevirtual 509	java/io/IOException:printStackTrace	()V
    //   364: goto -189 -> 175
    //   367: astore_2
    //   368: aload_0
    //   369: ifnull +7 -> 376
    //   372: aload_0
    //   373: invokevirtual 642	java/io/FileOutputStream:close	()V
    //   376: aload_2
    //   377: athrow
    //   378: astore_0
    //   379: aload_0
    //   380: invokevirtual 509	java/io/IOException:printStackTrace	()V
    //   383: goto -7 -> 376
    //   386: aload_3
    //   387: ifnull +51 -> 438
    //   390: aload_3
    //   391: invokevirtual 627	java/util/zip/ZipInputStream:close	()V
    //   394: return
    //   395: astore_0
    //   396: aload_0
    //   397: invokevirtual 509	java/io/IOException:printStackTrace	()V
    //   400: return
    //   401: astore_2
    //   402: aload_2
    //   403: invokevirtual 509	java/io/IOException:printStackTrace	()V
    //   406: goto -60 -> 346
    //   409: astore_0
    //   410: goto -72 -> 338
    //   413: astore_3
    //   414: aload 4
    //   416: astore_0
    //   417: goto -173 -> 244
    //   420: astore 4
    //   422: aload_2
    //   423: astore_0
    //   424: aload 4
    //   426: astore_2
    //   427: goto -59 -> 368
    //   430: astore 4
    //   432: aload 5
    //   434: astore_2
    //   435: goto -126 -> 309
    //   438: return
    //   439: goto -264 -> 175
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	442	0	paramString	String
    //   290	11	1	i	int
    //   22	210	2	localObject1	Object
    //   239	4	2	localIOException1	java.io.IOException
    //   245	108	2	localObject2	Object
    //   367	10	2	localObject3	Object
    //   401	22	2	localIOException2	java.io.IOException
    //   426	9	2	localObject4	Object
    //   17	374	3	localObject5	Object
    //   413	1	3	localIOException3	java.io.IOException
    //   19	1	4	localObject6	Object
    //   307	108	4	localIOException4	java.io.IOException
    //   420	5	4	localObject7	Object
    //   430	1	4	localIOException5	java.io.IOException
    //   26	407	5	localFile1	File
    //   8	181	6	localFile2	File
    //   42	256	7	localObject8	Object
    // Exception table:
    //   from	to	target	type
    //   87	95	96	java/io/IOException
    //   128	175	239	java/io/IOException
    //   175	180	239	java/io/IOException
    //   184	236	239	java/io/IOException
    //   265	270	239	java/io/IOException
    //   328	332	239	java/io/IOException
    //   360	364	239	java/io/IOException
    //   376	378	239	java/io/IOException
    //   379	383	239	java/io/IOException
    //   254	258	259	java/io/IOException
    //   284	291	307	java/io/IOException
    //   296	304	307	java/io/IOException
    //   320	324	327	java/io/IOException
    //   128	175	335	finally
    //   175	180	335	finally
    //   184	236	335	finally
    //   265	270	335	finally
    //   320	324	335	finally
    //   328	332	335	finally
    //   352	356	335	finally
    //   360	364	335	finally
    //   372	376	335	finally
    //   376	378	335	finally
    //   379	383	335	finally
    //   352	356	359	java/io/IOException
    //   275	284	367	finally
    //   311	316	367	finally
    //   372	376	378	java/io/IOException
    //   390	394	395	java/io/IOException
    //   342	346	401	java/io/IOException
    //   23	28	409	finally
    //   30	44	409	finally
    //   46	54	409	finally
    //   56	83	409	finally
    //   104	128	409	finally
    //   246	250	409	finally
    //   23	28	413	java/io/IOException
    //   30	44	413	java/io/IOException
    //   46	54	413	java/io/IOException
    //   56	83	413	java/io/IOException
    //   104	128	413	java/io/IOException
    //   284	291	420	finally
    //   296	304	420	finally
    //   275	284	430	java/io/IOException
  }
  
  private static boolean isInitialized()
  {
    synchronized (sInitLockObj)
    {
      boolean bool = sInitialized;
      return bool;
    }
  }
  
  public static boolean loadResourceForResident(String paramString1, String paramString2)
  {
    if (!isInitialized()) {
      Log.w("UnderstandFactory", "not init understand");
    }
    while (TextUtils.isEmpty(paramString1)) {
      return false;
    }
    try
    {
      boolean bool = SMSUnderstand.loadResourceForResident(paramString1, paramString2);
      return bool;
    }
    catch (Exception paramString1)
    {
      paramString1.printStackTrace();
    }
    return false;
  }
  
  public static UnderstandAction parseActionString(String paramString)
  {
    if (!TextUtils.isEmpty(paramString))
    {
      UnderstandAction localUnderstandAction = new UnderstandAction();
      if (localUnderstandAction.parseActionString(paramString)) {
        return localUnderstandAction;
      }
    }
    return null;
  }
  
  private static void prepareDataIntent(Intent paramIntent, UnderstandAction paramUnderstandAction, UnderstandMessage paramUnderstandMessage)
  {
    if ((mUriBase != null) && (mUriBase.length() > 0))
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(mUriBase);
      Object localObject = prepareUrlParam(paramUnderstandAction, paramUnderstandMessage);
      int j = 0;
      if (!TextUtils.isEmpty((CharSequence)localObject))
      {
        localStringBuilder.append("?").append((String)localObject);
        j = 1;
      }
      if (mParamCount > 0)
      {
        localObject = mParams;
        if (localObject != null)
        {
          int i;
          int k;
          label99:
          UnderstandAction.Params localParams;
          int m;
          if (j == 0)
          {
            i = 1;
            k = 0;
            if (k >= mParamCount) {
              break label235;
            }
            localParams = (UnderstandAction.Params)((List)localObject).get(k);
            if ((j == 0) || (!"url".equals(mName))) {
              break label161;
            }
            m = i;
          }
          label161:
          String str;
          do
          {
            k += 1;
            i = m;
            break label99;
            i = 0;
            break;
            str = findUriValue(mValue, paramUnderstandMessage);
            m = i;
          } while (TextUtils.isEmpty(str));
          if (i != 0)
          {
            localStringBuilder.append('?');
            i = 0;
          }
          for (;;)
          {
            localStringBuilder.append(mName).append('=').append(str);
            m = i;
            break;
            localStringBuilder.append('&');
          }
        }
      }
      label235:
      paramIntent.setData(Uri.parse(localStringBuilder.toString()));
    }
  }
  
  private static Intent prepareIntent(UnderstandAction paramUnderstandAction, UnderstandMessage paramUnderstandMessage, int paramInt)
  {
    Object localObject;
    if (paramUnderstandAction != null)
    {
      int j = mSlotId;
      int i = j;
      if (j < 0) {
        i = paramInt;
      }
      Intent localIntent = createIntent(paramUnderstandAction, i);
      localObject = localIntent;
      if (localIntent != null)
      {
        prepareDataIntent(localIntent, paramUnderstandAction, paramUnderstandMessage);
        localObject = localIntent;
        if (mCount > 0)
        {
          localObject = localIntent;
          if (mLists != null)
          {
            localObject = localIntent;
            if (mLists.size() > 0)
            {
              paramUnderstandAction = mLists.iterator();
              for (;;)
              {
                localObject = localIntent;
                if (!paramUnderstandAction.hasNext()) {
                  break;
                }
                localObject = (UnderstandAction.Params)paramUnderstandAction.next();
                localIntent.putExtra(mName, convertValue(mValue, paramUnderstandMessage, paramInt));
              }
            }
          }
        }
      }
    }
    else
    {
      localObject = null;
    }
    return (Intent)localObject;
  }
  
  private static Intent prepareMainIntent(Context paramContext, UnderstandAction paramUnderstandAction, UnderstandMessage paramUnderstandMessage, int paramInt)
  {
    if ((paramUnderstandAction != null) && (mEnterMain))
    {
      int j = mSlotId;
      int i = j;
      if (j < 0) {
        i = paramInt;
      }
      Intent localIntent = createMainIntent(paramContext, mPackageName, i, mSlotIdName);
      paramContext = localIntent;
      if (localIntent != null)
      {
        prepareDataIntent(localIntent, paramUnderstandAction, paramUnderstandMessage);
        paramContext = localIntent;
        if (mCount > 0)
        {
          paramContext = localIntent;
          if (mLists != null)
          {
            paramContext = localIntent;
            if (mLists.size() > 0)
            {
              paramUnderstandAction = mLists.iterator();
              for (;;)
              {
                paramContext = localIntent;
                if (!paramUnderstandAction.hasNext()) {
                  break;
                }
                paramContext = (UnderstandAction.Params)paramUnderstandAction.next();
                localIntent.putExtra(mName, convertValue(mValue, paramUnderstandMessage, paramInt));
              }
            }
          }
        }
      }
    }
    else
    {
      paramContext = null;
    }
    return paramContext;
  }
  
  private static String prepareUrlParam(UnderstandAction paramUnderstandAction, UnderstandMessage paramUnderstandMessage)
  {
    if (mUrlPartCount > 0)
    {
      List localList = mUrlParts;
      if (localList != null)
      {
        StringBuilder localStringBuilder = new StringBuilder("url=");
        int i = 0;
        if (i < mUrlPartCount)
        {
          String str = (String)localList.get(i);
          if ((str != null) && (str.startsWith("#"))) {
            localStringBuilder.append(findUriValue(str, paramUnderstandMessage));
          }
          for (;;)
          {
            i += 1;
            break;
            localStringBuilder.append(str);
          }
        }
        return localStringBuilder.toString();
      }
    }
    return "";
  }
  
  public static void reStartInitUnderstand()
  {
    setInitialized(false);
    SMSUnderstand.freeOntology();
    SMSUnderstand.setPlatform(ROM_VERSION);
    try
    {
      if (!SMSUnderstand.initial("/data/data/com.android.mms/app_understand/smsUnderstand.config"))
      {
        Log.e("UnderstandFactory", "restart understand failed");
        return;
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      return;
    }
    setInitialized(true);
  }
  
  public static void setInitialized(boolean paramBoolean)
  {
    synchronized (sInitLockObj)
    {
      sInitialized = paramBoolean;
      return;
    }
  }
  
  public static void setLocalHostNumber(String paramString, int paramInt)
  {
    if (paramInt == 0) {}
    for (CardIndex localCardIndex = CardIndex.CARD1;; localCardIndex = CardIndex.CARD2)
    {
      SMSUnderstand.setLocalHostPhoneNumber(paramString, localCardIndex);
      return;
    }
  }
  
  public static void setLocalHostPlace(String paramString, int paramInt)
  {
    if (paramInt == 0) {}
    for (CardIndex localCardIndex = CardIndex.CARD1;; localCardIndex = CardIndex.CARD2)
    {
      SMSUnderstand.setLocalHostPlace(paramString, localCardIndex);
      return;
    }
  }
  
  private static void showTextWithHighlight(TextView paramTextView, SpannableString paramSpannableString, String paramString1, String paramString2, int paramInt)
  {
    if (!TextUtils.isEmpty(paramString2))
    {
      paramString1 = paramString1.toLowerCase();
      paramString2 = paramString2.toLowerCase().split(" ");
      int k = paramString2.length;
      int i = 0;
      while (i < k)
      {
        String str = paramString2[i];
        int j = -str.length();
        for (;;)
        {
          j = paramString1.indexOf(str, str.length() + j);
          if (j == -1) {
            break;
          }
          paramSpannableString.setSpan(new TextSizeAdjustSpan(paramTextView.getContext(), paramInt), j, str.length() + j, 33);
        }
        i += 1;
      }
    }
  }
  
  public static void showTextWithUnderstand(TextView paramTextView, String paramString1, List<UnderstandMessage> paramList, String paramString2, int paramInt1, int paramInt2)
  {
    int i = 0;
    SpannableString localSpannableString = new SpannableString(paramString1);
    paramList = paramList.iterator();
    if (paramList.hasNext())
    {
      Iterator localIterator = nextmItems.iterator();
      for (int j = i;; j = 1)
      {
        UnderstandMessage.Item localItem;
        do
        {
          i = j;
          if (!localIterator.hasNext()) {
            break;
          }
          localItem = (UnderstandMessage.Item)localIterator.next();
        } while ((mHas <= 0) || (mEndPosition <= mStartPosition) || (mEndPosition >= paramString1.length()));
        TextSizeAdjustSpan localTextSizeAdjustSpan = new TextSizeAdjustSpan(paramTextView.getContext(), paramInt1);
        localTextSizeAdjustSpan.needUnderline();
        localSpannableString.setSpan(localTextSizeAdjustSpan, mStartPosition, mEndPosition, 33);
      }
    }
    if (!TextUtils.isEmpty(paramString2))
    {
      showTextWithHighlight(paramTextView, localSpannableString, paramString1, paramString2, paramInt2);
      i = 1;
    }
    if (i != 0)
    {
      paramTextView.setText(localSpannableString);
      return;
    }
    paramTextView.setText(paramString1);
  }
  
  /* Error */
  public static boolean unzipFiles()
  {
    // Byte code:
    //   0: new 475	java/io/File
    //   3: dup
    //   4: ldc_w 566
    //   7: invokespecial 481	java/io/File:<init>	(Ljava/lang/String;)V
    //   10: astore 7
    //   12: aload 7
    //   14: invokevirtual 585	java/io/File:mkdirs	()Z
    //   17: pop
    //   18: aconst_null
    //   19: astore 4
    //   21: aconst_null
    //   22: astore_3
    //   23: aload 4
    //   25: astore_2
    //   26: new 475	java/io/File
    //   29: dup
    //   30: invokestatic 561	com/android/mms/understand/TemplateUpdate:getUpdatedFile	()Ljava/lang/String;
    //   33: invokespecial 481	java/io/File:<init>	(Ljava/lang/String;)V
    //   36: astore 5
    //   38: aload 4
    //   40: astore_2
    //   41: aload 5
    //   43: invokevirtual 528	java/io/File:exists	()Z
    //   46: ifne +54 -> 100
    //   49: aload 4
    //   51: astore_2
    //   52: ldc -17
    //   54: new 65	java/lang/StringBuilder
    //   57: dup
    //   58: invokespecial 66	java/lang/StringBuilder:<init>	()V
    //   61: ldc_w 598
    //   64: invokevirtual 75	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   67: aload 5
    //   69: invokevirtual 582	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   72: invokevirtual 79	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   75: invokestatic 247	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   78: pop
    //   79: iconst_0
    //   80: ifeq +11 -> 91
    //   83: new 600	java/lang/NullPointerException
    //   86: dup
    //   87: invokespecial 601	java/lang/NullPointerException:<init>	()V
    //   90: athrow
    //   91: iconst_0
    //   92: ireturn
    //   93: astore_2
    //   94: aload_2
    //   95: invokevirtual 509	java/io/IOException:printStackTrace	()V
    //   98: iconst_0
    //   99: ireturn
    //   100: aload 4
    //   102: astore_2
    //   103: new 603	java/util/zip/ZipInputStream
    //   106: dup
    //   107: new 605	java/io/BufferedInputStream
    //   110: dup
    //   111: new 487	java/io/FileInputStream
    //   114: dup
    //   115: aload 5
    //   117: invokespecial 490	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   120: invokespecial 606	java/io/BufferedInputStream:<init>	(Ljava/io/InputStream;)V
    //   123: invokespecial 607	java/util/zip/ZipInputStream:<init>	(Ljava/io/InputStream;)V
    //   126: astore 4
    //   128: sipush 4096
    //   131: newarray <illegal type>
    //   133: astore 8
    //   135: ldc -17
    //   137: new 65	java/lang/StringBuilder
    //   140: dup
    //   141: invokespecial 66	java/lang/StringBuilder:<init>	()V
    //   144: ldc_w 609
    //   147: invokevirtual 75	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   150: aload 5
    //   152: invokevirtual 610	java/io/File:toString	()Ljava/lang/String;
    //   155: invokevirtual 75	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   158: ldc_w 612
    //   161: invokevirtual 75	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   164: aload 7
    //   166: invokevirtual 610	java/io/File:toString	()Ljava/lang/String;
    //   169: invokevirtual 75	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   172: invokevirtual 79	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   175: invokestatic 274	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   178: pop
    //   179: iconst_0
    //   180: istore_0
    //   181: aload 4
    //   183: invokevirtual 616	java/util/zip/ZipInputStream:getNextEntry	()Ljava/util/zip/ZipEntry;
    //   186: astore_2
    //   187: aload_2
    //   188: ifnull +221 -> 409
    //   191: iload_0
    //   192: iconst_1
    //   193: iadd
    //   194: istore_1
    //   195: new 475	java/io/File
    //   198: dup
    //   199: aload 7
    //   201: aload_2
    //   202: invokevirtual 621	java/util/zip/ZipEntry:getName	()Ljava/lang/String;
    //   205: invokespecial 596	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   208: astore_3
    //   209: ldc -17
    //   211: new 65	java/lang/StringBuilder
    //   214: dup
    //   215: invokespecial 66	java/lang/StringBuilder:<init>	()V
    //   218: ldc_w 766
    //   221: invokevirtual 75	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   224: aload_3
    //   225: invokevirtual 582	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   228: invokevirtual 79	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   231: invokestatic 545	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   234: pop
    //   235: aload_2
    //   236: invokevirtual 626	java/util/zip/ZipEntry:isDirectory	()Z
    //   239: ifeq +37 -> 276
    //   242: aload_3
    //   243: invokevirtual 585	java/io/File:mkdirs	()Z
    //   246: pop
    //   247: iload_1
    //   248: istore_0
    //   249: goto -68 -> 181
    //   252: astore_2
    //   253: aload 4
    //   255: astore_3
    //   256: aload_2
    //   257: astore 4
    //   259: aload_3
    //   260: astore_2
    //   261: aload 4
    //   263: invokevirtual 509	java/io/IOException:printStackTrace	()V
    //   266: aload_3
    //   267: ifnull +7 -> 274
    //   270: aload_3
    //   271: invokevirtual 627	java/util/zip/ZipInputStream:close	()V
    //   274: iconst_1
    //   275: ireturn
    //   276: aload_3
    //   277: invokevirtual 630	java/io/File:createNewFile	()Z
    //   280: pop
    //   281: aconst_null
    //   282: astore_2
    //   283: aconst_null
    //   284: astore 6
    //   286: new 632	java/io/FileOutputStream
    //   289: dup
    //   290: aload_3
    //   291: invokespecial 633	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   294: astore_3
    //   295: aload 4
    //   297: aload 8
    //   299: invokevirtual 637	java/util/zip/ZipInputStream:read	([B)I
    //   302: istore_0
    //   303: iload_0
    //   304: iconst_m1
    //   305: if_icmpeq +62 -> 367
    //   308: aload_3
    //   309: aload 8
    //   311: iconst_0
    //   312: iload_0
    //   313: invokevirtual 641	java/io/FileOutputStream:write	([BII)V
    //   316: goto -21 -> 295
    //   319: astore 5
    //   321: aload_3
    //   322: astore_2
    //   323: aload 5
    //   325: invokevirtual 509	java/io/IOException:printStackTrace	()V
    //   328: iload_1
    //   329: istore_0
    //   330: aload_3
    //   331: ifnull -150 -> 181
    //   334: aload_3
    //   335: invokevirtual 642	java/io/FileOutputStream:close	()V
    //   338: iload_1
    //   339: istore_0
    //   340: goto -159 -> 181
    //   343: astore_2
    //   344: aload_2
    //   345: invokevirtual 509	java/io/IOException:printStackTrace	()V
    //   348: iload_1
    //   349: istore_0
    //   350: goto -169 -> 181
    //   353: astore_2
    //   354: aload 4
    //   356: astore_3
    //   357: aload_3
    //   358: ifnull +7 -> 365
    //   361: aload_3
    //   362: invokevirtual 627	java/util/zip/ZipInputStream:close	()V
    //   365: aload_2
    //   366: athrow
    //   367: aload_3
    //   368: ifnull +142 -> 510
    //   371: aload_3
    //   372: invokevirtual 642	java/io/FileOutputStream:close	()V
    //   375: iload_1
    //   376: istore_0
    //   377: goto -196 -> 181
    //   380: astore_2
    //   381: aload_2
    //   382: invokevirtual 509	java/io/IOException:printStackTrace	()V
    //   385: iload_1
    //   386: istore_0
    //   387: goto -206 -> 181
    //   390: astore_3
    //   391: aload_2
    //   392: ifnull +7 -> 399
    //   395: aload_2
    //   396: invokevirtual 642	java/io/FileOutputStream:close	()V
    //   399: aload_3
    //   400: athrow
    //   401: astore_2
    //   402: aload_2
    //   403: invokevirtual 509	java/io/IOException:printStackTrace	()V
    //   406: goto -7 -> 399
    //   409: iload_0
    //   410: ifne +27 -> 437
    //   413: lconst_0
    //   414: invokestatic 769	com/android/mms/understand/UnderstandFactory:updateVersion	(J)V
    //   417: aload 4
    //   419: ifnull +8 -> 427
    //   422: aload 4
    //   424: invokevirtual 627	java/util/zip/ZipInputStream:close	()V
    //   427: iconst_0
    //   428: ireturn
    //   429: astore_2
    //   430: aload_2
    //   431: invokevirtual 509	java/io/IOException:printStackTrace	()V
    //   434: goto -7 -> 427
    //   437: aload 4
    //   439: ifnull +68 -> 507
    //   442: aload 4
    //   444: invokevirtual 627	java/util/zip/ZipInputStream:close	()V
    //   447: goto -173 -> 274
    //   450: astore_2
    //   451: aload_2
    //   452: invokevirtual 509	java/io/IOException:printStackTrace	()V
    //   455: goto -181 -> 274
    //   458: astore_2
    //   459: aload_2
    //   460: invokevirtual 509	java/io/IOException:printStackTrace	()V
    //   463: goto -189 -> 274
    //   466: astore_3
    //   467: aload_3
    //   468: invokevirtual 509	java/io/IOException:printStackTrace	()V
    //   471: goto -106 -> 365
    //   474: astore 4
    //   476: aload_2
    //   477: astore_3
    //   478: aload 4
    //   480: astore_2
    //   481: goto -124 -> 357
    //   484: astore 4
    //   486: goto -227 -> 259
    //   489: astore 5
    //   491: aload_3
    //   492: astore_2
    //   493: aload 5
    //   495: astore_3
    //   496: goto -105 -> 391
    //   499: astore 5
    //   501: aload 6
    //   503: astore_3
    //   504: goto -183 -> 321
    //   507: goto -233 -> 274
    //   510: iload_1
    //   511: istore_0
    //   512: goto -331 -> 181
    // Local variable table:
    //   start	length	slot	name	signature
    //   180	332	0	i	int
    //   194	317	1	j	int
    //   25	27	2	localObject1	Object
    //   93	2	2	localIOException1	java.io.IOException
    //   102	134	2	localObject2	Object
    //   252	5	2	localIOException2	java.io.IOException
    //   260	63	2	localObject3	Object
    //   343	2	2	localIOException3	java.io.IOException
    //   353	13	2	localObject4	Object
    //   380	16	2	localIOException4	java.io.IOException
    //   401	2	2	localIOException5	java.io.IOException
    //   429	2	2	localIOException6	java.io.IOException
    //   450	2	2	localIOException7	java.io.IOException
    //   458	19	2	localIOException8	java.io.IOException
    //   480	13	2	localObject5	Object
    //   22	350	3	localObject6	Object
    //   390	10	3	localObject7	Object
    //   466	2	3	localIOException9	java.io.IOException
    //   477	27	3	localObject8	Object
    //   19	424	4	localObject9	Object
    //   474	5	4	localObject10	Object
    //   484	1	4	localIOException10	java.io.IOException
    //   36	115	5	localFile1	File
    //   319	5	5	localIOException11	java.io.IOException
    //   489	5	5	localObject11	Object
    //   499	1	5	localIOException12	java.io.IOException
    //   284	218	6	localObject12	Object
    //   10	190	7	localFile2	File
    //   133	177	8	arrayOfByte	byte[]
    // Exception table:
    //   from	to	target	type
    //   83	91	93	java/io/IOException
    //   128	179	252	java/io/IOException
    //   181	187	252	java/io/IOException
    //   195	247	252	java/io/IOException
    //   276	281	252	java/io/IOException
    //   344	348	252	java/io/IOException
    //   381	385	252	java/io/IOException
    //   399	401	252	java/io/IOException
    //   402	406	252	java/io/IOException
    //   413	417	252	java/io/IOException
    //   295	303	319	java/io/IOException
    //   308	316	319	java/io/IOException
    //   334	338	343	java/io/IOException
    //   128	179	353	finally
    //   181	187	353	finally
    //   195	247	353	finally
    //   276	281	353	finally
    //   334	338	353	finally
    //   344	348	353	finally
    //   371	375	353	finally
    //   381	385	353	finally
    //   395	399	353	finally
    //   399	401	353	finally
    //   402	406	353	finally
    //   413	417	353	finally
    //   371	375	380	java/io/IOException
    //   286	295	390	finally
    //   323	328	390	finally
    //   395	399	401	java/io/IOException
    //   422	427	429	java/io/IOException
    //   442	447	450	java/io/IOException
    //   270	274	458	java/io/IOException
    //   361	365	466	java/io/IOException
    //   26	38	474	finally
    //   41	49	474	finally
    //   52	79	474	finally
    //   103	128	474	finally
    //   261	266	474	finally
    //   26	38	484	java/io/IOException
    //   41	49	484	java/io/IOException
    //   52	79	484	java/io/IOException
    //   103	128	484	java/io/IOException
    //   295	303	489	finally
    //   308	316	489	finally
    //   286	295	499	java/io/IOException
  }
  
  public static void updateVersion(long paramLong)
  {
    updateVersionFile(paramLong);
    MiStatSdkHelper.recordStringPropertyEvent("understand_category", "template_version", Long.toString(paramLong));
  }
  
  /* Error */
  public static void updateVersionFile(long paramLong)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: new 475	java/io/File
    //   6: dup
    //   7: invokestatic 480	com/android/mms/understand/TemplateUpdate:getVersionFile	()Ljava/lang/String;
    //   10: invokespecial 481	java/io/File:<init>	(Ljava/lang/String;)V
    //   13: astore_2
    //   14: ldc -17
    //   16: new 65	java/lang/StringBuilder
    //   19: dup
    //   20: invokespecial 66	java/lang/StringBuilder:<init>	()V
    //   23: ldc_w 771
    //   26: invokevirtual 75	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   29: lload_0
    //   30: invokevirtual 538	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   33: invokevirtual 79	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   36: invokestatic 274	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
    //   39: pop
    //   40: aload_2
    //   41: invokevirtual 630	java/io/File:createNewFile	()Z
    //   44: pop
    //   45: aconst_null
    //   46: astore_2
    //   47: aconst_null
    //   48: astore 4
    //   50: new 773	java/io/BufferedWriter
    //   53: dup
    //   54: new 775	java/io/FileWriter
    //   57: dup
    //   58: invokestatic 480	com/android/mms/understand/TemplateUpdate:getVersionFile	()Ljava/lang/String;
    //   61: invokespecial 776	java/io/FileWriter:<init>	(Ljava/lang/String;)V
    //   64: invokespecial 779	java/io/BufferedWriter:<init>	(Ljava/io/Writer;)V
    //   67: astore_3
    //   68: aload_3
    //   69: lload_0
    //   70: invokestatic 782	java/lang/String:valueOf	(J)Ljava/lang/String;
    //   73: invokevirtual 784	java/io/BufferedWriter:write	(Ljava/lang/String;)V
    //   76: aload_3
    //   77: invokevirtual 787	java/io/BufferedWriter:newLine	()V
    //   80: aload_3
    //   81: invokevirtual 790	java/io/BufferedWriter:flush	()V
    //   84: aload_3
    //   85: ifnull +100 -> 185
    //   88: aload_3
    //   89: invokevirtual 791	java/io/BufferedWriter:close	()V
    //   92: ldc 2
    //   94: monitorexit
    //   95: return
    //   96: astore_2
    //   97: aload_2
    //   98: invokevirtual 509	java/io/IOException:printStackTrace	()V
    //   101: goto -9 -> 92
    //   104: astore_2
    //   105: aload 4
    //   107: astore_3
    //   108: aload_2
    //   109: astore 4
    //   111: aload_3
    //   112: astore_2
    //   113: aload 4
    //   115: invokevirtual 509	java/io/IOException:printStackTrace	()V
    //   118: aload_3
    //   119: ifnull -27 -> 92
    //   122: aload_3
    //   123: invokevirtual 791	java/io/BufferedWriter:close	()V
    //   126: goto -34 -> 92
    //   129: astore_2
    //   130: aload_2
    //   131: invokevirtual 509	java/io/IOException:printStackTrace	()V
    //   134: goto -42 -> 92
    //   137: astore_2
    //   138: aload_2
    //   139: invokevirtual 262	java/lang/Exception:printStackTrace	()V
    //   142: goto -50 -> 92
    //   145: astore_2
    //   146: ldc 2
    //   148: monitorexit
    //   149: aload_2
    //   150: athrow
    //   151: astore_3
    //   152: aload_2
    //   153: ifnull +7 -> 160
    //   156: aload_2
    //   157: invokevirtual 791	java/io/BufferedWriter:close	()V
    //   160: aload_3
    //   161: athrow
    //   162: astore_2
    //   163: aload_2
    //   164: invokevirtual 509	java/io/IOException:printStackTrace	()V
    //   167: goto -7 -> 160
    //   170: astore 4
    //   172: aload_3
    //   173: astore_2
    //   174: aload 4
    //   176: astore_3
    //   177: goto -25 -> 152
    //   180: astore 4
    //   182: goto -71 -> 111
    //   185: goto -93 -> 92
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	188	0	paramLong	long
    //   13	34	2	localFile	File
    //   96	2	2	localIOException1	java.io.IOException
    //   104	5	2	localIOException2	java.io.IOException
    //   112	1	2	localObject1	Object
    //   129	2	2	localIOException3	java.io.IOException
    //   137	2	2	localException	Exception
    //   145	12	2	localObject2	Object
    //   162	2	2	localIOException4	java.io.IOException
    //   173	1	2	localObject3	Object
    //   67	56	3	localObject4	Object
    //   151	22	3	localObject5	Object
    //   176	1	3	localObject6	Object
    //   48	66	4	localIOException5	java.io.IOException
    //   170	5	4	localObject7	Object
    //   180	1	4	localIOException6	java.io.IOException
    // Exception table:
    //   from	to	target	type
    //   88	92	96	java/io/IOException
    //   50	68	104	java/io/IOException
    //   122	126	129	java/io/IOException
    //   40	45	137	java/lang/Exception
    //   88	92	137	java/lang/Exception
    //   97	101	137	java/lang/Exception
    //   122	126	137	java/lang/Exception
    //   130	134	137	java/lang/Exception
    //   156	160	137	java/lang/Exception
    //   160	162	137	java/lang/Exception
    //   163	167	137	java/lang/Exception
    //   3	40	145	finally
    //   40	45	145	finally
    //   88	92	145	finally
    //   97	101	145	finally
    //   122	126	145	finally
    //   130	134	145	finally
    //   138	142	145	finally
    //   156	160	145	finally
    //   160	162	145	finally
    //   163	167	145	finally
    //   50	68	151	finally
    //   113	118	151	finally
    //   156	160	162	java/io/IOException
    //   68	84	170	finally
    //   68	84	180	java/io/IOException
  }
}

/* Location:
 * Qualified Name:     com.android.mms.understand.UnderstandFactory
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */