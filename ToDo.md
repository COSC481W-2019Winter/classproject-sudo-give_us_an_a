# To-Do List for Week One
## Eleanor
### ToDo
 * Park here, delete parking spot buttons appearing
 * Having the structured text area for the information to appear once the user has parked.
 * Color of the backdrop and the buttons matching the design specifications (red or green buttons, black text, blue backdrop). 
### Test Plan
#### Begin Screen
* ParkedUp is on screen
* Map appears
* Top banner with app name is blue
* Park Here button is Green with black text
* Backdrop is blue
* On click of Park Here the screen will change to the Parked Screen

#### Parked Screen
* ParkedUp is on screen
* Map appears
* Top banner with app name is blue
* Delete Parking Spot button is Red with black text
* Backdrop is blue
* On click of Delete Parking Spot screen will change to Begin Screen
## Brian
### ToDo
* Initalize `LocationManager` class.
### Test 
#### Unit
* Confirm object is initialized successfully
* Confirm methods return proper types

## Lucas
### ToDo
### Test 

## Drew
### ToDo
 * Ensure orientation change does not impact layout
 * Welcome screen shows current location
### Test 
 * Confirm orientation stays in portrait mode when screen is turned horizontally
 * Confirm that when the app is launched, the current location is shown by default

# To-Do List for Week Two
## Eleanor
### ToDo
 * Exit button, exit popup and delete parking spot popup appearing on screen per reqirements
 * Button functionality working in regards to marking parking location and displaying information in regards to parking and current location.
### Test
I will write unit test for BeginActivity and ParkedActivity to test the functionality of each button and pop up. I believe I will be able to use espresso to test click buttons and select options within the pop ups to confirm what is happening. 
* park here bottom click test
* delete parking spot button test
* delete parking confirm popup test
* delete parking not confirm popup test
* exit from begin screen test
* exit from park screen test
* exit confirm popup test
* exit not confirm popup test

## Brian
### ToDo
* Ensure distance is calculating correctly and returns to MainActivity as the location moves.
* Ensure GPS coordinates are received and stored correctly from Maps API and returns to MainActivity as a variable to display.
### Test
#### Unit
* Current Location is pulled from Google Maps API
* Test distance
  * Cannot be <0
  * Random sample of coordinates should return correct distances
* Test time to car
  * Accurately handles any time input
  * Cannot be <0
* Location is stored when set method is called
* Get methods return correct values


#### Integration/System
* Data displayed updates regularly
  * Distance
  * Time to car
  * Current Location
* Parked location is displayed

## Lucas
### ToDo
### Test

## Drew
### ToDo
 * Marker does not get placed until button is pushed
 * Calculate difference between Marker and current location
### Test
 * Ensure that there is no marker placed by default, and that when button is pressed it is placed in the correct location
 #### Unit
 * Confirm that the correct distance has been returned to the MainActivity from the LocationManager class
