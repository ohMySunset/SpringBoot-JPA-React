package org.zerock.test;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;

public class SimpleTest {

    @Test
    public void test1() throws Exception{
        // Jsoup을 이용한 크롤링
        System.out.println("Test1......");
        Document doc = Jsoup.connect("https://comic.naver.com/webtoon/list.nhn?titleId=755674").get();
        //System.out.println(doc);

        // 원하는 요소만 뽑아내기
        Elements tds = doc.select(".viewList td img");
        //System.out.println(tds);

        // 반복문 돌려서 원하는 요소 가져오기
        tds.forEach(element -> {
            String imgURL = element.attr("src");
            System.out.println(imgURL);
            String title = element.attr("title");
            System.out.println(title);

        });



    }

    @Test
    // URL을 통한 이미지 다운
    public void testSave() throws Exception{
        // 실제 이미지 경로
        String path = "https://thumbnail7.coupangcdn.com/thumbnails/remote/492x492ex/image/vendor_inventory/e007/730afbd2a08d21aefead55ba85fd0ad834243481ca27ac958564bfc93223.jpg";

        URL url = new URL(path);

        // 읽기 Read -> 데이터 빨이들이기
        InputStream in = url.openStream();
        // 파일로 저장할 경로
        File fos = new File("/Users/yang1217/Documents/zzz/sample.jpg");
        // 새파일에 기존파일을 복사하기 Files.copy(Stirng, String)
        Files.copy(in, fos.toPath());

    };

    @Test
    // URL을 통한 이미지 다운이 막혀있을 경우 -> Header 정보를 변경
    public void testSave2() throws Exception{
        // 받아올 이미지의 경로
        String path = "https://image-comic.pstatic.net/webtoon/771933/7/20210513103937_1d65bc3c93c3eb1513482c9f4831493e_IMAG01_4.jpg";

        URL url = new URL(path);
        // Header에서 정보를 가져오기 위해 HttpURLConnection 사용.
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        System.out.println(urlConnection);

        // HttpUrlConnection에 Header 추가하기 (input, output 허용, 캐싱은 불허)
        urlConnection.setDoOutput(true);
        urlConnection.setDoInput(true);
        urlConnection.setUseCaches(false);

        //  브라우저가 보내는 정보 (user-agent:키, 값)
        urlConnection.setRequestProperty("user-agent","Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36");

        InputStream in = urlConnection.getInputStream();

        // 파일로 저장할 경로
        File fos = new File("/Users/yang1217/Documents/zzz/sample3.jpg");

        // 새파일에 기존파일을 복사하기 Files.copy(String, String)
        Files.copy(in, fos.toPath());

    }

    @Test
    public void testSave3() throws Exception{
        // 웹툰 이미지 모두 저장하기

        Document doc = Jsoup.connect("https://comic.naver.com/webtoon/detail.nhn?titleId=732955&no=192&weekday=wed").get();
        Elements imgs = doc.select(".wt_viewer img");

        imgs.forEach(element -> {
            String imgUrl = element.attr("src");
            String imgId = element.id();

            URL url = null;

            try {

                url = new URL(imgUrl);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                urlConnection.setDoOutput(true);
                urlConnection.setDoInput(true);
                urlConnection.setUseCaches(false);

                urlConnection.setRequestProperty("user-agent","Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.114 Safari/537.36");

                InputStream in = urlConnection.getInputStream();

                File fos = new File("/Users/yang1217/Documents/zzz/webtoon1/" + imgId);

                Files.copy(in, fos.toPath());
                
            } catch (IOException e) {
                e.printStackTrace();
            }


        });



    }
}
