package pacote;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.omg.CosNaming.IstringHelper;

public class Pacote {
	private int sequenceNumber;
	private int ackNumber;
	private short ConnectionID;
	private short ASF;
	private byte Dados[];
	
	public Pacote(int sequencia, int ack,short connecid,int asf,byte[] dados) {
		this.sequenceNumber = sequencia;
		this.ackNumber = ack;
		this.ConnectionID = connecid;
		this.Dados = dados;
		switch(asf) {
			case 1:
				this.SetA(true);//seta o ack
			case 2:
				this.SetS(true);//seta o syn
			case 3:;
				this.SetF(true);//seta fyn
			
		}
			
	}
	
	public Pacote(int sequencia, int ack,short connecid,int asf) {
		this.sequenceNumber = sequencia;
		this.ackNumber = ack;
		this.ConnectionID = connecid;
		switch(asf) {
			case 1:
				this.SetA(true);//seta o ack
			case 2:
				this.SetS(true);//seta o syn
			case 3:;
				this.SetF(true);//seta fyn
			default:
				this.SetA(false);
				this.SetF(false);
				this.SetS(false);
			
		}
			
	}
	
	public Pacote() {
		super();
	}
	
	
	public byte[] getDados() {
		return Dados;
	}

	public void setDados(byte[] dados) {
		Dados = dados;
	}

	public boolean getS() {
		if (ASF == 2 || ASF == 6 || ASF == 3 || ASF == 7) {
			return true;
		}
		return false;
	}
	
	public short getASF() {
		return ASF;
	}
	
	public void SetS(boolean b) {
		if ((b == true && getS() == true) || (b == false && getS() == false)) {
			return;
		}else if (b== true && getS() == false){
			if (ASF == 0) {
				ASF = 2;
			} else if (ASF == 1) {
				ASF = 3;
			} else if (ASF == 4) {
				ASF = 6;
			} else {
				ASF = 7;
			}
		}else{
			if (ASF == 2) {
				ASF = 0;
			} else if (ASF == 3) {
				ASF = 1;
			} else if (ASF == 6) {
				ASF = 4;
			} else {
				ASF = 5;
			}
		}
	}
	
	public boolean getA(){
		if (ASF == 4 || ASF == 5 || ASF == 6 || ASF == 7) {
			return true;
		}
		return false;
	}
	
	public void SetA(boolean b) {
		if ((b == true && getS() == true) || (b == false && getS() == false)) {
			return;
		}else if (b== true && getS() == false){
			if (ASF == 0) {
				ASF = 4;
			} else if (ASF == 1) {
				ASF = 5;
			} else if (ASF == 2) {
				ASF = 6;
			} else {
				ASF = 7;
			}
		}else{
			if (ASF == 4) {
				ASF = 0;
			} else if (ASF == 5) {
				ASF = 1;
			} else if (ASF == 6) {
				ASF = 2;
			} else {
				ASF = 3;
			}
		}
	}
	public boolean GetF(){
		if (ASF == 1 || ASF == 5 || ASF == 3 || ASF == 7) {
			return true;
		}
		return false;
	}
	public void SetF(boolean b) {
		if ((b == true && getS() == true) || (b == false && getS() == false)) {
			return;
		}else if (b== true && getS() == false){
			if (ASF == 0) {
				ASF = 1;
			} else if (ASF == 2) {
				ASF = 3;
			} else if (ASF == 4) {
				ASF = 5;
			} else {
				ASF = 7;
			}
		}else {
			if (ASF == 1) {
				ASF = 0;
			} else if (ASF == 3) {
				ASF = 2;
			} else if (ASF == 5) {
				ASF = 4;
			} else {
				ASF = 6;
			}
		}
	}
	
	
	public int getSequenceNumber() {
		return sequenceNumber;
	}

	public void setSequenceNumber(int sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

	public int getAckNumber() {
		return ackNumber;
	}

	public void setAckNumber(int ackNumber) {
		this.ackNumber = ackNumber;
	}

	public short getConnectionID() {
		return ConnectionID;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Pacote) {
			Pacote aux=(Pacote)obj;
			if(this.ConnectionID != aux.ConnectionID) {
				return false;
			}
			if(this.ackNumber != aux.ackNumber) {
				return false;
			}
			if(this.sequenceNumber != aux.sequenceNumber) {
				return false;
			}
			return true;
		}
		return false;
	}

	public void setConnectionID(short connectionID) {
		ConnectionID = connectionID;
	}

	public byte[] getPacote() {
		
		String c = this.sequenceNumber + ":" + this.ackNumber + ":" + this.ConnectionID + ":" + this.ASF+":";
		
		byte aux[] = c.getBytes();
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		try {
			baos.write(aux);
			if(this.Dados!=null) {
				baos.write(this.Dados);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("deu erro byteArray\n");
			e.printStackTrace();
			
		}
		return baos.toByteArray();
		
	}
	
	public void setPacote(byte[] dados) {
		
		String aux = new String(dados);
		String[] x = aux.split(":");
		this.sequenceNumber = Integer.parseInt(x[0]);
		this.ackNumber = Integer.parseInt(x[1]);
		this.ConnectionID = Short.valueOf(x[2]);
		//if(x.length==4) {
			this.ASF = Short.valueOf(x[3]);
		//}
		
		if(x.length==5) {
			this.Dados = x[4].getBytes();
		}
		
		
		
	}
	
}
