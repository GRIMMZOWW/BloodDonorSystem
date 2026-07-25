import enums.BloodGroup;
import enums.Urgency;
import exceptions.InvalidBloodGroupException;
import model.Donor;
import model.Recipient;
import service.FileService;
import service.MatchingService;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        MatchingService matchingService = new MatchingService();
        FileService fileService = new FileService();

        // load saved donors on startup
        List<Donor> savedDonors = fileService.loadDonors();
        for (Donor d : savedDonors) {
            matchingService.addDonor(d);
        }
        System.out.println("Loaded " + savedDonors.size() + " donor(s) from file.");

        boolean running = true;

        while (running) {
            System.out.println("\n=================================");
            System.out.println("   🩸 BLOOD DONOR MATCHING SYSTEM");
            System.out.println("=================================");
            System.out.println("1. Add Donor");
            System.out.println("2. Add Recipient (Request)");
            System.out.println("3. Process All Requests");
            System.out.println("4. View All Donors");
            System.out.println("5. Save & Exit");
            System.out.print("Enter choice: ");

            int choice;
            try {
                choice = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Please enter a number, not text.");
                sc.nextLine(); // clear bad input from scanner
                continue; // skip rest of loop, show menu again
            }

            switch (choice) {

                case 1:
                    try {
                        System.out.print("Donor Name: ");
                        String name = sc.nextLine();
                        System.out.print("Age: ");
                        int age = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Blood Group (e.g. A_POS, O_NEG, AB_POS): ");
                        String groupInput = sc.nextLine().toUpperCase();

                        BloodGroup group = parseBloodGroup(groupInput);
                        Donor donor = new Donor(name, age, group);
                        matchingService.addDonor(donor);

                        System.out.println("✅ Donor added successfully!");
                    } catch (InvalidBloodGroupException e) {
                        System.out.println("❌ " + e.getMessage());
                    }
                    break;

                case 2:
                    try {
                        System.out.print("Recipient Name: ");
                        String rname = sc.nextLine();
                        System.out.print("Age: ");
                        int rage = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Blood Group Needed (e.g. A_POS, O_NEG): ");
                        String rGroupInput = sc.nextLine().toUpperCase();
                        BloodGroup rGroup = parseBloodGroup(rGroupInput);

                        System.out.print("Urgency (LOW, MEDIUM, HIGH, CRITICAL): ");
                        String urgencyInput = sc.nextLine().toUpperCase();
                        Urgency urgency = Urgency.valueOf(urgencyInput);

                        Recipient recipient = new Recipient(rname, rage, rGroup, urgency);
                        matchingService.addRecipient(recipient);

                        System.out.println("✅ Recipient request added!");
                    } catch (InvalidBloodGroupException | IllegalArgumentException e) {
                        System.out.println("❌ Invalid input: " + e.getMessage());
                    }
                    break;

                case 3:
                    if (!matchingService.hasPendingRecipients()) {
                        System.out.println("No pending recipient requests.");
                    } else {
                        matchingService.processAllRecipients();
                    }
                    break;

                case 4:
                    System.out.println("\n--- All Donors ---");
                    for (Donor d : matchingService.getDonors()) {
                        d.display();
                    }
                    break;

                case 5:
                    fileService.saveDonors(matchingService.getDonors());
                    System.out.println("💾 Data saved. Goodbye!");
                    running = false;
                    break;

                default:
                    System.out.println("❌ Invalid choice, try again.");
            }
        }

        sc.close();
    }

    // helper method - validates blood group input, throws custom exception if wrong
    private static BloodGroup parseBloodGroup(String input) throws InvalidBloodGroupException {
        try {
            return BloodGroup.valueOf(input);
        } catch (IllegalArgumentException e) {
            throw new InvalidBloodGroupException("Invalid blood group: " + input +
                    " (use format like A_POS, O_NEG, AB_POS)");
        }
    }
}