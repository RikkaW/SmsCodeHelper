import android.content.ContentValues;
import cn.com.xy.sms.sdk.Iservice.XyCallBack;
import cn.com.xy.sms.sdk.db.DBManager;
import cn.com.xy.sms.sdk.db.entity.SysParamEntityManager;
import cn.com.xy.sms.sdk.log.LogManager;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONObject;

final class bz
  implements XyCallBack
{
  bz(ArrayList paramArrayList, String paramString1, String paramString2, boolean paramBoolean) {}
  
  public final void execute(Object... arg1)
  {
    if (??? != null) {}
    for (;;)
    {
      try
      {
        if ((???.length > 0) && (!"-1".equals(???[0].toString()))) {
          break;
        }
        SysParamEntityManager.setParam("LastPublicUpdate", String.valueOf(System.currentTimeMillis()));
      }
      catch (Throwable ???)
      {
        if (!LogManager.debug) {
          continue;
        }
        ???.printStackTrace();
        return;
      }
      synchronized (bx.a)
      {
        bx.b = false;
        return;
      }
    }
    Object localObject2 = a;
    ??? = new ContentValues();
    ???.put("lastloadtime", Integer.valueOf(0));
    localObject2 = ((ArrayList)localObject2).iterator();
    for (;;)
    {
      if (!((Iterator)localObject2).hasNext())
      {
        bx.a(b, c, d);
        return;
      }
      DBManager.update("tb_public_num_info", ???, "num = ?", new String[] { new JSONObject((String)((Iterator)localObject2).next()).getString("num") });
    }
  }
}

/* Location:
 * Qualified Name:     bz
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */