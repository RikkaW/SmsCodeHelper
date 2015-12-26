package com.android.mms.ui;

import android.app.AlertDialog.Builder;
import android.content.ActivityNotFoundException;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.TextUtils;
import android.text.style.URLSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.HashSet;
import miui.os.Build;
import miui.view.menu.ContextMenuDialog;

class SlideshowActivity$FlatshowAdapter
  extends BaseAdapter
{
  private SimplePduDoc mDoc;
  private ArrayList<Object> mElements = new ArrayList();
  private String mMsgId;
  private float mTextSize;
  
  public SlideshowActivity$FlatshowAdapter(SlideshowActivity paramSlideshowActivity, Uri paramUri)
  {
    mDoc = new SimplePduDoc(paramSlideshowActivity);
    mDoc.load(paramUri);
    mMsgId = paramUri.getLastPathSegment();
    if ((Build.IS_CM_CUSTOMIZATION) || (Build.IS_CU_CUSTOMIZATION))
    {
      loadElementsWithLayout();
      return;
    }
    loadElements();
  }
  
  private void addAudioNameView(LinearLayout paramLinearLayout, TextView paramTextView)
  {
    if ((Build.IS_CM_CUSTOMIZATION_TEST) && (paramTextView != null) && (paramLinearLayout.getChildCount() == 1)) {
      paramLinearLayout.addView(paramTextView);
    }
  }
  
  private TextView getAudioNameView(LinearLayout paramLinearLayout)
  {
    if ((Build.IS_CM_CUSTOMIZATION_TEST) && (paramLinearLayout.getChildCount() > 1)) {
      return (TextView)paramLinearLayout.getChildAt(1);
    }
    return null;
  }
  
  private void loadElements()
  {
    int i = 0;
    while (i < mDoc.getPageSize())
    {
      SimplePduPage localSimplePduPage = mDoc.getPage(i);
      if (localSimplePduPage != null)
      {
        int j = 0;
        while (j < localSimplePduPage.getPartSize())
        {
          SimplePduPart localSimplePduPart = localSimplePduPage.getPart(j);
          mElements.add(localSimplePduPart);
          j += 1;
        }
      }
      mElements.add(Integer.valueOf(i + 1));
      i += 1;
    }
  }
  
  private void loadElementsWithLayout()
  {
    boolean bool = mDoc.isLayoutImageBottom();
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    while (i < mDoc.getPageSize())
    {
      SimplePduPage localSimplePduPage = mDoc.getPage(i);
      if (localSimplePduPage != null)
      {
        int j = 0;
        if (j < localSimplePduPage.getPartSize())
        {
          SimplePduPart localSimplePduPart = localSimplePduPage.getPart(j);
          if ((bool) && (localSimplePduPart.getAttachmentType() == 0)) {
            mElements.add(localSimplePduPart);
          }
          for (;;)
          {
            j += 1;
            break;
            if ((!bool) && ((localSimplePduPart.getAttachmentType() == 1) || (localSimplePduPart.getAttachmentType() == 2))) {
              mElements.add(localSimplePduPart);
            } else {
              localArrayList.add(localSimplePduPart);
            }
          }
        }
        if (localArrayList.size() > 0) {
          mElements.addAll(localArrayList);
        }
        localArrayList.clear();
      }
      mElements.add(Integer.valueOf(i + 1));
      i += 1;
    }
  }
  
  private TextView setAudioName(TextView paramTextView, String paramString, LayoutInflater paramLayoutInflater)
  {
    if (Build.IS_CM_CUSTOMIZATION_TEST)
    {
      TextView localTextView = paramTextView;
      if (!TextUtils.isEmpty(paramString))
      {
        if (paramTextView == null) {
          localTextView = (TextView)paramLayoutInflater.inflate(2130968622, null);
        }
        localTextView.setVisibility(0);
        localTextView.setText(paramString);
        paramTextView = localTextView;
      }
      do
      {
        return paramTextView;
        paramTextView = localTextView;
      } while (localTextView == null);
      localTextView.setVisibility(8);
      return localTextView;
    }
    return paramTextView;
  }
  
  public int getCount()
  {
    return mElements.size();
  }
  
  public Object getItem(int paramInt)
  {
    return mElements.get(paramInt);
  }
  
  public long getItemId(int paramInt)
  {
    return paramInt;
  }
  
  public int getItemViewType(int paramInt)
  {
    Object localObject = mElements.get(paramInt);
    if ((localObject instanceof Integer)) {
      return 0;
    }
    if ((localObject instanceof SimplePduPart))
    {
      if (((SimplePduPart)localObject).getAttachmentType() == 0) {
        return 1;
      }
      return 2;
    }
    throw new IllegalStateException("Cannot recognize flatshow view type");
  }
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    paramViewGroup = mElements.get(paramInt);
    LayoutInflater localLayoutInflater = LayoutInflater.from(this$0);
    int i;
    int j;
    if ((paramViewGroup instanceof Integer))
    {
      paramInt = ((Integer)paramViewGroup).intValue();
      if (paramView != null) {}
      for (;;)
      {
        i = this$0.getResources().getDimensionPixelOffset(2131427341);
        j = this$0.getResources().getDimensionPixelOffset(2131427342);
        paramView.setPadding(i, j, i, j);
        ((TextView)paramView.findViewById(2131820656)).setText(String.format(this$0.getString(2131362134), new Object[] { Integer.valueOf(paramInt) }));
        return paramView;
        paramView = localLayoutInflater.inflate(2130968620, null, false);
      }
    }
    if ((paramViewGroup instanceof SimplePduPart))
    {
      paramInt = this$0.getResources().getDimensionPixelOffset(2131427341);
      i = this$0.getResources().getDimensionPixelOffset(2131427342);
      final SimplePduPart localSimplePduPart = (SimplePduPart)paramViewGroup;
      Object localObject2 = null;
      Drawable localDrawable = null;
      ImageView localImageView = null;
      Object localObject1 = null;
      TextView localTextView = null;
      int k;
      switch (localSimplePduPart.getAttachmentType())
      {
      default: 
        localObject1 = localObject2;
        paramViewGroup = localDrawable;
        if (localImageView != null)
        {
          MessageUtils.setAttachmentImage(localImageView, paramViewGroup, true);
          localImageView.setClickable(true);
          localImageView.setOnClickListener(new View.OnClickListener()
          {
            public void onClick(View paramAnonymousView)
            {
              try
              {
                this$0.startActivity(localSimplePduPart.getIntent());
                return;
              }
              catch (ActivityNotFoundException paramAnonymousView)
              {
                Toast.makeText(this$0, this$0.getString(2131362136), 0).show();
              }
            }
          });
          localImageView.setOnLongClickListener(new View.OnLongClickListener()
          {
            public boolean onLongClick(View paramAnonymousView)
            {
              paramAnonymousView = new ContextMenuDialog(this$0);
              paramAnonymousView.addMenuItem(2131362012, new Runnable()
              {
                public void run()
                {
                  String str = MessageUtils.saveMmsPartToDisk(this$0, val$part, mMsgId);
                  AlertDialog.Builder localBuilder = new AlertDialog.Builder(this$0);
                  localBuilder.setTitle(2131362012);
                  localBuilder.setIconAttribute(16843605);
                  if (str != null) {
                    localBuilder.setMessage(this$0.getString(2131362013, new Object[] { str }));
                  }
                  for (;;)
                  {
                    localBuilder.setNeutralButton(17039370, null);
                    localBuilder.show();
                    return;
                    localBuilder.setMessage(2131362014);
                  }
                }
              });
              paramAnonymousView.show();
              return true;
            }
          });
          if (paramViewGroup != null)
          {
            j = paramViewGroup.getIntrinsicHeight();
            k = paramViewGroup.getIntrinsicWidth();
            if (k > 200) {
              break label813;
            }
            localImageView.setLayoutParams(new LinearLayout.LayoutParams(k * 3 / 2, j * 3 / 2));
          }
          label327:
          if (paramView != null) {
            break label867;
          }
          localObject1 = new LinearLayout(this$0);
          ((LinearLayout)localObject1).setGravity(3);
          ((LinearLayout)localObject1).setOrientation(1);
          ((LinearLayout)localObject1).addView(localImageView);
          addAudioNameView((LinearLayout)localObject1, localTextView);
        }
        break;
      }
      for (;;)
      {
        if (localObject1 != null) {
          ((View)localObject1).setPadding(paramInt, i, paramInt, i);
        }
        return (View)localObject1;
        if (paramView != null)
        {
          paramViewGroup = (TextView)paramView;
          label411:
          MessageUtils.showTextWithHighlight(paramViewGroup, localSimplePduPart.getText(), SlideshowActivity.access$000(this$0), 2131689520);
          paramViewGroup.setTextSize(0, mTextSize);
          if (!Build.IS_CM_CUSTOMIZATION) {
            break label491;
          }
          paramViewGroup.setLinksClickable(false);
          paramViewGroup.setTextIsSelectable(false);
          paramViewGroup.setOnClickListener(new View.OnClickListener()
          {
            public void onClick(View paramAnonymousView)
            {
              ContextMenuDialog localContextMenuDialog = new ContextMenuDialog(this$0);
              paramAnonymousView = ((TextView)paramAnonymousView).getUrls();
              HashSet localHashSet = new HashSet();
              if (paramAnonymousView.length == 1)
              {
                paramAnonymousView = MessageUtils.getUriInfo(paramAnonymousView[0].getURL());
                MessageUtils.performUriOperation(this$0, paramAnonymousView, null);
                return;
              }
              int i = 0;
              while (i < paramAnonymousView.length)
              {
                final Object localObject = paramAnonymousView[i].getURL();
                if (!localHashSet.contains(localObject))
                {
                  localHashSet.add(localObject);
                  localObject = MessageUtils.getUriInfo((String)localObject);
                  localContextMenuDialog.addMenuItem(title, new Runnable()
                  {
                    public void run()
                    {
                      MessageUtils.performUriOperation(this$0, localObject, null);
                    }
                  });
                }
                i += 1;
              }
              localContextMenuDialog.setTitle(2131362018);
              localContextMenuDialog.show();
            }
          });
        }
        for (;;)
        {
          localObject1 = paramViewGroup;
          paramViewGroup = localDrawable;
          break;
          paramViewGroup = (TextView)localLayoutInflater.inflate(2130968622, null);
          break label411;
          label491:
          paramViewGroup.setTextIsSelectable(true);
          paramViewGroup.setLinksClickable(true);
        }
        if (paramView != null) {}
        for (localImageView = (ImageView)((LinearLayout)paramView).getChildAt(0);; localImageView = new ImageView(this$0))
        {
          paramViewGroup = localSimplePduPart.getImageNoCache(300, 300);
          localObject1 = localObject2;
          break;
        }
        paramViewGroup = null;
        if (Build.IS_CM_CUSTOMIZATION_TEST) {
          paramViewGroup = localSimplePduPart.getPduPartName();
        }
        if (paramView != null)
        {
          localObject1 = (LinearLayout)paramView;
          localImageView = (ImageView)((LinearLayout)localObject1).getChildAt(0);
          localObject1 = getAudioNameView((LinearLayout)localObject1);
        }
        for (;;)
        {
          localDrawable = this$0.getResources().getDrawable(2130837620);
          localTextView = setAudioName((TextView)localObject1, paramViewGroup, localLayoutInflater);
          paramViewGroup = localDrawable;
          localObject1 = localObject2;
          break;
          localImageView = new ImageView(this$0);
        }
        if (paramView != null) {}
        for (localImageView = (ImageView)((LinearLayout)paramView).getChildAt(0);; localImageView = new ImageView(this$0))
        {
          paramViewGroup = this$0.getResources().getDrawable(2130837634);
          localObject1 = localObject2;
          break;
        }
        if (paramView != null) {}
        for (localImageView = (ImageView)((LinearLayout)paramView).getChildAt(0);; localImageView = new ImageView(this$0))
        {
          paramViewGroup = this$0.getResources().getDrawable(2130837633);
          localObject1 = localObject2;
          break;
        }
        if (paramView != null) {}
        for (localImageView = (ImageView)((LinearLayout)paramView).getChildAt(0);; localImageView = new ImageView(this$0))
        {
          paramViewGroup = this$0.getResources().getDrawable(2130837624);
          localObject1 = localObject2;
          break;
        }
        label813:
        if (k > 300)
        {
          localImageView.setLayoutParams(new LinearLayout.LayoutParams(300, j * 300 / k));
          break label327;
        }
        localImageView.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        break label327;
        label867:
        if (localTextView == null)
        {
          paramViewGroup = getAudioNameView((LinearLayout)paramView);
          localObject1 = paramView;
          if (paramViewGroup != null)
          {
            paramViewGroup.setVisibility(8);
            localObject1 = paramView;
          }
        }
        else
        {
          addAudioNameView((LinearLayout)paramView, localTextView);
          localObject1 = paramView;
        }
      }
    }
    return null;
  }
  
  public int getViewTypeCount()
  {
    return 3;
  }
  
  public void setTextSize(float paramFloat)
  {
    mTextSize = paramFloat;
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.SlideshowActivity.FlatshowAdapter
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */