package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class MainController {

	//Plus Buttons
	@FXML private Button btnPlus1;
	@FXML private Button btnPlus2;
	@FXML private Button btnPlus3;
	@FXML private Button btnPlus4;
	@FXML private Button btnPlus5;
	@FXML private Button btnPlus6;
	@FXML private Button btnPlus7;
	@FXML private Button btnPlus8;
	@FXML private Button btnPlus9;
	@FXML private Button btnPlus10;
	@FXML private Button btnPlus11;
	@FXML private Button btnPlus12;

	//Minus Buttons
	@FXML private Button btnMin1;
	@FXML private Button btnMin2;
	@FXML private Button btnMin3;
	@FXML private Button btnMin4;
	@FXML private Button btnMin5;
	@FXML private Button btnMin6;
	@FXML private Button btnMin7;
	@FXML private Button btnMin8;
	@FXML private Button btnMin9;
	@FXML private Button btnMin10;
	@FXML private Button btnMin11;
	@FXML private Button btnMin12;
	private Button[] minList;

	//Quantity text fields
	@FXML private TextField txtQty1;
	@FXML private TextField txtQty2;
	@FXML private TextField txtQty3;
	@FXML private TextField txtQty4;
	@FXML private TextField txtQty5;
	@FXML private TextField txtQty6;
	@FXML private TextField txtQty7;
	@FXML private TextField txtQty8;
	@FXML private TextField txtQty9;
	@FXML private TextField txtQty10;
	@FXML private TextField txtQty11;
	@FXML private TextField txtQty12;
	private TextField[] qtyList;

	//Checkout Button
	@FXML private Button btnCheck;

	//Pizza Menu Button
	@FXML private Button btnCustPizza;

	//Create ItemList object
	static ItemList list = new ItemList(); //Package static visibility to allow "TotalController" access

	@FXML private void initialize() {
		//Retrieve Menu Items data from MenuItems.txt
		list.loadMenuItems();

		//Initialize minus button array
		minList = new Button[]{btnMin1, btnMin2, btnMin3, btnMin4, btnMin5, btnMin6,
							   btnMin7, btnMin8, btnMin9, btnMin10, btnMin11, btnMin12};

		//Initialize TextField array
		qtyList = new TextField[]{txtQty1, txtQty2, txtQty3, txtQty4, txtQty5, txtQty6,
				                  txtQty7, txtQty8, txtQty9, txtQty10, txtQty11, txtQty12};

        // Attach event handler(s)
			//Checkout button clicked
        	btnCheck.setOnAction(e -> {onCheckClicked();});

        	//Custom Pizza button clicked
        	btnCustPizza.setOnAction(e -> {onCustPizzaClicked();});

        	//Plus Buttons clicked
        	btnPlus1.setOnAction(e -> {onPlusClicked(0);});
        	btnPlus2.setOnAction(e -> {onPlusClicked(1);});
        	btnPlus3.setOnAction(e -> {onPlusClicked(2);});
        	btnPlus4.setOnAction(e -> {onPlusClicked(3);});
        	btnPlus5.setOnAction(e -> {onPlusClicked(4);});
        	btnPlus6.setOnAction(e -> {onPlusClicked(5);});
        	btnPlus7.setOnAction(e -> {onPlusClicked(6);});
        	btnPlus8.setOnAction(e -> {onPlusClicked(7);});
        	btnPlus9.setOnAction(e -> {onPlusClicked(8);});
        	btnPlus10.setOnAction(e -> {onPlusClicked(9);});
        	btnPlus11.setOnAction(e -> {onPlusClicked(10);});
        	btnPlus12.setOnAction(e -> {onPlusClicked(11);});


        	//Minus Buttons clicked
        	btnMin1.setOnAction(e -> {onMinClicked(0);});
        	btnMin2.setOnAction(e -> {onMinClicked(1);});
        	btnMin3.setOnAction(e -> {onMinClicked(2);});
        	btnMin4.setOnAction(e -> {onMinClicked(3);});
        	btnMin5.setOnAction(e -> {onMinClicked(4);});
        	btnMin6.setOnAction(e -> {onMinClicked(5);});
        	btnMin7.setOnAction(e -> {onMinClicked(6);});
        	btnMin8.setOnAction(e -> {onMinClicked(7);});
        	btnMin9.setOnAction(e -> {onMinClicked(8);});
        	btnMin10.setOnAction(e -> {onMinClicked(9);});
        	btnMin11.setOnAction(e -> {onMinClicked(10);});
        	btnMin12.setOnAction(e -> {onMinClicked(11);});

    }

	public void onPlusClicked(int plusIndex){
		//Take the item's quantity add 1
		int num = Integer.parseInt(qtyList[plusIndex].getText());
		num++;
		qtyList[plusIndex].setText("" + num);

		//Enable the minus button if qty is greater than 0
		if(num > 0){
			minList[plusIndex].setDisable(false);
		}

	}

	public void onMinClicked(int minIndex){
		//Retrieve the value of the Qty box and subtract 1
		int num = Integer.parseInt(qtyList[minIndex].getText());
		num--;
		qtyList[minIndex].setText("" + num);

		//If the qty is equal to 0, disable minus button
		if(num == 0){
			minList[minIndex].setDisable(true);
		}

	}

	public void onCheckClicked() {
		//Get both lists from the ItemList
		ArrayList<Item> menuItems = list.getMenuItems();   //ArrayList of Menu Items
	    ArrayList<Item> custOrder = list.getCustOrder();   //ArrayList of customer order

		//Gets chosen items and puts them into custOrder ArrayList
		for(int i = 0; i < qtyList.length; i++){
			int num = Integer.parseInt(qtyList[i].getText());

			if(num > 0){
				menuItems.get(i).setQuantity(num);
				custOrder.add(menuItems.get(i));
			}
		}

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
        Stage stage = (Stage) btnCheck.getScene().getWindow();
        // Close the window
        stage.close();
	}

	public void onCustPizzaClicked(){
		//Get both lists from the ItemList
		ArrayList<Item> menuItems = list.getMenuItems();   //ArrayList of Menu Items
		ArrayList<Item> custOrder = list.getCustOrder();   //ArrayList of customer order

		//Gets chosen items and puts them into custOrder ArrayList
		for(int i = 0; i < qtyList.length; i++){
			int num = Integer.parseInt(qtyList[i].getText());

			if(num > 0){
				menuItems.get(i).setQuantity(num);
				custOrder.add(menuItems.get(i));
			}
		}

		//Opens CustomPizza.fxml GUI window
		try {
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("CustomPizza.fxml"));
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
        Stage stage = (Stage) btnCheck.getScene().getWindow();
        // Close the window
        stage.close();
	}
}
