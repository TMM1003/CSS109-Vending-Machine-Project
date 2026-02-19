// VendingMachine.java
// Assignment 4: Working Together — Vending Machine (TODO-only skeleton)

// REQUIREMENTS OWNERSHIP SPLIT:

// Partner A — Thomas (Bullets 1–4, 8)
// 1) Large variety of snacks
// 2) Each snack has a price
// 3) Welcome + usage instructions
// 4) User selects snack
// 8) Departure message

// Partner B — Jordan (Bullets 5–7)
// 5) Accept unlimited $1 bills only
// 6) Exact change returned
// 7) Confirmation before transaction + full refund on cancel

import java.util.*;

public class VendingMachine {

    // ============================================================
    // SHARED STATE (Both partners may read; modify only your area)
    // ============================================================

    // Partner A (Thomas): owns inventory structure and formatting
    private final List<Snack> inventory = new ArrayList<>();

    // Partner B (Jordan): owns all money handling logic
    private int insertedAmount = 0;

    // Shared transaction state
    private Snack selectedSnack = null;

    private final Scanner scanner = new Scanner(System.in);

    // ============================================================
    // ENTRY POINT
    // ============================================================

    public static void main(String[] args) {
        // TODO (Thomas + Jordan integration):
        // Create VendingMachine instance and call run()
    }

    // ============================================================
    // TRANSACTION FLOW (Integration Layer — both coordinate here)
    // ============================================================

    public void run() {
        loadDefaultInventory();
        displayWelcomeAndInstructions();

        displaySnackMenu();
        selectedSnack = promptForSnackSelection();

        if (!acceptMoneyUntilReadyOrCancel(selectedSnack.getPrice())) {
            refundFullAmount();
            printDepartureMessage(null);
            resetTransactionState();
            return;
        }

        if (!promptForConfirmation(selectedSnack, selectedSnack.getPrice(), insertedAmount)) {
            refundFullAmount();
            printDepartureMessage(null);
            resetTransactionState();
            return;
        }

        System.out.println("Dispensing: " + selectedSnack.getName());
        int change = calculateChange(selectedSnack.getPrice());
        returnChange(change);
        printDepartureMessage(selectedSnack);
        resetTransactionState();
    }

    // ============================================================
    // PARTNER A — THOMAS (Bullets 1–4, 8)
    // ============================================================

    // Bullet 1 & 2: Large variety + price per snack
    private void loadDefaultInventory() {
        inventory.clear();
        inventory.add(new Snack("Classic Chips", 2));
        inventory.add(new Snack("BBQ Chips", 2));
        inventory.add(new Snack("Pretzels", 2));
        inventory.add(new Snack("Chocolate Bar", 3));
        inventory.add(new Snack("Granola Bar", 2));
        inventory.add(new Snack("Gummy Bears", 2));
        inventory.add(new Snack("Trail Mix", 3));
        inventory.add(new Snack("Peanuts", 2));
        inventory.add(new Snack("Cookies", 3));
        inventory.add(new Snack("Crackers", 2));
    }

    // Bullet 3: Welcome + instructions
    private void displayWelcomeAndInstructions() {
        System.out.println("Welcome to the CSC109 Vending Machine!");
        System.out.println("Select a snack by entering its menu number.");
        System.out.println("This machine accepts only $1 bills.");
        System.out.println("You will be asked to confirm before purchase.");
        System.out.println("You can cancel during payment or at confirmation.");
    }

    // Bullet 1 & 4: Display menu
    private void displaySnackMenu() {
        System.out.println();
        System.out.println("Available snacks:");
        for (int i = 0; i < inventory.size(); i++) {
            System.out.printf("%d) %s%n", i + 1, inventory.get(i));
        }
    }

    // Bullet 4: Snack selection + validation
    private Snack promptForSnackSelection() {
        while (true) {
            System.out.print("Enter snack number: ");
            int menuIndex = parseMenuIndex(readLineTrimmed(), 1, inventory.size());
            if (menuIndex != -1) {
                return inventory.get(menuIndex - 1);
            }
            System.out.println("Invalid selection. Enter a number from 1 to " + inventory.size() + ".");
        }
    }

    private String readLineTrimmed() {
        return scanner.nextLine().trim();
    }

    private int parseMenuIndex(String input, int minInclusive, int maxInclusive) {
        try {
            int parsed = Integer.parseInt(input);
            if (parsed >= minInclusive && parsed <= maxInclusive) {
                return parsed;
            }
            return -1;
        } catch (NumberFormatException ex) {
            return -1;
        }
    }

    // Bullet 8: Positive departure message
    private void printDepartureMessage(Snack snack) {
        if (snack == null) {
            System.out.println("Thanks for visiting the vending machine!");
            return;
        }
        System.out.println("Great choice: " + snack.getName() + ". Enjoy your snack!");
        System.out.println("Thanks for using the CSC109 Vending Machine.");
    }

    // Snack model (Thomas owns formatting and price structure)
    private static class Snack {
        private final String name;
        private final int price;

        Snack(String name, int price) {
            this.name = name;
            this.price = price;
        }

        String getName() {
            return name;
        }

        int getPrice() {
            return price;
        }

        String getFormattedPrice() {
            return "$" + price;
        }

        @Override
        public String toString() {
            return name + " - " + getFormattedPrice();
        }
    }

    // ============================================================
    // PARTNER B — JORDAN (Bullets 5–7)
    // ============================================================

    // Bullet 5: Accept unlimited $1 bills
    private boolean acceptMoneyUntilReadyOrCancel(int price) {
        // TODO (Jordan):
        // Loop until:
        // - insertedAmount >= price
        // - OR user cancels
        //
        // Accept ONLY $1 bills.
        // Return true if enough money inserted.
        // Return false if cancelled.
        int insertedAmount = 0;
        while(insertedAmount < price) {
            System.out.println("Insert $1 bill (or type 'cancel' to cancel):");
            String input = readLineTrimmed();
            if (input.equalsIgnoreCase("cancel")) {
                return false;
            } else if (input.equals("$1") || input.equals("1")) {
                insertedAmount += 1;
                System.out.println("Total inserted: $" + insertedAmount);
            } else {
                System.out.println("Invalid input. Please insert a $1 bill or type 'cancel'.");
            }
        }
        return true;
    }

    // Bullet 6: Exact change
    private int calculateChange(int price) {
        // TODO (Jordan):
        // Return insertedAmount - price
        return insertedAmount - price;
    }
    private void returnChange(int change){
        // TODO (Jordan):
        // Print exact change returned.
            if (change >0) {
                System.out.println("Returning change: $" + change);
            } else {
                System.out.println("No change to return.");
            }
    }

    // Bullet 7: Confirmation before transaction
    private boolean promptForConfirmation(Snack snack, int price, int inserted) {
        // TODO (Jordan):
        // Ask user to confirm purchase.
        // Return true if confirmed.
        // Return false if cancelled.
        System.out.printf("You selected: %s, which costs %s. You have inserted $%d.%n",
                snack.getName(), snack.getFormattedPrice(), inserted);
        System.out.println("Confirm purchase? (yes/no)");
        String input = readLineTrimmed();
        return input.equalsIgnoreCase("yes");

    }

    private void refundFullAmount() {
        // TODO (Jordan):
        // Print refund message.
        // Reset insertedAmount to 0.
        if (insertedAmount > 0) {
            System.out.println("Transaction cancelled. Refunding: $" + insertedAmount);
            insertedAmount = 0;
        } else {
            System.out.println("Transaction cancelled. No money to refund.");
        }
    }

    private void resetTransactionState() {
        // TODO (Jordan):
        // Reset insertedAmount and selectedSnack.
        insertedAmount = 0;
        selectedSnack = null;
    }
}

