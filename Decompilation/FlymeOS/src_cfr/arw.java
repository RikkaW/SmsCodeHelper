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

public class arw
extends aqm {
    protected static final Pattern c = Pattern.compile((String)anv.b("92165915dda3bda903f99d9148aad8cfeba4e93cccc34f5c749e2c8e75af6337c17b9d04940a31f3cbcb97080c1b35140f850ff027ce76e5ad1712a8e718c399b94b5c6e8d10e661eece9b5bce9c08116f21694b299c05fcfa39217d607cdb58b800a553e07e68ba9c0fa20f4e356b593024ab2e409e0915ec5ffc17d3dcac2fdc7dd65f66ae8aad8dcb7f12b78bd499199b3bf07ea18fff5e242897f31e14289825ddf26bcbaa85cf7ea7a406279616a862a271dbf0f0ab6e13d33a5a7b1e715eceaa194862219915eee00f51357c666abfa792bfb48cdf5f13eb8952fc2cb17281a7bd0afb8d53e2eeecc247036efa795ec049deea1968ffd09100001682d767b731b0272fa569665e319b65f52096d1ed68e0e9e69ecf6c8f224111f8a26e489f989222b1a22f37cb37899b13f4dee6511558e2a9d09e79983aebecb2375657a5f5252beb5c8b5899992412a49703100bdf83fbf84d4e0a263cda5748be8bd692641b3dfa0308bb6b518b44c29710db71a683e488d7779420e82b1cdd691e5d9c8bfdd05fa9d13fb59b85e5df5867175eace080fc36ab9f2d9a5d155c4665c4f62ddd5582d400db2ddef92b4c8fb13fb59b85e5df5867cf4b0871c62c0a89d6b9e5269e8a46d506d66f85270f5183348dcf6ea8ff5cb3a6867664bf50f46d57791cffe00a4d533815747fb1fdbcdd178e0aca6f2359ad2f2b4439b28636eb536cc750b7a08d967e697d3c22e0a777e5e302b93767fb425bf69430bf3460888ec7c3ce204d54f9914122b9f72f924f749e2c8e75af6337325b15c23a8cf6977420a77bec82c6e18bcacc4c448b7c990bbceb8137d1e891bfc6223a6c6f76306cdb653cd9668a176de8773710024255b5d9c3b3d738928857d3d176b690591a95ecc306a8d2aebdc6f71f0ba7890619608c01f08384ebf27f84aa0e5c7cc7543626254918c9c639e2af0cb55851309603f99d9148aad8cf9703283b9cf99f9af01c3ffdf6cc21364ac64c783d4c0e2b56863f9e50cd269f3934bc71a4377825ab83fadc8e4a4365eb8fa441ef075c0c652adb4e9451f76e31686fb8077a2bbe95908ebaf935703ed9041e178e6b553dd1ed68e0e9e69ecf6c8f224111f8a26e489f989222b1a22f37cb37899b13f4dee6511558e2a9d09e79983aebecb2375657a5f5252beb5c8b5899992412a49703100bdf83fbf84d4e0a263cda5748be8bd692641b3dfa0308bb6b518b44c29710db71a683e488d7779420e82b1cdd691e5d9c8bfdd05fa9d13fb59b85e5df5867175eace080fc36abf049b90b6907a0d38b8171a89d46ce4c07563a40fb916a62f82574a4f8a3f048e0c5f290ac8df25c704f9dc711c09156a116368f8e51a5b0222f96c42df11c454f100e8d4de970fd8e2b60ebba9c7ab063335b25f01195b7affa98ad706e4d84e185a5ce25dc1b27312f76db3a9669d56e3609dcb8bfa949dc1d5d140adf0ad4d250e3569da401994e67f199f161432d2c676b255f32c32c5dc625a3c11668b6da727f3818030f8b8acebb7d74220f64", DataBus.FILE_MASK));

    @Override
    public String a(String string2, List<BubbleEntity> list) {
        Matcher matcher = c.matcher((CharSequence)string2);
        while (matcher.find()) {
            String string3;
            if (matcher.groupCount() < 14 || this.a(string3 = matcher.group(1))) continue;
            Object object = new a();
            object.a = matcher.group(2);
            object.b = matcher.group(3);
            object.c = matcher.group(4);
            object.d = matcher.group(5);
            object.e = matcher.group(6);
            object.f = matcher.group(7);
            object.g = matcher.group(8);
            if (arw.b(object.f)) {
                object.f = matcher.group(9);
            }
            if (arw.b(object.g)) {
                object.g = matcher.group(10);
            }
            object.h = matcher.group(12);
            object.i = matcher.group(13);
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

        a() {
        }

        /*
         * Unable to fully structure code
         * Enabled aggressive block sorting
         * Lifted jumps to return sites
         */
        public DateReminderAction a(long var1_1) {
            if (arw.b(this.a)) {
                return null;
            }
            if (arw.b(this.c) && arw.b(this.d)) {
                return null;
            }
            var4_2 = aqn.b(var1_1);
            var15_3 = aqn.b(this.a, var1_1);
            var6_4 = var15_3.a;
            var3_5 = var4_2;
            if (var15_3.b) {
                var3_5 = var4_2 + 1;
            }
            if (arw.b(this.c)) ** GOTO lbl27
            var15_3 = aqn.a(this.c, var1_1, var6_4);
            var5_6 = var15_3.a;
            var4_2 = var6_4++;
            if (!var15_3.b) ** GOTO lbl-1000
            var4_2 = var6_4;
            if (var6_4 > 12) {
                var6_4 = var3_5 + 1;
                var4_2 = 1;
                var3_5 = var5_6;
                var5_6 = var6_4;
            } else lbl-1000: // 2 sources:
            {
                var6_4 = var3_5;
                var3_5 = var5_6;
                var5_6 = var6_4;
            }
            ** GOTO lbl31
lbl27: // 1 sources:
            var7_7 = aqn.a(this.d, var3_5, var6_4, var1_1);
            var4_2 = var6_4;
            var5_6 = var3_5;
            var3_5 = var7_7;
lbl31: // 3 sources:
            if ((var1_1 = aqn.a(var5_6, var4_2, var3_5)) == -1) {
                return null;
            }
            if (!arw.b(this.f)) {
                var6_4 = aqn.a(this.f, this.e);
                var3_5 = 0;
                if (!arw.b(this.g)) {
                    var3_5 = aqn.a(this.g);
                }
                var4_2 = arw.b(this.h) == false ? aqn.a(this.h, this.e) : var6_4;
                var5_6 = arw.b(this.i) == false ? aqn.a(this.i) : var3_5;
                var10_8 = var6_4;
                var12_9 = var3_5;
                var8_10 = (long)var4_2 * 3600000 + var1_1 + (long)var5_6 * 60000;
                var1_1 = var10_8 * 3600000 + var1_1 + var12_9 * 60000;
                var14_11 = false;
            } else {
                var8_10 = var1_1;
                var14_11 = true;
            }
            var15_3 = new DateReminderAction(null);
            var15_3.isAllDay = var14_11;
            var15_3.startTime = var1_1;
            var15_3.endTime = var8_10;
            return var15_3;
        }
    }

}

