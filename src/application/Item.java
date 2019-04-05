package application;

public class Item {

	//Field Variables
	private String _name;
	private double _price;
	private int _quantity;

	//Constructors
	public Item(String name, double price){
		_name = name;
		_price = price;
	}

	//Other Methods
	public String toString(){
		return "\n" + _name + " for $" + _price;
	}

	//Getters
	public String getName() {
		return _name;
	}

	public double getPrice() {
		return _price;
	}

	public int getQuantity(){
		return _quantity;
	}

	//Setters
	public void setName(String name) {
		_name = name;
	}

	public void setPrice(double price) {
		_price = price;
	}

	public void setQuantity(int quantity){
		_quantity = quantity;
	}
}
