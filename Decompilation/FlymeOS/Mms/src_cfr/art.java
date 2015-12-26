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
import flyme.support.v7.widget.StaggeredGridLayoutManager;

public final class art
implements Parcelable.Creator<StaggeredGridLayoutManager.SavedState> {
    public StaggeredGridLayoutManager.SavedState a(Parcel parcel) {
        return new StaggeredGridLayoutManager.SavedState(parcel);
    }

    public StaggeredGridLayoutManager.SavedState[] a(int n2) {
        return new StaggeredGridLayoutManager.SavedState[n2];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return this.a(parcel);
    }

    public /* synthetic */ Object[] newArray(int n2) {
        return this.a(n2);
    }
}

