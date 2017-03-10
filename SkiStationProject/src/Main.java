import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;
import eu.bitm.NominatimReverseGeocoding.Address;
import eu.bitm.NominatimReverseGeocoding.NominatimReverseGeocodingJAPI;

public class Main {

	public static void main(String[] args) throws Exception {

		// CSV Parsing + Skip first line (column name)
		CSVReader reader = new CSVReader(new FileReader("DATA/ski.csv"), ';', '\'', 1);

		// Stock all CSV in List
		List<String[]> allRows = reader.readAll();

		// Préparation stockage + écriture Lignes rejetées
		String csvKO = "DATA/OUTPUT/skiped.csv";
		CSVWriter writerSkipedRows = new CSVWriter(new FileWriter(csvKO, true), ';', CSVWriter.NO_QUOTE_CHARACTER);

		// Préparation stockage + écriture Lignes persistées
		String csvOK = "DATA/OUTPUT/skistation.csv";
		CSVWriter writerRowsOK = new CSVWriter(new FileWriter(csvOK, true), ';', CSVWriter.NO_QUOTE_CHARACTER);

		// Initialisation numéro d'itération
		int noIteration = 0;
		int noSkistationOK = 0;
		int noSkistationSkiped = 0;

		// Foreach Line in CSV
		for (String[] row : allRows) {
			// log(Arrays.toString(row));

			// Numéro d'itération
			noIteration += 1;
			log("Itération numéro = " + noIteration);

			// Création des métadonnées
			String skistationEnglishName = "null";
			float latitude = 0;
			float longitude = 0;
			String wikiDescription = "null";
			String wikiPictureUrl = "null";
			String country = "null";
			String countryCode = "null";

			// Extraction des données
			log("Extraction des données");
			skistationEnglishName = row[0];
			log("Name  = " + row[0]);
			latitude = Float.parseFloat(row[1]);
			log("Latitude = " + latitude);
			longitude = Float.parseFloat(row[2]);
			log("Longitude = " + longitude);

			// Sauter les données qui ont des caractères spéciaux SAUF "." "-" "\" "(" ")"
			Pattern specialChars = Pattern.compile("[^a-z0-9.\\-/\\(\\) ]", Pattern.CASE_INSENSITIVE);
			Matcher m = specialChars.matcher(skistationEnglishName);
			boolean isDirty = m.find();

			// SI caractères spéciaux OU nom vide -> LOG
			if (isDirty || skistationEnglishName == null || skistationEnglishName.isEmpty()) {

				log("Nom de station de ski INCORRECT -> Rejet");
				
				noSkistationSkiped += 1;
				
				log("numéro de station de ski INCORRECT = " + noSkistationSkiped);
				
				String[] skipedRow = new String[4];

				String noSkistationSkipedString = Integer.toString(noSkistationSkiped);
				String latitudeString = Float.toString(latitude);
				String longitudeString = Float.toString(longitude);

				// Loguer les lignes sautées
				skipedRow[0] = noSkistationSkipedString;
				skipedRow[1] = skistationEnglishName;
				skipedRow[2] = latitudeString;
				skipedRow[3] = longitudeString;

				writerSkipedRows.writeNext((String[]) skipedRow);
				writerSkipedRows.flush();

				log("Ligne rejetée !!!!");
				log("----------------------------------------------");

				continue;
			}

			// SI PAS DE caractères rejetés
			if (!isDirty) {
				
				log("Nom de station de ski correct");
				
				noSkistationOK += 1;
				
				log("numéro de station de ski = " + noSkistationOK);								
				
				// Reverse GeoCoding (Risque TimeOut)									
				List<String> adresseReverse = getCountryNameAndCityNameWithAPI(latitude, longitude);
				country = adresseReverse.get(0);
				log("Country = " + country);
				countryCode = adresseReverse.get(1);
				log("CountryCode = " + countryCode);

				// Wikidescription + Wikipicture
				if (skistationEnglishName != null && !skistationEnglishName.isEmpty()) {
					wikiDescription = getWikiDescription(skistationEnglishName);
					log("WikiDescription = " + wikiDescription);

					wikiPictureUrl = getWikiPictureUrl(skistationEnglishName);
					log("WikiPicture = " + wikiPictureUrl);
				}

				// LOG
				String[] rowOk = new String[8];

				String noSkistationOKString = Integer.toString(noSkistationOK);
				String latitudeString = Float.toString(latitude);
				String longitudeString = Float.toString(longitude);

				rowOk[0] = noSkistationOKString;
				rowOk[1] = skistationEnglishName;
				rowOk[2] = latitudeString;
				rowOk[3] = longitudeString;
				rowOk[4] = country;
				rowOk[5] = countryCode;
				rowOk[6] = wikiDescription;
				rowOk[7] = wikiPictureUrl;

				writerRowsOK.writeNext((String[]) rowOk);
				writerRowsOK.flush();

				// Persistence en JSON
				JSONObject obj = new JSONObject();
				obj.put("no", noSkistationOKString);
				obj.put("skistationEnglishName", skistationEnglishName);
				obj.put("latitude", latitude);
				obj.put("longitude", longitude);
				obj.put("country", country);
				obj.put("countryCode", countryCode);
				obj.put("wikiDescription", wikiDescription);
				obj.put("wikiPictureUrl", wikiPictureUrl);

				FileWriter file = new FileWriter("DATA/OUTPUT/skiStationJSON.txt", true);

				// try-with-resources statement based on post comment below :)
				try {
					file.write(obj.toString());
					log("Ajout au JSON OK");
					file.flush();
				} catch (Exception e) {
					e.printStackTrace();
				}

				log("Ligne OK");
				log("----------------------------------------------");
			}
		}
	}

	private static List<String> getCountryNameAndCityNameWithAPI(double latitude, double longitude) throws Exception {
		log("Reverse Geocoding");

		NominatimReverseGeocodingJAPI nominatim1 = new NominatimReverseGeocodingJAPI(18);

		Address adresse = nominatim1.getAdress(latitude, longitude);

		List<String> countryData = new ArrayList<String>();

		String country = "null";
		String countryCode = "null";
		String city = "null";
		String name = "null";

		if (adresse != null) {

			country = adresse.getCountry();
			countryCode = adresse.getCountryCode();

			countryData.add(country);
			countryData.add(countryCode);

			return countryData;

		}
		return null;

	}

	public static void log(String string) throws IOException {
		System.out.println(string);
		FileWriter writer = new FileWriter("DATA/OUTPUT/log.txt", true);
		Date date = new Date();
		writer.write(date.toString() + " * " + string + "\n");
		writer.close();
	}
	
	// Fonction de récupération de la description Wikipédia
	public static String getWikiDescription(String frenchName) throws Exception {
		Document xmlDocument = loadXML(
				"https://fr.wikipedia.org/w/api.php?format=xml&action=query&prop=extracts&exintro=&explaintext=&titles="
						+ frenchName.replace(" ", "%20"));
		String wikiDescription = "null";

		if (xmlDocument != null && xmlDocument.getElementsByTagName("extract") != null
				&& xmlDocument.getElementsByTagName("extract").item(0) != null) {
			wikiDescription = xmlDocument.getElementsByTagName("extract").item(0).getTextContent();
		}
		return wikiDescription;
	}

	// Fonction de récupération de la photo Wikipédia
	public static String getWikiPictureUrl(String frenchName) throws Exception {
		Document xmlDocument = loadXML(
				"https://fr.wikipedia.org/w/api.php?format=xml&action=query&prop=pageimages&pithumbsize=9000&titles="
						+ frenchName.replace(" ", "%20"));
		String wikiPictureUrl = "null";

		if (xmlDocument != null && xmlDocument.getElementsByTagName("thumbnail") != null
				&& xmlDocument.getElementsByTagName("thumbnail").item(0) != null) {
			wikiPictureUrl = xmlDocument.getElementsByTagName("thumbnail").item(0).getAttributes()
					.getNamedItem("source").getTextContent();
		}
		return wikiPictureUrl;
	}

	//
	public static Document loadXML(String url) throws Exception {
		InputStream is = null;
		String xmlText = null;
		try {
			is = new URL(url).openStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			xmlText = readAll(rd);
		} finally {
			is.close();
		}
		DocumentBuilderFactory fctr = DocumentBuilderFactory.newInstance();
		DocumentBuilder bldr = fctr.newDocumentBuilder();
		InputSource insrc = new InputSource(new StringReader(xmlText));
		return bldr.parse(insrc);
	}

	//
	private static String readAll(Reader rd) {
		StringBuilder sb = new StringBuilder();
		int cp;
		try {
			while ((cp = rd.read()) != -1) {
				sb.append((char) cp);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

}