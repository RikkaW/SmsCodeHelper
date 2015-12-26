package cn.com.xy.sms.sdk.ui.popu.util;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.NinePatch;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.NinePatchDrawable;
import android.os.Build.VERSION;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import cn.com.xy.sms.sdk.constant.Constant;
import cn.com.xy.sms.sdk.smsmessage.BusinessSmsMessage;
import cn.com.xy.sms.sdk.util.KeyManager;
import cn.com.xy.sms.sdk.util.StringUtils;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.nio.charset.Charset;

public class ViewUtil
{
  private static int a = -1;
  private static int b = -1;
  private static final Charset c = Charset.forName("UTF-8");
  
  private static String a(InputStream paramInputStream)
  {
    return readFully(new InputStreamReader(paramInputStream, c));
  }
  
  public static BitmapDrawable createBitmapByPath(Context paramContext, String paramString, int paramInt1, int paramInt2)
  {
    try
    {
      BitmapFactory.Options localOptions = new BitmapFactory.Options();
      inJustDecodeBounds = true;
      BitmapFactory.decodeFile(paramString, localOptions);
      paramInt2 = outWidth;
      int i = outHeight;
      inDensity = paramInt2;
      inTargetDensity = paramInt1;
      inJustDecodeBounds = false;
      paramString = BitmapFactory.decodeFile(paramString, localOptions);
      paramString.setDensity(paramInt2);
      paramContext = new BitmapDrawable(paramContext.getResources(), paramString);
      return paramContext;
    }
    catch (Throwable paramContext) {}
    return null;
  }
  
  /* Error */
  public static BitmapDrawable createBitmapByPath2(Context paramContext, File paramFile, int paramInt1, int paramInt2)
  {
    // Byte code:
    //   0: new 89	java/lang/StringBuilder
    //   3: dup
    //   4: ldc 91
    //   6: invokespecial 94	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   9: aload_1
    //   10: invokevirtual 100	java/io/File:getName	()Ljava/lang/String;
    //   13: invokevirtual 104	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   16: pop
    //   17: new 106	java/io/FileInputStream
    //   20: dup
    //   21: aload_1
    //   22: invokespecial 109	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   25: astore_1
    //   26: aload_1
    //   27: invokevirtual 113	java/io/FileInputStream:getFD	()Ljava/io/FileDescriptor;
    //   30: astore 4
    //   32: new 45	android/graphics/BitmapFactory$Options
    //   35: dup
    //   36: invokespecial 46	android/graphics/BitmapFactory$Options:<init>	()V
    //   39: astore 5
    //   41: aload 5
    //   43: iconst_1
    //   44: putfield 50	android/graphics/BitmapFactory$Options:inJustDecodeBounds	Z
    //   47: aload 4
    //   49: aconst_null
    //   50: aload 5
    //   52: invokestatic 117	android/graphics/BitmapFactory:decodeFileDescriptor	(Ljava/io/FileDescriptor;Landroid/graphics/Rect;Landroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;
    //   55: pop
    //   56: aload 5
    //   58: getfield 59	android/graphics/BitmapFactory$Options:outWidth	I
    //   61: istore_3
    //   62: aload 5
    //   64: iload_3
    //   65: putfield 65	android/graphics/BitmapFactory$Options:inDensity	I
    //   68: aload 5
    //   70: iload_2
    //   71: putfield 68	android/graphics/BitmapFactory$Options:inTargetDensity	I
    //   74: aload 5
    //   76: iconst_0
    //   77: putfield 50	android/graphics/BitmapFactory$Options:inJustDecodeBounds	Z
    //   80: aload 4
    //   82: aconst_null
    //   83: aload 5
    //   85: invokestatic 117	android/graphics/BitmapFactory:decodeFileDescriptor	(Ljava/io/FileDescriptor;Landroid/graphics/Rect;Landroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;
    //   88: astore 4
    //   90: aload 4
    //   92: iload_3
    //   93: invokevirtual 74	android/graphics/Bitmap:setDensity	(I)V
    //   96: new 76	android/graphics/drawable/BitmapDrawable
    //   99: dup
    //   100: aload_0
    //   101: invokevirtual 82	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   104: aload 4
    //   106: invokespecial 85	android/graphics/drawable/BitmapDrawable:<init>	(Landroid/content/res/Resources;Landroid/graphics/Bitmap;)V
    //   109: astore_0
    //   110: aload_1
    //   111: invokestatic 122	cn/com/xy/sms/sdk/util/d:a	(Ljava/io/Closeable;)V
    //   114: aload_0
    //   115: areturn
    //   116: astore_0
    //   117: aconst_null
    //   118: astore_0
    //   119: aload_0
    //   120: invokestatic 122	cn/com/xy/sms/sdk/util/d:a	(Ljava/io/Closeable;)V
    //   123: aconst_null
    //   124: areturn
    //   125: astore_0
    //   126: aconst_null
    //   127: astore_1
    //   128: aload_1
    //   129: invokestatic 122	cn/com/xy/sms/sdk/util/d:a	(Ljava/io/Closeable;)V
    //   132: aload_0
    //   133: athrow
    //   134: astore_0
    //   135: goto -7 -> 128
    //   138: astore_0
    //   139: aload_1
    //   140: astore_0
    //   141: goto -22 -> 119
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	144	0	paramContext	Context
    //   0	144	1	paramFile	File
    //   0	144	2	paramInt1	int
    //   0	144	3	paramInt2	int
    //   30	75	4	localObject	Object
    //   39	45	5	localOptions	BitmapFactory.Options
    // Exception table:
    //   from	to	target	type
    //   0	26	116	java/lang/Throwable
    //   0	26	125	finally
    //   26	110	134	finally
    //   26	110	138	java/lang/Throwable
  }
  
  public static BitmapDrawable createBitmapByPath2(Context paramContext, String paramString, int paramInt1, int paramInt2)
  {
    try
    {
      paramContext = createBitmapByPath2(paramContext, new File(paramString), paramInt1, paramInt2);
      return paramContext;
    }
    finally
    {
      paramContext = finally;
      throw paramContext;
    }
  }
  
  public static Drawable createDrawableByPath(Context paramContext, String paramString)
  {
    return createDrawableByPath(paramContext, paramString, true);
  }
  
  public static Drawable createDrawableByPath(Context paramContext, String paramString, boolean paramBoolean)
  {
    try
    {
      if (StringUtils.isNull(paramString)) {
        return null;
      }
      if (paramString.indexOf(".9.") != -1)
      {
        localBitmap = BitmapFactory.decodeFile(paramString);
        if (localBitmap != null)
        {
          localBitmap.setDensity(getDensity(paramContext));
          byte[] arrayOfByte = localBitmap.getNinePatchChunk();
          if (!NinePatch.isNinePatchChunk(arrayOfByte)) {
            return null;
          }
          paramContext = new NinePatchDrawable(paramContext.getResources(), localBitmap, arrayOfByte, new Rect(), null);
          return paramContext;
        }
      }
    }
    catch (Throwable paramContext)
    {
      Bitmap localBitmap;
      paramContext.printStackTrace();
      if (paramBoolean)
      {
        throw new Exception(paramString);
        if (paramBoolean)
        {
          throw new Exception(paramString);
          localBitmap = BitmapFactory.decodeFile(paramString);
          if (localBitmap == null)
          {
            if (!paramBoolean) {
              break label155;
            }
            throw new Exception(paramString);
          }
          localBitmap.setDensity(getDensity(paramContext));
          paramContext = new BitmapDrawable(paramContext.getResources(), localBitmap);
          return paramContext;
        }
      }
      return null;
    }
    label155:
    return null;
  }
  
  public static Bitmap createRepeaterX(int paramInt, Bitmap paramBitmap)
  {
    Bitmap localBitmap = Bitmap.createBitmap(paramBitmap.getWidth() * paramInt, paramBitmap.getHeight(), Bitmap.Config.ARGB_8888);
    Canvas localCanvas = new Canvas(localBitmap);
    int i = 0;
    for (;;)
    {
      if (i >= paramInt) {
        return localBitmap;
      }
      localCanvas.drawBitmap(paramBitmap, paramBitmap.getWidth() * i, 0.0F, null);
      i += 1;
    }
  }
  
  public static View createView(Context paramContext, int paramInt)
  {
    paramContext = new View(paramContext);
    paramContext.setId(paramInt);
    return paramContext;
  }
  
  public static View createViewFromResource(Context paramContext, int paramInt, ViewGroup paramViewGroup, boolean paramBoolean)
  {
    try
    {
      paramContext = ((LayoutInflater)paramContext.getSystemService("layout_inflater")).inflate(paramInt, paramViewGroup, paramBoolean);
      return paramContext;
    }
    catch (Throwable paramContext)
    {
      paramContext.printStackTrace();
    }
    return null;
  }
  
  public static int dp2px(Context paramContext, int paramInt)
  {
    return (int)TypedValue.applyDimension(1, paramInt, paramContext.getResources().getDisplayMetrics());
  }
  
  public static int getChannelType()
  {
    if (b == -1) {}
    try
    {
      KeyManager.initAppKey();
      if ("NQIDAQABCOOL".equals(KeyManager.channel))
      {
        b = 1;
        return b;
      }
    }
    catch (Throwable localThrowable)
    {
      for (;;)
      {
        localThrowable.printStackTrace();
        continue;
        if ("1w36SBLwVNEW_ZTE".equals(KeyManager.channel)) {
          b = 2;
        } else if ("GwIDAQABZTE".equals(KeyManager.channel)) {
          b = 4;
        } else if ("VMhlWdEwVNEW_LENOVO".equals(KeyManager.channel)) {
          b = 3;
        } else if ("Oq3iD6UlMAGIC".equals(KeyManager.channel)) {
          b = 5;
        } else if (("1i1BDH2wONE+".equals(KeyManager.channel)) || ("1i1BDH2wONE+CARD".equals(KeyManager.channel))) {
          b = 6;
        } else if ("3GdfMSKwHUAWEI".equals(KeyManager.channel)) {
          b = 7;
        } else if ("rq7Fyxl5DUOQU".equals(KeyManager.channel)) {
          b = 8;
        } else if ("j3FIT5mwLETV".equals(KeyManager.channel)) {
          b = 9;
        } else if ("0GCSqGSITOS".equals(KeyManager.channel)) {
          b = 10;
        } else if ("D6mKXM8MEIZU".equals(KeyManager.channel)) {
          b = 11;
        } else if ("XRyvMvZwSMARTISAN".equals(KeyManager.channel)) {
          b = 2;
        } else if ("dToXA5JQDAKELE".equals(KeyManager.channel)) {
          b = 2;
        } else if ("p5O4wKmwGIONEE".equals(KeyManager.channel)) {
          b = 10;
        } else if ("z5N7W51wKINGSUN".equals(KeyManager.channel)) {
          b = 1;
        } else if ("Cko59T6wSUGAR".equals(KeyManager.channel)) {
          b = 12;
        } else if ("oWIH+3ZQLEIDIANOS".equals(KeyManager.channel)) {
          b = 12;
        } else if ("al30zFgQTEST_T".equals(KeyManager.channel)) {
          b = 10;
        } else if ("gsjHPHwIKOOBEE".equals(KeyManager.channel)) {
          b = 12;
        } else if ("AjAFrJSQWENTAI".equals(KeyManager.channel)) {
          b = 12;
        } else if ("JqyMtaHQNUBIA".equals(KeyManager.channel)) {
          b = 12;
        } else if ("15Du354QGIONEECARD".equals(KeyManager.channel)) {
          b = 13;
        } else if ("rahtBH7wTCL".equals(KeyManager.channel)) {
          b = 14;
        } else if ("xU6UT6pwTOS2".equals(KeyManager.channel)) {
          b = 15;
        } else if ("5Gx84kmwYULONG_COOLPAD".equals(KeyManager.channel)) {
          b = 16;
        } else if ("tnjdWFeQKTOUCH".equals(KeyManager.channel)) {
          b = 12;
        } else if ("Uj2pznXQHCT".equals(KeyManager.channel)) {
          b = 12;
        } else if ("XkXZJmwIPPTV".equals(KeyManager.channel)) {
          b = 12;
        } else if ("dGxSiEbwTOSCARD".equals(KeyManager.channel)) {
          b = 17;
        } else if ("PzqP0ONQTOSWATCH".equals(KeyManager.channel)) {
          b = 18;
        } else if ("VCTyBOSwSmartisan".equals(KeyManager.channel)) {
          b = 18;
        } else if ("5rLWVKgQMEITU_PHONE".equals(KeyManager.channel)) {
          b = 12;
        } else {
          b = 0;
        }
      }
    }
  }
  
