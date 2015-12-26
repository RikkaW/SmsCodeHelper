import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.util.Log;
import com.android.mms.fragmentstyle.FavoriteActivity.c;
import com.android.mms.ui.ComposeMessageActivity;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class jy
{
  public static List<FavoriteActivity.c> a(HashMap<Long, FavoriteActivity.c> paramHashMap)
  {
    paramHashMap = paramHashMap.values().iterator();
    ArrayList localArrayList = new ArrayList();
    while (paramHashMap.hasNext()) {
      localArrayList.add(paramHashMap.next());
    }
    Collections.sort(localArrayList);
    return localArrayList;
  }
  
  public static void a(String paramString, Context paramContext, Activity paramActivity)
  {
    Intent localIntent = ComposeMessageActivity.a(paramContext, 0L);
    localIntent.putExtra("exit_on_sent", true);
    localIntent.putExtra("forwarded_message", true);
    localIntent.putExtra("sms_body", paramString);
    localIntent.setClassName(paramContext, "com.android.mms.ui.ForwardMessageActivity");
    paramActivity.startActivityForResult(localIntent, 113);
  }
  
  public static void a(vv paramvv, Context paramContext, Activity paramActivity)
  {
    long l = System.currentTimeMillis();
    Intent localIntent = ComposeMessageActivity.a(paramContext, 0L);
    localIntent.putExtra("exit_on_sent", true);
    localIntent.putExtra("forwarded_message", true);
    if (d.equals("sms")) {
      localIntent.putExtra("sms_body", o);
    }
    for (;;)
    {
      localIntent.setClassName(paramContext, "com.android.mms.ui.ForwardMessageActivity");
      paramActivity.startActivityForResult(localIntent, 113);
      wd.a("forwardmsg", "forwardMessage all", l);
      return;
      if (!y)
      {
        Log.i("NewMessageUtils", "forwardMms file_no_exsit");
        return;
      }
      if (B == null)
      {
        Log.i("NewMessageUtils", "forwardMms waiting_for_make_slideshow");
        return;
      }
      String str2 = paramContext.getResources().getString(2131492965);
      String str1 = str2;
      if (A != null) {
        str1 = str2 + A;
      }
      localIntent.putExtra("copy_pdu_uri", paramvv.K());
      localIntent.putExtra("mmsprotocl", paramvv.r());
      localIntent.putExtra("subject", str1);
    }
  }
  
  public static void a(Long[] paramArrayOfLong, Context paramContext)
  {
    new Thread(new jz(paramArrayOfLong, paramContext), "NewMessageUtils.updateSendFailedNotification").start();
  }
}

/* Location:
 * Qualified Name:     jy
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */