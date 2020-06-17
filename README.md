News - Byju's Android Assignment
================================


An application to show news headlines and details which uses [NewsAPI](https://newsapi.org/) to fetch the top headlines.


Architecture Design
-------------------

<img src="app_architecture_design.png" width="1000">


Screen Design
-------------

<img src="app_screen_design.png" width="1000">

Libraries and tools
-------------------

This application us libraries and tools used to build Modern Android application, mainly part of Android Jetpack with Kotlin

- [Kotlin](https://kotlinlang.org/) first
- [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html)
- [Architecture components](https://developer.android.com/topic/libraries/architecture/)
- [Dagger 2](https://developer.android.com/training/dependency-injection) for dependency injection 🗡
- [Retrofit](https://square.github.io/retrofit/)
- [Coil](https://github.com/coil-kt/coil)
- Other [Android Jetpack](https://developer.android.com/jetpack) components



Need to Done(Improvements)
--------------------------

- 100% Test cover using Unit(testing single method in viewmodel or repository), Instrumentation(Testing all functionality in a single activity/fragment) and E2E test cases
- Proper error handling and send event to UI
- Show fallback messages like on empty list ("no article available")
