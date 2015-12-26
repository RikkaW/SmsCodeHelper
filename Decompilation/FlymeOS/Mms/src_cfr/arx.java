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

public class arx
extends aqm {
    protected static final Pattern c = Pattern.compile((String)anv.b("92165915dda3bda9414f4b337ccc71b1e1af98075190feb9665e319b65f52096e238de4b8662601a3ea8545bf706304c17b1fe2a749b70cd6a36e22c4364f40d28a4dd92a4ab2adc56b15beb153671cb420d8dea395f745cec7629ddfe16395f5b1fdaed5aaec04bc83cbd57920591d92df3bcdb38314cd0b19ae56f3a727f2a1719ee8045834ef5795ec049deea19687ab63c88d0a9dc2a665e319b65f52096d1ed68e0e9e69ecf00da8bb2ee35f56a5ef5ca4805e69d61c6d6a998ff77fb9003f99d9148aad8cfeba4e93cccc34f5c749e2c8e75af6337c17b9d04940a31f3cbcb97080c1b35140cd38f4c8b59e770042fc2ab733a3589d5bc8eecc0937212adedde332d5876b2eaa7deb1b8d56f2d43316165f21009d9f340facd0fdefc7ccce18e4eb3812db2199b3bf07ea18fffaae9d7f9c8e95505bb37238d8a58d90756a094f4a89612a2e69e69c91d8a43758c25a8f8a26c7ce484ff13c9218a3344b9357b765f2e24bab2a1ba70dd72df5c27e43f0d781267453bb9171705513993f449e14d31e823da70253e586577ae61a76cad7ac5f68e6dc47c035fb86a436c5a258d38f307f2cd05c2eefb35cb55a9ac3befe6477cadd309395bfbd8cc0a2455666b015e1ab0e24f305399d694624049a395af74220df2edff90512fa4b6a7316d380ace607494aebd81efc489479effa316d807d162096b39f7b18fee861e0159646e94a6789a795ec049deea196840154c182c1d084cfb211137152269827036b0e83f94bebcf0debb8850def2fbb001ae7e4d6fff89779c80126d199341de2239fac8b2a0ab5678e6acc9cc3ae7146fab27a3aac2bd21707d45766801ed3e5cb1933b460b219c2a4608a43181be4e6ad781f85b9cc188e8c95f794ec61ff57c6f53980546e5bea4e2c7599602b67ac7688727e4aa7f478d6222cb1ef432cf2bd8cdff5dfa3b7fe670a813ae91fc9e40dd9ed55097a2bd91f3794f61b1969825ddf26bcbaa85c5ef89e12b0b02a57f60a7dc4b0ac8ebffd09100001682d7d3d93c801a55a9f9e02e0d32710c165f735cc67eae6d8e50e34400dc03e958989cf2d295cf9924bb4d7fa87fb1b27d0258cde88a2a2c6850325b15c23a8cf697555384d7923b03d34403d7328471e01bd406b887424e73107b6dc52c5d863a4e7ace3c6475271a85171eb08ea541c8e65bf3a21fd055ddded2bcb5f9de4787a9ac723f35a9819d851634394a1805dbfbd1fa62982158f45fe7fd9f2b7b996bea82b1c798e3810950400edcd0003ec48139363dd6b09bb62e0fe7f7cbf8d8563f70cedb226ca545dd34ecad41086fe1bd23dd141cf63136e5fd4d57d744689355cd26f20409631c5d0d6b5130f9d7a1e1b977a3e78bac5d5d4c307510ea3c2cbf2c676b255f32c32c420e60bd96634d9a265eecb569b5b0b40a166e367c74c6868c4948ddc55cb3497f45a81cad57662ea02afea667cdb617d49514a10e636c958b545ab6971fcae55e660adc235a2e1e1127db80bb6ad873fd5203e91b4d95454901189445151018f1f0820e686faccf0bd74fa006b7f665", DataBus.FILE_MASK));

    @Override
    public String a(String string2, List<BubbleEntity> list) {
        Matcher matcher = c.matcher((CharSequence)string2);
        while (matcher.find()) {
            String string3;
            if (matcher.groupCount() < 15 || this.a(string3 = matcher.group(1))) continue;
            Object object = new a();
            object.a = matcher.group(2);
            object.d = matcher.group(3);
            object.e = matcher.group(4);
            object.b = matcher.group(5);
            object.c = matcher.group(6);
            object.f = matcher.group(7);
            object.g = matcher.group(8);
            object.h = matcher.group(9);
            if (arx.b(object.g)) {
                object.g = matcher.group(10);
            }
            if (arx.b(object.h)) {
                object.h = matcher.group(11);
            }
            object.i = matcher.group(13);
            object.j = matcher.group(14);
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
        String f;
        String g;
        String h;
        String i;
        String j;

        a() {
        }

        /*
         * Enabled aggressive block sorting
         */
        public DateReminderAction a(long l2) {
            boolean bl2;
            int n2;
            long l3;
            Object object;
            int n3;
            int n4;
            int n5;
            if (arx.b(this.b) && arx.b(this.c) && arx.b(this.d) && arx.b(this.e)) {
                return null;
            }
            int n6 = aqn.b(l2);
            int n7 = aqn.c(l2);
            if (!arx.b(this.d)) {
                l2 = aqn.c(this.d, l2);
            } else if (!arx.b(this.e)) {
                l2 = aqn.d(this.e, l2);
            } else {
                if (!arx.b(this.b)) {
                    object = aqn.a(this.b, l2, n7);
                    n4 = n2 = object.a;
                    n5 = n7++;
                    n3 = n6;
                    if (object.b) {
                        n4 = n2;
                        n5 = n7;
                        n3 = n6;
                        if (n7 > 12) {
                            n3 = n6 + 1;
                            n5 = 1;
                            n4 = n2;
                        }
                    }
                } else {
                    n4 = aqn.a(this.c, n6, n7, l2);
                    n5 = n7;
                    n3 = n6;
                }
                l2 = aqn.a(n3, n5, n4);
            }
            if (l2 == -1) {
                return null;
            }
            if (!arx.b(this.g)) {
                n2 = aqn.a(this.g, this.f);
                n5 = 0;
                if (!arx.b(this.h)) {
                    n5 = aqn.a(this.h);
                }
                n3 = !arx.b(this.i) ? aqn.a(this.i, this.f) : n2;
                n4 = !arx.b(this.j) ? aqn.a(this.j) : n5;
                long l4 = n2;
                long l5 = n5;
                l3 = (long)n3 * 3600000 + l2 + (long)n4 * 60000;
                l2 = l4 * 3600000 + l2 + l5 * 60000;
                bl2 = false;
            } else {
                l3 = l2;
                bl2 = true;
            }
            object = new DateReminderAction(null);
            object.isAllDay = bl2;
            object.startTime = l2;
            object.endTime = l3;
            return object;
        }
    }

}

