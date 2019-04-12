package application;

import java.util.ArrayList;

public class Item {

	//Field Variables
	private String _name;											 //Name of the Item
	private double _price;											 //Price of the item
	private int _quantity;                                           //The quantity of the item ordered
	private boolean _isPizza;										 //Whether the item is a pizza item or not
	private ArrayList<String> _toppings = new ArrayList<String>();   //Array of toppings

	//Constructors
	public Item(){}

	public Item(String name, double price, boolean isPizza){
		_name = name;
		_price = price;
		_isPizza = isPizza;
	}

	//Other Methods
	@Override
	public String toString(){
		if(_isPizza){
			return _name + " for " + String.format("$%.2f", _price) +
				   "\nToppings: "+ _toppings + "\n\n";
		}else {
			return _name + " for $" + _price;
		}

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

	public ArrayList<String> getToppings(){
		return _toppings;
	}

	public boolean getIsPizza(){
		return _isPizza;
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
