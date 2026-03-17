import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Trial {
	public static void main(String[] args) throws NoSuchAlgorithmException {
		File file = new File("//home//oem//Downloads//eclipse-committers-2021-12-R-linux-gtk-x86_64//eclipse//abc.csv");
	    try {
	        // create FileWriter object with file as parameter
	        FileWriter outputfile = new FileWriter(file);
	  
	        // create CSVWriter object filewriter object as parameter
//	        CSVWriter writer = new CSVWriter(outputfile);
//	  
//	        // adding header to csv
//	        String[] header = { "Name", "Class", "Marks" };
//	        writer.writeNext(header);
//	        String longNumber = null;
//	        
//	        
//	  
//	        // add data to csv
//	        String[] data1 = { "Aman", "10", new StringBuilder("").append(longNumber).append("").toString()};
//	        writer.writeNext(data1);
//	        String[] data2 = { "Suraj", "10", "630" };
//	        writer.writeNext(data2);
//	  
//	        // closing writer connection
//	        writer.close();
	        List<String> test = new ArrayList();
	        test = null;
	        //test.add("word1");
	        //test.add("word2");
	        //String collect = test.stream().collect(Collectors.joining(","));
	        System.out.println(test == null ? "sample" : test.stream().collect(Collectors.joining(",")));
	    }
	    catch (IOException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
	}
}