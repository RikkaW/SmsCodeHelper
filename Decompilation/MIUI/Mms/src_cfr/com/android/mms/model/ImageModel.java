/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 *  android.graphics.Bitmap
 *  android.graphics.drawable.BitmapDrawable
 *  android.graphics.drawable.Drawable
 *  android.net.Uri
 *  android.text.TextUtils
 *  android.util.Log
 *  com.google.android.mms.MmsException
 *  com.google.android.mms.pdu.MiuiPduPersister
 *  com.google.android.mms.pdu.PduPart
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.util.Arrays
 *  java.util.HashSet
 *  miui.graphics.drawable.GifAnimationDrawable
 *  miui.os.Build
 *  miui.util.IOUtils
 */
package com.android.mms.model;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import com.android.mms.ContentRestrictionException;
import com.android.mms.ExceedMessageSizeException;
import com.android.mms.MmsConfig;
import com.android.mms.model.ContentRestrictionFactory;
import com.android.mms.model.RegionMediaModel;
import com.android.mms.model.RegionModel;
import com.android.mms.ui.UriImage;
import com.google.android.mms.MmsException;
import com.google.android.mms.pdu.MiuiPduPersister;
import com.google.android.mms.pdu.PduPart;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import miui.graphics.drawable.GifAnimationDrawable;
import miui.os.Build;
import miui.util.IOUtils;
import org.w3c.dom.events.Event;

public class ImageModel
extends RegionMediaModel {
    private static final Set<String> SUPPORTED_MMS_IMAGE_CONTENT_TYPES = new HashSet((Collection)Arrays.asList((Object[])new String[]{"image/jpeg"}));
    private SoftReference<Drawable> mDrawableCache = new SoftReference<Object>(null);
    private int mHeight;
    private int mWidth;

    public ImageModel(Context context, Uri uri, RegionModel regionModel) throws MmsException {
        super(context, "img", uri, regionModel);
        this.initModelFromUri(uri);
        this.checkContentRestriction();
    }

    public ImageModel(Context context, String string, String string2, Uri uri, RegionModel regionModel) throws MmsException {
        super(context, "img", string, string2, uri, regionModel);
        this.decodeImageBounds(uri);
    }

    private Drawable createThumbnailDrawable(int n, Uri uri) {
        block10 : {
            int n2;
            block9 : {
                int n3 = this.mWidth;
                int n4 = this.mHeight;
                n2 = 1;
                while (n3 / n2 > n || n4 / n2 > n) {
                    n2 *= 2;
                }
                if (Log.isLoggable((String)"Mms:app", (int)2)) {
                    Log.v((String)"Mms/image", (String)("createThumbnailBitmap: scale=" + n2 + ", w=" + n3 / n2 + ", h=" + n4 / n2));
                }
                GifAnimationDrawable gifAnimationDrawable = new GifAnimationDrawable();
                boolean bl = gifAnimationDrawable.load(this.mContext, uri);
                if (!bl) break block9;
                IOUtils.closeQuietly((InputStream)null);
                return gifAnimationDrawable;
            }
            uri = new UriImage(this.mContext, uri).getBitmap(n2);
            if (uri != null) break block10;
            IOUtils.closeQuietly((InputStream)null);
            return null;
        }
        try {
            uri = new BitmapDrawable(this.mContext.getResources(), (Bitmap)uri);
            return uri;
        }
        catch (OutOfMemoryError var2_3) {
            throw var2_3;
        }
        catch (Throwable var2_4) {
            throw var2_4;
        }
        finally {
            IOUtils.closeQuietly((InputStream)null);
        }
    }

    private void decodeImageBounds(Uri object) {
        object = new UriImage(this.mContext, (Uri)object);
        this.mWidth = object.getWidth();
        this.mHeight = object.getHeight();
    }

    private void initModelFromUri(Uri object) throws MmsException {
        object = new UriImage(this.mContext, (Uri)object);
        this.mContentType = object.getContentType();
        if (TextUtils.isEmpty((CharSequence)this.mContentType)) {
            throw new MmsException("Type of media is unknown.");
        }
        this.setInternalSrc(object.getSrc());
        this.mWidth = object.getWidth();
        this.mHeight = object.getHeight();
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private Drawable internalGetDrawable(Uri uri) {
        Drawable drawable2;
        Drawable drawable = drawable2 = this.mDrawableCache.get();
        if (drawable2 != null) return drawable;
        drawable = drawable2;
        try {
            drawable = uri = this.createThumbnailDrawable(480, uri);
            if (uri == null) return drawable;
            drawable = uri;
        }
        catch (OutOfMemoryError outOfMemoryError) {
            return drawable;
        }
        this.mDrawableCache = new SoftReference<Uri>(uri);
        return uri;
    }

    protected void checkContentRestriction() throws ContentRestrictionException {
        ContentRestrictionFactory.getContentRestriction().checkImageContentType(this.mContentType);
    }

    public Drawable getDrawable() {
        return this.internalGetDrawable(this.getUri());
    }

    @Override
    public boolean getMediaResizable() {
        if (Build.IS_CM_CUSTOMIZATION || Build.IS_CU_CUSTOMIZATION) {
            return false;
        }
        return true;
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void handleEvent(Event event) {
        if (event.getType().equals((Object)"SmilMediaStart")) {
            this.mVisible = true;
        } else if (this.mFill != 1) {
            this.mVisible = false;
        }
        this.notifyModelChanged(false);
    }

    @Override
    protected void resizeMedia(int n, long l) throws MmsException {
        UriImage uriImage = new UriImage(this.mContext, this.getUri());
        int n2 = MmsConfig.getMaxImageWidth();
        int n3 = MmsConfig.getMaxImageHeight();
        int n4 = this.getMediaSize();
        int n5 = n3;
        int n6 = n2;
        if (uriImage.getHeight() > uriImage.getWidth()) {
            n6 = n3;
            n5 = n2;
        }
        if (Log.isLoggable((String)"Mms:app", (int)2)) {
            Log.v((String)"Mms/image", (String)("resizeMedia size: " + n4 + " image.getWidth(): " + uriImage.getWidth() + " widthLimit: " + n6 + " image.getHeight(): " + uriImage.getHeight() + " heightLimit: " + n5 + " image.getContentType(): " + uriImage.getContentType()));
        }
        if (n4 != 0 && n4 <= n && uriImage.getWidth() <= n6 && uriImage.getHeight() <= n5 && SUPPORTED_MMS_IMAGE_CONTENT_TYPES.contains(uriImage.getContentType())) {
            if (Log.isLoggable((String)"Mms:app", (int)2)) {
                Log.v((String)"Mms/image", (String)"resizeMedia - already sized");
            }
            return;
        }
        PduPart pduPart = uriImage.getResizedImageAsPart(n6, n5, n);
        if (pduPart == null) {
            throw new ExceedMessageSizeException("Not enough memory to turn image into part: " + (Object)this.getUri());
        }
        this.mContentType = new String(pduPart.getContentType());
        String string = this.getSrc();
        uriImage = (UriImage)string.getBytes();
        pduPart.setContentLocation((byte[])uriImage);
        n = string.lastIndexOf(".");
        if (n != -1) {
            uriImage = (UriImage)string.substring(0, n).getBytes();
        }
        pduPart.setContentId((byte[])uriImage);
        uriImage = MiuiPduPersister.getPduPersister((Context)this.mContext);
        this.mSize = pduPart.getData().length;
        if (Log.isLoggable((String)"Mms:app", (int)2)) {
            Log.v((String)"Mms/image", (String)("resizeMedia mSize: " + this.mSize));
        }
        this.setUri(uriImage.persistPart(pduPart, l));
    }
}

