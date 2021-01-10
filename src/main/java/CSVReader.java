import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class CSVReader {

//    public static List<String[]> lines = new LinkedList<>();


    public static List<String[]> readAllCSVFilesInDirectory(String dirPathString){
        List<String[]> lines = new LinkedList<>();
        try {
            Path dirPath = Paths.get(dirPathString);            // create directory path
            try (DirectoryStream<Path> dirPaths = Files
                    .newDirectoryStream(dirPath)) {                                         // get all files from directory
                for (Path file : dirPaths) {
//                    System.out.println(file.getFileName().toString());                      // print all files
//                    System.out.println(file.toAbsolutePath().toString());                      // print all files
                    lines.addAll(readCSVFile(file.toAbsolutePath().toString(), "\t"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lines;
    }


    public static List<String[]> readCSVFile(String dirPathString, String splitBy){
        String line = "";
//        String splitBy = ",";
        List<String[]> lines = new LinkedList<>();
        try {
            //parsing a CSV file into BufferedReader class constructor
            BufferedReader br = new BufferedReader(new FileReader(dirPathString));
            while ((line = br.readLine()) != null)
            //returns a Boolean value
            {
//                String[] readedLineSplitted = line.split(splitBy);
                //use comma as separator
//                System.out.println("Emp[First Name=" + employee[1] + ", Last Name=" + employee[2] + ", Contact=" + employee[3] + ", City= " + employee[4] + "]");
                lines.add(line.split(splitBy));
            }
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public static <T> void showReadedFiles(List<T[]> list){
        list.stream().forEach(
                m->{
                    Arrays.stream(m).forEach(n->{
                        System.out.print(n+":");
                    });
                    System.out.println();
                })
        ;
    }




}
