package dto;

public class UsuarioDTO {
    
    private String password;
	private String email;
	private String rol;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return rol;
	}

	public void setRole(String role) {
		this.rol = role;
	}

}
