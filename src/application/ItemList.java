package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ItemList {
	//Final Variable
	private	static final double TAXPERC = 1.13; //***Assuming we're in Ontario, Canada

	//Field Variables
	private ArrayList<Item> menuItems = new ArrayList<Item>(); //ArrayList of menu Items from MenuItems.txt
	private ArrayList<Item> custOrder = new ArrayList<Item>(); //ArrayList of the customers order

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

	public void loadMenuItems(){
		//Retrieve data from MenuItems.txt and store into menuItems array
			Scanner reader = null;
			try {
				File fileDescriptor = new File("data/MenuItems.txt");
				reader = new Scanner(fileDescriptor);

				while (reader.hasNext()) {
					String record = reader.nextLine();

					//Split String into separate fields
					String[] fields = record.split(",");
					String itemName = fields[0];                        // item name
					double itemPrice = Double.parseDouble(fields[1]);   // item price

					Item pNew = new Item(itemName, itemPrice);
					menuItems.add(pNew);

				}
				//System.out.println(menuItems);

			} catch (FileNotFoundException e) {
				System.out.println("Error in your MenuItems.txt");
				//e.printStackTrace(); //   Add error recovery here if needed

			}finally {
				if (reader != null) {
					reader.close();
				}
			}
	}

	//Getters
	public ArrayList<Item> getCustOrder(){
		return custOrder;
	}

	public ArrayList<Item> getMenuItems(){
		return menuItems;
	}

}
