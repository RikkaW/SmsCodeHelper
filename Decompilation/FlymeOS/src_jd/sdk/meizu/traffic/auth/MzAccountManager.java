package sdk.meizu.traffic.auth;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

public class MzAccountManager
{
  private static final Uri ACCOUNT_BASE_URI = Uri.parse("content://com.meizu.account");
  public static final String ACCOUNT_TYPE = "com.meizu.account";
  private static final Uri ACCOUNT_URI = Uri.withAppendedPath(ACCOUNT_BASE_URI, "account");
  public static final String AUTHORITY = "com.meizu.account";
  public static final String PATH_ACCOUNT = "account";
  private static final String SELECTION_WITH_USER_ID = "userId=?";
  private ContentResolver mResolver;
  
  public MzAccountManager(Context paramContext)
  {
    mResolver = paramContext.getContentResolver();
  }
  
  public static Account getBaseAccount(Context paramContext)
  {
    paramContext = AccountManager.get(paramContext).getAccountsByType("com.meizu.account");
    if ((paramContext == null) || (paramContext.length == 0)) {
      return null;
    }
    return paramContext[0];
  }
  
  public static final boolean hasFlymeAccount(Context paramContext)
  {
    return getBaseAccount(paramContext) != null;
  }
  
  public static boolean isFlymeAccount(Account paramAccount)
  {
    return (paramAccount != null) && (type.equals("com.meizu.account"));
  }
  
  public static MeizuAccountInfo loadMzAccountInfo(Context paramContext)
  {
    localObject2 = null;
    Object localObject3 = null;
    localObject4 = getBaseAccount(paramContext);
    localObject1 = localObject2;
    if (localObject4 != null)
    {
      localObject4 = paramContext.getContentResolver().query(ACCOUNT_URI, null, "userId=?", new String[] { name }, null);
      paramContext = (Context)localObject3;
      if (localObject4 != null) {
        paramContext = (Context)localObject3;
      }
    }
    try
    {
      if (((Cursor)localObject4).moveToFirst()) {
        paramContext = new MeizuAccountInfo((Cursor)localObject4);
      }
      localObject1 = paramContext;
      if (localObject4 != null)
      {
        ((Cursor)localObject4).close();
        localObject1 = paramContext;
      }
    }
    catch (IllegalArgumentException paramContext)
    {
      paramContext.printStackTrace();
      localObject1 = localObject2;
      return null;
    }
    finally
    {
      if (localObject4 == null) {
        break label126;
      }
      ((Cursor)localObject4).close();
    }
    return (MeizuAccountInfo)localObject1;
  }
}

/* Location:
 * Qualified Name:     sdk.meizu.traffic.auth.MzAccountManager
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */