package br.edu.ufcg.ccc.psoft.cccpharma.CCCPharma.model.category.discount;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "great_discount")
public class GreatDiscount implements Discount{
    public GreatDiscount(){
    }

    public double getDiscount(){
        return (float) 0.25;
    }
}