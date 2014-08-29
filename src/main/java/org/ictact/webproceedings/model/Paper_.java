package org.ictact.webproceedings.model;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Paper.class)
public class Paper_ {

	public static volatile SingularAttribute<Paper, String> title;
	
	public static volatile SingularAttribute<Paper, Conference> conference;
	
	public static volatile SingularAttribute<Paper, PaperType> type;
	
}
