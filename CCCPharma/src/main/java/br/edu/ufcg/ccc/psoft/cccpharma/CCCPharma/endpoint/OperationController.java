package br.edu.ufcg.ccc.psoft.cccpharma.CCCPharma.endpoint;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ufcg.ccc.psoft.cccpharma.CCCPharma.authentication.Authenticator;
import br.edu.ufcg.ccc.psoft.cccpharma.CCCPharma.authentication.ClientAuthenticator;
import br.edu.ufcg.ccc.psoft.cccpharma.CCCPharma.controller.product.ProductController;
import br.edu.ufcg.ccc.psoft.cccpharma.CCCPharma.controller.user.UserController;
import br.edu.ufcg.ccc.psoft.cccpharma.CCCPharma.jsonAdaptedModels.Request.AuthenticatedRequest;
import br.edu.ufcg.ccc.psoft.cccpharma.CCCPharma.jsonAdaptedModels.category.UpdateInformationCategory;
import br.edu.ufcg.ccc.psoft.cccpharma.CCCPharma.jsonAdaptedModels.lot.RegisterInformationLot;
import br.edu.ufcg.ccc.psoft.cccpharma.CCCPharma.jsonAdaptedModels.product.*;
import br.edu.ufcg.ccc.psoft.cccpharma.CCCPharma.jsonAdaptedModels.user.*;
import br.edu.ufcg.ccc.psoft.cccpharma.CCCPharma.model.product.Product;
import br.edu.ufcg.ccc.psoft.cccpharma.CCCPharma.repository.CategoryRepository;
import br.edu.ufcg.ccc.psoft.cccpharma.CCCPharma.repository.ProductRepository;
import br.edu.ufcg.ccc.psoft.cccpharma.CCCPharma.repository.*;

@RestController
@SpringBootApplication
public class OperationController {

	private Authenticator authenticator;
	private ProductController productController;
	private UserController userController;
	
	@Autowired
	public OperationController(ProductRepository productDAO, CategoryRepository categoryDAO, UserRepository userDAO) {
		this.productController = new ProductController(productDAO, categoryDAO);
		this.userController = new UserController(userDAO);
		this.authenticator = new ClientAuthenticator(userDAO);
	}
	
	
	/**
	 * Validates an admin {@link User} and create {@link Product}
	 * <p><ul>
	 * <li>URL route: /product/create/</li>
     * <li>HTTP method: POST</li>
     * <li>Request's body: {user:{login, password}, data:{name, barcode, company, category, status}} </li> 
     * <li>Possibles response status: 201, 400, 401, 403</li>
	 * </ul></p>
	 *
	 * @param request expects a valid {@link VerificationInformationUser} and a {@link RegisterInformationProduct}.          (3)
	 */
	@RequestMapping(value="/product/create/", method=RequestMethod.POST)
	public ResponseEntity<Void> addProduct(@Valid @RequestBody AuthenticatedRequest<RegisterInformationProduct> request) {
		authenticateAdmin(request.getUser());
		RegisterInformationProduct product = request.getData();
		productController.addProduct(
				product.getName(),
				product.getBarcode(),
				product.getCompany(),
				product.getCategory(),
				product.getStatus()
		);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	/**
	 * Validates an admin {@link User} and changes {@link Product}'s price
	 * <p><ul>
	 * <li>URL route: /product/change_price/</li>
     * <li>HTTP method: PATCH</li>
     * <li>Request's body: {user:{login, password}, data:{barcode, price}}  </li>
     * <li>Possibles response status: 200, 400, 401, 403  </li>
	 * </ul></p>
	 *
	 * @param request expects a valid {@link VerificationInformationUser} and a {@link UpdateInformationProduct}.          (3)
	 */
	@RequestMapping(value="/product/change_price/", method=RequestMethod.PATCH)
	public ResponseEntity<Void> changeProductPrice(@Valid @RequestBody AuthenticatedRequest<UpdateInformationProduct> request) {
		authenticateAdmin(request.getUser());
		UpdateInformationProduct product = request.getData();
		productController.changeProductPrice(
				product.getNewPrice(),
				product.getBarcode()
		);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	/**
	 * Validates an admin {@link User} and decreases the {@link Product}'s amount
	 * <p><ul>
	 * <li>URL route: /product/decrease_amount/</li>
     * <li>HTTP method: PATCH</li>
     * <li>Request's body: {user:{login, password}, data:{barcode, removeAmount}}  </li>
     * <li>Possibles response status: 200, 400, 409, 401, 403</li>
	 * </ul></p>
	 *
	 * @param request expects a valid {@link VerificationInformationUser} and a {@link RemovalInformationProduct}
	 */
	@RequestMapping(value="/product/decrease_amount/", method=RequestMethod.PATCH)
	public ResponseEntity<Void> decreaseProductAmount(@Valid @RequestBody AuthenticatedRequest<RemovalInformationProduct> request) {
		authenticateAdmin(request.getUser());
		RemovalInformationProduct product = request.getData();
		productController.decreaseProductAmount(
				product.getRemoveAmount(),
				product.getBarcode()
		);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	/**
	 * Requests information of all {@link Product}.
	 * <p><ul>
	 * <li>URL route: /product/</li>
     * <li>HTTP method: GET</li>
     * <li>Response's body: {{name, company, status, price}, ...}  </li>
     * <li>Possibles response status: 200</li>
	 * </ul></p>
	 *
	 * @return returns a Json with a {@link List} of {@link PartialInformationProduct}
	 */
	@RequestMapping(value="/product/", method=RequestMethod.GET)
	public ResponseEntity<List<PartialInformationProduct>> getProductsInfo() {
		return ResponseEntity.ok(productController.getProductsInfo());
	}
	
	/**
	 * Validates an admin {@link User} and returns detailed information of all {@link Product}
	 * <p><ul>
	 * <li>URL route: /product/report/</li>
     * <li>HTTP method: GET</li>
     * <li>Request's body: {login, password}</li>
     * <li>Response's body: {{name, company, status, price, lots:{{amount, shelflife}, ... }}, ...}</li>  
     * <li>Possibles response status: 200, 401, 403</li>
	 * </ul></p>
	 * 
	 * @param user expects a valid {@link VerificationInformationUser}
	 * @return returns a Json with a {@link List} of {@link Product}
	 */
	@RequestMapping(value="/product/", method=RequestMethod.GET)
	public ResponseEntity<List<Product>> getInventoryReport(@Valid @RequestBody VerificationInformationUser user) {
		authenticateAdmin(user);		
		return ResponseEntity.ok(productController.getInventoryReport());
	}
	
	
	/**
	 * Validates an admin {@link User} and create a {@link Lot} of {@link Product}
	 * <p><ul>
	 * <li>URL route: /product/lot/ </li>
     * <li>HTTP method: POST</li>
     * <li>Request's body: {user:{login, pass}, data:{productbarcode, shelfLife, amount}}  </li>
     * <li>Possibles response status: 201, 400, 401, 403</li>
	 * </ul></p>
	 * 
	 * @param request expects a valid {@link VerificationInformationUser} and a {@link RegisterInformationLot}
	 */
	@RequestMapping(value="/product/lot/", method=RequestMethod.POST)
	public ResponseEntity<Void> addLot(@Valid @RequestBody AuthenticatedRequest<RegisterInformationLot> request) {
		authenticateAdmin(request.getUser());
		RegisterInformationLot lot = request.getData(); 
		productController.addLot(
				lot.getAmount(),
				lot.getShelfLife(),
				lot.getProductBarcode()
		);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	/**
	 * Validates an admin {@link User} and updates the discount of a {@link Category}
	 * <p><ul>
	 * <li>URL route: /category/</li>
     * <li>HTTP method: PATCH</li>
     * <li>Request's body: {user:{login, pass}, data:{categoryClass, newDiscount}}  </li>
     * <li>Possibles response status: 200, 400, 401, 403</li>
	 * </ul></p>
	 * 
	 * @param request expects a valid {@link VerificationInformationUser} and a {@link UpdateInformationCategory}
	 */
	@RequestMapping(value="/category/", method=RequestMethod.PATCH)
	public ResponseEntity<Void> changeCategoryDiscount(@Valid @RequestBody AuthenticatedRequest<UpdateInformationCategory> request) {
		authenticateAdmin(request.getUser());
		UpdateInformationCategory category = request.getData();
		productController.changeCategoryDiscount(
				category.getCategoryClass(),
				category.getNewDiscount()
		);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	/**
	 * Creates a new {@link User}
	 * <p><ul>
	 * <li>URL route: /user/create/</li>
     * <li>HTTP method: POST</li>
     * <li>Request's body: {name, login, password}  </li>
     * <li>Possibles response status: 201, 400, 409</li>
	 * </ul></p>
	 * 
	 * @param user expects a valid {@link RegisterInformationUser}
	 */
	@RequestMapping(value="/user/create/", method=RequestMethod.POST)
	public ResponseEntity<Void> addUser(@Valid @RequestBody RegisterInformationUser user) {
		userController.addUser(
				user.getName(),
				user.getLogin(),
				user.getPassword(),
				false
	    );
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	/**
	 * Checks if the login is used by any {@link User}
	 * <p><ul>
	 * <li>URL route: /user/</li>
	 * <li>HTTP method: GET</li>
	 * <li>Request's body: {login}</li>
	 * <li>Possibles response status: 200, 404</li>
	 * </ul></p>
	 * 
	 * @param user expects a valid {@link ExistanceInformationUser}
	 * @return returns an 200 status response if the user is in use and an 200 status response if the user is available 
	 */
	@RequestMapping(value="/user/", method=RequestMethod.GET)
	public ResponseEntity<Void> addUser(@Valid @RequestBody ExistanceInformationUser user) {
		if (userController.checkRegistered(user.getLogin())) {
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
	
	private void authenticateAdmin(VerificationInformationUser user) {
		authenticator.adminAuthenticate(user.getLogin(), user.getPassword());
	}
}
