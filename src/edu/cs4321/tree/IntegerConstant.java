package edu.cs4321.tree;

import edu.cs4321.visitor.OperatorVisitor;

import java.util.Map;

public class IntegerConstant implements Operator {
    int val;

    public IntegerConstant(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }

    @Override
    public int compute(Map<String, Integer> variables) {
        return val;
    }

    @Override
    public void accept(OperatorVisitor visitor) {
        visitor.visit(this);
    }
}
