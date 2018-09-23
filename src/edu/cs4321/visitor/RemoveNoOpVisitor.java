package edu.cs4321.visitor;

import edu.cs4321.tree.*;

/**
 * Should remove all terms that are noops.
 */
public class RemoveNoOpVisitor implements OperatorVisitor {
    private Operator operator = null;

    public Operator getOp() {
        return operator;
    }

    @Override
    public void visit(Addition op) {
        Operator left, right;
        op.getLeft().accept(this);
        left = operator;

        op.getRight().accept(this);
        right = operator;

        operator = new Addition(left, right);
    }

    @Override
    public void visit(IntegerConstant op) {
        operator = new IntegerConstant(op.getVal());
    }

    @Override
    public void visit(Multiplication op) {
        Operator left, right;
        op.getLeft().accept(this);
        left = operator;

        op.getRight().accept(this);
        right = operator;

        operator = new Multiplication(left, right);
    }

    @Override
    public void visit(Variable op) {
        operator = new Variable(op.getVariableName());
        operator = new Multiplication(operator, new IntegerConstant(2));
    }
}
