package com.example.cachecleaner;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
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
                freeStorageAndNotifyMethod.invoke(pm, Long.MAX_VALUE, null);
                Log.i(TAG, "Successfully invoked freeStorageAndNotify");
            } else {
                Log.e(TAG, "Method freeStorageAndNotify not found in PackageManager");
                Toast.makeText(this, "Échec: méthode introuvable.", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Log.e(TAG, "Error clearing cache", e);
            Toast.makeText(this, "Erreur lors du nettoyage.", Toast.LENGTH_SHORT).show();
        }

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, "Nettoyage terminé", Toast.LENGTH_SHORT).show();
                finish();
            }
        }, 3000);
    }
}
