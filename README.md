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

## Demo 示例

查看 [demo/](demo/) 文件夹，包含完整的演示项目

### 运行 Demo

```bash
# 在 Android Studio 中打开 demo 模块
# 运行项目即可查看效果
```

### 示例代码

```kotlin
// 基本使用
PermissionDialog.Builder(context)
    .setTitle("Asking for full control")
    .addPermission(R.drawable.ic_menu_view, "View and control screen", "Can view and control screen")
    .onDeny { /* 拒绝 */ }
    .onAllow { /* 允许 */ }
    .show()

// 多权限项
PermissionDialog.Builder(context)
    .setTitle("Allow app to")
    .addPermission(icon, "权限1", "描述1")
    .addPermission(icon, "权限2", "描述2")
    .setDenyText("Don't allow")
    .setAllowText("Allow")
    .show()
```

---

更多组件持续更新中...