# Design Specification

## The Problem
In today's fast-paced world, people are traveling to new and unfamilar locations parking their car wherever they can find a spot. While parking in a parking garage, on a main throughfare, or some side street, it is easy to forget where one's car is parked. Having to search for one's car is stressful and a waste of time.  

## The Solution
Our Android based app ParkedUp will solve this issue. While some new Apple phones have a parking tracker built into the map, Android phones do not have this feature. We intend on creating a simple to use app that will mark your parking location and be able to inform you where you are compared to your car.

### Parking button
The initial parking button labeled “Park Here” in a green color in the mid-bottom of the screen will allow the user to simply click the button and that is all that will be needed to mark their current location. This is the location that will be stored as the parking location so that the user can be guided back later in time when they are searching for their car.
### Map Interaction
It is very important for the user to visualize where they are in proximity to their car. We would like for the current location to be marked in addition to the parking location. This will allow the user to navigate to their car without using the exact coordinates. Even if the user wishes to use the coordinates as their primary way to walk back to their vehicle until they arrive closer then they would easily be able to see that perhaps their car is to their left, not their right, which would be difficult to distinguish based on coordinate locations.
### Unparking button
The button that will appear under the information on where the parking location and the user's current location will read “Delete Parking Spot” which the user can do at any time if they wish to delete where they had previously marked their car. The color red is universal for stop, so the user will stop and read what the button says before pressing it.
### Confirm button
The confirmation button is the last interactive part done by the user. If they have selected the “Delete Parking Spot” button then the screen will the same and the user will have to select the red button again to confirm that they do indeed want to remove the parking spot. This will prevent accidental deletion and make the user confirm that they want to remove the spot; it warns that once deleted then the location cannot be retrieved. 

### Welcome Screen
The welcome screen has a map and a "Park Here" button.  This simple and straight forward welcome screen will make it clear to anyone using the app, no matter how technologically savvy they are, where to click in order to mark their parking location. By displaying the map they can see where they are and only allowing them to press parking will limit the amount of user error. Green is universal for “go” so it will have the user automatically thinking this is the button to start tracking without even reading what is written on the button.
![alt text](https://github.com/COSC481W-2019Winter/classproject-sudo-give_us_an_a/blob/DesignSpecs/imgs/welcome_screen.png "Welcome Screen")

### Current Location
Shows parking location (coordinates and elevation). Shows where you currently are and how far you are away.  While the map will show the parking and current loaction, below will have the information showing the GPS coordinates or the parking and current locations. Hopefully, we can implement a feature whichc will allow to the app to track the elevation of both parking and current locations. And additionally we would like to implement the distance and time it will take for the user to walk back to their car. The only interation that the user can have will be moving with in the map and selectingn the delete parking button to remove the stored parking location.

![alt text](https://github.com/COSC481W-2019Winter/classproject-sudo-give_us_an_a/blob/DesignSpecs/imgs/current_location.png "Current Location")

### Confirm Deletion
Confirms to delete your current location or when you've found your car.  This last screen in the cycle will allow the user to  confirm that they wish to delete their parking location and will allow them to still view the information on the pakring spot, below will be an option to cancel, which will take the user back to the current location screen. If the user deletes the vehicle then the welcome screen will reapper allowing the user to mark a new parking location.
![alt text](https://github.com/COSC481W-2019Winter/classproject-sudo-give_us_an_a/blob/DesignSpecs/imgs/delete_confirmation.png "Delete Confirmation")

#### _Mandatory Aspects_
* _Map interaction_

* _Button interaction_

* _GPS location for parking and current position_


#### Desirable Aspects
 * Elevation

 * Time to walk back to car
