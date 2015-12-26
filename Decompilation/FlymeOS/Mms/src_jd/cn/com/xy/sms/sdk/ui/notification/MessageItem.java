package cn.com.xy.sms.sdk.ui.notification;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import ck;
import java.util.HashMap;
import java.util.Map;

public class MessageItem
  implements Parcelable
{
  public static final Parcelable.Creator<MessageItem> CREATOR = new ck();
  public int a;
  public String b;
  public String c;
  public Map<String, String> d = new HashMap();
  public Map<String, Object> e = new HashMap();
  
  public int describeContents()
  {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeInt(a);
    paramParcel.writeString(b);
    paramParcel.writeString(c);
    paramParcel.writeMap(d);
    paramParcel.writeMap(e);
  }
}

/* Location:
 * Qualified Name:     cn.com.xy.sms.sdk.ui.notification.MessageItem
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */