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
import com.ted.android.contacts.netparser.model.NearMoreModel;
import com.ted.android.contacts.netparser.model.ServiceItemModel;
import java.util.ArrayList;
import java.util.List;

public class aph
implements Parcelable.Creator<NearMoreModel> {
    public NearMoreModel a(Parcel parcel) {
        String string2 = parcel.readString();
        int n2 = parcel.readInt();
        parcel = parcel.createTypedArrayList(ServiceItemModel.CREATOR);
        if (string2 != null && parcel != null) {
            return new NearMoreModel(string2, n2, (List<ServiceItemModel>)parcel);
        }
        return null;
    }

    public NearMoreModel[] a(int n2) {
        return new NearMoreModel[n2];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return this.a(parcel);
    }

    public /* synthetic */ Object[] newArray(int n2) {
        return this.a(n2);
    }
}

