package br.edu.ufcg.ccc.psoft.cccpharma.CCCPharma.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.edu.ufcg.ccc.psoft.cccpharma.CCCPharma.model.product.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, String> {

}
