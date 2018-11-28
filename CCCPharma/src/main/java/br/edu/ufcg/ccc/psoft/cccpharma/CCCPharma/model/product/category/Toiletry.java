package br.edu.ufcg.ccc.psoft.cccpharma.CCCPharma.model.product.category;

import br.edu.ufcg.ccc.psoft.cccpharma.CCCPharma.model.product.discount.NoDiscount;

public class Toiletry extends Category {

    public Toiletry(){
        this.discount = new NoDiscount();
    }

    @Override
    public String toString(){
        return "Higiene Pessoal";
    }

}