import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NodeParser {

	private String nodeType;
	private String nodeValue;
	
	public String getNodeType() {
		return nodeType;
	}

	public String getNodeValue() {
		return nodeValue;
	}

	public boolean parse(String node) {
		Pattern pattern = Pattern.compile("<(.+?)>([^<]*)<\\/(.+?)>");
		Matcher matcher = pattern.matcher(node);
		
		if(matcher.find()) {
			this.nodeType = matcher.group(1);
			this.nodeValue = matcher.group(2);
			
			return true;
		}
		
		return false;
	}
	
}
