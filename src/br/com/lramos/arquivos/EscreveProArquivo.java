package br.com.lramos.arquivos;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import br.com.lramos.math.EncontraPrimos;

/**
 * 
 * Classe responsável por gerar arquivos com os outputs dos processamentos feitos pela classe {@link EncontraPrimos}.
 * 
 * @author leonardorm
 *
 */
public class EscreveProArquivo {

	private static final String DIRETORIO = "/home/leonardorm/Documentos/Projetos/outputs_primos";
	
	/**
	 * 
	 * 
	 * 
	 * @param file
	 * @param texto
	 * @throws IOException
	 */
	public void escreveNoArquivo (File file, String texto) throws IOException {
		
		FileWriter fileWriter = new FileWriter(file);
		
		BufferedWriter writer = new BufferedWriter(fileWriter);
		
		writer.write(texto);
		writer.close();
		
	}
	
	/**
	 * 
	 * Recebe nome da nova pasta e do novo arquivo.
	 * 
	 * @param subdir
	 * @param nome
	 * @return {@link File Arquivo} criado dentro da pasta.
	 */
	public File criaArquivo(String subdir, String nome) {

		subdir = File.separator + subdir + File.separator;
		
		File permfile = null;

		try {
			
			File dir = (criaSubDiretorio(subdir));
			
			dir.mkdirs();
			
			permfile = new File(dir + File.separator +  nome + ".txt");
			
			permfile.createNewFile();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return permfile;

	}
	
	/**
	 * 
	 * Cria uma pasta dentro da pasta outputs_primos.
	 * 
	 * @param nomeSubDir
	 * @return
	 * @throws IOException
	 */
	private File criaSubDiretorio(String nomeSubDir) throws IOException {
		
		nomeSubDir = DIRETORIO + nomeSubDir;

		File diretorio = new File(nomeSubDir);

		// Cria diretório, caso o mesmo não exista.
		if (!diretorio.exists()) {

			try {
				diretorio.mkdir();
			} catch (SecurityException se) {
				se.printStackTrace();
			}

		}
		
		return diretorio;

	}
	
	public static void main(String[] args) {
		
		EscreveProArquivo escritor = new EscreveProArquivo();
		
		escritor.criaArquivo("leo", "leo");
		
	}
}
