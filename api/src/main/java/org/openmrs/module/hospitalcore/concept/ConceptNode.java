/*
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */


package org.openmrs.module.hospitalcore.concept;

import java.util.Set;
import java.util.TreeSet;

import org.openmrs.Concept;

public class ConceptNode implements Comparable<ConceptNode> {

	private Concept concept;
	private Set<ConceptNode> childNodes = new TreeSet<ConceptNode>();
	private ConceptNode parent;

	public ConceptNode() {

	}

	public ConceptNode(Concept concept) {
		this.concept = concept;
	}

	public ConceptNode(Concept concept, ConceptNode parent) {
		this.concept = concept;
		this.parent = parent;
	}

	public int compareTo(ConceptNode o) {
		String mName = concept.getName().getName();
		String oName = o.getConcept().getName().getName();
		return mName.compareToIgnoreCase(oName);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((concept == null) ? 0 : concept.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ConceptNode other = (ConceptNode) obj;
		if (concept == null) {
			if (other.concept != null)
				return false;
		} else if (!concept.equals(other.concept))
			return false;
		return true;
	}

	public Concept getConcept() {
		return concept;
	}

	public void setConcept(Concept concept) {
		this.concept = concept;
	}

	public Set<ConceptNode> getChildNodes() {
		return childNodes;
	}

	public void setChildNodes(Set<ConceptNode> childNodes) {
		this.childNodes = childNodes;
	}

	public ConceptNode getParent() {
		return parent;
	}

	public void setParent(ConceptNode parent) {
		this.parent = parent;
	}

	@Override
	public String toString() {
		return "ConceptNode [conceptId=" + concept.getConceptId() + "]";
	}
	
	
}
