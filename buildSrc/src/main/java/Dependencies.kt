import org.gradle.api.JavaVersion

object Releases {
    const val versionCode = 10000
    const val versionName = "1.0.0"
}

object Config {
    const val minSdk = 23
    const val compileSdk = 31
    const val targetSdk = 31
    val javaVersion = JavaVersion.VERSION_1_8
    const val buildTools = "30.0.2"
}

object Versions {
    const val kotlin = "1.5.31"
    const val buildGradle = "7.0.3"

    const val databinding = "3.1.4"

    const val ktx = "1.6.0"
    const val activityKtx = "1.4.0"
    const val appCompat = "1.3.1"
    const val lifecycle = "2.4.0"
    const val arch = "2.1.0"
    const val room="2.2.6"

    const val material = "1.4.0"

    const val constraintLayout = "2.1.2"

    const val coroutines = "1.5.2"

    const val retrofit = "2.9.0"

    const val loggingInterceptor = "4.9.0"

    const val glide = "4.12.0"

    const val junit = "4.13.1"
    const val roboElectric = "4.4"
    const val junitExt = "1.1.2"
    const val espresso = "3.3.0"

    const val hilt = "2.38.1"

    // Jupiter uses 1.3.1 (was 1.4.0)
    const val activityCompose = "1.4.0"
    // Jupiter uses 1.0.3 (was 1.0.5)
    const val compose = "1.0.5"
    const val lifecycleVm = "2.4.0"

    const val navigationRuntime = "2.3.5"
    const val navigationHilt = "1.0.0-alpha03"
    // Jupiter uses 2.4.0-alpha10 (was 2.4.0-beta02)
    const val navigationCompose = "2.4.0-beta02"

    const val accompanist = "0.24.1-alpha"
}

object Deps {
    val gradle = GradleDeps
    val androidx = AndroidXDeps
    val foundation = FoundationDeps
    val coroutines = CoroutinesDeps
    val retrofit = RetrofitDeps
    val okhttp = OkHttpDeps
    val test = TestDeps
    val androidtest = AndroidTestDeps
    val glide = GlideDeps
    val databinding = DataBindingDeps
    val hilt = HiltDeps
}


object AndroidXDeps {
    const val ktx = "androidx.core:core-ktx:${Versions.ktx}"
    const val activityKtx = "androidx.activity:activity-ktx:${Versions.activityKtx}"
    const val kotlinExtensions = "androidx.core:core-ktx:${Versions.ktx}"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
    const val lifecycleLivedata = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
    const val archTest = "androidx.arch.core:core-testing:${Versions.arch}"
    const val roomRuntime = "androidx.room:room-runtime:${Versions.room}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
}

object FoundationDeps {
    const val material = "com.google.android.material:material:${Versions.material}"
}

object DataBindingDeps {
    const val compiler = "com.android.databinding:compiler:${Versions.databinding}"
}

object TestDeps {
    const val junit = "junit:junit:${Versions.junit}"
}

object CoroutinesDeps {
    const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val jvm = "org.jetbrains.kotlinx:kotlinx-coroutines-core-jvm:${Versions.coroutines}"
    const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
    const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
}

object RetrofitDeps {
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val moshi = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
    const val gson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
}

object OkHttpDeps {
    const val loggingInterceptor =
        "com.squareup.okhttp3:logging-interceptor:${Versions.loggingInterceptor}"
}

object AndroidTestDeps {
    const val junitExt = "androidx.test.ext:junit:${Versions.junitExt}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    const val roboElectric = "org.robolectric:robolectric:${Versions.roboElectric}"
    const val hiltAndroidTest = "com.google.dagger:hilt-android-testing:${Versions.hilt}"
}

object GlideDeps {
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val glideAnnotation = "com.github.bumptech.glide:compiler:${Versions.glide}"
}

object GradleDeps {
    const val buildGradle = "com.android.tools.build:gradle:${Versions.buildGradle}"
    const val kotlinGradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
}

object HiltDeps {
    const val hilt = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val hiltCore = "com.google.dagger:hilt-core:${Versions.hilt}"
    const val kapt = "com.google.dagger:hilt-compiler:${Versions.hilt}"
}

object ComposeDeps {
    const val activity = "androidx.activity:activity-compose:${Versions.activityCompose}"
    const val material = "androidx.compose.material:material:${Versions.compose}"
    const val animation = "androidx.compose.animation:animation:${Versions.compose}"
    const val uiTooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"
    const val lifecycleVm = "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.lifecycleVm}"
    const val testJunit = "androidx.compose.ui:ui-test-junit4:${Versions.compose}"
    const val navigation = "androidx.navigation:navigation-compose:${Versions.navigationCompose}"
}

object AccompanistDeps {
    const val drawablePainter = "com.google.accompanist:accompanist-drawablepainter:${Versions.accompanist}"
    const val insets = "com.google.accompanist:accompanist-insets:${Versions.accompanist}"
    const val systemUi = "com.google.accompanist:accompanist-systemuicontroller:${Versions.accompanist}"
}

object NavigationDeps {
    const val runtime = "androidx.navigation:navigation-runtime-ktx:${Versions.navigationRuntime}"
    const val hiltCompose = "androidx.hilt:hilt-navigation-compose:${Versions.navigationHilt}"
    const val compose = "androidx.navigation:navigation-compose:${Versions.navigationCompose}"
}

object CoilDeps {
    const val coilCompose = "io.coil-kt:coil-compose:1.4.0"
}

object TimberDeps {
    const val timber = "com.jakewharton.timber:timber:5.0.1"
}
