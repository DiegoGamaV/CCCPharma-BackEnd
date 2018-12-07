package br.edu.ufcg.ccc.psoft.cccpharma.CCCPharma.model.product.discount;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public interface Discount {
    public double getDiscount();
}