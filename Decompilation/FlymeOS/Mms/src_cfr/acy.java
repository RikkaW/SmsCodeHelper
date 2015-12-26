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
import com.android.mms.view.CustomTimePicker;

public final class acy
implements Parcelable.Creator<CustomTimePicker.SavedState> {
    public CustomTimePicker.SavedState a(Parcel parcel) {
        return new CustomTimePicker.SavedState(parcel, null);
    }

    public CustomTimePicker.SavedState[] a(int n2) {
        return new CustomTimePicker.SavedState[n2];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return this.a(parcel);
    }

    public /* synthetic */ Object[] newArray(int n2) {
        return this.a(n2);
    }
}

