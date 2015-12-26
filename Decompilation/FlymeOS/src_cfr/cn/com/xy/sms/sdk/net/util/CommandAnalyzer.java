/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 *  java.util.regex.Matcher
 *  java.util.regex.Pattern
 */
package cn.com.xy.sms.sdk.net.util;

import cn.com.xy.sms.sdk.net.util.d;
import cn.com.xy.sms.sdk.net.util.e;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class CommandAnalyzer {
    private static String a = "#(\\d+)\\{([^}]+)\\}";
    private static Pattern b = Pattern.compile((String)"#(\\d+)\\{([^}]+)\\}");

    /*
     * Enabled aggressive block sorting
     */
    public static List<d> a(String string2) {
        if (string2 == null || string2.trim().length() == 0 || !(string2 = b.matcher((CharSequence)string2)).find()) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        do {
            String string3 = string2.group(1);
            String string4 = string2.group(2);
            arrayList.add(new d(Integer.valueOf((String)string3), string4));
        } while (string2.find());
        return arrayList;
    }

    private static void a() {
        Object object = CommandAnalyzer.a("#0{-noWait} #4{-noWait -wait=10} #10{-ids=1,2,3 -domain=http://bizport.cn/newservice} #11{-sql=asfa dfa sdff}");
        e e2 = new e();
        object = object.iterator();
        while (object.hasNext()) {
            e2.exeCmd((d)object.next());
        }
        return;
    }
}

