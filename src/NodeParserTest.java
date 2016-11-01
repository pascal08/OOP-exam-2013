import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import org.junit.Test;

public class NodeParserTest {

	
	
	@Test
	public void test_parse_node_successful() {
        String node = "<VERTREK>7:30</VERTREK>";
        
        NodeParser nodeParser = new NodeParser();
        
        assertTrue(nodeParser.parse(node));
	}
	
	@Test
	public void test_parse_node_successful_2() {
        String node = " <VERTREK>7:30</VERTREK> ";
        
        NodeParser nodeParser = new NodeParser();
        
        assertTrue(nodeParser.parse(node));
	}
	
	
	@Test
	public void test_parse_node_failure() {
        String node = "<VERTREK7:30</VERTREK>";
        
        NodeParser nodeParser = new NodeParser();
        
        assertFalse(nodeParser.parse(node));
	}
	
	@Test
	public void test_parse_node_failure_2() {
        String node = "<VERTREK>7:30<VERTREK>";
        
        NodeParser nodeParser = new NodeParser();
        
        assertFalse(nodeParser.parse(node));
	}


}
