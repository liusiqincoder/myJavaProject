package Util;

import java.util.UUID;
/**
 * @Description   32位或者64位随机ID生成工具类
 * @Parameter
 * Created by Administrator on 2018/11/21.
 */
public class UUIDUtils {
	/**
	 * 随机生成id
	 * @return
	 */
	public static String getId(){
		return UUID.randomUUID().toString().replace("-", "").toUpperCase();
	}
	
	
	public static String getUUID64(){
		return getId()+getId();
	}
	
	/**
	 * 生成随机码
	 * @return
	 */
	public static String getCode(){
		return getId();
	}
	
	public static void main(String[] args) {
		System.out.println(getId());
	}
}
