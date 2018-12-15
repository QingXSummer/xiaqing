package test.test;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public class Test {
    public static void main(String[] args) {
        String msg = "你好啊";
        byte[] bytes = msg.getBytes();
        ByteBuffer buffer = ByteBuffer.allocate(5);
        buffer.put(bytes,0,5);



        Charset charset = Charset.forName("utf8");
        buffer.flip();
        System.out.println(charset.decode(buffer));
    }

}
