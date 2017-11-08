package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.inject.Inject;
import dtos.CustomerDTO;
import models.Customer;
import play.libs.Json;
import play.db.jpa.JPAApi;
import play.mvc.*;
import utils.utilities.PojoUtils;

import java.io.IOException;

import static mapper.DTOMapper.toCustomerDTO;
import static mapper.DTOMapper.toCustomerEntity;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

    private JPAApi jpaApi;

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */

    @Inject
    public HomeController(JPAApi jpaApi) {
        this.jpaApi = jpaApi;
    }

    public Result index() {
        JsonNode json = request().body().asJson();
        String name = json.findPath("name").textValue();
        if(name == null) {
            return badRequest("Missing parameter [name]");
        } else {
            return ok("Hello " + name);
        }
    }

    public Result createCustomer(){
        JsonNode jsonNode = request().body().asJson();
        if(jsonNode == null) {
            return badRequest("Expecting Json data");
        }
        CustomerDTO customerDTO;
        try {
            customerDTO = Json.fromJson(jsonNode, CustomerDTO.class);
            Customer entity = toCustomerEntity(customerDTO);
            jpaApi.withTransaction(() -> jpaApi.em().persist(entity));
            customerDTO = toCustomerDTO(entity);
            jsonNode = Json.toJson(customerDTO);
            return ok(jsonNode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return created();
    }
}
