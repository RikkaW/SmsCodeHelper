package cn.com.xy.sms.sdk.net.util;

import android.database.sqlite.SQLiteDatabase;
import bx;
import cn.com.xy.sms.sdk.constant.Constant;
import cn.com.xy.sms.sdk.db.DBManager;
import cn.com.xy.sms.sdk.db.entity.IccidInfo;
import cn.com.xy.sms.sdk.db.entity.IccidInfoManager;
import cn.com.xy.sms.sdk.db.entity.MatchCacheManager;
import cn.com.xy.sms.sdk.db.entity.a.e;
import cn.com.xy.sms.sdk.log.LogManager;
import cn.com.xy.sms.sdk.util.SceneconfigUtil;
import cn.com.xy.sms.sdk.util.StringUtils;
import java.util.Iterator;
import java.util.List;

public final class g
{
  private static void a(d paramd)
  {
    if (paramd == null) {}
    for (;;)
    {
      return;
      try
      {
        switch (paramd.a())
        {
        case 0: 
        case 1: 
        case 2: 
        case 3: 
        case 4: 
        case 7: 
        case 8: 
        case 9: 
        case 5: 
          e.e();
          localObject1 = IccidInfoManager.queryDeftIccidInfo(Constant.getContext());
          paramd = paramd.c();
          if (localObject1 == null) {
            break label155;
          }
          if ((paramd != null) && (paramd.length > 0))
          {
            localObject2 = areaCode;
            localObject1 = iccid;
            bx.a(paramd);
            return;
          }
          break;
        }
      }
      catch (Throwable paramd)
      {
        LogManager.e("XIAOYUAN", "EmergencyActionUtil doNoWait" + paramd.getMessage(), paramd);
        return;
      }
      bx.b(areaCode, iccid);
      return;
      label155:
      if ((paramd != null) && (paramd.length > 0))
      {
        bx.a(paramd);
        return;
      }
      bx.b("", "");
      return;
      SceneconfigUtil.updateData();
      return;
      paramd = paramd.c();
      if ((paramd != null) && (paramd.length > 0))
      {
        int j = paramd.length;
        int i = 0;
        while (i < j)
        {
          localObject1 = paramd[i];
          if (!StringUtils.isNull((String)localObject1)) {
            MatchCacheManager.deleteMatchCache((String)localObject1, System.currentTimeMillis());
          }
          i += 1;
        }
      }
    }
    Object localObject4 = null;
    Object localObject3 = null;
    Object localObject2 = localObject3;
    Object localObject1 = localObject4;
    try
    {
      String str = paramd.b();
      localObject2 = localObject3;
      localObject1 = localObject4;
      paramd = DBManager.getSQLiteDatabase();
      localObject2 = paramd;
      localObject1 = paramd;
      paramd.execSQL(str);
      DBManager.close(paramd);
      return;
    }
    catch (Throwable paramd)
    {
      localObject1 = localObject2;
      paramd.printStackTrace();
      return;
    }
    finally
    {
      DBManager.close((SQLiteDatabase)localObject1);
    }
  }
  
  public static void a(String paramString)
  {
    for (;;)
    {
      Object localObject2;
      try
      {
        paramString = CommandAnalyzer.a(paramString).iterator();
        if (!paramString.hasNext()) {
          return;
        }
        localObject2 = (d)paramString.next();
        if (localObject2 == null) {
          continue;
        }
        try
        {
          switch (((d)localObject2).a())
          {
          case 0: 
          case 1: 
          case 2: 
          case 3: 
          case 4: 
          case 7: 
          case 8: 
          case 9: 
          case 5: 
            e.e();
            Object localObject1 = IccidInfoManager.queryDeftIccidInfo(Constant.getContext());
            localObject2 = ((d)localObject2).c();
            if (localObject1 == null) {
              break label215;
            }
            if ((localObject2 == null) || (localObject2.length <= 0)) {
              break label201;
            }
            String str = areaCode;
            localObject1 = iccid;
            bx.a((String[])localObject2);
          }
        }
        catch (Throwable localThrowable)
        {
          LogManager.e("XIAOYUAN", "EmergencyActionUtil doNoWait" + localThrowable.getMessage(), localThrowable);
        }
        continue;
        bx.b(areaCode, iccid);
      }
      catch (Throwable paramString)
      {
        LogManager.e("XIAOYUAN", "EmergencyActionUtil doEmAction" + paramString.getMessage(), paramString);
        return;
      }
      label201:
      continue;
      label215:
      if ((localObject2 != null) && (localObject2.length > 0))
      {
        bx.a((String[])localObject2);
      }
      else
      {
        bx.b("", "");
        continue;
        SceneconfigUtil.updateData();
        continue;
        c((d)localObject2);
        continue;
        d((d)localObject2);
      }
    }
  }
  
  private static void b(d paramd)
  {
    e.e();
    Object localObject = IccidInfoManager.queryDeftIccidInfo(Constant.getContext());
    paramd = paramd.c();
    if (localObject != null)
    {
      if ((paramd != null) && (paramd.length > 0))
      {
        String str = areaCode;
        localObject = iccid;
        bx.a(paramd);
        return;
      }
      bx.b(areaCode, iccid);
      return;
    }
    if ((paramd != null) && (paramd.length > 0))
    {
      bx.a(paramd);
      return;
    }
    bx.b("", "");
  }
  
  private static void c(d paramd)
  {
    paramd = paramd.c();
    if ((paramd == null) || (paramd.length <= 0)) {}
    for (;;)
    {
      return;
      int j = paramd.length;
      int i = 0;
      while (i < j)
      {
        String str = paramd[i];
        if (!StringUtils.isNull(str)) {
          MatchCacheManager.deleteMatchCache(str, System.currentTimeMillis());
        }
        i += 1;
      }
    }
  }
  
  private static void d(d paramd)
  {
    Object localObject4 = null;
    Object localObject3 = null;
    Object localObject2 = localObject3;
    Object localObject1 = localObject4;
    try
    {
      String str = paramd.b();
      localObject2 = localObject3;
      localObject1 = localObject4;
      paramd = DBManager.getSQLiteDatabase();
      localObject2 = paramd;
      localObject1 = paramd;
      paramd.execSQL(str);
      DBManager.close(paramd);
      return;
    }
    catch (Throwable paramd)
    {
      localObject1 = localObject2;
      paramd.printStackTrace();
      return;
    }
    finally
    {
      DBManager.close((SQLiteDatabase)localObject1);
    }
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.net.util.g
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */