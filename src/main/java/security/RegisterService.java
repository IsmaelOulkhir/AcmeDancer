/*
 * LoginService.java
 * 
 * Copyright (C) 2018 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Academia;
import domain.Actores;
import domain.Alumno;
import repositories.ActorRepository;

@Service
@Transactional
public class RegisterService implements UserDetailsService {

	// Managed repository -----------------------------------------------------

	@Autowired
	UserAccountRepository	userRepository;
	@Autowired
	ActorRepository actorRepository;


	// Business methods -------------------------------------------------------

	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
		Assert.notNull(username);

		UserDetails result;

		result = this.userRepository.findByUsername(username);
		Assert.notNull(result);
		// WARNING: The following sentences prevent lazy initialisation problems!
		Assert.notNull(result.getAuthorities());
		result.getAuthorities().size();

		return result;
	}

	public static UserAccount getPrincipal() {
		UserAccount result;
		SecurityContext context;
		Authentication authentication;
		Object principal;

		// If the asserts in this method fail, then you're
		// likely to have your Tomcat's working directory
		// corrupt. Please, clear your browser's cache, stop
		// Tomcat, update your Maven's project configuration,
		// clean your project, clean Tomcat's working directory,
		// republish your project, and start it over.

		context = SecurityContextHolder.getContext();
		Assert.notNull(context);
		authentication = context.getAuthentication();
		Assert.notNull(authentication);
		principal = authentication.getPrincipal();
		Assert.isTrue(principal instanceof UserAccount);
		result = (UserAccount) principal;
		Assert.notNull(result);
		Assert.isTrue(result.getId() != 0);

		return result;
	}

	public void save(CredentialsRegister credentials) {
		// TODO Auto-generated method stub
		assert credentials != null;
		String tipo = credentials.getTipoUsuario();
		if (tipo.equals("Alumno")) {
			UserAccount u = new UserAccount();
			Authority a = new Authority();
			a.setAuthority(Authority.ALUMN);
			u.addAuthority(a);
			u.setPassword(getMd5(credentials.getPassword()));
			u.setUsername(credentials.getUsername());
			UserAccount result = this.userRepository.save(u);
			
			Alumno al = new Alumno();
			al.setApellidos(credentials.getApellidos());
			al.setCorreoElectronico(credentials.getCorreoElectronico());
			al.setdireccionPostal(credentials.getdireccionPostal());
			al.setNombre(credentials.getNombre());
			al.setApellidos(credentials.getApellidos());
			al.setnumeroTelefono(credentials.getnumeroTelefono());
			al.setUserAccount(result);
			actorRepository.save(al);
		}else {
			UserAccount u = new UserAccount();
			Authority a = new Authority();
			a.setAuthority(Authority.ACADEMY);
			u.addAuthority(a);
			u.setPassword(getMd5(credentials.getPassword()));
			u.setUsername(credentials.getUsername());
			UserAccount result = this.userRepository.save(u);
			
			Academia ac = new Academia();
			ac.setnombreComercial(credentials.getnombreComercial());
			ac.setApellidos(credentials.getApellidos());
			ac.setCorreoElectronico(credentials.getCorreoElectronico());
			ac.setdireccionPostal(credentials.getdireccionPostal());
			ac.setNombre(credentials.getNombre());
			ac.setApellidos(credentials.getApellidos());
			ac.setnumeroTelefono(credentials.getnumeroTelefono());
			ac.setUserAccount(result);
			actorRepository.save(ac);
		}		
	}
	
	public void save(Actores actor) {
		actorRepository.save(actor);
	}
	
	private String getMd5(String pass){
		Md5PasswordEncoder encoder;
		encoder = new Md5PasswordEncoder();
		String hash = encoder.encodePassword(pass, null);
		return hash;
	}

}
