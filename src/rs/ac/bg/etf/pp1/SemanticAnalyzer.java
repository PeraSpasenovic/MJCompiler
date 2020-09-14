package rs.ac.bg.etf.pp1;

import org.apache.log4j.Logger;
import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.*;

public class SemanticAnalyzer extends VisitorAdaptor {
	
	public static final Struct boolType = new Struct(Struct.Bool);
	
	int nVars;
	boolean mainExists = false;
	boolean errorDetected = false;
	boolean inClass = false;
	Struct currentType = null;

	Logger log = Logger.getLogger(getClass());

	public SemanticAnalyzer() {
		Tab.currentScope.addToLocals(new Obj(Obj.Type, "bool", boolType));
	}
	
	public void report_error(String message, SyntaxNode info) {
		errorDetected = true;
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append (" (linija ").append(line).append(")");
		log.error(msg.toString());
	}

	public void report_info(String message, SyntaxNode info) {
		StringBuilder msg = new StringBuilder(message); 
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append (" na liniji ").append(line);
		log.info(msg.toString());
	}
	
	public boolean getMainExists() {
		return mainExists;
	}
	
	public boolean passed() {
		return !errorDetected;
	}
	
    public void visit (ProgName progName){
    	progName.obj = Tab.insert(Obj.Prog, progName.getPName(), Tab.noType);
    	Tab.openScope();
    }

    public void visit (Program program){
    	nVars = Tab.currentScope.getnVars();
    	Tab.chainLocalSymbols(program.getProgName().obj);
    	Tab.closeScope();
    }
    
    public void visit (Type type) {
    	Obj typeNode = Tab.find(type.getTypeName());
    	if(typeNode == Tab.noObj){
    		report_error("Greska: Tip " + type.getTypeName() + " nije definisan!", type);
    		type.struct = Tab.noType;
    	}else{
    		if(Obj.Type == typeNode.getKind()){
    			type.struct = typeNode.getType();
    		}else{
    			report_error("Greska: Ime " + type.getTypeName() + " ne predstavlja tip!", type);
    			type.struct = Tab.noType;
    		}
    	}
    	currentType = type.struct;
    }
    
    public void visit (RegVarName varName) {
    	Obj varExists = Tab.currentScope.findSymbol(varName.getVarName());
    	
    	if (varExists == null) {
        	Tab.insert(Obj.Var, varName.getVarName(), currentType);    		
    	}
    	else {
    		report_error("Greska: Visestruka deklaracija promenljive " + varName.getVarName() + "!", varName);
    	}
    }
    
    public void visit (ArrVarName varName) {
    	Obj varExists = Tab.currentScope.findSymbol(varName.getVarName());
    	
    	if (varExists == null) {
        	Tab.insert(Obj.Var, varName.getVarName(), new Struct(Struct.Array,currentType));
    	}
    	else {
    		report_error("Greska: Visestruka deklaracija promenljive " + varName.getVarName() + "!", varName);
    	}
    }
    
    public void visit (ConstNum constNum) {
    	Obj constNode = Tab.currentScope.findSymbol(constNum.getConstName());
    	
    	if (constNode == null) {
    		if (currentType.getKind() == Struct.Int) {
    			constNode = Tab.insert(Obj.Con, constNum.getConstName(), currentType);
    			constNode.setAdr(constNum.getConstVal());
    		}
    		else {
    			report_error("Greska: Konstanta tipa int se ne moze inicijalizovati vrednoscu " + constNum.getConstVal() + "!", constNum);
    		}
    	}
    	else {
    		report_error("Greska: Visestruka deklaracija konstante " + constNum.getConstName(), constNum);
    	}
    }
    
    public void visit (ConstChar constChar) {
    	Obj constNode = Tab.currentScope.findSymbol(constChar.getConstName());
    	
    	if (constNode == null) {
    		if (currentType.getKind() == Struct.Char) {
    			constNode = Tab.insert(Obj.Con, constChar.getConstName(), currentType);
    			constNode.setAdr(constChar.getConstVal());
    		}
    		else {
    			report_error("Greska: Konstanta tipa char se ne moze inicijalizovati vrednoscu " + constChar.getConstVal() + "!", constChar);
    		}
    	}
    	else {
    		report_error("Greska: Visestruka deklaracija konstante " + constChar.getConstName(), constChar);
    	}
    }
    
    public void visit (ConstBool constBool) {
    	Obj constNode = Tab.currentScope.findSymbol(constBool.getConstName());
    	
    	if (constNode == null) {
    		if (currentType.getKind() == Struct.Bool) {
    			constNode = Tab.insert(Obj.Con, constBool.getConstName(), currentType);
    			int value = (constBool.getConstVal().booleanValue()) ? 1 : 0;
    			constNode.setAdr(value);
    		}
    		else {
    			report_error("Greska: Konstanta tipa bool se ne moze inicijalizovati vrednoscu " + constBool.getConstVal() + "!", constBool);
    		}
    	}
    	else {
    		report_error("Greska: Visestruka deklaracija konstante " + constBool.getConstName(), constBool);
    	}
    }
    
    public void visit (VarDesignator varDesignator) {
    	Obj obj = Tab.find(varDesignator.getName());
    	if(obj == Tab.noObj){
			report_error("Greska: Ime "+ varDesignator.getName()+" nije deklarisano! ", varDesignator);
    	}
    	varDesignator.obj = obj;
    }
    
    public void visit (ArrDesignator arrDesignator) {
    	Obj obj = Tab.find(arrDesignator.getName());
    	if (arrDesignator.getExpr().struct.getKind() != Struct.Int) {
			report_error("Greska: Indeks niza nije celobrojna vrednost! ", arrDesignator);    		
    	}
    	if(obj == Tab.noObj){
			report_error("Greska: Ime "+ arrDesignator.getName()+" nije deklarisano! ", arrDesignator);
    	}
    	if (obj.getType().getKind() != Struct.Array) {
    		report_error("Greska: "+ arrDesignator.getName()+" nije ime niza! ", arrDesignator);
    	}
    	arrDesignator.obj = obj;
    }
    
    public void visit (NumFactor numFactor) {
    	numFactor.struct = Tab.intType;
    }
    
    public void visit (CharFactor charFactor) {
    	charFactor.struct = Tab.charType;
    }
    
    public void visit (BoolFactor boolFactor) {
    	boolFactor.struct = boolType;
    }
    
    public void visit (VarFactor varFactor) {
    	varFactor.struct = varFactor.getDesignator().obj.getType();
    }
    
    public void visit (NewFactor newFactor) {
    	newFactor.struct = newFactor.getType().struct;
    }
    
    public void visit (NewArrFactor newFactor) {
    	if (newFactor.getExpr().struct.getKind() != Struct.Int) {
			report_error("Greska: Broj elemenata niza nije celobrojna vrednost!", newFactor);    		
    	}
    	newFactor.struct = new Struct(Struct.Array);
    	newFactor.struct.setElementType(newFactor.getType().struct);
    }
    
    public void visit (ParenFactor parenFactor) {
    	parenFactor.struct = parenFactor.getExpr().struct;
    }
    
    public void visit (FactorTerm factorTerm) {
    	SyntaxNode parent = factorTerm.getParent();
    	SyntaxNode child = factorTerm.getFactor();
    	if (parent.getClass() == MulRightTerm.class && child.getClass() != VarFactor.class) {
    		report_error("Greska: Levi operand dodele nije promenljiva, element niza ili polje objekta!", factorTerm);
    	}
    	factorTerm.struct = factorTerm.getFactor().struct;
    }
    
    public void visit (MulLeftTerm mulTerm) {
    	if (mulTerm.getFactor().struct != Tab.intType || mulTerm.getTerm().struct != Tab.intType) {
    		report_error("Greska: Operandi moraju biti celobrojne vrednosti!", mulTerm);
    	}
    	mulTerm.struct = Tab.intType;
    }
    
    public void visit (MulRightTerm mulTerm) {
    	if (mulTerm.getFactor().struct != Tab.intType || mulTerm.getTerm().struct != Tab.intType) {
    		report_error("Greska: Operandi moraju biti celobrojne vrednosti!", mulTerm);
    	}
    	mulTerm.struct = Tab.intType;
    }
    
    public void visit (TermExpr expr) {
    	SyntaxNode parent = expr.getParent();
    	SyntaxNode child = expr.getTerm();
    	if (parent.getClass() == AddRightExpr.class || parent.getClass() == AddLeftExpr.class) {
    		if (child.getClass() == MulRightTerm.class || child.getClass() == MulLeftTerm.class) {
    			report_error("Greska: Levi operand dodele nije promenljiva, element niza ili polje objekta!", expr);
    		}
    		else {
    			FactorTerm factorTerm = (FactorTerm) expr.getTerm();
    			if (factorTerm.getFactor().getClass() != VarFactor.class) {
    				report_error("Greska: Levi operand dodele nije promenljiva, element niza ili polje objekta!", expr);
    			}
    		}
    	}
    	expr.struct = expr.getTerm().struct;
    }
    
    public void visit (NegTermExpr expr) {
    	if (expr.getTerm().struct != Tab.intType) {
    		report_error("Greska: Clan aritmetickog izraza nije celobrojna vrednost!", expr);
    	}
    	if (expr.getParent().getClass() == AddRightExpr.class) {
    		report_error("Greska: Levi operand dodele nije promenljiva, element niza ili polje objekta!", expr);
    	}
    	expr.struct = Tab.intType;
    }
    
    public void visit (AddLeftExpr expr) {
    	if (expr.getExpr().struct != Tab.intType || expr.getTerm().struct != Tab.intType) {
    		report_error("Greska: Operandi moraju biti celobrojne vrednosti!", expr);
    	}
    	expr.struct = Tab.intType;
    }
    
    public void visit (AddRightExpr expr) {
    	if (expr.getExpr().struct != Tab.intType || expr.getTerm().struct != Tab.intType) {
    		report_error("Greska: Operandi moraju biti celobrojne vrednosti!", expr);
    	}
    	expr.struct = Tab.intType;
    }
    
    public void visit (DesignatorAssign stmt) {
    	if (!stmt.getExpr().struct.assignableTo(stmt.getDesignator().obj.getType())) {
    		report_error("Greska: Tipovi operanada dodele se razlikuju!", stmt);
    	}
    }
    
    public void visit (DesignatorInc stmt) {
    	if (stmt.getDesignator().obj.getType() != Tab.intType) {
    		report_error("Greska: Operand inkrementiranja nije celobrojna vrednost!", stmt);
    	}
    }
    
    public void visit (DesignatorDec stmt) {
    	if (stmt.getDesignator().obj.getType() != Tab.intType) {
    		report_error("Greska: Operand inkrementiranja nije celobrojna vrednost!", stmt);
    	}
    }
    
    public void visit (MethodTypeNameVoid method) {
    	Obj exists = Tab.currentScope.findSymbol(method.getMethName());
    	Obj methodObj = null;
    	if (exists == null) {
    		methodObj = Tab.insert(Obj.Meth, method.getMethName(), Tab.noType);    		
    	}
    	else {
    		report_error("Greska: Visestruka deklaracija funkcije " + method.getMethName() + "!", method);
    	}
    	if (method.getMethName().equals("main")) {
    		mainExists = true;
    	}
    	method.obj = methodObj;
    	Tab.openScope();
    }
    
    public void visit (MethodTypeNameType method) {
    	Obj exists = Tab.currentScope.findSymbol(method.getMethName());
    	Obj methodObj = null;
    	if (exists == null) {
        	methodObj = Tab.insert(Obj.Meth, method.getMethName(), method.getType().struct);    		
    	}
    	else {
    		report_error("Greska: Visestruka deklaracija funkcije " + method.getMethName() + "!", method);
    	}
    	if (method.getMethName().equals("main")) {
    		if (method.getType().struct == Tab.intType) {
        		mainExists = true;    			
    		}
    		else {
    			report_error("Greska: Los povratni tip funkcije main!", method);
    		}
    	}
    	method.obj = methodObj;
    	Tab.openScope();
    }
    
    public void visit (MethodDecl methodDecl) {
    	Tab.chainLocalSymbols(methodDecl.getMethodTypeName().obj);
    	Tab.closeScope();
    }
    
    public void visit (ClassName name) {
    	Obj typeNode = Tab.find(name.getName());
    	if(typeNode != Tab.noObj){
    		report_error("Greska: Visestruka definicija klase " + name.getName() + "!", name);
    		name.struct = Tab.noType;
    	}
    	else {
    		Obj noviTip = new Obj(Obj.Type, name.getName(), new Struct(Struct.Class));
    		Tab.currentScope.addToLocals(noviTip);
    		name.struct = noviTip.getType();
    		Tab.openScope();
    	}
    }
    
    public void visit (ClassDecl classDecl) {
    	Tab.chainLocalSymbols(classDecl.getClassName().struct);
    	Tab.closeScope();
    }
    
    public void visit (AbstractClassName name) {
    	Obj typeNode = Tab.find(name.getName());
    	if(typeNode != Tab.noObj){
    		report_error("Greska: Visestruka definicija klase " + name.getName() + "!", name);
    		name.struct = Tab.noType;
    	}
    	else {
    		Obj noviTip = new Obj(Obj.Type, name.getName(), new Struct(Struct.Class));
    		Tab.currentScope.addToLocals(noviTip);
    		name.struct = noviTip.getType();
    		Tab.openScope();
    	}
    }
    
    public void visit (AbstractClassDecl classDecl) {
    	Tab.chainLocalSymbols(classDecl.getAbstractClassName().struct);
    	Tab.closeScope();
    }
}




