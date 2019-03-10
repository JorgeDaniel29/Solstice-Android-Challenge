object Dependencies {

    //Application
    const val appId = "jorge.gonzalez.solstice.challenge"

    //Gradle
    const val gradle = "com.android.tools.build:gradle:${Versions.gradle}"
    const val kotlinGradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"


    //Kotlin
    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    const val ktx = "androidx.core:core-ktx:${Versions.ktx}"
    const val rxKotlin = "io.reactivex.rxjava2:rxkotlin:${Versions.rxKotlin}"

    // Androidx
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val androidxCore = "androidx.core:core:${Versions.androidxCore}"
    const val lifecycle = "androidx.lifecycle:lifecycle-extensions:${Versions.archComponents}"

    // Ui
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val materialDesign = "com.google.android.material:material:${Versions.materialDesign}"
    const val groupie = "com.xwray:groupie:${Versions.groupie}"
    const val groupieAndroidExtensions = "com.xwray:groupie-kotlin-android-extensions:${Versions.groupie}"
    const val picasso = "com.squareup.picasso:picasso:${Versions.picasso}"

    //Networking
    const val gson = "com.google.code.gson:gson:${Versions.gson}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitGsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofitGsonConverter}"
    const val retrofitRxSupport = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit}"
    const val okhttp3 = "com.squareup.okhttp3:okhttp:${Versions.okhttp3}"
    const val cache = "com.github.diareuse:mCache:${Versions.cache}"

    // Koin DI
    const val koinAndroid = "org.koin:koin-android:${Versions.koin}"
    const val koinAndroidLifecycle = "org.koin:koin-androidx-scope:${Versions.koin}"
    const val koinAndroidViewModel = "org.koin:koin-androidx-viewmodel:${Versions.koin}"
    const val koinTest = "org.koin:koin-test:${Versions.koin}"

    // Test dependencies
    const val jUnit = "junit:junit:${Versions.jUnit}"
    const val mockito = "com.nhaarman:mockito-kotlin:${Versions.mockito}"
    const val lifecycleTesting = "androidx.arch.core:core-testing:${Versions.archComponents}"
}