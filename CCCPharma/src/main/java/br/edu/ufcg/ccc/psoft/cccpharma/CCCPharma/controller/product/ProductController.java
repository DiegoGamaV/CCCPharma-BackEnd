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

    public void addLot(int productAmount, Date shelfLife, String productName){
        boolean exists = true;
        Product product = null;

        if (checkIfAvailable(productName)){
            product = getAvailableProductByName(productName);
        } else if (checkIfOutOfDate(productName)){
            product = getOutOfDateProductByName(productName);
            getOutOfDate().remove(product);
            product.setStatus("Available");
            this.availableProducts.add(product);
        } else if (checkIfOutOfStock(productName)){
            product = getOutOfStockProductByName(productName);
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

    public void changeProductPrice(double price, String productName){
        Product product = null;
        boolean exists = true;

        if (checkIfAvailable(productName)){
            product = getAvailableProductByName(productName);
        } else if (checkIfOutOfStock(productName)){
            product = getOutOfStockProductByName(productName);
        } else if (checkIfOutOfDate(productName)){
            product = getOutOfDateProductByName(productName);
        } else
            exists = false;

        if (exists){
            product.setPrice(price);
        } else
            throw new IllegalArgumentException("Product is not registered");
    }

    public void decreaseProductAmount(int amount, String productName){
        if (checkIfAvailable(productName)){
            getAvailableProductByName(productName).decreaseAmount(amount);
        } else{
            if (checkIfOutOfStock(productName) || checkIfOutOfDate(productName))
                throw new IllegalArgumentException("There is no lot of this product in stock");
            else
                throw new IllegalArgumentException("Product is not registered");
        }
    }
    
    public void changeCategoryDiscount(String categoryType, float discount) {
    	Category category = getCategory(categoryType);
    	category.setDiscount(discount);
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

    private Product getAvailableProductByName(String productName){
        for (Product product : this.availableProducts) {
            if (product.getName().equals(productName))
                return product;
        }
        return null;
    }

    private boolean checkIfOutOfStock(String productName){
        List<Product> outOfStockProducts = getOutOfStock();
        boolean result = false;
        int i = 0;
        while (result == false && i < outOfStockProducts.size()){
            Product product = outOfStockProducts.get(i);
            if (product.getName().equals(productName))
                result = true;
            else
                i++;
        }
        return true;
    }

    private Product getOutOfStockProductByName(String productName){
        List<Product> outOfStock = getOutOfStock();
        for (Product product : outOfStock) {
            if (product.getName().equals(productName))
                return product;
        }
        return null;
    }

    private boolean checkIfOutOfDate(String productName){
        List<Product> outOfDateProducts = getOutOfDate();
        boolean result = false;
        int i = 0;
        while (result == false && i < outOfDateProducts.size()){
            Product product = outOfDateProducts.get(i);
            if (product.getName().equals(productName))
                result = true;
            else
                i++;
        }
        return true;
    }

    private Product getOutOfDateProductByName(String productName){
        List<Product> outOfDate = getOutOfDate();
        for (Product product : outOfDate) {
            if (product.getName().equals(productName))
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