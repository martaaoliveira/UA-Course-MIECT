package lab11.ex1;

import java.util.Arrays;
import java.util.Random;

public class ex1 {
    public static void main(String[] args) {
        // Create an array of phones
        int arraySize = 5;
        Phone[] phones = generateRandomPhones(arraySize);

        // Create instances of the sorting algorithms
        SortingStrategy bubbleSort = new BubbleSort();
        SortingStrategy insertionSort = new InsertionSort();
        SortingStrategy quickSort = new QuickSort();
        SortingStrategy mergeSort = new MergeSort();

        // Test and measure the execution time of each sorting algorithm for each criteria
        String[] sortingCriteria = { "price", "cpu", "memory", "camera" };
        for (String criteria : sortingCriteria) {
            System.out.println("Sorting by " + criteria + ":");

            measureSortingTime(bubbleSort, phones.clone(), criteria, "Bubble Sort");
            measureSortingTime(insertionSort, phones.clone(), criteria, "Insertion Sort");
            measureSortingTime(quickSort, phones.clone(), criteria, "Quick Sort");
            measureSortingTime(mergeSort, phones.clone(), criteria, "Merge Sort");

            System.out.println();
        }
    }

    private static void measureSortingTime(SortingStrategy algorithm, Phone[] phones, String criteria,String algorithmName) {
        long startTime = System.nanoTime();

        algorithm.sort(phones, criteria);

        long endTime = System.nanoTime();
        long executionTime = endTime - startTime;

        System.out.println(algorithmName + " execution time for " + criteria + ": " + executionTime + " nanoseconds");

        // Print sorted phones
        for (Phone phone : phones) {
            System.out.println(phone.toString());
        }

        System.out.println();
    }


private static Phone[] generateRandomPhones(int size) {
    Phone[] phones = new Phone[size];
    Random random = new Random();
    String[] phoneTypes = {"Smartphone", "Iphone"};

    for (int i = 0; i < size; i++) {
        String cpu = "CPU " + i;
        double price = random.nextDouble() * 1000;
        int memory = random.nextInt(16) + 1;
        String camera = "Camera " + i;
        String type = phoneTypes[random.nextInt(phoneTypes.length)];

        phones[i] = new Phone(cpu, price, memory, camera, type);
    }

    return phones;
}

}