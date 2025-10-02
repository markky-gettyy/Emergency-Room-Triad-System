public class Main {

    public static void main(String[] args) {

        ERQueue erQueue = new ERQueue();

        

        // Patients arriving at the ER with their details

        erQueue.arrive("Pedro Cruz", 1, "Head injury - NOW UNCONSCIOUS ⚠", "23:52");

        erQueue.arrive("Carlos Mendoza", 2, "Compound fracture - leg", "23:50");

        erQueue.arrive("Lisa Wang", 2, "Severe asthma attack", "23:56");

        erQueue.arrive("Maria Santos", 3, "Deep laceration on arm", "23:48");

        erQueue.arrive("Ana Lim", 4, "Sprained ankle", "23:49");

        

        // Display the queue after all patients have arrived

        erQueue.displayQueue();



        // Treat the next patient (highest priority)

        erQueue.treatNext();

        

        // Display the remaining patients in the queue

        erQueue.displayQueue();

    }

}





ERAQUEUE



import java.util.PriorityQueue;



public class ERQueue {

  private PriorityQueue<Patient> queue;



  public ERQueue() {

    // PriorityQueue is used to order patients based on their priority and arrival time

    queue = new PriorityQueue<>();

  }



  // Method to add a patient to the queue

  public void arrive(String name, int priority, String condition, String time) {

    Patient newPatient = new Patient(name, priority, condition, time);

    queue.offer(newPatient);

    System.out.println("Patient " + name + " has arrived and is added to the queue.");

  }



  // Method to treat the patient with the highest priority (remove from the queue)

  public void treatNext() {

    if (queue.isEmpty()) {

      System.out.println("No patients to treat.");

      return;

    }

    Patient treatedPatient = queue.poll(); // Removes and returns the highest-priority patient

    System.out.println(">>> Treating patient now...");

    System.out.println("Treated: " + treatedPatient);

    // Inside ERQueue class after treating a patient





  }



  // Method to display the queue of patients

  public void displayQueue() {

    if (queue.isEmpty()) {

      System.out.println("No patients in the queue.");

      return;

    }



    System.out.println("=== UPDATED QUEUE ===");

    System.out.println("Patients Waiting: " + queue.size());

    int i = 1;

    for (Patient patient : queue) {

      System.out.println(i + ". " + patient);

      i++;

    }

  }

}





PATIENT



public class Patient implements Comparable<Patient> {

  private String name;

  private int priority; // 1 is the highest priority, 4 is the lowest

  private String condition;

  private String arrivalTime;



  // Constructor to initialize a new Patient

  public Patient(String name, int priority, String condition, String arrivalTime) {

     this.name = name;

    this.priority = priority;

    this.condition = condition;

    this.arrivalTime = arrivalTime;

  }



  // Getter methods

  public String getName() {

    return name;

  }



  public int getPriority() {

    return priority;

  }



  public String getCondition() {

    return condition;

  }



  public String getArrivalTime() {

    return arrivalTime;

  }



  // compareTo method to define the sorting order: priority first, then arrival time

  @Override

  public int compareTo(Patient otherPatient) {

    // First, compare by priority (ascending: lower number = higher priority)

    if (this.priority < otherPatient.priority) {

      return -1;

    } else if (this.priority > otherPatient.priority) {

      return 1;

    }

    // If priority is the same, compare by arrival time

    return this.arrivalTime.compareTo(otherPatient.arrivalTime);

  }



  @Override

  public String toString() {

    return String.format("[P%d] %s - %s (%s)", priority, name, condition, arrivalTime);

  }

}

Message
Type Message
