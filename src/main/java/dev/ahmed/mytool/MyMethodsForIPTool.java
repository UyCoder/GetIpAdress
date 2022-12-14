package dev.ahmed.mytool;

import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Ahmed Bughra
 * @create 2022-12-14  8:48 PM
 */
public class MyMethodsForIPTool {


    public static void getIpAdressAndLocationsFromUrl(String url) throws Exception {
        String content = getHTMLContentAsString(url);
        int no = 0;
        String resultHeader= "\n"+"This is a result from url: " + url+
                "\n"+"Date  : "+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"))+"\n"+"==========================="+"\n";
        Pattern pattern = Pattern.compile("\\d+\\.\\d+\\.\\d+\\.\\d+");
        Matcher matcher = pattern.matcher(content);

        FileWriter fileWriter = new FileWriter(System.getProperty("user.home")+"\\Desktop\\IpsAndCountrys.txt", true);
        fileWriter.write(resultHeader);
        while (matcher.find()) {
            String group = matcher.group(0);
            String lineText = (++no) +"\t" +group+ "\t \t"+ getIpInfos(group).get(0) + "\t \t"+ getIpInfos(group) + "\t" +"\n";
            System.out.print(lineText);
            fileWriter.write(lineText);
        }
        fileWriter.close();

    }





    public static String getHTMLContentAsString(String urlString) throws IOException {
        // Make an HTTP request to the URL of the web page
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // Get the HTML content as an InputStream
        InputStream inputStream = connection.getInputStream();

        // Read the HTML content from the InputStream as a string
        InputStreamReader reader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(reader);
        StringBuilder htmlStringBuilder = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            htmlStringBuilder.append(line);
        }
        String htmlString = htmlStringBuilder.toString();

        return htmlString;
    }



    public static List<String> getIpInfos(String ip) throws Exception {
        URL url = new URL("http://ip-api.com/json/" + ip);

        // Open a connection to the URL and get the InputStream
        InputStream input = url.openStream();

        // Use the InputStreamReader and BufferedReader classes to read the response
        InputStreamReader reader = new InputStreamReader(input);
        BufferedReader buffer = new BufferedReader(reader);

        // Read the response line by line and store it in a StringBuilder
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = buffer.readLine()) != null) {
            response.append(line);
        }
        buffer.close();

        // Use the JSONObject class to parse the response
        JSONObject json = new JSONObject(response.toString());

        // Get the country_name property from the JSON object
        String country = json.getString("country");
        String regionName = json.getString("regionName");
        String city = json.getString("city");
        String lat = json.getString("lat");
        String lon = json.getString("lon");
        List<String> ipInfo = new ArrayList<String>();
        ipInfo.add(country);
        ipInfo.add(regionName);
        ipInfo.add(city);
        ipInfo.add(lat);
        ipInfo.add(lon);

        // Return the country name
        return ipInfo;
    }

}
