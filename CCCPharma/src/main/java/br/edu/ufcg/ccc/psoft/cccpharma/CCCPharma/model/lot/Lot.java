package br.edu.ufcg.ccc.psoft.cccpharma.CCCPharma.model.lot;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Lot {
    private int amount;
    private Date shelfLife;

    public Lot(int amount, Date shelfLife){
        if (amount > 0)
            this.amount = amount;
        else
            throw new IllegalArgumentException("Amount cannot be negative or zero");
        this.shelfLife = shelfLife;
        if(!checkShelfLife())
            throw new IllegalArgumentException("Shelf life cannot be before or equal to current time");
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
		result = prime * result + amount;
		result = prime * result + ((shelfLife == null) ? 0 : shelfLife.hashCode());
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
		if (amount != other.amount)
			return false;
		if (shelfLife == null) {
			if (other.shelfLife != null)
				return false;
		} else if (!shelfLife.equals(other.shelfLife))
			return false;
		return true;
	}

	@Override
    public String toString(){
        return "VALIDADE: " + getShelfLifeInfo() + "; " + "Nº DE ITENS: " + this.amount;
    }
}