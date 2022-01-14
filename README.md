## What's new for v2.0.0

- Full login signup module added into the structure, customize as per project
- View binding - Kindly refer official link given below
- Don't delete extra.kt file, because it will help you for rename the package name. Just right click
  and do refactor your package name

## New screens

1. Login (email, password)
2. Signup (profile image - crop feature, full name, email, mobile, password, confirm password)
3. Forgot Password (email)
4. Reset password (password, confirm password)
5. Change password (old password, new password, confirm new password)
6. CMS (TOC, Privacy policy)
7. BaseRecyclerViewAdapter (All utility methods for the list, view binding)

## Validations

1. isValidEmail(text)
2. isValidPhone(text)
3. isValidPassword(text)
4. isPasswordAndConfirmPasswordMatch(password, confirmPassword)

## Datetime - DateTimeHelper class

1. String.getFormattedDatetime(inputFormat: String, outputFormat: String) - use if you have string
   datetime
2. String.getFormattedDatetime(outputFormat : String) - use if you have long datetime
3. getTimeAgo(long) - Specially for chat

## Other utility methods

1. toastSuccess(msg) / toastError(msg)
2. PrefKeys.getAuthKey() - get user auth key
3. showDialog()
4. startActivityCustom()
5. hideKeyboard()
6. getDeviceTimeZone() - Asia/Kotkata
7. getAndroidId()
8. setHtmlString(text)
9. showImagePicker() - Bottom image picker
10. callFromDialer() - Now phone call permission is not required.

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
3. saveData()
4. performLogin()
5. isValid() return true or false in each activity for validation
6. getUserList(), getCurrentOrderList()
7. setupUiData() for detail page
8. setupAdapter() for set recyclerview adapter

## Class name validation:

1. StudentListAdapter
2. OrderPagerAdapter > CurrentOrderListAdapter, PastOrderListAdapter

## Git code commit style:

feat: Login signup page fix: Login issue JIRA#5 (JIRA#IssueNumber - Issue name)
refactor: Login screen code refactor

## Toolbar: Please check parameter in BaseActivity

setupToolbar(toolbar, getString(R.string.app_name), false, Color.BLACK)


