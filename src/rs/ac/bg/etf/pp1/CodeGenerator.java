package rs.ac.bg.etf.pp1;

import rs.ac.bg.etf.pp1.CounterVisitor.*;
import rs.ac.bg.etf.pp1.ast.*;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.*;

public class CodeGenerator extends VisitorAdaptor {
	
	private int mainPc;
	
	public int getMainPc() {
		return mainPc;
	}
	
	public void visit(MethodTypeNameType methodTypeName){
		
		if("main".equalsIgnoreCase(methodTypeName.getMethName())){
			mainPc = Code.pc;
		}
		methodTypeName.obj.setAdr(Code.pc);
		// Collect arguments and local variables
		SyntaxNode methodNode = methodTypeName.getParent();
	
		VarCounter varCnt = new VarCounter();
		methodNode.traverseTopDown(varCnt);
		
		FormParamCounter fpCnt = new FormParamCounter();
		methodNode.traverseTopDown(fpCnt);
		
		// Generate the entry
		Code.put(Code.enter);
		Code.put(fpCnt.getCount());
		Code.put(fpCnt.getCount() + varCnt.getCount());
	}
	
	public void visit(MethodTypeNameVoid methodTypeName){
		
		if("main".equalsIgnoreCase(methodTypeName.getMethName())){
			mainPc = Code.pc;
		}
		methodTypeName.obj.setAdr(Code.pc);
		// Collect arguments and local variables
		SyntaxNode methodNode = methodTypeName.getParent();
	
		VarCounter varCnt = new VarCounter();
		methodNode.traverseTopDown(varCnt);
		
		FormParamCounter fpCnt = new FormParamCounter();
		methodNode.traverseTopDown(fpCnt);
		
		// Generate the entry
		Code.put(Code.enter);
		Code.put(fpCnt.getCount());
		Code.put(fpCnt.getCount() + varCnt.getCount());
	}

	public void visit(MethodDecl methodDecl){
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	public void visit(PrintStmt printStmt){
		boolean hasWidth = false;
		int width = 1;
		if (printStmt.getPrintNum() instanceof PrintNumExists) {
			width = ((PrintNumExists) printStmt.getPrintNum()).getPrintNum();
		}
		if (hasWidth) {
			Code.loadConst(width);				
		}
		if(printStmt.getExpr().struct == Tab.charType){
			if (!hasWidth) {
				Code.loadConst(1);
			}
			Code.put(Code.bprint);
		}else{
			if (!hasWidth) {
				Code.loadConst(5);
			}
			Code.put(Code.print);
		}
	}
	
	public void visit (NumFactor factor) {
		Obj con = Tab.insert(Obj.Con, "$", factor.struct);
		con.setLevel(0);
		con.setAdr(factor.getConstVal());
		
		Code.load(con);
	}
	
	public void visit (CharFactor factor) {
		Obj con = Tab.insert(Obj.Con, "$", factor.struct);
		con.setLevel(0);
		con.setAdr(factor.getConstVal());
		
		Code.load(con);
	}
	
	public void visit (BoolFactor factor) {
		Obj con = Tab.insert(Obj.Con, "$", factor.struct);
		con.setLevel(0);
		if (factor.getConstVal()) {
			con.setAdr(1);
		}
		else {
			con.setAdr(0);
		}
		
		Code.load(con);
	}
	
	public void visit(DesignatorAssign assignment){
		SyntaxNode operacija = assignment.getAssignop();
		if (operacija instanceof AddAs) {
			if (((AddAs)operacija).getAddopRight() instanceof AddRight) {
				Code.put(Code.add);
			}
			else {
				Code.put(Code.sub);
			}
		}
		else {
			if (operacija instanceof MulAs) {
				if (((MulAs)operacija).getMulopRight() instanceof MultiplyRight) {
					Code.put(Code.mul);
				}
				else {
					if (((MulAs)operacija).getMulopRight() instanceof DivideRight) {
						Code.put(Code.div);
					}
					else {
						Code.put(Code.rem);
					}
				}
			}
		}
		Code.store(assignment.getDesignator().obj);
	}
	
	public void visit (VarDesignator designator){
		SyntaxNode parent = designator.getParent();
		if(parent instanceof VarFactor){
			Code.load(designator.obj);
		}
		else {
			if (parent instanceof DesignatorAssign) {
				SyntaxNode operacija = ((DesignatorAssign)parent).getAssignop();
				if (operacija instanceof AddAs || operacija instanceof MulAs) {
					Code.load(designator.obj);				
				}
			}			
		}
	}
	
	public void visit (ArrDesignator designator) {
		SyntaxNode parent = designator.getParent();
		if (parent instanceof VarFactor) {
			if (parent.getParent().getParent() instanceof MulRightTerm) {
				Code.put(Code.dup2);
			}
			Code.load(designator.obj);
		}
		else {
			if (parent instanceof DesignatorAssign) {
				SyntaxNode operacija = ((DesignatorAssign)parent).getAssignop();
				if (operacija instanceof AddAs || operacija instanceof MulAs) {
					Code.put(Code.dup2);
					Code.load(designator.obj);				
				}
			}			
		}
	}
	
	public void visit (ArrayName arrayName) {
		Code.load(arrayName.obj);
	}
	
	public void visit (NewArrFactor factor) {
		Code.put(Code.newarray);
		if(factor.getType().struct == Tab.charType){
			Code.put(0);
		}
		else {
			Code.put(1);
		}
	}
	
	public void visit (MulLeftTerm term) {
		SyntaxNode instr = term.getMulopLeft();
		if (instr instanceof MultiplyLeft) {
			Code.put(Code.mul);
		}
		else {
			if (instr instanceof DivideLeft) {
				Code.put(Code.div);
			}
			else {
				Code.put(Code.rem);
			}
		}		
	}
	
	public void visit (MulRightTerm term) {
		SyntaxNode instr = term.getMulopRight();
		FactorTerm left = (FactorTerm) term.getTerm();
		VarFactor designator = (VarFactor) left.getFactor();
		if (instr instanceof MultiplyRight) {
			Code.put(Code.mul);
		}
		else {
			if (instr instanceof DivideRight) {
				Code.put(Code.div);
			}
			else {
				Code.put(Code.rem);
			}
		}
		if (designator.getDesignator() instanceof ArrDesignator) {
			Code.put(Code.dup2);
		}
		else {
			Code.put(Code.dup);
		}
		Code.store(designator.getDesignator().obj);
	}
	
	public void visit (AddLeftExpr expr) {
		SyntaxNode instr = expr.getAddopLeft();
		if (instr instanceof AddLeft) {
			Code.put(Code.add);
		}
		else {
			Code.put(Code.sub);
		}
	}
	
	public void visit (AddRightExpr expr) {
		SyntaxNode instr = expr.getAddopRight();
		TermExpr left = (TermExpr) expr.getExpr();
		VarFactor designator = (VarFactor) ((FactorTerm)left.getTerm()).getFactor();
		if (instr instanceof AddRight) {
			Code.put(Code.add);
		}
		else {
			Code.put(Code.sub);
		}
		if (designator.getDesignator() instanceof ArrDesignator) {
			Code.put(Code.dup2);
		}
		else {
			Code.put(Code.dup);
		}
		Code.store(designator.getDesignator().obj);
	}
	
	public void visit (NegTermExpr expr) {
		Code.put(Code.neg);
	}
	
	public void visit (DesignatorInc designator) {
		Obj obj = designator.getDesignator().obj;
		
		if (obj.getKind() == Obj.Elem) {
			Code.put(Code.dup2);
		}
		Code.load(obj);
		Code.loadConst(1);
		Code.put(Code.add);
		Code.store(obj);
	}
	
	public void visit (DesignatorDec designator) {
		Obj obj = designator.getDesignator().obj;
		
		if (obj.getKind() == Obj.Elem) {
			Code.put(Code.dup2);
		}
		Code.load(obj);
		Code.loadConst(1);
		Code.put(Code.sub);
		Code.store(obj);
	}
	
	public void visit (ReadStmt readStmt) {
		Obj obj = readStmt.getDesignator().obj;
		if (obj.getType() == Tab.charType) {
			Code.put(Code.bread);
		}
		else {
			Code.put(Code.read);
		}
		Code.store(obj);
	}
}


