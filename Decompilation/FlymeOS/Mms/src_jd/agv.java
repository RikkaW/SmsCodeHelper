import android.telephony.CellLocation;
import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;
import android.telephony.SignalStrength;
import java.util.List;

class agv
  extends PhoneStateListener
{
  agv(agu paramagu) {}
  
  public void onCellLocationChanged(CellLocation paramCellLocation)
  {
    if (paramCellLocation == null) {}
    for (;;)
    {
      return;
      try
      {
        if (!agu.a(a))
        {
          agu.a(a, paramCellLocation);
          agu.a(a, ahz.b());
          agu.b(a, ahz.b());
          return;
        }
      }
      catch (Throwable paramCellLocation)
      {
        paramCellLocation.printStackTrace();
      }
    }
  }
  
  public void onServiceStateChanged(ServiceState paramServiceState)
  {
    try
    {
      switch (paramServiceState.getState())
      {
      case 1: 
        agu.c(a).clear();
        agu.b(a, -113);
        return;
      }
    }
    catch (Throwable paramServiceState)
    {
      paramServiceState.printStackTrace();
      return;
    }
  }
  
  public void onSignalStrengthChanged(int paramInt)
  {
    int i = -113;
    for (;;)
    {
      try
      {
        switch (agu.b(a))
        {
        case 1: 
          agu.a(a, paramInt);
          return;
        }
      }
      catch (Throwable localThrowable)
      {
        localThrowable.printStackTrace();
        return;
      }
      paramInt = ahz.a(paramInt);
      continue;
      paramInt = ahz.a(paramInt);
      continue;
      paramInt = i;
    }
  }
  
  public void onSignalStrengthsChanged(SignalStrength paramSignalStrength)
  {
    int i = -113;
    for (;;)
    {
      try
      {
        switch (agu.b(a))
        {
        case 1: 
          agu.a(a, i);
          return;
        }
      }
      catch (Throwable paramSignalStrength)
      {
        paramSignalStrength.printStackTrace();
        return;
      }
      i = ahz.a(paramSignalStrength.getGsmSignalStrength());
      continue;
      i = paramSignalStrength.getCdmaDbm();
    }
  }
}

/* Location:
 * Qualified Name:     agv
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */