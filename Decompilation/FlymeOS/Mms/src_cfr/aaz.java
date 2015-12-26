/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.Intent
 *  android.net.Uri
 *  android.text.SpannableStringBuilder
 *  android.text.TextUtils
 *  android.text.style.ClickableSpan
 *  android.text.style.URLSpan
 *  android.view.View
 *  java.lang.Class
 *  java.lang.Object
 *  java.lang.String
 *  java.util.regex.Matcher
 *  java.util.regex.Pattern
 */
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ClickableSpan;
import android.text.style.URLSpan;
import android.view.View;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class aaz {
    private static aaz a;
    private final Pattern b = Pattern.compile((String)"@[\\u4e00-\\u9fa5\\w\\-]+");
    private final Pattern c = Pattern.compile((String)"#([^\\#|.]+)#");
    private final Pattern d = Pattern.compile((String)"http://t\\.cn/[a-zA-Z0-9\\-_]+");

    public static aaz a() {
        if (a == null) {
            a = new aaz();
        }
        return a;
    }

    public static void a(SpannableStringBuilder spannableStringBuilder) {
        aaz.a().b(spannableStringBuilder);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static boolean a(URLSpan[] arruRLSpan, String string2, SpannableStringBuilder spannableStringBuilder) {
        if (arruRLSpan == null) {
            return false;
        }
        int n2 = 0;
        while (n2 < arruRLSpan.length) {
            if (arruRLSpan[n2].getURL().startsWith(string2)) {
                spannableStringBuilder.removeSpan((Object)arruRLSpan[n2]);
                return true;
            }
            ++n2;
        }
        return false;
    }

    public void b(SpannableStringBuilder spannableStringBuilder) {
        URLSpan[] arruRLSpan;
        Matcher matcher = this.b.matcher((CharSequence)spannableStringBuilder.toString());
        while (matcher.find()) {
            arruRLSpan = matcher.group();
            spannableStringBuilder.setSpan((Object)new a(arruRLSpan.substring(1, arruRLSpan.length())), matcher.start(), matcher.end(), 33);
        }
        matcher = this.c.matcher((CharSequence)spannableStringBuilder.toString());
        while (matcher.find()) {
            arruRLSpan = matcher.group();
            spannableStringBuilder.setSpan((Object)new b(arruRLSpan.substring(1, arruRLSpan.length() - 1)), matcher.start(), matcher.end(), 33);
        }
        matcher = this.d.matcher((CharSequence)spannableStringBuilder.toString());
        arruRLSpan = (URLSpan[])spannableStringBuilder.getSpans(0, spannableStringBuilder.length(), (Class)URLSpan.class);
        while (matcher.find()) {
            String string2 = matcher.group();
            aaz.a(arruRLSpan, string2, spannableStringBuilder);
            spannableStringBuilder.setSpan((Object)new URLSpan(string2), matcher.start(), matcher.end(), 33);
        }
    }

    class a
    extends c {
        public a(String string2) {
            super(string2);
        }

        private String a(String string2) {
            if (TextUtils.isEmpty((CharSequence)string2)) {
                return "@";
            }
            return "@" + string2;
        }

        @Override
        void a(View view, String string2) {
            Intent intent = new Intent("com.meizu.weibo.action.TIMELINE");
            intent.putExtra("sns_name", this.a(string2));
            view.getContext().startActivity(intent);
        }
    }

    class b
    extends c {
        public b(String string2) {
            super(string2);
        }

        @Override
        void a(View view, String string2) {
            string2 = new Intent("android.intent.action.VIEW", Uri.parse((String)("http://huati.weibo.com/k/" + string2)));
            view.getContext().startActivity((Intent)string2);
        }
    }

    abstract class c
    extends ClickableSpan {
        private String a;

        public c(String string2) {
            this.a = string2;
        }

        abstract void a(View var1, String var2);

        public void onClick(View view) {
            this.a(view, this.a);
        }
    }

}

