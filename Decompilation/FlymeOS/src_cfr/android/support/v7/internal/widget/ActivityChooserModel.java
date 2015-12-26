/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ComponentName
 *  android.content.Context
 *  android.content.Intent
 *  android.content.pm.ActivityInfo
 *  android.content.pm.PackageManager
 *  android.content.pm.ResolveInfo
 *  android.database.DataSetObservable
 *  android.os.AsyncTask
 *  android.text.TextUtils
 *  android.util.Log
 *  android.util.Xml
 *  java.io.FileInputStream
 *  java.io.FileNotFoundException
 *  java.lang.Class
 *  java.lang.Float
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.lang.Throwable
 *  java.math.BigDecimal
 *  java.util.ArrayList
 *  java.util.Collections
 *  java.util.HashMap
 *  org.xmlpull.v1.XmlPullParser
 *  org.xmlpull.v1.XmlPullParserException
 */
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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class ActivityChooserModel
extends DataSetObservable {
    private static final String ATTRIBUTE_ACTIVITY = "activity";
    private static final String ATTRIBUTE_TIME = "time";
    private static final String ATTRIBUTE_WEIGHT = "weight";
    private static final boolean DEBUG = false;
    private static final int DEFAULT_ACTIVITY_INFLATION = 5;
    private static final float DEFAULT_HISTORICAL_RECORD_WEIGHT = 1.0f;
    public static final String DEFAULT_HISTORY_FILE_NAME = "activity_choser_model_history.xml";
    public static final int DEFAULT_HISTORY_MAX_LENGTH = 50;
    private static final String HISTORY_FILE_EXTENSION = ".xml";
    private static final int INVALID_INDEX = -1;
    private static final String LOG_TAG = ActivityChooserModel.class.getSimpleName();
    private static final String TAG_HISTORICAL_RECORD = "historical-record";
    private static final String TAG_HISTORICAL_RECORDS = "historical-records";
    private static final Map<String, ActivityChooserModel> sDataModelRegistry;
    private static final Object sRegistryLock;
    private final List<ActivityResolveInfo> mActivities = new ArrayList();
    private OnChooseActivityListener mActivityChoserModelPolicy;
    private ActivitySorter mActivitySorter;
    private boolean mCanReadHistoricalData;
    private final Context mContext;
    private final List<HistoricalRecord> mHistoricalRecords = new ArrayList();
    private boolean mHistoricalRecordsChanged;
    private final String mHistoryFileName;
    private int mHistoryMaxSize;
    private final Object mInstanceLock = new Object();
    private Intent mIntent;
    private boolean mReadShareHistoryCalled;
    private boolean mReloadActivities;

    static {
        sRegistryLock = new Object();
        sDataModelRegistry = new HashMap();
    }

    private ActivityChooserModel(Context context, String string2) {
        this.mActivitySorter = new DefaultSorter();
        this.mHistoryMaxSize = 50;
        this.mCanReadHistoricalData = true;
        this.mReadShareHistoryCalled = false;
        this.mHistoricalRecordsChanged = true;
        this.mReloadActivities = false;
        this.mContext = context.getApplicationContext();
        if (!TextUtils.isEmpty((CharSequence)string2) && !string2.endsWith(".xml")) {
            this.mHistoryFileName = string2 + ".xml";
            return;
        }
        this.mHistoryFileName = string2;
    }

    static /* synthetic */ Context access$200(ActivityChooserModel activityChooserModel) {
        return activityChooserModel.mContext;
    }

    static /* synthetic */ String access$300() {
        return LOG_TAG;
    }

    static /* synthetic */ String access$400(ActivityChooserModel activityChooserModel) {
        return activityChooserModel.mHistoryFileName;
    }

    static /* synthetic */ boolean access$502(ActivityChooserModel activityChooserModel, boolean bl2) {
        activityChooserModel.mCanReadHistoricalData = bl2;
        return bl2;
    }

    private boolean addHisoricalRecord(HistoricalRecord historicalRecord) {
        boolean bl2 = this.mHistoricalRecords.add(historicalRecord);
        if (bl2) {
            this.mHistoricalRecordsChanged = true;
            this.pruneExcessiveHistoricalRecordsIfNeeded();
            this.persistHistoricalDataIfNeeded();
            this.sortActivitiesIfNeeded();
            this.notifyChanged();
        }
        return bl2;
    }

    private void ensureConsistentState() {
        boolean bl2 = this.loadActivitiesIfNeeded();
        boolean bl3 = this.readHistoricalDataIfNeeded();
        this.pruneExcessiveHistoricalRecordsIfNeeded();
        if (bl2 | bl3) {
            this.sortActivitiesIfNeeded();
            this.notifyChanged();
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static ActivityChooserModel get(Context context, String string2) {
        Object object = sRegistryLock;
        synchronized (object) {
            ActivityChooserModel activityChooserModel;
            ActivityChooserModel activityChooserModel2 = activityChooserModel = sDataModelRegistry.get(string2);
            if (activityChooserModel == null) {
                activityChooserModel2 = new ActivityChooserModel(context, string2);
                sDataModelRegistry.put(string2, activityChooserModel2);
            }
            return activityChooserModel2;
        }
    }

    private boolean loadActivitiesIfNeeded() {
        boolean bl2;
        boolean bl3 = bl2 = false;
        if (this.mReloadActivities) {
            bl3 = bl2;
            if (this.mIntent != null) {
                this.mReloadActivities = false;
                this.mActivities.clear();
                List list = this.mContext.getPackageManager().queryIntentActivities(this.mIntent, 0);
                int n2 = list.size();
                for (int i2 = 0; i2 < n2; ++i2) {
                    ResolveInfo resolveInfo = (ResolveInfo)list.get(i2);
                    this.mActivities.add(new ActivityResolveInfo(resolveInfo));
                }
                bl3 = true;
            }
        }
        return bl3;
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private void persistHistoricalDataIfNeeded() {
        if (!this.mReadShareHistoryCalled) {
            throw new IllegalStateException("No preceding call to #readHistoricalData");
        }
        if (!this.mHistoricalRecordsChanged) {
            return;
        }
        this.mHistoricalRecordsChanged = false;
        if (TextUtils.isEmpty((CharSequence)this.mHistoryFileName)) return;
        AsyncTaskCompat.executeParallel(new PersistHistoryAsyncTask(), this.mHistoricalRecords, this.mHistoryFileName);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private void pruneExcessiveHistoricalRecordsIfNeeded() {
        int n2 = this.mHistoricalRecords.size() - this.mHistoryMaxSize;
        if (n2 <= 0) {
            return;
        }
        this.mHistoricalRecordsChanged = true;
        int n3 = 0;
        while (n3 < n2) {
            HistoricalRecord historicalRecord = this.mHistoricalRecords.remove(0);
            ++n3;
        }
    }

    private boolean readHistoricalDataIfNeeded() {
        if (this.mCanReadHistoricalData && this.mHistoricalRecordsChanged && !TextUtils.isEmpty((CharSequence)this.mHistoryFileName)) {
            this.mCanReadHistoricalData = false;
            this.mReadShareHistoryCalled = true;
            this.readHistoricalDataImpl();
            return true;
        }
        return false;
    }

    /*
     * WARNING - Removed back jump from a try to a catch block - possible behaviour change.
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    private void readHistoricalDataImpl() {
        var2_1 = this.mContext.openFileInput(this.mHistoryFileName);
        try {
            var3_7 = Xml.newPullParser();
            var3_7.setInput((InputStream)var2_1, null);
            var1_11 = 0;
            while (var1_11 != 1 && var1_11 != 2) {
                var1_11 = var3_7.next();
            }
            if (!"historical-records".equals((Object)var3_7.getName())) {
                throw new XmlPullParserException("Share records file does not start with historical-records tag.");
            }
            var4_12 = this.mHistoricalRecords;
            var4_12.clear();
            ** GOTO lbl26
            catch (FileNotFoundException var2_6) {
                return;
            }
            {
                catch (XmlPullParserException var3_8) {
                    Log.e((String)ActivityChooserModel.LOG_TAG, (String)("Error reading historical recrod file: " + this.mHistoryFileName), (Throwable)var3_8);
                    if (var2_1 == null) return;
                    try {
                        var2_1.close();
                        return;
                    }
                    catch (IOException var2_4) {
                        return;
                    }
                }
lbl26: // 1 sources:
                do {
                    if ((var1_11 = var3_7.next()) == 1) {
                        if (var2_1 == null) return;
                        try {
                            var2_1.close();
                            return;
                        }
                        catch (IOException var2_2) {
                            return;
                        }
                    }
                    if (var1_11 == 3 || var1_11 == 4) continue;
                    ** try [egrp 6[TRYBLOCK] [21 : 158->215)] { 
lbl37: // 2 sources:
                    if ("historical-record".equals((Object)var3_7.getName())) ** break block23
                    throw new XmlPullParserException("Share records file not well-formed.");
                    break;
                } while (true);
                catch (IOException var3_9) {
                    Log.e((String)ActivityChooserModel.LOG_TAG, (String)("Error reading historical recrod file: " + this.mHistoryFileName), (Throwable)var3_9);
                    if (var2_1 == null) return;
                    try {
                        var2_1.close();
                        return;
                    }
                    catch (IOException var2_3) {
                        return;
                    }
                }
                {
                    
                    var4_12.add(new HistoricalRecord(var3_7.getAttributeValue(null, "activity"), Long.parseLong((String)var3_7.getAttributeValue(null, "time")), Float.parseFloat((String)var3_7.getAttributeValue(null, "weight"))));
                    continue;
                }
            }
        }
lbl51: // 3 sources:
        catch (Throwable var3_10) {
            if (var2_1 == null) throw var3_10;
            try {
                var2_1.close();
            }
            catch (IOException var2_5) {
                throw var3_10;
            }
            throw var3_10;
        }
    }

    private boolean sortActivitiesIfNeeded() {
        if (this.mActivitySorter != null && this.mIntent != null && !this.mActivities.isEmpty() && !this.mHistoricalRecords.isEmpty()) {
            this.mActivitySorter.sort(this.mIntent, this.mActivities, Collections.unmodifiableList(this.mHistoricalRecords));
            return true;
        }
        return false;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public Intent chooseActivity(int n2) {
        Object object = this.mInstanceLock;
        synchronized (object) {
            Intent intent;
            if (this.mIntent == null) {
                return null;
            }
            this.ensureConsistentState();
            ActivityResolveInfo activityResolveInfo = this.mActivities.get(n2);
            activityResolveInfo = new ComponentName(activityResolveInfo.resolveInfo.activityInfo.packageName, activityResolveInfo.resolveInfo.activityInfo.name);
            Intent intent2 = new Intent(this.mIntent);
            intent2.setComponent((ComponentName)activityResolveInfo);
            if (this.mActivityChoserModelPolicy != null && this.mActivityChoserModelPolicy.onChooseActivity(this, intent = new Intent(intent2))) {
                return null;
            }
            this.addHisoricalRecord(new HistoricalRecord((ComponentName)activityResolveInfo, System.currentTimeMillis(), 1.0f));
            return intent2;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public ResolveInfo getActivity(int n2) {
        Object object = this.mInstanceLock;
        synchronized (object) {
            this.ensureConsistentState();
            return this.mActivities.get((int)n2).resolveInfo;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public int getActivityCount() {
        Object object = this.mInstanceLock;
        synchronized (object) {
            this.ensureConsistentState();
            return this.mActivities.size();
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public int getActivityIndex(ResolveInfo resolveInfo) {
        Object object = this.mInstanceLock;
        synchronized (object) {
            this.ensureConsistentState();
            List<ActivityResolveInfo> list = this.mActivities;
            int n2 = list.size();
            int n3 = 0;
            while (n3 < n2) {
                if (list.get((int)n3).resolveInfo == resolveInfo) {
                    return n3;
                }
                ++n3;
            }
            return -1;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public ResolveInfo getDefaultActivity() {
        Object object = this.mInstanceLock;
        synchronized (object) {
            this.ensureConsistentState();
            if (this.mActivities.isEmpty()) return null;
            return this.mActivities.get((int)0).resolveInfo;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public int getHistoryMaxSize() {
        Object object = this.mInstanceLock;
        synchronized (object) {
            return this.mHistoryMaxSize;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public int getHistorySize() {
        Object object = this.mInstanceLock;
        synchronized (object) {
            this.ensureConsistentState();
            return this.mHistoricalRecords.size();
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public Intent getIntent() {
        Object object = this.mInstanceLock;
        synchronized (object) {
            return this.mIntent;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void setActivitySorter(ActivitySorter activitySorter) {
        Object object = this.mInstanceLock;
        synchronized (object) {
            if (this.mActivitySorter == activitySorter) {
                return;
            }
            this.mActivitySorter = activitySorter;
            if (this.sortActivitiesIfNeeded()) {
                this.notifyChanged();
            }
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void setDefaultActivity(int n2) {
        Object object = this.mInstanceLock;
        synchronized (object) {
            this.ensureConsistentState();
            ActivityResolveInfo activityResolveInfo = this.mActivities.get(n2);
            ActivityResolveInfo activityResolveInfo2 = this.mActivities.get(0);
            float f2 = activityResolveInfo2 != null ? activityResolveInfo2.weight - activityResolveInfo.weight + 5.0f : 1.0f;
            this.addHisoricalRecord(new HistoricalRecord(new ComponentName(activityResolveInfo.resolveInfo.activityInfo.packageName, activityResolveInfo.resolveInfo.activityInfo.name), System.currentTimeMillis(), f2));
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void setHistoryMaxSize(int n2) {
        Object object = this.mInstanceLock;
        synchronized (object) {
            if (this.mHistoryMaxSize == n2) {
                return;
            }
            this.mHistoryMaxSize = n2;
            this.pruneExcessiveHistoricalRecordsIfNeeded();
            if (this.sortActivitiesIfNeeded()) {
                this.notifyChanged();
            }
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void setIntent(Intent intent) {
        Object object = this.mInstanceLock;
        synchronized (object) {
            if (this.mIntent == intent) {
                return;
            }
            this.mIntent = intent;
            this.mReloadActivities = true;
            this.ensureConsistentState();
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void setOnChooseActivityListener(OnChooseActivityListener onChooseActivityListener) {
        Object object = this.mInstanceLock;
        synchronized (object) {
            this.mActivityChoserModelPolicy = onChooseActivityListener;
            return;
        }
    }

    public static interface ActivityChooserModelClient {
        public void setActivityChooserModel(ActivityChooserModel var1);
    }

    public final class ActivityResolveInfo
    implements Comparable<ActivityResolveInfo> {
        public final ResolveInfo resolveInfo;
        public float weight;

        public ActivityResolveInfo(ResolveInfo resolveInfo) {
            this.resolveInfo = resolveInfo;
        }

        @Override
        public int compareTo(ActivityResolveInfo activityResolveInfo) {
            return Float.floatToIntBits((float)activityResolveInfo.weight) - Float.floatToIntBits((float)this.weight);
        }

        /*
         * Enabled aggressive block sorting
         * Lifted jumps to return sites
         */
        public boolean equals(Object object) {
            if (this == object) {
                return true;
            }
            if (object == null) {
                return false;
            }
            if (this.getClass() != object.getClass()) {
                return false;
            }
            object = (ActivityResolveInfo)object;
            if (Float.floatToIntBits((float)this.weight) == Float.floatToIntBits((float)object.weight)) return true;
            return false;
        }

        public int hashCode() {
            return Float.floatToIntBits((float)this.weight) + 31;
        }

        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("[");
            stringBuilder.append("resolveInfo:").append(this.resolveInfo.toString());
            stringBuilder.append("; weight:").append((Object)new BigDecimal((double)this.weight));
            stringBuilder.append("]");
            return stringBuilder.toString();
        }
    }

    public static interface ActivitySorter {
        public void sort(Intent var1, List<ActivityResolveInfo> var2, List<HistoricalRecord> var3);
    }

    final class DefaultSorter
    implements ActivitySorter {
        private static final float WEIGHT_DECAY_COEFFICIENT = 0.95f;
        private final Map<String, ActivityResolveInfo> mPackageNameToActivityMap;

        private DefaultSorter() {
            this.mPackageNameToActivityMap = new HashMap();
        }

        @Override
        public void sort(Intent object, List<ActivityResolveInfo> list, List<HistoricalRecord> list2) {
            Object object2;
            int n2;
            object = this.mPackageNameToActivityMap;
            object.clear();
            int n3 = list.size();
            for (n2 = 0; n2 < n3; ++n2) {
                object2 = list.get(n2);
                object2.weight = 0.0f;
                object.put(object2.resolveInfo.activityInfo.packageName, object2);
            }
            n2 = list2.size();
            float f2 = 1.0f;
            --n2;
            while (n2 >= 0) {
                object2 = list2.get(n2);
                ActivityResolveInfo activityResolveInfo = (ActivityResolveInfo)object.get(object2.activity.getPackageName());
                if (activityResolveInfo != null) {
                    float f3 = activityResolveInfo.weight;
                    activityResolveInfo.weight = object2.weight * f2 + f3;
                    f2 = 0.95f * f2;
                }
                --n2;
            }
            Collections.sort(list);
        }
    }

    public static final class HistoricalRecord {
        public final ComponentName activity;
        public final long time;
        public final float weight;

        public HistoricalRecord(ComponentName componentName, long l2, float f2) {
            this.activity = componentName;
            this.time = l2;
            this.weight = f2;
        }

        public HistoricalRecord(String string2, long l2, float f2) {
            this(ComponentName.unflattenFromString((String)string2), l2, f2);
        }

        /*
         * Enabled aggressive block sorting
         * Lifted jumps to return sites
         */
        public boolean equals(Object object) {
            if (this == object) {
                return true;
            }
            if (object == null) {
                return false;
            }
            if (this.getClass() != object.getClass()) {
                return false;
            }
            object = (HistoricalRecord)object;
            if (this.activity == null ? object.activity != null : !this.activity.equals((Object)object.activity)) {
                return false;
            }
            if (this.time != object.time) {
                return false;
            }
            if (Float.floatToIntBits((float)this.weight) == Float.floatToIntBits((float)object.weight)) return true;
            return false;
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        public int hashCode() {
            int n2;
            if (this.activity == null) {
                n2 = 0;
                do {
                    return ((n2 + 31) * 31 + (int)(this.time ^ this.time >>> 32)) * 31 + Float.floatToIntBits((float)this.weight);
                    break;
                } while (true);
            }
            n2 = this.activity.hashCode();
            return ((n2 + 31) * 31 + (int)(this.time ^ this.time >>> 32)) * 31 + Float.floatToIntBits((float)this.weight);
        }

        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("[");
            stringBuilder.append("; activity:").append((Object)this.activity);
            stringBuilder.append("; time:").append(this.time);
            stringBuilder.append("; weight:").append((Object)new BigDecimal((double)this.weight));
            stringBuilder.append("]");
            return stringBuilder.toString();
        }
    }

    public static interface OnChooseActivityListener {
        public boolean onChooseActivity(ActivityChooserModel var1, Intent var2);
    }

    final class PersistHistoryAsyncTask
    extends AsyncTask<Object, Void, Void> {
        private PersistHistoryAsyncTask() {
        }

        /*
         * Exception decompiling
         */
        public /* varargs */ Void doInBackground(Object ... var1_1) {
            // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
            // org.benf.cfr.reader.util.ConfusedCFRException: Started 2 blocks at once
            // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.getStartingBlocks(Op04StructuredStatement.java:371)
            // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:449)
            // org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:2859)
            // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:805)
            // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:220)
            // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:165)
            // org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:91)
            // org.benf.cfr.reader.entities.Method.analyse(Method.java:354)
            // org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:751)
            // org.benf.cfr.reader.entities.ClassFile.analyseInnerClassesPass1(ClassFile.java:664)
            // org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:747)
            // org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:683)
            // org.benf.cfr.reader.Main.doJar(Main.java:128)
            // org.benf.cfr.reader.Main.main(Main.java:178)
            throw new IllegalStateException("Decompilation failed");
        }
    }

}

