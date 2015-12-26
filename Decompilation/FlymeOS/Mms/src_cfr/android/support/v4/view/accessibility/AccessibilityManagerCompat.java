/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.accessibilityservice.AccessibilityServiceInfo
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.view.accessibility.AccessibilityManager
 *  java.lang.Object
 *  java.util.Collections
 */
package android.support.v4.view.accessibility;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.os.Build;
import android.support.v4.view.accessibility.AccessibilityManagerCompat$AccessibilityManagerIcsImpl$1;
import android.support.v4.view.accessibility.AccessibilityManagerCompatIcs;
import android.view.accessibility.AccessibilityManager;
import java.util.Collections;
import java.util.List;

public class AccessibilityManagerCompat {
    private static final AccessibilityManagerVersionImpl IMPL = Build.VERSION.SDK_INT >= 14 ? new AccessibilityManagerIcsImpl() : new AccessibilityManagerStubImpl();

    public static boolean addAccessibilityStateChangeListener(AccessibilityManager accessibilityManager, AccessibilityStateChangeListenerCompat accessibilityStateChangeListenerCompat) {
        return IMPL.addAccessibilityStateChangeListener(accessibilityManager, accessibilityStateChangeListenerCompat);
    }

    public static List<AccessibilityServiceInfo> getEnabledAccessibilityServiceList(AccessibilityManager accessibilityManager, int n2) {
        return IMPL.getEnabledAccessibilityServiceList(accessibilityManager, n2);
    }

    public static List<AccessibilityServiceInfo> getInstalledAccessibilityServiceList(AccessibilityManager accessibilityManager) {
        return IMPL.getInstalledAccessibilityServiceList(accessibilityManager);
    }

    public static boolean isTouchExplorationEnabled(AccessibilityManager accessibilityManager) {
        return IMPL.isTouchExplorationEnabled(accessibilityManager);
    }

    public static boolean removeAccessibilityStateChangeListener(AccessibilityManager accessibilityManager, AccessibilityStateChangeListenerCompat accessibilityStateChangeListenerCompat) {
        return IMPL.removeAccessibilityStateChangeListener(accessibilityManager, accessibilityStateChangeListenerCompat);
    }

    static class AccessibilityManagerIcsImpl
    extends AccessibilityManagerStubImpl {
        AccessibilityManagerIcsImpl() {
        }

        @Override
        public boolean addAccessibilityStateChangeListener(AccessibilityManager accessibilityManager, AccessibilityStateChangeListenerCompat accessibilityStateChangeListenerCompat) {
            return AccessibilityManagerCompatIcs.addAccessibilityStateChangeListener(accessibilityManager, accessibilityStateChangeListenerCompat.mListener);
        }

        @Override
        public List<AccessibilityServiceInfo> getEnabledAccessibilityServiceList(AccessibilityManager accessibilityManager, int n2) {
            return AccessibilityManagerCompatIcs.getEnabledAccessibilityServiceList(accessibilityManager, n2);
        }

        @Override
        public List<AccessibilityServiceInfo> getInstalledAccessibilityServiceList(AccessibilityManager accessibilityManager) {
            return AccessibilityManagerCompatIcs.getInstalledAccessibilityServiceList(accessibilityManager);
        }

        @Override
        public boolean isTouchExplorationEnabled(AccessibilityManager accessibilityManager) {
            return AccessibilityManagerCompatIcs.isTouchExplorationEnabled(accessibilityManager);
        }

        @Override
        public Object newAccessiblityStateChangeListener(AccessibilityStateChangeListenerCompat accessibilityStateChangeListenerCompat) {
            return AccessibilityManagerCompatIcs.newAccessibilityStateChangeListener(new AccessibilityManagerCompat$AccessibilityManagerIcsImpl$1(this, accessibilityStateChangeListenerCompat));
        }

        @Override
        public boolean removeAccessibilityStateChangeListener(AccessibilityManager accessibilityManager, AccessibilityStateChangeListenerCompat accessibilityStateChangeListenerCompat) {
            return AccessibilityManagerCompatIcs.removeAccessibilityStateChangeListener(accessibilityManager, accessibilityStateChangeListenerCompat.mListener);
        }
    }

    static class AccessibilityManagerStubImpl
    implements AccessibilityManagerVersionImpl {
        AccessibilityManagerStubImpl() {
        }

        @Override
        public boolean addAccessibilityStateChangeListener(AccessibilityManager accessibilityManager, AccessibilityStateChangeListenerCompat accessibilityStateChangeListenerCompat) {
            return false;
        }

        @Override
        public List<AccessibilityServiceInfo> getEnabledAccessibilityServiceList(AccessibilityManager accessibilityManager, int n2) {
            return Collections.emptyList();
        }

        @Override
        public List<AccessibilityServiceInfo> getInstalledAccessibilityServiceList(AccessibilityManager accessibilityManager) {
            return Collections.emptyList();
        }

        @Override
        public boolean isTouchExplorationEnabled(AccessibilityManager accessibilityManager) {
            return false;
        }

        @Override
        public Object newAccessiblityStateChangeListener(AccessibilityStateChangeListenerCompat accessibilityStateChangeListenerCompat) {
            return null;
        }

        @Override
        public boolean removeAccessibilityStateChangeListener(AccessibilityManager accessibilityManager, AccessibilityStateChangeListenerCompat accessibilityStateChangeListenerCompat) {
            return false;
        }
    }

    static interface AccessibilityManagerVersionImpl {
        public boolean addAccessibilityStateChangeListener(AccessibilityManager var1, AccessibilityStateChangeListenerCompat var2);

        public List<AccessibilityServiceInfo> getEnabledAccessibilityServiceList(AccessibilityManager var1, int var2);

        public List<AccessibilityServiceInfo> getInstalledAccessibilityServiceList(AccessibilityManager var1);

        public boolean isTouchExplorationEnabled(AccessibilityManager var1);

        public Object newAccessiblityStateChangeListener(AccessibilityStateChangeListenerCompat var1);

        public boolean removeAccessibilityStateChangeListener(AccessibilityManager var1, AccessibilityStateChangeListenerCompat var2);
    }

    public static abstract class AccessibilityStateChangeListenerCompat {
        final Object mListener;

        public AccessibilityStateChangeListenerCompat() {
            this.mListener = IMPL.newAccessiblityStateChangeListener(this);
        }

        public abstract void onAccessibilityStateChanged(boolean var1);
    }

}

