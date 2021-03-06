package se.edu.inclass;

import se.edu.inclass.data.DataManager;
import se.edu.inclass.task.Deadline;
import se.edu.inclass.task.Task;
import se.edu.inclass.task.TaskNameComparator;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Main {

    private TaskNameComparator taskNameComparator;

    public static void main(String[] args) {
        DataManager dm = new DataManager("./data/data.txt");
        ArrayList<Task> tasksData = dm.loadData();

        System.out.println("Printing deadlines");
        printDeadlines(tasksData);

        System.out.println("Total number of deadlines: " + countDeadlines(tasksData));
        printDeadlinesUsingStreams(tasksData);

        System.out.println(filterByString(tasksData, "11"));
    }

    private static int countDeadlines(ArrayList<Task> tasksData) {
        int count = 0;
        for (Task t : tasksData) {
            if (t instanceof Deadline) {
                count++;
            }
        }
        return count;
    }

    public static void printData(ArrayList<Task> tasksData) {
        tasksData.stream().forEach(System.out::println);
    }


    public static int countDeadlinesWithStreams(ArrayList<Task> taskData) {
        taskData.stream()
                .filter(n -> n instanceof Deadline)
                .count();
    }

    public static void printDeadlines(ArrayList<Task> tasksData) {
        tasksData.stream()
                .filter((n) -> n instanceof Deadline)
                .forEach(System.out::println);
    }

    public static void printDeadlinesUsingStreams(ArrayList<Task> taskData) {
        taskData.stream()
                .filter(n -> n instanceof Deadline)
                .sorted( (a,b) -> a.getDescription().toLowerCase().compareTo(b.getDescription().toLowerCase()))
                .forEach(System.out::println);
    }

    public static ArrayList<Task> filterByString(ArrayList<Task> taskData, String filterString) {
        ArrayList<Task> filteredList = (ArrayList<Task>) taskData.stream().filter(n -> n.getDescription().contains(filterString)).
                collect(Collectors.toList());
        return filteredList;
    }
}
