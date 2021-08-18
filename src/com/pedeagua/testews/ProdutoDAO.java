package com.pedeagua.testews;


import java.util.ArrayList;
import java.util.Vector;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class ProdutoDAO {
	//private static final String URL = "http://192.168.0.104:8080/PedeAguaWS/services/ProdutoDAO?wsdl";
	private static final String URL = "http://pedeaguaw.jelastic.under.com.br/services/ProdutoDAO?wsdl";
	private static final String NAMESPACE = "http://pedeaguaWS.com";	
	private static final String INSERIR = "inserirProduto";
	private static final String BUSCAR_ALL = "bucarProdutoAll";
	
	
	public boolean inserirProduto(Produto produto){
		
		SoapObject inserirProduto = new SoapObject(NAMESPACE, INSERIR);
		
		SoapObject p = new SoapObject(NAMESPACE, "produto");
		
		p.addProperty("nome", produto.getNome());
		p.addProperty("preco", produto.getPreco());
		p.addProperty("eh_agua", produto.getEh_agua());
		p.addProperty("eh_gas", produto.getEh_gas());
		
		inserirProduto.addSoapObject(p);
		
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		
		envelope.setOutputSoapObject(inserirProduto);
		
		envelope.implicitTypes = (true);
		
		HttpTransportSE http = new HttpTransportSE(URL);
		
			try {
				http.call("uri:" + INSERIR, envelope);
				
				SoapPrimitive spResposta = (SoapPrimitive) envelope.getResponse();
				
				return Boolean.parseBoolean(spResposta.toString()); 

			} catch (Exception e) {
				e.printStackTrace();
				return false;
			} 
			
	}
	
	public ArrayList<Produto> bucarProdutoAll() {
		ArrayList<Produto> lista = new ArrayList<Produto>();
		Produto p = null;
		
		SoapObject bucarProdutoAll = new SoapObject(NAMESPACE, BUSCAR_ALL);
		
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		
		envelope.setOutputSoapObject(bucarProdutoAll);
		
		envelope.implicitTypes = true;
		
		HttpTransportSE httpTransporte = new HttpTransportSE(URL);
		
		try {
			httpTransporte.call("uri:" + BUSCAR_ALL, envelope);
			
			Vector<SoapObject> resposta = (Vector<SoapObject>) envelope.getResponse();
			
			for (SoapObject soapObject : resposta) {
				p = new Produto();
				
				p.setId(Integer.parseInt(soapObject.getProperty("id").toString()));
				p.setNome(soapObject.getProperty("nome").toString());
				p.setPreco(soapObject.getProperty("preco").toString());
				p.setEh_agua(Integer.parseInt(soapObject.getProperty("eh_agua").toString()));
				p.setEh_gas(Integer.parseInt(soapObject.getProperty("eh_gas").toString()));
				
				lista.add(p);
			}

			
		} catch (Exception e) {
			e.printStackTrace();
			
			return lista;
		} 
		
		return lista;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
