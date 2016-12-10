package com.holySearch.parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import com.holySearch.bean.Beach;

public class BeachParser
{
	//public static JSONObject json = readJsonFromUrl("http://overpass-api.de/api/interpreter?data=[out:json];area[name=%22France%22];(node[natural=%22beach%22](area););out;");

	//fonction intermédiaire qui permet de lire une entrée entierement
	private static String readAll(Reader rd)
	{
		StringBuilder sb = new StringBuilder();
		int cp;
		try
		{
			while ((cp = rd.read()) != -1)
			{
				sb.append((char) cp);
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		return sb.toString();
	}

	//fonction qui permet de retourner un objet JSON à partir d'une url
	private static JSONObject readJsonFromUrl(String url) throws IOException
	{
		JSONObject json = null;
		InputStream is = null;
		try
		{
			is = new URL(url).openStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			String jsonText = readAll(rd);
			json = new JSONObject(jsonText);
		}
		catch(JSONException e)
		{
			e.printStackTrace();
		}
		finally
		{
			is.close();
		}
		return json;
	}

	public static ArrayList<Beach> getBeaches(String url) throws IOException
	{
		JSONObject json = readJsonFromUrl(url);
		Beach beach = null;
		ArrayList <Beach> beaches = new ArrayList<Beach>();
		String beachName = null;
		float lat = 0.0f;
		float lon = 0.0f;
		
		try
		{
			//on parcourt les éléments du résultat pour alimenter l'arrayList de Beach
			for(int i = 0; i < json.getJSONArray("elements").length(); i++)
			{
				//le name n'est pas toujours disponible pour éviter les erreurs on l'encapsule dans un if
				if(! json.getJSONArray("elements").getJSONObject(i).getJSONObject("tags").isNull("name"))
				{
					beachName = json.getJSONArray("elements").getJSONObject(i).getJSONObject("tags").get("name").toString();
				}
				else;
				
				//on alimente la latitude et la longitude
				lat = Float.parseFloat(json.getJSONArray("elements").getJSONObject(i).get("lat").toString());
				lon = Float.parseFloat(json.getJSONArray("elements").getJSONObject(i).get("lon").toString());
				
				//on alimente l'objet et on l'ajouter à l'ArrayList
				beach = new Beach();
				beach.setBeachName(beachName);
				beach.setLatitude(lat);
				beach.setLongitude(lon);
				beaches.add(beach);
				
				//on remet le beachName à null pour ne pas avoir de doublon au niveau des noms
				beachName = null;
			}
		}
		catch(JSONException e)
		{
			e.printStackTrace();
		}
		return beaches;
	}
}
