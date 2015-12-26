/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Boolean
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 *  java.util.HashMap
 *  java.util.regex.Matcher
 *  java.util.regex.Pattern
 */
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ata {
    public ata() {
        atb.a();
    }

    /*
     * Enabled aggressive block sorting
     */
    private asu a(Matcher matcher, ast ast2, String string2, Map<Integer, List<asu>> map, String string3) {
        asu asu2 = new asu();
        asu2.a(string3);
        asu2.b(this.a(string2, matcher.group(0)));
        if (ast2.c().booleanValue()) {
            asu2.c(matcher.group(ast2.i().intValue()));
        } else {
            asu2.c("\u56de\u590d" + asu2.a());
        }
        if (ast2.b().booleanValue()) {
            asu2.d(matcher.group(ast2.i().intValue()));
        }
        asu2.a(ast2.g());
        asu2.a(ast2.e());
        this.a(ast2, map, matcher.start(), asu2);
        return asu2;
    }

    /*
     * Enabled aggressive block sorting
     */
    private String a(String string2, String string3) {
        if (string2.contains((CharSequence)string3) || !(string2 = Pattern.compile((String)string3.replaceAll(",|\\.|/|\"|\\[|\\]|;|'|<|>|\\(|\\)|:|\\{|\\}|\\+|\\*", "[\uff0c\u3002\u3001\u201d\u201c\u3010\u3011\uff1b\u2018\u2019\u300a\u300b\uff08\uff09\uff1a\uff0f/\\{\\}\\+\\*]")).matcher((CharSequence)string2)).find()) {
            return string3;
        }
        return string2.group();
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private void a(ast list, Map<Integer, List<asu>> map, Integer n2, asu asu2) {
        if (list.f() != null && !list.f().b(asu2.a())) {
            return;
        }
        if (list.f() != null) {
            list.f().a(asu2);
        }
        if (!map.containsKey((Object)n2)) {
            list = new ArrayList();
            list.add(asu2);
            map.put(n2, list);
            return;
        }
        if (asu2.d() > map.get((Object)n2).get(0).d()) {
            list = map.get((Object)n2);
            list.clear();
            list.add(asu2);
            map.put(n2, list);
        }
        if (asu2.d() != map.get((Object)n2).get(0).d()) return;
        map.get((Object)n2).add(asu2);
    }

    public Set<asu> a(String object) {
        TreeSet<asu> treeSet = new TreeSet<asu>();
        HashMap hashMap = new HashMap();
        String string2 = atc.a((String)object);
        for (ast ast2 : atb.a) {
            if (ast2.f() != null && !ast2.f().a(string2)) continue;
            Matcher matcher = Pattern.compile((String)ast2.d()).matcher((CharSequence)string2);
            while (matcher.find()) {
                if (ast2.a().booleanValue()) {
                    for (int i2 = 1; i2 <= matcher.groupCount(); ++i2) {
                        String string3 = matcher.group(i2);
                        if (string3 == null) continue;
                        this.a(matcher, ast2, (String)object, (Map<Integer, List<asu>>)hashMap, string3);
                    }
                    continue;
                }
                this.a(matcher, ast2, (String)object, (Map<Integer, List<asu>>)hashMap, matcher.group(ast2.h().intValue()));
            }
        }
        object = hashMap.values().iterator();
        while (object.hasNext()) {
            treeSet.addAll((Collection)object.next());
        }
        return treeSet;
    }
}

