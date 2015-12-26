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
import com.meizu.interfaces.OnlineResult;

public final class aiw
implements Parcelable.Creator<OnlineResult> {
    public OnlineResult a(Parcel parcel) {
        return new OnlineResult(parcel, null);
    }

    public OnlineResult[] a(int n2) {
        return new OnlineResult[n2];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return this.a(parcel);
    }

    public /* synthetic */ Object[] newArray(int n2) {
        return this.a(n2);
    }
}

