package com.sao.stream;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author saozdemir
 * @project SpringBootWorkspace
 * @date 18 Şub 2026
 * <p>
 * @description:
 */
public class StreamTest {
    public static void main(String[] args) {
        List<AssignedTraining> selectedTrainingList = new ArrayList<>();
        List<AssignedTraining> trainingList = new ArrayList<>();
        Set<AssignedTraining> assignedTrainingsSet = new HashSet<>();
        selectedTrainingList.add(new AssignedTraining(1L, "Java"));
        selectedTrainingList.add(new AssignedTraining(2L, "Python"));
        selectedTrainingList.add(new AssignedTraining(3L, "JavaScript"));
        selectedTrainingList.add(new AssignedTraining(null, "Java"));
        selectedTrainingList.add(new AssignedTraining(4L, "Python"));
        selectedTrainingList.add(new AssignedTraining(4L, "C++"));

        trainingList.add(new AssignedTraining(5L, "F"));
        trainingList.add(new AssignedTraining(1L, "Java"));
        trainingList.add(new AssignedTraining(2L, "Python"));
        trainingList.add(new AssignedTraining(3L, "JavaScript"));

        /** Önce iki listeyi birleştir. Sonra stream ile Set e dönüştür.*/
        trainingList.addAll(selectedTrainingList);

        assignedTrainingsSet = trainingList.stream()
                .filter(at -> at.getId() != null)
                .collect(Collectors.toMap(AssignedTraining::getName, assignedTraining -> assignedTraining, (existing, replacement) -> existing))
                .values()
                .stream()
                .collect(Collectors.toSet());


            assignedTrainingsSet.forEach(training -> System.out.println("Id: " + training.getId() + ", Name: " + training.getName()));

    }
}
