/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.content.Context
 *  android.text.Editable
 *  android.text.Layout
 *  android.text.Spannable
 *  android.util.AttributeSet
 *  android.view.KeyEvent
 *  android.view.MotionEvent
 *  android.view.View
 *  android.widget.EditText
 *  android.widget.TextView
 *  java.lang.Object
 *  java.lang.String
 */
package com.android.mms.ui;

import android.content.Context;
import android.text.Editable;
import android.text.Layout;
import android.text.Spannable;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.android.mms.data.WorkingMessage;
import com.android.mms.ui.MessageEditableActivityBase;
import com.android.mms.ui.WidgetDrawableSpan;

public class MmsTextEditor
extends EditText {
    private MessageEditableActivityBase mActivity;
    private boolean mNickNamePressed = false;
    private int mPressedNickNamePosition = -1;

    public MmsTextEditor(Context context) {
        super(context);
        this.initContext(context);
    }

    public MmsTextEditor(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.initContext(context);
    }

    public MmsTextEditor(Context context, AttributeSet attributeSet, int n) {
        super(context, attributeSet, n);
        this.initContext(context);
    }

    /*
     * Enabled aggressive block sorting
     */
    private WidgetDrawableSpan getNicknameSpan(boolean bl) {
        TextView textView = new TextView(this.getContext());
        textView.setGravity(17);
        textView.setText(2131362052);
        textView.setTextAppearance(this.getContext(), 2131689545);
        int n = bl ? 2130837898 : 2130837897;
        textView.setBackgroundResource(n);
        textView.setPressed(bl);
        return new WidgetDrawableSpan(this.getContext(), (View)textView);
    }

    /*
     * Enabled aggressive block sorting
     */
    private int getPosition(int n, int n2) {
        int n3;
        Layout layout2 = this.getLayout();
        if (layout2 == null) {
            return -1;
        }
        int n4 = layout2.getLineForVertical(n2);
        int n5 = layout2.getOffsetForHorizontal(n4, (float)n);
        int n6 = n3 = (int)layout2.getPrimaryHorizontal(n5);
        n2 = n5;
        if (n3 > n) {
            if (n5 == layout2.getLineStart(n4)) {
                return -1;
            }
            n2 = n5 - 1;
            n6 = (int)layout2.getPrimaryHorizontal(n2);
        }
        if (n < n6 + 6) {
            return -1;
        }
        n6 = n2 >= layout2.getLineEnd(n4) - 1 ? (int)layout2.getLineRight(n4) : (int)layout2.getPrimaryHorizontal(n2 + 1);
        if (n <= n6 - 6) return n2;
        return -1;
    }

    private void initContext(Context context) {
        if (context instanceof MessageEditableActivityBase) {
            this.mActivity = (MessageEditableActivityBase)context;
            return;
        }
        throw new IllegalArgumentException("MmsTextEditor can only be used by MessageEditableActivityBase");
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    private boolean isOnPosition(int n, int n2, int n3) {
        Layout layout2 = this.getLayout();
        if (layout2 == null) {
            return false;
        }
        if ((n3 = layout2.getLineForVertical(n3)) != layout2.getLineForOffset(n)) return false;
        if (n2 < (int)layout2.getPrimaryHorizontal(n) + 6) return false;
        n = n >= layout2.getLineEnd(n3) - 1 ? (int)layout2.getLineRight(n3) : (int)layout2.getPrimaryHorizontal(n + 1);
        if (n2 > n - 6) return false;
        return true;
    }

    private void setNickNamePressed(boolean bl) {
        if (this.mPressedNickNamePosition == -1 || bl == this.mNickNamePressed) {
            return;
        }
        this.mNickNamePressed = bl;
        this.getText().setSpan((Object)this.getNicknameSpan(bl), this.mPressedNickNamePosition, this.mPressedNickNamePosition + 1, 33);
    }

    public boolean onKeyDown(int n, KeyEvent keyEvent) {
        if (n == 67 && this.getSelectionStart() == 0 && this.getSelectionEnd() == 0) {
            if (this.mActivity.getWorkingMessage().hasAttachment()) {
                this.mActivity.getWorkingMessage().removeAttachment(true);
                return true;
            }
            if (this.mActivity.getWorkingMessage().getTimeToSend() != 0) {
                this.mActivity.cancelTiming();
                return true;
            }
        }
        return super.onKeyDown(n, keyEvent);
    }

    protected void onTextChanged(CharSequence charSequence, int n, int n2, int n3) {
        if (charSequence instanceof Spannable) {
            Spannable spannable = (Spannable)charSequence;
            for (int i = 0; i < spannable.length(); ++i) {
                if (spannable.charAt(i) != '\uffff') continue;
                spannable.setSpan((Object)this.getNicknameSpan(false), i, i + 1, 33);
            }
        }
        super.onTextChanged(charSequence, n, n2, n3);
    }

    /*
     * Enabled aggressive block sorting
     */
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int n = motionEvent.getAction();
        int n2 = (int)motionEvent.getX();
        int n3 = (int)motionEvent.getY();
        int n4 = this.getCompoundPaddingLeft();
        n3 = this.getLayout() != null ? (n3 -= this.getExtendedPaddingTop()) : (n3 -= this.getCompoundPaddingTop());
        int n5 = n2 - n4 + this.getScrollX();
        int n6 = n3 + this.getScrollY();
        n4 = 0;
        n2 = 0;
        switch (n) {
            default: {
                n3 = n2;
                break;
            }
            case 0: {
                n4 = this.getPosition(n5, n6);
                n3 = n2;
                if (n4 == -1) break;
                n3 = n2;
                if (this.getText().charAt(n4) != '\uffff') break;
                this.mPressedNickNamePosition = n4;
                this.setNickNamePressed(true);
                return true;
            }
            case 3: {
                n3 = n2;
                if (this.mPressedNickNamePosition < 0) break;
                this.setNickNamePressed(false);
                this.mPressedNickNamePosition = -1;
                return true;
            }
            case 2: {
                n3 = n2;
                if (this.mPressedNickNamePosition < 0) break;
                if (this.isOnPosition(this.mPressedNickNamePosition, n5, n6)) {
                    this.setNickNamePressed(true);
                    return true;
                }
                this.setNickNamePressed(false);
                return true;
            }
            case 1: {
                n3 = n4;
                if (this.mPressedNickNamePosition >= 0) {
                    n3 = n4;
                    if (this.isOnPosition(this.mPressedNickNamePosition, n5, n6)) {
                        this.setNickNamePressed(false);
                        if (this.mActivity != null) {
                            this.mActivity.startNicknamePicker(true);
                        }
                        n3 = 1;
                    }
                }
                this.mPressedNickNamePosition = -1;
            }
        }
        if (n3 == 0) return super.onTouchEvent(motionEvent);
        return true;
    }
}

