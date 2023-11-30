public class ReferenceMonitor {
  
  private final HashMap<UserObjectTuple, Set<Capability>> acl;

  public ReferenceMonitor() {
    acl = new HashMap<UserObjectTuple, Set<Capability>>();
  }
  
  private boolean checkPermission(User user, File file, Permission permission) {
    UserObjectTuple temp = new UserObjectTuple(user, file);
    List<Permission> permissions = this.acl.get(temp);
    return permissions != null && permissions.contains(permission);
  }

  public void readFile(User user, File file) {
    if (!this.checkPermission(user, file, Permission.READ)) {
      return ;
    }

    System.out.println("Reading file " + file.getName() + " for user " + user.getName());
    Path path = Paths.get(file.getAbsolutePath());
    try {
        byte[] bytes = java.nio.file.Files.readAllBytes(path);
        System.out.println("File contents: " + new String(bytes));
    } catch (java.io.IOException e) {
        System.out.println("Error reading file " + file.getName() + ": " + e.getMessage());
    }
  } 

  public void writeFile(User user, File file, String content) {
    if (!this.checkPermission(user, file, Permission.WRITE)) {
      return ;
    }
    System.out.println("Writing to file " + file.getName() + " for user " + user.getName());
    Path path = Paths.get(file.getAbsolutePath());
    try {
        java.nio.file.Files.write(path, content.getBytes());
    } catch (java.io.IOException e) {
        System.out.println("Error writing file " + file.getName() + ": " + e.getMessage());
    }
  } 

  public void executeFile(User user, File file) {
    if (!this.checkPermission(user, file, Permission.EXECUTE)) {
      return ;
    }

    System.out.println("Executing file " + file.getName() + " for user " + user.getName());
    try {
        Runtime.getRuntime().exec(file.getAbsolutePath());
    } catch (java.io.IOException e) {
        System.out.println("Error executing file " + file.getName() + ": " + e.getMessage());
    }
  } 
}
