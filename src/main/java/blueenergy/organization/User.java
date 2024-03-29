package blueenergy.organization;

public class User {
	private String login;
	private String name;
	private String surname;
	private double salary = 2000;
	
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String username) {
		this.login = username;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}

	@Override
	public String toString() {
		return "User{" +
				"name='" + name + '\'' +
				", surname='" + surname + '\'' +
				'}';
	}
}
