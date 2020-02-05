package main;

import response.Deal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Storage
{
    private static int currentId = 1;
    private static HashMap<Integer, Deal> deals = new HashMap<>();

    public static List<Deal> getAllDeals()
    {
        ArrayList<Deal> dealsList = new ArrayList<>();
        dealsList.addAll(deals.values());
        return dealsList;
    }

    public static int addDeal (Deal deal)
    {
        int id = currentId++;
        deal.setId(id);
        deals.put(id, deal);
        return id;
    }

    public static Deal getDeal (int dealId)
    {
        if (deals.containsKey(dealId))
        {
            return deals.get(dealId);
        }
        return null;
    }

    public static void deleteDeal (Deal deal)
    {
        deals.remove(deal.getId());
    }

    public static void deleteAllDeals ()
    {
        deals.clear();
    }
}
