/**
 * 
 */
package dad.javafx.adivin;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * @author Ayoze Amaro
 *
 */
public class AppFX extends Application {

	private Label infoJuego;
	private Button enviarNum;
	private TextField numIntroducido;
	private int numeroAzar = numeroAleatorio(), totalIntentos, numero;

	@Override
	public void start(Stage primaryStage) throws Exception {

		// Label
		infoJuego = new Label();
		infoJuego.setText("Introduce un número del 1 al 100");
		
		// Input
		numIntroducido = new TextField();
		numIntroducido.setPrefColumnCount(5);
		numIntroducido.setMaxWidth(150);
		numIntroducido.setAlignment(Pos.CENTER);
		
		// Button
		enviarNum = new Button();
		enviarNum.setText("Comprobar");
		enviarNum.setOnAction(e -> onSaludarButtonAction(e));
		enviarNum.setDefaultButton(true); // Botón que saluda por defecto
		
		// Panel
		VBox root = new VBox();
		root.setSpacing(5);
		root.setAlignment(Pos.CENTER);
		root.getChildren().addAll(infoJuego, numIntroducido, enviarNum);

		Scene escena = new Scene(root, 320, 200);
		
		primaryStage.setScene(escena);
		primaryStage.setTitle("AdivinApp");
		primaryStage.show();
	}
	
	private void onSaludarButtonAction(ActionEvent e) {
		try { numero = Integer.parseInt(numIntroducido.getText()); } catch(Exception ex) { errorDetectado(); }
		
		if (numero == numeroAzar) {
			numeroAzar = numeroAleatorio();
			hasGanado(totalIntentos);
			totalIntentos = 0;
		}
		else { hasFallado(numero, numeroAzar); }
		totalIntentos++;
	}
	
	private int numeroAleatorio() {
		int numeroAzar = (int) (Math.random()*100+1);
		System.out.println(numeroAzar);
		return numeroAzar;
	}
	
	private static void hasGanado(int totalIntentos) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("AdivinApp");
		alert.setHeaderText("¡Has ganado!");
		alert.setContentText("Solo has necesitado " + totalIntentos + " intentos");
		alert.showAndWait();
	}
	
	private static void hasFallado(int numero, int numeroAzar) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("AdivinApp");
		alert.setHeaderText("¡Has fallado!");
		if(numero < numeroAzar)
			alert.setContentText("El número ha adivinar es mayor que 50");
		else
			alert.setContentText("El número ha adivinar es menor que 50");
		alert.showAndWait();
	}
	
	private void errorDetectado() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("AdivinApp");
		alert.setHeaderText("Error");
		alert.setContentText("El número introducido no es válido");
		alert.showAndWait();
	}

	public static void main(String[] args) {
		launch(args);
	}
	
}







