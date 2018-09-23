package edu.cs4321.tree;

import edu.cs4321.visitor.OperatorVisitor;

import java.util.Map;

public interface Operator {
    int compute(Map<String, Integer> variables);

    void accept(OperatorVisitor visitor);
}
