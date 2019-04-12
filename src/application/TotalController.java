package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;

public class TotalController {

	//FXML handles
	@FXML private TextArea txtItems;
	@FXML private TextArea txtQty;
	@FXML private TextArea txtPrice;
	@FXML private Button btnPay;
	@FXML private Label lblTotal;
	@FXML private Label lblTotalCost;
	@FXML private Label lblTax;

	//Field Variables
	private ItemList list = MainController.list;

	@FXML public void initialize() {
		printOrderedItems();

		btnPay.setOnAction(e -> {
			list.saveCustOrder();
			paymentConfirmation();
		});


	}

	public void paymentConfirmation(){
		ArrayList<Item> custOrder = list.getCustOrder();
		double total = list.calculateTotal(custOrder);

		Alert alert;
		alert = new Alert(AlertType.INFORMATION,
				 		 String.format( "Total was: $%.2f" ,
				 				         total*ItemList.TAXPERC + total + 5.00));
		alert.show();
	}

	public void printOrderedItems(){
		ArrayList<Item> custOrder = list.getCustOrder();
		double total = list.calculateTotal(custOrder);

		for(int i = 0; i < custOrder.size(); i++){
			String prevText = txtItems.getText();
			String prevQty = txtQty.getText();
			String prevPrice = txtPrice.getText();
			Item orderedItem = custOrder.get(i);

			txtItems.setText(prevText + orderedItem.getName() + "\n\n");
			txtQty.setText(prevQty + orderedItem.getQuantity() + "\n\n");
			txtPrice.setText(prevPrice +
							 String.format("$%.2f", orderedItem.getPrice()) +
							 "\n\n");
		}

		lblTotal.setText("" + String.format("$%.2f", total));
		lblTax.setText("" + String.format("$%.2f", total*ItemList.TAXPERC));
		lblTotalCost.setText(String.format("$%.2f", total*ItemList.TAXPERC + total + 5.00));

	}
}
