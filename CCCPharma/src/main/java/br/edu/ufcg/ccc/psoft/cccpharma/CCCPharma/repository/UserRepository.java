package br.edu.ufcg.ccc.psoft.cccpharma.CCCPharma.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.edu.ufcg.ccc.psoft.cccpharma.CCCPharma.model.user.User;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
}
