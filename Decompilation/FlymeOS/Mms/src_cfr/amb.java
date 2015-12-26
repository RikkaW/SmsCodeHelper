/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 *  org.apache.http.HttpRequestInterceptor
 *  org.apache.http.auth.AuthSchemeRegistry
 *  org.apache.http.client.CredentialsProvider
 *  org.apache.http.conn.ClientConnectionManager
 *  org.apache.http.cookie.CookieSpecRegistry
 *  org.apache.http.impl.client.DefaultHttpClient
 *  org.apache.http.params.HttpParams
 *  org.apache.http.protocol.BasicHttpContext
 *  org.apache.http.protocol.BasicHttpProcessor
 *  org.apache.http.protocol.HttpContext
 */
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.auth.AuthSchemeRegistry;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.cookie.CookieSpecRegistry;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.BasicHttpProcessor;
import org.apache.http.protocol.HttpContext;

class amb
extends DefaultHttpClient {
    final /* synthetic */ alz a;

    amb(alz alz2, ClientConnectionManager clientConnectionManager, HttpParams httpParams) {
        this.a = alz2;
        super(clientConnectionManager, httpParams);
    }

    protected HttpContext createHttpContext() {
        BasicHttpContext basicHttpContext = new BasicHttpContext();
        basicHttpContext.setAttribute("http.authscheme-registry", (Object)this.getAuthSchemes());
        basicHttpContext.setAttribute("http.cookiespec-registry", (Object)this.getCookieSpecs());
        basicHttpContext.setAttribute("http.auth.credentials-provider", (Object)this.getCredentialsProvider());
        return basicHttpContext;
    }

    protected BasicHttpProcessor createHttpProcessor() {
        BasicHttpProcessor basicHttpProcessor = super.createHttpProcessor();
        basicHttpProcessor.addRequestInterceptor(alz.b());
        basicHttpProcessor.addRequestInterceptor((HttpRequestInterceptor)this.a.new alz.a(null));
        return basicHttpProcessor;
    }
}

