package com.android.mms.ui;

import android.content.Context;
import android.net.Uri;
import android.util.AttributeSet;
import android.widget.TextView;
import wd;

public class VcardAttachmentView
  extends AttachMentViewBase
{
  private TextView a;
  private TextView b;
  private Uri c;
  
  public VcardAttachmentView(Context paramContext)
  {
    super(paramContext);
  }
  
  public VcardAttachmentView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  public void a(long paramLong)
  {
    wd.a(b, paramLong, getContext());
  }
  
  /* Error */
  public void b(Uri paramUri, String paramString, java.util.Map<String, ?> paramMap)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_1
    //   4: putfield 36	com/android/mms/ui/VcardAttachmentView:c	Landroid/net/Uri;
    //   7: aload_0
    //   8: monitorexit
    //   9: new 38	lv
    //   12: dup
    //   13: aload_0
    //   14: invokevirtual 24	com/android/mms/ui/VcardAttachmentView:getContext	()Landroid/content/Context;
    //   17: aload_1
    //   18: invokespecial 41	lv:<init>	(Landroid/content/Context;Landroid/net/Uri;)V
    //   21: astore_1
    //   22: aload_1
    //   23: invokevirtual 45	lv:n	()Ljava/lang/String;
    //   26: invokestatic 51	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   29: ifeq +67 -> 96
    //   32: aload_0
    //   33: getfield 53	com/android/mms/ui/VcardAttachmentView:a	Landroid/widget/TextView;
    //   36: aload_2
    //   37: invokevirtual 59	android/widget/TextView:setText	(Ljava/lang/CharSequence;)V
    //   40: aload_1
    //   41: invokevirtual 63	lv:p	()J
    //   44: lstore 4
    //   46: lload 4
    //   48: ldc2_w 64
    //   51: lcmp
    //   52: ifgt +85 -> 137
    //   55: aload_0
    //   56: getfield 20	com/android/mms/ui/VcardAttachmentView:b	Landroid/widget/TextView;
    //   59: new 67	java/lang/StringBuilder
    //   62: dup
    //   63: invokespecial 70	java/lang/StringBuilder:<init>	()V
    //   66: lload 4
    //   68: ldc2_w 71
    //   71: ladd
    //   72: ldc2_w 73
    //   75: ldiv
    //   76: invokevirtual 78	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   79: ldc 80
    //   81: invokevirtual 83	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   84: invokevirtual 86	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   87: invokevirtual 59	android/widget/TextView:setText	(Ljava/lang/CharSequence;)V
    //   90: return
    //   91: astore_1
    //   92: aload_0
    //   93: monitorexit
    //   94: aload_1
    //   95: athrow
    //   96: aload_0
    //   97: getfield 53	com/android/mms/ui/VcardAttachmentView:a	Landroid/widget/TextView;
    //   100: aload_1
    //   101: invokevirtual 45	lv:n	()Ljava/lang/String;
    //   104: invokevirtual 59	android/widget/TextView:setText	(Ljava/lang/CharSequence;)V
    //   107: goto -67 -> 40
    //   110: astore_1
    //   111: aload_0
    //   112: getfield 53	com/android/mms/ui/VcardAttachmentView:a	Landroid/widget/TextView;
    //   115: aload_0
    //   116: invokevirtual 90	com/android/mms/ui/VcardAttachmentView:getResources	()Landroid/content/res/Resources;
    //   119: ldc 91
    //   121: invokevirtual 97	android/content/res/Resources:getString	(I)Ljava/lang/String;
    //   124: invokevirtual 59	android/widget/TextView:setText	(Ljava/lang/CharSequence;)V
    //   127: aload_0
    //   128: getfield 20	com/android/mms/ui/VcardAttachmentView:b	Landroid/widget/TextView;
    //   131: ldc 99
    //   133: invokevirtual 59	android/widget/TextView:setText	(Ljava/lang/CharSequence;)V
    //   136: return
    //   137: aload_0
    //   138: getfield 20	com/android/mms/ui/VcardAttachmentView:b	Landroid/widget/TextView;
    //   141: lload 4
    //   143: ldc2_w 71
    //   146: ladd
    //   147: ldc2_w 64
    //   150: ldc 101
    //   152: aconst_null
    //   153: invokestatic 104	wd:a	(JJLjava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   156: invokevirtual 59	android/widget/TextView:setText	(Ljava/lang/CharSequence;)V
    //   159: return
    //   160: astore_1
    //   161: return
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	162	0	this	VcardAttachmentView
    //   0	162	1	paramUri	Uri
    //   0	162	2	paramString	String
    //   0	162	3	paramMap	java.util.Map<String, ?>
    //   44	98	4	l	long
    // Exception table:
    //   from	to	target	type
    //   2	9	91	finally
    //   92	94	91	finally
    //   9	40	110	gd
    //   40	46	110	gd
    //   55	90	110	gd
    //   96	107	110	gd
    //   137	159	110	gd
    //   9	40	160	com/google/android/mms/MmsException
    //   40	46	160	com/google/android/mms/MmsException
    //   55	90	160	com/google/android/mms/MmsException
    //   96	107	160	com/google/android/mms/MmsException
    //   137	159	160	com/google/android/mms/MmsException
  }
  
  /* Error */
  public void b(Uri paramUri, String paramString, java.util.Map<String, ?> paramMap, long paramLong)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_1
    //   4: putfield 36	com/android/mms/ui/VcardAttachmentView:c	Landroid/net/Uri;
    //   7: aload_0
    //   8: monitorexit
    //   9: new 38	lv
    //   12: dup
    //   13: aload_0
    //   14: invokevirtual 24	com/android/mms/ui/VcardAttachmentView:getContext	()Landroid/content/Context;
    //   17: aload_1
    //   18: invokespecial 41	lv:<init>	(Landroid/content/Context;Landroid/net/Uri;)V
    //   21: astore_1
    //   22: aload_1
    //   23: invokevirtual 45	lv:n	()Ljava/lang/String;
    //   26: invokestatic 51	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   29: ifeq +30 -> 59
    //   32: aload_0
    //   33: getfield 53	com/android/mms/ui/VcardAttachmentView:a	Landroid/widget/TextView;
    //   36: aload_2
    //   37: invokevirtual 59	android/widget/TextView:setText	(Ljava/lang/CharSequence;)V
    //   40: aload_0
    //   41: getfield 20	com/android/mms/ui/VcardAttachmentView:b	Landroid/widget/TextView;
    //   44: lload 4
    //   46: aload_0
    //   47: invokevirtual 24	com/android/mms/ui/VcardAttachmentView:getContext	()Landroid/content/Context;
    //   50: invokestatic 29	wd:a	(Landroid/widget/TextView;JLandroid/content/Context;)V
    //   53: return
    //   54: astore_1
    //   55: aload_0
    //   56: monitorexit
    //   57: aload_1
    //   58: athrow
    //   59: aload_0
    //   60: getfield 53	com/android/mms/ui/VcardAttachmentView:a	Landroid/widget/TextView;
    //   63: aload_1
    //   64: invokevirtual 45	lv:n	()Ljava/lang/String;
    //   67: invokevirtual 59	android/widget/TextView:setText	(Ljava/lang/CharSequence;)V
    //   70: goto -30 -> 40
    //   73: astore_1
    //   74: aload_0
    //   75: getfield 53	com/android/mms/ui/VcardAttachmentView:a	Landroid/widget/TextView;
    //   78: aload_0
    //   79: invokevirtual 90	com/android/mms/ui/VcardAttachmentView:getResources	()Landroid/content/res/Resources;
    //   82: ldc 91
    //   84: invokevirtual 97	android/content/res/Resources:getString	(I)Ljava/lang/String;
    //   87: invokevirtual 59	android/widget/TextView:setText	(Ljava/lang/CharSequence;)V
    //   90: aload_0
    //   91: getfield 20	com/android/mms/ui/VcardAttachmentView:b	Landroid/widget/TextView;
    //   94: ldc2_w 108
    //   97: aload_0
    //   98: invokevirtual 24	com/android/mms/ui/VcardAttachmentView:getContext	()Landroid/content/Context;
    //   101: invokestatic 29	wd:a	(Landroid/widget/TextView;JLandroid/content/Context;)V
    //   104: return
    //   105: astore_1
    //   106: return
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	107	0	this	VcardAttachmentView
    //   0	107	1	paramUri	Uri
    //   0	107	2	paramString	String
    //   0	107	3	paramMap	java.util.Map<String, ?>
    //   0	107	4	paramLong	long
    // Exception table:
    //   from	to	target	type
    //   2	9	54	finally
    //   55	57	54	finally
    //   9	40	73	gd
    //   40	53	73	gd
    //   59	70	73	gd
    //   9	40	105	com/google/android/mms/MmsException
    //   40	53	105	com/google/android/mms/MmsException
    //   59	70	105	com/google/android/mms/MmsException
  }
  
  protected void onFinishInflate()
  {
    a = ((TextView)findViewById(2131886217));
    b = ((TextView)findViewById(2131886218));
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.VcardAttachmentView
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */