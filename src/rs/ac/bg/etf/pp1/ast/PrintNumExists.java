// generated with ast extension for cup
// version 0.8
// 14/8/2020 3:2:57


package rs.ac.bg.etf.pp1.ast;

public class PrintNumExists extends PrintNum {

    private Integer printNum;

    public PrintNumExists (Integer printNum) {
        this.printNum=printNum;
    }

    public Integer getPrintNum() {
        return printNum;
    }

    public void setPrintNum(Integer printNum) {
        this.printNum=printNum;
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
        buffer.append("PrintNumExists(\n");

        buffer.append(" "+tab+printNum);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [PrintNumExists]");
        return buffer.toString();
    }
}
