package main;

import response.Deal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class Storage
{
    private static int currentId = 1;
    private static ConcurrentHashMap<Integer, Deal> deals = new ConcurrentHashMap<>();

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

    public static void deleteDeal (int dealId)
    {
        deals.remove(dealId);
    }

    public static void deleteAllDeals ()
    {
        deals.clear();
    }

    public static Deal changeDeal(int dealId, String name, String date)
    {
        Deal deal = deals.get(dealId);
        deal.setDate(date);
        deal.setName(name);
        return deal;
    }
}
