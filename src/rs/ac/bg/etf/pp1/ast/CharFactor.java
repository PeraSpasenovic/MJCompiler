// generated with ast extension for cup
// version 0.8
// 15/8/2020 12:7:24


package rs.ac.bg.etf.pp1.ast;

public class CharFactor extends Factor {

    private Character constVal;

    public CharFactor (Character constVal) {
        this.constVal=constVal;
    }

    public Character getConstVal() {
        return constVal;
    }

    public void setConstVal(Character constVal) {
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
        buffer.append("CharFactor(\n");

        buffer.append(" "+tab+constVal);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [CharFactor]");
        return buffer.toString();
    }
}
