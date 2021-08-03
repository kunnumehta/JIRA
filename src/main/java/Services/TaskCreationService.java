package Services;

import Model.*;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

public class TaskCreationService {

    private static TaskCreationService taskCreationService;
    private Map<String, Task> tasksMap;
    private Map<String, Subtrack> subtrackMap;
    private Map<String, Sprint> sprintMap;

    private TaskCreationService() {
        tasksMap = new LinkedHashMap<>();
        subtrackMap = new LinkedHashMap<>();
        sprintMap = new LinkedHashMap<>();
    }

    public static TaskCreationService getInstance(){
        if(taskCreationService == null){
            taskCreationService = new TaskCreationService();
        }
        return taskCreationService;
    }


    public void createTask(String title, String creator, String assignee, Status status, String date, Type type,
                           String additionalFeatures, String sprintName) throws Exception{
        try {
            Date dateObject = new SimpleDateFormat("yyyy-MM-dd").parse(date);
            String[] additionalFeature = additionalFeatures.split(",");
            Task task;
            switch (type) {
                case FEATURE:
                    task = new Feature(title, creator, assignee, status, type, dateObject, additionalFeature[0],
                            Impact.valueOf(additionalFeature[1]));
                    tasksMap.put(title, task);
                    if (sprintName != null) {
                        Sprint sprint = sprintMap.get(sprintName);
                        sprint.getSprintTasks().add(task);
                        task.setSprint(sprint);
                    }
                    break;
                case BUG:
                    task = new Bug(title, creator, assignee, status, type, dateObject, Severity.valueOf(additionalFeature[0]));
                    tasksMap.put(title, task);
                    if (sprintName != null) {
                        Sprint sprint = sprintMap.get(sprintName);
                        sprint.getSprintTasks().add(task);
                        task.setSprint(sprint);
                    }
                    break;
                case STORY:
                    task = new Story(title, creator, assignee, status, type, dateObject, additionalFeature[0]);
                    tasksMap.put(title, task);
                    if (sprintName != null) {
                        Sprint sprint = sprintMap.get(sprintName);
                        sprint.getSprintTasks().add(task);
                        task.setSprint(sprint);
                    }
                    break;
                default:
                    break;
            }
        } catch( Exception e) {
            System.out.println("exception " + e.getMessage());
        }
    }

    public void createSubtrack(String title, Status status, String taskName){
        Subtrack subtrack = new Subtrack(title,status);
        subtrackMap.put(title, subtrack);
        Task task = tasksMap.get(taskName);
        if(task instanceof Story){
            ((Story) task).getSubtracks().add(subtrack);
        }
    }

    public void changeTaskStatus(String taskName, Status status){
        tasksMap.get(taskName).setStatus(status);
    }

    public void changeTaskStatus(String taskName){
        //tasksMap.get(taskName).setStatus(status);
    }

    public void changeSubTrackStatus(String subTrackName, Status status){
        subtrackMap.get(subTrackName).setStatus(status);
    }

    public void changeAssigneeOfTask(String taskName, String assignee){
        tasksMap.get(taskName).setAssignee(assignee);
    }

    public void createSprint(String startDate, String endDate, String name) throws Exception{
        Date start =new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
        Date end =new SimpleDateFormat("yyyy-MM-dd").parse(endDate);
        Sprint sprint = new Sprint(start,end,name);
        sprintMap.put(name, sprint);
    }

    public void addTaskToSprint(String sprintName, String taskName){
        sprintMap.get(sprintName).getSprintTasks().add(tasksMap.get(taskName));
    }

    public void removeTaskFromSprint(String sprintName, String taskName){
        if(sprintMap.get(sprintName).getSprintTasks().contains(tasksMap.get(taskName)))
        sprintMap.get(sprintName).getSprintTasks().remove(tasksMap.get(taskName));
    }

    public void displayTasksOfUser(String username){
        for(Map.Entry<String, Task> taskEntry : tasksMap.entrySet()) {
            if(taskEntry.getValue().getAssignee() !=null && taskEntry.getValue().getAssignee().equals(username)){
                System.out.println("Task type => " + taskEntry.getValue().getType());
                System.out.println("Title => " + taskEntry.getValue().getTitle());
                if(taskEntry.getValue().getSprint() != null){
                    System.out.println("Sprint => " + taskEntry.getValue().getSprint().getName());
                } else {
                    System.out.println("Sprint => ");
                }
                if(taskEntry.getValue().getType().equals(Type.STORY)){
                    if( !((Story) taskEntry.getValue()).getSubtracks().isEmpty()){
                        System.out.println("SubTrack: ");
                        displaySubtracks((Story)taskEntry.getValue());
                    }
                }
            }
        }
    }

    public void displaySprintSnapshot(String sprintName){
        System.out.println("On track tasks");
        for(Task task : sprintMap.get(sprintName).getSprintTasks()) {
            if(task.getDueDate().before(sprintMap.get(sprintName).getEndDate())) {
                System.out.println(task.getTitle());
            }

        }
        System.out.println("Delayed tasks");
        for(Task task : sprintMap.get(sprintName).getSprintTasks()) {
            if (!task.getDueDate().before(sprintMap.get(sprintName).getEndDate()) && !task.getStatus().equals(Status.COMPLETED)) {
                System.out.println(task.getTitle());
            }
        }
    }

    public void displaySubtracks(Story task){
        for(Subtrack subtrack : task.getSubtracks()) {
            System.out.println(subtrack.getTitle());
        }
    }
}
