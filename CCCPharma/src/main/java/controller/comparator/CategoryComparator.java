package controller.comparator;

import model.product.Product;

public class CategoryComparator implements ProductComparator {

    @Override
    public int compare(Product o1, Product o2) {
        return o1.getCategory().compareTo(o2.getCategory());
    }

}
