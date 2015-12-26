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
import flyme.support.v7.widget.RecyclerView;

public final class arp
implements Parcelable.Creator<RecyclerView.SavedState> {
    public RecyclerView.SavedState a(Parcel parcel) {
        return new RecyclerView.SavedState(parcel);
    }

    public RecyclerView.SavedState[] a(int n2) {
        return new RecyclerView.SavedState[n2];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return this.a(parcel);
    }

    public /* synthetic */ Object[] newArray(int n2) {
        return this.a(n2);
    }
}

