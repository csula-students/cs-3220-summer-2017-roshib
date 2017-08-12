package jaxrs;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.inject.Singleton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jaxrs.FoodEntry;

@Path("")
@Singleton 
public class FoodResource {
    private FoodEntryDao dao = new FoodEntryDao();


    @GET
    @Path("fooditems")
    @Produces(MediaType.APPLICATION_JSON)
    public List<FoodEntry> getMenu() {
        return dao.list();
    }

    @GET
    @Path("fooditem/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public FoodEntry getFoodItemById(@PathParam("id") int id) {
        return dao.get(id).get();
    }

    @POST
    @Path("fooditems")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
 
    public boolean addFoodItem(FoodEntry foodItem) {
        dao.add(foodItem);
        return true;
    }

    @PUT
    @Path("fooditem/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public boolean updateFoodItem(FoodEntry foodItem, @PathParam("id") int id) {
        if (id == foodItem.getId()) {
            dao.update(foodItem);
            return true;
        } else {
            return false;
        }
    }

    @DELETE
    @Path("fooditem/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public boolean deleteFoodItem(@PathParam("id") int id) {
        dao.delete(id);
        return true;
    }
}
