package br.edu.ufcg.ccc.psoft.cccpharma.CCCPharma.model.product.category;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

import br.edu.ufcg.ccc.psoft.cccpharma.CCCPharma.model.product.Product;
import br.edu.ufcg.ccc.psoft.cccpharma.CCCPharma.model.product.discount.*;

@MappedSuperclass
public abstract class Category {

	@Id
	protected Long id;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "category")
    protected Discount discount;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "barcode", nullable = false)
    protected Product product;
    
    public double getDiscount(){
        return discount.getDiscount();
    }

    public void setDiscount(double discount){
        if (discount == 0.0)
            this.discount = new NoDiscount();
        else if (discount == 0.10)
            this.discount = new GoodDiscount();
        else if (discount == 0.25)
            this.discount = new GreatDiscount();
        else if (discount == 0.50)
            this.discount = new SuperDiscount();
        else
            throw new IllegalArgumentException("discount type not defined");
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((discount == null) ? 0 : discount.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Category other = (Category) obj;
		if (discount == null) {
			if (other.discount != null)
				return false;
		} else if (!discount.equals(other.discount))
			return false;
		return true;
	}

}