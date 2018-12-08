package Util;

import Model.User;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;

/**没完成
 * Created by Administrator on 2018/11/24.
 */
public class SocketUtil {
    public static String uploadFile(String ip,int port,File f) throws IOException {
        Socket server=new Socket(ip,port);
        DataOutputStream out=new DataOutputStream(server.getOutputStream());

        return "";
    }

    public static boolean receiveFile(){

        return true;
    }
}
