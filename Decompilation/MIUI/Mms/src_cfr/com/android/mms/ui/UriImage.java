/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ContentResolver
 *  android.content.Context
 *  android.database.Cursor
 *  android.database.sqlite.SqliteWrapper
 *  android.graphics.Bitmap
 *  android.graphics.BitmapFactory
 *  android.graphics.BitmapFactory$Options
 *  android.graphics.Rect
 *  android.media.ExifInterface
 *  android.net.Uri
 *  android.text.TextUtils
 *  android.util.Log
 *  android.webkit.MimeTypeMap
 *  com.google.android.mms.pdu.PduPart
 *  java.io.FileNotFoundException
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  miui.util.IOUtils
 */
package com.android.mms.ui;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SqliteWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.media.ExifInterface;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.MimeTypeMap;
import com.android.mms.LogTag;
import com.android.mms.model.ImageModel;
import com.google.android.mms.pdu.PduPart;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import miui.util.IOUtils;

public class UriImage {
    private String mContentType;
    private final Context mContext;
    private int mHeight;
    private String mPath;
    private int mRotation;
    private String mSrc;
    private final Uri mUri;
    private int mWidth;

    /*
     * Enabled aggressive block sorting
     */
    public UriImage(Context context, Uri uri) {
        if (context == null || uri == null) {
            throw new IllegalArgumentException();
        }
        if (uri.getScheme().equals((Object)"content")) {
            this.initFromContentUri(context, uri);
        } else if (uri.getScheme().equals((Object)"file")) {
            this.initFromFile(context, uri);
        }
        this.mSrc = this.mPath.substring(this.mPath.lastIndexOf(47) + 1);
        if (this.mSrc.startsWith(".") && this.mSrc.length() > 1) {
            this.mSrc = this.mSrc.substring(1);
        }
        this.mSrc = this.mSrc.replace(' ', '_');
        this.mContext = context;
        this.mUri = uri;
        this.decodeBoundsInfo();
    }

