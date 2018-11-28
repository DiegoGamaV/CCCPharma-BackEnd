package br.edu.ufcg.ccc.psoft.cccpharma.CCCPharma.model.product.discount;

public class SuperDiscount implements Discount{
    public SuperDiscount(){
    }

    public double getDiscount(){
        return (float) 0.5;
    }
}