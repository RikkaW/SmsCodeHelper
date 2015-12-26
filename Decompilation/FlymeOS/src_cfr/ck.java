/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.os.Parcel
 *  android.os.Parcelable
 *  android.os.Parcelable$Creator
 *  java.lang.ClassLoader
 *  java.lang.Object
 *  java.lang.String
 *  java.util.HashMap
 */
import android.os.Parcel;
import android.os.Parcelable;
import cn.com.xy.sms.sdk.ui.notification.MessageItem;
import java.util.HashMap;
import java.util.Map;

public final class ck
implements Parcelable.Creator {
    public MessageItem a(Parcel parcel) {
        MessageItem messageItem = new MessageItem();
        messageItem.a = parcel.readInt();
        messageItem.b = parcel.readString();
        messageItem.c = parcel.readString();
        messageItem.d = parcel.readHashMap(HashMap.class.getClassLoader());
        messageItem.e = parcel.readHashMap(HashMap.class.getClassLoader());
        return messageItem;
    }

    public MessageItem[] a(int n2) {
        return new MessageItem[n2];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return this.a(parcel);
    }

    public /* synthetic */ Object[] newArray(int n2) {
        return this.a(n2);
    }
}

