package br.edu.ufcg.ccc.psoft.cccpharma.CCCPharma.controller.product;

import java.util.*;

import br.edu.ufcg.ccc.psoft.cccpharma.CCCPharma.customExceptions.client400.BadRequest400Exception;
import br.edu.ufcg.ccc.psoft.cccpharma.CCCPharma.customExceptions.client400.Conflict409Exception;
import br.edu.ufcg.ccc.psoft.cccpharma.CCCPharma.jsonAdaptedModels.product.PartialInformationAvailableProduct;
import br.edu.ufcg.ccc.psoft.cccpharma.CCCPharma.jsonAdaptedModels.product.PartialInformationProduct;
import br.edu.ufcg.ccc.psoft.cccpharma.CCCPharma.jsonAdaptedModels.product.PartialInformationUnavailableProduct;
import br.edu.ufcg.ccc.psoft.cccpharma.CCCPharma.model.category.*;
import br.edu.ufcg.ccc.psoft.cccpharma.CCCPharma.model.product.Product;
import br.edu.ufcg.ccc.psoft.cccpharma.CCCPharma.repository.CategoryRepository;
import br.edu.ufcg.ccc.psoft.cccpharma.CCCPharma.repository.ProductRepository;

public class ProductController {

	private List<Product> products;
	private HashMap<String, Category> categories;
	private ProductRepository productDAO;
	private CategoryRepository categoryDAO;

	public ProductController(ProductRepository productDAO, CategoryRepository categoryDAO) {
		this.productDAO = productDAO;
		this.categoryDAO = categoryDAO;
		this.products = loadProducts();
		this.categories = loadCategories();
	}

	public void addProduct(String name, String barCode, String company, String categoryType, String status)
			throws Conflict409Exception {
		Category category = this.categories.get(categoryType);
		Product product = new Product(name, barCode, category, company, status);
		this.products.add(product);
		this.productDAO.save(product);
	}

	public void addLot(int productAmount, Date shelfLife, String barcode)
			throws BadRequest400Exception, Conflict409Exception {
		Product product = getProductByBarcode(barcode);

		if (product != null) {
			product.addLot(productAmount, shelfLife);
			if (product.getStatusInfo().toLowerCase().equals("unavailable"))
				product.setStatus("Available");
			this.productDAO.save(product);
		} else {
			throw new IllegalArgumentException("Product is not registered");
		}
	}

	public void changeProductPrice(double price, String barcode) throws BadRequest400Exception {
		Product product = getProductByBarcode(barcode);

		if (product != null) {
			product.setPrice(price);
			this.productDAO.save(product);
		} else
			throw new BadRequest400Exception("Product is not registered");
	}

	public void decreaseProductAmount(int amount, String barcode) throws BadRequest400Exception {
		Product product = getProductByBarcode(barcode);

		if (product != null) {
			if (product.getStatusInfo().toLowerCase().equals("unavailable"))
				throw new IllegalArgumentException("There is no lot of this product in stock");
			else {
				product.decreaseAmount(amount);
				this.productDAO.save(product);
			}
		} else
			throw new IllegalArgumentException("Product is not registered");
	}

	public void changeCategoryDiscount(String categoryType, double discount) throws Conflict409Exception {
		Category category = getCategory(categoryType);
		category.setDiscount(discount);
		this.categoryDAO.save(category);
	}

	public List<PartialInformationProduct> getProductsInfo() {
		List<PartialInformationProduct> productsInfo = new ArrayList<>();

		for (Product product : this.products) {
			if (product.getStatusInfo().toLowerCase().equals("available"))
				productsInfo.add(new PartialInformationAvailableProduct
						(product.getName(), product.getPrice(), product.getStatusInfo()));
			else
				productsInfo.add(new PartialInformationUnavailableProduct
						(product.getName(), product.getStatusInfo()));
		}
		return productsInfo;
	}

	public List<Product> getInventoryReport() {
		return this.products;
	}

	private Category getCategory(String categoryType) {
		Category category = this.categories.get(categoryType);
		if (category != null)
			return category;
		else
			throw new IllegalArgumentException("There is no such category in the system");
	}

	private Product getProductByBarcode(String barcode) {
		for (Product product : this.products) {
			if (product.getName().equals(barcode))
				return product;
		}
		return null;
	}

	private HashMap<String, Category> loadCategories() {
		Category cosmetic = this.categoryDAO.findById((long) 1).get();
		Category food = this.categoryDAO.findById((long) 2).get();
		Category medication = this.categoryDAO.findById((long) 3).get();
		Category toiletry = this.categoryDAO.findById((long) 4).get();

		HashMap<String, Category> categories = new HashMap<String, Category>();
		categories.put("cosmetic", cosmetic);
		categories.put("food", food);
		categories.put("medication", medication);
		categories.put("toiletry", toiletry);
		return categories;
	}

	private ArrayList<Product> loadProducts() {
		Iterable<Product> iterableProducts = this.productDAO.findAll();
		ArrayList<Product> productList = new ArrayList<Product>();
		for (Product product : iterableProducts) {
			productList.add(product);
		}
		return productList;
	}
}