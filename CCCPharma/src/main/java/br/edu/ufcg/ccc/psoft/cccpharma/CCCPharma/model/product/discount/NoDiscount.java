package br.edu.ufcg.ccc.psoft.cccpharma.CCCPharma.model.product.discount;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "no_discount")
public class NoDiscount implements Discount{

    public NoDiscount(){
    }

    public double getDiscount(){
        return (float) 0.0;
    }
}