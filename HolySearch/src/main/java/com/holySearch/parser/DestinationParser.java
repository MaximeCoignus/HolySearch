package com.holySearch.parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import com.holySearch.bean.Destination;

public class DestinationParser {

	ArrayList<String> countriesNameList = new ArrayList<String>();
	ArrayList<Destination> destinations = new ArrayList<Destination>();

	/**
	 * @return the countriesNameList
	 */
	public ArrayList<String> getCountriesNameList() {
		return countriesNameList;
	}

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

	// fonction qui permet de retourner un objet JSON à partir d'une url
	private static JSONObject readJsonFromFile(String fileName) throws IOException {

		JSONObject json = null;
		BufferedReader rd = new BufferedReader(
				new InputStreamReader(Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName),
						Charset.forName("UTF-8")));

		String jsonText = readAll(rd);
		json = new JSONObject(jsonText);
		return json;
	}

	public static Document loadXML(String url) throws Exception {
		InputStream is = null;
		String xmlText = null;
		try {
			is = new URL(url).openStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			xmlText = readAll(rd);
		} catch (JSONException e) {
			e.printStackTrace();
		} finally {
			is.close();
		}
		DocumentBuilderFactory fctr = DocumentBuilderFactory.newInstance();
		DocumentBuilder bldr = fctr.newDocumentBuilder();
		InputSource insrc = new InputSource(new StringReader(xmlText));
		return bldr.parse(insrc);
	}

	public ArrayList<Destination> getDestinations() throws Exception {
		getBeaches();
		getSkiStations();
		System.out.println("taille " + destinations.size());
		return destinations;
	}

	public void getBeaches() throws Exception {
		JSONObject json = readJsonFromFile("/beachJSON.txt");

		try {
			// on parcourt les éléments du résultat pour alimenter
			// l'arrayList de Country
			for (int i = 0; i < json.getJSONArray("elements").length(); i++) {
				Destination beach = new Destination();
				String beachFrenchName = "null";
				String beachEnglishName = "null";
				float lat = 0.0f;
				float lon = 0.0f;
				String wikiDescription = "null";
				String wikiPictureUrl = "null";
				System.out.println(i);
				if (!json.getJSONArray("elements").getJSONObject(i).getJSONObject("tags").isNull("name")) {

					beachFrenchName = json.getJSONArray("elements").getJSONObject(i).getJSONObject("tags").get("name")
							.toString();

					beachEnglishName = beachFrenchName;

					System.out.println(" indice beach = " + i + " le nom est ok ");

					// on alimente la latitude et la longitude

					lat = Float.parseFloat(json.getJSONArray("elements").getJSONObject(i).get("lat").toString());

					lon = Float.parseFloat(json.getJSONArray("elements").getJSONObject(i).get("lon").toString());

					if (beachFrenchName != null && !beachFrenchName.isEmpty()) {
						wikiDescription = getWikiDescription(beachFrenchName);

						wikiPictureUrl = getWikiPictureUrl(beachFrenchName);
					}
					String countryName = "null";

					if (!json.getJSONArray("elements").getJSONObject(i).getJSONObject("tags").isNull("is_in:country")) {
						countryName = json.getJSONArray("elements").getJSONObject(i).getJSONObject("tags")
								.get("is_in:country").toString();
					}

					countriesNameList.add(countryName);

					// on alimente l'objet et on l'ajouter à l'ArrayList
					beach.setDestinationFrenchName(beachFrenchName);
					beach.setDestinationEnglishName(beachEnglishName);
					beach.setDestinationLatitude(lat);
					beach.setDestinationLongitude(lon);
					beach.setDestinationWikiDescription(wikiDescription);
					beach.setDestinationWikiPicture(wikiPictureUrl);
					beach.setDestinationType("beach");
					destinations.add(beach);
					System.out.println(beach + " country = " + countriesNameList.get(countriesNameList.size() - 1));
				}
			}

		} catch (

		JSONException e) {
			e.printStackTrace();
		}
	}

	public void getSkiStations() throws Exception {
		JSONObject json = readJsonFromFile("/skiStationJSON.txt");

		try {
			// on parcourt les éléments du résultat pour alimenter
			// l'arrayList de Country
			for (int i = 0; i < json.getJSONArray("elements").length(); i++) {
				Destination skiStation = new Destination();
				String skiStationFrenchName = "null";
				String skiStationEnglishName = "null";
				float lat = 0.0f;
				float lon = 0.0f;
				String wikiDescription = "null";
				String wikiPictureUrl = "null";
				System.out.println(i);
				if (!json.getJSONArray("elements").getJSONObject(i).isNull("skistationEnglishName")) {

					skiStationFrenchName = json.getJSONArray("elements").getJSONObject(i).get("skistationEnglishName")
							.toString();

					skiStationEnglishName = skiStationFrenchName;

					System.out.println(" indice skiStation = " + i + " le nom est ok ");

					// on alimente la latitude et la longitude

					lat = Float.parseFloat(json.getJSONArray("elements").getJSONObject(i).get("latitude").toString());

					lon = Float.parseFloat(json.getJSONArray("elements").getJSONObject(i).get("longitude").toString());

					if (skiStationFrenchName != null && !skiStationFrenchName.isEmpty()) {
						wikiDescription = getWikiDescription(skiStationFrenchName);

						wikiPictureUrl = getWikiPictureUrl(skiStationFrenchName);
					}
					String countryName = "null";

					if (!json.getJSONArray("elements").getJSONObject(i).isNull("country")) {
						countryName = json.getJSONArray("elements").getJSONObject(i).get("country").toString();
					}

					countriesNameList.add(countryName);

					// on alimente l'objet et on l'ajouter à l'ArrayList
					skiStation.setDestinationFrenchName(skiStationFrenchName);
					skiStation.setDestinationEnglishName(skiStationEnglishName);
					skiStation.setDestinationLatitude(lat);
					skiStation.setDestinationLongitude(lon);
					skiStation.setDestinationWikiDescription(wikiDescription);
					skiStation.setDestinationWikiPicture(wikiPictureUrl);
					skiStation.setDestinationType("skiStation");
					destinations.add(skiStation);
					System.out
							.println(skiStation + " country = " + countriesNameList.get(countriesNameList.size() - 1));
				}
			}

		} catch (

		JSONException e) {
			e.printStackTrace();
		}
	}

	public static String getWikiDescription(String frenchName) throws Exception {
		Document xmlDocument = loadXML(
				"https://fr.wikipedia.org/w/api.php?format=xml&action=query&prop=extracts&exintro=&explaintext=&titles="
						+ frenchName.replace(" ", "%20"));
		String wikiDescription = "null";

		try {
			if (xmlDocument != null && xmlDocument.getElementsByTagName("extract") != null
					&& xmlDocument.getElementsByTagName("extract").item(0) != null) {
				wikiDescription = xmlDocument.getElementsByTagName("extract").item(0).getTextContent();
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return wikiDescription;
	}

	public static String getWikiPictureUrl(String frenchName) throws Exception {
		Document xmlDocument = loadXML(
				"https://fr.wikipedia.org/w/api.php?format=xml&action=query&prop=pageimages&pithumbsize=9000&titles="
						+ frenchName.replace(" ", "%20"));
		String wikiPictureUrl = "null";

		try {
			if (xmlDocument != null && xmlDocument.getElementsByTagName("thumbnail") != null
					&& xmlDocument.getElementsByTagName("thumbnail").item(0) != null) {
				wikiPictureUrl = xmlDocument.getElementsByTagName("thumbnail").item(0).getAttributes()
						.getNamedItem("source").getTextContent();
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return wikiPictureUrl;
	}

}
