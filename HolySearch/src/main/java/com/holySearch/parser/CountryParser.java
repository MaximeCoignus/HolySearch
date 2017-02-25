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

import com.holySearch.bean.Country;

public class CountryParser {

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
				Thread.currentThread().getContextClassLoader().getResourceAsStream("/countryJSON.txt"), Charset.forName("UTF-8")));

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

	public static ArrayList<String> getContinentNameList() throws Exception {
		JSONObject json = readJsonFromFile();
		ArrayList<String> continentNameList = new ArrayList<String>();
		String continentName = null;

		try {
			// on parcourt les éléments du résultat pour alimenter
			// l'arrayList de Country
			for (int i = 0; i < json.getJSONArray("elements").length(); i++) {

				if (!json.getJSONArray("elements").getJSONObject(i).getJSONObject("tags").isNull("is_in:continent")) {
					continentName = json.getJSONArray("elements").getJSONObject(i).getJSONObject("tags")
							.get("is_in:continent").toString();
				} else {
					continentName = "null";
				}

				continentNameList.add(continentName);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return continentNameList;
	}

	public static ArrayList<Country> getCountries() throws Exception {
		JSONObject json = readJsonFromFile();
		Country country = null;
		ArrayList<Country> countries = new ArrayList<Country>();
		String countryFrenchName = null;
		String countryEnglishName = null;
		float lat = 0.0f;
		float lon = 0.0f;
		float population = 0.0f;
		String currency = null;
		String isoa2 = null;
		String isoa3 = null;
		String wikiDescription = null;
		String wikiPictureUrl = null;

		try {
			// on parcourt les éléments du résultat pour alimenter
			// l'arrayList de Country
			for (int i = 0; i < json.getJSONArray("elements").length(); i++) {
				if (!json.getJSONArray("elements").getJSONObject(i).getJSONObject("tags").isNull("name:fr")) {

					countryFrenchName = json.getJSONArray("elements").getJSONObject(i).getJSONObject("tags")
							.get("name:fr").toString();
				}
				if (!json.getJSONArray("elements").getJSONObject(i).getJSONObject("tags").isNull("name:en")) {
					countryEnglishName = json.getJSONArray("elements").getJSONObject(i).getJSONObject("tags")
							.get("name:en").toString();
				}

				// on alimente la latitude et la longitude

				lat = Float.parseFloat(json.getJSONArray("elements").getJSONObject(i).get("lat").toString());

				lon = Float.parseFloat(json.getJSONArray("elements").getJSONObject(i).get("lon").toString());

				if (!json.getJSONArray("elements").getJSONObject(i).getJSONObject("tags").isNull("population")) {
					population = Float.parseFloat(json.getJSONArray("elements").getJSONObject(i).getJSONObject("tags")
							.get("population").toString());
				}

				if (!json.getJSONArray("elements").getJSONObject(i).getJSONObject("tags").isNull("ISO3166-1:alpha2")) {
					isoa2 = json.getJSONArray("elements").getJSONObject(i).getJSONObject("tags").get("ISO3166-1:alpha2")
							.toString();
				}

				if (!json.getJSONArray("elements").getJSONObject(i).getJSONObject("tags").isNull("ISO3166-1:alpha3")) {
					isoa3 = json.getJSONArray("elements").getJSONObject(i).getJSONObject("tags").get("ISO3166-1:alpha3")
							.toString();
				}

				if (!json.getJSONArray("elements").getJSONObject(i).getJSONObject("tags").isNull("currency")) {
					currency = json.getJSONArray("elements").getJSONObject(i).getJSONObject("tags").get("currency")
							.toString();
				}

				if (countryFrenchName != null && !countryFrenchName.isEmpty()) {
					wikiDescription = getWikiDescription(countryFrenchName);

					wikiPictureUrl = getWikiPictureUrl(countryFrenchName);
				}

				// on alimente l'objet et on l'ajouter à l'ArrayList
				country = new Country();
				country.setCountryFrenchName(countryFrenchName);
				country.setCountryEnglishName(countryEnglishName);
				country.setCountryLatitude(lat);
				country.setCountryLongitude(lon);
				country.setCountryPopulation(population);
				country.setCountryCurrency(currency);
				country.setCountryWikiDescription(wikiDescription);
				country.setCountryWikiPicture(wikiPictureUrl);
				country.setCountryIsoA2(isoa2);
				country.setCountryIsoA3(isoa3);
				countries.add(country);

			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return countries;
	}

	public static String getWikiDescription(String frenchName) throws Exception {
		Document xmlDocument = loadXML(
				"https://fr.wikipedia.org/w/api.php?format=xml&action=query&prop=extracts&exintro=&explaintext=&titles="
						+ frenchName.replace(" ", "%20"));
		String wikiDescription = null;

		try {
			if (xmlDocument.getElementsByTagName("extract").item(0) != null) {
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
			if (xmlDocument.getElementsByTagName("thumbnail").item(0) != null) {
				wikiPictureUrl = xmlDocument.getElementsByTagName("thumbnail").item(0).getAttributes()
						.getNamedItem("source").getTextContent();
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return wikiPictureUrl;
	}

}
