package android.support.v7.internal.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build.VERSION;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.ColorUtils;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.util.LruCache;
import android.support.v7.appcompat.R.attr;
import android.support.v7.appcompat.R.drawable;
import android.util.SparseArray;
import android.view.View;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

public final class TintManager
{
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
  
  static
  {
    if (Build.VERSION.SDK_INT < 21) {}
    for (boolean bool = true;; bool = false)
    {
      SHOULD_BE_USED = bool;
      DEFAULT_MODE = PorterDuff.Mode.SRC_IN;
      INSTANCE_CACHE = new WeakHashMap();
      COLOR_FILTER_CACHE = new ColorFilterLruCache(6);
      COLORFILTER_TINT_COLOR_CONTROL_NORMAL = new int[] { R.drawable.abc_textfield_search_default_mtrl_alpha, R.drawable.abc_textfield_default_mtrl_alpha, R.drawable.abc_ab_share_pack_mtrl_alpha };
      TINT_COLOR_CONTROL_NORMAL = new int[] { R.drawable.abc_ic_ab_back_mtrl_am_alpha, R.drawable.abc_ic_go_search_api_mtrl_alpha, R.drawable.abc_ic_search_api_mtrl_alpha, R.drawable.abc_ic_commit_search_api_mtrl_alpha, R.drawable.abc_ic_clear_mtrl_alpha, R.drawable.abc_ic_menu_share_mtrl_alpha, R.drawable.abc_ic_menu_copy_mtrl_am_alpha, R.drawable.abc_ic_menu_cut_mtrl_alpha, R.drawable.abc_ic_menu_selectall_mtrl_alpha, R.drawable.abc_ic_menu_paste_mtrl_am_alpha, R.drawable.abc_ic_menu_moreoverflow_mtrl_alpha, R.drawable.abc_ic_voice_search_api_mtrl_alpha };
      COLORFILTER_COLOR_CONTROL_ACTIVATED = new int[] { R.drawable.abc_textfield_activated_mtrl_alpha, R.drawable.abc_textfield_search_activated_mtrl_alpha, R.drawable.abc_cab_background_top_mtrl_alpha, R.drawable.abc_text_cursor_mtrl_alpha };
      COLORFILTER_COLOR_BACKGROUND_MULTIPLY = new int[] { R.drawable.abc_popup_background_mtrl_mult, R.drawable.abc_cab_background_internal_bg, R.drawable.abc_menu_hardkey_panel_mtrl_mult };
      TINT_COLOR_CONTROL_STATE_LIST = new int[] { R.drawable.abc_edit_text_material, R.drawable.abc_tab_indicator_material, R.drawable.abc_textfield_search_material, R.drawable.abc_spinner_mtrl_am_alpha, R.drawable.abc_spinner_textfield_background_material, R.drawable.abc_ratingbar_full_material, R.drawable.abc_switch_track_mtrl_alpha, R.drawable.abc_switch_thumb_material, R.drawable.abc_btn_default_mtrl_shape, R.drawable.abc_btn_borderless_material };
      TINT_CHECKABLE_BUTTON_LIST = new int[] { R.drawable.abc_btn_check_material, R.drawable.abc_btn_radio_material };
      return;
    }
  }
  
  private TintManager(Context paramContext)
  {
    mContextRef = new WeakReference(paramContext);
  }
  
  private static boolean arrayContains(int[] paramArrayOfInt, int paramInt)
  {
    boolean bool2 = false;
    int j = paramArrayOfInt.length;
    int i = 0;
    for (;;)
    {
      boolean bool1 = bool2;
      if (i < j)
      {
        if (paramArrayOfInt[i] == paramInt) {
          bool1 = true;
        }
      }
      else {
        return bool1;
      }
      i += 1;
    }
  }
  
  private ColorStateList createButtonColorStateList(Context paramContext)
  {
    int i = ThemeUtils.getThemeAttrColor(paramContext, R.attr.colorButtonNormal);
    int m = ThemeUtils.getThemeAttrColor(paramContext, R.attr.colorControlHighlight);
    int[] arrayOfInt1 = ThemeUtils.DISABLED_STATE_SET;
    int j = ThemeUtils.getDisabledThemeAttrColor(paramContext, R.attr.colorButtonNormal);
    paramContext = ThemeUtils.PRESSED_STATE_SET;
    int k = ColorUtils.compositeColors(m, i);
    int[] arrayOfInt2 = ThemeUtils.FOCUSED_STATE_SET;
    m = ColorUtils.compositeColors(m, i);
    return new ColorStateList(new int[][] { arrayOfInt1, paramContext, arrayOfInt2, ThemeUtils.EMPTY_STATE_SET }, new int[] { j, k, m, i });
  }
  
  private ColorStateList createCheckableButtonColorStateList(Context paramContext)
  {
    int[] arrayOfInt1 = ThemeUtils.DISABLED_STATE_SET;
    int i = ThemeUtils.getDisabledThemeAttrColor(paramContext, R.attr.colorControlNormal);
    int[] arrayOfInt2 = ThemeUtils.CHECKED_STATE_SET;
    int j = ThemeUtils.getThemeAttrColor(paramContext, R.attr.colorControlActivated);
    int[] arrayOfInt3 = ThemeUtils.EMPTY_STATE_SET;
    int k = ThemeUtils.getThemeAttrColor(paramContext, R.attr.colorControlNormal);
    return new ColorStateList(new int[][] { arrayOfInt1, arrayOfInt2, arrayOfInt3 }, new int[] { i, j, k });
  }
  
  private ColorStateList createEditTextColorStateList(Context paramContext)
  {
    int[] arrayOfInt1 = ThemeUtils.DISABLED_STATE_SET;
    int i = ThemeUtils.getDisabledThemeAttrColor(paramContext, R.attr.colorControlNormal);
    int[] arrayOfInt2 = ThemeUtils.NOT_PRESSED_OR_FOCUSED_STATE_SET;
    int j = ThemeUtils.getThemeAttrColor(paramContext, R.attr.colorControlNormal);
    int[] arrayOfInt3 = ThemeUtils.EMPTY_STATE_SET;
    int k = ThemeUtils.getThemeAttrColor(paramContext, R.attr.colorControlActivated);
    return new ColorStateList(new int[][] { arrayOfInt1, arrayOfInt2, arrayOfInt3 }, new int[] { i, j, k });
  }
  
  private ColorStateList createSpinnerColorStateList(Context paramContext)
  {
    int[] arrayOfInt1 = ThemeUtils.DISABLED_STATE_SET;
    int i = ThemeUtils.getDisabledThemeAttrColor(paramContext, R.attr.colorControlNormal);
    int[] arrayOfInt2 = ThemeUtils.NOT_PRESSED_OR_FOCUSED_STATE_SET;
    int j = ThemeUtils.getThemeAttrColor(paramContext, R.attr.colorControlNormal);
    int[] arrayOfInt3 = ThemeUtils.EMPTY_STATE_SET;
    int k = ThemeUtils.getThemeAttrColor(paramContext, R.attr.colorControlActivated);
    return new ColorStateList(new int[][] { arrayOfInt1, arrayOfInt2, arrayOfInt3 }, new int[] { i, j, k });
  }
  
  private ColorStateList createSwitchThumbColorStateList(Context paramContext)
  {
    int[][] arrayOfInt = new int[3][];
    int[] arrayOfInt1 = new int[3];
    ColorStateList localColorStateList = ThemeUtils.getThemeAttrColorStateList(paramContext, R.attr.colorSwitchThumbNormal);
    if ((localColorStateList != null) && (localColorStateList.isStateful()))
    {
      arrayOfInt[0] = ThemeUtils.DISABLED_STATE_SET;
      arrayOfInt1[0] = localColorStateList.getColorForState(arrayOfInt[0], 0);
      arrayOfInt[1] = ThemeUtils.CHECKED_STATE_SET;
      arrayOfInt1[1] = ThemeUtils.getThemeAttrColor(paramContext, R.attr.colorControlActivated);
      arrayOfInt[2] = ThemeUtils.EMPTY_STATE_SET;
      arrayOfInt1[2] = localColorStateList.getDefaultColor();
    }
    for (;;)
    {
      return new ColorStateList(arrayOfInt, arrayOfInt1);
      arrayOfInt[0] = ThemeUtils.DISABLED_STATE_SET;
      arrayOfInt1[0] = ThemeUtils.getDisabledThemeAttrColor(paramContext, R.attr.colorSwitchThumbNormal);
      arrayOfInt[1] = ThemeUtils.CHECKED_STATE_SET;
      arrayOfInt1[1] = ThemeUtils.getThemeAttrColor(paramContext, R.attr.colorControlActivated);
      arrayOfInt[2] = ThemeUtils.EMPTY_STATE_SET;
      arrayOfInt1[2] = ThemeUtils.getThemeAttrColor(paramContext, R.attr.colorSwitchThumbNormal);
    }
  }
  
  private ColorStateList createSwitchTrackColorStateList(Context paramContext)
  {
    int[] arrayOfInt1 = ThemeUtils.DISABLED_STATE_SET;
    int i = ThemeUtils.getThemeAttrColor(paramContext, 16842800, 0.1F);
    int[] arrayOfInt2 = ThemeUtils.CHECKED_STATE_SET;
    int j = ThemeUtils.getThemeAttrColor(paramContext, R.attr.colorControlActivated, 0.3F);
    int[] arrayOfInt3 = ThemeUtils.EMPTY_STATE_SET;
    int k = ThemeUtils.getThemeAttrColor(paramContext, 16842800, 0.3F);
    return new ColorStateList(new int[][] { arrayOfInt1, arrayOfInt2, arrayOfInt3 }, new int[] { i, j, k });
  }
  
  public static TintManager get(Context paramContext)
  {
    TintManager localTintManager2 = (TintManager)INSTANCE_CACHE.get(paramContext);
    TintManager localTintManager1 = localTintManager2;
    if (localTintManager2 == null)
    {
      localTintManager1 = new TintManager(paramContext);
      INSTANCE_CACHE.put(paramContext, localTintManager1);
    }
    return localTintManager1;
  }
  
  private ColorStateList getDefaultColorStateList(Context paramContext)
  {
    if (mDefaultColorStateList == null)
    {
      int i = ThemeUtils.getThemeAttrColor(paramContext, R.attr.colorControlNormal);
      int j = ThemeUtils.getThemeAttrColor(paramContext, R.attr.colorControlActivated);
      int[] arrayOfInt = ThemeUtils.DISABLED_STATE_SET;
      int k = ThemeUtils.getDisabledThemeAttrColor(paramContext, R.attr.colorControlNormal);
      mDefaultColorStateList = new ColorStateList(new int[][] { arrayOfInt, ThemeUtils.FOCUSED_STATE_SET, ThemeUtils.ACTIVATED_STATE_SET, ThemeUtils.PRESSED_STATE_SET, ThemeUtils.CHECKED_STATE_SET, ThemeUtils.SELECTED_STATE_SET, ThemeUtils.EMPTY_STATE_SET }, new int[] { k, j, j, j, j, j, i });
    }
    return mDefaultColorStateList;
  }
  
  public static Drawable getDrawable(Context paramContext, int paramInt)
  {
    if (isInTintList(paramInt)) {
      return get(paramContext).getDrawable(paramInt);
    }
    return ContextCompat.getDrawable(paramContext, paramInt);
  }
  
  private static boolean isInTintList(int paramInt)
  {
    return (arrayContains(TINT_COLOR_CONTROL_NORMAL, paramInt)) || (arrayContains(COLORFILTER_TINT_COLOR_CONTROL_NORMAL, paramInt)) || (arrayContains(COLORFILTER_COLOR_CONTROL_ACTIVATED, paramInt)) || (arrayContains(TINT_COLOR_CONTROL_STATE_LIST, paramInt)) || (arrayContains(COLORFILTER_COLOR_BACKGROUND_MULTIPLY, paramInt)) || (arrayContains(TINT_CHECKABLE_BUTTON_LIST, paramInt)) || (paramInt == R.drawable.abc_cab_background_top_material);
  }
  
