/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.Activity
 *  android.app.Dialog
 *  android.content.Context
 *  android.content.DialogInterface
 *  android.content.DialogInterface$OnCancelListener
 *  android.content.DialogInterface$OnDismissListener
 *  android.os.Bundle
 *  android.view.LayoutInflater
 *  android.view.View
 *  android.view.ViewParent
 *  android.view.Window
 *  java.lang.Object
 *  java.lang.String
 */
package android.support.v4.app;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewParent;
import android.view.Window;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class DialogFragment
extends Fragment
implements DialogInterface.OnCancelListener,
DialogInterface.OnDismissListener {
    private static final String SAVED_BACK_STACK_ID = "android:backStackId";
    private static final String SAVED_CANCELABLE = "android:cancelable";
    private static final String SAVED_DIALOG_STATE_TAG = "android:savedDialogState";
    private static final String SAVED_SHOWS_DIALOG = "android:showsDialog";
    private static final String SAVED_STYLE = "android:style";
    private static final String SAVED_THEME = "android:theme";
    public static final int STYLE_NORMAL = 0;
    public static final int STYLE_NO_FRAME = 2;
    public static final int STYLE_NO_INPUT = 3;
    public static final int STYLE_NO_TITLE = 1;
    int mBackStackId = -1;
    boolean mCancelable = true;
    Dialog mDialog;
    boolean mDismissed;
    boolean mShownByMe;
    boolean mShowsDialog = true;
    int mStyle = 0;
    int mTheme = 0;
    boolean mViewDestroyed;

    public void dismiss() {
        this.dismissInternal(false);
    }

    public void dismissAllowingStateLoss() {
        this.dismissInternal(true);
    }

    void dismissInternal(boolean bl2) {
        if (this.mDismissed) {
            return;
        }
        this.mDismissed = true;
        this.mShownByMe = false;
        if (this.mDialog != null) {
            this.mDialog.dismiss();
            this.mDialog = null;
        }
        this.mViewDestroyed = true;
        if (this.mBackStackId >= 0) {
            this.getFragmentManager().popBackStack(this.mBackStackId, 1);
            this.mBackStackId = -1;
            return;
        }
        FragmentTransaction fragmentTransaction = this.getFragmentManager().beginTransaction();
        fragmentTransaction.remove(this);
        if (bl2) {
            fragmentTransaction.commitAllowingStateLoss();
            return;
        }
        fragmentTransaction.commit();
    }

    public Dialog getDialog() {
        return this.mDialog;
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public LayoutInflater getLayoutInflater(Bundle bundle) {
        if (!this.mShowsDialog) {
            return super.getLayoutInflater(bundle);
        }
        this.mDialog = this.onCreateDialog(bundle);
        switch (this.mStyle) {
            case 3: {
                this.mDialog.getWindow().addFlags(24);
            }
            case 1: 
            case 2: {
                this.mDialog.requestWindowFeature(1);
            }
        }
        if (this.mDialog != null) {
            return (LayoutInflater)this.mDialog.getContext().getSystemService("layout_inflater");
        }
        return (LayoutInflater)this.mActivity.getSystemService("layout_inflater");
    }

    public boolean getShowsDialog() {
        return this.mShowsDialog;
    }

    public int getTheme() {
        return this.mTheme;
    }

    public boolean isCancelable() {
        return this.mCancelable;
    }

    /*
     * Enabled aggressive block sorting
     * Lifted jumps to return sites
     */
    @Override
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (!this.mShowsDialog) {
            return;
        }
        View view = this.getView();
        if (view != null) {
            if (view.getParent() != null) {
                throw new IllegalStateException("DialogFragment can not be attached to a container view");
            }
            this.mDialog.setContentView(view);
        }
        this.mDialog.setOwnerActivity((Activity)this.getActivity());
        this.mDialog.setCancelable(this.mCancelable);
        this.mDialog.setOnCancelListener((DialogInterface.OnCancelListener)this);
        this.mDialog.setOnDismissListener((DialogInterface.OnDismissListener)this);
        if (bundle == null) return;
        if ((bundle = bundle.getBundle("android:savedDialogState")) == null) return;
        this.mDialog.onRestoreInstanceState(bundle);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (!this.mShownByMe) {
            this.mDismissed = false;
        }
    }

    public void onCancel(DialogInterface dialogInterface) {
    }

    /*
     * Enabled aggressive block sorting
     */
    @Override
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        boolean bl2 = this.mContainerId == 0;
        this.mShowsDialog = bl2;
        if (bundle != null) {
            this.mStyle = bundle.getInt("android:style", 0);
            this.mTheme = bundle.getInt("android:theme", 0);
            this.mCancelable = bundle.getBoolean("android:cancelable", true);
            this.mShowsDialog = bundle.getBoolean("android:showsDialog", this.mShowsDialog);
            this.mBackStackId = bundle.getInt("android:backStackId", -1);
        }
    }

    @NonNull
    public Dialog onCreateDialog(Bundle bundle) {
        return new Dialog((Context)this.getActivity(), this.getTheme());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (this.mDialog != null) {
            this.mViewDestroyed = true;
            this.mDialog.dismiss();
            this.mDialog = null;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (!this.mShownByMe && !this.mDismissed) {
            this.mDismissed = true;
        }
    }

    public void onDismiss(DialogInterface dialogInterface) {
        if (!this.mViewDestroyed) {
            this.dismissInternal(true);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle bundle) {
        Bundle bundle2;
        super.onSaveInstanceState(bundle);
        if (this.mDialog != null && (bundle2 = this.mDialog.onSaveInstanceState()) != null) {
            bundle.putBundle("android:savedDialogState", bundle2);
        }
        if (this.mStyle != 0) {
            bundle.putInt("android:style", this.mStyle);
        }
        if (this.mTheme != 0) {
            bundle.putInt("android:theme", this.mTheme);
        }
        if (!this.mCancelable) {
            bundle.putBoolean("android:cancelable", this.mCancelable);
        }
        if (!this.mShowsDialog) {
            bundle.putBoolean("android:showsDialog", this.mShowsDialog);
        }
        if (this.mBackStackId != -1) {
            bundle.putInt("android:backStackId", this.mBackStackId);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (this.mDialog != null) {
            this.mViewDestroyed = false;
            this.mDialog.show();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (this.mDialog != null) {
            this.mDialog.hide();
        }
    }

    public void setCancelable(boolean bl2) {
        this.mCancelable = bl2;
        if (this.mDialog != null) {
            this.mDialog.setCancelable(bl2);
        }
    }

    public void setShowsDialog(boolean bl2) {
        this.mShowsDialog = bl2;
    }

    public void setStyle(int n2, int n3) {
        this.mStyle = n2;
        if (this.mStyle == 2 || this.mStyle == 3) {
            this.mTheme = 16973913;
        }
        if (n3 != 0) {
            this.mTheme = n3;
        }
    }

    public int show(FragmentTransaction fragmentTransaction, String string2) {
        this.mDismissed = false;
        this.mShownByMe = true;
        fragmentTransaction.add(this, string2);
        this.mViewDestroyed = false;
        this.mBackStackId = fragmentTransaction.commit();
        return this.mBackStackId;
    }

    public void show(FragmentManager object, String string2) {
        this.mDismissed = false;
        this.mShownByMe = true;
        object = object.beginTransaction();
        object.add(this, string2);
        object.commit();
    }

    @Retention(value=RetentionPolicy.SOURCE)
    @IntDef(value={0, 1, 2, 3})
    static @interface DialogStyle {
    }

}

