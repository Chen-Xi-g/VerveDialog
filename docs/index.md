---
title: 说明
layout: home
nav_order: 1
usetocbot: true
---

<div style="text-align: center;">
  <div>
      <img src="/assets/images/DialogIcon.png" alt="Dialog Icon">
  </div>

  <h1 style="font-size: 24px; font-weight: bold; margin: 10px 0;">适用于 Compose 的 Dialog</h1>

  <div style="margin: 20px 0;">
      <a href="https://jitpack.io/#Chen-Xi-g/VerveDialog">
          <img src="https://jitpack.io/v/Chen-Xi-g/VerveDialog.svg" alt="Jitpack Version">
      </a>
      <a href="https://developer.android.com/compose">
          <img src="https://img.shields.io/badge/Jetpack%20Compose%20-63C487?logo=jetpackcompose&logoColor=white" alt="Jetpack Compose Badge">
      </a>
      <a href="https://developer.android.com/kotlin?hl=zh-cn">
          <img src="https://img.shields.io/badge/Language-Kotlin-2376bc?labelColor=5384EC&color=7F32DA" alt="Kotlin Language Badge">
      </a>
      <a href="https://github.com/Chen-Xi-g/VerveDialog/blob/main/LICENSE">
          <img src="https://img.shields.io/badge/License-MIT-38519B?labelColor=272c3c" alt="License Badge">
      </a>
  </div>

</div>


## 说明

[Verve Dialog]是一个基于[Jetpack Compose]的高度可定制化 Dialog 库，通过全局配置和灵活的 API，您可以轻松调整对话框的样式和功能。

{: .note-title }
> 特性
>
> - **自定义样式**：支持背景颜色、形状、边框等自定义设置。
> - **可配置按钮**：可自定义对话框中的按钮布局。
> - **自动关闭**：支持在点击按钮后自动关闭对话框。
> - **底部对话框**：支持底部对话框样式，并可自定义拖拽手柄的显示。
> - **高扩展性：**支持用户根据不同需求进行扩展使用。

## 安装

**Set 1.** 添加[JitPack]存储库到`Gradle`中。

{% tabs gradle %}

{% tab gradle Gradle (Kotlin) %}
```kotlin
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven {
            url = uri("https://jitpack.io")
        }
    }
}
```
{% endtab %}

{% tab gradle Gradle (Short) %}
```groovy
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
        maven { url 'https://jitpack.io' }
    }
}
```
{% endtab %}

{% endtabs %}

**Set 2.** 添加依赖，替换Tag到最新版本。

[![JitPack](https://jitpack.io/v/Chen-Xi-g/VerveDialog.svg)](https://jitpack.io/#Chen-Xi-g/VerveDialog)

{% tabs implementation %}

{% tab implementation Gradle(Kotlin) %}
```kotlin
dependencies {
    implementation("com.github.Chen-Xi-g:VerveDialog:Tag")
}
```
{% endtab %}

{% tab implementation Gradle(Short) %}
```groovy
dependencies {
    implementation 'com.github.Chen-Xi-g:VerveDialog:Tag'
}
```
{% endtab %}

{% endtabs%}



[开始使用]({{ '/navigation/Use.html' | relative_url }}){: .btn .mr-4}



[Verve Dialog]: https://github.com/Chen-Xi-g/VerveDialog
[Jetpack Compose]: https://developer.android.com/compose
[JitPack]: https://jitpack.io/#Chen-Xi-g/VerveDialog
