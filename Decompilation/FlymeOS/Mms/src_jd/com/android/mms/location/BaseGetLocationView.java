package com.android.mms.location;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import java.util.ArrayList;
import kf;
import ko;
import kp;
import kq;
import kr;
import kt;
import kv;
import kw;

public abstract class BaseGetLocationView
  extends RelativeLayout
{
  public static int f = 19;
  protected ImageView a;
  protected ImageView b;
  public kt c;
  public boolean d = false;
  public boolean e = false;
  public SharedPreferences g;
  public kf h;
  protected String i;
  protected String j;
  protected String k;
  protected String l;
  public kw m;
  public final Object n = new Object();
  public final Object o = new Object();
  protected ProgressDialog p;
  public boolean q = false;
  public ArrayList<kr> r = new ArrayList();
  public Context s;
  public Handler t = new kp(this);
  public Runnable u = new kq(this);
  
  public BaseGetLocationView(Context paramContext)
  {
    this(paramContext, null);
    s = paramContext;
  }
  
  public BaseGetLocationView(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
    s = paramContext;
  }
  
  public BaseGetLocationView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    s = paramContext;
  }
  
  public void a()
  {
    h.h();
  }
  
  public void a(double paramDouble1, double paramDouble2)
  {
    SharedPreferences.Editor localEditor = g.edit();
    localEditor.putInt("last_latitude_baidu", (int)paramDouble1);
    localEditor.putInt("last_latitude_baidu", (int)paramDouble2);
    localEditor.apply();
    g = null;
  }
  
  public void a(int paramInt) {}
  
  public void a(int paramInt, String paramString)
  {
    if ((p != null) && (p.isShowing()))
    {
      p.dismiss();
      p = null;
    }
    h.a(paramInt);
  }
  
  /* Error */
  public void a(android.graphics.Bitmap paramBitmap)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 6
    //   3: aconst_null
    //   4: astore_2
    //   5: aconst_null
    //   6: astore 9
    //   8: aconst_null
    //   9: astore 10
    //   11: aconst_null
    //   12: astore 8
    //   14: aconst_null
    //   15: astore 7
    //   17: aload_0
    //   18: getfield 60	com/android/mms/location/BaseGetLocationView:e	Z
    //   21: ifeq +9 -> 30
    //   24: aload_0
    //   25: iconst_0
    //   26: putfield 60	com/android/mms/location/BaseGetLocationView:e	Z
    //   29: return
    //   30: iconst_1
    //   31: iconst_4
    //   32: ldc -121
    //   34: ldc -119
    //   36: invokestatic 140	kf:a	(ZILjava/lang/String;Ljava/lang/String;)V
    //   39: aload_1
    //   40: invokevirtual 146	android/graphics/Bitmap:getWidth	()I
    //   43: aload_1
    //   44: invokevirtual 149	android/graphics/Bitmap:getHeight	()I
    //   47: getstatic 155	android/graphics/Bitmap$Config:ARGB_8888	Landroid/graphics/Bitmap$Config;
    //   50: invokestatic 159	android/graphics/Bitmap:createBitmap	(IILandroid/graphics/Bitmap$Config;)Landroid/graphics/Bitmap;
    //   53: astore_3
    //   54: aload_3
    //   55: astore 4
    //   57: aload 9
    //   59: astore_2
    //   60: aload_0
    //   61: getfield 161	com/android/mms/location/BaseGetLocationView:a	Landroid/widget/ImageView;
    //   64: ifnull +100 -> 164
    //   67: aload_3
    //   68: astore 4
    //   70: aload 9
    //   72: astore_2
    //   73: new 163	android/graphics/Canvas
    //   76: dup
    //   77: aload_3
    //   78: invokespecial 165	android/graphics/Canvas:<init>	(Landroid/graphics/Bitmap;)V
    //   81: astore 5
    //   83: aload_3
    //   84: astore 4
    //   86: aload 9
    //   88: astore_2
    //   89: aload 5
    //   91: aload_1
    //   92: fconst_0
    //   93: fconst_0
    //   94: aconst_null
    //   95: invokevirtual 169	android/graphics/Canvas:drawBitmap	(Landroid/graphics/Bitmap;FFLandroid/graphics/Paint;)V
    //   98: aload_3
    //   99: astore 4
    //   101: aload 9
    //   103: astore_2
    //   104: aload_0
    //   105: getfield 52	com/android/mms/location/BaseGetLocationView:s	Landroid/content/Context;
    //   108: invokevirtual 175	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   111: ldc -80
    //   113: invokevirtual 182	android/content/res/Resources:getDrawable	(I)Landroid/graphics/drawable/Drawable;
    //   116: checkcast 184	android/graphics/drawable/BitmapDrawable
    //   119: invokevirtual 188	android/graphics/drawable/BitmapDrawable:getBitmap	()Landroid/graphics/Bitmap;
    //   122: astore 11
    //   124: aload_3
    //   125: astore 4
    //   127: aload 9
    //   129: astore_2
    //   130: aload 5
    //   132: aload 11
    //   134: aload_1
    //   135: invokevirtual 146	android/graphics/Bitmap:getWidth	()I
    //   138: aload 11
    //   140: invokevirtual 146	android/graphics/Bitmap:getWidth	()I
    //   143: isub
    //   144: iconst_2
    //   145: idiv
    //   146: i2f
    //   147: aload_1
    //   148: invokevirtual 149	android/graphics/Bitmap:getHeight	()I
    //   151: iconst_2
    //   152: idiv
    //   153: aload 11
    //   155: invokevirtual 149	android/graphics/Bitmap:getHeight	()I
    //   158: isub
    //   159: i2f
    //   160: aconst_null
    //   161: invokevirtual 169	android/graphics/Canvas:drawBitmap	(Landroid/graphics/Bitmap;FFLandroid/graphics/Paint;)V
    //   164: aload_3
    //   165: astore 4
    //   167: aload 9
    //   169: astore_2
    //   170: new 190	java/lang/StringBuilder
    //   173: dup
    //   174: invokespecial 191	java/lang/StringBuilder:<init>	()V
    //   177: ldc -63
    //   179: invokevirtual 197	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   182: invokestatic 203	java/lang/System:currentTimeMillis	()J
    //   185: invokevirtual 206	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   188: ldc -48
    //   190: invokevirtual 197	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   193: invokevirtual 212	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   196: astore 5
    //   198: aload_3
    //   199: astore 4
    //   201: aload 9
    //   203: astore_2
    //   204: aload 10
    //   206: astore 6
    //   208: aload 8
    //   210: astore 7
    //   212: aload_0
    //   213: getfield 52	com/android/mms/location/BaseGetLocationView:s	Landroid/content/Context;
    //   216: invokevirtual 216	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   219: new 218	java/io/File
    //   222: dup
    //   223: aload 5
    //   225: invokespecial 221	java/io/File:<init>	(Ljava/lang/String;)V
    //   228: invokestatic 227	android/net/Uri:fromFile	(Ljava/io/File;)Landroid/net/Uri;
    //   231: invokevirtual 233	android/content/ContentResolver:openOutputStream	(Landroid/net/Uri;)Ljava/io/OutputStream;
    //   234: astore 8
    //   236: aload_3
    //   237: astore 4
    //   239: aload 8
    //   241: astore_2
    //   242: aload 8
    //   244: astore 6
    //   246: aload 8
    //   248: astore 7
    //   250: aload_3
    //   251: getstatic 239	android/graphics/Bitmap$CompressFormat:JPEG	Landroid/graphics/Bitmap$CompressFormat;
    //   254: bipush 98
    //   256: aload 8
    //   258: invokevirtual 243	android/graphics/Bitmap:compress	(Landroid/graphics/Bitmap$CompressFormat;ILjava/io/OutputStream;)Z
    //   261: ifeq +41 -> 302
    //   264: aload_3
    //   265: astore 4
    //   267: aload 8
    //   269: astore_2
    //   270: aload 8
    //   272: astore 6
    //   274: aload 8
    //   276: astore 7
    //   278: aload 8
    //   280: invokevirtual 248	java/io/OutputStream:flush	()V
    //   283: aload_3
    //   284: astore 4
    //   286: aload 8
    //   288: astore_2
    //   289: aload 8
    //   291: astore 6
    //   293: aload 8
    //   295: astore 7
    //   297: aload 8
    //   299: invokevirtual 251	java/io/OutputStream:close	()V
    //   302: aload_1
    //   303: ifnull +7 -> 310
    //   306: aload_1
    //   307: invokevirtual 254	android/graphics/Bitmap:recycle	()V
    //   310: aload_3
    //   311: ifnull +7 -> 318
    //   314: aload_3
    //   315: invokevirtual 254	android/graphics/Bitmap:recycle	()V
    //   318: aload 5
    //   320: astore_1
    //   321: aload 8
    //   323: ifnull +11 -> 334
    //   326: aload 8
    //   328: invokevirtual 251	java/io/OutputStream:close	()V
    //   331: aload 5
    //   333: astore_1
    //   334: aload_0
    //   335: getfield 117	com/android/mms/location/BaseGetLocationView:p	Landroid/app/ProgressDialog;
    //   338: ifnull +21 -> 359
    //   341: aload_0
    //   342: getfield 117	com/android/mms/location/BaseGetLocationView:p	Landroid/app/ProgressDialog;
    //   345: invokevirtual 126	android/app/ProgressDialog:dismiss	()V
    //   348: aload_0
    //   349: getfield 82	com/android/mms/location/BaseGetLocationView:t	Landroid/os/Handler;
    //   352: aload_0
    //   353: getfield 87	com/android/mms/location/BaseGetLocationView:u	Ljava/lang/Runnable;
    //   356: invokevirtual 260	android/os/Handler:removeCallbacks	(Ljava/lang/Runnable;)V
    //   359: aload_0
    //   360: getfield 60	com/android/mms/location/BaseGetLocationView:e	Z
    //   363: ifne +351 -> 714
    //   366: aload_0
    //   367: iconst_1
    //   368: aload_1
    //   369: invokevirtual 263	com/android/mms/location/BaseGetLocationView:a	(ZLjava/lang/String;)V
    //   372: return
    //   373: astore_1
    //   374: iconst_1
    //   375: bipush 6
    //   377: ldc -121
    //   379: new 190	java/lang/StringBuilder
    //   382: dup
    //   383: invokespecial 191	java/lang/StringBuilder:<init>	()V
    //   386: ldc_w 265
    //   389: invokevirtual 197	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   392: aload_1
    //   393: invokevirtual 268	java/io/IOException:getMessage	()Ljava/lang/String;
    //   396: invokevirtual 197	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   399: invokevirtual 212	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   402: invokestatic 140	kf:a	(ZILjava/lang/String;Ljava/lang/String;)V
    //   405: aload 5
    //   407: astore_1
    //   408: goto -74 -> 334
    //   411: astore 6
    //   413: aconst_null
    //   414: astore_3
    //   415: aconst_null
    //   416: astore 5
    //   418: aload_3
    //   419: astore 4
    //   421: aload 7
    //   423: astore_2
    //   424: iconst_1
    //   425: bipush 6
    //   427: ldc -121
    //   429: new 190	java/lang/StringBuilder
    //   432: dup
    //   433: invokespecial 191	java/lang/StringBuilder:<init>	()V
    //   436: ldc_w 265
    //   439: invokevirtual 197	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   442: aload 6
    //   444: invokevirtual 269	java/io/FileNotFoundException:getMessage	()Ljava/lang/String;
    //   447: invokevirtual 197	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   450: invokevirtual 212	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   453: invokestatic 140	kf:a	(ZILjava/lang/String;Ljava/lang/String;)V
    //   456: aload_1
    //   457: ifnull +7 -> 464
    //   460: aload_1
    //   461: invokevirtual 254	android/graphics/Bitmap:recycle	()V
    //   464: aload_3
    //   465: ifnull +7 -> 472
    //   468: aload_3
    //   469: invokevirtual 254	android/graphics/Bitmap:recycle	()V
    //   472: aload 5
    //   474: astore_1
    //   475: aload 7
    //   477: ifnull -143 -> 334
    //   480: aload 7
    //   482: invokevirtual 251	java/io/OutputStream:close	()V
    //   485: aload 5
    //   487: astore_1
    //   488: goto -154 -> 334
    //   491: astore_1
    //   492: iconst_1
    //   493: bipush 6
    //   495: ldc -121
    //   497: new 190	java/lang/StringBuilder
    //   500: dup
    //   501: invokespecial 191	java/lang/StringBuilder:<init>	()V
    //   504: ldc_w 265
    //   507: invokevirtual 197	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   510: aload_1
    //   511: invokevirtual 268	java/io/IOException:getMessage	()Ljava/lang/String;
    //   514: invokevirtual 197	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   517: invokevirtual 212	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   520: invokestatic 140	kf:a	(ZILjava/lang/String;Ljava/lang/String;)V
    //   523: aload 5
    //   525: astore_1
    //   526: goto -192 -> 334
    //   529: astore 7
    //   531: aconst_null
    //   532: astore_3
    //   533: aconst_null
    //   534: astore 5
    //   536: aload_3
    //   537: astore 4
    //   539: aload 6
    //   541: astore_2
    //   542: iconst_1
    //   543: bipush 6
    //   545: ldc -121
    //   547: new 190	java/lang/StringBuilder
    //   550: dup
    //   551: invokespecial 191	java/lang/StringBuilder:<init>	()V
    //   554: ldc_w 265
    //   557: invokevirtual 197	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   560: aload 7
    //   562: invokevirtual 268	java/io/IOException:getMessage	()Ljava/lang/String;
    //   565: invokevirtual 197	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   568: invokevirtual 212	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   571: invokestatic 140	kf:a	(ZILjava/lang/String;Ljava/lang/String;)V
    //   574: aload_1
    //   575: ifnull +7 -> 582
    //   578: aload_1
    //   579: invokevirtual 254	android/graphics/Bitmap:recycle	()V
    //   582: aload_3
    //   583: ifnull +7 -> 590
    //   586: aload_3
    //   587: invokevirtual 254	android/graphics/Bitmap:recycle	()V
    //   590: aload 5
    //   592: astore_1
    //   593: aload 6
    //   595: ifnull -261 -> 334
    //   598: aload 6
    //   600: invokevirtual 251	java/io/OutputStream:close	()V
    //   603: aload 5
    //   605: astore_1
    //   606: goto -272 -> 334
    //   609: astore_1
    //   610: iconst_1
    //   611: bipush 6
    //   613: ldc -121
    //   615: new 190	java/lang/StringBuilder
    //   618: dup
    //   619: invokespecial 191	java/lang/StringBuilder:<init>	()V
    //   622: ldc_w 265
    //   625: invokevirtual 197	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   628: aload_1
    //   629: invokevirtual 268	java/io/IOException:getMessage	()Ljava/lang/String;
    //   632: invokevirtual 197	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   635: invokevirtual 212	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   638: invokestatic 140	kf:a	(ZILjava/lang/String;Ljava/lang/String;)V
    //   641: aload 5
    //   643: astore_1
    //   644: goto -310 -> 334
    //   647: astore_3
    //   648: aconst_null
    //   649: astore 4
    //   651: aload_1
    //   652: ifnull +7 -> 659
    //   655: aload_1
    //   656: invokevirtual 254	android/graphics/Bitmap:recycle	()V
    //   659: aload 4
    //   661: ifnull +8 -> 669
    //   664: aload 4
    //   666: invokevirtual 254	android/graphics/Bitmap:recycle	()V
    //   669: aload_2
    //   670: ifnull +7 -> 677
    //   673: aload_2
    //   674: invokevirtual 251	java/io/OutputStream:close	()V
    //   677: aload_3
    //   678: athrow
    //   679: astore_1
    //   680: iconst_1
    //   681: bipush 6
    //   683: ldc -121
    //   685: new 190	java/lang/StringBuilder
    //   688: dup
    //   689: invokespecial 191	java/lang/StringBuilder:<init>	()V
    //   692: ldc_w 265
    //   695: invokevirtual 197	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   698: aload_1
    //   699: invokevirtual 268	java/io/IOException:getMessage	()Ljava/lang/String;
    //   702: invokevirtual 197	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   705: invokevirtual 212	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   708: invokestatic 140	kf:a	(ZILjava/lang/String;Ljava/lang/String;)V
    //   711: goto -34 -> 677
    //   714: aload_0
    //   715: iconst_0
    //   716: putfield 60	com/android/mms/location/BaseGetLocationView:e	Z
    //   719: return
    //   720: astore_3
    //   721: goto -70 -> 651
    //   724: astore 7
    //   726: aconst_null
    //   727: astore 5
    //   729: goto -193 -> 536
    //   732: astore 7
    //   734: goto -198 -> 536
    //   737: astore 6
    //   739: aconst_null
    //   740: astore 5
    //   742: goto -324 -> 418
    //   745: astore 6
    //   747: goto -329 -> 418
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	750	0	this	BaseGetLocationView
    //   0	750	1	paramBitmap	android.graphics.Bitmap
    //   4	670	2	localObject1	Object
    //   53	534	3	localBitmap1	android.graphics.Bitmap
    //   647	31	3	localObject2	Object
    //   720	1	3	localObject3	Object
    //   55	610	4	localBitmap2	android.graphics.Bitmap
    //   81	660	5	localObject4	Object
    //   1	291	6	localObject5	Object
    //   411	188	6	localFileNotFoundException1	java.io.FileNotFoundException
    //   737	1	6	localFileNotFoundException2	java.io.FileNotFoundException
    //   745	1	6	localFileNotFoundException3	java.io.FileNotFoundException
    //   15	466	7	localOutputStream1	java.io.OutputStream
    //   529	32	7	localIOException1	java.io.IOException
    //   724	1	7	localIOException2	java.io.IOException
    //   732	1	7	localIOException3	java.io.IOException
    //   12	315	8	localOutputStream2	java.io.OutputStream
    //   6	196	9	localObject6	Object
    //   9	196	10	localObject7	Object
    //   122	32	11	localBitmap3	android.graphics.Bitmap
    // Exception table:
    //   from	to	target	type
    //   326	331	373	java/io/IOException
    //   30	54	411	java/io/FileNotFoundException
    //   480	485	491	java/io/IOException
    //   30	54	529	java/io/IOException
    //   598	603	609	java/io/IOException
    //   30	54	647	finally
    //   673	677	679	java/io/IOException
    //   60	67	720	finally
    //   73	83	720	finally
    //   89	98	720	finally
    //   104	124	720	finally
    //   130	164	720	finally
    //   170	198	720	finally
    //   212	236	720	finally
    //   250	264	720	finally
    //   278	283	720	finally
    //   297	302	720	finally
    //   424	456	720	finally
    //   542	574	720	finally
    //   60	67	724	java/io/IOException
    //   73	83	724	java/io/IOException
    //   89	98	724	java/io/IOException
    //   104	124	724	java/io/IOException
    //   130	164	724	java/io/IOException
    //   170	198	724	java/io/IOException
    //   212	236	732	java/io/IOException
    //   250	264	732	java/io/IOException
    //   278	283	732	java/io/IOException
    //   297	302	732	java/io/IOException
    //   60	67	737	java/io/FileNotFoundException
    //   73	83	737	java/io/FileNotFoundException
    //   89	98	737	java/io/FileNotFoundException
    //   104	124	737	java/io/FileNotFoundException
    //   130	164	737	java/io/FileNotFoundException
    //   170	198	737	java/io/FileNotFoundException
    //   212	236	745	java/io/FileNotFoundException
    //   250	264	745	java/io/FileNotFoundException
    //   278	283	745	java/io/FileNotFoundException
    //   297	302	745	java/io/FileNotFoundException
  }
  
  public void a(CharSequence paramCharSequence)
  {
    m.b(paramCharSequence);
  }
  
  public void a(String paramString)
  {
    i = paramString;
  }
  
  public void a(kr paramkr) {}
  
  public void a(kr paramkr1, kr paramkr2)
  {
    if ((paramkr1 != null) && (paramkr2 != null))
    {
      c();
      r.add(0, paramkr2);
      if (b(paramkr1))
      {
        paramkr2.b(true);
        h.b(0);
      }
      for (;;)
      {
        h.p().notifyDataSetChanged();
        return;
        paramkr2.b(false);
        paramkr1.b(true);
        r.add(1, paramkr1);
        h.b(1);
      }
    }
    h.b(-1);
  }
  
  public void a(kt paramkt)
  {
    g = PreferenceManager.getDefaultSharedPreferences(s);
  }
  
  public void a(boolean paramBoolean)
  {
    b.setEnabled(paramBoolean);
  }
  
  public final void a(boolean paramBoolean, String paramString)
  {
    Intent localIntent = new Intent();
    Log.i("location/BaseGetLocationView", "sendMessage addPic = " + paramBoolean + ", tempPicPath = " + paramString);
    if (paramBoolean)
    {
      localIntent.setData(Uri.parse(paramString));
      localIntent.putExtra("location_shortaddress", l);
      if (!TextUtils.isEmpty(j)) {
        break label132;
      }
      localIntent.putExtra("location_content", i);
    }
    for (;;)
    {
      localIntent.putExtra("send_out_directly", h.f());
      h.setResult(-1, localIntent);
      h.finish();
      return;
      localIntent.setData(null);
      break;
      label132:
      if (j.equals(i)) {
        localIntent.putExtra("location_content", i);
      } else {
        localIntent.putExtra("location_content", i + ", " + j);
      }
    }
  }
  
  public void a(boolean paramBoolean, kr paramkr)
  {
    if (paramkr != null)
    {
      c();
      r.add(0, paramkr);
      Log.i("location/BaseGetLocationView", "BaseGetLocationView addFirstLocation isCurrentCenter = " + paramBoolean);
      if (!paramBoolean) {
        break label66;
      }
      h.b(0);
    }
    for (;;)
    {
      h.p().notifyDataSetChanged();
      return;
      label66:
      paramkr.b(false);
    }
  }
  
  public void b(int paramInt) {}
  
  public void b(String paramString)
  {
    j = paramString;
  }
  
  public void b(kt paramkt)
  {
    c = paramkt;
    m = new kw(paramkt);
    m.a(this);
  }
  
  public void b(boolean paramBoolean) {}
  
  public final boolean b()
  {
    if (h == null) {
      return false;
    }
    return h.e();
  }
  
  public boolean b(kr paramkr)
  {
    return false;
  }
  
  public void c()
  {
    h.b(-1);
    h.p().clear();
  }
  
  public abstract void c(String paramString);
  
  public void d(String paramString) {}
  
  public boolean d()
  {
    return (h != null) && (h.c());
  }
  
  public void e() {}
  
  public void f()
  {
    if (m != null)
    {
      m.b();
      m = null;
    }
    if ((p != null) && (p.isShowing())) {
      p.dismiss();
    }
    c();
    t.removeCallbacks(u);
  }
  
  public void g()
  {
    kf.a(true, 4, "location/BaseGetLocationView", "onConfirmItemSelected");
  }
  
  public String getAddress()
  {
    return j;
  }
  
  public ArrayList<kr> getPoiList()
  {
    return r;
  }
  
  public String getPointName()
  {
    return i;
  }
  
  public void h() {}
  
  public void i() {}
  
  public void j()
  {
    if (p == null) {
      p = new ProgressDialog(s);
    }
    p.setIndeterminate(true);
    p.setCancelable(false);
    p.setMessage(s.getText(2131493822));
    e = false;
    t.postDelayed(u, 11000L);
    p.show();
  }
  
  public boolean k()
  {
    kf.a(true, 4, "location/BaseGetLocationView", "isHasDestory mHasDestory = " + q);
    return q;
  }
  
  public void l()
  {
    if ((TextUtils.isEmpty(i)) || (i.equals(s.getString(2131493350))))
    {
      d = false;
      return;
    }
    d = true;
  }
  
  protected void onDetachedFromWindow()
  {
    super.onDetachedFromWindow();
    synchronized (n)
    {
      n.notifyAll();
      if (m != null) {
        m.a();
      }
      return;
    }
  }
  
  public void onFinishInflate()
  {
    a = ((ImageView)findViewById(2131886582));
    b = ((ImageView)findViewById(2131886583));
    b.setOnClickListener(new ko(this));
  }
  
  public void setActivity(kf paramkf)
  {
    h = paramkf;
  }
  
  public void setFar(String paramString)
  {
    k = paramString;
  }
  
  public void setHasChooseAddress(boolean paramBoolean)
  {
    d = paramBoolean;
  }
  
  public void setHasDestory(boolean paramBoolean)
  {
    q = paramBoolean;
  }
  
  public void setIsTimeout(boolean paramBoolean)
  {
    e = paramBoolean;
  }
  
  public void setUrl(String paramString)
  {
    l = paramString;
  }
}

/* Location:
 * Qualified Name:     com.android.mms.location.BaseGetLocationView
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */