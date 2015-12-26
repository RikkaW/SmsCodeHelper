/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.text.TextUtils
 *  java.lang.Object
 *  java.lang.String
 *  java.util.regex.Pattern
 */
import android.text.TextUtils;
import com.ted.android.data.BubbleEntity;
import java.util.List;
import java.util.regex.Pattern;

abstract class aqm {
    private static final Pattern c = Pattern.compile((String)"[\\d\\-/]{1,5}");
    protected long a;
    protected String b;

    aqm() {
    }

    protected static boolean b(String string2) {
        if (TextUtils.isEmpty((CharSequence)string2) || "null".equals((Object)string2)) {
            return true;
        }
        return false;
    }

    public BubbleEntity a() {
        BubbleEntity bubbleEntity = new BubbleEntity();
        bubbleEntity.setId("-4");
        bubbleEntity.setShowType(2);
        return bubbleEntity;
    }

    public abstract String a(String var1, List<BubbleEntity> var2);

    public String a(String string2, List<BubbleEntity> list, long l2, String string3) {
        this.a = l2;
        this.b = string3;
        return this.a(string2, list);
    }

    protected boolean a(String string2) {
        if (string2.length() <= 3) {
            return true;
        }
        return false;
    }
}

