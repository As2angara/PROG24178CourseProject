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
	@FXML private TextArea txtPrices;
	@FXML private TextArea txtQuantity;
	@FXML private Button btnPay;
	@FXML private TextField txtTotalCost;

	//Field Variables
	private ItemList list = MainController.list;
	private ArrayList<Item> custOrder = list.getCustOrder();

	@FXML public void initialize() {
		printOrderedItems();

		btnPay.setOnAction(e -> {
			saveData();
			paymentConfirmation();
		});


	}

	public void paymentConfirmation(){
		Alert alert;
		alert = new Alert(AlertType.INFORMATION,
				 		 String.format( "Total was: $%.2f" , list.calculateTotal(custOrder))
				 		 );
		alert.show();
	}

	public void saveData(){  //**This will overwrite the old information. How could I add to the text, not replace it?
		PrintWriter writer = null;

		try {
			File fileDescriptor = new File("data/CustomerOrder.txt");
			writer = new PrintWriter(fileDescriptor);

			for(int i = 0; i < custOrder.size(); i++){
				Item orderedItem = custOrder.get(i);
				writer.println(
						orderedItem.getQuantity() + "," +
						orderedItem.getName() + "," +
						orderedItem.getPrice() );

			}

		}catch(FileNotFoundException e) {
			e.printStackTrace(); // Add error recovery here if needed

		}finally {
			if (writer != null) {
				writer.close();
			}
		}
	}

	public void printOrderedItems(){
		for(int i = 0; i < custOrder.size(); i++){
			txtItems.setText(txtItems.getText() + custOrder.get(i).getName() + "\n\n");
			txtQuantity.setText(txtQuantity.getText() + "x" + custOrder.get(i).getQuantity() + "\n\n");
			txtPrices.setText(txtPrices.getText() + custOrder.get(i).getPrice() + "\n\n");
		}

		txtTotalCost.setText(String.format("$%.2f", list.calculateTotal(custOrder)));

	}
}
