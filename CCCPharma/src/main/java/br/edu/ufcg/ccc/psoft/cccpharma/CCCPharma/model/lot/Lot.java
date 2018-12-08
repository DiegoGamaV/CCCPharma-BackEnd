package br.edu.ufcg.ccc.psoft.cccpharma.CCCPharma.model.lot;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.*;

import br.edu.ufcg.ccc.psoft.cccpharma.CCCPharma.customExceptions.client400.BadRequest400Exception;
import br.edu.ufcg.ccc.psoft.cccpharma.CCCPharma.model.product.Product;

@Entity
@Table(name = "lot")
public class Lot {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "amount", nullable = false)
    private int amount;
    
	@Column(name = "shelf_life", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date shelfLife;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "barcode")
    private Product product;

    public Lot(int amount, Date shelfLife) throws BadRequest400Exception{
        if (amount > 0)
            this.amount = amount;
        else
            throw new BadRequest400Exception("Amount cannot be negative or zero");
        this.shelfLife = shelfLife;
        if(!checkShelfLife())
            throw new BadRequest400Exception("Shelf life cannot be before or equal to current time");
    }

    public int getAmount() {
        return this.amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void decreaseAmount(int amount){
        this.amount -= amount;
    }

    public Date getShelfLife(){
        return this.shelfLife;
    }

    public String getShelfLifeInfo() {
        String pattern = "dd-MM-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(shelfLife);
    }

    public void setShelfLife(Date shelfLife) {
        this.shelfLife = shelfLife;
    }

    public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public boolean isOutOfStock(){
        return amount == 0;
    }

    public boolean isOutOfDate(){
        return checkShelfLife();
    }

    private boolean checkShelfLife() {
        Date currentTime = new Date();
        return this.shelfLife.after(currentTime);
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Lot other = (Lot) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
    public String toString(){
        return "VALIDADE: " + getShelfLifeInfo() + "; " + "Nº DE ITENS: " + this.amount;
    }
}