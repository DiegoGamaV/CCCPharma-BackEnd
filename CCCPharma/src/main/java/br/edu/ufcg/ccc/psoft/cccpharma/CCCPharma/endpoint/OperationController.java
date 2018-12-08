package br.edu.ufcg.ccc.psoft.cccpharma.CCCPharma.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ufcg.ccc.psoft.cccpharma.CCCPharma.controller.product.ProductController;
import br.edu.ufcg.ccc.psoft.cccpharma.CCCPharma.controller.user.UserController;
import br.edu.ufcg.ccc.psoft.cccpharma.CCCPharma.repository.CategoryRepository;
import br.edu.ufcg.ccc.psoft.cccpharma.CCCPharma.repository.ProductRepository;
import br.edu.ufcg.ccc.psoft.cccpharma.CCCPharma.repository.UserRepository;

@RestController
public class OperationController {

	private ProductController productController;
	private UserController userController;

	@Autowired
	public OperationController(ProductRepository productDAO, CategoryRepository categoryDAO, UserRepository userDAO) {
		this.productController = new ProductController(productDAO, categoryDAO);
		this.userController = new UserController(userDAO);
	}
}
