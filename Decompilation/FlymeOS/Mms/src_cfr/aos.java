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
import com.ted.android.contacts.netparser.model.CategoryItemModel;

public class aos
implements Parcelable.Creator<CategoryItemModel> {
    public CategoryItemModel a(Parcel object) {
        String string2 = object.readString();
        String string3 = object.readString();
        String string4 = object.readString();
        String string5 = object.readString();
        String string6 = object.readString();
        object = object.readString();
        if (string2 != null && string3 != null && string4 != null && string5 != null && string6 != null && object != null) {
            return new CategoryItemModel(string2, string3, string4, string5, string6, (String)object);
        }
        return null;
    }

    public CategoryItemModel[] a(int n2) {
        return new CategoryItemModel[n2];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return this.a(parcel);
    }

    public /* synthetic */ Object[] newArray(int n2) {
        return this.a(n2);
    }
}

