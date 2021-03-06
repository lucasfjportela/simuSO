package memoria;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Random;

public class MemoriaHD {
	private String local;
	//private int countNumeroLinhas = 0; // contador para saber se o indice n�o estrapolou o tamanho maximo da memoria do HD
	private int tamanho = 0;
	
	public MemoriaHD(String local, int pTamanho) {
		this.local = local;
		this.tamanho = pTamanho;
		try {
			File file = new File(this.local);
			if(file.exists()){
				return;
			} else {
				BufferedWriter buffWrite = new BufferedWriter(new FileWriter(file, true));
				Random r = new Random();
			
				for(int i = 0; i < pTamanho; i++){
					Integer seed = r.nextInt(100);
					this.setValorHD(seed);
				}
				
				buffWrite.close();
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void setValorHD(Integer pValor) {
		try {
			BufferedWriter buffWrite = new BufferedWriter(new FileWriter(this.local, true));
	        buffWrite.write(Integer.toString(pValor));
	        buffWrite.newLine();
	        	
	        
	        buffWrite.close();
	        //this.countNumeroLinhas++;
		}catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public Integer getValorHD(int indice) {
		Integer valor = 0;
		
		try {
			FileReader arq = new FileReader(this.local);
		    BufferedReader lerArq = new BufferedReader(arq);
		    String linha = lerArq.readLine();
		    
		    for(int i = 0; i < indice; i++) {
		    	linha = lerArq.readLine();
		    }
		    valor = Integer.parseInt(linha);
		    arq.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return valor;
	}
	
	public void swap(Integer pValor, Integer pIndice) {
		try {	        

			FileReader arq = new FileReader(this.local);
		    BufferedReader lerArq = new BufferedReader(arq);
		    String linha = lerArq.readLine();
		   
		 	BufferedWriter buffWrite = null;
		 	
		 	String []linhasSplite = new String[this.tamanho];
		 	int a = 0;
		 	while (linha != null) {
		 		linhasSplite[a] = linha.toString();
		 		linha = lerArq.readLine();
		 		++a;
		 	}
		 	
		 	linhasSplite[pIndice] = pValor.toString();
		 	
		 	buffWrite = new BufferedWriter(new FileWriter(this.local));
		 	
		 	for(int i = 0; i < this.tamanho; i++){
				this.setValorHD(Integer.parseInt(linhasSplite[i]));
			}
	        
	        buffWrite.close();
	        lerArq.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public int getTamanho() {
		return this.tamanho;
	}
}