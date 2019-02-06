# Architecture Specification

## MainActivity
Initializes android application. Handles all calls to GoogleMaps APIs as well as passing that information to local storage within `LocationManager`.

### Properties
| Name | Type | Description |
| ---- | --- | --- |
| mMap | GoogleMap | The GoogleMap object |

### Functionality
| Name | Parameters | Return | Behavior |
| ---- | --- | --- | --- |
| onCreate() | Bundle | None | Launches activity layout (interactive GUI) as well as places a map in the application using SupportMapFragment. Also contains `getMapAsync` which sets a callback object which will be triggered when the GoogleMap instance is ready to be used. |
| onMapReady() | GoogleMap | None | Callback interface for when the map is ready to be used. Once an instance of this interface is set on a MapFragment or MapView object, the `onMapReady(GoogleMap)` method is triggered when the map is ready to be used and provides a non-null instance of GoogleMap |
| parkButton() | None | None | Registers a clicked button with `View` to signify the user wanting to save the current location as a parking spot.
|exitButton() | None | None | Registers clicked button within `View`, terminates app and kills it's external processes.

## Connections
**Inputs**
* `LocationManager` - Gets parking coordinates and elevation as well as users current coordinates and elevation.
* `View` - Calls ~OnClick~ Listeners for associated buttons within the user interface.

**Outputs**
* 'View' - Displays layout and GoogleMap along with user interface. 

## LocationManager
Holds user information for location data. Contains latitude and longitude coordinates and elevation data for both the parked vehicle and the user's current location. 

## Properties
| Name | Type | Description |
| ---- | --- | --- |
| coordinates | float[] | Google Maps latitude and longitude data values passed in from MainActivity | 
| elevation | float | Elevation of user's current position provided by MainActivity.
| parkingCoord | float[] | Coordinates of parking spot saved from MainActivity |
| parkingElev | float | Elevation of user's parked vehicle saved from MainActivity. |

## Functionality
| Name | Parameters | Return | Description |
| ---- | ---- | ---- | ---- |
| getCoordinates() | None | float[] |Returns a float array containing the coordinate values stored in an array |
| getElevation() | None | float | Returns elevation value as a floating point number. |
| setParkCoord() | nMap | None | Input of coordinates from GoogleMaps nMap type and stores them as a float[].
| setParkElev() | nMap | None | Input of elevation contained with an nMap type converted and then stored as a float.

## Connections
**Inputs**
* `MainActivity` - GPS location data is pulled from nMap when called. Also calls functions when triggered by Viewer to do so.

**Outputs**
* `MainActivity` - Provides stored values for parking location coordinates and elevation as well as current position values.

## View 
## Properties
## Functionality
## Connections
**Inputs**
**Outputs**
