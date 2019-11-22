import java.util.Arrays;
import java.util.HashSet;

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
		
		
		// use g.overlapRoot("{class1 or method1 or cluster1}", "{class2 or method2 or cluster2}") 
		// to find ROOTs of overlapping subtrees

		
		// use this method to find the overlapping NODES given multiple roots
        String roots[] = {"org.apache.catalina.core.StandardHost",
        		"org.apache.catalina.core.StandardContext",
        		"org.apache.catalina.core.StandardEngine",
        		"org.apache.catalina.core.StandardService",
        		"org.apache.catalina.core.StandardServer"};
        g.overlap_s(new HashSet<String>(Arrays.asList(roots)) );
	}
}
