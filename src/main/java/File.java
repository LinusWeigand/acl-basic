public class File {
  private String filename;
  private Map<User, List<Permission>> acl;
  private String content;

  public File(String filename, String content) {
      this.filename = filename;
      this.acl = new HashMap<>();
  }

  public void setPermissions(User user, List<Permission> permissions) {
        this.acl.put(user, permissions);
  }

  private boolean checkPermission(User user, Permission permission) {
      List<Permission> permissions = this.acl.get(user);
      return permissions != null && permissions.contains(permission);
  }

  public String readFile(User user) {
    if (!this.checkPermission(user, Permission.READ)) {
      return null;
    }
    return content;
  }

  public void writeFile(User user, String content) {
    if (this.checkPermission(user, Permission.WRITE)) {
      this.content = content
    }
  }
}
