package com.android.mms.audio;

import android.net.Uri;
import android.os.AsyncTask;
import com.android.mms.MmsApp;
import com.xiaomi.mms.mx.data.Attachment;
import com.xiaomi.mms.utils.Mx2PduPersister;
import java.util.ArrayList;
import java.util.List;

final class AudioHelper$3
  extends AsyncTask<Void, Void, Void>
{
  AudioHelper$3(Attachment paramAttachment, Uri paramUri) {}
  
  protected Void doInBackground(Void... paramVarArgs)
  {
    val$attachment.mIsRead = true;
    paramVarArgs = new ArrayList();
    paramVarArgs.add(val$attachment);
    Mx2PduPersister.updateExtension(MmsApp.getApp(), val$uri, paramVarArgs);
    return null;
  }
}

/* Location:
 * Qualified Name:     com.android.mms.audio.AudioHelper.3
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */