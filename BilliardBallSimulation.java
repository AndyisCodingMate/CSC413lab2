import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.lang.Math;
public class BilliardBallSimulation {

    private static final double GRAVITY = 9.81; // m/s^2

    public static void main(String[] args) {
        // Simulation initial condition: starting height of the ball (yi)
    
        Scanner scanner = new Scanner(System.in);
    
        System.out.println("Please enter simulation parameters\n");
        System.out.print("Initial Height (in meters): ");

        double yi = scanner.nextDouble(); // Initial height in meters

        System.out.println();
        System.out.println("Simulating...\n");
        
        // Simulation parameters
        double t = 0.0; // Initial time
        double timeInterval = 1.0; // Time interval in seconds

        // Output CSV file
        String csvFile = "simulation_data.csv";
        try {
            FileWriter writer = new FileWriter(csvFile);

            // Writing the headers to the CSV file
            writer.append("time,height,velocity\n");

            // Simulating the descent of the billiard ball
            while (true) {
                double height = yi - 0.5 * GRAVITY * Math.pow(t, 2); // Calculating the height
                if (height <= 0) {
                    double velocity = GRAVITY * t; // Calculating the velocity at the time the ball hits the ground
                    writer.append(String.format("%.1f,%.1f,%.1f\n", t, 0.0, velocity)); // Writing the data to the CSV file
                    break; // Break the loop when the ball hits the ground
                } else {
                    double velocity = GRAVITY * t; // Calculating the velocity
                    writer.append(String.format("%.1f,%.1f,%.1f\n", t, height, velocity)); // Writing the data to the CSV file
                    t += timeInterval; // Incrementing time
                }
            }
            writer.flush();
            writer.close();
            scanner.close();
            System.out.println("Simulation data has been written to " + csvFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
