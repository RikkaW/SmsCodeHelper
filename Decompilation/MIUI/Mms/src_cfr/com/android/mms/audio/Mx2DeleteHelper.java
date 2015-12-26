/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.AsyncQueryHandler
 *  android.content.ComponentName
 *  android.content.ContentResolver
 *  android.content.ContentUris
 *  android.content.Context
 *  android.content.Intent
 *  android.database.Cursor
 *  android.net.Uri
 *  android.os.Bundle
 *  android.provider.Telephony
 *  android.provider.Telephony$Mms
 *  java.lang.Class
 *  java.lang.Object
 *  java.lang.String
 */
package com.android.mms.audio;

import android.content.AsyncQueryHandler;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Telephony;
import com.android.mms.MmsApp;
import com.android.mms.audio.AudioItemCache;
import com.android.mms.audio.DeleteCallback;
import com.android.mms.transaction.CleanFileService;
import com.android.mms.ui.PreviewImageLoader;

public class Mx2DeleteHelper
extends AsyncQueryHandler {
    private String[] mArgs;
    private AudioItemCache mAudioItemCache = null;
    private DeleteCallback mCallback;
    private String[] mPaths;
    private String mSelection;
    private Uri mUri;

    public Mx2DeleteHelper(ContentResolver contentResolver) {
        super(contentResolver);
    }

    public static void deleteMms(int n, Object object, Uri uri, String string, String[] arrstring, DeleteCallback deleteCallback, AudioItemCache audioItemCache) {
        Mx2DeleteHelper mx2DeleteHelper = new Mx2DeleteHelper(MmsApp.getApp().getContentResolver());
        mx2DeleteHelper.mAudioItemCache = audioItemCache;
        mx2DeleteHelper.mUri = uri;
        mx2DeleteHelper.mSelection = string;
        mx2DeleteHelper.mArgs = arrstring;
        mx2DeleteHelper.mCallback = deleteCallback;
        mx2DeleteHelper.startQuery(n, object, Telephony.Mms.CONTENT_URI, new String[]{"mx_extension", "_id"}, string, arrstring, null);
    }

    protected void onDeleteComplete(int n, Object object, int n2) {
        if (this.mPaths != null) {
            Intent intent = new Intent((Context)MmsApp.getApp(), (Class)CleanFileService.class);
            Bundle bundle = new Bundle();
            bundle.putStringArray("paths", this.mPaths);
            intent.putExtras(bundle);
            MmsApp.getApp().startService(intent);
        }
        long l = ContentUris.parseId((Uri)this.mUri);
        PreviewImageLoader.getInstance().cancelAndClear(l);
        if (this.mCallback != null) {
            this.mCallback.onDeleteComplete(n, object, n2);
        }
    }

    /*
     * Exception decompiling
     */
    protected void onQueryComplete(int var1_1, Object var2_2, Cursor var3_6) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [5[TRYBLOCK]], but top level block is 16[UNCONDITIONALDOLOOP]
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.processEndingBlocks(Op04StructuredStatement.java:394)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op04StructuredStatement.buildNestedBlocks(Op04StructuredStatement.java:446)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op03SimpleStatement.createInitialStructuredBlock(Op03SimpleStatement.java:2859)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:805)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:220)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:165)
        // org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:91)
        // org.benf.cfr.reader.entities.Method.analyse(Method.java:354)
        // org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:751)
        // org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:683)
        // org.benf.cfr.reader.Main.doJar(Main.java:128)
        // org.benf.cfr.reader.Main.main(Main.java:178)
        throw new IllegalStateException("Decompilation failed");
    }
}

