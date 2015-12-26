package com.android.mms.ui;

import android.content.Context;
import android.net.Uri;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;
import wd;

public class FileAttachmentView
  extends AttachMentViewBase
{
  private ImageView a;
  private TextView b;
  private TextView c;
  private Uri d;
  
  public FileAttachmentView(Context paramContext)
  {
    super(paramContext);
  }
  
  public FileAttachmentView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  public void a(long paramLong)
  {
    wd.a(c, paramLong, getContext());
  }
  
  /* Error */
  public void a(Uri paramUri, String paramString1, java.util.Map<String, ?> paramMap, String paramString2)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_1
    //   4: putfield 38	com/android/mms/ui/FileAttachmentView:d	Landroid/net/Uri;
    //   7: aload_0
    //   8: monitorexit
    //   9: new 40	li
    //   12: dup
    //   13: aload_0
    //   14: invokevirtual 26	com/android/mms/ui/FileAttachmentView:getContext	()Landroid/content/Context;
    //   17: aload_1
    //   18: invokespecial 43	li:<init>	(Landroid/content/Context;Landroid/net/Uri;)V
    //   21: astore_1
    //   22: aload_1
    //   23: invokevirtual 47	li:n	()Ljava/lang/String;
    //   26: invokestatic 53	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   29: ifeq +78 -> 107
    //   32: aload_0
    //   33: getfield 55	com/android/mms/ui/FileAttachmentView:b	Landroid/widget/TextView;
    //   36: aload_2
    //   37: invokevirtual 61	android/widget/TextView:setText	(Ljava/lang/CharSequence;)V
    //   40: aload_2
    //   41: aload 4
    //   43: aload_0
    //   44: getfield 63	com/android/mms/ui/FileAttachmentView:a	Landroid/widget/ImageView;
    //   47: iconst_0
    //   48: invokestatic 66	wd:a	(Ljava/lang/String;Ljava/lang/String;Landroid/widget/ImageView;Z)V
    //   51: aload_1
    //   52: invokevirtual 70	li:p	()J
    //   55: lstore 5
    //   57: lload 5
    //   59: ldc2_w 71
    //   62: lcmp
    //   63: ifgt +99 -> 162
    //   66: aload_0
    //   67: getfield 22	com/android/mms/ui/FileAttachmentView:c	Landroid/widget/TextView;
    //   70: new 74	java/lang/StringBuilder
    //   73: dup
    //   74: invokespecial 77	java/lang/StringBuilder:<init>	()V
    //   77: lload 5
    //   79: ldc2_w 78
    //   82: ladd
    //   83: ldc2_w 80
    //   86: ldiv
    //   87: invokevirtual 85	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   90: ldc 87
    //   92: invokevirtual 90	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   95: invokevirtual 93	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   98: invokevirtual 61	android/widget/TextView:setText	(Ljava/lang/CharSequence;)V
    //   101: return
    //   102: astore_1
    //   103: aload_0
    //   104: monitorexit
    //   105: aload_1
    //   106: athrow
    //   107: aload_0
    //   108: getfield 55	com/android/mms/ui/FileAttachmentView:b	Landroid/widget/TextView;
    //   111: aload_1
    //   112: invokevirtual 47	li:n	()Ljava/lang/String;
    //   115: invokevirtual 61	android/widget/TextView:setText	(Ljava/lang/CharSequence;)V
    //   118: aload_1
    //   119: invokevirtual 47	li:n	()Ljava/lang/String;
    //   122: aload 4
    //   124: aload_0
    //   125: getfield 63	com/android/mms/ui/FileAttachmentView:a	Landroid/widget/ImageView;
    //   128: iconst_0
    //   129: invokestatic 66	wd:a	(Ljava/lang/String;Ljava/lang/String;Landroid/widget/ImageView;Z)V
    //   132: goto -81 -> 51
    //   135: astore_1
    //   136: aload_0
    //   137: getfield 55	com/android/mms/ui/FileAttachmentView:b	Landroid/widget/TextView;
    //   140: aload_0
    //   141: invokevirtual 97	com/android/mms/ui/FileAttachmentView:getResources	()Landroid/content/res/Resources;
    //   144: ldc 98
    //   146: invokevirtual 104	android/content/res/Resources:getString	(I)Ljava/lang/String;
    //   149: invokevirtual 61	android/widget/TextView:setText	(Ljava/lang/CharSequence;)V
    //   152: aload_0
    //   153: getfield 22	com/android/mms/ui/FileAttachmentView:c	Landroid/widget/TextView;
    //   156: ldc 106
    //   158: invokevirtual 61	android/widget/TextView:setText	(Ljava/lang/CharSequence;)V
    //   161: return
    //   162: aload_0
    //   163: getfield 22	com/android/mms/ui/FileAttachmentView:c	Landroid/widget/TextView;
    //   166: lload 5
    //   168: ldc2_w 78
    //   171: ladd
    //   172: ldc2_w 71
    //   175: ldc 108
    //   177: aconst_null
    //   178: invokestatic 111	wd:a	(JJLjava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   181: invokevirtual 61	android/widget/TextView:setText	(Ljava/lang/CharSequence;)V
    //   184: return
    //   185: astore_1
    //   186: return
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	187	0	this	FileAttachmentView
    //   0	187	1	paramUri	Uri
    //   0	187	2	paramString1	String
    //   0	187	3	paramMap	java.util.Map<String, ?>
    //   0	187	4	paramString2	String
    //   55	112	5	l	long
    // Exception table:
    //   from	to	target	type
    //   2	9	102	finally
    //   103	105	102	finally
    //   9	51	135	gd
    //   51	57	135	gd
    //   66	101	135	gd
    //   107	132	135	gd
    //   162	184	135	gd
    //   9	51	185	com/google/android/mms/MmsException
    //   51	57	185	com/google/android/mms/MmsException
    //   66	101	185	com/google/android/mms/MmsException
    //   107	132	185	com/google/android/mms/MmsException
    //   162	184	185	com/google/android/mms/MmsException
  }
  
  /* Error */
  public void c(Uri paramUri, String paramString, java.util.Map<String, ?> paramMap)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_1
    //   4: putfield 38	com/android/mms/ui/FileAttachmentView:d	Landroid/net/Uri;
    //   7: aload_0
    //   8: monitorexit
    //   9: new 40	li
    //   12: dup
    //   13: aload_0
    //   14: invokevirtual 26	com/android/mms/ui/FileAttachmentView:getContext	()Landroid/content/Context;
    //   17: aload_1
    //   18: invokespecial 43	li:<init>	(Landroid/content/Context;Landroid/net/Uri;)V
    //   21: astore_1
    //   22: aload_1
    //   23: invokevirtual 47	li:n	()Ljava/lang/String;
    //   26: invokestatic 53	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   29: ifeq +76 -> 105
    //   32: aload_0
    //   33: getfield 55	com/android/mms/ui/FileAttachmentView:b	Landroid/widget/TextView;
    //   36: aload_2
    //   37: invokevirtual 61	android/widget/TextView:setText	(Ljava/lang/CharSequence;)V
    //   40: aload_2
    //   41: aload_0
    //   42: getfield 63	com/android/mms/ui/FileAttachmentView:a	Landroid/widget/ImageView;
    //   45: iconst_0
    //   46: invokestatic 117	wd:a	(Ljava/lang/String;Landroid/widget/ImageView;Z)V
    //   49: aload_1
    //   50: invokevirtual 70	li:p	()J
    //   53: lstore 4
    //   55: lload 4
    //   57: ldc2_w 71
    //   60: lcmp
    //   61: ifgt +97 -> 158
    //   64: aload_0
    //   65: getfield 22	com/android/mms/ui/FileAttachmentView:c	Landroid/widget/TextView;
    //   68: new 74	java/lang/StringBuilder
    //   71: dup
    //   72: invokespecial 77	java/lang/StringBuilder:<init>	()V
    //   75: lload 4
    //   77: ldc2_w 78
    //   80: ladd
    //   81: ldc2_w 80
    //   84: ldiv
    //   85: invokevirtual 85	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   88: ldc 87
    //   90: invokevirtual 90	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   93: invokevirtual 93	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   96: invokevirtual 61	android/widget/TextView:setText	(Ljava/lang/CharSequence;)V
    //   99: return
    //   100: astore_1
    //   101: aload_0
    //   102: monitorexit
    //   103: aload_1
    //   104: athrow
    //   105: aload_0
    //   106: getfield 55	com/android/mms/ui/FileAttachmentView:b	Landroid/widget/TextView;
    //   109: aload_1
    //   110: invokevirtual 47	li:n	()Ljava/lang/String;
    //   113: invokevirtual 61	android/widget/TextView:setText	(Ljava/lang/CharSequence;)V
    //   116: aload_1
    //   117: invokevirtual 47	li:n	()Ljava/lang/String;
    //   120: aload_0
    //   121: getfield 63	com/android/mms/ui/FileAttachmentView:a	Landroid/widget/ImageView;
    //   124: iconst_0
    //   125: invokestatic 117	wd:a	(Ljava/lang/String;Landroid/widget/ImageView;Z)V
    //   128: goto -79 -> 49
    //   131: astore_1
    //   132: aload_0
    //   133: getfield 55	com/android/mms/ui/FileAttachmentView:b	Landroid/widget/TextView;
    //   136: aload_0
    //   137: invokevirtual 97	com/android/mms/ui/FileAttachmentView:getResources	()Landroid/content/res/Resources;
    //   140: ldc 98
    //   142: invokevirtual 104	android/content/res/Resources:getString	(I)Ljava/lang/String;
    //   145: invokevirtual 61	android/widget/TextView:setText	(Ljava/lang/CharSequence;)V
    //   148: aload_0
    //   149: getfield 22	com/android/mms/ui/FileAttachmentView:c	Landroid/widget/TextView;
    //   152: ldc 106
    //   154: invokevirtual 61	android/widget/TextView:setText	(Ljava/lang/CharSequence;)V
    //   157: return
    //   158: aload_0
    //   159: getfield 22	com/android/mms/ui/FileAttachmentView:c	Landroid/widget/TextView;
    //   162: lload 4
    //   164: ldc2_w 78
    //   167: ladd
    //   168: ldc2_w 71
    //   171: ldc 108
    //   173: aconst_null
    //   174: invokestatic 111	wd:a	(JJLjava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   177: invokevirtual 61	android/widget/TextView:setText	(Ljava/lang/CharSequence;)V
    //   180: return
    //   181: astore_1
    //   182: return
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	183	0	this	FileAttachmentView
    //   0	183	1	paramUri	Uri
    //   0	183	2	paramString	String
    //   0	183	3	paramMap	java.util.Map<String, ?>
    //   53	110	4	l	long
    // Exception table:
    //   from	to	target	type
    //   2	9	100	finally
    //   101	103	100	finally
    //   9	49	131	gd
    //   49	55	131	gd
    //   64	99	131	gd
    //   105	128	131	gd
    //   158	180	131	gd
    //   9	49	181	com/google/android/mms/MmsException
    //   49	55	181	com/google/android/mms/MmsException
    //   64	99	181	com/google/android/mms/MmsException
    //   105	128	181	com/google/android/mms/MmsException
    //   158	180	181	com/google/android/mms/MmsException
  }
  
  /* Error */
  public void c(Uri paramUri, String paramString, java.util.Map<String, ?> paramMap, long paramLong)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_1
    //   4: putfield 38	com/android/mms/ui/FileAttachmentView:d	Landroid/net/Uri;
    //   7: aload_0
    //   8: monitorexit
    //   9: new 40	li
    //   12: dup
    //   13: aload_0
    //   14: invokevirtual 26	com/android/mms/ui/FileAttachmentView:getContext	()Landroid/content/Context;
    //   17: aload_1
    //   18: invokespecial 43	li:<init>	(Landroid/content/Context;Landroid/net/Uri;)V
    //   21: astore_1
    //   22: aload_1
    //   23: invokevirtual 47	li:n	()Ljava/lang/String;
    //   26: invokestatic 53	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   29: ifeq +39 -> 68
    //   32: aload_0
    //   33: getfield 55	com/android/mms/ui/FileAttachmentView:b	Landroid/widget/TextView;
    //   36: aload_2
    //   37: invokevirtual 61	android/widget/TextView:setText	(Ljava/lang/CharSequence;)V
    //   40: aload_2
    //   41: aload_0
    //   42: getfield 63	com/android/mms/ui/FileAttachmentView:a	Landroid/widget/ImageView;
    //   45: iconst_0
    //   46: invokestatic 117	wd:a	(Ljava/lang/String;Landroid/widget/ImageView;Z)V
    //   49: aload_0
    //   50: getfield 22	com/android/mms/ui/FileAttachmentView:c	Landroid/widget/TextView;
    //   53: lload 4
    //   55: aload_0
    //   56: invokevirtual 26	com/android/mms/ui/FileAttachmentView:getContext	()Landroid/content/Context;
    //   59: invokestatic 31	wd:a	(Landroid/widget/TextView;JLandroid/content/Context;)V
    //   62: return
    //   63: astore_1
    //   64: aload_0
    //   65: monitorexit
    //   66: aload_1
    //   67: athrow
    //   68: aload_0
    //   69: getfield 55	com/android/mms/ui/FileAttachmentView:b	Landroid/widget/TextView;
    //   72: aload_1
    //   73: invokevirtual 47	li:n	()Ljava/lang/String;
    //   76: invokevirtual 61	android/widget/TextView:setText	(Ljava/lang/CharSequence;)V
    //   79: aload_1
    //   80: invokevirtual 47	li:n	()Ljava/lang/String;
    //   83: aload_0
    //   84: getfield 63	com/android/mms/ui/FileAttachmentView:a	Landroid/widget/ImageView;
    //   87: iconst_0
    //   88: invokestatic 117	wd:a	(Ljava/lang/String;Landroid/widget/ImageView;Z)V
    //   91: goto -42 -> 49
    //   94: astore_1
    //   95: aload_0
    //   96: getfield 55	com/android/mms/ui/FileAttachmentView:b	Landroid/widget/TextView;
    //   99: aload_0
    //   100: invokevirtual 97	com/android/mms/ui/FileAttachmentView:getResources	()Landroid/content/res/Resources;
    //   103: ldc 98
    //   105: invokevirtual 104	android/content/res/Resources:getString	(I)Ljava/lang/String;
    //   108: invokevirtual 61	android/widget/TextView:setText	(Ljava/lang/CharSequence;)V
    //   111: aload_0
    //   112: getfield 22	com/android/mms/ui/FileAttachmentView:c	Landroid/widget/TextView;
    //   115: ldc2_w 120
    //   118: aload_0
    //   119: invokevirtual 26	com/android/mms/ui/FileAttachmentView:getContext	()Landroid/content/Context;
    //   122: invokestatic 31	wd:a	(Landroid/widget/TextView;JLandroid/content/Context;)V
    //   125: return
    //   126: astore_1
    //   127: return
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	128	0	this	FileAttachmentView
    //   0	128	1	paramUri	Uri
    //   0	128	2	paramString	String
    //   0	128	3	paramMap	java.util.Map<String, ?>
    //   0	128	4	paramLong	long
    // Exception table:
    //   from	to	target	type
    //   2	9	63	finally
    //   64	66	63	finally
    //   9	49	94	gd
    //   49	62	94	gd
    //   68	91	94	gd
    //   9	49	126	com/google/android/mms/MmsException
    //   49	62	126	com/google/android/mms/MmsException
    //   68	91	126	com/google/android/mms/MmsException
  }
  
  protected void onFinishInflate()
  {
    a = ((ImageView)findViewById(2131886465));
    b = ((TextView)findViewById(2131886217));
    c = ((TextView)findViewById(2131886218));
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.FileAttachmentView
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */