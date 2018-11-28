package br.edu.ufcg.ccc.psoft.cccpharma.CCCPharma.model.product.category;

import br.edu.ufcg.ccc.psoft.cccpharma.CCCPharma.model.product.discount.NoDiscount;

public class Medication extends Category {

    public Medication(){
        this.discount = new NoDiscount();
    }

    @Override
    public String toString(){
        return "Medicamento";
    }

}