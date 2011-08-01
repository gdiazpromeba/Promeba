package ar.org.promeba.util.init;

import java.math.BigInteger;
import java.security.SecureRandom;

public class Num {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		for (int i=0; i<100; i++){
			System.out.println(enteroAleatorio(10));
		}

	}
	
	private static String cadenaAleatoria(int medida){
		  SecureRandom random = new SecureRandom();
		  String res=new BigInteger(130, random).toString(32);
		  return res.substring(0, medida);
	}
	
	private static String enteroAleatorio(int medida){
		  SecureRandom random = new SecureRandom();
		  String res=new BigInteger(130, random).toString(10);
		  return res.substring(0, medida);
	}

}
