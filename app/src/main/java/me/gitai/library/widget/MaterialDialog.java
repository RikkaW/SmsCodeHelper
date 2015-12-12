package me.gitai.library.widget;

/**
 * Created by gitai on 15-10-31.
 */

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import rikka.smscodehelper.R;

public class MaterialDialog {
    private static final int BUTTON_BOTTOM = 9;
    private static final int BUTTON_TOP = 9;
    private boolean mCancel;
    private Context mContext;
    private AlertDialog mAlertDialog;
    private Builder mBuilder;
    private View mView;
    private int mTitleResId;
    private CharSequence mTitle;
    private int mMessageResId;
    private CharSequence mMessage;
    private Button mPositiveButton;
    private LinearLayout.LayoutParams mLayoutParams;
    private Button mNegativeButton;
    private boolean mHasShow = false;
    private Drawable mBackgroundDrawable;
    private int mBackgroundResId;
    private View mMessageContentView;
    private DialogInterface.OnDismissListener mOnDismissListener;

    public MaterialDialog(Context context) {
        this.mContext = context;
    }

    public void show() {
        if(!this.mHasShow) {
            this.mBuilder = new Builder();
        } else {
            this.mAlertDialog.show();
        }

        this.mHasShow = true;
    }

    public MaterialDialog setView(int i) {
        return setView(i, null);
    }

    public MaterialDialog setView(int i,OnViewInflateListener listener) {
        View v = LayoutInflater.from(mContext).inflate(i, null);
        //View v = ((LayoutInflater)this.mContext.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(i, null);
        if (listener!=null)
            listener.onInflate(v);
        return setView(v);
    }

    public MaterialDialog setView(View view) {
        this.mView = view;
        if(this.mBuilder != null) {
            this.mBuilder.setView(view);
        }

        return this;
    }

    public MaterialDialog setChildsVisibility(int... ids){
        for (int i = 0; i < ids.length; i++) {
            this.mView.findViewById(ids[i]).setVisibility(View.VISIBLE);
        }
        return this;
    }

    public MaterialDialog setContentView(int i) {
        return setContentView(i,null);
    }

    public MaterialDialog setContentView(int i,OnViewInflateListener listener) {
        View v = LayoutInflater.from(mContext).inflate(i, null);

        //View v = ((LayoutInflater)this.mContext.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(i, null);
        if (listener!=null)
            listener.onInflate(v);
        return setContentView(v);
    }

    public MaterialDialog setContentView(View view) {
        this.mMessageContentView = view;
        if(this.mBuilder != null) {
            this.mBuilder.setContentView(this.mMessageContentView);
        }

        return this;
    }

    public View getContentView(){
        return this.mMessageContentView;
    }

    public MaterialDialog setContentChildsVisibility(int... ids){
        for (int i = 0; i < ids.length; i++) {
            this.mMessageContentView.findViewById(ids[i]).setVisibility(View.VISIBLE);
        }
        return this;
    }

    public MaterialDialog setBackground(Drawable drawable) {
        this.mBackgroundDrawable = drawable;
        if(this.mBuilder != null) {
            this.mBuilder.setBackground(this.mBackgroundDrawable);
        }

        return this;
    }

    public MaterialDialog setBackgroundResource(int resId) {
        this.mBackgroundResId = resId;
        if(this.mBuilder != null) {
            this.mBuilder.setBackgroundResource(this.mBackgroundResId);
        }

        return this;
    }

    public void dismiss() {
        this.mAlertDialog.dismiss();
    }

    private int dip2px(float dpValue) {
        float scale = this.mContext.getResources().getDisplayMetrics().density;
        return (int)(dpValue * scale + 0.5F);
    }

    private static boolean isLollipop() {
        return Build.VERSION.SDK_INT >= 21;
    }

    public MaterialDialog setTitle(int resId) {
        this.mTitleResId = resId;
        if(this.mBuilder != null) {
            this.mBuilder.setTitle(resId);
        }

        return this;
    }

    public MaterialDialog setTitle(CharSequence title) {
        this.mTitle = title;
        if(this.mBuilder != null) {
            this.mBuilder.setTitle(title);
        }

        return this;
    }

    public MaterialDialog setMessage(int resId) {
        this.mMessageResId = resId;
        if(this.mBuilder != null) {
            this.mBuilder.setMessage(resId);
        }

        return this;
    }

    public MaterialDialog setMessage(CharSequence message) {
        this.mMessage = message;
        if(this.mBuilder != null) {
            this.mBuilder.setMessage(message);
        }

        return this;
    }

