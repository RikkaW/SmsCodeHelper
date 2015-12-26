/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.util.Pair
 *  java.io.BufferedReader
 *  java.io.InputStreamReader
 *  java.io.Reader
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.StringBuffer
 *  java.util.ArrayList
 *  org.apache.http.HttpEntity
 *  org.apache.http.HttpResponse
 *  org.apache.http.StatusLine
 *  org.apache.http.client.entity.UrlEncodedFormEntity
 *  org.apache.http.client.methods.HttpPost
 *  org.apache.http.client.methods.HttpUriRequest
 *  org.apache.http.message.BasicNameValuePair
 */
import android.util.Pair;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.message.BasicNameValuePair;

public class ank {
    public static String a(String string2, List<Pair<String, String>> list) {
        try {
            string2 = ank.b(string2, list);
            return string2;
        }
        catch (ane var0_1) {
            var0_1.printStackTrace();
            return null;
        }
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static String b(String object, List<Pair<String, String>> object2) {
        alz alz2 = alz.a("MEIZU", 30000, true);
        try {
            Object object3;
            object = new HttpPost((String)object);
            ArrayList arrayList = new ArrayList();
            object2 = object2.iterator();
            while (object2.hasNext()) {
                object3 = (Pair)object2.next();
                arrayList.add(new BasicNameValuePair((String)object3.first, (String)object3.second));
            }
            object.setEntity((HttpEntity)new UrlEncodedFormEntity((List)arrayList, "UTF-8"));
            object = alz2.execute((HttpUriRequest)object);
            int n2 = object.getStatusLine().getStatusCode();
            if (n2 != 200) throw new ane(n2, "Server response code : " + n2);
            object = object.getEntity().getContent();
            object2 = new BufferedReader((Reader)new InputStreamReader((InputStream)object, "UTF-8"));
            arrayList = new StringBuffer();
            while ((object3 = object2.readLine()) != null) {
                arrayList.append((String)object3).append("\n");
            }
            try {
                object.close();
                object = arrayList.toString();
                return object;
            }
            catch (ane var0_1) {
                throw var0_1;
            }
            catch (Exception var0_3) {
                var0_3.printStackTrace();
                throw new ane(var0_3.getMessage());
            }
        }
        finally {
            if (alz2 != null) {
                alz2.a();
            }
        }
    }
}

