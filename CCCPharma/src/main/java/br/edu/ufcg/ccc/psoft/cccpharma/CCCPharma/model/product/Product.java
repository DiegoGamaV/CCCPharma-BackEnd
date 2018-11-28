package br.edu.ufcg.ccc.psoft.cccpharma.CCCPharma.model.product;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.edu.ufcg.ccc.psoft.cccpharma.CCCPharma.model.lot.Lot;
import br.edu.ufcg.ccc.psoft.cccpharma.CCCPharma.model.product.category.*;

public class Product {
    private String name;
    private String barCode;
    private String company;
    private Category category;
    private Status status;
    private double price;
    private List<Lot> lots;

    public Product(String name, String barCode, String category, String company, String status ){
        this.name = name;
        this.barCode = barCode;
        this.company = company;
        this.price = 0.0;
        this.lots = new ArrayList<Lot>();
        setStatus(status);
        setCategory(category);
    };

    public double getPrice() {
        return (this.price - this.price * this.category.getDiscount());
    }

    public void setPrice(double price) {
        if (price > 0)
        	this.price = price;
        else
        	throw new IllegalArgumentException("Cannot assign a price of zero to a product");
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBarCode() {
        return this.barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getCompany() {
        return this.company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setStatus(String status){
        if (status.toLowerCase().equals("available"))
            this.status = Status.Available;
        else if (status.toLowerCase().equals("unavailable"))
            this.status = Status.Unavailable;
        else
            throw new IllegalArgumentException("Status value not defined");
    }

    public String getStatus(){
        if (this.status.equals(Status.Available))
            return "Available";
        else
            return "Unavailable";
    }

    public void setCategory(String category){
        switch (category.toLowerCase()){
            case "cosmetic":
                this.category = new Cosmetic();
                break;
            case "medication":
                this.category = new Medication();
                break;
            case "food":
                this.category = new Food();
                break;
            case "toiletry":
                this.category = new Toiletry();
                break;
            default:
                throw new IllegalArgumentException("category not defined");
        }
    }

    public String getCategory(){
        return this.category.toString();
    }
    
    public void addLot(int amount, Date shelfLife) {
    	this.lots.add(new Lot(amount, shelfLife));
    }
    
    private void ensureLotsNormality() {
    	for (Lot lot : this.lots) {
    		if (lot.isOutOfDate() || lot.isOutOfStock())
    			this.lots.remove(lot);
    	}
    }
    
    public int getAmount() {
    	int amount = 0;
    	int i = 0;
    	while (i < this.lots.size()) {
    		amount += this.lots.get(i).getAmount();
    	}
    	return amount;
    }
    
    public void decreaseAmount(int amount) {
    	ensureLotsNormality();
    	
    	int totalAmount = getAmount();
    	if (totalAmount - amount > 0) {
    		int i = 0;
    		while (amount > 0 && i < this.lots.size()) {
    			Lot closestToOutOfDate = getClosestToShelfLife();
    			if(closestToOutOfDate.getAmount() <= amount) {
    				amount -= closestToOutOfDate.getAmount();
    				this.lots.remove(closestToOutOfDate);
    				i++;
    			} else {
    				int auxliaryAmount = amount;
    				amount = 0;
    				closestToOutOfDate.decreaseAmount(auxliaryAmount);
    			}
    		}
    	} else if (totalAmount - amount == 0) {
    		this.lots = new ArrayList<Lot>();
    	} else
    		throw new IllegalArgumentException("Amount is bigger than products in stock");
    }
    
    private Lot getClosestToShelfLife() {
    	if (this.lots.size() > 0) {    		
    		Lot closestShelfLifeLot = this.lots.get(0);
    		int i = 0;
    		while (i < this.lots.size()) {
    			Lot lot = this.lots.get(i);
    			if (lot.getShelfLife().before(closestShelfLifeLot.getShelfLife())) {
    				closestShelfLifeLot = lot;
    			}
    		}
    		return closestShelfLifeLot;
    	} else
    		return null;
    }

    public String toString(){
        String description = "product: " + this.name
                         + "; Company: " + this.company
                         + "; Barcode: " + this.barCode
                         + "; Category: " + this.category.toString();

        if (this.status.equals(Status.Available)) {
            description += "; Price: " + price;
            description += "; Availability: Available";
        } else
            description += "; Availability: Unavailable";

        return description;
    }

}