package com.billystash.codesnippet.network.TCPClientListenner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class TCPSocketClientListenner {

    public void openTCPConnection() {
        try (Socket socket = new Socket("localhost", 2020)) {
            while (true) {
                InputStream input = socket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));

                System.out.println(reader.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean TCPConnectionIsAvailable() {
        boolean result;
        try {
            Socket socket = new Socket("localhost", 2020);
            socket.close();
            result = true;
        } catch (Exception e) {
            result = false;
        }
        return result;
    }
}
