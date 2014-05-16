package es.beebusiness.authenticator;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.exceptions.EncryptionOperationNotPossibleException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

public class AuthenticationTokenProcessingFilter extends GenericFilterBean {

	private AuthenticationManager authManager;
	private long vigencia;

	public AuthenticationTokenProcessingFilter(AuthenticationManager authManager,long vigencia) {
		this.authManager = authManager;
		this.vigencia=vigencia;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		String token = httpServletRequest.getHeader("token");

		if (token != null) {
			StandardPBEStringEncryptor jasypt = new StandardPBEStringEncryptor();
			jasypt.setPassword(ConstantesAutenticator.PASS);
			try {
				String desencrypt = jasypt.decrypt(token);
				String[] values = desencrypt.split("&&");
				String username = values[1];
				String creacion = values[2];
				long milisegundosCreacion = new Long(creacion);
				long milisegundosActual = System.currentTimeMillis();
				if (milisegundosActual - milisegundosCreacion < vigencia) {
					UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
							username, null);
					authentication.setDetails("REST");
					SecurityContextHolder.getContext().setAuthentication(
							authManager.authenticate(authentication));
				}
			} catch (EncryptionOperationNotPossibleException e) {

			} catch(Exception e){
				
			}

		}
		// continue thru the filter chain
		chain.doFilter(request, response);

	}

}
