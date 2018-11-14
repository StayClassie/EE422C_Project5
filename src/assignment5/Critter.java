
/* CRITTER.java
 * EE422C Project 5 submission by
 * Replace <...> with your actual data.
 * <Chris Classie>
 * <csc2859>
 * <16355>

 * Slip days used: <1>
 * Fall 2018
 */








package assignment5;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;


import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Constructor;

import static javafx.scene.layout.Priority.ALWAYS;


public abstract class Critter {
	/* NEW FOR PROJECT 5 */
	public enum CritterShape {
		CIRCLE,
		SQUARE,
		TRIANGLE,
		DIAMOND,
		STAR
	}

	/* the default color is white, which I hope makes critters invisible by default
	 * If you change the background color of your View component, then update the default
	 * color to be the same as you background
	 *
	 * critters must override at least one of the following three methods, it is not
	 * proper for critters to remain invisible in the view
	 *
	 * If a critter only overrides the outline color, then it will look like a non-filled
	 * shape, at least, that's the intent. You can edit these default methods however you
	 * need to, but please preserve that intent as you implement them.
	 */
	public javafx.scene.paint.Color viewColor() {
		return Color.web("#bf5700");
	}

	public javafx.scene.paint.Color viewOutlineColor() { return Color.WHITE; }
	public javafx.scene.paint.Color viewFillColor() { return Color.web("bf5700"); }

	public abstract CritterShape viewShape();
	protected static List<Critter> populate() {return population; }


	public int getX(){
		return this.x_coord;

	}

	public int getY(){
		return this.y_coord;

	}

	private static String myPackage;
	private	static List<Critter> population = new java.util.ArrayList<Critter>();
	private static List<Critter> babies = new java.util.ArrayList<Critter>();

	// Gets the package name.  This assumes that Critter and its subclasses are all in the same package.
	static {
		myPackage = Critter.class.getPackage().toString().split(" ")[1];
	}

	protected final String look(int direction, boolean steps) {
		energy -= Params.look_energy_cost;
		int x = 0;
		int y = 0;
		int steps2;
		int width = Params.world_width;
		int height = Params.world_height;

		if(!steps)
			steps2 = 1;
		else
			steps2 = 2;

		switch(direction) {
			//directions: up, down, left, right, up-left, up-right, down-left, down-right
			//x modified by width
			//y modified by height
			// up and left (-)
			//down and right (+)

			case 0:
				//up direction

				x = x_coord;
				y = (((y_coord - steps2) % height) + height) % height;
				break;

			case 1:
				//down direction
				x = x_coord;
				y = (((y_coord + steps2) % height) + height) % height;
				break;

			case 2:
				//left direction
				x = (((x_coord - steps2) % width) + width) % width;
				y = y_coord;
				break;

			case 3:
				//right direction
				x = (((x_coord + steps2) % width) + width ) % width;
				y = y_coord;
				break;

			case 4:
				//up-left direction

				x = (((x_coord - steps2) % width ) + width ) % width;
				y = (((y_coord - steps2) % height) + height ) % height;
				break;

			case 5:
				//up-right direction

				x = (((x_coord + steps2) % width) + width) % width;
				y = (((y_coord - steps2) % height ) + height ) % height;
				break;

			case 6:
				//down-left direction

				x = (((x_coord - steps2) % width) + width) % width;
				y = (((y_coord + steps2) % height) + height ) % height;
				break;

			case 7:
				//down-right direction
				x = (((x_coord + steps2) % width) + width) % width;
				y = (((y_coord + steps2) % height) + height ) % height;
				break;
		}

		for (Critter c : population){
			if (x == c.x_coord && y == c.y_coord){
				return c.toString();
			}
		}
		return null;
	}


	/* rest is unchanged from Project 4 */


	private static java.util.Random rand = new java.util.Random();
	public static int getRandomInt(int max) {
		return rand.nextInt(max);
	}

	public static void setSeed(long new_seed) {
		rand = new java.util.Random(new_seed);
	}

	private int dir = 0;


	/* a one-character long string that visually depicts your critter in the ASCII interface */
	public String toString() { return ""; }

	private int energy = 0;
	protected int getEnergy() { return energy; }

	private int x_coord;
	private int y_coord;

	protected final void walk(int direction) {
		switch (direction) {

			case 0:
				x_coord++;
				if (x_coord >= Params.world_width) {
					x_coord = 0;
				}
				break;

			case 1:
				y_coord++;
				if (y_coord > Params.world_height - 1) {
					y_coord = 0;
				}
				break;

			case 2:
				x_coord++;
				y_coord++;

				if (y_coord > Params.world_height - 1
						&& x_coord > Params.world_width - 1) {
					x_coord = 0;
					y_coord = 0;
				}

				if (x_coord > Params.world_width - 1) {
					x_coord = 0;
				}
				if (y_coord > Params.world_height - 1) {
					y_coord = 0;
				}
				break;

			case 3:
				x_coord--;
				if (x_coord < 0) {
					x_coord = Params.world_width - 1;
				}
				break;

			case 4:
				y_coord--;
				if (y_coord < 0) {
					y_coord = Params.world_height - 1;
				}
				break;

			case 5:
				x_coord++;
				y_coord--;

				if(x_coord >= Params.world_width
						&& y_coord < 0){
					y_coord= Params.world_height - 1;
					x_coord = 0;
				}

				if(y_coord < 0){
					y_coord = Params.world_height - 1;
				}

				if(x_coord >= Params.world_width){
					x_coord = 0;
				}
				break;

			case 6:
				x_coord--;
				y_coord--;

				if(y_coord < 0
						&& x_coord < 0){
					x_coord = Params.world_width - 1;
					y_coord = Params.world_height - 1;
				}

				if(x_coord < 0){
					x_coord = Params.world_width - 1;
				}

				if(y_coord < 0){
					y_coord = Params.world_height - 1;
				}
				break;

			case 7:
				y_coord++;
				x_coord--;

				if(x_coord < 0
						&& y_coord > Params.world_height - 1){

					x_coord = Params.world_width - 1;
					y_coord = 0;

				}

				if(y_coord > Params.world_height - 1){
					y_coord = 0;
				}

				if(x_coord < 0){
					x_coord = Params.world_width - 1;
				}
				break;
		}
	}

	protected final void run(int direction) {
		this.energy += (2*Params.walk_energy_cost);
		this.energy -= Params.rest_energy_cost;
		this.energy -= Params.run_energy_cost;
		walk(direction);
		walk(direction);
	}

	protected final void reproduce(Critter offspring, int direction) {
		//here we want to check if there is enough energy

		if(this.energy < Params.min_reproduce_energy){
			return;
		}

		//First step sets the offsprings energy to half of the parents
		//Second Step: gives the parents half energy and rounds up

		offspring.energy = this.energy / 2;
		this.energy = this.energy / 2 + this.energy % 2;


		//These two set the proper coordinates to the parents location

		offspring.x_coord = this.x_coord;
		offspring.y_coord = this.y_coord;

		//move in specific direction
		offspring.walk(direction);

		//add to list of babies
		babies.add(offspring);
	}


	private static void fightInstance(Critter crit1, Critter crit2){

		crit1.energy = crit1.energy - Params.rest_energy_cost;
		crit2.energy = crit2.energy - Params.rest_energy_cost;

		if(crit1.getEnergy() <= 0 || crit2.getEnergy() <= 0){
			return;
		}

		int crit1_AttackRoll = 0;
		int crit2_AttackRoll = 0;

		if(crit1.fight(crit2.toString())){
			if(crit1.getEnergy() <= 0){
				return;
			}
			crit1_AttackRoll = Critter.getRandomInt(crit1.getEnergy());
		}
		if(crit2.fight(crit1.toString())){
			if(crit2.getEnergy() <= 0){
				return;
			}
			crit2_AttackRoll = Critter.getRandomInt(crit2.getEnergy());
		}
		if(crit1.x_coord == crit2.x_coord
				&& crit1.y_coord == crit2.y_coord){
			if(crit1_AttackRoll >= crit2_AttackRoll){
				crit1.energy = crit2.getEnergy() / 2;
				crit2.energy = 0;
			}
			else{
				crit2.energy = crit1.getEnergy() / 2;
				crit1.energy = 0;
			}
		}

		return;

	}



	public abstract void doTimeStep();
	public abstract boolean fight(String oponent);


	public static void worldTimeStep() {
		int babycount = 0;
		boolean algaeRef = true;

		for (Critter c : population){
			c.doTimeStep();
		}

		for (Critter crit1: population){
			for(Critter crit2: population){
				if(crit1 != crit2
						&& crit1.x_coord == crit2.x_coord
						&& crit1.y_coord == crit2.y_coord){
					fightInstance(crit1,crit2);
				}
			}
		}

		for(Critter c: population){
			c.energy = c.energy - Params.rest_energy_cost - Params.walk_energy_cost;
		}

		for(int i = 0; i < Params.refresh_algae_count; i++){
			try{
				makeCritter("Algae");
				algaeRef = true;
			}
			catch (InvalidCritterException e){
				System.out.println("Algae didn't refresh");
				algaeRef = false;
			}
		}

		for(Critter babyCritter : babies){
			population.add(babyCritter);
			babycount++;

		}
		babies.clear();

		for(int i = 0; i < population.size();){
			if(population.get(i).energy <= 0){
				population.remove(i);
			}
			else{
				i++;
			}
		}

	}


	//public static void displayWorld(Object pane) {}
	// Alternate displayWorld, where you use Main.<pane> to reach into your
	 //  display component.
	    public static void displayWorld() {

		int height = Params.world_height;
		int width = Params.world_width;
		height += 1;
		width += 1;

			Main.worldGrid.getChildren().clear();

			ArrayList<Critter> population = (ArrayList<Critter>) Critter.populate();

			for (Critter c : population) {

				Critter.CritterShape shape = c.viewShape();
				Shape s = getIcon(c, shape);


				Main.worldGrid.add(s, c.getX(), c.getY(), 1, 1);


				Main.worldGrid.setHgrow(s, ALWAYS);
				Main.worldGrid.setVgrow(s, ALWAYS);
			}
		}


	/* create and initialize a Critter subclass
	 * critter_class_name must be the name of a concrete subclass of Critter, if not
	 * an InvalidCritterException must be thrown
	 */
	public static void makeCritter(String critter_class_name) throws InvalidCritterException {
		String className = myPackage + "." + critter_class_name;
		Class<?> newCritterClass = null;

		Class<?> c = null;
		int i = 0;
		int j = 0;

		try {

			c = Class.forName(className);
		} catch (ClassNotFoundException e) {
			throw new InvalidCritterException(critter_class_name);
		}
		try {

			Critter newCritter = (Critter) c.newInstance();
			Critter.population.add(newCritter);
			newCritter.energy = Params.start_energy;
			newCritter.x_coord = Critter.getRandomInt(Params.world_width);
			newCritter.y_coord = Critter.getRandomInt(Params.world_height);

		} catch (InstantiationException | IllegalAccessException e) {
			throw new InvalidCritterException(critter_class_name);
		}

	}

	public static List<Critter> getInstances(String critter_class_name) throws InvalidCritterException {
		List<Critter> result = new java.util.ArrayList<Critter>();

		Class<?> critter = null;
		Class<?> crit;

		String className = myPackage+"."+critter_class_name;



		try{
			critter = Class.forName(className);
		}
		catch(ClassNotFoundException e){
			throw new InvalidCritterException(critter_class_name);
		}


		for(Critter c : population){
			if(critter.isInstance(c)){
				result.add(c);
			}
		}
		return result;
	}

