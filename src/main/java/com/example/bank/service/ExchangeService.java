package com.example.bank.service;

import com.example.bank.exchangedate.ExchangeDateService;


import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ExchangeService {
    public static void main(String[] args) throws IOException, JSONException, ParseException {
        exchange();

    }
    public static void exchange() throws IOException, JSONException, ParseException {
        Calendar cal = Calendar.getInstance();
        for (int i =0;i < 365;i++) {
            cal.add(Calendar.DAY_OF_YEAR, -1);
            Date date1 = cal.getTime();


            final String NEW_FORMAT = "yyyy-MM-dd";

            String newDateString;

            SimpleDateFormat sdf = new SimpleDateFormat(NEW_FORMAT);
            String format = sdf.format(date1);

            String url_str = "https://api.exchangerate.host/" + format;

            URL url = new URL(url_str);

            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            request.connect();


            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(request.getInputStream()));
            String output = "";
            while ((output = bufferedReader.readLine()) != null) { //берем строку в неё записываю каждую новую строчку и сразу проверяем на null
                json(output);
            }
        }
    }
  //  public static String dateformat(Date date){
  //      Calendar cal =Calendar.getInstance();
  //      cal.setTime(date);
  //      cal.add(Calendar.DAY_OF_YEAR, -1);
  //      Date date1= cal.getTime();
  //      List<String> stringList = new ArrayList<>();
  //
  //  }

    public static void json(String output) throws JSONException {
        JSONObject jsonObject = new JSONObject(output);
        JSONObject currency= jsonObject.getJSONObject("rates");
        double a = currency.getDouble("RUB");
        System.out.println(a);
    }

    public Set<Map.Entry<String, Double>>exchangeDiagram(String period, String currencyCod) throws URISyntaxException, IOException, InterruptedException {
        return  ExchangeDateService.getRatesList(currencyCod, period).entrySet();

    }

}
