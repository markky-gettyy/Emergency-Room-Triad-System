package markk;

import java.util.PriorityQueue;
import java.util.Scanner;

public class Priority {

    public static void main(String[] args) {
        ERQueue er = new ERQueue();
        Scanner sc = new Scanner(System.in);

        System.out.println("Emergency Room Triage System");
        System.out.println("Commands: arrive | treat | display | exit");

        while (true) {
            System.out.print("\nEnter command: ");
            String cmd = sc.nextLine().trim().toLowerCase();

            if (cmd.equals("arrive")) {
                System.out.print("Patient name: ");
                String name = sc.nextLine();
                System.out.print("Priority (1=Critical, 2=Serious, 3=Stable, 4=Minor): ");
                int priority = Integer.parseInt(sc.nextLine());
                System.out.print("Condition: ");
                String condition = sc.nextLine();
                System.out.print("Arrival time (HH:mm): ");
                String time = sc.nextLine();

                er.arrive(name, priority, condition, time);

            } else if (cmd.equals("treat")) {
                er.treatNext();

            } else if (cmd.equals("display")) {
                er.displayQueue();

            } else if (cmd.equals("exit")) {
                System.out.println("Exiting...");
                break;

            } else {
                System.out.println("Unknown command. Try: arrive | treat | display | exit");
            }
        }
        sc.close();
    }
}

// ERQueue class manages the priority queue of patients
class ERQueue {

    private PriorityQueue<Patient> queue;

    public ERQueue() {
        queue = new PriorityQueue<>();
    }

    public void arrive(String name, int priority, String condition, String time) {
        Patient newPatient = new Patient(name, priority, condition, time);
        queue.offer(newPatient);
        System.out.println("Patient " + name + " has arrived and is added to the queue.");
    }

    public void treatNext() {
        if (queue.isEmpty()) {
            System.out.println("No patients to treat.");
            return;
        }
        Patient treatedPatient = queue.poll();
        System.out.println(">>> Treating patient now...");
        System.out.println("Treated: " + treatedPatient);
    }

    public void displayQueue() {
        if (queue.isEmpty()) {
            System.out.println("No patients in the queue.");
            return;
        }
        System.out.println("=== UPDATED QUEUE ===");
        System.out.println("Patients Waiting: " + queue.size());
        PriorityQueue<Patient> tempQueue = new PriorityQueue<>(queue);
        int i = 1;
        while (!tempQueue.isEmpty()) {
            System.out.println(i + ". " + tempQueue.poll());
            i++;
        }
    }
}

class Patient implements Comparable<Patient> {

    private String name;
    private int priority;
    private String condition;
    private String arrivalTime;

    public Patient(String name, int priority, String condition, String arrivalTime) {
        this.name = name;
        this.priority = priority;
        this.condition = condition;
        this.arrivalTime = arrivalTime;
    }

    @Override
    public int compareTo(Patient otherPatient) {
        if (this.priority != otherPatient.priority) {
            return Integer.compare(this.priority, otherPatient.priority);
        }
        return this.arrivalTime.compareTo(otherPatient.arrivalTime);
    }

    @Override
    public String toString() {
        return String.format("[P%d] %s - %s (%s)", priority, name, condition, arrivalTime);
    }
}
