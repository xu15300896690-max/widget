package com.xu.widget.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

/**
 * 权限请求弹窗
 * 深色模式，居中显示，用于 Android 权限授权确认
 * 
 * UI 特征：
 * - 居中弹窗，深灰色背景 (#1A1A1A)
 * - 白色主标题，浅灰色描述文字
 * - 两个按钮：Deny(左) + Allow(右)
 * - 带图标的内容区域
 */
class PermissionDialog(context: Context) : Dialog(context) {

    private var mTitle: String = ""
    private var mPermissionItems: List<PermissionItem> = emptyList()
    private var mDenyText: String = "Deny"
    private var mAllowText: String = "Allow"
    private var mOnDenyListener: (() -> Unit)? = null
    private var mOnAllowListener: (() -> Unit)? = null

    data class PermissionItem(
        val iconRes: Int,
        val title: String,
        val description: String
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window?.apply {
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT
            )
            setDimAmount(0.6f) // 背景遮罩透明度
        }

        setContentView(R.layout.widget_dialog_permission)
        setCancelable(true)
        setCanceledOnTouchOutside(true)

        initView()
    }

    private fun initView() {
        val titleView = findViewById<TextView>(R.id.dialog_title)
        val permissionContainer = findViewById<LinearLayout>(R.id.permission_container)
        val btnDeny = findViewById<TextView>(R.id.btn_deny)
        val btnAllow = findViewById<TextView>(R.id.btn_allow)

        // 设置标题
        titleView.text = mTitle

        // 设置权限列表
        permissionContainer.removeAllViews()
        mPermissionItems.forEach { item ->
            val itemView = layoutInflater.inflate(
                R.layout.widget_dialog_permission_item,
                permissionContainer,
                false
            )
            itemView.findViewById<ImageView>(R.id.permission_icon)
                .setImageResource(item.iconRes)
            itemView.findViewById<TextView>(R.id.permission_title).text = item.title
            itemView.findViewById<TextView>(R.id.permission_desc).text = item.description
            permissionContainer.addView(itemView)
        }

        // 设置按钮
        btnDeny.text = mDenyText
        btnAllow.text = mAllowText

        btnDeny.setOnClickListener {
            mOnDenyListener?.invoke()
            dismiss()
        }

        btnAllow.setOnClickListener {
            mOnAllowListener?.invoke()
            dismiss()
        }
    }

    class Builder(private val context: Context) {
        private var mTitle: String = "Asking for full control"
        private var mPermissionItems: MutableList<PermissionItem> = mutableListOf()
        private var mDenyText: String = "Deny"
        private var mAllowText: String = "Allow"
        private var mOnDenyListener: (() -> Unit)? = null
        private var mOnAllowListener: (() -> Unit)? = null

        fun setTitle(title: String): Builder {
            mTitle = title
            return this
        }

        fun addPermission(iconRes: Int, title: String, description: String): Builder {
            mPermissionItems.add(PermissionItem(iconRes, title, description))
            return this
        }

        fun setDenyText(text: String): Builder {
            mDenyText = text
            return this
        }

        fun setAllowText(text: String): Builder {
            mAllowText = text
            return this
        }

        fun onDeny(listener: () -> Unit): Builder {
            mOnDenyListener = listener
            return this
        }

        fun onAllow(listener: () -> Unit): Builder {
            mOnAllowListener = listener
            return this
        }

        fun build(): PermissionDialog {
            return PermissionDialog(context).apply {
                mTitle = this@Builder.mTitle
                mPermissionItems = this@Builder.mPermissionItems.toList()
                mDenyText = this@Builder.mDenyText
                mAllowText = this@Builder.mAllowText
                mOnDenyListener = this@Builder.mOnDenyListener
                mOnAllowListener = this@Builder.mOnAllowListener
            }
        }

        fun show(): PermissionDialog {
            return build().also { it.show() }
        }
    }
}