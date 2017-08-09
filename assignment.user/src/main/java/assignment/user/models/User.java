package assignment.user.models;


import javax.persistence.Entity;

import org.hibernate.validator.constraints.Email;


@Entity
public class User extends BaseModel{

    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;
    @Email
    private String email;
    private String pinCode;
    
    public User(String name, String email,String pinCode) {
        this.name = name;
        this.email = email;
        this.pinCode=pinCode;
    }

   
    public User() {}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPinCode() {
		return pinCode;
	}


	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}


	

    }
