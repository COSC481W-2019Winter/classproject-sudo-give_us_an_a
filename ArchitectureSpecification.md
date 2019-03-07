# Architecture Specification

## BeginActivity
Initializes android application. Handles all calls to GoogleMaps APIs as well as passing that information to local storage within `LocationManager`. Also takes information stored within `LocationManager` and displays it using Android's activity `Layouts`. Has the set up for inital screen.

### Properties
| Name | Type | Description |
| ---- | --- | --- |
| mMap | GoogleMap | The GoogleMap object |
| location | LocationManager | LocationManager Instance for storing map data and then displaying it.|
| mapFrag | SupportMapFragment | A fragment map component for placing a map in the app. |
| googleApiClient | GoogleApiClient | The main entry point for Google Play services integration. |
| locationRequest | LocationRequest |  Data object that contains quality of service parameters for requests |

### Functionality
| Name | Parameters | Return | Behavior |
| ---- | --- | --- | --- |
| onCreate() | Bundle | None | Launches `View` activity layout (interactive GUI) as well as places a map in the application using SupportMapFragment. Also contains `getMapAsync` which sets a callback object which will be triggered when the GoogleMap instance is ready to be used. Also contains functionality for button clicks of parkButton and exit button|
| onMapReady() | GoogleMap | None | Callback interface for when the map is ready to be used. Once an instance of this interface is set on a MapFragment or MapView object, the `onMapReady(GoogleMap)` method is triggered when the map is ready to be used and provides a non-null instance of GoogleMap |
| onRequestPermissionResult | Bundle | None | Handels permission requests in regard to access granted verses denied. |
| onLocationChange | Location | None | Moves google maps to the users current location. Also uses CameraUpdate to center camera and zoom in|
| onConnected | Bundle | None | Sets the rate for which the app requests current location to once per second. |

## ParkedActivity
Initializes android application. Handles all calls to GoogleMaps APIs as well as passing that information to local storage within `LocationManager`. Also takes information stored within `LocationManager` and displays it using Android's activity `Layouts`. Has the code for the screen while the user has a parked location.

### Properties
| Name | Type | Description |
| ---- | --- | --- |
| mMap | GoogleMap | The GoogleMap object |
| location | LocationManager | LocationManager Instance for storing map data and then displaying it.|
| mapFrag | SupportMapFragment | A fragment map component for placing a map in the app. |
| googleApiClient | GoogleApiClient | The main entry point for Google Play services integration. |
| locationRequest | LocationRequest |  Data object that contains quality of service parameters for requests |
| currentUserLocationMarker | Marker | The map marker object |

### Functionality
| Name | Parameters | Return | Behavior |
| ---- | --- | --- | --- |
| onCreate() | Bundle | None | Launches `View` activity layout (interactive GUI) as well as places a map in the application using SupportMapFragment. Also contains `getMapAsync` which sets a callback object which will be triggered when the GoogleMap instance is ready to be used. Also contains functionality for button clicks of unparkButton and exit button, then also handels the popups which appear when a button is sclicked on this screen. This method will also handle displaying the locations information on the screen in the form of GPS position|
| onMapReady() | GoogleMap | None | Callback interface for when the map is ready to be used. Once an instance of this interface is set on a MapFragment or MapView object, the `onMapReady(GoogleMap)` method is triggered when the map is ready to be used and provides a non-null instance of GoogleMap |
| onRequestPermissionResult | Bundle | None | Handels permission requests in regard to access granted verses denied. |
| onLocationChange | Location | None | Moves google maps to the users current location. Updates `LocationManager` current location coordinates.|
| onConnected | Bundle | None | Sets the rate for which the app requests current location to once per second. Also places initial marker for parked location and saves parked coordinates in `LocationManager`.|

## Connections
**Inputs**
* `LocationManager` - Gets parking coordinates and elevation as well as users current coordinates and elevation. Also calls the time to the travel in minutes for the current parking position.

**Outputs**
* `LocationManager` - Map data passed as Location type to later be converted by `LocationManager`.


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
| getCoordinates() | None | double[] |Returns a double array containing the coordinate values stored in an array |
| getElevation() | None | double | Returns elevation value as a double. |
| setParkCoord() | Location | None | Input of coordinates from GoogleMaps mMap type and stores them as a float[]. |
| setParkElev() | Location | None | Input of elevation contained with an mMap type converted and then stored as a float. |
| distanceToCar() | None | None | Finds the distance from the user to their car using the coordinates both the saved parking spot and the user's position. |
| timeToCar() | None | String | Computes the time to travel to the parked car using the parking position coordinates/elevation and the current position coordinates/elevation. |

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
