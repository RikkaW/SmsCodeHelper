package com.android.mms.ui;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnShowListener;
import android.content.res.Resources;
import android.text.TextUtils;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import miui.app.AlertDialog;
import miui.app.AlertDialog.Builder;

class PhraseActivity$SelectedMessage
{
  private AlertDialog mEditNewPhraseDialog;
  private TextView mEditNewPhraseView;
  private View mEditNewPhraseViewContainer;
  private boolean mNewPhrase;
  private Pair<String, Integer> mSelectedPhrase;
  
  public PhraseActivity$SelectedMessage(PhraseActivity paramPhraseActivity)
  {
    buildEditNewPhraseDialog();
  }
  
  private void buildEditNewPhraseDialog()
  {
    mEditNewPhraseViewContainer = LayoutInflater.from(PhraseActivity.access$100(this$0)).inflate(2130968684, null);
    mEditNewPhraseView = ((TextView)mEditNewPhraseViewContainer.findViewById(2131820839));
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(PhraseActivity.access$100(this$0));
    localBuilder.setCancelable(true);
    localBuilder.setView(mEditNewPhraseViewContainer);
    localBuilder.setOnShowListener(new DialogInterface.OnShowListener()
    {
      public void onShow(DialogInterface paramAnonymousDialogInterface)
      {
        MessageUtils.requestInputMethod(PhraseActivity.access$100(this$0), mEditNewPhraseView);
      }
    });
    localBuilder.setPositiveButton(2131361998, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface = mEditNewPhraseView.getText().toString();
        if (mNewPhrase)
        {
          PhraseActivity.SelectedMessage.access$502(PhraseActivity.SelectedMessage.this, false);
          if (TextUtils.isEmpty(paramAnonymousDialogInterface)) {
            return;
          }
          PhraseActivity.access$300(this$0).addPhrase(paramAnonymousDialogInterface);
          return;
        }
        if (TextUtils.isEmpty(paramAnonymousDialogInterface))
        {
          delete();
          return;
        }
        PhraseActivity.access$300(this$0).setPhrase(((Integer)mSelectedPhrase.second).intValue(), paramAnonymousDialogInterface);
      }
    });
    localBuilder.setNegativeButton(2131361892, null);
    mEditNewPhraseDialog = localBuilder.create();
  }
  
  public void delete()
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(PhraseActivity.access$100(this$0));
    localBuilder.setTitle(2131362100);
    localBuilder.setMessage(2131362101);
    localBuilder.setCancelable(true);
    localBuilder.setPositiveButton(2131361930, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        PhraseActivity.access$300(this$0).deletePhrase(((Integer)mSelectedPhrase.second).intValue());
      }
    });
    localBuilder.setNegativeButton(2131361892, null);
    localBuilder.show();
  }
  
  public void edit()
  {
    String str;
    if (!mNewPhrase) {
      str = (String)mSelectedPhrase.first;
    }
    for (int i = 2131362098;; i = 2131362097)
    {
      mEditNewPhraseView.setText(str);
      mEditNewPhraseDialog.setTitle(this$0.getResources().getString(i));
      mEditNewPhraseDialog.show();
      return;
      str = null;
    }
  }
  
  public String getMessage()
  {
    return (String)mSelectedPhrase.first;
  }
  
  public void selectPhrase(int paramInt, String paramString)
  {
    if (paramString == null) {}
    for (mNewPhrase = true;; mNewPhrase = false)
    {
      mSelectedPhrase = new Pair(paramString, Integer.valueOf(paramInt));
      return;
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.PhraseActivity.SelectedMessage
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */