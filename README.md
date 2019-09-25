Runners application gets the data from API ("http://849fairmount.com/mobile/runners.json") and shows the images in a recycler view.
The list of runners displayed is sorted by finish time with a column showing their finish position (ranking) within their age group.
Added refresh icon as well to refresh the list in case thr fails to load when the app is launched.
MVP architecture pattern is used to build the application

Third Party Libraries:

RXJava 2
Dagger 2.0
Retrofit lib
ButterKnife
Realm for database