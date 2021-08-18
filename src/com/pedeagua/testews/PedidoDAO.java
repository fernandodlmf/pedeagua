package com.pedeagua.testews;



import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.util.Log;


public class PedidoDAO {
	//private static final String URL = "http://192.168.0.104:8080/PedeAguaWS/services/PedidoDAO?wsdl";
	private static final String URL = "http://pedeaguaw.jelastic.under.com.br/services/PedidoDAO?wsdl";
	private static final String NAMESPACE = "http://pedeaguaWS.com";
	private static final String INSERIR = "inserirPedido";
	private static final String INSERIR_ITEM_PEDIDO = "inserirItemPedido";
	private static final String CANCELAR_PEDIDO = "cancelarPedido";
	private static final String SETAR_ENTREGUE = "setarEntregue";
	private static final String INSERIR_TODOS_ITENS_PEDIDO = "inserirTodosItemPedido";
	
	
	public int inserirPedido(Pedido pedido) {
		
		SoapObject inserirProduto = new SoapObject(NAMESPACE, INSERIR);
		
		SoapObject p = new SoapObject(NAMESPACE, "pedido");
		
		p.addProperty("cod_vendedor", pedido.getCod_vendedor());
		p.addProperty("cod_cliente", pedido.getCod_cliente());
		p.addProperty("valorTotal", pedido.getValorTotal());
		p.addProperty("trocoPara", pedido.getTrocoPara());

		
		inserirProduto.addSoapObject(p);
		
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		
		envelope.setOutputSoapObject(inserirProduto);
		
		envelope.implicitTypes = (true);
		
		HttpTransportSE http = new HttpTransportSE(URL);
		
			try {
				http.call("uri:" + INSERIR, envelope);
				
				SoapPrimitive spResposta = (SoapPrimitive) envelope.getResponse();
				
				return Integer.parseInt(spResposta.toString()); 

			} catch (Exception e) {
				Log.i("pedido", "erro ao inserir pedido =" +e.getMessage());
				return 0;
			} 

	}
	
	
	public boolean inserirItemPedido(ItemPedido itemPedido) {

		
		SoapObject inserirItemPedido = new SoapObject(NAMESPACE, INSERIR_ITEM_PEDIDO);
		
		SoapObject p = new SoapObject(NAMESPACE, "itemPedido");
		
		p.addProperty("cod_pedido", itemPedido.getCod_pedido());
		p.addProperty("cod_produto", itemPedido.getCod_produto());
		p.addProperty("qtd", itemPedido.getQtd());
		
		inserirItemPedido.addSoapObject(p);
		
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		
		envelope.setOutputSoapObject(inserirItemPedido);
		
		envelope.implicitTypes = (true);
		
		HttpTransportSE http = new HttpTransportSE(URL);
		
			try {
				http.call("uri:" + INSERIR_ITEM_PEDIDO, envelope);
				
				SoapPrimitive spResposta = (SoapPrimitive) envelope.getResponse();
				
				return Boolean.parseBoolean(spResposta.toString()); 

			} catch (Exception e) {
				e.printStackTrace();
				return false;
			} 
		
	}
	
	
	
	
	public boolean inserirTodosItemPedido(StringItem Stringjs) {
		
		
		SoapObject request = new SoapObject(NAMESPACE, INSERIR_TODOS_ITENS_PEDIDO);
		
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		
	
		
		SoapObject p = new SoapObject(NAMESPACE, "Stringjs");	
		p.addProperty("st", Stringjs.getSt());
			
		request.addSoapObject(p);
		
		envelope.setOutputSoapObject(request);
		
		envelope.implicitTypes = true;
		
		HttpTransportSE http = new HttpTransportSE(URL);
		
	
		try {
			http.call("uri:" + INSERIR_TODOS_ITENS_PEDIDO, envelope);
			
			SoapPrimitive spResposta = (SoapPrimitive) envelope.getResponse();
			
			return Boolean.parseBoolean(spResposta.toString()); 

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} 
		

	}
	
	public boolean cancelarPedido(Pedido pedido) {
		

		SoapObject request = new SoapObject(NAMESPACE, CANCELAR_PEDIDO);
		
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		
	
		
		SoapObject p = new SoapObject(NAMESPACE, "pedido");	
		p.addProperty("id", pedido.getId());
		p.addProperty("cod_cliente", pedido.getCod_cliente());
		p.addProperty("tempoCancelamento", pedido.getTempoCancelamento());
		
			
		request.addSoapObject(p);
		
		envelope.setOutputSoapObject(request);
		
		envelope.implicitTypes = true;
		
		HttpTransportSE http = new HttpTransportSE(URL);
		
	
		try {
			http.call("uri:" + CANCELAR_PEDIDO, envelope);
			
			SoapPrimitive spResposta = (SoapPrimitive) envelope.getResponse();
			
			return Boolean.parseBoolean(spResposta.toString()); 

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} 
	}
	
	public boolean setarEntregue(Pedido pedido) {
		

		SoapObject request = new SoapObject(NAMESPACE, SETAR_ENTREGUE);
		
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		
	
		
		SoapObject p = new SoapObject(NAMESPACE, "pedido");	
		p.addProperty("id", pedido.getId());
		p.addProperty("cod_cliente", pedido.getCod_cliente());
			
		request.addSoapObject(p);
		
		envelope.setOutputSoapObject(request);
		
		envelope.implicitTypes = true;
		
		HttpTransportSE http = new HttpTransportSE(URL);
		
	
		try {
			http.call("uri:" + SETAR_ENTREGUE, envelope);
			
			SoapPrimitive spResposta = (SoapPrimitive) envelope.getResponse();
			
			return Boolean.parseBoolean(spResposta.toString()); 

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} 
	}
		
		
}
