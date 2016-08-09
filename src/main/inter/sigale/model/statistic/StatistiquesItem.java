package inter.sigale.model.statistic;

import java.util.Date;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root
public class StatistiquesItem {

	
	@Attribute
	boolean succes;
	
	@Attribute
	Date date = new Date();
	
	
	
	public StatistiquesItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StatistiquesItem(boolean result_) {
		succes = result_;
	}


	public boolean isSucces() {
		
		return succes;
	}

	public boolean is(boolean b) {
		return b==succes;
	}


	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	
	public void setSucces(boolean succes) {
		this.succes = succes;
	}

}
