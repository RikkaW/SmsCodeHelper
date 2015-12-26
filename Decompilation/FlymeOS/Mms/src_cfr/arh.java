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
import flyme.support.v7.widget.MzRecyclerView;

public final class arh
implements Parcelable.Creator<MzRecyclerView.MZSavedState> {
    public MzRecyclerView.MZSavedState a(Parcel parcel) {
        return new MzRecyclerView.MZSavedState(parcel, null);
    }

    public MzRecyclerView.MZSavedState[] a(int n2) {
        return new MzRecyclerView.MZSavedState[n2];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return this.a(parcel);
    }

    public /* synthetic */ Object[] newArray(int n2) {
        return this.a(n2);
    }
}

