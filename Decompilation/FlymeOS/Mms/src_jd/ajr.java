import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.meizu.statsapp.UsageStatsProxy.Event;

public final class ajr
  implements Parcelable.Creator<UsageStatsProxy.Event>
{
  public UsageStatsProxy.Event a(Parcel paramParcel)
  {
    return new UsageStatsProxy.Event(paramParcel);
  }
  
  public UsageStatsProxy.Event[] a(int paramInt)
  {
    return new UsageStatsProxy.Event[paramInt];
  }
}

/* Location:
 * Qualified Name:     ajr
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */