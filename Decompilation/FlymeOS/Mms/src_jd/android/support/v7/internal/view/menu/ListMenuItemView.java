package android.support.v7.internal.view.menu;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v7.appcompat.R.id;
import android.support.v7.appcompat.R.layout;
import android.support.v7.appcompat.R.styleable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup.LayoutParams;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RadioButton;
import android.widget.TextView;

public class ListMenuItemView
  extends LinearLayout
  implements MenuView.ItemView
{
  private static final String TAG = "ListMenuItemView";
  private Drawable mBackground;
  private CheckBox mCheckBox;
  private Context mContext;
  private boolean mForceShowIcon;
  private ImageView mIconView;
  private LayoutInflater mInflater;
  private MenuItemImpl mItemData;
  private int mMenuType;
  private boolean mPreserveIconSpacing;
  private RadioButton mRadioButton;
  private TextView mShortcutView;
  private int mTextAppearance;
  private Context mTextAppearanceContext;
  private TextView mTitleView;
  
  public ListMenuItemView(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }
  
  public ListMenuItemView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet);
    mContext = paramContext;
    paramAttributeSet = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.MenuView, paramInt, 0);
    mBackground = paramAttributeSet.getDrawable(R.styleable.MenuView_android_itemBackground);
    mTextAppearance = paramAttributeSet.getResourceId(R.styleable.MenuView_android_itemTextAppearance, -1);
    mPreserveIconSpacing = paramAttributeSet.getBoolean(R.styleable.MenuView_preserveIconSpacing, false);
    mTextAppearanceContext = paramContext;
    paramAttributeSet.recycle();
  }
  
  private LayoutInflater getInflater()
  {
    if (mInflater == null) {
      mInflater = LayoutInflater.from(mContext);
    }
    return mInflater;
  }
  
  private void insertCheckBox()
  {
    mCheckBox = ((CheckBox)getInflater().inflate(R.layout.abc_list_menu_item_checkbox, this, false));
    addView(mCheckBox);
  }
  
  private void insertIconView()
  {
    mIconView = ((ImageView)getInflater().inflate(R.layout.abc_list_menu_item_icon, this, false));
    addView(mIconView, 0);
  }
  
  private void insertRadioButton()
  {
    mRadioButton = ((RadioButton)getInflater().inflate(R.layout.abc_list_menu_item_radio, this, false));
    addView(mRadioButton);
  }
  
  public MenuItemImpl getItemData()
  {
    return mItemData;
  }
  
  public void initialize(MenuItemImpl paramMenuItemImpl, int paramInt)
  {
    mItemData = paramMenuItemImpl;
    mMenuType = paramInt;
    if (paramMenuItemImpl.isVisible()) {}
    for (paramInt = 0;; paramInt = 8)
    {
      setVisibility(paramInt);
      setTitle(paramMenuItemImpl.getTitleForItemView(this));
      setCheckable(paramMenuItemImpl.isCheckable());
      setShortcut(paramMenuItemImpl.shouldShowShortcut(), paramMenuItemImpl.getShortcut());
      setIcon(paramMenuItemImpl.getIcon());
      setEnabled(paramMenuItemImpl.isEnabled());
      return;
    }
  }
  
  protected void onFinishInflate()
  {
    super.onFinishInflate();
    setBackgroundDrawable(mBackground);
    mTitleView = ((TextView)findViewById(R.id.title));
    if (mTextAppearance != -1) {
      mTitleView.setTextAppearance(mTextAppearanceContext, mTextAppearance);
    }
    mShortcutView = ((TextView)findViewById(R.id.shortcut));
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    if ((mIconView != null) && (mPreserveIconSpacing))
    {
      ViewGroup.LayoutParams localLayoutParams = getLayoutParams();
      LinearLayout.LayoutParams localLayoutParams1 = (LinearLayout.LayoutParams)mIconView.getLayoutParams();
      if ((height > 0) && (width <= 0)) {
        width = height;
      }
    }
    super.onMeasure(paramInt1, paramInt2);
  }
  
  public boolean prefersCondensedTitle()
  {
    return false;
  }
  
  public void setCheckable(boolean paramBoolean)
  {
    if ((!paramBoolean) && (mRadioButton == null) && (mCheckBox == null)) {}
    label51:
    label133:
    label139:
    do
    {
      return;
      Object localObject1;
      Object localObject2;
      if (mItemData.isExclusiveCheckable())
      {
        if (mRadioButton == null) {
          insertRadioButton();
        }
        localObject1 = mRadioButton;
        localObject2 = mCheckBox;
        if (!paramBoolean) {
          break label139;
        }
        ((CompoundButton)localObject1).setChecked(mItemData.isChecked());
        if (!paramBoolean) {
          break label133;
        }
      }
      for (int i = 0;; i = 8)
      {
        if (((CompoundButton)localObject1).getVisibility() != i) {
          ((CompoundButton)localObject1).setVisibility(i);
        }
        if ((localObject2 == null) || (((CompoundButton)localObject2).getVisibility() == 8)) {
          break;
        }
        ((CompoundButton)localObject2).setVisibility(8);
        return;
        if (mCheckBox == null) {
          insertCheckBox();
        }
        localObject1 = mCheckBox;
        localObject2 = mRadioButton;
        break label51;
      }
      if (mCheckBox != null) {
        mCheckBox.setVisibility(8);
      }
    } while (mRadioButton == null);
    mRadioButton.setVisibility(8);
  }
  
  public void setChecked(boolean paramBoolean)
  {
    if (mItemData.isExclusiveCheckable()) {
      if (mRadioButton == null) {
        insertRadioButton();
      }
    }
    for (Object localObject = mRadioButton;; localObject = mCheckBox)
    {
      ((CompoundButton)localObject).setChecked(paramBoolean);
      return;
      if (mCheckBox == null) {
        insertCheckBox();
      }
    }
  }
  
  public void setForceShowIcon(boolean paramBoolean)
  {
    mForceShowIcon = paramBoolean;
    mPreserveIconSpacing = paramBoolean;
  }
  
  public void setIcon(Drawable paramDrawable)
  {
    int i;
    if ((mItemData.shouldShowIcon()) || (mForceShowIcon))
    {
      i = 1;
      if ((i != 0) || (mPreserveIconSpacing)) {
        break label36;
      }
    }
    label36:
    while ((mIconView == null) && (paramDrawable == null) && (!mPreserveIconSpacing))
    {
      return;
      i = 0;
      break;
    }
    if (mIconView == null) {
      insertIconView();
    }
    if ((paramDrawable != null) || (mPreserveIconSpacing))
    {
      ImageView localImageView = mIconView;
      if (i != 0) {}
      for (;;)
      {
        localImageView.setImageDrawable(paramDrawable);
        if (mIconView.getVisibility() == 0) {
          break;
        }
        mIconView.setVisibility(0);
        return;
        paramDrawable = null;
      }
    }
    mIconView.setVisibility(8);
  }
  
  public void setShortcut(boolean paramBoolean, char paramChar)
  {
    if ((paramBoolean) && (mItemData.shouldShowShortcut())) {}
    for (paramChar = '\000';; paramChar = '\b')
    {
      if (paramChar == 0) {
        mShortcutView.setText(mItemData.getShortcutLabel());
      }
      if (mShortcutView.getVisibility() != paramChar) {
        mShortcutView.setVisibility(paramChar);
      }
      return;
    }
  }
  
  public void setTitle(CharSequence paramCharSequence)
  {
    if (paramCharSequence != null)
    {
      mTitleView.setText(paramCharSequence);
      if (mTitleView.getVisibility() != 0) {
        mTitleView.setVisibility(0);
      }
    }
    while (mTitleView.getVisibility() == 8) {
      return;
    }
    mTitleView.setVisibility(8);
  }
  
  public boolean showsIcon()
  {
    return mForceShowIcon;
  }
}

/* Location:
 * Qualified Name:     android.support.v7.internal.view.menu.ListMenuItemView
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */