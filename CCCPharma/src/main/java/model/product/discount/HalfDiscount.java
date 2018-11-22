package model.product.discount;

public class HalfDiscount implements Discount{
    public HalfDiscount(){
    }

    public double getDiscount(){
        return (float) 0.5;
    }
}
