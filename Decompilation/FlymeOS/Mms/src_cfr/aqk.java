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

public class aqk
extends aqc {
    private static final Pattern a = Pattern.compile((String)anv.b("b6ef42e4e43cbb857944bbe0a3243c8873bf3e0c73b1cd46522da3410bbf741e0dfa48daf0e266b37944bbe0a3243c8873bf3e0c73b1cd464afb35d1d22b43bf482dc58f73e65707a638a809dd8ebf359ec7ce07fa80f11dd70f23e529c83ddf37800e04fcdaa8d77cd052fda24f7499b0d05454a20fbc269543b7b07be6e02945c070658705ea3f2f6e723d48b1bfe4f7ff1c6fcc05a37e5dcc871d93ae984fd23b5de09162cd8b6afcf462a097a82c5dcc871d93ae984fd23b5de09162cd8be7a981ef5915db9ed4f0277f81232e931debe01091b5b4c58d11d11f00d891653eda76a75e7fc5e4a2f20cb4b71518cd006164ca87877192513aa658548df80b9d8cb506fe15188037800e04fcdaa8d738004870d25f4e8ee73afa11f955a3e64e744aa65d320ea81debe01091b5b4c5b027bd8bfd4c3fed27a89663e8ee52142c2c4a5473a7df950dece3903cd418d858496983ad1f53234133399861f8c95b922d6b3a99ec7cb4a0e46873d762d24344ad166bfc685359", DataBus.FILE_MASK));
    private static aqk b;

    static {
        ash ash2 = new ash();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("url:").append("\n").append(anv.a("(?<=^|[^0-9a-zA-Z\\-_/\\?=&@\\*%\\.]|(?:^|[^0-9a-zA-Z\\-_/\\?=&@\\*%])\\.)(?:(?i:https?)://)?(?:(?:(?:[0-9a-zA-Z\\-_]+)(?:(?:(?:\\.[0-9a-zA-Z\\-_]+)+(?::\\d{2,5})?/[0-9a-zA-Z\\-_/\\?=&\\.%,]*[0-9a-zA-Z\\-_/\\?=&])|(?:(?:\\.[0-9a-zA-Z\\-_]+)*\\.(?i:com|cn|gov|net|org|edu|cc)/?(?=$|[^0-9a-zA-Z\\-_/\\?=&\\.%]|\\.(?:$|[^0-9a-zA-Z\\-_/\\?=&])))))|(?:(?i:www)\\.[0-9a-zA-Z\\-_/\\?=&\\.%]*[0-9a-zA-Z\\-_/\\?=&]))", DataBus.FILE_MASK)).append("\n");
        ash2.a("regex.txt", stringBuilder.toString());
    }

    private aqk() {
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static aqk a() {
        if (b == null) {
            synchronized (aqk.class) {
                b = new aqk();
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
            bubbleEntity.setId("-7");
            bubbleEntity.setMatchedWords(string2.group(0));
            bubbleEntity.setIndex(string2.start(0));
            bubbleEntity.setShowType(2);
            CommonAction commonAction = new CommonAction(bubbleEntity);
            commonAction.showType = 2;
            commonAction.buttonText = "\u6253\u5f00\u94fe\u63a5";
            commonAction.action = 3;
            commonAction.url = string2.group(0);
            bubbleEntity.addAction(commonAction);
            string3.add(bubbleEntity);
        }
        return string3;
    }
}

