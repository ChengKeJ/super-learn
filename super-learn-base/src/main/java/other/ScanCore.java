package other;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author c.kj
 * @Description
 * @Date 2021/6/23
 * @Time 8:17 PM
 **/
public class ScanCore {

    public static void main(String[] args) {
        ArrayList<String> files = new ArrayList<String>();
        File file = new File("/Users/c.kj/project/book/docs");
        File[] tempList = file.listFiles();

        for (int i = 0; i < tempList.length; i++) {
            if (tempList[i].isFile()) {
                System.out.println("文件：" + tempList[i]);
                files.add(tempList[i].toString());
            }
            if (tempList[i].isDirectory()) {
                System.out.println("文件夹：" + tempList[i]);
            }
        }

        ArrayList<String> pdfFiles = new ArrayList<>();
        files.forEach(f -> {
            File tempFile = new File(f.trim());
            String fName = tempFile.getName();
            if (fName.contains("pdf")) {
                pdfFiles.add(fName);
            }
        });

        String dir = "/Users/c.kj/project/book/docs/";
        pdfFiles.forEach(g -> {
            String[] split = g.split(".pdf");
            String filemd = split[0] + ".md";
            String s = dir + filemd;
            System.out.println(s);
            File file1 = new File(s);
            try {
                boolean newFile = file1.createNewFile();
                if (newFile) {
                    BufferedWriter out = new BufferedWriter(new FileWriter(file1));
                    out.write("```pdf\n" +
                            "\t" + g + "\n" +
                            "``` ");
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
