/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.graphics.Bitmap
 *  android.media.MediaDescription
 *  android.media.MediaDescription$Builder
 *  android.net.Uri
 *  android.os.Bundle
 *  android.os.Parcel
 *  android.os.Parcelable
 *  android.os.Parcelable$Creator
 *  java.lang.Object
 *  java.lang.String
 */
package android.support.v4.media;

import android.graphics.Bitmap;
import android.media.MediaDescription;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

public class MediaDescriptionCompatApi21 {
    public static Object fromParcel(Parcel parcel) {
        return MediaDescription.CREATOR.createFromParcel(parcel);
    }

    public static CharSequence getDescription(Object object) {
        return ((MediaDescription)object).getDescription();
    }

    public static Bundle getExtras(Object object) {
        return ((MediaDescription)object).getExtras();
    }

    public static Bitmap getIconBitmap(Object object) {
        return ((MediaDescription)object).getIconBitmap();
    }

    public static Uri getIconUri(Object object) {
        return ((MediaDescription)object).getIconUri();
    }

    public static String getMediaId(Object object) {
        return ((MediaDescription)object).getMediaId();
    }

    public static CharSequence getSubtitle(Object object) {
        return ((MediaDescription)object).getSubtitle();
    }

    public static CharSequence getTitle(Object object) {
        return ((MediaDescription)object).getTitle();
    }

    public static void writeToParcel(Object object, Parcel parcel, int n2) {
        ((MediaDescription)object).writeToParcel(parcel, n2);
    }

    public static class Builder {
        public static Object build(Object object) {
            return ((MediaDescription.Builder)object).build();
        }

        public static Object newInstance() {
            return new MediaDescription.Builder();
        }

        public static void setDescription(Object object, CharSequence charSequence) {
            ((MediaDescription.Builder)object).setDescription(charSequence);
        }

        public static void setExtras(Object object, Bundle bundle) {
            ((MediaDescription.Builder)object).setExtras(bundle);
        }

        public static void setIconBitmap(Object object, Bitmap bitmap) {
            ((MediaDescription.Builder)object).setIconBitmap(bitmap);
        }

        public static void setIconUri(Object object, Uri uri) {
            ((MediaDescription.Builder)object).setIconUri(uri);
        }

        public static void setMediaId(Object object, String string2) {
            ((MediaDescription.Builder)object).setMediaId(string2);
        }

        public static void setSubtitle(Object object, CharSequence charSequence) {
            ((MediaDescription.Builder)object).setSubtitle(charSequence);
        }

        public static void setTitle(Object object, CharSequence charSequence) {
            ((MediaDescription.Builder)object).setTitle(charSequence);
        }
    }

}

