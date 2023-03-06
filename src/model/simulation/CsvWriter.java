package model.simulation;
	
	import java.io.BufferedWriter;
	import java.io.FileWriter;
	import java.io.IOException;

	public class CsvWriter {
	    public static void writeToCsvFile(double[][] data, String fileName) {
	        try {
	        	BufferedWriter csvWriter = new BufferedWriter(new FileWriter("C:\\Users\\Clayton\\Desktop\\" + fileName + ".txt"));
	            for (double[] row : data) {
	                for (int i = 0; i < row.length; i++) {
	                    csvWriter.write(String.valueOf(row[i]));
	                    if (i != row.length - 1) {
	                        csvWriter.write(",");
	                    }
	                }
	                csvWriter.newLine();
	            }
	            csvWriter.flush();
	            csvWriter.close();
	            System.out.println("CSV file created successfully!");
	        } catch (IOException e) {
	            System.out.println("An error occurred while writing the CSV file.");
	            e.printStackTrace();
	        }
	    }
	}