    /*
     * Loose catch block
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    private void decodeBoundsInfo() {
        InputStream inputStream;
        InputStream inputStream2 = null;
        InputStream inputStream3 = null;
        inputStream3 = inputStream = this.mContext.getContentResolver().openInputStream(this.mUri);
        inputStream2 = inputStream;
        BitmapFactory.Options options = new BitmapFactory.Options();
        inputStream3 = inputStream;
        inputStream2 = inputStream;
        options.inJustDecodeBounds = true;
        inputStream3 = inputStream;
        inputStream2 = inputStream;
        BitmapFactory.decodeStream((InputStream)inputStream, (Rect)null, (BitmapFactory.Options)options);
        inputStream3 = inputStream;
        inputStream2 = inputStream;
        if (this.mRotation % 180 == 0) {
            inputStream3 = inputStream;
            inputStream2 = inputStream;
            this.mWidth = options.outWidth;
            inputStream3 = inputStream;
            inputStream2 = inputStream;
            this.mHeight = options.outHeight;
        } else {
            inputStream3 = inputStream;
            inputStream2 = inputStream;
            this.mHeight = options.outWidth;
            inputStream3 = inputStream;
            inputStream2 = inputStream;
            this.mWidth = options.outHeight;
        }
        if (inputStream == null) return;
        inputStream.close();
        return;
        catch (IOException iOException) {
            Log.e((String)"Mms/image", (String)"IOException caught while closing stream", (Throwable)iOException);
            return;
        }
        {
            catch (FileNotFoundException fileNotFoundException) {
                inputStream2 = inputStream3;
                Log.e((String)"Mms/image", (String)"IOException caught while opening stream", (Throwable)fileNotFoundException);
                if (inputStream3 == null) return;
                try {
                    inputStream3.close();
                    return;
                }
                catch (IOException var1_4) {
                    Log.e((String)"Mms/image", (String)"IOException caught while closing stream", (Throwable)var1_4);
                    return;
                }
            }
        }
        catch (Throwable throwable) {
            if (inputStream2 == null) throw throwable;
            try {
                inputStream2.close();
            }
            catch (IOException var2_2) {
                Log.e((String)"Mms/image", (String)"IOException caught while closing stream", (Throwable)var2_2);
                throw throwable;
            }
            throw throwable;
        }
    }

    public static int exifOrientationToDegrees(int n) {
        switch (n) {
            default: {
                return 0;
            }
            case 6: {
                return 90;
            }
            case 3: {
                return 180;
            }
            case 8: 
        }
        return 270;
    }

    /*
     * Exception decompiling
     */
    private byte[] getGifImageData(int var1_1, int var2_2, int var3_3) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [1[TRYBLOCK]], but top level block is 21[UNCONDITIONALDOLOOP]
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

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    private void initFromContentUri(Context var1_1, Uri var2_3) {
        var5_4 = var1_1.getContentResolver();
        var4_5 = SqliteWrapper.query((Context)var1_1, (ContentResolver)var5_4, (Uri)var2_3, (String[])null, (String)null, (String[])null, (String)null);
        if (var4_5 == null) {
            throw new IllegalArgumentException("Query on " + var2_3 + " returns null result.");
        }
        if (var4_5.getCount() != 1) throw new IllegalArgumentException("Query on " + var2_3 + " returns 0 or multiple rows.");
        if (!var4_5.moveToFirst()) {
            throw new IllegalArgumentException("Query on " + var2_3 + " returns 0 or multiple rows.");
        }
        if (ImageModel.isMmsUri((Uri)var2_3)) {
            var1_1 = var2_3 = var4_5.getString(var4_5.getColumnIndexOrThrow("fn"));
            if (TextUtils.isEmpty((CharSequence)var2_3)) {
                var1_1 = var4_5.getString(var4_5.getColumnIndexOrThrow("_data"));
            }
            this.mContentType = var4_5.getString(var4_5.getColumnIndexOrThrow("ct"));
        } else {
            var1_1 = var2_3.getPath();
            try {
                this.mContentType = var4_5.getString(var4_5.getColumnIndexOrThrow("mime_type"));
            }
            catch (IllegalArgumentException var6_7) {
                try {
                    this.mContentType = var4_5.getString(var4_5.getColumnIndexOrThrow("mimetype"));
                }
                catch (IllegalArgumentException var6_8) {
                    this.mContentType = var5_4.getType((Uri)var2_3);
                }
            }
            this.mRotation = (var3_6 = var4_5.getColumnIndex("orientation")) == -1 ? 0 : var4_5.getInt(var3_6) % 360;
        }
        ** GOTO lbl30
        finally {
            var4_5.close();
        }
lbl30: // 2 sources:
        this.mPath = var1_1;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private void initFromFile(Context object, Uri uri) {
        String string2;
        this.mPath = uri.getPath();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        object = string2 = MimeTypeMap.getFileExtensionFromUrl((String)this.mPath);
        if (TextUtils.isEmpty((CharSequence)string2)) {
            int n = this.mPath.lastIndexOf(46);
            object = string2;
            if (n >= 0) {
                object = this.mPath.substring(n + 1);
            }
        }
        object = object == null ? null : mimeTypeMap.getMimeTypeFromExtension(object.toLowerCase());
        this.mContentType = object;
        try {
            this.mRotation = UriImage.exifOrientationToDegrees(new ExifInterface(uri.getPath()).getAttributeInt("Orientation", 1));
            return;
        }
        catch (Exception var1_2) {
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public Bitmap getBitmap(int n) {
        InputStream inputStream;
        int n2 = 1;
        BitmapFactory.Options options = new BitmapFactory.Options();
        InputStream inputStream2 = null;
        int n3 = n;
        n = n2;
        do {
            InputStream inputStream3;
            inputStream = null;
            try {
                inputStream = inputStream3 = this.mContext.getContentResolver().openInputStream(this.mUri);
            }
            catch (FileNotFoundException var6_10) {
                LogTag.error("Cannot open bitmap %s", this.mUri.toString());
            }
            if (inputStream == null) {
                return null;
            }
            options.inSampleSize = n3;
            try {
                inputStream2 = inputStream3 = BitmapFactory.decodeStream((InputStream)inputStream, (Rect)null, (BitmapFactory.Options)options);
                inputStream = inputStream2;
            }
            catch (OutOfMemoryError var6_8) {
                Log.w((String)"Mms/image", (String)("getResizedImageData: img too large to decode (OutOfMemoryError), may try with larger sampleSize. Curr sampleSize=" + n3));
                n3 *= 2;
                n2 = n + 1;
                inputStream = inputStream2;
                if (false) return inputStream;
                n = n2;
                if (n2 < 8) continue;
                inputStream = inputStream2;
                return inputStream;
            }
            return inputStream;
            break;
        } while (true);
        finally {
            IOUtils.closeQuietly((InputStream)inputStream);
        }
    }

    public String getContentType() {
        return this.mContentType;
    }

    public int getHeight() {
        return this.mHeight;
    }

    public PduPart getResizedImageAsPart(int n, int n2, int n3) {
        PduPart pduPart = new PduPart();
        byte[] arrby = this.getGifImageData(n, n2, n3);
        if (arrby != null) {
            pduPart.setData(arrby);
            pduPart.setContentType("image/gif".getBytes());
            return pduPart;
        }
        arrby = this.getResizedImageData(n, n2, n3);
        if (arrby == null) {
            return null;
        }
        pduPart.setData(arrby);
        pduPart.setContentType("image/jpeg".getBytes());
        return pduPart;
    }

    /*
     * Exception decompiling
     */
    public byte[] getResizedImageData(int var1_1, int var2_2, int var3_3) {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // org.benf.cfr.reader.util.ConfusedCFRException: Tried to end blocks [0[TRYBLOCK]], but top level block is 74[DOLOOP]
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

    public String getSrc() {
        return this.mSrc;
    }

    public int getWidth() {
        return this.mWidth;
    }
}

