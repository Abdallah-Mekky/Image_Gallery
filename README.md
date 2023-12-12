# Image_Gallery_App

Please ⭐️ this repo and share it with others.

# Install
* [Apk](https://drive.google.com/file/d/1nRtT7S_gF1PYQUXPTkqi6DGL-VtrxoVe/view?usp=sharing)

# Description
The Challenge
1. Create a project that implements an image API from here:
https://github.com/toddmotto/public-apis

   a. Examples: https://github.com/Giphy/GiphyAPI, https://docs.opendota.com, or
    https://deckofcardsapi.com/
   
2. The project must include
   
   a. A list of images.
   
   b. User can edit an image by applying filters (preset) on a picture (any random
      filter, e.g.: grayscale, Portrait, Yellowish, etc.).
   
   c. Write tests where possible.

3. The project must adhere to the `MVVM` design architecture and include a `dagger hilt`.

4. Bonus Points:

   d. Detail view of each item.
   
   e. Detail view should consist of a minimum of: an image, a title, and a description.
   
   f. List pagination if greater than 50 items or lazy-load.
   
   g. Additional view.


# Screenshots

<div>
  <img src="https://github.com/Abdallah-Mekky/Prayer_App/assets/80880411/cd7c2cc8-dcfc-4064-94b2-03657d691ba8"  width="250">
  <img src="https://github.com/Abdallah-Mekky/Prayer_App/assets/80880411/bd58819a-b658-440b-8f21-9756e99238a0"  width="250">
  <img src="https://github.com/Abdallah-Mekky/Prayer_App/assets/80880411/add01099-f83e-43d5-afed-1aa21fb8964a"  width="250">
  <img src="https://github.com/Abdallah-Mekky/Prayer_App/assets/80880411/17953a5c-63c1-4dec-96be-453438aa81b0"  width="250">
  <img src="https://github.com/Abdallah-Mekky/Prayer_App/assets/80880411/7dc44b84-b713-4b66-90bc-18c06bb3d837"  width="250">
  <img src="https://github.com/Abdallah-Mekky/Prayer_App/assets/80880411/b9554ac0-1045-421f-af9a-7fa3682f8b39"  width="250">
  <img src="https://github.com/Abdallah-Mekky/Prayer_App/assets/80880411/f0daba5e-51bc-4261-ac30-a42b413a4c49"  width="250">
  <img src="https://github.com/Abdallah-Mekky/Prayer_App/assets/80880411/ec280ca6-a365-4816-a842-97afecd7f3fb"  width="250">

</div>

# Video 

https://github.com/Abdallah-Mekky/Prayer_App/assets/80880411/8c86e2e5-49da-4b2c-8a44-cde6e10f2d4f




# Software stack
* [Kotlin](https://kotlinlang.org/)
* [Android JetPack](https://developer.android.com/jetpack) 
* [View Model](https://bit.ly/3e43P79) - The View Model class is designed to store and manage UI-related data in a lifecycle-conscious way.
* [Live Data](https://bit.ly/3KuahQR) - LiveData is an observable data holder class. Unlike a regular observable, LiveData is lifecycle-aware, meaning it respects the lifecycle of other app components, such as activities, fragments, or services.
* [View Binding][https://developer.android.com/topic/libraries/view-binding) - View binding is a feature that makes it easier to write code that interacts with views.
* [Dependency Injection Using Hilt](https://developer.android.com/training/dependency-injection/hilt-android) - Hilt is a dependency injection library for Android that reduces the boilerplate of doing manual dependency injection in your project.
* [Repository Pattern](https://medium.com/swlh/repository-pattern-in-android-c31d0268118c)   
* [Google gson](https://github.com/google/gson) - For parsing JSON data.
* [Pagination]([https://developer.android.com/topic/libraries/architecture/paging]) - The Paging Library helps you load and display small chunks of data at a time. 
* [Circular Image](https://github.com/hdodenhof/CircleImageView) - A fast circular ImageView perfect for profile images.
* [Navigation Component](https://developer.android.com/guide/navigation) - Navigation refers to the interactions that let users navigate across, into, and back out from the different pieces of content within your app.
* [Glide](https://github.com/bumptech/glide) - Glide is a fast and efficient open-source media management and image-loading framework for Android.
* [Sdp](https://github.com/intuit/sdp) - An android lib that provides a new size unit - sdp (scalable dp), It can help Android developers with supporting multiple screens.
* [Ssp](https://github.com/intuit/ssp) - An android lib that provides a new size unit - ssp (scalable sp), It can help Android developers with supporting multiple screens.

# Network Calls
* [Retrofit 2](https://square.github.io/retrofit/) - A type-safe HTTP client for Android and Java.

# Background tasks
* [Kotlin Coroutines](https://bit.ly/3Kq3ec3) - A coroutine is a concurrency design pattern that you can use on Android to simplify code that executes asynchronously.


# Code Architecture
* [MVVM Architecture Pattern](https://www.toptal.com/android/android-apps-mvvm-with-clean-architecture)

<p align="center">

<img src="https://user-images.githubusercontent.com/86564639/166422026-4a5f4f9b-44b6-44c7-b4c6-852be532b41f.png">
</p>

* [Clean Architecture](https://developer.android.com/topic/architecture)

<p align="center">

![http://fernandocejas.com/2015/07/18/architecting-android-the-evolution/](https://github.com/android10/Sample-Data/blob/master/Android-CleanArchitecture/clean_architecture.png)
</p>

# API
* [Pexles](https://www.pexels.com/api/)

# End Points 
* [curated](https://www.pexels.com/api/documentation/#photos-curated) - To get random photos.
* [search](https://www.pexels.com/api/documentation/#photos-search) - To search by category.
