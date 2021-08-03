package Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Sprint {
    private Date startDate;
    private Date endDate;
    private String name;
    List<Task> sprintTasks;

    public Sprint(Date start, Date end, String name) {
        this.endDate = end;
        this.startDate = start;
        this.name = name;
        this.sprintTasks = new ArrayList<>();
    }
}
