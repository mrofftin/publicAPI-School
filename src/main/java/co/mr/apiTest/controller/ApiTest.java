package co.mr.apiTest.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.IOException;

@RestController
public class ApiTest {

    @GetMapping("/apiTest")
    public String apiTest() throws IOException {
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1360000/TourStnInfoService/getTourStnVilageFcst"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=NgM4AEfqR5lvPHpMnDdOqa1EgpcRUSBeiKmvLmo3RyleWNOBGKNWS%2FNPLpkP12MlI6GGER6keJzILgL2dnuUuQ%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("dataType", "UTF-8") + "=" + URLEncoder.encode("JSON", "UTF-8")); /*요청자료형식(XML/JSON)*/
        urlBuilder.append("&" + URLEncoder.encode("CURRENT_DATE", "UTF-8") + "=" + URLEncoder.encode("2022011710", "UTF-8")); /*2016-12-01 01시부터 조회*/
        urlBuilder.append("&" + URLEncoder.encode("HOUR", "UTF-8") + "=" + URLEncoder.encode("24", "UTF-8")); /*CURRENT_DATE부터 24시간 후까지의 자료 호출*/
        urlBuilder.append("&" + URLEncoder.encode("COURSE_ID", "UTF-8") + "=" + URLEncoder.encode("341", "UTF-8")); /*관광 코스ID*/
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();

//        Gson gson = new GsonBuilder().create();
        // 두번째 인자: 변환하고자 하는 객체
//        DaejeonTourList dinfo = gson.fromJson(sb.toString(), DaejeonTourList.class);

//        return dinfo; // 객체 리턴

        return sb.toString();
    }
}
