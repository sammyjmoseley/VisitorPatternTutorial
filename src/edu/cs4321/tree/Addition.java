package edu.cs4321.tree;

import edu.cs4321.visitor.OperatorVisitor;

import java.util.Map;

public class Addition implements Operator {
    Operator left, right;

    public Addition(Operator left, Operator right) {
        this.left = left;
        this.right = right;
    }

    public Operator getLeft() {
        return left;
    }

    public Operator getRight() {
        return right;
    }

    @Override
    public int compute(Map<String, Integer> variables) {
        return left.compute(variables) + right.compute(variables);
    }

    @Override
    public void accept(OperatorVisitor visitor) {
        visitor.visit(this);
    }
}
