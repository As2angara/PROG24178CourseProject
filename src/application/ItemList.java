package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class ItemList {
	//Final Variable
	static final double TAXPERC = 0.13; //***Assuming we're in Ontario, Canada

	//Field Variables
	private ArrayList<Item> menuItems = new ArrayList<Item>();         //ArrayList of menu Items from MenuItems.txt
	private ArrayList<Item> custOrder = new ArrayList<Item>();         //ArrayList of the customers order
	private ArrayList<Item> custOrderPizza = new ArrayList<Item>();    //ArrayList of the customers pizza order
	private ArrayList<String> pizzaToppings = new ArrayList<String>(); //Array of pizza toppings strings

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

		return total;
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
				String itemName = fields[0];                          // item name
				double itemPrice = Double.parseDouble(fields[1]);     // item price
				boolean itemIsPizza = Boolean.parseBoolean(fields[2]);// item is pizza or not

				Item pNew = new Item(itemName, itemPrice,itemIsPizza);
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

	public void loadToppings(){
		//Retrieve data from MenuItems.txt and store into menuItems array
		Scanner reader = null;
		try {
			File fileDescriptor = new File("data/ToppingStrings.txt");
			reader = new Scanner(fileDescriptor);

			while (reader.hasNext()) {
				String record = reader.nextLine();

				//Split String into separate fields
				String[] fields = record.split(",");
				String toppingName = fields[0];      //Topping name

				pizzaToppings.add(toppingName);

			}
			//System.out.println(pizzaToppings);

		} catch (FileNotFoundException e) {
			System.out.println("Error in your MenuItems.txt");
			//e.printStackTrace(); //   Add error recovery here if needed

		}finally {
			if (reader != null) {
				reader.close();
			}
		}
	}

	public void saveCustOrder(){

		PrintWriter writer = null;

		try {
			File fileDescriptor = new File("data/CustomerOrder.txt");
			writer = new PrintWriter(fileDescriptor);

			for(int i = 0; i < custOrder.size(); i++){
				Item orderedItem = custOrder.get(i);

				if(orderedItem.getIsPizza()){
					writer.println(
							orderedItem.getQuantity() + "," +
							orderedItem.getName() + "," +
							orderedItem.getPrice() + "," +
							orderedItem.getIsPizza() + "," +
							orderedItem.getToppings());
				}else {
					writer.println(
							orderedItem.getQuantity() + "," +
							orderedItem.getName() + "," +
							orderedItem.getPrice() + "," +
							orderedItem.getIsPizza());
				}


			}

		}catch(FileNotFoundException e) {
			e.printStackTrace(); // Add error recovery here if needed

		}finally {
			if (writer != null) {
				writer.close();
			}
		}
	}

	//Getters
	public ArrayList<Item> getCustOrder(){
		return custOrder;
	}

	public ArrayList<Item> getCustOrderPizza(){
		return custOrderPizza;
	}

	public ArrayList<Item> getMenuItems(){
		return menuItems;
	}

	public ArrayList<String> getPizzaToppings(){
		return pizzaToppings;
	}

}
