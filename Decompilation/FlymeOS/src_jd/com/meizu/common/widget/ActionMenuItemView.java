package com.meizu.common.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnDismissListener;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.TextView;
import com.meizu.common.R.dimen;
import com.meizu.common.R.drawable;
import com.meizu.common.R.id;
import com.meizu.common.R.layout;

public class ActionMenuItemView
  extends LinearLayout
{
  static final String TAG = "ActionMenuItemView";
  private Context mContext;
  private ImageView mIcon;
  private boolean mIsShowing = false;
  private PopupMenu mPopup;
  private TextView mTitle;
  private ImageView mborderless;
  
  public ActionMenuItemView(Context paramContext)
  {
    super(paramContext);
    mContext = paramContext;
    initView();
  }
  
  public ActionMenuItemView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    mContext = paramContext;
    initView();
  }
  
  public ActionMenuItemView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    mContext = paramContext;
    initView();
  }
  
  private void dismiss()
  {
    if (mPopup != null) {
      mPopup.dismiss();
    }
  }
  
  private void initView()
  {
    View localView = LayoutInflater.from(mContext).inflate(R.layout.mc_action_menu_view, this);
    if (localView == null)
    {
      Log.w("CustomItemView", "can not inflate the view");
      return;
    }
    setClickable(true);
    setGravity(17);
    int i = getContext().getResources().getDimensionPixelSize(R.dimen.mz_action_button_min_width);
    int j = getContext().getResources().getDimensionPixelSize(R.dimen.mz_action_button_min_height);
    setMinimumWidth(i);
    setMinimumHeight(j);
    mTitle = ((TextView)localView.findViewById(R.id.menu_text));
    mIcon = ((ImageView)localView.findViewById(R.id.menu_image));
    mborderless = ((ImageView)localView.findViewById(R.id.borderless));
    mborderless.setRotation(180.0F);
    mTitle.setActivated(false);
    setOnClickListener(new ActionMenuItemView.1(this));
    setBackground(getResources().getDrawable(R.drawable.mc_action_menu_view_background));
  }
  
  public PopupMenu getPopup()
  {
    if (mPopup == null) {
      mPopup = new PopupMenu(mContext, this);
    }
    mPopup.setOnDismissListener(new MenuDismissListener());
    return mPopup;
  }
  
  public void inflateMenu(int paramInt)
  {
    mPopup = new PopupMenu(mContext, this);
    mPopup.getMenuInflater().inflate(paramInt, mPopup.getMenu());
    mPopup.setOnDismissListener(new MenuDismissListener());
  }
  
  public void setEnabled(boolean paramBoolean)
  {
    super.setEnabled(paramBoolean);
  }
  
  public void setIcon(Drawable paramDrawable)
  {
    if (mIcon != null)
    {
      mIcon.setVisibility(0);
      mIcon.setImageDrawable(paramDrawable);
    }
    if (mTitle != null) {
      mTitle.setVisibility(8);
    }
  }
  
  public void setOnDismissListener(PopupMenu.OnDismissListener paramOnDismissListener)
  {
    if (mPopup != null) {
      mPopup.setOnDismissListener(paramOnDismissListener);
    }
  }
  
  public void setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener paramOnMenuItemClickListener)
  {
    if (mPopup != null) {
      mPopup.setOnMenuItemClickListener(paramOnMenuItemClickListener);
    }
  }
  
  @Deprecated
  public void setPopupCenterHorizontal(boolean paramBoolean) {}
  
  public void setPressed(boolean paramBoolean)
  {
    super.setPressed(paramBoolean);
  }
  
  public void setTitle(CharSequence paramCharSequence)
  {
    if (mTitle != null)
    {
      mTitle.setVisibility(0);
      mTitle.setText(paramCharSequence);
    }
    if (mIcon != null) {
      mIcon.setVisibility(8);
    }
  }
  
  class MenuDismissListener
    implements PopupMenu.OnDismissListener
  {
    MenuDismissListener() {}
    
    public void onDismiss(PopupMenu paramPopupMenu)
    {
      ActionMenuItemView.access$102(ActionMenuItemView.this, false);
      mborderless.setRotation(180.0F);
    }
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.ActionMenuItemView
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */