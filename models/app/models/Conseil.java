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
@Table(name = "t_conseil")
@Data
@EqualsAndHashCode(of="pkConseil", callSuper=false)
public class Conseil implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "pkConseil")
  private Integer pkConseil;

  @Basic(optional = false)
  @Column(name = "abrev")
  private String abrev;

//  @OneToMany(cascade = CascadeType.ALL, mappedBy = "conseil")
//  @OrderBy("dateEntree ASC")
//  private List<Activite> activites;

  @Override
  public String toString() {
    return abrev;
  }
  
}
