package com.permissionx.zzhdev

import androidx.fragment.app.FragmentActivity

object PermissionX {

    private const val TAG = "InvisibleFragment"
    fun request(activity: FragmentActivity, vararg permissions: String, callback: PermissionCallback) {
        // FragmentActivity， 调用方需要传入activity， PermissionX的InvisibleFragment会关联此activity
        // activity关联invisibleFragment
        val fragmentManager = activity.supportFragmentManager
        val exitedFragment = fragmentManager.findFragmentByTag(TAG)
        val fragment = if (exitedFragment != null) {
            exitedFragment as InvisibleFragment
        } else {
            val invisibleFragment = InvisibleFragment()
            fragmentManager.beginTransaction().add(invisibleFragment, TAG).commitNow()
            invisibleFragment
        }
        // 获取invisibleFragment后 执行requestNow, 进行权限申请
        // vararg permissions ，是一个数组，不能直接传入到另一个可变参数函数中，要通过*permissions的方式传入到另一个可变参数的函数中；
        fragment.requestNow(callback, *permissions)
    }
}