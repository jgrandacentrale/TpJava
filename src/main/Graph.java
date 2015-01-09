package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by jeremygrand on 28/11/2014.
 */
public class Graph {
    private List<Vertex> vertices = new ArrayList<Vertex>();

    public Graph(Vertex... vertices) {
        this.vertices.addAll(Arrays.asList(vertices));
    }

    public int getDistance(String from, String to) {



        if(from==to){
            return 0;
        }

        Vertex ville_from = new Vertex("");
        Vertex ville_to = new Vertex("");
        int distance = -2;


        for (Vertex ville : vertices){
            if(ville.getName()==from){
                ville_from = ville;
                break;
            }
        }

        if(ville_from.getName()==""){
            return -1;
        }

        for (Vertex ville : vertices){
            if(ville.getName()==to){
                ville_to = ville;
                break;
            }
        }
        if(ville_to.getName()==""){
            return -1;
        }


        if(ville_from.isConnectedTo(to)){
            return ville_from.distanceToConnected(to);
        }
        else{

            List<String> villesIntermediaires = ville_from.getConnectedVertex();

            int compareDistance = 99999;

            for(String ville : villesIntermediaires){

                int premierPas = getDistance(from, ville);
                int reste = getDistance(ville,to);


                if(reste >0){
                    compareDistance = Math.min(reste+premierPas,compareDistance);
                }
                distance = compareDistance;

            }
        }


        return distance;
    }
}
