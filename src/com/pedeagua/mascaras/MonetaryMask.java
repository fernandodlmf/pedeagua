package com.pedeagua.mascaras;
import java.text.NumberFormat;

import android.content.Context;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.NumberKeyListener;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.EditText;
 
public class MonetaryMask implements TextWatcher {  
	  
	   private boolean isUpdating = false;  
	   private EditText mEditText;  
	   private NumberFormat mNF = NumberFormat.getCurrencyInstance(); 
	   
	   
	   public MonetaryMask(EditText et) {
		   mEditText = et;
	   }

	  
	   @Override  
	   public void onTextChanged(CharSequence cs, int start, int before, int after) {  
	      if (isUpdating) {  
	         isUpdating = false;  
	         return;  
	      }  
	  
	      isUpdating = true;  
	      String str = cs.toString();
	      Log.i("monetario", "str antes do replaceAll = "+str);
	      str = str.replaceAll("[^\\d]", "");
	      Log.i("monetario", "str depois do replaceAll = "+str);

	  
	      try {  
	         double value = (Double.parseDouble(str) / 100);  
	         str = mNF.format(value);
	         Log.i("monetario", "str formatado = "+str);
	         str = str.replaceAll("[R$]", "");
	         mEditText.setText(str);
	         Log.i("monetario", "str setado no et = "+str);
	         mEditText.setSelection(str.length());  
	      } catch (Exception e) {  
	         e.printStackTrace();  
	         ((Editable) cs).clear();  
	      }  
	   }  
	  
	   @Override  
	   public void beforeTextChanged(CharSequence cs, int start, int count, int after) {  
	      // Não iremos utilizar...  
	   }  
	  
	   @Override  
	   public void afterTextChanged(Editable e) {  
	      // Não iremos utilizar...  
	   }  
	  
	}  