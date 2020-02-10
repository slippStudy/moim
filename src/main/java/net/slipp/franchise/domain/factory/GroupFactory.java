package net.slipp.franchise.domain.factory;

import net.slipp.franchise.domain.Goal;
import net.slipp.franchise.domain.Group;
import net.slipp.franchise.domain.Plan;

public class GroupFactory {

    static Group create(Goal goal, Plan plan) {
        //무슨 그룹이 형성 될 것인가? 클라이언트가 무엇인가?
        return new Group(goal, plan);
    }
}
