import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Parser {
	// populate classes and member methods of those classes
	public static void classMethodParser(String filename, Graph g) {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(filename));
			String line = reader.readLine();
			while (line != null) {
				if (line.startsWith("contains:")) {
					line = line.substring(9).trim();
					String className = line.split("\\*")[0];
					String methodName = line.split("\\*")[1];
					g.addClass(className);
					try {
						g.addMethod(className, methodName, InvocationType.MEMBER);
					} catch (Exception e) {
//						System.out.println("!" + className + ": " + methodName + "does not exist in graph");
					}
				}				
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// populate methods called by the member methods
	public static void methodMethodParser(String filename, Graph g) {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(filename));
			String line = reader.readLine();
			while (line != null) {
				if (line.startsWith("M:")) {
					line = line.substring(2).trim();
//					System.out.println(line);
					String caller = line.split("\\*")[0];
					String invocationType = line.split("\\*")[1];
					String callee = line.split("\\*")[2];
//					System.out.println(caller + " " + callee);
					try {
						g.addMethod(caller, callee, InvocationType.UNKOWN);
					} catch (Exception e) {
//						System.out.println("!!" + caller + ": " + callee + "does not exist in graph");
					}
				}				
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// populate the clusters
	public static void clusterParser(String filename, Graph g) {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(filename));
			String line = reader.readLine();
			while (line != null) {
				if (line.startsWith("contain ")) {
					line = line.substring(8).trim();
//					System.out.println(line);
					String cluster = line.split(" ")[0];
					String cla = line.split(" ")[1];
					try {
						g.addClassToCluster(cluster, cla);
					} catch (Exception e) {
//						System.out.println("!!!" + cluster + ": " + cla + "does not exist in graph");
					}
				}				
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
