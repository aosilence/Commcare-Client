package commcare.capstone.comcare.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "lastlogin")
public class LastLogin {


	@DatabaseField(id = true) long id = 1;
	@DatabaseField(columnName = "name", canBeNull = true) String name;
	@DatabaseField(columnName = "pw", canBeNull = true) String pw;
	@DatabaseField(columnName = "token", canBeNull = true) long token;
	@DatabaseField(columnName = "display_name", canBeNull = true) String displayName;
	@DatabaseField(columnName = "logged_in", canBeNull = true) boolean loggedIn;


    public LastLogin(){
    }


	public boolean isLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public long getToken() {
		return token;
	}

	public void setToken(long token) {
		this.token = token;
	}
}
