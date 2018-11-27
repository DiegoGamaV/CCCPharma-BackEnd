package br.edu.ufcg.ccc.psoft.cccpharma.CCCPharma.controller.product;

import java.util.*;
import br.edu.ufcg.ccc.psoft.cccpharma.CCCPharma.model.lot.Lot;
import br.edu.ufcg.ccc.psoft.cccpharma.CCCPharma.model.product.Product;

public class ProductController {

    private HashMap<Integer, Lot> lots;
    private HashMap<String, String> removedLots;
    private List<Product> availableProducts;
    private HashMap<String, List<Product>> unavailableProducts;

    public ProductController(){
        this.lots = new HashMap<Integer, Lot>();
        this.removedLots = new HashMap<String, String>();
        this.availableProducts = new ArrayList<Product>();
        this.unavailableProducts = new HashMap<String, List<Product>>();

        this.unavailableProducts.put("OutOfStock", new ArrayList<Product>());
        this.unavailableProducts.put("OutOfDate", new ArrayList<Product>());
    }

    public void addProduct(String name, String barCode, String company, String category, String status){
        Product product = new Product(name, barCode, category, company, "available");
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
            Lot lot = new Lot(product, productAmount, shelfLife);
            this.lots.put(generateNewKey(), lot);
        } else {
            throw new IllegalArgumentException("Product is not registered");
        }
    }

    public void changeProductPrice(double price, String productName){
        ensureNormality();

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
        ensureNormality();

        if (checkIfAvailable(productName)){
            List<Lot> lots = getLotsByProduct(productName);
            Lot lowestLot = getWithLowestAmount(lots);
            lowestLot.decreaseAmount(amount);
        } else{
            if (checkIfOutOfStock(productName) || checkIfOutOfDate(productName))
                throw new IllegalArgumentException("There is no lot of this product in stock");
            else
                throw new IllegalArgumentException("Product is not registered");
        }
    }

    public void ensureNormality(){
        for (Product product : availableProducts){
            removeOutOfStockLots(product.getName());
            removeOutOfDateLots(product.getName());
        }
        List<Product> outOfDate = getOutOfDate();
        List<Product> outOfStock = getOutOfStock();
        for (Product product : outOfDate){
            product.setStatus("Unavailable");
        }
        for (Product product : outOfStock){
            product.setStatus("Unavailable");
        }
    }

    private Integer generateNewKey(){
        return getBiggestKey() + 1;
    }

    private Integer getBiggestKey(){
        Set<Integer> keys = this.lots.keySet();
        Integer biggest = null;
        if (keys.size() == 0){
            biggest = -1;
        } else {
            biggest = Integer.MIN_VALUE;
            for (Integer key : keys){
                if (biggest <= key)
                    biggest = key;
            }
        }
        return biggest;
    }

    private void removeOutOfStockLots(String productName){
        Collection<Lot> lots = this.lots.values();
        for (Lot lot : lots){
            if (lot.isOutOfStock() && lot.getProductName().equals(productName)){
                lots.remove(lot);
                this.removedLots.put(lot.toString(), "Reason: OutOfStock");
            }
        }
    }

    private void removeOutOfDateLots(String productName){
        Collection<Lot> lots = this.lots.values();
        for (Lot lot : lots){
            if (lot.isOutOfDate() && lot.getProductName().equals(productName)){
                lots.remove(lot);
                this.removedLots.put(lot.toString(), "Reason: OutOfDate");
            }
        }
    }

    private Lot getWithLowestAmount(List<Lot> lots){
        Lot lowestLot = new Lot(null, Integer.MAX_VALUE, new Date());
        for (Lot lot : lots){
            if (lot.getAmount() < lowestLot.getAmount())
                lowestLot = lot;
        }
        return lowestLot;
    }

    private List<Lot> getLotsByProduct(String productName){
        Collection<Lot> allLots = this.lots.values();
        List<Lot> lotsWithRequiredProduct = new ArrayList<Lot>();
        for (Lot lot : allLots){
            if (lot.getProductName().equals(productName))
                lotsWithRequiredProduct.add(lot);
        }

        return lotsWithRequiredProduct;
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
        return this.unavailableProducts.get("OutOfStock");
    }

    private List<Product> getOutOfDate(){
        return this.unavailableProducts.get("OutOfDate");
    }

}