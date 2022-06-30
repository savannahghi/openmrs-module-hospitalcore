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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.openmrs.Concept;
import org.openmrs.ConceptAnswer;
import org.openmrs.ConceptSet;
import org.openmrs.api.context.Context;

public class TestTree {

	private ConceptNode root;
	private boolean buildTreeSuccessful = false;
	private Set<Integer> conceptIDSet = new TreeSet<Integer>();
	private List<ConceptNode> flatList = new ArrayList<ConceptNode>();

	/**
	 * Constructor using a lab concept then building the tree of lab and its sub
	 * tests
	 * 
	 * @param labConcept
	 *            concept of the lab
	 */
	public TestTree(Concept labConcept) {
		root = new ConceptNode(labConcept);
		buildTestTree(root);
		buildTreeSuccessful = true;
		flatList = asList();
	}

	/**
	 * Constructor using a lab concept then building the tree of lab and its sub
	 * tests, it should find the concept using the concept name.
	 * 
	 * @param labName
	 *            concept name of the lab
	 */
	public TestTree(String labName) {
		Concept labConcept = Context.getConceptService().getConcept(labName);
		if (labConcept != null) {
			if ((labConcept.getConceptClass().getName().equals("Question"))
					&& (labConcept.getDatatype().getName().equals("Coded"))) {
				root = new ConceptNode(labConcept);
				buildTestTree(root);
				buildTreeSuccessful = true;
				flatList = asList();
			}
		}
	}

	/**
	 * Return true if build tree successfully, otherwise return false
	 * 
	 * @return
	 */
	public boolean buildTreeSuccessful() {
		return buildTreeSuccessful;
	}

	/**
	 * See whether a concept belongs to a lab.
	 * 
	 * @param concept
	 * @return
	 */
	public boolean contains(Concept concept) {
		return conceptIDSet.contains(concept.getId());
	}

	/**
	 * Get the root node of the test tree
	 * 
	 * @return
	 */
	public ConceptNode getRootLab() {
		return root;
	}

	/**
	 * Get the root node of test tree
	 * 
	 * @return
	 */
	public ConceptNode getRootNode() {
		return root;
	}

	/**
	 * Print to console the tree of test
	 */
	public void printTestTree() {
		if (buildTreeSuccessful) {
			printNode(root, 1);
		}
	}

	private void printNode(ConceptNode node, int level) {
		for (int i = 0; i < level; i++) {
			System.out.print("   ");
		}
		System.out.println(node.getConcept().getName().getName());
		for (ConceptNode child : node.getChildNodes()) {
			printNode(child, level + 1);
		}

	}

	private void buildTestTree(ConceptNode node) {
		if (!(node.getConcept().getConceptClass().getName().equals("Test"))
				|| (node.getConcept().getConceptClass().getName()
						.equals("LabSet"))) {
			for (ConceptAnswer ca : node.getConcept().getAnswers()) {
				Concept c = ca.getAnswerConcept();
				ConceptNode child = new ConceptNode(c, node);
				node.getChildNodes().add(child);
				conceptIDSet.add(c.getConceptId());
				buildTestTree(child);
			}

			for (ConceptSet cs : node.getConcept().getConceptSets()) {
				Concept c = cs.getConcept();
				ConceptNode child = new ConceptNode(c, node);
				node.getChildNodes().add(child);
				conceptIDSet.add(c.getConceptId());
				buildTestTree(child);
			}
		}
	}

	/**
	 * Get the concept id set
	 * 
	 * @return
	 */
	public Set<Integer> getConceptIDSet() {
		return conceptIDSet;
	}

	/**
	 * Convert the tree to a list of concept node
	 * 
	 * @return
	 */
	public List<ConceptNode> getTreeAsList() {
		return flatList;
	}

	private List<ConceptNode> asList() {
		List<ConceptNode> list = new ArrayList<ConceptNode>();
		Set<ConceptNode> set = new HashSet<ConceptNode>();
		convertToList(set, root);
		list.addAll(set);
		Collections.sort(list, new Comparator<ConceptNode>() {

			public int compare(ConceptNode o1, ConceptNode o2) {
				return o1.getConcept().getConceptId()
						- o2.getConcept().getConceptId();
			}

		});
		return list;
	}

	private void convertToList(Set<ConceptNode> set, ConceptNode node) {
		set.add(node);
		for (ConceptNode child : node.getChildNodes()) {
			convertToList(set, child);
		}
	}

	/**
	 * Find a node in tree
	 * 
	 * @param concept
	 * @return
	 */
	public ConceptNode findNode(Concept concept) {
		for (int i = 0; i < flatList.size(); i++) {
			ConceptNode node = flatList.get(i);
			if (node.getConcept().getConceptId().equals(concept.getConceptId()))
				return node;
		}
		return null;
	}

	/**
	 * Get the list of ConceptSet
	 * 
	 * @return
	 */
	public Set<Concept> getConceptSet() {
		Set<Concept> set = new HashSet<Concept>();
		for (ConceptNode node : flatList) {
			set.add(node.getConcept());
		}
		return set;
	}
}
