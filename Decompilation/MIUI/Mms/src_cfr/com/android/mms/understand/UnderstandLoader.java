/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.os.Handler
 *  android.os.Handler$Callback
 *  android.os.HandlerThread
 *  android.os.Looper
 *  android.os.Message
 *  android.text.TextUtils
 *  android.util.Log
 *  java.lang.Object
 *  java.lang.String
 *  java.util.HashMap
 *  miui.telephony.PhoneNumberUtils
 *  miui.telephony.PhoneNumberUtils$PhoneNumber
 */
package com.android.mms.understand;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import com.android.mms.MmsApp;
import com.android.mms.ui.MessageUtils;
import com.android.mms.understand.TemplateUpdate;
import com.android.mms.understand.UnderstandFactory;
import java.util.HashMap;
import java.util.Map;
import miui.telephony.PhoneNumberUtils;

public class UnderstandLoader {
    private static UnderstandLoader sInstance;
    private Handler.Callback mCallback;
    private Handler mHandler;
    private LoaderThread mLoaderThread;
    private TemplateUpdate.UpdatePatch mPatcher;
    private final Map<Object, RequestCallback> mPendingMap = new HashMap();

    private UnderstandLoader() {
        this.mCallback = new Handler.Callback(){

            /*
             * Enabled aggressive block sorting
             */
            public boolean handleMessage(Message message) {
                switch (message.what) {
                    default: {
                        return true;
                    }
                    case 21: {
                        if (!UnderstandLoader.this.mPendingMap.containsKey(message.obj)) return true;
                        ((RequestCallback)UnderstandLoader.this.mPendingMap.get(message.obj)).onRequestDone(true);
                        Log.v((String)"UnderstandLoader", (String)"loading number resource done");
                        return true;
                    }
                }
            }
        };
        this.mHandler = new Handler(Looper.getMainLooper(), this.mCallback);
    }

    static /* synthetic */ TemplateUpdate.UpdatePatch access$100(UnderstandLoader understandLoader) {
        return understandLoader.mPatcher;
    }

    public static void destroy() {
        UnderstandLoader.getInstance().requestDestroy();
    }

    public static void destroy(String string2, RequestCallback requestCallback) {
        UnderstandLoader.getInstance().requestDestroy(string2, requestCallback);
    }

    public static UnderstandLoader getInstance() {
        if (sInstance == null) {
            sInstance = new UnderstandLoader();
        }
        return sInstance;
    }

    private LoaderThread getLoaderThread() {
        if (this.mLoaderThread == null) {
            this.mLoaderThread = new LoaderThread();
            this.mLoaderThread.setPriority(1);
            this.mLoaderThread.start();
        }
        return this.mLoaderThread;
    }

    public static void init() {
        UnderstandLoader.getInstance().requestInit();
    }

    public static void prepare() {
        UnderstandLoader.getInstance().requestInitFiles();
    }

    public static void rePrepare(TemplateUpdate.UpdatePatch updatePatch) {
        UnderstandLoader.getInstance().requestUpdateFiles(updatePatch);
    }

    public static void request(String string2, RequestCallback requestCallback) {
        UnderstandLoader.getInstance().requestLoading(string2, requestCallback);
    }

    public static void update() {
        UnderstandLoader.getInstance().requestUpdate();
    }

    public void requestDestroy() {
        this.getLoaderThread().request(6);
    }

    /*
     * Enabled aggressive block sorting
     */
    public void requestDestroy(String string2, RequestCallback requestCallback) {
        if (TextUtils.isEmpty((CharSequence)string2) || requestCallback == null) {
            return;
        }
        if (this.mPendingMap.containsValue(requestCallback)) {
            this.mPendingMap.remove(string2);
            this.getLoaderThread().requestDestroy(string2);
            return;
        }
        Log.w((String)"UnderstandLoader", (String)" callback was already replaced, ignore. ");
    }

    public void requestInit() {
        this.getLoaderThread().request(4);
    }

    public void requestInitFiles() {
        this.getLoaderThread().request(1);
    }

    public void requestLoading(String string2, RequestCallback requestCallback) {
        if (!TextUtils.isEmpty((CharSequence)string2)) {
            this.mPendingMap.put(string2, requestCallback);
            this.getLoaderThread().requestLoad(string2);
        }
    }

    public void requestUpdate() {
        this.getLoaderThread().request(5);
    }

