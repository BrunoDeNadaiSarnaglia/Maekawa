import java.util.ArrayList;

/**
 * Created by Bruno on 5/1/2015.
 */
public class Mutex {

    public static int CS_INT;

    public static int NEXT_REQ;

    public static int TOT_EXEC_TIME;

    public static ArrayList<Node> nodes;

    public static ArrayList<Set> sets;

    public static void main(String[] args){
        if(args.length != 4){
            System.out.println("./mutex <cs_int> <next_req> <tot_exec_time> <option>");
            return;
        }
        CS_INT = Integer.parseInt(args[0]);
        NEXT_REQ = Integer.parseInt(args[1]);
        TOT_EXEC_TIME = Integer.parseInt(args[2]);
        nodes = InitializeNodesAndSets.initializeNodes();
        sets = InitializeNodesAndSets.initializeSets(nodes);
        for(Node node : nodes){
            node.setState("request");
        }
    }
}
