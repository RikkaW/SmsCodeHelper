import android.content.Context;
import android.text.TextUtils;
import com.ted.android.contacts.bubble.SmsCoreDatabaseHelper;
import com.ted.android.contacts.bubble.SmsCoreEngine;
import com.ted.android.data.BubbleEntity;
import com.ted.android.data.bubbleAction.ActionBase;
import com.ted.android.data.bubbleAction.CommonAction;
import com.ted.android.utils.TedSDKLog;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class aqg
  extends aqc
{
  private static final String b = aqg.class.getSimpleName();
  private static aqg c;
  ash a;
  private Context d;
  private atd e;
  
  private aqg(Context paramContext)
  {
    d = paramContext;
    e = atd.a();
    a = new ash();
  }
  
  public static aqg a(Context paramContext)
  {
    if (c == null) {}
    try
    {
      c = new aqg(paramContext);
      return c;
    }
    finally {}
  }
  
  private List<BubbleEntity> a(bn parambn, String paramString1, String paramString2)
  {
    int k = 0;
    ArrayList localArrayList = new ArrayList();
    bn localbn = parambn.a(d);
    for (;;)
    {
      Matcher localMatcher;
      int i;
      try
      {
        localMatcher = Pattern.compile(b).matcher(paramString1);
        if (localMatcher.find())
        {
          int m = localMatcher.groupCount();
          i = 0;
          if (i <= m) {}
        }
        else
        {
          return localArrayList;
        }
      }
      catch (PatternSyntaxException parambn)
      {
        parambn.printStackTrace();
        return localArrayList;
      }
      int j;
      try
      {
        paramString1 = new JSONObject(d);
        String str1 = localMatcher.group(i);
        TedSDKLog.d(b, "Matched Words: " + str1);
        if ((TextUtils.isEmpty(str1)) && (i != 0))
        {
          j = k;
        }
        else
        {
          int n = localMatcher.start(i);
          paramString2 = "{group" + i + "}";
          String str2 = "\\{group" + i + "\\}";
          j = k;
          if (d.contains(paramString2))
          {
            paramString2 = paramString1.getString(paramString2);
            j = k;
            if (!TextUtils.isEmpty(paramString2))
            {
              BubbleEntity localBubbleEntity = new BubbleEntity();
              if (i == 0)
              {
                localBubbleEntity.setIndex(-1);
                paramString1 = paramString2;
                label248:
                localBubbleEntity.setId(String.valueOf(a));
                localBubbleEntity.setMatchedWords(str1);
                localBubbleEntity.setShowType(g);
                paramString1 = a(localBubbleEntity, paramString1);
                if ((paramString1 != null) && (paramString1.size() != 0)) {
                  break label356;
                }
              }
              for (;;)
              {
                localArrayList.add(localBubbleEntity);
                k += 1;
                j = k;
                if (k < 3) {
                  break label382;
                }
                break;
                localBubbleEntity.setIndex(n);
                paramString1 = paramString2;
                if (TextUtils.isEmpty(str1)) {
                  break label248;
                }
                paramString1 = paramString2.replaceAll(str2, str1);
                break label248;
                label356:
                localBubbleEntity.addActions(paramString1);
              }
            }
          }
        }
        i += 1;
      }
      catch (JSONException paramString1)
      {
        j = k;
      }
      catch (PatternSyntaxException paramString1)
      {
        paramString1.printStackTrace();
        j = k;
      }
      label382:
      k = j;
    }
  }
  
  private List<ActionBase> a(BubbleEntity paramBubbleEntity, String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    for (;;)
    {
      int i;
      try
      {
        paramString = new JSONArray(paramString);
        i = 0;
        if (i >= paramString.length()) {
          return localArrayList;
        }
        CommonAction localCommonAction = new CommonAction(paramBubbleEntity, paramString.getString(i));
        if (((action != 3) || (!TextUtils.isEmpty(url))) && ((action != 8) || (!TextUtils.isEmpty(number))) && ((action != 10) || (!TextUtils.isEmpty(address))) && (!"阿里巴巴".equals(buttonText))) {
          localArrayList.add(localCommonAction);
        }
      }
      catch (JSONException paramBubbleEntity)
      {
        return localArrayList;
      }
      i += 1;
    }
  }
  
  private List<BubbleEntity> a(String paramString1, String paramString2, String paramString3)
  {
    ArrayList localArrayList = new ArrayList();
    paramString2 = SmsCoreEngine.searchBubble(paramString2, paramString3);
    if ((paramString2 == null) || (paramString2.length == 0)) {
      return localArrayList;
    }
    new StringBuilder();
    paramString2 = SmsCoreDatabaseHelper.a(d).a(paramString2).iterator();
    for (;;)
    {
      if (!paramString2.hasNext()) {
        return localArrayList;
      }
      List localList = a((bn)paramString2.next(), paramString1, paramString3);
      if ((localList != null) && (localList.size() > 0)) {
        localArrayList.addAll(localList);
      }
    }
  }
  
  protected List<BubbleEntity> a(String paramString1, String paramString2)
  {
    return a(paramString1, e.a(paramString1), paramString2);
  }
}

/* Location:
 * Qualified Name:     aqg
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */