package android.support.v7.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.graphics.drawable.shapes.Shape;
import android.support.v4.graphics.drawable.DrawableWrapper;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.R.attr;
import android.support.v7.internal.widget.TintManager;
import android.support.v7.internal.widget.TintTypedArray;
import android.util.AttributeSet;
import android.widget.RatingBar;

public class AppCompatRatingBar
  extends RatingBar
{
  private static final int[] TINT_ATTRS = { 16843067, 16843068 };
  private Bitmap mSampleTile;
  
  public AppCompatRatingBar(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public AppCompatRatingBar(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, R.attr.ratingBarStyle);
  }
  
  public AppCompatRatingBar(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    if (TintManager.SHOULD_BE_USED)
    {
      paramContext = TintTypedArray.obtainStyledAttributes(getContext(), paramAttributeSet, TINT_ATTRS, paramInt, 0);
      paramAttributeSet = paramContext.getDrawableIfKnown(0);
      if (paramAttributeSet != null) {
        setIndeterminateDrawable(tileifyIndeterminate(paramAttributeSet));
      }
      paramAttributeSet = paramContext.getDrawableIfKnown(1);
      if (paramAttributeSet != null) {
        setProgressDrawable(tileify(paramAttributeSet, false));
      }
      paramContext.recycle();
    }
  }
  
  private Shape getDrawableShape()
  {
    return new RoundRectShape(new float[] { 5.0F, 5.0F, 5.0F, 5.0F, 5.0F, 5.0F, 5.0F, 5.0F }, null, null);
  }
  
  private Drawable tileify(Drawable paramDrawable, boolean paramBoolean)
  {
    int j = 0;
    if ((paramDrawable instanceof DrawableWrapper))
    {
      localObject = ((DrawableWrapper)paramDrawable).getWrappedDrawable();
      if (localObject != null)
      {
        localObject = tileify((Drawable)localObject, paramBoolean);
        ((DrawableWrapper)paramDrawable).setWrappedDrawable((Drawable)localObject);
      }
    }
    do
    {
      return paramDrawable;
      if ((paramDrawable instanceof LayerDrawable))
      {
        LayerDrawable localLayerDrawable = (LayerDrawable)paramDrawable;
        int k = localLayerDrawable.getNumberOfLayers();
        paramDrawable = new Drawable[k];
        int i = 0;
        if (i < k)
        {
          int m = localLayerDrawable.getId(i);
          localObject = localLayerDrawable.getDrawable(i);
          if ((m == 16908301) || (m == 16908303)) {}
          for (paramBoolean = true;; paramBoolean = false)
          {
            paramDrawable[i] = tileify((Drawable)localObject, paramBoolean);
            i += 1;
            break;
          }
        }
        localObject = new LayerDrawable(paramDrawable);
        i = j;
        for (;;)
        {
          paramDrawable = (Drawable)localObject;
          if (i >= k) {
            break;
          }
          ((LayerDrawable)localObject).setId(i, localLayerDrawable.getId(i));
          i += 1;
        }
      }
    } while (!(paramDrawable instanceof BitmapDrawable));
    Object localObject = ((BitmapDrawable)paramDrawable).getBitmap();
    if (mSampleTile == null) {
      mSampleTile = ((Bitmap)localObject);
    }
    paramDrawable = new ShapeDrawable(getDrawableShape());
    localObject = new BitmapShader((Bitmap)localObject, Shader.TileMode.REPEAT, Shader.TileMode.CLAMP);
    paramDrawable.getPaint().setShader((Shader)localObject);
    if (paramBoolean) {
      return new ClipDrawable(paramDrawable, 3, 1);
    }
    return paramDrawable;
  }
  
  private Drawable tileifyIndeterminate(Drawable paramDrawable)
  {
    Object localObject = paramDrawable;
    if ((paramDrawable instanceof AnimationDrawable))
    {
      paramDrawable = (AnimationDrawable)paramDrawable;
      int j = paramDrawable.getNumberOfFrames();
      localObject = new AnimationDrawable();
      ((AnimationDrawable)localObject).setOneShot(paramDrawable.isOneShot());
      int i = 0;
      while (i < j)
      {
        Drawable localDrawable = tileify(paramDrawable.getFrame(i), true);
        localDrawable.setLevel(10000);
        ((AnimationDrawable)localObject).addFrame(localDrawable, paramDrawable.getDuration(i));
        i += 1;
      }
      ((AnimationDrawable)localObject).setLevel(10000);
    }
    return (Drawable)localObject;
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    try
    {
      super.onMeasure(paramInt1, paramInt2);
      if (mSampleTile != null) {
        setMeasuredDimension(ViewCompat.resolveSizeAndState(mSampleTile.getWidth() * getNumStars(), paramInt1, 0), getMeasuredHeight());
      }
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
}

/* Location:
 * Qualified Name:     android.support.v7.widget.AppCompatRatingBar
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */