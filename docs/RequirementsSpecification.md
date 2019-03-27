# Requirements Specification

## General
- _Only looking at Android phones since version KitKat and up for 95% usability_
- One page display with vector scaling graphics for minimum 720x1280 pixel resolution
- Ensure orientation change does not impact layout
- _Google map services should initiate as app starts_
- Notification at top of screen when not in app to show there is a parking location marked
  
## Functionality 
- _Park button to record GPS coordinates_
- _Map to show current location and parking location_
- Map to zoom in and out automatically to fit both locations as GPS location changes
- _Park here, Delete location, exit buttons to work_
- _Both the location deletion and Exit popups appear when their corresponding buttons are pressed_
- _Pressing No either on popup closes it and returns to current app state_
- _Once confirming location is deletion (pressing yes on popup) it reverts back to the welcome screen_
- _Welcome screen shows current location_
- _Exit button allows user to close the app_

## Technical
- _Store parking coordinates as local variables_
- _Current location is local variable constantly updated_
- _Current location updated every <1 second_
- Elevation is a local variable

## Design
### Color
- _Park here button is green_
- _Delete button is red_
- _Exit button is black X icon_
- _Confirm delete popup red_
### Text
- _Font should be simplistic and easily readable_
- Font size should be large 

### Ratio 
- Map is 70% of the screen on welcome screen
- Map should be 40% on marked location

# Models
## Context Model
![Context Model](https://github.com/COSC481W-2019Winter/classproject-sudo-give_us_an_a/blob/master/imgs/Context%20Model.png "Context Model")
## Event-Driven Model
![Event-Driven Model](https://github.com/COSC481W-2019Winter/classproject-sudo-give_us_an_a/blob/master/imgs/eventdriven_model.png "Event-Driven Model")
