package lab11.ex5;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class DirectorySizeCalculator {
    private long totalSize;

    public void calculateSize(Path dir, boolean recursive) throws IOException {
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
            for (Path entry : stream) {
                if (Files.isRegularFile(entry)) {
                    BasicFileAttributes attrs = Files.readAttributes(entry, BasicFileAttributes.class);
                    totalSize += attrs.size();
                } else if (Files.isDirectory(entry) && recursive) {
                    calculateSize(entry, true);
                }
            }
        }
    }

    public long getTotalSize() {
        return totalSize;
    }

    public static void main(String[] args) throws IOException {
        Path directory = Paths.get("src/lab11/ex5");
        boolean recursive = false; 

        if (args.length > 0){
            if (args[0].equalsIgnoreCase("-r")){
                recursive = true;
            }
        }

        DirectorySizeCalculator calculator = new DirectorySizeCalculator();
        calculator.calculateSize(directory, recursive);
        
        System.out.println("Total size: " + calculator.getTotalSize() + " bytes");
    }
}