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
import com.ted.android.contacts.netparser.model.HistoryItemModel;

public class apa
implements Parcelable.Creator<HistoryItemModel> {
    public HistoryItemModel a(Parcel parcel) {
        return new HistoryItemModel(parcel);
    }

    public HistoryItemModel[] a(int n2) {
        return new HistoryItemModel[n2];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return this.a(parcel);
    }

    public /* synthetic */ Object[] newArray(int n2) {
        return this.a(n2);
    }
}

