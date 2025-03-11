import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
public class App {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Error. Please enter a array size (integer), and a text file name such as 'example.txt'.");
            return;
        } else {
            try {
                int input = 0;
                input = Integer.parseInt(args[0]);
                String filename = args[1];
                int[] my_array = createRandomArray(input);
                writeArrayToFile(my_array, filename);
                int[] final_array = readFileToArray(filename);
                bubbleSort(final_array);
                String sortedArrayString = java.util.Arrays.toString(final_array);
                System.out.println("The Sorted Array is: " + sortedArrayString);

            } catch (Exception e) {
                System.out.println("Error. Please ensure your first argument is an integer.");
            }

        }
    }

    public static int[] createRandomArray(int arrayLength) {
        Random rand = new Random();
        int[] my_array = new int[arrayLength];
        for (int i = 0; i < arrayLength; i++) {
            my_array[i] = rand.nextInt(101);
        }
        return my_array;
    }
    
    public static void writeArrayToFile(int[] array, String filename) {
        try (BufferedWriter filesave = new BufferedWriter(new FileWriter(filename))) {
            for (int i = 0; i < array.length; i++) {
                filesave.write(Integer.toString(array[i]));
                filesave.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error: could not write to file");
        }
    }
    
    public static int[] readFileToArray(String filename) {
        int lineCount = 0;
        try(BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            while (reader.readLine() != null) {
                lineCount++;
            }
        } catch (IOException e) {
            System.out.println("Error: Couldn't read file properly.");
            return new int[0];
        }
        int[] array = new int[lineCount];
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            for (int i = 0; i < lineCount; i++) {
                array[i] = Integer.parseInt(reader.readLine().trim());
            }
        } catch (IOException e) {
            System.out.println("Error: Issue with converting text file to array.");
        }
        return array;
    }
    
    public static void bubbleSort(int[] array) {
        int a = array.length;
        for (int i = 0; i < a-1; i++) {
            for (int j = 0; j < a - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int value = array[j];
                    array[j] = array[j+1];
                    array[j+1] = value;
                }
            }
        }
    }
}

