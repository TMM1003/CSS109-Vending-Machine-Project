import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class VendingMachine {

    // TODO: Expand this catalog with a larger variety of snacks and real prices.
    private final Map<String, Integer> snackPrices = new LinkedHashMap<>();

    public static void main(String[] args) {
        VendingMachine machine = new VendingMachine();
        machine.run();
    }

    public void run() {
        // TODO: Implement top-level transaction flow.
        // Suggested order:
        // 1) initializeSnackCatalog()
        // 2) displayWelcomeMessage()
        // 3) displayInstructions()
        // 4) displaySnackMenu()
        // 5) promptSnackSelection(...)
        // 6) collectDollarBills(...)
        // 7) promptTransactionConfirmation(...)
        // 8) complete purchase OR process full refund
    }

    private void initializeSnackCatalog() {
        // TODO: Add snacks and prices (in whole dollars or cents, choose one consistent unit).
        // Example placeholders:
        // snackPrices.put("Chips", 2);
        // snackPrices.put("Chocolate Bar", 3);
        // snackPrices.put("Trail Mix", 4);
    }

    private void displayWelcomeMessage() {
        // TODO: Print a friendly welcome message.
    }

    private void displayInstructions() {
        // TODO: Print clear usage instructions.
        // Include:
        // - How to select a snack
        // - Only $1 bills accepted
        // - Confirmation step before finalizing
        // - Refund behavior on cancellation
    }

    private void displaySnackMenu() {
        // TODO: Print all available snacks with prices.
    }

    private String promptSnackSelection(Scanner scanner) {
        // TODO: Ask user to choose a snack from the menu.
        // TODO: Validate selection and re-prompt if invalid.
        return null; // TODO: Return selected snack name/key.
    }

    private int getPriceForSnack(String snackName) {
        // TODO: Return the selected snack's price from snackPrices.
        return 0;
    }

    private int collectDollarBills(Scanner scanner, int snackPrice) {
        // TODO: Repeatedly accept inserted money.
        // TODO: Accept only $1 bill increments.
        // TODO: Allow user to insert as much as they want.
        // TODO: Stop when enough money is inserted or when user signals done.
        return 0; // TODO: Return total inserted dollars.
    }

    private boolean promptTransactionConfirmation(Scanner scanner, String snackName, int snackPrice, int amountInserted) {
        // TODO: Ask user to confirm before carrying out transaction.
        // TODO: Return true if confirmed, false if cancelled.
        return false;
    }

    private void completeTransaction(String snackName, int snackPrice, int amountInserted) {
        // TODO: Dispense snack.
        // TODO: Calculate and return exact change if amountInserted > snackPrice.
        // TODO: Display successful purchase message.
    }

    private void processCancellation(int amountInserted) {
        // TODO: Return full refund to user.
        // TODO: Display cancellation/refund message.
    }

    private int calculateChange(int amountInserted, int snackPrice) {
        // TODO: Compute exact change.
        return 0;
    }

    private void displayDepartureMessage(String snackName) {
        // TODO: Print a positive goodbye message about the user's snack choice.
    }
}
