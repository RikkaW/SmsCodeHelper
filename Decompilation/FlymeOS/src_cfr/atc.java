/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Character
 *  java.lang.Object
 *  java.lang.String
 *  java.util.HashMap
 */
import java.util.HashMap;
import java.util.Map;

public class atc {
    public static Map<Character, Character> a = new HashMap();

    static {
        a.put(Character.valueOf((char)'\uff0c'), Character.valueOf((char)','));
        a.put(Character.valueOf((char)'\u3002'), Character.valueOf((char)'.'));
        a.put(Character.valueOf((char)'\u201d'), Character.valueOf((char)'\"'));
        a.put(Character.valueOf((char)'\u201c'), Character.valueOf((char)'\"'));
        a.put(Character.valueOf((char)'\uff1b'), Character.valueOf((char)';'));
        a.put(Character.valueOf((char)'\uff1a'), Character.valueOf((char)':'));
        a.put(Character.valueOf((char)'\u3001'), Character.valueOf((char)'/'));
        a.put(Character.valueOf((char)'\uff1f'), Character.valueOf((char)'?'));
        a.put(Character.valueOf((char)'\uff01'), Character.valueOf((char)'!'));
        a.put(Character.valueOf((char)'\uff08'), Character.valueOf((char)'('));
        a.put(Character.valueOf((char)'\uff09'), Character.valueOf((char)')'));
        a.put(Character.valueOf((char)'\u2018'), Character.valueOf((char)'\''));
        a.put(Character.valueOf((char)'\u2019'), Character.valueOf((char)'\''));
        a.put(Character.valueOf((char)'\u300a'), Character.valueOf((char)'<'));
        a.put(Character.valueOf((char)'\u300b'), Character.valueOf((char)'>'));
        a.put(Character.valueOf((char)'\uff03'), Character.valueOf((char)'#'));
        a.put(Character.valueOf((char)'\uff0b'), Character.valueOf((char)'+'));
        a.put(Character.valueOf((char)'\u3010'), Character.valueOf((char)'['));
        a.put(Character.valueOf((char)'\u3011'), Character.valueOf((char)']'));
        a.put(Character.valueOf((char)'\u2014'), Character.valueOf((char)'-'));
    }

    /*
     * Enabled aggressive block sorting
     */
    public static String a(String string2) {
        string2 = ate.a(string2.replaceAll("(?<=^|[^0-9a-zA-Z\\-_/?=&\\.@\\*]|(?:^|[^0-9a-zA-Z\\-_/?=&@\\*])\\.)(?:https?://)?(?:(?:(?:[0-9a-zA-Z\\-_]+)(?:(?:(?:\\.[0-9a-zA-Z\\-_]+)+(?::\\d{1,5})?/[0-9a-zA-Z\\-_/?=&\\.]*[0-9a-zA-Z\\-_/?=&])|(?:(?:\\.[0-9a-zA-Z\\-_]+)*\\.(?:com|cn|gov|net|org|edu|cc)/?(?=$|[^0-9a-zA-Z\\-_/?=&\\.]|\\.(?:$|[^0-9a-zA-Z\\-_/?=&])))))|(?:www\\.[0-9a-zA-Z\\-_/?=&\\.]*[0-9a-zA-Z\\-_/?=&]))", "")).replaceAll(" +", " ");
        char[] arrc = new char[string2.length()];
        int n2 = 0;
        while (n2 < string2.length()) {
            Character c2 = Character.valueOf((char)string2.charAt(n2));
            Character c3 = a.get((Object)c2);
            arrc[n2] = c3 != null ? c3.charValue() : c2.charValue();
            ++n2;
        }
        return new String(arrc).replaceAll(".*\u8bf7\u76f4\u63a5\u56de\u590d\u5e8f\u53f7", "\u56de\u590d\u5e8f\u53f7").replaceAll("(?![0-9])[0-2]?[0-9]:[0-6](?<![0-9])", " ").replaceAll("\"10\"\u5206\u624d\u662f\u6ee1\u610f", "").replaceAll("\u56de\u590d\u514d\u8d39", "").replaceAll("(?<=[\\u4E00-\\u9FFF\\)](?<!\u56de\u590d))(\\d{1,4}[\\.:\u4e3a/ ])(?!$)", " $1");
    }
}

