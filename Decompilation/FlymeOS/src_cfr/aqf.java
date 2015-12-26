/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.text.TextUtils
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 *  java.util.regex.Matcher
 *  java.util.regex.Pattern
 */
import android.text.TextUtils;
import com.ted.android.contacts.common.DataBus;
import com.ted.android.data.BubbleEntity;
import com.ted.android.data.bubbleAction.ActionBase;
import com.ted.android.data.bubbleAction.PhoneNumberAction;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class aqf
extends aqc {
    private static final Pattern a = Pattern.compile((String)anv.b("96c7560fe3462fadd6a328fac3b4a992f4b45012648bd4f39705f6a0cf9a9c7ceae2b6865fb1231d7fcb760d6cb980a17ed856b30a1169e38cd5791803201dd21e4d2b947809eada5df8069dd6d4a9e7274fff62a5232074ba0ee0f10c428fc6f70fdd0e6700df4cb6ef42e4e43cbb8555678bc7cdbcc5667d42f432279a09b7b7e8360a01168ca15106c856d36dbb2e8a14133c03dab1896d161725c15e9b83b891aeb9e000b35a4ce2261c6b50d621ce11893ac7985727c266505be98ef317e26f5c463a1d6696dbfd98496ae41281c943e0fa5f3490d2d76821d3b488cccc55678bc7cdbcc566994bbfa3e4a8ca7e95cc3fb6fc7b04ce7f60a7dc4b0ac8ebffd09100001682d7a6206691a5b8ff2686424c6e3a4c88aeaceb78fe4e8f256e1753b82080c669bd18359eb1f27a8c3dd0469d77588f8659f3f22f31b978168137e638573be32802b80be15cffd062daaceb78fe4e8f256e7e9c0c04b83e89c9f07437f95599cb69fceaa5105b922d0fca2c50a00cbecbea8c4ca2fee8a79c2443b47a148e9d6e634d290cea847ad0413fdc6a188e45d7bb795ec049deea1968ffd09100001682d7f0f9681a9b4fdf24dc7dd65f66ae8aaddf1e0eda53ae0ffa4b2221543ec2fc9c2a9b275bbe9cacb1b80be15cffd062daaceb78fe4e8f256e1753b82080c669bdae213d7df81de3148ed832229a01ab9b6e9c43e916688139f3f22f31b978168137e638573be32802d761e4fc24f34d21938ec187e1cefabb18c6196dbb2dcf6e43b47a148e9d6e63b26329b3c26f47392d3721ec4c33da6e7f60a7dc4b0ac8ebffd09100001682d76e9b3f7ea92b3ed0dc7dd65f66ae8aad64d95f44a6cc85b87f60a7dc4b0ac8ebffd09100001682d716a79bdc196d88e0f07437f95599cb69fceaa5105b922d0f6f247e83275316989c82eea71637f2d2de2239fac8b2a0abee31b7faa2d3afb31a6e84890d2c2ab7f07437f95599cb69746a3aa348e2aa6cf48fdf2557bceb16aceb78fe4e8f256e1753b82080c669bdd9086da6b1a6adff14c86b6027af26e1d653ec9c74827fa51753b82080c669bdea726a3e6c0fc505f227b1029b447c94d6d17a278017dcb5f01c3ffdf6cc21364ac64c783d4c0e2b4e144e630f14f5045a2768796ce6786cb80be15cffd062daaceb78fe4e8f256e1753b82080c669bd9cf02a5c6ddefe9118c6196dbb2dcf6e43b47a148e9d6e635970fd1e417db9d87719e42d86841863910fc47722d7f15ecf8d6151eda65e08938ec187e1cefabb18c6196dbb2dcf6e43b47a148e9d6e634404b27b0cbff6b81cf87548e3d238aa3dc3dbd2d719a03a7369351e71e97b49fceaa5105b922d0f9960ca9a681d9018315fd1dbfab41eb397c12bd3409da62fde2239fac8b2a0ab43b47a148e9d6e6385c5f32bf3c12b9e795ec049deea1968242920a8291bf63e6293c736f86c71c7749e2c8e75af6337559a39d072d1366a7f60a7dc4b0ac8ebffd09100001682d7cc260c9bbafcd15c17fb08325bee20cb795ec049deea19687d5728acf0c4ed292d028da6fb3b0765749e2c8e75af63372d87907406c35f517f60a7dc4b0ac8ebffd09100001682d7840c9b101b52d21c0ccfaa306caafd73ef59d436bb8e80bec943e0fa5f3490d2bdfaad15fd91673f6b146e33790edc0ccbabbe08dd6b411aeb0ba005298e42f16962a56af4899d88a3acd10ba537f813495fadf0e9cef26c2935e4d08a8cad8c0000a392709599dbb6e4a1395fe87975f49efd20f9d7f5aff0b4d7f4c5f33ed2b6423a29b754ff4d5b651d80feaf14a2eda5bed82015a85513ff75d5947524bfed00f4cf24e55e64431e8d07de58ed725b651d80feaf14a2aee4eed2decbf3e32aa49992e7cf8ba3b9b301b51c8a3e6458a370db1f1ca5fc4f21499a71d7440db424dbc10962732e9289e4383d42a110cd12e6c0bc59932f93436e596d679f05d61d0cb1c8868304c88e4291aec78d97bc3ba5a6646eb9e3d788ef0f256ca8ddaee9c55897fc48efc3b3bed76556e037efd64ec85a085bf9ea33b969995fce5b96ea96d58bae826724c48588e5809f8dece7de0f8e0c0a5ab4969b6935cf44588c3ba6e4f7c5d58ab1e9e8d9f6a6ff74cda43ddd9c61d4566730fecf2beaa265ba91bd83db3aa1f254f4967ae5da5d77adaa6c87ec24fd28adef73d9722185b90312a356c8743acc1021837c742eec89", DataBus.FILE_MASK));
    private static aqf b;

    private aqf() {
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public static aqf a() {
        if (b == null) {
            synchronized (aqf.class) {
                b = new aqf();
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
        block0 : while (string2.find()) {
            int n2 = string2.groupCount();
            int n3 = 1;
            do {
                if (n3 >= n2) continue block0;
                Object object = string2.group(n3);
                if (!TextUtils.isEmpty((CharSequence)object) && !TextUtils.equals((CharSequence)"null", (CharSequence)object)) {
                    BubbleEntity bubbleEntity = new BubbleEntity();
                    bubbleEntity.setId("-6");
                    bubbleEntity.setMatchedWords((String)object);
                    bubbleEntity.setIndex(string2.start(n3));
                    object = new PhoneNumberAction(bubbleEntity);
                    object.number = string2.group(n3);
                    bubbleEntity.addAction((ActionBase)object);
                    bubbleEntity.setShowType(2);
                    string3.add(bubbleEntity);
                }
                ++n3;
            } while (true);
            break;
        }
        return string3;
    }
}

