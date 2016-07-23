package inter.sigale.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlMixed;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import com.sun.xml.internal.txw2.annotation.XmlAttribute;

import inter.sigale.model.statistic.StatistiquesLexique;
import inter.sigale.model.statistic.StatistiquesUL;


@XmlRootElement
@XmlSeeAlso({UniteLexicale.class,Phrase.class})
public class Lexique implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String name ="intersigale-default";
	
	private List<UniteLexicale> listUniteLexicale = new ArrayList<UniteLexicale>();
	
	int iCourrante = 0;
	
	
	public Lexique() {
		super();		
	}
	
	public UniteLexicale next(int n) {
		iCourrante+=n;
		iCourrante = iCourrante % listUniteLexicale.size();
		return getUniteLexicaleCourante();
	}
	
	public UniteLexicale  next() {
		return next(1);
	}

	public UniteLexicale getUniteLexicaleCourante() {
		if (iCourrante >= listUniteLexicale.size()){
			return null;
		}
		return listUniteLexicale.get(iCourrante);
	}

	public void add(UniteLexicale uniteLexicale) {
		this.listUniteLexicale.add(uniteLexicale);
	}

 
	public List<UniteLexicale> getListUniteLexicale() {
		return listUniteLexicale;
	}

	@XmlElement
	public void setListUniteLexicale(List<UniteLexicale> listUniteLexicale) {
		this.listUniteLexicale = listUniteLexicale;
	}

	@Override
	public String toString() {
		String s =  super.toString()+ "  size : "+listUniteLexicale.size();
		for(Object ul : listUniteLexicale){
			s += "\n "+ul;
		}
		return s;
	}

	public int getNbUniteLexicale(boolean b) {
		int i =0;
		for(UniteLexicale ul : listUniteLexicale){
			if (ul.getStatistique().isLastResult(b)) {
				i++;
			};
		}
		return i;
	}
	private StatistiquesLexique statistiquesLexique;
	public void setStatistiquesLexique(StatistiquesLexique statistiquesLexique) {
		this.statistiquesLexique = statistiquesLexique;
		updateStatistiques();
	}

	
	public StatistiquesLexique getStatistiquesLexique() {
		if (statistiquesLexique == null){
			createStatistiqueLexique();
		}
		return statistiquesLexique;
	}

	private void createStatistiqueLexique() {
		this.statistiquesLexique = new StatistiquesLexique();
		for(UniteLexicale ul : listUniteLexicale){
			StatistiquesUL stat = ul.getStatistique();
			statistiquesLexique.getListStatistiqueUL().add(stat);
		}
	}
	
	private void updateStatistiques() {
		for(StatistiquesUL stat : statistiquesLexique.getListStatistiqueUL()){
			
		}
	}

	@XmlAttribute
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getiCourrante() {
		return iCourrante;
	}

	public void setiCourrante(int iCourrante) {
		this.iCourrante = iCourrante;
	}

	
}
