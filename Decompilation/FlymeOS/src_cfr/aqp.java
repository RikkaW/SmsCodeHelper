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

public class aqp
extends aqm {
    protected static final Pattern c = Pattern.compile((String)anv.b("92165915dda3bda9c4f854b8700936f75176edea99afaa5be57480bb49ef2a269c52d2df5dd8ea506218dfc0f63485bde41647cb04d60c179072e51a40d5848917a64f809b8d6318dc7dd65f66ae8aaddf1e0eda53ae0ffae259acf6cc4108ba0061675b4967176b665e319b65f52096d1ed68e0e9e69ecf6c8f224111f8a26e489f989222b1a22f37cb37899b13f4dee6511558e2a9d09e1bf877fef55cb4e4d3f52fe10946b8db4be8feeef1190a501dc883405f0e8605aceb78fe4e8f256e325b15c23a8cf69732813b3a6e6dd022fe7fc767ad980b9375a76ba2a26f471ee02e0d32710c165f735cc67eae6d8e50e34400dc03e958989cf2d295cf9924bb4d7fa87fb1b27d02137d75ead8d58d51110bb11429ba806135de7b9f990c9d7c9df83edecc64fd6bde2239fac8b2a0ab43b47a148e9d6e63f17583d64fdc37cd146fab27a3aac2bd21707d45766801edcdd04fbd425ed8914e89dab0b0064423a349f005b9aaf943745ead4b640003d53bff6e694437b7c3decd9181503e62550207bf7819f40a59183ee581cd47b5d85bf52733204640c40d1c6de974e9a60f99cbceb596c24a4ebc5bb644c7821a20219049306946b4020fa389eafd21a3eed62ae65e236c4b5ddb9e972eb3b10d94ffb41db547ec1f6fdcd99fb05b11a262cf8a6bf863a5dc280b79a184ac641870db9e972eb3b10d9426f77849476576f6955257e7117fe4652b8216b6106aee12a2f96b22b65148033270bc62c2130f4ded4ed76f9df6f22cf0c7ac5d4b7be8c88efe6b244c2d18f19eacb6c2b4a4ba6486fea26f496b40743b6994d19dcf186e79b1a5d786f641a4b659d0e670930e01dc7dd65f66ae8aad6a38a22882a492ea536cc750b7a08d96da118b3b71e3f43d4c6faf4739cc102d6b867da8ca0ecd4234cdefe7577594d83147b29bf780657d30b2a7e2ad13210fa810df739f2801a7850dcf73c93344eb1d0a5f7d2cc285eeea99b0c46783d29207d5fa5bae09a39afb0e90f071e0d84fffdb091b4c1a3040ea1e407ca6192ddbf3f22f31b978168137e638573be328026c700dea70e0cd57811ee757b5d1bf899eacb6c2b4a4ba644403d7328471e01bd406b887424e73107b6dc52c5d863a4e7ace3c6475271a85171eb08ea541c8e61ce4a2db49783f5f3476f22e3c168997117d30ff70d30c04083f30c69aca8ddfc604aa6579f8a9ba146fab27a3aac2bd802e6a682bda7b45bb24c09914d2401666db09ba71749686b146eb4e569ac1eaf4711864294e2d22a639a7715a355e9eff5b1fe49ff727b4e70184b67b31865cdf4e6ab544ab8ec8b86f2c9775ddc673acb464494c3761abec6aac391cb46f45f01c3ffdf6cc21364ac64c783d4c0e2b9c99bc2b383f8606199b3bf07ea18fffaae9d7f9c8e95505bb37238d8a58d90756a094f4a89612a2e69e69c91d8a43758c25a8f8a26c7ce484ff13c9218a3344b9357b765f2e24bab2a1ba70dd72df5c27e43f0d781267453bb9171705513993f449e14d31e823da70253e586577ae61a76cad7ac5f68e6dc47c035fb86a436c5a258d38f307f2cd05c2eefb35cb55a9ac3befe6477cadd309395bfbd8cc0a2455666b015e1ab0e24f305399d694624049a395af74220df2edff90512fa4b6a7316d380ace607494aebd81efc489479effa316d807d16209f86f134e5c373c9e44b30d6fcf4c4fb3dea4669955981b41de2239fac8b2a0ab0f850ff027ce76e5ad1712a8e718c3990ea26bfe350ef1de59a72ce0100944a4b8e56ef0c9de89bd5020bcc631a686c8f3f22f31b9781681af552c63ceffd88cfb21113715226982af89a9c5cb277a7832a34dcceb7bd6246ce7b54fd858a2c9e4cb189ca62e7a17303c95d96e53ff39", DataBus.FILE_MASK));

    @Override
    public String a(String string2, List<BubbleEntity> list) {
        Matcher matcher = c.matcher((CharSequence)string2);
        while (matcher.find()) {
            String string3;
            if (matcher.groupCount() < 11 || this.a(string3 = matcher.group(1))) continue;
            Object object = new a();
            object.a = matcher.group(2);
            object.b = matcher.group(3);
            object.c = matcher.group(4);
            object.d = matcher.group(5);
            object.e = matcher.group(6);
            object.f = matcher.group(7);
            object.g = matcher.group(8);
            object.h = matcher.group(9);
            object.i = matcher.group(10);
            object.j = matcher.group(11);
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
         * Unable to fully structure code
         * Enabled aggressive block sorting
         * Lifted jumps to return sites
         */
        public DateReminderAction a(long var1_1) {
            var11_2 = true;
            if (aqp.b(this.a) != false) return null;
            if (aqp.b(this.b)) {
                return null;
            }
            if (aqp.b(this.d) && aqp.b(this.e)) {
                return null;
            }
            var4_3 = aqn.a(this.a, var1_1);
            var12_4 = aqn.b(this.b, var1_1);
            var6_5 = var12_4.a;
            var3_6 = var4_3;
            if (var12_4.b) {
                var3_6 = var4_3 + 1;
            }
            if (aqp.b(this.d)) ** GOTO lbl27
            var12_4 = aqn.a(this.d, var1_1, var6_5);
            var5_7 = var12_4.a;
            var4_3 = var6_5++;
            if (!var12_4.b) ** GOTO lbl-1000
            var4_3 = var6_5;
            if (var6_5 > 12) {
                var4_3 = var3_6 + 1;
                var3_6 = 1;
            } else lbl-1000: // 2 sources:
            {
                var6_5 = var3_6;
                var3_6 = var4_3;
                var4_3 = var6_5;
            }
            ** GOTO lbl30
lbl27: // 1 sources:
            var5_7 = aqn.a(this.e, var3_6, var6_5, var1_1);
            var4_3 = var3_6;
            var3_6 = var6_5;
lbl30: // 3 sources:
            if ((var9_8 = aqn.a(var4_3, var3_6, var5_7)) == -1) {
                return null;
            }
            var5_7 = var4_3;
            if (!aqp.b(this.f)) {
                var5_7 = aqn.a(this.f, var1_1);
            }
            var4_3 = var3_6;
            var3_6 = var5_7;
            if (!aqp.b(this.g)) {
                var12_4 = aqn.b(this.g, var1_1);
                var4_3 = var6_5 = var12_4.a;
                var3_6 = var5_7;
                if (var12_4.b) {
                    var3_6 = var5_7 + 1;
                    var4_3 = var6_5;
                }
            }
            if (!aqp.b(this.i)) {
                var12_4 = aqn.a(this.i, var1_1, var4_3);
                var7_10 = var8_9 = var12_4.a;
                var5_7 = var4_3++;
                var6_5 = var3_6;
                if (var12_4.b) {
                    var7_10 = var8_9;
                    var5_7 = var4_3;
                    var6_5 = var3_6;
                    if (var4_3 > 12) {
                        var6_5 = var3_6 + 1;
                        var5_7 = 1;
                        var7_10 = var8_9;
                    }
                }
            } else {
                var7_10 = aqn.a(this.j, var3_6, var4_3, var1_1);
                var5_7 = var4_3;
                var6_5 = var3_6;
            }
            if ((var1_1 = aqn.a(var6_5, var5_7, var7_10)) == -1) {
                var1_1 = var9_8;
            } else {
                var11_2 = false;
            }
            var12_4 = new DateReminderAction(null);
            var12_4.isAllDay = var11_2;
            var12_4.startTime = var9_8;
            var12_4.endTime = var1_1 + 86400000 - 1000;
            return var12_4;
        }
    }

}

