import android.telephony.CellLocation;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;

public final class agy
{
  int a = Integer.MAX_VALUE;
  int b = Integer.MAX_VALUE;
  int c = Integer.MAX_VALUE;
  int d = Integer.MAX_VALUE;
  int e = Integer.MAX_VALUE;
  
  agy(CellLocation paramCellLocation)
  {
    if (paramCellLocation != null)
    {
      if (!(paramCellLocation instanceof GsmCellLocation)) {
        break label67;
      }
      paramCellLocation = (GsmCellLocation)paramCellLocation;
      e = paramCellLocation.getCid();
      d = paramCellLocation.getLac();
    }
    label67:
    while (!(paramCellLocation instanceof CdmaCellLocation)) {
      return;
    }
    paramCellLocation = (CdmaCellLocation)paramCellLocation;
    c = paramCellLocation.getBaseStationId();
    b = paramCellLocation.getNetworkId();
    a = paramCellLocation.getSystemId();
  }
}

/* Location:
 * Qualified Name:     agy
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */