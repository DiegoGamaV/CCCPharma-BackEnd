package model.product.discount;

public class QuarterDiscount implements Discount{
    public QuarterDiscount(){
    }

    public double getDiscount(){
        return (float) 0.25;
    }
}
