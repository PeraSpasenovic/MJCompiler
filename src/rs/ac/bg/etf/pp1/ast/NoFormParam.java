// generated with ast extension for cup
// version 0.8
// 7/8/2020 19:52:13


package rs.ac.bg.etf.pp1.ast;

public class NoFormParam extends FormPars {

    public NoFormParam () {
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
        buffer.append("NoFormParam(\n");

        buffer.append(tab);
        buffer.append(") [NoFormParam]");
        return buffer.toString();
    }
}