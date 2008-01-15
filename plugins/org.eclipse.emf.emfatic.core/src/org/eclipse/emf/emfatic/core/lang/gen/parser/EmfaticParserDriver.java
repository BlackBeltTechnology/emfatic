/**
 * @generated by Gymnast from Emfatic.ast on 09.02.2007 13:03:05
 */

package org.eclipse.emf.emfatic.core.lang.gen.parser;

import java.io.IOException;
import java.io.Reader;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.emfatic.core.generator.ecore.Builder;
import org.eclipse.emf.emfatic.core.generator.ecore.Connector;
import org.eclipse.emf.emfatic.core.generator.ecore.EmfaticSemanticWarning;
import org.eclipse.emf.emfatic.core.lang.gen.ast.CompUnit;
import org.eclipse.gymnast.runtime.core.ast.ASTNode;
import org.eclipse.gymnast.runtime.core.parser.IParser;
import org.eclipse.gymnast.runtime.core.parser.ParseContext;
import org.eclipse.gymnast.runtime.core.parser.ParseError;
import org.eclipse.gymnast.runtime.core.parser.ParseMessage;

public class EmfaticParserDriver implements IParser {

    public ParseContext parse(Reader input) {
    	
    	ExtSimpleCharStream stream = new ExtSimpleCharStream(input);
    	ExtEmfaticParserTokenManager tokenManager = new ExtEmfaticParserTokenManager(stream);
    	EmfaticParser parser = new EmfaticParser(tokenManager);
    	ParseContext parseContext = new ParseContext();
    	CompUnit compUnit = parseCompUnit(parser, parseContext);
    	if (compUnit != null) {
			EPackage rootPackage = addErrorsFromAST(parseContext);
			// for consumption by change listeners (notified by the editor)
			compUnit.setAST(rootPackage);
		}
    	
    	if (parseContext.getMessageCount() == 0) {
			System.out.println("Parse OK!");
		}
		else {
			ParseMessage[] msgs = parseContext.getMessages();
			for (int i = 0; i < msgs.length; i++) {
				// System.err.println(msgs[i].getMessage());
			}
		}
		
		return parseContext;
    }
    
    	private EPackage addErrorsFromAST(ParseContext parseContext) {
		EPackage rootPackage = null;
		// warnigns and errors computed not from the CST but from the AST
		Builder builder = new Builder();
		NullProgressMonitor npm = new NullProgressMonitor();
		ResourceSet resourceSet = new ResourceSetImpl();
		URI uri = URI.createPlatformResourceURI("dummy");
		Resource resource = resourceSet.createResource(uri);
		try {
			builder.build(parseContext, resource, npm);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (!parseContext.hasErrors()) {
			Connector connector = new Connector(builder);
			connector.connect(parseContext, resource, npm);
			rootPackage = (EPackage) resource.getContents().get(0);
			if (rootPackage != null) {
				// invoke EcoreValidator
				Diagnostician diagnostician = new Diagnostician();
				final Diagnostic diagnostic = diagnostician.validate(rootPackage);
				if (diagnostic.getSeverity() == Diagnostic.OK) {
					return rootPackage;
				}
				/*
				 * A tutorial on markers:
				 * 
				 * http://www.eclipse.org/articles/Article-Mark%20My%20Words/mark-my-words.html
				 * 
				 */

				CompUnit compUnit = (CompUnit) parseContext.getParseRoot();
				for (Diagnostic childDiagnostic : diagnostic.getChildren()) {
					Set<ASTNode> problemNodes = new HashSet<ASTNode>();
					String dMsg = childDiagnostic.getMessage();
					if (childDiagnostic.getData().size() > 0) {
						Object primarySourceOfProblem = childDiagnostic.getData().get(0);
						if (primarySourceOfProblem != null && primarySourceOfProblem instanceof EObject) {
							Set<ASTNode> cstUses = compUnit.getEcoreDecl2CstUse().get((EObject) primarySourceOfProblem);
							problemNodes.addAll(cstUses);
							if (problemNodes.size() == 0) {
								problemNodes.add(compUnit.getPackageDecl());
							}
						}
					}
					for (ASTNode problemNode : problemNodes) {
						ParseMessage pMsg = new EmfaticSemanticWarning.EcoreValidatorDiagnostic(problemNode, dMsg);
						parseContext.addParseMessage(pMsg);
					}
				}
			}
		}
		return rootPackage;
	}
    
    private CompUnit parseCompUnit(EmfaticParser parser, ParseContext parseContext) {
    	try {
			CompUnit compUnit = parser.compUnit();
			parseContext.setParseRoot(compUnit);
			return compUnit;
		} catch (ParseException ex) {
			ParseError parseError;
			Token token = ex.currentToken;
			if (token instanceof ExtToken) {
				ExtToken extToken = (ExtToken) token;
				int offset = extToken.tokenOffset;
				String tokenText = token.image;
				int length = (tokenText == null) ? 0 : tokenText.length();
				parseError = new ParseError(ex.getMessage(), offset, length);
			}
			else {
				parseError = new ParseError(ex.getMessage(), 1);
			}
			parseContext.addParseMessage(parseError);
		}
		catch (TokenMgrError ex) {
			ParseError parseError = new ParseError(ex.getMessage(), 1);
			parseContext.addParseMessage(parseError);
		}
		return null; 
    }

}