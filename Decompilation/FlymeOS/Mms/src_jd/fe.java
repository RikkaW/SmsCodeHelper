import android.annotation.SuppressLint;
import cn.com.xy.sms.util.ParseBubbleManager;
import cn.com.xy.sms.util.SdkCallBack;
import java.util.HashMap;

@SuppressLint({"NewApi"})
public class fe
{
  public static void a(String paramString1, String paramString2, String paramString3, String paramString4, long paramLong, byte paramByte, HashMap<String, Object> paramHashMap, SdkCallBack paramSdkCallBack, boolean paramBoolean)
  {
    ParseBubbleManager.queryDataByMsgItem(paramString1, paramString2, paramString4, paramString3, 1, paramLong, paramSdkCallBack, paramBoolean);
  }
}

/* Location:
 * Qualified Name:     fe
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */