/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.Resources
 *  android.content.res.Resources$NotFoundException
 *  android.graphics.Bitmap
 *  android.graphics.Bitmap$Config
 *  android.graphics.BitmapFactory
 *  android.graphics.BitmapFactory$Options
 *  android.graphics.Canvas
 *  android.graphics.NinePatch
 *  android.graphics.Paint
 *  android.graphics.Rect
 *  android.graphics.drawable.BitmapDrawable
 *  android.graphics.drawable.ColorDrawable
 *  android.graphics.drawable.Drawable
 *  android.graphics.drawable.Drawable$Callback
 *  android.graphics.drawable.GradientDrawable
 *  android.graphics.drawable.NinePatchDrawable
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.util.DisplayMetrics
 *  android.util.TypedValue
 *  android.view.LayoutInflater
 *  android.view.View
 *  android.view.ViewGroup
 *  android.widget.ImageView
 *  android.widget.TextView
 *  java.io.File
 *  java.io.FileInputStream
 *  java.io.InputStreamReader
 *  java.io.Reader
 *  java.io.StringWriter
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.lang.reflect.Field
 *  java.nio.charset.Charset
 */
package cn.com.xy.sms.sdk.ui.popu.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.NinePatch;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.NinePatchDrawable;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import cn.com.xy.sms.sdk.constant.Constant;
import cn.com.xy.sms.sdk.smsmessage.BusinessSmsMessage;
import cn.com.xy.sms.sdk.ui.popu.util.ResourceCacheUtil;
import cn.com.xy.sms.sdk.ui.popu.util.a;
import cn.com.xy.sms.sdk.util.KeyManager;
import cn.com.xy.sms.sdk.util.StringUtils;
import cn.com.xy.sms.sdk.util.d;
import java.io.Closeable;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.nio.charset.Charset;

public class ViewUtil {
    private static int a = -1;
    private static int b = -1;
    private static final Charset c = Charset.forName((String)"UTF-8");

    private static String a(InputStream inputStream) {
        return ViewUtil.readFully((Reader)new InputStreamReader(inputStream, c));
    }

    public static BitmapDrawable createBitmapByPath(Context context, String string2, int n2, int n3) {
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile((String)string2, (BitmapFactory.Options)options);
            n3 = options.outWidth;
            int n4 = options.outHeight;
            options.inDensity = n3;
            options.inTargetDensity = n2;
            options.inJustDecodeBounds = false;
            string2 = BitmapFactory.decodeFile((String)string2, (BitmapFactory.Options)options);
            string2.setDensity(n3);
            context = new BitmapDrawable(context.getResources(), (Bitmap)string2);
            return context;
        }
        catch (Throwable var0_1) {
            return null;
        }
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static BitmapDrawable createBitmapByPath2(Context var0, File var1_7, int var2_8, int var3_9) {
        new StringBuilder("imgPath=").append(var1_7.getName());
        var1_7 = new FileInputStream(var1_7);
        var4_10 = var1_7.getFD();
        var5_11 = new BitmapFactory.Options();
        var5_11.inJustDecodeBounds = true;
        BitmapFactory.decodeFileDescriptor((FileDescriptor)var4_10, (Rect)null, (BitmapFactory.Options)var5_11);
        var5_11.inDensity = var3_9 = var5_11.outWidth;
        var5_11.inTargetDensity = var2_8;
        var5_11.inJustDecodeBounds = false;
        var4_10 = BitmapFactory.decodeFileDescriptor((FileDescriptor)var4_10, (Rect)null, (BitmapFactory.Options)var5_11);
        var4_10.setDensity(var3_9);
        var0 = new BitmapDrawable(var0.getResources(), (Bitmap)var4_10);
        d.a((Closeable)var1_7);
        return var0;
        catch (Throwable var0_1) {
            var0_2 = null;
            ** GOTO lbl28
            catch (Throwable var0_3) {
                var1_7 = null;
                ** GOTO lbl24
                catch (Throwable var0_5) {}
lbl24: // 2 sources:
                d.a((Closeable)var1_7);
                throw var0_4;
            }
            catch (Throwable var0_6) {
                var0_2 = var1_7;
            }
lbl28: // 2 sources:
            d.a((Closeable)var0_2);
            return null;
        }
    }

    public static BitmapDrawable createBitmapByPath2(Context context, String string2, int n2, int n3) {
        synchronized (ViewUtil.class) {
            context = ViewUtil.createBitmapByPath2(context, new File(string2), n2, n3);
            return context;
        }
    }

    public static Drawable createDrawableByPath(Context context, String string2) {
        return ViewUtil.createDrawableByPath(context, string2, true);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static Drawable createDrawableByPath(Context context, String string2, boolean bl2) {
        try {
            if (StringUtils.isNull(string2)) {
                return null;
            }
            if (string2.indexOf(".9.") != -1) {
                Bitmap bitmap = BitmapFactory.decodeFile((String)string2);
                if (bitmap != null) {
                    bitmap.setDensity(ViewUtil.getDensity(context));
                    byte[] arrby = bitmap.getNinePatchChunk();
                    if (NinePatch.isNinePatchChunk((byte[])arrby)) return new NinePatchDrawable(context.getResources(), bitmap, arrby, new Rect(), null);
                    return null;
                }
                if (!bl2) return null;
                throw new Exception(string2);
            }
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            if (!bl2) return null;
            throw new Exception(string2);
        }
        Bitmap bitmap = BitmapFactory.decodeFile((String)string2);
        if (bitmap != null) {
            bitmap.setDensity(ViewUtil.getDensity(context));
            return new BitmapDrawable(context.getResources(), bitmap);
        }
        if (!bl2) return null;
        throw new Exception(string2);
    }

    public static Bitmap createRepeaterX(int n2, Bitmap bitmap) {
        Bitmap bitmap2 = Bitmap.createBitmap((int)(bitmap.getWidth() * n2), (int)bitmap.getHeight(), (Bitmap.Config)Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap2);
        int n3 = 0;
        while (n3 < n2) {
            canvas.drawBitmap(bitmap, (float)(bitmap.getWidth() * n3), 0.0f, null);
            ++n3;
        }
        return bitmap2;
    }

    public static View createView(Context context, int n2) {
        context = new View(context);
        context.setId(n2);
        return context;
    }

    public static View createViewFromResource(Context context, int n2, ViewGroup viewGroup, boolean bl2) {
        try {
            context = ((LayoutInflater)context.getSystemService("layout_inflater")).inflate(n2, viewGroup, bl2);
            return context;
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            return null;
        }
    }

    public static int dp2px(Context context, int n2) {
        return (int)TypedValue.applyDimension((int)1, (float)n2, (DisplayMetrics)context.getResources().getDisplayMetrics());
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static int getChannelType() {
        if (b != -1) return b;
        try {
            KeyManager.initAppKey();
        }
        catch (Throwable var0) {
            var0.printStackTrace();
        }
        if ("NQIDAQABCOOL".equals((Object)KeyManager.channel)) {
            b = 1;
            return b;
        }
        if ("1w36SBLwVNEW_ZTE".equals((Object)KeyManager.channel)) {
            b = 2;
            return b;
        }
        if ("GwIDAQABZTE".equals((Object)KeyManager.channel)) {
            b = 4;
            return b;
        }
        if ("VMhlWdEwVNEW_LENOVO".equals((Object)KeyManager.channel)) {
            b = 3;
            return b;
        }
        if ("Oq3iD6UlMAGIC".equals((Object)KeyManager.channel)) {
            b = 5;
            return b;
        }
        if ("1i1BDH2wONE+".equals((Object)KeyManager.channel) || "1i1BDH2wONE+CARD".equals((Object)KeyManager.channel)) {
            b = 6;
            return b;
        }
        if ("3GdfMSKwHUAWEI".equals((Object)KeyManager.channel)) {
            b = 7;
            return b;
        }
        if ("rq7Fyxl5DUOQU".equals((Object)KeyManager.channel)) {
            b = 8;
            return b;
        }
        if ("j3FIT5mwLETV".equals((Object)KeyManager.channel)) {
            b = 9;
            return b;
        }
        if ("0GCSqGSITOS".equals((Object)KeyManager.channel)) {
            b = 10;
            return b;
        }
        if ("D6mKXM8MEIZU".equals((Object)KeyManager.channel)) {
            b = 11;
            return b;
        }
        if ("XRyvMvZwSMARTISAN".equals((Object)KeyManager.channel)) {
            b = 2;
            return b;
        }
        if ("dToXA5JQDAKELE".equals((Object)KeyManager.channel)) {
            b = 2;
            return b;
        }
        if ("p5O4wKmwGIONEE".equals((Object)KeyManager.channel)) {
            b = 10;
            return b;
        }
        if ("z5N7W51wKINGSUN".equals((Object)KeyManager.channel)) {
            b = 1;
            return b;
        }
        if ("Cko59T6wSUGAR".equals((Object)KeyManager.channel)) {
            b = 12;
            return b;
        }
        if ("oWIH+3ZQLEIDIANOS".equals((Object)KeyManager.channel)) {
            b = 12;
            return b;
        }
        if ("al30zFgQTEST_T".equals((Object)KeyManager.channel)) {
            b = 10;
            return b;
        }
        if ("gsjHPHwIKOOBEE".equals((Object)KeyManager.channel)) {
            b = 12;
            return b;
        }
        if ("AjAFrJSQWENTAI".equals((Object)KeyManager.channel)) {
            b = 12;
            return b;
        }
        if ("JqyMtaHQNUBIA".equals((Object)KeyManager.channel)) {
            b = 12;
            return b;
        }
        if ("15Du354QGIONEECARD".equals((Object)KeyManager.channel)) {
            b = 13;
            return b;
        }
        if ("rahtBH7wTCL".equals((Object)KeyManager.channel)) {
            b = 14;
            return b;
        }
        if ("xU6UT6pwTOS2".equals((Object)KeyManager.channel)) {
            b = 15;
            return b;
        }
        if ("5Gx84kmwYULONG_COOLPAD".equals((Object)KeyManager.channel)) {
            b = 16;
            return b;
        }
        if ("tnjdWFeQKTOUCH".equals((Object)KeyManager.channel)) {
            b = 12;
            return b;
        }
        if ("Uj2pznXQHCT".equals((Object)KeyManager.channel)) {
            b = 12;
            return b;
        }
        if ("XkXZJmwIPPTV".equals((Object)KeyManager.channel)) {
            b = 12;
            return b;
        }
        if ("dGxSiEbwTOSCARD".equals((Object)KeyManager.channel)) {
            b = 17;
            return b;
        }
        if ("PzqP0ONQTOSWATCH".equals((Object)KeyManager.channel)) {
            b = 18;
            return b;
        }
        if ("VCTyBOSwSmartisan".equals((Object)KeyManager.channel)) {
            b = 18;
            return b;
        }
        if ("5rLWVKgQMEITU_PHONE".equals((Object)KeyManager.channel)) {
            b = 12;
            return b;
        }
        b = 0;
        return b;
    }

    public static String getCompletePath(String string2) {
        return String.valueOf((Object)Constant.getDRAWBLE_PATH()) + string2;
    }

    /*
     * Enabled aggressive block sorting
     */
    public static int getDensity(Context context) {
        if (a != -1) return a;
        if (ViewUtil.getChannelType() != 1 && ViewUtil.getChannelType() != 2 && ViewUtil.getChannelType() != 5 && ViewUtil.getChannelType() != 8) {
            context.getResources().getDisplayMetrics();
            a = 240;
            return a;
        }
        context.getResources().getDisplayMetrics();
        a = 480;
        return a;
    }

    public static float getDimension(int n2) {
        try {
            float f2 = Constant.getContext().getResources().getDimension(n2);
            return f2;
        }
        catch (Resources.NotFoundException var2_2) {
            var2_2.printStackTrace();
            return 0.0f;
        }
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static Drawable getDrawable(Context var0, String var1_1, boolean var2_3, boolean var3_4) {
        block13 : {
            block12 : {
                if (var0 == null) {
                    return null;
                }
                if (StringUtils.isNull(var1_1) != false) return null;
                var5_5 = var1_1.trim();
                if (!ViewUtil.isImagePath(var5_5)) break block12;
                if (var3_4 && (var1_1 = ResourceCacheUtil.getImgDrawable(var5_5)) != null && var1_1.getBitmap() != null && !var1_1.getBitmap().isRecycled()) {
                    return var1_1;
                }
                var0 = var4_6 = ViewUtil.createDrawableByPath((Context)var0, ViewUtil.getCompletePath(var5_5));
                if (var3_4 == false) return var0;
                var0 = var4_6;
                var1_1 = var4_6;
                if (var4_6 instanceof BitmapDrawable == false) return var0;
                var1_1 = var4_6;
                ResourceCacheUtil.putImgDrawable(var5_5, (BitmapDrawable)var4_6);
                return var4_6;
            }
            if (!ViewUtil.isColorParam(var5_5)) break block13;
            if (var3_4 && (var1_1 = ResourceCacheUtil.getColorDrawable(var5_5)) != null) {
                return var1_1;
            }
            var0 = var4_6 = a.a((Context)var0, var5_5).a();
            if (var3_4 == false) return var0;
            var0 = var4_6;
            if (var4_6 == null) return var0;
            var1_1 = var4_6;
            ResourceCacheUtil.putColorDrawable(var5_5, var4_6);
            return var4_6;
        }
        if (var2_3 == false) return null;
        if (!var3_4) ** GOTO lbl38
        var0 = ResourceCacheUtil.getColorDrawable(var5_5);
        if (var0 != null) {
            return var0;
        }
lbl38: // 3 sources:
        var0 = var4_6 = new ColorDrawable(ResourceCacheUtil.parseColor(var5_5));
        if (var3_4 == false) return var0;
        var1_1 = var4_6;
        try {
            ResourceCacheUtil.putColorDrawable(var5_5, var4_6);
            return var4_6;
        }
        catch (Throwable var4_7) {
            var0 = var1_1;
            var1_1 = var4_7;
            ** GOTO lbl50
            catch (Throwable var1_2) {
                var0 = null;
            }
lbl50: // 2 sources:
            var1_1.printStackTrace();
            return var0;
        }
    }

    public static String getXCode4(int n2) {
        if (n2 == 1) {
            return "3F3DCX";
        }
        return "363OFT";
    }

    /*
     * Enabled aggressive block sorting
     */
    public static boolean isColorParam(String string2) {
        if (!(StringUtils.isNull(string2) || string2.indexOf(";") == -1 && string2.indexOf("S#") == -1 && string2.indexOf("C#") == -1 && string2.indexOf("E#") == -1)) {
            return true;
        }
        return false;
    }

    public static boolean isImagePath(String string2) {
        if (!StringUtils.isNull(string2) && ((string2 = string2.toLowerCase()).endsWith("png") || string2.endsWith("jpg"))) {
            return true;
        }
        return false;
    }

    public static String readFully(Reader reader) {
        try {
            Object object = new StringWriter();
            char[] arrc = new char[1024];
            do {
                int n2;
                if ((n2 = reader.read(arrc)) == -1) {
                    object = object.toString();
                    return object;
                }
                object.write(arrc, 0, n2);
            } while (true);
        }
        finally {
            reader.close();
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static void recycle(Bitmap bitmap) {
        if (bitmap == null) return;
        try {
            if (bitmap.isRecycled()) return;
            bitmap.recycle();
            return;
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            return;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static void recycle(Drawable object) {
        if (object == null) return;
        object.setCallback(null);
        if (object instanceof BitmapDrawable) {
            ViewUtil.recycle(((BitmapDrawable)object).getBitmap());
            return;
        }
        boolean bl2 = object instanceof NinePatchDrawable;
        if (!bl2) return;
        object = (NinePatchDrawable)object;
        Field field = NinePatchDrawable.class.getDeclaredField("mNinePatch");
        if (field == null) return;
        field.setAccessible(true);
        object = field.get(object);
        if (object == null) return;
        field = NinePatch.class.getDeclaredField("mBitmap");
        if (field == null) return;
        field.setAccessible(true);
        object = (Bitmap)field.get(object);
        if (object == null) return;
        try {
            ViewUtil.recycle((Bitmap)object);
            return;
        }
        catch (Throwable var0_1) {
            try {
                var0_1.printStackTrace();
                return;
            }
            catch (Throwable var0_2) {
                var0_2.printStackTrace();
            }
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static void recycleImageView(ImageView imageView) {
        if (imageView == null) return;
        try {
            Drawable drawable2 = imageView.getDrawable();
            imageView.setImageDrawable(null);
            ViewUtil.recycle(drawable2);
            return;
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            return;
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static void recycleViewBg(View view) {
        if (view == null) return;
        try {
            ViewUtil.recycle(view.getBackground());
            view.setBackgroundDrawable(null);
            return;
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            return;
        }
    }

    public static void setBackground(View view, Drawable drawable2) {
        if (view == null) {
            return;
        }
        if (Build.VERSION.SDK_INT > 16) {
            view.setBackground(drawable2);
            return;
        }
        view.setBackgroundDrawable(drawable2);
    }

    public static void setColor(View view, String string2) {
        view = (GradientDrawable)view.getBackground();
        if (!StringUtils.isNull(string2)) {
            view.setColor(ResourceCacheUtil.parseColor(string2));
        }
    }

    public static void setImageSrc(Context context, ImageView imageView, String string2) {
        ViewUtil.setImageSrc(context, imageView, string2, false);
    }

    public static void setImageSrc(Context context, ImageView imageView, String string2, boolean bl2) {
        imageView.setImageDrawable(ViewUtil.getDrawable(context, string2, true, bl2));
    }

    public static void setTextViewValue(TextView textView, BusinessSmsMessage businessSmsMessage, String string2) {
        ViewUtil.setTextViewValue(textView, businessSmsMessage, string2, "");
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void setTextViewValue(TextView textView, BusinessSmsMessage object, String string2, int n2, int n3, String string3, Context context) {
        if (textView == null || object == null) return;
        {
            try {
                object = (String)object.getValue(string2);
                if (!StringUtils.isNull((String)object)) {
                    if (object.length() > n2) {
                        textView.setTextSize((float)ViewUtil.dp2px(context, n3));
                    }
                    ViewUtil.setTextViewValue(textView, (String)object);
                    return;
                }
                if (!StringUtils.isNull(string3)) {
                    ViewUtil.setTextViewValue(textView, string3);
                    return;
                }
                ViewUtil.setTextViewValue(textView, "");
                return;
            }
            catch (Throwable var0_1) {
                var0_1.printStackTrace();
                return;
            }
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    public static void setTextViewValue(TextView textView, BusinessSmsMessage object, String string2, String object2) {
        if (textView == null) return;
        if (object == null) return;
        try {
            object = (String)object.getValue(string2);
            if (object != null) {
                object2 = object;
            }
            ViewUtil.setTextViewValue(textView, (String)object2);
            return;
        }
        catch (Throwable var0_1) {
            var0_1.printStackTrace();
            return;
        }
    }

    public static void setTextViewValue(TextView textView, String string2) {
        if (textView != null) {
            String string3 = string2;
            if (string2 == null) {
                string3 = "";
            }
            textView.setText((CharSequence)string3);
            if (ViewUtil.getChannelType() == 2) {
                textView.requestLayout();
            }
        }
    }

    public static void setViewBg(Context context, View view, String string2) {
        ViewUtil.setViewBg(context, view, string2, false);
    }

    public static void setViewBg(Context context, View view, String string2, boolean bl2) {
        ViewUtil.setBackground(view, ViewUtil.getDrawable(context, string2, true, bl2));
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static void setViewBg2(Context object, View view, String string2) {
        block8 : {
            block9 : {
                block7 : {
                    if (object != null && view != null) {
                        try {
                            if (StringUtils.isNull(string2)) break block7;
                            if (ViewUtil.isImagePath(string2 = string2.trim())) {
                                view.setBackgroundDrawable(ViewUtil.createDrawableByPath((Context)object, ViewUtil.getCompletePath(string2)));
                                return;
                            }
                            if (!ViewUtil.isColorParam(string2)) break block8;
                            if ((object = a.a((Context)object, string2)) == null || (object = object.a()) == null) break block7;
                            break block9;
                        }
                        catch (Throwable var0_1) {
                            var0_1.printStackTrace();
                            return;
                        }
                    }
                }
                return;
            }
            view.setBackground((Drawable)object);
            return;
        }
        try {
            view.setBackgroundColor(ResourceCacheUtil.parseColor(string2));
            return;
        }
        catch (Throwable var0_2) {
            return;
        }
    }
}

