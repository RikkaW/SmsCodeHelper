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
import com.ted.android.contacts.netparser.model.IndexItemModel;

public class apb
implements Parcelable.Creator<IndexItemModel> {
    public IndexItemModel a(Parcel parcel) {
        String string2 = parcel.readString();
        int n2 = parcel.readInt();
        int n3 = parcel.readInt();
        int n4 = parcel.readInt();
        if (string2 != null) {
            return new IndexItemModel(string2, n2, n3, n4);
        }
        return null;
    }

    public IndexItemModel[] a(int n2) {
        return new IndexItemModel[n2];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return this.a(parcel);
    }

    public /* synthetic */ Object[] newArray(int n2) {
        return this.a(n2);
    }
}

