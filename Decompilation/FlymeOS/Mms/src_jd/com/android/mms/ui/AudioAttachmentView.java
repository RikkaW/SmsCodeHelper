package com.android.mms.ui;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;
import pw;
import px;
import wd;

public class AudioAttachmentView
  extends AttachMentViewBase
{
  private TextView a;
  private TextView b;
  private Uri c;
  private MediaPlayer d;
  private boolean e;
  private Context f;
  
  public AudioAttachmentView(Context paramContext)
  {
    super(paramContext);
    f = paramContext;
  }
  
  public AudioAttachmentView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    f = paramContext;
  }
  
  private void a()
  {
    Log.e("AudioAttachmentView", "Error occurred while playing audio.");
    m();
  }
  
  private void b()
  {
    if (d != null) {}
    try
    {
      d.stop();
      d.release();
      return;
    }
    finally
    {
      d = null;
    }
  }
  
  public void a(long paramLong)
  {
    wd.a(b, paramLong, f);
  }
  
  /* Error */
  public void a(Uri paramUri, String paramString, java.util.Map<String, ?> paramMap)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_1
    //   4: putfield 66	com/android/mms/ui/AudioAttachmentView:c	Landroid/net/Uri;
    //   7: aload_0
    //   8: monitorexit
    //   9: new 68	le
    //   12: dup
    //   13: aload_0
    //   14: invokevirtual 72	com/android/mms/ui/AudioAttachmentView:getContext	()Landroid/content/Context;
    //   17: aload_1
    //   18: iconst_0
    //   19: invokespecial 75	le:<init>	(Landroid/content/Context;Landroid/net/Uri;Z)V
    //   22: astore_1
    //   23: aload_1
    //   24: invokevirtual 79	le:n	()Ljava/lang/String;
    //   27: invokestatic 85	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   30: ifeq +67 -> 97
    //   33: aload_0
    //   34: getfield 87	com/android/mms/ui/AudioAttachmentView:a	Landroid/widget/TextView;
    //   37: aload_2
    //   38: invokevirtual 93	android/widget/TextView:setText	(Ljava/lang/CharSequence;)V
    //   41: aload_1
    //   42: invokevirtual 97	le:p	()J
    //   45: lstore 4
    //   47: lload 4
    //   49: ldc2_w 98
    //   52: lcmp
    //   53: ifgt +92 -> 145
    //   56: aload_0
    //   57: getfield 54	com/android/mms/ui/AudioAttachmentView:b	Landroid/widget/TextView;
    //   60: new 101	java/lang/StringBuilder
    //   63: dup
    //   64: invokespecial 103	java/lang/StringBuilder:<init>	()V
    //   67: lload 4
    //   69: ldc2_w 104
    //   72: ladd
    //   73: ldc2_w 106
    //   76: ldiv
    //   77: invokevirtual 111	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   80: ldc 113
    //   82: invokevirtual 116	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   85: invokevirtual 119	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   88: invokevirtual 93	android/widget/TextView:setText	(Ljava/lang/CharSequence;)V
    //   91: return
    //   92: astore_1
    //   93: aload_0
    //   94: monitorexit
    //   95: aload_1
    //   96: athrow
    //   97: aload_0
    //   98: getfield 87	com/android/mms/ui/AudioAttachmentView:a	Landroid/widget/TextView;
    //   101: aload_1
    //   102: invokevirtual 79	le:n	()Ljava/lang/String;
    //   105: invokevirtual 93	android/widget/TextView:setText	(Ljava/lang/CharSequence;)V
    //   108: goto -67 -> 41
    //   111: astore_1
    //   112: aload_2
    //   113: invokestatic 85	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   116: ifeq +86 -> 202
    //   119: aload_0
    //   120: getfield 87	com/android/mms/ui/AudioAttachmentView:a	Landroid/widget/TextView;
    //   123: aload_0
    //   124: invokevirtual 123	com/android/mms/ui/AudioAttachmentView:getResources	()Landroid/content/res/Resources;
    //   127: ldc 124
    //   129: invokevirtual 130	android/content/res/Resources:getString	(I)Ljava/lang/String;
    //   132: invokevirtual 93	android/widget/TextView:setText	(Ljava/lang/CharSequence;)V
    //   135: aload_0
    //   136: getfield 54	com/android/mms/ui/AudioAttachmentView:b	Landroid/widget/TextView;
    //   139: ldc -124
    //   141: invokevirtual 93	android/widget/TextView:setText	(Ljava/lang/CharSequence;)V
    //   144: return
    //   145: aload_0
    //   146: getfield 54	com/android/mms/ui/AudioAttachmentView:b	Landroid/widget/TextView;
    //   149: lload 4
    //   151: ldc2_w 104
    //   154: ladd
    //   155: ldc2_w 98
    //   158: ldc -122
    //   160: aconst_null
    //   161: invokestatic 137	wd:a	(JJLjava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   164: invokevirtual 93	android/widget/TextView:setText	(Ljava/lang/CharSequence;)V
    //   167: return
    //   168: astore_1
    //   169: aload_2
    //   170: invokestatic 85	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   173: ifeq +40 -> 213
    //   176: aload_0
    //   177: getfield 87	com/android/mms/ui/AudioAttachmentView:a	Landroid/widget/TextView;
    //   180: aload_0
    //   181: invokevirtual 123	com/android/mms/ui/AudioAttachmentView:getResources	()Landroid/content/res/Resources;
    //   184: ldc 124
    //   186: invokevirtual 130	android/content/res/Resources:getString	(I)Ljava/lang/String;
    //   189: invokevirtual 93	android/widget/TextView:setText	(Ljava/lang/CharSequence;)V
    //   192: aload_0
    //   193: getfield 54	com/android/mms/ui/AudioAttachmentView:b	Landroid/widget/TextView;
    //   196: ldc -124
    //   198: invokevirtual 93	android/widget/TextView:setText	(Ljava/lang/CharSequence;)V
    //   201: return
    //   202: aload_0
    //   203: getfield 87	com/android/mms/ui/AudioAttachmentView:a	Landroid/widget/TextView;
    //   206: aload_2
    //   207: invokevirtual 93	android/widget/TextView:setText	(Ljava/lang/CharSequence;)V
    //   210: goto -75 -> 135
    //   213: aload_0
    //   214: getfield 87	com/android/mms/ui/AudioAttachmentView:a	Landroid/widget/TextView;
    //   217: aload_2
    //   218: invokevirtual 93	android/widget/TextView:setText	(Ljava/lang/CharSequence;)V
    //   221: goto -29 -> 192
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	224	0	this	AudioAttachmentView
    //   0	224	1	paramUri	Uri
    //   0	224	2	paramString	String
    //   0	224	3	paramMap	java.util.Map<String, ?>
    //   45	105	4	l	long
    // Exception table:
    //   from	to	target	type
    //   2	9	92	finally
    //   93	95	92	finally
    //   9	41	111	gd
    //   41	47	111	gd
    //   56	91	111	gd
    //   97	108	111	gd
    //   145	167	111	gd
    //   9	41	168	com/google/android/mms/MmsException
    //   41	47	168	com/google/android/mms/MmsException
    //   56	91	168	com/google/android/mms/MmsException
    //   97	108	168	com/google/android/mms/MmsException
    //   145	167	168	com/google/android/mms/MmsException
  }
  
  /* Error */
  public void a(Uri paramUri, String paramString, java.util.Map<String, ?> paramMap, long paramLong)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_1
    //   4: putfield 66	com/android/mms/ui/AudioAttachmentView:c	Landroid/net/Uri;
    //   7: aload_0
    //   8: monitorexit
    //   9: new 68	le
    //   12: dup
    //   13: aload_0
    //   14: invokevirtual 72	com/android/mms/ui/AudioAttachmentView:getContext	()Landroid/content/Context;
    //   17: aload_1
    //   18: iconst_0
    //   19: invokespecial 75	le:<init>	(Landroid/content/Context;Landroid/net/Uri;Z)V
    //   22: astore_1
    //   23: aload_1
    //   24: invokevirtual 79	le:n	()Ljava/lang/String;
    //   27: invokestatic 85	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   30: ifeq +30 -> 60
    //   33: aload_0
    //   34: getfield 87	com/android/mms/ui/AudioAttachmentView:a	Landroid/widget/TextView;
    //   37: aload_2
    //   38: invokevirtual 93	android/widget/TextView:setText	(Ljava/lang/CharSequence;)V
    //   41: aload_0
    //   42: getfield 54	com/android/mms/ui/AudioAttachmentView:b	Landroid/widget/TextView;
    //   45: lload 4
    //   47: aload_0
    //   48: getfield 21	com/android/mms/ui/AudioAttachmentView:f	Landroid/content/Context;
    //   51: invokestatic 59	wd:a	(Landroid/widget/TextView;JLandroid/content/Context;)V
    //   54: return
    //   55: astore_1
    //   56: aload_0
    //   57: monitorexit
    //   58: aload_1
    //   59: athrow
    //   60: aload_0
    //   61: getfield 87	com/android/mms/ui/AudioAttachmentView:a	Landroid/widget/TextView;
    //   64: aload_1
    //   65: invokevirtual 79	le:n	()Ljava/lang/String;
    //   68: invokevirtual 93	android/widget/TextView:setText	(Ljava/lang/CharSequence;)V
    //   71: goto -30 -> 41
    //   74: astore_1
    //   75: aload_2
    //   76: invokestatic 85	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   79: ifeq +34 -> 113
    //   82: aload_0
    //   83: getfield 87	com/android/mms/ui/AudioAttachmentView:a	Landroid/widget/TextView;
    //   86: aload_0
    //   87: invokevirtual 123	com/android/mms/ui/AudioAttachmentView:getResources	()Landroid/content/res/Resources;
    //   90: ldc 124
    //   92: invokevirtual 130	android/content/res/Resources:getString	(I)Ljava/lang/String;
    //   95: invokevirtual 93	android/widget/TextView:setText	(Ljava/lang/CharSequence;)V
    //   98: aload_0
    //   99: getfield 54	com/android/mms/ui/AudioAttachmentView:b	Landroid/widget/TextView;
    //   102: ldc2_w 141
    //   105: aload_0
    //   106: getfield 21	com/android/mms/ui/AudioAttachmentView:f	Landroid/content/Context;
    //   109: invokestatic 59	wd:a	(Landroid/widget/TextView;JLandroid/content/Context;)V
    //   112: return
    //   113: aload_0
    //   114: getfield 87	com/android/mms/ui/AudioAttachmentView:a	Landroid/widget/TextView;
    //   117: aload_2
    //   118: invokevirtual 93	android/widget/TextView:setText	(Ljava/lang/CharSequence;)V
    //   121: goto -23 -> 98
    //   124: astore_1
    //   125: aload_2
    //   126: invokestatic 85	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   129: ifeq +34 -> 163
    //   132: aload_0
    //   133: getfield 87	com/android/mms/ui/AudioAttachmentView:a	Landroid/widget/TextView;
    //   136: aload_0
    //   137: invokevirtual 123	com/android/mms/ui/AudioAttachmentView:getResources	()Landroid/content/res/Resources;
    //   140: ldc 124
    //   142: invokevirtual 130	android/content/res/Resources:getString	(I)Ljava/lang/String;
    //   145: invokevirtual 93	android/widget/TextView:setText	(Ljava/lang/CharSequence;)V
    //   148: aload_0
    //   149: getfield 54	com/android/mms/ui/AudioAttachmentView:b	Landroid/widget/TextView;
    //   152: ldc2_w 141
    //   155: aload_0
    //   156: getfield 21	com/android/mms/ui/AudioAttachmentView:f	Landroid/content/Context;
    //   159: invokestatic 59	wd:a	(Landroid/widget/TextView;JLandroid/content/Context;)V
    //   162: return
    //   163: aload_0
    //   164: getfield 87	com/android/mms/ui/AudioAttachmentView:a	Landroid/widget/TextView;
    //   167: aload_2
    //   168: invokevirtual 93	android/widget/TextView:setText	(Ljava/lang/CharSequence;)V
    //   171: goto -23 -> 148
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	174	0	this	AudioAttachmentView
    //   0	174	1	paramUri	Uri
    //   0	174	2	paramString	String
    //   0	174	3	paramMap	java.util.Map<String, ?>
    //   0	174	4	paramLong	long
    // Exception table:
    //   from	to	target	type
    //   2	9	55	finally
    //   56	58	55	finally
    //   9	41	74	gd
    //   41	54	74	gd
    //   60	71	74	gd
    //   9	41	124	com/google/android/mms/MmsException
    //   41	54	124	com/google/android/mms/MmsException
    //   60	71	124	com/google/android/mms/MmsException
  }
  
  public void h()
  {
    try
    {
      if (e) {
        m();
      }
      return;
    }
    finally {}
  }
  
  public void l()
  {
    try
    {
      if ((!e) && (c != null))
      {
        d = MediaPlayer.create(f, c);
        if (d != null)
        {
          d.setAudioStreamType(3);
          d.setOnCompletionListener(new pw(this));
          d.setOnErrorListener(new px(this));
          e = true;
          d.start();
        }
      }
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  /* Error */
  public void m()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokespecial 175	com/android/mms/ui/AudioAttachmentView:b	()V
    //   6: aload_0
    //   7: iconst_0
    //   8: putfield 146	com/android/mms/ui/AudioAttachmentView:e	Z
    //   11: aload_0
    //   12: monitorexit
    //   13: return
    //   14: astore_1
    //   15: aload_0
    //   16: iconst_0
    //   17: putfield 146	com/android/mms/ui/AudioAttachmentView:e	Z
    //   20: aload_1
    //   21: athrow
    //   22: astore_1
    //   23: aload_0
    //   24: monitorexit
    //   25: aload_1
    //   26: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	27	0	this	AudioAttachmentView
    //   14	7	1	localObject1	Object
    //   22	4	1	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   2	6	14	finally
    //   6	11	22	finally
    //   15	22	22	finally
  }
  
  protected void onFinishInflate()
  {
    a = ((TextView)findViewById(2131886217));
    b = ((TextView)findViewById(2131886218));
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.AudioAttachmentView
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */