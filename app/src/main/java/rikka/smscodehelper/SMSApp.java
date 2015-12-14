package rikka.smscodehelper;

import android.app.Application;

import me.gitai.library.utils.SharedPreferencesUtil;
import me.gitai.library.utils.ToastUtil;

/**
 * Created by gitai on 15-12-12.
 */
public class SMSApp extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        SharedPreferencesUtil.initialize(this);
        ToastUtil.initialize(this);
    }
}
