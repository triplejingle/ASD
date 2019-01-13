public class DoubleHash {
    int [] hashTable;
    int currentSize;
    int tableSize = 13;
    int primeNumber = 7;
    DoubleHash(){
        hashTable=new int[tableSize];
        currentSize = 0;
        for(int i = 0;i< tableSize;i++){
            hashTable[i]= -1;
        }
    }
    boolean isFull(){
        return currentSize== tableSize;
    }
    int hash1(int key){
       return (key % tableSize);
    }

    int hash2(int key){
        return (primeNumber - (key%primeNumber));
    }

    void insertHash(int key){
        if(isFull()){
            return;
        }
        int index = hash1(key);

        if(hashTable[index]!=-1){
            int i = 0;
            while(true){
                //genereer nieuwe index
                if(hashTable[index]==-1){
                    hashTable[index]=key;
                    break;
                }
            }
        }else{
            hashTable[index] = key;
        }
        currentSize++;
    }
}
