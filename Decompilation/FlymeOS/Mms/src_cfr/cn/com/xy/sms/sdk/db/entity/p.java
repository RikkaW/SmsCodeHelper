/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  java.lang.Class
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.Throwable
 *  java.util.ArrayList
 */
package cn.com.xy.sms.sdk.db.entity;

import cn.com.xy.sms.sdk.db.entity.SceneRule;
import java.util.ArrayList;
import java.util.List;

public final class p {
    public String a;
    public String b;
    public int c;
    public int d = 0;
    public int e = 0;
    public List<SceneRule> f;
    private int g;

    public final void a(SceneRule sceneRule) {
        if (this.f == null) {
            this.f = new ArrayList();
        }
        this.f.add(sceneRule);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public final boolean equals(Object object) {
        if (object == null) {
            return false;
        }
        try {
            if (this.hashCode() == object.hashCode()) {
                return true;
            }
            if (this.getClass() != object.getClass()) return false;
            object = (p)object;
            if (this.a == null) return false;
            boolean bl2 = this.a.equals((Object)object.a);
            if (!bl2) return false;
            return true;
        }
        catch (Throwable var1_2) {
            var1_2.printStackTrace();
            return true;
        }
    }

    public final int hashCode() {
        return super.hashCode();
    }

    public final String toString() {
        return "Sceneconfig [sceneId=" + this.a + ", sceneVersion=" + this.b + ", isDownload=" + this.d + "]";
    }
}

