Photo Album Program Readme

Overview
The Photo Album program uses MVS model to design and implement a simple Photo Album application for displaying a list of snapshots made from shapes in both graphical view(GUI) and static web view(SVG), as well as managing and manipulating geometric shapes within the photo album. Shapes, such as rectangles and ovals, can be created, transformed, moved, modified, and removed in the album. Historical state of shapes is captured and saved in snapshots. Users need to give command line inputs with input file name, view type(graphical or web), output filename(mandantory for web view), screen width and height(optional) following below steps:

User Guide:
1. compile the PhotoAlbum.java file with "javac"
2. run PhotoAlbum [your input] e.g.-in inputFile -v web -out webView.html
3. In grapghical view, user can interact with the graph by click on "next"[keyboard >], "prev"[keyboard <], "select[keyboard ^]", and "quit[keyboard x]" button to move to different snapshots or quit the program. 
4. In web view, after the main class is run, an html file with your designated file name will be produced and saved in the same directory. User can see the web version picture of all the snapshots with no interactive functionalities.

Classes
【Model】
IPhotoAlbum
The IPhotoAlbum interface defines the contract for a photo album. It includes methods for creating shapes, removing shapes, retrieving all shapes, changing names, shape type, properties(location, color, scale), taking snapshots, retrieving snapshot IDs, resetting snapshots, and printing snapshots. In hw9, I added one more method which is to return the hashtable of the snapshots, with its unique id as key. This makes it easier to retrive and access specific snapshot by index / key afterwards to draw and make changes to the shapes. 

PhotoAlbum
The PhotoAlbum class implements the IPhotoAlbum interface and serves as the main component for managing shapes and snapshots. It contains methods for creating shapes, removing shapes, retrieving all shapes, changing names, shape type, properties(location, color, scale), taking snapshots, retrieving snapshot IDs, resetting snapshots, and printing snapshots. In hw9, I added one more method which is to return the hashtable of the snapshots, with its unique id as key. This makes it easier to retrive and access specific snapshot by index / key afterwards to draw and make changes to the shapes. 

ISnapshot
The ISnapshot interface defines the contract for a snapshot. It includes methods for getting snapshotID, getting description, getting timestamp and toString method. The toString method provides a formatted representation of the snapshot. In hw9, I added another method to retrieve all the shapes of the current snapshot and stored in a list. This method is used to help draw and make changes to shapes.

Snapthot
The Snapshot class implements the ISnapshot interface, representing a snapshot of the photo album at a specific moment. It includes information such as a snapshot ID, timestamp, description, and a list of shapes captured in the snapshot. It contains methods for getting snapshotID, getting description, getting timestamp and toString method. The toString method provides a formatted representation of the snapshot.In hw9, I added another method to retrieve all the shapes of the current snapshot and stored in a list. This method is used to help draw and make changes to shapes.

IShape
The IShape interface defines the basic structure for geometric shapes. It includes methods for setting and retrieving the shape's name, type, location, color, x, y, and the map of all properties(location, color, x, y). The toString method is implemented by concrete shape classes to provide a string representation. In hw9, I added three methods in this class, which makes each shape to be able to draw themselves. Similarly, a method to get the SVG representation of each shape for the Web view, as well as HexColor representation of color used in Web view.

AbstractShape
The AbstractShape class is an abstract implementation of the IShape interface. It includes common attributes such as location, name, shape type, color, x, y, and properties. It includes methods for setting and retrieving the shape's name, type, location, color, x, y, and the map of all properties(location, color, x, y). The class also provides a generic toString method and implements equals and hashCode methods. In hw9, I added three methods in this class, which makes each shape to be able to draw themselves. Similarly, a method to get the SVG representation of each shape for the Web view, as well as HexColor representation of color used in Web view.

Rectangle
The Rectangle class extends AbstractShape and represents a rectangular shape. It includes properties specific to rectangles, such as width and height. In hw9, I added three methods in this class, which makes each rectangle to be able to draw themselves. Similarly, a method to get the SVG representation of each rectangle for the Web view, as well as HexColor representation of color used in Web view.


Oval
The Oval class extends AbstractShape and represents an oval shape. It includes properties specific to ovals, such as radiusX and radiusY. In hw9, I added two methods in this class, a method to get the SVG representation of each shape for the Web view, as well as HexColor representation of color used in Web view.


Point2D
The Point2D class represents a 2D point with x and y coordinates. It includes methods for retrieving the coordinates and a toString method for a formatted string representation. The class also implements equals and hashCode methods.


CustomColor
The CustomColor class extends the Color class and represents a custom color with float values for red (r), green (g), and blue (b), ranging from 0.0 to 1.0. The toString method is overridden to provide a formatted string representation.

【View】
IPhotoAlbumView
The IPhotoAlbumView is interface of two classes - WebView and GraphicalView. Methods including, set up key bindings with the canvas, enable Next button, previous button, and select button, dispaly method.

WebView
The WebView class generates an HTML document with SVG markup to display snapshots and shapes. It takes a photoAlbum and an outputFile as parameters, creating an HTML file with a corresponding SVG representation of the snapshots.

GraphicalView
The GraphicalView class is a graphical user interface (GUI) implemented using Java Swing. It allows users to navigate through snapshots, displaying shapes in a graphical format. This class includes buttons for navigation and a drawing panel for rendering shapes.

DrawPanel
The DrawPanel class extends JPanel and is responsible for rendering shapes on the graphical canvas within the GraphicalView. It provides methods for navigating through snapshots and selecting a specific snapshot.

Event Listeners
The application includes custom event listeners (MyNextListener, MyPreviousListener, MyQuitListener, and MySelectListener) to handle user interactions with the GUI. These listeners trigger actions such as navigating to the next or previous snapshot, quitting the application, and selecting a specific snapshot.

【Controller】
IPhotoAlbumController
The IPhotoAlbumController interface defines a method go that takes input parameters such as input and output file paths, view type, and dimensions. This method is responsible for initializing the photo album, setting up the appropriate viewer based on the specified type, and displaying the viewer.

PhotoAlbumController
The PhotoAlbumController class implements the IPhotoAlbumController interface. It creates an instance of the photo album model and initializes a view based on the specified type (graphical or web). The controller reads commands from an input file, sets up the viewer, and displays it to the user.

【Utility】
Util
The Util class provides utility methods for transforming string representations of snapshots into ISnapshot objects. Additionally, it includes helper methods for parsing shape information and creating shape instances based on the provided data. 

CommandFileReader
The CommandFileReader class reads commands from an input file and executes corresponding operations on a provided IPhotoAlbum model. It supports commands for creating shapes, moving shapes, changing colors, resizing shapes, removing shapes, and taking snapshots.


Dependencies
The program uses the Color class from the java.awt package for handling color information.

Exception Handling
The program includes exception handling for invalid arguments during shape and snapshot creation, removing shape, changing properties, read user input. If an invalid argument is detected, an IllegalArgumentException is thrown with a corresponding error message.
