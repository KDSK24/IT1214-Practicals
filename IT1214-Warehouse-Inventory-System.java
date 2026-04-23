import java.util.*;

class Item {
    private String itemId;
    private String itemName;
    private int quantity;
    private double price;

    public Item(String itemId, String itemName, int quantity, double price) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.quantity = quantity;
        this.price = price;
    }

    public String getItemId() {
        return itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String toString() {
        return "ID: " + itemId +
               ", Name: " + itemName +
               ", Quantity: " + quantity +
               ", Price: $" + price;
    }
}

class Inventory {
    private HashMap<String, Item> items = new HashMap<>();

    public void addItem(Item item) {
        if (items.containsKey(item.getItemId())) {
            System.out.println("Item already exists!");
        } else {
            items.put(item.getItemId(), item);
            System.out.println("Item added successfully.");
        }
    }

    public void removeItem(String itemId) {
        if (items.remove(itemId) != null) {
            System.out.println("Item removed successfully.");
        } else {
            System.out.println("Item not found!");
        }
    }

    public void updateQuantity(String itemId, int quantity) {
        Item item = items.get(itemId);
        if (item != null) {
            item.setQuantity(quantity);
            System.out.println("Quantity updated.");
        } else {
            System.out.println("Item not found!");
        }
    }

    public void searchById(String itemId) {
        Item item = items.get(itemId);
        if (item != null) {
            System.out.println(item);
        } else {
            System.out.println("Item not found!");
        }
    }

    public void searchByName(String name) {
        boolean found = false;
        for (Item item : items.values()) {
            if (item.getItemName().equalsIgnoreCase(name)) {
                System.out.println(item);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Item not found!");
        }
    }

    public void displayAll() {
        if (items.isEmpty()) {
            System.out.println("Inventory is empty.");
            return;
        }
        for (Item item : items.values()) {
            System.out.println(item);
        }
    }
}

class Warehouse {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Inventory inventory = new Inventory();

        while (true) {
            System.out.println("\n=== Warehouse Inventory System ===");
            System.out.println("1. Add Item");
            System.out.println("2. Remove Item");
            System.out.println("3. Update Quantity");
            System.out.println("4. Search Item");
            System.out.println("5. Display All Items");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");

            int choice;

            try {
                choice = sc.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid input! Enter a number.");
                sc.nextLine();
                continue;
            }

            switch (choice) {
                case 1:
                    sc.nextLine();
                    System.out.print("Enter Item ID: ");
                    String id = sc.nextLine();

                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Quantity: ");
                    int qty = sc.nextInt();

                    System.out.print("Enter Price: ");
                    double price = sc.nextDouble();

                    inventory.addItem(new Item(id, name, qty, price));
                    break;

                case 2:
                    sc.nextLine();
                    System.out.print("Enter Item ID to remove: ");
                    inventory.removeItem(sc.nextLine());
                    break;

                case 3:
                    sc.nextLine();
                    System.out.print("Enter Item ID: ");
                    String updateId = sc.nextLine();

                    System.out.print("Enter new Quantity: ");
                    int newQty = sc.nextInt();

                    inventory.updateQuantity(updateId, newQty);
                    break;

                case 4:
                    sc.nextLine();
                    System.out.println("1. Search by ID");
                    System.out.println("2. Search by Name");
                    int searchChoice = sc.nextInt();
                    sc.nextLine();

                    if (searchChoice == 1) {
                        System.out.print("Enter ID: ");
                        inventory.searchById(sc.nextLine());
                    } else if (searchChoice == 2) {
                        System.out.print("Enter Name: ");
                        inventory.searchByName(sc.nextLine());
                    } else {
                        System.out.println("Invalid option!");
                    }
                    break;

                case 5:
                    inventory.displayAll();
                    break;

                case 6:
                    System.out.println("Exiting system...");
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
