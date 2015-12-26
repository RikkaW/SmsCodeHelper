/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.os.Bundle
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 */
package android.support.v4.view.accessibility;

import android.os.Bundle;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.view.accessibility.AccessibilityNodeProviderCompat;
import android.support.v4.view.accessibility.AccessibilityNodeProviderCompatJellyBean;
import java.util.ArrayList;
import java.util.List;

class AccessibilityNodeProviderCompat$AccessibilityNodeProviderJellyBeanImpl$1
implements AccessibilityNodeProviderCompatJellyBean.AccessibilityNodeInfoBridge {
    final /* synthetic */ AccessibilityNodeProviderCompat.AccessibilityNodeProviderJellyBeanImpl this$0;
    final /* synthetic */ AccessibilityNodeProviderCompat val$compat;

    AccessibilityNodeProviderCompat$AccessibilityNodeProviderJellyBeanImpl$1(AccessibilityNodeProviderCompat.AccessibilityNodeProviderJellyBeanImpl accessibilityNodeProviderJellyBeanImpl, AccessibilityNodeProviderCompat accessibilityNodeProviderCompat) {
        this.this$0 = accessibilityNodeProviderJellyBeanImpl;
        this.val$compat = accessibilityNodeProviderCompat;
    }

    @Override
    public Object createAccessibilityNodeInfo(int n2) {
        AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = this.val$compat.createAccessibilityNodeInfo(n2);
        if (accessibilityNodeInfoCompat == null) {
            return null;
        }
        return accessibilityNodeInfoCompat.getInfo();
    }

    @Override
    public List<Object> findAccessibilityNodeInfosByText(String object, int n2) {
        object = this.val$compat.findAccessibilityNodeInfosByText((String)object, n2);
        ArrayList arrayList = new ArrayList();
        int n3 = object.size();
        for (n2 = 0; n2 < n3; ++n2) {
            arrayList.add(((AccessibilityNodeInfoCompat)object.get(n2)).getInfo());
        }
        return arrayList;
    }

    @Override
    public boolean performAction(int n2, int n3, Bundle bundle) {
        return this.val$compat.performAction(n2, n3, bundle);
    }
}

