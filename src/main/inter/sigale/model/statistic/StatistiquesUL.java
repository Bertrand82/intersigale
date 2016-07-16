package inter.sigale.model.statistic;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import inter.sigale.model.Phrase;


@XmlRootElement(name="StatItem")
@XmlSeeAlso({StatistiquesItem.class})
public class StatistiquesUL {
	/**
	 * Pas encore utilisé 
	 */
	private String uniteLexicaleId ="0";
	private List<StatistiquesItem> list = new ArrayList<>();
	
	public StatistiquesUL(){
	}

	public String getResume() {
		
		return " Succes : "+geNbSucces()+" / Total : "+list.size()+" ";
	}

	private int  geNbSucces() {
		int i = 0;
		for (StatistiquesItem item : list){
			if (item.isSucces()){
				i++;
			}
		}
		return i;
	}

	public void add(boolean result) {
		StatistiquesItem item = new StatistiquesItem(result);
		list.add(item);
	}

	public boolean isLastResult(boolean b) {
		if (list.size() == 0){
			return false;
		}
		return list.get(list.size()-1).is(b);
		
	}

	@XmlElement(name="Id")
	public String getUniteLexicaleId() {
		return uniteLexicaleId;
	}

	public void setUniteLexicaleId(String uniteLexicaleId) {
		this.uniteLexicaleId = uniteLexicaleId;
	}

	public List<StatistiquesItem> getList() {
		return list;
	}
	@XmlElement(name="StatItem")
	public void setList(List<StatistiquesItem> list) {
		this.list = list;
	}

}
