package edu.cs4321.tree;

import edu.cs4321.visitor.OperatorVisitor;

import java.util.Map;

public class Variable implements Operator {
    String variableName;

    public Variable(String variableName) {
        this.variableName = variableName;
    }

    public String getVariableName() {
        return variableName;
    }

    @Override
    public int compute(Map<String, Integer> variables) {
        return variables.get(variableName);
    }

    @Override
    public void accept(OperatorVisitor visitor) {
        visitor.visit(this);
    }
}
