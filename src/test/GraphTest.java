package test;

import main.Vertex;
import main.Graph;
import main.Edge;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


/**
 * Created by jeremygrand on 28/11/2014.
 */
public class GraphTest {
    private Vertex lille = new Vertex("Lille");
    private Vertex paris = new Vertex("Paris");
    private Vertex reims = new Vertex("Reims");
    private Vertex nancy = new Vertex("Nancy");
    private Vertex lyon = new Vertex("Lyon");
    private Vertex marseille = new Vertex("Marseille");
    private Vertex lemans = new Vertex("Le Mans");
    private Vertex nantes = new Vertex("Nantes");
    private Vertex bordeaux = new Vertex("Bordeaux");
    private Vertex toulouse = new Vertex("Toulouse");
    private Vertex clermont = new Vertex("Clermont Ferrant");
    private Vertex montpellier = new Vertex("Montpellier");

    @Before
    public void setup() {
        lille.connectTo(reims, 206);
        lille.connectTo(paris, 222);
        lille.connectTo(nancy, 418);

        reims.connectTo(paris, 144);
        reims.connectTo(nancy, 245);
        reims.connectTo(lyon, 489);

        paris.connectTo(lyon, 465);
        paris.connectTo(lemans, 208);
        paris.connectTo(clermont, 423);

        lyon.connectTo(clermont, 166);
        lyon.connectTo(marseille, 313);
        lyon.connectTo(montpellier, 304);

        lemans.connectTo(nantes, 189);
        lemans.connectTo(bordeaux, 443);

        nantes.connectTo(bordeaux, 347);

        bordeaux.connectTo(toulouse, 243);

        toulouse.connectTo(montpellier, 245);

        montpellier.connectTo(marseille, 169);
        montpellier.connectTo(toulouse, 245);

        marseille.connectTo(montpellier, 169);

        clermont.connectTo(lyon, 166);
        clermont.connectTo(montpellier, 333);
        clermont.connectTo(marseille, 474);
    }

    @Test
    public void getDistanceForTwoAdjacentVertices() {
        Graph graph = new Graph(paris, lyon);

         assertEquals(graph.getDistance("Paris", "Lyon"), 465);
    }

    @Test
    public void getDistanceWithOneIntermediary(){
        Graph graph = new Graph(lille,paris,lyon);

        assertEquals(graph.getDistance("Lille","Paris")+graph.getDistance("Paris","Lyon"),graph.getDistance("Lille","Lyon"));
    }

    @Test
    public void getDistanceWithTwoIntermediaries(){
        Graph graph = new Graph(lille,paris,lyon,marseille);
        assertEquals(222+465+313,graph.getDistance("Lille","Marseille"));
    }

    @Test
    public void getBestDistance(){
        Graph graph = new Graph(lille,reims,paris,lyon,marseille);
        assertEquals(489+313,graph.getDistance("Reims","Marseille"));
        assertEquals(465+313,graph.getDistance("Paris","Marseille"));
        assertTrue(graph.getDistance("Lille", "Reims") + graph.getDistance("Reims", "Marseille") > graph.getDistance("Lille", "Paris") + graph.getDistance("Paris", "Marseille"));
        assertEquals(222 + 465 + 313, graph.getDistance("Lille", "Marseille"));
    }

    @Test
    public void getDistanceToNowhere() {
        Graph graph = new Graph(lille, reims, lyon, nancy);
        assertEquals(-1, graph.getDistance("Lille", "Tokyo"));  //Je renvoie -1 dans le cas où une ville demandée n'est pas dans le graphe.
    }

    @Test
    public void getDistanceNotPossible(){
            Graph graph = new Graph(lille,nancy,paris);
            assertEquals(-2,graph.getDistance("Nancy","Paris"));  //je renvoie -2 dans le cas où la distance n'est pas calculable.
    }

    @Test
    public void getDistanceInNoGraph(){
        Graph graph = new Graph();
        assertEquals(-1,graph.getDistance("Paris","Marseille"));  //Le graphe est vide, du coup, les villes demandées n'y sont pas : erreur -1.
    }
}
