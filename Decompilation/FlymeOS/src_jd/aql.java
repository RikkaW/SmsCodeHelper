import android.text.TextUtils;
import com.ted.android.contacts.common.DataBus;
import com.ted.android.data.BubbleEntity;
import com.ted.android.data.bubbleAction.VerificationCodeAction;
import com.ted.android.message.MessageUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class aql
  extends aqc
{
  private static final String a = aql.class.getSimpleName();
  private static aql b;
  private static String[] c = { "12306新验证码" };
  private static String[] d = { "影", "取票", "团购", "出示", "美团券", "优惠券", "代金券" };
  private static String[] e = { "验证码", "验证代码", "校验码", "动态码", "动态密码", "确认码", "激活码", "认证码", "上网密码", "验证短信", "无线登录密码", "aWiFi登录密码", "WLAN密码" };
  private static String[] f = { "验证码", "验证代码", "校验码", "动态码", "动态密码", "确认码", "激活码", "认证码", "上网密码", "验证码", "登录密码", "登录密码", "登录密码" };
  private static String[] g = { "^(?:app|118114|116114|10010|10086|10000|12345|google|google|51job|51talk|88wifi|alipay|andriod|android|arrayvpn|awifi|baidu|bbtree|card|cctv|chaowifi|club|cmbchina|cmcc|cmhebao|cmwap|csdn|ctrip|ctrip|dear|deeplyfu|download|ebank|email|email|facebook|facebook|free|freewifi|guide|http|https|icbc|imax|ipad|iphone|ireader|itunes|jinhua|jumei|laiwang|liepin|login|netpay|oppo|pass|pingan|pinyin|ppmoney|pptv|rmb|samsung|shop|tantan|taobao|tenpay|tianya|tv189|usim|uu898|vivo|watson|watsons|wecash|weibo|weixin|wifi|witown|wlan|online|password|cvv2|cvv|cvc2|cvc|95155|95313|95316|95319|95333|95352|95356|95395|95398|95501|95508|95526|95527|95528|95533|95537|95541|95555|95558|95559|95561|95566|95568|95574|95577|95580|95588|95593|95594|95595|95599|955112|955113|955333|955533|955558|955559|955580|955588|955599|955666|955800|955886|955993|955999|9550833|9551186|9551286|9555801|9555812|9555816|9556662|9570277|9572150|95105565|95105588|95105665|95105757|95196558|95558000|95588000|955119005|955580788|955581006|955581101|955581110|955800118|9545397913|9546011284|9546011680|9546011941|9551290003|95558100202261487|9559315103003370|955128695511|angelababy|baby|uber|microsoft|tlsg)$", "^(?:rmb|iphone)[\\s\\S]*$" };
  private static String[] h = { ",", "，", ".", "。", "!", "！", "(", "（", " " };
  private static String i = "4e407154c818b14ac943e0fa5f3490d27f6f6c24537cac2f3ae9bd9b8773223c1301f051a8ee8113aa12f89fcb3aecc051562c38e9febe43047de9870de76178d85fa9a99b0c9faf1da472bc393cba2ae2e2cb42c78fa61daa2e955dfcd535697d80cf1733e0126eb28c57309a11fdd74fbac09f0ae43d197bd14e2b22cdeb199a5fea409505bc97ceadfefe78e2fe9de3933dc2a644ad6de14deb3f6f3dfc91cf9f30ff937e49be79b2a7d8431c04d0e82894fd014ef6c954e74d8ee3c1c8237fd6b27595d7fa10c887c095ba96a0178f5d08aaa7fe44f7d814b201d7886753558d7de8c116e65693bbab7f757ecae7480810bd1db7d3ee3d1fdc4effbe79a0ee9a3d9d33161252d0e17355364298ba69f0677c214b403e0197a59d50d440a5e5929be753a0e1db2587dd9a9d39cce61dace9c0c2bdaa76595eb3414bc4b0f1fe5c424e0d03895932c4eae068a7ec8a73901ae8116fb9b4cb1980ca79ddac478cee3119743331427f2280e26e3ee7d12d1063f88efb79486f1b98115a1fe5e061ffaf4e685bb97fb7898a15aff2a106566787287d7aa7d39f6b94c5ea4edb5cc6db2f6598d8bd9fbde2d7ca2e620dbbdfff7bd74d657200eb4a4aeee0681ddb715f8620b1c4ff43fde0c135089c7cda12b9bdd933500a89cdc40d452026bd99c5da8cdf18505c1d37cfb5f0057f15cbdf5ad34e04753e2e6282d358dcc1ea697b12707afd4c7e3cd4ba913795c69fc639084a601e99599800376fe6c50855e75e179090e48659519580cce5e355d3fd613326e912a7f224d7b9e17c5b1abc3f444c0cfa9fa06f31fe0a95ce83578aefb3d676e44be2f4615c43d6e3da8e330b4944aebc9d9a1334";
  private static String j = "22dbf63a1ab196ee516b35e97ba990cc0d93655778c7eb75b29ed6bd27c82722133ee6bb7a0cbb8ca0d52f569a110d949d06be5c52671e491e42ff743865273577182a79806ab629e052fce59b30bdd3bf0656beacf68f6aa6484f4167991fa2afd0f0c973a931c294ac157ca32d86353d1fdc4effbe79a0ee9a3d9d33161252d0e17355364298ba69f0677c214b403e0197a59d50d440a5e5929be753a0e1db2587dd9a9d39cce61dace9c0c2bdaa76595eb3414bc4b0f1fe5c424e0d03895932c4eae068a7ec8a73901ae8116fb9b4cb1980ca79ddac474f0270f7abb0b578f3df644f66182411869aa43fcdf61e2359437d1e5a70086855678bc7cdbcc5664e49bce3261937dd542d2ac68241945e1deb99194795f439dce492e2381805948c5b09c843d7f043132b838e21d35b1a10d127a96bf123217f1c09989bac4fec506dbb587573bc84f2199da3dbace831345355e60c90b34449f44475bf0637869e918703f642f110d1b284a1c81001853daf43217e73003a2e16da69092122e2cf6560bba47cc4ca089e3981b44c744d";
  private static String k = anv.b(i, DataBus.FILE_MASK);
  private static String l = anv.b(j, DataBus.FILE_MASK);
  private static String m = "4e407154c818b14ac943e0fa5f3490d27f6f6c24537cac2f3ae9bd9b8773223c1301f051a8ee8113aa12f89fcb3aecc051562c38e9febe43047de9870de76178d85fa9a99b0c9faf1da472bc393cba2ae2e2cb42c78fa61daa2e955dfcd535697d80cf1733e0126eb28c57309a11fdd74fbac09f0ae43d197bd14e2b22cdeb19df15a60a38ed1bb393bbab7f757ecae7480810bd1db7d3ee3d1fdc4effbe79a0ee9a3d9d33161252d0e17355364298ba69f0677c214b403e0197a59d50d440a5e5929be753a0e1db2587dd9a9d39cce61dace9c0c2bdaa76595eb3414bc4b0f1fe5c424e0d03895932c4eae068a7ec8a73901ae8116fb9b4cb1980ca79ddac478cee3119743331427f2280e26e3ee7d13aa5c083d3a1899fb1022ab13184d69bbce94dd5b838fc5e303c95d96e53ff39";
  private static String n = anv.b(m, DataBus.FILE_MASK);
  
  public static aql a()
  {
    if (b == null) {}
    try
    {
      b = new aql();
      return b;
    }
    finally {}
  }
  
  private static String a(String[] paramArrayOfString, String paramString)
  {
    int i2 = 0;
    if ((paramArrayOfString == null) || (paramArrayOfString.length == 0)) {
      return "";
    }
    StringBuilder localStringBuilder = new StringBuilder();
    int i3 = paramArrayOfString.length;
    int i1 = 0;
    if (i1 >= i3) {
      return localStringBuilder.toString();
    }
    String str = paramArrayOfString[i1];
    if (i2 != 0) {
      localStringBuilder.append(paramString);
    }
    for (;;)
    {
      localStringBuilder.append(str);
      i1 += 1;
      break;
      i2 = 1;
    }
  }
  
  private static List<String> a(HashMap<Integer, String> paramHashMap)
  {
    ArrayList localArrayList = new ArrayList();
    if (paramHashMap.size() == 2)
    {
      paramHashMap = (String[])paramHashMap.values().toArray(new String[paramHashMap.values().size()]);
      if ((paramHashMap[0].contains(paramHashMap[1])) || (paramHashMap[1].contains(paramHashMap[0])))
      {
        if (paramHashMap[0].length() < paramHashMap[1].length()) {
          break label92;
        }
        localArrayList.add(paramHashMap[0]);
      }
    }
    return localArrayList;
    label92:
    localArrayList.add(paramHashMap[1]);
    return localArrayList;
  }
  
  private List<String> a(HashMap<Integer, String> paramHashMap, String paramString)
  {
    paramString = new ArrayList();
    paramHashMap = paramHashMap.entrySet().iterator();
    Map.Entry localEntry;
    do
    {
      if (!paramHashMap.hasNext()) {
        return paramString;
      }
      localEntry = (Map.Entry)paramHashMap.next();
    } while (((Integer)localEntry.getKey()).intValue() != 0);
    paramString.add((String)localEntry.getValue());
    return paramString;
  }
  
  private Map<String, String> a(HashMap<Integer, String> paramHashMap1, HashMap<Integer, String> paramHashMap2)
  {
    HashMap localHashMap = new HashMap();
    Iterator localIterator1 = paramHashMap1.entrySet().iterator();
    int i1 = Integer.MAX_VALUE;
    if (!localIterator1.hasNext()) {
      return localHashMap;
    }
    paramHashMap1 = (Map.Entry)localIterator1.next();
    int i3 = ((Integer)paramHashMap1.getKey()).intValue();
    String str1 = (String)paramHashMap1.getValue();
    Iterator localIterator2 = paramHashMap2.entrySet().iterator();
    label83:
    int i2;
    while (localIterator2.hasNext())
    {
      paramHashMap1 = (Map.Entry)localIterator2.next();
      i2 = ((Integer)paramHashMap1.getKey()).intValue();
      paramHashMap1 = (String)paramHashMap1.getValue();
      if (i3 >= i2) {
        break label227;
      }
      i2 = i2 - i3 - str1.length();
      label151:
      if (i2 < i1) {
        localHashMap.clear();
      }
      if (i2 <= i1)
      {
        if (TextUtils.isEmpty(paramHashMap1)) {
          break label262;
        }
        String str2 = e(paramHashMap1);
        if (TextUtils.isEmpty(str2)) {
          break label242;
        }
        paramHashMap1 = "复制" + str2;
      }
    }
    for (;;)
    {
      localHashMap.put(str1, paramHashMap1);
      i1 = i2;
      break label83;
      break;
      label227:
      i2 = i3 - i2 - paramHashMap1.length();
      break label151;
      label242:
      paramHashMap1 = "复制" + paramHashMap1;
      continue;
      label262:
      paramHashMap1 = "复制验证码";
    }
  }
  
  private boolean a(String paramString, String[] paramArrayOfString)
  {
    paramString = paramString.toLowerCase();
    int i2 = paramArrayOfString.length;
    int i1 = 0;
    for (;;)
    {
      if (i1 >= i2) {
        return false;
      }
      if (paramString.contains(paramArrayOfString[i1].toLowerCase())) {
        return true;
      }
      i1 += 1;
    }
  }
  
  private boolean b(String paramString, String[] paramArrayOfString)
  {
    paramString = paramString.toLowerCase();
    int i2 = paramArrayOfString.length;
    int i1 = 0;
    for (;;)
    {
      if (i1 >= i2) {
        return false;
      }
      if (paramString.matches(paramArrayOfString[i1])) {
        return true;
      }
      i1 += 1;
    }
  }
  
  private String d(String paramString)
  {
    String str = paramString.trim();
    int i1 = str.length();
    int i2;
    HashMap localHashMap;
    if (str.startsWith("["))
    {
      i2 = str.indexOf("]");
      paramString = str;
      if (i2 > 0)
      {
        paramString = str;
        if (i2 < i1 - 1)
        {
          localHashMap = b(str.substring(0, i2));
          if (localHashMap != null)
          {
            paramString = str;
            if (localHashMap.size() != 0) {}
          }
          else
          {
            paramString = str.substring(i2 + 1);
          }
        }
      }
    }
    do
    {
      do
      {
        do
        {
          do
          {
            return paramString;
            paramString = str;
          } while (!str.startsWith("【"));
          i2 = str.indexOf("】");
          paramString = str;
        } while (i2 <= 0);
        paramString = str;
      } while (i2 >= i1 - 1);
      localHashMap = b(str.substring(0, i2));
      if (localHashMap == null) {
        break;
      }
      paramString = str;
    } while (localHashMap.size() != 0);
    return str.substring(i2 + 1);
  }
  
  private String e(String paramString)
  {
    int i1 = 0;
    for (;;)
    {
      if (i1 >= e.length) {
        return null;
      }
      if (paramString.equals(e[i1])) {
        return f[i1];
      }
      i1 += 1;
    }
  }
  
  protected List<BubbleEntity> a(String paramString1, String paramString2)
  {
    paramString2 = new ArrayList();
    if (!MessageUtils.containsHanScript(paramString1)) {
      return paramString2;
    }
    Object localObject = a(paramString1);
    if ((localObject != null) && (((Map)localObject).size() > 0))
    {
      localObject = ((Map)localObject).entrySet().iterator();
      if (((Iterator)localObject).hasNext()) {}
    }
    else
    {
      return paramString2;
    }
    Map.Entry localEntry = (Map.Entry)((Iterator)localObject).next();
    BubbleEntity localBubbleEntity = new BubbleEntity();
    localBubbleEntity.setId("-1");
    localBubbleEntity.setIndex(paramString1.indexOf((String)localEntry.getKey()));
    localBubbleEntity.setMatchedWords((String)localEntry.getKey());
    VerificationCodeAction localVerificationCodeAction = new VerificationCodeAction(localBubbleEntity);
    if (!TextUtils.isEmpty((CharSequence)localEntry.getValue())) {}
    for (buttonText = ((String)localEntry.getValue());; buttonText = "复制验证码")
    {
      localVerificationCodeAction.setVerificationCode((String)localEntry.getKey());
      clip = ((String)localEntry.getKey());
      localBubbleEntity.addAction(localVerificationCodeAction);
      paramString2.add(localBubbleEntity);
      break;
    }
  }
  
  public Map<String, String> a(String paramString)
  {
    if (paramString == null) {
      return null;
    }
    if (a(paramString, c)) {
      return null;
    }
    Object localObject = aqo.a(paramString);
    HashMap localHashMap1 = c((String)localObject);
    String str1 = a(e, "|");
    l = String.format(l, new Object[] { str1 });
    paramString = Pattern.compile(l).matcher((CharSequence)localObject);
    boolean bool2 = paramString.find();
    boolean bool1 = bool2;
    if (!bool2)
    {
      n = String.format(n, new Object[] { str1 });
      paramString = Pattern.compile(n).matcher((CharSequence)localObject);
      bool1 = paramString.find();
    }
    HashMap localHashMap2;
    if (bool1)
    {
      localHashMap2 = new HashMap();
      str1 = paramString.group(1);
      if (!TextUtils.isEmpty(str1))
      {
        if (localHashMap1.containsValue("验证码"))
        {
          if (a((String)localObject, d)) {
            return null;
          }
        }
        else if ((!localHashMap1.containsValue("上网密码")) && (!localHashMap1.containsValue("无线登录密码")) && (!localHashMap1.containsValue("aWiFi登录密码")) && (!localHashMap1.containsValue("动态密码")) && (((String)localObject).contains("密码")) && (a((String)localObject, d))) {
          return null;
        }
        paramString = str1;
        if (str1.startsWith("-")) {
          paramString = str1.substring(1, str1.length());
        }
        if (!paramString.endsWith("-")) {
          break label809;
        }
      }
    }
    label802:
    label807:
    label809:
    for (str1 = paramString.substring(0, paramString.length() - 1);; str1 = paramString)
    {
      Iterator localIterator = localHashMap1.entrySet().iterator();
      for (;;)
      {
        if (!localIterator.hasNext())
        {
          if ((localHashMap2.size() == 0) && (!b(str1, g))) {
            localHashMap2.put(str1, "复制密码");
          }
          if ((localHashMap2 != null) && (localHashMap2.size() > 0)) {
            return localHashMap2;
          }
        }
        else
        {
          paramString = (String)((Map.Entry)localIterator.next()).getValue();
          if (TextUtils.isEmpty(paramString)) {
            break label802;
          }
          String str2 = e(paramString);
          if (!TextUtils.isEmpty(str2)) {
            paramString = "复制" + str2;
          }
        }
        for (;;)
        {
          if (b(str1, g)) {
            break label807;
          }
          localHashMap2.put(str1, paramString);
          break;
          paramString = "复制" + paramString;
          continue;
          if ((localHashMap1.containsValue("验证码")) && (a((String)localObject, d))) {
            return null;
          }
          if ((localHashMap1 == null) || (localHashMap1.size() == 0)) {
            return null;
          }
          str1 = d((String)localObject);
          paramString = MessageUtils.removePhoneNumber(MessageUtils.removeUrl(str1));
          if (paramString.length() > 100) {
            return null;
          }
          paramString = b(paramString);
          localObject = a(paramString, str1);
          if ((localObject != null) && (((List)localObject).size() > 0))
          {
            localHashMap2 = new HashMap();
            str1 = "复制验证码";
            paramString = str1;
            if (localHashMap1 != null)
            {
              paramString = str1;
              if (localHashMap1.size() > 0)
              {
                paramString = str1;
                if (!TextUtils.isEmpty((CharSequence)localHashMap1.get(Integer.valueOf(0)))) {
                  paramString = "复制" + (String)localHashMap1.get(Integer.valueOf(0));
                }
              }
            }
            localHashMap2.put((String)((List)localObject).get(0), paramString);
            return localHashMap2;
          }
          localObject = a(paramString);
          if ((localObject != null) && (((List)localObject).size() > 0))
          {
            localHashMap2 = new HashMap();
            str1 = "复制验证码";
            paramString = str1;
            if (localHashMap1 != null)
            {
              paramString = str1;
              if (localHashMap1.size() > 0)
              {
                paramString = str1;
                if (!TextUtils.isEmpty((CharSequence)localHashMap1.get(Integer.valueOf(0)))) {
                  paramString = "复制" + (String)localHashMap1.get(Integer.valueOf(0));
                }
              }
            }
            localHashMap2.put((String)((List)localObject).get(0), paramString);
            return localHashMap2;
          }
          return a(paramString, localHashMap1);
          paramString = "复制验证码";
        }
      }
    }
  }
  
  public HashMap<Integer, String> b(String paramString)
  {
    HashMap localHashMap = new HashMap();
    paramString = Pattern.compile(k).matcher(paramString);
    for (;;)
    {
      if (!paramString.find()) {
        return localHashMap;
      }
      int i1 = paramString.start(1);
      if (!b(paramString.group(1), g)) {
        localHashMap.put(Integer.valueOf(i1), paramString.group(1));
      }
    }
  }
  
  public HashMap<Integer, String> c(String paramString)
  {
    HashMap localHashMap = new HashMap();
    String[] arrayOfString = e;
    int i3 = arrayOfString.length;
    int i1 = 0;
    if (i1 >= i3) {
      return localHashMap;
    }
    String str = arrayOfString[i1];
    int i2 = 0;
    for (;;)
    {
      i2 = paramString.indexOf(str, i2);
      if (i2 == -1)
      {
        i1 += 1;
        break;
      }
      localHashMap.put(Integer.valueOf(i2), str);
      i2 += 1;
    }
  }
}

/* Location:
 * Qualified Name:     aql
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */