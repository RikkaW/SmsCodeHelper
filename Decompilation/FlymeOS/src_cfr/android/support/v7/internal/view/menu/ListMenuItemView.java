/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.content.res.TypedArray
 *  android.graphics.drawable.Drawable
 *  android.util.AttributeSet
 *  android.view.LayoutInflater
 *  android.view.View
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.widget.CheckBox
 *  android.widget.ImageView
 *  android.widget.LinearLayout
 *  android.widget.LinearLayout$LayoutParams
 *  android.widget.RadioButton
 *  android.widget.TextView
 *  java.lang.String
 */
package android.support.v7.internal.view.menu;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v7.appcompat.R;
import android.support.v7.internal.view.menu.MenuItemImpl;
import android.support.v7.internal.view.menu.MenuView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

public class ListMenuItemView
extends LinearLayout
implements MenuView.ItemView {
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

    public ListMenuItemView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ListMenuItemView(Context context, AttributeSet attributeSet, int n2) {
        super(context, attributeSet);
        this.mContext = context;
        attributeSet = context.obtainStyledAttributes(attributeSet, R.styleable.MenuView, n2, 0);
        this.mBackground = attributeSet.getDrawable(R.styleable.MenuView_android_itemBackground);
        this.mTextAppearance = attributeSet.getResourceId(R.styleable.MenuView_android_itemTextAppearance, -1);
        this.mPreserveIconSpacing = attributeSet.getBoolean(R.styleable.MenuView_preserveIconSpacing, false);
        this.mTextAppearanceContext = context;
        attributeSet.recycle();
    }

    private LayoutInflater getInflater() {
        if (this.mInflater == null) {
            this.mInflater = LayoutInflater.from((Context)this.mContext);
        }
        return this.mInflater;
    }

    private void insertCheckBox() {
        this.mCheckBox = (CheckBox)this.getInflater().inflate(R.layout.abc_list_menu_item_checkbox, (ViewGroup)this, false);
        this.addView((View)this.mCheckBox);
    }

    private void insertIconView() {
        this.mIconView = (ImageView)this.getInflater().inflate(R.layout.abc_list_menu_item_icon, (ViewGroup)this, false);
        this.addView((View)this.mIconView, 0);
    }

    private void insertRadioButton() {
        this.mRadioButton = (RadioButton)this.getInflater().inflate(R.layout.abc_list_menu_item_radio, (ViewGroup)this, false);
        this.addView((View)this.mRadioButton);
    }

    @Override
    public MenuItemImpl getItemData() {
        return this.mItemData;
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void initialize(MenuItemImpl menuItemImpl, int n2) {
        this.mItemData = menuItemImpl;
        this.mMenuType = n2;
        n2 = menuItemImpl.isVisible() ? 0 : 8;
        this.setVisibility(n2);
        this.setTitle(menuItemImpl.getTitleForItemView(this));
        this.setCheckable(menuItemImpl.isCheckable());
        this.setShortcut(menuItemImpl.shouldShowShortcut(), menuItemImpl.getShortcut());
        this.setIcon(menuItemImpl.getIcon());
        this.setEnabled(menuItemImpl.isEnabled());
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.setBackgroundDrawable(this.mBackground);
        this.mTitleView = (TextView)this.findViewById(R.id.title);
        if (this.mTextAppearance != -1) {
            this.mTitleView.setTextAppearance(this.mTextAppearanceContext, this.mTextAppearance);
        }
        this.mShortcutView = (TextView)this.findViewById(R.id.shortcut);
    }

    protected void onMeasure(int n2, int n3) {
        if (this.mIconView != null && this.mPreserveIconSpacing) {
            ViewGroup.LayoutParams layoutParams = this.getLayoutParams();
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams)this.mIconView.getLayoutParams();
            if (layoutParams.height > 0 && layoutParams2.width <= 0) {
                layoutParams2.width = layoutParams.height;
            }
        }
        super.onMeasure(n2, n3);
    }

    @Override
    public boolean prefersCondensedTitle() {
        return false;
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void setCheckable(boolean bl2) {
        CheckBox checkBox;
        RadioButton radioButton;
        if (!bl2 && this.mRadioButton == null && this.mCheckBox == null) return;
        if (this.mItemData.isExclusiveCheckable()) {
            if (this.mRadioButton == null) {
                this.insertRadioButton();
            }
            radioButton = this.mRadioButton;
            checkBox = this.mCheckBox;
        } else {
            if (this.mCheckBox == null) {
                this.insertCheckBox();
            }
            radioButton = this.mCheckBox;
            checkBox = this.mRadioButton;
        }
        if (bl2) {
            radioButton.setChecked(this.mItemData.isChecked());
            int n2 = bl2 ? 0 : 8;
            if (radioButton.getVisibility() != n2) {
                radioButton.setVisibility(n2);
            }
            if (checkBox == null || checkBox.getVisibility() == 8) return;
            {
                checkBox.setVisibility(8);
                return;
            }
        }
        if (this.mCheckBox != null) {
            this.mCheckBox.setVisibility(8);
        }
        if (this.mRadioButton == null) {
            return;
        }
        this.mRadioButton.setVisibility(8);
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void setChecked(boolean bl2) {
        RadioButton radioButton;
        if (this.mItemData.isExclusiveCheckable()) {
            if (this.mRadioButton == null) {
                this.insertRadioButton();
            }
            radioButton = this.mRadioButton;
        } else {
            if (this.mCheckBox == null) {
                this.insertCheckBox();
            }
            radioButton = this.mCheckBox;
        }
        radioButton.setChecked(bl2);
    }

    public void setForceShowIcon(boolean bl2) {
        this.mForceShowIcon = bl2;
        this.mPreserveIconSpacing = bl2;
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    @Override
    public void setIcon(Drawable drawable2) {
        boolean bl2 = this.mItemData.shouldShowIcon() || this.mForceShowIcon;
        if (!bl2 && !this.mPreserveIconSpacing) {
            return;
        }
        if (this.mIconView == null && drawable2 == null) {
            if (!this.mPreserveIconSpacing) return;
        }
        if (this.mIconView == null) {
            this.insertIconView();
        }
        if (drawable2 == null && !this.mPreserveIconSpacing) {
            this.mIconView.setVisibility(8);
            return;
        }
        ImageView imageView = this.mIconView;
        if (!bl2) {
            drawable2 = null;
        }
        imageView.setImageDrawable(drawable2);
        if (this.mIconView.getVisibility() == 0) return;
        this.mIconView.setVisibility(0);
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void setShortcut(boolean bl2, char c2) {
        c2 = bl2 && this.mItemData.shouldShowShortcut() ? '\u0000' : (char)8;
        if (c2 == '\u0000') {
            this.mShortcutView.setText((CharSequence)this.mItemData.getShortcutLabel());
        }
        if (this.mShortcutView.getVisibility() != c2) {
            this.mShortcutView.setVisibility((int)c2);
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void setTitle(CharSequence charSequence) {
        if (charSequence != null) {
            this.mTitleView.setText(charSequence);
            if (this.mTitleView.getVisibility() == 0) return;
            {
                this.mTitleView.setVisibility(0);
                return;
            }
        } else {
            if (this.mTitleView.getVisibility() == 8) return;
            {
                this.mTitleView.setVisibility(8);
                return;
            }
        }
    }

    @Override
    public boolean showsIcon() {
        return this.mForceShowIcon;
    }
}

