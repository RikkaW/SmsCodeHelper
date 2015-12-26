/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.media.MediaRouter
 *  android.media.MediaRouter$Callback
 *  android.media.MediaRouter$RouteInfo
 *  android.media.MediaRouter$UserRouteInfo
 *  java.lang.Object
 */
package android.support.v4.media.routing;

import android.media.MediaRouter;
import android.support.v4.media.routing.MediaRouterJellybeanMr1;

class MediaRouterJellybeanMr2
extends MediaRouterJellybeanMr1 {
    MediaRouterJellybeanMr2() {
    }

    public static void addCallback(Object object, int n2, Object object2, int n3) {
        ((MediaRouter)object).addCallback(n2, (MediaRouter.Callback)object2, n3);
    }

    public static Object getDefaultRoute(Object object) {
        return ((MediaRouter)object).getDefaultRoute();
    }

    public static final class RouteInfo {
        public static CharSequence getDescription(Object object) {
            return ((MediaRouter.RouteInfo)object).getDescription();
        }

        public static boolean isConnecting(Object object) {
            return ((MediaRouter.RouteInfo)object).isConnecting();
        }
    }

    public static final class UserRouteInfo {
        public static void setDescription(Object object, CharSequence charSequence) {
            ((MediaRouter.UserRouteInfo)object).setDescription(charSequence);
        }
    }

}

