package sp;

import java.util.Optional;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Class creating graphical user interface
 * for better manipulation with programme.
 *
 * @author  Pavel Prùcha and Tomáš Slíva
 */
public class GUI extends Application {

	/** Stages of GUI. */
	private Stage loadingStage, mainStage;
	
	/** Labels of GUI. */
	private Label entryLB, simulationLB, textLB, removeEdgeLB, removeEdgeDashLB;
	
	/** TextFields of GUI. */
	private TextField entryTF, simulationTF, watchNodeTF, removeEdgeTF1, removeEdgeTF2;
	
	/** Buttons of GUI. */
	private Button start, simulate, removeEdge, watchNode;
	
	/** TextArea of GUI. */
	public static TextArea textArea;
	
	/** Name of entry file. */
	public String entryFile;
	
	/** Name of simulationFile. */
	public String simulationFile;
	
	/** Variable for hiding windows. */
	public static int hide;
	
	/**
	 * This method is launching graphical user interface.
	 * 
	 * @param args  Parameter of command line.
	 */
	public static void main(String[] args) {
	        
		 launch(args);      
	}
	
	/**
	 * This method is creating loading stage of GUI.
	 * 
	 * @param loadingStage  Loading stage of GUI.
	 */
	@Override
	public void start(Stage loadingStage) throws Exception {
		
		if(hide == 1) {
			
			mainStage.close();
		}
		
		loadingStage.setTitle("PT - Prùcha, Slíva");
		loadingStage.setScene(getScene());
		loadingStage.setWidth(600);
		loadingStage.setHeight(450);
		loadingStage.setResizable(false);
		loadingStage.show();
		
		hide = 0;
		
		this.loadingStage = loadingStage;
	}

	/**
	 * Returns scene of GUI.
	 * 
	 * @return  Scene of GUI.
	 */
	private Scene getScene() {

		Scene startScene = new Scene(getBorderPane(), 600, 450);
		
		return startScene;
	}

	/**
	 * Returns layout of loading screen.
	 * 
	 * @return  Layout of loading screen.
	 */
	private Parent getBorderPane() {
		
		BorderPane borderPane = new BorderPane();
		
		borderPane.setTop(getTop());
		borderPane.setCenter(getCenter());
		borderPane.setBottom(getBottom());
		borderPane.setBackground(new Background(new BackgroundFill(Color.web("#B1D8FD"), CornerRadii.EMPTY, Insets.EMPTY)));
		
		return borderPane;
	}

	/**
	 * Returns top part of layout.
	 * 
	 * @return  Top part of layout.
	 */
	private Node getTop() {
	
		MenuBar menuBar = new MenuBar();
		
		Menu newMenu = new Menu("_Menu");
		
		MenuItem exit = new MenuItem("_Exit"); 
		
		exit.setOnAction(event -> exit());
		exit.setAccelerator(new KeyCodeCombination(KeyCode.X, KeyCombination.ALT_DOWN));
		
		newMenu.getItems().add(exit);
		
		Menu info = new Menu("I_nfo");
		
		MenuItem about = new MenuItem("_About");
		
		about.setOnAction(event -> getInfo());
		about.setAccelerator(new KeyCodeCombination(KeyCode.O, KeyCombination.ALT_DOWN));
		
		info.getItems().add(about);
		
		menuBar.getMenus().add(newMenu);
		menuBar.getMenus().add(info);
		
		return menuBar;
	}
	
	/**
	 * Returns items for loading entry files.
	 * 
	 * @return  Items for loading entry files.
	 */
	private Node getCenter() {
		
		VBox entryBox = new VBox();
		
		entryLB = new Label("Name of entry file:");
		entryLB.setFont(new Font("Calibri", 16));
		
		entryTF = new TextField("entryTest.txt");
		
		entryBox.getChildren().add(entryLB);
		entryBox.getChildren().add(entryTF);
		
		entryBox.setSpacing(10);
		
		VBox simulationBox = new VBox();
		
		simulationLB = new Label("Name of simulation file:");
		simulationLB.setFont(new Font("Calibri", 16));
		
		simulationTF = new TextField("simulationTest.txt");
		
		simulationBox.getChildren().add(simulationLB);
		simulationBox.getChildren().add(simulationTF);
		
		simulationBox.setSpacing(10);
		
		VBox box = new VBox();
		
		box.getChildren().add(entryBox);
		box.getChildren().add(simulationBox);
		
		box.setSpacing(20);
		box.setPadding(new Insets(80, 150, 0, 150));
		box.setAlignment(Pos.TOP_CENTER);
		
		return box;
	}

	/**
	 * Returns start button.
	 * 
	 * @return  Start button.
	 */
	private Node getBottom() {

		VBox startBox = new VBox();
		
		start = new Button("Start");
		
		start.setPrefWidth(100);
		start.setPrefHeight(50);
		start.setFont(new Font("Calibri", 20));
		
		start.setOnAction(event -> startApp());
		
		startBox.getChildren().add(start);
		
		startBox.setPadding(new Insets(0, 0, 80, 0));
		startBox.setAlignment(Pos.CENTER);
		
		return startBox;
	}
	

	/**
	 * This method is ending programme nicely.
	 */
	private void exit() {
		
		Alert confirmation = new Alert(AlertType.CONFIRMATION);
		confirmation.setTitle("Exiting programme");
		confirmation.setHeaderText("Do you really want to exit programme?");
		
		Optional <ButtonType> choose = confirmation.showAndWait();
		
		if (choose.get() == ButtonType.OK) {
			
			Platform.exit();
		}
	}
	
	/**
	 * This method is returning basic information about programme.
	 */
	private void getInfo() {
		
		Alert information = new Alert(AlertType.INFORMATION);
		information.setTitle("PT - Prùcha, Slíva");
		information.setHeaderText("Information about programme:");
		information.setContentText("This programme evaluates the shortest paths and handles all data requests between nodes."); 
		information.showAndWait();
	}
	
	/**
	 * This method is loading names of entry
	 * files and starting main programme.
	 */
	private void startApp() {
		
		entryFile = entryTF.getText();
		simulationFile = simulationTF.getText();
		
		if(entryTF.getText().length() == 0 || simulationTF.getText().length() == 0) {
			
			Alert error = new Alert(AlertType.ERROR);
			error.setTitle("Error !!!");
			error.setHeaderText("Write please name of entry and simulation file.");
			error.showAndWait();
		
		} else {
			
			getMainStage();
		}
	}

	/**
	 * This method is launching main stage of GUI.
	 */
	private void getMainStage() {
		
		if(hide == 0) {
			
			loadingStage.hide();
		}
		
		mainStage = new Stage();
		
		mainStage.setTitle("PT - Prùcha, Slíva");
		mainStage.setScene(new Scene(getMainBorderPane(), 600, 450));
		mainStage.setWidth(600);
		mainStage.setHeight(450);
		mainStage.setResizable(false);
		mainStage.show();
		
		hide = 1;
		
		this.mainStage = mainStage;
	}

	/**
	 * Returns layout of main screen.
	 * 
	 * @return  Layout of main screen.
	 */
	private Parent getMainBorderPane() {

		BorderPane borderPaneMain = new BorderPane();
		
		borderPaneMain.setTop(getMainTop());
		borderPaneMain.setLeft(getLeft());
		borderPaneMain.setRight(getRight());
		borderPaneMain.setBackground(new Background(new BackgroundFill(Color.web("#B1D8FD"), CornerRadii.EMPTY, Insets.EMPTY)));
		
		return borderPaneMain;
	}

	private Node getMainTop() {

		MenuBar menuBar = new MenuBar();
		
		Menu newMenu = new Menu("_Menu");
		
		MenuItem exit = new MenuItem("_Exit"); 
		
		exit.setOnAction(event -> exit());
		exit.setAccelerator(new KeyCodeCombination(KeyCode.X, KeyCombination.ALT_DOWN));
		
		MenuItem reset = new MenuItem("_Reset"); 
		
		reset.setOnAction(event -> reset());
		reset.setAccelerator(new KeyCodeCombination(KeyCode.R, KeyCombination.ALT_DOWN));
		
		newMenu.getItems().add(exit);
		newMenu.getItems().add(reset);
		
		Menu info = new Menu("I_nfo");
		
		MenuItem about = new MenuItem("_About");
		
		about.setOnAction(event -> getInfo());
		about.setAccelerator(new KeyCodeCombination(KeyCode.O, KeyCombination.ALT_DOWN));
		
		info.getItems().add(about);
		
		menuBar.getMenus().add(newMenu);
		menuBar.getMenus().add(info);
		
		return menuBar;
	}

