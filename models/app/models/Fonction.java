package models;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author jcstritt
 */
@Entity
@Table(name = "t_fonction")
@Data
@EqualsAndHashCode(of = "pkFonction", callSuper = false)
public class Fonction implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "pkFonction")
  private Integer pkFonction;

  @Basic(optional = false)
  @Column(name = "nomFonction")
  private String nomFonction;

//  @OneToMany(cascade = CascadeType.ALL, mappedBy = "fonction")
//  @OrderBy("dateEntree ASC")
//  private List<Activite> activites;

  @Override
  public String toString() {
    return nomFonction.toLowerCase();
  }
  
}
