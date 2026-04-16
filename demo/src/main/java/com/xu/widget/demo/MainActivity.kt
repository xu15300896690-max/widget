package com.xu.widget.demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.xu.widget.dialog.PermissionDialog

/**
 * PermissionDialog 使用示例
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 示例 1：基本使用
        showBasicDialog()

        // 示例 2：带多权限项
        showMultiPermissionDialog()
    }

    /**
     * 示例 1：基本权限弹窗
     */
    private fun showBasicDialog() {
        PermissionDialog.Builder(this)
            .setTitle("Asking for full control")
            .addPermission(
                android.R.drawable.ic_menu_view,
                "View and control screen",
                "Can view and control screen"
            )
            .onDeny {
                // 用户拒绝
                println("User denied permission")
            }
            .onAllow {
                // 用户允许
                println("User allowed permission")
            }
            .show()
    }

    /**
     * 示例 2：多权限项弹窗
     */
    private fun showMultiPermissionDialog() {
        PermissionDialog.Builder(this)
            .setTitle("Allow app to")
            .addPermission(
                android.R.drawable.ic_menu_view,
                "View and control screen",
                "Can view all screen content"
            )
            .addPermission(
                android.R.drawable.icMenuManage,
                "View and perform actions",
                "Can click buttons and controls"
            )
            .setDenyText("Don't allow")
            .setAllowText("Allow")
            .onDeny {
                println("Permission denied")
            }
            .onAllow {
                println("Permission granted")
            }
            .show()
    }
}