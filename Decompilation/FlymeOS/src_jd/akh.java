import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.meizu.update.UpdateInfo;

public final class akh
  implements Parcelable.Creator<UpdateInfo>
{
  public UpdateInfo a(Parcel paramParcel)
  {
    return new UpdateInfo(paramParcel, null);
  }
  
  public UpdateInfo[] a(int paramInt)
  {
    return new UpdateInfo[paramInt];
  }
}

/* Location:
 * Qualified Name:     akh
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */