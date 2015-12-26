package cn.com.xy.sms.sdk.util;

import android.content.ContentValues;
import cn.com.xy.sms.sdk.Iservice.XyCallBack;
import cn.com.xy.sms.sdk.db.DBManager;
import cn.com.xy.sms.sdk.db.entity.SysParamEntityManager;
import cn.com.xy.sms.sdk.db.entity.c;
import cn.com.xy.sms.sdk.db.entity.d;
import cn.com.xy.sms.sdk.db.entity.e;
import cn.com.xy.sms.sdk.db.entity.k;
import cn.com.xy.sms.sdk.net.NetUtil;
import cn.com.xy.sms.sdk.net.util.h;
import cn.com.xy.sms.sdk.queue.b;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

final class j
  implements XyCallBack
{
  j(boolean paramBoolean) {}
  
  public final void execute(Object... paramVarArgs)
  {
    Object localObject1;
    if (paramVarArgs != null)
    {
      for (;;)
      {
        try
        {
          if ((!paramVarArgs[0].toString().equals("0")) || (paramVarArgs.length != 2)) {
            continue;
          }
          paramVarArgs = h.a(paramVarArgs[1].toString());
          if (paramVarArgs != null) {
            continue;
          }
          SysParamEntityManager.setParam("JARS_UPDATE_TIME", String.valueOf(System.currentTimeMillis()));
          k.a();
          if ((a) && (NetUtil.checkAccessNetWork(2)))
          {
            paramVarArgs = e.a();
            if (!paramVarArgs.isEmpty())
            {
              paramVarArgs = paramVarArgs.iterator();
              if (paramVarArgs.hasNext()) {
                continue;
              }
            }
          }
          return;
        }
        catch (Throwable paramVarArgs)
        {
          long l1;
          int i;
          paramVarArgs.printStackTrace();
          if ((!a) || (!NetUtil.checkAccessNetWork(2))) {
            continue;
          }
          paramVarArgs = e.a();
          if (paramVarArgs.isEmpty()) {
            continue;
          }
          paramVarArgs = paramVarArgs.iterator();
          if (!paramVarArgs.hasNext()) {
            continue;
          }
          localObject1 = (d)paramVarArgs.next();
          if ((StringUtils.isNull(d)) || (f != 0)) {
            continue;
          }
          i.a((d)localObject1);
          continue;
        }
        finally
        {
          if (!a) {
            break label628;
          }
        }
        localObject1 = (d)paramVarArgs.next();
        if ((!StringUtils.isNull(d)) && (f == 0)) {
          i.a((d)localObject1);
        }
      }
      l1 = System.currentTimeMillis();
      localObject1 = paramVarArgs.get("updataJars");
      if (localObject1 != null)
      {
        localObject1 = (JSONArray)localObject1;
        i = 0;
      }
    }
    for (;;)
    {
      if (i >= ((JSONArray)localObject1).length())
      {
        k.a();
        if (paramVarArgs.containsKey("emergencyArray")) {
          c.a((JSONArray)paramVarArgs.get("emergencyArray"));
        }
        b.a();
        if ((!a) || (!NetUtil.checkAccessNetWork(2))) {
          break;
        }
        paramVarArgs = e.a();
        if (paramVarArgs.isEmpty()) {
          break;
        }
        paramVarArgs = paramVarArgs.iterator();
        while (paramVarArgs.hasNext())
        {
          localObject1 = (d)paramVarArgs.next();
          if ((!StringUtils.isNull(d)) && (f == 0)) {
            i.a((d)localObject1);
          }
        }
        break;
      }
      Object localObject2 = ((JSONArray)localObject1).getJSONObject(i);
      String str1 = ((JSONObject)localObject2).getString("name");
      String str2 = ((JSONObject)localObject2).getString("version");
      String str3 = ((JSONObject)localObject2).getString("url");
      long l2 = System.currentTimeMillis();
      long l3 = ((JSONObject)localObject2).getInt("delayStart");
      int j = ((JSONObject)localObject2).getInt("delayEnd");
      long l4 = j;
      try
      {
        localObject2 = new ContentValues();
        ((ContentValues)localObject2).put("version", str2);
        ((ContentValues)localObject2).put("url", str3);
        ((ContentValues)localObject2).put("status", Integer.valueOf(0));
        ((ContentValues)localObject2).put("update_time", l2);
        ((ContentValues)localObject2).put("delaystart", l3 + l1);
        ((ContentValues)localObject2).put("delayend", l4 + l1);
        DBManager.update("tb_jar_list", (ContentValues)localObject2, "name = ? ", new String[] { str1 });
        i += 1;
      }
      catch (Throwable localThrowable)
      {
        for (;;)
        {
          localThrowable.printStackTrace();
        }
      }
    }
    if (NetUtil.checkAccessNetWork(2))
    {
      localObject1 = e.a();
      if (!((List)localObject1).isEmpty()) {
        localObject1 = ((List)localObject1).iterator();
      }
    }
    for (;;)
    {
      if (!((Iterator)localObject1).hasNext()) {
        label628:
        throw paramVarArgs;
      }
      d locald = (d)((Iterator)localObject1).next();
      if ((!StringUtils.isNull(d)) && (f == 0)) {
        i.a(locald);
      }
    }
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.util.j
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */