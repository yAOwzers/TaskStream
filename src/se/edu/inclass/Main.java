package se.edu.inclass;

import se.edu.inclass.data.DataManager;
import se.edu.inclass.task.Deadline;
import se.edu.inclass.task.Task;
import se.edu.inclass.task.TaskNameComparator;

import javax.sound.midi.SysexMessage;

import java.awt.*;
import java.util.ArrayList;
import java.util.stream.Collectors;

/*
    Add code ot use streams to print task
    Add code to print deadliens using stream
    Add code to
 */

public class Main {

    private TaskNameComparator taskNameComparator;

    public static void main(String[] args) {
        DataManager dm = new DataManager("./data/data.txt");
        ArrayList<Task> tasksData = dm.loadData();

        System.out.println("Printing deadlines");
        printDeadlines(tasksData);

        System.out.println("Total number of deadlines: " + countDeadlines(tasksData));


        printDeadlinesUsingStreams(tasksData);

        String event = "event";
        for(Task t: filterTaskByString(tasksData, "11")){
            System.out.println(t);
        }

        printDataUsingStreams(tasksData);
        printDeadlinesUsingStreamsWithSorting(tasksData);

        System.out.println("TOtal number of deadlines using streams: " + countDeadlinesUsingStreams(tasksData));

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

    public static long countDeadlinesUsingStreams(ArrayList<Task> tasksData) {
        System.out.println("Calculating count using streams:");
        long count;
        count = tasksData.stream()
                .filter((t) -> t instanceof Deadline)
                .count();

        return count;
    }

    public static void printData(ArrayList<Task> tasksData) {
        for (Task t : tasksData) {
            System.out.println(t);
        }
    }

    public static void printDataUsingStreams(ArrayList<Task> tasksData) {
        System.out.println("Print data using streams:");
        tasksData.stream()
                .forEach(System.out::println);
    }

    public static void printDeadlines(ArrayList<Task> tasksData) {
        for (Task t : tasksData) {
            if (t instanceof Deadline) {
                System.out.println(t);
            }
        }
    }


    public static void printDeadlinesUsingStreamsWithSorting(ArrayList<Task> tasksdata) {
        tasksdata.stream()
                .filter((s) -> s instanceof Deadline)
                .sorted((a, b) -> a.getDescription().toLowerCase().compareTo(b.getDescription().toLowerCase()))
                .forEach(System.out::println);
    }

    public static ArrayList<Task> filterTaskByString(ArrayList<Task> tasksData, String filterString) {
        ArrayList<Task> filteredTaskList;
        filteredTaskList= tasksData.stream()
                .filter((s) -> s.getDescription().contains(filterString))
                .collect(toList());

        return filteredTaskList;
    }

    public static void printDeadlinesUsingStreams(ArrayList<Task> tasksData) {
        System.out.println("Printing deadline using streams:");
        tasksData.stream()
                .filter((t) -> t instanceof Deadline)
                .forEach(System.out::println);
    }
    }

