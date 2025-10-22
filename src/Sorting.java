import java.io.*;

public class Sorting {

    static void mergeEntities(Entity[] entitiesArr, int leftPointer, int middlePointer, int rightPointer){
        int subArrSize1 = leftPointer - middlePointer + 1;
        int subArrSize2 = rightPointer - middlePointer;

        Entity[] subArr1 = new Entity[subArrSize1];
        Entity[] subArr2 = new Entity[subArrSize2];


        //Copying the first half of the array into the first sub array
        for (int i = 0; i < subArrSize1; i++) {
            subArr1[i] = entitiesArr[leftPointer + i];
        }
        //Second verse same as the first
        //But nows its the second half
        for (int j = 0; j < subArrSize2; j++) {
            subArr2[j] = entitiesArr[middlePointer + 1 + j];
        }

        //These will be used for indexes in a loop later
        //I is used for sub array 1 and J is used for sub array 2
        int i = 0, j = 0, k = 0;

        while (i < subArrSize1 && j < subArrSize2) {
            //Compares the values of the current objects x coord
            if (subArr1[i].getPosition()[0] <= subArr2[i].getPosition()[0]) {
                entitiesArr[k] = subArr1[i];
                i++;
            } else {
                entitiesArr[k] = subArr2[i];
                j++;
            }
            k++;

        }
        //Any elements remaining in either of the sub arrays are added to the main array
        while (i < subArr1.length) {
            entitiesArr[k] = subArr1[i];
            i++;
            k++;
        }
        while (j < subArr2.length) {
            entitiesArr[k] = subArr2[j];
            j++;
            k++;
        }



        //Returns the sorted array
        //return entitiesArr;

    }

    public Entity[] mergeSort(Entity[] entities, int leftPointer, int rightPointer){

        if(leftPointer < rightPointer) {

            //Finds the middle of the list
            int middlePointer = ((leftPointer + (rightPointer - 1)) / 2);

            mergeSort(entities, leftPointer, middlePointer);
            mergeSort(entities, middlePointer + 1, rightPointer);

            mergeEntities(entities, leftPointer, middlePointer, rightPointer);

        }

        return entities;


    }

    public Entity[] insertionSort(Entity[] entities){
        for (int i = 1; i < entities.length; i++){
            Entity key = entities[i];

            int j = i - 1;

            while (j >= 0 && entities[j].getPosition()[0] > key.getPosition()[0]){

                entities[j + 1] = entities[j];
                j--;

            }
            entities[j+1] = key;

        }
        return entities;
    }

}
