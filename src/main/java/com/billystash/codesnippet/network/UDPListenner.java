package com.billystash.codesnippet.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;

public class UDPListenner {

    public void openUDPConnection() {
        try {
            DatagramSocket datagramSocket = new DatagramSocket(10110, InetAddress.getByName("localhost"));
            byte[] buffer = new byte[2048];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

            while (true) {
                datagramSocket.receive(packet);
                String msg = new String(buffer, 0, packet.getLength());
                System.out.println(msg.trim());
                packet.setLength(buffer.length);
            }
        } catch (Exception e) {
            System.err.println();
        }
    }

    public boolean UDPConnectionIsAvailable() {
        boolean result;
        try {
            DatagramSocket datagramSocket = new DatagramSocket(10110, InetAddress.getByName("localhost"));
            datagramSocket.close();
            result = true;
        } catch (Exception e) {
            result = false;
        }
        return result;
    }
}
