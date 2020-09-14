// generated with ast extension for cup
// version 0.8
// 14/8/2020 3:2:57


package rs.ac.bg.etf.pp1.ast;

public class MulRightTerm extends Term {

    private Term Term;
    private MulopRight MulopRight;
    private Factor Factor;

    public MulRightTerm (Term Term, MulopRight MulopRight, Factor Factor) {
        this.Term=Term;
        if(Term!=null) Term.setParent(this);
        this.MulopRight=MulopRight;
        if(MulopRight!=null) MulopRight.setParent(this);
        this.Factor=Factor;
        if(Factor!=null) Factor.setParent(this);
    }

    public Term getTerm() {
        return Term;
    }

    public void setTerm(Term Term) {
        this.Term=Term;
    }

    public MulopRight getMulopRight() {
        return MulopRight;
    }

    public void setMulopRight(MulopRight MulopRight) {
        this.MulopRight=MulopRight;
    }

    public Factor getFactor() {
        return Factor;
    }

    public void setFactor(Factor Factor) {
        this.Factor=Factor;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Term!=null) Term.accept(visitor);
        if(MulopRight!=null) MulopRight.accept(visitor);
        if(Factor!=null) Factor.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Term!=null) Term.traverseTopDown(visitor);
        if(MulopRight!=null) MulopRight.traverseTopDown(visitor);
        if(Factor!=null) Factor.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Term!=null) Term.traverseBottomUp(visitor);
        if(MulopRight!=null) MulopRight.traverseBottomUp(visitor);
        if(Factor!=null) Factor.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MulRightTerm(\n");

        if(Term!=null)
            buffer.append(Term.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MulopRight!=null)
            buffer.append(MulopRight.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Factor!=null)
            buffer.append(Factor.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MulRightTerm]");
        return buffer.toString();
    }
}
