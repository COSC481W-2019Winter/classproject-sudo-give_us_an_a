# Architecture Specification

## MapActivity
User can extend the MapActivity class for any user interface screens they want to display a map within. The addition of the MapFragment class allows the developer to exploit map viewing functionality provided via encapsulation. This change gives the developer more control over the details of the Activity class used to display a Google Map.

## Properties
| Name | Type | Description |
| ---- | --- | --- |
| mMap | GoogleMap | The GoogleMap object |

## Functionality
| Name | Parameters | Return | Behavior |
| ---- | --- | --- | --- |
| onCreate() | Bundle | None | Launches activity layout (interactive GUI) as well as places a map in the application using SupportMapFragment. Also contains getMapAsync which sets a callback object which will be triggered when the GoogleMap instance is ready to be used. |
| onMapReady() | GoogleMap | None | Callback interface for when the map is ready to be used. Once an instance of this interface is set on a MapFragment or MapView object, the onMapReady(GoogleMap) method is triggered when the map is ready to be used and provides a non-null instance of GoogleMap |

## Connections
**Inputs**
* 'unsure'
* 
**Outputs**
* 'unsure'
