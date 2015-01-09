package main;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jeremygrand on 28/11/2014.
 */
public class Vertex {
    private String name;

    private List<Edge> edges = new ArrayList<Edge>();

    public Vertex(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void connectTo(Vertex target, int distance) {
        edges.add(new Edge(target, distance));
    }

    public boolean isConnectedTo(String target){
        for(Edge it : edges){
            if(it.getTarget().getName()==target){
                return true;
            }
        }
        return false;
    }

    public int distanceToConnected(String target){
        for(Edge it : edges){
            if(it.getTarget().getName()==target){
                return it.getDistance();
            }
        }

        return -1;
    }

    public List<String> getConnectedVertex(){
        List<String> villesConnectees = new ArrayList<String>();
        for(Edge it : edges){
            villesConnectees.add(it.getTarget().getName());
        }
        return villesConnectees;
    }

}
