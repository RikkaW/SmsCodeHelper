/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 *  java.security.cert.X509Certificate
 */
package com.amap.api.mapcore2d;

import java.security.cert.X509Certificate;
import javax.net.ssl.X509TrustManager;

final class fb
implements X509TrustManager {
    fb() {
    }

    @Override
    public void checkClientTrusted(X509Certificate[] arrx509Certificate, String string2) {
    }

    @Override
    public void checkServerTrusted(X509Certificate[] arrx509Certificate, String string2) {
    }

    @Override
    public X509Certificate[] getAcceptedIssuers() {
        return null;
    }
}

