/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.graphics.drawable.Drawable
 *  android.os.Handler
 *  android.widget.ImageView
 *  java.lang.Long
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Thread
 *  java.util.ArrayList
 *  java.util.Map$Entry
 */
package com.android.mms.ui;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.widget.ImageView;
import com.android.mms.MmsApp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class PreviewImageLoader {
    private static PreviewImageLoader sInstance = null;
    private LinkedHashMap<Long, CacheNode> mCachedPreviews = new LinkedHashMap(32);
    private Context mContext = MmsApp.getApp().getApplicationContext();
    private Handler mHandler = new Handler();
    private LoaderThread mLoaderThread;
    private LinkedHashMap<Long, ArrayList<ImageView>> mRequestedIds = new LinkedHashMap();

    private PreviewImageLoader() {
    }

    static /* synthetic */ LinkedHashMap access$100(PreviewImageLoader previewImageLoader) {
        return previewImageLoader.mRequestedIds;
    }

    static /* synthetic */ Context access$200(PreviewImageLoader previewImageLoader) {
        return previewImageLoader.mContext;
    }

    static /* synthetic */ Handler access$500(PreviewImageLoader previewImageLoader) {
        return previewImageLoader.mHandler;
    }

    public static PreviewImageLoader getInstance() {
        synchronized (PreviewImageLoader.class) {
            if (sInstance == null) {
                sInstance = new PreviewImageLoader();
            }
            PreviewImageLoader previewImageLoader = sInstance;
            return previewImageLoader;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void cancel(long l, ImageView imageView) {
        LinkedHashMap<Long, ArrayList<ImageView>> linkedHashMap = this.mRequestedIds;
        synchronized (linkedHashMap) {
            ArrayList<ImageView> arrayList = this.mRequestedIds.get(l);
            if (arrayList != null) {
                arrayList.remove((Object)imageView);
            }
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    public void cancelAllRequests(boolean bl) {
        LinkedHashMap<Long, ArrayList<ImageView>> linkedHashMap = this.mRequestedIds;
        // MONITORENTER : linkedHashMap
        this.mRequestedIds.clear();
        // MONITOREXIT : linkedHashMap
        if (bl) {
            this.mCachedPreviews.clear();
        }
        if (this.mLoaderThread == null) return;
        this.mLoaderThread.interrupt();
        this.mLoaderThread = null;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void cancelAndClear(long l) {
        LinkedHashMap<Long, ArrayList<ImageView>> linkedHashMap = this.mRequestedIds;
        synchronized (linkedHashMap) {
            this.mRequestedIds.remove((Object)l);
        }
        this.mCachedPreviews.remove((Object)l);
    }

    public void onLowMemory() {
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    public boolean request(long l, long l2, ImageView imageView) {
        ArrayList arrayList;
        CacheNode cacheNode = this.mCachedPreviews.get(l);
        if (cacheNode != null) {
            imageView.setImageDrawable(cacheNode.drawable);
            if (cacheNode.timestamp <= l2) {
                return true;
            }
        }
        if (this.mLoaderThread == null) {
            this.mLoaderThread = new LoaderThread();
            this.mLoaderThread.setName("PreviewImageLoader");
            this.mLoaderThread.setPriority(1);
            this.mLoaderThread.start();
        }
        LinkedHashMap<Long, ArrayList<ImageView>> linkedHashMap = this.mRequestedIds;
        // MONITORENTER : linkedHashMap
        ArrayList arrayList2 = arrayList = this.mRequestedIds.get(l);
        if (arrayList == null) {
            arrayList2 = new ArrayList();
            this.mRequestedIds.put((Object)l, (Object)arrayList2);
        }
        arrayList2.add((Object)imageView);
        if (arrayList2.size() == 1) {
            this.mRequestedIds.notify();
        }
        // MONITOREXIT : linkedHashMap
        if (cacheNode != null) return true;
        return false;
    }

    private class CacheNode {
        public Drawable drawable;
        public long timestamp;

        private CacheNode() {
        }
    }

    private class LoaderThread
    extends Thread {
        private LoaderThread() {
        }

        /*
         * Exception decompiling
         */
        public void run() {
            // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
            // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [2[TRYBLOCK]], but top level block is 11[CATCHBLOCK]
            // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.processEndingBlocks(Op04StructuredStatement.java:394)
            // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:446)
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

