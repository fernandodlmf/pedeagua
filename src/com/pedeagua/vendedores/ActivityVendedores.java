package com.pedeagua.vendedores;
 
import java.util.ArrayList;
import java.util.List;


import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
 
import android.widget.Spinner;
import android.widget.Toast;
import br.com.projetofinal1.R;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.pedeagua.conexaoHttpClient.ConexaoHttpClient;
import com.pedeagua.finalizacao.MainActivity;
import com.pedeagua.login.PrincipalActivity;
import com.pedeagua.vendedores.adater.ListAdapterPersonalizado;
import com.pedeagua.vendedores.app.AppController;
import com.pedeagua.vendedores.model.Vendedor;
 
public class ActivityVendedores extends Activity {
    // Log tag
    private static final String TAG = ActivityVendedores.class.getSimpleName();
 
    // Movies json url
    private static final String url = "http://servidorweed1.no-ip.org:8080/json/movies.json";
    private ProgressDialog pDialog;
    public static List<Vendedor> movieList = new ArrayList<Vendedor>();
    private ListView listView;
    private ListAdapterPersonalizado adapter;
    int contador = 0;

    
    ListView listProdutos = null;
	Spinner spinerVendedores = null;
	int duracao = Toast.LENGTH_LONG;
	Button btProximo, btVoltar;
	public int codigo;
	int posicao = 0;
	int posicao1 = 0;
	public static int posicao3 ;
	String [] produtos;
	String [] vendedores;
	public static int cod_pedido;
	Context ctx = ActivityVendedores.this;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_vendedores);
 
        listView = (ListView) findViewById(R.id.list);
        adapter = new ListAdapterPersonalizado(this, movieList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(clickVendedores());
 
        pDialog = new ProgressDialog(this);
        
        // MOSTRANDO PROGRESSDIALOG ANTES DE FAZER O HTTP REQUEST
        pDialog.setMessage("CARREGANDO...");
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.show();
 
        // MUDANDO COR DA ACTION BAR
        getActionBar().setBackgroundDrawable(
                new ColorDrawable(Color.parseColor("#66ccff")));
 
        // CRIANDO volley REQUEST OBJECT
        JsonArrayRequest movieReq = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                        
                        hidePDialog();
 
                        // Parsing json
                        for (int i = 0; i < response.length(); i++) {
                            try {
 
                                JSONObject obj = response.getJSONObject(i);
                                Vendedor movie = new Vendedor();
                                movie.setTitle(obj.getString("title"));
                                movie.setThumbnailUrl(obj.getString("image"));
                                movie.setRating(obj.getString("rating"))
;
                                movie.setYear(obj.getInt("releaseYear"));
 
                                // Genre is json array
                                JSONArray genreArry = obj.getJSONArray("genre");
                                ArrayList<String> genre = new ArrayList<String>();
                                for (int j = 0; j < genreArry.length(); j++) {
                                    genre.add((String) genreArry.get(j));
                                }
                                movie.setGenre(genre);
 
                                // ADICIONANDO OS FILMES PARA O ARRAY
                                movieList.add(movie);
 
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
 
                        }
                        // NOTIFICA O LIST ADAPTER SOBRE MUDANÇAS NOS DADOS
                        // ENTÃO ELE RENDERIZA O LIST VIEW COM OS DADOS NOVOS.
                        adapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.d(TAG, "Error: " + error.getMessage());
                        hidePDialog();
 
                    }
                });
 
        // ADICIONANDO REQUEST PARA O REQUEST QUEUE
        AppController.getInstance().addToRequestQueue(movieReq);
    }
    
    public OnItemClickListener clickVendedores() {
    	
    	return(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				try {		
					posicao3 = position;					
					
					//criaPedido();
					
					startActivity(new Intent(ctx, com.pedeagua.finalizacao.MainActivity.class)); 

				} catch(Exception e) {
					
					Log.i("TAG", "DENTRO= " +e); 
					Toast.makeText(ctx, "Erro ao chamar tela de Finalização: " +e, duracao).show();
				}
				
			}});
    	
    }
   
    @Override
    public void onDestroy() {
        super.onDestroy();
        hidePDialog();
    }
 
    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
    	super.onCreateOptionsMenu(menu);
    	
    	/*MenuItem m1 = menu.add(0,0,0,"Item 0");
    			 m1.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
    			 m1.setIcon(R.drawable.bt_abrir_action); */
    	
    	getMenuInflater().inflate(R.menu.menu_vendedores, menu);
    	
    	return (true);
    	
    }
    
    @Override
    public boolean onMenuItemSelected(int panel, MenuItem item){
    	
    	   	    
    	    switch(item.getItemId()){
        	
        	case R.id.so_agua: 
        		    		
        		try{
        			
        			//Toast.makeText(ctx, "BOTÃO NÃO IMPLEMENTADO..." , duracao).show();
        			startActivity(new Intent(ctx, 
        					MainActivity.class));
        			  			
        		
        		} catch (Exception e) {
        			Log.i(TAG, "DENTRO = ERRO plus = " + e);
        		}
        		
        		
        		
        		break;
        		
        		
        	case R.id.so_gas: Toast.makeText(ctx, "BOTÃO NÃO IMPLEMENTADO..." , 
        			duracao).show(); break;
        	}
        	
        	return (true);
   
    }
    
    public void criaPedido () {
    	
    	try {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);
			
			String url2 = "http://servidorweed1.no-ip.org:8080/baseagua/criapedido.php";
			
			ArrayList<NameValuePair> parametrosPost2 = new ArrayList<NameValuePair>();
			parametrosPost2.add(new BasicNameValuePair("ven_codigo", String.valueOf(posicao3))); // SE LIGAR
			parametrosPost2.add(new BasicNameValuePair("cli_codigo", String.valueOf(PrincipalActivity.cod_cliente)));	// SE LIGAR
			String respostaRetornada2 = null;
			String resposta2 = null;
			
			
			
			try {

				respostaRetornada2 = ConexaoHttpClient.executaHttpClient(url2, parametrosPost2);
				resposta2 = respostaRetornada2.toString();
				resposta2 = resposta2.replaceAll("\\s+", "");
				cod_pedido = Integer.parseInt(resposta2);
				
				
					
					if(resposta2.equals(0)) {
						Toast.makeText(ctx, "Não foi possível abrir o pedido.", Toast.LENGTH_LONG).show();
						return;
					
							
					} else if (!resposta2.equals(0) && posicao3 == 0 ) {
						
						Toast.makeText(ctx, "Não foi possível abrir o pedido.", Toast.LENGTH_LONG).show();
						return;
						
					} else if (!resposta2.equals(0) && posicao3 != 0 && cod_pedido != 0)
						{
						
						startActivity( new Intent(ctx, com.pedeagua.finalizacao.MainActivity.class));
						
						}// FIM DO CRIA PEDIDO
						
						 
			} catch (Exception e) {				
				Toast.makeText(ctx, "Sem conexão com a Internet.", duracao).show();
			} 
	
		} catch (Exception e) {
			Toast.makeText(ctx, "Erro ao criar pedido: " + e.getMessage(), duracao).show();
		}
    }
    
}