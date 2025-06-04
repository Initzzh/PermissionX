package com.sunnyweather.permissionx


import android.Manifest
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.permissionx.zzhdev.PermissionX

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val makeCallBtn = findViewById<Button>(R.id.makeCallBtn)
        makeCallBtn.setOnClickListener {
            PermissionX.request(this,
                Manifest.permission.CALL_PHONE ) { allGranted, deniedList ->
                    if  (allGranted) {
                        call()
                    } else {
                        Toast.makeText(this,
                            "You denied $deniedList ",
                            Toast.LENGTH_SHORT).show()
                    }
            }

          /*  // 一次申请多个权限
            PermissionX.request(this,
                Manifest.permission.CALL_PHONE,
                Manifest.permission.READ_CONTACTS) { allGranted, deniedList ->
                if (allGranted) {
                    Toast.makeText(this, "All permissions are granted", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "You denied $deniedList", Toast.LENGTH_SHORT).show()
                }

            }*/
        }
    }

    private fun call(){
        try {
            val callIntent = Intent(Intent.ACTION_CALL)
            callIntent.data = Uri.parse("tel:10086")
            startActivity(callIntent)
        } catch (e:  SecurityException) {
            e.printStackTrace()
        }



    }
}