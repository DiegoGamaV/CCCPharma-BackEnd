package model.product.category;

import model.product.discount.NoDiscount;

public class Cosmetic extends Category{

    public Cosmetic(){
        this.discount = new NoDiscount();
    }

    @Override
    public String toString(){
        return "Cosmetic";
    }

}
