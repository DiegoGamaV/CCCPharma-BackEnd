package br.edu.ufcg.ccc.psoft.cccpharma.CCCPharma.model.product.discount;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "super_discount")
public class SuperDiscount implements Discount{
    public SuperDiscount(){
    }

    public double getDiscount(){
        return (float) 0.5;
    }
}