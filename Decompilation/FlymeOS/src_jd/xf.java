import android.content.Context;
import com.android.mms.model.Model;
import com.android.mms.ui.ViewInterface;

public abstract class xf
  implements lj
{
  protected final Context mContext;
  public Model mModel;
  public ViewInterface mView;
  
  public xf(Context paramContext, ViewInterface paramViewInterface, Model paramModel)
  {
    mContext = paramContext;
    mView = paramViewInterface;
    mModel = paramModel;
  }
  
  public abstract void cancelBackgroundLoading();
  
  public Model getModel()
  {
    return mModel;
  }
  
  public ViewInterface getView()
  {
    return mView;
  }
  
  public abstract void present(zy paramzy);
  
  public void setModel(Model paramModel)
  {
    mModel = paramModel;
  }
  
  public void setView(ViewInterface paramViewInterface)
  {
    mView = paramViewInterface;
  }
}

/* Location:
 * Qualified Name:     xf
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */