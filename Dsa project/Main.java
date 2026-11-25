import java.util.*;

public class Main {
    static double[] prices = new double[1000];
    static int count = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Add Price");
            System.out.println("2. Display Prices");
            System.out.println("3. Analysis");
            System.out.println("4. Sort Ascending");
            System.out.println("5. Binary Search");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");

            int choice;
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid choice.");
                continue;
            }

            if (choice == 1) {
                addPrice(sc);
            } else if (choice == 2) {
                displayPrices();
            } else if (choice == 3) {
                analyzePrices();
            } else if (choice == 4) {
                sortPrices();
            } else if (choice == 5) {
                searchPrice(sc);
            } else if (choice == 6) {
                break;
            } else {
                System.out.println("Invalid choice.");
            }
        }
        sc.close();
    }

    static void addPrice(Scanner sc) {
        System.out.print("Price: ");
        try {
            double price = Double.parseDouble(sc.nextLine());
            if (price <= 0) {
                System.out.println("Invalid price. Enter a positive number.");
                return;
            }
            prices[count] = price;
            count++;
            System.out.printf("Price added. Day %d: %.2f%n", count, price);
        } catch (Exception e) {
            System.out.println("Invalid price. Enter a positive number.");
        }
    }

    static void displayPrices() {
        if (count == 0) {
            System.out.println("No prices stored.");
            return;
        }
        for (int i = 0; i < count; i++) {
            System.out.printf("Day %d: %.2f%n", i + 1, prices[i]);
        }
    }

    static void analyzePrices() {
        if (count == 0) {
            System.out.println("No prices stored.");
            return;
        }
        double high = prices[0];
        double low = prices[0];
        int highDay = 1;
        int lowDay = 1;
        double sum = 0;

        for (int i = 0; i < count; i++) {
            if (prices[i] > high) {
                high = prices[i];
                highDay = i + 1;
            }
            if (prices[i] < low) {
                low = prices[i];
                lowDay = i + 1;
            }
            sum += prices[i];
        }

        if (count == 1) {
            System.out.printf("Highest: %.2f | Lowest: %.2f%n", high, low);
        } else {
            System.out.printf("Highest: %.2f (Day %d) | Lowest: %.2f (Day %d)%n", high, highDay, low, lowDay);
        }
        System.out.printf("Average: %.2f%n", sum / count);
    }

    static double[] getSortedArray() {
        double[] sorted = new double[count];
        for (int i = 0; i < count; i++) {
            sorted[i] = prices[i];
        }
        for (int i = 0; i < count - 1; i++) {
            for (int j = 0; j < count - i - 1; j++) {
                if (sorted[j] > sorted[j + 1]) {
                    double temp = sorted[j];
                    sorted[j] = sorted[j + 1];
                    sorted[j + 1] = temp;
                }
            }
        }
        return sorted;
    }

    static void sortPrices() {
        if (count == 0) {
            System.out.println("No prices stored.");
            return;
        }
        double[] sorted = getSortedArray();
        for (int i = 0; i < count; i++) {
            System.out.printf("%.2f", sorted[i]);
            if (i < count - 1) System.out.print(", ");
        }
        System.out.println();
    }

    static int binarySearch(double[] arr, double target) {
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    static void searchPrice(Scanner sc) {
        if (count == 0) {
            System.out.println("No prices stored.");
            return;
        }
        System.out.print("Search price: ");
        try {
            double target = Double.parseDouble(sc.nextLine());
            double[] sorted = getSortedArray();
            int index = binarySearch(sorted, target);
            if (index != -1) {
                System.out.printf("Price %.2f found at position %d%n", target, index + 1);
            } else {
                System.out.printf("Price %.2f not found.%n", target);
            }
        } catch (Exception e) {
            System.out.println("Invalid price. Enter a positive number.");
        }
    }
}