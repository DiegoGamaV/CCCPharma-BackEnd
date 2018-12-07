package br.edu.ufcg.ccc.psoft.cccpharma.CCCPharma.model.product.discount;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "good_discount")
public class GoodDiscount implements Discount{

    public GoodDiscount(){
    }

    public double getDiscount(){
        return (float) 0.10;
    }
    
}