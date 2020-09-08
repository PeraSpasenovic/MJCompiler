// generated with ast extension for cup
// version 0.8
// 8/8/2020 3:43:31


package rs.ac.bg.etf.pp1.ast;

public class BoolFactor extends Factor {

    private Boolean constVal;

    public BoolFactor (Boolean constVal) {
        this.constVal=constVal;
        
    }

    public Boolean getConstVal() {
        return constVal;
    }

    public void setConstVal(Boolean constVal) {
        this.constVal=constVal;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        
    }

    public void traverseBottomUp(Visitor visitor) {
        
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("BoolFactor(\n");

        if(constVal!=null)
            buffer.append("  "+tab+constVal.toString());
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [BoolFactor]");
        return buffer.toString();
    }
}
