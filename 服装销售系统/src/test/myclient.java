package test;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by Administrator on 2018/11/17.
 */
public class myclient {
    private final static String serverName="127.0.0.1";
    private final static int port=10000;
    public static void main(String[] args){
        Socket server=null;
        try {
            System.out.println("连接主机："+serverName+"，端口："+port);
            server=new Socket(serverName,port);
            System.out.println("主机地址："+server.getLocalSocketAddress()+",端口号："+server.getLocalPort());
            DataOutputStream out=new DataOutputStream(server.getOutputStream());
            out.writeUTF("我是客户端,已经连接到服务器");
            DataInputStream in=new DataInputStream(server.getInputStream());
            System.out.println("服务器响应："+in.readUTF());
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(server!=null)
                    server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
