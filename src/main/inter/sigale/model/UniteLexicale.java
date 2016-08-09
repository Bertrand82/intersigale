package inter.sigale.model;

import java.util.ArrayList;
import java.util.List;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Transient;

import inter.sigale.model.statistic.StatistiquesUL;
import inter.sigale.util.UtilDivers;

@Root
public class UniteLexicale {

/*	@ElementListUnion({
		   @ElementList(entry="p2", inline=true, type=Phrase.class)	               
	})
*/
	
	@Element
	private Phrase phrase_0 ;
	@Element
	private Phrase phrase_1;
	@Transient
	private List<Vote> listVotes = new ArrayList<Vote>();
	@Transient
	private StatistiquesUL statistique = new StatistiquesUL();
	
	
	
	
	public UniteLexicale() {
		super();
		// TODO Auto-generated constructor stub
	}




	public UniteLexicale(Phrase phrase_0_, Phrase phrase_1_) {
		super();
		phrase_0 = phrase_0_;
		phrase_1 = phrase_1_;
	}




	public boolean resultProcess(String text) {
		String text2 = UtilDivers.removeAccents(text);
		String text2_ref = UtilDivers.removeAccents(getPhrase_1().text);
		boolean result = text2_ref.equalsIgnoreCase(text2);
		this.statistique.add(result);
		return result;
	}



//	@Element(name="phrase_0")
	 public Phrase getPhrase_0() {
		
		return phrase_0;
	}



	public void setPhrase_0(Phrase phrase_0_) {
		phrase_0 = phrase_0_;
	}


//	@Element(name="phrase_1")
	public Phrase getPhrase_1() {
		return phrase_1;
	}


	
	public void setPhrase_1(Phrase phrase_1_) {
		phrase_1 = phrase_1_;
	}






	public List<Vote> getListVotes() {
		return listVotes;
	}



	@Override
	public String toString() {
		return " ---- "+phrase_0+" ---- "+phrase_1;
	}



	public String getStatistiqueResume() {
		
		return this.statistique.getResume();
	}



	public StatistiquesUL getStatistique() {
		String uniteLexicaleId = getId();
		statistique.setUniteLexicaleId(uniteLexicaleId);
		return statistique;
	}



	/**
	 * Id est la phrase a traduire. Ce n'est pas forcement une bonne id�e.
	 * @return
	 */
	public String getId() {		
		return getPhrase_0().getText();
	}




	public void setStatistique(StatistiquesUL statistique) {
		this.statistique = statistique;
	}




	

}
