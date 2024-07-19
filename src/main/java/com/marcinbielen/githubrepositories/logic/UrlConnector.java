package com.marcinbielen.githubrepositories.logic;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

@Service
public class UrlConnector {

    public String getResponse(URL url) throws IOException {
        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Accept", "application/json");
        InputStream inputStream = con.getInputStream();
        StringBuilder sb = new StringBuilder();
        Scanner scanner = new Scanner(inputStream);
        while (scanner.hasNext()) {
            sb.append(scanner.next());
        }
        return sb.toString();
    }
}
