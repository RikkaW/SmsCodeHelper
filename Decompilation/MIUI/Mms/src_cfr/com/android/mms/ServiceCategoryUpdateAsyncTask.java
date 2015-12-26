/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ContentProviderOperation
 *  android.content.ContentProviderOperation$Builder
 *  android.content.ContentProviderResult
 *  android.content.ContentResolver
 *  android.content.ContentValues
 *  android.content.Context
 *  android.content.SharedPreferences
 *  android.content.SharedPreferences$Editor
 *  android.database.Cursor
 *  android.net.Uri
 *  android.net.Uri$Builder
 *  android.os.AsyncTask
 *  android.preference.PreferenceManager
 *  android.provider.Telephony
 *  android.provider.Telephony$Mms
 *  android.provider.Telephony$MmsSms
 *  android.provider.Telephony$Sms
 *  android.provider.Telephony$Threads
 *  android.util.Log
 *  java.lang.Boolean
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.util.ArrayList
 *  java.util.HashSet
 *  miui.telephony.phonenumber.CountryCode
 */
package com.android.mms;

import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.provider.Telephony;
import android.util.Log;
import com.android.mms.LogTag;
import com.android.mms.MmsApp;
import com.android.mms.ui.MessageUtils;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import miui.telephony.phonenumber.CountryCode;

