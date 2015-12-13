package rikka.smscodehelper;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.SwitchPreference;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.readystatesoftware.systembartint.SystemBarTintManager;

import me.gitai.library.utils.SharedPreferencesUtil;
import me.gitai.library.widget.MaterialDialog;

/**
 * Created by gitai on 15-12-12.
 */
public class MainPreferences extends PreferenceActivity implements Preference.OnPreferenceChangeListener {

    private SwitchPreference preference_hide_launcher_icon;
    private boolean hidden_launcher_icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        addPreferencesFromResource(R.xml.preferences);

        setFitsSystemWindows(true);
        setTranslucentStatus(true);

        getActionBar().setDisplayShowHomeEnabled(false);

        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintResource(R.color.colorPrimaryDark);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            MaterialDialog materialDialog = new MaterialDialog(this);
            materialDialog.setMessage(R.string.tip_m);
            materialDialog.setPositiveButton(R.string.get_permission, new MaterialDialog.OnClickListener() {
                @Override
                public boolean onClick(View v, View MaterialDialog) {
                    getPermission(Manifest.permission.RECEIVE_SMS);
                    return true;
                }
            });
            materialDialog.show();
        }

        SharedPreferences.Editor editor = SharedPreferencesUtil.getEditor(null);
        SharedPreferences sharedPreferences = SharedPreferencesUtil.getInstence(null);

        hidden_launcher_icon = sharedPreferences.getBoolean("hidden_launcher_icon", false);
        hideLauncher(hidden_launcher_icon);

        preference_hide_launcher_icon = (SwitchPreference)findPreference("hidden_launcher_icon");
        preference_hide_launcher_icon.setOnPreferenceChangeListener(this);
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        switch (preference.getKey()){
            case "hidden_launcher_icon":
                hideLauncher((Boolean) newValue);
                return true;
        }
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 0:
                if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    MaterialDialog materialDialog = new MaterialDialog(this);

                    materialDialog.setTitle("OAQ");
                    materialDialog.setMessage(R.string.permission_denied);
                    materialDialog.setPositiveButton(R.string.exit, new MaterialDialog.OnClickListener() {
                        @Override
                        public boolean onClick(View v, View MaterialDialog) {
                            finish();
                            return false;
                        }
                    });
                    materialDialog.show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @TargetApi(14)
    private void setFitsSystemWindows(boolean on) {
        ((ViewGroup)findViewById(android.R.id.content)).getChildAt(0).setFitsSystemWindows(on);
    }

    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    private void getPermission(String permission)
    {
        if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{permission}, 0);
        }
    }

    private void hideLauncher(boolean hidden) {
        PackageManager p = getApplicationContext().getPackageManager();

        int state = PackageManager.COMPONENT_ENABLED_STATE_ENABLED;
        if (hidden){
            state = PackageManager.COMPONENT_ENABLED_STATE_DISABLED;
        }

        if (p.getComponentEnabledSetting(getComponentName()) != state){
            p.setComponentEnabledSetting(getComponentName(),state, PackageManager.DONT_KILL_APP);
        }
    }

}
