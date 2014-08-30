package org.ictact.webproceedings.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("ca")
public class ConferenceAttachment extends AAttachment {

}
