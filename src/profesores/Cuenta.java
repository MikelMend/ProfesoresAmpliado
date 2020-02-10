package profesores;

import java.math.BigInteger;
import java.util.TreeMap;

public class Cuenta {
	private double saldo;
	private String numeroCuenta;
	private String cifTitular;
	
	public Cuenta() {
		
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public String getCifTitular() {
		return cifTitular;
	}

	public void setCifTitular(String cifTitular) {
		this.cifTitular = cifTitular;
	}
	
	public static void filtroCuenta(String numeroCuenta) throws Exception{
		int factores[]= {1,2,4,8,5,10,9,7,3,6};
		
		int acumulador, digitoCalculado;
		
		if(numeroCuenta.length() !=24 )
			throw new Exception("Número incorrecto de dígitos");
		if(numeroCuenta.charAt(0) != 'E')
			throw new Exception("El primer caracter debe ser una E");
		if(numeroCuenta.charAt(1) != 'S')
			throw new Exception("El segundo caracter debe ser una S");
		boolean encontradoDigitoNoNumerico = false;
		
		for(int i=2; i<=numeroCuenta.length() -1 && !encontradoDigitoNoNumerico; i++) {
			char digito = numeroCuenta.charAt(1);
			if (digito<'0' || digito>'9') {
				encontradoDigitoNoNumerico = true;
			}
		}
		
		if(encontradoDigitoNoNumerico)
			throw new Exception("Caracteres no númericos en el número de cuenta");
		
			BigInteger numero = new BigInteger(numeroCuenta.substring(4, 24) + "142800");
			int numeroControlIBAN = 98 - (numero.mod(new BigInteger("97")).intValue());
			String digitosControlIBAN = String.valueOf(numeroControlIBAN);
			if(numeroControlIBAN < 10)
				digitosControlIBAN = "0" +digitosControlIBAN;
			
			acumulador = 0;
			for(int i=4; i<=11; i++) {
				acumulador = acumulador + (numeroCuenta.charAt(i) -48) * factores[i -2];
			}
			digitoCalculado = 11 -(acumulador % 11);
			switch(digitoCalculado) {
			case 10: digitoCalculado = 1;
				break;
			case 11: digitoCalculado = 0;
				break;
			}
			if(digitoCalculado != ((int)numeroCuenta.charAt(13)-48))
				throw new Exception("Segundo dígito de control erróneo");
			
			TreeMap<String, String> tmEEEE = new TreeMap<String, String>();
			String banco;
			cargaEntidadesBancarias(tmEEEE);
			String EEEE= numeroCuenta.substring(4,8);
			System.out.println("Entidad: "+ EEEE);
			if(tmEEEE.containsKey(EEEE)) {
				banco=tmEEEE.get(EEEE);
				System.out.println("Banco: "+ banco);
		}else {
			throw new Exception("Código banco inexistente");
		}
		TreeMap<String, String> tmEEEESSSS = new TreeMap<String, String>();
		String sucursal="";
		cargaSucursalesBancarias(tmEEEESSSS);
		String EEEESSSS = numeroCuenta.substring(4, 12);
		System.out.println("Sucursal: "+ numeroCuenta.substring(8,12));
		if(tmEEEESSSS.containsKey(EEEESSSS)) {
			sucursal=tmEEEESSSS.get(EEEESSSS);
			System.out.println("Sucursal: " + sucursal);
		}else {
			throw new Exception ("Código banco inexistente");
		}
		
	}
	static void cargaEntidadesBancarias(TreeMap<String, String> tmEEEE) {
		tmEEEE.put("2100", "Caixabank");
		tmEEEE.put("0081", "Banco Sabadell");
		tmEEEE.put("1465", "ING Bank");
		tmEEEE.put("0081", "Banco Sabadell");
		tmEEEE.put("2038", "Bankia");
		tmEEEE.put("0049", "Banco Santander");
		
	}
	static void cargaSucursalesBancarias(TreeMap<String, String> tmEEEESSSS) {
		tmEEEESSSS.put("21004231","Elche Urbana 1");
		tmEEEESSSS.put("21004232","Elche Urbana 2");
		tmEEEESSSS.put("21004233","Elche Urbana 3");
		tmEEEESSSS.put("21004234","Elche Urbana 4");
		tmEEEESSSS.put("21003894","Elche Urbana 5");
		tmEEEESSSS.put("00816781","Elche Urbana 1");
		tmEEEESSSS.put("00816782","Elche Urbana 3");
		tmEEEESSSS.put("00816783","Elche Urbana 3");
		tmEEEESSSS.put("00816784","Elche Urbana 4");
		tmEEEESSSS.put("14654561","Elche Urbana 1");
		tmEEEESSSS.put("14654562","Elche Urbana 2");
		tmEEEESSSS.put("00811152","Elche Urbana 1");
		tmEEEESSSS.put("00811153","Elche Urbana 2");
		tmEEEESSSS.put("00811152","Elche Urbana 3");
		tmEEEESSSS.put("20384441","Elche Urbana 1");
		tmEEEESSSS.put("00492221","Elche Urbana 1");
		tmEEEESSSS.put("00492222","Elche Urbana 2");
		tmEEEESSSS.put("00491111","Elche Urbana 1");
		
		
	}
		
}
