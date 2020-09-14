// generated with ast extension for cup
// version 0.8
// 14/8/2020 3:2:57


package rs.ac.bg.etf.pp1.ast;

public class AbstractMethodDecl implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private AbstractReturnType AbstractReturnType;
    private String methName;
    private FormPars FormPars;

    public AbstractMethodDecl (AbstractReturnType AbstractReturnType, String methName, FormPars FormPars) {
        this.AbstractReturnType=AbstractReturnType;
        if(AbstractReturnType!=null) AbstractReturnType.setParent(this);
        this.methName=methName;
        this.FormPars=FormPars;
        if(FormPars!=null) FormPars.setParent(this);
    }

    public AbstractReturnType getAbstractReturnType() {
        return AbstractReturnType;
    }

    public void setAbstractReturnType(AbstractReturnType AbstractReturnType) {
        this.AbstractReturnType=AbstractReturnType;
    }

    public String getMethName() {
        return methName;
    }

    public void setMethName(String methName) {
        this.methName=methName;
    }

    public FormPars getFormPars() {
        return FormPars;
    }

    public void setFormPars(FormPars FormPars) {
        this.FormPars=FormPars;
    }

    public SyntaxNode getParent() {
        return parent;
    }

    public void setParent(SyntaxNode parent) {
        this.parent=parent;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line=line;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(AbstractReturnType!=null) AbstractReturnType.accept(visitor);
        if(FormPars!=null) FormPars.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(AbstractReturnType!=null) AbstractReturnType.traverseTopDown(visitor);
        if(FormPars!=null) FormPars.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(AbstractReturnType!=null) AbstractReturnType.traverseBottomUp(visitor);
        if(FormPars!=null) FormPars.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("AbstractMethodDecl(\n");

        if(AbstractReturnType!=null)
            buffer.append(AbstractReturnType.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(" "+tab+methName);
        buffer.append("\n");

        if(FormPars!=null)
            buffer.append(FormPars.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [AbstractMethodDecl]");
        return buffer.toString();
    }
}
