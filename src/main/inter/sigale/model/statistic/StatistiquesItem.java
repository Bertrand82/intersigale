package inter.sigale.model.statistic;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.sun.xml.internal.txw2.annotation.XmlAttribute;

@XmlRootElement(name="iItemStat")
public class StatistiquesItem {

	
	
	boolean succes;
	Date date = new Date();
	
	
	
	public StatistiquesItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StatistiquesItem(boolean result_) {
		succes = result_;
	}

	@XmlAttribute
	public boolean isSucces() {
		
		return succes;
	}

	public boolean is(boolean b) {
		return b==succes;
	}

	@XmlAttribute
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
