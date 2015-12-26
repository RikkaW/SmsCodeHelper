package com.android.mms.ui;

import android.content.Context;
import android.text.Editable;
import android.text.Layout;
import android.text.Spannable;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.EditText;
import android.widget.TextView;
import com.android.mms.data.WorkingMessage;

public class MmsTextEditor
  extends EditText
{
  private MessageEditableActivityBase mActivity;
  private boolean mNickNamePressed = false;
  private int mPressedNickNamePosition = -1;
  
  public MmsTextEditor(Context paramContext)
  {
    super(paramContext);
    initContext(paramContext);
  }
  
  public MmsTextEditor(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    initContext(paramContext);
  }
  
  public MmsTextEditor(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    initContext(paramContext);
  }
  
  private WidgetDrawableSpan getNicknameSpan(boolean paramBoolean)
  {
    TextView localTextView = new TextView(getContext());
    localTextView.setGravity(17);
    localTextView.setText(2131362052);
    localTextView.setTextAppearance(getContext(), 2131689545);
    if (paramBoolean) {}
    for (int i = 2130837898;; i = 2130837897)
    {
      localTextView.setBackgroundResource(i);
      localTextView.setPressed(paramBoolean);
      return new WidgetDrawableSpan(getContext(), localTextView);
    }
  }
  
  private int getPosition(int paramInt1, int paramInt2)
  {
    Layout localLayout = getLayout();
    if (localLayout == null) {
      paramInt2 = -1;
    }
    for (;;)
    {
      return paramInt2;
      int m = localLayout.getLineForVertical(paramInt2);
      int j = localLayout.getOffsetForHorizontal(m, paramInt1);
      int k = (int)localLayout.getPrimaryHorizontal(j);
      int i = k;
      paramInt2 = j;
      if (k > paramInt1)
      {
        if (j == localLayout.getLineStart(m)) {
          return -1;
        }
        paramInt2 = j - 1;
        i = (int)localLayout.getPrimaryHorizontal(paramInt2);
      }
      if (paramInt1 < i + 6) {
        return -1;
      }
      if (paramInt2 >= localLayout.getLineEnd(m) - 1) {}
      for (i = (int)localLayout.getLineRight(m); paramInt1 > i - 6; i = (int)localLayout.getPrimaryHorizontal(paramInt2 + 1)) {
        return -1;
      }
    }
  }
  
  private void initContext(Context paramContext)
  {
    if ((paramContext instanceof MessageEditableActivityBase))
    {
      mActivity = ((MessageEditableActivityBase)paramContext);
      return;
    }
    throw new IllegalArgumentException("MmsTextEditor can only be used by MessageEditableActivityBase");
  }
  
  private boolean isOnPosition(int paramInt1, int paramInt2, int paramInt3)
  {
    Layout localLayout = getLayout();
    if (localLayout == null) {}
    for (;;)
    {
      return false;
      paramInt3 = localLayout.getLineForVertical(paramInt3);
      if ((paramInt3 == localLayout.getLineForOffset(paramInt1)) && (paramInt2 >= (int)localLayout.getPrimaryHorizontal(paramInt1) + 6))
      {
        if (paramInt1 >= localLayout.getLineEnd(paramInt3) - 1) {}
        for (paramInt1 = (int)localLayout.getLineRight(paramInt3); paramInt2 <= paramInt1 - 6; paramInt1 = (int)localLayout.getPrimaryHorizontal(paramInt1 + 1)) {
          return true;
        }
      }
    }
  }
  
  private void setNickNamePressed(boolean paramBoolean)
  {
    if ((mPressedNickNamePosition == -1) || (paramBoolean == mNickNamePressed)) {
      return;
    }
    mNickNamePressed = paramBoolean;
    getText().setSpan(getNicknameSpan(paramBoolean), mPressedNickNamePosition, mPressedNickNamePosition + 1, 33);
  }
  
  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if ((paramInt == 67) && (getSelectionStart() == 0) && (getSelectionEnd() == 0))
    {
      if (mActivity.getWorkingMessage().hasAttachment())
      {
        mActivity.getWorkingMessage().removeAttachment(true);
        return true;
      }
      if (mActivity.getWorkingMessage().getTimeToSend() != 0L)
      {
        mActivity.cancelTiming();
        return true;
      }
    }
    return super.onKeyDown(paramInt, paramKeyEvent);
  }
  
  protected void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
    if ((paramCharSequence instanceof Spannable))
    {
      Spannable localSpannable = (Spannable)paramCharSequence;
      int i = 0;
      while (i < localSpannable.length())
      {
        if (localSpannable.charAt(i) == 65535) {
          localSpannable.setSpan(getNicknameSpan(false), i, i + 1, 33);
        }
        i += 1;
      }
    }
    super.onTextChanged(paramCharSequence, paramInt1, paramInt2, paramInt3);
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    int m = paramMotionEvent.getAction();
    int j = (int)paramMotionEvent.getX();
    int i = (int)paramMotionEvent.getY();
    int k = getCompoundPaddingLeft();
    int n;
    int i1;
    if (getLayout() != null)
    {
      i -= getExtendedPaddingTop();
      n = j - k + getScrollX();
      i1 = i + getScrollY();
      k = 0;
      j = 0;
      switch (m)
      {
      default: 
        i = j;
      }
    }
    for (;;)
    {
      if (i == 0) {
        break label290;
      }
      return true;
      i -= getCompoundPaddingTop();
      break;
      k = getPosition(n, i1);
      i = j;
      if (k != -1)
      {
        i = j;
        if (getText().charAt(k) == 65535)
        {
          mPressedNickNamePosition = k;
          setNickNamePressed(true);
          i = 1;
          continue;
          i = j;
          if (mPressedNickNamePosition >= 0)
          {
            setNickNamePressed(false);
            mPressedNickNamePosition = -1;
            i = 1;
            continue;
            i = j;
            if (mPressedNickNamePosition >= 0)
            {
              if (isOnPosition(mPressedNickNamePosition, n, i1)) {
                setNickNamePressed(true);
              }
              for (;;)
              {
                i = 1;
                break;
                setNickNamePressed(false);
              }
              i = k;
              if (mPressedNickNamePosition >= 0)
              {
                i = k;
                if (isOnPosition(mPressedNickNamePosition, n, i1))
                {
                  setNickNamePressed(false);
                  if (mActivity != null) {
                    mActivity.startNicknamePicker(true);
                  }
                  i = 1;
                }
              }
              mPressedNickNamePosition = -1;
            }
          }
        }
      }
    }
    label290:
    return super.onTouchEvent(paramMotionEvent);
  }
}

/* Location:
 * Qualified Name:     com.android.mms.ui.MmsTextEditor
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */