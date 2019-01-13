package Examples;

/***
 * Collision is precies hetzelfde zoals op school behandeld.
 * Hashing is precies hetzelfde zoals op school behandeld.
 *
 * What is double hashing?
 * Double hashing is manier op collisions op te lossen.
 *
 * Hoe werkt het?
 * Double hashing past een tweede hash to op de key als er een collision voorkomt.
 *
 */
public class DoubleHashingOn {
    private DataItem[] hashArray;

    private int arraySize;

    private DataItem bufItem; // for deleted items

    DoubleHashingOn(int size) {
        arraySize = size;
        hashArray = new DataItem[arraySize];
        bufItem = new DataItem(-1);
    }

    public void displayTable() {
        System.out.print("Table: ");
        for (int j = 0; j < arraySize; j++) {
            if (hashArray[j] != null)
                System.out.print(hashArray[j].getKey() + " ");
            else
                System.out.print("** ");
        }
        System.out.println("");
    }

    public int hashFunc1(int key) {
        return key % arraySize;
    }

    public int hashFunc2(int key) {
        return 6 - key % 6;
    }

    public void insert(int key, DataItem item) {
        int hashVal = hashFunc1(key); // hash the key
        int stepSize = hashFunc2(key); // get step size
        // until empty cell or -1
        while (hashArray[hashVal] != null && hashArray[hashVal].getKey() != -1) {
            hashVal += stepSize; // add the step
            hashVal %= arraySize; // for wraparound
        }
        hashArray[hashVal] = item; // insert item
    }

    public DataItem delete(int key) {
        int hashVal = hashFunc1(key);
        int stepSize = hashFunc2(key); // get step size

        while (hashArray[hashVal] != null) {
            if (hashArray[hashVal].getKey() == key) {
                DataItem temp = hashArray[hashVal]; // save item
                hashArray[hashVal] = bufItem; // delete item
                return temp; // return item
            }
            hashVal += stepSize; // add the step
            hashVal %= arraySize; // for wraparound
        }
        return null; // can't find item
    }

    public DataItem find(int key) {
        int hashVal = hashFunc1(key); // hash the key
        int stepSize = hashFunc2(key); // get step size

        while (hashArray[hashVal] != null) {
            if (hashArray[hashVal].getKey() == key)
                return hashArray[hashVal]; // yes, return item
            hashVal += stepSize; // add the step
            hashVal %= arraySize; // for wraparound
        }
        return null; // can't find item
    }
}