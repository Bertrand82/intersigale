package inter.sigale.model.statistic;

import java.util.ArrayList;
import java.util.List;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.ElementUnion;
import org.simpleframework.xml.Root;


@Root

public class StatistiquesLexique {
	@ElementUnion({
        @Element(name="text", type=String.class,required=false),
        @Element(name="int", type=Integer.class,required=false),
        @Element(name="long", type=Long.class,required=false),
        @Element(name="double", type=Double.class,required=false)
     })
	
	@ElementList
	private List<StatistiquesUL> listStatistiqueUL = new ArrayList<StatistiquesUL>();

	public List<StatistiquesUL> getListStatistiqueUL() {
		return listStatistiqueUL;
	}

	
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
