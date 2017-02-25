package com.holySearch.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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
import org.springframework.util.StringUtils;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import com.holySearch.bean.City;

public class CityParser {

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
	private static JSONObject readJsonFromFile() throws IOException {

		JSONObject json = null;
		BufferedReader rd = new BufferedReader(new InputStreamReader(
				Thread.currentThread().getContextClassLoader().getResourceAsStream("/cityJSON.txt"), Charset.forName("UTF-8")));
		System.out.println("add cities");

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

	public static ArrayList<String> getCountryNameList() throws Exception {
		JSONObject json = readJsonFromFile();
		ArrayList<String> countryNameList = new ArrayList<String>();
		String countryName = null;

		try {
			// on parcourt les éléments du résultat pour alimenter
			// l'arrayList de Country
			for (int i = 0; i < json.getJSONArray("elements").length(); i++) {

				if (!json.getJSONArray("elements").getJSONObject(i).getJSONObject("tags").isNull("is_in:country")) {
					countryName = json.getJSONArray("elements").getJSONObject(i).getJSONObject("tags")
							.get("is_in:country").toString();
				} else {
					countryName = "null";
				}

				countryNameList.add(countryName);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return countryNameList;
	}

	public static ArrayList<City> getCities() throws Exception {
		JSONObject json = readJsonFromFile();
		City city = null;
		ArrayList<City> cities = new ArrayList<City>();
		String cityFrenchName = null;
		String cityEnglishName = null;
		float lat = 0.0f;
		float lon = 0.0f;
		float population = 0.0f;
		String wikiDescription = null;
		String wikiPictureUrl = null;
		String capital = "no";

		try {
			// on parcourt les éléments du résultat pour alimenter
			// l'arrayList de Country
			for (int i = 0; i < json.getJSONArray("elements").length(); i++) {
				if (!json.getJSONArray("elements").getJSONObject(i).getJSONObject("tags").isNull("name:fr")) {

					cityFrenchName = json.getJSONArray("elements").getJSONObject(i).getJSONObject("tags").get("name:fr")
							.toString();
				}
				if (!json.getJSONArray("elements").getJSONObject(i).getJSONObject("tags").isNull("name:en")) {
					cityEnglishName = json.getJSONArray("elements").getJSONObject(i).getJSONObject("tags")
							.get("name:en").toString();
				}

				// on alimente la latitude et la longitude

				lat = Float.parseFloat(json.getJSONArray("elements").getJSONObject(i).get("lat").toString());

				lon = Float.parseFloat(json.getJSONArray("elements").getJSONObject(i).get("lon").toString());

				if (!json.getJSONArray("elements").getJSONObject(i).getJSONObject("tags").isNull("population")
						&& (StringUtils.countOccurrencesOf(json.getJSONArray("elements").getJSONObject(i)
								.getJSONObject("tags").get("population").toString(), ".") <= 1)) {
					try {
						population = Float
								.parseFloat(json.getJSONArray("elements").getJSONObject(i).getJSONObject("tags")
										.get("population").toString().replaceAll(" ", "").replace(",", "."));
					} catch (NumberFormatException nfe) {
						population = 0.0f;
					}
				}

				if (cityFrenchName != null && !cityFrenchName.isEmpty()) {
					wikiDescription = getWikiDescription(cityFrenchName);

					wikiPictureUrl = getWikiPictureUrl(cityFrenchName);
				}

				// on alimente l'objet et on l'ajouter à l'ArrayList
				city = new City();
				city.setCityFrenchName(cityFrenchName);
				city.setCityEnglishName(cityEnglishName);
				city.setCityLatitude(lat);
				city.setCityLongitude(lon);
				city.setCityPopulation(population);
				city.setCityWikiDescription(wikiDescription);
				city.setCityWikiPicture(wikiPictureUrl);
				if (!json.getJSONArray("elements").getJSONObject(i).getJSONObject("tags").isNull("capital")) {
					capital = json.getJSONArray("elements").getJSONObject(i).getJSONObject("tags").get("capital")
							.toString();
				}
				if ("yes".equals(capital)) {
					city.setCapital(true);
				} else {
					city.setCapital(false);
				}
				cities.add(city);
				System.out.println(city);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return cities;
	}

	public static String getWikiDescription(String frenchName) throws Exception {
		Document xmlDocument = loadXML(
				"https://fr.wikipedia.org/w/api.php?format=xml&action=query&prop=extracts&exintro=&explaintext=&titles="
						+ frenchName.replace(" ", "%20"));
		String wikiDescription = null;

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
		String wikiPictureUrl = null;

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
