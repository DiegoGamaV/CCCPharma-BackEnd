package br.edu.ufcg.ccc.psoft.cccpharma.CCCPharma.model.product.category;

import br.edu.ufcg.ccc.psoft.cccpharma.CCCPharma.model.product.discount.*;

public abstract class Category {

    protected Discount discount;

    public double getDiscount(){
        return discount.getDiscount();
    }

    public void setDiscount(double discount){
        if (discount == 0.0)
            this.discount = new NoDiscount();
        else if (discount == 0.10)
            this.discount = new GoodDiscount();
        else if (discount == 0.25)
            this.discount = new GreatDiscount();
        else if (discount == 0.50)
            this.discount = new SuperDiscount();
        else
            throw new IllegalArgumentException("discount type not defined");
    }

}