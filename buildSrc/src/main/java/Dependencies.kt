import org.gradle.api.JavaVersion

object Releases {
    const val versionCode = 10000
    const val versionName = "1.0.0"
}

object Config {
    val minSdk = 21
    val compileSdk = 30
    val targetSdk = 30
    val javaVersion = JavaVersion.VERSION_1_8
    val buildTools = "29.0.2"
}


object Versions {
    const val kotlin = "1.4.31"
    const val buildGradle = "4.1.3"

    const val databinding = "3.1.4"

    const val ktx = "1.3.2"
    const val appCompat = "1.2.0"
    const val lifecycle = "2.3.1"
    const val arch = "2.1.0"
    const val room="2.2.6"

    const val material = "1.3.0"

    const val constraintLayout = "2.0.4"

    const val coroutines = "1.4.2"

    const val retrofit = "2.9.0"

    const val loggingInterceptor = "4.9.0"

    const val glide = "4.12.0"

    const val toothpick = "3.1.0"

    const val junit = "4.13.1"
    const val junitExt = "1.1.2"
    const val espresso = "3.3.0"
}

object Deps {
    val kotlinStandardLibrary = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
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
}


object AndroidXDeps {
    const val ktx = "androidx.core:core-ktx:${Versions.ktx}"
    const val kotlinExtensions = "androidx.core:core-ktx:${Versions.ktx}"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    const val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
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
    val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
}

object RetrofitDeps {
    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    val moshi = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
    val gson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
}

object OkHttpDeps {
    val loggingInterceptor =
        "com.squareup.okhttp3:logging-interceptor:${Versions.loggingInterceptor}"
}

object AndroidTestDeps {
    const val junitExt = "androidx.test.ext:junit:${Versions.junitExt}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
}

object GlideDeps {
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val glideAnnotation = "com.github.bumptech.glide:compiler:${Versions.glide}"
}

object ToothpickDeps {
    const val ktp = "com.github.stephanenicolas.toothpick:ktp:${Versions.toothpick}"
    const val compiler = "com.github.stephanenicolas.toothpick:toothpick-compiler:${Versions.toothpick}"
    const val smoothie = "com.github.stephanenicolas.toothpick:smoothie-androidx:${Versions.toothpick}"
}

object GradleDeps {
    val buildGradle = "com.android.tools.build:gradle:${Versions.buildGradle}"
    val kotlinGradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
}