    public MaterialDialog setPositiveButton(int resId, final OnClickListener listener) {
        this.mPositiveButton = new Button(this.mContext);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(-2, -2);
        this.mPositiveButton.setLayoutParams(params);
        this.mPositiveButton.setBackgroundResource(R.drawable.button);
        this.mPositiveButton.setTextColor(Color.argb(255, 35, 159, 242));
        this.mPositiveButton.setText(resId);
        this.mPositiveButton.setGravity(17);
        this.mPositiveButton.setTextSize(14.0F);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.setMargins(this.dip2px(2.0F), 0, this.dip2px(12.0F), this.dip2px(9.0F));
        this.mPositiveButton.setLayoutParams(layoutParams);
        this.mPositiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!=null){
                    if (listener.onClick(v,MaterialDialog.this.mView)){
                        MaterialDialog.this.dismiss();
                    }
                }else{
                    MaterialDialog.this.dismiss();
                }
            }
        });
        if(isLollipop()) {
            this.mPositiveButton.setBackgroundResource(android.R.color.transparent);
        }

        return this;
    }

    public MaterialDialog setPositiveButton(String text, final OnClickListener listener) {
        this.mPositiveButton = new Button(this.mContext);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(-2, -2);
        this.mPositiveButton.setLayoutParams(params);
        this.mPositiveButton.setBackgroundResource(R.drawable.button);
        this.mPositiveButton.setTextColor(Color.argb(255, 35, 159, 242));
        this.mPositiveButton.setText(text);
        this.mPositiveButton.setGravity(17);
        this.mPositiveButton.setTextSize(14.0F);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.setMargins(this.dip2px(2.0F), 0, this.dip2px(12.0F), this.dip2px(9.0F));
        this.mPositiveButton.setLayoutParams(layoutParams);
        this.mPositiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!=null){
                    if (listener.onClick(v,MaterialDialog.this.mView)){
                        MaterialDialog.this.dismiss();
                    }
                }else{
                    MaterialDialog.this.dismiss();
                }
            }
        });
        if(isLollipop()) {
            this.mPositiveButton.setBackgroundResource(android.R.color.transparent);
        }

        return this;
    }

    public MaterialDialog setNegativeButton(int resId, final OnClickListener listener) {
        this.mNegativeButton = new Button(this.mContext);
        this.mLayoutParams = new LinearLayout.LayoutParams(-2, -2);
        this.mNegativeButton.setLayoutParams(this.mLayoutParams);
        this.mNegativeButton.setBackgroundResource(R.drawable.button);
        this.mNegativeButton.setText(resId);
        this.mNegativeButton.setTextColor(Color.argb(222, 0, 0, 0));
        this.mNegativeButton.setTextSize(14.0F);
        this.mNegativeButton.setGravity(17);
        this.mNegativeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!=null){
                    if (listener.onClick(v,MaterialDialog.this.mView)){
                        MaterialDialog.this.dismiss();
                    }
                }else{
                    MaterialDialog.this.dismiss();
                }
            }
        });
        if(isLollipop()) {
            this.mNegativeButton.setBackgroundResource(android.R.color.transparent);
        }

        return this;
    }

    public MaterialDialog setNegativeButton(String text, final OnClickListener listener) {
        this.mNegativeButton = new Button(this.mContext);
        this.mLayoutParams = new LinearLayout.LayoutParams(-2, -2);
        this.mNegativeButton.setLayoutParams(this.mLayoutParams);
        this.mNegativeButton.setBackgroundResource(R.drawable.button);
        this.mNegativeButton.setText(text);
        this.mNegativeButton.setTextColor(Color.argb(222, 0, 0, 0));
        this.mNegativeButton.setTextSize(14.0F);
        this.mNegativeButton.setGravity(17);
        this.mNegativeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!=null){
                    if (listener.onClick(v,MaterialDialog.this.mView)){
                        MaterialDialog.this.dismiss();
                    }
                }else{
                    MaterialDialog.this.dismiss();
                }
            }
        });
        if(isLollipop()) {
            this.mNegativeButton.setBackgroundResource(android.R.color.transparent);
        }

        return this;
    }

    public MaterialDialog setCanceledOnTouchOutside(boolean cancel) {
        this.mCancel = cancel;
        if(this.mBuilder != null) {
            this.mBuilder.setCanceledOnTouchOutside(this.mCancel);
        }

        return this;
    }

    public MaterialDialog setOnDismissListener(DialogInterface.OnDismissListener onDismissListener) {
        this.mOnDismissListener = onDismissListener;
        return this;
    }

    private class Builder {
        private TextView mTitleView;
        private TextView mMessageView;
        private Window mAlertDialogWindow;
        private LinearLayout mButtonLayout;

        private Builder() {
            MaterialDialog.this.mAlertDialog = (new android.app.AlertDialog.Builder(MaterialDialog.this.mContext)).create();
            MaterialDialog.this.mAlertDialog.getWindow();
                    //.setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT); //TODO: 全局
            MaterialDialog.this.mAlertDialog.show();
            MaterialDialog.this.mAlertDialog.getWindow().clearFlags(131080);
            MaterialDialog.this.mAlertDialog.getWindow().setSoftInputMode(4);
            this.mAlertDialogWindow = MaterialDialog.this.mAlertDialog.getWindow();
            View contv = LayoutInflater.from(MaterialDialog.this.mContext).inflate(R.layout.layout_materialdialog, (ViewGroup)null);
            contv.setFocusable(true);
            contv.setFocusableInTouchMode(true);
            this.mAlertDialogWindow.setBackgroundDrawableResource(R.drawable.material_dialog_window);
            this.mAlertDialogWindow.setContentView(contv);
            new android.view.WindowManager.LayoutParams(-2, -2, 2002, 131072, -3);
            this.mTitleView = (TextView)this.mAlertDialogWindow.findViewById(R.id.title);
            this.mMessageView = (TextView)this.mAlertDialogWindow.findViewById(R.id.message);
            this.mButtonLayout = (LinearLayout)this.mAlertDialogWindow.findViewById(R.id.buttonLayout);
            LinearLayout linearLayout;
            if(MaterialDialog.this.mView != null) {
                linearLayout = (LinearLayout)this.mAlertDialogWindow.findViewById(R.id.contentView);
                linearLayout.removeAllViews();
                linearLayout.addView(MaterialDialog.this.mView);
            }

            if(MaterialDialog.this.mTitleResId != 0) {
                this.setTitle(MaterialDialog.this.mTitleResId);
            }

            if(MaterialDialog.this.mTitle != null) {
                this.setTitle(MaterialDialog.this.mTitle);
            }

            if(MaterialDialog.this.mTitle == null && MaterialDialog.this.mTitleResId == 0) {
                this.mTitleView.setVisibility(View.GONE);
            }

            if(MaterialDialog.this.mMessageResId != 0) {
                this.setMessage(MaterialDialog.this.mMessageResId);
            }

            if(MaterialDialog.this.mMessage != null) {
                this.setMessage(MaterialDialog.this.mMessage);
            }

            if(MaterialDialog.this.mPositiveButton != null) {
                this.mButtonLayout.addView(MaterialDialog.this.mPositiveButton);
            }

            if(MaterialDialog.this.mLayoutParams != null && MaterialDialog.this.mNegativeButton != null) {
                if(this.mButtonLayout.getChildCount() > 0) {
                    MaterialDialog.this.mLayoutParams.setMargins(MaterialDialog.this.dip2px(12.0F), 0, 0, MaterialDialog.this.dip2px(9.0F));
                    MaterialDialog.this.mNegativeButton.setLayoutParams(MaterialDialog.this.mLayoutParams);
                    this.mButtonLayout.addView(MaterialDialog.this.mNegativeButton, 1);
                } else {
                    MaterialDialog.this.mNegativeButton.setLayoutParams(MaterialDialog.this.mLayoutParams);
                    this.mButtonLayout.addView(MaterialDialog.this.mNegativeButton);
                }
            }

            if(MaterialDialog.this.mBackgroundResId != 0) {
                linearLayout = (LinearLayout)this.mAlertDialogWindow.findViewById(R.id.material_background);
                linearLayout.setBackgroundResource(MaterialDialog.this.mBackgroundResId);
            }

            if(MaterialDialog.this.mBackgroundDrawable != null) {
                linearLayout = (LinearLayout)this.mAlertDialogWindow.findViewById(R.id.material_background);
                linearLayout.setBackground(MaterialDialog.this.mBackgroundDrawable);
            }

            if(MaterialDialog.this.mMessageContentView != null) {
                this.setContentView(MaterialDialog.this.mMessageContentView);
            }

            MaterialDialog.this.mAlertDialog.setCanceledOnTouchOutside(MaterialDialog.this.mCancel);
            if(MaterialDialog.this.mOnDismissListener != null) {
                MaterialDialog.this.mAlertDialog.setOnDismissListener(MaterialDialog.this.mOnDismissListener);
            }

        }

        public void setTitle(int resId) {
            this.mTitleView.setText(resId);
        }

        public void setTitle(CharSequence title) {
            this.mTitleView.setText(title);
        }

        public void setMessage(int resId) {
            this.mMessageView.setText(resId);
        }

        public void setMessage(CharSequence message) {
            this.mMessageView.setAutoLinkMask(Linkify.WEB_URLS);
            this.mMessageView.setText(message);
        }

        public void setPositiveButton(String text, View.OnClickListener listener) {
            Button button = new Button(MaterialDialog.this.mContext);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(-2, -2);
            button.setLayoutParams(params);
            button.setBackgroundResource(R.drawable.material_card);
            button.setTextColor(Color.argb(255, 35, 159, 242));
            button.setText(text);
            button.setGravity(17);
            button.setTextSize(14.0F);
            button.setPadding(MaterialDialog.this.dip2px(12.0F), 0, MaterialDialog.this.dip2px(32.0F), MaterialDialog.this.dip2px(9.0F));
            button.setOnClickListener(listener);
            this.mButtonLayout.addView(button);
        }

        public void setNegativeButton(String text, View.OnClickListener listener) {
            Button button = new Button(MaterialDialog.this.mContext);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(-2, -2);
            button.setLayoutParams(params);
            button.setBackgroundResource(R.drawable.material_card);
            button.setText(text);
            button.setTextColor(Color.argb(222, 0, 0, 0));
            button.setTextSize(14.0F);
            button.setGravity(17);
            button.setPadding(0, 0, 0, MaterialDialog.this.dip2px(8.0F));
            button.setOnClickListener(listener);
            if(this.mButtonLayout.getChildCount() > 0) {
                params.setMargins(20, 0, 10, MaterialDialog.this.dip2px(9.0F));
                button.setLayoutParams(params);
                this.mButtonLayout.addView(button, 1);
            } else {
                button.setLayoutParams(params);
                this.mButtonLayout.addView(button);
            }

        }

        public void setView(View view) {
            LinearLayout l = (LinearLayout)this.mAlertDialogWindow.findViewById(R.id.contentView);
            l.removeAllViews();
            android.view.ViewGroup.LayoutParams layoutParams = new android.view.ViewGroup.LayoutParams(-1, -2);
            view.setLayoutParams(layoutParams);
            view.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                public void onFocusChange(View v, boolean hasFocus) {
                    System.out.println("-->" + hasFocus);
                    Builder.this.mAlertDialogWindow.setSoftInputMode(5);
                    InputMethodManager imm = (InputMethodManager)MaterialDialog.this.mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(2, 1);
                }
            });
            l.addView(view);
            if(view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup)view;

                int i;
                for(i = 0; i < viewGroup.getChildCount(); ++i) {
                    if(viewGroup.getChildAt(i) instanceof EditText) {
                        EditText autoCompleteTextView = (EditText)viewGroup.getChildAt(i);
                        autoCompleteTextView.setFocusable(true);
                        autoCompleteTextView.requestFocus();
                        autoCompleteTextView.setFocusableInTouchMode(true);
                    }
                }

                for(i = 0; i < viewGroup.getChildCount(); ++i) {
                    if(viewGroup.getChildAt(i) instanceof AutoCompleteTextView) {
                        AutoCompleteTextView var7 = (AutoCompleteTextView)viewGroup.getChildAt(i);
                        var7.setFocusable(true);
                        var7.requestFocus();
                        var7.setFocusableInTouchMode(true);
                    }
                }
            }

        }

        public void setContentView(View contentView) {
            android.view.ViewGroup.LayoutParams layoutParams = new android.view.ViewGroup.LayoutParams(-1, -2);
            contentView.setLayoutParams(layoutParams);
            LinearLayout linearLayout = (LinearLayout)this.mAlertDialogWindow.findViewById(R.id.message_content_view);
            if(linearLayout != null) {
                linearLayout.removeAllViews();
                linearLayout.addView(contentView);
            }

            for(int i = 0; i < linearLayout.getChildCount(); ++i) {
                if(linearLayout.getChildAt(i) instanceof AutoCompleteTextView) {
                    AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView)linearLayout.getChildAt(i);
                    autoCompleteTextView.setFocusable(true);
                    autoCompleteTextView.requestFocus();
                    autoCompleteTextView.setFocusableInTouchMode(true);
                }
            }

        }

        public void setBackground(Drawable drawable) {
            LinearLayout linearLayout = (LinearLayout)this.mAlertDialogWindow.findViewById(R.id.material_background);
            linearLayout.setBackground(drawable);
        }

        public void setBackgroundResource(int resId) {
            LinearLayout linearLayout = (LinearLayout)this.mAlertDialogWindow.findViewById(R.id.material_background);
            linearLayout.setBackgroundResource(resId);
        }

        public void setCanceledOnTouchOutside(boolean canceledOnTouchOutside) {
            MaterialDialog.this.mAlertDialog.setCanceledOnTouchOutside(canceledOnTouchOutside);
        }
    }

    public interface OnClickListener {
        boolean onClick(View v, View MaterialDialog);
    }

    public interface OnViewInflateListener {
        boolean onInflate(View v);
    }
}
