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
import com.meizu.update.UpdateInfo;

public final class akh
implements Parcelable.Creator<UpdateInfo> {
    public UpdateInfo a(Parcel parcel) {
        return new UpdateInfo(parcel, null);
    }

    public UpdateInfo[] a(int n2) {
        return new UpdateInfo[n2];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return this.a(parcel);
    }

    public /* synthetic */ Object[] newArray(int n2) {
        return this.a(n2);
    }
}

