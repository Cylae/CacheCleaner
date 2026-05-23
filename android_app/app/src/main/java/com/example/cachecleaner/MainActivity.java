package com.example.cachecleaner;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import java.lang.reflect.Method;

public class MainActivity extends Activity {
    private static final String TAG = "CacheCleaner";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clearAllCache();
    }

    private void clearAllCache() {
        try {
            PackageManager pm = getPackageManager();
            Method[] methods = pm.getClass().getDeclaredMethods();
            Method freeStorageAndNotifyMethod = null;

            for (Method m : methods) {
                if (m.getName().equals("freeStorageAndNotify")) {
                    freeStorageAndNotifyMethod = m;
                    break;
                }
            }

            if (freeStorageAndNotifyMethod != null) {
                // freeStorageAndNotify(long freeStorageSize, IPackageDataObserver observer)
                freeStorageAndNotifyMethod.invoke(pm, Long.MAX_VALUE, null);
                Log.i(TAG, "Successfully invoked freeStorageAndNotify");
                Toast.makeText(this, "Cache clearing requested.", Toast.LENGTH_SHORT).show();
            } else {
                Log.e(TAG, "Method freeStorageAndNotify not found in PackageManager");
                Toast.makeText(this, "Failed to clear cache: Method not found.", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Log.e(TAG, "Error clearing cache", e);
            Toast.makeText(this, "Error clearing cache.", Toast.LENGTH_SHORT).show();
        }

        // Close the app shortly after triggering the clean
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, 1500);
    }
}
