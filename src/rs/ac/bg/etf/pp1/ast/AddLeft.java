// generated with ast extension for cup
// version 0.8
// 10/8/2020 4:4:4


package rs.ac.bg.etf.pp1.ast;

public class AddLeft extends AddopLeft {

    public AddLeft () {
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
        buffer.append("AddLeft(\n");

        buffer.append(tab);
        buffer.append(") [AddLeft]");
        return buffer.toString();
    }
}