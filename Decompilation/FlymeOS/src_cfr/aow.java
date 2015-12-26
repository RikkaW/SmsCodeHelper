/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.os.Parcel
 *  android.os.Parcelable
 *  android.os.Parcelable$Creator
 *  java.lang.Object
 *  java.lang.String
 */
import android.os.Parcel;
import android.os.Parcelable;
import com.ted.android.contacts.netparser.model.CollectionItemModel;

public class aow
implements Parcelable.Creator<CollectionItemModel> {
    public CollectionItemModel a(Parcel object) {
        String string2 = object.readString();
        String string3 = object.readString();
        String string4 = object.readString();
        String string5 = object.readString();
        object = object.readString();
        if (string2 != null && string3 != null && string4 != null && string5 != null && object != null) {
            return new CollectionItemModel(string2, string3, string4, string5, (String)object);
        }
        return null;
    }

    public CollectionItemModel[] a(int n2) {
        return new CollectionItemModel[n2];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return this.a(parcel);
    }

    public /* synthetic */ Object[] newArray(int n2) {
        return this.a(n2);
    }
}

