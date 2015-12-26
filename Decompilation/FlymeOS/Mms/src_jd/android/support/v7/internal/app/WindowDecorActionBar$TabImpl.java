package android.support.v7.internal.app;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBar.TabListener;
import android.support.v7.app.ActionBar.TabListenerSDK;
import android.support.v7.internal.widget.ScrollingTabContainerView;
import android.support.v7.internal.widget.TintManager;
import android.view.LayoutInflater;
import android.view.View;

public class WindowDecorActionBar$TabImpl
  extends ActionBar.Tab
{
  private ActionBar.TabListener mCallback;
  private ActionBar.TabListenerSDK mCallbackSDK;
  private CharSequence mContentDesc;
  private View mCustomView;
  private Drawable mIcon;
  private boolean mIsEnabled = true;
  private int mPosition = -1;
  private Object mTag;
  private CharSequence mText;
  
  public WindowDecorActionBar$TabImpl(WindowDecorActionBar paramWindowDecorActionBar) {}
  
  public ActionBar.TabListener getCallback()
  {
    return mCallback;
  }
  
  public ActionBar.TabListenerSDK getCallbackSDK()
  {
    return mCallbackSDK;
  }
  
  public CharSequence getContentDescription()
  {
    return mContentDesc;
  }
  
  public View getCustomView()
  {
    return mCustomView;
  }
  
  public Drawable getIcon()
  {
    return mIcon;
  }
  
  public int getPosition()
  {
    return mPosition;
  }
  
  public Object getTag()
  {
    return mTag;
  }
  
  public CharSequence getText()
  {
    return mText;
  }
  
  public boolean isEnabled()
  {
    return mIsEnabled;
  }
  
  public void select()
  {
    this$0.selectTab(this);
  }
  
  public ActionBar.Tab setContentDescription(int paramInt)
  {
    return setContentDescription(WindowDecorActionBar.access$1200(this$0).getResources().getText(paramInt));
  }
  
  public ActionBar.Tab setContentDescription(CharSequence paramCharSequence)
  {
    mContentDesc = paramCharSequence;
    if (mPosition >= 0) {
      WindowDecorActionBar.access$1300(this$0).updateTab(mPosition);
    }
    return this;
  }
  
  public ActionBar.Tab setCustomView(int paramInt)
  {
    return setCustomView(LayoutInflater.from(this$0.getThemedContext()).inflate(paramInt, null));
  }
  
  public ActionBar.Tab setCustomView(View paramView)
  {
    mCustomView = paramView;
    if (mPosition >= 0) {
      WindowDecorActionBar.access$1300(this$0).updateTab(mPosition);
    }
    return this;
  }
  
  public ActionBar.Tab setEnabled(boolean paramBoolean)
  {
    mIsEnabled = paramBoolean;
    if (mPosition >= 0) {
      WindowDecorActionBar.access$1300(this$0).updateTab(mPosition);
    }
    return this;
  }
  
  public ActionBar.Tab setIcon(int paramInt)
  {
    return setIcon(this$0.getTintManager().getDrawable(paramInt));
  }
  
  public ActionBar.Tab setIcon(Drawable paramDrawable)
  {
    mIcon = paramDrawable;
    if (mPosition >= 0) {
      WindowDecorActionBar.access$1300(this$0).updateTab(mPosition);
    }
    return this;
  }
  
  public void setPosition(int paramInt)
  {
    mPosition = paramInt;
  }
  
  public ActionBar.Tab setTabListener(ActionBar.TabListener paramTabListener)
  {
    mCallback = paramTabListener;
    return this;
  }
  
  public ActionBar.Tab setTabListenerSDK(ActionBar.TabListenerSDK paramTabListenerSDK)
  {
    mCallbackSDK = paramTabListenerSDK;
    return this;
  }
  
  public ActionBar.Tab setTag(Object paramObject)
  {
    mTag = paramObject;
    return this;
  }
  
  public ActionBar.Tab setText(int paramInt)
  {
    return setText(WindowDecorActionBar.access$1200(this$0).getResources().getText(paramInt));
  }
  
  public ActionBar.Tab setText(CharSequence paramCharSequence)
  {
    mText = paramCharSequence;
    if (mPosition >= 0) {
      WindowDecorActionBar.access$1300(this$0).updateTab(mPosition);
    }
    return this;
  }
}

/* Location:
 * Qualified Name:     android.support.v7.internal.app.WindowDecorActionBar.TabImpl
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */