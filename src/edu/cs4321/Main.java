package edu.cs4321;

import edu.cs4321.tree.Operator;
import edu.cs4321.visitor.Mult2Visitor;

import java.util.Map;

public class Main {

    public static void main(String[] args) {
        Operator op = Parse.parse("(a + 2)*3");
        Mult2Visitor mult2Visitor = new Mult2Visitor();
        op.accept(mult2Visitor);
        op = mult2Visitor.getOp();

        System.out.println(op.compute(Map.of("a", 2)));
    }
}
