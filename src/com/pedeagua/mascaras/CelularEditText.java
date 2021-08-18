package com.pedeagua.mascaras;

import android.content.Context;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.NumberKeyListener;
import android.util.AttributeSet;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

public class CelularEditText extends EditText {

	private class KeylistenerNumber extends NumberKeyListener {

		@Override
		protected char[] getAcceptedChars() {
			return new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8',
					'9' };
		}

		@Override
		public int getInputType() {
			return InputType.TYPE_CLASS_NUMBER
					| InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS;
		}
	}

	private boolean isUpdating;
	private int FUltimoTamanho = 0;

	private final KeylistenerNumber keylistenerNumber = new KeylistenerNumber();

	private int positioning[] = { 1, 2, 3, 5, 6, 7, 8, 9, 11, 12, 13, 14 };



	public CelularEditText(Context context) {
		super(context);
		initialize();
	}

	public CelularEditText(Context context, AttributeSet attrs) {
		super(context, attrs);
		initialize();
	}

	public CelularEditText(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initialize();
	}

	public String getCleanText() {
		String text = CelularEditText.this.getText().toString();
		text.replaceAll("[^0-9]*", "");
		return text;
	}

	private void initialize() {

		final int maxNumberLength = 11;
		this.setKeyListener(keylistenerNumber);
		this.setImeOptions(EditorInfo.IME_ACTION_NEXT);	
		this.setText("(  )      -     ");
		this.setSelection(1);
		this.addTextChangedListener(new TextWatcher() {

			@Override
			public void afterTextChanged(Editable s) {
				
				String current = s.toString();
				
				if (isUpdating) {
					isUpdating = false;
					return;
				}
				
				
				if (FUltimoTamanho>s.length()){
					FUltimoTamanho = s.length();
					return;
					}					
				else
					FUltimoTamanho = s.length();
				
				String number = current.replaceAll("[^0-9]*", "").trim();
				if (number.length() > maxNumberLength) 
					number = number.substring(0, maxNumberLength);
				
				
				int length 			= number.length();
				
				String paddedNumber = padNumber(number, maxNumberLength);
				String ddd 	 		= paddedNumber.substring(0, 2);
				String part1 		= paddedNumber.substring(2, 7);
				String part2 		= paddedNumber.substring(7, 11);
				String phone 		= "(" + ddd + ")" + part1 + "-" + part2;
				isUpdating 	 		= true;
				
				CelularEditText.this.setText(phone);				
				CelularEditText.this.setSelection(positioning[length]);				
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
			}
		});
	}

	protected String padNumber(String number, int maxLength) {
		String padded = new String(number);
		for (int i = 0; i < maxLength - number.length(); i++) 
			padded += " ";		
		
		return padded;
	}
}
