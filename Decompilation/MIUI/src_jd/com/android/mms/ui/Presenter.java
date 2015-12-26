package com.android.mms.ui;

import android.content.Context;
import com.android.mms.model.IModelChangedObserver;
import com.android.mms.model.Model;

public abstract class Presenter
  implements IModelChangedObserver
{
  protected final Context mContext;
  protected Model mModel;
  protected ViewInterface mView;
  
  public Presenter(Context paramContext, ViewInterface paramViewInterface, Model paramModel)
  {
    mContext = paramContext;
    mView = paramViewInterface;
    mModel = paramModel;
    mModel.registerModelChangedObserver(this);
  }
  
  public abstract void present();
}

/* Location:
 * Qualified Name:     com.android.mms.ui.Presenter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */