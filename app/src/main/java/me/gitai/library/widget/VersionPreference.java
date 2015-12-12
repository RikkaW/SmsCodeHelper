package me.gitai.library.widget;

import android.content.Context;
import android.preference.Preference;
import android.util.AttributeSet;

import rikka.smscodehelper.BuildConfig;
import rikka.smscodehelper.R;

/**
 * Created by gitai on 15-11-5.
 */
public class VersionPreference extends Preference{

    public VersionPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public VersionPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public VersionPreference(Context context) {
        super(context);
        init();
    }


    private void init(){
        setSummary(
                String.format(
                        "%s %s-%s(%s)",
                        getContext().getString(R.string.app_name),
                        BuildConfig.VERSION_NAME,
                        BuildConfig.BUILD_TYPE,
                        BuildConfig.VERSION_CODE));

        // Remove Update task on 15-12-12.
        // setOnPreferenceClickListener(this);
    }
}
