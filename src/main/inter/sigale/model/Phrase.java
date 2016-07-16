package inter.sigale.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Phrase {
	String text;
	List<Audio> listAudio = new ArrayList<>();
	
	public Phrase() {
		super();
	}
	
	public Phrase(String text) {
		this();
		this.text=text;
	}
	@XmlElement(name="text")
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
