// generated with ast extension for cup
// version 0.8
// 15/8/2020 12:7:24


package rs.ac.bg.etf.pp1.ast;

public class AbstractClassDecl implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private AbstractClassName AbstractClassName;
    private Extends Extends;
    private VarDeclList VarDeclList;
    private AbstractClassMethodList AbstractClassMethodList;

    public AbstractClassDecl (AbstractClassName AbstractClassName, Extends Extends, VarDeclList VarDeclList, AbstractClassMethodList AbstractClassMethodList) {
        this.AbstractClassName=AbstractClassName;
        if(AbstractClassName!=null) AbstractClassName.setParent(this);
        this.Extends=Extends;
        if(Extends!=null) Extends.setParent(this);
        this.VarDeclList=VarDeclList;
        if(VarDeclList!=null) VarDeclList.setParent(this);
        this.AbstractClassMethodList=AbstractClassMethodList;
        if(AbstractClassMethodList!=null) AbstractClassMethodList.setParent(this);
    }

    public AbstractClassName getAbstractClassName() {
        return AbstractClassName;
    }

    public void setAbstractClassName(AbstractClassName AbstractClassName) {
        this.AbstractClassName=AbstractClassName;
    }

    public Extends getExtends() {
        return Extends;
    }

    public void setExtends(Extends Extends) {
        this.Extends=Extends;
    }

    public VarDeclList getVarDeclList() {
        return VarDeclList;
    }

    public void setVarDeclList(VarDeclList VarDeclList) {
        this.VarDeclList=VarDeclList;
    }

    public AbstractClassMethodList getAbstractClassMethodList() {
        return AbstractClassMethodList;
    }

    public void setAbstractClassMethodList(AbstractClassMethodList AbstractClassMethodList) {
        this.AbstractClassMethodList=AbstractClassMethodList;
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
        if(AbstractClassName!=null) AbstractClassName.accept(visitor);
        if(Extends!=null) Extends.accept(visitor);
        if(VarDeclList!=null) VarDeclList.accept(visitor);
        if(AbstractClassMethodList!=null) AbstractClassMethodList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(AbstractClassName!=null) AbstractClassName.traverseTopDown(visitor);
        if(Extends!=null) Extends.traverseTopDown(visitor);
        if(VarDeclList!=null) VarDeclList.traverseTopDown(visitor);
        if(AbstractClassMethodList!=null) AbstractClassMethodList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(AbstractClassName!=null) AbstractClassName.traverseBottomUp(visitor);
        if(Extends!=null) Extends.traverseBottomUp(visitor);
        if(VarDeclList!=null) VarDeclList.traverseBottomUp(visitor);
        if(AbstractClassMethodList!=null) AbstractClassMethodList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("AbstractClassDecl(\n");

        if(AbstractClassName!=null)
            buffer.append(AbstractClassName.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Extends!=null)
            buffer.append(Extends.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarDeclList!=null)
            buffer.append(VarDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(AbstractClassMethodList!=null)
            buffer.append(AbstractClassMethodList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [AbstractClassDecl]");
        return buffer.toString();
    }
}
