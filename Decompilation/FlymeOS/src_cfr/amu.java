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
import com.meizu.update.iresponse.MzUpdateResponse;

public final class amu
implements Parcelable.Creator<MzUpdateResponse> {
    public MzUpdateResponse a(Parcel parcel) {
        return new MzUpdateResponse(parcel);
    }

    public MzUpdateResponse[] a(int n2) {
        return new MzUpdateResponse[n2];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return this.a(parcel);
    }

    public /* synthetic */ Object[] newArray(int n2) {
        return this.a(n2);
    }
}