	/**
	 * Returns items for completing simulation.
	 * 
	 * @return  Items for completing simulation.
	 */
	private Node getLeft() {
		
		VBox box = new VBox();
		
		textLB = new Label("Running simulation:");
		
		textArea = new TextArea();
		
		textArea.setEditable(false);
		textArea.setPrefWidth(400);
		textArea.setPrefHeight(550);
		
		box.getChildren().add(textLB);
		box.getChildren().add(textArea);
		
		box.setSpacing(10);
		
		BorderPane.setMargin(box, new Insets(10, 10, 10, 5));
		
		return box;
	}

	/**
	 * Returns buttons for manipulating with programme.
	 * 
	 * @return  Buttons of main stage.
	 */
	private Node getRight() {

		VBox vbox = new VBox();
		
		simulate = new Button("Simulate");
		
		simulate.setPrefWidth(120);
		simulate.setPrefHeight(50);
		simulate.setFont(new Font("Calibri", 16));
		
		simulate.setOnAction(event -> startSimulation());
		
		removeEdgeLB = new Label("Edge between nodes:");
		
		HBox hbox = new HBox();
		
		removeEdgeTF1 = new TextField();
		removeEdgeTF1.setMaxWidth(50);
		
		removeEdgeDashLB = new Label("-");
		
		removeEdgeTF2 = new TextField();
		removeEdgeTF2.setMaxWidth(50);
		
		hbox.getChildren().add(removeEdgeTF1);
		hbox.getChildren().add(removeEdgeDashLB);
		hbox.getChildren().add(removeEdgeTF2);
		
		hbox.setSpacing(8);
		hbox.setPadding(new Insets(0, 20, 0, 20));
		hbox.setAlignment(Pos.CENTER);
		
		removeEdge = new Button("Remove edge");
		
		removeEdge.setPrefWidth(120);
		removeEdge.setPrefHeight(50);
		removeEdge.setFont(new Font("Calibri", 16));
		
		removeEdge.setOnAction(event -> Graph.removeEdge());
		
		watchNodeTF = new TextField();
		watchNodeTF.setMaxWidth(120);
		
		watchNode = new Button("Watch node");
		
		watchNode.setPrefWidth(120);
		watchNode.setPrefHeight(50);
		watchNode.setFont(new Font("Calibri", 16));
		
		watchNode.setOnAction(event -> SendingData.watchNode());
		
		vbox.getChildren().add(simulate);
		vbox.getChildren().add(removeEdge);
		vbox.getChildren().add(removeEdgeLB);
		vbox.getChildren().add(hbox);
		vbox.getChildren().add(watchNode);
		vbox.getChildren().add(watchNodeTF);
		
		
		vbox.setSpacing(10);
		vbox.setPadding(new Insets(0, 20, 0, 20));
		vbox.setAlignment(Pos.CENTER);
		
		return vbox;
	}
	
	/**
	 * This method is returning back to the start stage.
	 */
	private void reset() {
		
		Alert confirmation = new Alert(AlertType.CONFIRMATION);
		confirmation.setTitle("Reset application");
		confirmation.setHeaderText("Do you really want to reset application?");
		
		Optional <ButtonType> choose = confirmation.showAndWait();
		
		if (choose.get() == ButtonType.OK) {
			
			try {
				
				start(loadingStage);
			
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		}
	}

	/**
	 * This method is creating process of all requests.
	 */
	private void startSimulation() {
		
		DataInput.loadEntryValues(entryFile, true, textArea);
		
		SimulationInput.loadSimulation(simulationFile, true, textArea);
	}
}