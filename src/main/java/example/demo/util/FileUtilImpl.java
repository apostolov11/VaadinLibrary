package example.demo.util;

import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class FileUtilImpl implements FileUtil {

    @Override
    public String[] fileContent(String pathFile) throws IOException {

        File file = new File(pathFile);

        BufferedReader bf = new BufferedReader(new FileReader(file));
        List<String> fileInfo = new ArrayList<>();
        String line = bf.readLine();
        while (line != null) {
            fileInfo.add(line);
            line = bf.readLine();
        }


        return fileInfo.toArray(String[]::new);
    }
}