  private static void setPorterDuffColorFilter(Drawable paramDrawable, int paramInt, PorterDuff.Mode paramMode)
  {
    PorterDuff.Mode localMode = paramMode;
    if (paramMode == null) {
      localMode = DEFAULT_MODE;
    }
    PorterDuffColorFilter localPorterDuffColorFilter = COLOR_FILTER_CACHE.get(paramInt, localMode);
    paramMode = localPorterDuffColorFilter;
    if (localPorterDuffColorFilter == null)
    {
      paramMode = new PorterDuffColorFilter(paramInt, localMode);
      COLOR_FILTER_CACHE.put(paramInt, localMode, paramMode);
    }
    paramDrawable.setColorFilter(paramMode);
  }
  
  public static void tintViewBackground(View paramView, TintInfo paramTintInfo)
  {
    Drawable localDrawable = paramView.getBackground();
    if (mHasTintList)
    {
      int i = mTintList.getColorForState(paramView.getDrawableState(), mTintList.getDefaultColor());
      if (mHasTintMode)
      {
        paramTintInfo = mTintMode;
        setPorterDuffColorFilter(localDrawable, i, paramTintInfo);
      }
    }
    for (;;)
    {
      if (Build.VERSION.SDK_INT <= 10) {
        paramView.invalidate();
      }
      return;
      paramTintInfo = null;
      break;
      localDrawable.clearColorFilter();
    }
  }
  
  public Drawable getDrawable(int paramInt)
  {
    return getDrawable(paramInt, false);
  }
  
  public Drawable getDrawable(int paramInt, boolean paramBoolean)
  {
    Object localObject1 = (Context)mContextRef.get();
    if (localObject1 == null) {
      return null;
    }
    Object localObject3 = ContextCompat.getDrawable((Context)localObject1, paramInt);
    localObject1 = localObject3;
    Object localObject2;
    if (localObject3 != null)
    {
      localObject2 = localObject3;
      if (Build.VERSION.SDK_INT >= 8) {
        localObject2 = ((Drawable)localObject3).mutate();
      }
      localObject1 = getTintList(paramInt);
      if (localObject1 == null) {
        break label101;
      }
      localObject2 = DrawableCompat.wrap((Drawable)localObject2);
      DrawableCompat.setTintList((Drawable)localObject2, (ColorStateList)localObject1);
      localObject3 = getTintMode(paramInt);
      localObject1 = localObject2;
      if (localObject3 != null)
      {
        DrawableCompat.setTintMode((Drawable)localObject2, (PorterDuff.Mode)localObject3);
        localObject1 = localObject2;
      }
    }
    for (;;)
    {
      return (Drawable)localObject1;
      label101:
      if (paramInt == R.drawable.abc_cab_background_top_material) {
        return new LayerDrawable(new Drawable[] { getDrawable(R.drawable.abc_cab_background_internal_bg), getDrawable(R.drawable.abc_cab_background_top_mtrl_alpha) });
      }
      localObject1 = localObject2;
      if (!tintDrawableUsingColorFilter(paramInt, (Drawable)localObject2))
      {
        localObject1 = localObject2;
        if (paramBoolean) {
          localObject1 = null;
        }
      }
    }
  }
  
  public final ColorStateList getTintList(int paramInt)
  {
    ColorStateList localColorStateList = null;
    Object localObject = (Context)mContextRef.get();
    if (localObject == null)
    {
      localObject = null;
      return (ColorStateList)localObject;
    }
    if (mTintLists != null) {
      localColorStateList = (ColorStateList)mTintLists.get(paramInt);
    }
    if (localColorStateList == null)
    {
      if (paramInt == R.drawable.abc_edit_text_material) {
        localColorStateList = createEditTextColorStateList((Context)localObject);
      }
      for (;;)
      {
        localObject = localColorStateList;
        if (localColorStateList == null) {
          break;
        }
        if (mTintLists == null) {
          mTintLists = new SparseArray();
        }
        mTintLists.append(paramInt, localColorStateList);
        return localColorStateList;
        if (paramInt == R.drawable.abc_switch_track_mtrl_alpha) {
          localColorStateList = createSwitchTrackColorStateList((Context)localObject);
        } else if (paramInt == R.drawable.abc_switch_thumb_material) {
          localColorStateList = createSwitchThumbColorStateList((Context)localObject);
        } else if ((paramInt == R.drawable.abc_btn_default_mtrl_shape) || (paramInt == R.drawable.abc_btn_borderless_material)) {
          localColorStateList = createButtonColorStateList((Context)localObject);
        } else if ((paramInt == R.drawable.abc_spinner_mtrl_am_alpha) || (paramInt == R.drawable.abc_spinner_textfield_background_material)) {
          localColorStateList = createSpinnerColorStateList((Context)localObject);
        } else if (arrayContains(TINT_COLOR_CONTROL_NORMAL, paramInt)) {
          localColorStateList = ThemeUtils.getThemeAttrColorStateList((Context)localObject, R.attr.colorControlNormal);
        } else if (arrayContains(TINT_COLOR_CONTROL_STATE_LIST, paramInt)) {
          localColorStateList = getDefaultColorStateList((Context)localObject);
        } else if (arrayContains(TINT_CHECKABLE_BUTTON_LIST, paramInt)) {
          localColorStateList = createCheckableButtonColorStateList((Context)localObject);
        }
      }
    }
    return localColorStateList;
  }
  
  final PorterDuff.Mode getTintMode(int paramInt)
  {
    PorterDuff.Mode localMode = null;
    if (paramInt == R.drawable.abc_switch_thumb_material) {
      localMode = PorterDuff.Mode.MULTIPLY;
    }
    return localMode;
  }
  
  public final boolean tintDrawableUsingColorFilter(int paramInt, Drawable paramDrawable)
  {
    Context localContext = (Context)mContextRef.get();
    if (localContext == null) {
      return false;
    }
    int i;
    PorterDuff.Mode localMode;
    int j;
    if (arrayContains(COLORFILTER_TINT_COLOR_CONTROL_NORMAL, paramInt))
    {
      i = R.attr.colorControlNormal;
      localMode = null;
      j = 1;
      paramInt = -1;
    }
    for (;;)
    {
      if (j != 0)
      {
        setPorterDuffColorFilter(paramDrawable, ThemeUtils.getThemeAttrColor(localContext, i), localMode);
        if (paramInt != -1) {
          paramDrawable.setAlpha(paramInt);
        }
        return true;
        if (arrayContains(COLORFILTER_COLOR_CONTROL_ACTIVATED, paramInt))
        {
          i = R.attr.colorControlActivated;
          localMode = null;
          j = 1;
          paramInt = -1;
          continue;
        }
        if (arrayContains(COLORFILTER_COLOR_BACKGROUND_MULTIPLY, paramInt))
        {
          localMode = PorterDuff.Mode.MULTIPLY;
          j = 1;
          i = 16842801;
          paramInt = -1;
          continue;
        }
        if (paramInt == R.drawable.abc_list_divider_mtrl_alpha)
        {
          i = 16842800;
          paramInt = Math.round(40.8F);
          localMode = null;
          j = 1;
        }
      }
      else
      {
        return false;
      }
      paramInt = -1;
      i = 0;
      localMode = null;
      j = 0;
    }
  }
  
  static class ColorFilterLruCache
    extends LruCache<Integer, PorterDuffColorFilter>
  {
    public ColorFilterLruCache(int paramInt)
    {
      super();
    }
    
    private static int generateCacheKey(int paramInt, PorterDuff.Mode paramMode)
    {
      return (paramInt + 31) * 31 + paramMode.hashCode();
    }
    
    PorterDuffColorFilter get(int paramInt, PorterDuff.Mode paramMode)
    {
      return (PorterDuffColorFilter)get(Integer.valueOf(generateCacheKey(paramInt, paramMode)));
    }
    
    PorterDuffColorFilter put(int paramInt, PorterDuff.Mode paramMode, PorterDuffColorFilter paramPorterDuffColorFilter)
    {
      return (PorterDuffColorFilter)put(Integer.valueOf(generateCacheKey(paramInt, paramMode)), paramPorterDuffColorFilter);
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v7.internal.widget.TintManager
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */