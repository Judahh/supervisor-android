package android.G4FlexSupervisor.web;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.R.array;
import android.util.Log;

public class Http {
	public String downloadFile(String sUrl){
		try{
			URL url=new URL(sUrl);
			HttpURLConnection connection=(HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setDoInput(true);
			connection.setDoOutput(false);
			connection.connect();
			InputStream input =connection.getInputStream();
			String file=readString(input);
			connection.disconnect();
			return file;
		}catch(MalformedURLException exception){
			// TODO: handle exception
		}catch (IOException exception) {
			// TODO: handle exception
		}
		return null;
	}

	private String readString(InputStream input) throws IOException {
		byte[] bytes=readBytes(input);
		String text=new String(bytes);
		return text;
	}

	private byte[] readBytes(InputStream input) throws IOException {
		ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
		try{
			byte[]buffer=new byte[1024];
			int lenth;
			while((lenth=input.read(buffer))>0){
				byteArrayOutputStream.write(buffer,0,lenth);
			}
			byte[] bytes=byteArrayOutputStream.toByteArray();
			return bytes;
		}finally{
			byteArrayOutputStream.close();
			input.close();
		}
	}
}
