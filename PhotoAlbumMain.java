import controller.IPhotoAlbumController;
import controller.PhotoAlbumController;

public class PhotoAlbumMain {
  public static void main(String[] args) {
    // Check if there are an odd number of arguments (excluding xmax and ymax)
    if ((args.length - 1) % 2 == 0) {
      System.out.println("Invalid number of arguments. Each pair of arguments should be in the form -key value.");
      System.exit(1);
    }
    // Parse command-line arguments
    String inputFile = null;
    String outputFile = null;
    // Default value is 1000
    int viewWidth = 1000;
    int viewHeight = 1000;
    String viewType = null;

    for (int i = 0; i < args.length - 1; i = i + 2) {
      String key = args[i];
      String value = args[i + 1];
      switch (key.toLowerCase()) {
        case "-in":
          inputFile = value;
          break;
        case "-out":
          outputFile = value;
          break;
        case "-view":
        case "-v":
          viewType = value.toLowerCase();
          break;
        default:
          // Check if the key is a numeric value
          try {
            int intValue = Integer.parseInt(key);
            viewWidth = intValue;
            viewHeight = Integer.parseInt(value);
          } catch (NumberFormatException e) {
            System.out.println("Invalid argument: " + key);
            System.exit(1);
          }
      }
    }
    // Check if mandatory arguments are provided
    if (inputFile == null || viewType == null) {
      System.out.println("Missing mandatory arguments. Usage: -in \"input-file\" -view \"type-of-view\" [-out \"where-output-should-go\"] [xmax] [ymax]");
      System.exit(1);
    }
    // Check if view type is valid
    if (!viewType.equals("graphical") && !viewType.equals("web")) {
      System.out.println("Invalid view type. Supported types: graphical, web");
      System.exit(1);
    }
    // Check if view type is web and if view is provided
    if (viewType.equals("web") && outputFile == null) {
      System.out.println("Web view needs output file name.");
      System.exit(1);
    }
    // Create the photo album controller and initialize the application
    IPhotoAlbumController controller = new PhotoAlbumController();
    try {
      controller.go(inputFile, outputFile, viewType, viewWidth, viewHeight);

    } catch (Exception e) {
      System.out.println(e);
    }
  }
}
