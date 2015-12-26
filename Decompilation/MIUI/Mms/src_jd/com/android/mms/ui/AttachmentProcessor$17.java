package com.android.mms.ui;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.AsyncTask;
import android.text.TextUtils;
import com.android.mms.data.WorkingMessage;
import com.android.mms.model.MediaModel;
import com.android.mms.model.SlideModel;
import com.android.mms.model.SlideshowModel;

class AttachmentProcessor$17
  extends AsyncTask<Void, Void, Void>
{
  AttachmentProcessor$17(AttachmentProcessor paramAttachmentProcessor) {}
  
  protected Void doInBackground(Void... paramVarArgs)
  {
    Object localObject1 = AttachmentProcessor.access$100(this$0).getWorkingMessage();
    paramVarArgs = ((WorkingMessage)localObject1).saveAsMms(false);
    Object localObject2 = ((WorkingMessage)localObject1).getSlideshow();
    if ((localObject2 == null) || (((SlideshowModel)localObject2).size() == 0)) {}
    for (;;)
    {
      return null;
      if (!((WorkingMessage)localObject1).hasSlideshow())
      {
        paramVarArgs = ((SlideshowModel)localObject2).get(0);
        if (paramVarArgs.hasImage()) {
          paramVarArgs = paramVarArgs.getImage();
        }
      }
      while (paramVarArgs != null)
      {
        localObject1 = new Intent("android.intent.action.VIEW");
        ((Intent)localObject1).addFlags(1);
        localObject2 = paramVarArgs.getContentType();
        ((Intent)localObject1).setDataAndType(paramVarArgs.getUri(), (String)localObject2);
        paramVarArgs = paramVarArgs.getSrc();
        if (!TextUtils.isEmpty(paramVarArgs)) {
          ((Intent)localObject1).putExtra("display_name", paramVarArgs);
        }
        AttachmentProcessor.access$100(this$0).startActivity((Intent)localObject1);
        return null;
        if (paramVarArgs.hasVideo())
        {
          paramVarArgs = paramVarArgs.getVideo();
        }
        else if (paramVarArgs.hasAudio())
        {
          paramVarArgs = paramVarArgs.getAudio();
          continue;
          localObject1 = new Intent(AttachmentProcessor.access$100(this$0), SlideshowActivity.class);
          ((Intent)localObject1).setData(paramVarArgs);
          AttachmentProcessor.access$100(this$0).startActivity((Intent)localObject1);
          return null;
        }
        else
        {
          paramVarArgs = null;
        }
      }
    }
  }
  
  protected void onPostExecute(Void paramVoid)
  {
    paramVoid = (DialogFragment)AttachmentProcessor.access$100(this$0).getFragmentManager().findFragmentByTag("view_progress_dialog");
    if (paramVoid != null) {
      paramVoid.dismissAllowingStateLoss();
    }
  }
  
  protected void onPreExecute()
  {
    FragmentTransaction localFragmentTransaction = AttachmentProcessor.access$100(this$0).getFragmentManager().beginTransaction();
    AttachmentProcessor.ProgressDialogFragment localProgressDialogFragment = new AttachmentProcessor.ProgressDialogFragment();
    localProgressDialogFragment.setCancelable(false);
    localProgressDialogFragment.show(localFragmentTransaction, "view_progress_dialog");
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.AttachmentProcessor.17
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */