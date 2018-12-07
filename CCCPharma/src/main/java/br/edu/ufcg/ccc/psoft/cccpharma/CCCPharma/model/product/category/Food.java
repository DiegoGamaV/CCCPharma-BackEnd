package br.edu.ufcg.ccc.psoft.cccpharma.CCCPharma.model.product.category;

import javax.persistence.Entity;
import javax.persistence.Table;

import br.edu.ufcg.ccc.psoft.cccpharma.CCCPharma.model.product.discount.NoDiscount;

@Entity
@Table(name = "category")
public class Food extends Category {

    public Food(){
        this.discount = new NoDiscount();
    }

    @Override
    public String toString(){
        return "Alimento";
    }

}