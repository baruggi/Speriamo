package mensa;

public class Menu {
	private String primo,secondo,frutta,dolce;
	
	public Menu(String primo, String secondo, String frutta, String dolce) {
	this.primo = primo;
	this.secondo= secondo;
	this.frutta = frutta;
	this.dolce = dolce;
		}

	public Menu() {
		// TODO Auto-generated constructor stub
	}

	public String getPrimo() {
		return primo;
	}

	public void setPrimo(String primo) {
		this.primo = primo;
	}

	public String getSecondo() {
		return secondo;
	}

	public void setSecondo(String secondo) {
		this.secondo = secondo;
	}

	public String getFrutta() {
		return frutta;
	}

	public void setFrutta(String frutta) {
		this.frutta = frutta;
	}

	public String getDolce() {
		return dolce;
	}

	public void setDolce(String dolce) {
		this.dolce = dolce;
	}


}
