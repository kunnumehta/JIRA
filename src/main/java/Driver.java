import Model.Status;
import Model.Type;
import Services.TaskCreationService;


public class Driver {

    public static void main(String[] args) throws Exception{
        TaskCreationService taskCreationService = TaskCreationService.getInstance();
        taskCreationService.createSprint("2019-04-09","2019-04-13", "Sprint 1");
        taskCreationService.createTask("Create Dashboard","Brad", "Peter", Status.OPEN, "2019-04-12", Type.FEATURE,
                "Create console for debugging,LOW", null);
        taskCreationService.createTask("Fix mySql issue","Ryan", "Ryan", Status.IN_PROGRESS, "2019-04-14", Type.BUG,
                "P0", "Sprint 1");
        taskCreationService.createTask("Create a microservice","Amy", "Ryan", Status.COMPLETED, "2019-03-12", Type.STORY,
                "Add logging to feature" , "Sprint 1");
        taskCreationService.createTask("Setup Console","Ryan", "Ryan", Status.IN_PROGRESS, "2019-04-14", Type.FEATURE,
                "Create console for debugging,HIGH", null);
        taskCreationService.createTask("Console Api","Ryan", "Ryan", Status.IN_PROGRESS, "2019-04-14", Type.FEATURE,
                "Create api for console,HIGH", null);
        taskCreationService.createTask("Bug2","Ryan", "Ryan", Status.IN_PROGRESS, "2019-04-14", Type.BUG,
                "P1", null);

        taskCreationService.createSubtrack("Development", Status.OPEN, "Create a microservice");
        taskCreationService.createSubtrack("Unit test", Status.OPEN, "Create a microservice");
        taskCreationService.createSubtrack("Integration Test", Status.OPEN, "Create a microservice");
        taskCreationService.changeSubTrackStatus("Development", Status.COMPLETED);
        taskCreationService.changeTaskStatus("Console Api", Status.COMPLETED);
        taskCreationService.displayTasksOfUser("Ryan");
        System.out.println();
        taskCreationService.displayTasksOfUser("Peter");
        taskCreationService.displaySprintSnapshot("Sprint 1");
        taskCreationService.addTaskToSprint("Sprint 1", "Console Api");
        taskCreationService.removeTaskFromSprint("Sprint 1", "Create a microservice");
        taskCreationService.changeAssigneeOfTask("Fix mySql issue", "Brad");
        taskCreationService.displayTasksOfUser("Ryan");
        System.out.println();
        taskCreationService.displayTasksOfUser("Brad");
        taskCreationService.displaySprintSnapshot("Sprint 1");
    }
}
