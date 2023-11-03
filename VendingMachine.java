//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Vending Machine
// Course:   CS 300 Fall 2022
//
// Author:   Kamila Domagala
// Email:    kdomagala@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    Dylan Williams
// Partner Email:   dmwilliams24@wisc.edu
// Partner Lecturer's Name: Hobbes LeGault
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   X Write-up states that pair programming is allowed for this assignment.
//   X We have both read and understand the course Pair Programming Policy.
//   X We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP ////////////////////////// 
//
// Persons:         Aditya Tolia
// Online Sources:  Piazza: 
//                  https://canvas.wisc.edu/courses/323005/external_tools/65
//                  Helped me fix a bug in my addItem method.
//
///////////////////////////////////////////////////////////////////////////////
/**
 * TODO This class holds the addItem, getItemAtIndex, removeNextItem,
 * getItemOccurrences, containsItem, getItemsOccurrencesByExpirationDate, and
 * getItemsSummary methods, which simulate a vending machine.
 * 
 * @author Kamila Domagala
 *
 */
public class VendingMachine {

    /**
     * Adds/appends an item defined by its description and expirationDate to a
     * vending machine represented by an oversize array of strings defined by the
     * two-dimensional array items and its size itemsCount. The item will be added
     * to the end of the array. If the vending machine is full, the new item won't
     * be added and the method returns the items count passed as input without
     * making any changes to the contents of the array items.
     * 
     * @param description    description of the item to be added to the vending
     *                       machine
     * @param expirationDate a string parsable to a positive integer which
     *                       represents the expiration date of the item. The date
     *                       "0" represents January 1st 2023.
     * @param items          a two-dimensional of strings storing items. items[i][0]
     *                       and items[i][1] respectively represent the description
     *                       and the expiration date of the item stored at index i
     * @param itemsCount     number of items in the vending machine
     * @return the size of the vending machine after trying to add the new item
     */
    public static int addItem(String description, String expirationDate, String[][] items, int itemsCount) {
        // checks if vending machine is full
        boolean isFull = false;
        if (itemsCount >= items.length) {
            isFull = true;
        }
        // if full, returns original number of items
        if (isFull == true) {
            return itemsCount;
        }
        // if empty, adds an item with given description and expiration date at index 0
        if (itemsCount == 0) {
            items[0] = new String[] { description, expirationDate };
            // if not empty, appends new item
        } else {
            items[itemsCount] = new String[] { description, expirationDate };
        }

        return itemsCount + 1;
    }

    /**
     * Returns without removing a string representation of the item at the given
     * index within the vending machine defined by the array items and its size
     * itemsCount. This method does not make any changes to the contents of the
     * vending machine.
     * 
     * @param items      two dimensional array storing items within a vending
     *                   machine where items[i][0] represents the description of the
     *                   item at index i and items[i][1] stores its expiration date.
     * @param itemsCount (size) number of items stored in the vending machine
     * @param index      index of an item within the provided vending machine
     * @return a string representation of the item stored at the given index within
     *         the vending machine defined by items and itemsCount. The returned
     *         string must have the following format: "description (expiration
     *         date)". If the provided index is out of the range of indexes
     *         0..itemsCount-1, the method returns "ERROR INVALID INDEX"
     */
    public static String getItemAtIndex(int index, String[][] items, int itemsCount) {
        // checks if index is out of bounds
        if (index >= itemsCount) {
            return "ERROR INVALID INDEX";

        } else {
            return items[index][0] + " (" + items[index][1] + ")";
        }

    }

    /**
     * Returns without removing the index of the item having the provided
     * description and the smallest expiration date within the vending machine
     * defined by the array items and its size itemsCount.
     * 
     * @param description description of the item to get its index
     * @param items       two dimensional array storing items within a vending
     *                    machine where items[i][0] represents the description of
     *                    the item at index i and items[i][1] stores its expiration
     *                    date.
     * @param itemsCount  (size) number of items stored in the vending machine
     * @return the index of the next item, meaning the item with the given
     *         description and the smallest expiration date. If no match found,
     *         return -1.
     */
    public static int getIndexNextItem(String description, String[][] items, int itemsCount) {
        int counter = 0;
        int minExp = 0;
        // iterates through array and counts the items with the same description.
        for (int i = 0; i < itemsCount; i++) {
            if (items[i][0].equals(description)) {
                minExp = Integer.parseInt(items[i][1]);
                counter++;
            }
        }
        // checks if a match is found
        if (counter < 1) {
            return -1;
            // if match found, iterates to find item with the lowest expiration date
        } else if (counter > 1) {
            for (int i = 0; i < itemsCount; i++) {

                if (items[i][0].equals(description) && Integer.parseInt(items[i][1]) < minExp) {
                    minExp = Integer.parseInt(items[i][1]);
                }
            }
            // returns the index of the item with the lowest expiration date
            for (int j = 0; j < itemsCount; j++) {

                if (items[j][0].equals(description) && Integer.parseInt(items[j][1]) == minExp) {
                    return j;
                }
            }
        } else {
            for (int i = 0; i < itemsCount; i++) {
                if (items[i][0].equals(description)) {
                    return i;
                }
            }
        }
        return 0;
    }

