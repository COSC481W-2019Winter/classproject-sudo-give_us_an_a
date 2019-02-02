----
## Installing and Configuring Android Studio (subject to change)

**Installation**

* go here to download Android Studio (Community Edition): [link](https://www.google.com/aclk?sa=l&ai=DChcSEwi2j4nh9ozgAhUKpmkKHfvHAiwYABABGgJpcQ&sig=AOD64_2um9lDNXWWCtyXn8cTdQnVuzk2NA&adurl=&q=&ved=2ahUKEwjk-YPh9ozgAhXn64MKHQXeBkYQqyQoAHoECAQQBA)
* install

**Configuring Android Studio and Your Android Device**
* find out your devices' Android API Level -> Apps>Settings>About Device || About Phone>Android Version
* in Android Studio download your devices' SDK -> Tools>SDK Manager> now check the version that your phone has -> Apply>OK>I Agree
* now download SDK Tools -> Tools>SDK Manager>SDK Tools (located in top 1/3 of window)
  now check the following to download:
  
  -Google Play services
  
  -Google USB Driver
  
  -(Optional)Intel x86 Emulator Accelerator (HAXM installer) <--only if you wish to use an emulator instead of a device
  
* enable developer mode on your device open -> Settings>System>About phone> now tap Build number 7 times and return to previous screen
  to see Devoloper options
* tap Developer options and enable USB debugging
* plug your device into computer via USB cable
* a notification should pop up on device saying something like "How would you like to connect this device?"
  with this you may have to experiment with either connecting as MTP or PTP (I find PTP to work more often) or other options
* to test that everything works go back to Android Studio and click the green Play button in the top menu (this will take a little while)
* once everything is done it should automatically install and launch an android app which says "hello world" in the middle
  
**Troubleshooting Android Problems**
* (A)
* in newer android devices after pushing play the layout may pop up and drop out or be completely grey on your device. if this occurs goto -> app/manifests/AndroidManifest.xml and add the bottom line of this code. then try launching again

        <application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/AppTheme">
        <!--make sure to add this so it works on newer devices-->
        <uses-library android:name="org.apache.http.legacy" android:required="false"/>
        
* (B)
* if after this, the app still doesn't launch on your device or you are still getting a "grey screen" do the following:
1. goto our groups' slack account and look for the new API key under the channel api_key [here](https://sudoa.slack.com/messages/CFVVAEBS5/) and copy it
2. come back into android studio and open two files:
   in the top left of the screen you should see a dropdown that says Android. click it and select Project which will show you 
   your directories in a different perspective
3. open: 

     **Project/"your project name"/app/src/debug/res/values/google_maps_api.xml**

     also open:
   
     **Project/"your project name"/app/src/release/res/values/google_maps_api.xml**
   
     in both instances you should see a file which has this line of code at the bottom:
   
       <string name="google_maps_key" templateMergeStrategy="preserve" translatable="false">"new api goes here"</string>
   
   "new api goes here" is where you should paste the API key you copied from our slack [api_key channel](https://sudoa.slack.com/messages/CFVVAEBS5/). make sure there are no quotation marks

4. to find your official Package name go back the top left of the screen you should see a dropdown that says Project now. click it and select Android this time. this will show your directories in a different perspective 

     goto -> **app/java** where there should be three files all with the same package names, something like:
     
    **com.dev2qa.parkedup2**
    
     write this down (or copy it by right clicking the top folder and clicking Copy Reference) and keep track of it somewhere

5. next we need our projects to be working with the same Google Maps API key (as far as the Google Cloud is concerned). we just collected our official Package name, now we need everyones' personal Android projects' SHA-1 certificate fingerprint. to get this, go back to the top left of the screen where it now says Project, click the dropdown and click Android to bring it back to the original directory perspective

     go back to -> **Project/"your project name"/app/src/debug/res/values/google_maps_api.xml**
     under the *commented* code in the middle there should be something resembling this:

       SHA-1 certificate fingerprint
       34:AE:5F:83:35:19:59:85:B3:G2:88:BB:22:11:00:B1:34:6E:N6:92

5. copy this certificate code (the second line) and paste it into our slack here: [sha-1_certificates](https://sudoa.slack.com/messages/CFWD44V7W/) where I will collect them and input them into the Google Cloud Console I have set up for this project. this will allow us all to use the same API key for all personal projects we are working in at home
