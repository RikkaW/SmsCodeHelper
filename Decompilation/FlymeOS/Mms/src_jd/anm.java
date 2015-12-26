import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.res.Resources;

public class anm
{
  public static AlertDialog a(Context paramContext, String paramString, DialogInterface.OnClickListener paramOnClickListener, DialogInterface.OnCancelListener paramOnCancelListener)
  {
    return a(paramContext, null, paramString, paramOnClickListener, paramOnCancelListener, -1);
  }
  
  public static AlertDialog a(Context paramContext, String paramString1, String paramString2, DialogInterface.OnClickListener paramOnClickListener, DialogInterface.OnCancelListener paramOnCancelListener, int paramInt)
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(paramContext);
    if ((paramString1 != null) && (paramString1.length() > 0)) {
      localBuilder.setTitle(paramString1);
    }
    if (paramString2 != null) {
      localBuilder.setMessage(paramString2);
    }
    if (paramInt > 0) {
      localBuilder.setIcon(paramInt);
    }
    localBuilder.setPositiveButton(paramContext.getResources().getString(17039370), paramOnClickListener);
    localBuilder.setCancelable(false);
    if (paramOnCancelListener != null) {
      localBuilder.setOnCancelListener(paramOnCancelListener);
    }
    return localBuilder.show();
  }
  
  public static ProgressDialog a(Context paramContext)
  {
    ProgressDialog localProgressDialog = new ProgressDialog(paramContext);
    localProgressDialog.setProgressStyle(0);
    localProgressDialog.setMessage(paramContext.getResources().getString(akq.d.mzuc_wait_tip));
    localProgressDialog.setIndeterminate(false);
    localProgressDialog.setCancelable(true);
    localProgressDialog.setCanceledOnTouchOutside(false);
    return localProgressDialog;
  }
}

/* Location:
 * Qualified Name:     anm
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */