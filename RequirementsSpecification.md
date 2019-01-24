# Requirements Specification

## General
- _Only looking at Android phones since version KitKat and up for 95% usability_
- One page display with vector scaling graphics for minimum 1024 pixel resolution
- Ensure orentation change does not impact layout
- _Google maps should initiate as app starts_
- Notification at top of screen when not in app to show there is a parking location marked
  
## Functionality 
- _Park button to record GPS coordinates_
- _Map to show current location and parking location_
- Map to zoom in and out automatically to fit both locations as GPS location changes
- _Buttons to work_
- _Popup to work_
- _Pressing No on popup closes it and retains location of car_
- _Once location is deleted (pressing yes on popup) it reverts back to the welcome screen_
- _Welcome screen shows current location_

## Technical
- _Store parking coordinates as local variables_
- _Current location is local variable constantly updated_
- _Current location updated every < 1 second_
- Elevation is a local variable

## Design
### Color
- _Park here button is green_
- _Delete button is red_
- _Confirm delete popup red_
### Text
- _Font should be simplistic and easily readable_
- Font size should be large 

### Ratio 
- Map is 70% of the screen on welcome screen
- Map should be 40% on marked location

# Models
## Context Model
![alt text](https://github.com/COSC481W-2019Winter/classproject-sudo-give_us_an_a/blob/Requirements/imgs/Context%20Model.png "Context Model")
## Event-Driven Model
![alt text](https://github.com/COSC481W-2019Winter/classproject-sudo-give_us_an_a/blob/Requirements/imgs/eventdriven_model.png "Event-Driven Model")
