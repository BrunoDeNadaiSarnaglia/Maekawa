import java.util.ArrayList;

/**
 * Created by Bruno on 5/1/2015.
 */
public class Mutex {

    public static int CS_INT;

    public static int NEXT_REQ;

    public static int TOT_EXEC_TIME;

    public static int TIMEOUT_DEADLOCK;

    public static ArrayList<Node> nodes;

    public static ArrayList<Set> sets;

    private static long finishTime;

    public static void main(String[] args){
        if(args.length != 4){
            System.out.println("./mutex <cs_int> <next_req> <tot_exec_time> <option>");
            return;
        }
        CS_INT = Integer.parseInt(args[0]);
        NEXT_REQ = Integer.parseInt(args[1]);
        TOT_EXEC_TIME = Integer.parseInt(args[2]);
//        TIMEOUT_DEADLOCK = 2*Math.max(NEXT_REQ, CS_INT);
        TIMEOUT_DEADLOCK = 1000;
        finishTime = System.currentTimeMillis() + TOT_EXEC_TIME;
        nodes = InitializeNodesAndSets.initializeNodes();
        sets = InitializeNodesAndSets.initializeSets(nodes);
        for(Node node : nodes){
            node.setState("request");
        }
        for (Node node : nodes){
            node.start();
        }
        new Timer().start();
        new DeadLockChecker().start();
    }

    private static class DeadLockChecker extends Thread {
        @Override
        public void run() {
            while(true) {
                if (Node.lastChange + Mutex.TIMEOUT_DEADLOCK < System.currentTimeMillis()) {
                    System.out.println("DeadLock");
                    System.out.println(Node.lastChange);
                    System.out.println(Mutex.TIMEOUT_DEADLOCK);
                    System.out.println(System.currentTimeMillis());
                    int mostVoted = 0;
                    int countVotes = Mutex.nodes.get(0).getVotesCount();
                    for (Node node : nodes) {
                        if (countVotes < node.getVotesCount()) {
                            mostVoted = node.getIdProcess();
                            countVotes = node.getVotesCount();
                        }
                    }
                    Node nodeMostVoted = Mutex.nodes.get(mostVoted);
                    System.out.println("most voted: " + mostVoted);
                    for (int i = 0; i < 9; i++) {
                        System.out.println("i : " + Mutex.nodes.get(i).getVotesCount());
                    }
                    for (Integer id : nodeMostVoted.getSet()) {
                        if (mostVoted != Mutex.nodes.get(id).getLastVote()) {
                            System.out.println(id);
                            int id1 = Mutex.nodes.get(id).getLastVote();
                            Mutex.nodes.get(id1).decreaseVotesCount();
                            Mutex.nodes.get(mostVoted).queueMessage(new Message(id, mostVoted, "yes"));
                            Mutex.nodes.get(id).queueMessage(new Message(id1, id, "require"));
                            Mutex.nodes.get(id).setLastVote(mostVoted);
                            break;
                        }
                    }
                    Node.lastChange = System.currentTimeMillis();
                }
            }
        }
    }

    private static class Timer extends Thread{
        @Override
        public void run(){
            while(true){
                if(finishTime < System.currentTimeMillis()){
                    System.out.println("TIMEOUT");
                    System.exit(0);
                }
            }
        }
    }
}
