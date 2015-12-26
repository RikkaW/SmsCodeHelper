package android.support.v7.internal.widget;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.ListAdapter;

class SpinnerCompat$DialogPopup
  implements DialogInterface.OnClickListener, SpinnerCompat.SpinnerPopup
{
  private ListAdapter mListAdapter;
  private AlertDialog mPopup;
  private CharSequence mPrompt;
  
  private SpinnerCompat$DialogPopup(SpinnerCompat paramSpinnerCompat) {}
  
  public void dismiss()
  {
    if (mPopup != null)
    {
      mPopup.dismiss();
      mPopup = null;
    }
  }
  
  public Drawable getBackground()
  {
    return null;
  }
  
  public CharSequence getHintText()
  {
    return mPrompt;
  }
  
  public int getHorizontalOffset()
  {
    return 0;
  }
  
  public int getVerticalOffset()
  {
    return 0;
  }
  
  public boolean isShowing()
  {
    if (mPopup != null) {
      return mPopup.isShowing();
    }
    return false;
  }
  
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    this$0.setSelection(paramInt);
    if (this$0.mOnItemClickListener != null) {
      this$0.performItemClick(null, paramInt, mListAdapter.getItemId(paramInt));
    }
    dismiss();
  }
  
  public void setAdapter(ListAdapter paramListAdapter)
  {
    mListAdapter = paramListAdapter;
  }
  
  public void setBackgroundDrawable(Drawable paramDrawable)
  {
    Log.e("Spinner", "Cannot set popup background for MODE_DIALOG, ignoring");
  }
  
  public void setHorizontalOffset(int paramInt)
  {
    Log.e("Spinner", "Cannot set horizontal offset for MODE_DIALOG, ignoring");
  }
  
  public void setPromptText(CharSequence paramCharSequence)
  {
    mPrompt = paramCharSequence;
  }
  
  public void setVerticalOffset(int paramInt)
  {
    Log.e("Spinner", "Cannot set vertical offset for MODE_DIALOG, ignoring");
  }
  
  public void show()
  {
    if (mListAdapter == null) {
      return;
    }
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this$0.getContext());
    if (mPrompt != null) {
      localBuilder.setTitle(mPrompt);
    }
    mPopup = localBuilder.setSingleChoiceItems(mListAdapter, this$0.getSelectedItemPosition(), this).create();
    mPopup.show();
  }
}

/* Location:
 * Qualified Name:     android.support.v7.internal.widget.SpinnerCompat.DialogPopup
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */