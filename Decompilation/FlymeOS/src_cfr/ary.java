/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Object
 *  java.lang.String
 *  java.util.regex.Matcher
 *  java.util.regex.Pattern
 */
import com.ted.android.contacts.common.DataBus;
import com.ted.android.data.BubbleEntity;
import com.ted.android.data.bubbleAction.ActionBase;
import com.ted.android.data.bubbleAction.DateReminderAction;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ary
extends aqm {
    protected static final Pattern c = Pattern.compile((String)anv.b("92165915dda3bda99c0901c152811bf11b691aa2f9c5c28239db7c69f3d418f5a281a2bc3377ce90538fa9ecf6c576e4261bde7939c718ca612bc78214bda7d87cfe525dcd70a26712bc52e1a1ee37a29825ddf26bcbaa85c5ef89e12b0b02a57f60a7dc4b0ac8ebffd09100001682d7d3d93c801a55a9f9e02e0d32710c165f735cc67eae6d8e50e34400dc03e958989cf2d295cf9924bb4d7fa87fb1b27d0258cde88a2a2c6850325b15c23a8cf697555384d7923b03d34403d7328471e01bd406b887424e73107b6dc52c5d863a4e7ace3c6475271a85171eb08ea541c8e65bf3a21fd055ddded2bcb5f9de4787a9ac723f35a9819d851634394a1805dbfbd1fa62982158f45fe7fd9f2b7b996bea82b1c798e3810950400edcd0003ec48139363dd6b09bb62e0fe7f7cbf8d8563f70cedb226ca545dd34ecad41086fe1bd23dd141cf63136e5fd4d57d744689355cd26f20409631c5d0d6b5130f9d7a1e1b977a3e78bac5d5d4c307510ea3c2cbf2c676b255f32c32c420e60bd96634d9a265eecb569b5b0b40a166e367c74c6868c4948ddc55cb3497f45a81cad57662ea02afea667cdb617d49514a10e636c958b545ab6971fcae55e660adc235a2e1e1127db80bb6ad873fd5203e91b4d95454901189445151018f1f0820e686faccf69479b912f7c71a5", DataBus.FILE_MASK));

    @Override
    public String a(String string2, List<BubbleEntity> list) {
        Matcher matcher = c.matcher((CharSequence)string2);
        while (matcher.find()) {
            String string3;
            if (matcher.groupCount() < 10 || this.a(string3 = matcher.group(1))) continue;
            Object object = new a();
            object.a = matcher.group(2);
            object.b = matcher.group(3);
            object.c = matcher.group(4);
            if (ary.b(object.b)) {
                object.b = matcher.group(5);
            }
            if (ary.b(object.c)) {
                object.c = matcher.group(6);
            }
            object.d = matcher.group(8);
            object.e = matcher.group(9);
            if ((object = object.a(this.a)) == null) continue;
            BubbleEntity bubbleEntity = this.a();
            bubbleEntity.setMatchedWords(string3);
            object.setParent(bubbleEntity);
            object.setBody(this.b);
            bubbleEntity.addAction((ActionBase)object);
            list.add(bubbleEntity);
            string2 = string2.replace((CharSequence)string3, (CharSequence)"");
        }
        return string2;
    }

    static class a {
        String a;
        String b;
        String c;
        String d;
        String e;

        a() {
        }

        /*
         * Enabled aggressive block sorting
         */
        public DateReminderAction a(long l2) {
            long l3;
            boolean bl2;
            if (ary.b(this.a)) {
                return null;
            }
            l2 = aqn.a(aqn.b(l2), aqn.c(l2), aqn.d(l2));
            if (!ary.b(this.b)) {
                int n2 = aqn.a(this.b, this.a);
                int n3 = 0;
                if (!ary.b(this.c)) {
                    n3 = aqn.a(this.c);
                }
                int n4 = !ary.b(this.d) ? aqn.a(this.d, this.a) : n2;
                int n5 = !ary.b(this.e) ? aqn.a(this.e) : n3;
                long l4 = n2;
                long l5 = n3;
                l3 = (long)n4 * 3600000 + l2 + (long)n5 * 60000;
                l2 = l4 * 3600000 + l2 + l5 * 60000;
                bl2 = false;
            } else {
                bl2 = true;
                l3 = l2;
            }
            DateReminderAction dateReminderAction = new DateReminderAction(null);
            dateReminderAction.isAllDay = bl2;
            dateReminderAction.startTime = l2;
            dateReminderAction.endTime = l3;
            return dateReminderAction;
        }
    }

}

