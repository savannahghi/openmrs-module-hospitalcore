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


package org.openmrs.module.hospitalcore.impl;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Concept;
import org.openmrs.ConceptAnswer;
import org.openmrs.ConceptClass;
import org.openmrs.ConceptDatatype;
import org.openmrs.ConceptDescription;
import org.openmrs.ConceptMap;
import org.openmrs.ConceptName;
import org.openmrs.ConceptNameTag;
import org.openmrs.ConceptSource;
import org.openmrs.Encounter;
import org.openmrs.EncounterType;
import org.openmrs.GlobalProperty;
import org.openmrs.Location;
import org.openmrs.Obs;
import org.openmrs.Patient;

import org.openmrs.Person;
import org.openmrs.PersonAddress;
import org.openmrs.PersonAttribute;
import org.openmrs.PersonAttributeType;
import org.openmrs.User;
import org.openmrs.api.APIException;
import org.openmrs.api.ConceptService;
import org.openmrs.api.context.Context;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.hospitalcore.HospitalCoreService;
import org.openmrs.module.hospitalcore.concept.ConceptModel;
import org.openmrs.module.hospitalcore.concept.Mapping;
import org.openmrs.module.hospitalcore.concept.Synonym;
import org.openmrs.module.hospitalcore.db.HospitalCoreDAO;
import org.openmrs.module.hospitalcore.model.CoreForm;
import org.openmrs.module.hospitalcore.model.EhrDepartment;
import org.openmrs.module.hospitalcore.model.EhrHospitalWaiver;
import org.openmrs.module.hospitalcore.model.OpdTestOrder;
import org.openmrs.module.hospitalcore.model.PatientCategoryDetails;
import org.openmrs.module.hospitalcore.model.PatientSearch;
import org.openmrs.module.hospitalcore.model.PatientServiceBillItem;
import org.openmrs.module.hospitalcore.util.HospitalCoreConstants;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class HospitalCoreServiceImpl extends BaseOpenmrsService implements
		HospitalCoreService {

	private Log log = LogFactory.getLog(this.getClass());

	public HospitalCoreServiceImpl() {
	}

	protected HospitalCoreDAO dao;

	public List<Obs> listObsGroup(Integer personId, Integer conceptId,
			Integer min, Integer max) throws APIException {
		return dao.listObsGroup(personId, conceptId, min, max);
	}

	private Concept insertConcept(ConceptService conceptService,
			String dataTypeName, String conceptClassName, String concept) {
		try {
			ConceptDatatype datatype = Context.getConceptService()
					.getConceptDatatypeByName(dataTypeName);
			ConceptClass conceptClass = conceptService
					.getConceptClassByName(conceptClassName);
			Concept con = conceptService.getConcept(concept);
			if (con == null) {
				con = new Concept();
				ConceptName name = new ConceptName(concept, Context.getLocale());
				con.addName(name);
				con.setDatatype(datatype);
				con.setConceptClass(conceptClass);
				return conceptService.saveConcept(con);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void creatConceptQuestionAndAnswer(ConceptService conceptService,
			User user, String conceptParent, String... conceptChild) {
		Concept concept = conceptService.getConcept(conceptParent);
		if (concept == null) {
			insertConcept(conceptService, "Coded", "Question", conceptParent);
		}
		if (concept != null) {

			for (String hn : conceptChild) {
				insertHospital(conceptService, hn);
			}
			addConceptAnswers(concept, conceptChild, user);
		}
	}

	private void addConceptAnswers(Concept concept, String[] answerNames,
			User creator) {
		Set<Integer> currentAnswerIds = new HashSet<Integer>();
		for (ConceptAnswer answer : concept.getAnswers()) {
			currentAnswerIds.add(answer.getAnswerConcept().getConceptId());
		}
		boolean changed = false;
		for (String answerName : answerNames) {
			Concept answer = Context.getConceptService().getConcept(answerName);
			if (!currentAnswerIds.contains(answer.getConceptId())) {
				changed = true;
				ConceptAnswer conceptAnswer = new ConceptAnswer(answer);
				conceptAnswer.setCreator(creator);
				concept.addAnswer(conceptAnswer);
			}
		}
		if (changed) {
			Context.getConceptService().saveConcept(concept);
		}
	}

	private Concept insertHospital(ConceptService conceptService,
			String hospitalName) {
		try {
			ConceptDatatype datatype = Context.getConceptService()
					.getConceptDatatypeByName("N/A");
			ConceptClass conceptClass = conceptService
					.getConceptClassByName("Misc");
			Concept con = conceptService.getConceptByName(hospitalName);
			// System.out.println(con);
			if (con == null) {
				con = new Concept();
				ConceptName name = new ConceptName(hospitalName,
						Context.getLocale());
				con.addName(name);
				con.setDatatype(datatype);
				con.setConceptClass(conceptClass);
				return conceptService.saveConcept(con);
			}
			return con;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public EncounterType insertEncounterTypeByKey(String type)
			throws APIException {
		EncounterType encounterType = null;
		try {
			GlobalProperty gp = Context.getAdministrationService()
					.getGlobalPropertyObject(type);
			encounterType = Context.getEncounterService().getEncounterType(
					gp.getPropertyValue());
			if (encounterType == null) {
				encounterType = new EncounterType(gp.getPropertyValue(), "");
				encounterType = Context.getEncounterService()
						.saveEncounterType(encounterType);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return encounterType;
	}

	/**
	 * 
	 * @param type
	 * @throws APIException
	 */
	/*
	 * public EncounterType insertEncounterTypeByKey(String type) throws
	 * APIException { EncounterType encounterType = null; try { GlobalProperty
	 * gp = Context.getAdministrationService().getGlobalPropertyObject(type);
	 * encounterType =
	 * Context.getEncounterService().getEncounterType(gp.getPropertyValue()); if
	 * (encounterType == null) { encounterType = new
	 * EncounterType(gp.getPropertyValue(), "");
	 * encounterType=Context.getEncounterService
	 * ().saveEncounterType(encounterType); } } catch (Exception e) {
	 * e.printStackTrace(); } return encounterType; }
	 * 
	 * public void addConceptAnswers(ConceptService conceptService, Concept
	 * concept, String[] answerNames, User creator) throws APIException {
	 * Set<Integer> currentAnswerIds = new HashSet<Integer>(); for
	 * (ConceptAnswer answer : concept.getAnswers()) {
	 * currentAnswerIds.add(answer.getAnswerConcept().getConceptId()); } boolean
	 * changed = false;
	 * 
	 * for (String answerName : answerNames) {
	 * System.out.println("======================== CJUE: "+answerName);
	 * insertAnswerConcept(Context.getConceptService(), answerName);
	 * System.out.println("========OUT================ CJUE "+answerName); }
	 * 
	 * for (String answerName : answerNames) {
	 * System.out.println("answerName in== : "+answerName); Concept answer =
	 * Context.getConceptService().getConcept(answerName); if
	 * (!currentAnswerIds.contains(answer.getConceptId())) { changed = true;
	 * System.out.println("co nhay vao day khong "+answer); ConceptAnswer
	 * conceptAnswer = new ConceptAnswer(answer);
	 * conceptAnswer.setCreator(creator); concept.addAnswer(conceptAnswer); } }
	 * if (changed) { System.out.println("nhay vao tao cai concept: ");
	 * Context.getConceptService().saveConcept(concept); } }
	 * 
	 * private Concept insertAnswerConcept(ConceptService conceptService, String
	 * hospitalName) { try { ConceptDatatype datatype =
	 * Context.getConceptService() .getConceptDatatypeByName("N/A");
	 * ConceptClass conceptClass = conceptService
	 * .getConceptClassByName("Misc"); Concept con =
	 * conceptService.getConceptByName(hospitalName); //
	 * System.out.println(con); if (con == null) { con = new Concept();
	 * ConceptName name = new ConceptName(hospitalName, Context.getLocale());
	 * con.addName(name); con.setDatatype(datatype);
	 * con.setConceptClass(conceptClass); return
	 * conceptService.saveConcept(con); } return con; } catch (Exception e) {
	 * e.printStackTrace(); } return null; }
	 * 
	 * public HospitalCoreDAO getDao() { return dao; }
	 * 
	 * public void setDao(HospitalCoreDAO dao) { this.dao = dao; }
	 *//**
	 * Insert a concept unless it exists
	 * 
	 * @param dataTypeName
	 * @param conceptClassName
	 * @param conceptName
	 * @return found concept or created
	 */
	/*
	 * public Concept insertConceptUnlessExist(String dataTypeName, String
	 * conceptClassName, String conceptName) { Concept con = null; try {
	 * ConceptService conceptService = Context.getConceptService();
	 * ConceptDatatype datatype =
	 * Context.getConceptService().getConceptDatatypeByName(dataTypeName);
	 * System.out.println("me datatype: "+datatype); ConceptClass conceptClass =
	 * conceptService .getConceptClassByName(conceptClassName);
	 * System.out.println("me conceptclass: "+conceptClass); con =
	 * conceptService.getConceptByName(conceptName);
	 * System.out.println("be4 con: "+con); if (con == null) { con = new
	 * Concept(); ConceptName name = new
	 * ConceptName(conceptName,Context.getLocale()); con.addName(name);
	 * con.setDatatype(datatype); con.setConceptClass(conceptClass);
	 * System.out.println("con after: datatype: "+con.getDatatype());
	 * System.out.println("con after: conceptClass: "+conceptClass);
	 * //con.setDateCreated(new Date()); Concept ccccc
	 * =conceptService.saveConcept(con); System.out.println("cccccc; "+ccccc);
	 * return ccccc; } } catch (Exception e) { e.printStackTrace(); } return
	 * con; }
	 */
	/**
	 * Create the global obs for a patient
	 *
	 */
	public Concept insertConceptUnlessExist(String dataTypeName,
			String conceptClassName, String conceptName) throws APIException {
		Concept con = null;
		try {
			ConceptService conceptService = Context.getConceptService();
			ConceptDatatype datatype = Context.getConceptService()
					.getConceptDatatypeByName(dataTypeName);
			// System.out.println("me datatype: "+datatype);
			ConceptClass conceptClass = conceptService
					.getConceptClassByName(conceptClassName);
			// System.out.println("me conceptclass: "+conceptClass);
			con = conceptService.getConceptByName(conceptName);
			// System.out.println("be4 con: "+con);
			if (con == null) {
				con = new Concept();
				ConceptName name = new ConceptName(conceptName,
						Context.getLocale());
				con.addName(name);
				con.setDatatype(datatype);
				con.setConceptClass(conceptClass);
				// System.out.println("con after: datatype: "+con.getDatatype());
				// System.out.println("con after: conceptClass: "+conceptClass);
				// con.setDateCreated(new Date());
				Concept ccccc = conceptService.saveConcept(con);
				// System.out.println("cccccc; "+ccccc);
				return ccccc;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public Obs createObsGroup(Patient patient, String properyKey) {
		String opdVisitConceptName = Context.getAdministrationService()
				.getGlobalProperty(properyKey);
		if (!StringUtils.isBlank(opdVisitConceptName)) {
			Concept concept = insertConceptUnlessExist("N/A", "Misc",
					opdVisitConceptName);
			Obs obs = new Obs();
			obs.setPerson(patient);
			obs.setConcept(concept);
			obs.setDateCreated(new Date());
			obs.setObsDatetime(new Date());
			obs.setLocation(new Location(1));
			return Context.getObsService().saveObs(obs,
					"Global obs for " + patient.getPersonName().getGivenName());
		}
		return null;
	}

	/**
	 * Get global obs for a patient
	 * 
	 * @param patient
	 * @return
	 */
	public Obs getObsGroup(Patient patient) {
		String name = Context.getAdministrationService().getGlobalProperty(
				HospitalCoreConstants.PROPERTY_OBSGROUP);
		Obs obs = null;
		try {
			Concept concept = Context.getConceptService()
					.getConceptByName(name);
			List<Obs> obses = listObsGroup(patient.getPersonId(),
					concept.getConceptId(), 0, 1);
			obs = CollectionUtils.isEmpty(obses) ? null : obses.get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return obs;
	}

	public Obs getObsGroupCurrentDate(Integer personId) throws APIException {
		String name = Context.getAdministrationService().getGlobalProperty(
				HospitalCoreConstants.PROPERTY_OBSGROUP);
		Concept concept = Context.getConceptService().getConceptByName(name);
		// TODO Auto-generated method stub
		return dao.getObsGroupCurrentDate(personId, concept.getConceptId());
	}

	public HospitalCoreDAO getDao() {
		return dao;
	}

	public void setDao(HospitalCoreDAO dao) {
		this.dao = dao;
	}

	/**
	 * Insert a synonym to an existing concept.
	 * 
	 * @param concept
	 * @param name
	 */
	public void insertSynonym(Concept concept, String name) {
		Locale loc = new Locale("en");
		ConceptName conceptName = new ConceptName(name, loc);
		ConceptNameTag tag = Context.getConceptService()
				.getConceptNameTagByName("synonym");
		conceptName.addTag(tag);
		conceptName.setDateCreated(new Date());
		conceptName.setCreator(Context.getAuthenticatedUser());
		concept.addName(conceptName);
		Context.getConceptService().saveConcept(concept);
	}

	/**
	 * Insert a synonym to an existing concept.
	 * 
	 * @param concept
	 * @param sourceCode
	 */
	public void insertMapping(Concept concept, String sourceName,
			String sourceCode) {
		ConceptSource conceptSource = Context.getConceptService()
				.getConceptSourceByName(sourceName);
		List<ConceptMap> conceptMaps = new ArrayList<ConceptMap>();
		conceptMaps.addAll(concept.getConceptMappings());

		boolean found = false;
		for (ConceptMap cm : concept.getConceptMappings()) {
			if (cm.getConceptReferenceTerm().getConceptSource().equals(conceptSource))
				if (cm.getConceptReferenceTerm().getCode().equalsIgnoreCase(sourceCode)) {
					found = true;
					break;
				}
		}

		if (!found) {
			ConceptMap conceptMap = new ConceptMap();
			conceptMap.setConcept(concept);
			conceptMap.getConceptReferenceTerm().setConceptSource(conceptSource);
			conceptMap.getConceptReferenceTerm().setCode(sourceCode);
			conceptMap.setDateCreated(new Date());
			conceptMap.setCreator(Context.getAuthenticatedUser());
			concept.addConceptMapping(conceptMap);
			Context.getConceptService().saveConcept(concept);
		}
	}

	public Concept insertConcept(String dataTypeName, String conceptClassName,
			String name, String shortname, String description)
			throws APIException {
		Concept con = null;
		try {
			ConceptService conceptService = Context.getConceptService();

			con = conceptService.getConceptByName(name);
			if (con == null) {
				con = new Concept();
				Locale loc = new Locale("en");

				// Add concept name
				con = addName(con, name.toUpperCase(), loc);

				// Add concept shortname
				con = addName(con, shortname.toUpperCase(), loc);

				// Add description
				ConceptDescription conceptDescription = new ConceptDescription(
						description, loc);
				con.addDescription(conceptDescription);

				// Add datatype
				ConceptDatatype conceptDatatype = Context.getConceptService()
						.getConceptDatatypeByName(dataTypeName);
				con.setDatatype(conceptDatatype);

				// add conceptClass
				ConceptClass conceptClass = conceptService
						.getConceptClassByName(conceptClassName);
				con.setConceptClass(conceptClass);

				return conceptService.saveConcept(con);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	private Concept addName(Concept concept, String name, Locale loc) {
		if (!StringUtils.isBlank(name)) {
			ConceptName conceptName = new ConceptName(name, loc);
			// ConceptNameTag tag = Context.getConceptService()
			// .getConceptNameTagByName(type);
			// conceptName.addTag(tag);
			conceptName.setDateCreated(new Date());
			conceptName.setCreator(Context.getAuthenticatedUser());
			concept.addName(conceptName);
		}
		return concept;
	}

	public Integer importConcepts(InputStream diagnosisStream,
			InputStream mappingStream, InputStream synonymStream)
			throws XPathExpressionException, ParserConfigurationException,
			SAXException, IOException {
		Integer diagnosisNo = 0;
		Set<ConceptModel> concepts = new HashSet<ConceptModel>();
		Set<Mapping> mapping = new HashSet<Mapping>();
		Set<Synonym> synonym = new HashSet<Synonym>();
		if (diagnosisStream != null) {
			concepts = parseDiagnosis(diagnosisStream);
		}
		if (mappingStream != null) {
			mapping = parseMapping(mappingStream);
		}
		if (synonymStream != null) {
			synonym = parseSynonym(synonymStream);
		}

		List<ConceptModel> conceptModels = merge(concepts, mapping, synonym);

		System.out.println("NUMBER OF CONCEPTS + " + conceptModels.size());
		diagnosisNo = dao.buildConcepts(conceptModels);
		return diagnosisNo;
	}

	private Set<ConceptModel> parseDiagnosis(InputStream stream)
			throws ParserConfigurationException, SAXException, IOException,
			XPathExpressionException {
		Set<ConceptModel> concepts = new TreeSet<ConceptModel>();
		DocumentBuilderFactory domFactory = DocumentBuilderFactory
				.newInstance();
		domFactory.setNamespaceAware(true);
		DocumentBuilder builder = domFactory.newDocumentBuilder();
		Document doc = builder.parse(stream);

		NodeList rows = doc.getFirstChild().getChildNodes();
		for (int i = 0; i < rows.getLength(); i++) {
			Node row = rows.item(i);
			if (row.getNodeName().equalsIgnoreCase("row")) {
				ConceptModel cm = new ConceptModel();
				NodeList fields = row.getChildNodes();
				for (int j = 0; j < fields.getLength(); j++) {
					Node field = fields.item(j);
					NamedNodeMap attributes = field.getAttributes();
					if (field.getNodeName().equalsIgnoreCase("field")) {
						String type = attributes.getNamedItem("name")
								.getTextContent();
						String value = field.getTextContent();

						if ("name".equalsIgnoreCase(type)) {
							cm.setName(value);
						}

						if ("description".equalsIgnoreCase(type)) {
							cm.setDescription(value);
						}
					}
				}
				concepts.add(cm);
			}
		}
		return concepts;
	}

	private Set<Mapping> parseMapping(InputStream stream)
			throws ParserConfigurationException, SAXException, IOException,
			XPathExpressionException {
		Set<Mapping> mappings = new TreeSet<Mapping>();
		DocumentBuilderFactory domFactory = DocumentBuilderFactory
				.newInstance();
		domFactory.setNamespaceAware(true);
		DocumentBuilder builder = domFactory.newDocumentBuilder();
		Document doc = builder.parse(stream);

		NodeList rows = doc.getFirstChild().getChildNodes();
		for (int i = 0; i < rows.getLength(); i++) {
			Node row = rows.item(i);
			if (row.getNodeName().equalsIgnoreCase("row")) {
				Mapping cm = new Mapping();
				NodeList fields = row.getChildNodes();
				for (int j = 0; j < fields.getLength(); j++) {
					Node field = fields.item(j);
					NamedNodeMap attributes = field.getAttributes();
					if (field.getNodeName().equalsIgnoreCase("field")) {
						String type = attributes.getNamedItem("name")
								.getTextContent();
						String value = field.getTextContent();

						if ("name".equalsIgnoreCase(type)) {
							cm.setName(value);
						}

						if ("source_code".equalsIgnoreCase(type)) {
							cm.setSourceCode(value);
						}

						if ("source_name".equalsIgnoreCase(type)) {
							cm.setSource(value);
						}
					}
				}
				mappings.add(cm);
			}
		}
		return mappings;
	}

	private Set<Synonym> parseSynonym(InputStream stream)
			throws ParserConfigurationException, SAXException, IOException,
			XPathExpressionException {
		Set<Synonym> synnonyms = new TreeSet<Synonym>();
		DocumentBuilderFactory domFactory = DocumentBuilderFactory
				.newInstance();
		domFactory.setNamespaceAware(true);
		DocumentBuilder builder = domFactory.newDocumentBuilder();
		Document doc = builder.parse(stream);

		NodeList rows = doc.getFirstChild().getChildNodes();
		for (int i = 0; i < rows.getLength(); i++) {
			Node row = rows.item(i);
			if (row.getNodeName().equalsIgnoreCase("row")) {
				Synonym cm = new Synonym();
				NodeList fields = row.getChildNodes();
				for (int j = 0; j < fields.getLength(); j++) {
					Node field = fields.item(j);
					NamedNodeMap attributes = field.getAttributes();
					if (field.getNodeName().equalsIgnoreCase("field")) {
						String type = attributes.getNamedItem("name")
								.getTextContent();
						String value = field.getTextContent();

						if ("concept".equalsIgnoreCase(type)) {
							cm.setName(value);
						}

						if ("synonym".equalsIgnoreCase(type)) {
							cm.setSynonym(value);
						}
					}
				}
				synnonyms.add(cm);
			}
		}
		return synnonyms;
	}

	private List<ConceptModel> merge(Set<ConceptModel> conceptSet,
			Set<Mapping> mappingSet, Set<Synonym> synonymSet) {
		List<ConceptModel> conceptList = new ArrayList<ConceptModel>();
		conceptList.addAll(conceptSet);
		for (Mapping mapping : mappingSet) {
			int index = indexOf(conceptList, mapping.getName());
			if (index >= 0) {
				ConceptModel concept = conceptList.get(index);
				concept.getMappings().add(mapping);
			}
		}

		for (Synonym synonym : synonymSet) {
			int index = indexOf(conceptList, synonym.getName());
			if (index >= 0) {
				ConceptModel concept = conceptList.get(index);
				concept.getSynonyms().add(synonym.getSynonym());
			}
		}

		return conceptList;
	}

	private int indexOf(List<ConceptModel> conceptList, String name) {
		ConceptModel concept = new ConceptModel();
		concept.setName(name);
		return Collections.binarySearch(conceptList, concept);
	}

	public List<Patient> searchPatient(String nameOrIdentifier, String gender,
			int age, int rangeAge, String date, int rangeDay,
			String relativeName) throws APIException {
		return dao.searchPatient(nameOrIdentifier, gender, age, rangeAge, date,
				rangeDay, relativeName);
	}

	public List<Patient> searchPatient(String nameOrIdentifier, String gender, int age, int rangeAge, String lastDayOfVisit,
									   int lastVisit, String relativeName, String maritalStatus, String phoneNumber,
									   String nationalId, String fileNumber) throws APIException {
		return dao.searchPatient(nameOrIdentifier, gender, age, rangeAge, lastDayOfVisit, lastVisit, relativeName,
				maritalStatus, phoneNumber, nationalId, fileNumber);
	}

	public List<Patient> searchPatient(String hql) {
		return dao.searchPatient(hql);
	}

	public BigInteger getPatientSearchResultCount(String hql) {
		return dao.getPatientSearchResultCount(hql);
	}

	public List<PersonAttribute> getPersonAttributes(Integer patientId) {
		return dao.getPersonAttributes(patientId);
	}

	public Encounter getLastVisitEncounter(Patient patient,
			List<EncounterType> types) {
		return dao.getLastVisitEncounter(patient, types);
	}
	
	/**
	 * Save core form
	 * 
	 * @param form
	 * @return
	 */
	public CoreForm saveCoreForm(CoreForm form) {
		return dao.saveCoreForm(form);
	}

	/**
	 * Get core form by id
	 * 
	 * @param id
	 * @return
	 */
	public CoreForm getCoreForm(Integer id) {
		return dao.getCoreForm(id);
	}

	/**
	 * Get core forms by name
	 * 
	 * @param conceptName
	 * @return
	 */
	public List<CoreForm> getCoreForms(String conceptName) {
		return dao.getCoreForms(conceptName);
	}

	/**
	 * Get all core forms
	 * 
	 * @return
	 */
	public List<CoreForm> getCoreForms(){
		return dao.getCoreForms();
	}

	/**
	 * Delete core form
	 * 
	 * @param form
	 */
	public void deleteCoreForm(CoreForm form){
		dao.deleteCoreForm(form);
	}
	
	/**
	 * Save patientSearch
	 */
	public PatientSearch savePatientSearch(PatientSearch patientSearch){
		return dao.savePatientSearch(patientSearch);
	}

	/**
	 * 
	 * @see org.openmrs.module.hospitalcore.HospitalCoreService#getLastVisitTime(Patient)
	 */
	public java.util.Date getLastVisitTime(Patient patientID) {
	    return dao.getLastVisitTime(patientID);
    }
	
	//ghanshyam 3-june-2013 New Requirement #1632 Orders from dashboard must be appear in billing queue.User must be able to generate bills from this queue
	public PatientSearch getPatientByPatientId(int patientId){
		return dao.getPatientByPatientId(patientId);
	}
	
	public PatientSearch getPatient(int patientID){
		return dao.getPatient(patientID);
	}
	
	public List<Obs> getObsByEncounterAndConcept(Encounter encounter,Concept concept){
		return dao.getObsByEncounterAndConcept(encounter,concept);
	}
	
	public PersonAddress getPersonAddress(Person person){
		return dao.getPersonAddress(person);
	}
	
	public OpdTestOrder getOpdTestOrder(Integer opdOrderId){
		return dao.getOpdTestOrder(opdOrderId);
	}
	
	public PersonAttributeType getPersonAttributeTypeByName(String attributeName){
		return dao.getPersonAttributeTypeByName(attributeName);
	}
	
	public Obs getObs(Person person,Encounter encounter){
		return dao.getObs(person,encounter);
	}
	
	public String getPatientType(Patient patientId){
		return dao.getPatientType(patientId);
	}
	
	public List<Obs> getObsInstanceForDiagnosis(Encounter encounter,Concept concept){
		return dao.getObsInstanceForDiagnosis(encounter,concept);
	}

	@Override
	public List<OpdTestOrder> getAllOpdOrdersByDateRange(boolean today,String fromDate,String toDate) {
		return dao.getAllOpdOrdersByDateRange(today,fromDate,toDate);
	}

	@Override
	public List<PatientServiceBillItem> getAllPatientServiceBillItemsByDate(boolean today,String fromDate, String toDate) {
		return dao.getAllPatientServiceBillItemsByDate(today,fromDate,toDate);
	}

	@Override
	public PatientCategoryDetails savePatientCategoryDetails(PatientCategoryDetails patientCategoryDetails) throws APIException {
		return dao.savePatientCategoryDetails(patientCategoryDetails);
	}

	@Override
	public PatientCategoryDetails getPatientCategoryDetailsById(Integer patientDetailsId) throws APIException {
		return dao.getPatientCategoryDetailsById(patientDetailsId);
	}

	@Override
	public PatientCategoryDetails getPatientCategoryDetailsByPatient(Patient patient) throws APIException {
		return dao.getPatientCategoryDetailsByPatient(patient);
	}

	@Override
	public List<PatientCategoryDetails> getAllPatientCategoryDetails(String property, String value, Date startDate, Date endDate) throws APIException {
		return dao.getAllPatientCategoryDetails(property, value, startDate, endDate);
	}

	@Override
	public EhrDepartment saveDepartment(EhrDepartment ehrDepartment) throws APIException {
		return dao.saveDepartment(ehrDepartment);
	}

	@Override
	public EhrDepartment getDepartmentById(Integer departmentId) throws APIException {
		return dao.getDepartmentById(departmentId);
	}

	@Override
	public EhrDepartment getDepartmentByName(String departmentName) throws APIException {
		return dao.getDepartmentByName(departmentName);
	}

	@Override
	public List<EhrDepartment> getAllDepartment() throws APIException {
		return dao.getAllDepartment();
	}

	@Override
	public List<PatientServiceBillItem> getPatientServiceBillByDepartment(EhrDepartment ehrDepartment, Date startDate, Date endDate) throws APIException {
		return dao.getPatientServiceBillByDepartment(ehrDepartment, startDate, endDate);
	}

	@Override
	public EhrHospitalWaiver saveEhrHospitalWaiver(EhrHospitalWaiver ehrHospitalWaiver) throws APIException {
		return dao.saveEhrHospitalWaiver(ehrHospitalWaiver);
	}

	@Override
	public EhrHospitalWaiver getEhrHospitalWaiverById(Integer waiverId) throws APIException {
		return dao.getEhrHospitalWaiverById(waiverId);
	}

	@Override
	public List<EhrHospitalWaiver> getAllEhrHospitalWaiver(Date startDate, Date endDate) throws APIException {
		return dao.getAllEhrHospitalWaiver(startDate, endDate);
	}

}
