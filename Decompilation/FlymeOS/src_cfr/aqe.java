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
import com.ted.android.core.ReplyMsgItem;
import com.ted.android.data.BubbleEntity;
import com.ted.android.data.bubbleAction.ActionBase;
import com.ted.android.data.bubbleAction.QuickReplyAction;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class aqe
extends aqc {
    private static final String a = aqe.class.getSimpleName();
    private static aqe b;
    private ata c = new ata();

    private aqe() {
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static aqe a() {
        if (b == null) {
            synchronized (aqe.class) {
                b = new aqe();
            }
        }
        return b;
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    protected List<BubbleEntity> a(String string2, String string3) {
        ArrayList arrayList = new ArrayList();
        if (TextUtils.isEmpty((CharSequence)string2)) {
            return arrayList;
        }
        Iterator<asu> iterator = this.c.a(string2).iterator();
        do {
            Object object;
            BubbleEntity bubbleEntity;
            boolean bl2;
            block8 : {
                QuickReplyAction quickReplyAction;
                if (!iterator.hasNext()) {
                    return arrayList;
                }
                object = iterator.next();
                List<ActionBase> list = arrayList.iterator();
                do {
                    if (!list.hasNext()) {
                        bl2 = false;
                        break block8;
                    }
                    bubbleEntity = (BubbleEntity)list.next();
                } while (!TextUtils.equals((CharSequence)object.b(), (CharSequence)bubbleEntity.getMatchedWords()));
                list = bubbleEntity.getActions();
                if (list != null && list.size() > 0) {
                    quickReplyAction = (QuickReplyAction)list.get(0);
                    ((ActionBase)list.get((int)0)).buttonText = quickReplyAction.getItem().description;
                }
                if ((list = ReplyMsgItem.fromReply((asu)object, string3)) != null) {
                    quickReplyAction = list.toAction(bubbleEntity);
                    quickReplyAction.buttonText = list.description;
                    bubbleEntity.addAction(quickReplyAction);
                    bl2 = true;
                } else {
                    bl2 = true;
                }
            }
            if (bl2) continue;
            bubbleEntity = new BubbleEntity();
            bubbleEntity.setId("-5");
            bubbleEntity.setMatchedWords(object.b());
            bubbleEntity.setIndex(string2.indexOf(object.b()));
            if ((object = ReplyMsgItem.fromReply((asu)object, string3)) == null) continue;
            bubbleEntity.addAction(object.toAction(bubbleEntity));
            arrayList.add(bubbleEntity);
        } while (true);
    }
}

