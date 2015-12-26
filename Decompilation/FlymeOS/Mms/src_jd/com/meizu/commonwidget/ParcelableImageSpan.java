package com.meizu.commonwidget;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.FontMetricsInt;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.text.ParcelableSpan;
import android.text.style.ReplacementSpan;
import java.lang.ref.WeakReference;

public class ParcelableImageSpan
  extends ReplacementSpan
  implements ParcelableSpan
{
  private Paint.FontMetricsInt a;
  private final int b;
  private final int c;
  private final int d;
  private final int e;
  private final int f;
  private final int g;
  private WeakReference<Drawable> h;
  
  private Paint.FontMetricsInt a(Paint paramPaint)
  {
    if (a == null) {
      a = paramPaint.getFontMetricsInt();
    }
    for (;;)
    {
      return a;
      paramPaint.getFontMetricsInt(a);
    }
  }
  
  private Drawable c()
  {
    Object localObject = h;
    Drawable localDrawable = null;
    if (localObject != null) {
      localDrawable = (Drawable)((WeakReference)localObject).get();
    }
    localObject = localDrawable;
    if (localDrawable == null)
    {
      localObject = a();
      h = new WeakReference(localObject);
    }
    return (Drawable)localObject;
  }
  
  /* Error */
  public Drawable a()
  {
    // Byte code:
    //   0: invokestatic 56	android/content/res/Resources:getSystem	()Landroid/content/res/Resources;
    //   3: aload_0
    //   4: getfield 58	com/meizu/commonwidget/ParcelableImageSpan:b	I
    //   7: invokevirtual 62	android/content/res/Resources:getDrawable	(I)Landroid/graphics/drawable/Drawable;
    //   10: astore 7
    //   12: aload 7
    //   14: invokevirtual 66	android/graphics/drawable/Drawable:getIntrinsicWidth	()I
    //   17: istore_2
    //   18: aload 7
    //   20: invokevirtual 69	android/graphics/drawable/Drawable:getIntrinsicHeight	()I
    //   23: istore_1
    //   24: aload_0
    //   25: getfield 71	com/meizu/commonwidget/ParcelableImageSpan:d	I
    //   28: ifne +24 -> 52
    //   31: aload_0
    //   32: getfield 73	com/meizu/commonwidget/ParcelableImageSpan:e	I
    //   35: ifne +17 -> 52
    //   38: aload_0
    //   39: getfield 75	com/meizu/commonwidget/ParcelableImageSpan:f	I
    //   42: ifne +10 -> 52
    //   45: aload_0
    //   46: getfield 77	com/meizu/commonwidget/ParcelableImageSpan:g	I
    //   49: ifeq +130 -> 179
    //   52: aload_0
    //   53: getfield 71	com/meizu/commonwidget/ParcelableImageSpan:d	I
    //   56: istore_3
    //   57: aload_0
    //   58: getfield 75	com/meizu/commonwidget/ParcelableImageSpan:f	I
    //   61: istore 4
    //   63: aload_0
    //   64: getfield 73	com/meizu/commonwidget/ParcelableImageSpan:e	I
    //   67: istore 5
    //   69: aload_0
    //   70: getfield 77	com/meizu/commonwidget/ParcelableImageSpan:g	I
    //   73: istore 6
    //   75: new 79	android/graphics/drawable/InsetDrawable
    //   78: dup
    //   79: aload 7
    //   81: aload_0
    //   82: getfield 71	com/meizu/commonwidget/ParcelableImageSpan:d	I
    //   85: aload_0
    //   86: getfield 73	com/meizu/commonwidget/ParcelableImageSpan:e	I
    //   89: aload_0
    //   90: getfield 75	com/meizu/commonwidget/ParcelableImageSpan:f	I
    //   93: aload_0
    //   94: getfield 77	com/meizu/commonwidget/ParcelableImageSpan:g	I
    //   97: invokespecial 82	android/graphics/drawable/InsetDrawable:<init>	(Landroid/graphics/drawable/Drawable;IIII)V
    //   100: astore 8
    //   102: iload_1
    //   103: iload 5
    //   105: iload 6
    //   107: iadd
    //   108: iadd
    //   109: istore_1
    //   110: iload_2
    //   111: iload_3
    //   112: iload 4
    //   114: iadd
    //   115: iadd
    //   116: istore_2
    //   117: aload 8
    //   119: astore 7
    //   121: aload 7
    //   123: iconst_0
    //   124: iconst_0
    //   125: iload_2
    //   126: iload_1
    //   127: invokevirtual 86	android/graphics/drawable/Drawable:setBounds	(IIII)V
    //   130: aload 7
    //   132: areturn
    //   133: astore 7
    //   135: aconst_null
    //   136: astore 7
    //   138: ldc 88
    //   140: new 90	java/lang/StringBuilder
    //   143: dup
    //   144: invokespecial 93	java/lang/StringBuilder:<init>	()V
    //   147: ldc 95
    //   149: invokevirtual 99	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   152: aload_0
    //   153: getfield 58	com/meizu/commonwidget/ParcelableImageSpan:b	I
    //   156: invokevirtual 102	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   159: invokevirtual 106	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   162: invokestatic 111	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   165: pop
    //   166: aload 7
    //   168: areturn
    //   169: astore 8
    //   171: goto -33 -> 138
    //   174: astore 8
    //   176: goto -38 -> 138
    //   179: goto -58 -> 121
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	182	0	this	ParcelableImageSpan
    //   23	104	1	i	int
    //   17	109	2	j	int
    //   56	59	3	k	int
    //   61	54	4	m	int
    //   67	41	5	n	int
    //   73	35	6	i1	int
    //   10	121	7	localObject	Object
    //   133	1	7	localException1	Exception
    //   136	31	7	localDrawable	Drawable
    //   100	18	8	localInsetDrawable	android.graphics.drawable.InsetDrawable
    //   169	1	8	localException2	Exception
    //   174	1	8	localException3	Exception
    // Exception table:
    //   from	to	target	type
    //   0	12	133	java/lang/Exception
    //   12	52	169	java/lang/Exception
    //   52	102	169	java/lang/Exception
    //   121	130	174	java/lang/Exception
  }
  
  public int b()
  {
    return c;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public void draw(Canvas paramCanvas, CharSequence paramCharSequence, int paramInt1, int paramInt2, float paramFloat, int paramInt3, int paramInt4, int paramInt5, Paint paramPaint)
  {
    paramCharSequence = c();
    paramCanvas.save();
    paramInt2 = paramInt5 - getBoundsbottom;
    if (c == 2)
    {
      paramPaint = a(paramPaint);
      paramInt1 = paramInt2 - (descent - ascent - getBoundsbottom >> 1);
    }
    for (;;)
    {
      paramCanvas.translate(paramFloat, paramInt1);
      paramCharSequence.draw(paramCanvas);
      paramCanvas.restore();
      return;
      paramInt1 = paramInt2;
      if (c == 1) {
        paramInt1 = paramInt2 - adescent;
      }
    }
  }
  
  public int getSize(Paint paramPaint, CharSequence paramCharSequence, int paramInt1, int paramInt2, Paint.FontMetricsInt paramFontMetricsInt)
  {
    return cgetBoundsright;
  }
  
  public int getSpanTypeId()
  {
    return 25;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeInt(b());
    paramParcel.writeInt(b);
    paramParcel.writeInt(d);
    paramParcel.writeInt(e);
    paramParcel.writeInt(f);
    paramParcel.writeInt(g);
  }
}

/* Location:
 * Qualified Name:     com.meizu.commonwidget.ParcelableImageSpan
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */