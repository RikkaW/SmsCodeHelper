import android.accounts.AccountManager;
import android.accounts.OnAccountsUpdateListener;
import android.content.Context;
import android.util.Log;
import com.android.mms.MmsApp;
import java.util.ArrayList;

public class pj
{
  private static volatile boolean a = false;
  private static pj c;
  private ArrayList<pj.a> b = null;
  private OnAccountsUpdateListener d = new pk(this);
  
  public static final boolean a()
  {
    return a;
  }
  
  public static boolean a(Context paramContext, String paramString)
  {
    paramContext = AccountManager.get(paramContext).getAccountsByType(paramString);
    return (paramContext != null) && (paramContext.length > 0);
  }
  
  public static pj d()
  {
    if (c != null) {
      return c;
    }
    pj localpj = new pj();
    c = localpj;
    return localpj;
  }
  
  public void a(Context paramContext)
  {
    if ((a(paramContext, "com.meizu.sns.sina")) || (a(paramContext, "com.sina.weibo.authenticator"))) {}
    for (boolean bool = true;; bool = false)
    {
      a = bool;
      b();
      return;
    }
  }
  
  public void a(String paramString, boolean paramBoolean)
  {
    Log.i("SnsHelper", "onAccountChange(), accountType = " + paramString + ", hasLogin = " + paramBoolean);
    if ((b == null) || (b.size() == 0)) {}
    for (;;)
    {
      return;
      int i = 0;
      while (i < b.size())
      {
        ((pj.a)b.get(i)).a(paramString, paramBoolean);
        i += 1;
      }
    }
  }
  
  public void b()
  {
    if (d != null) {
      AccountManager.get(MmsApp.c()).addOnAccountsUpdatedListener(d, null, true);
    }
  }
  
  public void c()
  {
    if (d != null) {
      AccountManager.get(MmsApp.c()).removeOnAccountsUpdatedListener(d);
    }
    if (b != null) {
      b.clear();
    }
  }
  
  public static abstract interface a
  {
    public abstract void a(String paramString, boolean paramBoolean);
  }
}

/* Location:
 * Qualified Name:     pj
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */