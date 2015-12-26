/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 *  org.apache.http.Header
 *  org.apache.http.HeaderElement
 *  org.apache.http.HttpEntity
 *  org.apache.http.HttpResponse
 *  org.apache.http.HttpResponseInterceptor
 *  org.apache.http.protocol.HttpContext
 */
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.protocol.HttpContext;

class aob
implements HttpResponseInterceptor {
    final /* synthetic */ ab a;

    aob(ab ab2) {
        this.a = ab2;
    }

    /*
     * Enabled aggressive block sorting
     */
    public void process(HttpResponse httpResponse, HttpContext httpContext) {
        HeaderElement[] arrheaderElement;
        httpContext = httpResponse.getEntity();
        if (httpContext == null || (arrheaderElement = httpContext.getContentEncoding()) == null) {
            return;
        }
        arrheaderElement = arrheaderElement.getElements();
        int n2 = arrheaderElement.length;
        int n3 = 0;
        while (n3 < n2) {
            if (arrheaderElement[n3].getName().equalsIgnoreCase("gzip")) {
                httpResponse.setEntity((HttpEntity)new ab.a((HttpEntity)httpContext));
                return;
            }
            ++n3;
        }
    }
}

