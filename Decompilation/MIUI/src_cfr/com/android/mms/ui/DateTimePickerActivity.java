/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.AlertDialog
 *  android.app.AlertDialog$Builder
 *  android.content.Context
 *  android.content.DialogInterface
 *  android.content.DialogInterface$OnClickListener
 *  android.content.Intent
 *  android.content.res.Resources
 *  android.graphics.drawable.Drawable
 *  android.os.Bundle
 *  android.text.TextUtils
 *  android.text.format.DateFormat
 *  android.text.format.DateUtils
 *  android.view.View
 *  android.view.View$OnClickListener
 *  android.widget.Button
 *  android.widget.RadioButton
 *  android.widget.RadioGroup
 *  android.widget.RadioGroup$OnCheckedChangeListener
 *  android.widget.TextView
 *  java.lang.Boolean
 *  java.lang.Integer
 *  java.lang.Math
 *  java.lang.Object
 *  java.lang.String
 *  java.lang.StringBuffer
 *  java.lang.System
 *  java.util.Calendar
 *  miui.R
 *  miui.R$layout
 *  miui.app.ActionBar
 *  miui.app.Activity
 *  miui.widget.DatePicker
 *  miui.widget.DatePicker$OnDateChangedListener
 *  miui.widget.TimePicker
 *  miui.widget.TimePicker$OnTimeChangedListener
 */
package com.android.mms.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import java.util.Calendar;
import miui.R;
import miui.app.ActionBar;
import miui.app.Activity;
import miui.widget.DatePicker;
import miui.widget.TimePicker;

public class DateTimePickerActivity
extends Activity
implements View.OnClickListener,
DatePicker.OnDateChangedListener,
TimePicker.OnTimeChangedListener {
    public static String CONTENT_TYPE = "vnd.android.cursor.item/datetime";
    public static String FIELD_TIME;
    public static String FIELD_TITLE;
    private int mActionBarCustomPaddingH;
    private int mActionBarCustomPaddingV;
    private Calendar mCalendar;
    private Button mCancelButton;
    private RadioButton mDateButton;
    private DatePicker mDatePicker;
    private RadioGroup mDateTimeGroup;
    private boolean mIs24HourFormat = false;
    private boolean mIsDate = true;
    private Button mOkButton;
    private RadioGroup.OnCheckedChangeListener mOnCheckedChangeListener;
    private RadioButton mTimeButton;
    private TimePicker mTimePicker;
    private String mTitle;
    private TextView mTitleText;

    static {
        FIELD_TITLE = "title";
        FIELD_TIME = "time";
    }

    public DateTimePickerActivity() {
        this.mOnCheckedChangeListener = new RadioGroup.OnCheckedChangeListener(){

            public void onCheckedChanged(RadioGroup radioGroup, int n) {
                if (radioGroup.getCheckedRadioButtonId() == 2131820610) {
                    DateTimePickerActivity.this.mIsDate = true;
                    DateTimePickerActivity.this.setView(DateTimePickerActivity.this.mIsDate);
                    return;
                }
                DateTimePickerActivity.this.mIsDate = false;
                DateTimePickerActivity.this.setView(DateTimePickerActivity.this.mIsDate);
            }
        };
    }

    private void configureTitleView() {
        ActionBar actionBar = this.getActionBar();
        actionBar.setCustomView(R.layout.edit_mode_title);
        actionBar = actionBar.getCustomView();
        actionBar.setBackgroundDrawable(null);
        actionBar.setPaddingRelative(this.mActionBarCustomPaddingH, this.mActionBarCustomPaddingV, this.mActionBarCustomPaddingH, 0);
        this.mTitleText = (TextView)actionBar.findViewById(16908310);
        this.mCancelButton = (Button)actionBar.findViewById(16908313);
        this.mCancelButton.setText(17039360);
        this.mOkButton = (Button)actionBar.findViewById(16908314);
        this.mOkButton.setText(17039370);
        this.mOkButton.setOnClickListener((View.OnClickListener)this);
        this.mCancelButton.setOnClickListener((View.OnClickListener)this);
        this.mTitleText.setText((CharSequence)this.mTitle);
    }

    private void formatDateAndShow() {
        long l = this.mCalendar.getTimeInMillis();
        String string2 = DateUtils.formatDateTime((Context)this, (long)l, (int)20);
        String string3 = DateUtils.formatDateTime((Context)this, (long)l, (int)2);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(string2);
        if (!TextUtils.isEmpty((CharSequence)string3)) {
            stringBuffer.append("      ");
            stringBuffer.append(string3);
        }
        this.mDateButton.setText((CharSequence)stringBuffer.toString());
    }

    /*
     * Enabled aggressive block sorting
     */
    private void formatTimeAndShow() {
        long l = this.mCalendar.getTimeInMillis();
        int n = this.mIs24HourFormat ? 128 : 64;
        String string2 = DateUtils.formatDateTime((Context)this, (long)l, (int)(n | 1));
        this.mTimeButton.setText((CharSequence)string2);
    }

    private void initLayout() {
        this.setContentView(2130968601);
        this.mTimePicker = (TimePicker)this.findViewById(2131820613);
        this.mTimePicker.setIs24HourView(Boolean.valueOf((boolean)this.mIs24HourFormat));
        this.mDatePicker = (DatePicker)this.findViewById(2131820612);
        this.mDateTimeGroup = (RadioGroup)this.findViewById(2131820609);
        this.mDateTimeGroup.setOnCheckedChangeListener(this.mOnCheckedChangeListener);
        this.mTimeButton = (RadioButton)this.findViewById(2131820611);
        this.mDateButton = (RadioButton)this.findViewById(2131820610);
        this.setView(this.mIsDate);
        this.initPickers();
        this.formatDateAndShow();
        this.formatTimeAndShow();
    }

    private void initPickers() {
        int n = this.mCalendar.get(1);
        int n2 = this.mCalendar.get(2);
        int n3 = this.mCalendar.get(5);
        int n4 = this.mCalendar.get(11);
        int n5 = this.mCalendar.get(12);
        this.mDatePicker.init(n, n2, n3, (DatePicker.OnDateChangedListener)this);
        long l = Math.min((long)this.mCalendar.getTimeInMillis(), (long)System.currentTimeMillis()) / 86400000 * 86400000;
        this.mDatePicker.setMinDate(l);
        this.mDatePicker.setMaxDate(l + 315360000000L);
        this.mTimePicker.setCurrentHour(Integer.valueOf((int)n4));
        this.mTimePicker.setCurrentMinute(Integer.valueOf((int)n5));
        this.mTimePicker.setOnTimeChangedListener((TimePicker.OnTimeChangedListener)this);
    }

    /*
     * Enabled aggressive block sorting
     */
    private void setView(boolean bl) {
        int n = 0;
        TimePicker timePicker = this.mTimePicker;
        int n2 = bl ? 8 : 0;
        timePicker.setVisibility(n2);
        timePicker = this.mDatePicker;
        n2 = bl ? n : 8;
        timePicker.setVisibility(n2);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            default: {
                return;
            }
            case 16908314: {
                if (this.mCalendar.getTimeInMillis() < System.currentTimeMillis()) {
                    new AlertDialog.Builder((Context)this).setTitle(2131362153).setPositiveButton(17039370, null).setCancelable(true).show();
                    return;
                }
                this.mCalendar.set(13, 0);
                this.mCalendar.set(14, 0);
                view = new Intent();
                view.putExtra(FIELD_TIME, this.mCalendar.getTimeInMillis());
                this.setResult(-1, (Intent)view);
                this.finish();
                return;
            }
            case 16908313: 
        }
        view = new Intent();
        view.putExtra(FIELD_TIME, this.mCalendar.getTimeInMillis());
        this.setResult(0, (Intent)view);
        this.finish();
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mCalendar = Calendar.getInstance();
        bundle = this.getIntent();
        long l = bundle.getLongExtra(FIELD_TIME, System.currentTimeMillis());
        this.mCalendar.setTimeInMillis(l);
        this.mTitle = bundle.getStringExtra(FIELD_TITLE);
        this.mIs24HourFormat = DateFormat.is24HourFormat((Context)this);
        this.mActionBarCustomPaddingH = this.getResources().getDimensionPixelSize(2131427427);
        this.mActionBarCustomPaddingV = this.getResources().getDimensionPixelSize(2131427428);
        this.configureTitleView();
        this.initLayout();
    }

    public void onDateChanged(DatePicker datePicker, int n, int n2, int n3, boolean bl) {
        this.mCalendar.set(1, n);
        this.mCalendar.set(2, n2);
        this.mCalendar.set(5, n3);
        this.formatDateAndShow();
    }

    public void onTimeChanged(TimePicker timePicker, int n, int n2) {
        this.mCalendar.set(11, n);
        this.mCalendar.set(12, n2);
        this.formatTimeAndShow();
    }

}

