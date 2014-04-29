package es.beebusiness.authenticator;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import es.beebusiness.domain.Usuario;
import es.beebusiness.exception.BusinessException;
import es.beebusiness.service.IUsuarioService;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private IUsuarioService usuarioService;
	@Autowired
	private Encrypter encode;

	@Transactional(readOnly = true)
	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		String username = authentication.getName();
		String password = (String) authentication.getCredentials();
		boolean validaPassword=true;
		Usuario usuario = null;
		Set<GrantedAuthority> permisos = new HashSet<GrantedAuthority>();
		if (authentication.getDetails() instanceof String) {
			String detalle=(String) authentication.getDetails();
			if("REST".equalsIgnoreCase(detalle)){
				validaPassword=false;
			}
		} 
		if(validaPassword){
			try {
				usuario = usuarioService.get(username);
			} catch (BusinessException e) {
				throw new BadCredentialsException("Usuario no encontrado.");
			}
			String passEncode = encode.encode(password);
			if (!usuario.getPassword().equals(passEncode)) {
				throw new BadCredentialsException("Contraseña incorrecta.");
			}
			for (String rol : usuario.getRoles()) {
				permisos.add(new SimpleGrantedAuthority(rol));
			}
		}
		
		

		return new UsernamePasswordAuthenticationToken(username, password,
				permisos);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return true;
	}

}
