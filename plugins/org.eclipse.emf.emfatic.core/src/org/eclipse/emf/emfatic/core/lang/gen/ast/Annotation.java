package org.eclipse.emf.emfatic.core.lang.gen.ast;

import org.eclipse.gymnast.runtime.core.ast.ASTNode;
import org.eclipse.gymnast.runtime.core.ast.TokenInfo;

/**
 *
 * @generated by Gymnast from Emfatic.ast on 06.02.2007 17:57:34
 */
public class Annotation extends EmfaticASTNode  {


	private EmfaticTokenNode _at;
	private StringLiteralOrQualifiedID _source;
	private EmfaticTokenNode _lparen;
	private KeyEqualsValueList _keyEqualsValueList;
	private EmfaticTokenNode _rparen;

	public EmfaticTokenNode getAt() {
		return _at;
	}
	public StringLiteralOrQualifiedID getSource() {
		return _source;
	}
	public EmfaticTokenNode getLparen() {
		return _lparen;
	}
	public KeyEqualsValueList getKeyEqualsValueList() {
		return _keyEqualsValueList;
	}
	public EmfaticTokenNode getRparen() {
		return _rparen;
	}


	/**
	 * @return the number of children of this ASTNode
	 */
	public int getChildCount() {
		int count = 0;
		if (_at != null) count++;
		if (_source != null) count++;
		if (_lparen != null) count++;
		if (_keyEqualsValueList != null) count++;
		if (_rparen != null) count++;

		return count;
	}

	/**
	 * @param index the index of a child ASTNode to get
	 * @return the child ASTNode at the given index
	 * @throws IndexOutOfBoundsException when the index is out of bounds
	 */
	public ASTNode getChild(int index) {
		int count = -1;
		if ((_at != null) && (++count == index)) return _at;
		if ((_source != null) && (++count == index)) return _source;
		if ((_lparen != null) && (++count == index)) return _lparen;
		if ((_keyEqualsValueList != null) && (++count == index)) return _keyEqualsValueList;
		if ((_rparen != null) && (++count == index)) return _rparen;

		throw new IndexOutOfBoundsException();
	}
	
	/**
	 * Construct a new Annotation.
	 */
	public Annotation(
		TokenInfo at,
		StringLiteralOrQualifiedID source,
		TokenInfo lparen,
		KeyEqualsValueList keyEqualsValueList,
		TokenInfo rparen
	) {
		super();

		if (at != null) {
			_at = new EmfaticTokenNode(at);
			if (_at._parent != null) throw new RuntimeException();
			_at._parent = this;
		}
		if (source != null) {
			_source = source;
			if (_source._parent != null) throw new RuntimeException();
			_source._parent = this;
		}
		if (lparen != null) {
			_lparen = new EmfaticTokenNode(lparen);
			if (_lparen._parent != null) throw new RuntimeException();
			_lparen._parent = this;
		}
		if (keyEqualsValueList != null) {
			_keyEqualsValueList = keyEqualsValueList;
			if (_keyEqualsValueList._parent != null) throw new RuntimeException();
			_keyEqualsValueList._parent = this;
		}
		if (rparen != null) {
			_rparen = new EmfaticTokenNode(rparen);
			if (_rparen._parent != null) throw new RuntimeException();
			_rparen._parent = this;
		}

	}

	/**
	 * This method overrides the superclass <code>acceptImpl</code> providing
	 * the same implementation.  Here <code>this</code> refers to this specific node
	 * class, so the <code>beginVisit</code> and <code>endVisit</code> methods
	 * specific to this type in the visitor will be invoked.
	 */
	public void acceptImpl(EmfaticASTNodeVisitor visitor) {
		boolean visitChildren = visitor.beginVisit(this);
		if (visitChildren) visitChildren(visitor);
		visitor.endVisit(this);
	}

}
