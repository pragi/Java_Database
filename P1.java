import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class P1 {
	public class coaches {
		public String Coach_ID;
		public int season;
		public String first_name;
		public String last_name;
		public int season_win;
		public int season_loss;
		public int playoff_win;
		public int playoff_loss;
		public String team;
	}

	public class teams {
		public String Team_ID;
		public String Location;
		public String Name;
		public String League;
	}
	/* Define data structures for holding the data here */

	ArrayList<coaches> array_of_coaches = new ArrayList<coaches>();
	ArrayList<teams> array_of_teams = new ArrayList<teams>();


	public P1() {
		/* initialize the data structures */
		//array_of_coaches = new coaches[50];
		//array_of_teams = new teams[50];
	}

	public void run() {
		CommandParser parser = new CommandParser();

		System.out.println("The mini-DB of NBA coaches and teams");
		System.out.println("Please enter a command.  Enter 'help' for a list of commands.");
		System.out.println();
		System.out.print("> "); 

		Command cmd = null;
		while ((cmd = parser.fetchCommand()) != null) {
			//System.out.println(cmd);

			boolean result=false;

			if (cmd.getCommand().equals("help")) {
				result = doHelp();

				/* You need to implement all the following commands */
			} else if (cmd.getCommand().equals("add_coach")) {
				coaches temp = new coaches();
				String[] params = cmd.getParameters();

				if (params.length != 9){
					System.out.println("add_coach function requires exactly 9 parameters. Please try again.");
				}
				else{
					temp.Coach_ID = params[0];
					if (isvalidint(params[1])) temp.season = Integer.parseInt(params[1]); else continue;
					temp.first_name = params[2];
					temp.last_name = params[3];
					if (isvalidint(params[4])) temp.season_win = Integer.parseInt(params[4]); else continue;
					if (isvalidint(params[5])) temp.season_loss = Integer.parseInt(params[5]); else continue;
					if (isvalidint(params[6])) temp.playoff_win = Integer.parseInt(params[6]); else continue;
					if (isvalidint(params[7])) temp.playoff_loss = Integer.parseInt(params[7]); else continue;
					temp.team = params[8];

					array_of_coaches.add(temp);
				}

			} else if (cmd.getCommand().equals("add_team")) {
				teams temp = new teams();
				String[] params = cmd.getParameters();

				if (params.length != 4){
					System.out.println("add_team function requires exactly 4 parameters. Please try again.");
				}
				else{
					temp.Team_ID = params[0];
					temp.Location = params[1];
					temp.Name = params[2];
					temp.League = params[3];

					array_of_teams.add(temp);
				}

			} else if (cmd.getCommand().equals("print_coaches")) {

				int i;
				System.out.println();
				for (i = 0; i < array_of_coaches.size(); i++)
					print_one_coach(array_of_coaches,i);

			} else if (cmd.getCommand().equals("print_teams")) {

				int i;
				System.out.println();
				for (i =0; i < array_of_teams.size(); i++)
					print_one_team(i);

			} else if (cmd.getCommand().equals("coaches_by_name")) {

				String coach_last_name = cmd.getParameters()[0];
				String temp = coach_last_name.replace('+',' ');
				//				System.out.println(coach_last_name);
				//				System.out.println(temp);
				int i;

				System.out.println();
				for (i = 0; i < array_of_coaches.size(); i++)
				{
					if (temp.equalsIgnoreCase(array_of_coaches.get(i).last_name))
						print_one_coach(array_of_coaches,i);		
				}


			} else if (cmd.getCommand().equals("teams_by_city")) {

				String city_name = cmd.getParameters()[0];
				String temp = city_name.replace('+',' ');
				//				System.out.println(city_name);
				//				System.out.println(temp);
				int i;

				System.out.println();
				for (i = 0; i < array_of_teams.size(); i++)
				{
					if (temp.equalsIgnoreCase(array_of_teams.get(i).Location))
						print_one_team(i);		
				}

			} else if (cmd.getCommand().equals("load_coaches")) {

				String file_name = cmd.getParameters()[0];
				System.out.println("Information from file " + file_name + " has been saved.");
				// FileInputStream fstream = new FileInputStream("file");
				File input_file = new File(file_name);

				try {
					Scanner scanner = new Scanner(input_file);
					scanner.useDelimiter(System.getProperty("line.separator"));

					if (scanner.hasNext())
						scanner.next();

					while (scanner.hasNext()) {

						Scanner line_scanner = new Scanner(scanner.next());
						line_scanner.useDelimiter("\\s*,\\s*");
						// line_scanner.useDelimiter("[,\n]+");
						// line_scanner.useDelimiter("[, \t]+");

						while (line_scanner.hasNext()) {
							// String temp = line_scanner.next();
							// System.out.println("value read = " + temp);

							coaches temp = new coaches();

							temp.Coach_ID = line_scanner.next();
							temp.season = line_scanner.nextInt();
							line_scanner.next(); // ignoring the 1
							temp.first_name = line_scanner.next();
							temp.last_name = line_scanner.next();
							temp.season_win = line_scanner.nextInt();
							temp.season_loss = line_scanner.nextInt();
							temp.playoff_win = line_scanner.nextInt();
							temp.playoff_loss = line_scanner.nextInt();
							temp.team = line_scanner.next();

							array_of_coaches.add(temp);
						}
						// System.out.println("now reading new line");
						line_scanner.close();
					}
					// System.out.println("now reading new line");
					// scanner.close();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}



			} else if (cmd.getCommand().equals("load_teams")) {

				String file_name = cmd.getParameters()[0];
				System.out.println("Information from file " + file_name + " has been saved.");
				//FileInputStream fstream = new FileInputStream("file");
				File input_file = new File(file_name);

				try {
					Scanner scanner = new Scanner(input_file);
					scanner.useDelimiter(System.getProperty("line.separator"));

					if (scanner.hasNext())
						scanner.next();

					while (scanner.hasNext())
					{

						Scanner line_scanner = new Scanner(scanner.next());
						line_scanner.useDelimiter("\\s*,\\s*");
						//line_scanner.useDelimiter("[,\n]+");
						//line_scanner.useDelimiter("[, \t]+");

						while (line_scanner.hasNext())
						{
							//						String temp = line_scanner.next();
							//						System.out.println("value read = " + temp);

							teams temp = new teams();

							temp.Team_ID = line_scanner.next();
							temp.Location = line_scanner.next();
							temp.Name = line_scanner.next();
							temp.League = line_scanner.next();


							array_of_teams.add(temp);
						}
						//System.out.println("now reading new line");
						line_scanner.close();
					}
					//System.out.println("now reading new line");
					//scanner.close();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else if (cmd.getCommand().equals("best_coach")) {

				String season_string = cmd.getParameters()[0];
				int season;
				if (isvalidint(season_string))
					season = Integer.parseInt(season_string);
				else
					continue;
				int i;
				int sum = -100;
				int index = 0;

				for (i = 0; i < array_of_coaches.size(); i++)
				{
					if (season == array_of_coaches.get(i).season)
					{
						int temp = ((array_of_coaches.get(i).season_win) - (array_of_coaches.get(i).season_loss))
								+ ((array_of_coaches.get(i).playoff_win)- (array_of_coaches.get(i).playoff_loss));

						if (sum < temp)
						{
							sum = temp;
							index = i;
						}


					}
				}
				//System.out.println("higest sum = " + sum);
				System.out.println();
				print_one_coach(array_of_coaches,index);



			} else if (cmd.getCommand().equals("search_coaches")) {

				String[] params = cmd.getParameters();
				String field, value;
				int i, j;


				ArrayList<coaches> temp_array = new ArrayList<coaches>(array_of_coaches);

				for (i = 0; i < params.length; i++){
					//					System.out.println(params[i]);

					Scanner scanner = new Scanner(params[i]);
					scanner.useDelimiter("=");
					field = scanner.next();
					value = scanner.next();
					//					System.out.println("field of para " + i + " is " + field);
					//					System.out.println("value of para " + i + " is " + value);

					search_single_query(temp_array, field, value);
				}

				System.out.println();
				for (j = 0; j < temp_array.size() ; j++)
					print_one_coach(temp_array,j);



			} else if (cmd.getCommand().equals("exit")) {
				System.out.println("Leaving the database, goodbye!");
				break;
			} else if (cmd.getCommand().equals("")) {
			} else {
				System.out.println("Invalid Command, try again!");
			} 

			if (result) {
				// ...
			}

			System.out.print("> "); 
		}        
	}

	private void search_single_query(ArrayList<coaches> temp, String field, String value){
		int i;

		if (field.equalsIgnoreCase("Coach_ID")){

			for (i = 0; i < temp.size(); i++) {
				if (!(value.equalsIgnoreCase(temp.get(i).Coach_ID))){
					temp.remove(i);
					--i;
				}
			}

		} else if (field.equalsIgnoreCase("season")){
			int int_value = Integer.parseInt(value);

			for (i = 0; i < temp.size(); i++) {
				if (int_value != temp.get(i).season){
					temp.remove(i);
					--i;
				}
			}

		} else if (field.equalsIgnoreCase("first_name")){
			String temp_value = value.replace('+',' ');
			for (i = 0; i < temp.size(); i++) {
				if (!(temp_value.equalsIgnoreCase(temp.get(i).first_name))){
					temp.remove(i);
					--i;
				}
			}

		} else if (field.equalsIgnoreCase("last_name")){
			String temp_value = value.replace('+',' ');
			for (i = 0; i < temp.size(); i++) {
				if (!(temp_value.equalsIgnoreCase(temp.get(i).last_name))){
					temp.remove(i);
					--i;
				}
			}

		} else if (field.equalsIgnoreCase("season_win")) {
			int int_value = Integer.parseInt(value);

			for (i = 0; i < temp.size(); i++) {
				if (int_value != temp.get(i).season_win){
					temp.remove(i);
					--i;
				}
			}

		} else if (field.equalsIgnoreCase("season_loss")) {
			int int_value = Integer.parseInt(value);

			for (i = 0; i < temp.size(); i++) {
				if (int_value != temp.get(i).season_loss){
					temp.remove(i);
					--i;
				}
			}

		} else if (field.equalsIgnoreCase("playoff_win")) {
			int int_value = Integer.parseInt(value);

			for (i = 0; i < temp.size(); i++) {
				if (int_value != temp.get(i).playoff_win){
					temp.remove(i);
					--i;
				}
			}

		} else if (field.equalsIgnoreCase("playoff_loss")){
			int int_value = Integer.parseInt(value);

			for (i = 0; i < temp.size(); i++) {
				if (int_value != temp.get(i).playoff_loss){
					temp.remove(i);
					--i;
				}
			}

		} else if (field.equalsIgnoreCase("team")) {

			for (i = 0; i < temp.size(); i++) {
				if (!(value.equalsIgnoreCase(temp.get(i).team))){
					temp.remove(i);
					--i;
				}
			}

		} else {
			System.out.println("Could not find the search field " + field + " and was thus ignored.");
		}

	}
	
	private boolean isvalidint(String str){
		int i = 0;
		while(i < str.length())
		{
			if (!Character.isDigit(str.charAt(i)))
				break;
			i++;
		}
		if (i == str.length())
			return true;
		else{
			System.out.println("The input " + str + " is not valid integer. Please try again.");
			return false;
		}
	}


	private void print_one_coach(ArrayList<coaches> temp, int i) {

		System.out.print(temp.get(i).Coach_ID); 
		if (temp.get(i).Coach_ID.length() < 8)
			System.out.print("\t\t");
		else
			System.out.print("\t");

		System.out.print(temp.get(i).season); System.out.print("\t");
		System.out.print(temp.get(i).first_name); System.out.print("\t");
		System.out.print(temp.get(i).last_name);
		if (temp.get(i).last_name.length() < 8)
			System.out.print("\t\t");
		else
			System.out.print("\t");
		System.out.print(temp.get(i).season_win); System.out.print("\t");
		System.out.print(temp.get(i).season_loss); System.out.print("\t");
		System.out.print(temp.get(i).playoff_win); System.out.print("\t");
		System.out.print(temp.get(i).playoff_loss); System.out.print("\t");
		System.out.println(temp.get(i).team);

	}

	private void print_one_team(int i)
	{
		System.out.print(array_of_teams.get(i).Team_ID); System.out.print("\t");
		System.out.print(array_of_teams.get(i).Location);
		if (array_of_teams.get(i).Location.length() < 8)
			System.out.print("\t\t");
		else
			System.out.print("\t");

		System.out.print(array_of_teams.get(i).Name);
		if (array_of_teams.get(i).Name.length() < 8)
			System.out.print("\t\t");
		else
			System.out.print("\t");

		System.out.println(array_of_teams.get(i).League);
	}

	private boolean doHelp() {
		System.out.println("add_coach ID SEASON FIRST_NAME LAST_NAME SEASON_WIN "); 
		System.out.println("          EASON_LOSS PLAYOFF_WIN PLAYOFF_LOSS TEAM - add new coach data");
		System.out.println("add_team ID LOCATION NAME LEAGUE - add a new team");
		System.out.println("print_coaches - print a listing of all coaches");
		System.out.println("print_teams - print a listing of all teams");
		System.out.println("coaches_by_name NAME - list info of coaches with the specified name");
		System.out.println("teams_by_city CITY - list the teams in the specified city");
		System.out.println("load_coach FILENAME - bulk load of coach info from a file");
		System.out.println("load_team FILENAME - bulk load of team info from a file");
		System.out.println("best_coach SEASON - print the name of the coach with the most netwins in a specified season");
		System.out.println("search_coaches field=VALUE - print the name of the coach satisfying the specified conditions");
		System.out.println("exit - quit the program");        
		return true;
	}



	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new P1().run();
	}



}
