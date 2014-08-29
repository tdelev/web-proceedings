package org.ictact.webproceedings.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("pa")
public class PaperAttachment extends AAttachment {

}
