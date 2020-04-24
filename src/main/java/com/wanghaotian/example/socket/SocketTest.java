package com.wanghaotian.example.socket;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;

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
@Slf4j
public class SocketTest {
    public static void main(String[] args) throws IOException {
        SocketTest socketTest = new SocketTest();
        Socket socket = socketTest.getNewSocketConnect();
        if (ObjectUtils.isNotEmpty(socket)) {
            Scanner scanner = new Scanner(System.in);
            log.info("等待输入!");
            socketTest.keepLive(socket);
            while (scanner.hasNext()) {
                String outString = scanner.next();
                OutputStream outputStream = socket.getOutputStream();
                outputStream.write(outString.getBytes(), 0, outString.length());
                outputStream.flush();
            }
        }

    }

    private void keepLive(final Socket socket) {
        Thread thread = new Thread(() -> {
            while (true) {
                InputStream inputStream ;
                try {
                    inputStream = socket.getInputStream();
                    int length = inputStream.available();
                    byte[] bytes = new byte[length];
                    int inputSize=inputStream.read(bytes);
                    String s = new String(bytes,0,inputSize,FONT_CODE);
                    if (IF_LIVE_STRING.equals(s)) {
                            log.info("接收到服务端发起的心跳报文检验!");
                            socket.getOutputStream().write(LIVE_STRING.getBytes());
                    }
                }  catch (IOException e) {
                    log.info("发生异常:%s",e.getMessage());
                }
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    log.info("发生中断异常!");
                    Thread.currentThread().interrupt();
                }
            }
        });
        thread.setName("keepLive");
        thread.start();
    }

    private Socket getNewSocketConnect() {
        try(Socket socket=new Socket()) {
            SocketAddress socketAddress = new InetSocketAddress(HOST_NAME, PORT);
            socket.connect(socketAddress);
            return socket;
        } catch (IOException i) {
            log.info("发生异常!%s",i.getMessage());
        }
        return null;
    }

}
