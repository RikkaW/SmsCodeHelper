/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.os.Bundle
 *  android.view.View
 *  android.view.accessibility.AccessibilityEvent
 *  java.lang.Object
 *  java.lang.String
 */
import android.os.Bundle;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import flyme.support.v7.widget.RecyclerView;

public class arq
extends AccessibilityDelegateCompat {
    final RecyclerView a;
    final AccessibilityDelegateCompat b;

    private boolean b() {
        return this.a.s();
    }

    public AccessibilityDelegateCompat a() {
        return this.b;
    }

    @Override
    public void onInitializeAccessibilityEvent(View object, AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent((View)object, accessibilityEvent);
        accessibilityEvent.setClassName((CharSequence)RecyclerView.class.getName());
        if (object instanceof RecyclerView && !this.b() && (object = (RecyclerView)object).getLayoutManager() != null) {
            object.getLayoutManager().a(accessibilityEvent);
        }
    }

    @Override
    public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
        accessibilityNodeInfoCompat.setClassName(RecyclerView.class.getName());
        if (!this.b() && this.a.getLayoutManager() != null) {
            this.a.getLayoutManager().a(accessibilityNodeInfoCompat);
        }
    }

    @Override
    public boolean performAccessibilityAction(View view, int n2, Bundle bundle) {
        if (super.performAccessibilityAction(view, n2, bundle)) {
            return true;
        }
        if (!this.b() && this.a.getLayoutManager() != null) {
            return this.a.getLayoutManager().a(n2, bundle);
        }
        return false;
    }
}

