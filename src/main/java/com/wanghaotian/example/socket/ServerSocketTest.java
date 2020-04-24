package com.wanghaotian.example.socket;

import lombok.extern.slf4j.Slf4j;

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
@Slf4j
public class ServerSocketTest {
    private ServerSocket serverSocket;
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private final List<Socket> sockets = Collections.synchronizedList(new ArrayList<>());
    private final List<Socket> haveHeartSockets = Collections.synchronizedList(new ArrayList<>());
    private static final int DEFAULT_PORT=9000;
    private int port;
    public int getPort() {
        return port;
    }

    public static void main(String[] args) throws IOException {
        ServerSocketTest serverSocketTest = new ServerSocketTest(PORT);
        serverSocketTest.start();
    }
    public ServerSocketTest() throws IOException {
        this.serverSocket=new ServerSocket(DEFAULT_PORT);
    }


    public ServerSocketTest(int port) throws IOException {
        this.port = port;
        this.serverSocket=new ServerSocket(port);
    }

    public void start() throws IOException {
        listen();
        listenCount();
        showMessage();
        haveHeatBeat();
    }


    private void listen() {
        Thread thread = new Thread(() -> {
            log.info("开始监听!");
            while (true) {
                Socket socket = null;
                try {
                    socket = serverSocket.accept();
                } catch (IOException e) {
                    log.info("发生异常!:{}",e.getMessage());
                }
                ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();
                writeLock.lock();
                sockets.add(socket);
                haveHeartSockets.add(socket);
                log.info("现在已经有" + sockets.size() + "个客户端进行连接.");
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
                    log.info("现在已经有{}个客户端进行连接.",sockets.size());
                }
                writeLock.unlock();
                dealSleep(5000,Thread.currentThread());
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
                            log.info("第{}个Socket消息:{}" ,i+1,new String(bytes, StandardCharsets.UTF_8));
                        }
                    } catch (IOException e) {
                        log.info("showMessage发生异常:{}",e.getMessage());
                    }
                }
                readLock.unlock();
            }
        });
        thread.setName("showMessage");
        thread.start();

    }


    private void haveHeatBeat() throws IOException {
        Thread thread = new Thread(() -> {
            while (true) {
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
                    log.info("发生异常:{}",e.getMessage());
                }finally {
                    readLock.unlock();
                }
            }
        });

        thread.setName("HaveHeatBeat");
        thread.start();
    }
    private void dealSleep(long sleepTime,Thread thread){
        try {
            thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            log.info("dealSleep发生异常!信息:{}",e.getMessage());
            thread.interrupt();
        }
    }



    public List<Socket> getSockets() {
        return sockets;
    }
}
