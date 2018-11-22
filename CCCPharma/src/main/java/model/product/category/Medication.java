package model.product.category;

import model.product.discount.NoDiscount;

public class Medication extends Category {

    public Medication(){
        this.discount = new NoDiscount();
    }

    @Override
    public String toString(){
        return "Medication";
    }

}
