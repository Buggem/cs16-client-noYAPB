package in.celest.xash3d.cs16client;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    ActivityResultLauncher<Intent> mActivityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode() != Activity.RESULT_OK) {
                Log.e(TAG, result.toString());
            }
        }
    });

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            getPackageManager().getPackageInfo("su.xash.engine", 0);
        } catch (PackageManager.NameNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=su.xash.engine")));
            return;
        }

        mActivityResultLauncher.launch(new Intent().setComponent(new ComponentName("su.xash.engine", "su.xash.engine.XashActivity")).putExtra("gamedir", "cstrike").putExtra("gamelibdir", getApplicationInfo().nativeLibraryDir).putExtra("package", getPackageName()));
    }
}