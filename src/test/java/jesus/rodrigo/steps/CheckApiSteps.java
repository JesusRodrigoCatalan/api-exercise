package jesus.rodrigo.steps;

import io.restassured.response.Response;
import jesus.rodrigo.api.PetApiService;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import jesus.rodrigo.constants.Constants;
import jesus.rodrigo.models.Pet;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertEquals;

import java.util.List;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "html:target/cucumber", "json:target/cucumber-report.json"},
        features={"src/test/resources/features"},
        glue = {"jesus.rodrigo.steps"}
)

public class CheckApiSteps {
    List<Pet> petList;
    PetApiService petApiService= new PetApiService();
    Pet pet;

    @When("I get {string} pets")
    public void getPetByStatus(String status) throws Exception {
        this.petList=petApiService.getPetByStatus(status);
    }

    @Then("I assert I get {string} pets as expected")
    public void assertPetsStatuses(String status){
        for (Pet pet : petList){
            assertEquals(pet.getStatus(),status);
        }
    }

    @When("I send a POST with a new pet with id {int}")
    public void postNewPet(int id) throws Exception {
        this.pet=petApiService.postNewPet(id);
    }

    @Then("I assert the new pet is added")
    public void assertNewPetIsAdded() throws Exception {
        Response response=petApiService.getResponseAfterPost(pet.getId());
        assertEquals(Constants.RESPONSE_200, response.getStatusCode());
    }

    @When("I send an UPDATE to the new pet with id {int} and new status {string}")
    public void updateNewPetStatus(int id, String newStatus) throws Exception {
        this.pet=petApiService.getPetFromApiById(id);
        this.pet.setStatus(newStatus);
        this.pet=petApiService.updatePet(this.pet);
    }

    @Then("I assert the existing pet is updated to {string}")
    public void assertExistingPetStatus(String newStatus){
        assertEquals(newStatus, this.pet.getStatus());
    }

    @When("I send a DELETE the new pet with id {int}")
    public void deleteExistingPet(int id) throws Exception {
        this.pet=petApiService.getPetFromApiById(id);
        petApiService.deletePet(this.pet);
    }

    @Then ("I assert the existing pet is deleted")
    public void assertDeletedPet() throws Exception {
        Response response=petApiService.getResponseAfterDelete(this.pet.getId());
        assertEquals(Constants.RESPONSE_404, response.getStatusCode());
    }

}



