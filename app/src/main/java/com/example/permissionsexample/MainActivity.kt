package com.example.permissionsexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import android.Manifest
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnCameraPermission.setOnClickListener{
            val a : String = Manifest.permission.CAMERA
            if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "You already have the permission for the camera and GPS.", Toast.LENGTH_LONG).show()
            } else {
                // Request Permission
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA, Manifest.permission.ACCESS_FINE_LOCATION), CAMERA_AND_FINE_LOCATION_PERMISSION)
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == CAMERA_PERMISSION_CODE){
            if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission granted for camera", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "oops you just denied the permission for camera.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        private const val CAMERA_PERMISSION_CODE = 1
        private const val FINE_LOCATION_PERMISSION_CODE = 2
        private const val CAMERA_AND_FINE_LOCATION_PERMISSION = 12
    }
}