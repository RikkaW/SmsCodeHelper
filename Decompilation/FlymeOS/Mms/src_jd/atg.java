import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import java.net.URI;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpResponseException;

public abstract class atg
  implements aut
{
  private String a = "UTF-8";
  private Handler b;
  private boolean c;
  private URI d = null;
  private Header[] e = null;
  private Looper f = null;
  
  public atg()
  {
    this(null);
  }
  
  public atg(Looper paramLooper)
  {
    Looper localLooper = paramLooper;
    if (paramLooper == null) {
      localLooper = Looper.myLooper();
    }
    f = localLooper;
    a(false);
  }
  
  protected Message a(int paramInt, Object paramObject)
  {
    return Message.obtain(b, paramInt, paramObject);
  }
  
  public final void a()
  {
    b(a(2, null));
  }
  
  public final void a(int paramInt)
  {
    b(a(5, new Object[] { Integer.valueOf(paramInt) }));
  }
  
  public void a(int paramInt1, int paramInt2)
  {
    if (paramInt2 > 0) {}
    for (double d1 = paramInt1 * 1.0D / paramInt2 * 100.0D;; d1 = -1.0D)
    {
      Log.v("AsyncHttpResponseHandler", String.format("Progress %d from %d (%2.0f%%)", new Object[] { Integer.valueOf(paramInt1), Integer.valueOf(paramInt2), Double.valueOf(d1) }));
      return;
    }
  }
  
  public abstract void a(int paramInt, Header[] paramArrayOfHeader, byte[] paramArrayOfByte);
  
  public final void a(int paramInt, Header[] paramArrayOfHeader, byte[] paramArrayOfByte, Throwable paramThrowable)
  {
    b(a(1, new Object[] { Integer.valueOf(paramInt), paramArrayOfHeader, paramArrayOfByte, paramThrowable }));
  }
  
  protected void a(Message paramMessage)
  {
    switch (what)
    {
    default: 
      return;
    case 0: 
      paramMessage = (Object[])obj;
      if ((paramMessage != null) && (paramMessage.length >= 3))
      {
        a(((Integer)paramMessage[0]).intValue(), (Header[])paramMessage[1], (byte[])paramMessage[2]);
        return;
      }
      Log.e("AsyncHttpResponseHandler", "SUCCESS_MESSAGE didn't got enough params");
      return;
    case 1: 
      paramMessage = (Object[])obj;
      if ((paramMessage != null) && (paramMessage.length >= 4))
      {
        b(((Integer)paramMessage[0]).intValue(), (Header[])paramMessage[1], (byte[])paramMessage[2], (Throwable)paramMessage[3]);
        return;
      }
      Log.e("AsyncHttpResponseHandler", "FAILURE_MESSAGE didn't got enough params");
      return;
    case 2: 
      f();
      return;
    case 3: 
      g();
      return;
    case 4: 
      paramMessage = (Object[])obj;
      if ((paramMessage != null) && (paramMessage.length >= 2)) {
        try
        {
          a(((Integer)paramMessage[0]).intValue(), ((Integer)paramMessage[1]).intValue());
          return;
        }
        catch (Throwable paramMessage)
        {
          Log.e("AsyncHttpResponseHandler", "custom onProgress contains an error", paramMessage);
          return;
        }
      }
      Log.e("AsyncHttpResponseHandler", "PROGRESS_MESSAGE didn't got enough params");
      return;
    case 5: 
      paramMessage = (Object[])obj;
      if ((paramMessage != null) && (paramMessage.length == 1))
      {
        b(((Integer)paramMessage[0]).intValue());
        return;
      }
      Log.e("AsyncHttpResponseHandler", "RETRY_MESSAGE didn't get enough params");
      return;
    }
    h();
  }
  
  public void a(aut paramaut, HttpResponse paramHttpResponse) {}
  
  protected void a(Runnable paramRunnable)
  {
    if (paramRunnable != null)
    {
      if ((d()) || (b == null)) {
        paramRunnable.run();
      }
    }
    else {
      return;
    }
    if (b != null) {}
    for (boolean bool = true;; bool = false)
    {
      a.a(bool, "handler should not be null!");
      b.post(paramRunnable);
      return;
    }
  }
  
  public void a(URI paramURI)
  {
    d = paramURI;
  }
  
  public void a(HttpResponse paramHttpResponse)
  {
    StatusLine localStatusLine;
    byte[] arrayOfByte;
    if (!Thread.currentThread().isInterrupted())
    {
      localStatusLine = paramHttpResponse.getStatusLine();
      arrayOfByte = a(paramHttpResponse.getEntity());
      if (!Thread.currentThread().isInterrupted())
      {
        if (localStatusLine.getStatusCode() < 300) {
          break label85;
        }
        a(localStatusLine.getStatusCode(), paramHttpResponse.getAllHeaders(), arrayOfByte, new HttpResponseException(localStatusLine.getStatusCode(), localStatusLine.getReasonPhrase()));
      }
    }
    return;
    label85:
    b(localStatusLine.getStatusCode(), paramHttpResponse.getAllHeaders(), arrayOfByte);
  }
  
  public void a(boolean paramBoolean)
  {
    boolean bool = paramBoolean;
    if (!paramBoolean)
    {
      bool = paramBoolean;
      if (f == null)
      {
        bool = true;
        Log.w("AsyncHttpResponseHandler", "Current thread has not called Looper.prepare(). Forcing synchronous mode.");
      }
    }
    if ((!bool) && (b == null)) {}
    for (b = new atg.a(this, f);; b = null) {
      do
      {
        c = bool;
        return;
      } while ((!bool) || (b == null));
    }
  }
  
  public void a(Header[] paramArrayOfHeader)
  {
    e = paramArrayOfHeader;
  }
  
  /* Error */
  byte[] a(org.apache.http.HttpEntity paramHttpEntity)
  {
    // Byte code:
    //   0: sipush 4096
    //   3: istore_2
    //   4: aload_1
    //   5: ifnull +189 -> 194
    //   8: aload_1
    //   9: invokeinterface 233 1 0
    //   14: astore 9
    //   16: aload 9
    //   18: ifnull +176 -> 194
    //   21: aload_1
    //   22: invokeinterface 237 1 0
    //   27: lstore 6
    //   29: lload 6
    //   31: ldc2_w 238
    //   34: lcmp
    //   35: ifle +13 -> 48
    //   38: new 241	java/lang/IllegalArgumentException
    //   41: dup
    //   42: ldc -13
    //   44: invokespecial 246	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   47: athrow
    //   48: lload 6
    //   50: lconst_0
    //   51: lcmp
    //   52: ifgt +65 -> 117
    //   55: new 248	org/apache/http/util/ByteArrayBuffer
    //   58: dup
    //   59: iload_2
    //   60: invokespecial 250	org/apache/http/util/ByteArrayBuffer:<init>	(I)V
    //   63: astore 10
    //   65: sipush 4096
    //   68: newarray <illegal type>
    //   70: astore 11
    //   72: iconst_0
    //   73: istore_2
    //   74: aload 9
    //   76: aload 11
    //   78: invokevirtual 256	java/io/InputStream:read	([B)I
    //   81: istore_3
    //   82: iload_3
    //   83: iconst_m1
    //   84: if_icmpeq +16 -> 100
    //   87: invokestatic 176	java/lang/Thread:currentThread	()Ljava/lang/Thread;
    //   90: invokevirtual 179	java/lang/Thread:isInterrupted	()Z
    //   93: istore 8
    //   95: iload 8
    //   97: ifeq +27 -> 124
    //   100: aload 9
    //   102: invokestatic 261	ab:a	(Ljava/io/InputStream;)V
    //   105: aload_1
    //   106: invokestatic 264	ab:a	(Lorg/apache/http/HttpEntity;)V
    //   109: aload 10
    //   111: invokevirtual 268	org/apache/http/util/ByteArrayBuffer:toByteArray	()[B
    //   114: astore_1
    //   115: aload_1
    //   116: areturn
    //   117: lload 6
    //   119: l2i
    //   120: istore_2
    //   121: goto -66 -> 55
    //   124: iload_2
    //   125: iload_3
    //   126: iadd
    //   127: istore_2
    //   128: aload 10
    //   130: aload 11
    //   132: iconst_0
    //   133: iload_3
    //   134: invokevirtual 272	org/apache/http/util/ByteArrayBuffer:append	([BII)V
    //   137: lload 6
    //   139: lconst_0
    //   140: lcmp
    //   141: ifgt +17 -> 158
    //   144: lconst_1
    //   145: lstore 4
    //   147: aload_0
    //   148: iload_2
    //   149: lload 4
    //   151: l2i
    //   152: invokevirtual 274	atg:b	(II)V
    //   155: goto -81 -> 74
    //   158: lload 6
    //   160: lstore 4
    //   162: goto -15 -> 147
    //   165: astore 10
    //   167: aload 9
    //   169: invokestatic 261	ab:a	(Ljava/io/InputStream;)V
    //   172: aload_1
    //   173: invokestatic 264	ab:a	(Lorg/apache/http/HttpEntity;)V
    //   176: aload 10
    //   178: athrow
    //   179: astore_1
    //   180: invokestatic 279	java/lang/System:gc	()V
    //   183: new 281	java/io/IOException
    //   186: dup
    //   187: ldc_w 283
    //   190: invokespecial 284	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   193: athrow
    //   194: aconst_null
    //   195: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	196	0	this	atg
    //   0	196	1	paramHttpEntity	org.apache.http.HttpEntity
    //   3	146	2	i	int
    //   81	53	3	j	int
    //   145	16	4	l1	long
    //   27	132	6	l2	long
    //   93	3	8	bool	boolean
    //   14	154	9	localInputStream	java.io.InputStream
    //   63	66	10	localByteArrayBuffer	org.apache.http.util.ByteArrayBuffer
    //   165	12	10	localObject	Object
    //   70	61	11	arrayOfByte	byte[]
    // Exception table:
    //   from	to	target	type
    //   65	72	165	finally
    //   74	82	165	finally
    //   87	95	165	finally
    //   128	137	165	finally
    //   147	155	165	finally
    //   55	65	179	java/lang/OutOfMemoryError
    //   100	115	179	java/lang/OutOfMemoryError
    //   167	179	179	java/lang/OutOfMemoryError
  }
  
  public final void b()
  {
    b(a(3, null));
  }
  
  public void b(int paramInt)
  {
    Log.d("AsyncHttpResponseHandler", String.format("Request retry no. %d", new Object[] { Integer.valueOf(paramInt) }));
  }
  
  public final void b(int paramInt1, int paramInt2)
  {
    b(a(4, new Object[] { Integer.valueOf(paramInt1), Integer.valueOf(paramInt2) }));
  }
  
  public final void b(int paramInt, Header[] paramArrayOfHeader, byte[] paramArrayOfByte)
  {
    b(a(0, new Object[] { Integer.valueOf(paramInt), paramArrayOfHeader, paramArrayOfByte }));
  }
  
  public abstract void b(int paramInt, Header[] paramArrayOfHeader, byte[] paramArrayOfByte, Throwable paramThrowable);
  
  protected void b(Message paramMessage)
  {
    if ((d()) || (b == null)) {
      a(paramMessage);
    }
    while (Thread.currentThread().isInterrupted()) {
      return;
    }
    if (b != null) {}
    for (boolean bool = true;; bool = false)
    {
      a.a(bool, "handler should not be null!");
      b.sendMessage(paramMessage);
      return;
    }
  }
  
  public void b(aut paramaut, HttpResponse paramHttpResponse) {}
  
  public void b(String paramString)
  {
    a = paramString;
  }
  
  public final void c()
  {
    b(a(6, null));
  }
  
  public boolean d()
  {
    return c;
  }
  
  public String e()
  {
    if (a == null) {
      return "UTF-8";
    }
    return a;
  }
  
  public void f() {}
  
  public void g() {}
  
  public void h()
  {
    Log.d("AsyncHttpResponseHandler", "Request got cancelled");
  }
  
  static class a
    extends Handler
  {
    private final atg a;
    
    a(atg paramatg, Looper paramLooper)
    {
      super();
      a = paramatg;
    }
    
    public void handleMessage(Message paramMessage)
    {
      a.a(paramMessage);
    }
  }
}

/* Location:
 * Qualified Name:     atg
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */