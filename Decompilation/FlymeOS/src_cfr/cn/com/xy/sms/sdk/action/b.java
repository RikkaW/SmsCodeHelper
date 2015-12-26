/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.io.BufferedReader
 *  java.io.InputStreamReader
 *  java.io.OutputStream
 *  java.io.PrintWriter
 *  java.io.Reader
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.StringBuffer
 *  java.lang.Throwable
 *  java.net.HttpURLConnection
 *  java.net.URL
 *  java.net.URLConnection
 */
package cn.com.xy.sms.sdk.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.Callable;

final class b
implements Callable<String> {
    private final /* synthetic */ String a;

    b(String string2) {
        this.a = string2;
    }

    /*
     * Unable to fully structure code
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private String a() {
        var4_1 = null;
        var1_2 = (HttpURLConnection)new URL(this.a).openConnection();
        var1_2.setRequestProperty("accept", "*/*");
        var1_2.setRequestProperty("connection", "Keep-Alive");
        var1_2.setRequestProperty("user-agent", "alitester");
        var1_2.setRequestMethod("GET");
        var1_2.setConnectTimeout(5000);
        var1_2.setReadTimeout(5000);
        var1_2.setDoOutput(true);
        var1_2.setDoInput(true);
        var2_9 = new PrintWriter(var1_2.getOutputStream());
        var2_9.flush();
        var4_1 = var1_2 = new BufferedReader((Reader)new InputStreamReader(var1_2.getInputStream()));
        var3_11 = var2_9;
        try {
            var5_12 = new StringBuffer();
        }
        catch (Throwable var5_13) lbl-1000: // 3 sources:
        {
            block28 : {
                var4_1 = var1_2;
                var3_11 = var2_9;
                var5_14.printStackTrace();
                if (var2_9 == null) break block28;
                var2_9.close();
            }
            if (var1_2 != null) {
                var1_2.close();
            }
            do {
                return null;
                break;
            } while (true);
        }
        do {
            block27 : {
                var4_1 = var1_2;
                var3_11 = var2_9;
                var6_17 = var1_2.readLine();
                if (var6_17 != null) break block27;
                var4_1 = var1_2;
                var3_11 = var2_9;
                var5_12 = var5_12.toString();
                var2_9.close();
                var1_2.close();
                return var5_12;
            }
            var4_1 = var1_2;
            var3_11 = var2_9;
            var5_12.append(var6_17);
            continue;
            break;
        } while (true);
        catch (IOException var1_3) {
            var1_3.printStackTrace();
            return var5_12;
        }
        {
            catch (IOException var1_4) {
                var1_4.printStackTrace();
                return null;
            }
        }
        catch (Throwable var1_5) {
            var2_9 = null;
lbl60: // 3 sources:
            do {
                if (var2_9 != null) {
                    var2_9.close();
                }
                if (var4_1 != null) {
                    var4_1.close();
                }
                do {
                    throw var1_6;
                    break;
                } while (true);
                catch (IOException var2_10) {
                    var2_10.printStackTrace();
                    throw var1_6;
                }
                break;
            } while (true);
        }
        catch (Throwable var1_7) {
            ** GOTO lbl60
        }
        {
            catch (Throwable var1_8) {
                var2_9 = var3_11;
                ** continue;
            }
        }
        catch (Throwable var5_15) {
            var1_2 = null;
            var2_9 = null;
            ** GOTO lbl-1000
        }
        catch (Throwable var5_16) {
            var1_2 = null;
            ** GOTO lbl-1000
        }
    }

    @Override
    public final /* synthetic */ Object call() {
        return this.a();
    }
}

