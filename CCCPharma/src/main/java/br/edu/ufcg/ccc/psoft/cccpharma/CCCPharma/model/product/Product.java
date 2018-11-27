package br.edu.ufcg.ccc.psoft.cccpharma.CCCPharma.model.product;

import br.edu.ufcg.ccc.psoft.cccpharma.CCCPharma.model.product.category.*;

public class Product {
    private String name;
    private String barCode;
    private String company;
    private Category category;
    private Status status;
    private double price;

    public Product(String name, String barCode, String category, String company, String status ){
        this.name = name;
        this.barCode = barCode;
        this.company = company;
        this.price = 0.0;
        setStatus(status);
        setCategory(category);
    };

    public double getPrice() {
        return (this.price - this.price * this.category.getDiscount());
    }

    public void setPrice(double price) {
        this.price = price;
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

    private boolean isPriceNotNegative(){
        return this.price >= 0.0;
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