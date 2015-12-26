/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Boolean
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.util.HashSet
 */
import java.util.HashSet;
import java.util.Set;

public class atb {
    public static Set<ast> a = new HashSet();

    public static void a() {
        ast ast2 = new ast();
        ast2.a("(?:\u56de\u590d)(?:\u77ed\u4fe1)?(?:\u6307\u4ee4)?([\"'])([^#-]+?)\\1");
        ast2.a(5);
        ast2.b(2);
        ast2.a(new asw());
        ast ast3 = new ast();
        ast3.a("(?:\u56de\u590d)(?:[\"'])([0-9]+-[0-9]+)(?:[\"'])");
        ast3.d(true);
        ast3.a(5);
        ast3.b(1);
        ast ast4 = new ast();
        ast4.a("(?:\u56de\u590d)(?: )?(?:[\"'])(.+?#.+?)(?:[\"'])");
        ast4.a(5);
        ast4.b(1);
        ast4.d(true);
        ast ast5 = new ast();
        ast5.a("(?:\u56de\u590d|(?<=[\u8bf7,;.!\\[\\]]|\u9000\u8ba2)\u56de)(?:\u77ed\u4fe1)?(?:\u6307\u4ee4|\u6570\u5b57|\u5b57\u6bcd)?(?: *)([a-zA-Z0-9\u4f4d]+)(?: *)?");
        ast5.a(4);
        ast5.b(1);
        ast ast6 = new ast();
        ast6.a("(?:\u56de\u590d|\u56de)?(@5TD|TD|T)\u9000\u8ba2");
        ast6.b(1);
        ast6.a(5);
        ast ast7 = new ast();
        ast7.a("(?:\u56de\u590d)(?:\u6570\u5b57)?([0-9]{1,2}[\\-\u5230][0-9]{1,2})");
        ast7.d(true);
        ast7.a(5);
        ast7.b(1);
        ast ast8 = new ast();
        ast8.a("(?:\u56de\u590d)([a-zA-Z0-9]+#[a-zA-Z0-9]+)");
        ast8.b(1);
        ast8.a(5);
        ast8.d(true);
        ast ast9 = new ast();
        ast9.a("(?:\u56de\u590d)(?::)?([^\"]{1,6}[#\\+][^,.;\"]+)");
        ast9.b(1);
        ast9.a(6);
        ast9.d(true);
        ast ast10 = new ast();
        ast10.a("(?:\u56de\u590d|\u56de)([a-zA-Z0-9]+)(?:[/\\.])([a-zA-Z0-9]+)(?:[/\\.])([a-zA-Z0-9]+)(?:[/\\.])?([a-zA-Z0-9]+)?");
        ast10.a(6);
        ast10.b(1);
        ast10.a(true);
        ast ast11 = new ast();
        ast11.a("(?:\u53d1\u77ed\u4fe1)(?:\")?([a-zA-Z0-9]+)(?:[\u5230\u81f3])([0-9]{4,})(?:\")?");
        ast11.d(true);
        ast11.a(5);
        ast11.b(true);
        ast11.b(1);
        ast11.c(2);
        ast ast12 = new ast();
        ast12.a("(?:\u7f16\u8f91|\u53d1\u9001|\u56de\u590d|\u53d1)(?: )?(?:\u514d\u8d39)?(?:\u6307\u4ee4|\u77ed\u4fe1|\u6c49\u5b57|\u6570\u5b57|\u5b57\u6bcd|\u5185\u5bb9)?(?:\u6a21\u677f)?(?:\u514d\u8d39)?(?:\u53d1\u9001)?(?::)?(?:\u6570\u5b57)?(?:\")?([^.,;]*?)(?:\")?(?: )?(?:\u514d\u8d39)?(?:\u53d1)?(?:\u9001)?(?:\u5230|\u81f3)(?: *)(?:\")?([0-9]{4,})");
        ast12.d(true);
        ast12.a(6);
        ast12.b(true);
        ast12.b(1);
        ast12.c(2);
        ast12.a(new asy());
        ast ast13 = new ast();
        ast13.a("(?<=[:\\?;!.,;?@\\r\\n\\t\\] ]|\u5e8f\u53f7|^)(?:\")?([0-9]+ *|[A-Z])([:\\./ \u4e3a\u662f\u5bf9\uff0d\\-\"])(?![0-9]+[:|.|,|)| |\\-|\u5728|M|\u5143|\u5e74|\u5c0f\u65f6|\u6298])(?:[ \\[]?)([^;()\\r\\n\\t\\.?\\[, ]+)");
        ast13.a(new asz());
        ast13.c(true);
        ast13.b(1);
        ast13.c(3);
        ast13.a(6);
        ast ast14 = new ast();
        ast14.a("(?:\u56de\u590d)?([0-9]{1,2})(?:\u53ca\u4ee5\u4e0b)?(?:\u4e3a|\u5bf9)([^,.;]+)");
        ast14.a(new asx());
        ast14.b(1);
        ast14.c(2);
        ast14.a(7);
        ast14.c(true);
        a.add(ast11);
        a.add(ast12);
        a.add(ast13);
        a.add(ast14);
        a.add(ast2);
        a.add(ast3);
        a.add(ast7);
        a.add(ast10);
        a.add(ast5);
        a.add(ast6);
        a.add(ast4);
        a.add(ast8);
        a.add(ast9);
    }
}

