import android.os.Parcel;
import android.os.Parcelable.Creator;
import cn.com.xy.sms.sdk.ui.notification.MessageItem;
import java.util.HashMap;

public final class ck
  implements Parcelable.Creator
{
  public MessageItem a(Parcel paramParcel)
  {
    MessageItem localMessageItem = new MessageItem();
    a = paramParcel.readInt();
    b = paramParcel.readString();
    c = paramParcel.readString();
    d = paramParcel.readHashMap(HashMap.class.getClassLoader());
    e = paramParcel.readHashMap(HashMap.class.getClassLoader());
    return localMessageItem;
  }
  
  public MessageItem[] a(int paramInt)
  {
    return new MessageItem[paramInt];
  }
}

/* Location:
 * Qualified Name:     ck
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */