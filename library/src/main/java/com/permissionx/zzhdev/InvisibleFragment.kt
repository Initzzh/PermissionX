package com.permissionx.zzhdev

import android.content.pm.PackageManager
import androidx.fragment.app.Fragment


typealias PermissionCallback = (Boolean, List<String>) -> Unit
class InvisibleFragment: Fragment() {

    // 外部调用此sdkmodule时，需要编写的逻辑处理
    private var callback: PermissionCallback? = null

    fun requestNow(cb: PermissionCallback, vararg permissions: String) {
        callback = cb

        // 执行请求权限申请
        requestPermissions(permissions, 1)
    }

    // requestPermissions会回调onRequestPermissionResult函数
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == 1) {
            val deniedList = ArrayList<String>()
            for ((index, result) in grantResults.withIndex()) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    // 权限未授权则放入deniedList中
                    deniedList.add(permissions[index])
                }

            }
            // 判断所有权限是否已授权
            val allGranted = deniedList.isEmpty()
            // callback 不为空，则执行callback()函数
            callback?.let { it(allGranted, deniedList) }
        }
    }



}