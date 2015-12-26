/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.ColorStateList
 *  android.graphics.ColorFilter
 *  android.graphics.PorterDuff
 *  android.graphics.PorterDuff$Mode
 *  android.graphics.PorterDuffColorFilter
 *  android.graphics.drawable.Drawable
 *  android.graphics.drawable.LayerDrawable
 *  android.os.Build
 *  android.os.Build$VERSION
 *  android.util.SparseArray
 *  android.view.View
 *  java.lang.Integer
 *  java.lang.Math
 *  java.lang.Object
 *  java.lang.String
 *  java.util.WeakHashMap
 */
package android.support.v7.internal.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.ColorUtils;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.util.LruCache;
import android.support.v7.appcompat.R;
import android.support.v7.internal.widget.ThemeUtils;
import android.support.v7.internal.widget.TintInfo;
import android.util.SparseArray;
import android.view.View;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

public final class TintManager {
    private static final int[] COLORFILTER_COLOR_BACKGROUND_MULTIPLY;
    private static final int[] COLORFILTER_COLOR_CONTROL_ACTIVATED;
    private static final int[] COLORFILTER_TINT_COLOR_CONTROL_NORMAL;
    private static final ColorFilterLruCache COLOR_FILTER_CACHE;
    private static final boolean DEBUG = false;
    private static final PorterDuff.Mode DEFAULT_MODE;
    private static final WeakHashMap<Context, TintManager> INSTANCE_CACHE;
    public static final boolean SHOULD_BE_USED;
    private static final String TAG = "TintManager";
    private static final int[] TINT_CHECKABLE_BUTTON_LIST;
    private static final int[] TINT_COLOR_CONTROL_NORMAL;
    private static final int[] TINT_COLOR_CONTROL_STATE_LIST;
    private final WeakReference<Context> mContextRef;
    private ColorStateList mDefaultColorStateList;
    private SparseArray<ColorStateList> mTintLists;

    /*
     * Enabled aggressive block sorting
     */
    static {
        boolean bl2 = Build.VERSION.SDK_INT < 21;
        SHOULD_BE_USED = bl2;
        DEFAULT_MODE = PorterDuff.Mode.SRC_IN;
        INSTANCE_CACHE = new WeakHashMap();
        COLOR_FILTER_CACHE = new ColorFilterLruCache(6);
        COLORFILTER_TINT_COLOR_CONTROL_NORMAL = new int[]{R.drawable.abc_textfield_search_default_mtrl_alpha, R.drawable.abc_textfield_default_mtrl_alpha, R.drawable.abc_ab_share_pack_mtrl_alpha};
        TINT_COLOR_CONTROL_NORMAL = new int[]{R.drawable.abc_ic_ab_back_mtrl_am_alpha, R.drawable.abc_ic_go_search_api_mtrl_alpha, R.drawable.abc_ic_search_api_mtrl_alpha, R.drawable.abc_ic_commit_search_api_mtrl_alpha, R.drawable.abc_ic_clear_mtrl_alpha, R.drawable.abc_ic_menu_share_mtrl_alpha, R.drawable.abc_ic_menu_copy_mtrl_am_alpha, R.drawable.abc_ic_menu_cut_mtrl_alpha, R.drawable.abc_ic_menu_selectall_mtrl_alpha, R.drawable.abc_ic_menu_paste_mtrl_am_alpha, R.drawable.abc_ic_menu_moreoverflow_mtrl_alpha, R.drawable.abc_ic_voice_search_api_mtrl_alpha};
        COLORFILTER_COLOR_CONTROL_ACTIVATED = new int[]{R.drawable.abc_textfield_activated_mtrl_alpha, R.drawable.abc_textfield_search_activated_mtrl_alpha, R.drawable.abc_cab_background_top_mtrl_alpha, R.drawable.abc_text_cursor_mtrl_alpha};
        COLORFILTER_COLOR_BACKGROUND_MULTIPLY = new int[]{R.drawable.abc_popup_background_mtrl_mult, R.drawable.abc_cab_background_internal_bg, R.drawable.abc_menu_hardkey_panel_mtrl_mult};
        TINT_COLOR_CONTROL_STATE_LIST = new int[]{R.drawable.abc_edit_text_material, R.drawable.abc_tab_indicator_material, R.drawable.abc_textfield_search_material, R.drawable.abc_spinner_mtrl_am_alpha, R.drawable.abc_spinner_textfield_background_material, R.drawable.abc_ratingbar_full_material, R.drawable.abc_switch_track_mtrl_alpha, R.drawable.abc_switch_thumb_material, R.drawable.abc_btn_default_mtrl_shape, R.drawable.abc_btn_borderless_material};
        TINT_CHECKABLE_BUTTON_LIST = new int[]{R.drawable.abc_btn_check_material, R.drawable.abc_btn_radio_material};
    }

    private TintManager(Context context) {
        this.mContextRef = new WeakReference<Context>(context);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private static boolean arrayContains(int[] arrn, int n2) {
        boolean bl2 = false;
        int n3 = arrn.length;
        int n4 = 0;
        do {
            boolean bl3 = bl2;
            if (n4 >= n3) return bl3;
            if (arrn[n4] == n2) {
                return true;
            }
            ++n4;
        } while (true);
    }

    private ColorStateList createButtonColorStateList(Context object) {
        int n2 = ThemeUtils.getThemeAttrColor((Context)object, R.attr.colorButtonNormal);
        int n3 = ThemeUtils.getThemeAttrColor((Context)object, R.attr.colorControlHighlight);
        int[] arrn = ThemeUtils.DISABLED_STATE_SET;
        int n4 = ThemeUtils.getDisabledThemeAttrColor((Context)object, R.attr.colorButtonNormal);
        object = ThemeUtils.PRESSED_STATE_SET;
        int n5 = ColorUtils.compositeColors(n3, n2);
        int[] arrn2 = ThemeUtils.FOCUSED_STATE_SET;
        n3 = ColorUtils.compositeColors(n3, n2);
        return new ColorStateList((int[][])new int[][]{arrn, object, arrn2, ThemeUtils.EMPTY_STATE_SET}, new int[]{n4, n5, n3, n2});
    }

    private ColorStateList createCheckableButtonColorStateList(Context context) {
        int[] arrn = ThemeUtils.DISABLED_STATE_SET;
        int n2 = ThemeUtils.getDisabledThemeAttrColor(context, R.attr.colorControlNormal);
        int[] arrn2 = ThemeUtils.CHECKED_STATE_SET;
        int n3 = ThemeUtils.getThemeAttrColor(context, R.attr.colorControlActivated);
        int[] arrn3 = ThemeUtils.EMPTY_STATE_SET;
        int n4 = ThemeUtils.getThemeAttrColor(context, R.attr.colorControlNormal);
        return new ColorStateList((int[][])new int[][]{arrn, arrn2, arrn3}, new int[]{n2, n3, n4});
    }

    private ColorStateList createEditTextColorStateList(Context context) {
        int[] arrn = ThemeUtils.DISABLED_STATE_SET;
        int n2 = ThemeUtils.getDisabledThemeAttrColor(context, R.attr.colorControlNormal);
        int[] arrn2 = ThemeUtils.NOT_PRESSED_OR_FOCUSED_STATE_SET;
        int n3 = ThemeUtils.getThemeAttrColor(context, R.attr.colorControlNormal);
        int[] arrn3 = ThemeUtils.EMPTY_STATE_SET;
        int n4 = ThemeUtils.getThemeAttrColor(context, R.attr.colorControlActivated);
        return new ColorStateList((int[][])new int[][]{arrn, arrn2, arrn3}, new int[]{n2, n3, n4});
    }

    private ColorStateList createSpinnerColorStateList(Context context) {
        int[] arrn = ThemeUtils.DISABLED_STATE_SET;
        int n2 = ThemeUtils.getDisabledThemeAttrColor(context, R.attr.colorControlNormal);
        int[] arrn2 = ThemeUtils.NOT_PRESSED_OR_FOCUSED_STATE_SET;
        int n3 = ThemeUtils.getThemeAttrColor(context, R.attr.colorControlNormal);
        int[] arrn3 = ThemeUtils.EMPTY_STATE_SET;
        int n4 = ThemeUtils.getThemeAttrColor(context, R.attr.colorControlActivated);
        return new ColorStateList((int[][])new int[][]{arrn, arrn2, arrn3}, new int[]{n2, n3, n4});
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private ColorStateList createSwitchThumbColorStateList(Context context) {
        int[][] arrarrn = new int[3][];
        int[] arrn = new int[3];
        ColorStateList colorStateList = ThemeUtils.getThemeAttrColorStateList(context, R.attr.colorSwitchThumbNormal);
        if (colorStateList != null && colorStateList.isStateful()) {
            arrarrn[0] = ThemeUtils.DISABLED_STATE_SET;
            arrn[0] = colorStateList.getColorForState(arrarrn[0], 0);
            arrarrn[1] = ThemeUtils.CHECKED_STATE_SET;
            arrn[1] = ThemeUtils.getThemeAttrColor(context, R.attr.colorControlActivated);
            arrarrn[2] = ThemeUtils.EMPTY_STATE_SET;
            arrn[2] = colorStateList.getDefaultColor();
            do {
                return new ColorStateList((int[][])arrarrn, arrn);
                break;
            } while (true);
        }
        arrarrn[0] = ThemeUtils.DISABLED_STATE_SET;
        arrn[0] = ThemeUtils.getDisabledThemeAttrColor(context, R.attr.colorSwitchThumbNormal);
        arrarrn[1] = ThemeUtils.CHECKED_STATE_SET;
        arrn[1] = ThemeUtils.getThemeAttrColor(context, R.attr.colorControlActivated);
        arrarrn[2] = ThemeUtils.EMPTY_STATE_SET;
        arrn[2] = ThemeUtils.getThemeAttrColor(context, R.attr.colorSwitchThumbNormal);
        return new ColorStateList((int[][])arrarrn, arrn);
    }

    private ColorStateList createSwitchTrackColorStateList(Context context) {
        int[] arrn = ThemeUtils.DISABLED_STATE_SET;
        int n2 = ThemeUtils.getThemeAttrColor(context, 16842800, 0.1f);
        int[] arrn2 = ThemeUtils.CHECKED_STATE_SET;
        int n3 = ThemeUtils.getThemeAttrColor(context, R.attr.colorControlActivated, 0.3f);
        int[] arrn3 = ThemeUtils.EMPTY_STATE_SET;
        int n4 = ThemeUtils.getThemeAttrColor(context, 16842800, 0.3f);
        return new ColorStateList((int[][])new int[][]{arrn, arrn2, arrn3}, new int[]{n2, n3, n4});
    }

    public static TintManager get(Context context) {
        TintManager tintManager;
        TintManager tintManager2 = tintManager = (TintManager)INSTANCE_CACHE.get((Object)context);
        if (tintManager == null) {
            tintManager2 = new TintManager(context);
            INSTANCE_CACHE.put((Object)context, (Object)tintManager2);
        }
        return tintManager2;
    }

    private ColorStateList getDefaultColorStateList(Context context) {
        if (this.mDefaultColorStateList == null) {
            int n2 = ThemeUtils.getThemeAttrColor(context, R.attr.colorControlNormal);
            int n3 = ThemeUtils.getThemeAttrColor(context, R.attr.colorControlActivated);
            int[] arrn = ThemeUtils.DISABLED_STATE_SET;
            int n4 = ThemeUtils.getDisabledThemeAttrColor(context, R.attr.colorControlNormal);
            this.mDefaultColorStateList = new ColorStateList((int[][])new int[][]{arrn, ThemeUtils.FOCUSED_STATE_SET, ThemeUtils.ACTIVATED_STATE_SET, ThemeUtils.PRESSED_STATE_SET, ThemeUtils.CHECKED_STATE_SET, ThemeUtils.SELECTED_STATE_SET, ThemeUtils.EMPTY_STATE_SET}, new int[]{n4, n3, n3, n3, n3, n3, n2});
        }
        return this.mDefaultColorStateList;
    }

    public static Drawable getDrawable(Context context, int n2) {
        if (TintManager.isInTintList(n2)) {
            return TintManager.get(context).getDrawable(n2);
        }
        return ContextCompat.getDrawable(context, n2);
    }

    private static boolean isInTintList(int n2) {
        if (TintManager.arrayContains(TINT_COLOR_CONTROL_NORMAL, n2) || TintManager.arrayContains(COLORFILTER_TINT_COLOR_CONTROL_NORMAL, n2) || TintManager.arrayContains(COLORFILTER_COLOR_CONTROL_ACTIVATED, n2) || TintManager.arrayContains(TINT_COLOR_CONTROL_STATE_LIST, n2) || TintManager.arrayContains(COLORFILTER_COLOR_BACKGROUND_MULTIPLY, n2) || TintManager.arrayContains(TINT_CHECKABLE_BUTTON_LIST, n2) || n2 == R.drawable.abc_cab_background_top_material) {
            return true;
        }
        return false;
    }

    private static void setPorterDuffColorFilter(Drawable drawable2, int n2, PorterDuff.Mode mode) {
        PorterDuffColorFilter porterDuffColorFilter;
        PorterDuff.Mode mode2 = mode;
        if (mode == null) {
            mode2 = DEFAULT_MODE;
        }
        mode = porterDuffColorFilter = COLOR_FILTER_CACHE.get(n2, mode2);
        if (porterDuffColorFilter == null) {
            mode = new PorterDuffColorFilter(n2, mode2);
            COLOR_FILTER_CACHE.put(n2, mode2, (PorterDuffColorFilter)mode);
        }
        drawable2.setColorFilter((ColorFilter)mode);
    }

    /*
     * Enabled aggressive block sorting
     */
    public static void tintViewBackground(View view, TintInfo tintInfo) {
        Drawable drawable2 = view.getBackground();
        if (tintInfo.mHasTintList) {
            int n2 = tintInfo.mTintList.getColorForState(view.getDrawableState(), tintInfo.mTintList.getDefaultColor());
            tintInfo = tintInfo.mHasTintMode ? tintInfo.mTintMode : null;
            TintManager.setPorterDuffColorFilter(drawable2, n2, (PorterDuff.Mode)tintInfo);
        } else {
            drawable2.clearColorFilter();
        }
        if (Build.VERSION.SDK_INT <= 10) {
            view.invalidate();
        }
    }

    public Drawable getDrawable(int n2) {
        return this.getDrawable(n2, false);
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public Drawable getDrawable(int n2, boolean bl2) {
        Drawable drawable2;
        Context context = this.mContextRef.get();
        if (context == null) {
            return null;
        }
        context = drawable2 = ContextCompat.getDrawable(context, n2);
        if (drawable2 == null) return context;
        Drawable drawable3 = drawable2;
        if (Build.VERSION.SDK_INT >= 8) {
            drawable3 = drawable2.mutate();
        }
        if ((context = this.getTintList(n2)) != null) {
            drawable3 = DrawableCompat.wrap(drawable3);
            DrawableCompat.setTintList(drawable3, (ColorStateList)context);
            drawable2 = this.getTintMode(n2);
            context = drawable3;
            if (drawable2 == null) return context;
            DrawableCompat.setTintMode(drawable3, (PorterDuff.Mode)drawable2);
            return drawable3;
        }
        if (n2 == R.drawable.abc_cab_background_top_material) {
            return new LayerDrawable(new Drawable[]{this.getDrawable(R.drawable.abc_cab_background_internal_bg), this.getDrawable(R.drawable.abc_cab_background_top_mtrl_alpha)});
        }
        context = drawable3;
        if (this.tintDrawableUsingColorFilter(n2, drawable3)) return context;
        context = drawable3;
        if (!bl2) return context;
        return null;
    }

    /*
     * Enabled aggressive block sorting
     */
    public final ColorStateList getTintList(int n2) {
        ColorStateList colorStateList = null;
        Context context = this.mContextRef.get();
        if (context == null) {
            return null;
        }
        if (this.mTintLists != null) {
            colorStateList = (ColorStateList)this.mTintLists.get(n2);
        }
        if (colorStateList != null) {
            return colorStateList;
        }
        if (n2 == R.drawable.abc_edit_text_material) {
            colorStateList = this.createEditTextColorStateList(context);
        } else if (n2 == R.drawable.abc_switch_track_mtrl_alpha) {
            colorStateList = this.createSwitchTrackColorStateList(context);
        } else if (n2 == R.drawable.abc_switch_thumb_material) {
            colorStateList = this.createSwitchThumbColorStateList(context);
        } else if (n2 == R.drawable.abc_btn_default_mtrl_shape || n2 == R.drawable.abc_btn_borderless_material) {
            colorStateList = this.createButtonColorStateList(context);
        } else if (n2 == R.drawable.abc_spinner_mtrl_am_alpha || n2 == R.drawable.abc_spinner_textfield_background_material) {
            colorStateList = this.createSpinnerColorStateList(context);
        } else if (TintManager.arrayContains(TINT_COLOR_CONTROL_NORMAL, n2)) {
            colorStateList = ThemeUtils.getThemeAttrColorStateList(context, R.attr.colorControlNormal);
        } else if (TintManager.arrayContains(TINT_COLOR_CONTROL_STATE_LIST, n2)) {
            colorStateList = this.getDefaultColorStateList(context);
        } else if (TintManager.arrayContains(TINT_CHECKABLE_BUTTON_LIST, n2)) {
            colorStateList = this.createCheckableButtonColorStateList(context);
        }
        context = colorStateList;
        if (colorStateList == null) return context;
        if (this.mTintLists == null) {
            this.mTintLists = new SparseArray();
        }
        this.mTintLists.append(n2, (Object)colorStateList);
        return colorStateList;
    }

    final PorterDuff.Mode getTintMode(int n2) {
        PorterDuff.Mode mode = null;
        if (n2 == R.drawable.abc_switch_thumb_material) {
            mode = PorterDuff.Mode.MULTIPLY;
        }
        return mode;
    }

    /*
     * Enabled aggressive block sorting
     */
    public final boolean tintDrawableUsingColorFilter(int n2, Drawable drawable2) {
        int n3;
        boolean bl2;
        PorterDuff.Mode mode;
        Context context = this.mContextRef.get();
        if (context == null) {
            return false;
        }
        if (TintManager.arrayContains(COLORFILTER_TINT_COLOR_CONTROL_NORMAL, n2)) {
            n3 = R.attr.colorControlNormal;
            mode = null;
            bl2 = true;
            n2 = -1;
        } else if (TintManager.arrayContains(COLORFILTER_COLOR_CONTROL_ACTIVATED, n2)) {
            n3 = R.attr.colorControlActivated;
            mode = null;
            bl2 = true;
            n2 = -1;
        } else if (TintManager.arrayContains(COLORFILTER_COLOR_BACKGROUND_MULTIPLY, n2)) {
            mode = PorterDuff.Mode.MULTIPLY;
            bl2 = true;
            n3 = 16842801;
            n2 = -1;
        } else if (n2 == R.drawable.abc_list_divider_mtrl_alpha) {
            n3 = 16842800;
            n2 = Math.round((float)40.8f);
            mode = null;
            bl2 = true;
        } else {
            n2 = -1;
            n3 = 0;
            mode = null;
            bl2 = false;
        }
        if (!bl2) return false;
        TintManager.setPorterDuffColorFilter(drawable2, ThemeUtils.getThemeAttrColor(context, n3), mode);
        if (n2 != -1) {
            drawable2.setAlpha(n2);
        }
        return true;
    }

    static class ColorFilterLruCache
    extends LruCache<Integer, PorterDuffColorFilter> {
        public ColorFilterLruCache(int n2) {
            super(n2);
        }

        private static int generateCacheKey(int n2, PorterDuff.Mode mode) {
            return (n2 + 31) * 31 + mode.hashCode();
        }

        PorterDuffColorFilter get(int n2, PorterDuff.Mode mode) {
            return (PorterDuffColorFilter)this.get(ColorFilterLruCache.generateCacheKey(n2, mode));
        }

        PorterDuffColorFilter put(int n2, PorterDuff.Mode mode, PorterDuffColorFilter porterDuffColorFilter) {
            return this.put(ColorFilterLruCache.generateCacheKey(n2, mode), porterDuffColorFilter);
        }
    }

}

