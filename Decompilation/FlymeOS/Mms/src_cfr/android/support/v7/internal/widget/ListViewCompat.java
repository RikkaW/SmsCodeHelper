/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.graphics.Canvas
 *  android.graphics.Rect
 *  android.graphics.drawable.Drawable
 *  android.util.AttributeSet
 *  android.view.View
 *  android.view.View$MeasureSpec
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.widget.AbsListView
 *  android.widget.ListAdapter
 *  android.widget.ListView
 *  java.lang.Math
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.reflect.Field
 */
package android.support.v7.internal.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.graphics.drawable.DrawableWrapper;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListAdapter;
import android.widget.ListView;
import java.lang.reflect.Field;

public class ListViewCompat
extends ListView {
    public static final int INVALID_POSITION = -1;
    public static final int NO_POSITION = -1;
    private static final int[] STATE_SET_NOTHING = new int[]{0};
    private Field mIsChildViewEnabled;
    int mSelectionBottomPadding = 0;
    int mSelectionLeftPadding = 0;
    int mSelectionRightPadding = 0;
    int mSelectionTopPadding = 0;
    private GateKeeperDrawable mSelector;
    final Rect mSelectorRect = new Rect();

    public ListViewCompat(Context context) {
        this(context, null);
    }

    public ListViewCompat(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ListViewCompat(Context context, AttributeSet attributeSet, int n2) {
        super(context, attributeSet, n2);
        try {
            this.mIsChildViewEnabled = AbsListView.class.getDeclaredField("mIsChildViewEnabled");
            this.mIsChildViewEnabled.setAccessible(true);
            return;
        }
        catch (NoSuchFieldException var1_2) {
            var1_2.printStackTrace();
            return;
        }
    }

    protected void dispatchDraw(Canvas canvas) {
        this.drawSelectorCompat(canvas);
        super.dispatchDraw(canvas);
    }

    protected void drawSelectorCompat(Canvas canvas) {
        Drawable drawable2;
        if (!this.mSelectorRect.isEmpty() && (drawable2 = this.getSelector()) != null) {
            drawable2.setBounds(this.mSelectorRect);
            drawable2.draw(canvas);
        }
    }

    public void drawableStateChanged() {
        super.drawableStateChanged();
        this.setSelectorEnabled(true);
        this.updateSelectorStateCompat();
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public int lookForSelectablePosition(int n2, boolean bl2) {
        int n3;
        ListAdapter listAdapter = this.getAdapter();
        if (listAdapter == null) return -1;
        if (this.isInTouchMode()) {
            return -1;
        }
        int n4 = listAdapter.getCount();
        if (!this.getAdapter().areAllItemsEnabled()) {
            if (bl2) {
                n3 = Math.max((int)0, (int)n2);
                do {
                    n2 = n3;
                    if (n3 < n4) {
                        n2 = n3;
                        if (!listAdapter.isEnabled(n3)) {
                            ++n3;
                            continue;
                        }
                    }
                    break;
                    break;
                } while (true);
            } else {
                n3 = Math.min((int)n2, (int)(n4 - 1));
                do {
                    n2 = n3;
                    if (n3 < 0) break;
                    n2 = n3;
                    if (listAdapter.isEnabled(n3)) break;
                    --n3;
                } while (true);
            }
            if (n2 < 0) return -1;
            n3 = n2;
            if (n2 < n4) return n3;
            return -1;
        }
        if (n2 < 0) return -1;
        n3 = n2;
        if (n2 < n4) return n3;
        return -1;
    }

    /*
     * Enabled aggressive block sorting
     */
    public int measureHeightOfChildrenCompat(int n2, int n3, int n4, int n5, int n6) {
        n3 = this.getListPaddingTop();
        int n7 = this.getListPaddingBottom();
        this.getListPaddingLeft();
        this.getListPaddingRight();
        n4 = this.getDividerHeight();
        Drawable drawable2 = this.getDivider();
        ListAdapter listAdapter = this.getAdapter();
        if (listAdapter == null) {
            return n3 + n7;
        }
        n3 = n7 + n3;
        if (n4 <= 0 || drawable2 == null) {
            n4 = 0;
        }
        int n8 = 0;
        drawable2 = null;
        int n9 = 0;
        int n10 = listAdapter.getCount();
        n7 = 0;
        while (n7 < n10) {
            ViewGroup.LayoutParams layoutParams;
            int n11 = listAdapter.getItemViewType(n7);
            if (n11 != n9) {
                drawable2 = null;
                n9 = n11;
            }
            n11 = (layoutParams = (drawable2 = listAdapter.getView(n7, (View)drawable2, (ViewGroup)this)).getLayoutParams()) != null && layoutParams.height > 0 ? View.MeasureSpec.makeMeasureSpec((int)layoutParams.height, (int)1073741824) : View.MeasureSpec.makeMeasureSpec((int)0, (int)0);
            drawable2.measure(n2, n11);
            if (n7 > 0) {
                n3 += n4;
            }
            if ((n3 += drawable2.getMeasuredHeight()) >= n5) {
                n2 = n5;
                if (n6 < 0) return n2;
                n2 = n5;
                if (n7 <= n6) return n2;
                n2 = n5;
                if (n8 <= 0) return n2;
                n2 = n5;
                if (n3 == n5) return n2;
                return n8;
            }
            n11 = n8;
            if (n6 >= 0) {
                n11 = n8;
                if (n7 >= n6) {
                    n11 = n3;
                }
            }
            ++n7;
            n8 = n11;
        }
        return n3;
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    protected void positionSelectorCompat(int n2, View view) {
        Rect rect = this.mSelectorRect;
        rect.set(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
        rect.left -= this.mSelectionLeftPadding;
        rect.top -= this.mSelectionTopPadding;
        rect.right += this.mSelectionRightPadding;
        rect.bottom += this.mSelectionBottomPadding;
        try {
            boolean bl2 = this.mIsChildViewEnabled.getBoolean((Object)this);
            if (view.isEnabled() != bl2) {
                view = this.mIsChildViewEnabled;
                bl2 = !bl2;
                view.set((Object)this, (Object)bl2);
                if (n2 != -1) {
                    this.refreshDrawableState();
                }
            }
            return;
        }
        catch (IllegalAccessException var2_3) {
            var2_3.printStackTrace();
            return;
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void positionSelectorLikeFocusCompat(int n2, View view) {
        boolean bl2 = true;
        Drawable drawable2 = this.getSelector();
        boolean bl3 = drawable2 != null && n2 != -1;
        if (bl3) {
            drawable2.setVisible(false, false);
        }
        this.positionSelectorCompat(n2, view);
        if (bl3) {
            view = this.mSelectorRect;
            float f2 = view.exactCenterX();
            float f3 = view.exactCenterY();
            if (this.getVisibility() != 0) {
                bl2 = false;
            }
            drawable2.setVisible(bl2, false);
            DrawableCompat.setHotspot(drawable2, f2, f3);
        }
    }

    public void positionSelectorLikeTouchCompat(int n2, View view, float f2, float f3) {
        this.positionSelectorLikeFocusCompat(n2, view);
        view = this.getSelector();
        if (view != null && n2 != -1) {
            DrawableCompat.setHotspot((Drawable)view, f2, f3);
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public void setSelector(Drawable drawable2) {
        GateKeeperDrawable gateKeeperDrawable = drawable2 != null ? new GateKeeperDrawable(drawable2) : null;
        this.mSelector = gateKeeperDrawable;
        super.setSelector((Drawable)this.mSelector);
        gateKeeperDrawable = new Rect();
        if (drawable2 != null) {
            drawable2.getPadding((Rect)gateKeeperDrawable);
        }
        this.mSelectionLeftPadding = gateKeeperDrawable.left;
        this.mSelectionTopPadding = gateKeeperDrawable.top;
        this.mSelectionRightPadding = gateKeeperDrawable.right;
        this.mSelectionBottomPadding = gateKeeperDrawable.bottom;
    }

    public void setSelectorEnabled(boolean bl2) {
        if (this.mSelector != null) {
            this.mSelector.setEnabled(bl2);
        }
    }

    protected boolean shouldShowSelectorCompat() {
        if (this.touchModeDrawsInPressedStateCompat() && this.isPressed()) {
            return true;
        }
        return false;
    }

    public boolean touchModeDrawsInPressedStateCompat() {
        return false;
    }

    protected void updateSelectorStateCompat() {
        Drawable drawable2 = this.getSelector();
        if (drawable2 != null && this.shouldShowSelectorCompat()) {
            drawable2.setState(this.getDrawableState());
        }
    }

    static class GateKeeperDrawable
    extends DrawableWrapper {
        private boolean mEnabled = true;

        public GateKeeperDrawable(Drawable drawable2) {
            super(drawable2);
        }

        @Override
        public void draw(Canvas canvas) {
            if (this.mEnabled) {
                super.draw(canvas);
            }
        }

        void setEnabled(boolean bl2) {
            this.mEnabled = bl2;
        }

        @Override
        public void setHotspot(float f2, float f3) {
            if (this.mEnabled) {
                super.setHotspot(f2, f3);
            }
        }

        @Override
        public void setHotspotBounds(int n2, int n3, int n4, int n5) {
            if (this.mEnabled) {
                super.setHotspotBounds(n2, n3, n4, n5);
            }
        }

        @Override
        public boolean setState(int[] arrn) {
            if (this.mEnabled) {
                return super.setState(arrn);
            }
            return false;
        }

        @Override
        public boolean setVisible(boolean bl2, boolean bl3) {
            if (this.mEnabled) {
                return super.setVisible(bl2, bl3);
            }
            return false;
        }
    }

}

