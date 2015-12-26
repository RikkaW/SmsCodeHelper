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
import com.ted.android.contacts.netparser.model.CollectionDataModel;
import com.ted.android.contacts.netparser.model.CollectionItemModel;
import java.util.ArrayList;
import java.util.List;

public class aov
implements Parcelable.Creator<CollectionDataModel> {
    public CollectionDataModel a(Parcel parcel) {
        if ((parcel = parcel.createTypedArrayList(CollectionItemModel.CREATOR)) != null) {
            return new CollectionDataModel((List<CollectionItemModel>)parcel);
        }
        return null;
    }

    public CollectionDataModel[] a(int n2) {
        return new CollectionDataModel[n2];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return this.a(parcel);
    }

    public /* synthetic */ Object[] newArray(int n2) {
        return this.a(n2);
    }
}

