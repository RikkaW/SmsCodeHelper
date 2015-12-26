/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.graphics.Bitmap
 *  android.media.MediaMetadata
 *  android.media.MediaMetadata$Builder
 *  android.media.Rating
 *  java.lang.Object
 *  java.lang.String
 */
package android.support.v4.media;

import android.graphics.Bitmap;
import android.media.MediaMetadata;
import android.media.Rating;
import java.util.Set;

class MediaMetadataCompatApi21 {
    MediaMetadataCompatApi21() {
    }

    public static Bitmap getBitmap(Object object, String string2) {
        return ((MediaMetadata)object).getBitmap(string2);
    }

    public static long getLong(Object object, String string2) {
        return ((MediaMetadata)object).getLong(string2);
    }

    public static Object getRating(Object object, String string2) {
        return ((MediaMetadata)object).getRating(string2);
    }

    public static CharSequence getText(Object object, String string2) {
        return ((MediaMetadata)object).getText(string2);
    }

    public static Set<String> keySet(Object object) {
        return ((MediaMetadata)object).keySet();
    }

    public static class Builder {
        public static Object build(Object object) {
            return ((MediaMetadata.Builder)object).build();
        }

        public static Object newInstance() {
            return new MediaMetadata.Builder();
        }

        public static void putBitmap(Object object, String string2, Bitmap bitmap) {
            ((MediaMetadata.Builder)object).putBitmap(string2, bitmap);
        }

        public static void putLong(Object object, String string2, long l2) {
            ((MediaMetadata.Builder)object).putLong(string2, l2);
        }

        public static void putRating(Object object, String string2, Object object2) {
            ((MediaMetadata.Builder)object).putRating(string2, (Rating)object2);
        }

        public static void putString(Object object, String string2, String string3) {
            ((MediaMetadata.Builder)object).putString(string2, string3);
        }

        public static void putText(Object object, String string2, CharSequence charSequence) {
            ((MediaMetadata.Builder)object).putText(string2, charSequence);
        }
    }

}

