# Widget

Android 公共组件库 - 自定义 View 和 Dialog 控件

## 组件列表

### PermissionDialog
权限请求弹窗 - 深色模式的权限授权对话框

#### 特性
- 居中弹窗，深灰色背景 (#1A1A1A)
- 支持多个权限项显示
- Deny + Allow 双按钮布局
- 青色高亮强调

#### 使用方式

```kotlin
PermissionDialog.Builder(context)
    .setTitle("Asking for full control")
    .addPermission(R.drawable.ic_screen, "View and control screen", 
                   "Can view and control screen")
    .addPermission(R.drawable.ic_action, "View and perform actions", 
                   "Can perform actions")
    .onDeny {
        // 拒绝处理
    }
    .onAllow {
        // 允许处理
    }
    .show()
```

#### 文件结构
```
widget/
├── PermissionDialog.kt              # 弹窗类
└── res/
    ├── layout/
    │   ├── widget_dialog_permission.xml      # 弹窗布局
    │   └── widget_dialog_permission_item.xml # 权限项布局
    └── drawable/
        ├── widget_dialog_bg.xml              # 弹窗背景
        ├── widget_dialog_btn_deny.xml        # Deny 按钮
        └── widget_dialog_btn_allow.xml       # Allow 按钮
```

---

更多组件持续更新中...