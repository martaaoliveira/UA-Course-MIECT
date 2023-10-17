package lab07.ex1;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        EmployeeInterface emp1 = new Employee("Lara");
        TeamMember member1 = new TeamMember(new Employee("Paulo"));
        TeamLeader leader1 = new TeamLeader(new Employee("António"));
        TeamLeader leader2 = new TeamLeader(emp1);
        TeamLeader leader3 = new TeamLeader(member1);
        Manager manager1 = new Manager(emp1);
        Manager manager2 = new Manager(new TeamLeader(new TeamMember(new Employee("Beatriz"))));

        EmployeeInterface list[] = {emp1, member1, leader1, leader2, leader3, manager1, manager2};

        for(EmployeeInterface emp_inter : list)
            emp_inter.work();

        member1.start(new Date());
        member1.work();
        member1.colaborate();
        leader1.plan();
        member1.terminate(new Date());
        manager1.manage();
        System.out.println();

    }
    
}
