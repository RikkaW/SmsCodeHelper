import android.accounts.Account;
import android.accounts.OnAccountsUpdateListener;

class pk
  implements OnAccountsUpdateListener
{
  pk(pj parampj) {}
  
  public void onAccountsUpdated(Account[] paramArrayOfAccount)
  {
    boolean bool2 = false;
    Object localObject2 = null;
    int j = paramArrayOfAccount.length;
    int i = 0;
    for (;;)
    {
      Object localObject1 = localObject2;
      boolean bool1 = bool2;
      if (i < j)
      {
        localObject1 = paramArrayOfAccount[i];
        if ((type.equals("com.meizu.sns.sina")) || (type.equals("com.sina.weibo.authenticator")))
        {
          bool1 = true;
          localObject1 = type;
        }
      }
      else
      {
        if (bool1 != pj.e())
        {
          pj.a(bool1);
          a.a((String)localObject1, bool1);
        }
        return;
      }
      i += 1;
    }
  }
}

/* Location:
 * Qualified Name:     pk
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */