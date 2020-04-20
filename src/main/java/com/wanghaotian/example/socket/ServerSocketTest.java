package com.wanghaotian.example.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import static com.wanghaotian.example.socket.SocketProperty.*;

/**
 * author;Wanghaotian
 * data:2020/4/20 0020
 */

public class ServerSocketTest {
    private static ServerSocket serverSocket;

    static {
        try {
            serverSocket = new ServerSocket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private final List<Socket> sockets = Collections.synchronizedList(new ArrayList<>());
    private final List<Socket> haveHeartSockets = Collections.synchronizedList(new ArrayList<>());

    public static ServerSocket getServerSocket() {
        return serverSocket;
    }

    public static void setServerSocket(ServerSocket serverSocket) {
        ServerSocketTest.serverSocket = serverSocket;
    }

    public static void main(String[] args) throws IOException {
        ServerSocketTest serverSocketTest = new ServerSocketTest();
        serverSocketTest.init();
    }

    public void init() throws IOException {
        serverSocket = new ServerSocket(PORT);
        listen();
        listenCount();
        showMessage();
        haveHeatBeat();
    }

    private void listen() {
        Thread thread = new Thread(() -> {
            while (true) {
                System.out.println("等待连接.");
                Socket socket = null;
                try {
                    socket = serverSocket.accept();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();
                writeLock.lock();
                sockets.add(socket);
                haveHeartSockets.add(socket);
                System.out.println("现在已经有" + sockets.size() + "个客户端进行连接.");
                writeLock.unlock();
            }
        });
        thread.setName("listen");
        thread.start();
    }

    private void listenCount() {
        Thread thread = new Thread(() -> {
            while (true) {
                ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();
                writeLock.lock();
                if (haveHeartSockets.size() != sockets.size()) {
                    sockets.clear();
                    sockets.addAll(haveHeartSockets);
                    System.out.println("现在已经有" + sockets.size() + "个客户端进行连接.");
                }
                writeLock.unlock();
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.setName("listenCount");
        thread.start();
    }

    private void showMessage() {
        Thread thread = new Thread(() -> {
            while (true) {
                ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
                readLock.lock();
                int size = sockets.size();
                for (int i = 0; i < size; i++) {
                    try {
                        Socket socket = sockets.get(i);
                        int length = socket.getInputStream().available();
                        if (length != 0) {
                            byte[] bytes = new byte[length];
                            socket.getInputStream().read(bytes);
                            System.out.println("第" + (i + 1) + "个Socket消息:" + new String(bytes, StandardCharsets.UTF_8));
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                readLock.unlock();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
        thread.setName("showMessage");
        thread.start();

    }


    private void haveHeatBeat() throws IOException {
        Thread thread = new Thread(() -> {
            while (true) {
                int i = 0;
                ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
                Socket tsocket=null;
                try {
                    readLock.lock();
                    for (Socket socket : sockets) {
                        if (!socket.isClosed()) {
                           tsocket=socket;
                           socket.getOutputStream().write(IF_LIVE_STRING.getBytes());
                        }
                    }
                } catch (SocketException socketException)
                {
                    haveHeartSockets.remove(tsocket);
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    readLock.unlock();
                }
            }
        });

        try {
            Thread.sleep(2500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.setName("HaveHeatBeat");
        thread.start();
    }

    public List<Socket> getSockets() {
        return sockets;
    }
}
