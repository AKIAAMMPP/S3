package beans;

public class User {
	private String email;
    private String typeUser;

    public User(String email, String typeUser) {
        this.email = email;
        this.typeUser = typeUser;
    }
    public User() {
    	
    }

    public String getEmail() {
        return email;
    }

    public String getTypeUser() {
        return typeUser;
    }

	

}