	public static String runStats(List<Critter> critters) {
		String output = "";
		output += "" + critters.size() + " critters as follows -- ";
		java.util.Map<String, Integer> critter_count = new java.util.HashMap<String, Integer>();
		for (Critter crit : critters) {
			String crit_string = crit.toString();
			Integer old_count = critter_count.get(crit_string);
			if (old_count == null) {
				critter_count.put(crit_string,  1);
			} else {
				critter_count.put(crit_string, old_count.intValue() + 1);
			}
		}
		String prefix = "";
		for (String s : critter_count.keySet()) {
			output = output + prefix + s + ":" + critter_count.get(s);
			prefix = ", ";
		}
		output = output + "\n";
		return output;
	}

	/* the TestCritter class allows some critters to "cheat". If you want to
	 * create tests of your Critter model, you can create subclasses of this class
	 * and then use the setter functions contained here.
	 *
	 * NOTE: you must make sure thath the setter functions work with your implementation
	 * of Critter. That means, if you're recording the positions of your critters
	 * using some sort of external grid or some other data structure in addition
	 * to the x_coord and y_coord functions, then you MUST update these setter functions
	 * so that they correctup update your grid/data structure.
	 */
	static abstract class TestCritter extends Critter {
		protected void setEnergy(int new_energy_value) {
			super.energy = new_energy_value;
		}

		protected void setX_coord(int new_x_coord) {
			super.x_coord = new_x_coord;
		}

		protected void setY_coord(int new_y_coord) {
			super.y_coord = new_y_coord;
		}

		protected int getX_coord() {
			return super.x_coord;
		}

		protected int getY_coord() {
			return super.y_coord;
		}


		/*
		 * This method getPopulation has to be modified by you if you are not using the population
		 * ArrayList that has been provided in the starter code.  In any case, it has to be
		 * implemented for grading tests to work.
		 */
		protected static List<Critter> getPopulation() {
			return population;
		}

		/*
		 * This method getBabies has to be modified by you if you are not using the babies
		 * ArrayList that has been provided in the starter code.  In any case, it has to be
		 * implemented for grading tests to work.  Babies should be added to the general population
		 * at either the beginning OR the end of every timestep.
		 */
		protected static List<Critter> getBabies() {
			return babies;
		}
	}

	/**
	 * Clear the world of all critters, dead and alive
	 */
	public static void clearWorld() {
		population.clear();
		babies.clear();
	}


	protected static int tileSize = 10;

	static Shape getIcon(Critter critter, Critter.CritterShape shape) {
		Shape s = null;

		int index = 0;

		if (shape.equals(Critter.CritterShape.CIRCLE)) {

			index = 0;

		} else if (shape.equals(Critter.CritterShape.SQUARE)) {

			index = 1;

		} else if (shape.equals(Critter.CritterShape.TRIANGLE)) {

			index = 2;

		} else if (shape.equals(Critter.CritterShape.DIAMOND)) {

			index = 3;

		} else if (shape.equals(Critter.CritterShape.STAR)) {

			index = 4;

		}

		switch (index) {

			case 0:
				//circle

				s = new Circle(tileSize / 2);
				s.setFill(critter.viewFillColor());

				break;

			case 1:
				//sqaure

				s = new Rectangle(tileSize, tileSize);
				s.setFill(critter.viewFillColor());

				break;

			case 2:
				//triangle

				s = drawTriangle();
				s.setFill(critter.viewFillColor());
				break;

			case 3:
				//diamond

				s = drawDiamond();
				s.setFill(critter.viewFillColor());
				break;

			case 4:
				//star

				s = drawStar();
				s.setFill(critter.viewFillColor());
				break;

			default:
				break;
		}

		s.setStroke(critter.viewOutlineColor());
		return s;
	}


	public static void drawGrid() {
		int h = Params.world_height * tileSize;
		int w = Params.world_width * tileSize;
		for (int i = 0; i < h; i += tileSize) {
			for (int j = 0; j < w; j += tileSize) {
				Rectangle tile = new Rectangle(tileSize, tileSize);

				tile.setStroke(Color.GRAY);
				tile.setFill(Color.BLACK);

				Main.worldGrid.add(tile, i, j);
			}
		}
	}


	/*
	public static void drawCritter(int x_coord, int y_coord, Critter.CritterShape shape, Color outline, Color fill) {

		Shape shape1 = getIcon(critter, shape);

		shape1.setFill(fill);
		shape1.setStroke(outline);

		if (x_coord == 0
				&& y_coord == 0) {
			Main.critGrid.add(shape1, x_coord, y_coord);
		} else if (x_coord == 0) {
			Main.critGrid.add(shape1, x_coord, y_coord - 1);
		} else if (y_coord == 0) {
			Main.critGrid.add(shape1, x_coord - 1, y_coord);
		} else {
			Main.critGrid.add(shape1, x_coord - 1, y_coord - 1);
		}
	}
*/

	public static void paint() {
		Main.critGrid.getChildren().clear();
		Main.worldGrid.getChildren().clear();
		drawGrid();
	}

	private static Shape drawTriangle(){
		Polygon triangle = new Polygon();
		triangle.getPoints().addAll(1.0, 1.0, (double)tileSize - 1.0, 1.0, (double)(tileSize/2), (double)tileSize - 1.0);

		return triangle;
	}

	private static Shape drawDiamond(){
		Polygon diamond = new Polygon();
		diamond.getPoints().addAll(1.0, (double)(tileSize/2), (double)(tileSize/2), (double)tileSize - 1.0,
				(double)tileSize - 1.0, (double)(tileSize/2),(double)(tileSize/2), 1.0);

		return diamond;
	}


	private static Shape drawStar(){
		Polygon star = new Polygon();
		star.getPoints().addAll(1.0, (double)tileSize/2.0, (double)tileSize/3.0, (double)tileSize*(2.0/3.0),
				(double)tileSize/3.0, (double)tileSize-1.0,(double)tileSize*(2.0/3.0), (double)tileSize*(2.0/3.0),
				(double)tileSize-1.0, (double)tileSize-1.0, (double)tileSize*(2.0/3.0), (double)tileSize/2.0,
				(double)tileSize-1.0, 1.0,(double)tileSize*(2.0/3.0), (double)tileSize/3.0,
				(double)tileSize/3.0, 1.0, (double)tileSize/3.0,(double)tileSize/3.0);


		return star;

	}

//clean up VVV

	public static ObservableList<String> getClasses (){


		File file = new File("./src/assignment5");
		File[] listOfFiles = file.listFiles();

		ObservableList<String> classes = FXCollections.observableArrayList();

		for (int i = 0; i < listOfFiles.length; i++) {

			if (listOfFiles[i].isFile() && listOfFiles[i].toString().endsWith(".java")) {
				classes.add(listOfFiles[i].toString());

			} else if (listOfFiles[i].isDirectory()) {

				File subDir = new File(listOfFiles[i].toString());
				File[] subFiles = subDir.listFiles();

				for (int j = 0; i < subFiles.length; j++) {

					if (subFiles[i].isFile() && subFiles[i].toString().endsWith(".java")) {
						classes.add(subFiles[i].toString());
					}
				}
			}
		}


		for (int i = 0; i < classes.size(); i++)
		{
			int index = classes.get(i).toString().lastIndexOf('/');

			if (index == -1) {
				index = classes.get(i).toString().lastIndexOf('\\');
			}
			String stripped = classes.get(i).toString().substring(index + 1);
			index = stripped.lastIndexOf('.');

			if (index != -1) {
				String secondStripped = stripped.substring(0, index);
				classes.set(i, secondStripped);
			}

			else
			{
				classes.set(i, stripped);
			}
		}

		String myPackage = Critter.class.getPackage().toString().split(" ")[1];

		for (int i = 0; i < classes.size(); i++) {

			try {
				String className = myPackage + "." + classes.get(i);
				String critterClassName = myPackage + ".Critter";

				Class<?> newCritter = Class.forName(className);

				Class<?> critterClass = Class.forName(critterClassName);

				if (!critterClass.isAssignableFrom(newCritter)) {
					classes.remove(i);
					i -= 1;
				}
			} catch (Exception e)
			{
				classes.remove(i);
				System.out.println("No Stats");
			}
		}

		return classes;
	}


}

