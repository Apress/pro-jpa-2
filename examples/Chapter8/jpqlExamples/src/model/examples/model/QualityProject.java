package examples.model;

import javax.persistence.Entity;
import javax.persistence.DiscriminatorValue;

@Entity
@DiscriminatorValue("QP")
public class QualityProject extends Project {
}
