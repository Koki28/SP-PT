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
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Class creating graphical user interface
 * for better manipulation with programm.
 *
 * @author  Pavel Prùcha and Tomáš Slíva
 */
public class GUI extends Application {

	/** Stages of GUI. */
	private Stage loadingStage, mainStage;
	
	/** Labels of GUI. */
	private Label entryLB, simulationLB, textLB;
	
	/** TextFields of GUI. */
	private TextField entryTF, simulationTF, watchNodeTF;
	
	/** Buttons of GUI. */
	private Button start, simulate, removeEdge, watchNode;
	
	/** TextArea of GUI. */
	private TextArea textArea;
	
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
		
		entryTF = new TextField();
		entryTF.setPromptText("entryName.txt");
		
		entryBox.getChildren().add(entryLB);
		entryBox.getChildren().add(entryTF);
		
		entryBox.setSpacing(10);
		
		VBox simulationBox = new VBox();
		
		simulationLB = new Label("Name of simulation file:");
		simulationLB.setFont(new Font("Calibri", 16));
		
		simulationTF = new TextField();
		simulationTF.setPromptText("simulationName.txt");
		
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
	 * This method is ending programm nicely.
	 */
	private void exit() {
		
		Alert confirmation = new Alert(AlertType.CONFIRMATION);
		confirmation.setTitle("Exiting programm");
		confirmation.setHeaderText("Do you really want to exit programm?");
		
		Optional <ButtonType> choose = confirmation.showAndWait();
		
		if (choose.get() == ButtonType.OK) {
			
			Platform.exit();
		}
	}
	
	/**
	 * This method is returning basic information about programm.
	 */
	private void getInfo() {
		
		Alert information = new Alert(AlertType.INFORMATION);
		information.setTitle("PT - Prùcha, Slíva");
		information.setHeaderText("Information about programm:");
		information.setContentText("This programm evaluates the shortest paths and handles all data requests between nodes."); 
		information.showAndWait();
	}
	
	/**
	 * This method is loading names of entry
	 * files and starting main programm.
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
	 * 
	 * 
	 * @return
	 */
	private Parent getMainBorderPane() {

		BorderPane borderPaneMain = new BorderPane();
		
		borderPaneMain.setTop(getTop());
		borderPaneMain.setLeft(getLeft());
		borderPaneMain.setRight(getRight());
		borderPaneMain.setBackground(new Background(new BackgroundFill(Color.web("#B1D8FD"), CornerRadii.EMPTY, Insets.EMPTY)));
		
		return borderPaneMain;
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
		textArea.setPrefWidth(430);
		textArea.setPrefHeight(550);
		
		box.getChildren().add(textLB);
		box.getChildren().add(textArea);
		
		box.setSpacing(10);
		
		BorderPane.setMargin(box, new Insets(10, 10, 10, 5));
		
		return box;
	}

	/**
	 * Returns buttons for manipulating with programm.
	 * 
	 * @return  Buttons of main stage.
	 */
	private Node getRight() {

		VBox box = new VBox();
		
		simulate = new Button("Simulate");
		
		simulate.setPrefWidth(120);
		simulate.setPrefHeight(50);
		simulate.setFont(new Font("Calibri", 16));
		
		simulate.setOnAction(event -> startSimulation());
		
		removeEdge = new Button("Remove edge");
		
		removeEdge.setPrefWidth(120);
		removeEdge.setPrefHeight(50);
		removeEdge.setFont(new Font("Calibri", 16));
		
		removeEdge.setOnAction(event -> Graph.removeEdge());
		
		watchNodeTF = new TextField();
		watchNodeTF.setPromptText("Watch node: 1 - 10");
		watchNodeTF.setMaxWidth(120);
		
		watchNode = new Button("Watch node");
		
		watchNode.setPrefWidth(120);
		watchNode.setPrefHeight(50);
		watchNode.setFont(new Font("Calibri", 16));
		
		watchNode.setOnAction(event -> SendingData.watchNode());
		
		box.getChildren().add(simulate);
		box.getChildren().add(removeEdge);
		box.getChildren().add(watchNodeTF);
		box.getChildren().add(watchNode);
		
		box.setSpacing(20);
		box.setPadding(new Insets(0, 20, 0, 20));
		box.setAlignment(Pos.CENTER);
		
		return box;
	}

	private void startSimulation() {
		
	//	DataInput.loadEntryValues();
		
	//	SimulationInput.loadSimulation();
	}
}