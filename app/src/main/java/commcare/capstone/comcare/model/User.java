package commcare.capstone.comcare.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.HashSet;
import java.util.Set;

@DatabaseTable(tableName = "user")
public class User {
	@DatabaseField(generatedId = true, allowGeneratedIdInsert = true) long id;
	@DatabaseField(columnName = "login", canBeNull = true) private String login;
	@DatabaseField(columnName = "password", canBeNull = true) private String password;
	@DatabaseField(columnName = "fullName", canBeNull = true) private String fullName;
	@DatabaseField(columnName = "role", canBeNull = true) private String role;
	@DatabaseField(columnName = "email", canBeNull = true) private String email;
	@DatabaseField(columnName = "deleted", canBeNull = true) private boolean deleted = false;
	@DatabaseField(columnName = "token", canBeNull = true) private long token = 0l;
	
	public static final String ROLE_ADMIN = "Admin";
	public static final String ROLE_USER = "User";

	public User()
	{

	}
	public User(commcare.capstone.comcare.webservice.User u) {
		setId(u.id);
		setLogin(u.login);
		setPassword(u.password);
		setFullName(u.fullName);
		setRole(u.role);
		setEmail(u.email);
		setDeleted(u.deleted);
		setToken(u.token);
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public boolean isDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public long getToken() {
		return token;
	}
	public void setToken(long token) {
		this.token = token;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
}
