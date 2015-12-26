/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.content.Context
 *  android.content.Intent
 *  android.os.Bundle
 *  android.text.Editable
 *  android.view.KeyEvent
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.view.View$OnKeyListener
 *  android.widget.Button
 *  android.widget.EditText
 *  android.widget.TextView
 *  android.widget.Toast
 *  java.lang.Integer
 *  java.lang.Object
 *  java.lang.String
 */
package com.android.mms.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.android.mms.ui.MessageUtils;

public class EditSlideDurationActivity
extends Activity {
    private int mCurSlide;
    private Button mDone;
    private EditText mDur;
    private TextView mLabel;
    private final View.OnClickListener mOnDoneClickListener;
    private final View.OnKeyListener mOnKeyListener;
    private Bundle mState;
    private int mTotal;

    public EditSlideDurationActivity() {
        this.mOnKeyListener = new View.OnKeyListener(){

            public boolean onKey(View view, int n, KeyEvent keyEvent) {
                if (keyEvent.getAction() != 0) {
                    return false;
                }
                switch (n) {
                    default: {
                        return false;
                    }
                    case 23: 
                }
                EditSlideDurationActivity.this.editDone();
                return false;
            }
        };
        this.mOnDoneClickListener = new View.OnClickListener(){

            public void onClick(View view) {
                EditSlideDurationActivity.this.editDone();
            }
        };
    }

    private void notifyUser(int n) {
        this.mDur.requestFocus();
        this.mDur.selectAll();
        Toast.makeText((Context)this, (int)n, (int)0).show();
    }

    /*
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Lifted jumps to return sites
     */
    protected void editDone() {
        var2_1 = this.mDur.getText().toString();
        try {
            var1_3 = Integer.valueOf((String)var2_1);
            ** if (var1_3 > 0) goto lbl-1000
        }
        catch (NumberFormatException var2_2) {
            this.notifyUser(2131361886);
            return;
        }
lbl-1000: // 1 sources:
        {
            this.notifyUser(2131361887);
            return;
        }
lbl-1000: // 1 sources:
        {
        }
        this.setResult(-1, new Intent(this.mDur.getText().toString()));
        this.finish();
    }

    /*
     * Enabled aggressive block sorting
     */
    protected void onCreate(Bundle bundle) {
        int n;
        super.onCreate(bundle);
        this.setContentView(2130968604);
        MessageUtils.setWindowLayoutParams(this);
        if (bundle == null) {
            bundle = this.getIntent();
            this.mCurSlide = bundle.getIntExtra("slide_index", 1);
            this.mTotal = bundle.getIntExtra("slide_total", 1);
            n = bundle.getIntExtra("dur", 8);
        } else {
            this.mState = bundle.getBundle("state");
            this.mCurSlide = this.mState.getInt("slide_index", 1);
            this.mTotal = this.mState.getInt("slide_total", 1);
            n = this.mState.getInt("dur", 8);
        }
        this.mLabel = (TextView)this.findViewById(2131820628);
        this.mLabel.setText((CharSequence)(this.getString(2131361883) + " " + (this.mCurSlide + 1) + "/" + this.mTotal));
        this.mDur = (EditText)this.findViewById(2131820629);
        this.mDur.setText((CharSequence)String.valueOf((int)n));
        this.mDur.setOnKeyListener(this.mOnKeyListener);
        this.mDone = (Button)this.findViewById(2131820630);
        this.mDone.setOnClickListener(this.mOnDoneClickListener);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    protected void onSaveInstanceState(Bundle bundle) {
        int n;
        super.onSaveInstanceState(bundle);
        this.mState = new Bundle();
        this.mState.putInt("slide_index", this.mCurSlide);
        this.mState.putInt("slide_total", this.mTotal);
        try {
            n = Integer.parseInt((String)this.mDur.getText().toString());
        }
        catch (NumberFormatException var3_3) {
            n = 5;
        }
        this.mState.putInt("dur", n);
        bundle.putBundle("state", this.mState);
    }

}