  public static String getCompletePath(String paramString)
  {
    return Constant.getDRAWBLE_PATH() + paramString;
  }
  
  public static int getDensity(Context paramContext)
  {
    if (a == -1)
    {
      if ((getChannelType() != 1) && (getChannelType() != 2) && (getChannelType() != 5) && (getChannelType() != 8)) {
        break label54;
      }
      paramContext.getResources().getDisplayMetrics();
    }
    for (a = 480;; a = 240)
    {
      return a;
      label54:
      paramContext.getResources().getDisplayMetrics();
    }
  }
  
  public static float getDimension(int paramInt)
  {
    try
    {
      float f = Constant.getContext().getResources().getDimension(paramInt);
      return f;
    }
    catch (Resources.NotFoundException localNotFoundException)
    {
      localNotFoundException.printStackTrace();
    }
    return 0.0F;
  }
  
  public static Drawable getDrawable(Context paramContext, String paramString, boolean paramBoolean1, boolean paramBoolean2)
  {
    if (paramContext == null) {
      return null;
    }
    for (;;)
    {
      try
      {
        if (StringUtils.isNull(paramString)) {
          continue;
        }
        str = paramString.trim();
        if (isImagePath(str))
        {
          if (paramBoolean2)
          {
            paramString = ResourceCacheUtil.getImgDrawable(str);
            if ((paramString != null) && (paramString.getBitmap() != null) && (!paramString.getBitmap().isRecycled())) {
              return paramString;
            }
          }
          localDrawable = createDrawableByPath(paramContext, getCompletePath(str));
          paramContext = localDrawable;
          if (paramBoolean2)
          {
            paramContext = localDrawable;
            paramString = localDrawable;
          }
        }
      }
      catch (Throwable paramString)
      {
        String str;
        Drawable localDrawable;
        ColorDrawable localColorDrawable;
        paramContext = null;
        continue;
        paramContext = null;
        continue;
      }
      try
      {
        if ((localDrawable instanceof BitmapDrawable))
        {
          paramString = localDrawable;
          ResourceCacheUtil.putImgDrawable(str, (BitmapDrawable)localDrawable);
          paramContext = localDrawable;
        }
        return paramContext;
      }
      catch (Throwable localThrowable)
      {
        paramContext = paramString;
        paramString = localThrowable;
      }
      if (isColorParam(str))
      {
        if (paramBoolean2)
        {
          paramString = ResourceCacheUtil.getColorDrawable(str);
          if (paramString != null) {
            return paramString;
          }
        }
        localDrawable = a.a(paramContext, str).a();
        paramContext = localDrawable;
        if (paramBoolean2)
        {
          paramContext = localDrawable;
          if (localDrawable != null)
          {
            paramString = localDrawable;
            ResourceCacheUtil.putColorDrawable(str, localDrawable);
            paramContext = localDrawable;
            continue;
            paramString.printStackTrace();
          }
        }
      }
      else
      {
        if (!paramBoolean1) {
          continue;
        }
        if (paramBoolean2)
        {
          paramContext = ResourceCacheUtil.getColorDrawable(str);
          if (paramContext != null) {
            return paramContext;
          }
        }
        localColorDrawable = new ColorDrawable(ResourceCacheUtil.parseColor(str));
        paramContext = localColorDrawable;
        if (paramBoolean2)
        {
          paramString = localColorDrawable;
          ResourceCacheUtil.putColorDrawable(str, localColorDrawable);
          paramContext = localColorDrawable;
        }
      }
    }
  }
  
  public static String getXCode4(int paramInt)
  {
    if (paramInt == 1) {
      return "3F3DCX";
    }
    return "363OFT";
  }
  
  public static boolean isColorParam(String paramString)
  {
    if (!StringUtils.isNull(paramString))
    {
      if (paramString.indexOf(";") != -1) {}
      while ((paramString.indexOf("S#") != -1) || (paramString.indexOf("C#") != -1) || (paramString.indexOf("E#") != -1)) {
        return true;
      }
    }
    return false;
  }
  
  public static boolean isImagePath(String paramString)
  {
    if (!StringUtils.isNull(paramString))
    {
      paramString = paramString.toLowerCase();
      if ((paramString.endsWith("png")) || (paramString.endsWith("jpg"))) {
        return true;
      }
    }
    return false;
  }
  
  /* Error */
  public static String readFully(java.io.Reader paramReader)
  {
    // Byte code:
    //   0: new 430	java/io/StringWriter
    //   3: dup
    //   4: invokespecial 431	java/io/StringWriter:<init>	()V
    //   7: astore_2
    //   8: sipush 1024
    //   11: newarray <illegal type>
    //   13: astore_3
    //   14: aload_0
    //   15: aload_3
    //   16: invokevirtual 437	java/io/Reader:read	([C)I
    //   19: istore_1
    //   20: iload_1
    //   21: iconst_m1
    //   22: if_icmpne +14 -> 36
    //   25: aload_2
    //   26: invokevirtual 438	java/io/StringWriter:toString	()Ljava/lang/String;
    //   29: astore_2
    //   30: aload_0
    //   31: invokevirtual 441	java/io/Reader:close	()V
    //   34: aload_2
    //   35: areturn
    //   36: aload_2
    //   37: aload_3
    //   38: iconst_0
    //   39: iload_1
    //   40: invokevirtual 445	java/io/StringWriter:write	([CII)V
    //   43: goto -29 -> 14
    //   46: astore_2
    //   47: aload_0
    //   48: invokevirtual 441	java/io/Reader:close	()V
    //   51: aload_2
    //   52: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	53	0	paramReader	java.io.Reader
    //   19	21	1	i	int
    //   7	30	2	localObject1	Object
    //   46	6	2	localObject2	Object
    //   13	25	3	arrayOfChar	char[]
    // Exception table:
    //   from	to	target	type
    //   0	14	46	finally
    //   14	20	46	finally
    //   25	30	46	finally
    //   36	43	46	finally
  }
  
  public static void recycle(Bitmap paramBitmap)
  {
    if (paramBitmap != null) {}
    try
    {
      if (!paramBitmap.isRecycled()) {
        paramBitmap.recycle();
      }
      return;
    }
    catch (Throwable paramBitmap)
    {
      paramBitmap.printStackTrace();
    }
  }
  
  public static void recycle(Drawable paramDrawable)
  {
    if (paramDrawable != null) {
      try
      {
        paramDrawable.setCallback(null);
        if ((paramDrawable instanceof BitmapDrawable))
        {
          recycle(((BitmapDrawable)paramDrawable).getBitmap());
          return;
        }
        boolean bool = paramDrawable instanceof NinePatchDrawable;
        if (bool) {
          try
          {
            paramDrawable = (NinePatchDrawable)paramDrawable;
            Field localField = NinePatchDrawable.class.getDeclaredField("mNinePatch");
            if (localField != null)
            {
              localField.setAccessible(true);
              paramDrawable = localField.get(paramDrawable);
              if (paramDrawable != null)
              {
                localField = NinePatch.class.getDeclaredField("mBitmap");
                if (localField != null)
                {
                  localField.setAccessible(true);
                  paramDrawable = (Bitmap)localField.get(paramDrawable);
                  if (paramDrawable != null)
                  {
                    recycle(paramDrawable);
                    return;
                  }
                }
              }
            }
          }
          catch (Throwable paramDrawable)
          {
            paramDrawable.printStackTrace();
            return;
          }
        }
        return;
      }
      catch (Throwable paramDrawable)
      {
        paramDrawable.printStackTrace();
      }
    }
  }
  
  public static void recycleImageView(ImageView paramImageView)
  {
    if (paramImageView != null) {}
    try
    {
      Drawable localDrawable = paramImageView.getDrawable();
      paramImageView.setImageDrawable(null);
      recycle(localDrawable);
      return;
    }
    catch (Throwable paramImageView)
    {
      paramImageView.printStackTrace();
    }
  }
  
  public static void recycleViewBg(View paramView)
  {
    if (paramView != null) {}
    try
    {
      recycle(paramView.getBackground());
      paramView.setBackgroundDrawable(null);
      return;
    }
    catch (Throwable paramView)
    {
      paramView.printStackTrace();
    }
  }
  
  public static void setBackground(View paramView, Drawable paramDrawable)
  {
    if (paramView == null) {
      return;
    }
    if (Build.VERSION.SDK_INT > 16)
    {
      paramView.setBackground(paramDrawable);
      return;
    }
    paramView.setBackgroundDrawable(paramDrawable);
  }
  
  public static void setColor(View paramView, String paramString)
  {
    paramView = (GradientDrawable)paramView.getBackground();
    if (!StringUtils.isNull(paramString)) {
      paramView.setColor(ResourceCacheUtil.parseColor(paramString));
    }
  }
  
  public static void setImageSrc(Context paramContext, ImageView paramImageView, String paramString)
  {
    setImageSrc(paramContext, paramImageView, paramString, false);
  }
  
  public static void setImageSrc(Context paramContext, ImageView paramImageView, String paramString, boolean paramBoolean)
  {
    paramImageView.setImageDrawable(getDrawable(paramContext, paramString, true, paramBoolean));
  }
  
  public static void setTextViewValue(TextView paramTextView, BusinessSmsMessage paramBusinessSmsMessage, String paramString)
  {
    setTextViewValue(paramTextView, paramBusinessSmsMessage, paramString, "");
  }
  
  public static void setTextViewValue(TextView paramTextView, BusinessSmsMessage paramBusinessSmsMessage, String paramString1, int paramInt1, int paramInt2, String paramString2, Context paramContext)
  {
    if ((paramTextView != null) && (paramBusinessSmsMessage != null))
    {
      try
      {
        paramBusinessSmsMessage = (String)paramBusinessSmsMessage.getValue(paramString1);
        if (!StringUtils.isNull(paramBusinessSmsMessage))
        {
          if (paramBusinessSmsMessage.length() > paramInt1) {
            paramTextView.setTextSize(dp2px(paramContext, paramInt2));
          }
          setTextViewValue(paramTextView, paramBusinessSmsMessage);
          return;
        }
        if (!StringUtils.isNull(paramString2))
        {
          setTextViewValue(paramTextView, paramString2);
          return;
        }
      }
      catch (Throwable paramTextView)
      {
        paramTextView.printStackTrace();
        return;
      }
      setTextViewValue(paramTextView, "");
    }
  }
  
  public static void setTextViewValue(TextView paramTextView, BusinessSmsMessage paramBusinessSmsMessage, String paramString1, String paramString2)
  {
    if ((paramTextView != null) && (paramBusinessSmsMessage != null)) {}
    try
    {
      paramBusinessSmsMessage = (String)paramBusinessSmsMessage.getValue(paramString1);
      if (paramBusinessSmsMessage == null) {}
      for (;;)
      {
        setTextViewValue(paramTextView, paramString2);
        return;
        paramString2 = paramBusinessSmsMessage;
      }
      return;
    }
    catch (Throwable paramTextView)
    {
      paramTextView.printStackTrace();
    }
  }
  
  public static void setTextViewValue(TextView paramTextView, String paramString)
  {
    if (paramTextView != null)
    {
      String str = paramString;
      if (paramString == null) {
        str = "";
      }
      paramTextView.setText(str);
      if (getChannelType() == 2) {
        paramTextView.requestLayout();
      }
    }
  }
  
  public static void setViewBg(Context paramContext, View paramView, String paramString)
  {
    setViewBg(paramContext, paramView, paramString, false);
  }
  
  public static void setViewBg(Context paramContext, View paramView, String paramString, boolean paramBoolean)
  {
    setBackground(paramView, getDrawable(paramContext, paramString, true, paramBoolean));
  }
  
  public static void setViewBg2(Context paramContext, View paramView, String paramString)
  {
    if (paramContext == null) {}
    do
    {
      do
      {
        for (;;)
        {
          return;
          if (paramView != null) {
            try
            {
              if (!StringUtils.isNull(paramString))
              {
                paramString = paramString.trim();
                if (isImagePath(paramString))
                {
                  paramView.setBackgroundDrawable(createDrawableByPath(paramContext, getCompletePath(paramString)));
                  return;
                }
              }
            }
            catch (Throwable paramContext)
            {
              paramContext.printStackTrace();
              return;
            }
          }
        }
        if (!isColorParam(paramString)) {
          break;
        }
        paramContext = a.a(paramContext, paramString);
      } while (paramContext == null);
      paramContext = paramContext.a();
    } while (paramContext == null);
    paramView.setBackground(paramContext);
    return;
    try
    {
      paramView.setBackgroundColor(ResourceCacheUtil.parseColor(paramString));
      return;
    }
    catch (Throwable paramContext) {}
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.ui.popu.util.ViewUtil
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */