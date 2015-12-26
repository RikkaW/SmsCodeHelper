/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.os.Parcel
 *  android.os.Parcelable
 *  android.os.Parcelable$Creator
 *  android.os.SystemClock
 *  java.lang.Object
 *  java.lang.String
 *  java.util.Locale
 */
package android.location;

import android.location.Country$1;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import java.util.Locale;

public class Country
implements Parcelable {
    public static final int COUNTRY_SOURCE_LOCALE = 3;
    public static final int COUNTRY_SOURCE_LOCATION = 1;
    public static final int COUNTRY_SOURCE_NETWORK = 0;
    public static final int COUNTRY_SOURCE_SIM = 2;
    public static final Parcelable.Creator<Country> CREATOR = new Country$1();
    private final String mCountryIso;
    private int mHashCode;
    private final int mSource;
    private final long mTimestamp;

    public Country(Country country) {
        this.mCountryIso = country.mCountryIso;
        this.mSource = country.mSource;
        this.mTimestamp = country.mTimestamp;
    }

    public Country(String string2, int n2) {
        if (string2 == null || n2 < 0 || n2 > 3) {
            throw new IllegalArgumentException();
        }
        this.mCountryIso = string2.toUpperCase(Locale.US);
        this.mSource = n2;
        this.mTimestamp = SystemClock.elapsedRealtime();
    }

    private Country(String string2, int n2, long l2) {
        if (string2 == null || n2 < 0 || n2 > 3) {
            throw new IllegalArgumentException();
        }
        this.mCountryIso = string2.toUpperCase(Locale.US);
        this.mSource = n2;
        this.mTimestamp = l2;
    }

    /* synthetic */ Country(String string2, int n2, long l2, Country$1 country$1) {
        this(string2, n2, l2);
    }

    public int describeContents() {
        return 0;
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof Country)) return false;
        if (!this.mCountryIso.equals((Object)(object = (Country)object).getCountryIso())) return false;
        if (this.mSource == object.getSource()) return true;
        return false;
    }

    public boolean equalsIgnoreSource(Country country) {
        if (country != null && this.mCountryIso.equals((Object)country.getCountryIso())) {
            return true;
        }
        return false;
    }

    public final String getCountryIso() {
        return this.mCountryIso;
    }

    public final int getSource() {
        return this.mSource;
    }

    public final long getTimestamp() {
        return this.mTimestamp;
    }

    public int hashCode() {
        if (this.mHashCode == 0) {
            this.mHashCode = (this.mCountryIso.hashCode() + 221) * 13 + this.mSource;
        }
        return this.mHashCode;
    }

    public String toString() {
        return "Country {ISO=" + this.mCountryIso + ", source=" + this.mSource + ", time=" + this.mTimestamp + "}";
    }

    public void writeToParcel(Parcel parcel, int n2) {
        parcel.writeString(this.mCountryIso);
        parcel.writeInt(this.mSource);
        parcel.writeLong(this.mTimestamp);
    }
}

