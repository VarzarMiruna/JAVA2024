/*package Bonus;

import Home.Person;
import Home.Driver;
import Home.Destination;
import Home.Passenger;
import org.jgrapht.alg.flow.EdmondsKarpMFImpl;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import java.util.*;
import java.util.stream.Collectors;

public class RandomProblem {
    private int numberOfPersons;
    private int numberOfDestinations;
    private List<Person> persons = new ArrayList<>();
    private List<Destination> destinations = new ArrayList<>();

    public boolean contains( Destination d) {
        for (Destination destination : this.destinations) {
            if (destination.getName().equals(d.getName())) {
                return true;
            }
        }
        return false;
    }

    public RandomProblem(int numberOfPersons, int numberOfDestinations) {
        List<Person> persons = new ArrayList<>();
        List<Person> destinations = new ArrayList<>();
        this.numberOfPersons = numberOfPersons;
        this.numberOfDestinations = numberOfDestinations;
    }

    /**
     * Genereaza niste liste de persoane si destinatii cu preferinte random
     */
  /*  public void randomGenerator() {
        Random random = new Random();
        List<String> newDestinations = new ArrayList<>();
        for (int i = 0; i < numberOfDestinations; i++) {
            newDestinations.add("D" + i);
        }
        this.destinations = newDestinations.stream().map(Destination::new).collect(Collectors.toList());
        Map<String, Set<String>> personPreferences = new HashMap<>();
        for (int i = 0; i < numberOfPersons; i++) {
            Collections.shuffle(newDestinations, random);
            // selectam un nr random intre 1 si nr de proiecte
            int numAdmissibleDestinations = random.nextInt(numberOfDestinations) + 1;
            // le adugam in lista de admissibledestinations
            Set<String> admissibleDestinations = new HashSet<>(newDestinations.subList(0, numAdmissibleDestinations));
            personPreferences.put("P" + i, admissibleDestinations);
            Person person= new person("P" + i);
            person.setAdmissibleDestinations(admissibleDestinations.stream().map(Destination::new).collect(Collectors.toList()));
            this.persons.add(person);
        }
        for (String person: personPreferences.keySet()) {
            Set<String> admissibleDestinations = personPreferences.get(person);
            System.out.println(person+ ": " + admissibleDestinations);
        }

    }

    /**
     * Determines the maximum cardinality matching using a greedy algorithm

     Determine a maximum cardinality set of persons such that any driver and any passenger we should pick from this set, they do not have a common destination.
     */
   /* public void greedyAlgorithm() {
        Map<Person, Destination> allocation = new HashMap<>();
        // sortam Personii dupa nr de proiecte pe care le au in ordine crescatoare
        List<Person> sortedpersons = this.persons.stream()
                .sorted(Comparator.comparingInt(s -> s.getAdmissibleDestinations().size()))
                .collect(Collectors.toList());
        // Personul primeste primul proiect disponibil si apoi scoatem proiectul din lista de
        // proiecte care au rams disponibile

        for (personperson: sortedpersons) {
            for (Destination destination : person.getAdmissibleDestinations()) {
                // verific daca proiectul este in lista de proiecte
                int isThere = 0;
                for (Destination ok : destinations)
                    if (destination.getName().equals(ok.getName()))
                        isThere = 1;
                if (isThere == 1 && !allocation.containsValue(destination)) {
                    allocation.put(person, destination);
                    this.destinations.remove(destination);
                    break;
                }
            }
        }
        //allocation.forEach((key, value) -> System.out.println("personul " + key.getName() + " are alocat proiectul: " + value.getName()));
    }

    /**
     * Determines the maximum cardinality matching using edmondsKarp algorithm
     */
   /* public void edmondsKarp() {
        SimpleDirectedWeightedGraph<String, DefaultWeightedEdge> graph = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);
        Set<String> destinationNames = destinations.stream().map(Destination::getName).collect(Collectors.toSet());
        Set<String> personsNames = persons.stream().map(Person::getName).collect(Collectors.toSet());
        for (String person: personsNames) {
            graph.addVertex(person);
        }
        for (String destination : destinationNames) {
            graph.addVertex(destination);
        }
        graph.addVertex("source");
        graph.addVertex("sink");
        Map<Person, List<String>> prefMap = new HashMap<>();
        for (personperson: persons) {
            prefMap.put(person, person.getAdmissibleDestinations().stream().map(Destination::getName).collect(Collectors.toList()));
        }
        for (String s : personsNames) {
            graph.addVertex(s);
        }
        for (String p : destinationNames) {
            graph.addVertex(p);
        }
        graph.addVertex("source");
        graph.addVertex("sink");
        // adaugam muchiile corespunzatoare de la persons la destinations
        for (persons : prefMap.keySet()) {
            for (String p : prefMap.get(s))
                graph.setEdgeWeight(graph.addEdge(s.getName(), p), 1);
        }
        // de la source la toate destination
        for (String s : personsNames) {
            graph.setEdgeWeight(graph.addEdge("source", s), 1);
        }
        // de la destinations la sink
        for (String p : destinationNames) {
            graph.setEdgeWeight(graph.addEdge(p, "sink"), 1);
        }
        System.out.println(graph.edgeSet());
        EdmondsKarpMFImpl<String, DefaultWeightedEdge> ek = new EdmondsKarpMFImpl<>(graph);
        EdmondsKarpMFImpl.MaximumFlow<DefaultWeightedEdge> maxFlow = ek.getMaximumFlow("source", "sink");
        System.out.println("Maximum cardinality matching:");
        for (DefaultWeightedEdge edge : graph.edgeSet()) {
            if (maxFlow.getFlow(edge) > 0) {
                System.out.println(graph.getEdgeSource(edge) + " - " + graph.getEdgeTarget(edge));
            }
        }
    }
}*/
