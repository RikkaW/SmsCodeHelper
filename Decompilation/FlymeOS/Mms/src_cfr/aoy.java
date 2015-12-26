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
import com.ted.android.contacts.netparser.model.DottingEventItemModel;

public class aoy
implements Parcelable.Creator<DottingEventItemModel> {
    public DottingEventItemModel a(Parcel parcel) {
        return new DottingEventItemModel(parcel);
    }

    public DottingEventItemModel[] a(int n2) {
        return new DottingEventItemModel[n2];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return this.a(parcel);
    }

    public /* synthetic */ Object[] newArray(int n2) {
        return this.a(n2);
    }
}

