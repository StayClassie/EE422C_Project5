
/* MAIN.java
 * EE422C Project 5 submission by
 * Replace <...> with your actual data.
 * <Chris Classie>
 * <csc2859>
 * <16355>

 * Slip days used: <1>
 * Fall 2018
 */



package assignment5;

import javafx.scene.media.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
		import java.io.IOException;
		import java.lang.reflect.*;
		import java.net.URL;
		import java.util.ArrayList;
		import java.util.Enumeration;
		import java.util.List;

		import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
		import javafx.application.Platform;
		import javafx.collections.FXCollections;
		import javafx.collections.ObservableList;
import java.util.concurrent.TimeUnit;
		import javafx.geometry.*;
		import javafx.scene.Scene;
		import javafx.scene.control.*;
		import javafx.scene.layout.*;
		import javafx.scene.text.Font;
		import javafx.scene.text.Text;
		import javafx.stage.Stage;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.layout.Background;
		import javafx.scene.layout.BackgroundFill;
		import javafx.scene.layout.BorderPane;
		import javafx.animation.AnimationTimer;
		import javafx.application.Application;
		import javafx.application.Platform;
		import javafx.event.ActionEvent;
		import javafx.event.EventHandler;
import javafx.geometry.Pos;
		import org.omg.CORBA.DynAnyPackage.Invalid;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.fxml.FXMLLoader;
import java.io.PrintStream;
import java.io.ByteArrayOutputStream;
import javafx.scene.control.Control;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;



public class Main extends Application {

	public static int timerN = 1;

	private static BorderPane borderPane = new BorderPane();
	static GridPane worldGrid = new GridPane();
	static GridPane critGrid = new GridPane();

	Button makeBtn;
	Button stepBtn;
	Button step2Btn;
	Button seedBtn;
	Button statsBtn;
	Button quitBtn;
	Button animationStartBtn;
	Button animationStopBtn;
	Button speed1;
	Button speed2;
	Button speed3;

	Label enterNumber;
	Label statsResults;
	Label enterType;
	Label enterTypeStats;
	Label enterSeed;
	Label animateSpeed;
	Label enterSteps;
	Label add;
	Label run;

	Slider slider;

	VBox controller;
	VBox stats;

	static Text textArea;
	TextField typeCont;
	TextField typeStats;
	TextField number;
	TextField numSteps;
	TextField seed;

	ComboBox classNames1;
	ComboBox classNames2;

	Region buffer1;



	ByteArrayOutputStream stdOut = new ByteArrayOutputStream();
	static boolean stop = false;


	private static String myPackage;

