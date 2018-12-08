package test;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Administrator on 2018/11/17.
 */
public class myServer {
    private final static int port=10000;
    private static ServerSocket server=null;

    public static void main(String[] args){
        Socket client=null;
        try {
            server=new ServerSocket(port);
            System.out.println("等待连接，端口号为"+server.getLocalPort());
            server.setSoTimeout(100000);
            client=server.accept();
            System.out.println("远程主机为："+client.getRemoteSocketAddress());
//            DataInputStream in=new DataInputStream(client.getInputStream());
//            System.out.println(in.readUTF());
            DataOutputStream out=new DataOutputStream(client.getOutputStream());
            out.writeUTF("谢谢连接我，"+client.getLocalSocketAddress()+"\n再见\n");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (client!=null)
                    client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
