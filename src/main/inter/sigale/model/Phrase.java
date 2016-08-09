package inter.sigale.model;

import java.util.ArrayList;
import java.util.List;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root
public class Phrase {
	
	@Element
	String text;
	
	List<Audio> listAudio = new ArrayList<Audio>();
	
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
	
	
}
