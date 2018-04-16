# General information of the project:

- **Language**: Kotlin
- **IDE**: Android Studio 
- **Version control**: GIT
- **Design Pattern**: MVP (Model - View - Presenter)

The project follows a feature structure, there is a feature folder where all additional features would be placed, in this example the feature provided is `ScoreSummary`
Each feature manages its own views, presenters, and repositories, the access to them are provided through interfaces defined at the `Contract` interface at the feature folder level, 
this way, any new method required by the given feature (in the view or in the presenter) should be added in the contract.

To create the network client communication I chose the library `Retrofit`, the calls to the different endpoints would be through the service interface so any additional feature
that requires a new endpoint will be added here.

The management of the response is done with `RxJava`, using `Observables` in the the repository and `Subscription` in the `Presenter`.
This subscription is cleared when the view is destroyed in order to avoid memory leaks.

I decided to keep models simple setting only the variables used, any additional change in the view that requires new data should require to expand the models.

I declared a `GsonFactoryClass` to initialize the `GSON` builder in order to save/retrieve the data in orientation change.
I declared a `TextFormatService` to format the main info text depending on the data received.
In order to format the text with different colors and sizes I used a external class (`Truss`) provided by https://gist.github.com/JakeWharton/11274467 (I imported that class as a Java class)

The custom view is defined in the common view folder, this is a fully customizable view by the developer where he can set the following parameters:

- **innerCircleMargin** - Margin of the inner Circle with the outer Circle
- **outerCircleMargin** - Margin of the outer Circle with the margins of the screen
- **strokeInnerCircle** - Stroke dimension of the inner circle
- **strokeOuterCircle** - Stroke dimension of the outer circle
- **innerArcStartingAngle** - Position of the circle where to start to draw the colored circle
- **outerCircleColor** - Color of the outer circle
- **innerCircleColor_0_90** - Color for values between 0% and 25%
- **innerCircleColor_90_180** - Color for values between 25% and 50%
- **innerCircleColor_180_270** - Color for values between 50% and 75%
- **innerCircleColor_270_360** - Color for values between 75% and 100%

I provided a gamma of 5 colors in the colors.xml resource that are used through the project.
I provided internationalization for Spanish language.
I provided a `UnitTest` class of the presenter implemented.
I provided a `UITest` class of the view developed.

The gradle file has 2 build types, one for debug an other for production, both of them refer to the same API URL (this should be improved including signing options
for the production version)

# Scrum 
In order to manage this project I followed the principles of _Scrum Methodology_ identifying the following tasks and estimating them as follows:

-[x] **TaskA**: Setup the project -> Story Points: **3**
-[x] **TaskB**: Establish the MVP architecture & Network Calls -> Story Points: **5**
-[x] **TaskC**: Design the DonutView -> Story Points: **8**
-[x] **TaskD**: Integrate API response with Donut View -> Story Points: **3**
-[x] **TaskE**: Internationalization -> Story Points: **1**
-[x] **TaskF**: UnitTest of the presenter -> Story Points: **2**
-[x] **TaskG**: UITest of the view -> Story Points: **3**

Other tasks identified not implemented:

-[ ] **TaskH**: Animation of the inner circle
-[ ] **TaskI**: Define custom styles for the app
-[ ] **TaskJ**: Implement `Dagger` to inject the presenter
-[ ] **TaskK**: Implement persistence for not network