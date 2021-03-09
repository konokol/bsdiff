package org.ivan.bsdiff;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Debug;
import android.os.Environment;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.ivan.bspatch.BSPatchUtils;


public class MainActivity extends AppCompatActivity {

    TextView tv;
    long t1;
    long t2;

    // Storage Permissions
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    /**
     * Checks if the app has permission to write to device storage
     *
     * If the app does not has permission then the user will be prompted to grant permissions
     *
     * @param activity
     */
    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Example of a call to a native method
        tv = (TextView) findViewById(R.id.sample_text);
        String str = "BSPatch";
        tv.setText(str);


        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyStoragePermissions(MainActivity.this);
                tv.append("\n__________________");
                new PatchTask().execute();
            }
        });
    }


    class PatchTask extends AsyncTask<Object, Object, Object> {

        @Override
        protected Object doInBackground(Object[] params) {
            String oldFile = Environment.getExternalStorageDirectory().getAbsolutePath()+"/weixin6325android861.apk";
            String newFile = Environment.getExternalStorageDirectory().getAbsolutePath()+"/weixin_new.apk";
            String patch = Environment.getExternalStorageDirectory().getAbsolutePath()+"/patch.apk";
            BSPatchUtils.patch(oldFile, newFile, patch);
            Log.e("patch time: _________\n", String.valueOf(t2-t1) + "         " + Debug.getNativeHeapAllocatedSize());
            Log.e("_____________________\n", "");
            return null;
        }

        @Override
        protected void onPreExecute() {
            tv.append("\npatch start...------memory allocated:" + Debug.getNativeHeapAllocatedSize());
            t1 = System.currentTimeMillis();
        }

        @Override
        protected void onPostExecute(Object obj) {
            t2 = System.currentTimeMillis();
            tv.append("\npatch finished----memory allocated:" + Debug.getNativeHeapAllocatedSize());
            tv.append("\ncost " +(t2-t1)+" ms");
        }
    };

}
