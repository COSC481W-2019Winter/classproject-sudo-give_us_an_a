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
  
