import android.text.TextUtils;
import com.ted.android.contacts.common.DataBus;
import com.ted.android.data.BubbleEntity;
import com.ted.android.data.bubbleAction.CarrierAction;
import com.ted.android.message.MessageUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class apy
  extends aqc
{
  public static final String a = anv.b("ed9b375a2ab43d11a86f10183e36165c6a199dd39ff20a89ed4a098a4d24afd18f7e9b59e9dbb2146c5dc66e5271e4fc191be52a45a50652fb9abce08aaac7f4e03e9db2d4391d76909bd75320d4918e2faef94c3071a012f8c43e5ed163be565974624b6602acc82a33ad13157002a7fece16dd867801b4f59a493d291bcd53ddb63c7040814088d62ba426adc04027efde7ffb626d6d028a0a63594e1bb6bea4ef51b6a3ea0bc92b35282db100917ab35dda3794f6b5e70f538137b3122f7cc54cee615eb0961e618d3e81e95116df0a4f63d4196e0d9aa2c76f6430f9a473053e7feef2461b55c95ed188ae79ed8901f45ef7a46511c404804906ca0e7b2fcbe2682231ff41ef751cd4c0226e876de98564ab90eb18493873c467f042136fc6fcb12fb1668b5a990ce47c8aa02b89bf6366519a6b08ab634f3266ccc7bbfe2334d2f8f0cdc151102cde77e7d550882939c2cc6707da67c9514ce1f8d7ab25", DataBus.FILE_MASK);
  private static final String b = apy.class.getSimpleName();
  private static final String c = anv.b("a6f6c024abbebe3e67ed49733b3ff81810a0b63ebaab73e65ff1faa37edd443ef311570ab6714f3e735cfe0f53373f9d8c0444f1888916e15dcc871d93ae984f45de513dfb02a94df2578efc21e8e2cd3d7be57a24b60f177944bbe0a3243c882accda3c078446466fcb0a47ff9fa9660bace34b92a9753e30757d43c105a6e05998d2842b9e01ef37800e04fcdaa8d7d2aaf1ed856941a155ca98770aca5f43fd6a80902fb2ef840303bf33338eed3530757d43c105a6e05998d2842b9e01ef37800e04fcdaa8d7d2aaf1ed856941a1e439b94490114d45afcea358d8be6bb8", DataBus.FILE_MASK);
  private static apy d;
  private static final HashMap<String, String> e = new apz();
  
  public static apy a()
  {
    if (d == null) {}
    try
    {
      d = new apy();
      return d;
    }
    finally {}
  }
  
  public static String a(String paramString)
  {
    if (!TextUtils.isEmpty(paramString)) {
      paramString = Pattern.compile(a).matcher(paramString);
    }
    String str;
    do
    {
      if (!paramString.find()) {
        return null;
      }
      str = paramString.group();
    } while (!e.containsKey(str));
    return (String)e.get(str);
  }
  
  private List<String> a(List<String> paramList)
  {
    ArrayList localArrayList = new ArrayList();
    paramList = paramList.iterator();
    for (;;)
    {
      if (!paramList.hasNext()) {
        return localArrayList;
      }
      String str = (String)paramList.next();
      if (!localArrayList.contains(str)) {
        localArrayList.add(str);
      }
    }
  }
  
  private String b(String paramString)
  {
    String str1 = "";
    paramString = MessageUtils.removePhoneNumber(MessageUtils.removeUrl(paramString.replaceAll(c, "  ").replace("订单", "  ").replace("其中", "  ")));
    Object localObject = Pattern.compile("(?=.*?(?:快递|快件|镖号|天天|挂号信|增益|飞豹|京东|速尔|信丰|佳吉|佳怡|华宇|龙邦|飞康达|联昊通|运通|路通|快捷|捷特|新邦|全日通|源伟丰|安捷|丰达|银捷|城市之星|盛辉|远成|一邦|顺丰|申通|圆通|国通|全一|中通|全峰|德邦|韵达|宅急送|EMS|DHL|UPS|如风达|汇通|优速|邮局))(?=.*?(?:^|[^0-9a-zA-Z至])(\\d{10,30}|[a-zA-Z]{2}\\d{8,28}|[a-zA-Z]{2}\\d{6,26}[a-zA-Z]{2})(?!订单|[0-9a-zA-Z]))").matcher(paramString);
    HashMap localHashMap = new HashMap();
    for (;;)
    {
      if (!((Matcher)localObject).find())
      {
        localObject = a(localHashMap, c(paramString));
        paramString = str1;
        if (localObject != null)
        {
          paramString = str1;
          if (((List)localObject).size() > 0) {
            paramString = (String)a((List)localObject).get(0);
          }
        }
        str1 = paramString;
        if (paramString.length() == 10)
        {
          localObject = anu.b(paramString);
          if ((!paramString.toString().startsWith("400")) && (!paramString.toString().startsWith("800")))
          {
            str1 = paramString;
            if (!anu.c((String)localObject)) {}
          }
          else
          {
            str1 = "";
          }
        }
        return str1;
      }
      String str2 = ((Matcher)localObject).group(1);
      localHashMap.put(Integer.valueOf(((Matcher)localObject).start(1)), str2);
    }
  }
  
  private HashMap<Integer, String> c(String paramString)
  {
    HashMap localHashMap = new HashMap();
    String[] arrayOfString = a.split("\\|");
    int k = arrayOfString.length;
    int i = 0;
    if (i >= k) {
      return localHashMap;
    }
    String str = arrayOfString[i];
    int j = 0;
    for (;;)
    {
      j = paramString.indexOf(str, j);
      if (j == -1)
      {
        i += 1;
        break;
      }
      localHashMap.put(Integer.valueOf(j), str);
      j += 1;
    }
  }
  
  public List<BubbleEntity> a(String paramString1, String paramString2)
  {
    paramString2 = new ArrayList();
    String str = b(paramString1);
    Object localObject = anu.b(str);
    if ((!TextUtils.isEmpty(str)) && (!anu.c((String)localObject)))
    {
      localObject = new BubbleEntity();
      ((BubbleEntity)localObject).setId("-2");
      ((BubbleEntity)localObject).setMatchedWords(str);
      ((BubbleEntity)localObject).setIndex(paramString1.indexOf(str));
      CarrierAction localCarrierAction = new CarrierAction((BubbleEntity)localObject);
      localCarrierAction.setCarrierName(a(paramString1));
      localCarrierAction.setCariierNumber(str);
      ((BubbleEntity)localObject).addAction(localCarrierAction);
      paramString2.add(localObject);
    }
    return paramString2;
  }
  
  public List<String> a(HashMap<Integer, String> paramHashMap1, HashMap<Integer, String> paramHashMap2)
  {
    ArrayList localArrayList = new ArrayList();
    paramHashMap1 = paramHashMap1.entrySet().iterator();
    int i = Integer.MAX_VALUE;
    if (!paramHashMap1.hasNext()) {
      return localArrayList;
    }
    Object localObject1 = (Map.Entry)paramHashMap1.next();
    int k = ((Integer)((Map.Entry)localObject1).getKey()).intValue();
    localObject1 = (String)((Map.Entry)localObject1).getValue();
    Iterator localIterator = paramHashMap2.entrySet().iterator();
    label83:
    Object localObject2;
    if (localIterator.hasNext())
    {
      localObject2 = (Map.Entry)localIterator.next();
      j = ((Integer)((Map.Entry)localObject2).getKey()).intValue();
      localObject2 = (String)((Map.Entry)localObject2).getValue();
      if (k >= j) {
        break label190;
      }
    }
    label190:
    for (int j = j - k - ((String)localObject1).length();; j = k - j - ((String)localObject2).length())
    {
      if (j < i) {
        localArrayList.clear();
      }
      if (j > i) {
        break label83;
      }
      localArrayList.add(localObject1);
      i = j;
      break label83;
      break;
    }
  }
}

/* Location:
 * Qualified Name:     apy
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */