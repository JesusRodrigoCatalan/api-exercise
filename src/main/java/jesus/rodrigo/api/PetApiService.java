package jesus.rodrigo.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import jesus.rodrigo.constants.Constants;
import jesus.rodrigo.models.Pet;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import jesus.rodrigo.models.PetCategory;
import jesus.rodrigo.models.Tag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static io.restassured.RestAssured.given;


public class PetApiService {
    private static final String PETAPIURL="https://petstore.swagger.io/v2/pet";
    private static final String FINDBYSTATUS="/findByStatus";
    ObjectMapper mapper= new ObjectMapper();
    Response response;
    private static final Logger LOGGER = LogManager.getLogger(PetApiService.class);

    public List<Pet> getPetByStatus(String status) throws JsonProcessingException {
        List<Pet> petList;
        String getPetURL=PETAPIURL + FINDBYSTATUS;
        response=given().queryParam(Constants.STATUS, status).when().get(getPetURL);
        LOGGER.info("Getting pet by status: {}", response.getBody().asString());
        petList = mapper.readValue(response.asString(),new TypeReference<List<Pet>>(){});
        return petList;
    }
    public Pet postNewPet(int id) throws JsonProcessingException {
        Pet newPet= createNewPet(id);
        Pet pet;
        String body = mapper.writeValueAsString(newPet);
        LOGGER.info("Sending new availble pet: {}", body);
        response = given().header(Constants.CONTENT_TYPE, Constants.APPLICATION_JSON).body(body).when().
                post(PETAPIURL).then().extract().response();
        LOGGER.info("Getting response from the API {}", response.getBody().prettyPrint());
        pet = mapper.readValue(response.asString(),Pet.class);
        return pet;
    }
    public Pet createNewPet(int id){
        Pet pet= new Pet();
        pet.setId(id);
        pet.setName("Tobi");
        pet.setStatus("available");
        PetCategory category= new PetCategory(3,"big_dogs");
        pet.setCategory(category);

        Tag tag= new Tag();
        tag.setId(5);
        tag.setName("tag1");
        List<Tag> tagList = new ArrayList<>();
        tagList.add(tag);

        pet.setTags(tagList);
        return pet;
    }
    public Response getResponseAfterPost(long id) throws Exception {
        //This for is created to wait for the API to effectively post
        for(int i=0; i<10;i++){
            response=given().given().contentType(ContentType.JSON).get(PETAPIURL + "/" + id);
            if(Constants.RESPONSE_200==response.getStatusCode()){
                break;
            }
            Thread.sleep(1000);
        }
        LOGGER.info("Trying to retrieve posted Pet with Id {}. " +
                        "Response status code {}" +
                        "and response body {}",
                        id, response.getStatusCode(), response.getBody().prettyPrint());
        return response;
    }
    public Response getResponseAfterDelete(long id) throws Exception {
        //This for is created to wait for the API to effectively delete
        for(int i=0; i<10;i++){
            response=given().given().contentType(ContentType.JSON).get(PETAPIURL + "/" + id);
            if(Constants.RESPONSE_404==response.getStatusCode()){
                break;
            }
            Thread.sleep(1000);
        }
        LOGGER.info("Trying to retrieve deleted Pet with Id {}. " +
                        "Response with status code {}" +
                        "and response body {}",
                id, response.getStatusCode(), response.getBody().prettyPrint());
        return response;
    }
    public Pet getPetFromApiById(long id) throws Exception{
        Pet pet;
        for(int i=0; i<10;i++){
            response=given().given().contentType(ContentType.JSON).get(PETAPIURL + "/" + id);
            if(Constants.RESPONSE_200==response.getStatusCode()){
                break;
            }
            Thread.sleep(1000);
        }
        LOGGER.info("Retrieving Pet with Id {}. " +
                        "Response with status code {}" +
                        "and response body {}",
                id, response.getStatusCode(), response.getBody().prettyPrint());
        pet= mapper.readValue(response.asString(),Pet.class);
        return pet;
    }
    public Pet updatePet(Pet pet) throws Exception {
        Pet updatedPet;
        String body = mapper.writeValueAsString(pet);
        response= given().header(Constants.CONTENT_TYPE, Constants.APPLICATION_JSON).body(body).when().
                post(PETAPIURL).then().extract().response();
        LOGGER.info("Updating pet: {}", body);
        updatedPet= mapper.readValue(response.asString(),Pet.class);
        return updatedPet;
    }
    public void deletePet(Pet pet) throws Exception {
        String body = mapper.writeValueAsString(pet);
        response = given().header(Constants.CONTENT_TYPE, Constants.APPLICATION_JSON).body(body).when().
                delete(PETAPIURL + "/" + pet.getId()).then().extract().response();
        LOGGER.info("Deleting pet: {}"+
                "Response with status code {}", body, response.getBody().prettyPrint());
    }


}
