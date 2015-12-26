/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.graphics.Rect
 *  android.transition.Transition
 *  android.transition.Transition$EpicenterCallback
 *  android.transition.TransitionManager
 *  android.transition.TransitionSet
 *  android.view.View
 *  android.view.ViewGroup
 *  android.view.ViewTreeObserver
 *  android.view.ViewTreeObserver$OnPreDrawListener
 *  java.lang.Object
 *  java.lang.String
 *  java.util.ArrayList
 */
package android.support.v4.app;

import android.graphics.Rect;
import android.support.v4.app.FragmentTransitionCompat21$1;
import android.support.v4.app.FragmentTransitionCompat21$2;
import android.support.v4.app.FragmentTransitionCompat21$3;
import android.support.v4.app.FragmentTransitionCompat21$4;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

class FragmentTransitionCompat21 {
    FragmentTransitionCompat21() {
    }

    static /* synthetic */ void access$000(ArrayList arrayList, View view) {
        FragmentTransitionCompat21.captureTransitioningViews(arrayList, view);
    }

    static /* synthetic */ Rect access$100(View view) {
        return FragmentTransitionCompat21.getBoundsOnScreen(view);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static void addTargets(Object object, ArrayList<View> arrayList) {
        int n2;
        if ((object = (Transition)object) instanceof TransitionSet) {
            object = (TransitionSet)object;
            int n3 = object.getTransitionCount();
            for (n2 = 0; n2 < n3; ++n2) {
                FragmentTransitionCompat21.addTargets((Object)object.getTransitionAt(n2), arrayList);
            }
            return;
        } else {
            if (FragmentTransitionCompat21.hasSimpleTarget((Transition)object) || !FragmentTransitionCompat21.isNullOrEmpty(object.getTargets())) return;
            int n4 = arrayList.size();
            n2 = 0;
            while (n2 < n4) {
                object.addTarget((View)arrayList.get(n2));
                ++n2;
            }
            return;
        }
    }

    public static void addTransitionTargets(Object object, Object object2, View view, ViewRetriever viewRetriever, View view2, EpicenterView epicenterView, Map<String, String> map, ArrayList<View> arrayList, Map<String, View> map2, ArrayList<View> arrayList2) {
        if (object != null || object2 != null) {
            if ((object = (Transition)object) != null) {
                object.addTarget(view2);
            }
            if (object2 != null) {
                FragmentTransitionCompat21.addTargets((Object)((Transition)object2), arrayList2);
            }
            if (viewRetriever != null) {
                view.getViewTreeObserver().addOnPreDrawListener((ViewTreeObserver.OnPreDrawListener)new FragmentTransitionCompat21$2(view, viewRetriever, map, map2, (Transition)object, arrayList, view2));
            }
            FragmentTransitionCompat21.setSharedElementEpicenter((Transition)object, epicenterView);
        }
    }

    public static void beginDelayedTransition(ViewGroup viewGroup, Object object) {
        TransitionManager.beginDelayedTransition((ViewGroup)viewGroup, (Transition)((Transition)object));
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static Object captureExitingViews(Object object, View view, ArrayList<View> arrayList, Map<String, View> map, View view2) {
        Object object2 = object;
        if (object == null) return object2;
        FragmentTransitionCompat21.captureTransitioningViews(arrayList, view);
        if (map != null) {
            arrayList.removeAll(map.values());
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        arrayList.add((Object)view2);
        FragmentTransitionCompat21.addTargets((Object)((Transition)object), arrayList);
        return object;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static void captureTransitioningViews(ArrayList<View> arrayList, View view) {
        if (view.getVisibility() != 0) return;
        if (view instanceof ViewGroup) {
            if ((view = (ViewGroup)view).isTransitionGroup()) {
                arrayList.add((Object)view);
                return;
            }
            int n2 = view.getChildCount();
            int n3 = 0;
            while (n3 < n2) {
                FragmentTransitionCompat21.captureTransitioningViews(arrayList, view.getChildAt(n3));
                ++n3;
            }
            return;
        }
        arrayList.add((Object)view);
    }

    public static void cleanupTransitions(View view, View view2, Object object, ArrayList<View> arrayList, Object object2, ArrayList<View> arrayList2, Object object3, ArrayList<View> arrayList3, Object object4, ArrayList<View> arrayList4, Map<String, View> map) {
        object = (Transition)object;
        object2 = (Transition)object2;
        object3 = (Transition)object3;
        if ((object4 = (Transition)object4) != null) {
            view.getViewTreeObserver().addOnPreDrawListener((ViewTreeObserver.OnPreDrawListener)new FragmentTransitionCompat21$4(view, (Transition)object, view2, arrayList, (Transition)object2, arrayList2, (Transition)object3, arrayList3, map, arrayList4, (Transition)object4));
        }
    }

    public static Object cloneTransition(Object object) {
        Object object2 = object;
        if (object != null) {
            object2 = ((Transition)object).clone();
        }
        return object2;
    }

    public static void excludeTarget(Object object, View view, boolean bl2) {
        ((Transition)object).excludeTarget(view, bl2);
    }

    public static void findNamedViews(Map<String, View> map, View view) {
        if (view.getVisibility() == 0) {
            String string2 = view.getTransitionName();
            if (string2 != null) {
                map.put(string2, view);
            }
            if (view instanceof ViewGroup) {
                view = (ViewGroup)view;
                int n2 = view.getChildCount();
                for (int i2 = 0; i2 < n2; ++i2) {
                    FragmentTransitionCompat21.findNamedViews(map, view.getChildAt(i2));
                }
            }
        }
    }

    private static Rect getBoundsOnScreen(View view) {
        Rect rect = new Rect();
        int[] arrn = new int[2];
        view.getLocationOnScreen(arrn);
        rect.set(arrn[0], arrn[1], arrn[0] + view.getWidth(), arrn[1] + view.getHeight());
        return rect;
    }

    public static String getTransitionName(View view) {
        return view.getTransitionName();
    }

    private static boolean hasSimpleTarget(Transition transition) {
        if (!(FragmentTransitionCompat21.isNullOrEmpty(transition.getTargetIds()) && FragmentTransitionCompat21.isNullOrEmpty(transition.getTargetNames()) && FragmentTransitionCompat21.isNullOrEmpty(transition.getTargetTypes()))) {
            return true;
        }
        return false;
    }

    private static boolean isNullOrEmpty(List list) {
        if (list == null || list.isEmpty()) {
            return true;
        }
        return false;
    }

    /*
     * Enabled aggressive block sorting
     */
    public static Object mergeTransitions(Object object, Object object2, Object object3, boolean bl2) {
        Transition transition = (Transition)object;
        object = (Transition)object2;
        object3 = (Transition)object3;
        if (transition == null || object == null) {
            bl2 = true;
        }
        if (bl2) {
            object2 = new TransitionSet();
            if (transition != null) {
                object2.addTransition(transition);
            }
            if (object != null) {
                object2.addTransition((Transition)object);
            }
            if (object3 != null) {
                object2.addTransition((Transition)object3);
            }
            return object2;
        }
        object2 = null;
        if (object != null && transition != null) {
            object = new TransitionSet().addTransition((Transition)object).addTransition(transition).setOrdering(1);
        } else if (object == null) {
            object = object2;
            if (transition != null) {
                object = transition;
            }
        }
        if (object3 == null) {
            return object;
        }
        object2 = new TransitionSet();
        if (object != null) {
            object2.addTransition((Transition)object);
        }
        object2.addTransition((Transition)object3);
        return object2;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static void removeTargets(Object object, ArrayList<View> arrayList) {
        List list;
        if ((object = (Transition)object) instanceof TransitionSet) {
            object = (TransitionSet)object;
            int n2 = object.getTransitionCount();
            int n3 = 0;
            while (n3 < n2) {
                FragmentTransitionCompat21.removeTargets((Object)object.getTransitionAt(n3), arrayList);
                ++n3;
            }
            return;
        }
        if (FragmentTransitionCompat21.hasSimpleTarget((Transition)object) || (list = object.getTargets()) == null || list.size() != arrayList.size() || !list.containsAll(arrayList)) return;
        for (int i2 = arrayList.size() - 1; i2 >= 0; --i2) {
            object.removeTarget((View)arrayList.get(i2));
        }
    }

    public static void setEpicenter(Object object, View view) {
        ((Transition)object).setEpicenterCallback((Transition.EpicenterCallback)new FragmentTransitionCompat21$1(FragmentTransitionCompat21.getBoundsOnScreen(view)));
    }

    private static void setSharedElementEpicenter(Transition transition, EpicenterView epicenterView) {
        if (transition != null) {
            transition.setEpicenterCallback((Transition.EpicenterCallback)new FragmentTransitionCompat21$3(epicenterView));
        }
    }

    public static class EpicenterView {
        public View epicenter;
    }

    public static interface ViewRetriever {
        public View getView();
    }

}

