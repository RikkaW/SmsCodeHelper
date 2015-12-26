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

public class aru
extends aqm {
    protected static final Pattern c = Pattern.compile((String)anv.b("92165915dda3bda903f99d9148aad8cfeba4e93cccc34f5c749e2c8e75af6337c17b9d04940a31f3cbcb97080c1b35140f850ff027ce76e5ad1712a8e718c399b94b5c6e8d10e661eece9b5bce9c08116f21694b299c05fcfa39217d607cdb58b800a553e07e68ba9c0fa20f4e356b59e8300702627281a1ec5ffc17d3dcac2fdc7dd65f66ae8aad8dcb7f12b78bd499199b3bf07ea18fff5e242897f31e14289825ddf26bcbaa85cf7ea7a406279616a862a271dbf0f0ab6e13d33a5a7b1e715eceaa194862219915eee00f51357c666abfa792bfb48cdf5f13eb8952fc2cb17281a7bd0afb8d53e2eeecc247036efa795ec049deea1968ffd09100001682d767b731b0272fa569665e319b65f52096d1ed68e0e9e69ecf6c8f224111f8a26e489f989222b1a22f37cb37899b13f4dee6511558e2a9d09e79983aebecb2375657a5f5252beb5c8b5899992412a49703100bdf83fbf84d4e0a263cda5748be8bd692641b3dfa0308bb6b518b44c29710db71a683e488d7779420e82b1cdd691e5d9c8bfdd05fa9d13fb59b85e5df5867175eace080fc36ab9f2d9a5d155c4665c4f62ddd5582d400db2ddef92b4c8fb13fb59b85e5df5867cf4b0871c62c0a89d6b9e5269e8a46d506d66f85270f5183348dcf6ea8ff5cb3a6867664bf50f46d57791cffe00a4d533815747fb1fdbcdd178e0aca6f2359ad2f2b4439b28636eb536cc750b7a08d967e697d3c22e0a777e5e302b93767fb425bf69430bf3460888ec7c3ce204d54f9914122b9f72f924f749e2c8e75af6337325b15c23a8cf6977420a77bec82c6e18bcacc4c448b7c990bbceb8137d1e891bfc6223a6c6f763013e4785e2bb8832aa1cfca30ad7c09d8fa2a39cd8c484769795ec049deea1968ffd09100001682d7d3d93c801a55a9f9e02e0d32710c165f325b15c23a8cf6977420a77bec82c6e18bcacc4c448b7c99f7b430499c1c8804429411283225e898ddad84469de0841a38c7e2ae3eafffd7c96e4e9cfecb8c2372e0db48f6b9e568ec5ffc17d3dcac2fdc7dd65f66ae8aad8dcb7f12b78bd499199b3bf07ea18fff5e242897f31e14289825ddf26bcbaa85cf7ea7a406279616a862a271dbf0f0ab6e13d33a5a7b1e715eceaa194862219915eee00f51357c666abfa792bfb48cdf5f13eb8952fc2cb17281a7bd0afb8d53e2eeecc247036efa795ec049deea1968ffd09100001682d767b731b0272fa569665e319b65f52096d1ed68e0e9e69ecf6c8f224111f8a26e489f989222b1a22f37cb37899b13f4dee6511558e2a9d09e79983aebecb2375657a5f5252beb5c8b5899992412a49703100bdf83fbf84d4e0a263cda5748be8bd692641b3dfa0308bb6b518b44c29710db71a683e488d7779420e82b1cdd691e5d9c8bfdd05fa9d13fb59b85e5df5867175eace080fc36ab9f2d9a5d155c4665c4f62ddd5582d400db2ddef92b4c8fb13fb59b85e5df5867cf4b0871c62c0a89d6b9e5269e8a46d506d66f85270f5183348dcf6ea8ff5cb3a6867664bf50f46d57791cffe00a4d53353a84c8712715ea083f30c69aca8ddfc604aa6579f8a9ba146fab27a3aac2bdb3c12581b2b09bc82c37de0433e847c9b19ae56f3a727f2a1719ee8045834ef5795ec049deea19687ab63c88d0a9dc2a665e319b65f52096d1ed68e0e9e69ecf00da8bb2ee35f56a5ef5ca4805e69d6179c6cd2dabe79293efed9b29fcfe4d8d", DataBus.FILE_MASK));

    @Override
    public String a(String string2, List<BubbleEntity> list) {
        Matcher matcher = c.matcher((CharSequence)string2);
        while (matcher.find()) {
            String string3;
            if (matcher.groupCount() < 9 || this.a(string3 = matcher.group(1))) continue;
            Object object = new a();
            object.a = matcher.group(2);
            object.b = matcher.group(3);
            object.c = matcher.group(4);
            object.d = matcher.group(5);
            object.e = matcher.group(6);
            object.f = matcher.group(7);
            object.g = matcher.group(8);
            object.h = matcher.group(9);
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

        a() {
        }

        /*
         * Enabled aggressive block sorting
         */
        public DateReminderAction a(long l2) {
            long l3;
            int n2;
            int n3;
            boolean bl2 = true;
            if (aru.b(this.a)) {
                return null;
            }
            if (aru.b(this.c) && aru.b(this.d)) {
                return null;
            }
            int n4 = aqn.b(l2);
            Object object = aqn.b(this.a, l2);
            int n5 = object.a;
            int n6 = n4;
            if (object.b) {
                n6 = n4 + 1;
            }
            if (!aru.b(this.c)) {
                object = aqn.a(this.c, l2, n5);
                n3 = object.a;
                n4 = n5++;
                if (object.b) {
                    n4 = n5;
                    if (n5 > 12) {
                        ++n6;
                        n4 = 1;
                    }
                }
            } else {
                n3 = aqn.a(this.d, n6, n5, l2);
                n4 = n5;
            }
            if ((l3 = aqn.a(n6, n4, n3)) == -1) {
                return null;
            }
            n3 = n4;
            n4 = n6;
            if (!aru.b(this.e)) {
                object = aqn.b(this.e, l2);
                n3 = n5 = object.a;
                n4 = n6;
                if (object.b) {
                    n4 = n6 + 1;
                    n3 = n5;
                }
            }
            if (!aru.b(this.g)) {
                int n7;
                object = aqn.a(this.g, l2, n3);
                n2 = n7 = object.a;
                n6 = n3++;
                n5 = n4;
                if (object.b) {
                    n2 = n7;
                    n6 = n3;
                    n5 = n4;
                    if (n3 > 12) {
                        n5 = n4 + 1;
                        n6 = 1;
                        n2 = n7;
                    }
                }
            } else {
                n2 = aqn.a(this.h, n4, n3, l2);
                n6 = n3;
                n5 = n4;
            }
            if ((l2 = aqn.a(n5, n6, n2)) == -1) {
                l2 = l3;
            } else {
                bl2 = false;
            }
            object = new DateReminderAction(null);
            object.isAllDay = bl2;
            object.startTime = l3;
            object.endTime = l2 + 86400000 - 1000;
            return object;
        }
    }

}

