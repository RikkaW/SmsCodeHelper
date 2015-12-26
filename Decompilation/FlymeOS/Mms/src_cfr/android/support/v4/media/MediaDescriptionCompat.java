/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.graphics.Bitmap
 *  android.net.Uri
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.os.Bundle
 *  android.os.Parcel
 *  android.os.Parcelable
 *  android.os.Parcelable$Creator
 *  android.text.TextUtils
 *  java.lang.ClassLoader
 *  java.lang.Object
 *  java.lang.String
 */
package android.support.v4.media;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.media.MediaDescriptionCompat$1;
import android.support.v4.media.MediaDescriptionCompatApi21;
import android.text.TextUtils;

public final class MediaDescriptionCompat
implements Parcelable {
    public static final Parcelable.Creator<MediaDescriptionCompat> CREATOR = new MediaDescriptionCompat$1();
    private final CharSequence mDescription;
    private Object mDescriptionObj;
    private final Bundle mExtras;
    private final Bitmap mIcon;
    private final Uri mIconUri;
    private final String mMediaId;
    private final CharSequence mSubtitle;
    private final CharSequence mTitle;

    private MediaDescriptionCompat(Parcel parcel) {
        this.mMediaId = parcel.readString();
        this.mTitle = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.mSubtitle = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.mDescription = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.mIcon = (Bitmap)parcel.readParcelable(null);
        this.mIconUri = (Uri)parcel.readParcelable(null);
        this.mExtras = parcel.readBundle();
    }

    /* synthetic */ MediaDescriptionCompat(Parcel parcel, MediaDescriptionCompat$1 mediaDescriptionCompat$1) {
        this(parcel);
    }

    private MediaDescriptionCompat(String string2, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, Bitmap bitmap, Uri uri, Bundle bundle) {
        this.mMediaId = string2;
        this.mTitle = charSequence;
        this.mSubtitle = charSequence2;
        this.mDescription = charSequence3;
        this.mIcon = bitmap;
        this.mIconUri = uri;
        this.mExtras = bundle;
    }

    /* synthetic */ MediaDescriptionCompat(String string2, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, Bitmap bitmap, Uri uri, Bundle bundle, MediaDescriptionCompat$1 mediaDescriptionCompat$1) {
        this(string2, charSequence, charSequence2, charSequence3, bitmap, uri, bundle);
    }

    public static MediaDescriptionCompat fromMediaDescription(Object object) {
        if (object == null || Build.VERSION.SDK_INT < 21) {
            return null;
        }
        Object object2 = new Builder();
        object2.setMediaId(MediaDescriptionCompatApi21.getMediaId(object));
        object2.setTitle(MediaDescriptionCompatApi21.getTitle(object));
        object2.setSubtitle(MediaDescriptionCompatApi21.getSubtitle(object));
        object2.setDescription(MediaDescriptionCompatApi21.getDescription(object));
        object2.setIconBitmap(MediaDescriptionCompatApi21.getIconBitmap(object));
        object2.setIconUri(MediaDescriptionCompatApi21.getIconUri(object));
        object2.setExtras(MediaDescriptionCompatApi21.getExtras(object));
        object2 = object2.build();
        object2.mDescriptionObj = object;
        return object2;
    }

    public int describeContents() {
        return 0;
    }

    public CharSequence getDescription() {
        return this.mDescription;
    }

    public Bundle getExtras() {
        return this.mExtras;
    }

    public Bitmap getIconBitmap() {
        return this.mIcon;
    }

    public Uri getIconUri() {
        return this.mIconUri;
    }

    public Object getMediaDescription() {
        if (this.mDescriptionObj != null || Build.VERSION.SDK_INT < 21) {
            return this.mDescriptionObj;
        }
        Object object = MediaDescriptionCompatApi21.Builder.newInstance();
        MediaDescriptionCompatApi21.Builder.setMediaId(object, this.mMediaId);
        MediaDescriptionCompatApi21.Builder.setTitle(object, this.mTitle);
        MediaDescriptionCompatApi21.Builder.setSubtitle(object, this.mSubtitle);
        MediaDescriptionCompatApi21.Builder.setDescription(object, this.mDescription);
        MediaDescriptionCompatApi21.Builder.setIconBitmap(object, this.mIcon);
        MediaDescriptionCompatApi21.Builder.setIconUri(object, this.mIconUri);
        MediaDescriptionCompatApi21.Builder.setExtras(object, this.mExtras);
        this.mDescriptionObj = MediaDescriptionCompatApi21.Builder.build(object);
        return this.mDescriptionObj;
    }

    public String getMediaId() {
        return this.mMediaId;
    }

    public CharSequence getSubtitle() {
        return this.mSubtitle;
    }

    public CharSequence getTitle() {
        return this.mTitle;
    }

    public String toString() {
        return this.mTitle + ", " + this.mSubtitle + ", " + this.mDescription;
    }

    public void writeToParcel(Parcel parcel, int n2) {
        if (Build.VERSION.SDK_INT < 21) {
            parcel.writeString(this.mMediaId);
            TextUtils.writeToParcel((CharSequence)this.mTitle, (Parcel)parcel, (int)n2);
            TextUtils.writeToParcel((CharSequence)this.mSubtitle, (Parcel)parcel, (int)n2);
            TextUtils.writeToParcel((CharSequence)this.mDescription, (Parcel)parcel, (int)n2);
            parcel.writeParcelable((Parcelable)this.mIcon, n2);
            parcel.writeParcelable((Parcelable)this.mIconUri, n2);
            parcel.writeBundle(this.mExtras);
            return;
        }
        MediaDescriptionCompatApi21.writeToParcel(this.getMediaDescription(), parcel, n2);
    }

    public static final class Builder {
        private CharSequence mDescription;
        private Bundle mExtras;
        private Bitmap mIcon;
        private Uri mIconUri;
        private String mMediaId;
        private CharSequence mSubtitle;
        private CharSequence mTitle;

        public MediaDescriptionCompat build() {
            return new MediaDescriptionCompat(this.mMediaId, this.mTitle, this.mSubtitle, this.mDescription, this.mIcon, this.mIconUri, this.mExtras, null);
        }

        public Builder setDescription(CharSequence charSequence) {
            this.mDescription = charSequence;
            return this;
        }

        public Builder setExtras(Bundle bundle) {
            this.mExtras = bundle;
            return this;
        }

        public Builder setIconBitmap(Bitmap bitmap) {
            this.mIcon = bitmap;
            return this;
        }

        public Builder setIconUri(Uri uri) {
            this.mIconUri = uri;
            return this;
        }

        public Builder setMediaId(String string2) {
            this.mMediaId = string2;
            return this;
        }

        public Builder setSubtitle(CharSequence charSequence) {
            this.mSubtitle = charSequence;
            return this;
        }

        public Builder setTitle(CharSequence charSequence) {
            this.mTitle = charSequence;
            return this;
        }
    }

}

