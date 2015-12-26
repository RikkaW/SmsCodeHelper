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
import android.support.v4.view.accessibility.AccessibilityNodeProviderCompatKitKat;
import java.util.ArrayList;
import java.util.List;

class AccessibilityNodeProviderCompat$AccessibilityNodeProviderKitKatImpl$1
implements AccessibilityNodeProviderCompatKitKat.AccessibilityNodeInfoBridge {
    final /* synthetic */ AccessibilityNodeProviderCompat.AccessibilityNodeProviderKitKatImpl this$0;
    final /* synthetic */ AccessibilityNodeProviderCompat val$compat;

    AccessibilityNodeProviderCompat$AccessibilityNodeProviderKitKatImpl$1(AccessibilityNodeProviderCompat.AccessibilityNodeProviderKitKatImpl accessibilityNodeProviderKitKatImpl, AccessibilityNodeProviderCompat accessibilityNodeProviderCompat) {
        this.this$0 = accessibilityNodeProviderKitKatImpl;
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
    public Object findFocus(int n2) {
        AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = this.val$compat.findFocus(n2);
        if (accessibilityNodeInfoCompat == null) {
            return null;
        }
        return accessibilityNodeInfoCompat.getInfo();
    }

    @Override
    public boolean performAction(int n2, int n3, Bundle bundle) {
        return this.val$compat.performAction(n2, n3, bundle);
    }
}

