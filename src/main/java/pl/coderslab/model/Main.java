package pl.coderslab.model;

import pl.coderslab.dao.AdminDao;
import pl.coderslab.dao.PlanDao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Plan> plans = PlanDao.findAllPlans();
        Iterator<Plan> itr = plans.listIterator();
        while (itr.hasNext()) {
            System.out.println(itr.next());
        }
    }
}
