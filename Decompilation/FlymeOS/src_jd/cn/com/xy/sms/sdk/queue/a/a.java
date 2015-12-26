package cn.com.xy.sms.sdk.queue.a;

import cn.com.xy.sms.sdk.constant.Constant;
import cn.com.xy.sms.sdk.db.entity.SysParamEntityManager;
import cn.com.xy.sms.sdk.dex.DexUtil;
import cn.com.xy.sms.sdk.util.d;
import java.io.File;
import java.util.Iterator;
import java.util.List;

public final class a
{
  private static int a = 1;
  
  public static List<File> a(String paramString)
  {
    return d.e(Constant.getPath("duoqu_temp"), paramString + "_", ".zip");
  }
  
  public static void a(String paramString1, String paramString2)
  {
    Object localObject = a(paramString1);
    if ((localObject != null) && (!((List)localObject).isEmpty())) {
      localObject = ((List)localObject).iterator();
    }
    for (;;)
    {
      if ((localObject == null) || (!((Iterator)localObject).hasNext())) {
        return;
      }
      File localFile = (File)((Iterator)localObject).next();
      if ((paramString1 + "_" + paramString2 + "_").compareTo(localFile.getName()) >= 0)
      {
        localFile.delete();
        new StringBuilder("deleteFile=").append(localFile.getAbsolutePath());
      }
      else
      {
        ((Iterator)localObject).remove();
        new StringBuilder("不删除").append(localFile.getAbsolutePath());
      }
    }
  }
  
  public static boolean a(int paramInt)
  {
    try
    {
      int j = SysParamEntityManager.getIntParam(Constant.getContext(), "ONLINE_UPDATE_RES_PERIOD");
      int i = j;
      if (j <= 0) {
        i = 2;
      }
      long l1 = SysParamEntityManager.getLongParam("LastCheckResourseTime_" + paramInt, 0L, Constant.getContext());
      long l2 = System.currentTimeMillis();
      long l3 = DexUtil.getUpdateCycleByType(9, 86400000L * i);
      if (l2 > l3 + l1) {
        return true;
      }
    }
    catch (Throwable localThrowable) {}
    return false;
  }
  
  public static void b(int paramInt)
  {
    SysParamEntityManager.setParam("LastCheckResourseTime_" + paramInt, System.currentTimeMillis());
  }
  
  public static String c(int paramInt)
  {
    if (paramInt == 1) {
      return "5YD15P";
    }
    return "3T0CFQ";
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.queue.a.a
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */