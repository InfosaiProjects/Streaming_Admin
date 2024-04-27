package bean;

public class LoginPojo
{
  private String username;
  private String password;
  private String adminName;
  private String fullTime;
  private String adminRight;
  private int id;
  
  public String getAdminName()
  {
    return adminName;
  }
  
  public void setAdminName(String adminName)
  {
    this.adminName = adminName;
  }
  
  public String getFullTime()
  {
    return fullTime;
  }
  
  public void setFullTime(String fullTime)
  {
    this.fullTime = fullTime;
  }
  
  public String getAdminRight()
  {
    return adminRight;
  }
  
  public void setAdminRight(String adminRight)
  {
    this.adminRight = adminRight;
  }
  
  public String getUsername()
  {
    return username;
  }
  
  public void setUsername(String username)
  {
    this.username = username;
  }
  
  public String getPassword()
  {
    return password;
  }
  
  public void setPassword(String password)
  {
    this.password = password;
  }
  
  public int getId()
  {
    return id;
  }
  
  public void setId(int id)
  {
    this.id = id;
  }
}