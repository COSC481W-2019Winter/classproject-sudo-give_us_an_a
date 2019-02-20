# Communication Guidelines
 
Our team will use our slack channel (SudoA.slack) to check in with one another, ask questions and share advice.

Our current setup for our slack has #general, #project, #api\_key and #sha-1\_certificate channels. With each channel having a specific purpose it allows us to separate and keep organized. We have utilized the pinning feature to mark information that is very important.

We also use Github with GitExtentions for git to push our work which keeps the team updated on project progress. Commits, issues, pull requests, etc. are automatically posted to the #project channel by the Github Integration bot in Slack.

# Our Goals

## Week One Goals

### XML Layout (View)
 * Park here, delete parking spot buttons appearing
 * Having the structured text area for the information to appear once the user has parked.
 * Color of the backdrop and the buttons matching the design specifications (red or green buttons, black text, blue backdrop).

### MainActivity (Presenter)
 * Ensure orientation change does not impact layout
 * Welcome screen shows current location

### LocationManager (Model)

* Initialize all variables to prepare for all the calculations 

### Interface Setup
 * Create class structure setup for contract between `LocationManager` and `MainActivity` with documentation.
 
## Week Two Goals

### XML Layout (View)
 * Exit button, exit popup and delete parking spot popup appearing
 * Button functionality working in regards to marking parking location and displaying information in regards to parking and current location.

### MainActivity (Presenter)
 * Marker does not get placed until button is pushed
 * Calculate difference between Marker and current location

### LocationManager (Model)

* Enure distance is calculating correctly and returns to `MainActivity` as the location moves.
* Ensure GPS coordinates are receieved and stored correctly from Maps API and returns to `MainActivity` as a variable to display.

### Interface Setup
 * Make additional interfaces for pushing needed information to any XML Layout.
  
 


# Roles
 
Brian - `LocationManager` (Model)

Lucas - Setting up interface and connections between `LocationManager` and `MainActivity`

Drew - `MainActivity` (Presenter)

Eleanor - XML file (View) with formatting and design
