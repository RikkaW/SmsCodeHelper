/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.accessibilityservice.AccessibilityServiceInfo
 *  android.content.pm.ResolveInfo
 *  android.os.Build
 *  android.os.Build$VERSION
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 */
package android.support.v4.accessibilityservice;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.support.v4.accessibilityservice.AccessibilityServiceInfoCompatIcs;
import android.support.v4.accessibilityservice.AccessibilityServiceInfoCompatJellyBeanMr2;

public class AccessibilityServiceInfoCompat {
    public static final int CAPABILITY_CAN_FILTER_KEY_EVENTS = 8;
    public static final int CAPABILITY_CAN_REQUEST_ENHANCED_WEB_ACCESSIBILITY = 4;
    public static final int CAPABILITY_CAN_REQUEST_TOUCH_EXPLORATION = 2;
    public static final int CAPABILITY_CAN_RETRIEVE_WINDOW_CONTENT = 1;
    public static final int DEFAULT = 1;
    public static final int FEEDBACK_ALL_MASK = -1;
    public static final int FEEDBACK_BRAILLE = 32;
    public static final int FLAG_INCLUDE_NOT_IMPORTANT_VIEWS = 2;
    public static final int FLAG_REPORT_VIEW_IDS = 16;
    public static final int FLAG_REQUEST_ENHANCED_WEB_ACCESSIBILITY = 8;
    public static final int FLAG_REQUEST_FILTER_KEY_EVENTS = 32;
    public static final int FLAG_REQUEST_TOUCH_EXPLORATION_MODE = 4;
    private static final AccessibilityServiceInfoVersionImpl IMPL = Build.VERSION.SDK_INT >= 18 ? new AccessibilityServiceInfoJellyBeanMr2() : (Build.VERSION.SDK_INT >= 14 ? new AccessibilityServiceInfoIcsImpl() : new AccessibilityServiceInfoStubImpl());

    private AccessibilityServiceInfoCompat() {
    }

    public static String capabilityToString(int n2) {
        switch (n2) {
            default: {
                return "UNKNOWN";
            }
            case 1: {
                return "CAPABILITY_CAN_RETRIEVE_WINDOW_CONTENT";
            }
            case 2: {
                return "CAPABILITY_CAN_REQUEST_TOUCH_EXPLORATION";
            }
            case 4: {
                return "CAPABILITY_CAN_REQUEST_ENHANCED_WEB_ACCESSIBILITY";
            }
            case 8: 
        }
        return "CAPABILITY_CAN_FILTER_KEY_EVENTS";
    }

    public static String feedbackTypeToString(int n2) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        block7 : while (n2 > 0) {
            int n3 = 1 << Integer.numberOfTrailingZeros((int)n2);
            n2 &= ~ n3;
            if (stringBuilder.length() > 1) {
                stringBuilder.append(", ");
            }
            switch (n3) {
                default: {
                    continue block7;
                }
                case 1: {
                    stringBuilder.append("FEEDBACK_SPOKEN");
                    continue block7;
                }
                case 4: {
                    stringBuilder.append("FEEDBACK_AUDIBLE");
                    continue block7;
                }
                case 2: {
                    stringBuilder.append("FEEDBACK_HAPTIC");
                    continue block7;
                }
                case 16: {
                    stringBuilder.append("FEEDBACK_GENERIC");
                    continue block7;
                }
                case 8: 
            }
            stringBuilder.append("FEEDBACK_VISUAL");
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    public static String flagToString(int n2) {
        switch (n2) {
            default: {
                return null;
            }
            case 1: {
                return "DEFAULT";
            }
            case 2: {
                return "FLAG_INCLUDE_NOT_IMPORTANT_VIEWS";
            }
            case 4: {
                return "FLAG_REQUEST_TOUCH_EXPLORATION_MODE";
            }
            case 8: {
                return "FLAG_REQUEST_ENHANCED_WEB_ACCESSIBILITY";
            }
            case 16: {
                return "FLAG_REPORT_VIEW_IDS";
            }
            case 32: 
        }
        return "FLAG_REQUEST_FILTER_KEY_EVENTS";
    }

    public static boolean getCanRetrieveWindowContent(AccessibilityServiceInfo accessibilityServiceInfo) {
        return IMPL.getCanRetrieveWindowContent(accessibilityServiceInfo);
    }

    public static int getCapabilities(AccessibilityServiceInfo accessibilityServiceInfo) {
        return IMPL.getCapabilities(accessibilityServiceInfo);
    }

    public static String getDescription(AccessibilityServiceInfo accessibilityServiceInfo) {
        return IMPL.getDescription(accessibilityServiceInfo);
    }

    public static String getId(AccessibilityServiceInfo accessibilityServiceInfo) {
        return IMPL.getId(accessibilityServiceInfo);
    }

    public static ResolveInfo getResolveInfo(AccessibilityServiceInfo accessibilityServiceInfo) {
        return IMPL.getResolveInfo(accessibilityServiceInfo);
    }

    public static String getSettingsActivityName(AccessibilityServiceInfo accessibilityServiceInfo) {
        return IMPL.getSettingsActivityName(accessibilityServiceInfo);
    }

    static class AccessibilityServiceInfoIcsImpl
    extends AccessibilityServiceInfoStubImpl {
        AccessibilityServiceInfoIcsImpl() {
        }

        @Override
        public boolean getCanRetrieveWindowContent(AccessibilityServiceInfo accessibilityServiceInfo) {
            return AccessibilityServiceInfoCompatIcs.getCanRetrieveWindowContent(accessibilityServiceInfo);
        }

        @Override
        public int getCapabilities(AccessibilityServiceInfo accessibilityServiceInfo) {
            if (this.getCanRetrieveWindowContent(accessibilityServiceInfo)) {
                return 1;
            }
            return 0;
        }

        @Override
        public String getDescription(AccessibilityServiceInfo accessibilityServiceInfo) {
            return AccessibilityServiceInfoCompatIcs.getDescription(accessibilityServiceInfo);
        }

        @Override
        public String getId(AccessibilityServiceInfo accessibilityServiceInfo) {
            return AccessibilityServiceInfoCompatIcs.getId(accessibilityServiceInfo);
        }

        @Override
        public ResolveInfo getResolveInfo(AccessibilityServiceInfo accessibilityServiceInfo) {
            return AccessibilityServiceInfoCompatIcs.getResolveInfo(accessibilityServiceInfo);
        }

        @Override
        public String getSettingsActivityName(AccessibilityServiceInfo accessibilityServiceInfo) {
            return AccessibilityServiceInfoCompatIcs.getSettingsActivityName(accessibilityServiceInfo);
        }
    }

    static class AccessibilityServiceInfoJellyBeanMr2
    extends AccessibilityServiceInfoIcsImpl {
        AccessibilityServiceInfoJellyBeanMr2() {
        }

        @Override
        public int getCapabilities(AccessibilityServiceInfo accessibilityServiceInfo) {
            return AccessibilityServiceInfoCompatJellyBeanMr2.getCapabilities(accessibilityServiceInfo);
        }
    }

    static class AccessibilityServiceInfoStubImpl
    implements AccessibilityServiceInfoVersionImpl {
        AccessibilityServiceInfoStubImpl() {
        }

        @Override
        public boolean getCanRetrieveWindowContent(AccessibilityServiceInfo accessibilityServiceInfo) {
            return false;
        }

        @Override
        public int getCapabilities(AccessibilityServiceInfo accessibilityServiceInfo) {
            return 0;
        }

        @Override
        public String getDescription(AccessibilityServiceInfo accessibilityServiceInfo) {
            return null;
        }

        @Override
        public String getId(AccessibilityServiceInfo accessibilityServiceInfo) {
            return null;
        }

        @Override
        public ResolveInfo getResolveInfo(AccessibilityServiceInfo accessibilityServiceInfo) {
            return null;
        }

        @Override
        public String getSettingsActivityName(AccessibilityServiceInfo accessibilityServiceInfo) {
            return null;
        }
    }

    static interface AccessibilityServiceInfoVersionImpl {
        public boolean getCanRetrieveWindowContent(AccessibilityServiceInfo var1);

        public int getCapabilities(AccessibilityServiceInfo var1);

        public String getDescription(AccessibilityServiceInfo var1);

        public String getId(AccessibilityServiceInfo var1);

        public ResolveInfo getResolveInfo(AccessibilityServiceInfo var1);

        public String getSettingsActivityName(AccessibilityServiceInfo var1);
    }

}

