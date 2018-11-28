package br.edu.ufcg.ccc.psoft.cccpharma.CCCPharma.model.product.category;

import br.edu.ufcg.ccc.psoft.cccpharma.CCCPharma.model.product.discount.NoDiscount;

public class Food extends Category {

    public Food(){
        this.discount = new NoDiscount();
    }

    @Override
    public String toString(){
        return "Alimento";
    }

}