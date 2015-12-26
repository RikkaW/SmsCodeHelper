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

public final class ars
implements Parcelable.Creator<StaggeredGridLayoutManager.LazySpanLookup.FullSpanItem> {
    public StaggeredGridLayoutManager.LazySpanLookup.FullSpanItem a(Parcel parcel) {
        return new StaggeredGridLayoutManager.LazySpanLookup.FullSpanItem(parcel);
    }

    public StaggeredGridLayoutManager.LazySpanLookup.FullSpanItem[] a(int n2) {
        return new StaggeredGridLayoutManager.LazySpanLookup.FullSpanItem[n2];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return this.a(parcel);
    }

    public /* synthetic */ Object[] newArray(int n2) {
        return this.a(n2);
    }
}

