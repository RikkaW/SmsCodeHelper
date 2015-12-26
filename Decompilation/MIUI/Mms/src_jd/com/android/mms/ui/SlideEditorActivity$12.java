package com.android.mms.ui;

import android.content.ContentUris;
import android.widget.Toast;
import com.android.mms.ExceedMessageSizeException;
import com.android.mms.ResolutionException;
import com.android.mms.UnsupportContentTypeException;
import com.google.android.mms.MmsException;
import com.google.android.mms.pdu.MiuiPduPersister;
import com.google.android.mms.pdu.PduPart;

class SlideEditorActivity$12
  implements MessageUtils.ResizeImageResultCallback
{
  SlideEditorActivity$12(SlideEditorActivity paramSlideEditorActivity) {}
  
  public void onResizeResult(PduPart paramPduPart, boolean paramBoolean)
  {
    SlideEditorActivity localSlideEditorActivity = this$0;
    if (paramPduPart == null)
    {
      Toast.makeText(this$0, SlideEditorActivity.access$1500(this$0, 2131361853, SlideEditorActivity.access$1400(this$0)), 0).show();
      return;
    }
    try
    {
      SlideEditorActivity.access$500(this$0).checkMessageSize(SlideEditorActivity.access$800(this$0), paramPduPart.getData().length);
      long l = ContentUris.parseId(SlideEditorActivity.access$300(this$0));
      paramPduPart = MiuiPduPersister.getPduPersister(localSlideEditorActivity).persistPart(paramPduPart, l);
      SlideEditorActivity.access$500(this$0).changeImage(SlideEditorActivity.access$800(this$0), paramPduPart);
      return;
    }
    catch (MmsException paramPduPart)
    {
      SlideEditorActivity.access$1600(this$0, "add picture failed");
      Toast.makeText(this$0, SlideEditorActivity.access$1500(this$0, 2131361853, SlideEditorActivity.access$1400(this$0)), 0).show();
      return;
    }
    catch (UnsupportContentTypeException paramPduPart)
    {
      MessageUtils.showErrorDialog(this$0, SlideEditorActivity.access$1500(this$0, 2131361847, SlideEditorActivity.access$1400(this$0)), SlideEditorActivity.access$1500(this$0, 2131361848, SlideEditorActivity.access$1400(this$0)));
      return;
    }
    catch (ResolutionException paramPduPart)
    {
      MessageUtils.showErrorDialog(this$0, SlideEditorActivity.access$1700(this$0, 2131361854), SlideEditorActivity.access$1700(this$0, 2131361855));
      return;
    }
    catch (ExceedMessageSizeException paramPduPart)
    {
      MessageUtils.showErrorDialog(this$0, SlideEditorActivity.access$1700(this$0, 2131361849), SlideEditorActivity.access$1500(this$0, 2131361853, SlideEditorActivity.access$1400(this$0)));
    }
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.SlideEditorActivity.12
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */