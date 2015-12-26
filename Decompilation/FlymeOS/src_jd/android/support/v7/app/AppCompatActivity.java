package android.support.v7.app;

import android.app.FragmentManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.app.TaskStackBuilder.SupportParentable;
import android.support.v7.view.ActionMode;
import android.support.v7.view.ActionMode.Callback;
import android.support.v7.widget.Toolbar;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

public class AppCompatActivity
  extends FragmentActivity
  implements TaskStackBuilder.SupportParentable, ActionBarDrawerToggle.DelegateProvider, AppCompatCallback
{
  private AppCompatDelegate mDelegate;
  
  public void addContentView(View paramView, ViewGroup.LayoutParams paramLayoutParams)
  {
    getDelegate().addContentView(paramView, paramLayoutParams);
  }
  
  public AppCompatDelegate getDelegate()
  {
    if (mDelegate == null) {
      mDelegate = AppCompatDelegate.create(this, this);
    }
    return mDelegate;
  }
  
  @Nullable
  public ActionBarDrawerToggle.Delegate getDrawerToggleDelegate()
  {
    return getDelegate().getDrawerToggleDelegate();
  }
  
  public MenuInflater getMenuInflater()
  {
    return getDelegate().getMenuInflater();
  }
  
  @Nullable
  public ActionBar getSupportActionBar()
  {
    return getDelegate().getSupportActionBar();
  }
  
  @Nullable
  public Intent getSupportParentActivityIntent()
  {
    return NavUtils.getParentActivityIntent(this);
  }
  
  public void invalidateOptionsMenu()
  {
    getDelegate().invalidateOptionsMenu();
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
    getDelegate().onConfigurationChanged(paramConfiguration);
  }
  
  public void onContentChanged()
  {
    onSupportContentChanged();
  }
  
  public void onCreate(@Nullable Bundle paramBundle)
  {
    getDelegate().installViewFactory();
    super.onCreate(paramBundle);
    getDelegate().onCreate(paramBundle);
  }
  
  public void onCreateSupportNavigateUpTaskStack(TaskStackBuilder paramTaskStackBuilder)
  {
    paramTaskStackBuilder.addParentStack(this);
  }
  
  public void onDestroy()
  {
    super.onDestroy();
    getDelegate().onDestroy();
  }
  
  public final boolean onMenuItemSelected(int paramInt, MenuItem paramMenuItem)
  {
    if (super.onMenuItemSelected(paramInt, paramMenuItem)) {
      return true;
    }
    ActionBar localActionBar = getSupportActionBar();
    if ((paramMenuItem.getItemId() == 16908332) && (localActionBar != null) && ((localActionBar.getDisplayOptions() & 0x4) != 0)) {
      return onSupportNavigateUp();
    }
    return false;
  }
  
  protected void onPostCreate(@Nullable Bundle paramBundle)
  {
    super.onPostCreate(paramBundle);
    getDelegate().onPostCreate(paramBundle);
  }
  
  protected void onPostResume()
  {
    super.onPostResume();
    getDelegate().onPostResume();
  }
  
  public void onPrepareSupportNavigateUpTaskStack(TaskStackBuilder paramTaskStackBuilder) {}
  
  public void onStop()
  {
    super.onStop();
    getDelegate().onStop();
  }
  
  public void onSupportActionModeFinished(ActionMode paramActionMode) {}
  
  public void onSupportActionModeStarted(ActionMode paramActionMode) {}
  
  @Deprecated
  public void onSupportContentChanged() {}
  
  public boolean onSupportNavigateUp()
  {
    Object localObject = getSupportParentActivityIntent();
    if (localObject != null)
    {
      if (supportShouldUpRecreateTask((Intent)localObject))
      {
        localObject = TaskStackBuilder.create(this);
        onCreateSupportNavigateUpTaskStack((TaskStackBuilder)localObject);
        onPrepareSupportNavigateUpTaskStack((TaskStackBuilder)localObject);
        ((TaskStackBuilder)localObject).startActivities();
      }
      for (;;)
      {
        try
        {
          ActivityCompat.finishAffinity(this);
          return true;
        }
        catch (IllegalStateException localIllegalStateException)
        {
          finish();
          continue;
        }
        supportNavigateUpTo(localIllegalStateException);
      }
    }
    return false;
  }
  
  protected void onTitleChanged(CharSequence paramCharSequence, int paramInt)
  {
    super.onTitleChanged(paramCharSequence, paramInt);
    getDelegate().setTitle(paramCharSequence);
  }
  
  @Nullable
  public ActionMode onWindowStartingSupportActionMode(ActionMode.Callback paramCallback)
  {
    return null;
  }
  
  public void setContentView(int paramInt)
  {
    getDelegate().setContentView(paramInt);
  }
  
  public void setContentView(View paramView)
  {
    getDelegate().setContentView(paramView);
  }
  
  public void setContentView(View paramView, ViewGroup.LayoutParams paramLayoutParams)
  {
    getDelegate().setContentView(paramView, paramLayoutParams);
  }
  
  public void setSupportActionBar(@Nullable Toolbar paramToolbar)
  {
    getDelegate().setSupportActionBar(paramToolbar);
  }
  
  @Deprecated
  public void setSupportProgress(int paramInt) {}
  
  @Deprecated
  public void setSupportProgressBarIndeterminate(boolean paramBoolean) {}
  
  @Deprecated
  public void setSupportProgressBarIndeterminateVisibility(boolean paramBoolean) {}
  
  @Deprecated
  public void setSupportProgressBarVisibility(boolean paramBoolean) {}
  
  public void setUiOptions(int paramInt)
  {
    getDelegate().setUiOptions(paramInt);
  }
  
  public ActionMode startMultiChoiceActionMode(ActionMode.Callback paramCallback)
  {
    return getDelegate().startMultiChoiceActionMode(paramCallback);
  }
  
  public ActionMode startSupportActionMode(ActionMode.Callback paramCallback)
  {
    return getDelegate().startSupportActionMode(paramCallback);
  }
  
  public void supportFinishAfterTransition()
  {
    if (!getFragmentManager().popBackStackImmediate()) {
      super.supportFinishAfterTransition();
    }
  }
  
  public void supportInvalidateOptionsMenu()
  {
    getDelegate().invalidateOptionsMenu();
  }
  
  public void supportNavigateUpTo(Intent paramIntent)
  {
    NavUtils.navigateUpTo(this, paramIntent);
  }
  
  public boolean supportRequestWindowFeature(int paramInt)
  {
    return getDelegate().requestWindowFeature(paramInt);
  }
  
  public boolean supportShouldUpRecreateTask(Intent paramIntent)
  {
    return NavUtils.shouldUpRecreateTask(this, paramIntent);
  }
}

/* Location:
 * Qualified Name:     android.support.v7.app.AppCompatActivity
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */