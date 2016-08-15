package inter.sigale.model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root
public class Visible {
	@Attribute
	private int start=0;
	@Attribute
	private int end=0;
	public Visible() {
		
	}
	
	
	public Visible(int start, int end) {
		super();
		this.start = start;
		this.end = end;
	}


	public int getStart() {
		return Math.min(start, end);
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getEnd() {
		return Math.max(start, end);
	}
	public void setEnd(int end) {
		this.end = end;
	}
	
	
}
