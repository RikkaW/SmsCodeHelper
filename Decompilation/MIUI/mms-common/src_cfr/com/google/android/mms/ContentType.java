/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 */
package com.google.android.mms;

import java.util.ArrayList;

public class ContentType {
    public static final String APP_DRM_CONTENT = "application/vnd.oma.drm.content";
    public static final String APP_DRM_MESSAGE = "application/vnd.oma.drm.message";
    public static final String APP_SMIL = "application/smil";
    public static final String APP_WAP_XHTML = "application/vnd.wap.xhtml+xml";
    public static final String APP_XHTML = "application/xhtml+xml";
    public static final String AUDIO_3GPP = "audio/3gpp";
    public static final String AUDIO_AAC = "audio/aac";
    public static final String AUDIO_AAC_MP4 = "audio/aac_mp4";
    public static final String AUDIO_AMR = "audio/amr";
    public static final String AUDIO_EVRC = "audio/evrc";
    public static final String AUDIO_IMELODY = "audio/imelody";
    public static final String AUDIO_MID = "audio/mid";
    public static final String AUDIO_MIDI = "audio/midi";
    public static final String AUDIO_MP3 = "audio/mp3";
    public static final String AUDIO_MP4 = "audio/mp4";
    public static final String AUDIO_MPEG = "audio/mpeg";
    public static final String AUDIO_MPEG3 = "audio/mpeg3";
    public static final String AUDIO_MPG = "audio/mpg";
    public static final String AUDIO_OGG = "application/ogg";
    public static final String AUDIO_QCELP = "audio/qcelp";
    public static final String AUDIO_UNSPECIFIED = "audio/*";
    public static final String AUDIO_X_MID = "audio/x-mid";
    public static final String AUDIO_X_MIDI = "audio/x-midi";
    public static final String AUDIO_X_MP3 = "audio/x-mp3";
    public static final String AUDIO_X_MPEG = "audio/x-mpeg";
    public static final String AUDIO_X_MPEG3 = "audio/x-mpeg3";
    public static final String AUDIO_X_MPG = "audio/x-mpg";
    public static final String AUDIO_X_WAV = "audio/x-wav";
    public static final String IMAGE_GIF = "image/gif";
    public static final String IMAGE_JPEG = "image/jpeg";
    public static final String IMAGE_JPG = "image/jpg";
    public static final String IMAGE_PNG = "image/png";
    public static final String IMAGE_UNSPECIFIED = "image/*";
    public static final String IMAGE_WBMP = "image/vnd.wap.wbmp";
    public static final String IMAGE_X_MS_BMP = "image/x-ms-bmp";
    public static final String MMS_GENERIC = "application/vnd.wap.mms-generic";
    public static final String MMS_MESSAGE = "application/vnd.wap.mms-message";
    public static final String MULTIPART_ALTERNATIVE = "application/vnd.wap.multipart.alternative";
    public static final String MULTIPART_MIXED = "application/vnd.wap.multipart.mixed";
    public static final String MULTIPART_RELATED = "application/vnd.wap.multipart.related";
    public static final String TEXT_HTML = "text/html";
    public static final String TEXT_PLAIN = "text/plain";
    public static final String TEXT_VCALENDAR = "text/x-vCalendar";
    public static final String TEXT_VCARD = "text/x-vCard";
    public static final String VIDEO_3G2 = "video/3gpp2";
    public static final String VIDEO_3GPP = "video/3gpp";
    public static final String VIDEO_H263 = "video/h263";
    public static final String VIDEO_MP4 = "video/mp4";
    public static final String VIDEO_UNSPECIFIED = "video/*";
    private static final ArrayList<String> sSupportedAudioTypes;
    private static final ArrayList<String> sSupportedContentTypes;
    private static final ArrayList<String> sSupportedImageTypes;
    private static final ArrayList<String> sSupportedVideoTypes;

