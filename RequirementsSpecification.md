# Requirements Specification

## General
- Only looking at andriod phones since version KitKat and up for 95% usability
- One page display with vector scaling graphics for minimum 1024 pixel resolution
- Ensure orentation change does not impact layout
- Google maps should initiate as app starts
- Notification at top of screen when not in app to show there is a parking location marked
  
## Functionality 
- Park button to record GPS coordinates
- Map to show current location and parking location
- Map to zoom in and out automatically to fit both locations as GPS location changes
- Buttons to work
- Popup to work
- Pressing No on popup closes it and retains location of car
- Once location is deleted (pressing yes on popup) it reverts back to the welcome screen
- Welcome screen shows current location

## Technical
- Store parking coordantes as local variables
- Current location is local variable constantly updated
- Current location updated every < 1 second 
- Elevation is a local varible

## Design
### Color
- Park here button is green
- Delete button is red
- Confirm delete popup red
### Text
- Font should be simplistic and easily readable
- Font size should be large 

### Ratio 
- Map is 70% of the screen on welcome screen
- Map should be 40% on marked location

# Models
## Context Model
![alt text](https://github.com/COSC481W-2019Winter/classproject-sudo-give_us_an_a/blob/Requirements/imgs/Context%20Model.png "Context Model")
## Event-Driven Model
![alt text](https://github.com/COSC481W-2019Winter/classproject-sudo-give_us_an_a/blob/Requirements/imgs/eventdriven_model.png "Event-Driven Model")