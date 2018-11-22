package model.product.category;

import model.product.discount.*;

public abstract class Category {

    protected Discount discount;

    public double getDiscount(){
        return discount.getDiscount();
    }

    public void setDiscount(double discount){
        if (discount == 0.0)
            this.discount = new NoDiscount();
        else if (discount == 0.10)
            this.discount = new TenthDiscount();
        else if (discount == 0.25)
            this.discount = new QuarterDiscount();
        else if (discount == 0.50)
            this.discount = new HalfDiscount();
        else
            throw new IllegalArgumentException("discount type not defined");
    }

}
