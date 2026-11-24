import java.util.*;

public class Main {

    // ArrayList to store questions
    static String[] questions = {
        "What is Java?",
        "Which data structure uses LIFO?",
        "Which data structure uses FIFO?",
        "Which algorithm is used for searching?",
        "Which is a sorting algorithm?"
    };

    static String[][] options = {
        {"A. Language", "B. Database", "C. Browser", "D. Hardware"},
        {"A. Queue", "B. Stack", "C. Array", "D. Tree"},
        {"A. Stack", "B. Tree", "C. Queue", "D. Graph"},
        {"A. Binary Search", "B. Bubble Sort", "C. Merge Sort", "D. DFS"},
        {"A. BFS", "B. DFS", "C. Bubble Sort", "D. Binary Search"}
    };

    static String[] answers = {"A", "B", "C", "A", "C"};

    // Queue for students
    static Queue<Student> studentQueue = new LinkedList<>();

    // ArrayList for results
    static ArrayList<Student> results = new ArrayList<>();

    // Stack for answer history
    static Stack<String> answerStack = new Stack<>();

    static Scanner sc = new Scanner(System.in);

    static class Student {
        String name;
        String roll;
        int score;

        Student(String name, String roll) {
            this.name = name;
            this.roll = roll;
            this.score = 0;
        }
    }

    // Register Student
    static void registerStudent() {
        System.out.print("Enter Student Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Roll Number: ");
        String roll = sc.nextLine();

        studentQueue.add(new Student(name, roll));

        System.out.println("Student Registered Successfully!");
    }

    // Start Examination
    static void startExam() {

        if (studentQueue.isEmpty()) {
            System.out.println("Please Register First!");
            return;
        }

        Student student = studentQueue.poll();

        System.out.println("\nWelcome " + student.name);
        System.out.println("Examination Started!");

        answerStack.clear();

        for (int i = 0; i < questions.length; i++) {

            System.out.println("\nQ" + (i + 1) + ". " + questions[i]);

            for (String option : options[i]) {
                System.out.println(option);
            }

            System.out.print("Enter Answer (A/B/C/D): ");
            String answer = sc.nextLine().toUpperCase();

            while (!answer.matches("[ABCD]")) {
                System.out.print("Invalid! Enter A/B/C/D: ");
                answer = sc.nextLine().toUpperCase();
            }

            // Push answer into Stack
            answerStack.push(answer);

            if (answer.equals(answers[i])) {
                student.score++;
            }
        }

        System.out.println("\nExam Completed!");
        System.out.println("Your Score: " + student.score
                + "/" + questions.length);

        results.add(student);
    }

    // Linear Search
    static void searchResult() {

        System.out.print("Enter Roll Number: ");
        String roll = sc.nextLine();

        for (Student s : results) {
            if (s.roll.equals(roll)) {
                System.out.println("Student Found!");
                System.out.println("Name: " + s.name);
                System.out.println("Roll: " + s.roll);
                System.out.println("Score: " + s.score);
                return;
            }
        }

        System.out.println("Student Not Found!");
    }

    // Bubble Sort
    static void sortResults() {

        if (results.isEmpty()) {
            System.out.println("No Results Available!");
            return;
        }

        for (int i = 0; i < results.size(); i++) {
            for (int j = 0; j < results.size() - i - 1; j++) {

                if (results.get(j).score <
                    results.get(j + 1).score) {

                    Student temp = results.get(j);
                    results.set(j, results.get(j + 1));
                    results.set(j + 1, temp);
                }
            }
        }

        System.out.println("\n----- LEADERBOARD -----");

        for (Student s : results) {
            System.out.println(s.name + " - " + s.score);
        }
    }

    // Main Menu
    public static void main(String[] args) {

        while (true) {

            System.out.println("\n==========================");
            System.out.println(" ONLINE EXAMINATION SYSTEM");
            System.out.println("==========================");

            System.out.println("1. Register Student");
            System.out.println("2. Start Examination");
            System.out.println("3. Search Result");
            System.out.println("4. Sort Results");
            System.out.println("5. Exit");

            System.out.print("Enter Your Choice: ");

            String choice = sc.nextLine();

            switch (choice) {

                case "1":
                    registerStudent();
                    break;

                case "2":
                    startExam();
                    break;

                case "3":
                    searchResult();
                    break;

                case "4":
                    sortResults();
                    break;

                case "5":
                    System.out.println("Thank You!");
                    return;

                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }
}