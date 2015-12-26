/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.os.Parcel
 *  android.os.Parcelable
 *  android.os.Parcelable$Creator
 *  java.lang.Object
 */
import android.os.Parcel;
import android.os.Parcelable;
import com.meizu.statsapp.UsageStatsProxy;

public final class ajr
implements Parcelable.Creator<UsageStatsProxy.Event> {
    public UsageStatsProxy.Event a(Parcel parcel) {
        return new UsageStatsProxy.Event(parcel);
    }

    public UsageStatsProxy.Event[] a(int n2) {
        return new UsageStatsProxy.Event[n2];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return this.a(parcel);
    }

    public /* synthetic */ Object[] newArray(int n2) {
        return this.a(n2);
    }
}

