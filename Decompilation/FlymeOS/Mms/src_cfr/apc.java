/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 *  org.apache.http.HttpHost
 *  org.apache.http.HttpRequest
 *  org.apache.http.HttpRequestInterceptor
 *  org.apache.http.auth.AuthScheme
 *  org.apache.http.auth.AuthScope
 *  org.apache.http.auth.AuthState
 *  org.apache.http.auth.Credentials
 *  org.apache.http.client.CredentialsProvider
 *  org.apache.http.impl.auth.BasicScheme
 *  org.apache.http.protocol.HttpContext
 */
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.auth.AuthScheme;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.AuthState;
import org.apache.http.auth.Credentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.protocol.HttpContext;

class apc
implements HttpRequestInterceptor {
    final /* synthetic */ ab a;

    apc(ab ab2) {
        this.a = ab2;
    }

    public void process(HttpRequest httpRequest, HttpContext httpContext) {
        httpRequest = (AuthState)httpContext.getAttribute("http.auth.target-scope");
        CredentialsProvider credentialsProvider = (CredentialsProvider)httpContext.getAttribute("http.auth.credentials-provider");
        httpContext = (HttpHost)httpContext.getAttribute("http.target_host");
        if (httpRequest.getAuthScheme() == null && (httpContext = credentialsProvider.getCredentials(new AuthScope(httpContext.getHostName(), httpContext.getPort()))) != null) {
            httpRequest.setAuthScheme((AuthScheme)new BasicScheme());
            httpRequest.setCredentials((Credentials)httpContext);
        }
    }
}

