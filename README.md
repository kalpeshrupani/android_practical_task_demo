## What's new for v2.0.0

- Full login signup module added into the structure, customize as per project
- View binding - Kindly refer official link given below
- Don't delete extra.kt file, because it will help you for rename the package name. Just right click
  and do refactor your package name

## Other utility methods

1. showDialog()
2. startActivityCustom()
3. hideKeyboard()
4. getDeviceTimeZone() - Asia/Kotkata
5. getAndroidId()

## integers.xml - Add minimum and maximum length validation for the whole project

## User full links

1. AndroidX versions: https://developer.android.com/jetpack/androidx/versions
2.

ViewBinding: https://proandroiddev.com/migrating-the-deprecated-kotlin-android-extensions-compiler-plugin-to-viewbinding-d234c691dec7

## Special thanks to

[Android-Clean-Architecture] (https://github.com/android10/Android-CleanArchitecture-Kotlin)
[Koin] - (https://insert-koin.io/)
[Coroutine Scope]

- (https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/-coroutine-scope/)
  [Launch]
  -(https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/launch.html)
  [Async-(Deferred)]
- (https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines/async.html)

## Naming conversation style

TextView: tvName EditText: etFirstName or edtFirstName Button: btSave or btnSave CheckBox: cbMale
RadioButton: rbMale ViewPager: viewPagerAbc Recyclerview: recyclerViewAbc

## Method name validation: (Line spacing increase the code readability for other developers so please refer home or splash activity and write code)

1. init()
2. attachObserver()


## Git code commit style:

feat: Login signup page fix: Login issue JIRA#5 (JIRA#IssueNumber - Issue name)
refactor: Login screen code refactor

## Toolbar: Please check parameter in BaseActivity

setupToolbar(toolbar, getString(R.string.app_name), false, Color.BLACK)


