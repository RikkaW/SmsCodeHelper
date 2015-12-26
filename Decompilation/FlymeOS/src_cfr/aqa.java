/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.String
 *  java.util.ArrayList
 *  java.util.regex.Matcher
 *  java.util.regex.Pattern
 */
import com.ted.android.contacts.common.DataBus;
import com.ted.android.data.BubbleEntity;
import com.ted.android.data.bubbleAction.ActionBase;
import com.ted.android.data.bubbleAction.CommonAction;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class aqa
extends aqc {
    private static final Pattern a = Pattern.compile((String)anv.b("f5f1bd25d46e755118708f42cdf0655cf8bd061e5afee12f118ae26d076f9e03f5f1bd25d46e7551350c7ada387309c9e78b62a2ce9fb28a265efd853e75322395ab322304fd867e1d2b35d5a61673f7ef07fcf00a43626c64197971969d530c84908bd2dad73086", DataBus.FILE_MASK));
    private static aqa b;

    private aqa() {
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static aqa a() {
        if (b == null) {
            synchronized (aqa.class) {
                b = new aqa();
            }
        }
        return b;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    @Override
    protected List<BubbleEntity> a(String string2, String string3) {
        string3 = new ArrayList();
        if (string2 == null) return string3;
        string2 = a.matcher((CharSequence)string2);
        while (string2.find()) {
            BubbleEntity bubbleEntity = new BubbleEntity();
            bubbleEntity.setId("-8");
            bubbleEntity.setMatchedWords(string2.group(0));
            bubbleEntity.setIndex(string2.start(0));
            bubbleEntity.setShowType(2);
            CommonAction commonAction = new CommonAction(bubbleEntity);
            commonAction.action = 9;
            commonAction.showType = 2;
            commonAction.buttonText = "\u53d1\u9001\u90ae\u4ef6";
            commonAction.url = string2.group(0);
            bubbleEntity.addAction(commonAction);
            string3.add(bubbleEntity);
        }
        return string3;
    }
}

