package uy.edu.fing.volandouy.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSVReader {

	public static List<Map<String, String>> readCSV(String filePath) throws IOException {
		List<Map<String, String>> records = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String line;

			String[] headers = br.readLine().split(";");

			while ((line = br.readLine()) != null) {
				if (line.isEmpty()) {
					continue;
				}

				String[] values = line.split(";");
				Map<String, String> record = new HashMap<>();

				for (int i = 0; i < headers.length; i++) {
					record.put(headers[i], values[i]);
				}

				records.add(record);
			}
		}
		return records;
	}
}