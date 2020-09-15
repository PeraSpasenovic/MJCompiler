// generated with ast extension for cup
// version 0.8
// 15/8/2020 12:7:24


package rs.ac.bg.etf.pp1.ast;

public class ForStmt extends Statement {

    private InitialDesignator InitialDesignator;
    private ForCondition ForCondition;
    private AfterDesignator AfterDesignator;
    private Statement Statement;

    public ForStmt (InitialDesignator InitialDesignator, ForCondition ForCondition, AfterDesignator AfterDesignator, Statement Statement) {
        this.InitialDesignator=InitialDesignator;
        if(InitialDesignator!=null) InitialDesignator.setParent(this);
        this.ForCondition=ForCondition;
        if(ForCondition!=null) ForCondition.setParent(this);
        this.AfterDesignator=AfterDesignator;
        if(AfterDesignator!=null) AfterDesignator.setParent(this);
        this.Statement=Statement;
        if(Statement!=null) Statement.setParent(this);
    }

    public InitialDesignator getInitialDesignator() {
        return InitialDesignator;
    }

    public void setInitialDesignator(InitialDesignator InitialDesignator) {
        this.InitialDesignator=InitialDesignator;
    }

    public ForCondition getForCondition() {
        return ForCondition;
    }

    public void setForCondition(ForCondition ForCondition) {
        this.ForCondition=ForCondition;
    }

    public AfterDesignator getAfterDesignator() {
        return AfterDesignator;
    }

    public void setAfterDesignator(AfterDesignator AfterDesignator) {
        this.AfterDesignator=AfterDesignator;
    }

    public Statement getStatement() {
        return Statement;
    }

    public void setStatement(Statement Statement) {
        this.Statement=Statement;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(InitialDesignator!=null) InitialDesignator.accept(visitor);
        if(ForCondition!=null) ForCondition.accept(visitor);
        if(AfterDesignator!=null) AfterDesignator.accept(visitor);
        if(Statement!=null) Statement.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(InitialDesignator!=null) InitialDesignator.traverseTopDown(visitor);
        if(ForCondition!=null) ForCondition.traverseTopDown(visitor);
        if(AfterDesignator!=null) AfterDesignator.traverseTopDown(visitor);
        if(Statement!=null) Statement.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(InitialDesignator!=null) InitialDesignator.traverseBottomUp(visitor);
        if(ForCondition!=null) ForCondition.traverseBottomUp(visitor);
        if(AfterDesignator!=null) AfterDesignator.traverseBottomUp(visitor);
        if(Statement!=null) Statement.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ForStmt(\n");

        if(InitialDesignator!=null)
            buffer.append(InitialDesignator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ForCondition!=null)
            buffer.append(ForCondition.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(AfterDesignator!=null)
            buffer.append(AfterDesignator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Statement!=null)
            buffer.append(Statement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ForStmt]");
        return buffer.toString();
    }
}
