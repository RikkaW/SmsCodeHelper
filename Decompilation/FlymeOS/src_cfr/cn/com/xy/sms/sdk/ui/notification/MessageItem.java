/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.os.Parcel
 *  android.os.Parcelable
 *  android.os.Parcelable$Creator
 *  java.lang.Object
 *  java.lang.String
 *  java.util.HashMap
 */
package cn.com.xy.sms.sdk.ui.notification;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.HashMap;
import java.util.Map;

public class MessageItem
implements Parcelable {
    public static final Parcelable.Creator<MessageItem> CREATOR = new ck();
    public int a;
    public String b;
    public String c;
    public Map<String, String> d = new HashMap();
    public Map<String, Object> e = new HashMap();

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int n2) {
        parcel.writeInt(this.a);
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        parcel.writeMap(this.d);
        parcel.writeMap(this.e);
    }
}

