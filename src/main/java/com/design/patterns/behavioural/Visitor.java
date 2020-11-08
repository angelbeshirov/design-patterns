package com.design.patterns.behavioural;

import java.util.ArrayList;
import java.util.List;

/**
 * In object-oriented programming and software engineering, the visitor design pattern is a way of separating an algorithm from an
 * object structure on which it operates. A practical result of this separation is the ability to add new operations to existing
 * object structures without modifying the structures. It is one way to follow the open/closed principle.
 * @author angel.beshirov
 */
abstract class Node {
    private int value;


    public Node(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public abstract void accept(Visitor visitor);
}

class TreeNode extends Node {
    private List<Node> children;

    public TreeNode(int value) {
        super(value);
        this.children = new ArrayList<>();
    }

    public void addChild(Node child) {
        children.add(child);
    }

    @Override
    public void accept(Visitor visitor) {
        for(Node child: children) {
            child.accept(visitor);
        }


        visitor.visitTreeNode(this);
    }
}

class TreeLeaf extends Node {
    public TreeLeaf(int value) {
        super(value);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitLeaf(this);
    }
}

interface Visitor {
    void visitLeaf(Node node);
    void visitTreeNode(Node node);
}

class BfsVisitor implements Visitor {

    @Override
    public void visitLeaf(Node node) {
        System.out.println("Visiting leaf with value " + node.getValue());
    }

    @Override
    public void visitTreeNode(Node node) {
        System.out.println("Visiting tree node with value " + node.getValue());
    }
}


class TestVisitor {
    public static void main(String... args) {
        TreeNode root = new TreeNode(3);
        TreeNode child1 = new TreeNode(4);
        TreeNode child2 = new TreeNode(5);

        root.addChild(child1);
        root.addChild(child2);

        Node child3 = new TreeNode(6);
        Node child4 = new TreeNode(7);

        child1.addChild(child3);
        child1.addChild(child4);

        Node child5 = new TreeNode(8);
        Node child6 = new TreeNode(9);

        child2.addChild(child5);
        child2.addChild(child6);

        Visitor bfsVisit = new BfsVisitor();
        root.accept(bfsVisit);
    }
}
