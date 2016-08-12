package inter.sigale.model;

import java.util.ArrayList;
import java.util.List;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root
public class Phrase {
	
	@Element
	String text;
	
	List<Audio> listAudio = new ArrayList<Audio>();
	
	@Attribute
	public boolean showText;
	
	@ElementList
	public List<Visible> listVisible = new ArrayList<Visible>();
	
	public Phrase() {
		super();
	}
	
	public Phrase(String text) {
		this();
		this.text=text;
	}
	
	public void setText(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	@Override
	public String toString() {
		return text;
	}

	public boolean isShowText() {
		return showText;
	}

	public void setShowText(boolean showText) {
		this.showText = showText;
	}

	public int getEndVisible(int i) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getwStartVisible(int i){
		return 0;
	}
	
	
}
