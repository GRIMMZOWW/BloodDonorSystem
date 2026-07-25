package service;

import enums.BloodGroup;

public class CompatibilityService {

    // checks if donorGroup can safely give blood to recipientGroup
    // rule: O_NEG can give to everyone, AB_POS can receive from everyone
    public static boolean isCompatible(BloodGroup donorGroup, BloodGroup recipientGroup) {

        if (recipientGroup == BloodGroup.O_NEG) {
            return donorGroup == BloodGroup.O_NEG;
        }

        if (recipientGroup == BloodGroup.O_POS) {
            return donorGroup == BloodGroup.O_NEG || donorGroup == BloodGroup.O_POS;
        }

        if (recipientGroup == BloodGroup.A_NEG) {
            return donorGroup == BloodGroup.O_NEG || donorGroup == BloodGroup.A_NEG;
        }

        if (recipientGroup == BloodGroup.A_POS) {
            return donorGroup == BloodGroup.O_NEG || donorGroup == BloodGroup.O_POS ||
                   donorGroup == BloodGroup.A_NEG || donorGroup == BloodGroup.A_POS;
        }

        if (recipientGroup == BloodGroup.B_NEG) {
            return donorGroup == BloodGroup.O_NEG || donorGroup == BloodGroup.B_NEG;
        }

        if (recipientGroup == BloodGroup.B_POS) {
            return donorGroup == BloodGroup.O_NEG || donorGroup == BloodGroup.O_POS ||
                   donorGroup == BloodGroup.B_NEG || donorGroup == BloodGroup.B_POS;
        }

        if (recipientGroup == BloodGroup.AB_NEG) {
            return donorGroup == BloodGroup.O_NEG || donorGroup == BloodGroup.A_NEG ||
                   donorGroup == BloodGroup.B_NEG || donorGroup == BloodGroup.AB_NEG;
        }

        if (recipientGroup == BloodGroup.AB_POS) {
            return true; // AB+ accepts blood from everyone
        }

        return false;
    }
}