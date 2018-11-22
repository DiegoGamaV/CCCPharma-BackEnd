package model.product.category;

import model.product.discount.NoDiscount;

public class Toiletry extends Category {

    public Toiletry(){
        this.discount = new NoDiscount();
    }

    @Override
    public String toString(){
        return "Toiletry";
    }

}
