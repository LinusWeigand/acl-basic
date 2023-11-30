public class User{
  private String name;
  private ReferenceMonitor monitor;

  public User(String name) {
    this.name = name;
  }

  public readFile(File file) {
    monitor.readFile(this, file)
  }
  public writeFile(File file, String content) {
    monitor.writeFile(this, file, content)
  }
  public executeFile(File file) {
    monitor.executeFile(this, file)
  }
}
