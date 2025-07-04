# Setup cell
!apt-get install openjdk-11-jdk-headless -qq > /dev/null
  %%writefile FlightSystem.java
import java.util.*;

class Flight {
    String airline;
    String destination;
    int cost;
    int time;
    String flightNo;
    
    public Flight(String airline, String destination, int cost, int time, String flightNo) {
        this.airline = airline;
        this.destination = destination;
        this.cost = cost;
        this.time = time;
        this.flightNo = flightNo;
    }
}

class Node implements Comparable<Node> {
    String city;
    int cost;
    int time;
    List<Flight> path;

    public Node(String city, int cost, int time, List<Flight> path) {
        this.city = city;
        this.cost = cost;
        this.time = time;
        this.path = new ArrayList<>(path);
    }

    @Override
    public int compareTo(Node other) {
        return Integer.compare(this.cost, other.cost);
    }
}

public class FlightSystem {
    static Map<String, List<Flight>> flightsGraph = new HashMap<>();
    
    public static void addFlight(String source, String destination, String airline, 
                               int cost, int time, String flightNo) {
        flightsGraph.putIfAbsent(source, new ArrayList<>());
        flightsGraph.get(source).add(new Flight(airline, destination, cost, time, flightNo));
    }
    
    public static void initializeFlights() {
        // Delhi Routes
        addFlight("Delhi", "Mumbai", "Indigo", 4500, 120, "6E-201");
        addFlight("Delhi", "Mumbai", "Vistara", 6000, 110, "UK-901");
        addFlight("Delhi", "Bangalore", "Air India", 6500, 150, "AI-801");
        addFlight("Delhi", "Bangalore", "SpiceJet", 5500, 160, "SG-401");
        addFlight("Delhi", "Hyderabad", "Indigo", 5000, 130, "6E-301");
        addFlight("Delhi", "Chennai", "Air India", 7000, 180, "AI-701");
        addFlight("Delhi", "Kolkata", "Indigo", 4000, 120, "6E-501");
        addFlight("Delhi", "Goa", "Go First", 6500, 150, "G8-101");
        
        // Mumbai Routes
        addFlight("Mumbai", "Delhi", "Vistara", 5800, 115, "UK-902");
        addFlight("Mumbai", "Bangalore", "Indigo", 3500, 90, "6E-202");
        addFlight("Mumbai", "Chennai", "Air India", 4000, 100, "AI-702");
        addFlight("Mumbai", "Hyderabad", "SpiceJet", 3800, 95, "SG-402");
        addFlight("Mumbai", "Goa", "Indigo", 2500, 60, "6E-302");
        
        // Bangalore Routes
        addFlight("Bangalore", "Delhi", "Vistara", 6200, 155, "UK-903");
        addFlight("Bangalore", "Mumbai", "Air Asia", 3300, 85, "I5-201");
        addFlight("Bangalore", "Hyderabad", "Indigo", 2800, 70, "6E-203");
        addFlight("Bangalore", "Chennai", "SpiceJet", 2000, 60, "SG-403");
        addFlight("Bangalore", "Kochi", "Indigo", 3500, 90, "6E-303");
        
        // Other Routes
        addFlight("Hyderabad", "Chennai", "Air India", 3200, 80, "AI-703");
        addFlight("Chennai", "Kolkata", "Indigo", 4500, 120, "6E-502");
        addFlight("Kolkata", "Guwahati", "SpiceJet", 3800, 100, "SG-404");
        addFlight("Goa", "Delhi", "Vistara", 6300, 155, "UK-904");
        addFlight("Pune", "Bangalore", "Indigo", 3000, 75, "6E-204");
    }
    
    public static void findCheapestFlight(String src, String dest) {
        if (!flightsGraph.containsKey(src)) {
            System.out.println("No flights available from " + src);
            return;
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        Map<String, Integer> minCost = new HashMap<>();
        pq.add(new Node(src, 0, 0, new ArrayList<>()));
        minCost.put(src, 0);
        boolean found = false;

        while (!pq.isEmpty()) {
            Node currentNode = pq.poll();
            String currentCity = currentNode.city;

            if (currentCity.equals(dest)) {
                System.out.println("\n✈️ Cheapest Route Found ✈️");
                System.out.println("──────────────────────────");
                System.out.println("Total Cost: ₹" + currentNode.cost);
                System.out.println("Total Time: " + currentNode.time + " minutes");
                System.out.println("Flight Path:");
                System.out.println("──────────────────────────");
                String fromCity = src;
                for (Flight flight : currentNode.path) {
                    System.out.printf("• %s → %s\n  %s (%s) | ₹%d | %d mins\n",
                                    fromCity, flight.destination, 
                                    flight.airline, flight.flightNo,
                                    flight.cost, flight.time);
                    fromCity = flight.destination;
                }
                found = true;
                break;
            }

            for (Flight flight : flightsGraph.getOrDefault(currentCity, new ArrayList<>())) {
                int newCost = currentNode.cost + flight.cost;
                if (newCost < minCost.getOrDefault(flight.destination, Integer.MAX_VALUE)) {
                    minCost.put(flight.destination, newCost);
                    List<Flight> newPath = new ArrayList<>(currentNode.path);
                    newPath.add(flight);
                    pq.add(new Node(flight.destination, newCost, 
                                  currentNode.time + flight.time, newPath));
                }
            }
        }
        
        if (!found) {
            System.out.println("No route available from " + src + " to " + dest);
        }
    }

    public static void main(String[] args) {
        initializeFlights();
        
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n🏨 Flight Route Finder 🏨");
            System.out.println("1. Find cheapest flight");
            System.out.println("2. List all cities");
            System.out.println("3. Exit");
            System.out.print("Choose option: ");
            
            int choice = sc.nextInt();
            if (choice == 3) break;
            
            if (choice == 2) {
                System.out.println("\nAvailable Cities:");
                System.out.println("────────────────");
                flightsGraph.keySet().stream().sorted()
                    .forEach(city -> System.out.println("• " + city));
                continue;
            }
            
            System.out.print("Enter departure city: ");
            String from = sc.next();
            System.out.print("Enter arrival city: ");
            String to = sc.next();
            
            findCheapestFlight(from, to);
        }
        sc.close();
        System.out.println("\nThank you for using our Flight System! ✈️");
    }
}
# Compile and Run
!javac FlightSystem.java
!java FlightSystem
