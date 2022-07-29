package com.example.bank.exchangedate;


import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;

import com.google.gson.Gson;
import com.mysql.cj.xdevapi.JsonParser;
import net.bytebuddy.description.method.MethodDescription;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.SECONDS;


public class ExchangeDateService {
    public static void main(String[]args) throws IOException, URISyntaxException, InterruptedException{
       getRatesList("BYN","30 days");

    }

    public static Map<String,Double> getRatesList (String currencyCod,String date) throws URISyntaxException, IOException, InterruptedException {
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateForUrl =simpleDateFormat.format(date1);
          String dateForUrlstart= createDate(date);
        String url_str = "https://api.exchangerate.host/timeseries?start_date="+dateForUrlstart+"&end_date="+dateForUrl+"base="+currencyCod;
        URI url = new URI(url_str);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(url)
                .timeout(Duration.of(15, SECONDS))
                .build();

        // HttpURLConnection request = (HttpURLConnection) url.openConnection();
        // request.connect();
        //  System.out.println(request.getResponseMessage());
        HttpResponse<String>stringHttpResponse = HttpClient.newBuilder().build().
                send(request,HttpResponse.BodyHandlers.ofString());
        System.out.println(stringHttpResponse.body());

        Map<String ,Double>stringDoubleMap=connect(stringHttpResponse.body(),"RUB");
        Set<Map.Entry<String,Double>> entrySet = stringDoubleMap.entrySet();
        for ( Map.Entry<String,Double> i :entrySet) {
            System.out.println(i);
        }
        return stringDoubleMap;
    }
    public static String createDate(String date){
        Date date1 = new Date();
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateForUrl = "";
        switch (date){
            case "5 days":calendar.add(Calendar.DAY_OF_YEAR, -5);
                date1= calendar.getTime();
                dateForUrl =simpleDateFormat.format(date1);
                break;
            case "30 days":calendar.add(Calendar.DAY_OF_YEAR, -30);
                date1= calendar.getTime();
                dateForUrl =simpleDateFormat.format(date1);
                break;
            case "180 days":calendar.add(Calendar.DAY_OF_YEAR, -180);
                date1= calendar.getTime();
                dateForUrl =simpleDateFormat.format(date1);
                break;
            case "365 days": calendar.add(Calendar.DAY_OF_YEAR, -365);
                date1= calendar.getTime();
                dateForUrl =simpleDateFormat.format(date1);
                break;
        }
        return dateForUrl;

    }





    public static Map<String, Double> connect(String body, String code) {
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(body,JsonObject.class);
        JsonObject currency = jsonObject.getAsJsonObject("rates");

        Map<String, Map<String, Double>> map = gson.fromJson(currency, new TypeToken<Map<String, Map<String, Double>>>(){}.getType());
        // через Type Token мы заполняем мапу
       Map<String,Double>stringStringMap = map.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey,x-> x.getValue().get(code)));


       return stringStringMap;

       // double a = currency.getDouble("RUB");
    }




}

