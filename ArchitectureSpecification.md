# Architecture Specification

## BeginActivity
Initializes android application. Handles all calls to GoogleMaps APIs as well as passing that information to local storage within `LocationManager`. Also takes information stored within `LocationManager` and displays it using Android's activity `Layouts`. Has the set up for inital screen.

### Properties
| Name | Type | Description |
| ---- | --- | --- |
| mMap | GoogleMap | The GoogleMap object |
| location | LocationManager | LocationManager Instance for storing map data and then displaying it.| 

### Functionality
| Name | Parameters | Return | Behavior |
| ---- | --- | --- | --- |
| onCreate() | Bundle | None | Launches `View` activity layout (interactive GUI) as well as places a map in the application using SupportMapFragment. Also contains `getMapAsync` which sets a callback object which will be triggered when the GoogleMap instance is ready to be used. |
| onMapReady() | GoogleMap | None | Callback interface for when the map is ready to be used. Once an instance of this interface is set on a MapFragment or MapView object, the `onMapReady(GoogleMap)` method is triggered when the map is ready to be used and provides a non-null instance of GoogleMap |
| parkBtn() | None | None | Creates and registers a clicked button within the android activity layout to signify the user wanting to save the current location as a parking spot. |
| unParkBtn() | None | None | Creates and registers a clicked button within the android activity layout to allow the user to remove the saved parking space coordinates. `LocationManager` is then told to delete saved coordinates and elevation for the parking space. |
| exitButton() | None | None | Creates and registers a clicked button within "View", terminates application and kills it's external processes.
| confirmDeleteBtn() | None | boolean | Creates "pop-up" within activity layout to allow the user to confirm deletion of the parking position. Returns true if the user selects "yes" and false if the user selects "no". |
| displayCoord() | float[] | None | Displays coordinate values within current activity layout. |
| displayElev() | float | None | Displays elevation values within current activity layout. |
| displayDist() | float | None | Displays the total distance between the user's current position and the car's parking spot within the activity layout. | 
| displayTime() | int | None | Displays the time to travel to the vehicle in minutes within the application activity layout. |

## Connections
**Inputs**
* `LocationManager` - Gets parking coordinates and elevation as well as users current coordinates and elevation. Also calls the time to the travel in minutes for the current parking position.

**Outputs**
* `LocationManager` - Map data passed as mMap type to later be converted by `LocationManager`.


## ParkedActivity
Initializes android application. Handles all calls to GoogleMaps APIs as well as passing that information to local storage within `LocationManager`. Also takes information stored within `LocationManager` and displays it using Android's activity `Layouts`. Has the code for the screen while the user has a parked location.

### Properties
| Name | Type | Description |
| ---- | --- | --- |
| mMap | GoogleMap | The GoogleMap object |
| location | LocationManager | LocationManager Instance for storing map data and then displaying it.| 

### Functionality
| Name | Parameters | Return | Behavior |
| ---- | --- | --- | --- |
| onCreate() | Bundle | None | Launches `View` activity layout (interactive GUI) as well as places a map in the application using SupportMapFragment. Also contains `getMapAsync` which sets a callback object which will be triggered when the GoogleMap instance is ready to be used. |
| onMapReady() | GoogleMap | None | Callback interface for when the map is ready to be used. Once an instance of this interface is set on a MapFragment or MapView object, the `onMapReady(GoogleMap)` method is triggered when the map is ready to be used and provides a non-null instance of GoogleMap |
| parkBtn() | None | None | Creates and registers a clicked button within the android activity layout to signify the user wanting to save the current location as a parking spot. |
| unParkBtn() | None | None | Creates and registers a clicked button within the android activity layout to allow the user to remove the saved parking space coordinates. `LocationManager` is then told to delete saved coordinates and elevation for the parking space. |
| exitButton() | None | None | Creates and registers a clicked button within "View", terminates application and kills it's external processes.
| confirmDeleteBtn() | None | boolean | Creates "pop-up" within activity layout to allow the user to confirm deletion of the parking position. Returns true if the user selects "yes" and false if the user selects "no". |
| displayCoord() | float[] | None | Displays coordinate values within current activity layout. |
| displayElev() | float | None | Displays elevation values within current activity layout. |
| displayDist() | float | None | Displays the total distance between the user's current position and the car's parking spot within the activity layout. | 
| displayTime() | int | None | Displays the time to travel to the vehicle in minutes within the application activity layout. |

## Connections
**Inputs**
* `LocationManager` - Gets parking coordinates and elevation as well as users current coordinates and elevation. Also calls the time to the travel in minutes for the current parking position.

**Outputs**
* `LocationManager` - Map data passed as mMap type to later be converted by `LocationManager`.


## LocationManager
Holds user information for location data. Contains latitude and longitude coordinates and elevation data for both the parked vehicle and the user's current location. 

### Properties
| Name | Type | Description |
| ---- | --- | --- |
| coordinates | float[] | Google Maps latitude and longitude data values passed in from MainActivity | 
| elevation | float | Elevation of user's current position provided by MainActivity.
| parkingCoord | float[] | Coordinates of parking spot saved from MainActivity |
| parkingElev | float | Elevation of user's parked vehicle saved from MainActivity. |

### Functionality
| Name | Parameters | Return | Description |
| ---- | ---- | ---- | ---- |
| getCoordinates() | None | float[] |Returns a float array containing the coordinate values stored in an array |
| getElevation() | None | float | Returns elevation value as a floating point number. |
| setParkCoord() | mMap | None | Input of coordinates from GoogleMaps mMap type and stores them as a float[]. |
| setParkElev() | mMap | None | Input of elevation contained with an mMap type converted and then stored as a float. |
| distanceToCar() | float[] | float | Finds the distance from the user to their car using the coordinates and elevation of both the saved parking spot and the user's position. |
| timeToCar() | float | String | Computes the time to travel to the parked car using the parking position coordinates/elevation and the current position coordinates/elevation. |

### Connections
**Inputs**
* `MainActivity` - GPS location data is pulled from mMap when called. Also calls functions when triggered by layout to do so.

**Outputs**
* `MainActivity` - Passes stored values for saved parking location coordinates and elevation as well as the current position values.


# Tasks Split up

Brian - `LocationManager` 

Lucas - Setting up interface and connections between `LocationManager` and `MainActivity`

Drew - `MainActivity` 

Eleanor - XML file (View) with formatting and design
