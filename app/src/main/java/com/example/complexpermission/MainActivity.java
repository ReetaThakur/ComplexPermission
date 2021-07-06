package com.example.complexpermission;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button permissionFor;
    private static final int REQEST_CODE=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        permissionFor=findViewById(R.id.btn);
        permissionFor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.CAMERA,Manifest.permission.READ_EXTERNAL_STORAGE},REQEST_CODE);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults[0]== PackageManager.PERMISSION_GRANTED&&grantResults[1]==PackageManager.PERMISSION_GRANTED){
            Toast.makeText(MainActivity.this,"Camera and Storage are Granted",Toast.LENGTH_LONG).show();
        }else if(grantResults[0]== PackageManager.PERMISSION_GRANTED&&grantResults[1]==PackageManager.PERMISSION_DENIED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, permissions[1])) {
                Toast.makeText(MainActivity.this, "Camera is granted but Storage is  Denied ", Toast.LENGTH_LONG).show();

            } else {
                Toast.makeText(MainActivity.this, "Camera is Granted but Storage is denied by selecting do not show again", Toast.LENGTH_LONG).show();
            }
        }else if(grantResults[0]== PackageManager.PERMISSION_DENIED&&grantResults[1]==PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,permissions[0])) {
                Toast.makeText(MainActivity.this, " Camera is Denied and Storage are Granted", Toast.LENGTH_LONG).show();
            }else {
                Toast.makeText(MainActivity.this, "Storage is Granted but Camera denied by selecting do not show again ", Toast.LENGTH_LONG).show();
            }
            }else if(grantResults[0]== PackageManager.PERMISSION_DENIED&&grantResults[1]==PackageManager.PERMISSION_DENIED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, permissions[0]) &&
                    ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, permissions[1])) {
                Toast.makeText(MainActivity.this, "Camera and Storage are Denied", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(MainActivity.this, "Camera and Storage are Denied by selecting do not show again", Toast.LENGTH_LONG).show();
            }
        }
    }
}