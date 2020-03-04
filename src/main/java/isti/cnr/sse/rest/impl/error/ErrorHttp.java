package isti.cnr.sse.rest.impl.error;

import javax.ws.rs.core.Response.Status;

public enum ErrorHttp {

	NonAutorizzato (403),
	InputNonValido (406),
	DispositivoNNValido (409),
	ErrorContentType (415),
	SuperoLimite (429),


	fuoriorario (500),
	Default (500),
	Null (0);

	private final int value;

	ErrorHttp(int i){
		this.value = i;
	}


	public static ErrorHttp get(String tipo) {
		if (tipo.equals("InputNonValido")) {
			return InputNonValido;
		}
		if (tipo.equals("DispositivoNNValido")) {
			return DispositivoNNValido;
		}
		if (tipo.equals("SuperoLimite")) {
			return SuperoLimite;
		}
		if (tipo.equals("NonAutorizzato")) {
			return NonAutorizzato;
		}
		if (tipo.equals("ErrorContentType")) {
			return ErrorContentType;
		}
		if (tipo.equals("fuoriorario")) {
			return fuoriorario;
		}
		if (tipo.equals("Default")) {
			return fuoriorario;
		}

		return Null;
	}


	public int getValue() {
		return value;
	}



}