    public void requestUpdateFiles(TemplateUpdate.UpdatePatch updatePatch) {
        this.mPatcher = updatePatch;
        this.getLoaderThread().request(2);
    }

    private class LoaderThread
    extends HandlerThread
    implements Handler.Callback {
        private Handler mLoaderThreadHandler;

        public LoaderThread() {
            super("UnderstandLoader");
        }

        /*
         * Enabled aggressive block sorting
         */
        public boolean handleMessage(Message object) {
            Log.v((String)"UnderstandLoader", (String)(" handleMessage: " + object.what));
            switch (object.what) {
                default: {
                    Log.e((String)"UnderstandLoader", (String)("unknown action for load thread with what is " + object.what));
                    return true;
                }
                case 1: {
                    UnderstandFactory.initUnderstandFiles();
                    return true;
                }
                case 2: {
                    if (!UnderstandFactory.unzipFiles()) return true;
                    UnderstandFactory.setInitialized(false);
                    UnderstandFactory.updateVersion(UnderstandLoader.access$100((UnderstandLoader)UnderstandLoader.this).mVersion);
                    this.mLoaderThreadHandler.sendEmptyMessage(5);
                    return true;
                }
                case 5: {
                    this.mLoaderThreadHandler.removeMessages(5);
                    UnderstandFactory.reStartInitUnderstand();
                    return true;
                }
                case 4: {
                    UnderstandFactory.initUnderstand();
                    this.mLoaderThreadHandler.sendEmptyMessage(7);
                    return true;
                }
                case 3: {
                    object = (String)object.obj;
                    UnderstandFactory.loadResourceForResident((String)object, "");
                    Message message = Message.obtain();
                    message.what = 21;
                    message.obj = object;
                    UnderstandLoader.this.mHandler.sendMessage(message);
                    return true;
                }
                case 6: {
                    UnderstandFactory.freeAllResourcesForResident();
                    UnderstandLoader.this.mHandler.removeCallbacksAndMessages((Object)null);
                    return true;
                }
                case 8: {
                    UnderstandFactory.freeResourceForResident((String)object.obj, null);
                    return true;
                }
                case 7: {
                    Object object2;
                    object = MessageUtils.getPhoneNumbers((Context)MmsApp.getApp());
                    if (object.size() > 0) {
                        UnderstandFactory.setLocalHostNumber((String)object.get(0), 0);
                        object2 = PhoneNumberUtils.PhoneNumber.parse((CharSequence)((CharSequence)object.get(0)));
                        String string2 = object2.getLocation((Context)MmsApp.getApp());
                        object2.recycle();
                        if (!TextUtils.isEmpty((CharSequence)string2)) {
                            UnderstandFactory.setLocalHostPlace(string2, 0);
                        }
                    }
                    if (object.size() <= 1) return true;
                    UnderstandFactory.setLocalHostNumber((String)object.get(1), 1);
                    object = PhoneNumberUtils.PhoneNumber.parse((CharSequence)((CharSequence)object.get(1)));
                    object2 = object.getLocation((Context)MmsApp.getApp());
                    object.recycle();
                    if (TextUtils.isEmpty((CharSequence)object2)) return true;
                    UnderstandFactory.setLocalHostPlace((String)object2, 1);
                    return true;
                }
            }
        }

        public void request(int n) {
            if (this.mLoaderThreadHandler == null) {
                this.mLoaderThreadHandler = new Handler(this.getLooper(), (Handler.Callback)this);
            }
            this.mLoaderThreadHandler.sendEmptyMessage(n);
        }

        public void requestDestroy(String string2) {
            if (this.mLoaderThreadHandler == null) {
                this.mLoaderThreadHandler = new Handler(this.getLooper(), (Handler.Callback)this);
            }
            Message message = Message.obtain();
            message.what = 8;
            message.obj = string2;
            this.mLoaderThreadHandler.sendMessage(message);
        }

        public void requestLoad(String string2) {
            if (this.mLoaderThreadHandler == null) {
                this.mLoaderThreadHandler = new Handler(this.getLooper(), (Handler.Callback)this);
            }
            Message message = Message.obtain();
            message.what = 3;
            message.obj = string2;
            this.mLoaderThreadHandler.sendMessage(message);
        }
    }

    public static interface RequestCallback {
        public void onRequestDone(boolean var1);
    }

}

