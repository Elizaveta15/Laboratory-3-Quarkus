package org.acme;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.ArrayList;

@Path("/animal")
public class AnimalResource {
    private final ArrayList<Animal> animals = new ArrayList<>();

    public AnimalResource() {
        animals.add(new Animal(10, "Mouse"));
        animals.add(new Animal(11, "Dog"));
        animals.add(new Animal(12, "Cat"));
    }

    @GET
    @Path("/main")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        System.out.println("res");
        return "About animals";
    }

    /**
     * GET-query without param
     */
    @GET
    @Path("/getOne")
    public Animal getOne() {
        return new Animal(0, "Fox");
    }

    /**
     * GET-query with PathParam
     */
    @GET
    @Path("/getOnePath/{name}")
    public Animal getOnePath(@PathParam("name") String name) {
        return new Animal(name);
    }

    /**
     * GET-query with QueryParam
     */
    @GET
    @Path("/getOneQuery")
    public Animal getOneQuery(@QueryParam("name") String name) {
        return new Animal(name);
    }

    /**
     * POST-query Object -> Object
     */
    @POST
    @Path("/postOneShowOne")
    public Animal postOneShowOne(Animal animal) {
        return new Mammal(animal);
    }

    /**
     * POST-query Object -> ListObject
     */
    @POST
    @Path("/postOneShowList")
    public ArrayList<Mammal> postOneShowList(Animal animal) {
        ArrayList<Mammal> mammals = new ArrayList<>();
        mammals.add(new Mammal(animal));
        mammals.add(new Mammal(animal));
        mammals.add(new Mammal(animal));
        return mammals;
    }

    /**
     * POST-query ListObject -> Object
     */
    @POST
    @Path("/postListShowOne")
    public Animal postListShowOne(ArrayList<Animal> animals) {
        return animals.get(1);
    }

    /**
     * POST-query ListObject -> Object from In-Memory Collection
     */
    @POST
    @Path("/postMemory")
    public ArrayList<Animal> listCollection(Animal animal) {
        animals.add(animal);
        return animals;
    }

    /**
     * PUT-query ListObject -> Object from In-Memory Collection
     */
    @PUT
    @Path("/putMemory")
    public ArrayList<Animal> putMemory(Animal animal) {
        if (animals.stream().noneMatch(item -> item.getName().equals(animal.getName()))) {
            animals.add(new Animal(animal));
        }
        return animals;
    }

    /**
     * DELETE-query ListObject -> Object from In-Memory Collection
     */
    @DELETE
    @Path("/deleteMemory")
    public ArrayList<Animal> deleteMemory(@QueryParam("id") Integer id) {
        animals.removeIf(existingAnimal -> existingAnimal.getId().equals(id));
        return animals;
    }
}