package com.wanghaotian.example.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Scanner;

import static com.wanghaotian.example.socket.SocketProperty.*;

/**
 * author;Wanghaotian
 * data:2020/4/20 0020
 */
public class SocketTest {
    public static void main(String[] args) throws IOException {
        SocketTest socketTest = new SocketTest();
        Socket socket = socketTest.getNewSocketConnect();
        Scanner scanner = new Scanner(System.in);
        System.out.println("等待输入");
        socketTest.keepLive(socket);
        while (scanner.hasNext()) {
            String outString = scanner.next();
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write(outString.getBytes(), 0, outString.length());
            outputStream.flush();
        }


    }

    private void keepLive(final Socket socket) {
        Thread thread = new Thread(() -> {
            while (true) {
                InputStream inputStream = null;
                try {
                    inputStream = socket.getInputStream();
                    int length = 0;
                    length = inputStream.available();
                    byte[] bytes = new byte[length];
                    inputStream.read(bytes);
                    String s = new String(bytes, FONT_CODE);

                    if (IF_LIVE_STRING.equals(s)) {
                        try {
                            socket.getOutputStream().write(LIVE_STRING.getBytes());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    private Socket getNewSocketConnect() throws IOException {
        Socket socket = new Socket();
        SocketAddress socketAddress = new InetSocketAddress(HOST_NAME, PORT);
        socket.connect(socketAddress);
        return socket;
    }

}
