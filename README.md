# EgeNav

## Introduction ##

With the widespread use of mobile devices, location-awareness in software applications becomes an increasingly important concept. In this framework, it is aimed to develop an easy to use navigation framework for Java developers. By using this navigation framework, software developers will be able to add map and navigation support (such as location, speed, heading and direction information on a map) to their applications. Getting maps and directions is made possible by using external URL-based map and direction services. Thus, software developers will be able to integrate maps, navigation and direction support easily to their applications.

For getting URL-based maps and directions, an upper class structure is developed. Then these classes are extended to support Google Maps and Google Directions API directly. Using other URL-based map and direction services are also possible by extending the upper classes. In addition to using external direction services, EgeNav also supports raster image-based path finding.

To minimize network costs for downloading map images over the Internet, EgeNav employs a caching strategy. As the user's location changes, neighboring map tiles are downloaded. It is possible to compose a map from cached maps by combining cached maps.

EgeNav also incorporates ready to use Graphical User Interface (GUI) components. One of these components is the **MapPanel** component (class). **MapPanel** class provides following functionalities:

1. Shows maps associated with the stored **MapURL** object
2. Presenting audio, visual and textual navigation and direction info and instructions
3. Storing navigation history
4. Setting **MapURL** object, map type and zoom level
5. Dragging map by mouse to explore other regions, etc.

##Features##

EgeNav supports following features:

1. Navigation support
2. URL-based map and directions support
  * Presenting audio, visual and textual navigation and direction info and instructions on the map
3. Storing navigation history
4. Visual, textual and audio directions support
5. Caching of maps
6. Constructing new maps by combining cached maps
7. Raster image-based path finding
8. Raster image-based directions support
  * Detection of junction points and the available directions
  * Detection of turning points


##Downloads##

1. [Demo application 1](https://drive.google.com/open?id=0B0jxyO3H3yKHX0pDc05yNEZVZjA) (includes EgeNav library)
2. [Demo application 2](https://drive.google.com/open?id=0B0jxyO3H3yKHMEc2UnRVd1lPZlk) (includes EgeNav library)

##Demo Applications##

2 demo applications are developed using EgeNav framework to promote and show what EgeNav is capable of doing. The demo applications, each including EgeNav library, other external libraries and the user manual packed in a single file can be downloaded from [here](https://drive.google.com/drive/folders/0B0jxyO3H3yKHX19GTTJQVEdkbm8).

The demo applications can be executed by double clicking **EgeNav.jar** file after extracting the contents of the zip file to a directory. The 2 demo applications are included in both of the **EgeNav.jar** files. The only differences between the demo files are in the manifest files where the main-class (the class that will be run by the JVM) are different and also the sample data that is specific to each application are different.

Please note that, each of the **EgeNav.jar** files contains EgeNav framework classes plus the 2 demo applications. So If you want to use the EgeNav framework, just include any one of the **EgeNav.jar** files in the classpath of your project. And you are done.

###Demo Application 1##

This application is a sample navigation application which shows maps, direction support and navigation information to the user as shown in the below figure.
![alt tag](/EgeNav/docs/ss/EgeNav_img1.png)

This demo application supports 
* setting map type and zoom level, 
* dragging map to explore other regions, 
* traveling from one point to another point, 
* getting textual, audio and visual direction instructions for travelling from the current location to a destination location, 
* simulating a real-time travel, passing from many locations which are defined in a file.

###Demo Application 2##

The second demo application is implemented to show image-based path finding features of EgeNav as shown in the below figure.
![alt tag](/EgeNav/docs/ss/EgeNav_img2.png)

This demo application supports 
* path finding in maps represented as raster images, 
* getting textual and visual direction instructions for travelling from one location to a destination location, 
* listing junction points and the available directions (in degree angles) relative to the heading direction in each junction point on the path, 
* listing turning points and the turning angle (in degrees) relative to the heading direction in each turning point on the path.

##Documentation##

Under construction...

##Acknowledgments##

This project was partially supported by Ege University's Scientific Research Project program under grant number 12-MUH-001.
