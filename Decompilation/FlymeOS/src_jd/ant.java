import com.ted.android.contacts.common.DataBus;

public class ant
{
  public static Boolean a(String paramString)
  {
    int i;
    if (paramString.indexOf("teddymobile.cn") > -1)
    {
      i = 1;
      if (paramString.indexOf("j.tdbear.cn") <= -1) {
        break label42;
      }
    }
    label42:
    for (int j = 1;; j = 0)
    {
      if ((j == 0) && (i == 0)) {
        break label47;
      }
      return Boolean.valueOf(true);
      i = 0;
      break;
    }
    label47:
    return Boolean.valueOf(false);
  }
  
  public static String a(String paramString1, String paramString2, String paramString3)
  {
    Object localObject = paramString1;
    if (a(paramString1).booleanValue()) {}
    try
    {
      localObject = new anx(paramString1);
      if ((!((anx)localObject).a("lat")) || (!((anx)localObject).a("lng")) || (!((anx)localObject).c("lat")) || (!((anx)localObject).c("lng")))
      {
        ((anx)localObject).a("lat", paramString2);
        ((anx)localObject).a("lng", paramString3);
      }
      ((anx)localObject).a("tdbear_id", DataBus.DID);
      ((anx)localObject).a("tdbear_ch", String.valueOf(DataBus.CID));
      localObject = ((anx)localObject).a();
      return (String)localObject;
    }
    catch (Exception paramString2)
    {
      paramString2.printStackTrace();
    }
    return paramString1;
  }
}

/* Location:
 * Qualified Name:     ant
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */