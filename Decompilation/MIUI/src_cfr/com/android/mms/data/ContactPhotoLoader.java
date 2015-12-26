/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ContentResolver
 *  android.content.Context
 *  android.content.res.Resources
 *  android.database.Cursor
 *  android.graphics.Bitmap
 *  android.graphics.BitmapFactory
 *  android.graphics.BitmapFactory$Options
 *  android.net.Uri
 *  android.os.Handler
 *  android.os.Handler$Callback
 *  android.os.HandlerThread
 *  android.os.Looper
 *  android.os.Message
 *  android.provider.ContactsContract
 *  android.provider.ContactsContract$Data
 *  android.provider.MiuiSettings
 *  android.provider.MiuiSettings$System
 *  android.widget.ImageView
 *  com.google.android.collect.Lists
 *  java.lang.Boolean
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.util.ArrayList
 *  java.util.concurrent.ConcurrentHashMap
 *  miui.graphics.BitmapFactory
 *  miui.yellowpage.ContactThumbnailProcessor
 *  miui.yellowpage.YellowPageImgLoader
 *  miui.yellowpage.YellowPageImgLoader$Image
 *  miui.yellowpage.YellowPageImgLoader$Image$ImageProcessor
 */
package com.android.mms.data;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.provider.ContactsContract;
import android.provider.MiuiSettings;
import android.widget.ImageView;
import com.android.mms.data.Contact;
import com.google.android.collect.Lists;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import miui.graphics.BitmapFactory;
import miui.yellowpage.ContactThumbnailProcessor;
import miui.yellowpage.YellowPageImgLoader;

