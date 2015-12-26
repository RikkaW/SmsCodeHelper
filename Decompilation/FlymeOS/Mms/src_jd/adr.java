import android.app.Activity;
import android.util.Log;
import cn.com.xy.sms.sdk.util.StringUtils;
import cn.com.xy.sms.util.SdkCallBack;
import com.android.mms.view.MessageListItemSms;
import com.android.mms.view.MessageListItemSms.a;
import com.android.mms.view.MmsFoldableTextView;
import org.json.JSONArray;

public class adr
  implements SdkCallBack
{
  public adr(MessageListItemSms paramMessageListItemSms, vv paramvv) {}
  
  public void execute(Object... paramVarArgs)
  {
    if ((paramVarArgs == null) || (paramVarArgs.length == 0))
    {
      MessageListItemSms.a(b);
      return;
    }
    Log.d("duoqu_xiaoyuan", "getSimpleBubbleData obj[0]: " + paramVarArgs[0] + " obj[1]:" + paramVarArgs[1] + " obj[2]:" + paramVarArgs[2]);
    int i = ((Integer)paramVarArgs[0]).intValue();
    if (paramVarArgs.length > 2)
    {
      String str1 = (String)paramVarArgs[2];
      String str2 = (String)b.z.getTag();
      if ((StringUtils.isNull(str2)) || (StringUtils.isNull(str1)) || (!str2.equals(str1)))
      {
        MessageListItemSms.a(b);
        return;
      }
    }
    switch (i)
    {
    case -3: 
    default: 
      return;
    case -4: 
      b.z();
      return;
    case -2: 
    case -1: 
      b.z();
      return;
    case 0: 
      MessageListItemSms.a(b, new MessageListItemSms.a(b, Long.valueOf(a.e), (JSONArray)paramVarArgs[1]));
      if ((MessageListItemSms.b(b) != null) && (bb).g > 0))
      {
        b.a(MessageListItemSms.b(b));
        return;
      }
      b.z();
      return;
    }
    ((Activity)b.U).runOnUiThread(new ads(this, paramVarArgs));
  }
}

/* Location:
 * Qualified Name:     adr
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */