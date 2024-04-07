import java.util.Scanner;
public class Fort {
	private String name;
	private boolean visited;
	
	public Fort(String name) {
		this.name = name;
		this.visited = false;
	}
	
	public String getName() {
		return name;
	}
	
	public void visit(Wagon wagon) {
		this.visited = true;
		System.out.println("You've arried at " + this.name +". What would you like to do?");
		Scanner scanner = new Scanner(System.in);
		while(true) {
			System.out.println("\nOptions:");
			System.out.println("1. Shop");
			System.out.println("2. Talk to people");
			System.out.println("3. Look Around");
			System.out.println("4. Rest");
			System.out.println("5. Continue Traveling");
			System.out.println("Enter your choice (1-5):");
			String choice = scanner.nextLine();
			
			switch(choice) {
			case "1":
                shop(wagon);
                break;
            case "2":
                talkToPeople();
                break;
            case "3":
                lookAround();
                break;
            case "4":
                rest(wagon);
                break;
	    case "5":
		System.out.println("You continue your journey from the fort.");	
			}
		}
		
		private void shop(Wagon wagon) {
			System.out.println(" ");
		}
		}
		
		private void talkToPeople() {
			
		}
		
		private void lookAround() {
			
		}
		
		private void rest(Wagon wagon) {
			
		}
	}
}
