package application;

import java.util.ArrayList;

public class ItemList {
	//Final Variable
	private	static final double TAXPERC = 1.13; //***Assuming we're in Ontario, Canada

	//Field Variables
	private ArrayList<Item> custOrder = new ArrayList<Item>();

	//Constructors
	public ItemList() {
	}

	//Other Methods
	public double calculateTotal(ArrayList<Item> list){
		double total = 0;

		for(int i = 0; i < list.size(); i++){
			Item temp = list.get(i);
			total += temp.getPrice()*temp.getQuantity();
		}

		return total*TAXPERC;
	}

	//Getters
	public ArrayList<Item> getCustOrder(){
		return custOrder;
	}

}
