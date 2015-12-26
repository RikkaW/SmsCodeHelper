/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.text.TextUtils
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 */
import android.text.TextUtils;
import com.ted.android.data.BubbleEntity;
import com.ted.android.data.bubbleAction.ActionBase;
import com.ted.android.data.bubbleAction.CommonAction;
import com.ted.android.message.BubbleUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class aqb
extends aqc {
    private static final String a = aqb.class.getSimpleName();
    private static aqb b;

    private aqb() {
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static aqb a() {
        if (b == null) {
            synchronized (aqb.class) {
                b = new aqb();
            }
        }
        return b;
    }

    @Override
    protected List<BubbleEntity> a(String string2, String string3) {
        ArrayList arrayList = new ArrayList();
        List<String> list = BubbleUtils.getAddressFromSmsBodyRegex(string2);
        if (list == null || list.size() <= 0) {
            return arrayList;
        }
        list = list.iterator();
        while (list.hasNext()) {
            Object object = (String)list.next();
            if (TextUtils.isEmpty((CharSequence)object)) continue;
            BubbleEntity bubbleEntity = new BubbleEntity();
            bubbleEntity.setMatchedWords((String)object);
            bubbleEntity.setIndex(string2.indexOf((String)object));
            bubbleEntity.setId("-3");
            object = new CommonAction(bubbleEntity);
            object.icon = "http://img.teddymobile.cn/2015/01/31/1589eaf61e688465f85897fc29cadb52_60X60.png";
            object.number = string3;
            object.buttonText = "\u5730\u56fe\u5bfc\u822a";
            object.action = 10;
            bubbleEntity.addAction((ActionBase)object);
            arrayList.add(bubbleEntity);
        }
        return arrayList;
    }
}

