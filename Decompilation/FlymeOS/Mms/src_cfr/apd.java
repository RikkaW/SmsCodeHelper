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
import com.ted.android.contacts.netparser.model.IndexItemModel;
import com.ted.android.contacts.netparser.model.IndexListModel;
import java.util.ArrayList;
import java.util.List;

public class apd
implements Parcelable.Creator<IndexListModel> {
    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public IndexListModel a(Parcel object) {
        int n2 = object.readInt();
        String string2 = object.readString();
        object = object.createTypedArrayList(IndexItemModel.CREATOR);
        if (string2 == null) return null;
        if (object == null) return null;
        try {
            return new IndexListModel(n2, string2, (List<IndexItemModel>)object);
        }
        catch (OutOfMemoryError var1_2) {
            var1_2.printStackTrace();
            do {
                return null;
                break;
            } while (true);
        }
        catch (Exception var1_3) {
            var1_3.printStackTrace();
            return null;
        }
    }

    public IndexListModel[] a(int n2) {
        return new IndexListModel[n2];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return this.a(parcel);
    }

    public /* synthetic */ Object[] newArray(int n2) {
        return this.a(n2);
    }
}