public class ContactPhotoLoader
implements Handler.Callback {
    private static final String[] EMPTY_STRING_ARRAY = new String[0];
    private final String[] CONTACT_COLUMNS = new String[]{"_id", "data15"};
    private final ConcurrentHashMap<Long, BitmapHolder> mContactBitmapCache = new ConcurrentHashMap();
    private final ConcurrentHashMap<String, BitmapHolder> mContactNameBitmapCache = new ConcurrentHashMap();
    private final int mContactPhotoWidth;
    private final Context mContext;
    private final int mDefaultContactResourceId;
    private final int mDefaultSPResourceId;
    private LoaderThread mLoaderThread;
    private boolean mLoadingRequested;
    private final Handler mMainThreadHandler;
    private boolean mPaused;
    private final ConcurrentHashMap<ImageView, Contact> mPendingRequests = new ConcurrentHashMap();
    private ContactThumbnailProcessor mRoundImageProcessor;

    public ContactPhotoLoader(Context context, int n, int n2) {
        this.mMainThreadHandler = new Handler((Handler.Callback)this);
        this.mDefaultContactResourceId = n;
        this.mDefaultSPResourceId = n2;
        this.mRoundImageProcessor = new ContactThumbnailProcessor(context);
        this.mContext = context;
        this.mContactPhotoWidth = context.getResources().getDimensionPixelSize(2131427447);
    }

    private void cacheBitmap(long l, byte[] arrby) {
        if (!this.mPaused) {
            this.mContactBitmapCache.put((Object)l, (Object)this.createBitmapHolderFromBytes(arrby));
        }
    }

    private void cacheNameBitmap(String string) {
        if (!this.mPaused) {
            this.mContactNameBitmapCache.put((Object)string, (Object)this.createNameBitmapHolderFromName(string));
        }
    }

    private void clearContactBitmapCache(boolean bl) {
        if (!bl && this.mContactBitmapCache.size() > 0) {
            this.mContactBitmapCache.clear();
        }
        if (this.mContactNameBitmapCache.size() > 0) {
            this.mContactNameBitmapCache.clear();
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private BitmapHolder createBitmapHolderFromBytes(byte[] object) {
        BitmapHolder bitmapHolder = new BitmapHolder();
        bitmapHolder.state = 2;
        if (object == null) return bitmapHolder;
        try {
            object = BitmapFactory.decodeByteArray((byte[])object, (int)0, (int)object.length, (BitmapFactory.Options)null);
            bitmapHolder.bitmapRef = new SoftReference<Bitmap>(BitmapFactory.createPhoto((Context)this.mContext, (Bitmap)object, (int)this.mContactPhotoWidth));
        }
        catch (OutOfMemoryError outOfMemoryError) {
            return bitmapHolder;
        }
        return bitmapHolder;
    }

    private BitmapHolder createNameBitmapHolderFromName(String string) {
        BitmapHolder bitmapHolder = new BitmapHolder();
        bitmapHolder.state = 2;
        if (string != null) {
            bitmapHolder.bitmapRef = new SoftReference<Bitmap>(BitmapFactory.createNameBitmap((Context)this.mContext, (String)string, (int)this.mContactPhotoWidth));
        }
        return bitmapHolder;
    }

    /*
     * Enabled aggressive block sorting
     */
    private boolean loadCachedNamePhoto(ImageView imageView, Contact object) {
        String string = object.getName();
        if (string == null) {
            imageView.setImageResource(this.mDefaultContactResourceId);
            return true;
        }
        object = (BitmapHolder)this.mContactNameBitmapCache.get((Object)string);
        if (object == null) {
            object = new BitmapHolder();
            this.mContactNameBitmapCache.put((Object)string, object);
        } else {
            if (object.bitmapRef == null) {
                imageView.setImageResource(this.mDefaultContactResourceId);
                if (object.state == 2) return true;
                return false;
            }
            string = object.bitmapRef.get();
            if (string != null) {
                imageView.setImageBitmap((Bitmap)string);
                if (object.state == 2) return true;
                return false;
            }
            object.bitmapRef = null;
        }
        imageView.setImageResource(this.mDefaultContactResourceId);
        object.state = 0;
        return false;
    }

    /*
     * Enabled aggressive block sorting
     */
    private boolean loadCachedPhoto(ImageView imageView, Contact object) {
        boolean bl = true;
        BitmapHolder bitmapHolder = (BitmapHolder)this.mContactBitmapCache.get((Object)object.getPhotoId());
        if (bitmapHolder == null) {
            bitmapHolder = new BitmapHolder();
            this.mContactBitmapCache.put((Object)object.getPhotoId(), (Object)bitmapHolder);
            object = bitmapHolder;
        } else {
            if (bitmapHolder.bitmapRef == null) {
                imageView.setImageResource(this.mDefaultContactResourceId);
                if (bitmapHolder.state == 2) return bl;
                return false;
            }
            object = bitmapHolder.bitmapRef.get();
            if (object != null) {
                imageView.setImageBitmap((Bitmap)object);
                if (bitmapHolder.state == 2) return bl;
                return false;
            }
            bitmapHolder.bitmapRef = null;
            object = bitmapHolder;
        }
        imageView.setImageResource(this.mDefaultContactResourceId);
        object.state = 0;
        return false;
    }

    private void obtainPhotoIdsAndNamesToLoad(ArrayList<Long> arrayList, ArrayList<String> arrayList2, ArrayList<String> arrayList3) {
        arrayList.clear();
        arrayList2.clear();
        arrayList3.clear();
        Iterator iterator = this.mPendingRequests.values().iterator();
        while (iterator.hasNext()) {
            Object object = (Contact)iterator.next();
            Object object2 = object.getPhotoId();
            if (object2.longValue() == 0) {
                object2 = object.getName();
                if (object2 == null || (object = (BitmapHolder)this.mContactNameBitmapCache.get(object2)) == null || object.state != 0) continue;
                object.state = 1;
                arrayList3.add(object2);
                continue;
            }
            object = (BitmapHolder)this.mContactBitmapCache.get(object2);
            if (object == null || object.state != 0) continue;
            object.state = 1;
            arrayList.add(object2);
            arrayList2.add((Object)object2.toString());
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private void processLoadedImages() {
        Iterator iterator = this.mPendingRequests.keySet().iterator();
        while (iterator.hasNext()) {
            ImageView imageView = (ImageView)iterator.next();
            Contact contact = (Contact)this.mPendingRequests.get((Object)imageView);
            boolean bl = contact.getPhotoId() == 0 ? this.loadCachedNamePhoto(imageView, contact) : this.loadCachedPhoto(imageView, contact);
            if (!bl) continue;
            iterator.remove();
        }
        if (!this.mPendingRequests.isEmpty()) {
            this.requestLoading();
        }
    }

    private void requestLoading() {
        if (!this.mLoadingRequested) {
            this.mLoadingRequested = true;
            this.mMainThreadHandler.sendEmptyMessage(1);
        }
    }

    public void cancelRequest(ImageView imageView) {
        YellowPageImgLoader.cancelLoading((Context)this.mContext, (ImageView)imageView);
        this.mPendingRequests.remove((Object)imageView);
    }

    public void clear() {
        this.mPendingRequests.clear();
        Iterator iterator = this.mContactBitmapCache.values().iterator();
        while (iterator.hasNext()) {
            ((BitmapHolder)iterator.next()).state = 0;
        }
        iterator = this.mContactNameBitmapCache.values().iterator();
        while (iterator.hasNext()) {
            ((BitmapHolder)iterator.next()).state = 0;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public boolean handleMessage(Message message) {
        boolean bl = true;
        switch (message.what) {
            default: {
                return false;
            }
            case 1: {
                this.mLoadingRequested = false;
                if (this.mPaused) return bl;
                if (this.mLoaderThread == null) {
                    this.mLoaderThread = new LoaderThread(this.mContext.getContentResolver());
                    this.mLoaderThread.setPriority(1);
                    this.mLoaderThread.start();
                }
                this.mLoaderThread.requestLoading();
                return true;
            }
            case 2: 
        }
        if (this.mPaused) return bl;
        this.processLoadedImages();
        return true;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void loadPhoto(ImageView imageView, Contact contact) {
        if (!contact.isB2cNumber() && !contact.isYellowPageNumber() && contact.getPhotoId() == 0) {
            if (!MiuiSettings.System.useWordPhoto((Context)this.mContext)) {
                imageView.setImageResource(this.mDefaultContactResourceId);
                this.mPendingRequests.remove((Object)imageView);
                return;
            }
            YellowPageImgLoader.cancelLoading((Context)this.mContext, (ImageView)imageView);
            if (this.loadCachedNamePhoto(imageView, contact)) {
                this.mPendingRequests.remove((Object)imageView);
                return;
            } else {
                this.mPendingRequests.put((Object)imageView, (Object)contact);
                if (this.mPaused) return;
                {
                    this.requestLoading();
                    return;
                }
            }
        } else {
            if (contact.isB2cNumber()) {
                YellowPageImgLoader.cancelLoading((Context)this.mContext, (ImageView)imageView);
                this.mPendingRequests.remove((Object)imageView);
                YellowPageImgLoader.loadThumbnailByName((Context)this.mContext, (ImageView)imageView, (YellowPageImgLoader.Image.ImageProcessor)this.mRoundImageProcessor, (String)contact.getYellowPageThumbnailName(), (int)this.mDefaultSPResourceId);
                return;
            }
            if (contact.isYellowPageNumber()) {
                YellowPageImgLoader.cancelLoading((Context)this.mContext, (ImageView)imageView);
                this.mPendingRequests.remove((Object)imageView);
                YellowPageImgLoader.loadThumbnail((Context)this.mContext, (ImageView)imageView, (YellowPageImgLoader.Image.ImageProcessor)this.mRoundImageProcessor, (String)contact.getNumber(), (int)this.mDefaultSPResourceId);
                return;
            }
            YellowPageImgLoader.cancelLoading((Context)this.mContext, (ImageView)imageView);
            if (this.loadCachedPhoto(imageView, contact)) {
                this.mPendingRequests.remove((Object)imageView);
                return;
            }
            this.mPendingRequests.put((Object)imageView, (Object)contact);
            if (this.mPaused) return;
            {
                this.requestLoading();
                return;
            }
        }
    }

    public void pause() {
        this.mPaused = true;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void requestClear(boolean bl) {
        if (this.mLoaderThread != null) {
            this.mLoaderThread.requestClear(bl);
            return;
        } else {
            if (this.mLoadingRequested) return;
            {
                this.clearContactBitmapCache(bl);
                return;
            }
        }
    }

    public void resume() {
        this.mPaused = false;
        if (!this.mPendingRequests.isEmpty()) {
            this.requestLoading();
        }
    }

    public void stop() {
        this.pause();
        if (this.mLoaderThread != null) {
            this.mLoaderThread.quit();
            this.mLoaderThread = null;
        }
        this.clear();
    }

    private static class BitmapHolder {
        SoftReference<Bitmap> bitmapRef;
        int state;

        private BitmapHolder() {
        }
    }

    private class LoaderThread
    extends HandlerThread
    implements Handler.Callback {
        private Handler mLoaderThreadHandler;
        private final ArrayList<String> mNameList;
        private final ArrayList<Long> mPhotoIds;
        private final ArrayList<String> mPhotoIdsAsStrings;
        private final ContentResolver mResolver;
        private final StringBuilder mStringBuilder;

        public LoaderThread(ContentResolver contentResolver) {
            super("ContactPhotoLoader");
            this.mStringBuilder = new StringBuilder();
            this.mPhotoIds = Lists.newArrayList();
            this.mPhotoIdsAsStrings = Lists.newArrayList();
            this.mNameList = Lists.newArrayList();
            this.mResolver = contentResolver;
        }

        /*
         * Enabled force condition propagation
         * Lifted jumps to return sites
         */
        private void loadContactNamesPhoto() {
            int n = this.mNameList.size();
            if (n == 0) {
                return;
            }
            int n2 = 0;
            while (n2 < n) {
                ContactPhotoLoader.this.cacheNameBitmap((String)this.mNameList.get(n2));
                ++n2;
            }
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        private void loadContactPhotosFromDatabase() {
            Cursor cursor;
            int n;
            int n2 = this.mPhotoIds.size();
            if (n2 == 0) {
                return;
            }
            this.mStringBuilder.setLength(0);
            this.mStringBuilder.append("_id IN(");
            for (n = 0; n < n2; ++n) {
                if (n != 0) {
                    this.mStringBuilder.append(',');
                }
                this.mStringBuilder.append('?');
            }
            this.mStringBuilder.append(')');
            Cursor cursor2 = null;
            try {
                cursor = this.mResolver.query(ContactsContract.Data.CONTENT_URI, ContactPhotoLoader.this.CONTACT_COLUMNS, this.mStringBuilder.toString(), (String[])this.mPhotoIdsAsStrings.toArray((Object[])EMPTY_STRING_ARRAY), null);
                if (cursor != null) {
                    do {
                        cursor2 = cursor;
                        if (cursor.moveToNext()) {
                            cursor2 = cursor;
                            Long l = cursor.getLong(0);
                            cursor2 = cursor;
                            byte[] arrby = cursor.getBlob(1);
                            cursor2 = cursor;
                            ContactPhotoLoader.this.cacheBitmap(l, arrby);
                            cursor2 = cursor;
                            this.mPhotoIds.remove((Object)l);
                            continue;
                        }
                        break;
                        break;
                    } while (true);
                }
            }
            catch (Throwable var4_5) {
                if (cursor2 != null) {
                    cursor2.close();
                }
                throw var4_5;
            }
            if (cursor != null) {
                cursor.close();
            }
            n2 = this.mPhotoIds.size();
            n = 0;
            while (n < n2) {
                ContactPhotoLoader.this.cacheBitmap((Long)this.mPhotoIds.get(n), null);
                ++n;
            }
        }

        /*
         * Enabled aggressive block sorting
         */
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 0: {
                    ContactPhotoLoader.this.obtainPhotoIdsAndNamesToLoad(this.mPhotoIds, this.mPhotoIdsAsStrings, this.mNameList);
                    this.loadContactPhotosFromDatabase();
                    this.loadContactNamesPhoto();
                    ContactPhotoLoader.this.mMainThreadHandler.sendEmptyMessage(2);
                }
                default: {
                    return true;
                }
                case 1: 
            }
            boolean bl = (Boolean)message.obj;
            ContactPhotoLoader.this.clearContactBitmapCache(bl);
            return true;
        }

        public void requestClear(boolean bl) {
            if (this.mLoaderThreadHandler == null) {
                this.mLoaderThreadHandler = new Handler(this.getLooper(), (Handler.Callback)this);
            }
            this.mLoaderThreadHandler.removeMessages(1);
            Message.obtain((Handler)this.mLoaderThreadHandler, (int)1, (Object)bl).sendToTarget();
        }

        public void requestLoading() {
            if (this.mLoaderThreadHandler == null) {
                this.mLoaderThreadHandler = new Handler(this.getLooper(), (Handler.Callback)this);
            }
            this.mLoaderThreadHandler.removeMessages(0);
            this.mLoaderThreadHandler.sendEmptyMessage(0);
        }
    }

}

