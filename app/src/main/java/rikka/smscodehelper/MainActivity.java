package rikka.smscodehelper;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.DialogStyle);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            builder.setMessage("在 Android 6.0 以上需要由用户授予权限，点击获取后，如果没有权限将弹出授权窗口，授权后应用将会从启动器隐藏。");
            builder.setPositiveButton("获取权限", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    getPermission(Manifest.permission.RECEIVE_SMS);
                }
            });
        } else {
            builder.setMessage("点击确认后应用将会从启动器隐藏。");
            builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    hideLauncher();
                    finish();
                }
            });
        }

        AlertDialog alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();

        /*Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getPermission(Manifest.permission.RECEIVE_SMS);
            }
        });*/
    }

    private void getPermission(String permission)
    {
        if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{permission}, 0);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 0:
                if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.DialogStyle);

                    builder.setTitle("OAQ");
                    builder.setMessage("被拒绝了");
                    builder.setPositiveButton("退出", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    });
                    builder.create().show();
                }
                else {
                    finish();
                    hideLauncher();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private void hideLauncher() {
        PackageManager p = getApplicationContext().getPackageManager();
        p.setComponentEnabledSetting(getComponentName(),PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
    }
}
