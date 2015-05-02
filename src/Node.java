import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Bruno on 5/1/2015.
 */
public class Node implements Runnable {

    private int id;
    private String state;
    private Set set;
    private LinkedList<String> messageQueue = new LinkedList<String>();

    public Node(int id) {
        this.id = id;
        state = "Init";
    }

    public void setSet(Set set) {
        this.set = set;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public void run() {

    }

    private class listener implements Runnable{

        @Override
        public void run() {
            
        }
    }
}
