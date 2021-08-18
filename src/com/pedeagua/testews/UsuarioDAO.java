package com.pedeagua.testews;


import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import android.util.Log;


public class UsuarioDAO {
	//private static final String URL = "http://192.168.0.104:8080/PedeAguaWS/services/UsuarioDAO?wsdl";
	
	private static final String URL = "http://pedeaguaw.jelastic.under.com.br/services/UsuarioDAO?wsdl";
	private static final String NAMESPACE = "http://pedeaguaWS.com";
	private static final String INSERIR_CLIENTE = "inserirCliente";
	private static final String INSERIR_VENDEDOR = "inserirVendedor";
	private static final String INSERIR_ENDERECO = "inserirEndereco";
	private static final String BUSCAR_USUARIO = "buscarUsuario";
	private static final String ATUALIZAR_QUALIFICACAO = "atualizarQualificacao";
	
	
	public boolean inserirCliente(Usuario cliente) {
		
		SoapObject inserirCliente = new SoapObject(NAMESPACE, INSERIR_CLIENTE);
		
		SoapObject p = new SoapObject(NAMESPACE, "cliente");
		
		p.addProperty("nome", cliente.getNome());
		p.addProperty("senha", cliente.getSenha());
		p.addProperty("email", cliente.getEmail());
		p.addProperty("cod_endereco", cliente.getCod_endereco());
		p.addProperty("telefone", cliente.getTelefone());
		p.addProperty("eh_vendedor", cliente.getEh_vendedor());
		p.addProperty("eh_cliente", cliente.getEh_cliente());
		p.addProperty("ativo", cliente.getAtivo());
		
		
		inserirCliente.addSoapObject(p);
		
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		
		envelope.setOutputSoapObject(inserirCliente);
		
		envelope.implicitTypes = (true);
		
		HttpTransportSE http = new HttpTransportSE(URL);
		
			try {
				http.call("uri:" + INSERIR_CLIENTE, envelope);
				
				SoapPrimitive spResposta = (SoapPrimitive) envelope.getResponse();
				return Boolean.parseBoolean(spResposta.toString()); 

			} catch (Exception e) {
				Log.i("pedido", "erro ao inserir cliente =" +e.getMessage());
				return false;
			} 

	}
	
	public boolean inserirVendedor(Usuario vendedor) {

		
		SoapObject inserirVendedor = new SoapObject(NAMESPACE, INSERIR_VENDEDOR);
		
		SoapObject p = new SoapObject(NAMESPACE, "vendedor");
		
		p.addProperty("nome", vendedor.getNome());
		p.addProperty("senha", vendedor.getSenha());
		p.addProperty("email", vendedor.getEmail());
		p.addProperty("cod_endereco", vendedor.getCod_endereco());
		p.addProperty("telefone", vendedor.getTelefone());
		p.addProperty("eh_vendedor", vendedor.getEh_cliente());
		p.addProperty("ativo", vendedor.getAtivo());
		
		inserirVendedor.addSoapObject(p);
		
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		
		envelope.setOutputSoapObject(inserirVendedor);
		
		envelope.implicitTypes = (true);
		
		HttpTransportSE http = new HttpTransportSE(URL);
		
			try {
				http.call("uri:" + INSERIR_VENDEDOR, envelope);
				
				SoapPrimitive spResposta = (SoapPrimitive) envelope.getResponse();
				
				return Boolean.parseBoolean(spResposta.toString()); 

			} catch (Exception e) {
				e.printStackTrace();
				return false;
			} 
		
	}
	
	public int inserirEndereco(Endereco endereco) {

		
		SoapObject inserirEndereco = new SoapObject(NAMESPACE, INSERIR_ENDERECO);
		
		SoapObject p = new SoapObject(NAMESPACE, "endereco");
		
		p.addProperty("rua", endereco.getRua());
		p.addProperty("bairro", endereco.getBairro());
		p.addProperty("cep", endereco.getCep());
		p.addProperty("numero", endereco.getNumero());
		p.addProperty("complemento", endereco.getComplemento());
		p.addProperty("cidade", endereco.getCidade());
		p.addProperty("estado",endereco.getEstado());
		
		inserirEndereco.addSoapObject(p);
		
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		
		envelope.setOutputSoapObject(inserirEndereco);
		
		envelope.implicitTypes = (true);
		
		HttpTransportSE http = new HttpTransportSE(URL);
		
			try {
				http.call("uri:" + INSERIR_ENDERECO, envelope);
				
				SoapPrimitive spResposta = (SoapPrimitive) envelope.getResponse();
				
				return Integer.parseInt(spResposta.toString()); 

			} catch (Exception e) {
				e.printStackTrace();
				return 0;
			} 
		
	}
	
	
	public int buscarUsuario(Usuario usuario) {

		
		SoapObject buscarUsuario = new SoapObject(NAMESPACE, BUSCAR_USUARIO);
		
		SoapObject user = new SoapObject(NAMESPACE, "usuario");
		
		user.addProperty("email", usuario.getEmail());
		user.addProperty("senha", usuario.getSenha());
		
		buscarUsuario.addSoapObject(user);
		
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		
		envelope.setOutputSoapObject(buscarUsuario);
		
		envelope.implicitTypes = true;
		
		HttpTransportSE httpTransporte = new HttpTransportSE(URL);
		
		try {
			httpTransporte.call("uri:" + BUSCAR_USUARIO, envelope);
			
			SoapPrimitive spResposta = (SoapPrimitive) envelope.getResponse();
			
			return Integer.parseInt(spResposta.toString()); 
			
		} catch (Exception e) {
			e.printStackTrace();
			
			return 0;
		} 
		
	}
	
	public boolean atualizarQualificacao(Usuario usuario) {

		SoapObject atualizarQualificacao = new SoapObject(NAMESPACE, ATUALIZAR_QUALIFICACAO);
		
		SoapObject p = new SoapObject(NAMESPACE, "usuario");
		
		p.addProperty("rating", usuario.getRating());
		p.addProperty("id", usuario.getId());

		atualizarQualificacao.addSoapObject(p);
		
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		
		envelope.setOutputSoapObject(atualizarQualificacao);
		
		envelope.implicitTypes = (true);
		
		HttpTransportSE http = new HttpTransportSE(URL);
		
			try {
				http.call("uri:" + ATUALIZAR_QUALIFICACAO, envelope);
				
				SoapPrimitive spResposta = (SoapPrimitive) envelope.getResponse();
				
				return Boolean.parseBoolean(spResposta.toString()); 

			} catch (Exception e) {
				e.printStackTrace();
				return false;
			} 
		
	}
			
}
