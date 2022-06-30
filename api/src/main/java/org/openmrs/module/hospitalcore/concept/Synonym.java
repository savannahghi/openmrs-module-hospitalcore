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

public class Synonym implements Comparable<Synonym> {
	private String name;
	private String synonym;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSynonym() {
		return synonym;
	}

	public void setSynonym(String synonym) {
		this.synonym = synonym;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((synonym == null) ? 0 : synonym.hashCode());
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
		Synonym other = (Synonym) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (synonym == null) {
			if (other.synonym != null)
				return false;
		} else if (!synonym.equals(other.synonym))
			return false;
		return true;
	}
	
	public int compareTo(Synonym o) {
		String mt = this.getName() + this.getSynonym();
		String ot = this.getName() + this.getSynonym();
		return mt.compareTo(ot);
	}
	
	
	
	
}
