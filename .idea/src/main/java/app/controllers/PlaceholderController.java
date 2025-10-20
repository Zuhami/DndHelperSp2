package app.controllers.impl;

import app.config.HibernateConfig;
import app.controllers.IController;
import app.daos.impl.PlaceholderDAO; // TODO: Ret DAO
import app.dtos.PlaceholderDTO;
import app.entities.PlaceholderEntity; // TODO: Ret entity
import io.javalin.http.Context;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class PlaceholderController implements IController<PlaceholderDTO, Integer> { // TODO: Ret navne

    private final PlaceholderDAO dao;

    public PlaceholderController() {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();
        this.dao = PlaceholderDAO.getInstance(emf);
    }

    @Override
    public void read(Context ctx) {
        int id = ctx.pathParamAsClass("id", Integer.class).check(this::validatePrimaryKey, "Not a valid id").get();
        PlaceholderDTO dto = dao.read(id);
        ctx.res().setStatus(200);
        ctx.json(dto, PlaceholderDTO.class);
    }

    @Override
    public void readAll(Context ctx) {
        List<PlaceholderDTO> dtos = dao.readAll();
        ctx.res().setStatus(200);
        ctx.json(dtos, PlaceholderDTO.class);
    }

    @Override
    public void create(Context ctx) {
        PlaceholderDTO jsonRequest = ctx.bodyAsClass(PlaceholderDTO.class);
        PlaceholderDTO dto = dao.create(jsonRequest);
        ctx.res().setStatus(201);
        ctx.json(dto, PlaceholderDTO.class);
    }

    @Override
    public void update(Context ctx) {
        int id = ctx.pathParamAsClass("id", Integer.class).check(this::validatePrimaryKey, "Not a valid id").get();
        PlaceholderDTO dto = dao.update(id, validateEntity(ctx));
        ctx.res().setStatus(200);
        ctx.json(dto, PlaceholderEntity.class);
    }

    @Override
    public void delete(Context ctx) {
        int id = ctx.pathParamAsClass("id", Integer.class).check(this::validatePrimaryKey, "Not a valid id").get();
        dao.delete(id);
        ctx.res().setStatus(204);
    }

    @Override
    public boolean validatePrimaryKey(Integer integer) {
        return dao.validatePrimaryKey(integer);
    }

    @Override
    public PlaceholderDTO validateEntity(Context ctx) {
        // TODO: Ret valideringsregler
        return ctx.bodyValidator(PlaceholderDTO.class)
                .check(p -> p.getName() != null && !p.getName().isEmpty(), "Name must be set")
                .check(p -> p.getAddress() != null && !p.getAddress().isEmpty(), "Address must be set")
                .check(p -> p.getType() != null, "Type must be set")
                .get();
    }

    public void populate(Context ctx) {
        dao.populate();
        ctx.res().setStatus(200);
        ctx.json("{ \"message\": \"Database has been populated\" }");
    }
}
