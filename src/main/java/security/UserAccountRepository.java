/*
 * UserAccountRepository.java
 *
 * Copyright (C) 2018 Universidad de Sevilla
 *
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package security;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Actores;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Integer> {

	@Query("select ua from UserAccount ua where ua.username = ?1")
	UserAccount findByUsername(String username);

	@Query("select a.userAccount from Actores a where a.id = ?1")
	UserAccount findByActorId(int actorId);

	@Query("select c from Actores c where c.userAccount.username = ?1")
	Actores findActorByUsername(String username);

	@Query("select c from Actores c where c.userAccount.id = ?1")
	Actores findActorByUsernameId(Integer username);
}