	static {
		myPackage = Critter.class.getPackage().toString().split(" ")[1];
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			System.setOut(new PrintStream(stdOut));


			worldGrid.setStyle("-fx-background-color: #bf5700; -fx-background-insets: 0, 5;");

			worldGrid.setGridLinesVisible(true);

			worldGrid.setHgap(2);
			worldGrid.setVgap(2);

			worldGrid.getAlignment();

			worldGrid.setPrefWidth(800);
			worldGrid.setMaxSize(1000,800);



			//buttons

			//MAKE
			makeBtn = new Button("Make Critters");
			makeBtn.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {

					int x = 0;
					Integer num;
					String className = classNames1.getValue().toString();

					if (className != null && number.getText().isEmpty() == false) {
						if (number.getText() == null | number.getText().isEmpty()) {

							x = 0;
							num = 1;
						} else {
							num = Integer.valueOf(number.getText().toString());
							x = 1;
						}
						while (num != 0) {

							try {
								Critter.makeCritter(className);

							} catch (InvalidCritterException e) {
								return;
							}
							num -= 1;
						}

					}
					Critter.displayWorld();

					}


			});

			//STEP
			stepBtn = new Button("Step");
			stepBtn.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					Critter.worldTimeStep();
					Critter.displayWorld();
				}
			});

			step2Btn = new Button ("Do Steps");
			step2Btn.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					for(int i = 0; i < Integer.parseInt(numSteps.getText()); i++){
						Critter.worldTimeStep();
					}
					Critter.displayWorld();
				}
			});


			//QUIT
			quitBtn = new Button("Quit");
			quitBtn.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					System.exit(0);
				}
			});


			//SEED
			seedBtn = new Button("Seed");
			seedBtn.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {


					String newSeed = seed.getText();

					try{
					Integer seed = Integer.parseInt(newSeed);

					if(seed < 0) throw new Exception();

					Critter.setSeed(Integer.toUnsignedLong(seed));
				}
				catch(Exception e) {

					}
				}
			});



			//ANIMATE
			animationStartBtn = new Button("Start Animation");
			animationStartBtn.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					int speed = (int) slider.getValue();
					stop = false;

					while (!stop) {

						animationStopBtn.setOnAction(e -> stop = true);
						for (int i = 0; i < speed; i++) {

							Critter.worldTimeStep();
							animationStopBtn.setOnAction(e -> stop = true);

						}
						speed = (int) slider.getValue();
						try {
							TimeUnit.SECONDS.sleep(1);
							stop = true;
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}
						animationStopBtn.setOnAction(e -> stop = true);
						Critter.displayWorld();
					}
				}
			});


			animationStopBtn = new Button("Stop Animation");

			speed1 = new Button("Speed 1");
			speed2 = new Button("Speed 2");
			speed3 = new Button("Speed 3");

			Text leftTitle = new Text();
			leftTitle.setText("        What Starts Here \nChanges The Critter World");

			enterType = new Label("Choose a Critter: ");
			typeCont = new TextField();
			enterSteps = new Label("OR \nEnter Step Number");
			numSteps = new TextField();
			enterTypeStats = new Label("Choose a Critter: ");
			typeStats = new TextField();
			animateSpeed = new Label("Animate Speed");
			slider = new Slider(0, 100, 1);
			slider.setShowTickLabels(true);
			slider.setShowTickMarks(true);
			enterNumber = new Label("Create How Many?");
			enterSeed = new Label("Enter seed");
			number = new TextField();
			seed = new TextField();
			add = new Label("Add Critters");
			run = new Label("Step");



			classNames1 = new ComboBox();
			classNames1.setItems(Critter.getClasses());

			classNames2 = new ComboBox();
			classNames2.setItems(Critter.getClasses());

			buffer1 = new Region();

			controller = new VBox(leftTitle, add, enterType, classNames1, enterNumber,
					number, makeBtn, buffer1, run, stepBtn, enterSteps, numSteps, step2Btn,
					animateSpeed, slider, animationStartBtn,
					animationStopBtn, enterSeed, seed, seedBtn, quitBtn);

			controller.setSpacing(5);
			controller.setPadding(new Insets(10));


			Text statsTitle = new Text();
			statsTitle.setText("Stats");

			textArea = new Text("");
			typeStats.setPrefColumnCount(20);
			textArea.setWrappingWidth(200);
			textArea.setUnderline(false);

			statsResults = new Label("Results: ");


			statsBtn = new Button("View Stats");
			statsBtn.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					int x = 0;

					String classStats = classNames2.getValue().toString();
					String myPackage = Critter.class.getPackage().toString().split(" ")[1];
					String className = myPackage + "." + classStats;

					try {
						Class<?> newCritter = Class.forName(className);
						Method stats = newCritter.getMethod("runStats", List.class);
						List<Critter> instances = Critter.getInstances(classStats);
						stats.invoke(newCritter, instances);
						x++;
					} catch (Exception e) {
						return;

					}
					String results = stdOut.toString();
					textArea.setText(results);
					x--;
				}
			});

			stats = new VBox(statsTitle, enterTypeStats, classNames2,
					statsBtn, statsResults, textArea);
			stats.setSpacing(5);
			stats.getAlignment();
			stats.setPadding(new Insets(10));


			BorderPane root = new BorderPane();
			root.setPadding(new Insets(10));

			root.setLeft(controller);
			root.setCenter(worldGrid);
			root.setRight(stats);



			String musicFile = "09.Eminem - Rap God Instrumental.mp3";

			Media sound = new Media(new File(musicFile).toURI().toString());
			MediaPlayer mediaPlayer = new MediaPlayer(sound);
			mediaPlayer.play();
			mediaPlayer.setVolume(2);




			Scene scene = new Scene(root, 1500, 1200, Color.GREEN);
			primaryStage.setTitle("University of Critter at Austin");

			primaryStage.setScene(scene);
			primaryStage.show();

			Critter.displayWorld();

		} catch (Exception e) {
			e.printStackTrace();
		}


	}

	public static void main(String[] args) {
		launch(args);
	}


}









