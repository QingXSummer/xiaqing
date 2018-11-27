package com.java.socket.nio;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.locks.LockSupport;

public class NioClient {


    static Object lockObj = new Object();

    Logger logger = Logger.getLogger(NioClient.class);

    Selector selector ;

    ByteBuffer readBuffer = ByteBuffer.allocate(5);
    ByteBuffer writeBuffer = ByteBuffer.allocate(5);

    /**
     * 是否通过服务端验证
     */
    volatile boolean pass =false;

    static Scanner scanner = new Scanner(System.in);

    private SocketChannel clientKey=null;

    Charset charset = Charset.forName("utf8");

    private static final String passMsg ="name-pass";

    private static  final String noPassMsg="name-no-pass";

    public NioClient(String ip, int port) {
        this.initClient(ip, port);
    }

    private void initClient(String ip, int port) {
        try {
            this.selector = Selector.open();
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            socketChannel.register(selector, SelectionKey.OP_CONNECT, null);
            socketChannel.connect(new InetSocketAddress(ip, port));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void connect() throws IOException {
        while (true) {
            selector.select();
            Iterator <SelectionKey> keys = selector.selectedKeys().iterator();
            logger.debug("keys-size:" + selector.selectedKeys().size());
            while (keys.hasNext()) {
                SelectionKey key =  keys.next();
                keys.remove();
                if (key.isConnectable()) {
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    // 如果正在连接，则完成连接  
                    if (socketChannel.isConnectionPending()) {
                        socketChannel.finishConnect();
                    }
                    int inter = SelectionKey.OP_READ;
                    socketChannel.register(selector, inter);
                    clientKey=socketChannel;
                    System.out.println("成功连接服务器");
                }
                if (key.isReadable()) {
                    handReadKey(key);
                }
            }
        }
    }

    /**
     *
     * @param readKey   读的key
     */
    void handReadKey(SelectionKey readKey){
        try {
            readBuffer.clear();
            SocketChannel socketChannel = (SocketChannel) readKey.channel();
            socketChannel.read(readBuffer);
            readBuffer.flip();
            byte[] bytes = new byte[readBuffer.limit()];
            readBuffer.get(bytes);
            String msg = new String(bytes);
            if(passMsg.equals(msg)){
                pass();
                System.out.println("昵称验证通过，您可以和其他人聊天了");

            }else if(noPassMsg.equals(msg)){
                System.out.println("昵称已存在，请重新输入");
            }else{
                System.out.println(msg);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void sendMsg(String msg){
        try {
            writeBuffer.clear();
            writeBuffer.put(msg.getBytes());
            writeBuffer.flip();
            clientKey.write(writeBuffer);
        } catch (IOException e) {
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private boolean checkName(String name){
        try {
            if(name!=null && !"".equals(name)){
                return  true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return  false;
    }

    private  void pass(){
        this.pass=true;
    }

    private boolean isPass(){
        return  pass;
    }

    public static void main(String[] args) {
        try {
            final NioClient client = new NioClient("127.0.0.1", 8888);

            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (!client.isPass()){
                        String name = scanner.nextLine();
                        boolean hasPass= client.checkName(name);
                        if(!hasPass){
                            System.out.println("昵称不符合规则，请重新出入昵称");
                            continue;
                        }
                        client.sendMsg(name);
                        LockSupport.park(lockObj);
                    }
                    while (true) {
                        String msg = scanner.nextLine();
                        client.sendMsg(msg);
                    }
                }
            });
            thread.start();
            client.connect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}