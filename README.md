# Blood Donor Matching System

Simple console-based Java project to manage blood donors and recipients, and match them based on blood group compatibility and urgency.

## What it does

1. Add Donor — name, age, blood group
2. Add Recipient (Request) — name, age, blood group needed, urgency (LOW/MEDIUM/HIGH/CRITICAL)
3. Process All Requests — matches recipients to compatible available donors, most urgent recipient first
4. View All Donors — shows every donor and their availability
5. Save & Exit — saves donor list to `donors.txt` so data persists between runs

## Project structure

```
src/
  Main.java                          - menu loop, user input
  enums/
    BloodGroup.java                  - A_POS, A_NEG, B_POS, B_NEG, AB_POS, AB_NEG, O_POS, O_NEG
    Urgency.java                     - LOW, MEDIUM, HIGH, CRITICAL
  model/
    Person.java                      - abstract parent class (name, age, blood group)
    Donor.java                       - extends Person, adds availability + last donation date
    Recipient.java                   - extends Person, adds urgency + fulfilled status
  exceptions/
    InvalidBloodGroupException.java  - thrown on bad blood group input
    NoMatchFoundException.java       - thrown when no compatible donor exists
  service/
    CompatibilityService.java        - checks if a donor's blood group matches a recipient's need
    MatchingService.java             - stores donors/recipients, finds matches, processes by urgency
    FileService.java                 - saves/loads donor list to/from donors.txt
```

## How to run

Compile and run `Main.java` from the `src` folder using any Java IDE (Eclipse) or:

```
javac -d bin src/Main.java src/**/*.java
java -cp bin Main
```

## Blood group compatibility rules

O_NEG is the universal donor (can give to everyone). AB_POS is the universal recipient (can receive from everyone). Full rules are handled in `CompatibilityService.java`.

## Notes

- `donors.txt` is created automatically when you save — not needed on first run.
- Urgency order when processing requests: CRITICAL > HIGH > MEDIUM > LOW.
