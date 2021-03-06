# 1 Map to fit both locations
Sprint 1
## Effort
Small
## Acceptance Criteria
* Change Marker (parked location) to red
* Have both locations on screen visable
## Who is doing it
Drew
## User Story
To allow the user to visually see where they are in relation to their car. This will prevent them from getting lost.
## Tasks
* Modify markerOptions.icon to HUE_RED
* Use LatLngBounds.Builder to create a bounds variable of Marker & current location
* Use CameraUpdateFactory to zoom the camera to the saved bounds

# 2 Setting Menu
Sprint 1
## Effort
Medium
## Acceptance Criteria
* Notification Toggle
* Mi/Km toggle
* About page

## Who is doing it
Eleanor
## User Story
The user can change preferences and find out more about the app.
## Tasks
* Create empty menu
* Add KI/KM toggle button
* Add Notification toggle button
* Add about toggle button
* Add in functionality
* Add saving user preferences
## 2.1 Notification
Sprint 1
### Effort
Small
### Acceptance Criteria
* static bar
* possibly distance and time back to the car
* allow for notifications to be turned off
### Who is doing it
Lucas
### User Story
To remind the user they have a parked location and give them information on where their car is.
## Tasks
* Verify needed library is included in dependencies
* Use NotificationCompat.Builder to create notification text and title
* Set the notification's tap action to open app
* Create parameters that cause a notification to appear
## 2.2 Metric Units
Sprint 1
### Effort
Small
### Acceptance Criteria
* variable swap
### Who is doing it
Brian 
### User Story
For international users to select their perferred unit of measure (km/m or mi/feet).
## Tasks
* Create units variable
* Create toggle method
* Modify distanceToCar for metric
* Modify getDistance for metric
# 3 Walking Navigation
Sprint 1
## Effort
Medium
## Acceptance Criteria
* on path 
* live walking speed
## Who is doing it
Brian
## User Story
In order for the user to have an accurate time/distance to get to their car and the way to get there.
## Tasks
* Research Walk Navigation API methods and implement
* Ensure Walk path is automatically updating upon movement
* Calculate speed based on user movement
* Handle scenario where user is stationary
# 4. Elevation
## Effort
Large
## Acceptance Criteria
* RESEARCH
## Who is doing it

## User Story
## Tasks

# 5 Text formatting for Higher Pixel
## Effort
Small
## Acceptance Criteria
* testing
* possibly already fixed
## Who is doing it

## User Story
## Tasks

# 6 Font Change
## Effort
Small
## Acceptance Criteria
* change to nicer font
* change to different size
## Who is doing it

## User Story
## Tasks

# 7 Overall UI style
## Effort
Large
## Acceptance Criteria
* Buttons
* Background
* Icon 
* Map view
* Are you walking back notification?
* Detailed (small font)/Simple (large font) toggle for more and less display options. Ease of Use for the visually impared.
## Who is doing it

## User Story
## Tasks
