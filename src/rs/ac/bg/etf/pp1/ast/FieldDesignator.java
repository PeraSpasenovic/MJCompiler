// generated with ast extension for cup
// version 0.8
// 15/8/2020 12:7:24


package rs.ac.bg.etf.pp1.ast;

public class FieldDesignator extends Designator {

    private String className;
    private String fieldName;

    public FieldDesignator (String className, String fieldName) {
        this.className=className;
        this.fieldName=fieldName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className=className;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName=fieldName;
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
        buffer.append("FieldDesignator(\n");

        buffer.append(" "+tab+className);
        buffer.append("\n");

        buffer.append(" "+tab+fieldName);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [FieldDesignator]");
        return buffer.toString();
    }
}
