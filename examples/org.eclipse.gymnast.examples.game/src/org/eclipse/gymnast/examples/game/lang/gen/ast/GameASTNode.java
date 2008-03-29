package org.eclipse.gymnast.examples.game.lang.gen.ast;

import org.eclipse.gymnast.runtime.core.ast.ASTNode;
import org.eclipse.gymnast.runtime.core.ast.ASTNodeImpl;
import org.eclipse.gymnast.runtime.core.ast.TokenInfo;

/**
 * The superclass of all ASTNodes for language Game.
 *
 * @generated by Gymnast from game.ast on Jun 7, 2006 1:11:57 AM
 */
public abstract class GameASTNode extends ASTNodeImpl {

	protected GameASTNode _parent;

	/**
	 * @return the parent of this ASTNode or null if this is the root node of a tree
	 */
	public ASTNode getParent() {
		return _parent;
	}

	/**
	 * Construct a new GameASTNode.
	 */
	public GameASTNode() {
		super();
	}

	/**
	 * Construct a new GameASTNode.
	 *
	 * @param token a Token to initialize the offset and text for this node.
	 */
	public GameASTNode(TokenInfo tokenInfo) {
		super(tokenInfo);
	}

	/**
	 * The external entry point used to initiate the visitor on this node.
	 * 
	 * @param visitor the Visitor to visit this node tree
	 */
	public final void accept(GameASTNodeVisitor visitor) {
		visitor.preVisit(this);
		acceptImpl(visitor);
		visitor.postVisit(this);
	}

	/**
	 * This method can be overridden by subclasses which should provide exactly
	 * the same implementation.  Here <code>this</code> refers to the generic node
	 * class, but in the subclass implementations <code>this</code> will refer to
	 * the specific subclass type.  Thus the correct specific <code>beginVisit</code>
	 * and <code>endVisit</code> methods will be invoked for each subclass and the
	 * generic methods will be invoked for subclasses that don't need specific visitor
	 * behavior.
	 */
	public void acceptImpl(GameASTNodeVisitor visitor) {
		boolean visitChildren = visitor.beginVisit(this);
		if (visitChildren) visitChildren(visitor);
		visitor.endVisit(this);
	}

	/**
	 * Iterate through the children of this node and accept the visitor on each.
	 */
	protected void visitChildren(GameASTNodeVisitor visitor) {
		for (int i = 0; i < getChildCount(); i++) {
			ASTNode child = getChild(i);
			if (child instanceof GameASTNode) {
				GameASTNode c = (GameASTNode)child;
				c.accept(visitor);
			}
		}
	}

}