    /**
     * Removes the item having the provided description with the smallest expiration
     * date within the vending machine defined by the array items and its size
     * itemsCount. This method should maintain the order of precedence of items in
     * the vending machine. This means that the remove operation involves a shift
     * operation.
     * 
     * @param description description of the item to remove or dispense
     * @param items       array storing items within a vending machine
     * @param itemsCount  (size) number of items stored in the vending machine
     * @return size of the vending machine after removing the item with the given
     *         description and the smallest expiration date. If no match found, this
     *         method must return the provided itemsCount without making any change
     *         to the contents of the vending machine.
     */
    public static int removeNextItem(String description, String[][] items, int itemsCount) {

        boolean isFound = VendingMachine.containsItem(description, items, itemsCount);
        int index = 0;
        int minExp = 10000;

        if (!isFound) {
            return itemsCount;
        }
        // checks that the item matches the description finds the smallest expiration
        // date
        for (int i = 0; i < itemsCount; i++) {
            if (items[i][0].equals(description) && Integer.parseInt(items[i][1]) < minExp) {
                minExp = Integer.parseInt(items[i][1]);
                index = i;
            }
        }
        // calls getItemsOccurrencesByExpirationDate method to count how many repeats
        // there are of the same item
        // and same date
        if ((getItemsOccurrencesByExpirationDate(description, String.valueOf(minExp), items, itemsCount) > 1)) {

            for (int i = 0; i < itemsCount; i++) {
                if (items[i][0].equals(description) && Integer.parseInt(items[i][1]) == minExp) {
                    // shifting
                    for (int j = i; j < itemsCount - 1; j++) {
                        items[j] = items[j + 1];
                    }
                    items[itemsCount - 1] = null;
                    return itemsCount - 1;
                }
            }

        }
        // checks for the item with the same description and smallest expiration date.
        else {
            for (int i = 0; i < itemsCount; i++) {
                if (items[i][0].equals(description) && Integer.parseInt(items[i][1]) == minExp) {
                    // shifting
                    for (int j = i; j < itemsCount - 1; j++) {
                        items[j] = items[j + 1];
                    }
                    items[itemsCount - 1] = null;
                    return itemsCount - 1;
                }
            }

        }

        return 0;

    }

    /**
     * Returns the number of occurrences of an item with a given description within
     * the vending machine defined by items and itemsCount
     * 
     * @param description description (name) of an item
     * @param items       two dimensional array storing items within a vending
     *                    machine where items[i][0] represents the description of
     *                    the item at index i and items[i][1] stores its expiration
     *                    date.
     * @param itemsCount  (size) number of items stored in the vending machine
     * @return the number of occurrences of an item with a given description within
     *         the vending machine
     */
    public static int getItemOccurrences(String description, String[][] items, int itemsCount) {
        int numOccurrences = 0;
        // iterates through the array to count the number of items with the same
        // description
        for (int i = 0; i < itemsCount; i++) {
            if ((items[i][0]).equals(description)) {
                numOccurrences++;
            }
        }
        return numOccurrences;
    }

    /**
     * Checks whether a vending machine defined by the array items and its size
     * itemsCount contains at least an item with the provided description
     * 
     * @param description description (name) of an item to search
     * @param items       two dimensional array storing items within a vending
     *                    machine where items[i][0] represents the description of
     *                    the item at index i and items[i][1] stores its expiration
     *                    date.
     * @param itemsCount  (size) number of items stored in the vending machine
     * @return true if there is a match with description in the vending machine,
     *         false otherwise
     */
    public static boolean containsItem(String description, String[][] items, int itemsCount) {

        for (int i = 0; i < itemsCount; i++) {
            if ((items[i][0]).equals(description)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the number of items in the vending machine with a specific
     * description and whose expiration dates are greater or equal to a specific
     * expiration date
     * 
     * @param description    description of the item to find its number of
     *                       occurrences
     * @param expirationDate specific (earliest) expiration date
     * @param items          two dimensional array storing items within a vending
     *                       machine where items[i][0] represents the description of
     *                       the item at index i and items[i][1] stores its
     *                       expiration date.
     * @param itemsCount     (size) number of items stored in the vending machine
     * @return the number of items with a specific description and whose expiration
     *         date is greater or equal to the given one
     */
    public static int getItemsOccurrencesByExpirationDate(String description, String expirationDate, String[][] items,
            int itemsCount) {

        int numOccurrences = 0;
        for (int i = 0; i < itemsCount; i++) {
            // check if expiration date integer is more than or equal to and match given
            // description
            if (Integer.parseInt(items[i][1]) >= Integer.parseInt(expirationDate) && description.equals(items[i][0])) {
                numOccurrences++;
            }

        }
        return numOccurrences;
    }

    /**
     * Returns a summary of the contents of a vending machine in the following
     * format: Each line contains the description or item name followed by one the
     * number of occurrences of the item name in the vending machine between
     * parentheses. For instance, if the vending machine contains five bottles of
     * water, ten chocolates, and seven snacks, the summary description will be as
     * follows. "water (5)\nchocolate (10)\nsnack (7)" If the vending machine is
     * empty, this method returns an empty string ""
     * 
     * @param items      two dimensional array storing items within a vending
     *                   machine where items[i][0] represents the description of the
     *                   item at index i and items[i][1] stores its expiration date.
     * @param itemsCount (size) number of items stored in the vending machine
     * @return a descriptive summary of the contents of a vending machine
     */
    public static String getItemsSummary(String[][] items, int itemsCount) {

        String description = "";
        for (int i = 0; i < itemsCount; i++) {

            if (!description.contains(items[i][0])) {
                description = description + items[i][0] + " (" + getItemOccurrences(items[i][0], items, itemsCount)
                        + ")" + "\n";
            }
            if (items[i] == null) {
                return "";
            }
        }

        return description;
    }

}
