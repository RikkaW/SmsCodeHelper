import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import com.ted.android.contacts.bubble.SmsCoreDatabaseHelper;
import com.ted.android.contacts.common.DataBus;
import com.ted.android.data.bubbleAction.CommonAction;
import com.ted.android.smscard.CardBase;
import com.ted.android.smscard.CardBaseUtils;
import com.ted.android.smscard.CardbaseFactory;
import com.ted.android.utils.TedSDKLog;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONObject;

public class bn
{
  private static final String j = bn.class.getSimpleName();
  private static final Pattern k = Pattern.compile("(\\d+)");
  public int a;
  public String b;
  public String c;
  public String d;
  public String e;
  public String f;
  public int g = 0;
  public String h;
  public String i;
  
  private int a(int paramInt)
  {
    int m = paramInt;
    if (paramInt > 100000) {
      m = paramInt / 100000 * 100000;
    }
    return m;
  }
  
  private String a(String paramString, Matcher paramMatcher)
  {
    int m = 1;
    for (;;)
    {
      if (m > paramMatcher.groupCount()) {
        return paramString;
      }
      String str2 = "{group" + m + "}";
      String str1 = paramString;
      if (paramString.indexOf(str2) > -1) {
        str1 = paramString.replace(str2, paramMatcher.group(m));
      }
      m += 1;
      paramString = str1;
    }
  }
  
  public static List<bn> a(Cursor paramCursor, boolean paramBoolean)
  {
    ArrayList localArrayList = new ArrayList();
    if ((paramCursor != null) && (paramCursor.getCount() > 0)) {
      paramCursor.moveToFirst();
    }
    for (;;)
    {
      if (paramCursor.isAfterLast()) {
        return localArrayList;
      }
      localArrayList.add(b(paramCursor, paramBoolean));
      paramCursor.moveToNext();
    }
  }
  
  private void a(String paramString, CardBase paramCardBase, Matcher paramMatcher)
  {
    try
    {
      TedSDKLog.d(j, "MenuString: " + paramString);
      paramString = new JSONObject(paramString).getJSONArray("{group0}");
      ArrayList localArrayList = new ArrayList();
      if (paramString != null)
      {
        int m = 0;
        for (;;)
        {
          if (m >= paramString.length())
          {
            if (localArrayList.size() <= 0) {
              break;
            }
            paramCardBase.setActions(localArrayList);
            return;
          }
          localArrayList.add(new CommonAction(null, a(paramString.getString(m), paramMatcher)));
          m += 1;
        }
      }
      return;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  private static bn b(Cursor paramCursor, boolean paramBoolean)
  {
    bn localbn = new bn();
    a = paramCursor.getInt(paramCursor.getColumnIndex("b_id"));
    String str2 = paramCursor.getString(paramCursor.getColumnIndex("b_regex"));
    String str1 = str2;
    if (paramBoolean) {
      str1 = anv.b(str2, DataBus.FILE_MASK);
    }
    b = str1;
    c = paramCursor.getString(paramCursor.getColumnIndex("b_number"));
    d = paramCursor.getString(paramCursor.getColumnIndex("b_json_string"));
    d = paramCursor.getString(paramCursor.getColumnIndex("b_json_string"));
    e = paramCursor.getString(paramCursor.getColumnIndex("b_keys"));
    f = paramCursor.getString(paramCursor.getColumnIndex("b_type"));
    g = paramCursor.getInt(paramCursor.getColumnIndex("b_show_type"));
    h = paramCursor.getString(paramCursor.getColumnIndex("b_card_title"));
    i = paramCursor.getString(paramCursor.getColumnIndex("b_card_subtitle"));
    return localbn;
  }
  
  private String b(String paramString)
  {
    String str = paramString;
    if (!paramString.isEmpty()) {
      if ((!paramString.endsWith("。")) && (!paramString.endsWith("，")))
      {
        str = paramString;
        if (!paramString.endsWith("")) {}
      }
      else
      {
        str = paramString.substring(0, paramString.length() - 1);
      }
    }
    return str;
  }
  
  public bn a(Context paramContext)
  {
    int n = 0;
    int m = a(a);
    if (m < 100000) {}
    do
    {
      return this;
      m = a(m);
      localObject2 = SmsCoreDatabaseHelper.a(paramContext).a(m);
    } while (localObject2 == null);
    if ((g == -1) && (g != -1)) {
      g = g;
    }
    paramContext = b;
    String str = b;
    Object localObject1 = paramContext;
    if (!TextUtils.isEmpty(paramContext))
    {
      localObject1 = paramContext;
      if (!TextUtils.isEmpty(str))
      {
        localObject1 = str.split(",");
        m = 0;
      }
    }
    for (;;)
    {
      if (m >= localObject1.length)
      {
        localObject1 = paramContext;
        b = ((String)localObject1);
        paramContext = d;
        localObject2 = d;
        localObject1 = paramContext;
        if (!TextUtils.isEmpty(paramContext))
        {
          localObject1 = paramContext;
          if (!TextUtils.isEmpty((CharSequence)localObject2))
          {
            localObject1 = ((String)localObject2).split(",");
            m = n;
            if (m < localObject1.length) {
              break;
            }
            localObject1 = paramContext;
          }
        }
        d = ((String)localObject1);
        return this;
      }
      paramContext = paramContext.replace("{ted" + (m + 1) + "}", localObject1[m]);
      m += 1;
    }
    Object localObject2 = "{ted" + (m + 1) + "}";
    if (TextUtils.equals("*", localObject1[m])) {}
    for (paramContext = paramContext.replace((CharSequence)localObject2, "");; paramContext = paramContext.replace((CharSequence)localObject2, localObject1[m]))
    {
      m += 1;
      break;
    }
  }
  
  public CardBase a(String paramString)
  {
    Object localObject1 = null;
    Object localObject2 = b;
    String str1 = f;
    Object localObject3 = e;
    String str2 = d;
    TedSDKLog.d(j, "Checking Regex id: " + a);
    localObject3 = Pattern.compile((String)localObject2).matcher(paramString);
    paramString = (String)localObject1;
    int n;
    int m;
    if (((Matcher)localObject3).find())
    {
      localObject2 = CardbaseFactory.creator(str1);
      mCardType = CardBaseUtils.convertTypeToInt(str1);
      ((CardBase)localObject2).setMatchedId(a);
      a(str2, (CardBase)localObject2, (Matcher)localObject3);
      n = ((Matcher)localObject3).groupCount();
      paramString = "";
      m = 1;
      if (m > n)
      {
        if ((!TextUtils.isEmpty(i)) && (!TextUtils.isEmpty(paramString))) {
          ((CardBase)localObject2).setSubTitle(a(i, paramString));
        }
        if ((!TextUtils.isEmpty(e)) && (!TextUtils.isEmpty(paramString)))
        {
          localObject1 = e;
          if (e.contains(i)) {
            localObject1 = e.replace(i, "");
          }
          ((CardBase)localObject2).addData(a((String)localObject1, paramString));
        }
        if ((!TextUtils.isEmpty(h)) && (!TextUtils.isEmpty(paramString))) {
          ((CardBase)localObject2).setTitle(a(h, paramString));
        }
        paramString = (String)localObject2;
      }
    }
    else
    {
      return paramString;
    }
    str1 = ((Matcher)localObject3).group(m);
    localObject1 = str1;
    if (TextUtils.isEmpty(str1)) {
      localObject1 = "null";
    }
    if (m == n) {}
    for (paramString = paramString + (String)localObject1;; paramString = paramString + (String)localObject1 + "<teddy>")
    {
      m += 1;
      break;
    }
  }
  
  public LinkedHashMap<String, String> a(String paramString1, String paramString2)
  {
    ArrayList localArrayList4 = new ArrayList();
    ArrayList localArrayList2 = new ArrayList();
    ArrayList localArrayList1 = new ArrayList();
    ArrayList localArrayList3 = new ArrayList();
    Object localObject2 = paramString1.toCharArray();
    Object localObject1 = "";
    int m = 0;
    String[] arrayOfString1;
    int n;
    label121:
    label130:
    int i1;
    int i2;
    if (m >= localObject2.length)
    {
      arrayOfString1 = paramString2.split("<teddy>");
      if (localArrayList4.size() == 0)
      {
        localArrayList1 = localArrayList2;
        n = 0;
      }
    }
    else
    {
      String[] arrayOfString2;
      for (;;)
      {
        if (n >= localArrayList4.size())
        {
          paramString1 = new LinkedHashMap();
          if (localArrayList3.size() <= 0) {
            break label959;
          }
          paramString2 = localArrayList1.iterator();
          m = 0;
          if (paramString2.hasNext()) {
            break label906;
          }
          return paramString1;
          n = localObject2[m];
          if (n == 60) {
            if (m - 1 > 0)
            {
              paramString1 = (String)localObject1;
              if (localObject2[(m - 1)] == '>') {}
            }
            else
            {
              paramString1 = (String)localObject1;
              if (localObject1 != "")
              {
                localArrayList2.add(localObject1);
                paramString1 = "";
              }
            }
          }
          for (;;)
          {
            if (m == localObject2.length - 1) {
              localArrayList2.add(paramString1);
            }
            m += 1;
            localObject1 = paramString1;
            break;
            if (n == 62)
            {
              if ((m + 1 >= localObject2.length) || (localObject2[(m + 1)] != '<'))
              {
                paramString1 = (String)localObject1;
                if (localObject1 != "")
                {
                  localArrayList4.add(localObject1);
                  paramString1 = "";
                }
              }
              else
              {
                paramString1 = localObject1 + "<teddy>";
              }
            }
            else {
              paramString1 = localObject1 + localObject2[m];
            }
          }
        }
        arrayOfString2 = ((String)localArrayList4.get(n)).split("<teddy>");
        int i3 = arrayOfString2.length;
        i1 = 0;
        i2 = 0;
        localObject1 = "";
        label355:
        if (i1 < i3) {
          break label410;
        }
        if (i2 == 0)
        {
          localArrayList1.add((String)localArrayList2.get(n));
          localArrayList3.add(b((String)localObject1));
        }
        n += 1;
      }
      label410:
      paramString1 = arrayOfString2[i1];
      if (paramString1.replaceAll("\\d+", "").equals(""))
      {
        paramString2 = arrayOfString1[(Integer.parseInt(paramString1) - 1)].replace(" ", "");
        m = i2;
        paramString1 = (String)localObject1;
        if (!TextUtils.isEmpty(paramString2))
        {
          if (!"null".equals(paramString2)) {
            break label495;
          }
          paramString1 = (String)localObject1;
          m = i2;
        }
      }
    }
    for (;;)
    {
      label480:
      i1 += 1;
      i2 = m;
      localObject1 = paramString1;
      break label355;
      label495:
      paramString1 = localObject1 + paramString2 + " ";
      m = i2;
      continue;
      boolean bool = paramString1.startsWith("!");
      localObject2 = paramString1;
      if (bool) {
        localObject2 = paramString1.substring(1);
      }
      String str1 = ((String)localObject2).replaceAll("\\d+", "");
      paramString2 = (String)localObject1;
      label906:
      label959:
      do
      {
        try
        {
          String str2 = arrayOfString1[(Integer.parseInt(localObject2.replace(str1, "")) - 1)];
          paramString2 = (String)localObject1;
          if (TextUtils.isEmpty(str2)) {
            continue;
          }
          paramString2 = (String)localObject1;
          if ("null".equals(str2)) {
            continue;
          }
          paramString2 = (String)localObject1;
          if (str1.equals(""))
          {
            paramString2 = (String)localObject1;
            paramString1 = localObject1 + str2 + " ";
            m = i2;
            break label480;
          }
          paramString2 = (String)localObject1;
          if (localObject2.split("\\d+")[0].equals(str1))
          {
            paramString2 = (String)localObject1;
            paramString1 = localObject1 + str1;
            paramString2 = paramString1;
            paramString1 = paramString1 + str2;
            m = i2;
            break label480;
          }
          paramString2 = (String)localObject1;
          paramString1 = localObject1 + str2;
          paramString2 = paramString1;
          paramString1 = paramString1 + str1;
          m = i2;
        }
        catch (NumberFormatException paramString1)
        {
          localObject1 = k.matcher((CharSequence)localObject2);
          m = i2;
          paramString1 = paramString2;
        }
        if (!((Matcher)localObject1).find()) {
          break label480;
        }
        localObject1 = ((Matcher)localObject1).group(1);
        m = i2;
        paramString1 = paramString2;
        if (!TextUtils.isDigitsOnly((CharSequence)localObject1)) {
          break label480;
        }
        paramString1 = arrayOfString1[(Integer.parseInt(localObject1) - 1)];
        if (((TextUtils.isEmpty(paramString1)) || ("null".equals(paramString1))) && (bool))
        {
          m = 1;
          paramString1 = paramString2;
          break label480;
        }
        paramString1 = ((String)localObject2).replace((CharSequence)localObject1, paramString1);
        paramString1 = paramString2 + paramString1;
        m = i2;
        break label480;
        localObject1 = (String)paramString2.next();
        if (!TextUtils.isEmpty((CharSequence)localArrayList3.get(m))) {
          paramString1.put(localObject1, (String)localArrayList3.get(m));
        }
        m += 1;
        break label121;
        paramString2 = localArrayList1.iterator();
        while (paramString2.hasNext()) {
          paramString1.put((String)paramString2.next(), "");
        }
        break label130;
        break;
      } while (!bool);
      m = 1;
      paramString1 = (String)localObject1;
    }
  }
}

/* Location:
 * Qualified Name:     bn
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */