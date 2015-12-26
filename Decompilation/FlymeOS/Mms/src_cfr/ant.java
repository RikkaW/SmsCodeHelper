/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Boolean
 *  java.lang.Object
 *  java.lang.String
 */
import com.ted.android.contacts.common.DataBus;

public class ant {
    /*
     * Enabled aggressive block sorting
     */
    public static Boolean a(String string2) {
        boolean bl2 = string2.indexOf("teddymobile.cn") > -1;
        if (string2.indexOf("j.tdbear.cn") <= -1) return true;
        return true;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static String a(String string2, String string3, String string4) {
        Object object = string2;
        if (ant.a(string2) == false) return object;
        try {
            object = new anx(string2);
            if (!(object.a("lat") && object.a("lng") && object.c("lat") && object.c("lng"))) {
                object.a("lat", string3);
                object.a("lng", string4);
            }
            object.a("tdbear_id", DataBus.DID);
            object.a("tdbear_ch", String.valueOf((int)DataBus.CID));
            return object.a();
        }
        catch (Exception exception) {
            exception.printStackTrace();
            return string2;
        }
    }
}

