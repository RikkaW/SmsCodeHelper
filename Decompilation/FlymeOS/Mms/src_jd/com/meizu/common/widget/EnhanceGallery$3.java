package com.meizu.common.widget;

import android.view.ActionMode;
import android.view.DragEvent;
import android.view.View;
import android.view.View.OnDragListener;
import android.widget.Checkable;

class EnhanceGallery$3
  implements View.OnDragListener
{
  EnhanceGallery$3(EnhanceGallery paramEnhanceGallery) {}
  
  public boolean onDrag(View paramView, DragEvent paramDragEvent)
  {
    if (this$0.mDragAndDropPosition == -1) {
      return false;
    }
    switch (paramDragEvent.getAction())
    {
    default: 
      return true;
    case 1: 
      return true;
    case 2: 
      return true;
    case 5: 
      return true;
    case 6: 
      return true;
    case 3: 
      return false;
    case 4: 
      paramView = this$0.getChildAt(this$0.mDragAndDropPosition - this$0.mFirstPosition);
      if (paramView != null)
      {
        if (!(paramView instanceof EnhanceGallery.DragShadowItem)) {
          break label227;
        }
        View localView = ((EnhanceGallery.DragShadowItem)paramView).getDragView();
        if (localView != null) {
          localView.setAlpha(1.0F);
        }
        if (EnhanceGallery.access$500(this$0)) {
          paramView.setAlpha(1.0F);
        }
      }
      label164:
      if (!paramDragEvent.getResult()) {
        this$0.setItemChecked(this$0.mDragAndDropPosition, true);
      }
      for (;;)
      {
        this$0.mDragAndDropPosition = -1;
        if ((this$0.getCheckedItemCount() > 0) || (EnhanceGallery.access$600(this$0) == null)) {
          break;
        }
        EnhanceGallery.access$600(this$0).finish();
        break;
        label227:
        paramView.setAlpha(1.0F);
        break label164;
        if (paramView != null)
        {
          paramView = paramView.findViewById(16908289);
          if ((paramView != null) && ((paramView instanceof Checkable))) {
            ((Checkable)paramView).setChecked(false);
          }
          this$0.invalidateViews();
        }
      }
    }
    paramView = this$0.getChildAt(this$0.mDragAndDropPosition - this$0.mFirstPosition);
    if (paramView != null)
    {
      if (!(paramView instanceof EnhanceGallery.DragShadowItem)) {
        break label421;
      }
      paramDragEvent = ((EnhanceGallery.DragShadowItem)paramView).getDragView();
      if (paramDragEvent != null) {
        paramDragEvent.setAlpha(1.0F);
      }
      if (EnhanceGallery.access$500(this$0)) {
        paramView.setAlpha(1.0F);
      }
    }
    for (;;)
    {
      paramView = paramView.findViewById(16908289);
      if ((paramView != null) && ((paramView instanceof Checkable))) {
        ((Checkable)paramView).setChecked(false);
      }
      this$0.requestLayout();
      if ((this$0.getCheckedItemCount() <= 0) && (EnhanceGallery.access$600(this$0) != null)) {
        EnhanceGallery.access$600(this$0).finish();
      }
      this$0.mDragAndDropPosition = -1;
      break;
      label421:
      paramView.setAlpha(1.0F);
    }
  }
}

/* Location:
 * Qualified Name:     com.meizu.common.widget.EnhanceGallery.3
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */