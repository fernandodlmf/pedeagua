package com.pedeagua.conexaoHttpClient;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

public class ConexaoHttpClient {
	
	public static HttpClient httpCliente;
	public static final int TIMEOUT_CONEXAO = 15 * 1000;
	
	private static HttpClient getHttpClient ()
	{
		if (httpCliente == null) {
			
			httpCliente = new DefaultHttpClient();
				HttpParams httparametros = httpCliente.getParams();
				HttpConnectionParams.setConnectionTimeout(httparametros,TIMEOUT_CONEXAO);
				HttpConnectionParams.setSoTimeout(httparametros, TIMEOUT_CONEXAO);
				
				ConnManagerParams.setTimeout(httparametros, TIMEOUT_CONEXAO);
							
		}
		
			return httpCliente;
		
	}
	
	public static String executaHttpClient (String url, ArrayList<NameValuePair> parametrosPost) throws Exception {
		BufferedReader bufferReader = null;
			try {
	
			HttpClient cliente = getHttpClient();
			HttpPost httpPost = new HttpPost(url);
			UrlEncodedFormEntity entidade = new UrlEncodedFormEntity(parametrosPost);
			httpPost.setEntity(entidade);
			HttpResponse httpResposta = cliente.execute(httpPost);
			bufferReader = new BufferedReader(new InputStreamReader(httpResposta.getEntity().getContent()));
			StringBuffer stringBuffer = new StringBuffer("");
			String line = "";
			String LS = System.getProperty("line.separator");
			
			while ((line = bufferReader.readLine()) != null ) 
			{
					stringBuffer.append(line + LS);
					
			}
				bufferReader.close();
			
				String resposta = stringBuffer.toString();
				return resposta;
			
			} finally {
				
				if (bufferReader != null) {
					try {
						
					bufferReader.close();
					
					} catch (IOException e) {
						e.printStackTrace();
					}
					
				}
				
			}
	}
	
	public static String executaHttpGet (String url) throws Exception  {
		BufferedReader bufferReader = null;
			try {
	
			HttpClient cliente = getHttpClient();
			HttpGet httpGet = new HttpGet(url);
			HttpResponse httpResposta = cliente.execute(httpGet);
			bufferReader = new BufferedReader(new InputStreamReader(httpResposta.getEntity().getContent()));
			StringBuffer stringBuffer = new StringBuffer("");
			String line = "";
			String LS = System.getProperty("line.separator");
			
			while ((line = bufferReader.readLine()) != null ) 
			{
					stringBuffer.append(line + LS);
					
			}
				bufferReader.close();
			
				String resposta = stringBuffer.toString();
				return resposta;
			
			} finally {
				
				if (bufferReader != null) {
					try {
						
					bufferReader.close();
					
					} catch (IOException e) {
						e.printStackTrace();
					}
					
				}
				
			}
	}
	
}
