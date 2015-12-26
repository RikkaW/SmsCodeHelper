/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.util.Hashtable
 *  java.util.Map$Entry
 */
package com.android.mms.jwap_port;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class PublicIdentifiers {
    private static PublicIdentifiers instance;
    private Hashtable publicIdentifiers;
    private Hashtable systemIdentifiers;

    private PublicIdentifiers() {
        this.initialize();
    }

    public static PublicIdentifiers getInstance() {
        if (instance == null) {
            instance = new PublicIdentifiers();
        }
        return instance;
    }

    private String getKeyFromValue(Iterator iterator, String string) {
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry)iterator.next();
            if (!entry.getValue().toString().equalsIgnoreCase(string)) continue;
            return entry.getKey().toString();
        }
        return "1";
    }

    private void initialize() {
        this.publicIdentifiers = new Hashtable();
        this.publicIdentifiers.put((Object)"0", (Object)"STRING_TABLE");
        this.publicIdentifiers.put((Object)"1", (Object)"UNKNOWN");
        this.publicIdentifiers.put((Object)"2", (Object)"-//WAPFORUM//DTD WML 1.0//EN");
        this.publicIdentifiers.put((Object)"3", (Object)"-//WAPFORUM//DTD WTA 1.0//EN");
        this.publicIdentifiers.put((Object)"4", (Object)"-//WAPFORUM//DTD WML 1.1//EN");
        this.publicIdentifiers.put((Object)"5", (Object)"-//WAPFORUM//DTD SI 1.0//EN");
        this.publicIdentifiers.put((Object)"6", (Object)"-//WAPFORUM//DTD SL 1.0//EN");
        this.publicIdentifiers.put((Object)"7", (Object)"-//WAPFORUM//DTD CO 1.0//EN");
        this.publicIdentifiers.put((Object)"8", (Object)"-//WAPFORUM//DTD CHANNEL 1.1//EN");
        this.publicIdentifiers.put((Object)"9", (Object)"-//WAPFORUM//DTD WML 1.2//EN");
        this.publicIdentifiers.put((Object)"A", (Object)"-//WAPFORUM//DTD WML 1.3//EN");
        this.publicIdentifiers.put((Object)"B", (Object)"-//WAPFORUM//DTD PROV 1.0//EN");
        this.publicIdentifiers.put((Object)"C", (Object)"-//WAPFORUM//DTD WTA-WML 1.2//EN");
        this.publicIdentifiers.put((Object)"D", (Object)"-//WAPFORUM//DTD CHANNEL 1.2//EN");
        this.publicIdentifiers.put((Object)"E", (Object)"-//OMA//DTD DRMREL 1.0//EN");
        this.publicIdentifiers.put((Object)"1100", (Object)"-//PHONE.COM//DTD ALERT 1.0//EN");
        this.publicIdentifiers.put((Object)"FD1", (Object)"-//SYNCML//DTD SyncML 1.0//EN");
        this.publicIdentifiers.put((Object)"FD2", (Object)"-//SYNCML//DTD DevInf 1.0//EN");
        this.publicIdentifiers.put((Object)"FD3", (Object)"-//SYNCML//DTD SyncML 1.1//EN");
        this.publicIdentifiers.put((Object)"FD4", (Object)"-//SYNCML//DTD DevInf 1.1//EN");
        this.systemIdentifiers = new Hashtable();
        this.systemIdentifiers.put((Object)"STRING_TABLE", (Object)"");
        this.systemIdentifiers.put((Object)"UNKNOWN", (Object)"");
        this.systemIdentifiers.put((Object)"-//WAPFORUM//DTD WML 1.0//EN", (Object)"");
        this.systemIdentifiers.put((Object)"-//WAPFORUM//DTD WTA 1.0//EN", (Object)"");
        this.systemIdentifiers.put((Object)"-//WAPFORUM//DTD WML 1.1//EN", (Object)"http://www.wapforum.org/DTD/wml_1_1.dtd");
        this.systemIdentifiers.put((Object)"-//WAPFORUM//DTD SI 1.0//EN", (Object)"http://www.wapforum.org/DTD/si.dtd");
        this.systemIdentifiers.put((Object)"-//WAPFORUM//DTD SL 1.0//EN", (Object)"http://www.wapforum.org/DTD/sl.dtd");
        this.systemIdentifiers.put((Object)"-//WAPFORUM//DTD CO 1.0//EN", (Object)"");
        this.systemIdentifiers.put((Object)"-//WAPFORUM//DTD CHANNEL 1.1//EN", (Object)"");
        this.systemIdentifiers.put((Object)"-//WAPFORUM//DTD WML 1.2//EN", (Object)"http://www.wapforum.org/DTD/wml12.dtd");
        this.systemIdentifiers.put((Object)"-//WAPFORUM//DTD WML 1.3//EN", (Object)"http://www.wapforum.org/DTD/wml13.dtd");
        this.systemIdentifiers.put((Object)"-//WAPFORUM//DTD PROV 1.0//EN", (Object)"http://www.wapforum.org/DTD/prov.dtd");
        this.systemIdentifiers.put((Object)"-//WAPFORUM//DTD WTA-WML 1.2//EN", (Object)"http://www.wapforum.org/DTD/wta-wml12.dtd");
        this.systemIdentifiers.put((Object)"-//WAPFORUM//DTD CHANNEL 1.2//EN", (Object)"http://www.wapforum.org/DTD/channel12.dtd");
        this.systemIdentifiers.put((Object)"-//OMA//DTD DRMREL 1.0//EN", (Object)"");
        this.systemIdentifiers.put((Object)"-//PHONE.COM//DTD ALERT 1.0//EN", (Object)"");
    }

    public String getPublicIdentifier(int n) {
        String string;
        String string2 = Integer.toHexString((int)n).toUpperCase();
        string2 = string = (String)this.publicIdentifiers.get((Object)string2);
        if (string == null) {
            string2 = (String)this.publicIdentifiers.get((Object)"1");
        }
        return string2;
    }

    public String getPublicIdentifierValueHex(String string) {
        return this.getKeyFromValue(this.publicIdentifiers.entrySet().iterator(), string);
    }

    public String getSystemIdentifier(String string) {
        return (String)this.systemIdentifiers.get((Object)string);
    }
}

