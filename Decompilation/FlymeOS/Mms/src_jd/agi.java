import android.location.GpsStatus.NmeaListener;

final class agi
  implements GpsStatus.NmeaListener
{
  private agi(agf paramagf) {}
  
  public final void onNmeaReceived(long paramLong, String paramString)
  {
    try
    {
      agf.c(a, paramLong);
      agf.a(a, paramString);
      return;
    }
    catch (Exception paramString) {}
  }
}

/* Location:
 * Qualified Name:     agi
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */