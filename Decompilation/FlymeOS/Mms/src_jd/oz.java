import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.Telephony.Carriers;
import android.text.TextUtils;
import android.util.Log;

public class oz
{
  private static final String[] d = { "type", "mmsc", "mmsproxy", "mmsport" };
  private String a;
  private String b;
  private int c = -1;
  
  public oz(Context paramContext, String paramString)
  {
    Log.v("TransactionSettings", "TransactionSettings: apnName: " + paramString);
    Object localObject1 = "current IS NOT NULL";
    Object localObject2;
    if (!TextUtils.isEmpty(paramString))
    {
      localObject2 = "current IS NOT NULL" + " AND apn=?";
      localObject1 = new String[1];
      localObject1[0] = paramString.trim();
      paramString = (String)localObject2;
    }
    for (;;)
    {
      localObject1 = paramContext.getContentResolver().query(Telephony.Carriers.CONTENT_URI, d, paramString, (String[])localObject1, null);
      localObject2 = new StringBuilder().append("TransactionSettings looking for apn: ").append(paramString).append(" returned: ");
      if (localObject1 == null)
      {
        paramContext = "null cursor";
        Log.v("TransactionSettings", paramContext);
        if (localObject1 != null) {
          break label192;
        }
        Log.e("TransactionSettings", "Apn is not found in Database!");
      }
      label192:
      int i;
      label351:
      do
      {
        return;
        paramContext = ((Cursor)localObject1).getCount() + " hits";
        break;
        i = 0;
        for (;;)
        {
          try
          {
            if ((!((Cursor)localObject1).moveToNext()) || (!TextUtils.isEmpty(a))) {
              break;
            }
            if (a(((Cursor)localObject1).getString(0), "mms"))
            {
              paramContext = ((Cursor)localObject1).getString(1);
              if (paramContext == null)
              {
                i = 1;
              }
              else
              {
                a = ((String)aau.a("android.net.NetworkUtils", "trimV4AddrZeros", String.class, paramContext.trim()));
                b = ((String)aau.a("android.net.NetworkUtils", "trimV4AddrZeros", String.class, ((Cursor)localObject1).getString(2)));
                if (a()) {
                  paramContext = ((Cursor)localObject1).getString(3);
                }
                try
                {
                  c = Integer.parseInt(paramContext);
                  i = 1;
                }
                catch (NumberFormatException localNumberFormatException)
                {
                  if (!TextUtils.isEmpty(paramContext)) {
                    break label351;
                  }
                }
                Log.w("TransactionSettings", "mms port not set!");
                continue;
                Log.e("TransactionSettings", "Bad port number format: " + paramContext, localNumberFormatException);
              }
            }
          }
          finally
          {
            ((Cursor)localObject1).close();
          }
        }
        ((Cursor)localObject1).close();
        Log.v("TransactionSettings", "APN setting: MMSC: " + a + " looked for: " + paramString);
      } while ((i == 0) || (!TextUtils.isEmpty(a)));
      Log.e("TransactionSettings", "Invalid APN setting: MMSC is empty");
      return;
      Object localObject3 = null;
      paramString = (String)localObject1;
      localObject1 = localObject3;
    }
  }
  
  public oz(String paramString1, String paramString2, int paramInt)
  {
    if (paramString1 != null) {}
    for (paramString1 = paramString1.trim();; paramString1 = null)
    {
      a = paramString1;
      b = paramString2;
      c = paramInt;
      if (Log.isLoggable("Mms:transaction", 2)) {
        Log.v("TransactionSettings", "TransactionSettings: " + a + " proxyAddress: " + b + " proxyPort: " + c);
      }
      return;
    }
  }
  
  private static boolean a(String paramString1, String paramString2)
  {
    if (TextUtils.isEmpty(paramString1)) {
      return true;
    }
    paramString1 = paramString1.split(",");
    int j = paramString1.length;
    int i = 0;
    for (;;)
    {
      if (i >= j) {
        break label57;
      }
      Object localObject = paramString1[i];
      if ((((String)localObject).equals(paramString2)) || (((String)localObject).equals("*"))) {
        break;
      }
      i += 1;
    }
    label57:
    return false;
  }
  
  public boolean a()
  {
    return (b != null) && (b.trim().length() != 0);
  }
}

/* Location:
 * Qualified Name:     oz
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */