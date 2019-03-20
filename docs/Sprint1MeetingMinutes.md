# Sprint 1 Meeting Minutes
## Brian
### What I did last time
* Finished 2.2 Metric Units
* Started working on 3. Navigation by testing speed
    * Found API level needs to be >=26 for good speed confidence
### Problems
* I had a debugging problem where I'm trying to overlay a debugging window over the app on the phone so that I could test the speed variables in real time. So far all debugging has been exclusive to Android Studio. I will probably use TextView to accomplish this.
### What I will do next
* Finish implementing speed
* Start and finish adding user settings configuation file
* Try to implement navigation
## Drew
### What I did last time
* Researched how to change Marker color
* Researched how to make screen zoom accordingly to show current and parked locations
* Attempted further refactoring
### Problems
* Furthur refactoring proved to be much more difficult than previously expected. Because the Android Google Maps API is required to implement so many methods that all need to be within the same "thread" of the map object's original instantiation, it becomes difficult to abstract required map methods (and team created variables and methods we have assigned to map related tasks) out of the activity without taking everything out, and therefore just duplicating our problem to a brand new activity.
### What I will do next
* Modify markerOptions.icon to HUE_RED
* Use LatLngBounds.Builder to create a bounds variable of Marker & current location
* Use CameraUpdateFactory to zoom the camera to the saved bounds
## Elaenor
### What I did last time
* Researched on how to create the menu
* Went through a toutorial, completed the empty menu an it majory broke the app. 
* I have started going through a differnt way to implement the menu.
### Problems
* It is much more difficult than anticipated to add in a menu option once most of our code already exists, there are not many toutoriaals about adding in a menu with existign activites.
* We need to save the users prefences per phone, is a bit outside my comfort zone (Brian offered to help).
### What I will do next
* Continue to get basic functionality working on the menu.
## Lucas
### What I did last time
### Problems
### What I will do next
