/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.os.Parcel
 *  android.os.Parcelable
 *  android.os.Parcelable$Creator
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 */
import android.os.Parcel;
import android.os.Parcelable;
import com.ted.android.contacts.netparser.model.NearDataModel;
import com.ted.android.contacts.netparser.model.NearItemModel;
import com.ted.android.contacts.netparser.model.NearMoreModel;
import java.util.ArrayList;
import java.util.List;

public class apf
implements Parcelable.Creator<NearDataModel> {
    public NearDataModel a(Parcel parcel) {
        String string2 = parcel.readString();
        String string3 = parcel.readString();
        ArrayList arrayList = parcel.createTypedArrayList(NearItemModel.CREATOR);
        parcel = parcel.createTypedArrayList(NearMoreModel.CREATOR);
        if (string2 != null && string3 != null && arrayList != null && parcel != null) {
            return new NearDataModel(string2, string3, (List<NearItemModel>)arrayList, (List<NearMoreModel>)parcel);
        }
        return null;
    }

    public NearDataModel[] a(int n2) {
        return new NearDataModel[n2];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return this.a(parcel);
    }

    public /* synthetic */ Object[] newArray(int n2) {
        return this.a(n2);
    }
}

