package br.edu.ufcg.ccc.psoft.cccpharma.CCCPharma.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.edu.ufcg.ccc.psoft.cccpharma.CCCPharma.model.category.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {

}
