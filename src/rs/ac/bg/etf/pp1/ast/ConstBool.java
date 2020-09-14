// generated with ast extension for cup
// version 0.8
// 14/8/2020 3:2:57


package rs.ac.bg.etf.pp1.ast;

public class ConstBool extends ConstInit {

    private String constName;
    private Boolean constVal;

    public ConstBool (String constName, Boolean constVal) {
        this.constName=constName;
        this.constVal=constVal;
        
    }

    public String getConstName() {
        return constName;
    }

    public void setConstName(String constName) {
        this.constName=constName;
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
        buffer.append("ConstBool(\n");

        buffer.append(" "+tab+constName);
        buffer.append("\n");

        if(constVal!=null)
            buffer.append("  "+tab+constVal.toString());
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstBool]");
        return buffer.toString();
    }
}
