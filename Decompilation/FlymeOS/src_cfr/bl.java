/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.text.TextUtils
 *  android.util.Log
 *  java.io.File
 *  java.io.FileOutputStream
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.util.zip.GZIPInputStream
 *  org.apache.http.Header
 *  org.apache.http.HttpEntity
 *  org.apache.http.HttpResponse
 *  org.apache.http.HttpVersion
 *  org.apache.http.ProtocolVersion
 *  org.apache.http.StatusLine
 *  org.apache.http.client.methods.HttpGet
 *  org.apache.http.client.methods.HttpUriRequest
 *  org.apache.http.impl.client.DefaultHttpClient
 *  org.apache.http.params.BasicHttpParams
 *  org.apache.http.params.HttpConnectionParams
 *  org.apache.http.params.HttpParams
 *  org.apache.http.params.HttpProtocolParams
 */
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.ted.android.contacts.common.util.FileUtil;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.ProtocolVersion;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;

public class bl {
    private static final String a = bl.class.getSimpleName();

    public static boolean a(Context context, String string2, String string3) {
        if (context == null && aux.a) {
            Log.w((String)a, (String)"download context is null, download failed!");
            return false;
        }
        if (TextUtils.isEmpty((CharSequence)string2) && aux.a) {
            Log.w((String)a, (String)"download url is null, download failed!");
            return false;
        }
        if (TextUtils.isEmpty((CharSequence)string3) && aux.a) {
            Log.w((String)a, (String)"download path is null, download failed!");
            return false;
        }
        return bl.b(context, string2, string3);
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive exception aggregation
     */
    private static boolean b(Context var0, String var1_8, String var2_10) {
        block49 : {
            block46 : {
                block47 : {
                    block48 : {
                        block44 : {
                            block45 : {
                                var11_22 = null;
                                var12_23 = null;
                                if (var0 != null) ** GOTO lbl15
                                var7_24 = aux.a;
                                if (!var7_24) break block44;
                                if (!false) ** GOTO lbl10
                                throw new NullPointerException();
lbl10: // 1 sources:
                                if (!false) break block45;
                                throw new NullPointerException();
                            }
lbl13: // 3 sources:
                            do {
                                return false;
                                break;
                            } while (true);
                        }
                        if (aux.a) {
                            Log.i((String)bl.a, (String)("download url = " + (String)var1_8));
                            Log.i((String)bl.a, (String)("download path = " + var2_10 /* !! */ ));
                        }
                        var13_25 = new BasicHttpParams();
                        HttpProtocolParams.setVersion((HttpParams)var13_25, (ProtocolVersion)HttpVersion.HTTP_1_1);
                        HttpProtocolParams.setUserAgent((HttpParams)var13_25, (String)"Teddy-Upload");
                        HttpConnectionParams.setConnectionTimeout((HttpParams)var13_25, (int)60000);
                        HttpConnectionParams.setSoTimeout((HttpParams)var13_25, (int)60000);
                        var10_26 = new DefaultHttpClient((HttpParams)var13_25);
                        var1_8 = new HttpGet((String)var1_8);
                        var1_8.setParams((HttpParams)var13_25);
                        var1_8.setHeader("Accept-Encoding", "gzip");
                        var13_25 = var10_26.execute((HttpUriRequest)var1_8);
                        var1_8 = var13_25.getEntity();
                        var8_27 = var1_8.getContentLength();
                        var1_8 = var1_8.getContent();
                        var10_26 = var13_25.getFirstHeader("Content-Encoding");
                        if (var10_26 == null || !var10_26.getValue().equalsIgnoreCase("gzip")) break block46;
                        var10_26 = new GZIPInputStream((InputStream)var1_8);
                        var3_28 = true;
                        var1_8 = var10_26;
lbl40: // 3 sources:
                        var10_26 = var13_25.getFirstHeader("Transder-Encoding");
                        if (var10_26 == null || !var10_26.getValue().equalsIgnoreCase("chunked")) break block47;
                        Log.i((String)bl.a, (String)("isChunked = " + true));
                        var4_29 = true;
lbl44: // 2 sources:
                        var10_26 = var2_10 /* !! */ ;
                        if (!var2_10 /* !! */ .startsWith("/")) {
                            var10_26 = var0.getFilesDir().getAbsolutePath().concat("/" + var2_10 /* !! */ );
                        }
                        var0 = new File((String)var10_26);
                        FileUtil.deleteFile((File)var0);
                        FileUtil.ensurePathExists(var0.getParentFile());
                        var0 = new FileOutputStream((String)var10_26, false);
                        if (var1_8 == null) ** GOTO lbl58
                        var2_11 = new byte[1024];
                        var5_30 = 0;
lbl55: // 2 sources:
                        if ((var6_31 = var1_8.read(var2_11)) > 0) break block48;
                        if (aux.a) {
                            Log.i((String)bl.a, (String)("downloaded byte = " + var5_30));
                        }
lbl58: // 4 sources:
                        var0.flush();
                        var2_13 = var13_25.getStatusLine();
                        var5_30 = var2_13.getStatusCode();
                        if (!aux.a) break block49;
                        Log.i((String)bl.a, (String)("statusCode = " + var5_30 + " " + var2_13.getReasonPhrase()));
                        Log.i((String)bl.a, (String)("length = " + var8_27));
                        break block49;
lbl65: // 1 sources:
                        if (aux.a) {
                            Log.d((String)bl.a, (String)"downloadFile success!");
                        }
                        if (var0 == null) ** GOTO lbl70
                        var0.close();
lbl70: // 2 sources:
                        if (var1_8 != null) {
                            var1_8.close();
                        }
lbl72: // 4 sources:
                        do {
                            return true;
                            break;
                        } while (true);
                    }
                    var0.write(var2_11, 0, var6_31);
                    var5_30 += var6_31;
                    ** GOTO lbl55
lbl80: // 2 sources:
                    if (aux.a) {
                        Log.w((String)bl.a, (String)"downloadFile failed!");
                    }
                    FileUtil.deleteFile((String)var10_26);
                    if (var0 == null) ** GOTO lbl86
                    try {
                        var0.close();
lbl86: // 2 sources:
                        if (var1_8 == null) ** GOTO lbl13
                        var1_8.close();
                        return false;
                    }
                    catch (Exception var0_1) {
                        return false;
                    }
                    catch (Exception var2_14) {
                        var0 = null;
                        var10_26 = var12_23;
lbl95: // 5 sources:
                        if (aux.a) {
                            var2_15.printStackTrace();
                            Log.w((String)bl.a, (String)("downloadFile Exception : " + var2_15.getMessage()));
                        }
                        if (var10_26 == null) ** GOTO lbl101
                        try {
                            var10_26.close();
lbl101: // 2 sources:
                            if (var0 == null) ** continue;
                            var0.close();
                            return false;
                        }
                        catch (Exception var0_2) {
                            return false;
                        }
                    }
                    catch (Throwable var0_3) {
                        var1_8 = null;
                        var10_26 = var11_22;
lbl109: // 5 sources:
                        do {
                            block50 : {
                                if (var10_26 == null) ** GOTO lbl113
                                try {
                                    var10_26.close();
lbl113: // 2 sources:
                                    if (var1_8 == null) break block50;
                                    var1_8.close();
                                }
                                catch (Exception var1_9) {
                                    ** continue;
                                }
                            }
lbl118: // 2 sources:
                            do {
                                throw var0;
                                break;
                            } while (true);
                            break;
                        } while (true);
                    }
                    catch (Throwable var0_4) {
                        var10_26 = var11_22;
                        ** GOTO lbl109
                    }
                    catch (Throwable var0_5) {
                        var10_26 = var11_22;
                        ** GOTO lbl109
                    }
                    catch (Throwable var2_16) {
                        var10_26 = var0;
                        var0 = var2_16;
                        ** GOTO lbl109
                    }
                    {
                        catch (Throwable var2_17) {
                            var1_8 = var0;
                            var0 = var2_17;
                            ** continue;
                        }
                    }
                    catch (Exception var2_18) {
                        var10_26 = var12_23;
                        var0 = var1_8;
                        ** GOTO lbl95
                    }
                    catch (Exception var2_19) {
                        var0 = var1_8;
                        var10_26 = var12_23;
                        ** GOTO lbl95
                    }
                    catch (Exception var2_20) {
                        var10_26 = var0;
                        var0 = var1_8;
                        ** GOTO lbl95
                    }
                    catch (Exception var0_6) {
                        ** continue;
                    }
                    catch (Exception var0_7) {
                        return false;
                    }
                }
                var4_29 = false;
                ** GOTO lbl44
            }
            var3_28 = false;
            ** GOTO lbl40
        }
        if (var5_30 != 200 || var8_27 <= 0 && !var3_28 && !var4_29) ** GOTO lbl80
        ** GOTO lbl65
    }
}

