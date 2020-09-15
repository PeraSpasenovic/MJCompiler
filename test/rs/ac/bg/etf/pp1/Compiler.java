package rs.ac.bg.etf.pp1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import java_cup.runtime.Symbol;
import rs.ac.bg.etf.pp1.ast.Program;
import rs.ac.bg.etf.pp1.util.Log4JUtils;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;

public class Compiler {
	
	static {
		DOMConfigurator.configure(Log4JUtils.instance().findLoggerConfigFile());
		Log4JUtils.instance().prepareLogFile(Logger.getRootLogger());
	}
	
	public static void tsdump() {
		Tab.dump();
	}
	
	public static void main(String[] args) throws Exception {
		
		Logger log = Logger.getLogger(Compiler.class);
		if (args.length < 2) {
			log.error("Greska: Nedovoljno unetih argumenata! Format komande: Compiler <ulaznifajl>.mj <izlaznifajl>.obj");
		}
		
		Reader br = null;
		try {
//			File sourceCode = new File("test/test301.mj");
			File sourceCode = new File(args[0]);
			
			log.info("Prevodjenje izvornog fajla: " + sourceCode.getAbsolutePath());
			
			br = new BufferedReader(new FileReader(sourceCode));
			Yylex lexer = new Yylex(br);
			
			MJParser p = new MJParser(lexer);
	        Symbol s = p.parse();  //pocetak parsiranja
	        
	        Program prog = (Program)(s.value); 
			// ispis sintaksnog stabla
			log.info(prog.toString(""));
			log.info("===================================");

			Tab.init(); // Universe scope
			
			// ispis prepoznatih programskih konstrukcija
			SemanticAnalyzer v = new SemanticAnalyzer();
			prog.traverseBottomUp(v); 
			
	        Tab.dump();
	        
	        if(!p.errorDetected && v.passed() && v.getMainExists()){
//				File objFile = new File("test/program.obj");
	        	File objFile = new File(args[1]);
				if(objFile.exists()) objFile.delete();
				
				CodeGenerator codeGenerator = new CodeGenerator();
				prog.traverseBottomUp(codeGenerator);
				Code.dataSize = v.nVars;
				Code.mainPc = codeGenerator.getMainPc();
				Code.write(new FileOutputStream(objFile));
				log.info("Prevodjenje uspesno zavrseno!");
			}else{
				if (p.errorDetected) {
					log.error("Prevodjenje NIJE uspesno zavrseno zbog sintaksne greske!");					
				}
				else {
					if (!v.passed()) {
						log.error("Prevodjenje NIJE uspesno zavrseno zbog semanticke greske!");
					}
					else {
						log.error("Prevodjenje NIJE uspesno zavrseno - nema main funkcije!");
					}
				}
			}
		} 
		finally {
			if (br != null) try { br.close(); } catch (IOException e1) { log.error(e1.getMessage(), e1); }
		}

	}
}