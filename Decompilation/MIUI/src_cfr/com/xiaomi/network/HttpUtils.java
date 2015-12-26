/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.net.Uri
 *  android.net.Uri$Builder
 *  android.text.TextUtils
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.net.URL
 *  java.util.ArrayList
 */
package com.xiaomi.network;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.network.NameValuePair;
import com.xiaomi.channel.commonutils.network.Network;
import com.xiaomi.network.Fallback;
import com.xiaomi.network.HostManager;
import com.xiaomi.network.HttpProcessor;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public abstract class HttpUtils {
    public static String get(Context context, String string2, List<NameValuePair> list) {
        return HttpUtils.httpRequest(context, string2, list, new DefaultHttpGetProcessor(), true);
    }

    static int getHttpGetTxtTraffic(int n, int n2) {
        return (n2 + 243) / 1448 * 132 + 1080 + n + n2;
    }

    static int getHttpPostTxtTraffic(int n, int n2, int n3) {
        return (n2 + 200) / 1448 * 132 + 1011 + n2 + n + n3;
    }

    static int getPostDataLength(List<NameValuePair> object) {
        int n = 0;
        object = object.iterator();
        while (object.hasNext()) {
            NameValuePair nameValuePair = (NameValuePair)object.next();
            int n2 = n;
            if (!TextUtils.isEmpty((CharSequence)nameValuePair.getName())) {
                n2 = n + nameValuePair.getName().length();
            }
            n = n2;
            if (TextUtils.isEmpty((CharSequence)nameValuePair.getValue())) continue;
            n = n2 + nameValuePair.getValue().length();
        }
        return n * 2;
    }

    static int getStringUTF8Length(String string2) {
        if (TextUtils.isEmpty((CharSequence)string2)) {
            return 0;
        }
        try {
            int n = string2.getBytes("UTF-8").length;
            return n;
        }
        catch (UnsupportedEncodingException var0_1) {
            return 0;
        }
    }

    private static int getTraffic(HttpProcessor httpProcessor, String string2, List<NameValuePair> list, String string3) {
        if (httpProcessor.getRequestType() == 1) {
            return HttpUtils.getHttpGetTxtTraffic(string2.length(), HttpUtils.getStringUTF8Length(string3));
        }
        if (httpProcessor.getRequestType() == 2) {
            int n = HttpUtils.getPostDataLength(list);
            return HttpUtils.getHttpPostTxtTraffic(string2.length(), n, HttpUtils.getStringUTF8Length(string3));
        }
        return -1;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static String httpRequest(Context context, String object, List<NameValuePair> list, HttpProcessor httpProcessor, boolean bl) {
        if (!Network.hasNetwork(context)) return null;
        try {
            Object object2;
            ArrayList arrayList = new ArrayList();
            Object object3 = null;
            Object object4 = arrayList;
            if (bl) {
                object3 = object2 = HostManager.getInstance().getFallbacksByURL((String)object);
                object4 = arrayList;
                if (object2 != null) {
                    object4 = object2.getUrls((String)object);
                    object3 = object2;
                }
            }
            if (!object4.contains(object)) {
                object4.add(object);
            }
            object = null;
            Iterator iterator = object4.iterator();
            do {
                object2 = object;
                if (!iterator.hasNext()) return object2;
                String string2 = (String)iterator.next();
                arrayList = list != null ? new ArrayList(list) : null;
                long l = System.currentTimeMillis();
                object2 = object;
                try {
                    if (!httpProcessor.prepare(context, string2, (List<NameValuePair>)arrayList)) {
                        return object;
                    }
                    object2 = object;
                    object2 = object4 = httpProcessor.visit(context, string2, (List<NameValuePair>)arrayList);
                    if (!TextUtils.isEmpty((CharSequence)object4)) {
                        object2 = object4;
                        if (object3 == null) return object2;
                        {
                            object2 = object4;
                            object3.succeedUrl(string2, System.currentTimeMillis() - l, HttpUtils.getTraffic(httpProcessor, string2, arrayList, (String)object4));
                            return object4;
                        }
                    }
                    object = object4;
                    if (object3 == null) continue;
                    object2 = object4;
                    object3.failedUrl(string2, System.currentTimeMillis() - l, HttpUtils.getTraffic(httpProcessor, string2, arrayList, (String)object4), null);
                    object = object4;
                }
                catch (IOException var1_3) {
                    if (object3 != null) {
                        object3.failedUrl(string2, System.currentTimeMillis() - l, HttpUtils.getTraffic(httpProcessor, string2, arrayList, (String)object2), var1_3);
                    }
                    var1_3.printStackTrace();
                    object = object2;
                }
            } while (true);
        }
        catch (MalformedURLException var0_1) {
            var0_1.printStackTrace();
        }
        return null;
    }

    public static class DefaultHttpGetProcessor
    extends HttpProcessor {
        public DefaultHttpGetProcessor() {
            super(1);
        }

        @Override
        public String visit(Context context, String string2, List<NameValuePair> object) throws IOException {
            if (object == null) {
                return Network.downloadXml(context, new URL(string2));
            }
            string2 = Uri.parse((String)string2).buildUpon();
            object = object.iterator();
            while (object.hasNext()) {
                NameValuePair nameValuePair = (NameValuePair)object.next();
                string2.appendQueryParameter(nameValuePair.getName(), nameValuePair.getValue());
            }
            return Network.downloadXml(context, new URL(string2.toString()));
        }
    }

}

