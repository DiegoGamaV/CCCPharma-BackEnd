package br.edu.ufcg.ccc.psoft.cccpharma.CCCPharma.model.category.discount;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public interface Discount {
    public double getDiscount();
}