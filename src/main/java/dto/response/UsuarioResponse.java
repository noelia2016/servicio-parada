package dto.response;

import ps.model.Usuario;

public class UsuarioResponse {

	private Usuario usuario;

	public UsuarioResponse(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}

