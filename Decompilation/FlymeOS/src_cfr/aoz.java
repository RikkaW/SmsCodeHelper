/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.os.Parcel
 *  android.os.Parcelable
 *  android.os.Parcelable$Creator
 *  java.lang.Object
 *  java.util.ArrayList
 */
import android.os.Parcel;
import android.os.Parcelable;
import com.ted.android.contacts.netparser.model.HistoryDataModel;
import com.ted.android.contacts.netparser.model.HistoryItemModel;
import java.util.ArrayList;
import java.util.List;

public class aoz
implements Parcelable.Creator<HistoryDataModel> {
    public HistoryDataModel a(Parcel parcel) {
        if ((parcel = parcel.createTypedArrayList(HistoryItemModel.CREATOR)) != null) {
            return new HistoryDataModel((List<HistoryItemModel>)parcel);
        }
        return null;
    }

    public HistoryDataModel[] a(int n2) {
        return new HistoryDataModel[n2];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return this.a(parcel);
    }

    public /* synthetic */ Object[] newArray(int n2) {
        return this.a(n2);
    }
}

