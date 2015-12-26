/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.ContentResolver
 *  android.content.Context
 *  android.content.Intent
 *  android.content.res.Resources
 *  android.graphics.Bitmap
 *  android.graphics.BitmapFactory
 *  android.graphics.BitmapFactory$Options
 *  android.graphics.Rect
 *  android.graphics.drawable.BitmapDrawable
 *  android.graphics.drawable.Drawable
 *  android.net.Uri
 *  android.text.TextUtils
 *  android.util.Log
 *  com.google.android.mms.pdu.CharacterSets
 *  com.google.android.mms.pdu.MiuiPduPersister
 *  com.google.android.mms.pdu.PduPart
 *  java.io.ByteArrayOutputStream
 *  java.lang.Math
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  miui.graphics.drawable.GifAnimationDrawable
 *  miui.util.IOUtils
 */
package com.android.mms.ui;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import com.android.mms.ui.MessageUtils;
import com.google.android.mms.pdu.CharacterSets;
import com.google.android.mms.pdu.MiuiPduPersister;
import com.google.android.mms.pdu.PduPart;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import miui.graphics.drawable.GifAnimationDrawable;
import miui.util.IOUtils;

public class SimplePduPart
extends PduPart {
    private int mAttachmentType = -1;
    private Context mContext;

    SimplePduPart(Context context) {
        this.mContext = context;
    }

    /*
     * Loose catch block
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    private Drawable extractImageFromData(int n, int n2) {
        Object object2;
        Object object;
        block12 : {
            Uri uri = this.getDataUri();
            object2 = new GifAnimationDrawable();
            boolean bl = object2.load(this.mContext, uri);
            if (bl) {
                return object2;
            }
            object = null;
            object2 = null;
            {
                catch (OutOfMemoryError outOfMemoryError) {
                    MessageUtils.writeHprofDataToFile();
                    Log.e((String)"SimplePduPart", (String)"Not enough memory.", (Throwable)outOfMemoryError);
                    return null;
                }
            }
            InputStream inputStream = this.mContext.getContentResolver().openInputStream(uri);
            int n3 = 1;
            object2 = inputStream;
            if (inputStream == null) break block12;
            object2 = inputStream;
            object = inputStream;
            BitmapFactory.Options options = new BitmapFactory.Options();
            object2 = inputStream;
            object = inputStream;
            options.inJustDecodeBounds = true;
            object2 = inputStream;
            object = inputStream;
            BitmapFactory.decodeStream((InputStream)inputStream, (Rect)null, (BitmapFactory.Options)options);
            object2 = inputStream;
            object = inputStream;
            if (options.outHeight > n2) {
                object2 = inputStream;
                object = inputStream;
                n3 = options.outHeight / n2;
            }
            n2 = n3;
            object2 = inputStream;
            object = inputStream;
            if (options.outWidth > n) {
                object2 = inputStream;
                object = inputStream;
                n2 = Math.max((int)n3, (int)(options.outWidth / n));
            }
            object2 = inputStream;
            object = inputStream;
            IOUtils.closeQuietly((InputStream)inputStream);
            object2 = inputStream;
            object = inputStream;
            object2 = inputStream = this.mContext.getContentResolver().openInputStream(uri);
            if (inputStream == null) break block12;
            object2 = inputStream;
            object = inputStream;
            uri = new BitmapFactory.Options();
            object2 = inputStream;
            object = inputStream;
            uri.inSampleSize = n2;
            object2 = inputStream;
            object = inputStream;
            uri = BitmapFactory.decodeStream((InputStream)inputStream, (Rect)null, (BitmapFactory.Options)uri);
            object2 = inputStream;
            object = inputStream;
            uri = new BitmapDrawable(this.mContext.getResources(), (Bitmap)uri);
            IOUtils.closeQuietly((InputStream)inputStream);
            return uri;
        }
        IOUtils.closeQuietly((InputStream)object2);
        return null;
        catch (IOException iOException) {
            object = object2;
            try {
                Log.e((String)"SimplePduPart", (String)"Cannot extract image from data", (Throwable)iOException);
            }
            catch (Throwable var5_6) {
                IOUtils.closeQuietly((InputStream)object);
                throw var5_6;
            }
            IOUtils.closeQuietly((InputStream)object2);
            return null;
        }
    }

    private String extractTextFromData() {
        byte[] arrby;
        if (this.loadData() && (arrby = this.getData()) != null) {
            try {
                if (this.getCharset() == 0) {
                    return new String(arrby);
                }
                String string2 = new String(arrby, CharacterSets.getMimeName((int)this.getCharset()));
                return string2;
            }
            catch (UnsupportedEncodingException var2_3) {
                return new String(arrby);
            }
        }
        return "";
    }

    public int getAttachmentType() {
        return this.mAttachmentType;
    }

    public Drawable getImageNoCache(int n, int n2) {
        return this.extractImageFromData(n, n2);
    }

    public Intent getIntent() {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addFlags(1);
        intent.setDataAndType(this.getDataUri(), MiuiPduPersister.toIsoString((byte[])this.getContentType()));
        String string2 = this.getPduPartName();
        if (!TextUtils.isEmpty((CharSequence)string2)) {
            intent.putExtra("display_name", string2);
        }
        return intent;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public String getPduPartName() {
        Object var2_1 = null;
        Object object = this.getContentLocation();
        if (object != null && object.length > 0) {
            return new String((byte[])object);
        }
        object = this.getContentId();
        if (object != null && object.length > 0) {
            return new String((byte[])object);
        }
        object = this.getFilename();
        if (object != null && object.length > 0) {
            return new String((byte[])object);
        }
        byte[] arrby = this.getName();
        object = var2_1;
        if (arrby == null) return object;
        object = var2_1;
        if (arrby.length <= 0) return object;
        return new String(arrby);
    }

    public String getText() {
        return this.extractTextFromData();
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive exception aggregation
     */
    boolean loadData() {
        block22 : {
            block20 : {
                block21 : {
                    if (this.getData() != null) {
                        return true;
                    }
                    var5_1 = new ByteArrayOutputStream();
                    var4_2 = null;
                    var3_3 = null;
                    var2_5 = this.mContext.getContentResolver().openInputStream(this.getDataUri());
                    if (var2_5 != null) break block20;
                    if (var2_5 == null) break block21;
                    var2_5.close();
                }
lbl13: // 2 sources:
                do {
                    return false;
                    break;
                } while (true);
            }
            var3_3 = var2_5;
            var4_2 = var2_5;
            var6_11 = new byte[256];
            var3_3 = var2_5;
            var4_2 = var2_5;
            var1_12 = var2_5.read(var6_11);
            while (var1_12 >= 0) {
                var3_3 = var2_5;
                var4_2 = var2_5;
                var5_1.write(var6_11, 0, var1_12);
                var3_3 = var2_5;
                var4_2 = var2_5;
                var1_12 = var2_5.read(var6_11);
            }
            if (var2_5 == null) break block22;
            try {
                var2_5.close();
            }
            catch (IOException var2_9) {
                ** continue;
            }
        }
lbl35: // 2 sources:
        do {
            this.setData(var5_1.toByteArray());
            return true;
            break;
        } while (true);
        catch (IOException var2_6) {
            if (var3_3 != null) {
                var3_3.close();
            }
lbl42: // 4 sources:
            do {
                return false;
                break;
            } while (true);
        }
        catch (Throwable var2_7) {
            if (var4_2 != null) {
                var4_2.close();
            }
lbl48: // 4 sources:
            do {
                throw var2_7;
                break;
            } while (true);
        }
        catch (IOException var2_8) {
            ** continue;
        }
        {
            catch (IOException var2_10) {
                ** continue;
            }
        }
        {
            catch (IOException var3_4) {
                ** continue;
            }
        }
    }

    public void setAttachmentType(int n) {
        this.mAttachmentType = n;
    }
}

