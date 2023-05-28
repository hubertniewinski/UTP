package zad1;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

public class Futil {
    public static void processDir(String dirName, String resultFileName) {
        Path dir = Paths.get(dirName);
        Path res = Paths.get(resultFileName);

        try{
            if(Files.exists(res)){
                Files.delete(res);
            }

            Files.createFile(res);

            try(BufferedWriter writer = Files.newBufferedWriter(res, StandardCharsets.UTF_8)){
                Files.walkFileTree(dir, new FileVisitor<Path>() {
                    @Override
                    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                        return FileVisitResult.CONTINUE;
                    }

                    @Override
                    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                        if (file.toString().endsWith(".txt")) {
                            List<String> lines = Files.readAllLines(file, Charset.forName("Cp1250"));
                            for (String line : lines) {
                                writer.write(line);
                                writer.newLine();
                            }
                        }
                        return FileVisitResult.CONTINUE;
                    }

                    @Override
                    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                        return FileVisitResult.CONTINUE;
                    }

                    @Override
                    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                        return FileVisitResult.CONTINUE;
                    }
                });
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
