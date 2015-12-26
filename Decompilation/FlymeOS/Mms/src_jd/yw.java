import android.content.Context;
import android.net.Uri;
import android.util.Log;

public class yw
{
  private final Context a;
  private lr b;
  
  public yw(Context paramContext, lr paramlr)
  {
    a = paramContext;
    b = paramlr;
  }
  
  public void a(int paramInt1, int paramInt2)
  {
    if (paramInt2 >= 0) {
      b.a(paramInt1).a(paramInt2);
    }
  }
  
  public void a(int paramInt, Uri paramUri)
  {
    b.a(paramInt).a(new lk(a, paramUri, b.f().a()));
  }
  
  public void a(int paramInt, String paramString)
  {
    lq locallq;
    lu locallu;
    int j;
    if (paramString != null)
    {
      locallq = b.a(paramInt);
      locallu = locallq.p();
      j = paramString.getBytes().length;
      if (locallu != null) {
        break label97;
      }
      locallu = new lu(a, "text/plain", "text_" + paramInt + ".txt", b.f().b());
      locallu.a(paramString);
      locallq.a(locallu);
    }
    label97:
    while (paramString.equals(locallu.a())) {
      return;
    }
    int k = locallu.a().getBytes().length;
    int i;
    if (k == 0)
    {
      paramInt = j + lm.f();
      i = k;
      if (paramInt <= i) {
        break label210;
      }
      b.d(paramInt - i);
      b.b(paramInt - i);
      locallq.a(paramInt - i);
      locallu.a(paramString);
    }
    for (;;)
    {
      locallu.a(paramString);
      return;
      paramInt = j;
      i = k;
      if (paramString.length() != 0) {
        break;
      }
      i = k + lm.f();
      paramInt = j;
      break;
      label210:
      b.c(i - paramInt);
      locallq.b(i - paramInt);
      locallu.a(paramString);
    }
  }
  
  public void a(lr paramlr)
  {
    b = paramlr;
  }
  
  public boolean a()
  {
    return a(b.size());
  }
  
  public boolean a(int paramInt)
  {
    int i = b.size();
    if (i < 20)
    {
      lq locallq = new lq(b);
      locallq.a(new lu(a, "text/plain", "text_" + i + ".txt", b.f().b()));
      b.a(paramInt, locallq);
      return true;
    }
    Log.w("Mms:slideshow", "The limitation of the number of slides is reached.");
    return false;
  }
  
  public boolean a(int paramInt, lq paramlq)
  {
    if (b.size() < 20)
    {
      b.b(paramInt, paramlq);
      return true;
    }
    Log.w("Mms:slideshow", "The limitation of the number of slides is reached.");
    return false;
  }
  
  public void b()
  {
    while (b.size() > 0) {
      b(0);
    }
  }
  
  public void b(int paramInt)
  {
    b.b(paramInt);
  }
  
  public void b(int paramInt, Uri paramUri)
  {
    paramUri = new le(a, paramUri);
    lq locallq = b.a(paramInt);
    locallq.a(paramUri);
    locallq.d(paramUri.i());
  }
  
  public void c(int paramInt, Uri paramUri)
  {
    paramUri = new lw(a, paramUri, b.f().a());
    lq locallq = b.a(paramInt);
    locallq.a(paramUri);
    locallq.d(paramUri.i());
  }
  
  public boolean c(int paramInt)
  {
    return b.a(paramInt).m();
  }
  
  public void d(int paramInt, Uri paramUri)
  {
    paramUri = new lv(a, paramUri);
    lq locallq = b.a(paramInt);
    locallq.a(paramUri);
    locallq.d(paramUri.i());
  }
  
  public boolean d(int paramInt)
  {
    return b.a(paramInt).o();
  }
  
  public void e(int paramInt, Uri paramUri)
  {
    paramUri = new lt(a, paramUri);
    lq locallq = b.a(paramInt);
    locallq.a(paramUri);
    locallq.d(paramUri.i());
  }
  
  public boolean e(int paramInt)
  {
    return b.a(paramInt).n();
  }
  
  public void f(int paramInt)
  {
    b.a(paramInt - 1, b.b(paramInt));
  }
  
  public void f(int paramInt, Uri paramUri)
  {
    paramUri = new li(a, paramUri);
    lq locallq = b.a(paramInt);
    locallq.a(paramUri);
    locallq.d(paramUri.i());
  }
  
  public void g(int paramInt)
  {
    b.a(paramInt + 1, b.b(paramInt));
  }
  
  public void h(int paramInt)
  {
    b.f().a(paramInt);
  }
  
  public String i(int paramInt)
  {
    if (b.a(paramInt) != null) {}
    for (lu locallu = b.a(paramInt).p(); locallu == null; locallu = null) {
      return "";
    }
    return locallu.a();
  }
}

/* Location:
 * Qualified Name:     yw
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */