package android.support.v7.internal.widget;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.DataSetObservable;
import android.os.AsyncTask;
import android.support.v4.os.AsyncTaskCompat;
import android.text.TextUtils;
import android.util.Log;
import android.util.Xml;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.xmlpull.v1.XmlSerializer;

public class ActivityChooserModel
  extends DataSetObservable
{
  private static final String ATTRIBUTE_ACTIVITY = "activity";
  private static final String ATTRIBUTE_TIME = "time";
  private static final String ATTRIBUTE_WEIGHT = "weight";
  private static final boolean DEBUG = false;
  private static final int DEFAULT_ACTIVITY_INFLATION = 5;
  private static final float DEFAULT_HISTORICAL_RECORD_WEIGHT = 1.0F;
  public static final String DEFAULT_HISTORY_FILE_NAME = "activity_choser_model_history.xml";
  public static final int DEFAULT_HISTORY_MAX_LENGTH = 50;
  private static final String HISTORY_FILE_EXTENSION = ".xml";
  private static final int INVALID_INDEX = -1;
  private static final String LOG_TAG = ActivityChooserModel.class.getSimpleName();
  private static final String TAG_HISTORICAL_RECORD = "historical-record";
  private static final String TAG_HISTORICAL_RECORDS = "historical-records";
  private static final Map<String, ActivityChooserModel> sDataModelRegistry = new HashMap();
  private static final Object sRegistryLock = new Object();
  private final List<ActivityResolveInfo> mActivities = new ArrayList();
  private OnChooseActivityListener mActivityChoserModelPolicy;
  private ActivitySorter mActivitySorter = new DefaultSorter(null);
  private boolean mCanReadHistoricalData = true;
  private final Context mContext;
  private final List<HistoricalRecord> mHistoricalRecords = new ArrayList();
  private boolean mHistoricalRecordsChanged = true;
  private final String mHistoryFileName;
  private int mHistoryMaxSize = 50;
  private final Object mInstanceLock = new Object();
  private Intent mIntent;
  private boolean mReadShareHistoryCalled = false;
  private boolean mReloadActivities = false;
  
  private ActivityChooserModel(Context paramContext, String paramString)
  {
    mContext = paramContext.getApplicationContext();
    if ((!TextUtils.isEmpty(paramString)) && (!paramString.endsWith(".xml")))
    {
      mHistoryFileName = (paramString + ".xml");
      return;
    }
    mHistoryFileName = paramString;
  }
  
  private boolean addHisoricalRecord(HistoricalRecord paramHistoricalRecord)
  {
    boolean bool = mHistoricalRecords.add(paramHistoricalRecord);
    if (bool)
    {
      mHistoricalRecordsChanged = true;
      pruneExcessiveHistoricalRecordsIfNeeded();
      persistHistoricalDataIfNeeded();
      sortActivitiesIfNeeded();
      notifyChanged();
    }
    return bool;
  }
  
  private void ensureConsistentState()
  {
    boolean bool1 = loadActivitiesIfNeeded();
    boolean bool2 = readHistoricalDataIfNeeded();
    pruneExcessiveHistoricalRecordsIfNeeded();
    if ((bool1 | bool2))
    {
      sortActivitiesIfNeeded();
      notifyChanged();
    }
  }
  
  public static ActivityChooserModel get(Context paramContext, String paramString)
  {
    synchronized (sRegistryLock)
    {
      ActivityChooserModel localActivityChooserModel2 = (ActivityChooserModel)sDataModelRegistry.get(paramString);
      ActivityChooserModel localActivityChooserModel1 = localActivityChooserModel2;
      if (localActivityChooserModel2 == null)
      {
        localActivityChooserModel1 = new ActivityChooserModel(paramContext, paramString);
        sDataModelRegistry.put(paramString, localActivityChooserModel1);
      }
      return localActivityChooserModel1;
    }
  }
  
  private boolean loadActivitiesIfNeeded()
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (mReloadActivities)
    {
      bool1 = bool2;
      if (mIntent != null)
      {
        mReloadActivities = false;
        mActivities.clear();
        List localList = mContext.getPackageManager().queryIntentActivities(mIntent, 0);
        int j = localList.size();
        int i = 0;
        while (i < j)
        {
          ResolveInfo localResolveInfo = (ResolveInfo)localList.get(i);
          mActivities.add(new ActivityResolveInfo(localResolveInfo));
          i += 1;
        }
        bool1 = true;
      }
    }
    return bool1;
  }
  
  private void persistHistoricalDataIfNeeded()
  {
    if (!mReadShareHistoryCalled) {
      throw new IllegalStateException("No preceding call to #readHistoricalData");
    }
    if (!mHistoricalRecordsChanged) {}
    do
    {
      return;
      mHistoricalRecordsChanged = false;
    } while (TextUtils.isEmpty(mHistoryFileName));
    AsyncTaskCompat.executeParallel(new PersistHistoryAsyncTask(null), new Object[] { mHistoricalRecords, mHistoryFileName });
  }
  
  private void pruneExcessiveHistoricalRecordsIfNeeded()
  {
    int j = mHistoricalRecords.size() - mHistoryMaxSize;
    if (j <= 0) {}
    for (;;)
    {
      return;
      mHistoricalRecordsChanged = true;
      int i = 0;
      while (i < j)
      {
        HistoricalRecord localHistoricalRecord = (HistoricalRecord)mHistoricalRecords.remove(0);
        i += 1;
      }
    }
  }
  
  private boolean readHistoricalDataIfNeeded()
  {
    if ((mCanReadHistoricalData) && (mHistoricalRecordsChanged) && (!TextUtils.isEmpty(mHistoryFileName)))
    {
      mCanReadHistoricalData = false;
      mReadShareHistoryCalled = true;
      readHistoricalDataImpl();
      return true;
    }
    return false;
  }
  
  /* Error */
  private void readHistoricalDataImpl()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 145	android/support/v7/internal/widget/ActivityChooserModel:mContext	Landroid/content/Context;
    //   4: aload_0
    //   5: getfield 169	android/support/v7/internal/widget/ActivityChooserModel:mHistoryFileName	Ljava/lang/String;
    //   8: invokevirtual 274	android/content/Context:openFileInput	(Ljava/lang/String;)Ljava/io/FileInputStream;
    //   11: astore_2
    //   12: invokestatic 280	android/util/Xml:newPullParser	()Lorg/xmlpull/v1/XmlPullParser;
    //   15: astore_3
    //   16: aload_3
    //   17: aload_2
    //   18: aconst_null
    //   19: invokeinterface 286 3 0
    //   24: iconst_0
    //   25: istore_1
    //   26: iload_1
    //   27: iconst_1
    //   28: if_icmpeq +18 -> 46
    //   31: iload_1
    //   32: iconst_2
    //   33: if_icmpeq +13 -> 46
    //   36: aload_3
    //   37: invokeinterface 289 1 0
    //   42: istore_1
    //   43: goto -17 -> 26
    //   46: ldc 63
    //   48: aload_3
    //   49: invokeinterface 292 1 0
    //   54: invokevirtual 295	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   57: ifne +55 -> 112
    //   60: new 268	org/xmlpull/v1/XmlPullParserException
    //   63: dup
    //   64: ldc_w 297
    //   67: invokespecial 298	org/xmlpull/v1/XmlPullParserException:<init>	(Ljava/lang/String;)V
    //   70: athrow
    //   71: astore_3
    //   72: getstatic 98	android/support/v7/internal/widget/ActivityChooserModel:LOG_TAG	Ljava/lang/String;
    //   75: new 159	java/lang/StringBuilder
    //   78: dup
    //   79: invokespecial 160	java/lang/StringBuilder:<init>	()V
    //   82: ldc_w 300
    //   85: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   88: aload_0
    //   89: getfield 169	android/support/v7/internal/widget/ActivityChooserModel:mHistoryFileName	Ljava/lang/String;
    //   92: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   95: invokevirtual 167	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   98: aload_3
    //   99: invokestatic 306	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   102: pop
    //   103: aload_2
    //   104: ifnull +7 -> 111
    //   107: aload_2
    //   108: invokevirtual 311	java/io/FileInputStream:close	()V
    //   111: return
    //   112: aload_0
    //   113: getfield 122	android/support/v7/internal/widget/ActivityChooserModel:mHistoricalRecords	Ljava/util/List;
    //   116: astore 4
    //   118: aload 4
    //   120: invokeinterface 222 1 0
    //   125: aload_3
    //   126: invokeinterface 289 1 0
    //   131: istore_1
    //   132: iload_1
    //   133: iconst_1
    //   134: if_icmpne +14 -> 148
    //   137: aload_2
    //   138: ifnull -27 -> 111
    //   141: aload_2
    //   142: invokevirtual 311	java/io/FileInputStream:close	()V
    //   145: return
    //   146: astore_2
    //   147: return
    //   148: iload_1
    //   149: iconst_3
    //   150: if_icmpeq -25 -> 125
    //   153: iload_1
    //   154: iconst_4
    //   155: if_icmpeq -30 -> 125
    //   158: ldc 60
    //   160: aload_3
    //   161: invokeinterface 292 1 0
    //   166: invokevirtual 295	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   169: ifne +57 -> 226
    //   172: new 268	org/xmlpull/v1/XmlPullParserException
    //   175: dup
    //   176: ldc_w 313
    //   179: invokespecial 298	org/xmlpull/v1/XmlPullParserException:<init>	(Ljava/lang/String;)V
    //   182: athrow
    //   183: astore_3
    //   184: getstatic 98	android/support/v7/internal/widget/ActivityChooserModel:LOG_TAG	Ljava/lang/String;
    //   187: new 159	java/lang/StringBuilder
    //   190: dup
    //   191: invokespecial 160	java/lang/StringBuilder:<init>	()V
    //   194: ldc_w 300
    //   197: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   200: aload_0
    //   201: getfield 169	android/support/v7/internal/widget/ActivityChooserModel:mHistoryFileName	Ljava/lang/String;
    //   204: invokevirtual 164	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   207: invokevirtual 167	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   210: aload_3
    //   211: invokestatic 306	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   214: pop
    //   215: aload_2
    //   216: ifnull -105 -> 111
    //   219: aload_2
    //   220: invokevirtual 311	java/io/FileInputStream:close	()V
    //   223: return
    //   224: astore_2
    //   225: return
    //   226: aload 4
    //   228: new 20	android/support/v7/internal/widget/ActivityChooserModel$HistoricalRecord
    //   231: dup
    //   232: aload_3
    //   233: aconst_null
    //   234: ldc 31
    //   236: invokeinterface 317 3 0
    //   241: aload_3
    //   242: aconst_null
    //   243: ldc 34
    //   245: invokeinterface 317 3 0
    //   250: invokestatic 323	java/lang/Long:parseLong	(Ljava/lang/String;)J
    //   253: aload_3
    //   254: aconst_null
    //   255: ldc 37
    //   257: invokeinterface 317 3 0
    //   262: invokestatic 329	java/lang/Float:parseFloat	(Ljava/lang/String;)F
    //   265: invokespecial 332	android/support/v7/internal/widget/ActivityChooserModel$HistoricalRecord:<init>	(Ljava/lang/String;JF)V
    //   268: invokeinterface 184 2 0
    //   273: pop
    //   274: goto -149 -> 125
    //   277: astore_3
    //   278: aload_2
    //   279: ifnull +7 -> 286
    //   282: aload_2
    //   283: invokevirtual 311	java/io/FileInputStream:close	()V
    //   286: aload_3
    //   287: athrow
    //   288: astore_2
    //   289: return
    //   290: astore_2
    //   291: goto -5 -> 286
    //   294: astore_2
    //   295: return
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	296	0	this	ActivityChooserModel
    //   25	131	1	i	int
    //   11	131	2	localFileInputStream	java.io.FileInputStream
    //   146	74	2	localIOException1	IOException
    //   224	59	2	localIOException2	IOException
    //   288	1	2	localIOException3	IOException
    //   290	1	2	localIOException4	IOException
    //   294	1	2	localFileNotFoundException	FileNotFoundException
    //   15	34	3	localXmlPullParser	org.xmlpull.v1.XmlPullParser
    //   71	90	3	localXmlPullParserException	org.xmlpull.v1.XmlPullParserException
    //   183	71	3	localIOException5	IOException
    //   277	10	3	localObject	Object
    //   116	111	4	localList	List
    // Exception table:
    //   from	to	target	type
    //   12	24	71	org/xmlpull/v1/XmlPullParserException
    //   36	43	71	org/xmlpull/v1/XmlPullParserException
    //   46	71	71	org/xmlpull/v1/XmlPullParserException
    //   112	125	71	org/xmlpull/v1/XmlPullParserException
    //   125	132	71	org/xmlpull/v1/XmlPullParserException
    //   158	183	71	org/xmlpull/v1/XmlPullParserException
    //   226	274	71	org/xmlpull/v1/XmlPullParserException
    //   141	145	146	java/io/IOException
    //   12	24	183	java/io/IOException
    //   36	43	183	java/io/IOException
    //   46	71	183	java/io/IOException
    //   112	125	183	java/io/IOException
    //   125	132	183	java/io/IOException
    //   158	183	183	java/io/IOException
    //   226	274	183	java/io/IOException
    //   219	223	224	java/io/IOException
    //   12	24	277	finally
    //   36	43	277	finally
    //   46	71	277	finally
    //   72	103	277	finally
    //   112	125	277	finally
    //   125	132	277	finally
    //   158	183	277	finally
    //   184	215	277	finally
    //   226	274	277	finally
    //   107	111	288	java/io/IOException
    //   282	286	290	java/io/IOException
    //   0	12	294	java/io/FileNotFoundException
  }
  
  private boolean sortActivitiesIfNeeded()
  {
    if ((mActivitySorter != null) && (mIntent != null) && (!mActivities.isEmpty()) && (!mHistoricalRecords.isEmpty()))
    {
      mActivitySorter.sort(mIntent, mActivities, Collections.unmodifiableList(mHistoricalRecords));
      return true;
    }
    return false;
  }
  
  public Intent chooseActivity(int paramInt)
  {
    synchronized (mInstanceLock)
    {
      if (mIntent == null) {
        return null;
      }
      ensureConsistentState();
      Object localObject2 = (ActivityResolveInfo)mActivities.get(paramInt);
      localObject2 = new ComponentName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name);
      Intent localIntent1 = new Intent(mIntent);
      localIntent1.setComponent((ComponentName)localObject2);
      if (mActivityChoserModelPolicy != null)
      {
        Intent localIntent2 = new Intent(localIntent1);
        if (mActivityChoserModelPolicy.onChooseActivity(this, localIntent2)) {
          return null;
        }
      }
      addHisoricalRecord(new HistoricalRecord((ComponentName)localObject2, System.currentTimeMillis(), 1.0F));
      return localIntent1;
    }
  }
  
  public ResolveInfo getActivity(int paramInt)
  {
    synchronized (mInstanceLock)
    {
      ensureConsistentState();
      ResolveInfo localResolveInfo = mActivities.get(paramInt)).resolveInfo;
      return localResolveInfo;
    }
  }
  
  public int getActivityCount()
  {
    synchronized (mInstanceLock)
    {
      ensureConsistentState();
      int i = mActivities.size();
      return i;
    }
  }
  
  public int getActivityIndex(ResolveInfo paramResolveInfo)
  {
    for (;;)
    {
      int i;
      synchronized (mInstanceLock)
      {
        ensureConsistentState();
        List localList = mActivities;
        int j = localList.size();
        i = 0;
        if (i < j)
        {
          if (getresolveInfo == paramResolveInfo) {
            return i;
          }
        }
        else {
          return -1;
        }
      }
      i += 1;
    }
  }
  
  public ResolveInfo getDefaultActivity()
  {
    synchronized (mInstanceLock)
    {
      ensureConsistentState();
      if (!mActivities.isEmpty())
      {
        ResolveInfo localResolveInfo = mActivities.get(0)).resolveInfo;
        return localResolveInfo;
      }
      return null;
    }
  }
  
  public int getHistoryMaxSize()
  {
    synchronized (mInstanceLock)
    {
      int i = mHistoryMaxSize;
      return i;
    }
  }
  
  public int getHistorySize()
  {
    synchronized (mInstanceLock)
    {
      ensureConsistentState();
      int i = mHistoricalRecords.size();
      return i;
    }
  }
  
  public Intent getIntent()
  {
    synchronized (mInstanceLock)
    {
      Intent localIntent = mIntent;
      return localIntent;
    }
  }
  
  public void setActivitySorter(ActivitySorter paramActivitySorter)
  {
    synchronized (mInstanceLock)
    {
      if (mActivitySorter == paramActivitySorter) {
        return;
      }
      mActivitySorter = paramActivitySorter;
      if (sortActivitiesIfNeeded()) {
        notifyChanged();
      }
      return;
    }
  }
  
  public void setDefaultActivity(int paramInt)
  {
    for (;;)
    {
      synchronized (mInstanceLock)
      {
        ensureConsistentState();
        ActivityResolveInfo localActivityResolveInfo1 = (ActivityResolveInfo)mActivities.get(paramInt);
        ActivityResolveInfo localActivityResolveInfo2 = (ActivityResolveInfo)mActivities.get(0);
        if (localActivityResolveInfo2 != null)
        {
          f = weight - weight + 5.0F;
          addHisoricalRecord(new HistoricalRecord(new ComponentName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name), System.currentTimeMillis(), f));
          return;
        }
      }
      float f = 1.0F;
    }
  }
  
  public void setHistoryMaxSize(int paramInt)
  {
    synchronized (mInstanceLock)
    {
      if (mHistoryMaxSize == paramInt) {
        return;
      }
      mHistoryMaxSize = paramInt;
      pruneExcessiveHistoricalRecordsIfNeeded();
      if (sortActivitiesIfNeeded()) {
        notifyChanged();
      }
      return;
    }
  }
  
  public void setIntent(Intent paramIntent)
  {
    synchronized (mInstanceLock)
    {
      if (mIntent == paramIntent) {
        return;
      }
      mIntent = paramIntent;
      mReloadActivities = true;
      ensureConsistentState();
      return;
    }
  }
  
  public void setOnChooseActivityListener(OnChooseActivityListener paramOnChooseActivityListener)
  {
    synchronized (mInstanceLock)
    {
      mActivityChoserModelPolicy = paramOnChooseActivityListener;
      return;
    }
  }
  
  public static abstract interface ActivityChooserModelClient
  {
    public abstract void setActivityChooserModel(ActivityChooserModel paramActivityChooserModel);
  }
  
  public final class ActivityResolveInfo
    implements Comparable<ActivityResolveInfo>
  {
    public final ResolveInfo resolveInfo;
    public float weight;
    
    public ActivityResolveInfo(ResolveInfo paramResolveInfo)
    {
      resolveInfo = paramResolveInfo;
    }
    
    public int compareTo(ActivityResolveInfo paramActivityResolveInfo)
    {
      return Float.floatToIntBits(weight) - Float.floatToIntBits(weight);
    }
    
    public boolean equals(Object paramObject)
    {
      if (this == paramObject) {}
      do
      {
        return true;
        if (paramObject == null) {
          return false;
        }
        if (getClass() != paramObject.getClass()) {
          return false;
        }
        paramObject = (ActivityResolveInfo)paramObject;
      } while (Float.floatToIntBits(weight) == Float.floatToIntBits(weight));
      return false;
    }
    
    public int hashCode()
    {
      return Float.floatToIntBits(weight) + 31;
    }
    
    public String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("[");
      localStringBuilder.append("resolveInfo:").append(resolveInfo.toString());
      localStringBuilder.append("; weight:").append(new BigDecimal(weight));
      localStringBuilder.append("]");
      return localStringBuilder.toString();
    }
  }
  
  public static abstract interface ActivitySorter
  {
    public abstract void sort(Intent paramIntent, List<ActivityChooserModel.ActivityResolveInfo> paramList, List<ActivityChooserModel.HistoricalRecord> paramList1);
  }
  
  final class DefaultSorter
    implements ActivityChooserModel.ActivitySorter
  {
    private static final float WEIGHT_DECAY_COEFFICIENT = 0.95F;
    private final Map<String, ActivityChooserModel.ActivityResolveInfo> mPackageNameToActivityMap = new HashMap();
    
    private DefaultSorter() {}
    
    public void sort(Intent paramIntent, List<ActivityChooserModel.ActivityResolveInfo> paramList, List<ActivityChooserModel.HistoricalRecord> paramList1)
    {
      paramIntent = mPackageNameToActivityMap;
      paramIntent.clear();
      int j = paramList.size();
      int i = 0;
      Object localObject;
      while (i < j)
      {
        localObject = (ActivityChooserModel.ActivityResolveInfo)paramList.get(i);
        weight = 0.0F;
        paramIntent.put(resolveInfo.activityInfo.packageName, localObject);
        i += 1;
      }
      i = paramList1.size();
      float f1 = 1.0F;
      i -= 1;
      if (i >= 0)
      {
        localObject = (ActivityChooserModel.HistoricalRecord)paramList1.get(i);
        ActivityChooserModel.ActivityResolveInfo localActivityResolveInfo = (ActivityChooserModel.ActivityResolveInfo)paramIntent.get(activity.getPackageName());
        if (localActivityResolveInfo == null) {
          break label180;
        }
        float f2 = weight;
        weight = (weight * f1 + f2);
        f1 = 0.95F * f1;
      }
      label180:
      for (;;)
      {
        i -= 1;
        break;
        Collections.sort(paramList);
        return;
      }
    }
  }
  
  public static final class HistoricalRecord
  {
    public final ComponentName activity;
    public final long time;
    public final float weight;
    
    public HistoricalRecord(ComponentName paramComponentName, long paramLong, float paramFloat)
    {
      activity = paramComponentName;
      time = paramLong;
      weight = paramFloat;
    }
    
    public HistoricalRecord(String paramString, long paramLong, float paramFloat)
    {
      this(ComponentName.unflattenFromString(paramString), paramLong, paramFloat);
    }
    
    public boolean equals(Object paramObject)
    {
      if (this == paramObject) {}
      do
      {
        return true;
        if (paramObject == null) {
          return false;
        }
        if (getClass() != paramObject.getClass()) {
          return false;
        }
        paramObject = (HistoricalRecord)paramObject;
        if (activity == null)
        {
          if (activity != null) {
            return false;
          }
        }
        else if (!activity.equals(activity)) {
          return false;
        }
        if (time != time) {
          return false;
        }
      } while (Float.floatToIntBits(weight) == Float.floatToIntBits(weight));
      return false;
    }
    
    public int hashCode()
    {
      if (activity == null) {}
      for (int i = 0;; i = activity.hashCode()) {
        return ((i + 31) * 31 + (int)(time ^ time >>> 32)) * 31 + Float.floatToIntBits(weight);
      }
    }
    
    public String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("[");
      localStringBuilder.append("; activity:").append(activity);
      localStringBuilder.append("; time:").append(time);
      localStringBuilder.append("; weight:").append(new BigDecimal(weight));
      localStringBuilder.append("]");
      return localStringBuilder.toString();
    }
  }
  
  public static abstract interface OnChooseActivityListener
  {
    public abstract boolean onChooseActivity(ActivityChooserModel paramActivityChooserModel, Intent paramIntent);
  }
  
  final class PersistHistoryAsyncTask
    extends AsyncTask<Object, Void, Void>
  {
    private PersistHistoryAsyncTask() {}
    
    public Void doInBackground(Object... paramVarArgs)
    {
      int i = 0;
      List localList = (List)paramVarArgs[0];
      Object localObject2 = (String)paramVarArgs[1];
      for (;;)
      {
        try
        {
          paramVarArgs = mContext.openFileOutput((String)localObject2, 0);
          localObject2 = Xml.newSerializer();
          int j;
          ActivityChooserModel.HistoricalRecord localHistoricalRecord;
          ((XmlSerializer)localObject2).endTag(null, "historical-records");
        }
        catch (FileNotFoundException paramVarArgs)
        {
          try
          {
            ((XmlSerializer)localObject2).setOutput(paramVarArgs, null);
            ((XmlSerializer)localObject2).startDocument("UTF-8", Boolean.valueOf(true));
            ((XmlSerializer)localObject2).startTag(null, "historical-records");
            j = localList.size();
            if (i >= j) {
              break label213;
            }
            localHistoricalRecord = (ActivityChooserModel.HistoricalRecord)localList.remove(0);
            ((XmlSerializer)localObject2).startTag(null, "historical-record");
            ((XmlSerializer)localObject2).attribute(null, "activity", activity.flattenToString());
            ((XmlSerializer)localObject2).attribute(null, "time", String.valueOf(time));
            ((XmlSerializer)localObject2).attribute(null, "weight", String.valueOf(weight));
            ((XmlSerializer)localObject2).endTag(null, "historical-record");
            i += 1;
            continue;
            paramVarArgs = paramVarArgs;
            Log.e(ActivityChooserModel.LOG_TAG, "Error writing historical recrod file: " + (String)localObject2, paramVarArgs);
          }
          catch (IllegalArgumentException localIllegalArgumentException)
          {
            Log.e(ActivityChooserModel.LOG_TAG, "Error writing historical recrod file: " + mHistoryFileName, localIllegalArgumentException);
            ActivityChooserModel.access$502(ActivityChooserModel.this, true);
            if (paramVarArgs == null) {
              continue;
            }
            try
            {
              paramVarArgs.close();
              return null;
            }
            catch (IOException paramVarArgs)
            {
              return null;
            }
          }
          catch (IllegalStateException localIllegalStateException)
          {
            Log.e(ActivityChooserModel.LOG_TAG, "Error writing historical recrod file: " + mHistoryFileName, localIllegalStateException);
            ActivityChooserModel.access$502(ActivityChooserModel.this, true);
            if (paramVarArgs == null) {
              continue;
            }
            try
            {
              paramVarArgs.close();
              return null;
            }
            catch (IOException paramVarArgs)
            {
              return null;
            }
          }
          catch (IOException localIOException)
          {
            Log.e(ActivityChooserModel.LOG_TAG, "Error writing historical recrod file: " + mHistoryFileName, localIOException);
            ActivityChooserModel.access$502(ActivityChooserModel.this, true);
            if (paramVarArgs == null) {
              continue;
            }
            try
            {
              paramVarArgs.close();
              return null;
            }
            catch (IOException paramVarArgs)
            {
              return null;
            }
          }
          finally
          {
            ActivityChooserModel.access$502(ActivityChooserModel.this, true);
            if (paramVarArgs == null) {
              break label446;
            }
          }
          return null;
        }
        label213:
        ((XmlSerializer)localObject2).endDocument();
        ActivityChooserModel.access$502(ActivityChooserModel.this, true);
        if (paramVarArgs != null) {
          try
          {
            paramVarArgs.close();
            return null;
          }
          catch (IOException paramVarArgs)
          {
            return null;
          }
        }
      }
      try
      {
        paramVarArgs.close();
        label446:
        throw ((Throwable)localObject1);
      }
      catch (IOException paramVarArgs)
      {
        for (;;) {}
      }
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v7.internal.widget.ActivityChooserModel
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */