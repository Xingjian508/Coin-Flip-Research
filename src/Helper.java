import java.io.*;

public class Helper {
}

class Convert {
    public static void convert() {
        System.out.println("Initialize ...");
        String fileName = "Pascal's Triangle Copy";
        Read read = new Read(fileName + ".txt");
        Print print = new Print();
        try{
            print.setFileName(fileName + ".csv");
            read.setPrint(print);
            read.exec(1);
        } catch(IOException ex){
            ex.printStackTrace();
        }
        System.out.println("Finished");
    }
}

class Print {

    protected String fileName;

    protected FileWriter writer;

    public Print() {}

    public Print(String fileName) throws IOException {
        this.fileName = fileName;
        this.writer = new FileWriter(this.fileName);
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String fileName) throws IOException {
        this.fileName = fileName;
        this.writer = new FileWriter(this.fileName);
    }

    public void close() throws IOException {
        this.writer.flush();
        this.writer.close();
    }

    public void addRow(String[] c) throws IOException {
        int l = c.length;
        for(int i = 0; i < l; i++) {
            this.writer.append(c[i]);
            if(i != (l - 1)) {
                this.writer.append(",");
            }
        }
        this.writer.append('\n');
    }
}

class Read {

    protected String fileName;

    protected BufferedReader bufferedReader;

    protected Print print;

    public Read(String fileName) {
        this.fileName = fileName;
    }

    public Read(Print print, String fileName) {
        this.print = print;
        this.fileName = fileName;
    }

    public void setPrint(Print print) {
        this.print = print;
    }

    public Print getPrint() {
        return this.print;
    }

    public void exec(Integer type) {
        String sCurrentLine = "";
        try{
            this.bufferedReader = new BufferedReader(
                    new FileReader(this.fileName));

            while((sCurrentLine = this.bufferedReader.readLine()) != null) {
                String[] columns = sCurrentLine.split("\\s");
                int length = columns.length;
                if(type == 1) {
                    this.print.addRow(columns);
                } else if(length >= 2){
                    double col1 = Double.parseDouble(columns[1]);
                    if(col1 > 20){
                        if(type == 2){
                            this.print.addRow(columns);
                        } else if(type == 3 && length >= 3 && columns[2] != null){
                            this.print.addRow(columns);
                        }
                    }
                }
            }
            this.print.close();

        } catch(IOException ex) {
            ex.printStackTrace();
        } finally {
            try{
                if(this.bufferedReader != null) {
                    this.bufferedReader.close();
                }
            } catch(IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}

class Write {
    public static void writeDataLineByLine(String filePath) {

        String[][] employees = new String[1][];
        // first create file object for file placed at location
        // specified by filepath
        File file = new File(filePath);
        try {
            // create FileWriter object with file as parameter
            FileWriter fileWriter = new FileWriter(file);

            for (String[] data : employees) {
                StringBuilder line = new StringBuilder();
                for (int i = 0; i < data.length; i++) {
                    line.append(data[i]);
                    if (i != data.length - 1) {
                        line.append(',');
                    }
                }
                line.append("\n");
                fileWriter.write(line.toString());
            }
            fileWriter.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
