/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.util.Log
 *  android.util.SparseArray
 *  android.view.View
 *  android.view.ViewGroup
 *  android.view.ViewTreeObserver
 *  android.view.ViewTreeObserver$OnPreDrawListener
 *  java.io.PrintWriter
 *  java.io.Writer
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.System
 *  java.util.ArrayList
 */
package android.support.v4.app;

import android.content.Context;
import android.os.Build;
import android.support.v4.app.BackStackRecord$1;
import android.support.v4.app.BackStackRecord$2;
import android.support.v4.app.BackStackRecord$3;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentContainer;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentManagerImpl;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentTransitionCompat21;
import android.support.v4.app.SharedElementCallback;
import android.support.v4.util.ArrayMap;
import android.support.v4.util.LogWriter;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

final class BackStackRecord
extends FragmentTransaction
implements FragmentManager.BackStackEntry,
Runnable {
    static final int OP_ADD = 1;
    static final int OP_ATTACH = 7;
    static final int OP_DETACH = 6;
    static final int OP_HIDE = 4;
    static final int OP_NULL = 0;
    static final int OP_REMOVE = 3;
    static final int OP_REPLACE = 2;
    static final int OP_SHOW = 5;
    static final boolean SUPPORTS_TRANSITIONS;
    static final String TAG = "FragmentManager";
    boolean mAddToBackStack;
    boolean mAllowAddToBackStack = true;
    int mBreadCrumbShortTitleRes;
    CharSequence mBreadCrumbShortTitleText;
    int mBreadCrumbTitleRes;
    CharSequence mBreadCrumbTitleText;
    boolean mCommitted;
    int mEnterAnim;
    int mExitAnim;
    Op mHead;
    int mIndex = -1;
    final FragmentManagerImpl mManager;
    String mName;
    int mNumOp;
    int mPopEnterAnim;
    int mPopExitAnim;
    ArrayList<String> mSharedElementSourceNames;
    ArrayList<String> mSharedElementTargetNames;
    Op mTail;
    int mTransition;
    int mTransitionStyle;

    /*
     * Enabled aggressive block sorting
     */
    static {
        boolean bl2 = Build.VERSION.SDK_INT >= 21;
        SUPPORTS_TRANSITIONS = bl2;
    }

    public BackStackRecord(FragmentManagerImpl fragmentManagerImpl) {
        this.mManager = fragmentManagerImpl;
    }

    static /* synthetic */ ArrayMap access$000(BackStackRecord backStackRecord, TransitionState transitionState, boolean bl2, Fragment fragment) {
        return backStackRecord.mapSharedElementsIn(transitionState, bl2, fragment);
    }

    static /* synthetic */ void access$100(BackStackRecord backStackRecord, ArrayMap arrayMap, TransitionState transitionState) {
        backStackRecord.setEpicenterIn(arrayMap, transitionState);
    }

    static /* synthetic */ void access$200(BackStackRecord backStackRecord, TransitionState transitionState, Fragment fragment, Fragment fragment2, boolean bl2, ArrayMap arrayMap) {
        backStackRecord.callSharedElementEnd(transitionState, fragment, fragment2, bl2, arrayMap);
    }

    static /* synthetic */ void access$300(BackStackRecord backStackRecord, TransitionState transitionState, int n2, Object object) {
        backStackRecord.excludeHiddenFragments(transitionState, n2, object);
    }

    private TransitionState beginTransition(SparseArray<Fragment> object, SparseArray<Fragment> sparseArray, boolean bl2) {
        boolean bl3;
        int n2 = 0;
        TransitionState transitionState = new TransitionState();
        transitionState.nonExistentView = new View((Context)this.mManager.mActivity);
        int n3 = 0;
        boolean bl4 = false;
        do {
            bl3 = bl4;
            if (n3 >= object.size()) break;
            if (this.configureTransitions(object.keyAt(n3), transitionState, bl2, (SparseArray<Fragment>)object, sparseArray)) {
                bl4 = true;
            }
            ++n3;
        } while (true);
        for (int i2 = n2; i2 < sparseArray.size(); ++i2) {
            n3 = sparseArray.keyAt(i2);
            bl4 = bl3;
            if (object.get(n3) == null) {
                bl4 = bl3;
                if (this.configureTransitions(n3, transitionState, bl2, (SparseArray<Fragment>)object, sparseArray)) {
                    bl4 = true;
                }
            }
            bl3 = bl4;
        }
        object = transitionState;
        if (!bl3) {
            object = null;
        }
        return object;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private void calculateFragments(SparseArray<Fragment> var1_1, SparseArray<Fragment> var2_2) {
        block17 : {
            if (!this.mManager.mContainer.hasView()) {
                return;
            }
            var5_3 = this.mHead;
            while (var5_3 != null) {
                switch (var5_3.cmd) {
                    case 1: {
                        this.setLastIn(var2_2, var5_3.fragment);
                        ** break;
                    }
                    case 2: {
                        var4_5 = var5_3.fragment;
                        if (this.mManager.mAdded != null) {
                            var3_4 = 0;
                            break block17;
                        }
                        var6_6 = var4_5;
lbl15: // 2 sources:
                        this.setLastIn(var2_2, var6_6);
                        ** break;
                    }
                    case 3: {
                        BackStackRecord.setFirstOut(var1_1, var5_3.fragment);
                        ** break;
                    }
                    case 4: {
                        BackStackRecord.setFirstOut(var1_1, var5_3.fragment);
                        ** break;
                    }
                    case 5: {
                        this.setLastIn(var2_2, var5_3.fragment);
                        ** break;
                    }
                    case 6: {
                        BackStackRecord.setFirstOut(var1_1, var5_3.fragment);
                    }
lbl28: // 7 sources:
                    default: {
                        ** GOTO lbl32
                    }
                    case 7: 
                }
                this.setLastIn(var2_2, var5_3.fragment);
lbl32: // 2 sources:
                var5_3 = var5_3.next;
            }
            return;
        }
        do {
            var6_6 = var4_5;
            if (var3_4 >= this.mManager.mAdded.size()) ** GOTO lbl15
            var7_7 = (Fragment)this.mManager.mAdded.get(var3_4);
            if (var4_5 == null) ** GOTO lbl-1000
            var6_6 = var4_5;
            if (var7_7.mContainerId == var4_5.mContainerId) lbl-1000: // 2 sources:
            {
                if (var7_7 == var4_5) {
                    var6_6 = null;
                } else {
                    BackStackRecord.setFirstOut(var1_1, var7_7);
                    var6_6 = var4_5;
                }
            }
            ++var3_4;
            var4_5 = var6_6;
        } while (true);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void callSharedElementEnd(TransitionState object, Fragment fragment, Fragment fragment2, boolean bl2, ArrayMap<String, View> arrayMap) {
        object = bl2 ? fragment2.mEnterTransitionCallback : fragment.mEnterTransitionCallback;
        if (object != null) {
            object.onSharedElementEnd((List<String>)new ArrayList(arrayMap.keySet()), (List<View>)new ArrayList(arrayMap.values()), null);
        }
    }

    private static Object captureExitingViews(Object object, Fragment fragment, ArrayList<View> arrayList, ArrayMap<String, View> arrayMap, View view) {
        Object object2 = object;
        if (object != null) {
            object2 = FragmentTransitionCompat21.captureExitingViews(object, fragment.getView(), arrayList, arrayMap, view);
        }
        return object2;
    }

    /*
     * Enabled aggressive block sorting
     */
    private boolean configureTransitions(int n2, TransitionState transitionState, boolean bl2, SparseArray<Fragment> object, SparseArray<Fragment> object2) {
        Object object3;
        ViewGroup viewGroup = (ViewGroup)this.mManager.mContainer.findViewById(n2);
        if (viewGroup == null) {
            return false;
        }
        Object object4 = (Fragment)object2.get(n2);
        Fragment fragment = (Fragment)object.get(n2);
        Object object5 = BackStackRecord.getEnterTransition((Fragment)object4, bl2);
        Object object6 = BackStackRecord.getSharedElementTransition((Fragment)object4, fragment, bl2);
        ArrayMap<String, View> arrayMap = BackStackRecord.getExitTransition(fragment, bl2);
        if (object5 == null && object6 == null && arrayMap == null) {
            return false;
        }
        object = null;
        ArrayList arrayList = new ArrayList();
        if (object6 != null) {
            object3 = this.remapSharedElements(transitionState, fragment, bl2);
            arrayList.add((Object)transitionState.nonExistentView);
            arrayList.addAll(object3.values());
            object2 = bl2 ? fragment.mEnterTransitionCallback : object4.mEnterTransitionCallback;
            object = object3;
            if (object2 != null) {
                object2.onSharedElementStart((List<String>)new ArrayList(object3.keySet()), (List<View>)new ArrayList(object3.values()), null);
                object = object3;
            }
        }
        object2 = new ArrayList();
        object3 = BackStackRecord.captureExitingViews(arrayMap, fragment, object2, object, transitionState.nonExistentView);
        if (this.mSharedElementTargetNames != null && object != null && (object = (View)object.get(this.mSharedElementTargetNames.get(0))) != null) {
            if (object3 != null) {
                FragmentTransitionCompat21.setEpicenter(object3, (View)object);
            }
            if (object6 != null) {
                FragmentTransitionCompat21.setEpicenter(object6, (View)object);
            }
        }
        object = new BackStackRecord$1(this, (Fragment)object4);
        if (object6 != null) {
            this.prepareSharedElementTransition(transitionState, (View)viewGroup, object6, (Fragment)object4, fragment, bl2, arrayList);
        }
        fragment = new ArrayList();
        arrayMap = new ArrayMap<String, View>();
        bl2 = bl2 ? object4.getAllowReturnTransitionOverlap() : object4.getAllowEnterTransitionOverlap();
        object4 = FragmentTransitionCompat21.mergeTransitions(object5, object3, object6, bl2);
        if (object4 != null) {
            FragmentTransitionCompat21.addTransitionTargets(object5, object6, (View)viewGroup, (FragmentTransitionCompat21.ViewRetriever)object, transitionState.nonExistentView, transitionState.enteringEpicenterView, transitionState.nameOverrides, fragment, arrayMap, arrayList);
            this.excludeHiddenFragmentsAfterEnter((View)viewGroup, transitionState, n2, object4);
            FragmentTransitionCompat21.excludeTarget(object4, transitionState.nonExistentView, true);
            this.excludeHiddenFragments(transitionState, n2, object4);
            FragmentTransitionCompat21.beginDelayedTransition(viewGroup, object4);
            FragmentTransitionCompat21.cleanupTransitions((View)viewGroup, transitionState.nonExistentView, object5, fragment, object3, object2, object6, arrayList, object4, transitionState.hiddenFragmentViews, arrayMap);
        }
        if (object4 != null) {
            return true;
        }
        return false;
    }

    private void doAddOp(int n2, Fragment fragment, String object, int n3) {
        fragment.mFragmentManager = this.mManager;
        if (object != null) {
            if (fragment.mTag != null && !object.equals((Object)fragment.mTag)) {
                throw new IllegalStateException("Can't change tag of fragment " + fragment + ": was " + fragment.mTag + " now " + (String)object);
            }
            fragment.mTag = object;
        }
        if (n2 != 0) {
            if (fragment.mFragmentId != 0 && fragment.mFragmentId != n2) {
                throw new IllegalStateException("Can't change container ID of fragment " + fragment + ": was " + fragment.mFragmentId + " now " + n2);
            }
            fragment.mFragmentId = n2;
            fragment.mContainerId = n2;
        }
        object = new Op();
        object.cmd = n3;
        object.fragment = fragment;
        this.addOp((Op)object);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void excludeHiddenFragments(TransitionState transitionState, int n2, Object object) {
        if (this.mManager.mAdded == null) {
            return;
        }
        int n3 = 0;
        while (n3 < this.mManager.mAdded.size()) {
            Fragment fragment = (Fragment)this.mManager.mAdded.get(n3);
            if (fragment.mView != null && fragment.mContainer != null && fragment.mContainerId == n2) {
                if (fragment.mHidden) {
                    if (!transitionState.hiddenFragmentViews.contains((Object)fragment.mView)) {
                        FragmentTransitionCompat21.excludeTarget(object, fragment.mView, true);
                        transitionState.hiddenFragmentViews.add((Object)fragment.mView);
                    }
                } else {
                    FragmentTransitionCompat21.excludeTarget(object, fragment.mView, false);
                    transitionState.hiddenFragmentViews.remove((Object)fragment.mView);
                }
            }
            ++n3;
        }
    }

    private void excludeHiddenFragmentsAfterEnter(View view, TransitionState transitionState, int n2, Object object) {
        view.getViewTreeObserver().addOnPreDrawListener((ViewTreeObserver.OnPreDrawListener)new BackStackRecord$3(this, view, transitionState, n2, object));
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static Object getEnterTransition(Fragment object, boolean bl2) {
        if (object == null) {
            return null;
        }
        if (bl2) {
            object = object.getReenterTransition();
            do {
                return FragmentTransitionCompat21.cloneTransition(object);
                break;
            } while (true);
        }
        object = object.getEnterTransition();
        return FragmentTransitionCompat21.cloneTransition(object);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static Object getExitTransition(Fragment object, boolean bl2) {
        if (object == null) {
            return null;
        }
        if (bl2) {
            object = object.getReturnTransition();
            do {
                return FragmentTransitionCompat21.cloneTransition(object);
                break;
            } while (true);
        }
        object = object.getExitTransition();
        return FragmentTransitionCompat21.cloneTransition(object);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static Object getSharedElementTransition(Fragment object, Fragment fragment, boolean bl2) {
        if (object == null || fragment == null) {
            return null;
        }
        if (bl2) {
            object = fragment.getSharedElementReturnTransition();
            do {
                return FragmentTransitionCompat21.cloneTransition(object);
                break;
            } while (true);
        }
        object = object.getSharedElementEnterTransition();
        return FragmentTransitionCompat21.cloneTransition(object);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private ArrayMap<String, View> mapEnteringSharedElements(TransitionState arrayMap, Fragment fragment, boolean bl2) {
        ArrayMap<String, View> arrayMap2 = new ArrayMap<String, View>();
        fragment = fragment.getView();
        arrayMap = arrayMap2;
        if (fragment == null) return arrayMap;
        arrayMap = arrayMap2;
        if (this.mSharedElementSourceNames == null) return arrayMap;
        FragmentTransitionCompat21.findNamedViews(arrayMap2, (View)fragment);
        if (bl2) {
            return BackStackRecord.remapNames(this.mSharedElementSourceNames, this.mSharedElementTargetNames, arrayMap2);
        }
        arrayMap2.retainAll(this.mSharedElementTargetNames);
        return arrayMap2;
    }

    private ArrayMap<String, View> mapSharedElementsIn(TransitionState transitionState, boolean bl2, Fragment fragment) {
        ArrayMap<String, View> arrayMap = this.mapEnteringSharedElements(transitionState, fragment, bl2);
        if (bl2) {
            if (fragment.mExitTransitionCallback != null) {
                fragment.mExitTransitionCallback.onMapSharedElements((List<String>)this.mSharedElementTargetNames, (Map<String, View>)arrayMap);
            }
            this.setBackNameOverrides(transitionState, arrayMap, true);
            return arrayMap;
        }
        if (fragment.mEnterTransitionCallback != null) {
            fragment.mEnterTransitionCallback.onMapSharedElements((List<String>)this.mSharedElementTargetNames, (Map<String, View>)arrayMap);
        }
        this.setNameOverrides(transitionState, arrayMap, true);
        return arrayMap;
    }

    private void prepareSharedElementTransition(TransitionState transitionState, View view, Object object, Fragment fragment, Fragment fragment2, boolean bl2, ArrayList<View> arrayList) {
        view.getViewTreeObserver().addOnPreDrawListener((ViewTreeObserver.OnPreDrawListener)new BackStackRecord$2(this, view, object, arrayList, transitionState, bl2, fragment, fragment2));
    }

    private static ArrayMap<String, View> remapNames(ArrayList<String> arrayList, ArrayList<String> arrayList2, ArrayMap<String, View> arrayMap) {
        if (arrayMap.isEmpty()) {
            return arrayMap;
        }
        ArrayMap<String, View> arrayMap2 = new ArrayMap<String, View>();
        int n2 = arrayList.size();
        for (int i2 = 0; i2 < n2; ++i2) {
            View view = arrayMap.get(arrayList.get(i2));
            if (view == null) continue;
            arrayMap2.put((String)arrayList2.get(i2), view);
        }
        return arrayMap2;
    }

    /*
     * Enabled aggressive block sorting
     */
    private ArrayMap<String, View> remapSharedElements(TransitionState transitionState, Fragment fragment, boolean bl2) {
        ArrayMap<String, View> arrayMap;
        ArrayMap<String, View> arrayMap2 = arrayMap = new ArrayMap<String, View>();
        if (this.mSharedElementSourceNames != null) {
            FragmentTransitionCompat21.findNamedViews(arrayMap, fragment.getView());
            if (bl2) {
                arrayMap.retainAll(this.mSharedElementTargetNames);
                arrayMap2 = arrayMap;
            } else {
                arrayMap2 = BackStackRecord.remapNames(this.mSharedElementSourceNames, this.mSharedElementTargetNames, arrayMap);
            }
        }
        if (bl2) {
            if (fragment.mEnterTransitionCallback != null) {
                fragment.mEnterTransitionCallback.onMapSharedElements((List<String>)this.mSharedElementTargetNames, (Map<String, View>)arrayMap2);
            }
            this.setBackNameOverrides(transitionState, arrayMap2, false);
            return arrayMap2;
        }
        if (fragment.mExitTransitionCallback != null) {
            fragment.mExitTransitionCallback.onMapSharedElements((List<String>)this.mSharedElementTargetNames, (Map<String, View>)arrayMap2);
        }
        this.setNameOverrides(transitionState, arrayMap2, false);
        return arrayMap2;
    }

    /*
     * Enabled aggressive block sorting
     */
    private void setBackNameOverrides(TransitionState transitionState, ArrayMap<String, View> arrayMap, boolean bl2) {
        int n2 = this.mSharedElementTargetNames == null ? 0 : this.mSharedElementTargetNames.size();
        int n3 = 0;
        while (n3 < n2) {
            String string2 = (String)this.mSharedElementSourceNames.get(n3);
            Object object = arrayMap.get((String)this.mSharedElementTargetNames.get(n3));
            if (object != null) {
                object = FragmentTransitionCompat21.getTransitionName((View)object);
                if (bl2) {
                    BackStackRecord.setNameOverride(transitionState.nameOverrides, string2, (String)object);
                } else {
                    BackStackRecord.setNameOverride(transitionState.nameOverrides, (String)object, string2);
                }
            }
            ++n3;
        }
    }

    private void setEpicenterIn(ArrayMap<String, View> view, TransitionState transitionState) {
        if (this.mSharedElementTargetNames != null && !view.isEmpty() && (view = view.get(this.mSharedElementTargetNames.get(0))) != null) {
            transitionState.enteringEpicenterView.epicenter = view;
        }
    }

    private static void setFirstOut(SparseArray<Fragment> sparseArray, Fragment fragment) {
        int n2;
        if (fragment != null && (n2 = fragment.mContainerId) != 0 && !fragment.isHidden() && fragment.isAdded() && fragment.getView() != null && sparseArray.get(n2) == null) {
            sparseArray.put(n2, (Object)fragment);
        }
    }

    private void setLastIn(SparseArray<Fragment> sparseArray, Fragment fragment) {
        int n2;
        if (fragment != null && (n2 = fragment.mContainerId) != 0) {
            sparseArray.put(n2, (Object)fragment);
        }
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static void setNameOverride(ArrayMap<String, String> arrayMap, String string2, String string3) {
        if (string2 == null || string3 == null || string2.equals((Object)string3)) return;
        for (int i2 = 0; i2 < arrayMap.size(); ++i2) {
            if (!string2.equals((Object)arrayMap.valueAt(i2))) continue;
            arrayMap.setValueAt(i2, string3);
            return;
        }
        arrayMap.put(string2, string3);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void setNameOverrides(TransitionState transitionState, ArrayMap<String, View> arrayMap, boolean bl2) {
        int n2 = arrayMap.size();
        int n3 = 0;
        while (n3 < n2) {
            String string2 = arrayMap.keyAt(n3);
            String string3 = FragmentTransitionCompat21.getTransitionName(arrayMap.valueAt(n3));
            if (bl2) {
                BackStackRecord.setNameOverride(transitionState.nameOverrides, string2, string3);
            } else {
                BackStackRecord.setNameOverride(transitionState.nameOverrides, string3, string2);
            }
            ++n3;
        }
    }

    private static void setNameOverrides(TransitionState transitionState, ArrayList<String> arrayList, ArrayList<String> arrayList2) {
        if (arrayList != null) {
            for (int i2 = 0; i2 < arrayList.size(); ++i2) {
                String string2 = (String)arrayList.get(i2);
                String string3 = (String)arrayList2.get(i2);
                BackStackRecord.setNameOverride(transitionState.nameOverrides, string2, string3);
            }
        }
    }

    @Override
    public FragmentTransaction add(int n2, Fragment fragment) {
        this.doAddOp(n2, fragment, null, 1);
        return this;
    }

    @Override
    public FragmentTransaction add(int n2, Fragment fragment, String string2) {
        this.doAddOp(n2, fragment, string2, 1);
        return this;
    }

    @Override
    public FragmentTransaction add(Fragment fragment, String string2) {
        this.doAddOp(0, fragment, string2, 1);
        return this;
    }

    /*
     * Enabled aggressive block sorting
     */
    void addOp(Op op2) {
        if (this.mHead == null) {
            this.mTail = op2;
            this.mHead = op2;
        } else {
            op2.prev = this.mTail;
            this.mTail.next = op2;
            this.mTail = op2;
        }
        op2.enterAnim = this.mEnterAnim;
        op2.exitAnim = this.mExitAnim;
        op2.popEnterAnim = this.mPopEnterAnim;
        op2.popExitAnim = this.mPopExitAnim;
        ++this.mNumOp;
    }

    @Override
    public FragmentTransaction addSharedElement(View object, String string2) {
        if (SUPPORTS_TRANSITIONS) {
            if ((object = FragmentTransitionCompat21.getTransitionName((View)object)) == null) {
                throw new IllegalArgumentException("Unique transitionNames are required for all sharedElements");
            }
            if (this.mSharedElementSourceNames == null) {
                this.mSharedElementSourceNames = new ArrayList();
                this.mSharedElementTargetNames = new ArrayList();
            }
            this.mSharedElementSourceNames.add(object);
            this.mSharedElementTargetNames.add((Object)string2);
        }
        return this;
    }

    @Override
    public FragmentTransaction addToBackStack(String string2) {
        if (!this.mAllowAddToBackStack) {
            throw new IllegalStateException("This FragmentTransaction is not allowed to be added to the back stack.");
        }
        this.mAddToBackStack = true;
        this.mName = string2;
        return this;
    }

    @Override
    public FragmentTransaction attach(Fragment fragment) {
        Op op2 = new Op();
        op2.cmd = 7;
        op2.fragment = fragment;
        this.addOp(op2);
        return this;
    }

    /*
     * Enabled aggressive block sorting
     */
    void bumpBackStackNesting(int n2) {
        if (this.mAddToBackStack) {
            if (FragmentManagerImpl.DEBUG) {
                Log.v((String)"FragmentManager", (String)("Bump nesting in " + this + " by " + n2));
            }
            Op op2 = this.mHead;
            while (op2 != null) {
                Fragment fragment;
                if (op2.fragment != null) {
                    fragment = op2.fragment;
                    fragment.mBackStackNesting += n2;
                    if (FragmentManagerImpl.DEBUG) {
                        Log.v((String)"FragmentManager", (String)("Bump nesting of " + op2.fragment + " to " + op2.fragment.mBackStackNesting));
                    }
                }
                if (op2.removed != null) {
                    for (int i2 = op2.removed.size() - 1; i2 >= 0; --i2) {
                        fragment = (Fragment)op2.removed.get(i2);
                        fragment.mBackStackNesting += n2;
                        if (!FragmentManagerImpl.DEBUG) continue;
                        Log.v((String)"FragmentManager", (String)("Bump nesting of " + fragment + " to " + fragment.mBackStackNesting));
                    }
                }
                op2 = op2.next;
            }
        }
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public void calculateBackFragments(SparseArray<Fragment> var1_1, SparseArray<Fragment> var2_2) {
        if (!this.mManager.mContainer.hasView()) {
            return;
        }
        var4_3 = this.mHead;
        while (var4_3 != null) {
            switch (var4_3.cmd) {
                case 1: {
                    BackStackRecord.setFirstOut(var1_1, var4_3.fragment);
                    ** break;
                }
                case 2: {
                    if (var4_3.removed != null) {
                        for (var3_4 = var4_3.removed.size() - 1; var3_4 >= 0; --var3_4) {
                            this.setLastIn(var2_2, (Fragment)var4_3.removed.get(var3_4));
                        }
                    }
                    BackStackRecord.setFirstOut(var1_1, var4_3.fragment);
                    ** break;
                }
                case 3: {
                    this.setLastIn(var2_2, var4_3.fragment);
                    ** break;
                }
                case 4: {
                    this.setLastIn(var2_2, var4_3.fragment);
                    ** break;
                }
                case 5: {
                    BackStackRecord.setFirstOut(var1_1, var4_3.fragment);
                    ** break;
                }
                case 6: {
                    this.setLastIn(var2_2, var4_3.fragment);
                }
lbl27: // 7 sources:
                default: {
                    ** GOTO lbl31
                }
                case 7: 
            }
            BackStackRecord.setFirstOut(var1_1, var4_3.fragment);
lbl31: // 2 sources:
            var4_3 = var4_3.next;
        }
    }

    @Override
    public int commit() {
        return this.commitInternal(false);
    }

    @Override
    public int commitAllowingStateLoss() {
        return this.commitInternal(true);
    }

    /*
     * Enabled aggressive block sorting
     */
    int commitInternal(boolean bl2) {
        if (this.mCommitted) {
            throw new IllegalStateException("commit already called");
        }
        if (FragmentManagerImpl.DEBUG) {
            Log.v((String)"FragmentManager", (String)("Commit: " + this));
            this.dump("  ", null, new PrintWriter((Writer)new LogWriter("FragmentManager")), null);
        }
        this.mCommitted = true;
        this.mIndex = this.mAddToBackStack ? this.mManager.allocBackStackIndex(this) : -1;
        this.mManager.enqueueAction(this, bl2);
        return this.mIndex;
    }

    @Override
    public FragmentTransaction detach(Fragment fragment) {
        Op op2 = new Op();
        op2.cmd = 6;
        op2.fragment = fragment;
        this.addOp(op2);
        return this;
    }

    @Override
    public FragmentTransaction disallowAddToBackStack() {
        if (this.mAddToBackStack) {
            throw new IllegalStateException("This transaction is already being added to the back stack");
        }
        this.mAllowAddToBackStack = false;
        return this;
    }

    public void dump(String string2, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] arrstring) {
        this.dump(string2, printWriter, true);
    }

    /*
     * Enabled aggressive block sorting
     */
    public void dump(String string2, PrintWriter printWriter, boolean bl2) {
        if (bl2) {
            printWriter.print(string2);
            printWriter.print("mName=");
            printWriter.print(this.mName);
            printWriter.print(" mIndex=");
            printWriter.print(this.mIndex);
            printWriter.print(" mCommitted=");
            printWriter.println(this.mCommitted);
            if (this.mTransition != 0) {
                printWriter.print(string2);
                printWriter.print("mTransition=#");
                printWriter.print(Integer.toHexString((int)this.mTransition));
                printWriter.print(" mTransitionStyle=#");
                printWriter.println(Integer.toHexString((int)this.mTransitionStyle));
            }
            if (this.mEnterAnim != 0 || this.mExitAnim != 0) {
                printWriter.print(string2);
                printWriter.print("mEnterAnim=#");
                printWriter.print(Integer.toHexString((int)this.mEnterAnim));
                printWriter.print(" mExitAnim=#");
                printWriter.println(Integer.toHexString((int)this.mExitAnim));
            }
            if (this.mPopEnterAnim != 0 || this.mPopExitAnim != 0) {
                printWriter.print(string2);
                printWriter.print("mPopEnterAnim=#");
                printWriter.print(Integer.toHexString((int)this.mPopEnterAnim));
                printWriter.print(" mPopExitAnim=#");
                printWriter.println(Integer.toHexString((int)this.mPopExitAnim));
            }
            if (this.mBreadCrumbTitleRes != 0 || this.mBreadCrumbTitleText != null) {
                printWriter.print(string2);
                printWriter.print("mBreadCrumbTitleRes=#");
                printWriter.print(Integer.toHexString((int)this.mBreadCrumbTitleRes));
                printWriter.print(" mBreadCrumbTitleText=");
                printWriter.println((Object)this.mBreadCrumbTitleText);
            }
            if (this.mBreadCrumbShortTitleRes != 0 || this.mBreadCrumbShortTitleText != null) {
                printWriter.print(string2);
                printWriter.print("mBreadCrumbShortTitleRes=#");
                printWriter.print(Integer.toHexString((int)this.mBreadCrumbShortTitleRes));
                printWriter.print(" mBreadCrumbShortTitleText=");
                printWriter.println((Object)this.mBreadCrumbShortTitleText);
            }
        }
        if (this.mHead == null) {
            return;
        }
        printWriter.print(string2);
        printWriter.println("Operations:");
        String string3 = string2 + "    ";
        Op op2 = this.mHead;
        int n2 = 0;
        while (op2 != null) {
            String string4;
            switch (op2.cmd) {
                default: {
                    string4 = "cmd=" + op2.cmd;
                    break;
                }
                case 0: {
                    string4 = "NULL";
                    break;
                }
                case 1: {
                    string4 = "ADD";
                    break;
                }
                case 2: {
                    string4 = "REPLACE";
                    break;
                }
                case 3: {
                    string4 = "REMOVE";
                    break;
                }
                case 4: {
                    string4 = "HIDE";
                    break;
                }
                case 5: {
                    string4 = "SHOW";
                    break;
                }
                case 6: {
                    string4 = "DETACH";
                    break;
                }
                case 7: {
                    string4 = "ATTACH";
                }
            }
            printWriter.print(string2);
            printWriter.print("  Op #");
            printWriter.print(n2);
            printWriter.print(": ");
            printWriter.print(string4);
            printWriter.print(" ");
            printWriter.println((Object)op2.fragment);
            if (bl2) {
                if (op2.enterAnim != 0 || op2.exitAnim != 0) {
                    printWriter.print(string2);
                    printWriter.print("enterAnim=#");
                    printWriter.print(Integer.toHexString((int)op2.enterAnim));
                    printWriter.print(" exitAnim=#");
                    printWriter.println(Integer.toHexString((int)op2.exitAnim));
                }
                if (op2.popEnterAnim != 0 || op2.popExitAnim != 0) {
                    printWriter.print(string2);
                    printWriter.print("popEnterAnim=#");
                    printWriter.print(Integer.toHexString((int)op2.popEnterAnim));
                    printWriter.print(" popExitAnim=#");
                    printWriter.println(Integer.toHexString((int)op2.popExitAnim));
                }
            }
            if (op2.removed != null && op2.removed.size() > 0) {
                for (int i2 = 0; i2 < op2.removed.size(); ++i2) {
                    printWriter.print(string3);
                    if (op2.removed.size() == 1) {
                        printWriter.print("Removed: ");
                    } else {
                        if (i2 == 0) {
                            printWriter.println("Removed:");
                        }
                        printWriter.print(string3);
                        printWriter.print("  #");
                        printWriter.print(i2);
                        printWriter.print(": ");
                    }
                    printWriter.println(op2.removed.get(i2));
                }
            }
            op2 = op2.next;
            ++n2;
        }
    }

    @Override
    public CharSequence getBreadCrumbShortTitle() {
        if (this.mBreadCrumbShortTitleRes != 0) {
            return this.mManager.mActivity.getText(this.mBreadCrumbShortTitleRes);
        }
        return this.mBreadCrumbShortTitleText;
    }

    @Override
    public int getBreadCrumbShortTitleRes() {
        return this.mBreadCrumbShortTitleRes;
    }

    @Override
    public CharSequence getBreadCrumbTitle() {
        if (this.mBreadCrumbTitleRes != 0) {
            return this.mManager.mActivity.getText(this.mBreadCrumbTitleRes);
        }
        return this.mBreadCrumbTitleText;
    }

    @Override
    public int getBreadCrumbTitleRes() {
        return this.mBreadCrumbTitleRes;
    }

    @Override
    public int getId() {
        return this.mIndex;
    }

    @Override
    public String getName() {
        return this.mName;
    }

    public int getTransition() {
        return this.mTransition;
    }

    public int getTransitionStyle() {
        return this.mTransitionStyle;
    }

    @Override
    public FragmentTransaction hide(Fragment fragment) {
        Op op2 = new Op();
        op2.cmd = 4;
        op2.fragment = fragment;
        this.addOp(op2);
        return this;
    }

    @Override
    public boolean isAddToBackStackAllowed() {
        return this.mAllowAddToBackStack;
    }

    @Override
    public boolean isEmpty() {
        if (this.mNumOp == 0) {
            return true;
        }
        return false;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    public TransitionState popFromBackStack(boolean var1_1, TransitionState var2_2, SparseArray<Fragment> var3_3, SparseArray<Fragment> var4_4) {
        if (FragmentManagerImpl.DEBUG) {
            Log.v((String)"FragmentManager", (String)("popFromBackStack: " + this));
            this.dump("  ", null, new PrintWriter((Writer)new LogWriter("FragmentManager")), null);
        }
        var9_5 = var2_2;
        if (!BackStackRecord.SUPPORTS_TRANSITIONS) ** GOTO lbl16
        if (var2_2 != null) ** GOTO lbl12
        if (var3_3.size() != 0) ** GOTO lbl-1000
        var9_5 = var2_2;
        if (var4_4.size() != 0) lbl-1000: // 2 sources:
        {
            var9_5 = this.beginTransition((SparseArray<Fragment>)var3_3, var4_4, true);
        }
        ** GOTO lbl16
lbl12: // 1 sources:
        var9_5 = var2_2;
        if (!var1_1) {
            BackStackRecord.setNameOverrides((TransitionState)var2_2, this.mSharedElementTargetNames, this.mSharedElementSourceNames);
            var9_5 = var2_2;
        }
lbl16: // 5 sources:
        this.bumpBackStackNesting(-1);
        var5_6 = var9_5 != null ? 0 : this.mTransitionStyle;
        var6_7 = var9_5 != null ? 0 : this.mTransition;
        var2_2 = this.mTail;
        while (var2_2 != null) {
            var7_8 = var9_5 != null ? 0 : var2_2.popEnterAnim;
            var8_9 = var9_5 != null ? 0 : var2_2.popExitAnim;
            switch (var2_2.cmd) {
                default: {
                    throw new IllegalArgumentException("Unknown cmd: " + var2_2.cmd);
                }
                case 1: {
                    var3_3 = var2_2.fragment;
                    var3_3.mNextAnim = var8_9;
                    this.mManager.removeFragment((Fragment)var3_3, FragmentManagerImpl.reverseTransit(var6_7), var5_6);
                    break;
                }
                case 2: {
                    var3_3 = var2_2.fragment;
                    if (var3_3 != null) {
                        var3_3.mNextAnim = var8_9;
                        this.mManager.removeFragment((Fragment)var3_3, FragmentManagerImpl.reverseTransit(var6_7), var5_6);
                    }
                    if (var2_2.removed == null) break;
                    for (var8_9 = 0; var8_9 < var2_2.removed.size(); ++var8_9) {
                        var3_3 = (Fragment)var2_2.removed.get(var8_9);
                        var3_3.mNextAnim = var7_8;
                        this.mManager.addFragment((Fragment)var3_3, false);
                    }
                    break;
                }
                case 3: {
                    var3_3 = var2_2.fragment;
                    var3_3.mNextAnim = var7_8;
                    this.mManager.addFragment((Fragment)var3_3, false);
                    break;
                }
                case 4: {
                    var3_3 = var2_2.fragment;
                    var3_3.mNextAnim = var7_8;
                    this.mManager.showFragment((Fragment)var3_3, FragmentManagerImpl.reverseTransit(var6_7), var5_6);
                    break;
                }
                case 5: {
                    var3_3 = var2_2.fragment;
                    var3_3.mNextAnim = var8_9;
                    this.mManager.hideFragment((Fragment)var3_3, FragmentManagerImpl.reverseTransit(var6_7), var5_6);
                    break;
                }
                case 6: {
                    var3_3 = var2_2.fragment;
                    var3_3.mNextAnim = var7_8;
                    this.mManager.attachFragment((Fragment)var3_3, FragmentManagerImpl.reverseTransit(var6_7), var5_6);
                    break;
                }
                case 7: {
                    var3_3 = var2_2.fragment;
                    var3_3.mNextAnim = var7_8;
                    this.mManager.detachFragment((Fragment)var3_3, FragmentManagerImpl.reverseTransit(var6_7), var5_6);
                }
            }
            var2_2 = var2_2.prev;
        }
        if (var1_1) {
            this.mManager.moveToState(this.mManager.mCurState, FragmentManagerImpl.reverseTransit(var6_7), var5_6, true);
            var9_5 = null;
        }
        if (this.mIndex < 0) return var9_5;
        this.mManager.freeBackStackIndex(this.mIndex);
        this.mIndex = -1;
        return var9_5;
    }

    @Override
    public FragmentTransaction remove(Fragment fragment) {
        Op op2 = new Op();
        op2.cmd = 3;
        op2.fragment = fragment;
        this.addOp(op2);
        return this;
    }

    @Override
    public FragmentTransaction replace(int n2, Fragment fragment) {
        return this.replace(n2, fragment, null);
    }

    @Override
    public FragmentTransaction replace(int n2, Fragment fragment, String string2) {
        if (n2 == 0) {
            throw new IllegalArgumentException("Must use non-zero containerViewId");
        }
        this.doAddOp(n2, fragment, string2, 2);
        return this;
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    @Override
    public void run() {
        if (FragmentManagerImpl.DEBUG) {
            Log.v((String)"FragmentManager", (String)("Run: " + this));
        }
        if (this.mAddToBackStack && this.mIndex < 0) {
            throw new IllegalStateException("addToBackStack() called after commit()");
        }
        this.bumpBackStackNesting(1);
        if (BackStackRecord.SUPPORTS_TRANSITIONS) {
            var6_1 = new SparseArray();
            var7_2 = new SparseArray();
            this.calculateFragments((SparseArray<Fragment>)var6_1, (SparseArray<Fragment>)var7_2);
            var7_2 = this.beginTransition((SparseArray<Fragment>)var6_1, (SparseArray<Fragment>)var7_2, false);
        } else {
            var7_2 = null;
        }
        var1_3 = var7_2 != null ? 0 : this.mTransitionStyle;
        var2_4 = var7_2 != null ? 0 : this.mTransition;
        var8_5 = this.mHead;
        block9 : do {
            if (var8_5 == null) {
                this.mManager.moveToState(this.mManager.mCurState, var2_4, var1_3, true);
                if (this.mAddToBackStack == false) return;
                this.mManager.addBackStackState(this);
                return;
            }
            var3_6 = var7_2 != null ? 0 : var8_5.enterAnim;
            var4_7 = var7_2 != null ? 0 : var8_5.exitAnim;
            switch (var8_5.cmd) {
                default: {
                    throw new IllegalArgumentException("Unknown cmd: " + var8_5.cmd);
                }
                case 1: {
                    var6_1 = var8_5.fragment;
                    var6_1.mNextAnim = var3_6;
                    this.mManager.addFragment((Fragment)var6_1, false);
                    break;
                }
                case 2: {
                    var6_1 = var8_5.fragment;
                    if (this.mManager.mAdded != null) {
                        var5_8 = 0;
                        break block9;
                    }
                    var9_9 = var6_1;
lbl38: // 2 sources:
                    if (var9_9 == null) break;
                    var9_9.mNextAnim = var3_6;
                    this.mManager.addFragment((Fragment)var9_9, false);
                    break;
                }
                case 3: {
                    var6_1 = var8_5.fragment;
                    var6_1.mNextAnim = var4_7;
                    this.mManager.removeFragment((Fragment)var6_1, var2_4, var1_3);
                    break;
                }
                case 4: {
                    var6_1 = var8_5.fragment;
                    var6_1.mNextAnim = var4_7;
                    this.mManager.hideFragment((Fragment)var6_1, var2_4, var1_3);
                    break;
                }
                case 5: {
                    var6_1 = var8_5.fragment;
                    var6_1.mNextAnim = var3_6;
                    this.mManager.showFragment((Fragment)var6_1, var2_4, var1_3);
                    break;
                }
                case 6: {
                    var6_1 = var8_5.fragment;
                    var6_1.mNextAnim = var4_7;
                    this.mManager.detachFragment((Fragment)var6_1, var2_4, var1_3);
                    break;
                }
                case 7: {
                    var6_1 = var8_5.fragment;
                    var6_1.mNextAnim = var3_6;
                    this.mManager.attachFragment((Fragment)var6_1, var2_4, var1_3);
                }
            }
            var8_5 = var8_5.next;
        } while (true);
        do {
            var9_9 = var6_1;
            if (var5_8 >= this.mManager.mAdded.size()) ** GOTO lbl38
            var10_10 = (Fragment)this.mManager.mAdded.get(var5_8);
            if (FragmentManagerImpl.DEBUG) {
                Log.v((String)"FragmentManager", (String)("OP_REPLACE: adding=" + var6_1 + " old=" + var10_10));
            }
            if (var6_1 == null) ** GOTO lbl-1000
            var9_9 = var6_1;
            if (var10_10.mContainerId == var6_1.mContainerId) lbl-1000: // 2 sources:
            {
                if (var10_10 == var6_1) {
                    var8_5.fragment = null;
                    var9_9 = null;
                } else {
                    if (var8_5.removed == null) {
                        var8_5.removed = new ArrayList();
                    }
                    var8_5.removed.add((Object)var10_10);
                    var10_10.mNextAnim = var4_7;
                    if (this.mAddToBackStack) {
                        ++var10_10.mBackStackNesting;
                        if (FragmentManagerImpl.DEBUG) {
                            Log.v((String)"FragmentManager", (String)("Bump nesting of " + var10_10 + " to " + var10_10.mBackStackNesting));
                        }
                    }
                    this.mManager.removeFragment(var10_10, var2_4, var1_3);
                    var9_9 = var6_1;
                }
            }
            ++var5_8;
            var6_1 = var9_9;
        } while (true);
    }

    @Override
    public FragmentTransaction setBreadCrumbShortTitle(int n2) {
        this.mBreadCrumbShortTitleRes = n2;
        this.mBreadCrumbShortTitleText = null;
        return this;
    }

    @Override
    public FragmentTransaction setBreadCrumbShortTitle(CharSequence charSequence) {
        this.mBreadCrumbShortTitleRes = 0;
        this.mBreadCrumbShortTitleText = charSequence;
        return this;
    }

    @Override
    public FragmentTransaction setBreadCrumbTitle(int n2) {
        this.mBreadCrumbTitleRes = n2;
        this.mBreadCrumbTitleText = null;
        return this;
    }

    @Override
    public FragmentTransaction setBreadCrumbTitle(CharSequence charSequence) {
        this.mBreadCrumbTitleRes = 0;
        this.mBreadCrumbTitleText = charSequence;
        return this;
    }

    @Override
    public FragmentTransaction setCustomAnimations(int n2, int n3) {
        return this.setCustomAnimations(n2, n3, 0, 0);
    }

    @Override
    public FragmentTransaction setCustomAnimations(int n2, int n3, int n4, int n5) {
        this.mEnterAnim = n2;
        this.mExitAnim = n3;
        this.mPopEnterAnim = n4;
        this.mPopExitAnim = n5;
        return this;
    }

    @Override
    public FragmentTransaction setTransition(int n2) {
        this.mTransition = n2;
        return this;
    }

    @Override
    public FragmentTransaction setTransitionStyle(int n2) {
        this.mTransitionStyle = n2;
        return this;
    }

    @Override
    public FragmentTransaction show(Fragment fragment) {
        Op op2 = new Op();
        op2.cmd = 5;
        op2.fragment = fragment;
        this.addOp(op2);
        return this;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(128);
        stringBuilder.append("BackStackEntry{");
        stringBuilder.append(Integer.toHexString((int)System.identityHashCode((Object)this)));
        if (this.mIndex >= 0) {
            stringBuilder.append(" #");
            stringBuilder.append(this.mIndex);
        }
        if (this.mName != null) {
            stringBuilder.append(" ");
            stringBuilder.append(this.mName);
        }
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    static final class Op {
        int cmd;
        int enterAnim;
        int exitAnim;
        Fragment fragment;
        Op next;
        int popEnterAnim;
        int popExitAnim;
        Op prev;
        ArrayList<Fragment> removed;

        Op() {
        }
    }

    public class TransitionState {
        public FragmentTransitionCompat21.EpicenterView enteringEpicenterView;
        public ArrayList<View> hiddenFragmentViews;
        public ArrayMap<String, String> nameOverrides;
        public View nonExistentView;

        public TransitionState() {
            this.nameOverrides = new ArrayMap();
            this.hiddenFragmentViews = new ArrayList();
            this.enteringEpicenterView = new FragmentTransitionCompat21.EpicenterView();
        }
    }

}