public class ServiceCategoryUpdateAsyncTask
extends AsyncTask<Void, Void, Boolean> {
    private static String TAG = "ServiceProviderUpdateAsyncTask";
    private static ServiceCategoryUpdateAsyncTask sUpdateTask;
    private Context mContext;
    private boolean mIsFirstUpdate;
    private Set<IServiceCategoryUpdateListener> mUpdateListeners = new HashSet(4);

    private ServiceCategoryUpdateAsyncTask(Context context, boolean bl, IServiceCategoryUpdateListener iServiceCategoryUpdateListener) {
        this.mContext = context;
        this.mIsFirstUpdate = bl;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private boolean addListener(IServiceCategoryUpdateListener iServiceCategoryUpdateListener) {
        synchronized (this) {
            if (this.mUpdateListeners.contains(iServiceCategoryUpdateListener)) return false;
            return this.mUpdateListeners.add(iServiceCategoryUpdateListener);
        }
    }

    private boolean removeListener(IServiceCategoryUpdateListener iServiceCategoryUpdateListener) {
        synchronized (this) {
            boolean bl = this.mUpdateListeners.remove(iServiceCategoryUpdateListener);
            return bl;
        }
    }

    public static boolean startUpdateAsyncTask(Context context, boolean bl, IServiceCategoryUpdateListener iServiceCategoryUpdateListener) {
        synchronized (ServiceCategoryUpdateAsyncTask.class) {
            block8 : {
                boolean bl2;
                block7 : {
                    boolean bl3;
                    bl2 = bl3 = false;
                    if (sUpdateTask != null) break block7;
                    bl2 = bl3;
                    if (!CountryCode.isChinaEnvironment()) break block7;
                    sUpdateTask = new ServiceCategoryUpdateAsyncTask(context, bl, iServiceCategoryUpdateListener);
                    bl2 = true;
                }
                if (sUpdateTask == null) break block8;
                sUpdateTask.addListener(iServiceCategoryUpdateListener);
                if (!bl2) break block8;
                sUpdateTask.execute((Object[])new Void[]{null});
            }
            return true;
            finally {
            }
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void unRegisterListener(IServiceCategoryUpdateListener iServiceCategoryUpdateListener) {
        if (sUpdateTask != null) {
            ServiceCategoryUpdateAsyncTask serviceCategoryUpdateAsyncTask = sUpdateTask;
            synchronized (serviceCategoryUpdateAsyncTask) {
                sUpdateTask.removeListener(iServiceCategoryUpdateListener);
                return;
            }
        }
    }

    private void updateAdvancedSeen(ContentResolver contentResolver) {
        Cursor cursor;
        block9 : {
            ContentValues contentValues;
            block8 : {
                cursor = contentResolver.query(Telephony.Sms.CONTENT_URI, new String[]{"count(*)"}, "(seen=1 AND advanced_seen=0)", null, null);
                if (cursor != null) {
                    if (!cursor.moveToFirst() || cursor.getLong(0) <= 0) break block8;
                    contentValues = new ContentValues(1);
                    contentValues.put("advanced_seen", Integer.valueOf((int)3));
                    contentResolver.update(Telephony.Sms.CONTENT_URI, contentValues, "(seen=1 AND advanced_seen=0)", null);
                }
            }
            if ((cursor = contentResolver.query(Telephony.Mms.CONTENT_URI, new String[]{"count(*)"}, "(seen=1 AND advanced_seen=0)", null, null)) != null) {
                if (!cursor.moveToFirst() || cursor.getLong(0) <= 0) break block9;
                contentValues = new ContentValues(1);
                contentValues.put("advanced_seen", Integer.valueOf((int)3));
                contentResolver.update(Telephony.Mms.CONTENT_URI, contentValues, "(seen=1 AND advanced_seen=0)", null);
            }
        }
        return;
        finally {
            cursor.close();
        }
        finally {
            cursor.close();
        }
    }

    protected /* varargs */ Boolean doInBackground(Void ... cursor) {
        ContentResolver contentResolver = this.mContext.getContentResolver();
        long l = System.currentTimeMillis();
        ArrayList arrayList = new ArrayList();
        Telephony.Threads.CONTENT_URI.buildUpon().appendQueryParameter("simple", "true").build();
        cursor = contentResolver.query(Uri.withAppendedPath((Uri)Telephony.MmsSms.CONTENT_URI, (String)"address-snippet"), null, null, null, null);
        if (cursor != null) {
            int n = cursor.getColumnIndex("address");
            int n2 = cursor.getColumnIndex("_id");
            int n3 = cursor.getColumnIndex("snippet");
            cursor.moveToPosition(-1);
            while (cursor.moveToNext()) {
                String string = cursor.getString(n);
                int n4 = cursor.getInt(n2);
                String string2 = cursor.getString(n3);
                int n5 = MessageUtils.getServiceCategory(this.mContext, string, string2);
                if (n5 == 0) continue;
                string = new ContentValues();
                string.put("sp_type", Integer.valueOf((int)n5));
                arrayList.add((Object)ContentProviderOperation.newUpdate((Uri)Telephony.Threads.CONTENT_URI).withSelection("_id=?", new String[]{String.valueOf((int)n4)}).withValues((ContentValues)string).build());
                n4 = arrayList.size();
                if (n4 < 50) continue;
                contentResolver.applyBatch("mms-sms", arrayList);
                arrayList.clear();
            }
        }
        try {
            if (!arrayList.isEmpty()) {
                contentResolver.applyBatch("mms-sms", arrayList);
            }
            if (this.mIsFirstUpdate) {
                this.updateAdvancedSeen(contentResolver);
            }
            return true;
        }
        catch (Exception var1_2) {
            LogTag.error(var1_2.getMessage(), new Object[0]);
            return false;
        }
        finally {
            long l2 = System.currentTimeMillis();
            Log.d((String)TAG, (String)("update existing threads: " + (l2 - l) + "ms"));
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    protected void onPostExecute(Boolean iterator) {
        if (iterator.booleanValue()) {
            iterator = PreferenceManager.getDefaultSharedPreferences((Context)MmsApp.getApp()).edit();
            iterator.putLong("pref_service_category_upadate_time", System.currentTimeMillis());
            iterator.commit();
            iterator = this.mUpdateListeners.iterator();
            while (iterator.hasNext()) {
                iterator.next().onUpdateSuccess();
            }
        } else {
            iterator = this.mUpdateListeners.iterator();
            while (iterator.hasNext()) {
                ((IServiceCategoryUpdateListener)iterator.next()).onUpdateSuccess();
            }
        }
        synchronized (this) {
            this.mUpdateListeners.clear();
            sUpdateTask = null;
        }
        this.mContext = null;
    }

    public static interface IServiceCategoryUpdateListener {
        public void onUpdateSuccess();
    }

}

