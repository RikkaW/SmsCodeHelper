package rikka.smscodehelper;

import android.Manifest;
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

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getPermission(Manifest.permission.RECEIVE_SMS);


            }
        });
    }

    private void getPermission(String permission)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, new String[]{permission}, 0);
                }
                else {
                    Snackbar.make(findViewById(R.id.content), "好像不用获取权限的样子呢 ><", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 0:
                if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Snackbar.make(findViewById(R.id.content), "被拒绝了 OAQ", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                else {
                    Snackbar.make(findViewById(R.id.content), "有权限啦 www", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

}
