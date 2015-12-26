/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.Intent
 *  android.text.TextUtils
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 *  java.util.regex.Matcher
 *  java.util.regex.Pattern
 */
package com.miui.gallery.lib;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MiuiGalleryUtils {
    private static final String[] ALBUM_SHARE_BARCODE_URLS = MiuiGalleryUtils.ALBUM_SHARE_INVITATION_URLS = new String[]{"http://mij.cc/[a-z]+/[a-zA-Z0-9\\-_]{16}#a", "http://mi1.cc/[a-zA-Z0-9\\-_]{16}#a", null};
    private static final ArrayList<Pattern> ALBUM_SHARE_BARCODE_URL_PATTERNS;
    private static final ArrayList<Pattern> ALBUM_SHARE_BARCODE_URL_STRICT_PATTERNS;
    private static final String[] ALBUM_SHARE_INVITATION_URLS;
    private static final ArrayList<Pattern> ALBUM_SHARE_INVITATION_URL_PATTERNS;
    private static final ArrayList<Pattern> ALBUM_SHARE_INVITATION_URL_STRICT_PATTERNS;

    static {
        ALBUM_SHARE_INVITATION_URL_PATTERNS = Lists.newArrayList();
        ALBUM_SHARE_INVITATION_URL_STRICT_PATTERNS = Lists.newArrayList();
        ALBUM_SHARE_BARCODE_URL_PATTERNS = Lists.newArrayList();
        ALBUM_SHARE_BARCODE_URL_STRICT_PATTERNS = Lists.newArrayList();
        MiuiGalleryUtils.initPatterns(ALBUM_SHARE_INVITATION_URLS, ALBUM_SHARE_INVITATION_URL_PATTERNS, ALBUM_SHARE_INVITATION_URL_STRICT_PATTERNS);
        MiuiGalleryUtils.initPatterns(ALBUM_SHARE_BARCODE_URLS, ALBUM_SHARE_BARCODE_URL_PATTERNS, ALBUM_SHARE_BARCODE_URL_STRICT_PATTERNS);
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private static String detectAlbumShareInvitationUrl(String string2) {
        Matcher matcher;
        if (TextUtils.isEmpty((CharSequence)string2)) {
            return null;
        }
        string2 = MiuiGalleryUtils.limitMaxLength(string2, 1000);
        Iterator iterator = ALBUM_SHARE_INVITATION_URL_PATTERNS.iterator();
        do {
            if (!iterator.hasNext()) return null;
        } while (!(matcher = ((Pattern)iterator.next()).matcher((CharSequence)string2)).find());
        return matcher.group(0);
    }

    private static Intent getAlbumShareInvitationIntent(Context context, String string2, String string3, String string4, String string5, int n) {
        if (TextUtils.isEmpty((CharSequence)string2)) {
            throw new IllegalArgumentException("Url is empty");
        }
        context = new Intent("com.miui.gallery.ACTION_ALBUM_SHARE_INVITATION");
        context.setPackage("com.miui.gallery");
        context.putExtra("invitation_url", string2);
        context.putExtra("invitation_title", MiuiGalleryUtils.limitMaxLength(string3, 1000));
        context.putExtra("invitation_content", MiuiGalleryUtils.limitMaxLength(string4, 1000));
        context.putExtra("invitation_type", string5);
        context.putExtra("invitation_opt", n);
        return context;
    }

    public static boolean handleAsAlbumShareInvitation(Context context, String string2, String string3, String string4) {
        return MiuiGalleryUtils.handleAsAlbumShareInvitation(context, string2, string3, string4, 5);
    }

    public static boolean handleAsAlbumShareInvitation(Context context, String string2, String string3, String string4, int n) {
        String string5 = MiuiGalleryUtils.detectAlbumShareInvitationUrl(string3);
        if (TextUtils.isEmpty((CharSequence)string5)) {
            return false;
        }
        if ((string2 = MiuiGalleryUtils.getAlbumShareInvitationIntent(context, string5, string2, string3, string4, n)) == null) {
            return false;
        }
        context.sendBroadcast((Intent)string2);
        return true;
    }

    private static void initPatterns(String[] arrstring, ArrayList<Pattern> arrayList, ArrayList<Pattern> arrayList2) {
        for (String string2 : arrstring) {
            if (string2 == null) continue;
            arrayList.add((Object)Pattern.compile((String)string2));
            arrayList2.add((Object)Pattern.compile((String)String.format((String)"^%s$", (Object[])new Object[]{string2})));
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static String limitMaxLength(String string2, int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Bad length, maxLen=" + n);
        }
        if (string2 == null) {
            return null;
        }
        String string3 = string2;
        if (string2.length() < n) return string3;
        return string2.substring(0, n);
    }
}