    static {
        sSupportedContentTypes = new ArrayList();
        sSupportedImageTypes = new ArrayList();
        sSupportedAudioTypes = new ArrayList();
        sSupportedVideoTypes = new ArrayList();
        sSupportedContentTypes.add((Object)"text/plain");
        sSupportedContentTypes.add((Object)"text/html");
        sSupportedContentTypes.add((Object)"text/x-vCalendar");
        sSupportedContentTypes.add((Object)"text/x-vCard");
        sSupportedContentTypes.add((Object)"image/jpeg");
        sSupportedContentTypes.add((Object)"image/gif");
        sSupportedContentTypes.add((Object)"image/vnd.wap.wbmp");
        sSupportedContentTypes.add((Object)"image/png");
        sSupportedContentTypes.add((Object)"image/jpg");
        sSupportedContentTypes.add((Object)"image/x-ms-bmp");
        sSupportedContentTypes.add((Object)"audio/aac");
        sSupportedContentTypes.add((Object)"audio/aac_mp4");
        sSupportedContentTypes.add((Object)"audio/qcelp");
        sSupportedContentTypes.add((Object)"audio/evrc");
        sSupportedContentTypes.add((Object)"audio/amr");
        sSupportedContentTypes.add((Object)"audio/imelody");
        sSupportedContentTypes.add((Object)"audio/mid");
        sSupportedContentTypes.add((Object)"audio/midi");
        sSupportedContentTypes.add((Object)"audio/mp3");
        sSupportedContentTypes.add((Object)"audio/mp4");
        sSupportedContentTypes.add((Object)"audio/mpeg3");
        sSupportedContentTypes.add((Object)"audio/mpeg");
        sSupportedContentTypes.add((Object)"audio/mpg");
        sSupportedContentTypes.add((Object)"audio/x-mid");
        sSupportedContentTypes.add((Object)"audio/x-midi");
        sSupportedContentTypes.add((Object)"audio/x-mp3");
        sSupportedContentTypes.add((Object)"audio/x-mpeg3");
        sSupportedContentTypes.add((Object)"audio/x-mpeg");
        sSupportedContentTypes.add((Object)"audio/x-mpg");
        sSupportedContentTypes.add((Object)"audio/x-wav");
        sSupportedContentTypes.add((Object)"audio/3gpp");
        sSupportedContentTypes.add((Object)"application/ogg");
        sSupportedContentTypes.add((Object)"video/3gpp");
        sSupportedContentTypes.add((Object)"video/3gpp2");
        sSupportedContentTypes.add((Object)"video/h263");
        sSupportedContentTypes.add((Object)"video/mp4");
        sSupportedContentTypes.add((Object)"application/smil");
        sSupportedContentTypes.add((Object)"application/vnd.wap.xhtml+xml");
        sSupportedContentTypes.add((Object)"application/xhtml+xml");
        sSupportedContentTypes.add((Object)"application/vnd.oma.drm.content");
        sSupportedContentTypes.add((Object)"application/vnd.oma.drm.message");
        sSupportedImageTypes.add((Object)"image/jpeg");
        sSupportedImageTypes.add((Object)"image/gif");
        sSupportedImageTypes.add((Object)"image/vnd.wap.wbmp");
        sSupportedImageTypes.add((Object)"image/png");
        sSupportedImageTypes.add((Object)"image/jpg");
        sSupportedImageTypes.add((Object)"image/x-ms-bmp");
        sSupportedAudioTypes.add((Object)"audio/aac");
        sSupportedAudioTypes.add((Object)"audio/aac_mp4");
        sSupportedAudioTypes.add((Object)"audio/qcelp");
        sSupportedAudioTypes.add((Object)"audio/evrc");
        sSupportedAudioTypes.add((Object)"audio/amr");
        sSupportedAudioTypes.add((Object)"audio/imelody");
        sSupportedAudioTypes.add((Object)"audio/mid");
        sSupportedAudioTypes.add((Object)"audio/midi");
        sSupportedAudioTypes.add((Object)"audio/mp3");
        sSupportedAudioTypes.add((Object)"audio/mpeg3");
        sSupportedAudioTypes.add((Object)"audio/mpeg");
        sSupportedAudioTypes.add((Object)"audio/mpg");
        sSupportedAudioTypes.add((Object)"audio/mp4");
        sSupportedAudioTypes.add((Object)"audio/x-mid");
        sSupportedAudioTypes.add((Object)"audio/x-midi");
        sSupportedAudioTypes.add((Object)"audio/x-mp3");
        sSupportedAudioTypes.add((Object)"audio/x-mpeg3");
        sSupportedAudioTypes.add((Object)"audio/x-mpeg");
        sSupportedAudioTypes.add((Object)"audio/x-mpg");
        sSupportedAudioTypes.add((Object)"audio/x-wav");
        sSupportedAudioTypes.add((Object)"audio/3gpp");
        sSupportedAudioTypes.add((Object)"application/ogg");
        sSupportedVideoTypes.add((Object)"video/3gpp");
        sSupportedVideoTypes.add((Object)"video/3gpp2");
        sSupportedVideoTypes.add((Object)"video/h263");
        sSupportedVideoTypes.add((Object)"video/mp4");
    }

    private ContentType() {
    }

    public static ArrayList<String> getAudioTypes() {
        return (ArrayList)sSupportedAudioTypes.clone();
    }

    public static ArrayList<String> getImageTypes() {
        return (ArrayList)sSupportedImageTypes.clone();
    }

    public static ArrayList<String> getSupportedTypes() {
        return (ArrayList)sSupportedContentTypes.clone();
    }

    public static ArrayList<String> getVideoTypes() {
        return (ArrayList)sSupportedVideoTypes.clone();
    }

    public static boolean isAudioType(String string) {
        if (string != null && string.startsWith("audio/")) {
            return true;
        }
        return false;
    }

    public static boolean isDrmType(String string) {
        if (string != null && (string.equals((Object)"application/vnd.oma.drm.content") || string.equals((Object)"application/vnd.oma.drm.message"))) {
            return true;
        }
        return false;
    }

    public static boolean isImageType(String string) {
        if (string != null && string.startsWith("image/")) {
            return true;
        }
        return false;
    }

    public static boolean isSupportedAudioType(String string) {
        if (ContentType.isAudioType(string) && ContentType.isSupportedType(string)) {
            return true;
        }
        return false;
    }

    public static boolean isSupportedImageType(String string) {
        if (ContentType.isImageType(string) && ContentType.isSupportedType(string)) {
            return true;
        }
        return false;
    }

    public static boolean isSupportedType(String string) {
        if (string != null && sSupportedContentTypes.contains((Object)string)) {
            return true;
        }
        return false;
    }

    public static boolean isSupportedVideoType(String string) {
        if (ContentType.isVideoType(string) && ContentType.isSupportedType(string)) {
            return true;
        }
        return false;
    }

    public static boolean isTextType(String string) {
        if (string != null && string.startsWith("text/")) {
            return true;
        }
        return false;
    }

    public static boolean isUnspecified(String string) {
        if (string != null && string.endsWith("*")) {
            return true;
        }
        return false;
    }

    public static boolean isVideoType(String string) {
        if (string != null && string.startsWith("video/")) {
            return true;
        }
        return false;
    }
}

