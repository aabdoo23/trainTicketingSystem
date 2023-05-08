import javafx.util.Pair;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.*;

public class TrainPathFinder extends JFrame {
    private JComboBox<String> cbSource;
    private JComboBox<String> cbDestination;
    private JButton generateButton;
    private JList<String> trainPathsList;
    private JList<String> trainsList;
    private JButton selectButton;
    private JPanel mainPanel;
    private JButton bookTrainButton;

    public LinkedList<TrainPath> generateAllTrainPaths(Station startStation, Station endStation)  {
        // Create a graph where each station is a node and each train is an edge between two stations
        Map<Station, LinkedList<Train>> graph = new HashMap<>();
        for (Train train : globals.trainLinkedList) {
            Station sourceStation = train.getSourceStation();
            Station destinationStation = train.getDestinationStation();
            graph.computeIfAbsent(sourceStation, k -> new LinkedList<>()).add(train);
            graph.computeIfAbsent(destinationStation, k -> new LinkedList<>()).add(train);
        }

        // Initialize a queue with the starting station and a path of trains starting with an empty LinkedList
        Queue<Pair<Station, LinkedList<Train>>> queue = new LinkedList<>();
        queue.offer(new Pair<>(startStation, new LinkedList<>()));

        // Keep track of the visited stations
        Set<Station> visited = new HashSet<>();
        visited.add(startStation);

        // Keep track of all the possible paths
        LinkedList<TrainPath> paths = new LinkedList<>();

        // Perform a breadth-first search on the graph
        while (!queue.isEmpty()) {
            Pair<Station, LinkedList<Train>> current = queue.poll();
            Station currentStation = current.getKey();
            LinkedList<Train> currentPath = current.getValue();

            // Check all adjacent stations that can be reached by a train from the current station
            for (Train train : graph.get(currentStation)) {
                Station nextStation = (train.getSourceStation() == currentStation) ? train.getDestinationStation() : train.getSourceStation();

                if (!visited.contains(nextStation)) {
                    visited.add(nextStation);
                    LinkedList<Train> nextPath = new LinkedList<>(currentPath);
                    nextPath.add(train);

                    if (nextStation == endStation) {
                        // Found a path from start station to end station
                        TrainPath trainPath = new TrainPath(nextPath);
                        paths.add(trainPath);
                    } else {
                        queue.offer(new Pair<>(nextStation, nextPath));
                    }
                }
            }
        }

        return paths;
    }

    TrainPathFinder(User user){
        setContentPane(mainPanel);
        setTitle("Train path finder");
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setSize(800, 350);
        setLocationRelativeTo(null);
        setVisible(true);
        globals.makeList(globals.stationLinkedList,cbSource);
        globals.makeList(globals.stationLinkedList,cbDestination);


        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LinkedList<TrainPath>trainPaths=generateAllTrainPaths(globals.stationLinkedList.get(cbSource.getSelectedIndex()),globals.stationLinkedList.get(cbDestination.getSelectedIndex()));
                globals.makeList(trainPaths,trainPathsList);
            }
        });
        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LinkedList<TrainPath>trainPaths=generateAllTrainPaths(globals.stationLinkedList.get(cbSource.getSelectedIndex()),globals.stationLinkedList.get(cbDestination.getSelectedIndex()));
                globals.makeList(trainPaths.get(trainPathsList.getSelectedIndex()).getTrains(),trainsList);
            }
        });
        bookTrainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int ind=trainsList.getSelectedIndex();
                LinkedList<TrainPath>trainPaths=generateAllTrainPaths(globals.stationLinkedList.get(cbSource.getSelectedIndex()),globals.stationLinkedList.get(cbDestination.getSelectedIndex()));
                Train train1=trainPaths.get(trainPathsList.getSelectedIndex()).getTrains().get(ind);
                for (Train train:globals.trainLinkedList){
                    if (train==train1){
                        new newReservation(user,train);
                    }
                }
            }
        });
    }
}
