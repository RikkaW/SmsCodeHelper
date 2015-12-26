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
import com.ted.android.contacts.netparser.model.BannerDataModel;
import com.ted.android.contacts.netparser.model.BannerItemModel;
import java.util.ArrayList;
import java.util.List;

public class aol
implements Parcelable.Creator<BannerDataModel> {
    public BannerDataModel a(Parcel parcel) {
        String string2 = parcel.readString();
        String string3 = parcel.readString();
        parcel = parcel.createTypedArrayList(BannerItemModel.CREATOR);
        if (string2 != null && string3 != null && parcel != null) {
            return new BannerDataModel(string2, string3, (List<BannerItemModel>)parcel);
        }
        return null;
    }

    public BannerDataModel[] a(int n2) {
        return new BannerDataModel[n2];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return this.a(parcel);
    }

    public /* synthetic */ Object[] newArray(int n2) {
        return this.a(n2);
    }
}

