import android.app.Activity;
import android.content.res.Resources;
import android.os.Handler;
import android.text.format.DateUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.com.xy.sms.sdk.Iservice.XyCallBack;
import cn.com.xy.sms.sdk.constant.Constant;
import cn.com.xy.sms.sdk.net.NetUtil;
import cn.com.xy.sms.sdk.smsmessage.BusinessSmsMessage;
import cn.com.xy.sms.sdk.ui.popu.util.ContentUtil;
import cn.com.xy.sms.sdk.ui.popu.util.ResourceCacheUtil;
import cn.com.xy.sms.sdk.ui.popu.util.ViewManger;
import cn.com.xy.sms.sdk.util.JsonUtil;
import cn.com.xy.sms.sdk.util.StringUtils;
import cn.com.xy.sms.util.ParseManager;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class cw
  extends dg
{
  private static final String I = ContentUtil.getResourceString(Constant.getContext(), br.f.duoqu_no_train_info);
  private static boolean J = false;
  private RelativeLayout A;
  private int B = 0;
  private int C = 0;
  private int D = 0;
  private List<String> E = null;
  private JSONArray F = null;
  private int G = 0;
  private final Handler H = new Handler();
  private String K = "";
  private JSONArray L = new JSONArray();
  private int M = 0;
  private int N = 0;
  private int O = 0;
  private boolean P = true;
  public View.OnClickListener a = new cx(this);
  fc b = new cy(this);
  fd c = new db(this);
  private TextView d;
  private TextView j;
  private ImageView k = null;
  private TextView l;
  private TextView m;
  private TextView n;
  private TextView o;
  private ImageView p = null;
  private TextView q;
  private TextView r;
  private ImageView s = null;
  private ImageView t = null;
  private TextView u;
  private TextView v;
  private TextView w;
  private TextView x;
  private ImageView y;
  private ImageView z;
  
  public cw(Activity paramActivity, BusinessSmsMessage paramBusinessSmsMessage, XyCallBack paramXyCallBack, int paramInt1, ViewGroup paramViewGroup, int paramInt2)
  {
    super(paramActivity, paramBusinessSmsMessage, paramXyCallBack, paramInt1, paramViewGroup, paramInt2);
  }
  
  private JSONObject a(BusinessSmsMessage paramBusinessSmsMessage)
  {
    String str1 = "View_train_content_0";
    String str2 = (String)paramBusinessSmsMessage.getValue("db_air_data_index");
    if (!StringUtils.isNull(str2))
    {
      str1 = "View_train_content_" + str2;
      D = Integer.parseInt(str2);
    }
    return (JSONObject)paramBusinessSmsMessage.getValue(str1);
  }
  
  private JSONObject a(BusinessSmsMessage paramBusinessSmsMessage, int paramInt)
  {
    return (JSONObject)paramBusinessSmsMessage.getValue("View_train_content_" + paramInt);
  }
  
  private void a(String paramString, int paramInt)
  {
    String str = paramString;
    if (StringUtils.isNull(paramString)) {
      str = "#E6E6E6";
    }
    try
    {
      ViewManger.setViewBg(f, e, str, paramInt, 0);
      return;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  private void a(JSONArray paramJSONArray)
  {
    int i1 = 0;
    if (paramJSONArray == null)
    {
      c(false);
      return;
    }
    for (;;)
    {
      try
      {
        Object localObject = a(g);
        if (localObject == null) {
          return;
        }
        str1 = (String)JsonUtil.getValueFromJsonObject((JSONObject)localObject, "view_depart_city");
        String str3 = (String)JsonUtil.getValueFromJsonObject((JSONObject)localObject, "view_depart_date");
        str2 = (String)JsonUtil.getValueFromJsonObject((JSONObject)localObject, "view_arrive_city");
        localObject = (String)JsonUtil.getValueFromJsonObject((JSONObject)localObject, "view_arrive_time");
        if ((str2 == null) || (str2.equalsIgnoreCase(ContentUtil.NO_DATA)))
        {
          c(true);
          b(paramJSONArray);
        }
        if (str3 != null)
        {
          if (str3.equalsIgnoreCase(ContentUtil.NO_DATA))
          {
            break label825;
            int i2 = paramJSONArray.length();
            if (i1 < i2)
            {
              str3 = (String)JsonUtil.getValueFromJsonObject(paramJSONArray.getJSONObject(i1), "name");
              if ((i != 0) && (str3.equalsIgnoreCase(str1)))
              {
                localObject = (String)JsonUtil.getValueFromJsonObject(paramJSONArray.getJSONObject(i1), "stt");
                ContentUtil.setText(m, (String)localObject, ContentUtil.NO_DATA);
                g.bubbleJsonObj.put("db_train_depart_time_" + b(g), localObject);
              }
              if (!str3.equalsIgnoreCase(str2)) {
                continue;
              }
              paramJSONArray = (String)JsonUtil.getValueFromJsonObject(paramJSONArray.getJSONObject(i1), "spt");
              ContentUtil.setText(o, paramJSONArray, ContentUtil.NO_DATA);
              if (!StringUtils.isNull(paramJSONArray)) {
                continue;
              }
              b(false);
              g.bubbleJsonObj.put("db_train_arrive_time_" + b(g), paramJSONArray);
            }
          }
          else
          {
            i = 0;
            continue;
          }
          b(true);
          continue;
        }
      }
      catch (Exception paramJSONArray)
      {
        paramJSONArray.printStackTrace();
        return;
        i1 += 1;
        continue;
      }
      finally
      {
        String str1 = (String)g.getValue("db_train_arrive_city_" + b(g));
        String str2 = (String)g.getValue("db_train_arrive_time_" + b(g));
        if (!StringUtils.isNull(str1)) {
          ContentUtil.setText(v, str1, ContentUtil.NO_DATA);
        }
        if (!StringUtils.isNull(str2))
        {
          ContentUtil.setText(o, str2, ContentUtil.NO_DATA);
          b(true);
        }
      }
      label825:
      int i = 1;
    }
  }
  
  private void a(JSONObject paramJSONObject)
  {
    if (paramJSONObject == null)
    {
      f();
      return;
    }
    ContentUtil.setText(j, (String)JsonUtil.getValueFromJsonObject(paramJSONObject, "train_seat_arr_complex_LIST"), ContentUtil.NO_DATA);
    Object localObject1 = (String)JsonUtil.getValueFromJsonObject(paramJSONObject, "view_name");
    Object localObject2;
    String str1;
    String str2;
    if (!StringUtils.isNull(((String)localObject1).replaceAll(" ", "").replaceAll(",", "")))
    {
      ContentUtil.setText(r, (String)localObject1, ContentUtil.NO_DATA);
      q.setVisibility(0);
      r.setVisibility(0);
      t.setVisibility(0);
      p = t;
      f();
      localObject1 = (String)g.getValue("db_train_depart_time_" + b(g));
      if ((StringUtils.isNull((String)JsonUtil.getValueFromJsonObject(paramJSONObject, "view_depart_time"))) && (StringUtils.isNull((String)JsonUtil.getValueFromJsonObject(paramJSONObject, "view_depart_date")))) {
        break label634;
      }
      localObject2 = "";
      str1 = (String)JsonUtil.getValueFromJsonObject(paramJSONObject, "view_depart_time");
      str2 = (String)JsonUtil.getValueFromJsonObject(paramJSONObject, "view_depart_date");
      if (!StringUtils.isNull(str2)) {
        localObject2 = "" + str2;
      }
      localObject1 = localObject2;
      if (!StringUtils.isNull(str2))
      {
        localObject1 = localObject2;
        if (!StringUtils.isNull(str1)) {
          localObject1 = (String)localObject2 + " ";
        }
      }
      localObject2 = localObject1;
      if (!StringUtils.isNull(str1)) {
        localObject2 = (String)localObject1 + str1;
      }
      ContentUtil.setText(m, (String)localObject2, ContentUtil.NO_DATA);
      label318:
      localObject1 = (String)g.getValue("db_train_arrive_time_" + b(g));
      if (StringUtils.isNull((String)localObject1)) {
        break label655;
      }
      ContentUtil.setText(o, (String)localObject1, ContentUtil.NO_DATA);
      b(true);
      localObject2 = A.getLayoutParams();
      localObject1 = (String)JsonUtil.getValueFromJsonObject(paramJSONObject, "view_depart_city");
      str1 = (String)JsonUtil.getValueFromJsonObject(paramJSONObject, "view_arrive_city");
      if ((StringUtils.isNull((String)localObject1)) && (StringUtils.isNull(str1)))
      {
        u.setVisibility(8);
        v.setVisibility(8);
      }
      ContentUtil.setText(u, (String)localObject1, ContentUtil.NO_DATA);
      ContentUtil.setText(v, str1, ContentUtil.NO_DATA);
      w.setVisibility(0);
      x.setVisibility(0);
      localObject1 = (String)JsonUtil.getValueFromJsonObject(paramJSONObject, "view_seat_type");
      str1 = (String)JsonUtil.getValueFromJsonObject(paramJSONObject, "view_carriage_and_seat_number");
      if ((!StringUtils.isNull((String)localObject1)) || (!StringUtils.isNull(str1))) {
        break label825;
      }
      w.setVisibility(8);
      x.setVisibility(8);
    }
    label634:
    label655:
    label825:
    for (height = B;; height = C)
    {
      paramJSONObject = (JSONObject)localObject1;
      if (!StringUtils.isNull((String)localObject1)) {
        paramJSONObject = ((String)localObject1).replaceAll(",", " ");
      }
      ContentUtil.setText(w, paramJSONObject, ContentUtil.NO_DATA);
      ContentUtil.setText(x, str1, ContentUtil.NO_DATA);
      A.setLayoutParams((ViewGroup.LayoutParams)localObject2);
      return;
      q.setVisibility(8);
      r.setVisibility(8);
      t.setVisibility(8);
      p = s;
      break;
      if (StringUtils.isNull((String)localObject1)) {
        break label318;
      }
      ContentUtil.setText(m, (String)localObject1, ContentUtil.NO_DATA);
      break label318;
      str1 = (String)JsonUtil.getValueFromJsonObject(paramJSONObject, "view_arrive_time");
      str2 = (String)JsonUtil.getValueFromJsonObject(paramJSONObject, "view_arrive_date");
      localObject2 = "";
      if (!StringUtils.isNull(str2)) {
        localObject2 = "" + str2;
      }
      localObject1 = localObject2;
      if (!StringUtils.isNull(str2))
      {
        localObject1 = localObject2;
        if (!StringUtils.isNull(str1)) {
          localObject1 = (String)localObject2 + " ";
        }
      }
      localObject2 = localObject1;
      if (!StringUtils.isNull(str1)) {
        localObject2 = (String)localObject1 + str1;
      }
      if ((StringUtils.isNull(str1)) && (StringUtils.isNull(str2))) {
        b(false);
      }
      for (;;)
      {
        ContentUtil.setText(o, (String)localObject2, ContentUtil.NO_DATA);
        break;
        b(true);
      }
    }
  }
  
  private String b(BusinessSmsMessage paramBusinessSmsMessage)
  {
    paramBusinessSmsMessage = (String)paramBusinessSmsMessage.getValue("db_air_data_index");
    if (!StringUtils.isNull(paramBusinessSmsMessage)) {
      return "" + paramBusinessSmsMessage;
    }
    return "0";
  }
  
  private static JSONArray b(JSONArray paramJSONArray, String paramString1, String paramString2)
  {
    JSONArray localJSONArray = new JSONArray();
    int i2 = paramJSONArray.length();
    int i = 0;
    int i1 = 0;
    for (;;)
    {
      if (i < i2)
      {
        String str = (String)JsonUtil.getValueFromJsonObject(paramJSONArray.getJSONObject(i), "name");
        if (i1 != 0) {
          localJSONArray.put(paramJSONArray.getJSONObject(i));
        }
        if (str.equalsIgnoreCase(paramString1)) {
          i1 = 1;
        }
        if (!str.equalsIgnoreCase(paramString2)) {}
      }
      else
      {
        return localJSONArray;
      }
      i += 1;
    }
  }
  
  private void b(BusinessSmsMessage paramBusinessSmsMessage, int paramInt)
  {
    if ((paramBusinessSmsMessage == null) || (bubbleJsonObj == null) || (messageBody == null)) {
      return;
    }
    for (;;)
    {
      try
      {
        if (!StringUtils.isNull((String)paramBusinessSmsMessage.getValue("station_list_" + paramInt))) {
          break;
        }
        long l1 = bubbleJsonObj.optLong("QueryTime_" + paramInt);
        str1 = (String)JsonUtil.getValueFromJsonObject(bubbleJsonObj, "networkState");
        if (((DateUtils.isToday(l1)) && (!"offNetwork".equalsIgnoreCase(str1))) || ((!NetUtil.checkAccessNetWork()) && ("offNetwork".equalsIgnoreCase(str1)))) {
          break;
        }
        bubbleJsonObj.put("QueryTime_" + paramInt, System.currentTimeMillis());
        cz localcz = new cz(this, paramBusinessSmsMessage, paramInt);
        String str2 = String.valueOf(paramBusinessSmsMessage.getSmsId());
        str1 = (String)E.get(paramInt);
        if (str1 == null)
        {
          str1 = null;
          Object localObject = a(g, paramInt);
          if (localObject == null) {
            break;
          }
          String str3 = (String)JsonUtil.getValueFromJsonObject((JSONObject)localObject, "view_depart_city");
          localObject = (String)JsonUtil.getValueFromJsonObject((JSONObject)localObject, "view_arrive_city");
          HashMap localHashMap = new HashMap();
          localHashMap.put("phoneNumber", (String)paramBusinessSmsMessage.getValue("phoneNum"));
          localHashMap.put("titleNo", paramBusinessSmsMessage.getTitleNo());
          localHashMap.put("msgId", str2);
          localHashMap.put("bubbleJsonObj", bubbleJsonObj.toString());
          localHashMap.put("messageBody", paramBusinessSmsMessage.getMessageBody());
          bubbleJsonObj.put("networkState", null);
          ParseManager.queryTrainInfo(str2, str1, str3, (String)localObject, localHashMap, localcz);
          return;
        }
      }
      catch (JSONException paramBusinessSmsMessage)
      {
        paramBusinessSmsMessage.printStackTrace();
        return;
      }
      String str1 = str1.replace("次", "");
    }
  }
  
  private void b(JSONArray paramJSONArray)
  {
    paramJSONArray = new dc(this, paramJSONArray);
    if (v != null)
    {
      v.setOnClickListener(paramJSONArray);
      z.setOnClickListener(paramJSONArray);
    }
  }
  
  private void b(boolean paramBoolean)
  {
    if (P == paramBoolean) {
      return;
    }
    P = paramBoolean;
    if (paramBoolean)
    {
      a(n, M);
      a(o, M);
      a(s, O);
    }
    for (;;)
    {
      e.requestLayout();
      return;
      a(n, N);
      a(o, N);
      a(s, N);
    }
  }
  
  /* Error */
  private void c(BusinessSmsMessage paramBusinessSmsMessage)
  {
    // Byte code:
    //   0: aload_1
    //   1: ldc_w 485
    //   4: invokevirtual 160	cn/com/xy/sms/sdk/smsmessage/BusinessSmsMessage:getValue	(Ljava/lang/String;)Ljava/lang/Object;
    //   7: checkcast 162	java/lang/String
    //   10: astore_2
    //   11: aload_2
    //   12: invokestatic 168	cn/com/xy/sms/sdk/util/StringUtils:isNull	(Ljava/lang/String;)Z
    //   15: ifeq +4 -> 19
    //   18: return
    //   19: aload_0
    //   20: aload_2
    //   21: ldc_w 487
    //   24: invokevirtual 491	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   27: invokestatic 497	java/util/Arrays:asList	([Ljava/lang/Object;)Ljava/util/List;
    //   30: putfield 97	cw:E	Ljava/util/List;
    //   33: aload_0
    //   34: aload_1
    //   35: invokespecial 231	cw:a	(Lcn/com/xy/sms/sdk/smsmessage/BusinessSmsMessage;)Lorg/json/JSONObject;
    //   38: astore_2
    //   39: aload_2
    //   40: ifnull -22 -> 18
    //   43: aload_0
    //   44: aload_2
    //   45: invokespecial 499	cw:a	(Lorg/json/JSONObject;)V
    //   48: aload_0
    //   49: aload_0
    //   50: getfield 229	cw:g	Lcn/com/xy/sms/sdk/smsmessage/BusinessSmsMessage;
    //   53: invokespecial 145	cw:b	(Lcn/com/xy/sms/sdk/smsmessage/BusinessSmsMessage;)Ljava/lang/String;
    //   56: astore 4
    //   58: aload_1
    //   59: new 170	java/lang/StringBuilder
    //   62: dup
    //   63: invokespecial 171	java/lang/StringBuilder:<init>	()V
    //   66: ldc_w 501
    //   69: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   72: aload 4
    //   74: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   77: invokevirtual 181	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   80: invokevirtual 160	cn/com/xy/sms/sdk/smsmessage/BusinessSmsMessage:getValue	(Ljava/lang/String;)Ljava/lang/Object;
    //   83: checkcast 113	org/json/JSONArray
    //   86: astore_3
    //   87: aload_3
    //   88: astore_2
    //   89: aload_3
    //   90: ifnonnull +103 -> 193
    //   93: aload_3
    //   94: astore_2
    //   95: aload_1
    //   96: new 170	java/lang/StringBuilder
    //   99: dup
    //   100: invokespecial 171	java/lang/StringBuilder:<init>	()V
    //   103: ldc_w 367
    //   106: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   109: aload 4
    //   111: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   114: invokevirtual 181	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   117: invokevirtual 160	cn/com/xy/sms/sdk/smsmessage/BusinessSmsMessage:getValue	(Ljava/lang/String;)Ljava/lang/Object;
    //   120: ifnull +73 -> 193
    //   123: new 113	org/json/JSONArray
    //   126: dup
    //   127: aload_1
    //   128: new 170	java/lang/StringBuilder
    //   131: dup
    //   132: invokespecial 171	java/lang/StringBuilder:<init>	()V
    //   135: ldc_w 367
    //   138: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   141: aload 4
    //   143: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   146: invokevirtual 181	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   149: invokevirtual 160	cn/com/xy/sms/sdk/smsmessage/BusinessSmsMessage:getValue	(Ljava/lang/String;)Ljava/lang/Object;
    //   152: checkcast 162	java/lang/String
    //   155: invokespecial 504	org/json/JSONArray:<init>	(Ljava/lang/String;)V
    //   158: astore_2
    //   159: aload_2
    //   160: ifnull +33 -> 193
    //   163: aload_1
    //   164: getfield 283	cn/com/xy/sms/sdk/smsmessage/BusinessSmsMessage:bubbleJsonObj	Lorg/json/JSONObject;
    //   167: new 170	java/lang/StringBuilder
    //   170: dup
    //   171: invokespecial 171	java/lang/StringBuilder:<init>	()V
    //   174: ldc_w 501
    //   177: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   180: aload 4
    //   182: invokevirtual 177	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   185: invokevirtual 181	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   188: aload_2
    //   189: invokevirtual 289	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   192: pop
    //   193: aload_0
    //   194: aload_2
    //   195: invokespecial 197	cw:a	(Lorg/json/JSONArray;)V
    //   198: aload_0
    //   199: aload_1
    //   200: invokespecial 506	cw:d	(Lcn/com/xy/sms/sdk/smsmessage/BusinessSmsMessage;)V
    //   203: return
    //   204: astore_3
    //   205: aconst_null
    //   206: astore_2
    //   207: aload_3
    //   208: invokevirtual 223	java/lang/Exception:printStackTrace	()V
    //   211: goto -18 -> 193
    //   214: astore 4
    //   216: aload_3
    //   217: astore_2
    //   218: aload 4
    //   220: astore_3
    //   221: goto -14 -> 207
    //   224: astore_3
    //   225: goto -18 -> 207
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	228	0	this	cw
    //   0	228	1	paramBusinessSmsMessage	BusinessSmsMessage
    //   10	208	2	localObject	Object
    //   86	8	3	localJSONArray	JSONArray
    //   204	13	3	localException1	Exception
    //   220	1	3	localException2	Exception
    //   224	1	3	localException3	Exception
    //   56	125	4	str	String
    //   214	5	4	localException4	Exception
    // Exception table:
    //   from	to	target	type
    //   48	87	204	java/lang/Exception
    //   95	159	214	java/lang/Exception
    //   163	193	224	java/lang/Exception
  }
  
  private void c(boolean paramBoolean)
  {
    ImageView localImageView = z;
    if (paramBoolean) {}
    for (int i = 0;; i = 8)
    {
      ContentUtil.setViewVisibility(localImageView, i);
      return;
    }
  }
  
  private void d(BusinessSmsMessage paramBusinessSmsMessage)
  {
    int i = 0;
    while (i < E.size())
    {
      b(paramBusinessSmsMessage, i);
      i += 1;
    }
  }
  
  private void e(BusinessSmsMessage paramBusinessSmsMessage)
  {
    Object localObject = (String)paramBusinessSmsMessage.getValue("view_train_number_list");
    if (StringUtils.isNull((String)localObject)) {
      return;
    }
    localObject = ((String)localObject).split(";");
    int i = 0;
    JSONObject localJSONObject;
    for (;;)
    {
      if (i < localObject.length)
      {
        localJSONObject = new JSONObject();
        try
        {
          localJSONObject.put("name", localObject[i]);
          L.put(localJSONObject);
          i += 1;
        }
        catch (JSONException localJSONException2)
        {
          for (;;)
          {
            localJSONException2.printStackTrace();
          }
        }
      }
    }
    E = Arrays.asList((Object[])localObject);
    F = new JSONArray();
    i = 0;
    for (;;)
    {
      if (i < localObject.length)
      {
        localJSONObject = new JSONObject();
        try
        {
          localJSONObject.put("num", E.get(i));
          F.put(localJSONObject);
          i += 1;
        }
        catch (JSONException localJSONException1)
        {
          for (;;)
          {
            localJSONException1.printStackTrace();
          }
        }
      }
    }
    if ((E != null) && (!E.isEmpty())) {
      G = E.size();
    }
    if ((E != null) && (G >= 2) && (messageBody.indexOf("单程") == -1))
    {
      k.setVisibility(0);
      k.setOnClickListener(a);
      j.setOnClickListener(a);
      return;
    }
    k.setVisibility(8);
    k.setOnClickListener(null);
    j.setOnClickListener(null);
  }
  
  private void f()
  {
    int i1 = g.getTableDataSize("duoqu_table_data_horiz");
    JSONArray localJSONArray = g.getActionJsonArray();
    if (localJSONArray != null) {}
    for (int i = localJSONArray.length();; i = 0)
    {
      if ((i == 0) && (i1 == 0))
      {
        p.setVisibility(8);
        a(br.c.duoqu_bottom_rectangle);
        return;
      }
      p.setVisibility(0);
      a(br.c.duoqu_bottom_rectangle_0);
      return;
    }
  }
  
  public void a()
  {
    M = Math.round(f.getResources().getDimension(br.b.bubble_tableline_show));
    O = Math.round(f.getResources().getDimension(br.b.bubble_splitline_show));
    N = Math.round(f.getResources().getDimension(br.b.bubble_tableline_gone));
    k = ((ImageView)e.findViewById(br.d.duoqu_switch_train_num_place));
    d = ((TextView)e.findViewById(br.d.duoqu_train_num_title));
    j = ((TextView)e.findViewById(br.d.duoqu_train_num));
    l = ((TextView)e.findViewById(br.d.duoqu_train_depart_time_title));
    m = ((TextView)e.findViewById(br.d.duoqu_train_depart_time));
    n = ((TextView)e.findViewById(br.d.duoqu_train_arrive_time_title));
    o = ((TextView)e.findViewById(br.d.duoqu_train_arrive_time));
    s = ((ImageView)e.findViewById(br.d.duoqu_train_dotted_split_3));
    t = ((ImageView)e.findViewById(br.d.duoqu_train_dotted_split_4));
    p = s;
    B = ((int)f.getResources().getDimension(br.b.duoqu_budy_title_height));
    C = ((int)f.getResources().getDimension(br.b.duoqu_budy_title_double_height));
    u = ((TextView)e.findViewById(br.d.duoqu_train_depart_city));
    v = ((TextView)e.findViewById(br.d.duoqu_train_arrive_city));
    w = ((TextView)e.findViewById(br.d.duoqu_train_seat_type));
    x = ((TextView)e.findViewById(br.d.duoqu_train_seat_info));
    r = ((TextView)e.findViewById(br.d.duoqu_train_passenger));
    q = ((TextView)e.findViewById(br.d.duoqu_train_passenger_title));
    A = ((RelativeLayout)e.findViewById(br.d.train_title_rl));
    z = ((ImageView)e.findViewById(br.d.duoqu_base_select_train_place));
    y = ((ImageView)e.findViewById(br.d.duoqu_img_line));
  }
  
  public void a(int paramInt)
  {
    if (!StringUtils.isNull((String)g.getValue("v_by_bg")))
    {
      a((String)g.getValue("v_by_bg"), paramInt);
      return;
    }
    if (!StringUtils.isNull((String)g.getValue("v_by_bg_sec")))
    {
      a((String)g.getValue("v_by_bg_sec"), paramInt);
      return;
    }
    a("", paramInt);
  }
  
  public void a(View paramView, int paramInt)
  {
    if (paramView == null) {}
    ViewGroup.LayoutParams localLayoutParams;
    do
    {
      return;
      localLayoutParams = paramView.getLayoutParams();
    } while (localLayoutParams == null);
    height = paramInt;
    paramView.setLayoutParams(localLayoutParams);
  }
  
  public void a(BusinessSmsMessage paramBusinessSmsMessage, boolean paramBoolean)
  {
    g = paramBusinessSmsMessage;
    if (paramBusinessSmsMessage == null) {
      return;
    }
    int i1 = paramBusinessSmsMessage.getTableDataSize("duoqu_table_data_horiz");
    Object localObject = g.getActionJsonArray();
    if (localObject != null) {}
    for (int i = ((JSONArray)localObject).length();; i = 0)
    {
      if ((i == 0) && (i1 == 0))
      {
        p.setVisibility(8);
        a(br.c.duoqu_bottom_rectangle);
      }
      for (;;)
      {
        localObject = paramBusinessSmsMessage.getImgNameByKey("v_by_l_color");
        String str = paramBusinessSmsMessage.getImgNameByKey("v_by_r_color");
        ContentUtil.isTextSetColor(d, (String)localObject, br.a.duoqu_calls_l);
        ContentUtil.isTextSetColor(l, (String)localObject, br.a.duoqu_calls_l);
        ContentUtil.isTextSetColor(n, (String)localObject, br.a.duoqu_calls_l);
        ContentUtil.isTextSetColor(q, (String)localObject, br.a.duoqu_calls_l);
        ContentUtil.isTextSetColor(j, str, br.a.duoqu_calls_r);
        ContentUtil.isTextSetColor(m, str, br.a.duoqu_calls_r);
        ContentUtil.isTextSetColor(o, str, br.a.duoqu_calls_r);
        ContentUtil.isTextSetColor(r, (String)localObject, br.a.duoqu_calls_r);
        localObject = paramBusinessSmsMessage.getImgNameByKey("v_hd_bg");
        str = paramBusinessSmsMessage.getImgNameByKey("v_hd_bg_fir");
        TextView localTextView = j;
        if (StringUtils.isNull((String)localObject))
        {
          localObject = str;
          label214:
          ContentUtil.setTextColor(localTextView, (String)localObject);
          localObject = paramBusinessSmsMessage.getImgNameByKey("v_by_text_thr");
          if (StringUtils.isNull((String)localObject)) {}
        }
        try
        {
          ViewManger.setViewBg(Constant.getContext(), y, (String)localObject, 0, -1, true);
          K = ((String)paramBusinessSmsMessage.getValue("v_by_text_fir_l_color"));
          if (!StringUtils.isNull(K))
          {
            ContentUtil.textSetColor(u, K);
            localObject = (String)paramBusinessSmsMessage.getValue("v_by_text_fir_r_color");
            if (StringUtils.isNull((String)localObject)) {
              break label519;
            }
            ContentUtil.textSetColor(v, (String)localObject);
            localObject = (String)paramBusinessSmsMessage.getValue("v_by_text_sec_l_color");
            if (StringUtils.isNull((String)localObject)) {
              break label535;
            }
            ContentUtil.textSetColor(w, (String)localObject);
            localObject = (String)paramBusinessSmsMessage.getValue("v_by_text_sec_r_color");
            if (StringUtils.isNull((String)localObject)) {
              break label551;
            }
            ContentUtil.textSetColor(x, (String)localObject);
            ViewManger.setViewBg(f, A, paramBusinessSmsMessage.getImgNameByKey("v_by_bg_fir"), br.c.duoqu_budy_title_rectangle, 0);
            localObject = paramBusinessSmsMessage.getImgNameByKey("v_by_iconname");
            if (StringUtils.isNull((String)localObject)) {}
          }
        }
        catch (Exception localException2)
        {
          try
          {
            ViewManger.setViewBg(Constant.getContext(), z, (String)localObject, 0, -1, true);
            localObject = paramBusinessSmsMessage.getImgNameByKey("v_by_iconname2");
            if (StringUtils.isNull((String)localObject)) {}
          }
          catch (Exception localException2)
          {
            try
            {
              for (;;)
              {
                ViewManger.setViewBg(Constant.getContext(), k, (String)localObject, 0, -1, true);
                c(paramBusinessSmsMessage);
                e(paramBusinessSmsMessage);
                return;
                p.setVisibility(0);
                a(br.c.duoqu_bottom_rectangle_0);
                break;
                break label214;
                localException1 = localException1;
                localException1.printStackTrace();
                continue;
                u.setTextColor(ResourceCacheUtil.parseColor("#ffffff"));
                continue;
                label519:
                v.setTextColor(ResourceCacheUtil.parseColor("#ffffff"));
                continue;
                label535:
                w.setTextColor(ResourceCacheUtil.parseColor("#ffffff"));
                continue;
                label551:
                x.setTextColor(ResourceCacheUtil.parseColor("#ffffff"));
              }
              localException2 = localException2;
              localException2.printStackTrace();
            }
            catch (Exception localException3)
            {
              for (;;)
              {
                localException3.printStackTrace();
              }
            }
          }
        }
      }
    }
  }
}

/* Location:
 * Qualified Name:     cw
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */