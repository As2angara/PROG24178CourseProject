package application;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CustomPizzaController {

	//CheckBoxes for pizza options
	@FXML private CheckBox chkSmall;
	@FXML private CheckBox chkMed;
	@FXML private CheckBox chkLarge;
	private CheckBox[] checkList;

	//CheckBoxes for Pizza Toppings
	@FXML private CheckBox chkTop1;
	@FXML private CheckBox chkTop2;
	@FXML private CheckBox chkTop3;
	@FXML private CheckBox chkTop4;
	@FXML private CheckBox chkTop5;
	@FXML private CheckBox chkTop6;
	@FXML private CheckBox chkTop7;
	@FXML private CheckBox chkTop8;
	@FXML private CheckBox chkTop9;
	@FXML private CheckBox chkTop10;
	@FXML private CheckBox chkTop11;
	private CheckBox[] topCheckList;

	//Other FXML handles
	@FXML private Pane paneToppings;
	@FXML private Button btnAddOrder;
	@FXML private Button btnCheckout;
	@FXML private TextArea txtPizzaOrder;

	//Create ItemList Object
	private ItemList list = MainController.list;

	//Field Variables
	private int numToppings = 0;

	@FXML private void initialize(){
		//Load Toppings
		list.loadToppings();

		//Populate checklist arrays
		checkList = new CheckBox[]{chkSmall, chkMed, chkLarge};
		topCheckList = new CheckBox[]{chkTop1, chkTop2, chkTop3, chkTop4,
				                      chkTop5, chkTop6, chkTop7, chkTop8,
				                      chkTop9, chkTop10, chkTop11};

		//Handlers for pizza types check boxes
		chkSmall.setOnAction(e -> {onListChecked(0);});
		chkMed.setOnAction(e -> {onListChecked(1);});
		chkLarge.setOnAction(e -> {onListChecked(2);});

		//Handlers for pizza toppings check boxes
		chkTop1.setOnAction(e -> {onToppingsChecked(0);});
		chkTop2.setOnAction(e -> {onToppingsChecked(1);});
		chkTop3.setOnAction(e -> {onToppingsChecked(2);});
		chkTop4.setOnAction(e -> {onToppingsChecked(3);});
		chkTop5.setOnAction(e -> {onToppingsChecked(4);});
		chkTop6.setOnAction(e -> {onToppingsChecked(5);});
		chkTop7.setOnAction(e -> {onToppingsChecked(6);});
		chkTop8.setOnAction(e -> {onToppingsChecked(7);});
		chkTop9.setOnAction(e -> {onToppingsChecked(8);});
		chkTop10.setOnAction(e -> {onToppingsChecked(9);});
		chkTop11.setOnAction(e -> {onToppingsChecked(10);});

		//Others
		btnAddOrder.setOnAction(e -> {onAddOrderClicked();});
		btnCheckout.setOnAction(e -> {onCheckClicked();});

	}

	public void onToppingsChecked(int index){
		//call toppings counter method
		int numToppingsChosen = getNumToppingsSelected();

		//Disable/Enable the other pizza toppings options
		if(numToppingsChosen >= numToppings){
			for(int i = 0; i < topCheckList.length; i++){
				if(topCheckList[i].isSelected()){
					continue;
				}else {
					topCheckList[i].setDisable(true);
				}
			}
		}else {
			for(int i = 0; i < topCheckList.length; i++){
				topCheckList[i].setDisable(false);
			}
		}
	}

	public int getNumToppingsSelected(){
		int numToppingsChosen = 0;

		for(int i = 0; i < topCheckList.length; i++){
			if(topCheckList[i].isSelected()){
				numToppingsChosen++;
			}
		}
		return numToppingsChosen;
	}

	public void onListChecked(int index){
		//Reset toppings checkboxes
		for(int i = 0; i < topCheckList.length; i++){
			topCheckList[i].setSelected(false);
			topCheckList[i].setDisable(false);
		}


		//Disable the other pizza options check boxes
		if(checkList[index].isSelected()){
			for(int i = 0; i < checkList.length; i++){
				if(i == index){
					continue;
				}else {
					checkList[i].setDisable(true);
					paneToppings.setDisable(false);
					btnAddOrder.setDisable(false);
				}
			}
		}else {
			for(int i = 0; i < checkList.length; i++){
				checkList[i].setDisable(false);
				paneToppings.setDisable(true);
				btnAddOrder.setDisable(true);
			}
		}

		//Set the number of toppings to the corresponding checkbox clicked
		numToppings = index + 1;

	}

	public void onAddOrderClicked(){
		//Determine which menu item selected and add them into customer order array
		ArrayList<Item> custOrderPizza = list.getCustOrderPizza();
		Item order = null;

		for(int i = 0; i < checkList.length; i++){
			if(checkList[i].isSelected()){
				switch(i){
				case 0:
					order = new Item("Small Pizza", 5.00, true);
					break;
				case 1:
					order = new Item("Medium Pizza", 10.00, true);
					break;
				case 2:
					order = new Item("Large Pizza", 12.50, true);
					break;
				}
				order.setQuantity(1);
				custOrderPizza.add(order);
			}
		}

		//Determine which toppings were selected
		ArrayList<String> toppings = order.getToppings(); //the order's toppings
		ArrayList<String> _list = list.getPizzaToppings(); //Official pizza toppings

		for(int i = 0; i < topCheckList.length; i++){
			if(topCheckList[i].isSelected()){
				toppings.add(_list.get(i));
			}
		}

		//Reset Values for possible next order
			//Reset toppings checkboxes
			for(int i = 0; i < topCheckList.length; i++){
				topCheckList[i].setSelected(false);
			}
			paneToppings.setDisable(true);
			btnAddOrder.setDisable(true);

			//Reset Pizza Combo options
			for(int i = 0; i < checkList.length; i++){
				checkList[i].setSelected(false);
				checkList[i].setDisable(false);
			}

		//Print out order on order screen
		txtPizzaOrder.setText("");
		for(int i = 0; i < custOrderPizza.size(); i++){
			txtPizzaOrder.setText(txtPizzaOrder.getText() +
					              custOrderPizza.get(i).toString());
		}

	}

	public void onCheckClicked() {
		//Push the contents from custOrderPizza into custOrder ArrayList
		ArrayList<Item> custOrderPizza = list.getCustOrderPizza();
		ArrayList<Item> custOrder = list.getCustOrder();

		custOrder.addAll(custOrderPizza);

		//Opens Total.fxml GUI window
		try {
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("Total.fxml"));
			Scene scene = new Scene(root, 900, 700);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			Stage secondStage = new Stage();
			secondStage.setScene(scene);
			secondStage.initModality(Modality.APPLICATION_MODAL);
			secondStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}

		// Get a reference to the stage
        Stage stage = (Stage) btnCheckout.getScene().getWindow();
        // Close the window
        stage.close();
	}


}
