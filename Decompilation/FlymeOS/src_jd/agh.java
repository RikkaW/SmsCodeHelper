import android.telephony.CellLocation;
import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;
import android.telephony.SignalStrength;

final class agh
  extends PhoneStateListener
{
  private agh(agf paramagf) {}
  
  public final void onCellLocationChanged(CellLocation paramCellLocation)
  {
    try
    {
      agf.b(a, System.currentTimeMillis());
      agf.a(a, paramCellLocation);
      super.onCellLocationChanged(paramCellLocation);
      return;
    }
    catch (Exception paramCellLocation) {}
  }
  
  public final void onServiceStateChanged(ServiceState paramServiceState)
  {
    try
    {
      if (paramServiceState.getState() == 0)
      {
        agf.a(a, true);
        String[] arrayOfString = agf.a(agf.f(a));
        agf.a(a, Integer.parseInt(arrayOfString[0]));
        agf.b(a, Integer.parseInt(arrayOfString[1]));
      }
      for (;;)
      {
        super.onServiceStateChanged(paramServiceState);
        return;
        agf.a(a, false);
      }
      return;
    }
    catch (Exception paramServiceState) {}
  }
  
  public final void onSignalStrengthsChanged(SignalStrength paramSignalStrength)
  {
    try
    {
      if (agf.g(a)) {
        agf.c(a, paramSignalStrength.getCdmaDbm());
      }
      for (;;)
      {
        super.onSignalStrengthsChanged(paramSignalStrength);
        return;
        agf.c(a, paramSignalStrength.getGsmSignalStrength());
        if (agf.h(a) == 99) {
          agf.c(a, -1);
        } else {
          agf.c(a, agf.h(a) * 2 - 113);
        }
      }
      return;
    }
    catch (Exception paramSignalStrength) {}
  }
}

/* Location:
 * Qualified Name:     agh
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */