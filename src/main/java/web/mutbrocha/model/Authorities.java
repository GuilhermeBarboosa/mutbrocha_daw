package web.mutbrocha.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "authorities")
public class Authorities {
	@Id
	private String username;
	private String authority;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	@Override
	public int hashCode() {
		return Objects.hash(authority, username);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Authorities other = (Authorities) obj;
		return Objects.equals(authority, other.authority) && Objects.equals(username, other.username);
	}
	@Override
	public String toString() {
		return "Authorities [username=" + username + ", authority=" + authority + "]";
	}
	
	
	
}
