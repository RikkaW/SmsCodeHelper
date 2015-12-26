package com.android.mms.ui;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import com.android.mms.data.WorkingMessage;

class AttachmentProcessor$19
  extends AsyncTask<Void, Void, Uri>
{
  AttachmentProcessor$19(AttachmentProcessor paramAttachmentProcessor) {}
  
  protected Uri doInBackground(Void... paramVarArgs)
  {
    return AttachmentProcessor.access$100(this$0).getWorkingMessage().saveAsMms(false);
  }
  
  protected void onPostExecute(Uri paramUri)
  {
    Intent localIntent = new Intent(AttachmentProcessor.access$100(this$0), SlideshowEditActivity.class);
    localIntent.setData(paramUri);
    AttachmentProcessor.access$100(this$0).startActivityForResult(localIntent, 106);
    paramUri = (DialogFragment)AttachmentProcessor.access$100(this$0).getFragmentManager().findFragmentByTag("edit_progress_dialog");
    if (paramUri != null) {
      paramUri.dismissAllowingStateLoss();
    }
  }
  
  protected void onPreExecute()
  {
    FragmentTransaction localFragmentTransaction = AttachmentProcessor.access$100(this$0).getFragmentManager().beginTransaction();
    AttachmentProcessor.ProgressDialogFragment localProgressDialogFragment = new AttachmentProcessor.ProgressDialogFragment();
    localProgressDialogFragment.setCancelable(false);
    localProgressDialogFragment.show(localFragmentTransaction, "edit_progress_dialog");
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.AttachmentProcessor.19
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */