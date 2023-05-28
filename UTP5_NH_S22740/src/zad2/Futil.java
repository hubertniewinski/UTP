package zad2;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
                Files.walk(dir).filter(Files::isRegularFile).filter(p -> p.toString().endsWith(".txt")).forEach(p -> {
                    try {
                        List<String> lines = Files.readAllLines(p, Charset.forName("Cp1250"));
                        for (String line : lines) {
                            writer.write(line);
                            writer.newLine();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
