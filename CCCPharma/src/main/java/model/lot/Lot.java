package model.lot;

import model.product.Product;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Lot {
    private Product product;
    private int amount;
    private Date shelfLife;

    public Lot(Product product, int amount, Date shelfLife){
        this.product = product;
        if (isPositive(amount))
            this.amount = amount;
        else
            throw new IllegalArgumentException("Amount cannot be negative or zero");
        this.shelfLife = shelfLife;
        if(!checkShelfLife())
            throw new IllegalArgumentException("Shelf life cannot be before or equal to current time");
    }

    public String getProductName() {
        return getProduct().getName();
    }

    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getAmount() {
        return this.amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void decreaseAmount(int amount){
        if (isPositive(this.amount)) {
            this.amount = this.amount - amount;
            if (isPositive(this.amount))
                this.amount = 0;
        }
        else
            throw new IllegalArgumentException("Lot amount is already 0");
    }

    private boolean isPositive(int amount){
        return amount > 0;
    }

    public Date getShelfLife(){
        return this.shelfLife;
    }

    public String getShelfLifeInfo() {
        String pattern = "dd-MM-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(shelfLife);
    }

    public void setShelfLife(Date shelfLife) {
        this.shelfLife = shelfLife;
    }

    public boolean isOutOfStock(){
        return amount == 0;
    }

    public boolean isOutOfDate(){
        return checkShelfLife();
    }

    private boolean checkShelfLife() {
        Date currentTime = new Date();
        return this.shelfLife.after(currentTime);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Lot)) return false;
        Lot lot = (Lot) o;
        return amount == lot.amount &&
                Objects.equals(product, lot.product) &&
                Objects.equals(shelfLife, lot.shelfLife);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, amount, shelfLife);
    }

    @Override
    public String toString(){
        return "Product: " + this.product.getName() + "; Lot amount: " + this.amount + "; " + getShelfLifeInfo();
    }
}
