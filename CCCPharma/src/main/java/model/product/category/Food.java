package model.product.category;

import model.product.discount.NoDiscount;

public class Food extends Category {

    public Food(){
        this.discount = new NoDiscount();
    }

    @Override
    public String toString(){
        return "Food";
    }

}
