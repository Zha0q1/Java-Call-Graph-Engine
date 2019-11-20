
public class Main {
	public static void main(String args[]) {
		System.out.println("Hello W");
		// do not touch these
		Graph g = new Graph();
		Parser.classMethodParser("call_graph.txt", g);
		Parser.methodMethodParser("call_graph.txt", g);
		Parser.clusterParser("tomcat-1.0.0_acdc_clustered.rsf", g);
		Parser.clusterParser("tomcat1.0.0_383_topics_287_arc_clusters.rsf", g);
		
		
		// use g.bfs("{class/method/cluster}") to run bfs 
		// use g.overlap("{class1/method1/cluster1}", "{class2/method2/cluster2}") to find overlap
		
		g.bfs("org.apache.catalina.core.StandardHost");
//		g.bfs("org.apache.catalina.core.StandardContext");
        g.overlap("org.apache.catalina.core.StandardContext", "org.apache.catalina.core.StandardHost");
		//g.overlap("258", "286");
	}
}
