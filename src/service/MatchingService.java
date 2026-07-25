package service;

import enums.BloodGroup;
import exceptions.NoMatchFoundException;
import model.Donor;
import model.Recipient;

import java.util.*;

public class MatchingService {

    private List<Donor> donors;
    private PriorityQueue<Recipient> recipientQueue;

    public MatchingService() {
        donors = new ArrayList<>();

        // PriorityQueue needs a Comparator to know order - higher urgency served first
        recipientQueue = new PriorityQueue<>(
                (r1, r2) -> r2.getUrgency().getPriorityLevel() - r1.getUrgency().getPriorityLevel()
        );
    }

    public void addDonor(Donor donor) {
        donors.add(donor);
    }

    public void addRecipient(Recipient recipient) {
        recipientQueue.add(recipient);
    }

    public List<Donor> getDonors() {
        return donors;
    }

    // finds first available compatible donor for the given recipient
    public Donor findDonorFor(Recipient recipient) throws NoMatchFoundException {
        BloodGroup neededGroup = recipient.getBloodGroup();

        for (Donor d : donors) {
            if (d.isAvailable() && CompatibilityService.isCompatible(d.getBloodGroup(), neededGroup)) {
                return d;
            }
        }

        throw new NoMatchFoundException("No compatible donor found for " + recipient.getName() +
                " (needs " + neededGroup.getLabel() + ")");
    }

    // processes recipients in priority order (CRITICAL first, LOW last)
    public void processAllRecipients() {
        while (!recipientQueue.isEmpty()) {
            Recipient recipient = recipientQueue.poll(); // removes highest priority first
            System.out.println("\nProcessing: " + recipient.getName() +
                    " | Urgency: " + recipient.getUrgency());

            try {
                Donor matchedDonor = findDonorFor(recipient);
                matchedDonor.setAvailable(false);
                recipient.setFulfilled(true);

                System.out.println("✅ Match found! Donor: " + matchedDonor.getName() +
                        " (" + matchedDonor.getBloodGroup().getLabel() + ") -> " +
                        recipient.getName() + " (" + recipient.getBloodGroup().getLabel() + ")");

            } catch (NoMatchFoundException e) {
                System.out.println("❌ " + e.getMessage());
            }
        }
    }

    public boolean hasPendingRecipients() {
        return !recipientQueue.isEmpty();
    }
}