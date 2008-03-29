/*******************************************************************************
 * Copyright (c) 2004, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.gymnast.generator.core.ast;

import org.eclipse.gymnast.runtime.core.ast.ASTNode;

/**
 *
 * @generated by Gymnast from gymnast.ast on Aug 15, 2004 2:28:15 PM
 */
public class AltRuleBody extends GymnastASTNode  {


	private AltSeq _preSeq;
	private Alts _alts;
	private AltSeq _postSeq;

	public AltSeq getPreSeq() {
		return _preSeq;
	}
	public Alts getAlts() {
		return _alts;
	}
	public AltSeq getPostSeq() {
		return _postSeq;
	}


	/**
	 * @return the number of children of this ASTNode
	 */
	public int getChildCount() {
		int count = 0;
		if (_preSeq != null) count++;
		if (_alts != null) count++;
		if (_postSeq != null) count++;

		return count;
	}

	/**
	 * @param index the index of a child ASTNode to get
	 * @return the child ASTNode at the given index
	 * @throws IndexOutOfBoundsException when the index is out of bounds
	 */
	public ASTNode getChild(int index) {
		int count = -1;
		if ((_preSeq != null) && (++count == index)) return _preSeq;
		if ((_alts != null) && (++count == index)) return _alts;
		if ((_postSeq != null) && (++count == index)) return _postSeq;

		throw new IndexOutOfBoundsException();
	}
	
	/**
	 * Construct a new AltRuleBody.
	 */
	public AltRuleBody(
		AltSeq preSeq,
		Alts alts,
		AltSeq postSeq
	) {
		super();

		if (preSeq != null) {
			_preSeq = preSeq;
			if (_preSeq._parent != null) throw new RuntimeException();
			_preSeq._parent = this;
		}
		if (alts != null) {
			_alts = alts;
			if (_alts._parent != null) throw new RuntimeException();
			_alts._parent = this;
		}
		if (postSeq != null) {
			_postSeq = postSeq;
			if (_postSeq._parent != null) throw new RuntimeException();
			_postSeq._parent = this;
		}

	}

	/**
	 * This method overrides the superclass <code>acceptImpl</code> providing
	 * the same implementation.  Here <code>this</code> refers to this specific node
	 * class, so the <code>beginVisit</code> and <code>endVisit</code> methods
	 * specific to this type in the visitor will be invoked.
	 */
	public void acceptImpl(GymnastASTNodeVisitor visitor) {
		boolean visitChildren = visitor.beginVisit(this);
		if (visitChildren) visitChildren(visitor);
		visitor.endVisit(this);
	}

}