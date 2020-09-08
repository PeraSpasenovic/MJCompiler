// generated with ast extension for cup
// version 0.8
// 8/8/2020 3:43:31


package rs.ac.bg.etf.pp1.ast;

public class Modulate extends Mulop {

    public Modulate () {
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
        buffer.append("Modulate(\n");

        buffer.append(tab);
        buffer.append(") [Modulate]");
        return buffer.toString();
    }
}
