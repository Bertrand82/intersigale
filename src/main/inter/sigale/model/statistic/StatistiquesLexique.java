package inter.sigale.model.statistic;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;


@XmlRootElement
@XmlSeeAlso({StatistiquesUL.class})
public class StatistiquesLexique {

	private List<StatistiquesUL> listStatistiqueUL = new ArrayList<>();

	public List<StatistiquesUL> getListStatistiqueUL() {
		return listStatistiqueUL;
	}

	@XmlElement(name="UL")
	public void setListStatistiqueUL(List<StatistiquesUL> listStatistiqueUL) {
		this.listStatistiqueUL = listStatistiqueUL;
	}

	@Override
	public String toString() {
		return "StatistiquesLexique [listStatistiqueUL.size =" + listStatistiqueUL.size() +"  "+listStatistiqueUL+ "]";
	}

	public StatistiquesUL getStatistiqueULById(String id) {
		if (id==null){
			id="";
		}
		for(StatistiquesUL sul: listStatistiqueUL){
			if (id.equals(sul.getUniteLexicaleId())){
				return sul;
			}
		}
		return new StatistiquesUL();
	}
	
}