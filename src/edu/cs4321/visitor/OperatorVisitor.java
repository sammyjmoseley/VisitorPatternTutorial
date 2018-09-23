package edu.cs4321.visitor;

import edu.cs4321.tree.Addition;
import edu.cs4321.tree.IntegerConstant;
import edu.cs4321.tree.Multiplication;
import edu.cs4321.tree.Variable;

public interface OperatorVisitor {
    void visit(Addition op);
    void visit(IntegerConstant op);
    void visit(Multiplication op);
    void visit(Variable op);
}
