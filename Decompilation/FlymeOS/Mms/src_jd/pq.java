import android.content.Context;
import android.net.Uri;
import android.util.Log;

public class pq
  extends po
{
  private final boolean i;
  private String j;
  private Uri k;
  private String l;
  
  public pq(Context paramContext, String paramString1, String paramString2, String paramString3, long paramLong, boolean paramBoolean, Uri paramUri, int paramInt, String paramString4)
  {
    super(paramContext, null, paramString2, paramString3, paramLong, paramInt);
    i = paramBoolean;
    j = paramString1;
    k = paramUri;
    l = paramString4;
  }
  
  private void a(String paramString)
  {
    Log.d("SnsMessageManager", "[SnsSingleRecipientSender] " + paramString);
  }
  
  /* Error */
  public boolean a(long paramLong)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 56	pq:c	Ljava/lang/String;
    //   4: ifnonnull +13 -> 17
    //   7: new 58	com/google/android/mms/MmsException
    //   10: dup
    //   11: ldc 60
    //   13: invokespecial 62	com/google/android/mms/MmsException:<init>	(Ljava/lang/String;)V
    //   16: athrow
    //   17: aload_0
    //   18: aload_0
    //   19: getfield 20	pq:j	Ljava/lang/String;
    //   22: ldc 64
    //   24: ldc 66
    //   26: invokevirtual 72	java/lang/String:replaceAll	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   29: putfield 20	pq:j	Ljava/lang/String;
    //   32: getstatic 78	java/lang/Integer:TYPE	Ljava/lang/Class;
    //   35: astore_3
    //   36: getstatic 78	java/lang/Integer:TYPE	Ljava/lang/Class;
    //   39: astore 4
    //   41: aload_0
    //   42: getfield 81	pq:a	Landroid/content/Context;
    //   45: astore 5
    //   47: aload_0
    //   48: getfield 22	pq:k	Landroid/net/Uri;
    //   51: astore 6
    //   53: ldc 83
    //   55: ldc 85
    //   57: iconst_4
    //   58: anewarray 87	java/lang/Class
    //   61: dup
    //   62: iconst_0
    //   63: ldc 89
    //   65: aastore
    //   66: dup
    //   67: iconst_1
    //   68: ldc 91
    //   70: aastore
    //   71: dup
    //   72: iconst_2
    //   73: aload_3
    //   74: aastore
    //   75: dup
    //   76: iconst_3
    //   77: aload 4
    //   79: aastore
    //   80: iconst_4
    //   81: anewarray 93	java/lang/Object
    //   84: dup
    //   85: iconst_0
    //   86: aload 5
    //   88: aastore
    //   89: dup
    //   90: iconst_1
    //   91: aload 6
    //   93: aastore
    //   94: dup
    //   95: iconst_2
    //   96: iconst_4
    //   97: invokestatic 97	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   100: aastore
    //   101: dup
    //   102: iconst_3
    //   103: iconst_0
    //   104: invokestatic 97	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   107: aastore
    //   108: invokestatic 102	aau:a	(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Class;[Ljava/lang/Object;)Ljava/lang/Object;
    //   111: checkcast 104	java/lang/Boolean
    //   114: invokevirtual 108	java/lang/Boolean:booleanValue	()Z
    //   117: ifne +33 -> 150
    //   120: new 58	com/google/android/mms/MmsException
    //   123: dup
    //   124: new 31	java/lang/StringBuilder
    //   127: dup
    //   128: invokespecial 34	java/lang/StringBuilder:<init>	()V
    //   131: ldc 110
    //   133: invokevirtual 40	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   136: aload_0
    //   137: getfield 22	pq:k	Landroid/net/Uri;
    //   140: invokevirtual 113	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   143: invokevirtual 44	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   146: invokespecial 62	com/google/android/mms/MmsException:<init>	(Ljava/lang/String;)V
    //   149: athrow
    //   150: new 115	android/content/Intent
    //   153: dup
    //   154: ldc 117
    //   156: aload_0
    //   157: getfield 22	pq:k	Landroid/net/Uri;
    //   160: aload_0
    //   161: getfield 81	pq:a	Landroid/content/Context;
    //   164: ldc 119
    //   166: invokespecial 122	android/content/Intent:<init>	(Ljava/lang/String;Landroid/net/Uri;Landroid/content/Context;Ljava/lang/Class;)V
    //   169: astore_3
    //   170: aload_3
    //   171: ldc 124
    //   173: iconst_1
    //   174: invokevirtual 128	android/content/Intent:putExtra	(Ljava/lang/String;Z)Landroid/content/Intent;
    //   177: pop
    //   178: aload_0
    //   179: getfield 130	pq:d	Ljava/lang/String;
    //   182: invokestatic 136	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   185: ifeq +112 -> 297
    //   188: aload_3
    //   189: ldc -118
    //   191: ldc -116
    //   193: invokevirtual 143	android/content/Intent:putExtra	(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
    //   196: pop
    //   197: aload_0
    //   198: getfield 81	pq:a	Landroid/content/Context;
    //   201: iconst_0
    //   202: aload_3
    //   203: iconst_0
    //   204: invokestatic 149	android/app/PendingIntent:getBroadcast	(Landroid/content/Context;ILandroid/content/Intent;I)Landroid/app/PendingIntent;
    //   207: astore_3
    //   208: invokestatic 154	abh:a	()Labh;
    //   211: aload_0
    //   212: getfield 20	pq:j	Ljava/lang/String;
    //   215: aload_0
    //   216: getfield 56	pq:c	Ljava/lang/String;
    //   219: aload_3
    //   220: aload_0
    //   221: getfield 24	pq:l	Ljava/lang/String;
    //   224: invokevirtual 157	abh:a	(Ljava/lang/String;Ljava/lang/String;Landroid/app/PendingIntent;Ljava/lang/String;)V
    //   227: ldc -97
    //   229: iconst_2
    //   230: invokestatic 163	android/util/Log:isLoggable	(Ljava/lang/String;I)Z
    //   233: ifeq +62 -> 295
    //   236: aload_0
    //   237: new 31	java/lang/StringBuilder
    //   240: dup
    //   241: invokespecial 34	java/lang/StringBuilder:<init>	()V
    //   244: ldc -91
    //   246: invokevirtual 40	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   249: aload_0
    //   250: getfield 20	pq:j	Ljava/lang/String;
    //   253: invokevirtual 40	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   256: ldc -89
    //   258: invokevirtual 40	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   261: aload_0
    //   262: getfield 171	pq:f	J
    //   265: invokevirtual 174	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   268: ldc -80
    //   270: invokevirtual 40	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   273: aload_0
    //   274: getfield 22	pq:k	Landroid/net/Uri;
    //   277: invokevirtual 113	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   280: ldc -78
    //   282: invokevirtual 40	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   285: iconst_1
    //   286: invokevirtual 181	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   289: invokevirtual 44	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   292: invokespecial 183	pq:a	(Ljava/lang/String;)V
    //   295: iconst_0
    //   296: ireturn
    //   297: ldc 29
    //   299: ldc -71
    //   301: invokestatic 50	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   304: pop
    //   305: goto -78 -> 227
    //   308: astore_3
    //   309: new 58	com/google/android/mms/MmsException
    //   312: dup
    //   313: new 31	java/lang/StringBuilder
    //   316: dup
    //   317: invokespecial 34	java/lang/StringBuilder:<init>	()V
    //   320: ldc -69
    //   322: invokevirtual 40	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   325: aload_3
    //   326: invokevirtual 113	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   329: ldc -67
    //   331: invokevirtual 40	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   334: invokevirtual 44	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   337: invokespecial 62	com/google/android/mms/MmsException:<init>	(Ljava/lang/String;)V
    //   340: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	341	0	this	pq
    //   0	341	1	paramLong	long
    //   35	185	3	localObject	Object
    //   308	18	3	localException	Exception
    //   39	39	4	localClass	Class
    //   45	42	5	localContext	Context
    //   51	41	6	localUri	Uri
    // Exception table:
    //   from	to	target	type
    //   178	227	308	java/lang/Exception
    //   297	305	308	java/lang/Exception
  }
}

/* Location:
 * Qualified Name:     pq
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */