package br.edu.ufcg.ccc.psoft.cccpharma.CCCPharma.controller.product;

import java.util.*;
import br.edu.ufcg.ccc.psoft.cccpharma.CCCPharma.model.product.Product;
import br.edu.ufcg.ccc.psoft.cccpharma.CCCPharma.model.product.category.*;

public class ProductController {

    private List<Product> availableProducts;
    private HashMap<String, List<Product>> unavailableProducts;
    private HashMap<String, Category> categories;

    public ProductController(){
        this.availableProducts = new ArrayList<Product>();
        this.categories = new HashMap<String, Category>();
        this.unavailableProducts = new HashMap<String, List<Product>>();
        
        this.categories.put("cosmetic", new Cosmetic());
        this.categories.put("food", new Food());
        this.categories.put("medication", new Medication());
        this.categories.put("toiletry", new Toiletry());
        this.unavailableProducts.put("outofstock", new ArrayList<Product>());
        this.unavailableProducts.put("outofdate", new ArrayList<Product>());
    }

    public void addProduct(String name, String barCode, String company, String categoryType, String status){
    	Category category = this.categories.get(categoryType);
        Product product = new Product(name, barCode, category, company, "Available");
        this.availableProducts.add(product);
    }

    public void addLot(int productAmount, Date shelfLife, String barcode){
        boolean exists = true;
        Product product = null;

        if (checkIfAvailable(barcode)){
            product = getAvailableProductByBarcode(barcode);
        } else if (checkIfOutOfDate(barcode)){
            product = getOutOfDateProductByName(barcode);
            getOutOfDate().remove(product);
            product.setStatus("Available");
            this.availableProducts.add(product);
        } else if (checkIfOutOfStock(barcode)){
            product = getOutOfStockProductByBarcode(barcode);
            getOutOfStock().remove(product);
            product.setStatus("Available");
            this.availableProducts.add(product);
        } else
            exists = false;

        if (exists){
            product.addLot(productAmount, shelfLife);
        } else {
            throw new IllegalArgumentException("Product is not registered");
        }
    }

    public void changeProductPrice(double price, String barcode){
        Product product = null;
        boolean exists = true;

        if (checkIfAvailable(barcode)){
            product = getAvailableProductByBarcode(barcode);
        } else if (checkIfOutOfStock(barcode)){
            product = getOutOfStockProductByBarcode(barcode);
        } else if (checkIfOutOfDate(barcode)){
            product = getOutOfDateProductByName(barcode);
        } else
            exists = false;

        if (exists){
            product.setPrice(price);
        } else
            throw new IllegalArgumentException("Product is not registered");
    }

    public void decreaseProductAmount(int amount, String barcode){
        if (checkIfAvailable(barcode)){
            getAvailableProductByBarcode(barcode).decreaseAmount(amount);
        } else{
            if (checkIfOutOfStock(barcode) || checkIfOutOfDate(barcode))
                throw new IllegalArgumentException("There is no lot of this product in stock");
            else
                throw new IllegalArgumentException("Product is not registered");
        }
    }
    
    public void changeCategoryDiscount(String categoryType, float discount) {
    	Category category = getCategory(categoryType);
    	category.setDiscount(discount);
    }
    
    public String getProductsInfo() {
    	String description = "";
    	for (Product product : this.availableProducts) {
    		description += product.partialInfo() + System.lineSeparator();
    	}
    	for (Product product : getOutOfDate()) {
    		description += product.partialInfo() + System.lineSeparator();
    	}
    	for (Product product : getOutOfStock()) {
    		description +=  product.partialInfo() + System.lineSeparator();
    	}
    	return description;
    }
    
    public String getInventoryReport() {
    	String description = "";
    	for (Product product : this.availableProducts) {
    		description += product.completeInfo() + System.lineSeparator();
    	}
    	for (Product product : getOutOfDate()) {
    		description += product.completeInfo() + System.lineSeparator();
    	}
    	for (Product product : getOutOfStock()) {
    		description +=  product.completeInfo() + System.lineSeparator();
    	}
    	return description;
    }
    
    private Category getCategory(String categoryType) {
    	Category category = this.categories.get(categoryType);
    	if (category != null)
    		return category;
    	else
    		throw new IllegalArgumentException("There is no such category in the system");
    }
    
    private boolean checkIfAvailable(String productName){
        boolean result = false;
        int i = 0;
        while (result == false && i < this.availableProducts.size()){
            Product product = this.availableProducts.get(i);
            if (product.getName().equals(productName))
                result = true;
            else
                i++;
        }
        return true;
    }

    private Product getAvailableProductByBarcode(String barcode){
        for (Product product : this.availableProducts) {
            if (product.getName().equals(barcode))
                return product;
        }
        return null;
    }

    private boolean checkIfOutOfStock(String barcode){
        List<Product> outOfStockProducts = getOutOfStock();
        boolean result = false;
        int i = 0;
        while (result == false && i < outOfStockProducts.size()){
            Product product = outOfStockProducts.get(i);
            if (product.getName().equals(barcode))
                result = true;
            else
                i++;
        }
        return true;
    }

    private Product getOutOfStockProductByBarcode(String barcode){
        List<Product> outOfStock = getOutOfStock();
        for (Product product : outOfStock) {
            if (product.getName().equals(barcode))
                return product;
        }
        return null;
    }

    private boolean checkIfOutOfDate(String barcode){
        List<Product> outOfDateProducts = getOutOfDate();
        boolean result = false;
        int i = 0;
        while (result == false && i < outOfDateProducts.size()){
            Product product = outOfDateProducts.get(i);
            if (product.getName().equals(barcode))
                result = true;
            else
                i++;
        }
        return true;
    }

    private Product getOutOfDateProductByName(String barcode){
        List<Product> outOfDate = getOutOfDate();
        for (Product product : outOfDate) {
            if (product.getName().equals(barcode))
                return product;
        }
        return null;
    }

    private List<Product> getOutOfStock(){
        return this.unavailableProducts.get("outofstock");
    }

    private List<Product> getOutOfDate(){
        return this.unavailableProducts.get("outofdate");
    }

}