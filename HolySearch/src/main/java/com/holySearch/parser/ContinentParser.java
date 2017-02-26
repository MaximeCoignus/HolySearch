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
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.holySearch.bean.Continent;

public class ContinentParser {

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
				Thread.currentThread().getContextClassLoader().getResourceAsStream("/continentJSON.txt"),
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

	public static ArrayList<Continent> getContinents() throws Exception {
		JSONObject json = readJsonFromFile();
		ArrayList<Continent> continents = new ArrayList<Continent>();

		try {
			// on parcourt les éléments du résultat pour alimenter
			// l'arrayList de Continent
			for (int i = 0; i < json.getJSONArray("elements").length(); i++) {
				String continentFrenchName = "null";
				String continentEnglishName = "null";
				float lat = 0.0f;
				float lon = 0.0f;
				float population = 0.0f;
				float sizekm = 0.0f;
				String wikiDescription = "null";
				String wikiPictureUrl = "null";

				Continent continent = new Continent();

				continentFrenchName = json.getJSONArray("elements").getJSONObject(i).getJSONObject("tags")
						.get("name:fr").toString();
				continentEnglishName = json.getJSONArray("elements").getJSONObject(i).getJSONObject("tags")
						.get("name:en").toString();

				// on alimente la latitude et la longitude
				lat = Float.parseFloat(json.getJSONArray("elements").getJSONObject(i).get("lat").toString());
				lon = Float.parseFloat(json.getJSONArray("elements").getJSONObject(i).get("lon").toString());

				population = Float.parseFloat(json.getJSONArray("elements").getJSONObject(i).getJSONObject("tags")
						.get("population").toString());
				sizekm = Float.parseFloat(
						json.getJSONArray("elements").getJSONObject(i).getJSONObject("tags").get("sqkm").toString());

				wikiDescription = getWikiDescription(continentFrenchName);

				wikiPictureUrl = getWikiPictureUrl(continentFrenchName);

				// on alimente l'objet et on l'ajouter à l'ArrayList
				continent.setContinentFrenchName(continentFrenchName);
				continent.setContinentEnglishName(continentEnglishName);
				continent.setContinentLatitude(lat);
				continent.setContinentLongitude(lon);
				continent.setContinentPopulation(population);
				continent.setContinentSizeKilometer(sizekm);
				continent.setContinentWikiDescription(wikiDescription);
				continent.setContinentWikiPicture(wikiPictureUrl);
				continents.add(continent);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return continents;
	}

	public static String getWikiDescription(String frenchName) throws Exception {
		Document xmlDocument = loadXML(
				"https://fr.wikipedia.org/w/api.php?format=xml&action=query&prop=extracts&exintro=&explaintext=&titles="
						+ frenchName.replace(" ", "%20"));
		String wikiDescription = "null";

		try {
			wikiDescription = xmlDocument.getElementsByTagName("extract").item(0).getTextContent();
			System.out.println(wikiDescription);
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
			wikiPictureUrl = xmlDocument.getElementsByTagName("thumbnail").item(0).getAttributes()
					.getNamedItem("source").getTextContent();
			System.out.println(wikiPictureUrl);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return wikiPictureUrl;
	}

}
