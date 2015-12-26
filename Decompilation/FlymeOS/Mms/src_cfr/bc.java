/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.util.Log
 *  java.lang.Object
 *  java.lang.String
 *  org.apache.http.Header
 *  org.apache.http.HttpRequest
 *  org.apache.http.HttpRequestInterceptor
 *  org.apache.http.protocol.HttpContext
 */
import android.util.Log;
import java.util.Iterator;
import java.util.Set;
import org.apache.http.Header;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.protocol.HttpContext;

class bc
implements HttpRequestInterceptor {
    final /* synthetic */ ab a;

    bc(ab ab2) {
        this.a = ab2;
    }

    public void process(HttpRequest httpRequest, HttpContext object) {
        if (!httpRequest.containsHeader("Accept-Encoding")) {
            httpRequest.addHeader("Accept-Encoding", "gzip");
        }
        object = ab.a(this.a).keySet().iterator();
        while (object.hasNext()) {
            String string2 = (String)object.next();
            if (httpRequest.containsHeader(string2)) {
                Header header = httpRequest.getFirstHeader(string2);
                Log.d((String)"AsyncHttpClient", (String)String.format((String)"Headers were overwritten! (%s | %s) overwrites (%s | %s)", (Object[])new Object[]{string2, ab.a(this.a).get(string2), header.getName(), header.getValue()}));
                httpRequest.removeHeader(header);
            }
            httpRequest.addHeader(string2, (String)ab.a(this.a).get(string2));
        }
        return;
    }
}

