
public class Main {
	public static void main(String args[]) {
		System.out.println("Hello W");
		Graph g = new Graph();
		Parser.classMethodParser("call_graph.txt", g);
//		g.bfs("org.apache.tomcat.util.http.MimeHeaders");
		Parser.methodMethodParser("call_graph.txt", g);
		
		Parser.clusterParser("tomcat-1.0.0_acdc_clustered.rsf", g);
//		g.BFS("org.apache.catalina.core.StandardWrapper");
//		g.bfs("org.apache.catalina.core.StandardHost");
//		g.bfs("org.apache.catalina.core.StandardContext");
		g.overlap("org.apache.catalina.core.StandardContext", "org.apache.catalina.core.StandardHost");
	}
}
