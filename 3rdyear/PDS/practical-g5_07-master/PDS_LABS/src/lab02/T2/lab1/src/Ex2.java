package lab02.T2.lab1.src;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ex2 {
    public static void main(String[] args) throws FileNotFoundException {
        int size = 20;
        List<String> words = new ArrayList<>();
        int flagOut = 0;
        String fname = "";
        String fnameOut = "";

        if (args.length < 4) {
            System.out.println("Too few arguments!");
            System.exit(0);
        } else {

            for (int i = 0; i < args.length; i++) {
                switch (args[i]) {
                    case "-i":
                        fname = args[i + 1];
                        continue;

                    case "-s":
                        size = Integer.parseInt(args[i + 1]);
                        continue;

                    case "-o":
                        flagOut = 1;
                        fnameOut = args[i + 1];
                        continue;
                }
            }
        }

        try {
            File file = new File(fname);
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                words.add(sc.nextLine());
            }

            System.out.println(words);

            WSGenerator maker = new WSGenerator(size, words);

            sc.close();
            if (flagOut == 1) {
                System.out.println("Write name of file:\n");
                fname = sc.nextLine();
                maker.writeToFile(fname);

                sc.close();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }



    }
}
