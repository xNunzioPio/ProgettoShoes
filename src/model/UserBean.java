package model;

public class UserBean {
	
	private int idUser;
    private String role;
    private String email;
    private String password;
    public boolean valid;
	
	public UserBean() {
		idUser=-1;
		role="";
		email="";
		password="";
	}
	
	public int getIdUser() {
		return idUser;
	}
	
	public void setIdUser(int newIdUser) {
		idUser=newIdUser;
	}

    public String getPassword() {
       return password;
	}

    public void setPassword(String newPassword) {
       password = newPassword;
	}
	
			
    public String getEmail() {
       return email;
			}

    public void setEmail(String newEmail) {
       email = newEmail;
			}
    
    public String getRole() {
    	return role;
    }
    
    public void setRole(String newRole) {
    	role=newRole;
    }
				
    public boolean isValid() {
       return valid;
	}

    public void setValid(boolean newValid) {
       valid = newValid;
	}	
}

