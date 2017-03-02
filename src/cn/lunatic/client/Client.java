package cn.lunatic.client;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.TreeMap;

import cn.lunatic.base.util.TimeUtil;
import cn.lunatic.service.Service_Test;
import cn.lunatic.service.socket.pack.PackTools;
import cn.lunatic.service.socket.pack.PackageClient;

public class Client {

	public static void main(String[] args) {
		registService();
	}

	
	public static void registService(){
		try {
			Socket connToServer = new Socket("localhost", 4000);
			cn.lunatic.service.socket.client.Client client = new cn.lunatic.service.socket.client.Client(connToServer);
			
			PackageClient pack_regist_service = new PackageClient(TimeUtil.now("yyyyMMddHHmmss"), 
					PackTools.PACK_TYPE_REGIST_SERVICE, "testService", new Service_Test());
			client.sendPack(pack_regist_service);

			{
				PackageClient pack_call_service = new PackageClient(TimeUtil.now("yyyyMMddHHmmss"), 
						PackTools.PACK_TYPE_CALL_SERVICE, "testService", new TreeMap<Object, Object>());
				client.sendPack(pack_call_service);
			}
			
//			
//			PackageClient pack_close_conn = new PackageClient(TimeUtil.now("yyyyMMddHHmmss"), 
//					PackTools.PACK_TYPE_CLOSE_CONNECTION);
//			client.sendPack(pack_close_conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void testConnection() {
		try {
			Socket connectionToServer = new Socket("localhost", 4000);
			DataInputStream inFromServer = new DataInputStream(connectionToServer.getInputStream());
			DataOutputStream outToServer = new DataOutputStream(connectionToServer.getOutputStream());
			System.out.println("input your number now:");
			String outStr, inStr;
			boolean goon = true;
			BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
			while (goon) {
				outStr = buf.readLine();
				outToServer.writeUTF(outStr);
				outToServer.flush();
				inStr = inFromServer.readUTF();
				if (!inStr.equals("bye"))
					System.out.println("the returned data is:" + inStr);
				else
					goon = false;
			}
			inFromServer.close();
			outToServer.close();
			connectionToServer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
