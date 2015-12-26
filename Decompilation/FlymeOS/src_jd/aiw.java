import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.meizu.interfaces.OnlineResult;

public final class aiw
  implements Parcelable.Creator<OnlineResult>
{
  public OnlineResult a(Parcel paramParcel)
  {
    return new OnlineResult(paramParcel, null);
  }
  
  public OnlineResult[] a(int paramInt)
  {
    return new OnlineResult[paramInt];
  }
}

/* Location:
 * Qualified Name:     aiw
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */