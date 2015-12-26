package android.location;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.os.SystemClock;
import java.util.Locale;

public class Country
  implements Parcelable
{
  public static final int COUNTRY_SOURCE_LOCALE = 3;
  public static final int COUNTRY_SOURCE_LOCATION = 1;
  public static final int COUNTRY_SOURCE_NETWORK = 0;
  public static final int COUNTRY_SOURCE_SIM = 2;
  public static final Parcelable.Creator<Country> CREATOR = new Country.1();
  private final String mCountryIso;
  private int mHashCode;
  private final int mSource;
  private final long mTimestamp;
  
  public Country(Country paramCountry)
  {
    mCountryIso = mCountryIso;
    mSource = mSource;
    mTimestamp = mTimestamp;
  }
  
  public Country(String paramString, int paramInt)
  {
    if ((paramString == null) || (paramInt < 0) || (paramInt > 3)) {
      throw new IllegalArgumentException();
    }
    mCountryIso = paramString.toUpperCase(Locale.US);
    mSource = paramInt;
    mTimestamp = SystemClock.elapsedRealtime();
  }
  
  private Country(String paramString, int paramInt, long paramLong)
  {
    if ((paramString == null) || (paramInt < 0) || (paramInt > 3)) {
      throw new IllegalArgumentException();
    }
    mCountryIso = paramString.toUpperCase(Locale.US);
    mSource = paramInt;
    mTimestamp = paramLong;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if (paramObject == this) {}
    do
    {
      return true;
      if (!(paramObject instanceof Country)) {
        break;
      }
      paramObject = (Country)paramObject;
    } while ((mCountryIso.equals(((Country)paramObject).getCountryIso())) && (mSource == ((Country)paramObject).getSource()));
    return false;
    return false;
  }
  
  public boolean equalsIgnoreSource(Country paramCountry)
  {
    return (paramCountry != null) && (mCountryIso.equals(paramCountry.getCountryIso()));
  }
  
  public final String getCountryIso()
  {
    return mCountryIso;
  }
  
  public final int getSource()
  {
    return mSource;
  }
  
  public final long getTimestamp()
  {
    return mTimestamp;
  }
  
  public int hashCode()
  {
    if (mHashCode == 0) {
      mHashCode = ((mCountryIso.hashCode() + 221) * 13 + mSource);
    }
    return mHashCode;
  }
  
  public String toString()
  {
    return "Country {ISO=" + mCountryIso + ", source=" + mSource + ", time=" + mTimestamp + "}";
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeString(mCountryIso);
    paramParcel.writeInt(mSource);
    paramParcel.writeLong(mTimestamp);
  }
}

/* Location:
 * Qualified Name:     android.location.Country
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */