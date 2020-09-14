// generated with ast extension for cup
// version 0.8
// 14/8/2020 3:2:57


package rs.ac.bg.etf.pp1.ast;

public class ModulateRight extends MulopRight {

    public ModulateRight () {
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
        buffer.append("ModulateRight(\n");

        buffer.append(tab);
        buffer.append(") [ModulateRight]");
        return buffer.toString();
    }
}
