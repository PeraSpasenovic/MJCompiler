// generated with ast extension for cup
// version 0.8
// 15/8/2020 12:7:24


package rs.ac.bg.etf.pp1.ast;

public class AbstractReturnVoid extends AbstractReturnType {

    public AbstractReturnVoid () {
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
        buffer.append("AbstractReturnVoid(\n");

        buffer.append(tab);
        buffer.append(") [AbstractReturnVoid]");
        return buffer.toString();
    }
}
