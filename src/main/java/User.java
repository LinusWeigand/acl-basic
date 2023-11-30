public class User {

  private String username;
  private File file

  public User(String username, File file){
    this.username = username;
    this.file = file;
  }

  private String readFile(){
    file.readFile()
  }

  private void writeFile(){
    file.writeFile()
  }
  
  private void executeFile(){
    file.executeFile()
  }
}
